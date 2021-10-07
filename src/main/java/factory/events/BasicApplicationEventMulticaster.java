package factory.events;

import factory.factory.BeanFactory;

public class BasicApplicationEventMulticaster extends AbstractApplicationEventMulticaster {
    public BasicApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(final ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
