package forpleuvoir.dhwu1a.core.event.friend;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.common.data.FriendData;

import static forpleuvoir.dhwu1a.core.common.ApiKey.INPUTTING;

/**
 * 好友输入状态改变
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.friend
 * <p>#class_name FriendInputStatusChangedEvent
 * <p>#create_time 2021/7/3 20:59
 */
public class FriendInputStatusChangedEvent extends FriendEvent {

    /**
     * 输入状态
     */
    @SerializedName(INPUTTING)
    public final Boolean inputting;

    public FriendInputStatusChangedEvent(FriendData friend, Boolean inputting) {
        super(FriendEventType.FriendInputStatusChangedEvent, friend);
        this.inputting = inputting;
    }

    @Override
    public String toPlainText() {
        return String.format("%s[%b]", type, inputting);
    }
}
