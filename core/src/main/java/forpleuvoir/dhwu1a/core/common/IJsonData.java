package forpleuvoir.dhwu1a.core.common;

import forpleuvoir.dhwu1a.core.util.JsonUtil;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.common
 * <p>#class_name IJsonData
 * <p>#create_time 2021/6/30 0:38
 */
public interface IJsonData {

    default String toJsonString() {
        return JsonUtil.toJsonStr(this);
    }
}
