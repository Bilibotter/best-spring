package factory.mine;

import factory.annotation.Component;

@Component(value = "testComponent")
public class TempComponent {
    private String token;
    public void authenticate() {
        System.out.println(token);
    }
}
