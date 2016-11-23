package cn.com.admin.sys.controller;

import cn.com.admin.common.model.SysMenu;
import cn.com.admin.common.result.DatagridPage;
import cn.com.admin.sys.service.MenuService;
import cn.com.admin.sys.valid.MenuValidator;
import cn.com.admin.sys.valid.UserValidator;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

import java.util.List;

/**
 * MenuController
 */
public class MenuController extends Controller {
	private static final MenuService menuService = MenuService.service;

	public void index() {
		render("show.html");
	}
	public List pList() {
		return menuService.pList() ;
	}
	public DatagridPage list() {
		return menuService.page(getBean(SysMenu.class,""),getParaToInt("page"),getParaToInt("rows")) ;
	}
	@Before(MenuValidator.class)
	public void save() {
		menuService.save(getModel(SysMenu.class, ""));
	}
	@Before(MenuValidator.class)
	public void update() {
		menuService.update(getModel(SysMenu.class, ""));
	}
	public SysMenu edit() {
		return menuService.edit(getModel(SysMenu.class, "").getMid());
	}
	public void del() {
		menuService.del(getParaValuesToInt("mid[]"));
	}

	/**
	 * 获取ztree全部数据
	 * @return
     */
	public List getZtreeAll() {
		return menuService.getZtreeAll() ;
	}
}


