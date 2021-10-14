package factory.mine;

import factory.annotation.Bean;
import factory.annotation.Configuration;

@Configuration
public class TestConfiguration {
    @Bean
    public TestBean testBean() {
        TestBean bean = new TestBean();
        bean.setName("yhm");
        bean.setId(13L);
        return bean;
    }
}
