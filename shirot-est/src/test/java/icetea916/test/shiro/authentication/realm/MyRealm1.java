package icetea916.test.shiro.authentication.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * 需要在ini中指定realm的实现
 */
public class MyRealm1 implements Realm {

    @Override
    public String getName() {
        return "myReal1";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        // 仅支持用户名密码的token
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        if (!"icetea".equals(username)) {
            throw new UnknownAccountException();// 用户名错误
        }
        if (!"123".equals(password)) {
            throw new IncorrectCredentialsException();// 密码错误
        }

        // 认证成功，返回一个AuthenticationInfo实现
        return new SimpleAuthenticationInfo(username, password, getName());
    }

}
