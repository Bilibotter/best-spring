package factory.bean;

import java.util.List;

public interface AnnotationBeanDefinition extends BeanDefinition {
    void setConfigurationName(String configurationName);
    String getConfigurationName();
    void setConfigurationBeanMethod(String configurationBeanMethod);
    String getConfigurationBeanMethod();
    void setConfigBeanMethodParamTypes(Class[] classes);
    Class[] getConfigBeanMethodParamTypes();
    void setConfigBeanMethodParamRefs(List<String> configBeanMethodParamRefs);
    List<String> getConfigBeanMethodParamRefs();
}
