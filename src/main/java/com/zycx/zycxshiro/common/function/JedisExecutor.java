package com.zycx.zycxshiro.common.function;


import com.zycx.zycxshiro.common.exception.RedisConnectException;

@FunctionalInterface
public interface JedisExecutor<T, R> {
    R excute(T t) throws RedisConnectException;
}
