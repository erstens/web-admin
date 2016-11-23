package cn.com.admin.sys.valid;

import cn.com.admin.common.valid.AbstractBaseValidator;
import com.jfinal.core.Controller;

/**
 * MenuValidator.
 */
public class MenuValidator extends AbstractBaseValidator {
	@Override
	protected void validate(Controller controller) {
		validateRequiredString("txt_name", "txtNameMsg", "请输入菜单名!");
		validateRequiredString("p_id", "pIdMsg", "父级id为空写0填充.");
		validateRequiredString("url", "urlMsg", "url为空请填写#!");
		validateRequiredString("seq_no", "seqNoMsg", "序号为空!");
	}
}