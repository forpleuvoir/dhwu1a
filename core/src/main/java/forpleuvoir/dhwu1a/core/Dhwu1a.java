package forpleuvoir.dhwu1a.core;

import forpleuvoir.dhwu1a.config.Dhwu1aConfig;
import forpleuvoir.dhwu1a.config.LogConfig;
import forpleuvoir.dhwu1a.core.event.base.EventBus;
import forpleuvoir.dhwu1a.core.user.bot.Bot;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

/**
 * 程序入口
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core
 * <p>#class_name Dhwu1a
 * <p>#create_time 2021/6/28 20:28
 */
public class Dhwu1a {
    private static Dhwu1a INSTANCE;

    public final LogConfig logConfig;
    public Bot bot;
    private boolean running;
    private final Dhwu1aConfig config;
    private EventBus eventBus;

    private final List<Consumer<Dhwu1a>> onStart = new CopyOnWriteArrayList<>();
    private final List<Consumer<Dhwu1a>> onClose = new CopyOnWriteArrayList<>();
    private final List<Consumer<Dhwu1a>> onRestart = new CopyOnWriteArrayList<>();


    public static Dhwu1a getInstance() {
        return INSTANCE;
    }

    public static void initialize(Dhwu1aConfig config) {
        if (INSTANCE == null) {
            INSTANCE = new Dhwu1a(config);
        }
    }

    private Dhwu1a(Dhwu1aConfig config) {
        Thread.currentThread().setName("dhwu1a");
        this.logConfig = config.getLogConfig();
        this.config = config;
    }

    public void start() {
        this.eventBus = EventBus.getInstance();
        Bot.initialize(config);
        this.bot = Bot.getInstance();
        running = true;
        onStart();
    }

    private void onStart() {
        onStart.forEach(onStartConsumer -> onStartConsumer.accept(this));
    }

    public void reStart() {
        start();
        onRestart();
    }

    private void onRestart() {
        onRestart.forEach(onRestartConsumer -> onRestartConsumer.accept(this));
    }

    public void close() {
        bot.close();
        bot = null;
        running = false;
        onClose();
    }

    private void onClose() {
        onClose.forEach(onCloseConsumer -> onCloseConsumer.accept(this));
    }

    public boolean isRunning() {
        return running;
    }

    public Bot getBot() {
        return bot;
    }


    public void addOnStartListener(Consumer<Dhwu1a> consumer) {
        onStart.add(consumer);
    }

    public void addOnRestartListener(Consumer<Dhwu1a> consumer) {
        onRestart.add(consumer);
    }

    public void addOnCloseListener(Consumer<Dhwu1a> consumer) {
        onClose.add(consumer);
    }

    public EventBus getEventBus() {
        return eventBus;
    }
}
