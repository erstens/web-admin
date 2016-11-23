package cn.com.admin.sys.service;

import cn.com.admin.common.controller.LoginController;
import cn.com.admin.common.dao.BaseDAO;
import cn.com.admin.common.model.SysUser;
import cn.com.admin.common.result.DatagridPage;
import cn.com.admin.common.util.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.tx.Tx;

import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * service
 * Created by wang'ao on 2016/11/9 0009.
 */
public class UserService {
    public static final UserService service = new UserService();
    private static final SysUser user = SysUser.dao;
    private static final BaseDAO dao = BaseDAO.dao;

    public DatagridPage page(SysUser u,Integer pageNumber,Integer pageSize) {
        return user.queryForList(u,pageNumber,pageSize) ;
    }

    @Before(Tx.class)
    public void save(SysUser model) {
        model.setCreateTime(DateUtil.formatNow());
        model.save();
    }
    public SysUser edit(Integer uid) {
        return user.findById(uid);
    }
    @Before(Tx.class)
    public void update(SysUser model) {
        model.update() ;
    }
    @Before(Tx.class)
    public void del(Integer [] ids) {
        dao.deleteByInId(user,ids) ;
    }

    public Boolean login(SysUser bean, LoginController loginController) {
        SysUser u = user.findUserFirst(bean.getUname(),bean.getPwd()) ;
        try {
            if(null != u) {
                Cookie c = new Cookie("user", URLEncoder.encode(JSONObject.toJSONString(u),"UTF-8")) ;
                c.setPath(loginController.getRequest().getContextPath());
                loginController.setCookie(c) ;
            }
            else throw new RuntimeException("账号或者密码不正确,请重新输入.") ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return true;
    }
}