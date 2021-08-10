package forpleuvoir.dhwu1a.core.config

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.IJsonData

/**
 * 配置类
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.config
 *
 * #class_name Dhwu1aConfig
 *
 * #create_time 2021/8/9 23:34
 */
class Dhwu1aConfig(botId: Long, ip: String, port: Int, verifyKey: String, logConfig: LogConfig) : IJsonData, IConfig {

    @SerializedName("botId")
    var botId: Long = botId
        private set

    @SerializedName("ip")
    var ip: String = ip
        private set

    @SerializedName("port")
    var port: Int = port
        private set

    @SerializedName("verifyKey")
    var verifyKey: String = verifyKey
        private set

    @SerializedName("logConfig")
    var logConfig: LogConfig = logConfig
        private set


    override fun fromJson(jsonElement: JsonElement): Dhwu1aConfig {
        if (jsonElement.isJsonObject) {
            val json = jsonElement.asJsonObject
            botId = json.get("botId").asLong
            ip = json.get("ip").asString
            port = json.get("port").asInt
            verifyKey = json.get("verifyKey").asString
            logConfig = LogConfig().fromJson(json.get("logConfig"))
        }
        return this
    }

}
