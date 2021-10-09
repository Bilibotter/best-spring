package factory.bean;

import factory.support.PropertyValue;
import factory.support.PropertyValues;

import java.util.Properties;

public class BasicBeanDefinition implements BeanDefinition {

    String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;

    String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;

    private Class beanClass;

    private PropertyValues propertyValues;

    private String initMethodName;

    private String destroyMethodName;

    private String scope = SCOPE_SINGLETON;

    private boolean singleton = true;

    private boolean prototype = false;

    public BasicBeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues;
    }

    public BasicBeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    @Override
    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    @Override
    public String getInitMethodName() {
        return initMethodName;
    }

    @Override
    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    @Override
    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    @Override
    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

    public String getScope() {
        return scope;
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public boolean isSingleton() {
        return singleton;
    }

    @Override
    public void setSingleton(boolean singleton) {
        this.singleton = singleton;
    }

    @Override
    public boolean isPrototype() {
        return prototype;
    }

    @Override
    public void setPrototype(boolean prototype) {
        this.prototype = prototype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BasicBeanDefinition that = (BasicBeanDefinition) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder().append(singleton, that.singleton).append(prototype, that.prototype).append(beanClass, that.beanClass).append(propertyValues, that.propertyValues).append(initMethodName, that.initMethodName).append(destroyMethodName, that.destroyMethodName).append(scope, that.scope).isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37).append(beanClass).append(propertyValues).append(initMethodName).append(destroyMethodName).append(scope).append(singleton).append(prototype).toHashCode();
    }
}
