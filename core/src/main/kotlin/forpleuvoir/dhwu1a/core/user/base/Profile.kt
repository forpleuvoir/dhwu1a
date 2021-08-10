package forpleuvoir.dhwu1a.core.user.base

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.*

/**
 * bot资料
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.user.bot.base
 *
 * #class_name Profile
 *
 * #create_time 2021/6/28 22:32
 */
class Profile : IJsonData {
    /**
     * 昵称
     */
    @SerializedName(NICKNAME)
    val nickname: String

    /**
     * 邮箱
     */
    @SerializedName(EMAIL)
    val email: String

    /**
     * 年龄
     */
    @SerializedName(AGE)
    val age: Int

    /**
     * 等级
     */
    @SerializedName(LEVEL)
    val level: Int

    /**
     * 标记
     */
    @SerializedName(SIGN)
    val sign: String

    /**
     * 性别
     */
    @SerializedName(SEX)
    val sex: String

    constructor(jsonObject: JsonObject) {
        nickname = jsonObject[NICKNAME].asString
        email = jsonObject[EMAIL].asString
        age = jsonObject[AGE].asInt
        level = jsonObject[LEVEL].asInt
        sign = jsonObject[SIGN].asString
        sex = jsonObject[SEX].asString
    }

    constructor(nickname: String, email: String, age: Int, level: Int, sign: String, sex: String) {
        this.nickname = nickname
        this.email = email
        this.age = age
        this.level = level
        this.sign = sign
        this.sex = sex
    }

}