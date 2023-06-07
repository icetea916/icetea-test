package life.icetea.test.guava;

public class NullTest {

    public static void main(String[] args) {
        int age = 0; // 不复制直接使用会报编译错误
        System.out.println(age);

        long money;
        money = 10L;
        System.out.println("user money" + money);

        String name = "icetea";
        System.out.println("user name:" + name);
    }
}
