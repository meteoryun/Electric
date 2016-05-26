package com.meteor.electric.dao.system;

import java.util.List;

import com.meteor.electric.dao.commons.CommonDaos;
import com.meteor.electric.domain.system.DataDictionary;

/**
 * 
 * @author	meteor-yun
 * @version	1.0
 * @createTime	2016年1月20日
 * @className	DataDictionaryDao
 * @description	<p>数据字典表DAO层</p>
 */
public interface DataDictionaryDao extends CommonDaos<DataDictionary>{

	public static final String DAO_NAME = "com.meteor.electric.dao.system.impl.DataDictionaryDaoImpl";
	
	/**
	 * 
	 * @author	meteor-yun
	 * @version	1.0
	 * @createTime	2016年1月20日
	 * @methodName	findAllDictionaryType
	 * @description	查找数据字典表中所有的字典类型
	 * @return	因为只查询表中某一列值,所以采用直接返回一个List<Sting>类型
	 */
	public List<String> findAllDictionaryType(String fieldName);
}
