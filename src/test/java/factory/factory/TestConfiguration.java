package factory.factory;

import factory.context.ClassPathXmlApplicationContext;
import factory.mine.TestBean;
import factory.temp.DoubleDog;
import factory.temp.SingleDog;
import org.junit.Test;

public class TestConfiguration {
    @Test
    public void testConfiguration() throws Exception {
        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("classpath:scanner.xml");
        TestBean bean = (TestBean) appContext.getBean("testBean");
        System.out.println(bean);
    }
}
