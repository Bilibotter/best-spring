package factory.context;

import factory.events.ApplicationEventPublisher;
import factory.extension.ListableBeanFactory;

public interface ApplicationContext extends ListableBeanFactory, ApplicationEventPublisher {
}
