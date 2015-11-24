package com.meteor.electric.action.system;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.meteor.electric.action.commons.CommonAction;
import com.meteor.electric.domain.system.RunMonitorCommonInfo;
import com.meteor.electric.service.system.RunMonitorCommonInfoService;
import com.meteor.electric.util.Struts2Util;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

/**
 * @class:RunMonitorAction
 * @description:记录站点和设备运行情况信息的类
 * @Vsersion:V1.0.0
 * @author:Xuyunfei
 * @createTime:2015-11-01
 */
@Controller(value="runMonitorAction")
@Scope(value="prototype")
public class RunMonitorAction extends CommonAction<RunMonitorCommonInfo> implements Preparable{

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(RunMonitorAction.class);
	private RunMonitorCommonInfo runMonitorCommonInfo = this.getModel();
	@Inject
	@Named(value=RunMonitorCommonInfoService.SERVICE_NAME)
	private RunMonitorCommonInfoService runMonitorCommonInfoService;
	
	public RunMonitorCommonInfoService getRunMonitorCommonInfoService() {
		return runMonitorCommonInfoService;
	}
	
	public void prepareFinfindRunMonitorMsg(){
		if(this.runMonitorCommonInfo != null){
			this.runMonitorCommonInfo = null;
		}
	}
	
	/**
	 * @method:findRunMonitorMsg()
	 * @description:查询运行监控表中当前的设备站点信息并返回
	 * @Vsersion:V1.0.0
	 * @Author:xuyunfei
	 * @createTime:2015-11-01
	 * @params: none
	 * @return:<result>/WEB-INF/pages/system/actingIndex.jsp</result>
	 */
	public String findRunMonitorMsg(){
		List<RunMonitorCommonInfo> lists = this.runMonitorCommonInfoService.findRunMonitorMsg();
		if(null == lists || lists.size() == 0){
			logger.warn("当前数据表中没有任何设备或者站点运行信息",lists);
		}else{
			RunMonitorCommonInfo runMonitorCommonInfo = lists.get(0);
			Struts2Util.push(ActionContext.getContext(), runMonitorCommonInfo);
		}
		return SUCCESS;
	}
	
	/**
	 * @method:saveRunMonitorMsg()
	 * @description:保存运行监控信息如果存在则更新
	 * @Vsersion:V1.0.0
	 * @Author:xuyunfei
	 * @createTime:2015-11-01
	 * @params: none
	 * @return:<result>/WEB-INF/pages/system/actingIndex.jsp</result>
	 */
	public String save(){
		this.runMonitorCommonInfoService.saveRunMonitorMsg(runMonitorCommonInfo);
		return "save";
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
	public void prepare() throws Exception {}

}
