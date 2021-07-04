package forpleuvoir.dhwu1a.core.websocket.command;

/**
 * WS指令
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.websocket.base
 * <p>#class_name Command
 * <p>#create_time 2021/7/4 13:39
 */
public enum Command {
    groupList("groupList", "使用此方法获取bot的群列表"),
    memberList("memberList", "获取群成员列表"),
    friendList("friendList", "获取好友列表"),
    botProfile("botProfile", "获取Bot资料"),
    sendFriendMessage("sendFriendMessage", "发送好友消息"),
    sendGroupMessage("sendGroupMessage", "发送群消息"),
    sendTempMessage("sendTempMessage", "发送临时会话消息"),
    sendNudge("sendNudge", "发送头像戳一戳消息"),
    recall("recall", "撤回消息");

    private final String description;
    private final String command;

    Command(String command, String description) {
        this.description = description;
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }
}
