package forpleuvoir.dhwu1a.core.common

import forpleuvoir.dhwu1a.core.util.toJsonStr

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.common
 *
 * #class_name IJsonData
 *
 * #create_time 2021/6/30 0:38
 */
interface IJsonData {

      fun toJsonString(): String {
        return this.toJsonStr()
    }
}