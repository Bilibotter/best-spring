package factory.factory;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import factory.aop.InstantiationAwareBeanPostProcessor;
import factory.bean.*;
import factory.extension.BeanPostProcessor;
import factory.extension.DisposableBean;
import factory.extension.DisposableBeanAdapter;
import factory.extension.InitializingBean;
import factory.support.CglibSubclassingInstantiationStrategy;
import factory.support.InstantiationStrategy;
import factory.support.PropertyValue;
import factory.support.PropertyValues;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args){
        Object bean = resolveBeforeInstantiation(beanName, beanDefinition);
        return bean == null ? doCreateBean(beanName, beanDefinition, args) : bean;
    }

    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Object bean;
        try {
            bean = createBeanInstance(beanName, beanDefinition, args);
            if (beanDefinition.isSingleton()) {
                Object finalBean = bean;
                addSingletonFactory(beanName, ()->getEarlyBeanReference(beanName, beanDefinition, finalBean));
            }
            boolean continueWithPropertyPopulation = applyBeanPostProcessorsAfterInstantiation(beanName, bean);
            if (!continueWithPropertyPopulation) {
                return bean;
            }
            applyBeanPostProcessorsBeforeApplyingPropertyValues(beanName, bean, beanDefinition);
            applyPropertyValues(beanName, bean, beanDefinition);
            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new RuntimeException("Instantiation of bean failed", e);
        }
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);
        Object exposedObject = bean;
        if (beanDefinition.isSingleton()) {
            exposedObject = getSingleton(beanName);
            registerSingleton(beanName, exposedObject);
        }
        return exposedObject;
    }

    protected Object getEarlyBeanReference(String beanName, BeanDefinition beanDefinition, Object bean) {
        Object exposedObject = bean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                exposedObject = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).getEarlyBeanReference(exposedObject, beanName);
                if (exposedObject == null) {
                    return exposedObject;
                }
            }
        }
        return exposedObject;
    }

    private boolean applyBeanPostProcessorsAfterInstantiation(String beanName, Object bean) {
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                InstantiationAwareBeanPostProcessor processor = (InstantiationAwareBeanPostProcessor) beanPostProcessor;
                if (!processor.postProcessAfterInstantiation(bean, beanName)) {
                    return false;
                }
             }
        }
        return true;
    }

    protected void applyBeanPostProcessorsBeforeApplyingPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (!(beanPostProcessor instanceof InstantiationAwareBeanPostProcessor)) {
                continue;
            }
            PropertyValues pvs = ((InstantiationAwareBeanPostProcessor) beanPostProcessor)
                    .postProcessPropertyValues(beanDefinition.getPropertyValues(), bean, beanName);
            // null不能使用for-each
            if (pvs == null) {
                continue;
            }
            for (PropertyValue propertyValue : pvs.getPropertyValues()) {
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
        }
    }

    protected Object resolveBeforeInstantiation(String beanName, BeanDefinition beanDefinition) {
        Object bean = applyBeanPostProcessorsBeforeInstantiation(beanDefinition.getBeanClass(), beanName);
        if (bean != null) {
            applyBeanPostProcessorsAfterInitialization(bean, beanName);
        }
        return bean;
    }

    protected Object applyBeanPostProcessorsBeforeInstantiation(Class<?> beanClass, String beanName) {
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                Object result = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessorBeforeInstantiation(beanClass, beanName);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        if (bean instanceof Aware) {
            if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
            if (bean instanceof BeanClassLoaderAware) {
                ((BeanClassLoaderAware) bean).setClassLoader(getBeanClassLoader());
            }
            if (bean instanceof BeanNameAware) {
                ((BeanNameAware) bean).setBeanName(beanName);
            }
        }
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);
        invokeInitMethods(beanName, wrappedBean, beanDefinition);
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        return wrappedBean;
    }

    private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) {
        if (wrappedBean instanceof InitializingBean) {
            ((InitializingBean) wrappedBean).afterPropertiesSet();
        }
        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotEmpty(initMethodName)) {
            try {
                Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
                initMethod.invoke(wrappedBean);
            } catch (NoSuchMethodException e1) {
                throw new RuntimeException("Could not find an init method named '"+initMethodName+"' on bean with name '"+beanName+"'");
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("Init method named '"+beanName +"' on bean with name '"+wrappedBean +"' is no accessible");
            } catch (InvocationTargetException e3) {
                throw new RuntimeException(e3.getMessage(), e3);
            }
        }
    }

    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) throws Exception {
        if (beanDefinition instanceof AnnotationBeanDefinition && ((AnnotationBeanDefinition) beanDefinition).getConfigurationName() != null) {
            return createAnnotationBeanInstance(beanName, (AnnotationBeanDefinition) beanDefinition);
        }
        return createBasicBeanInstance(beanName, beanDefinition, args);
    }

    private Object createAnnotationBeanInstance(String beanName, AnnotationBeanDefinition beanDefinition) {
        Object configInstance = getBean(beanDefinition.getConfigurationName());
        Method method;
        try {
            method = configInstance.getClass().getMethod(beanDefinition.getConfigurationBeanMethod());
            Object args = getParamValues(beanDefinition.getConfigBeanMethodParamTypes());
            Object beanInstance = method.invoke(configInstance, args);
            return beanInstance;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Configuration class ["+beanDefinition.getConfigurationName()+"] miss method "+beanDefinition.getConfigurationBeanMethod()+" to create bean ["+beanName+"]", e);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("During invoke "+beanDefinition.getConfigurationBeanMethod() + " error occur", e);
        }
    }

    private Object[] getParamValues(Class[] injectedBeanClass) {
        Object[] args = new Object[injectedBeanClass.length];
        for (int i = 0; i < injectedBeanClass.length; i++) {
            Object dependentBean = getBean(injectedBeanClass[i]);
            args[i] = dependentBean;
        }
        return args;
    }

    private Object createBasicBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) throws Exception {
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

    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        if (!beanDefinition.isSingleton()) {
            return;
        }
        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }
}
