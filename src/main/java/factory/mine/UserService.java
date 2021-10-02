package factory.mine;

public class UserService {

    private String uId;
    private String company;
    private String location;
    private UserDao userDao;

    public String queryUserInfo() {
        return userDao.queryUserName(uId);
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserService{");
        sb.append("uId='").append(uId).append('\'');
        sb.append(", company='").append(company).append('\'');
        sb.append(", location='").append(location).append('\'');
        sb.append(", userDao=").append(userDao);
        sb.append('}');
        return sb.toString();
    }
}
