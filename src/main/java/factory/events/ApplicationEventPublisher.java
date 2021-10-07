package factory.events;

public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}
