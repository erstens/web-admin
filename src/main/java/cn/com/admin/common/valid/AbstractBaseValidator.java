package cn.com.admin.common.valid;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

import java.util.Enumeration;

/**
 * 继承jfinal的valid
 * 实现通用的错误输出格式
 * Created by wang'ao on 2016/11/21 0021.
 */
public abstract class AbstractBaseValidator extends Validator {
    protected void handleError(Controller controller) {
        Enumeration<String> names = controller.getAttrNames();

        StringBuffer buf = new StringBuffer();
        while (names.hasMoreElements()) {
            String s = names.nextElement();
            buf.append(controller.getAttr(s).toString()) ;
        }
        throw new RuntimeException("[" + buf + "]") ;
    }
}
