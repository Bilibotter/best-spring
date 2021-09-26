package temp;

public class UserServiceImpl implements UserService {

    private String name;

    public UserServiceImpl() {
        this.name = "yhm";
    }

    @Override
    public void queryUserInfo() {
        System.out.println("查询用户信息：" + name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("").append(name);
        return sb.toString();
    }
}
