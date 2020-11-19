package icetea.test.spring.aop.test1;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Aspect // 声明该类是个切面类
@Component
public class BuyAspectJ {

    @Pointcut("execution(* icetea.test.spring.aop.test1.IBuy.buy(..))")
    public void point() {
    }

    /**
     * 声明一个切点，并使用aspectJ切点表达式来定义切面：
     * 通知类型一共5种：
     *
     * @Before 通知方法会在目标方法调用前执行
     * @After 通知方法会在目标方法返回后或异常后执行
     * @AfterReturning 通知方法会在目标方法返回参数后执行
     * @AfterThrowing 通知方法会在目标方法抛出异常后调用
     * @Around 通知方法会将目标方法封装起来
     */
    @Before("execution(* icetea.test.spring.aop.test1.IBuy.buy(..)) && within(icetea.test.spring.aop.test1.*) && bean(girl2)")
    public void buyBefore() {
        System.out.println("男孩女孩都买了自己喜欢的东西");
    }

    @After("point()")
    public void buyAfter() {
        System.out.println("男孩女孩买完东西后都很高兴");
    }

    @AfterReturning("point()")
    public void buyAfterReturning() {
        System.out.println("男孩女孩买完东西后都很高兴的回家了");
    }

    @Around("point()")
    public void buyAround(ProceedingJoinPoint pj) {
        try {
            System.out.println("Around aaa ...");
            pj.proceed();
            System.out.println("Around bbb ...");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}
