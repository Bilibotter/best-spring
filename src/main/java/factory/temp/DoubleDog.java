package factory.temp;

import factory.annotation.Autowired;
import factory.annotation.Component;
import factory.annotation.Value;

@Component
public class DoubleDog {
    @Autowired
    private SingleDog dog;
    @Value("${double}")
    private String value;

    public void call() {
        System.out.println("My single dog say:\""+dog.getValue()+"\"");
    }

    public SingleDog getDog() {
        return dog;
    }

    public void setDog(SingleDog dog) {
        this.dog = dog;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
