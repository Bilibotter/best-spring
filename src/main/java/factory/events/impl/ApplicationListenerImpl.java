package factory.events.impl;
import factory.events.ApplicationListener;

public class ApplicationListenerImpl implements ApplicationListener<SubApplicationEvent> {
    @Override
    public void onApplicationEvent(SubApplicationEvent event) {
        System.out.println(event.getSource());
    }
}
