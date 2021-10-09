package factory.aop;

import factory.extension.BeanPostProcessor;
import factory.support.PropertyValues;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    Object postProcessorBeforeInstantiation(Class<?> beanClass, String beanName);

    PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws Exception;

}
