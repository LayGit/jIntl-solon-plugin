package com.laylib.jintl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.noear.solon.annotation.Inject;
import org.noear.solon.test.HttpTestBase;
import org.noear.solon.test.SolonJUnit4ClassRunner;
import org.noear.solon.test.SolonTest;

import java.io.IOException;
import java.util.Locale;

/**
 * Solon Test
 *
 * @author Lay
 * @date 2022/8/30
 */
@SolonTest(IntlApplication.class)
@RunWith(SolonJUnit4ClassRunner.class)
public class IntlTests extends HttpTestBase {

    @Inject
    IntlSource intlSource;

    @Test
    public void test() {
        String code = "http.serverError";
        String msg = intlSource.getMessage(code, Locale.ENGLISH);
        Assert.assertEquals("Internal Server Error", msg);
    }

    @Test
    public void testHttp() throws IOException {
        assert path("/demo/welcome").get().equals("Welcome to Internet");
        assert path("/demo/hello?name=Lay").get().equals("Hello, Lay!");
    }
}
