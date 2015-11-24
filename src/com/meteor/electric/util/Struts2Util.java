package com.meteor.electric.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.meteor.electric.action.system.RunMonitorAction;
import com.opensymphony.xwork2.ActionContext;

/**
 * @class:Struts2Util
 * @description:封装了Struts2中的一些常用方法
 * @Vsersion:V1.0.0
 * @author:Xuyunfei
 * @createTime:2015-11-01
 */
public class Struts2Util {

	private static final Logger logger = LogManager.getLogger(RunMonitorAction.class);
	/**
	 * @method:push()
	 * @description:将某个对象压入栈顶
	 * @Vsersion:V1.0.0
	 * @Author:xuyunfei
	 * @createTime:2015-11-01
	 * @params:none
	 * @return:none
	 */
	public static void push(ActionContext actionContext,Object obj){
		if(null == actionContext || null == obj){
			logger.warn("actionContext is empty[" + actionContext + "]or obj is empty[" + obj + "]");
			return;
		}
		actionContext.getValueStack().push(obj);
	}
}
