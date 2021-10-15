package life.icetea.test.designpattern.factoryTemplate;

public class ConcreteCreator extends Creator {
    @Override
    protected Product factoryMethod() {
        System.out.println("生产自己特有产品的特殊逻辑");
        return new ProductImpl();
    }
}
