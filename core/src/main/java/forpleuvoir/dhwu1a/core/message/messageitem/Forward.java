package forpleuvoir.dhwu1a.core.message.messageitem;

import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import forpleuvoir.dhwu1a.core.common.IJsonData;
import forpleuvoir.dhwu1a.core.message.base.IPlainText;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItemType;
import forpleuvoir.dhwu1a.core.util.JsonUtil;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static forpleuvoir.dhwu1a.core.websocket.base.ApiKey.*;

/**
 * {@link #nodeList} 消息列表
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.messageitem
 * <p>#class_name Forward
 * <p>#create_time 2021/6/30 0:28
 */
public class Forward extends MessageItem {
    /**
     * 消息列表
     */
    @SerializedName(NODE_LIST)
    public final List<NodeItem> nodeList;

    public Forward(List<NodeItem> nodeList) {
        super(MessageItemType.Forward);
        this.nodeList = nodeList;
    }

    public Forward(@Nonnull NodeItem... nodeItems) {
        this(Arrays.asList(nodeItems));
    }

    @Override
    public String toPlainText() {
        return null;
    }

    /**
     * {@link #senderId} 发送者QQ号 <p>
     * {@link #time } 发送时间 时间戳, 单位 秒 <p>
     * {@link #senderName } 发送者名称 <p>
     * {@link #messageChain } 消息链 <p>
     * {@link #messageId } 消息ID
     */
    @JsonAdapter(NodeItemAdapter.class)
    public static class NodeItem implements IPlainText, IJsonData {
        /**
         * 发送者QQ号
         */
        @SerializedName(SENDER_ID)
        public final Long senderId;
        /**
         * 发送时间 时间戳, 单位 秒
         */
        @SerializedName(TIME)
        public final Long time;
        /**
         * 发送者名称
         */
        @SerializedName(SENDER_NAME)
        public final String senderName;
        /**
         * 消息链
         */
        @SerializedName(MESSAGE_CHAIN)
        public final List<MessageItem> messageChain;
        /**
         * 可以只使用消息messageId，从缓存中读取一条消息作为节点
         */
        @SerializedName(MESSAGE_ID)
        public final Integer messageId;

        private NodeItem(Long senderId, Long time, String senderName,
                         List<MessageItem> messageChain, Integer messageId
        ) {
            this.senderId = senderId;
            this.time = time;
            this.senderName = senderName;
            this.messageChain = messageChain;
            this.messageId = messageId;
        }

        public NodeItem(Long senderId, Long time, String senderName,
                        List<MessageItem> messageChain
        ) {
            this.senderId = senderId;
            this.time = time;
            this.senderName = senderName;
            this.messageChain = messageChain;
            this.messageId = null;
        }

        @Override
        public String toPlainText() {
            return null;
        }
    }

    public static class NodeItemAdapter extends TypeAdapter<NodeItem> {

        @Override
        public void write(JsonWriter out, NodeItem value
        ) throws IOException {
            out.beginObject();
            out.name(SENDER_ID).value(value.senderId);
            out.name(TIME).value(value.time);
            out.name(SENDER_NAME).value(value.senderName);
            out.name(MESSAGE_ID).value(value.messageId);
            out.name(MESSAGE_CHAIN).jsonValue(JsonUtil.toJsonStr(value.messageChain));
            out.endObject();
        }

        @Override
        public NodeItem read(JsonReader in) throws IOException {
            Long senderId = null;
            Long time = null;
            String senderName = null;
            List<MessageItem> messageChain = new LinkedList<>();
            Integer messageId = null;
            in.beginObject();
            while (in.hasNext()) {
                switch (in.nextName()) {
                    case SENDER_ID -> senderId = in.nextLong();
                    case TIME -> time = in.nextLong();
                    case SENDER_NAME -> senderName = in.nextString();
                    case MESSAGE_ID -> {
                        try {
                            messageId = in.nextInt();
                        } catch (IllegalStateException ignored) {
                            in.nextNull();
                        }
                    }
                    case MESSAGE_CHAIN -> {
                        in.beginArray();
                        in.beginObject();
                        JsonObject object = new JsonObject();
                        while (in.hasNext()) {
                            object.addProperty(in.nextName(), in.nextString());
                        }
                        messageChain.add(MessageItem.parse(object));
                        in.endObject();
                        in.endArray();
                    }
                }
            }
            in.endObject();
            return new NodeItem(senderId, time, senderName, messageChain, messageId);
        }
    }

}
