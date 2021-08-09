package forpleuvoir.dhwu1a.config

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
class LogConfig(
    @SerializedName("info")
    var info: Boolean,
    @SerializedName("debug")
    var debug: Boolean,
    @SerializedName("error")
    var error: Boolean,
    @SerializedName("warn")
    var warn: Boolean
) : IJsonData, IConfig {
    constructor() : this(true, true, true, true)
}
