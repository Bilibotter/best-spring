package factory.mine;

import factory.annotation.Component;
import factory.annotation.Value;

@Component
public class InjectedComponent {
    @Value("${token}")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
