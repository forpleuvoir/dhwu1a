package forpleuvoir.dhwu1a.core.user.bot

import com.google.gson.JsonObject
import forpleuvoir.dhwu1a.core.common.DATA
import forpleuvoir.dhwu1a.core.common.IJsonData
import forpleuvoir.dhwu1a.core.common.TARGET
import forpleuvoir.dhwu1a.core.common.data.FriendData
import forpleuvoir.dhwu1a.core.common.data.GroupData
import forpleuvoir.dhwu1a.core.config.Dhwu1aConfig
import forpleuvoir.dhwu1a.core.user.Friend
import forpleuvoir.dhwu1a.core.user.Group
import forpleuvoir.dhwu1a.core.user.base.Profile
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog
import forpleuvoir.dhwu1a.core.util.JsonUtil
import forpleuvoir.dhwu1a.core.websocket.EventWSC
import forpleuvoir.dhwu1a.core.websocket.MessageWSC
import forpleuvoir.dhwu1a.core.websocket.base.CommandSender
import forpleuvoir.dhwu1a.core.websocket.command.Command
import java.util.concurrent.ConcurrentLinkedDeque
import java.util.function.Consumer

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
class Bot private constructor(config: Dhwu1aConfig) : IJsonData {
    /**
     * Bot的QQ号
     */
    @JvmField
    val id: Long

    /**
     * Bot资料
     */
    var profile: Profile? = null
        private set

    /**
     * 消息websocket客户端
     */
    private val messageWSC: MessageWSC?

    /**
     * 事件websocket客户端
     */
    private val eventWSC: EventWSC?

    /**
     * Bot的所有好友
     */
    private val friends: ConcurrentLinkedDeque<Friend> = ConcurrentLinkedDeque<Friend>()

    /**
     * Bot加入的所有群
     */
    private val groups: ConcurrentLinkedDeque<Group> = ConcurrentLinkedDeque<Group>()
    private fun init() {
        eventWSC?.connect()
        try {
            messageWSC?.connectBlocking()
            sync()
        } catch (e: InterruptedException) {
            log.error(e.message, e)
        }
    }

    fun sync() {
        syncGroup()
        syncFriend()
        syncProfile()
    }

    fun syncFriend() {
        log.info("同步好友列表")
        sendCommand(CommandSender(Command.FriendList)) { data: JsonObject ->
            if (data.get(DATA).isJsonArray) {
                friends.clear()
                for (element in data.get(DATA).asJsonArray) {
                    friends.add(Friend(JsonUtil.gson.fromJson(element, FriendData::class.java)))
                }
            }
        }
    }

    fun syncGroup() {
        log.info("同步群列表")
        sendCommand(CommandSender(Command.GroupList)) { data: JsonObject ->
            if (data.get(DATA).isJsonArray) {
                groups.clear()
                for (datum in data.get(DATA).asJsonArray) {
                    val groupData = JsonUtil.gson.fromJson(datum, GroupData::class.java)
                    groups.add(Group(groupData))
                }
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

    fun syncProfile() {
        log.info("同步Bot资料")
        sendCommand(
            CommandSender(Command.BotProfile)
        ) { data: JsonObject? ->
            profile = JsonUtil.gson.fromJson(data, Profile::class.java)
        }
    }

    fun sendCommand(sender: CommandSender, consumer: Consumer<JsonObject>?) {
        messageWSC?.sendMessage(sender, consumer)
    }

    fun getFriend(id: Long?): Friend? {
        return friends.stream().filter { friend: Friend -> friend.id == id }.findFirst()
            .orElse(null)
    }

    fun getGroup(id: Long?): Group? {
        return groups.stream().filter { group: Group -> group.id == id }.findFirst().orElse(null)
    }

    override fun toJsonString(): String {
        return profile!!.toJsonString()
    }

    fun close() {
        messageWSC?.close()
        eventWSC?.close()
    }

    companion object {
        @Transient
        private val log: Dhwu1aLog = Dhwu1aLog(Bot::class.java)
        var instance: Bot? = null
            private set

        fun initialize(config: Dhwu1aConfig) {
            if (instance == null) {
                instance = Bot(config)
                instance!!.init()
            }
        }
    }

    init {
        id = config.botId
        messageWSC = MessageWSC.getInstance(this, config.ip, config.port, config.verifyKey)
        eventWSC = EventWSC.getInstance(this, config.ip, config.port, config.verifyKey)
    }
}