package cn.com.admin.common.controller;

import cn.com.admin.common.model.SysUser;
import cn.com.admin.sys.service.UserService;
import com.jfinal.core.Controller;

import javax.servlet.http.Cookie;

/**
 * LoginController
 */
public class LoginController extends Controller {
	private static final UserService userService = UserService.service;

	public void index() {
		render("show.html") ;
	}
	public void login() {
		userService.login(getBean(SysUser.class,""),this);
	}
	public void loginOut() {
		removeCookie("user") ;
	}
	@Override
	public Controller removeCookie(String name) {
		Cookie c = new Cookie(name,"") ;
		c.setPath(getRequest().getContextPath());
		c.setMaxAge(0);
		c.setValue(null);
		setCookie(c);
		return this;
	}
}