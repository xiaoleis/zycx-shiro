package com.zycx.zycxshiro.system.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zycx.zycxshiro.common.domain.QueryRequest;
import com.zycx.zycxshiro.system.domain.City;

import java.util.List;

public interface CityService extends IService<City> {

    IPage<City> findCitys(QueryRequest request, City city);

    List<City> findCityList(QueryRequest request, City city);

    List<City> selectListByIds(List idsList);

    void createCity(City city) throws Exception;

    void updateCity(City city);

    void deleteCitys(String[] cityIds);

    /**
     * 批量插入
     * @param list List<City>
     */
    void batchInsert(List<City> list);
}
