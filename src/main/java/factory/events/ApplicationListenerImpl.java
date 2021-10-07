package factory.events;
import factory.events.SubApplicationEvent;

public class ApplicationListenerImpl implements ApplicationListener<SubApplicationEvent> {
    @Override
    public void onApplicationEvent(SubApplicationEvent event) {
        System.out.println(event.getSource());
    }
}
