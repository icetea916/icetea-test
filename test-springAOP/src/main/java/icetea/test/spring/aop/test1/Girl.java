package icetea.test.spring.aop.test1;

public class Girl implements IBuy {

    @Override
    public String buy() {
        System.out.println("女孩买了一件漂亮的衣服");
        return "衣服";
    }

}
