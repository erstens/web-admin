package cn.com.admin.common.result;

/**
 * ActionResult返回结果封装
 * Created by wang'ao on 2016/11/9 0009.
 */
public class ActionResult {
    private String msg ;
    private Boolean success ;
    private Object data ;
    private RuntimeException exception ;
    private String detail ;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public RuntimeException getException() {
        return exception;
    }

    public void setException(RuntimeException exception) {
        this.exception = exception;
    }
}
