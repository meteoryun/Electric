package com.meteor.electric.dao.system.impl;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.meteor.electric.dao.system.DataDictionaryDao;

public class DataDictionaryDaoImplTest {

	public ApplicationContext applicationContext;
	
	@Before
	public void before(){
		applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	}
	
	@After
	public void after(){
		ClassPathXmlApplicationContext path = (ClassPathXmlApplicationContext)applicationContext;
		path.close();
	}
	
	@Test
	public void testFindAllDictionaryType() {
	
		DataDictionaryDao dataDictionaryDao = applicationContext.getBean(DataDictionaryDao.DAO_NAME, DataDictionaryDao.class);
		String hql = "select distinct dataDictionary.typeKey from DataDictionary dataDictionary";

		List<String> lists = dataDictionaryDao.findSingleColumsBySpecifyCondition("");
		System.out.println(lists);
	}

}
