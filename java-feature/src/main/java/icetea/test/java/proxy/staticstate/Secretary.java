package icetea.test.java.proxy.staticstate;

import icetea.test.java.proxy.IWork;
import icetea.test.java.proxy.Leader;

/**
 * 秘书类：静态代理领导
 */
public class Secretary implements IWork {

    private Leader leader;

    public Secretary(Leader leader) {
        this.leader = leader;
    }


    @Override
    public void meeting() {
        System.out.println("秘书先给老板准备材料");
        leader.meeting();
    }

    @Override
    public int evaluate(String name) {
        System.out.println("秘书给老板引荐人才");
        return leader.evaluate(name);
    }
}
