package life.icetea.test.designpattern.factory.simple;

public class OperationFactory {

    public static IOperation createProduct(String op) {
        IOperation oper;
        switch (op) {
            case "+":
                oper = new OperationAdd();
                break;
            case "-":
                oper = new OperationSub();
                break;
            case "*":
                oper = new OperationMul();
                break;
            case "/":
                oper = new OperationDiv();
                break;
            default:
                throw new UnsupportedOperationException("不支持该操作");
        }
        return oper;
    }

}
