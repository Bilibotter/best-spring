package factory.factory;

import org.junit.Test;
import temp.UserServiceImpl;

public class TestInject {
    @Test
    public void testFactory() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinition beanDefinition = new BasicBeanDefinition(UserServiceImpl.class);
        beanFactory.registryBeanDefinition("userServiceImpl", beanDefinition);

        UserServiceImpl userService = (UserServiceImpl) beanFactory.getBean("userServiceImpl", "yhm");
        userService.queryUserInfo();
        beanFactory.setInstantiationStrategy(new SimpleInstantiationStrategy());
    }

    @Test
    public void testFactory1() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.setInstantiationStrategy(new SimpleInstantiationStrategy());
        BeanDefinition beanDefinition = new BasicBeanDefinition(UserServiceImpl.class);
        beanFactory.registryBeanDefinition("userServiceImpl", beanDefinition);

        UserServiceImpl userService = (UserServiceImpl) beanFactory.getBean("userServiceImpl", "yhm");
        userService.queryUserInfo();
    }
}
