package com.laylib.jintl.solon;

import com.laylib.jintl.annotation.ProviderType;
import com.laylib.jintl.solon.annotation.ProviderConfigScannerRegistrar;
import com.laylib.jintl.solon.utils.ProviderConfigFinder;
import org.noear.solon.core.AopContext;
import org.noear.solon.core.Plugin;

/**
 * jIntl Plugin
 *
 * @author Lay
 * @date 2022/8/30
 */
public class IntlPlugin implements Plugin {

    @SuppressWarnings("unchecked")
    @Override
    public void start(AopContext context) {
        context.beanBuilderAdd(ProviderType.class, (clz, bw, anno) -> ProviderConfigFinder.addType(anno.value(), bw));

        // register scan annotation
        ProviderConfigScannerRegistrar.register(context);
    }
}
