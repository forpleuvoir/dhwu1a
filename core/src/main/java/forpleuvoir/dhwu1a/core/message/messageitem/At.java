package forpleuvoir.dhwu1a.core.message.messageitem;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItemType;

import javax.annotation.Nullable;

import static forpleuvoir.dhwu1a.core.common.ApiKey.DISPLAY;
import static forpleuvoir.dhwu1a.core.common.ApiKey.TARGET;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.messageitem
 * <p>#class_name At
 * <p>#create_time 2021/6/30 0:11
 */
public class At extends MessageItem {
    /**
     * 群员QQ号
     */
    @SerializedName(TARGET)
    public final Long target;
    /**
     * At时显示的文字，发送消息时无效，自动使用群名片
     */
    @SerializedName(DISPLAY)
    @Nullable
    public final String display;

    public At(Long target, @Nullable String display) {
        super(MessageItemType.At);
        this.target = target;
        this.display = display;
    }

    public At(Long target) {
        this(target, null);
    }

    @Override
    public String toPlainText() {
        return String.format("@%s", display == null ? target : display);
    }
}
