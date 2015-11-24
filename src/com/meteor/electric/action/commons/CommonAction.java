package com.meteor.electric.action.commons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.meteor.electric.util.GenericTypeConversion;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CommonAction<T> extends ActionSupport implements ModelDriven<T>, ServletRequestAware,ServletResponseAware{

	private static final long serialVersionUID = 1L;
	protected T entity;
	protected HttpServletRequest request;
	protected HttpServletResponse response;

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
