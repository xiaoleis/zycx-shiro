package com.zycx.zycxshiro.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zycx.zycxshiro.system.domain.LoginLog;

public interface LoginLogService extends IService<LoginLog> {

    void saveLoginLog (LoginLog loginLog);
}
