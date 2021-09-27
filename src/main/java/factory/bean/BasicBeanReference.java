package factory.bean;

public class BasicBeanReference implements BeanReference {
    private String beanName;

    public BasicBeanReference(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
