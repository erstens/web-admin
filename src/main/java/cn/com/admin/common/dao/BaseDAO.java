package cn.com.admin.common.dao;

import com.jfinal.plugin.activerecord.Model;

/**
 * baseDao
 * Created by wang'ao on 2016/11/10 0010.
 */
public class BaseDAO {
    public static final BaseDAO dao = new BaseDAO();
    public void deleteByInId(Model model,Integer [] ids) {
        for (Integer id : ids) {
            model.deleteById(id) ;
        }
    }
}
