package forpleuvoir.dhwu1a.core.common.data;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.common.IJsonData;
import forpleuvoir.dhwu1a.core.message.base.IPlainText;

import static forpleuvoir.dhwu1a.core.common.ApiKey.ID;
import static forpleuvoir.dhwu1a.core.common.ApiKey.KIND;

/**
 * 戳一戳事件发生的主体 (上下文)
 *
 * @author forpleuvoir
 * <p>#project_name dhwuia
 * <p>#package forpleuvoir.dhwuia.core.event.data
 * <p>#class_name Subject
 * <p>#create_time 2021/4/18 2:42
 */
public class Subject implements IJsonData, IPlainText {
    /**
     * 事件发生主体的 ID (群号 / 好友 QQ 号)
     */
    @SerializedName(ID)
    public final long id;
    /**
     * 戳一戳事件发生的主体的类型, 可以为 Group 和 Friend
     */
    @SerializedName(KIND)
    public final SubjectType kind;

    public Subject(JsonObject object) {
        this.id = object.get(ID).getAsLong();
        this.kind = SubjectType.valueOf(object.get(KIND).getAsString());
    }

    @Override
    public String toPlainText() {
        return String.format("subject(id:%d,kind:%s)", id, kind);
    }

    /**
     * 戳一戳事件发生的主体的类型
     */
    public enum SubjectType {
        Group,
        Friend,
        Stranger
    }
}
