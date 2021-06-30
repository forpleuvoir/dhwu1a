package forpleuvoir.dhwu1a.core.util;

import com.google.gson.*;
import forpleuvoir.dhwu1a.core.message.messageitem.Forward;

import java.awt.*;
import java.util.Collection;

/**
 * json工具类
 *
 * @author forpleuvoir
 * <p>#project_name suikalib
 * <p>#package forpleuvoir.dhwuia.core.util
 * <p>#class_name JsonUtil
 * <p>#create_time 2020/11/10 21:46
 */
public class JsonUtil {
    public static Gson gson;

    static {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Forward.NodeItem.class, new Forward.NodeItemAdapter());
        builder.setPrettyPrinting();
        gson = builder.create();
    }

    public static StringBuffer toStringBuffer(Object obj) {
        return new StringBuffer(gson.toJson(obj));
    }

    /**
     * 将对象转换成json {@link String}
     *
     * @param json 需要转换的对象
     * @return json字符串
     */
    public static String toJsonStr(Object json) {
        return gson.toJson(json);
    }

    /**
     * 检查json{@link String}是否包含 key
     *
     * @param jsonString json{@link String}
     * @param key        key
     * @return {@link Boolean}
     */
    public static boolean hasKey(String jsonString, String key) {
        try {
            JsonObject jsonObject = strToJsonObject(jsonString);
            return jsonObject.has(key);
        } catch (Exception ignore) {
            return false;
        }
    }

    /**
     * 将json字符串转换成{@link JsonObject}
     *
     * @param json 需要转换的{@link String}对象
     * @return JsonObject对象
     * @throws JsonSyntaxException 无效的json文本
     */
    public static JsonObject strToJsonObject(String json) {
        return JsonParser.parseString(json).getAsJsonObject();
    }

    /**
     * 将对象转换成{@link JsonElement}
     *
     * @param object 需要转换的{@link Object}对象
     * @return {@link JsonElement}对象
     * @throws JsonSyntaxException 无效的json文本
     */
    public static JsonElement toJsonObject(Object object) {
        return gson.toJsonTree(object);
    }

    /**
     * 将json字符串转换成{@link JsonElement}
     *
     * @param json 需要转换的{@link String}对象
     * @return {@link JsonElement}对象
     * @throws JsonSyntaxException 无效的json文本
     */
    public static JsonElement strToJsonElement(String json) {
        return JsonParser.parseString(json);
    }

    /**
     * 将json字符串转换成{@link JsonArray}
     *
     * @param json 需要转换的{@link String}对象
     * @return JsonObject对象
     * @throws JsonSyntaxException 无效的json文本
     */
    public static JsonArray strToJsonArray(String json) {
        return JsonParser.parseString(json).getAsJsonArray();
    }

    /**
     * 将{@link Collection}转换成{@link JsonArray}
     *
     * @param collection 需要转换的{@link Collection}
     * @return {@link JsonArray}
     */
    public static JsonArray toJsonArray(Collection<?> collection) {
        return gson.toJsonTree(collection).getAsJsonArray();
    }


}
