package com.huawei.dynamicdb.dynamicdbconfig;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 获取DynamicDataSourceHolder类中已有的数据库连接名，
 * 该类继承于AbstractRoutingDataSource类，当执行数据库操作时调用该类完成数据库切换动作
 *
 * @author Marke
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	
	@Override
	protected Object determineCurrentLookupKey() {
		return DynamicDataSourceHolder.getDataSource();
	}	

}
