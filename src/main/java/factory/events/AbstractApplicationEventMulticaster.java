package factory.events;

import factory.bean.BeanFactoryAware;
import factory.factory.BeanFactory;
import factory.util.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {
    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = Collections.synchronizedSet(new LinkedHashSet<>(16));
    private BeanFactory beanFactory;

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event) {
        return applicationListeners.stream()
                .filter(it->supportsEvent(it, event))
                .collect(Collectors.toList());
    }

    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
        Class<? extends ApplicationListener> listenerClass = applicationListener.getClass();
        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
        Type genericInterface = targetClass.getGenericInterfaces()[0];
        Type temp = ((ParameterizedType) genericInterface).getRawType();
        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("wrong event class name: "+className, e);
        }
        return eventClassName.isAssignableFrom(event.getClass());
    }

    /*
    public static void main(String[] args) {
        ApplicationListener eventListener = new ApplicationListenerImpl();
        ApplicationListener subEventListener = new ApplicationListenerImpl();
        SubApplicationEvent subEvent = new SubApplicationEvent("Source");
        AbstractApplicationEventMulticaster.supportsEvent(eventListener, subEvent);
        AbstractApplicationEventMulticaster.supportsEvent(subEventListener, subEvent);
        return;
    }

     */
}
