import com.google.gson.JsonObject;
import forpleuvoir.dhwu1a.core.Dhwu1a;
import forpleuvoir.dhwu1a.core.config.Dhwu1aConfig;
import forpleuvoir.dhwu1a.core.config.LogConfig;
import forpleuvoir.dhwu1a.core.message.messageitem.MessageItem;
import forpleuvoir.dhwu1a.core.util.JsonUtil;
import forpleuvoir.dhwu1a.core.websocket.GetData;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package PACKAGE_NAME
 * <p>#class_name Dhwu1aTest
 * <p>#create_time 2021/6/28 21:24
 */
public class Dhwu1aTest extends Dhwu1a {

    public Dhwu1aTest(Dhwu1aConfig config) {
        super(config);
    }

    public static void main(String[] args) {
        b();
    }

    public static void b() {
        String str = """
                {
                    "type": "Quote",
                    "id": 123456,
                    "groupId": 123456789,
                    "senderId": 987654321,
                    "targetId": 9876543210,
                    "origin": [
                        { "type": "Plain", text: "text" }
                    ]
                }
                """;
        JsonObject asJsonObject = JsonUtil.strToJsonElement(str).getAsJsonObject();
        MessageItem parse = MessageItem.parse(asJsonObject);
        System.out.println(parse.toJsonString());
    }

    @Test
    public static void a() {
        GetData getData = JsonUtil.gson.fromJson("""
                                                         {
                                                             "syncId": -1,
                                                             "data": {
                                                                 "botId":2481735118,
                                                                 "ip": "bot.forpleuvoir.com",
                                                                 "port": 8080,
                                                                 "verifyKey": "FORPLEUVOIR",
                                                                 "sign": "mirai",
                                                                 "sex": "UNKNOWN",
                                                                 "logConfig": {
                                                                     "info": true,
                                                                     "debug": true,
                                                                     "warn": true,
                                                                     "error": true
                                                                 }
                                                             }
                                                         }""", GetData.class);
        Dhwu1aConfig config = JsonUtil.gson.fromJson(getData.getData(), Dhwu1aConfig.class);
        System.out.println(getData.getData().toString());
        System.out.println(config);
    }

    @Test
    public void test() throws URISyntaxException {
        Dhwu1aTest dhwu1aTest = new Dhwu1aTest(
                new Dhwu1aConfig(2481735118L, "bot.forpleuvoir.com", 8080, "FORPLEUVOIR", new LogConfig()));
        dhwu1aTest.start();
    }

    @Override
    public void initialize() {

    }
}
