package com.laylib.jintl;

import com.laylib.jintl.solon.annotation.ProviderConfigScan;
import com.laylib.jintl.solon.context.IntlSourceHolder;
import org.noear.solon.Solon;
import org.noear.solon.annotation.*;

import java.util.Locale;

/**
 * Intl Application for test
 *
 * @author Lay
 * @date 2022/8/30
 */
@ProviderConfigScan(basePackages = "org.test")
@Controller
@Mapping("demo")
public class IntlApplication {
    public static void main(String[] args) {
        Solon.start(IntlApplication.class, args);
    }

    @Inject
    IntlSource intlSource;

    @Get
    @Mapping("welcome")
    public String welcome() {
        return intlSource.getMessage("demo.welcome", Locale.ENGLISH);
    }

    @Get
    @Mapping("hello")
    public String hello(@Param("name") String name) {
        // static usage
        return sayHello(name);
    }

    private static String sayHello(String name) {
        // demo.hello is set to Hello, {0}!
        return IntlSourceHolder.get().getMessage("demo.hello", new Object[]{name}, Locale.ENGLISH);
    }
}
