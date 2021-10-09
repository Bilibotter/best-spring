package factory.annotation;

import cn.hutool.core.util.ClassUtil;
import factory.bean.BasicBeanDefinition;
import factory.bean.BeanDefinition;

import java.util.LinkedHashSet;
import java.util.Set;

public class ClassPathScanningCandidateComponentProvider {
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BasicBeanDefinition(clazz));
        }
        return candidates;
    }
}
