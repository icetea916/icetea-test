package life.icetea.test.designpattern.factory;

public class Factory {

    public static Product createProduct() {
        return new ProductImpl();
    }

}
