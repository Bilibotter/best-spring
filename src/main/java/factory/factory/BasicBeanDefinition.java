package factory.factory;

public class BasicBeanDefinition implements BeanDefinition {
    private Class beanClass;

    public BasicBeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
