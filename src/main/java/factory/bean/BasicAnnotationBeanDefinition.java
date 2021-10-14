package factory.bean;


import factory.support.PropertyValues;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class BasicAnnotationBeanDefinition extends BasicBeanDefinition implements AnnotationBeanDefinition {

    private String configurationName;

    private String configurationBeanMethod;

    private Class[] configBeanMethodParamTypes;

    private List<String> configBeanMethodParamRefs;


    public BasicAnnotationBeanDefinition(Class beanClass, PropertyValues propertyValues) {
        super(beanClass, propertyValues);
    }

    public BasicAnnotationBeanDefinition(Class beanClass) {
        super(beanClass);
    }

    @Override
    public String getConfigurationName() {
        return configurationName;
    }

    @Override
    public void setConfigurationName(String configurationName) {
        this.configurationName = configurationName;
    }

    @Override
    public String getConfigurationBeanMethod() {
        return configurationBeanMethod;
    }

    @Override
    public void setConfigurationBeanMethod(String configurationBeanMethod) {
        this.configurationBeanMethod = configurationBeanMethod;
    }

    @Override
    public Class[] getConfigBeanMethodParamTypes() {
        return configBeanMethodParamTypes;
    }

    @Override
    public void setConfigBeanMethodParamTypes(Class[] configBeanMethodParamTypes) {
        this.configBeanMethodParamTypes = configBeanMethodParamTypes;
    }

    @Override
    public List<String> getConfigBeanMethodParamRefs() {
        return configBeanMethodParamRefs;
    }

    @Override
    public void setConfigBeanMethodParamRefs(List<String> configBeanMethodParamRefs) {
        this.configBeanMethodParamRefs = configBeanMethodParamRefs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BasicAnnotationBeanDefinition that = (BasicAnnotationBeanDefinition) o;

        return new EqualsBuilder().appendSuper(super.equals(o)).append(configurationName, that.configurationName).append(configurationBeanMethod, that.configurationBeanMethod).append(configBeanMethodParamTypes, that.configBeanMethodParamTypes).append(configBeanMethodParamRefs, that.configBeanMethodParamRefs).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).appendSuper(super.hashCode()).append(configurationName).append(configurationBeanMethod).append(configBeanMethodParamTypes).append(configBeanMethodParamRefs).toHashCode();
    }
}
