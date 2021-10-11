package factory.aop;

import factory.extension.BeanPostProcessor;
import factory.support.PropertyValues;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    Object postProcessorBeforeInstantiation(Class<?> beanClass, String beanName);

    boolean postProcessAfterInstantiation(Object bean, String beanName);

    PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws Exception;

    default Object getEarlyBeanReference(Object bean, String beanName) {
        return bean;
    }

}
