package com.huawei.dynamicdb.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huawei.dynamicdb.dynamicdbconfig.TargetDataSource;
import com.huawei.dynamicdb.mapper.SzwbmMapper;
import com.huawei.dynamicdb.mapper.ZwbmMapper;

@Service
public class TestDao {
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private ZwbmMapper zwbmMapper;
	@Autowired
	private SzwbmMapper szwbmMapper;
	
	@TargetDataSource(name="ds1")
	public Object getData() {
		logger.info("hhhhh");
		return zwbmMapper.testData();
	}
	
	@TargetDataSource(name="ds2")
	public Object getData2(){
		logger.info("hhhhh");
		return szwbmMapper.queryData();
	}
}
