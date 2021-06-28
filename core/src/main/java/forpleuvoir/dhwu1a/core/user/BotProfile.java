package forpleuvoir.dhwu1a.core.user;

import com.google.gson.JsonObject;

import static forpleuvoir.dhwu1a.core.websocket.base.ApiKey.*;

/**
 * bot资料
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.user
 * <p>#class_name BotProfile
 * <p>#create_time 2021/6/28 22:32
 */
public class BotProfile {

    public final String nickname;
    public final String email;
    public final int age;
    public final int level;
    public final String sign;
    public final String sex;

    public BotProfile(JsonObject jsonObject) {
        this.nickname = jsonObject.get(NICKNAME).getAsString();
        this.email = jsonObject.get(EMAIL).getAsString();
        this.age = jsonObject.get(AGE).getAsInt();
        this.level = jsonObject.get(LEVEL).getAsInt();
        this.sign = jsonObject.get(SIGN).getAsString();
        this.sex = jsonObject.get(SEX).getAsString();
    }

    public BotProfile(String nickname, String email, int age, int level, String sign, String sex) {
        this.nickname = nickname;
        this.email = email;
        this.age = age;
        this.level = level;
        this.sign = sign;
        this.sex = sex;
    }
}
