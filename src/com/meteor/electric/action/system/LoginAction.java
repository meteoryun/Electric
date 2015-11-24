package com.meteor.electric.action.system;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.meteor.electric.action.commons.CommonAction;
import com.meteor.electric.domain.system.User;
import com.opensymphony.xwork2.Preparable;
/**
 * @class:LoginAction
 * @description:处理用户登录信息的类
 * @Vsersion:V1.0.0
 * @author Xuyunfei
 * @createTime:2015-10-31
 */
@Controller(value="loginAction")
@Scope(value="prototype")
public class LoginAction extends CommonAction<User> implements Preparable{

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(LoginAction.class);
	private User user = this.getModel();
	
	/**
	 * @method:login()
	 * @description:用户登录系统处理方法
	 * @Vsersion:V1.0.0
	 * @Author:xuyunfei
	 * @createTime:2015-10-31
	 * @params:none
	 * @return:<result name="login">/WEB-INF/pages/menu/home.jsp</result>
	 */
	public String login(){
		logger.info("登录信息:" + user);
		return "login";
	}
	
	/**
	 * @method:prepareOut()
	 * @description:执行退出方法时会先执行该方法,使model对象不被放入栈顶
	 * @Vsersion:V1.0.0
	 * @Author:xuyunfei
	 * @createTime:2015-10-31
	 * @params:none
	 * @return:none
	 */
	public void prepareOut(){
		if(this.user != null){
			this.user = null;
		}
	}
	
	/**
	 * @method:out()
	 * @description:用户退出系统时执行的方法
	 * @Vsersion:V1.0.0
	 * @Author:xuyunfei
	 * @createTime:2015-10-31
	 * @params:none
	 * @return:<result name="out" type="redirect">/WEB-INF/pages/menu/index.jsp</result>
	 */
	public String out(){
		this.request.getSession().invalidate();
		return "out";
	}

	/**
	 * @method:prepare()
	 * @description:PrepareInterceptor拦截器默认总是执行的方法,如果不想总是
	 * 执行可以在配置文件指定参数alwaysInvokePrepare=false
	 * @Vsersion:V1.0.0
	 * @Author:xuyunfei
	 * @createTime:2015-10-31
	 * @params:none
	 * @return:none
	 */
	@Override
	public void prepare() throws Exception {}
}
