package factory.mine;

import factory.annotation.Autowired;
import factory.annotation.Component;

import java.lang.reflect.Field;

@Component(value = "testComponent")
public class TempComponent {
    private String token;
    @Autowired
    private InjectedComponent injectedComponent;
    public void authenticate() {
        System.out.println(injectedComponent.getToken());
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public InjectedComponent getInjectedComponent() {
        return injectedComponent;
    }

    public void setInjectedComponent(InjectedComponent injectedComponent) {
        this.injectedComponent = injectedComponent;
    }
}
