package com.huawei.dynamicdb.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huawei.dynamicdb.dao.TestDao;

@Controller
@RequestMapping("/test/")
public class TestAction {
	Logger logger = Logger.getLogger(TestAction.class);
	@Autowired
	private TestDao template;
	
	@RequestMapping("/test")
	public void getResult() {

		logger.info(template.getData());
		
		logger.info(template.getData2());
	}
}
