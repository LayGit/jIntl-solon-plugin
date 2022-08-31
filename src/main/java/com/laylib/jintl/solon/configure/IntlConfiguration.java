package com.laylib.jintl.solon.configure;

import com.laylib.jintl.IntlSource;
import com.laylib.jintl.config.BaseProviderConfig;
import com.laylib.jintl.config.IntlConfig;
import com.laylib.jintl.solon.context.IntlSourceHolder;
import com.laylib.jintl.solon.utils.ProviderConfigFinder;
import org.noear.solon.Solon;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;

/**
 * Config Intel Source
 *
 * @author Lay
 * @date 2022/8/30
 */
@Configuration
public class IntlConfiguration {

    @Bean
    public static IntlSource intlSource() {
        IntlProperties intlProperties = Solon.cfg().getBean("intl", IntlProperties.class);

        // resolve intl config
        IntlConfig config = new IntlConfig();
        config.setUseCodeAsDefaultMessage(intlProperties.isUseCodeAsDefaultMessage());
        config.setFallbackLanguageOnly(intlProperties.isFallbackLanguageOnly());

        // resolve provider config
        BaseProviderConfig providerConfig = ProviderConfigFinder.find();
        IntlSource intlSource =  new IntlSource(config, providerConfig);

        // static cached to holder
        IntlSourceHolder.init(intlSource);
        return intlSource;
    }
}
