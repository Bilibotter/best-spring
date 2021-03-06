package factory.factory;

import factory.annotation.StringValueResolver;
import factory.bean.BeanDefinition;
import factory.bean.ConfigurableBeanFactory;
import factory.bean.FactoryBean;
import factory.bean.FactoryBeanRegistrySupport;
import factory.extension.BeanPostProcessor;
import factory.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    private final List<StringValueResolver> embeddedValueResolvers = new ArrayList<>();

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    @Override
    public Object getBean(String name){
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws Exception {
        return doGetBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        return (T) getBean(name);
    }

    protected <T> T doGetBean(final String name, final Object[] args){
        Object sharedInstance = getSingleton(name);
        if (sharedInstance == null) {
            BeanDefinition beanDefinition = getBeanDefinition(name);
            sharedInstance = createBean(name, beanDefinition, args);
        }
        return (T) getObjectForBeanInstance(sharedInstance, name);
    }

    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        if (! (beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }
        Object object = getCacheObjectForFactoryBean(beanName);
        if (object == null) {
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factoryBean, beanName);
        }
        return object;
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args);

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    @Override
    public void addEmbeddedValueResolver(StringValueResolver resolver) {
        this.embeddedValueResolvers.add(resolver);
    }

    @Override
    public String resolveEmbeddedValue(String value) {
        String result = value;
        for (StringValueResolver resolver : embeddedValueResolvers) {
            result = resolver.resolveStringValueResolver(result);
        }
        return result;
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }
}
