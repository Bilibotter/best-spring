package factory.context;

import factory.bean.ConfigurableListableBeanFactory;
import factory.events.*;
import factory.extension.BeanPostProcessor;
import factory.factory.BeanFactoryPostProcessor;
import factory.factory.DefaultListableBeanFactory;
import factory.io.DefaultResourceLoader;
import factory.mine.ApplicationContextAwarePostProcessor;

import java.util.Collection;
import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";
    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() {
        refreshBeanFactory();
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        invokeBeanFactoryPostProcessors(beanFactory);
        beanFactory.addBeanPostProcessor(new ApplicationContextAwarePostProcessor(this));
        registerBeanFactory(beanFactory);
        initApplicationEventMultiCaster();
        registerListener();
        beanFactory.preInstantiateSingletons();
        finishRefresh();
    }

    protected abstract void refreshBeanFactory();

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void initApplicationEventMultiCaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new BasicApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    private void registerListener() {
        // getBeansOfType是使用BeanDefinition来查找的
        // class对应上则使用getBean
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener listener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessorBeanFactory(beanFactory);
        }
    }

    private void registerBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public void close() {
        publishEvent(new ContextClosedEvent(this));
        getBeanFactory().destroySingletons();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws Exception {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws Exception {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws Exception {
        return getBeanFactory().getBean(name, requiredType);
    }
}
