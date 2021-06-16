package life.icetea.test.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm1 implements Realm {

    @Override
    public String getName() {
        return "myRealm1";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取用户名
        String username = (String) token.getPrincipal();
        // 获取密码
        String password = new String((char[]) token.getCredentials());
        if (!"icetea".equals(username)) {
            // 账号错误
            throw new UnknownAccountException();
        }
        if (!"1234".equals(password)) {
            // 密码错误
            throw new IncorrectCredentialsException();
        }

        // 身份认证成功则放回一个AuthenticationInfo
        return new SimpleAuthenticationInfo(username, password, this.getName());
    }
}
