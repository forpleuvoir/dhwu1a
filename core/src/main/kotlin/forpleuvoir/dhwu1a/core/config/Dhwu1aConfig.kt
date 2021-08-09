package forpleuvoir.dhwu1a.core.config

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
class Dhwu1aConfig(
    @SerializedName("botId")
    val botId: Long,
    @SerializedName("ip")
    val ip: String,
    @SerializedName("port")
    val port: Int,
    @SerializedName("verifyKey")
    val verifyKey: String,
    @SerializedName("logConfig")
    val logConfig: LogConfig
) : IJsonData, IConfig {

}
