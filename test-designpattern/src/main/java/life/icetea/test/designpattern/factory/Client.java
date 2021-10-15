package life.icetea.test.designpattern.factory;

public class Client {

    public static void main(String[] args) {
        Product product = Factory.createProduct();
        product.operation();
    }

}
