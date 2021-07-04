package forpleuvoir.dhwu1a.core.event;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.common.data.Subject;
import forpleuvoir.dhwu1a.core.event.base.Dhwu1aEvent;

import static forpleuvoir.dhwu1a.core.common.ApiKey.*;

/**
 * 戳一戳事件
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event
 * <p>#class_name NudgeEvent
 * <p>#create_time 2021/7/4 2:29
 */
public class NudgeEvent extends Dhwu1aEvent {
    /**
     * 事件类型
     */
    @SerializedName(TYPE)
    public static final String type = "NudgeEvent";
    /**
     * 戳一戳发起人 QQ 号
     */
    @SerializedName(FROM_ID)
    public final Long fromId;
    /**
     * 被戳人的 QQ 号
     */
    @SerializedName(TARGET)
    public final long target;
    /**
     * 戳一戳事件发生的主体 (上下文)
     */
    @SerializedName(SUBJECT)
    public final Subject subject;
    /**
     * 动作
     */
    @SerializedName(ACTION)
    public final String action;
    /**
     * 后缀
     */
    @SerializedName(SUFFIX)
    public final String suffix;

    public NudgeEvent(JsonObject object) {
        this.fromId = object.get(FROM_ID).getAsLong();
        this.target = object.get(TARGET).getAsLong();
        this.subject = new Subject(object.get(SUBJECT).getAsJsonObject());
        this.action = object.get(ACTION).getAsString();
        this.suffix = object.get(SUFFIX).getAsString();
    }

    @Override
    public String toPlainText() {
        return String
                .format("%s[fromId:%d,target:%d,action:%s,suffix:%s,subject:%s]", type, fromId, target, action, suffix,
                        subject.toPlainText()
                );
    }
}
