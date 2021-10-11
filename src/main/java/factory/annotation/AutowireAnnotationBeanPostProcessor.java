package factory.annotation;

import cn.hutool.core.bean.BeanUtil;
import factory.aop.InstantiationAwareBeanPostProcessor;
import factory.bean.BeanFactoryAware;
import factory.bean.ConfigurableListableBeanFactory;
import factory.factory.BeanFactory;
import factory.support.PropertyValues;
import factory.util.ClassUtils;

import java.io.File;
import java.lang.reflect.Field;

@Component
public class AutowireAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private ConfigurableListableBeanFactory beanFactory;

    public AutowireAnnotationBeanPostProcessor() {
    }

    public AutowireAnnotationBeanPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public Object postProcessorBeforeInstantiation(Class<?> beanClass, String beanName) {
        return null;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) {
        return true;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws Exception {
        Class<?> clazz = bean.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            Value valueAnnotation = field.getAnnotation(Value.class);
            if (valueAnnotation != null) {
                String value = valueAnnotation.value();
                value = beanFactory.resolveEmbeddedValue(value);
                BeanUtil.setFieldValue(bean, field.getName(), value);
            }
            Autowired autowiredAnnotation = field.getAnnotation(Autowired.class);
            if (autowiredAnnotation != null) {
                Class<?> fieldType = field.getType();
                Qualifier qualifierAnnotation = field.getAnnotation(Qualifier.class);
                Object dependentBean = null;
                if (qualifierAnnotation == null) {
                    dependentBean = beanFactory.getBean(fieldType);
                } else {
                    dependentBean = beanFactory.getBean(qualifierAnnotation.value(), fieldType);
                }
                BeanUtil.setFieldValue(bean, field.getName(), dependentBean);
            }
        }
        return pvs;
    }
}
