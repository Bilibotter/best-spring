package factory.bean;

import factory.support.PropertyValues;

public interface BeanDefinition {
    Class getBeanClass();

    PropertyValues getPropertyValues();
}
