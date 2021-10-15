package life.icetea.test.designpattern.prototype;

/**
 * 没有用原型模式demo
 *
 * @author icetea
 */
public class WithoutPrototypePatternDemo {

    public static void main(String[] args) {
        // 不用原型模式
        Product product = new Product("测试", new Component("测试组件"));
        // 手动copy
        Product copyProduct = new Product(product.getName(), product.getComponent());

        // 问题
        // 1. 代码的拷贝逻辑需要调用方自己实现
        // 2. 相同的拷贝逻辑会分散在很多不同的地方,如果拷贝逻辑改变了,多个调用的地方都要修改代码
        // 3. 可维护性,可拓展性,很差
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
    }


}
