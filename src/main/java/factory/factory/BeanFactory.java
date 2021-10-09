package factory.factory;

public interface BeanFactory {
    Object getBean(String name) throws Exception;
    Object getBean(String name, Object... args) throws Exception;
    <T> T getBean(String name, Class<T> requiredType) throws Exception;
    <T> T getBean(Class<T> requiredType);
}
