package life.icetea.test.designpattern.prototype;

/**
 * 原型模式demo
 *
 * @author icetea
 */
public class PrototypePatternDemo {

    public static void main(String[] args) throws CloneNotSupportedException {
        Product product = new Product("测试", new Component("测试组件"));
        // 自动copy
        Product copyProduct = (Product) product.clone();
        copyProduct.component.setName("测试组件2");
        System.out.println(copyProduct);
        System.out.println(product);

        /**
         * 原型模式,就是在要拷贝的类里实现一个clone()方法,自己拷贝自己
         * 拷贝分两种类型:
         * 1.浅拷贝 Shallow Copy
         *  对基本类型的属性会进行值传递,当修改其中一个对象中的属性时,不会影响另一个对象中的属性
         *  对引用数据的属性,比如数组或某类对象,会进行引用传递,及两个对象中的属性指向同一个实例对象,修改对象会相互影响.
         * 2.深拷贝 Deep Copy
         *  深拷贝就是对引用类型的数据,也进行拷贝,开辟新的内存空间
         */

    }

    public static class Component {

        private String name;

        public Component(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Component{" +
                    "name='" + name + '\'' +
                    '}';
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return new Component(getName());
        }
    }

    public static class Product {

        private String name;
        private Component component;

        public Product(String name, Component component) {
            this.name = name;
            this.component = component;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Component getComponent() {
            return component;
        }

        public void setComponent(Component component) {
            this.component = component;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "name='" + name + '\'' +
                    ", component=" + component +
                    '}';
        }

        /**
         * 覆盖clone方法,实现自己的逻辑
         *
         * @return
         * @throws CloneNotSupportedException
         */
        @Override
        protected Object clone() throws CloneNotSupportedException {
            // 浅拷贝
//            return new Product(getName(), getComponent());

            // 深拷贝
            return new Product(getName(), (Component) getComponent().clone());
        }
    }


}
