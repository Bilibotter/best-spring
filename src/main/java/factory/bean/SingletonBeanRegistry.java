package factory.bean;

public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void registerSingleton(String beanName, Object singletonObject);
}
