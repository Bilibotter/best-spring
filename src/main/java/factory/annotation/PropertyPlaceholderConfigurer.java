package factory.annotation;

import factory.bean.BeanDefinition;
import factory.bean.ConfigurableListableBeanFactory;
import factory.factory.BeanFactoryPostProcessor;
import factory.io.DefaultResourceLoader;
import factory.io.Resource;
import factory.support.PropertyValue;
import factory.support.PropertyValues;

import java.io.IOException;
import java.util.Properties;

public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;

    @Override
    public void postProcessorBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        try {
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());
            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanDefinitionName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
                // PropertyValue可用于接收属性填充的对象
                // PropertyValues是PropertyValue的容器
                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)) {
                        continue;
                    }
                    String strVal = (String) value;
                    StringBuffer buffer = new StringBuffer(strVal);
                    int startIdx = buffer.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
                    int endIdx = buffer.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
                    if (startIdx != -1 && endIdx != -1 && startIdx < endIdx) {
                        String propKey = strVal.substring(startIdx+2, endIdx);
                        String propValue = properties.getProperty(propKey);
                        buffer.replace(startIdx+2, endIdx, propValue);
                        propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), buffer.toString()));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not load properties", e);
        }
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
