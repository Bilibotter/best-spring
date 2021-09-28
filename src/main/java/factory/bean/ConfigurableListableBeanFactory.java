package factory.bean;

import factory.extension.ListableBeanFactory;
import factory.factory.AbstractAutowireCapableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
}
