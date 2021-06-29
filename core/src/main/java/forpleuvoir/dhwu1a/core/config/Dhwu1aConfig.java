package forpleuvoir.dhwu1a.core.config;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.util.JsonUtil;

/**
 * 配置
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.config
 * <p>#class_name Dhwu1aConfig
 * <p>#create_time 2021/6/28 20:34
 */
public class Dhwu1aConfig {
    @SerializedName("botId")
    public final Long botId;
    @SerializedName("ip")
    public final String ip;
    @SerializedName("port")
    public final Integer port;
    @SerializedName("verifyKey")
    public final String verifyKey;
    @SerializedName("logConfig")
    public LogConfig logConfig;

    public Dhwu1aConfig(Long botId, String ip, Integer port, String verifyKey, LogConfig logConfig) {
        this.botId = botId;
        this.ip = ip;
        this.port = port;
        this.verifyKey = verifyKey;
        this.logConfig = logConfig;
    }

    @Override
    public String toString() {
        return JsonUtil.toJsonStr(this);
    }
}