package com.huawei.dynamicdb.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SzwbmMapper {
	List<Map<String, String>> queryData();
}
