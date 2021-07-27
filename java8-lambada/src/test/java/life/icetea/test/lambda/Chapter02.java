package life.icetea.test.lambda;

import org.junit.Test;

import java.awt.event.ActionListener;
import java.util.function.BinaryOperator;

/**
 * 章节2: lambda表达式介绍
 */
public class Chapter02 {

    /**
     * lambda 表达式的多种写法
     */
    @Test
    public void test01() {
        // 第一种: 无参, ()空括号表示没有参数
        Runnable noArguments = () -> System.out.println("Hello world");
        // 第二种: 一个参数 只 有一个参数可以省略括号
        ActionListener oneArgument = event -> System.out.println("button clicked");
        // 第三种: 多个表达式语句, 只有一个代码语句时可以省略大括号{}, 多个语句用{}大括号括起来
        Runnable multiStatement = () -> {
            System.out.print("Hello");
            System.out.println(" world");
        };
        // 第四种: 多个参数和一个返回值
        BinaryOperator<Long> add = (x, y) -> x + y;
        // 第五种: 显示声明参数类型, 默认的参数类型可以由编译器推断得出.但也可以手动显示声明参数类型
        BinaryOperator<Long> addExplicit = (Long x, Long y) -> x + y;
    }

}
