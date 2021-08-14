package forpleuvoir.dhwu1a.core.util

import com.google.gson.*
import forpleuvoir.dhwu1a.core.message.messageitem.NodeItem
import forpleuvoir.dhwu1a.core.message.messageitem.NodeItemAdapter

/**
 * json工具类
 *
 * @author forpleuvoir
 *
 * #project_name suikalib
 *
 * #package forpleuvoir.dhwuia.core.util
 *
 * #class_name JsonUtil
 *
 * #create_time 2020/11/10 21:46
 */
object JsonUtil {
    @JvmField
    var gson: Gson

    init {
        val builder = GsonBuilder()
        builder.registerTypeAdapter(NodeItem::class.java, NodeItemAdapter())
        builder.setPrettyPrinting()
        gson = builder.create()
    }
}

fun String?.isEmptyString(): Boolean {
    return (this == null) || this.isEmpty()
}

/**
 * 获取json[String] 对应 key 的值
 *
 * @param key        key
 * @return [JsonElement] 如果没有则返回 null
 */
fun String.getKeyValue(key: String): JsonElement? {
    return this.ifHasKey(key)?.let { jsonObject: JsonObject -> jsonObject[key] }
}

/**
 * 如果json对象[JsonObject] 包含对应key则返回[JsonElement]
 *
 * @param key        key
 * @return [JsonElement] 如果没有则返回 null
 */
fun JsonObject.ifHasKey(key: String): JsonElement? {
    return if (this.has(key)) {
        this[key]
    } else null
}

/**
 * 如果json[String] 包含对应key则返回[JsonObject]
 *
 * @param key        key
 * @return [JsonObject] 如果没有则返回 null
 */
fun String.ifHasKey(key: String): JsonObject? {
    return if (this.hasKey(key)) {
        this.toJsonObject()
    } else null
}

/**
 * 检查json[String]是否包含 key
 *
 * @param key        key
 * @return [Boolean]
 */
fun String.hasKey(key: String): Boolean {
    return try {
        this.toJsonObject().has(key)
    } catch (ignore: Exception) {
        false
    }
}

/**
 * 将json字符串转换成[JsonObject]
 *
 * @return JsonObject对象
 */
fun String.toJsonObject(): JsonObject {
    return JsonParser().parse(this).asJsonObject
}

/**
 * 将对象转换成[JsonObject]
 *
 * @return [JsonObject]对象
 */
fun Any.toJsonObject(): JsonObject {
    return JsonUtil.gson.toJsonTree(this).asJsonObject
}

/**
 * 将[List]转换成[JsonArray]
 *
 * @return [JsonArray]
 */
fun List<*>.toJsonArray(): JsonArray {
    return JsonUtil.gson.toJsonTree(this).asJsonArray
}

/**
 * 将json字符串转换成[JsonArray]
 *
 * @return JsonObject对象
 * @throws JsonSyntaxException 无效的json文本
 */
fun String.toJsonArray(): JsonArray {
    return JsonParser().parse(this).asJsonArray
}

/**
 * 将对象转换成json [String]
 *
 * @return json字符串
 */
fun Any.toJsonStr(): String {
    return JsonUtil.gson.toJson(this)
}