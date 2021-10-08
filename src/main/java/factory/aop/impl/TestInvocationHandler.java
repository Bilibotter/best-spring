package factory.aop.impl;

import factory.aop.MethodMatcher;
import factory.mine.UserService;
import factory.mine.UserServiceFace;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TestInvocationHandler implements InvocationHandler {
    MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* factory.mine.UserServiceFace.*(..))");
    private Object targetObj;

    public TestInvocationHandler(Object targetObj) {
        this.targetObj = targetObj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (methodMatcher.matches(method, targetObj.getClass())) {
            MethodInterceptor methodInterceptor = new TestMethodInterceptor();
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObj, method, args));
        }
        return method.invoke(targetObj, args);
    }
}
