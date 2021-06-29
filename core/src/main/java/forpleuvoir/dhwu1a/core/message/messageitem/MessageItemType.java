package forpleuvoir.dhwu1a.core.message.messageitem;


/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.messageitem
 * <p>#class_name MessageItemType
 * <p>#create_time 2021/6/29 23:03
 */
public enum MessageItemType {
    Source(Source.class),
    Quote(Quote.class),
    At(At.class),
    AtAll(AtAll.class),
    Face(Face.class),
    Plain(Plain.class),
    Image(Image.class),
    FlashImage(FlashImage.class),
    Voice(Voice.class),
    Xml(Xml.class),
    Json(Json.class),
    App(App.class),
    Poke(Poke.class),
    Forward(Forward.class),
    File(File.class),
    MusicShare(MusicShare.class);

    private final Class<? extends MessageItem> clazz;

    MessageItemType(Class<? extends MessageItem> clazz) {
        this.clazz=clazz;
    }

    public Class<? extends MessageItem> getClazz() {
        return clazz;
    }
}
