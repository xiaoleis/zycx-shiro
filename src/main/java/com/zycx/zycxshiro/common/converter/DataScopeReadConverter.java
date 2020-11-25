package com.zycx.zycxshiro.common.converter;

import com.wuwenze.poi.convert.ReadConverter;
import com.wuwenze.poi.exception.ExcelKitReadConverterException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author : wl
 * @version : 1
 */
public class DataScopeReadConverter implements ReadConverter {
    @Override
    public Object convert(Object value) throws ExcelKitReadConverterException {
        return value==null?"": StringUtils.trim(value.toString()).equals("全部数据")?"0": StringUtils.trim(value.toString()).equals("部门数据")?"1": StringUtils.trim(value.toString()).equals("个人数据")?"2":"-1";
    }
}
