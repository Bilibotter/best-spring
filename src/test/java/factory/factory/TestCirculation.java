package factory.factory;

import factory.context.ClassPathXmlApplicationContext;
import factory.temp.DoubleDog;
import factory.temp.SingleDog;
import org.junit.Test;

public class TestCirculation {
    public void testDefaultValue() {
        Object bean;
        //System.out.println(bean == null);
    }

    @Test
    public void testCirculation() {
        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("classpath:scanner.xml");
        SingleDog dog1 = (SingleDog) appContext.getBeansOfType(SingleDog.class).values().toArray()[0];
        dog1.call();
        DoubleDog dog2 = (DoubleDog) appContext.getBeansOfType(DoubleDog.class).values().toArray()[0];
        dog2.call();
    }
}
