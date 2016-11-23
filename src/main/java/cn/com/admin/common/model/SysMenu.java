package cn.com.admin.common.model;

import cn.com.admin.common.model.base.BaseSysMenu;
import cn.com.admin.common.result.DatagridPage;
import cn.com.admin.common.util.Conditions;

import java.util.List;

import static java.awt.SystemColor.menu;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class SysMenu extends BaseSysMenu<SysMenu> {
	private String startTime ;
	private String endTime ;

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public static final SysMenu dao = new SysMenu();

	/**
	 * 查询列表数据
	 * @param user
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public DatagridPage queryForList(SysMenu user, Integer pageNumber, Integer pageSize) {
		Conditions c = new Conditions() ;

		c.setFiledQuery(Conditions.FUZZY,"txt_name");
		c.setValueQuery(Conditions.GREATER_EQUAL,"create_time",user.getStartTime());
		c.setValueQuery(Conditions.LESS_EQUAL,"create_time",user.getEndTime());

		c.modelToCondition(user);
		return DatagridPage.toGridPage(user.paginate(pageNumber, pageSize, "SELECT *", "FROM sys_menu WHERE 1=1 " + c.getSql() + " ORDER BY mid DESC ",c.getParamList().toArray(new String[]{}))) ;
	}

	public List getPList() {
		return find("SELECT mid,txt_name FROM sys_menu WHERE 1=1 AND p_id = 0 ");
	}

	public List queryAll() {
		return find("SELECT * FROM sys_menu WHERE 1=1 ");
	}

	public List<SysMenu> queryMenusByUid(Integer uid) {
		return find("SELECT sm.* FROM sys_menu sm JOIN sys_role_menu_rela srmr ON srmr.mid = sm.mid JOIN sys_role sr ON sr.rid = srmr.rid JOIN sys_user su ON su.rid = sr.rid AND su.uid = ? ORDER BY seq_no ",uid);
	}
}