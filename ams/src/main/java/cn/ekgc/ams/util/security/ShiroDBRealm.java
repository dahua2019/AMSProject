package cn.ekgc.ams.util.security;

import cn.ekgc.ams.controller.base.enums.StatusEnum;
import cn.ekgc.ams.pojo.entity.User;
import cn.ekgc.ams.service.UserService;
import cn.ekgc.ams.util.MD5Util;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * <b>Shiro 认证域</b>
 * @author Wang
 * @version 1.0.0
 * @since 1.0.0
 */
public class ShiroDBRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession session;

	/**
	 * <b>当需要授权的时候，会调用该方法</b>
	 * @param principalCollection
	 * @return
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		return null;
	}

	/**
	 * <b>当需要认证的时候，会调用该方法</b>
	 * @param authenticationToken
	 * @return
	 * @throws AuthenticationException
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		//将 AuthenticationToken 进行强制类型转换
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		//根据用户名查找对应的 User 对象
		try {
			User user = userService.getUserByCellphone(token.getUsername());
			//判断用户对象是否为 null 和状态是否为启用
			if (user != null && user.getStatus() == StatusEnum.STATUS_ENABLE.getCode()) {
				//从 token中获得用户的登录密码，并对其加密(从 token 中获得的密码是数组类型)
				String password = MD5Util.encrypt(new String(token.getPassword()));
				//将 password 重新设定到 token 中
				token.setPassword(password.toCharArray());
				//创建 SimpleAuthenticationInfo 来校验用户提交的信息
				SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getCellphone(), user.getPassword(), getName());
				//默认登录成功，绑定 Session 对象
				session.setAttribute("user", user);
				return info;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
