package forpleuvoir.dhwu1a.core.event.request;

import com.google.gson.annotations.SerializedName;

import static forpleuvoir.dhwu1a.core.common.ApiKey.GROUP_NAME;

/**
 * 用户入群申请（Bot需要有管理员权限）
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.request
 * <p>#class_name MemberJoinRequestEvent
 * <p>#create_time 2021/7/4 2:02
 */
public class MemberJoinRequestEvent extends RequestEvent {
    /**
     * 申请人申请入群的群名称
     */
    @SerializedName(GROUP_NAME)
    public final String groupName;

    private MemberJoinRequestEvent(Long eventId, Long fromId, String nick, String message,
                                   Long groupId,
                                   String groupName
    ) {
        super(RequestEventType.MemberJoinRequestEvent, eventId, fromId, groupId, nick, message);
        this.groupName = groupName;
    }
}
