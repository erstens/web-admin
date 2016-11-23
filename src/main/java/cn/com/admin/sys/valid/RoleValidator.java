package cn.com.admin.sys.valid;

import cn.com.admin.common.valid.AbstractBaseValidator;
import com.jfinal.core.Controller;

/**
 * RoleValidator.
 */
public class RoleValidator extends AbstractBaseValidator {
	@Override
	protected void validate(Controller controller) {
		validateRequiredString("txt_name", "txtName", "请输入角色编码名!");
		validateRequiredString("r_code", "rCode", "请输入角色编码!");
	}
}