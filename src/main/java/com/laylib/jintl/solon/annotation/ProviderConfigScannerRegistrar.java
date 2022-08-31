package com.laylib.jintl.solon.annotation;

import com.laylib.jintl.config.BaseProviderConfig;
import org.noear.solon.core.AopContext;

/**
 * Message Config Scanner Registrar
 *
 * @author Lay
 */
public class ProviderConfigScannerRegistrar {
    public static void register(AopContext context) {
        context.beanBuilderAdd(ProviderConfigScan.class, (clz, bw, anno) -> {
            // scan inner providers
            context.beanScan(BaseProviderConfig.class.getPackageName());

            String[] annoPackages = anno.basePackages();
            if (annoPackages != null && annoPackages.length > 0) {
                for (String pkg : annoPackages) {
                    context.beanScan(pkg);
                }
            }
        });
    }
}
