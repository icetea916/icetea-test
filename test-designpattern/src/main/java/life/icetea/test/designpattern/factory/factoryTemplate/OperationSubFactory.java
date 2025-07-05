package life.icetea.test.designpattern.factory.factoryTemplate;


public class OperationSubFactory implements IOperationFactory {

    @Override
    public IOperation createOperation() {
        return new OperationSub();
    }

}
