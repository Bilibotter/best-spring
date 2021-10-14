package factory.mine;

import factory.annotation.Bean;
import factory.annotation.Configuration;

@Configuration
public class TestConfiguration {
    @Bean
    public TestBeanPlus testBeanPlus() {
        TestBeanPlus bean = new TestBeanPlus();
        bean.setName("lmx");
        bean.setId(11L);
        return bean;
    }

    @Bean
    public TestBean testBean(TestBeanPlus testBeanPlus) {
        System.out.println(testBeanPlus);
        TestBean bean = new TestBean();
        bean.setName("yhm");
        bean.setId(13L);
        return bean;
    }
}
