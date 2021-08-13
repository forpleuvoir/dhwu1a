package forpleuvoir.dhwu1a.core.user.bot

import com.google.gson.JsonObject
import forpleuvoir.dhwu1a.core.common.DATA
import forpleuvoir.dhwu1a.core.common.IJsonData
import forpleuvoir.dhwu1a.core.common.TARGET
import forpleuvoir.dhwu1a.core.common.data.FriendData
import forpleuvoir.dhwu1a.core.common.data.GroupData
import forpleuvoir.dhwu1a.core.common.day
import forpleuvoir.dhwu1a.core.config.Dhwu1aConfig
import forpleuvoir.dhwu1a.core.event.group.BotLeaveEventActive
import forpleuvoir.dhwu1a.core.event.group.BotLeaveEventKick
import forpleuvoir.dhwu1a.core.user.Friend
import forpleuvoir.dhwu1a.core.user.Group
import forpleuvoir.dhwu1a.core.user.base.Profile
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog
import forpleuvoir.dhwu1a.core.util.JsonUtil
import forpleuvoir.dhwu1a.core.websocket.EventWSC
import forpleuvoir.dhwu1a.core.websocket.MessageWSC
import forpleuvoir.dhwu1a.core.websocket.base.CommandSender
import forpleuvoir.dhwu1a.core.websocket.command.Command
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import java.util.concurrent.ConcurrentLinkedDeque


/**
 * bot本体
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.user.bot
 *
 * #class_name Bot
 *
 * #create_time 2021/6/28 20:38
 */
class Bot : IJsonData {
    companion object {
        val instance: Bot by lazy { Bot() }
    }

    private lateinit var config: Dhwu1aConfig

    @Transient
    private val log: Dhwu1aLog = Dhwu1aLog(Bot::class.java)

    /**
     * Bot的QQ号
     */
    var id: Long = 0
        private set

    /**
     * Bot资料
     */
    var profile: Profile? = null
        private set

    /**
     * 消息websocket客户端
     */
    private lateinit var messageWSC: MessageWSC

    /**
     * 事件websocket客户端
     */
    private lateinit var eventWSC: EventWSC

    /**
     * Bot的所有好友
     */
    private val friends: ConcurrentLinkedDeque<Friend> = ConcurrentLinkedDeque<Friend>()

    /**
     * Bot加入的所有群
     */
    private val groups: ConcurrentLinkedDeque<Group> = ConcurrentLinkedDeque<Group>()

    /**
     * 同步状态
     *
     * 0:群同步
     *
     * 1:好友同步
     *
     * 2:资料同步
     */
    private var syncCompleted: Array<Boolean> = arrayOf(false, false, false)


    fun initialize(config: Dhwu1aConfig) {
        this.config = config
        this.id = this.config.botId
        this.messageWSC = MessageWSC.getInstance(this, config.ip, config.port, config.verifyKey)
        this.messageWSC.setOnOpenCallback {
            sync()
            syncTask()
        }
        this.messageWSC.connect()
        this.eventWSC = EventWSC.getInstance(this, config.ip, config.port, config.verifyKey)
        this.eventWSC.connect()
    }

    private fun syncTask() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 3)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        var date = calendar.time
        if (date.before(Date()))
            date = Date(date.time + 1L.day())
        kotlin.concurrent.timer("bot sync", true, date, 1L.day()) {
            sync()
        }
    }

    fun sync() {
        syncStarted()
        syncGroup()
        syncFriend()
        syncProfile {
            profile?.let {
                syncCompleted[2] = true
                syncCompleted()
            }
        }
    }

    private fun syncStarted() {
        messageWSC.synchronizing.set(true)
        eventWSC.synchronizing.set(true)
        for (i in 1..syncCompleted.size) {
            syncCompleted[i - 1] = false
        }
    }

    private fun syncCompleted() {
        if (syncCompleted[0] && syncCompleted[1] && syncCompleted[2]) {
            runBlocking {
                messageWSC.synchronizing.set(false)
                eventWSC.synchronizing.set(false)
            }
        }
    }

    private fun syncFriend() {
        log.info("同步好友列表")
        val startTime = System.currentTimeMillis()
        sendCommand(CommandSender(Command.FriendList)) { data: JsonObject ->
            if (data.get(DATA).isJsonArray) {
                friends.clear()
                for (element in data.get(DATA).asJsonArray) {
                    friends.add(Friend(JsonUtil.gson.fromJson(element, FriendData::class.java)))
                }
                syncCompleted[1] = true
                syncCompleted()
                log.info("同步好友列表完成({})耗时:{}ms", friends.size, System.currentTimeMillis() - startTime)
            }
        }
    }

    private fun syncGroup() {
        log.info("同步群列表")
        val startTime = System.currentTimeMillis()
        sendCommand(CommandSender(Command.GroupList)) { data: JsonObject ->
            if (data.get(DATA).isJsonArray) {
                groups.clear()
                for (datum in data.get(DATA).asJsonArray) {
                    val groupData = JsonUtil.gson.fromJson(datum, GroupData::class.java)
                    groups.add(Group(groupData).syncCompleted {
                        syncCompleted[0] = true
                        syncCompleted()
                    })
                }
                log.info("同步群列表完成({})耗时:{}ms", groups.size, System.currentTimeMillis() - startTime)
            }
        }
    }

    /**
     * 撤回消息
     *
     * @param messageId 消息ID
     */
    fun recall(messageId: Int?) {
        sendCommand(
            CommandSender(Command.Recall, mapOf(TARGET to messageId)),
            null
        )
    }

    fun getProfileSync(): Profile {
        //todo 还没做完啊
        return this.profile!!
    }

    fun getProfileAsync(profile: (Profile?) -> Unit) {
        runBlocking { launch { syncProfile(profile) } }
    }

    private fun syncProfile(profile: (Profile?) -> Unit) {
        log.info("同步Bot资料")
        val startTime = System.currentTimeMillis()
        sendCommand(CommandSender(Command.BotProfile)) { data: JsonObject? ->
            this.profile = JsonUtil.gson.fromJson(data, Profile::class.java)
            profile.invoke(this.profile)
            log.info("同步Bot资料完成,耗时:{}ms", System.currentTimeMillis() - startTime)
        }
    }

    fun sendCommand(sender: CommandSender, consumer: ((JsonObject) -> Unit)?) {
        messageWSC.sendMessage(sender, consumer)
    }

    fun getFriend(id: Long): Friend? {
        return friends.stream().filter { friend: Friend -> friend.id == id }.findFirst()
            .orElse(null)
    }

    fun getGroup(id: Long): Group? {
        return groups.stream().filter { group: Group -> group.id == id }.findFirst().orElse(null)
    }

    fun leaveGroup(eventKick: BotLeaveEventKick?, eventActive: BotLeaveEventActive?): Boolean {
        eventKick?.let { return groups.removeIf { group -> group.id == eventKick.group.id } }
        eventActive?.let { return groups.removeIf { group -> group.id == eventActive.group.id } }
        return false
    }

    override fun toJsonString(): String {
        return profile!!.toJsonString()
    }

    fun close() {
        messageWSC.close()
        eventWSC.close()
    }


}

