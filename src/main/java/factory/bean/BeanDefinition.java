package factory.bean;

import factory.support.PropertyValues;

public interface BeanDefinition {

    Class getBeanClass();

    PropertyValues getPropertyValues();

    String getInitMethodName();

    void setInitMethodName(String initMethodName);

    String getDestroyMethodName();

    void setDestroyMethodName(String destroyMethodName);

    void setScope(String scope);

    boolean isSingleton();

    void setSingleton(boolean singleton);

    boolean isPrototype();

    void setPrototype(boolean prototype);
}
