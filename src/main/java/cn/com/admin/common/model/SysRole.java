package cn.com.admin.common.model;

import cn.com.admin.common.model.base.BaseSysRole;
import cn.com.admin.common.result.DatagridPage;
import cn.com.admin.common.util.Conditions;

import java.util.List;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class SysRole extends BaseSysRole<SysRole> {
	public static final SysRole dao = new SysRole();
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

	/**
	 * 查询列表数据
	 * @param role
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public DatagridPage queryForList(SysRole role, Integer pageNumber, Integer pageSize) {
		Conditions c = new Conditions() ;

		c.setFiledQuery(Conditions.FUZZY,"r_code");
		c.setFiledQuery(Conditions.FUZZY,"txt_name");
		c.setValueQuery(Conditions.GREATER_EQUAL,"create_time",role.getStartTime());
		c.setValueQuery(Conditions.LESS_EQUAL,"create_time",role.getEndTime());

		c.modelToCondition(role);
		return DatagridPage.toGridPage(role.paginate(pageNumber, pageSize, "SELECT *", "FROM sys_role WHERE 1=1 " + c.getSql() + " ORDER BY rid DESC ",c.getParamList().toArray(new String[]{}))) ;
	}

	/**
	 * 查询全部
	 * @return
     */
	public List queryAll() {
		return find("SELECT * FROM sys_role ");
	}
}
