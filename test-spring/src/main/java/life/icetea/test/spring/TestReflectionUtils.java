package life.icetea.test.spring;


import org.springframework.util.ReflectionUtils;

public class TestReflectionUtils {


    public void foo() {

    }

    private void bar() {

    }

    public static void main(String[] args) {
        ReflectionUtils.doWithMethods(TestReflectionUtils.class, method -> {
            System.out.println(method.getName());
        });
    }

}
