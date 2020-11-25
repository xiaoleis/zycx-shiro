package com.zycx.zycxshiro.common.options;

import com.wuwenze.poi.config.Options;

/**
 * @author : wl
 * @version : 1
 */
public class DataScopeOptions implements Options {
    @Override
    public String[] get() {
        return new String[]{"个人数据","部门数据","全部数据"};
    }
}
