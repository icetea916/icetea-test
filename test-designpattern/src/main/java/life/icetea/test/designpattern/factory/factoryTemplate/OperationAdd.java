package life.icetea.test.designpattern.factory.factoryTemplate;

public class OperationAdd implements IOperation {
    @Override
    public int operate(int x, int y) {
        return x + y;
    }
}
