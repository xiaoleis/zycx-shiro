package com.zycx.zycxshiro.system.service.impl;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zycx.zycxshiro.common.util.AddressUtil;
import com.zycx.zycxshiro.common.util.HttpContextUtil;
import com.zycx.zycxshiro.common.util.IPUtil;
import com.zycx.zycxshiro.system.dao.LoginLogMapper;
import com.zycx.zycxshiro.system.domain.LoginLog;
import com.zycx.zycxshiro.system.service.LoginLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service("loginLogService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

    @Override
    @Transactional
    public void saveLoginLog(LoginLog loginLog) {
        loginLog.setLoginTime(new Date());
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        String ip = IPUtil.getIpAddr(request);
        loginLog.setIp(ip);
        loginLog.setLocation(AddressUtil.getCityInfo(ip));
        this.save(loginLog);
    }
}
