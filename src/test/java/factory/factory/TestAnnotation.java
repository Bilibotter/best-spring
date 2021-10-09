package factory.factory;

import factory.context.ClassPathXmlApplicationContext;
import factory.mine.TempComponent;
import org.junit.Test;

public class TestAnnotation {
    @Test
    public void testProperty() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:scanner.xml");
        TempComponent tempComponent = (TempComponent)applicationContext.getBean("tempComponent");
        tempComponent.authenticate();
        return;
    }
}
