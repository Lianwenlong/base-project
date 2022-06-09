package com.lian.base.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author LianWenLong
 * @version 1.0
 * @date 2022年04月18日 18:05
 */

@SpringBootApplication(scanBasePackages = {"com.lian.base"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
