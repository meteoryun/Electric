package com.meteor.electric.dao.system.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.meteor.electric.dao.commons.impl.CommonDaoImpls;
import com.meteor.electric.dao.system.DataDictionaryDao;
import com.meteor.electric.domain.system.DataDictionary;
import com.meteor.electric.util.ReflectUtils;

/**
 * 
 * @author		meteor-yun
 * @version		1.0
 * @createTime	2016年2月1日
 * @className	DataDictionaryDaoImpl
 * @description	<p>数据字典表DAO层实现类</p>
 */
@Repository(value=DataDictionaryDao.DAO_NAME)
public class DataDictionaryDaoImpl extends CommonDaoImpls<DataDictionary> implements DataDictionaryDao{
	
	/**
	 * 
	 * @see com.meteor.electric.dao.system.DataDictionaryDao#findAllDictionaryType()
	 * @author		meteor-yun
	 * @version		1.0
	 * @createTime	2016年2月1日
	 * @description <p>查询数据字典表中所有的字典类型</p>
	 */
	@Override
	public List<String> findAllDictionaryType(String fieldName) {
		List<String> lists = this.findSingleColumsBySpecifyCondition(fieldName);
		
		return lists;
	}

}
