package com.meteor.electric.service.system.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meteor.electric.dao.system.DataDictionaryDao;
import com.meteor.electric.domain.system.DataDictionary;
import com.meteor.electric.service.system.DataDictionaryService;
import com.meteor.electric.util.GenericTypeConversion;

@Service(value=DataDictionaryService.SERVIE_NAME)
@Transactional(readOnly=true)
public class DataDictionaryServiceImpl implements DataDictionaryService{

	@Resource(name=DataDictionaryDao.DAO_NAME)
	private DataDictionaryDao dataDictionaryDao;
	
	/**
	 * 
	 * @see com.meteor.electric.service.system.DataDictionaryService#findAllDictionaryType(java.lang.String)
	 * @author		meteor-yun
	 * @version		1.0
	 * @createTime	2016年2月1日
	 * @description	<p>查询数据字典表所有的字典类型,业务层类</p>
	 */
	@Override
	public List<String> findAllDictionaryType() {
		
		return this.dataDictionaryDao.findAllDictionaryType("typeKey");
	}

	@Transactional()
	@Override
	public List<DataDictionary> editType(DataDictionary dataDictionary) {
		
		return this.dataDictionaryDao.customConditionFind(GenericTypeConversion.cunstomConditionFindAssemble(dataDictionary, true));
	}

	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public void saveTypeOrItem(String keywordname, String typeflag, String[] itemnames) {
		
		if(typeflag.equals("add")){
			List<DataDictionary> lists = this.dataDictionaryDao.findSepecifyConditionAllEntityBySingleColums("typeKey", keywordname);
			dataDictionaryDao.delete(lists);
			this.saveObjectList(keywordname,itemnames);
		}else{
			this.saveObjectList(keywordname, itemnames);
		}
	}

	private void saveObjectList(String keywordname, String[] itemnames) {
		for(int i=0; i<itemnames.length; i++){
			DataDictionary dataDictionary = new DataDictionary();
			dataDictionary.setTypeKey(keywordname);
			dataDictionary.setItemCode(i+1);
			dataDictionary.setItemName(itemnames[i]);
			this.dataDictionaryDao.save(dataDictionary);
		}
	}

}
