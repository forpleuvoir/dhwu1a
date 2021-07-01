package forpleuvoir.dhwu1a.core.message.base;

import forpleuvoir.dhwu1a.core.common.IJsonData;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.base
 * <p>#class_name IMessage
 * <p>#create_time 2021/6/29 22:25
 */
public interface IMessage extends IPlainText, IJsonData {
    void printMessageLog();
}
