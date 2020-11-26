package com.zycx.zycxshiro.common.config;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.zycx.zycxshiro.common.interceptor.PaginationInterceptorImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(value = {"cc.mrbird.febs.*.dao"})
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptorImpl();
    }

}
