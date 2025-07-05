package life.icetea.test.designpattern.factory.abstractfactory;

public class AbstractFactoryImpl1 implements AbstractFactory {
    @Override
    public ProductA createProductA() {
        return new ProductAImpl1();
    }

    @Override
    public ProductB createProductB() {
        return new ProductBImpl1();
    }
}
