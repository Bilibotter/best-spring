package factory.factory;

import factory.bean.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {
    void postProcessorBeanFactory(ConfigurableListableBeanFactory beanFactory);
}
