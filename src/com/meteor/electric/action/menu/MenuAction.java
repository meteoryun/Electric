package com.meteor.electric.action.menu;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.meteor.electric.action.system.RunMonitorAction;
import com.meteor.electric.domain.system.RunMonitorCommonInfo;
import com.meteor.electric.service.system.RunMonitorCommonInfoService;
import com.meteor.electric.util.Struts2Util;
import com.opensymphony.xwork2.ActionContext;

@Controller(value="menuAction")
@Scope(value="prototype")
public class MenuAction {

	private static final Logger logger = LogManager.getLogger(RunMonitorAction.class);
	@Inject
	@Named(value=RunMonitorCommonInfoService.SERVICE_NAME)
	private RunMonitorCommonInfoService runMonitorCommonInfoService;
	
	public RunMonitorCommonInfoService getRunMonitorCommonInfoService() {
		return runMonitorCommonInfoService;
	}
	
	public String title(){
		return "title";
	}
	
	public String left(){
		return "left";
	}
	
	public String change(){
		return "change";
	}
	
	public String loading(){
		return "loading";
	}
	
	public String alermStation(){
		dev_station_msg();
		return "alermStation";
	}
	
	public String alermDevice(){
		dev_station_msg();
		return "alermDevice";
	}
	
	private void dev_station_msg() {
		List<RunMonitorCommonInfo> lists = this.runMonitorCommonInfoService.findRunMonitorMsg();
		if(null == lists || lists.size() == 0){
			logger.warn("当前数据表中没有任何设备或者站点运行信息",lists);
		}else{
			RunMonitorCommonInfo runMonitorCommonInfo = lists.get(0);
			Struts2Util.push(ActionContext.getContext(), runMonitorCommonInfo);
		}
	}
}
