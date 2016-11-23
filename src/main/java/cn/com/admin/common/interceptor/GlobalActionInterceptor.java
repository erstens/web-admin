package cn.com.admin.common.interceptor;

import cn.com.admin.common.result.ActionResult;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import org.apache.commons.lang.StringUtils;

/**
 * Created by wang'ao on 2016/11/10 0010.
 */
public class GlobalActionInterceptor implements Interceptor {

    public void intercept(Invocation inv) {
        if (!"/login".equals(inv.getControllerKey()) && !StringUtils.isNotBlank(inv.getController().getCookie("user"))) {
            inv.getController().redirect("/login");
            return ;
        }
        ActionResult r = new ActionResult() ;
        try {
            inv.invoke();
            r.setSuccess(true);
            r.setMsg("操作成功.");
            r.setData(inv.getReturnValue());
        }
        catch(RuntimeException e) {
            r.setMsg("操作失败.");
            r.setDetail(e.getMessage());
            r.setException(e);
            r.setSuccess(false);
        }
        if(!"index".equals(inv.getMethodName())) inv.getController().renderJson(r);
    }
}
