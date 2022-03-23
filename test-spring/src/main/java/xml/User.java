package xml;

/**
 * 定义一个用户对象bean
 */
public class User {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 年龄
     */
    private Integer age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User(String userName, Integer age) {
        this.userName = userName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }

    public void initUser() {
        System.out.println("初始化用户bean后执行");
    }

    public void destroyUser() {
        System.out.println("bean销毁之后执行");
    }
}