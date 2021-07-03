package forpleuvoir.dhwu1a.core.message.messageitem;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItemType;

import static forpleuvoir.dhwu1a.core.common.ApiKey.VALUE;

/**
 * 骰子消息
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.messageitem
 * <p>#class_name Dice
 * <p>#create_time 2021/7/1 1:08
 */
public class Dice extends MessageItem {
    /**
     * 点数
     */
    @SerializedName(VALUE)
    public Integer value;

    public Dice(Integer value) {
        super(MessageItemType.Dice);
        this.value = value;
    }

    @Override
    public String toPlainText() {
        return String.format("骰子:%d点", value);
    }
}
