package cn.com.admin.sys.controller;

import cn.com.admin.common.model.SysRole;
import cn.com.admin.common.result.DatagridPage;
import cn.com.admin.sys.service.RoleService;
import cn.com.admin.sys.valid.RoleValidator;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

import java.util.List;

/**
 * RoleController
 */
public class RoleController extends Controller {
	private static final RoleService roleService = RoleService.service;

	public void index() {
		render("show.html");
	}
	public DatagridPage list() {
		return roleService.page(getBean(SysRole.class,""),getParaToInt("page"),getParaToInt("rows")) ;
	}
	@Before(RoleValidator.class)
	public void save() {
		roleService.save(getModel(SysRole.class, ""));
	}
	@Before(RoleValidator.class)
	public void update() {
		roleService.update(getModel(SysRole.class, ""));
	}
	public SysRole edit() {
		return roleService.edit(getModel(SysRole.class, "").getRid());
	}
	public void del() {
		roleService.del(getParaValuesToInt("rid[]"));
	}

	/**
	 * 获取全部combobox数据
	 * @return
     */
	public List getAll() {
		return roleService.getAll() ;
	}

	/**
	 * 获取角色菜单ids
	 * @return
     */
	public List getMenuIds() {
		return roleService.getMenuIds(getParaToInt("rid")) ;
	}

	/**
	 * 更新菜单
	 */
	public void updateMenu() {
		roleService.updateMenu(getParaValues("ids[]"),getParaToInt("rid"));
	}
}


