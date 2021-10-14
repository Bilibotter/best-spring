package factory.factory;

import factory.context.ClassPathXmlApplicationContext;
import factory.mine.TestConfiguration;
import org.junit.Test;

import java.lang.reflect.Method;

public class TestInvoke {
    @Test
    public void test1() throws Exception {
        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("classpath:scanner.xml");
        TestConfiguration config = (TestConfiguration) appContext.getBean("testConfiguration");
        Method[] methods = config.getClass().getMethods();
        return;
    }

    public void test() {
        /*
        Method method = TestConfiguration.class.getMethod(beanDefinition.getConfigurationBeanMethod());
        Object[] args = getParamValues(beanDefinition.getConfigBeanMethodParamTypes());
        Object beanInstance = method.invoke(configInstance, args);
        */
    }

    private Object[] getParamValues(Class[] injectedBeanClass) {
        Object[] args = new Object[injectedBeanClass.length];
        for (int i = 0; i < injectedBeanClass.length; i++) {
            Object dependentBean = new Object();
            args[i] = dependentBean;
        }
        return args;
    }
}
