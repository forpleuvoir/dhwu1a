package forpleuvoir.dhwu1a.core.message.messageitem;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItemType;

import static forpleuvoir.dhwu1a.core.common.ApiKey.*;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.messageitem
 * <p>#class_name File
 * <p>#create_time 2021/6/30 0:29
 */
public class File extends MessageItem {
    /**
     * 文件识别id
     */
    @SerializedName(ID)
    public final String id;
    /**
     * 文件名
     */
    @SerializedName(NAME)
    public final String name;
    /**
     * 文件大小
     */
    @SerializedName(SIZE)
    public final Long size;

    public File(String id, String name, Long size) {
        super(MessageItemType.File);
        this.id = id;
        this.name = name;
        this.size = size;
    }

    @Override
    public String toPlainText() {
        return String.format("文件(%s)", name);
    }
}
