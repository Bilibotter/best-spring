package factory.mine;

import factory.extension.BeanPostProcessor;
import temp.UserServicePlus;

public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (bean instanceof UserService) {
            UserService userService = (UserService) bean;
            userService.setLocation("改为：深圳");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
