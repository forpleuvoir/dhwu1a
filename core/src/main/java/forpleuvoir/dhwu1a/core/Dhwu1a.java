package forpleuvoir.dhwu1a.core;

import forpleuvoir.dhwu1a.core.config.Dhwu1aConfig;
import forpleuvoir.dhwu1a.core.config.LogConfig;
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
public class Dhwu1a {
    public static LogConfig LOG_CONFIG = new LogConfig();
    public static Bot bot;
    public static boolean running;

    public Dhwu1a(Dhwu1aConfig config) {
        Thread.currentThread().setName("dhwu1a");
        LOG_CONFIG = config.logConfig;
        bot = new Bot(config);
    }

    public void start() throws URISyntaxException {
        bot.initialize();
        running = true;
    }

    public void close() {
        bot.close();
        running = false;
    }

    public static Bot getBot() {
        return bot;
    }

}
