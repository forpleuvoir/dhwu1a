package forpleuvoir.dhwu1a.core.message.messagesender;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.common.data.GroupData;
import forpleuvoir.dhwu1a.core.message.base.MessageSender;
import forpleuvoir.dhwu1a.core.user.Group;
import forpleuvoir.dhwu1a.core.user.Member;
import forpleuvoir.dhwu1a.core.user.base.Permission;

import javax.annotation.Nullable;

import static forpleuvoir.dhwu1a.core.common.ApiKey.*;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.messagesender
 * <p>#class_name GroupMessageSender
 * <p>#create_time 2021/7/1 21:56
 */
public class GroupMessageSender extends MessageSender {

    @SerializedName(MEMBER_NAME)
    public String memberName;
    @SerializedName(SPECIAL_TITLE)
    public String specialTitle;
    @SerializedName(PERMISSION)
    public Permission permission;
    @SerializedName(JOIN_TIMESTAMP)
    public Integer joinTimestamp;
    @SerializedName(LAST_SPEAK_TIMESTAMP)
    public Integer lastSpeakTimestamp;
    @SerializedName(MUTE_TIME_REMAINING)
    public Integer muteTimeRemaining;
    @SerializedName(GROUP)
    private GroupData group;

    protected GroupMessageSender(Long id) {
        super(id);
    }

    @Nullable
    @Override
    public Group getUser() {
        return group.getUser();
    }

    @Nullable
    public Member getMember() {
        if (group.getUser() != null)
            return group.getUser().getMember(this.id);
        return null;
    }
}
