package life.icetea.test.designpattern.factory.factoryTemplate;


/**
 * 工厂方法模式
 */
public class AFactoryTemplateTest {

    public static void main(String[] args) {
        IOperationFactory factory = new OperationAddFactory();
        IOperation operation = factory.createOperation();
        int operate = operation.operate(1, 2);
        System.out.println(operate);

        IOperationFactory factory2 = new OperationSubFactory();
        IOperation operation2 = factory2.createOperation();
        int operate1 = operation2.operate(1, 2);
        System.out.println(operate1);

    }

}
