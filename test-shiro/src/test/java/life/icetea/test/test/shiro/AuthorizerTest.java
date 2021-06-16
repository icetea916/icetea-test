package life.icetea.test.test.shiro;

import junit.framework.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class AuthorizerTest {


    @Test
    public void testIsPermitted() {
        // 1. 获取securityManager工厂,此处使用ini配置初始化SecurityManager
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-authorizer.ini");
        // 2. 获取securityManager实例
        SecurityManager instance = factory.getInstance();
        SecurityUtils.setSecurityManager(instance);
        // 3. 获取subject并创建身份和密码进行登录验证
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("icetea", "1234");
        // 4. 登录认证
        subject.login(token);

        subject.isAuthenticated();

        //判断拥有权限：user:create
        Assert.assertTrue(subject.isPermitted("user1:update"));
        Assert.assertTrue(subject.isPermitted("user2:update"));

        //通过二进制位的方式表示权限
        Assert.assertTrue(subject.isPermitted("+user1+2"));//新增权限
        Assert.assertTrue(subject.isPermitted("+user1+8"));//查看权限
        Assert.assertTrue(subject.isPermitted("+user2+10"));//新增及查看

        Assert.assertFalse(subject.isPermitted("+user1+4"));//没有删除权限

        Assert.assertTrue(subject.isPermitted("menu:view"));//通过MyRolePermissionResolver解析得到的权限
    }

    @Test
    public void testIsPermitted2() {
        // 1. 获取securityManager工厂,此处使用ini配置初始化SecurityManager
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-jdbc-authorizer.ini");
        // 2. 获取securityManager实例
        SecurityManager instance = factory.getInstance();
        SecurityUtils.setSecurityManager(instance);
        // 3. 获取subject并创建身份和密码进行登录验证
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("icetea", "1234");
        // 4. 登录认证
        subject.login(token);

        //判断拥有权限：user:create
        Assert.assertTrue(subject.isPermitted("user1:update"));
        Assert.assertTrue(subject.isPermitted("user2:update"));
        //通过二进制位的方式表示权限
        Assert.assertTrue(subject.isPermitted("+user1+2"));//新增权限
        Assert.assertTrue(subject.isPermitted("+user1+8"));//查看权限
        Assert.assertTrue(subject.isPermitted("+user2+10"));//新增及查看

        Assert.assertFalse(subject.isPermitted("+user1+4"));//没有删除权限

        Assert.assertTrue(subject.isPermitted("menu:view"));//通过MyRolePermissionResolver解析得到的权限
    }
}
