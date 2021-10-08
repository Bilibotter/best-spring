package factory.factory;

import factory.aop.AdvisedSupport;
import factory.aop.Cglib2AopProxy;
import factory.aop.JDKDynamicAopProxy;
import factory.aop.TargetSource;
import factory.aop.impl.AspectJExpressionPointcut;
import factory.aop.impl.TestInvocationHandler;
import factory.aop.impl.TestMethodInterceptor;
import factory.context.ClassPathXmlApplicationContext;
import factory.mine.MyUserService;
import factory.mine.UserService;
import factory.mine.UserServiceFace;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class TestAop {
    @Test
    public void testProxyMethod() throws Exception {
        // ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        // Object targetObj = (UserServiceFace)context.getBean("userService");
        Object targetObj = new MyUserService();
        UserServiceFace proxy = (UserServiceFace) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                targetObj.getClass().getInterfaces(),
                new TestInvocationHandler(targetObj));
        String result = proxy.queryUserInfo();
        return;
    }

    @Test
    public void testMatch() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* factory.mine.UserService.*(..))");
        Class<UserService> clazz = UserService.class;
        Method method = clazz.getMethod("queryUserInfo");
        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));
    }

    @Test
    public void testPointcut() {
        Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = Collections.synchronizedSet(new HashSet<PointcutPrimitive>());
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
        String expression = "execution(* factory.mine.UserService.*(..))";
        PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(SUPPORTED_PRIMITIVES, this.getClass().getClassLoader());
        PointcutExpression pointcutExpression = pointcutParser.parsePointcutExpression(expression);
        return;
    }

    @Test
    public void testProxy() {
        UserServiceFace userServiceFace = new MyUserService();
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userServiceFace));
        advisedSupport.setMethodInterceptor(new TestMethodInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* factory.mine.UserServiceFace.*(..))"));
        UserServiceFace jdkProxy = (UserServiceFace) new JDKDynamicAopProxy(advisedSupport).getProxy();
        System.out.println("测试结果："+ jdkProxy.queryUserInfo());
        UserServiceFace cglibProxy = (UserServiceFace) new Cglib2AopProxy(advisedSupport).getProxy();
        System.out.println("测试结果："+ cglibProxy.queryUserInfo());
    }

    @Test
    public void testAop() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:aop.xml");
        UserServiceFace userService = (MyUserService) applicationContext.getBean("myUserService");
        String result = userService.queryUserInfo();
        return;
    }
}
