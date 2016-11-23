package cn.com.admin.sys.valid;

import cn.com.admin.common.valid.AbstractBaseValidator;
import com.jfinal.core.Controller;

/**
 * UserValidator.
 */
public class UserValidator extends AbstractBaseValidator {
	@Override
	protected void validate(Controller controller) {
		validateRequiredString("uname", "unameMsg", "请输入用户名!");
		validateRequiredString("pwd", "pwdMsg", "请输入密码!");
		validateRequiredString("is_used", "isUsedMsg", "请选择是否启用!");
	}
}