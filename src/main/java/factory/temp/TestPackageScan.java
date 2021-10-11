package factory.temp;


import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import factory.annotation.Component;
import factory.bean.BasicBeanDefinition;
import factory.bean.BeanDefinition;

import java.util.HashSet;
import java.util.Set;

public class TestPackageScan {
    /*
    public static void main(String[] args) {
        Set<BeanDefinition> candidates = new HashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation("factory", Component.class);
        for (Class< ? > clazz : classes) {
            candidates.add(new BasicBeanDefinition(clazz));
        }
        String[] basePackages = StrUtil.split("factory, haha, yhm  ", ",")
                .stream().map(String::trim).toArray(String[]::new);
        return;
    }
    */
}
