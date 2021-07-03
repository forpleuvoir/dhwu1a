package forpleuvoir.dhwu1a.core.message.messageitem;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItemType;

import static forpleuvoir.dhwu1a.core.common.ApiKey.NAME;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.messageitem
 * <p>#class_name Poke
 * <p>#create_time 2021/6/30 0:28
 */
public class Poke extends MessageItem {
    /**
     * 戳一戳的类型
     */
    @SerializedName(NAME)
    public final PokeType name;

    public Poke(String name) {
        super(MessageItemType.Poke);
        this.name = PokeType.valueOf(name);
    }

    @Override
    public String toPlainText() {
        return name.getName();
    }

    /**
     * 戳一戳的类型
     */
    public enum PokeType {
        Poke("戳一戳"),
        ShowLove("比心"),
        Like("点赞"),
        Heartbroken("心碎"),
        SixSixSix("666"),
        FangDaZhao("放大招");

        public final String name;

        PokeType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
