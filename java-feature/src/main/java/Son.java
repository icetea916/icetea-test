public class Son extends Parent {
    // 静态变量
    public static String s_StaticField = "子类--静态变量";
    // 变量
    public String s_Field = "子类--变量";

    // 静态初始化块
    static {
        System.out.println(s_StaticField);
        System.out.println("子类--静态初始化块");
    }

    // 初始化块
    {
        System.out.println(s_Field);
        System.out.println("子类--初始化块");
    }

    // 构造器
    public Son() {
        //super();
        System.out.println("子类--构造器");
    }

    // 程序入口
    public static void main(String[] args) {
        System.out.println("*************in main***************");
        new Son();
        System.out.println("*************second subClass***************");
        new Son();
    }
}
