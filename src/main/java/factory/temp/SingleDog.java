package factory.temp;

import factory.annotation.Autowired;
import factory.annotation.Component;
import factory.annotation.Value;

@Component
public class SingleDog {
    @Autowired
    private DoubleDog dog;
    @Value("${single}")
    private String value;

    public void call() {
        System.out.println("My double dog say:\""+dog.getValue()+"\"");
    }

    public DoubleDog getDog() {
        return dog;
    }

    public void setDog(DoubleDog dog) {
        this.dog = dog;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
