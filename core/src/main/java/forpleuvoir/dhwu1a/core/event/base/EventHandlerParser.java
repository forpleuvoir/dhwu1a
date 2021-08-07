package forpleuvoir.dhwu1a.core.event.base;

import forpleuvoir.dhwu1a.core.event.base.annotation.EventHandler;
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog;
import forpleuvoir.dhwu1a.core.util.ReflectionUtil;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.LinkedList;
import java.util.List;

/**
 * 时间处理程序解析器
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.base
 * <p>#class_name EventHandlerParser
 * <p>#create_time 2021/8/7 20:11
 */
public class EventHandlerParser {

    private transient static final Dhwu1aLog log = new Dhwu1aLog(EventHandlerParser.class);

    @SuppressWarnings("unchecked")
    public static void parse(Listener listener, EventBus eventBus) {
        List<Method> methods = getMethods(listener);
        for (Method method : methods) {
            Parameter parameter = method.getParameters()[0];
            Class<? extends Dhwu1aEvent> type = (Class<? extends Dhwu1aEvent>) parameter.getType();
            eventBus.subscribe(type, event -> {
                try {
                    method.invoke(listener, event);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            });
        }
    }

    private static List<Method> getMethods(Listener listener) {
        var methods = new LinkedList<Method>();
        for (Method method : listener.getClass().getMethods()) {
            method.setAccessible(true);
            if (method.getReturnType() == void.class) {
                if (method.getAnnotation(EventHandler.class) != null) {
                    if (method.getParameterCount() == 1) {
                        Parameter parameter = method.getParameters()[0];
                        if (ReflectionUtil.isExtended(parameter.getType(), Dhwu1aEvent.class)) {
                            methods.add(method);
                        }
                    }
                }
            }
        }
        return methods;
    }
}
