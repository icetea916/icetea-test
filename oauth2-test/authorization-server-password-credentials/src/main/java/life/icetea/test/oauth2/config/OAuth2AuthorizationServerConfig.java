package life.icetea.test.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * 授权服务器配置
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    // 用户认证
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //security.checkTokenAccess("permitAll()");
        security.checkTokenAccess("isAuthenticated()");
    }


//    /**
//     * 授权模式-用户名密码模式
//     * @param clients the client details configurer
//     * @throws Exception
//     */
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("clientapp").secret("112233") // Client 账号、密码。
//                .authorizedGrantTypes("password") // 密码模式
//                .scopes("read_userinfo", "read_contacts") // 可授权的 Scope
////                .and().withClient() // 可以继续配置新的 Client
//        ;
//    }

    /**
     * 授权模式-授权码模式
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("clientapp").secret("112233") // Client 账号、密码。
                .authorizedGrantTypes("authorization_code")
                .redirectUris("http://localhost:9090/callback") // 配置回调地址，选填。
                .scopes("read_userinfo", "read_contacts") // 可授权的 Scope
//                .and().withClient() // 可以继续配置新的 Client
        ;
    }

}
