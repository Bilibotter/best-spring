package factory.events.impl;

import factory.events.ApplicationListener;
import factory.events.ContextRefreshEvent;

public class ContextRefreshListener implements ApplicationListener<ContextRefreshEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshEvent event) {
        System.out.println("刷新时间："+event.getClass().getName());
    }
}
