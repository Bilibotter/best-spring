package factory.bean;

public interface FactoryBean<T> {
    T getObject();
    Class<?> getObjectType();
    boolean isSingleton();
}
