package life.icetea.test.designpattern.factory.abstractfactory;

public class AbstractFactoryImpl2 implements AbstractFactory {
    @Override
    public ProductA createProductA() {
        return new ProductAImpl2();
    }

    @Override
    public ProductB createProductB() {
        return new ProductBImpl2();
    }
}
