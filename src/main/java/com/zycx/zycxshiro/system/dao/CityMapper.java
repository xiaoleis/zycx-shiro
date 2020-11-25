package com.zycx.zycxshiro.system.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zycx.zycxshiro.common.annotation.DataFilter;
import com.zycx.zycxshiro.system.domain.City;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@DataFilter(filterMethods={"selectPage"})
public interface CityMapper extends BaseMapper<City> {
}
