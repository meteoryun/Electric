package com.meteor.electric.action.start;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.meteor.electric.action.commons.CommonAction;
import com.meteor.electric.domain.start.ElectricText;
import com.meteor.electric.service.start.ElectricTextService;

@Controller(value="electricTextAction")
@Scope(value="prototype")
public class ElectricTextAction extends CommonAction<ElectricText>{

	private static final long serialVersionUID = 1L;
	
	@Inject
	@Named(value=ElectricTextService.SERVICE_NAME)
	private ElectricTextService electricTextService;
	private ElectricText electricText = this.getModel();
	
	public ElectricTextService getElectricTextService() {
		return electricTextService;
	}
	
	public String save(){
		this.electricTextService.saveElectric(electricText);
		return "save";
	}

}
