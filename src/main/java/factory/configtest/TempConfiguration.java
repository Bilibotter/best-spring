package factory.configtest;

import factory.annotation.Configuration;

@Configuration
public class TempConfiguration {
    public static void main(String[] args) {
        System.out.println(TempConfiguration.class.isAnnotationPresent(Configuration.class));
    }
}
