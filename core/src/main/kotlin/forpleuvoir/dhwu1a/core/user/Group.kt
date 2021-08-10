package forpleuvoir.dhwu1a.core.user

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import forpleuvoir.dhwu1a.core.common.DATA
import forpleuvoir.dhwu1a.core.common.TARGET
import forpleuvoir.dhwu1a.core.common.data.GroupData
import forpleuvoir.dhwu1a.core.common.data.MemberData
import forpleuvoir.dhwu1a.core.message.base.MessageSenderObject
import forpleuvoir.dhwu1a.core.user.base.User
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog
import forpleuvoir.dhwu1a.core.util.JsonUtil
import forpleuvoir.dhwu1a.core.websocket.base.CommandSender
import forpleuvoir.dhwu1a.core.websocket.command.Command
import java.util.concurrent.ConcurrentLinkedDeque
import java.util.function.Consumer

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.user
 *
 * #class_name Group
 *
 * #create_time 2021/7/1 21:58
 */
class Group(groupData: GroupData) : User(groupData.id) {
    /**
     * 群信息
     */
    @JvmField
    val data: GroupData
    private val members: ConcurrentLinkedDeque<Member> = ConcurrentLinkedDeque<Member>()
    fun syncMember() {
        log.info("({})同步群员列表", data.name)
        bot?.sendCommand(
            CommandSender(
                Command.MemberList,
                mapOf(TARGET to id)
            )
        ) { data: JsonObject ->
            if (data.get(DATA).isJsonArray) {
                members.clear()
                data.get(DATA).asJsonArray.forEach(Consumer { element: JsonElement? ->
                    val memberData = JsonUtil.gson.fromJson<MemberData>(element, MemberData::class.java)
                    members.add(Member(this, memberData))
                })
            }
        }
    }

    override fun messageSenderObject(): MessageSenderObject {
        return MessageSenderObject(
            Command.SendGroupMessage, log, String.format("[%s(%d)]", data.name, id),
            this
        )
    }

    override fun toJsonString(): String {
        return data.toJsonString()
    }

    fun getMember(id: Long): Member? {
        return members.stream().filter { member: Member -> member.id == id }.findFirst().orElse(null)
    }

    companion object {
        @Transient
        private val log: Dhwu1aLog = Dhwu1aLog(Group::class.java)
    }

    init {
        data = groupData
        syncMember()
    }
}