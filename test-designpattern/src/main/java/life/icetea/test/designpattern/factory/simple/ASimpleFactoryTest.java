package life.icetea.test.designpattern.factory.simple;

/**
 * 简单工厂模式测试(静态工厂方法)
 */
public class ASimpleFactoryTest {

    public static void main(String[] args) {
        IOperation product = OperationFactory.createProduct("+");
        int sum = product.operate(1, 2);
        System.out.println(sum);
    }

}
