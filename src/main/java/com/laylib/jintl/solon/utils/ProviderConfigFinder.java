package com.laylib.jintl.solon.utils;

import com.laylib.jintl.config.BaseProviderConfig;
import com.laylib.jintl.config.LocalProviderConfig;
import com.laylib.jintl.solon.exception.ProviderConfigNotFoundException;
import org.noear.solon.Solon;
import org.noear.solon.core.BeanWrap;

import java.util.HashMap;
import java.util.Map;

/**
 * Message Provider Finder
 *
 * @author Lay
 */
public class ProviderConfigFinder {
    private static final Map<String, BeanWrap> providerConfigClazzMap = new HashMap<>();

    public static void addType(String type, BeanWrap beanWrap) {
        providerConfigClazzMap.put(type, beanWrap);
    }

    public static BaseProviderConfig find() {
        String type = Solon.cfg().get("intl.provider.type");
        try {
            if (type == null) {
                return new LocalProviderConfig();
            }

            for (Map.Entry<String, BeanWrap> providerConfigWrap : providerConfigClazzMap.entrySet()) {
                if (providerConfigWrap.getKey().equals(type)) {
                    return (BaseProviderConfig) Solon.cfg().getBean("intl.provider", providerConfigWrap.getValue().clz());
                }
            }
        } catch (Exception e) {
            throw new ProviderConfigNotFoundException(type, e);
        }

        throw new ProviderConfigNotFoundException(type, null);
    }
}
