package factory.mine;

public class MyUserService implements UserServiceFace {
    @Override
    public String queryUserInfo() {
        return "invoke myUserService";
    }
}
