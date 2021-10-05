package factory.bean;

import factory.factory.BeanFactory;

public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory);
}
