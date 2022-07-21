package com.lian.base.common.i18n;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.LRUCache;
import cn.hutool.core.util.ObjectUtil;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Objects;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * <p>
 * 国际化翻译器
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/21 11:07
 */
public class I18nTranslator {

    /**
     * 所有国际化信息缓存
     */
    private static final LRUCache<String, ResourceBundleMessageSource> MESSAGE_SOURCES = CacheUtil.newLRUCache(1000);


    /**
     * 国际化翻译
     *
     * @param propPath 相对配置文件路径
     * @param code     配置中的编码
     * @param params   翻译参数
     * @return String
     */
    public static String translate(String propPath, String code, Object... params) {
        if (ObjectUtil.isEmpty(propPath)) {
            throw new NullPointerException("国际化资源路径");
        }
        if (ObjectUtil.isEmpty(code)) {
            throw new NullPointerException("国际化编码");
        }

        // 获取当前系统语言
        Locale locale = LocaleContextHolder.getLocale();
        ResourceBundleMessageSource messageSource = MESSAGE_SOURCES.get(propPath);
        if (Objects.isNull(messageSource)) {
            // 加载资源文件,文件必须在classpath目录下
            messageSource = new ResourceBundleMessageSource();
            messageSource.setBasename(propPath);
            messageSource.setUseCodeAsDefaultMessage(true);
            messageSource.setAlwaysUseMessageFormat(true);
            messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
            MESSAGE_SOURCES.put(propPath, messageSource);
        }
        return messageSource.getMessage(code, params, locale);
    }

}
