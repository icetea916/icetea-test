package life.icetea.test.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TestRole {

    @Test
    public void testRole() {
        // 1. 获取securityManager工厂,此处使用ini配置初始化SecurityManager
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-role.ini");
        // 2. 获取securityManager实例
        SecurityManager instance = factory.getInstance();
        SecurityUtils.setSecurityManager(instance);
        // 3. 获取subject并创建身份和密码进行登录验证
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("icetea", "1234");
        // 4. 登录认证
        subject.login(token);

        Assert.assertTrue(subject.hasRole("role1"));
        Assert.assertTrue(subject.hasAllRoles(Arrays.asList("role1", "role2")));
        boolean[] booleans = subject.hasRoles(Arrays.asList("role1", "role2", "role3"));
        Assert.assertTrue(booleans[0]);
        Assert.assertTrue(booleans[1]);
        Assert.assertFalse(booleans[2]);

        // 6. 退出
        subject.logout();
    }

}
