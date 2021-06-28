import forpleuvoir.dhwu1a.core.Dhwu1a;
import forpleuvoir.dhwu1a.core.config.Dhwu1aConfig;
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
    @Test
    public void a(){

    }

    @Test
    public void test()throws URISyntaxException {
        Dhwu1aTest dhwu1aTest = new Dhwu1aTest(
                new Dhwu1aConfig(2481735118L, "bot.forpleuvoir.com", 8080, "FORPLEUVOIR"));
        dhwu1aTest.start();
    }

    @Override
    public void initialize() {

    }
}
