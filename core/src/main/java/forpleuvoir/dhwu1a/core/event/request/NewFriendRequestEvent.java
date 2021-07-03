package forpleuvoir.dhwu1a.core.event.request;


/**
 * 添加好友申请
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.request
 * <p>#class_name NewFriendRequestEvent
 * <p>#create_time 2021/7/4 1:44
 */
public class NewFriendRequestEvent extends RequestEvent {

    private NewFriendRequestEvent(Long eventId, Long fromId, String nick, String message,
                                  Long groupId
    ) {
        super(RequestEventType.NewFriendRequestEvent, eventId, groupId, fromId, nick, message);
    }

}
