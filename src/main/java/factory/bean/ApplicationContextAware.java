package factory.bean;

import factory.context.ApplicationContext;

public interface ApplicationContextAware extends Aware{
    void setApplicationContext(ApplicationContext applicationContext);
}
