package factory.bean;

import factory.annotation.StringValueResolver;
import factory.extension.BeanPostProcessor;
import factory.factory.BeanFactory;

public interface ConfigurableBeanFactory extends BeanFactory, SingletonBeanRegistry{
    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    void destroySingletons();

    void addEmbeddedValueResolver(StringValueResolver resolver);

    String resolveEmbeddedValue(String value);
}
