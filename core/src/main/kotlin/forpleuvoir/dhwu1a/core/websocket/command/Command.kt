package forpleuvoir.dhwu1a.core.websocket.command

/**
 * WS指令
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.websocket.base
 *
 * #class_name Command
 *
 * #create_time 2021/7/4 13:39
 */
enum class Command(val command: String, val description: String) {
    GroupList("groupList", "获取bot的群列表"),
    MemberList("memberList", "获取群成员列表"),
    FriendList("friendList", "获取好友列表"),
    BotProfile("botProfile", "获取Bot资料"),
    SendFriendMessage("sendFriendMessage", "发送好友消息"),
    SendGroupMessage("sendGroupMessage", "发送群消息"),
    SendTempMessage("sendTempMessage", "发送临时会话消息"),
    SendNudge("sendNudge", "发送头像戳一戳消息"),
    Recall("recall", "撤回消息");
}