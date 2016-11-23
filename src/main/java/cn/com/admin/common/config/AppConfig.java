package cn.com.admin.common.config;

import cn.com.admin.common.controller.IndexController;
import cn.com.admin.common.controller.LoginController;
import cn.com.admin.common.interceptor.GlobalActionInterceptor;
import cn.com.admin.common.model._MappingKit;
import cn.com.admin.sys.controller.MenuController;
import cn.com.admin.sys.controller.RoleController;
import cn.com.admin.sys.controller.UserController;
import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import org.beetl.core.GroupTemplate;
import org.beetl.ext.jfinal.BeetlRenderFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * API引导式配置
 */
public class AppConfig extends JFinalConfig {

	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		// 加载少量必要配置，随后可用PropKit.get(...)获取值
		PropKit.use("a_little_config.txt");
		me.setDevMode(PropKit.getBoolean("devMode", false));

		loadPropertyFile("db.properties");
		//配置模板
		me.setMainRenderFactory(new BeetlRenderFactory());
		//获取GroupTemplate模板，可以设置共享变量操作
		GroupTemplate groupTemplate = BeetlRenderFactory.groupTemplate;
		Map<String,Object> shared = new HashMap<String,Object>();
		shared.put("root", "/web-admin");
		groupTemplate.setSharedVars(shared);

		me.setDevMode(getPropertyToBoolean("config.devModel", false));
		me.setViewType(ViewType.JSP);
		me.setEncoding("UTF-8");
	}

	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		me.add("/", IndexController.class);	// 第三个参数为该Controller的视图存放路径
		me.add("/user", UserController.class,"/view/admin/user");			// 第三个参数省略时默认与第一个参数值相同，在此即为 "/user"
		me.add("/login", LoginController.class,"/view/admin/login");
		me.add("/role", RoleController.class,"/view/admin/role");
		me.add("/menu", MenuController.class,"/view/admin/menu");
	}

	public static C3p0Plugin createC3p0Plugin() {
		return new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
	}

	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// 配置C3p0数据库连接池插件
		C3p0Plugin C3p0Plugin = createC3p0Plugin();
		me.add(C3p0Plugin);

		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(C3p0Plugin);
		me.add(arp);

		// 所有配置在 MappingKit 中搞定
		_MappingKit.mapping(arp);
	}

	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		// 添加控制层全局拦截器
		me.addGlobalActionInterceptor(new GlobalActionInterceptor());
	}

	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {

	}

	/**
	 * 建议使用 JFinal 手册推荐的方式启动项目
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
		JFinal.start("src/main/webapp", 80, "/web-admin", 5);
	}
}
