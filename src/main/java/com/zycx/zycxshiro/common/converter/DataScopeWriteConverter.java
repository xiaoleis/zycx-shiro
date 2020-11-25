package com.zycx.zycxshiro.common.converter;

import com.wuwenze.poi.convert.WriteConverter;
import com.wuwenze.poi.exception.ExcelKitWriteConverterException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author : wl
 * @version : 1
 */
public class DataScopeWriteConverter implements WriteConverter {
    @Override
    public String convert(Object value) throws ExcelKitWriteConverterException {
        return value==null?"": StringUtils.trim(value.toString()).equals("0")?"全部数据": StringUtils.trim(value.toString()).equals("1")?"部门数据": StringUtils.trim(value.toString()).equals("2")?"个人数据":"未知数据范围";
    }
}
