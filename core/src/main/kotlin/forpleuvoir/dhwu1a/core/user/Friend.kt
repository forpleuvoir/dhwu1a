package forpleuvoir.dhwu1a.core.user

import forpleuvoir.dhwu1a.core.common.KIND
import forpleuvoir.dhwu1a.core.common.SUBJECT
import forpleuvoir.dhwu1a.core.common.TARGET
import forpleuvoir.dhwu1a.core.common.data.FriendData
import forpleuvoir.dhwu1a.core.common.data.Subject
import forpleuvoir.dhwu1a.core.message.base.MessageSenderObject
import forpleuvoir.dhwu1a.core.user.base.User
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog
import forpleuvoir.dhwu1a.core.websocket.base.CommandSender
import forpleuvoir.dhwu1a.core.websocket.command.Command

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.user
 *
 * #class_name Friend
 *
 * #create_time 2021/6/29 22:44
 */
class Friend(
    /**
     * 好友信息
     */
    val data: FriendData
) : User(data.id) {
    fun nudge() {
        bot?.sendCommand(
            CommandSender(
                Command.SendNudge,
                mapOf(TARGET to id, SUBJECT to id, KIND to Subject.SubjectType.Friend)
            ),
            null
        )
    }

    override fun toJsonString(): String {
        return data.toJsonString()
    }

    override fun messageSenderObject(): MessageSenderObject {
        return MessageSenderObject(
            Command.SendFriendMessage, log, String.format("%s(%d)", data.nickname, id), this
        )
    }

    companion object {
        @Transient
        private val log = Dhwu1aLog(Friend::class.java)
    }
}