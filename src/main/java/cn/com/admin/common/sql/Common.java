package cn.com.admin.common.sql;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wang'ao on 2016/11/11 0011.
 */
public class Common {
    /**
     * 将Model类转换为Map modelToMap
     *
     * @return 返回对象
     * @Exception 异常对象
     */
    public static Map<String, Object> modelToMap(Model<?> model) {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] names = model._getAttrNames();
        for (String str : names) {
            map.put(str, model.get(str));
        }
        return map;
    }
    /**
     * 将Record转换成Map recordToMap
     *
     * @return 返回对象
     * @Exception 异常对象
     */
    public static Map<String, Object> recordToMap(Record record) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (record != null) {
            String[] columns = record.getColumnNames();
            for (String col : columns) {
                map.put(col, record.get(col));
            }
        }
        return map;
    }
    /**
     * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
     *
     * @param obj
     * @return
     */
    @SuppressWarnings("unchecked")
    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof String && (obj.equals(""))) {
            return true;
        } else if (obj instanceof Short && ((Short) obj).shortValue() == 0) {
            return true;
        } else if (obj instanceof Integer && ((Integer) obj).intValue() == 0) {
            return true;
        } else if (obj instanceof Double && ((Double) obj).doubleValue() == 0) {
            return true;
        } else if (obj instanceof Float && ((Float) obj).floatValue() == 0) {
            return true;
        } else if (obj instanceof Long && ((Long) obj).longValue() == 0) {
            return true;
        } else if (obj instanceof Boolean && !((Boolean) obj)) {
            return true;
        } else if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
            return true;
        } else if (obj instanceof Map && ((Map) obj).isEmpty()) {
            return true;
        } else if (obj instanceof Object[] && ((Object[]) obj).length == 0) {
            return true;
        }
        return false;
    }
}
