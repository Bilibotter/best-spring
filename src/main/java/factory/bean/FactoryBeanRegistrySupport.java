package factory.bean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {

    protected static final Object NULL_OBJECT = new Object();

    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>(16);

    protected Object getCacheObjectForFactoryBean(String beanName) {
        Object object = factoryBeanObjectCache.get(beanName);
        // object的equals与==等价
        return object == NULL_OBJECT ? null : object;
    }

    protected Object getObjectFromFactoryBean(FactoryBean factory, String beanName) {
        Object object = this.factoryBeanObjectCache.get(beanName);
        if (object != null) {
            return object;
        }
        object = this.doGetObjectFromFactoryBean(factory, beanName);
        if (factory.isSingleton()) {
            this.factoryBeanObjectCache.put(beanName, object);
        }
        return object == NULL_OBJECT ? null : object;
    }

    private Object doGetObjectFromFactoryBean(final FactoryBean factory, final String beanName) {
        try {
            return factory.getObject();
        } catch (Exception e) {
            throw new RuntimeException("FactoryBean threw exception on object["+beanName+"] creation", e);
        }
    }
}
