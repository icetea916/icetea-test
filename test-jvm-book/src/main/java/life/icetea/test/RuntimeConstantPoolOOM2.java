package life.icetea.test;

/**
 * @author icetea
 * @date 2022-11-04 20:23
 */
public class RuntimeConstantPoolOOM2 {

    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);

    }

}
