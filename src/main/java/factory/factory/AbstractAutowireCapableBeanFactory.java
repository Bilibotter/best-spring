package factory.factory;

import cn.hutool.core.bean.BeanUtil;
import factory.bean.AutowireCapableBeanFactory;
import factory.bean.BeanDefinition;
import factory.bean.BeanReference;
import factory.extension.BeanPostProcessor;
import factory.support.CglibSubclassingInstantiationStrategy;
import factory.support.InstantiationStrategy;
import factory.support.PropertyValue;
import factory.support.PropertyValues;

import java.lang.reflect.Constructor;
import java.util.Properties;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args){
        Object bean = null;
        try {
            bean = createBeanInstance(beanName, beanDefinition, args);
            applyPropertyValues(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new RuntimeException("Instantiation of bean failed", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        Object wrappedBean
    }

    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) throws Exception {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?> [] declaredConstructor = beanClass.getConstructors();
        for (Constructor ctor : declaredConstructor) {
            // 实际上spring还要做参数类型的校验
            if (args != null && ctor.getParameterCount() == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                BeanUtil.setProperty(bean, name, value);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error setting property values: "+beanName, e);
        }
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }
}
