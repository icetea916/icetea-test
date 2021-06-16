package life.icetea.test.test.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TestPermission {

    @Test
    public void testPermission() {
        // 1. 获取securityManager工厂,此处使用ini配置初始化SecurityManager
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
        // 2. 获取securityManager实例
        SecurityManager instance = factory.getInstance();
        SecurityUtils.setSecurityManager(instance);
        // 3. 获取subject并创建身份和密码进行登录验证
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("icetea", "1234");
        // 4. 登录认证
        subject.login(token);

        Assert.assertTrue(subject.isPermitted("user:create"));
        Assert.assertTrue(subject.isPermittedAll("user:create", "user:update"));
        boolean[] booleans = subject.isPermitted("user:create", "user:update", "user:delete");
        Assert.assertTrue(booleans[0]);
        Assert.assertTrue(booleans[1]);
        Assert.assertFalse(booleans[2]);

        // 6. 退出
        subject.logout();
    }

    @Test
    public void testCheckPermission() {
        // 1. 获取securityManager工厂,此处使用ini配置初始化SecurityManager
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
        // 2. 获取securityManager实例
        SecurityManager instance = factory.getInstance();
        SecurityUtils.setSecurityManager(instance);
        // 3. 获取subject并创建身份和密码进行登录验证
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("icetea", "1234");
        // 4. 登录认证
        subject.login(token);

        subject.checkPermission("user:create");
        subject.checkPermissions("user:create", "user:update");
        subject.checkPermission("user:view");

        // 6. 退出
        subject.logout();
    }

    /**
     * 权限通配符
     */
    @Test
    public void testCheckPermission2() {
        // 1. 获取securityManager工厂,此处使用ini配置初始化SecurityManager
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
        // 2. 获取securityManager实例
        SecurityManager instance = factory.getInstance();
        SecurityUtils.setSecurityManager(instance);
        // 3. 获取subject并创建身份和密码进行登录验证
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("icetea916", "1234");
        // 4. 登录认证
        subject.login(token);

        subject.checkPermissions("user:create", "user:delete");

        // 6. 退出
        subject.logout();
    }

}
