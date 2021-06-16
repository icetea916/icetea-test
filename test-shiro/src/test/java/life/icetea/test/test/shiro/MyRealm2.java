package life.icetea.test.test.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm2 implements Realm {

    @Override
    public String getName() {
        return "myRealm2";
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
        if (!"icetea916".equals(username)) {
            // 账号错误
            throw new UnknownAccountException();
        }
        if (!"1234".equals(password)) {
            // 密码错误
            throw new IncorrectCredentialsException();
        }

        // 身份认证成功则放回一个AuthenticationInfo
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, password, this.getName());

        return simpleAuthenticationInfo;
    }
}
