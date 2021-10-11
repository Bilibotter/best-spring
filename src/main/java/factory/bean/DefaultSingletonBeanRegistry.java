package factory.bean;

import factory.bean.SingletonBeanRegistry;
import factory.extension.DisposableBean;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>(16);

    protected final Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>(16);

    private final Map<String, ObjectFactory<?>> singletonFactories = new ConcurrentHashMap<>(16);

    private final Map<String, DisposableBean> disposableBeans = new ConcurrentHashMap<>(16);

    @Override
    public Object getSingleton(String beanName) {
        Object singletonObject = singletonObjects.get(beanName);
        if (singletonObject != null) {
            return singletonObject;
        }
        singletonObject = earlySingletonObjects.get(beanName);
        if (singletonObject == null) {
            ObjectFactory<?> objectFactory = singletonFactories.get(beanName);
            if (objectFactory != null) {
                singletonObject = singletonFactories.remove(beanName).getObject();
                earlySingletonObjects.put(beanName, singletonObject);
            }
        }
        return singletonObject;
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
        earlySingletonObjects.remove(beanName);
        singletonFactories.remove(beanName);
    }

    protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory) {
        if (!singletonObjects.containsKey(beanName)) {
            singletonFactories.put(beanName, singletonFactory);
            earlySingletonObjects.remove(beanName);
        }
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    public void destroySingletons() {
        for(String key : disposableBeans.keySet()) {
            DisposableBean disposableBean = disposableBeans.remove(key);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new RuntimeException("Destroy method on bean with name "+key+" threw an exception", e);
            }
        }
    }
}
