package factory.bean;

import factory.bean.SingletonBeanRegistry;
import factory.extension.DisposableBean;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    private final Map<String, DisposableBean> disposableBeans = new ConcurrentHashMap<>(16);

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
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
