package factory.factory;

import factory.bean.BasicBeanDefinition;
import factory.bean.BasicBeanReference;
import factory.bean.BeanDefinition;
import factory.support.PropertyValue;
import factory.support.PropertyValues;
import factory.support.SimpleInstantiationStrategy;
import org.junit.Test;
import temp.UserServiceHolder;
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

    @Test
    public void testConstructorInject() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registryBeanDefinition("userServiceImpl", new BasicBeanDefinition(UserServiceImpl.class));
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("userService", new BasicBeanReference("userServiceImpl")));
        BeanDefinition beanDefinition = new BasicBeanDefinition(UserServiceHolder.class, propertyValues);
        beanFactory.registryBeanDefinition("userServiceHolder", beanDefinition);
        UserServiceHolder userServiceHolder = (UserServiceHolder) beanFactory.getBean("userServiceHolder");
        return;
    }
}
