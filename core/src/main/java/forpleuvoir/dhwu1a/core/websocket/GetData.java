package forpleuvoir.dhwu1a.core.websocket;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.util.JsonUtil;

/**
 * 获取数据格式
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.websocket
 * <p>#class_name GetData
 * <p>#create_time 2021/6/29 21:29
 */
public class GetData {
    @SerializedName("syncId")
    protected Integer syncId;
    @SerializedName("data")
    protected JsonObject data;



    public boolean isCallback() {
        return this.syncId != -1;
    }

    @Override
    public String toString() {
        return JsonUtil.toJsonStr(this);
    }

    public JsonObject getData() {
        return data;
    }
}
