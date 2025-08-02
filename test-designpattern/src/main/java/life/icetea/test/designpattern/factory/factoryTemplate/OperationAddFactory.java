package life.icetea.test.designpattern.factory.factoryTemplate;


public class OperationAddFactory implements IOperationFactory {
    @Override
    public IOperation createOperation() {
        return new OperationAdd();
    }
}
