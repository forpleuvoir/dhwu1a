package forpleuvoir.dhwu1a.core.config;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.util.JsonUtil;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.config
 * <p>#class_name LogConfig
 * <p>#create_time 2021/6/29 23:33
 */
public class LogConfig {
    @SerializedName("info")
    public Boolean info = true;
    @SerializedName("debug")
    public Boolean debug = true;
    @SerializedName("error")
    public Boolean error = true;
    @SerializedName("warn")
    public Boolean warn = true;

    public LogConfig() {
    }

    public LogConfig(Boolean info, Boolean debug, Boolean error, Boolean warn) {
        this.info = info;
        this.debug = debug;
        this.error = error;
        this.warn = warn;
    }

    @Override
    public String toString() {
        return JsonUtil.toJsonStr(this);
    }
}
