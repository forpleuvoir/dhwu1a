package forpleuvoir.dhwu1a.core;

import forpleuvoir.dhwu1a.core.config.Dhwu1aConfig;
import forpleuvoir.dhwu1a.core.config.LogConfig;
import forpleuvoir.dhwu1a.core.event.base.EventBus;
import forpleuvoir.dhwu1a.core.user.bot.Bot;

import java.net.URISyntaxException;

/**
 * 程序入口
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core
 * <p>#class_name Dhwu1a
 * <p>#create_time 2021/6/28 20:28
 */
public abstract class Dhwu1a {
    private final Dhwu1aConfig config;
    public static LogConfig LOG_CONFIG = new LogConfig();
    public static Bot bot = null;
    private final EventBus eventBus;

    public Dhwu1a(Dhwu1aConfig config) {
        Thread.currentThread().setName("dhwu1a");
        this.config = config;
        LOG_CONFIG = config.logConfig;
        eventBus = new EventBus();
    }

    public void start() throws URISyntaxException {
        bot = new Bot(this.config);
    }

    public abstract void initialize();

    public void close() {
    }

    public final EventBus getEventBus() {
        return eventBus;
    }
}
