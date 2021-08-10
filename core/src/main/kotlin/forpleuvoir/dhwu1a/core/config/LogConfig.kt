package forpleuvoir.dhwu1a.core.config

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.IJsonData

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.config
 *
 * #class_name LogConfig
 *
 * #create_time 2021/6/29 23:33
 */
class LogConfig : IJsonData, IConfig {
    @SerializedName("info")
    var info: Boolean = true
        private set

    @SerializedName("debug")
    var debug: Boolean = true
        private set

    @SerializedName("error")
    var error: Boolean = true
        private set

    @SerializedName("warn")
    var warn: Boolean = true
        private set

    override fun fromJson(jsonElement: JsonElement): LogConfig {
        if (jsonElement.isJsonObject) {
            val json = jsonElement.asJsonObject
            info = json.get("info").asBoolean
            debug = json.get("debug").asBoolean
            error = json.get("error").asBoolean
            warn = json.get("warn").asBoolean
        }
        return this
    }
}
