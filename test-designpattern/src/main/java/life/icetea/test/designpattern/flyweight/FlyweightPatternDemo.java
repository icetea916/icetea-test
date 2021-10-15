package life.icetea.test.designpattern.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元模式 demo
 * <p>
 * 享 共享
 * 元  元数据
 * 同一数据在整个系统中共用
 *
 * @author icetea
 */
public class FlyweightPatternDemo {

    public static void main(String[] args) {
        Flyweight flyweight1 = FlyweightFactory.getInstance("测试1");
        Flyweight flyweight2 = FlyweightFactory.getInstance("测试2");
        Flyweight flyweight3 = FlyweightFactory.getInstance("测试2");
        System.out.println(flyweight1 == flyweight2);
        System.out.println(flyweight2 == flyweight3);
    }

    public static interface Flyweight {

        void execute();

        String getName();

        void setName(String name);
    }

    public static class ConcreteFlyweight implements Flyweight {

        private String name;

        public ConcreteFlyweight(String name) {
            this.name = name;
        }

        @Override
        public void execute() {
            System.out.println(name + "执行逻辑操作");
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public void setName(String name) {
            this.name = name;
        }

    }


    public static class FlyweightFactory {

        public static Map<String, Flyweight> cachePool = new HashMap<>();

        public static Flyweight getInstance(String name) {
            Flyweight flyweight = cachePool.get(name);
            if (flyweight == null) {
                flyweight = new ConcreteFlyweight(name);
                cachePool.put(name, flyweight);
            }
            return flyweight;
        }

    }


}
