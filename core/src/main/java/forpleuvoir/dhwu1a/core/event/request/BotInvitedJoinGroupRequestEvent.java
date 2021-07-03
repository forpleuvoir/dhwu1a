package forpleuvoir.dhwu1a.core.event.request;

import com.google.gson.annotations.SerializedName;

import static forpleuvoir.dhwu1a.core.common.ApiKey.GROUP_NAME;

/**
 * Bot被邀请入群申请
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.request
 * <p>#class_name BotInvitedJoinGroupRequestEvent
 * <p>#create_time 2021/7/4 2:02
 */
public class BotInvitedJoinGroupRequestEvent extends RequestEvent {
    /**
     * 被邀请进入群的群名称
     */
    @SerializedName(GROUP_NAME)
    public final String groupName;

    private BotInvitedJoinGroupRequestEvent(Long eventId, Long fromId, String nick,
                                            String message,
                                            Long groupId,
                                            String groupName
    ) {
        super(RequestEventType.BotInvitedJoinGroupRequestEvent, eventId, fromId, groupId, nick, message);
        this.groupName = groupName;
    }
}
