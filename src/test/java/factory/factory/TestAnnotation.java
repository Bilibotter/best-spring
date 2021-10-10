package factory.factory;

import factory.context.ClassPathXmlApplicationContext;
import factory.mine.TempComponent;
import org.junit.Test;

import java.util.List;

public class TestAnnotation {
    @Test
    public void testProperty() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:scanner.xml");
        TempComponent tempComponent = (TempComponent)applicationContext.getBean("testComponent");
        tempComponent.authenticate();
        return;
    }

    @Test
    public void testIterNull() {
        List<String> yhm = null;
        for (String s : yhm) {
            System.out.println(s);
        }
        return;
    }
}
