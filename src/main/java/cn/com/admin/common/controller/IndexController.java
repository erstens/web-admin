package cn.com.admin.common.controller;

import cn.com.admin.common.model.SysUser;
import cn.com.admin.sys.service.MenuService;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * IndexController
 */
public class IndexController extends Controller {
    private static final MenuService menuService = MenuService.service;
    public void index() throws UnsupportedEncodingException {
        setAttr("user", JSONObject.parseObject(URLDecoder.decode(getCookie("user"), "UTF-8"), SysUser.class));
        setAttr("menus", menuService.getMenusSort(JSONObject.parseObject(URLDecoder.decode(getCookie("user"), "UTF-8"), SysUser.class).getUid()));
        render("/index.html");
    }
}
