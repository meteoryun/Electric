package com.meteor.electric.action.system;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.meteor.electric.action.commons.CommonAction;
import com.meteor.electric.domain.system.DataDictionary;
import com.meteor.electric.service.system.DataDictionaryService;
import com.meteor.electric.util.Struts2Util;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

/**
 * 
 * @author		meteor-yun
 * @version		1.0
 * @createTime	2016年2月1日
 * @className	DataDictionaryAction
 * @description	<p>数据字典表action类</p>
 */
@Controller(value="dataDictionaryAction")
@Scope(value="prototype")
public class DataDictionaryAction extends CommonAction<DataDictionary> implements Preparable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	@Named(value=DataDictionaryService.SERVIE_NAME)
	private DataDictionaryService dataDictionaryService;
	private String keywordname;
	private String typeflag;
	private String [] itemnames;
	
	public String getKeywordname() {
		return keywordname;
	}

	public void setKeywordname(String keywordname) {
		this.keywordname = keywordname;
	}

	public String getTypeflag() {
		return typeflag;
	}

	public void setTypeflag(String typeflag) {
		this.typeflag = typeflag;
	}

	public String[] getItemnames() {
		return itemnames;
	}

	public void setItemnames(String[] itemnames) {
		this.itemnames = itemnames;
	}

	public DataDictionaryService getDataDictionaryService() {
		return dataDictionaryService;
	}
	
	/**
	 * 
	 * @author		meteor-yun
	 * @version		1.0
	 * @createTime	2016年2月1日
	 * @methodName	queryAllDictionaryType
	 * @description	<p>查询数据字典表中所有的字典类型</p>
	 * @param		
	 * @result		<result name="{1}">/WEB-INF/pages/system/dictionaryIndex.jsp</result>
	 */
	public String queryAllType(){
		List<String> allTypes = this.dataDictionaryService.findAllDictionaryType();
		Struts2Util.push(ActionContext.getContext(), allTypes);
		return "queryAllType";
	}
	
	public void prepareQueryAllType(){
		this.entity = null;
	}

	/**
	 * 
	 * @author meteor-yun
	 * @version v1.0
	 * @createTime 2016年5月25日
	 * @description:修改数据字典中对应字典类型对应项
	 * @return	<result name="edit">/WEB-INF/pages/system/dictionaryEdit.jsp</result>
	 */
	public String edit(){
		List<DataDictionary> lists = dataDictionaryService.editType(this.entity);
		Struts2Util.push(ActionContext.getContext(), lists);
		return "edit";
	}
	
	/**
	 * 
	 * @author meteor-yun
	 * @version v1.0
	 * @createTime 2016年5月26日
	 * @description: 增加数据字典中字典类型或者字典类型对应的项
	 * @return
	 */
	public String save(){
		this.dataDictionaryService.saveTypeOrItem(keywordname,typeflag,itemnames);
		return "save";
	}
	
	public void prepareSave(){
		this.entity = null;
	}

	/**
	 * @method:prepare()
	 * @description:PrepareInterceptor拦截器默认总是执行的方法,如果不想总是
	 * 执行可以在配置文件指定参数alwaysInvokePrepare=false
	 * @Vsersion:V1.0.0
	 * @Author:xuyunfei
	 * @createTime:2015-11-01
	 * @params:none
	 * @return:none
	 */
	@Override
	public void prepare() throws Exception {
	}
}
