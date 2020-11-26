package com.zycx.zycxshiro;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@EnableAsync
public class ZycxShiroApplication {

    public static void main(String[] args) {
        //SpringApplication.run(ZycxShiroApplication.class, args);

        new SpringApplicationBuilder(ZycxShiroApplication.class)
                .run(args);
    }

}
