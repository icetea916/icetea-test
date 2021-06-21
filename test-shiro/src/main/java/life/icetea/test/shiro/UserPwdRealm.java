//package life.icetea.test.shiro;
//
//import cn.jf180.promotion.bmg.config.shiro.pojo.SysPermission;
//import cn.jf180.promotion.bmg.config.shiro.pojo.SysRole;
//import cn.jf180.promotion.bmg.config.shiro.pojo.UserInfo;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.session.Session;
//import org.apache.shiro.subject.PrincipalCollection;
//
//@Slf4j
//public class UserPwdRealm extends AuthorizingRealm {
//
//	private Gson gson = new Gson();
//
//	@Override
//	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//		Session session = SecurityUtils.getSubject().getSession();
//		UserInfo userInfo = gson.fromJson((String)session.getAttribute("userInfo"), new TypeToken<UserInfo>(){}.getType());
//		for (SysRole sysRole : userInfo.sysRoles) {
//			authorizationInfo.addRole(sysRole.getRole());
//			for (SysPermission sysPermission : userInfo.sysPermissions) {
//				authorizationInfo.addStringPermission(sysPermission.getPermission());
//			}
//		}
//		return authorizationInfo;
//	}
//
//	@Override
//	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//
//		log.warn("进入账号验证，此处不应进入");
//
//		return null;
//	}
//
//
//
//}
