package factory.config;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import factory.annotation.Bean;
import factory.annotation.Component;
import factory.annotation.Configuration;
import factory.bean.*;
import factory.factory.BeanFactoryPostProcessor;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

@Component
public class ConfigurationClassPostProcessor implements BeanFactoryPostProcessor {

    private Set<String> resolvedConfiguration = new HashSet<>(16);

    @Override
    public void postProcessorBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        if (!(beanFactory instanceof BeanDefinitionRegistry)) {
            throw new RuntimeException("Bean factory "+beanFactory.getClass().getSimpleName()+" don't support registry bean definition with @Bean annotation!");
        }
        resolveConfiguration((BeanDefinitionRegistry) beanFactory);
    }

    private void resolveConfiguration(BeanDefinitionRegistry registry) {
        BeanDefinition candidate;
        for (String beanName : registry.getBeanDefinitionNames()) {
            try {
                candidate = registry.getBeanDefinition(beanName);
            } catch (Exception neverExpected) {
                throw new RuntimeException("How do I know this fucking exception?!");
            }
            Class<?> configurationClass = candidate.getBeanClass();
            if (!configurationClass.isAnnotationPresent(Configuration.class) || resolvedConfiguration.contains(beanName)) {
                continue;
            }
            registryAnnotationBeanDefinitions(registry, beanName, (AnnotationBeanDefinition) candidate);
            resolvedConfiguration.add(beanName);
        }
    }

    private void registryAnnotationBeanDefinitions(BeanDefinitionRegistry registry, String configBeanName, AnnotationBeanDefinition configBeanDefinition) {
        Method[] methods = ClassUtil.getDeclaredMethods(configBeanDefinition.getBeanClass());
        String beanName;
        for (Method method : methods) {
            Bean annotation = method.getAnnotation(Bean.class);
            if (annotation == null) {
                continue;
            }
            AnnotationBeanDefinition beanDefinition = new BasicAnnotationBeanDefinition(method.getReturnType());
            // JDK的魔鬼写法
            if ("".equals(beanName = annotation.value())) {
                beanName = StrUtil.lowerFirst(beanDefinition.getBeanClass().getSimpleName());
            }
            if (registry.containsBeanDefinition(beanName)) {
                throw new RuntimeException("Duplicate bean name registry, bean name["+beanName+"]");
            }
            beanDefinition.setInitMethodName(annotation.intiMethod());
            beanDefinition.setDestroyMethodName(annotation.destroyMethod());
            beanDefinition.setConfigurationName(configBeanName);
            beanDefinition.setConfigurationBeanMethod(method.getName());
            beanDefinition.setConfigBeanMethodParamTypes(method.getParameterTypes());
            registry.registryBeanDefinition(beanName, beanDefinition);
        }
    }
}
