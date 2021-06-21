//package life.icetea.test.shiro;
//
//import org.apache.shiro.session.mgt.SessionManager;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.apache.shiro.web.servlet.ShiroHttpSession;
//import org.apache.shiro.web.servlet.SimpleCookie;
//import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.annotation.Resource;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//
//@Configuration
//public class ShiroConfig {
//
////	@Resource
////	private RedisSessionDAO redisSessionDAO;
//
//	@Bean
//	@Primary
//	public UserPwdRealm userPwdRealm(){
//		UserPwdRealm userPwdRealm = new UserPwdRealm();
//		return userPwdRealm;
//	}
//
////	@Bean
////	public RedisCacheManager redisCacheManager() {
////		return new RedisCacheManager();
////	}
//
////	@Bean
////	public SessionManager sessionManager() {
////		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
////		SimpleCookie simpleCookie = new SimpleCookie(ShiroHttpSession.DEFAULT_SESSION_ID_NAME);
////		simpleCookie.setPath("/");
////		simpleCookie.setHttpOnly(true);
////		sessionManager.setSessionIdCookie(simpleCookie);
////		sessionManager.setSessionDAO(redisSessionDAO);
////		sessionManager.setDeleteInvalidSessions(true);
////		sessionManager.setSessionValidationSchedulerEnabled(true);
////		return sessionManager;
////	}
//
//
//
//	@Bean
//	public SecurityManager securityManager(){
//		DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
//		securityManager.setRealm(userPwdRealm());
//		securityManager.setSessionManager(sessionManager());
//		securityManager.setCacheManager(redisCacheManager());
//		return securityManager;
//	}
//
//	//使注解生效
//	@Bean
//	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
//		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//		return authorizationAttributeSourceAdvisor;
//	}
//
//
//	@Bean
//	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
//		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//		shiroFilterFactoryBean.setLoginUrl("http://v2.jf180.cn/backbmg/unauth");
//		shiroFilterFactoryBean.setSecurityManager(securityManager);
//		//拦截器
//		Map<String,String> filterChainDefinitionMap=new LinkedHashMap<>();
//		// authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
//		filterChainDefinitionMap.put("/**/unauth", "anon");
//		filterChainDefinitionMap.put("/**/logout", "anon");
//		filterChainDefinitionMap.put("/**/login", "anon");
//		filterChainDefinitionMap.put("/**", "authc");
//
//		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//
//		return shiroFilterFactoryBean;
//	}
//
//
//}
