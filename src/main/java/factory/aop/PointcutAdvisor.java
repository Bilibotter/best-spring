package factory.aop;

public interface PointcutAdvisor extends Advisor {
    Pointcut getPointcut();
}
