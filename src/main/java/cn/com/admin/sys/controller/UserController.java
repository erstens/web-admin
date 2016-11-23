package cn.com.admin.sys.controller;

import cn.com.admin.common.model.SysUser;
import cn.com.admin.common.result.DatagridPage;
import cn.com.admin.sys.service.UserService;
import cn.com.admin.sys.valid.UserValidator;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

import java.util.List;

/**
 * UserController
 */
public class UserController extends Controller {
	private static final UserService userService = UserService.service;

	public void index() {
		render("show.html");
	}
	public DatagridPage list() {
		return userService.page(getBean(SysUser.class,""),getParaToInt("page"),getParaToInt("rows")) ;
	}
	@Before(UserValidator.class)
	public void save() {
		userService.save(getModel(SysUser.class, ""));
	}
	@Before(UserValidator.class)
	public void update() {
		userService.update(getModel(SysUser.class, ""));
	}
	public SysUser edit() {
		return userService.edit(getModel(SysUser.class, "").getUid());
	}
	public void del() {
		userService.del(getParaValuesToInt("uid[]"));
	}
}


