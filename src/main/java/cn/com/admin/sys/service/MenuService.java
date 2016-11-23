package cn.com.admin.sys.service;

import cn.com.admin.common.dao.BaseDAO;
import cn.com.admin.common.model.SysMenu;
import cn.com.admin.common.result.DatagridPage;
import cn.com.admin.common.util.DateUtil;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.tx.Tx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * service
 * Created by wang'ao on 2016/11/9 0009.
 */
public class MenuService {
    public static final MenuService service = new MenuService();
    private static final SysMenu menu = SysMenu.dao;
    private static final BaseDAO dao = BaseDAO.dao;

    public DatagridPage page(SysMenu u,Integer pageNumber,Integer pageSize) {
        return menu.queryForList(u,pageNumber,pageSize) ;
    }

    @Before(Tx.class)
    public void save(SysMenu model) {
        model.setCreateTime(DateUtil.formatNow());
        model.save();
    }
    public SysMenu edit(Integer uid) {
        return menu.findById(uid);
    }
    @Before(Tx.class)
    public void update(SysMenu model) {
        model.update() ;
    }
    @Before(Tx.class)
    public void del(Integer [] ids) {
        dao.deleteByInId(menu,ids) ;
    }

    public List pList() {
        return menu.getPList() ;
    }

    public List getZtreeAll() {
        List<SysMenu> list = menu.queryAll();
        List ztrees = new ArrayList<>() ;
        for (SysMenu sysMenu : list) {
            Map m = new HashMap<>() ;
            m.put("id",sysMenu.getMid()) ;
            m.put("pId",sysMenu.getPId()) ;
            m.put("createTime",sysMenu.getCreateTime()) ;
            m.put("name",sysMenu.getTxtName()) ;
            m.put("seqNo",sysMenu.getSeqNo()) ;
            m.put("url",sysMenu.getUrl()) ;

            ztrees.add(m);
        }
        return ztrees;
    }

    /**
     * 获取分类菜单
     * @param uid
     * @return
     */
    public List getMenusSort(Integer uid) {
        List<SysMenu> l = menu.queryMenusByUid(uid) ;
        List<Map<Integer,List>> list = new ArrayList<>() ;
        Map<Integer,Integer> indexMap = new HashMap<>() ;//{id:index}
        for (SysMenu menu : l) {
            if(menu.getPId() == 0) {
                indexMap.put(menu.getMid(), list.size() == 0 ? 0 : (list.size() - 1));

                Map m = new HashMap<>();
                m.put("mId", menu.getMid());
                m.put("mTxt", menu.getTxtName());
                m.put("iconClass", menu.getIconClass());
                m.put("subMenu", new ArrayList<>());

                list.add(m);
            }
        }
        for (SysMenu menu : l) {
            if(menu.getPId() != 0){
                List sub = list.get(indexMap.get(menu.getPId())).get("subMenu") ;

                Map mSub = new HashMap<>() ;
                mSub.put("mId",menu.getMid()) ;
                mSub.put("mTxt",menu.getTxtName()) ;
                mSub.put("iconClass",menu.getIconClass()) ;
                mSub.put("url",menu.getUrl()) ;

                sub.add(mSub);
            }

        }
        return list;
    }
}