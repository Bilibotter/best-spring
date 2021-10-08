package factory.factory;

import factory.context.ClassPathXmlApplicationContext;
import factory.events.ContextClosedEvent;
import factory.events.impl.CustomEvent;
import org.junit.Test;

public class TestEvent {
    @Test
    public void testEvent() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.publishEvent(new CustomEvent(context, System.currentTimeMillis()<<4, "成功了"));
        context.registerShutdownHook();
    }
}
