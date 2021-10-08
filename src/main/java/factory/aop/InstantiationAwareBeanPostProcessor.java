package factory.aop;

import factory.extension.BeanPostProcessor;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {
    Object postProcessorBeforeInstantiation(Class<?> beanClass, String beanName);
}
