package forpleuvoir.dhwu1a.core.config

import com.google.gson.JsonElement
import forpleuvoir.dhwu1a.core.util.JsonUtil

/**
 * 配置接口
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.config
 * <p>#class_name IConfig
 * <p>#create_time 2021/8/9 23:44
 */
interface IConfig {
    /**
     * 将数据写入JsonElement
     * @return JsonElement
     */
    fun toJson(): JsonElement {
        return JsonUtil.gson.toJsonTree(this)
    }
}

inline fun <reified C : IConfig> JsonElement.getAsConfig(): C {
    return JsonUtil.gson.fromJson(this, C::class.java)
}