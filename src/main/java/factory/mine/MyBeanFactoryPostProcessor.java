package factory.mine;

import factory.bean.BeanDefinition;
import factory.bean.ConfigurableListableBeanFactory;
import factory.factory.BeanFactoryPostProcessor;
import factory.support.PropertyValue;
import factory.support.PropertyValues;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessorBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }
}
