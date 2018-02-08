package com.huawei.dynamicdb.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ZwbmMapper {
	List<Map<String,String>> testData();
}
