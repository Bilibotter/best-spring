package factory.annotation;

import cn.hutool.core.util.ClassUtil;
import factory.bean.BasicBeanDefinition;
import factory.bean.BeanDefinition;

import java.util.LinkedHashSet;
import java.util.Set;

public class ClassPathScanningCandidateComponentProvider {
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackage(basePackage, clazz->clazz.isAnnotationPresent(Component.class)||clazz.isAnnotationPresent(Configuration.class));
        for (Class<?> clazz : classes) {
            candidates.add(new BasicBeanDefinition(clazz));
        }
        return candidates;
    }

    /*
    public static void main(String[] args) {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider();
        provider.findCandidateComponents("factory");
    }
     */
}