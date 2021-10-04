package factory.bean;

import factory.extension.BeanPostProcessor;
import factory.factory.BeanFactory;

public interface ConfigurableBeanFactory extends BeanFactory, SingletonBeanRegistry{
    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    void destroySingletons();
}
