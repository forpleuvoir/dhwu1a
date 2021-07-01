package forpleuvoir.dhwu1a.core.message.messagesender;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.message.base.MessageSender;
import forpleuvoir.dhwu1a.core.message.messagesender.data.GroupData;
import forpleuvoir.dhwu1a.core.user.Group;
import forpleuvoir.dhwu1a.core.user.Member;
import forpleuvoir.dhwu1a.core.user.base.Permission;

import javax.annotation.Nullable;

import static forpleuvoir.dhwu1a.core.websocket.base.ApiKey.*;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.messagesender
 * <p>#class_name TempMessageSender
 * <p>#create_time 2021/7/1 21:59
 */
public class TempMessageSender extends MessageSender {

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

    protected TempMessageSender(Long id) {
        super(id);
    }

    @Nullable
    @Override
    public Member getUser() {
        if (bot == null) return null;
        Group group = bot.getGroup(this.group.id);
        if (group == null) return null;
        return group.getMember(this.id);
    }
}
