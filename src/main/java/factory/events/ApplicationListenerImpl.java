package factory.events;

public class ApplicationListenerImpl<E extends ApplicationEvent> implements ApplicationListener<E> {
    @Override
    public void onApplicationEvent(E event) {
        System.out.println(event.getSource());
    }
}
