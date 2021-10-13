package factory.bean;


import factory.support.PropertyValues;

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
}
