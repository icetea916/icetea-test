package life.icetea.test.designpattern.factory.simple;

public class OperationDiv implements IOperation {
    @Override
    public int operate(int x, int y) {
        return x / y;
    }
}
