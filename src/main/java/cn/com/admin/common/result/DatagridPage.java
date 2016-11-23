package cn.com.admin.common.result;

import com.jfinal.plugin.activerecord.Page;

import java.util.List;

/**
 * easyui datagrid contruct
 * Created by wang'ao on 2016/11/9 0009.
 */
public class DatagridPage {
    private Integer total ;
    private List rows ;
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    /**
     * 转换到easyui grid
     * @param p
     * @return
     */
    public static DatagridPage toGridPage(Page p) {
        DatagridPage page = new DatagridPage() ;
        page.setTotal(p.getTotalRow());
        page.setRows(p.getList());
        return page ;
    }
}
