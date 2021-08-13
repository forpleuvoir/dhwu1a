package forpleuvoir.dhwu1a.core.user

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import forpleuvoir.dhwu1a.core.common.DATA
import forpleuvoir.dhwu1a.core.common.TARGET
import forpleuvoir.dhwu1a.core.common.data.GroupData
import forpleuvoir.dhwu1a.core.common.data.MemberData
import forpleuvoir.dhwu1a.core.event.group.MemberJoinEvent
import forpleuvoir.dhwu1a.core.event.group.MemberLeaveEventKick
import forpleuvoir.dhwu1a.core.message.base.MessageSenderObject
import forpleuvoir.dhwu1a.core.user.base.Permission
import forpleuvoir.dhwu1a.core.user.base.User
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog
import forpleuvoir.dhwu1a.core.util.JsonUtil
import forpleuvoir.dhwu1a.core.websocket.base.CommandSender
import forpleuvoir.dhwu1a.core.websocket.command.Command
import java.util.concurrent.ConcurrentLinkedDeque

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
    private var syncCompleted: (() -> Unit)? = null

    fun syncCompleted(syncCompleted: (() -> Unit)): Group {
        this.syncCompleted = syncCompleted
        return this
    }

    fun syncMember() {
        log.info("({})同步群员列表", data.name)
        val startTime = System.currentTimeMillis()
        bot.sendCommand(CommandSender(Command.MemberList, mapOf(TARGET to id))) { data: JsonObject ->
            if (data[DATA].isJsonArray) {
                members.clear()
                data[DATA].asJsonArray.forEach { element: JsonElement? ->
                    val memberData = JsonUtil.gson.fromJson(element, MemberData::class.java)
                    members.add(Member(this, memberData))
                }
                syncCompleted?.invoke()
                log.info(
                    "({}[{}])同步群员列表完成,耗时:{}ms",
                    this.data.name,
                    members.size,
                    System.currentTimeMillis() - startTime
                )
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

    fun addMember(memberJoinEvent: MemberJoinEvent) {
        if (getMember(memberJoinEvent.member.id) == null)
            this.members.add(Member(this, memberJoinEvent.member))
    }

    fun removeMember(memberLeaveEventKick: MemberLeaveEventKick) {
        this.members.removeIf { member -> member.id == memberLeaveEventKick.member.id }
    }

    fun getOwner(): Member {
        return members.stream().filter { member -> member.data.permission == Permission.OWNER }.findFirst().get()
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