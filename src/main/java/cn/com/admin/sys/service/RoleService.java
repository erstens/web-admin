package cn.com.admin.sys.service;

import cn.com.admin.common.dao.BaseDAO;
import cn.com.admin.common.model.SysRole;
import cn.com.admin.common.model.SysRoleMenuRela;
import cn.com.admin.common.result.DatagridPage;
import cn.com.admin.common.util.DateUtil;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.tx.Tx;

import java.util.ArrayList;
import java.util.List;

/**
 * service
 * Created by wang'ao on 2016/11/9 0009.
 */
public class RoleService {
    public static final RoleService service = new RoleService();
    private static final SysRole role = SysRole.dao;
    private static final SysRoleMenuRela roleMenu = SysRoleMenuRela.dao;
    private static final BaseDAO dao = BaseDAO.dao;

    public DatagridPage page(SysRole u,Integer pageNumber,Integer pageSize) {
        return role.queryForList(u,pageNumber,pageSize) ;
    }

    @Before(Tx.class)
    public void save(SysRole model) {
        model.setCreateTime(DateUtil.formatNow());
        model.save();
    }
    public SysRole edit(Integer rid) {
        return role.findById(rid);
    }
    @Before(Tx.class)
    public void update(SysRole model) {
        model.update() ;
    }
    @Before(Tx.class)
    public void del(Integer [] ids) {
        dao.deleteByInId(role,ids) ;
        for (Integer id : ids) {
            roleMenu.deleteByRid(id);
        }
    }

    public List getAll() {
        return role.queryAll();
    }

    public List getMenuIds(Integer rid) {
        List<SysRoleMenuRela> list = roleMenu.queryMenuIds(rid);
        List res = new ArrayList<>() ;
        for (SysRoleMenuRela r : list) {
            res.add(r.getMid());
        }
        return res;
    }
    @Before(Tx.class)
    public void updateMenu(String[] paraValues,Integer rid) {
        roleMenu.deleteByRid(rid) ;
        roleMenu.insertValues(paraValues,rid);
    }
}