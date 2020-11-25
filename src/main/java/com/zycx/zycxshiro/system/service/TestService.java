package com.zycx.zycxshiro.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zycx.zycxshiro.system.domain.Test;

import java.util.List;

public interface TestService extends IService<Test> {

    List<Test> findTests();

    /**
     * 批量插入
     * @param list List<Test>
     */
    void batchInsert(List<Test> list);
}
