package factory.events.impl;

import factory.events.ApplicationListener;
import factory.events.ContextClosedEvent;

public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件："+this.getClass().getName());
    }
}
