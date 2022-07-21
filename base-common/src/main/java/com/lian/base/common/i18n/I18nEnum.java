package com.lian.base.common.i18n;

/**
 * <p>
 * 国际化枚举接口
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/21 00:48
 */
public interface I18nEnum {

    String name();

    /**
     * 获取枚举编码
     *
     * @return 枚举编码
     */
    default String getCode() {
        return name();
    }
}
