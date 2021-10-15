package life.icetea.test.designpattern.builder;

public class ConcreteBuilder implements Builder {

    private Product product = new Product();

    private ConcreteBuilder() {
    }

    public static Builder build() {
        return new ConcreteBuilder();
    }

    public Product create() {
        return product;
    }

    @Override
    public void field1(String value) {

    }

    @Override
    public void field2(String value) {

    }

}
