package life.icetea.test.overried;

/**
 * @author icetea
 */
public class Father {

    public static void main(String[] args) {
        Father son = new Son();
        son.sayHello();
    }

    public void sayHello() {
        System.out.println("hello");
    }
}
