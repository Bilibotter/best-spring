package factory.factory;

import factory.bean.BeanDefinition;
import factory.bean.BeanDefinitionRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    public void registryBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String name) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        assert (beanDefinition != null);
        return beanDefinition;
    }
}
