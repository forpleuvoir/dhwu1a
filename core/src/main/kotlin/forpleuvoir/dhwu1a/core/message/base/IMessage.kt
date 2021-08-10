package forpleuvoir.dhwu1a.core.message.base

import forpleuvoir.dhwu1a.core.common.IJsonData

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.message.base
 *
 * #class_name IMessage
 *
 * #create_time 2021/6/29 22:25
 */
interface IMessage : IPlainText, IJsonData {
    fun printMessageLog()
}