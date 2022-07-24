package com.lian.base.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 项目自定义配置
 * 类名需根据自己项目需求变更
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/24 10:52
 */
@Data
@Configuration
@ConfigurationProperties("base")
public class BaseProperties {
    
    private GlobalResponse globalResponse = new GlobalResponse();

    @Data
    public static class GlobalResponse {
        /**
         * 是否启用,默认关闭
         */
        private boolean enabled = false;
        /**
         * 忽略的返回值类型不处理,类全路径
         */
        private Class<?>[] ignoreReturnType;
        /**
         * 忽略的方法不处理,需要完全匹配方法名、参数和返回值
         * 如：public java.util.Set<org.gocom.coframe.sdk.model.CofFunction> org.gocom.coframe.sdk.tools.CofToolsController.scanFunctions(java.lang.String)
         */
        private String[] ignoreMethod;

        /**
         * 忽略的包路径不处理
         * 支持通配符号*如：com.a.*.b.*
         */
        private String[] ignorePath;
    }
}
