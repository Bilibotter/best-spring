package factory.factory;

import factory.context.ClassPathXmlApplicationContext;
import factory.temp.DoubleDog;
import factory.temp.SingleDog;
import org.junit.Test;

public class TestConfiguration {
    @Test
    public void testConfiguration() {
        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("classpath:scanner.xml");

    }
}
