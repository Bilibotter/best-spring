package factory.bean;

import factory.extension.BeanPostProcessor;
import factory.extension.ListableBeanFactory;
import factory.factory.AbstractAutowireCapableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    BeanDefinition getBeanDefinition(String beanName);

    void preInstantiateSingletons();

    @Override
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
