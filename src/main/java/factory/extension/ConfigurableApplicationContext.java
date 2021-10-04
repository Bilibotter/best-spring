package factory.extension;

public interface ConfigurableApplicationContext extends ApplicationContext {
    void refresh();

    void registerShutdownHook();

    void close();
}
