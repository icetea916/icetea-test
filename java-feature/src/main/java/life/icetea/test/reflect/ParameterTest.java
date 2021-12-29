package life.icetea.test.reflect;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

/**
 * 反射 Parameter类测试
 * https://blog.csdn.net/Goodbye_Youth/article/details/83757757
 *
 * @author icetea
 */
public class ParameterTest {

    public void test(final String key, String value) {

    }

    public static void main(String[] args) throws Exception {
        Method method = ParameterTest.class.getMethod("test", String.class, String.class);
        Parameter[] parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            // 正常编译得到: arg0 arg1, 即编译器的命名
            // 若修改编译器参数增加-parameters后编译得到: key value, 即用户定义的参数的名称
            System.out.println(parameter.getName());
        }

        for (Parameter parameter : parameters) {
            AnnotatedType annotatedType = parameter.getAnnotatedType();
            // class java.lang.String
            // class java.lang.String
            System.out.println(annotatedType.getType());
        }

        // getModifiers(): 返回修饰该参数对象修饰符的整数形式，使用 Modifier 类对其进行解码
        for (Parameter parameter : parameters) {
            // final
            System.out.println(Modifier.toString(parameter.getModifiers()));
        }

    }

}
