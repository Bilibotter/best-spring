package factory.bean;

public interface BeanClassLoaderAware extends Aware {
    void setClassLoader(ClassLoader classLoader);
}
