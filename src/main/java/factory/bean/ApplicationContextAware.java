package factory.bean;

import factory.extension.ApplicationContext;

public interface ApplicationContextAware extends Aware{
    void setApplicationContext(ApplicationContext applicationContext);
}
