package icetea916.test.shiro.authentication;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

public class LoginLogoutTest {

    @Test
    public void testHelloWorld() {
        // 1.获取securityManager工厂
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        // 2.获取securityManager实例
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);


        // 创建身份验证Token
        UsernamePasswordToken token = new UsernamePasswordToken("icetea", "3");

        // 得到主体(当前登录的主体，验证成功后一些信息会保存在主体中)，并进行身份验证
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);

        Assert.assertEquals(true, subject.isAuthenticated());

        // 退出
        subject.logout();
    }

    @Test
    public void testCustomRealm() {
        // 1.获取securityManager工厂
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        // 2.获取securityManager实例
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);


        // 创建身份验证Token
        UsernamePasswordToken token = new UsernamePasswordToken("icetea", "123");

        // 得到主体(当前登录的主体，验证成功后一些信息会保存在主体中)，并进行身份验证
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);

        Assert.assertEquals(true, subject.isAuthenticated());

        // 退出
        subject.logout();
    }

    @Test
    public void testJDBCRealm() {
        // 1.获取securityManager工厂
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
        // 2.获取securityManager实例
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);



        // 创建身份验证Token
        UsernamePasswordToken token = new UsernamePasswordToken("icetea", "1");

        // 得到主体(当前登录的主体，验证成功后一些信息会保存在主体中)，并进行身份验证
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);

        Assert.assertEquals(true, subject.isAuthenticated());

        // 退出
        subject.logout();
    }

}
