package factory.aop;

import factory.util.ClassUtils;

public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Class<?>[] getTargetClass() {
        Class<?> clazz = target.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;
        return target.getClass().getInterfaces();
    }

    public Object getTarget() {
        return target;
    }

}
