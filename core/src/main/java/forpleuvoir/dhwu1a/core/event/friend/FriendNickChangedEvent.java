package forpleuvoir.dhwu1a.core.event.friend;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.common.data.FriendData;

import static forpleuvoir.dhwu1a.core.common.ApiKey.FROM;
import static forpleuvoir.dhwu1a.core.common.ApiKey.TO;

/**
 * 好友昵称改变
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.friend
 * <p>#class_name FriendNickChangedEvent
 * <p>#create_time 2021/7/3 21:06
 */
public class FriendNickChangedEvent extends FriendEvent {

    /**
     * 原昵称
     */
    @SerializedName(FROM)
    public final String from;
    /**
     * 修改后的昵称
     */
    @SerializedName(TO)
    public final String to;

    public FriendNickChangedEvent(FriendData friend, String from, String to) {
        super(FriendEventType.FriendNickChangedEvent, friend);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toPlainText() {
        return String.format("%s[from:%s,to:%s]", type, from, to);
    }
}
