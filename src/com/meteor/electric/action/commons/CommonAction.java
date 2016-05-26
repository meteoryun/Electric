package com.meteor.electric.action.commons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.meteor.electric.util.GenericTypeConversion;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * @author meteor-yun
 * @version: 1.0
 * @createTime: 2016年1月20日
 * @className: CommonAction
 * @description:<p>所有action类的父类,实现了一些公共的方法</p>
 * @param <T>
 */
public class CommonAction<T> extends ActionSupport implements ModelDriven<T>, ServletRequestAware,ServletResponseAware{

	private static final long serialVersionUID = 1L;
	protected T entity;
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	/**
	 * 
	 * @author meteor-yun
	 * @version: 1.0
	 * @createTime: 2016年1月20日
	 * @methodName: CommonAction
	 * @description:<p>统一生成对应实体bean的一个对象,并赋值给ModenDriven对象</p>
	 */
	public CommonAction(){
		Class<T> clazz = GenericTypeConversion.getActualTypeArguments(this.getClass());
		this.entity =  GenericTypeConversion.getInstance(clazz);
	}

	@Override
	public T getModel() {
		return this.entity;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
