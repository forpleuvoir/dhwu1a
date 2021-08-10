package forpleuvoir.dhwu1a.core.websocket

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.util.toJsonStr

/**
 * 获取数据格式
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.websocket
 *
 * #class_name GetData
 *
 * #create_time 2021/6/29 21:29
 */
open class GetData(syncId: Int, data: JsonObject) {
    @SerializedName("syncId")
    var syncId: Int = syncId
        protected set
    @SerializedName("data")
    var data: JsonObject = data
        protected set
    val isCallback: Boolean
        get() = syncId != -1

    override fun toString(): String {
        return this.toJsonStr()
    }
}