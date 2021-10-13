package factory.config;

import factory.bean.BeanDefinitionRegistry;
import factory.bean.ConfigurableListableBeanFactory;
import factory.factory.BeanFactoryPostProcessor;
import factory.factory.DefaultListableBeanFactory;


public class ConfigurationClassPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessorBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;
    }
}
