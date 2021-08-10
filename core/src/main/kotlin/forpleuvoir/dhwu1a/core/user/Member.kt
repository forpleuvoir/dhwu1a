package forpleuvoir.dhwu1a.core.user

import com.google.gson.JsonObject
import forpleuvoir.dhwu1a.core.common.KIND
import forpleuvoir.dhwu1a.core.common.MEMBER_ID
import forpleuvoir.dhwu1a.core.common.SUBJECT
import forpleuvoir.dhwu1a.core.common.TARGET
import forpleuvoir.dhwu1a.core.common.data.MemberData
import forpleuvoir.dhwu1a.core.common.data.Subject
import forpleuvoir.dhwu1a.core.message.base.MessageSenderObject
import forpleuvoir.dhwu1a.core.user.base.Profile
import forpleuvoir.dhwu1a.core.user.base.User
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog
import forpleuvoir.dhwu1a.core.util.JsonUtil
import forpleuvoir.dhwu1a.core.websocket.base.CommandSender
import forpleuvoir.dhwu1a.core.websocket.command.Command
import java.util.function.Consumer

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.user
 *
 * #class_name Member
 *
 * #create_time 2021/7/1 21:59
 */
class Member(
    /**
     * 群员所在群
     */
    val group: Group, memberData: MemberData
) : User(memberData.id) {

    /**
     * 群员信息
     */
    @JvmField
    val data: MemberData

    /**
     * 群员资料
     */
    var profile: Profile? = null
        private set

    fun getProfile(consumer: Consumer<Profile?>?) {
        bot.sendCommand(
            CommandSender(Command.MemberList, mapOf(TARGET to group.id, MEMBER_ID to id))
        ) { data: JsonObject? ->
            profile = JsonUtil.gson.fromJson(data, Profile::class.java)
            consumer?.accept(profile)
        }
    }

    fun nudge() {
        bot.sendCommand(
            CommandSender(
                Command.SendNudge,
                mapOf(TARGET to id, SUBJECT to group.id, KIND to Subject.SubjectType.Group)
            ),
            null
        )
    }

    override fun messageSenderObject(): MessageSenderObject {
        return MessageSenderObject(
            Command.SendTempMessage,
            log, String.format(
                "[%s(%d)] %s(Temp %d)",
                group.data.name,
                group.id,
                data.memberName,
                id
            ),
            this
        )
    }

    override fun toJsonString(): String {
        return data.toJsonString()
    }

    companion object {
        @Transient
        private val log: Dhwu1aLog = Dhwu1aLog(Member::class.java)
    }

    init {
        data = memberData
        getProfile(null)
    }
}