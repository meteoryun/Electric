package com.meteor.electric.service.system;

import java.util.List;

import com.meteor.electric.domain.system.DataDictionary;

public interface DataDictionaryService {

	public static final String SERVIE_NAME = "com.meteor.electric.service.system.impl.DataDictionaryServiceImpl";
	
	/**
	 * 
	 * @author		meteor-yun
	 * @version		1.0
	 * @createTime	2016年2月1日
	 * @methodName	findAllDictionaryType
	 * @description	<p>查询数据字典表所有的字典类型</p>
	 * @param	null
	 * @result		List<String>
	 */
	public List<String> findAllDictionaryType();

	/**
	 * 
	 * @author meteor-yun
	 * @version v1.0
	 * @createTime 2016年5月25日
	 * @description:修改数据字典类型
	 * @return
	 */
	public List<DataDictionary> editType(DataDictionary dataDictionary);

	/**
	 * 
	 * @author meteor-yun
	 * @version v1.0
	 * @createTime 2016年5月26日
	 * @description:保存数据类型或者对应类型的数据项
	 * @param keywordname
	 * @param typeflag
	 * @param itemnames
	 */
	public void saveTypeOrItem(String keywordname, String typeflag, String[] itemnames);
}
