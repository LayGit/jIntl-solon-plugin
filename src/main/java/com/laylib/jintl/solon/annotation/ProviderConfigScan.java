package com.laylib.jintl.solon.annotation;

import org.noear.solon.annotation.Alias;

import java.lang.annotation.*;

/**
 * Message Provider Scan Package
 *
 * @author Lay
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface ProviderConfigScan {
    @Alias("basePackages")
    String[] value() default {};

    @Alias("value")
    String[] basePackages() default {};
}
