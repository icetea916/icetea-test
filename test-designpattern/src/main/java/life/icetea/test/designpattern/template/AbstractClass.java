package life.icetea.test.designpattern.template;

public abstract class AbstractClass {

    public void templateMethod() {
        System.out.println("处理优惠打折相关的基础通用逻辑");
        // 但是对于优惠打折具体的处理逻辑，交给不同的折扣类型子类自己去实现
        method1();
        method2();
        method3();
    }

    public abstract void method1();

    public abstract void method2();

    public abstract void method3();

}
