package factory.bean;

import factory.bean.BeanDefinition;

// 基本实现是存一张beanName对应BeanDefinition的map
public interface BeanDefinitionRegistry {

    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);

    BeanDefinition getBeanDefinition(String name) throws Exception;

    boolean containsBeanDefinition(String beanName);

}
