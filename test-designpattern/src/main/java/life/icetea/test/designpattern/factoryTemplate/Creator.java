package life.icetea.test.designpattern.factoryTemplate;

public abstract class Creator {

    public Product create() {
        System.out.println("生产一个产品的通用基础逻辑");
        return factoryMethod();
    }

    protected abstract Product factoryMethod();


}
