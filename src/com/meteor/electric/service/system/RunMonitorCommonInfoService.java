package com.meteor.electric.service.system;

import java.util.List;

import com.meteor.electric.domain.system.RunMonitorCommonInfo;

public interface RunMonitorCommonInfoService {
	
	public static final String SERVICE_NAME = "com.meteor.electric.service.system.impl.RunMonitorCommonInfoServiceImpl";
	
	public List<RunMonitorCommonInfo> findRunMonitorMsg();
	
	public void saveRunMonitorMsg(RunMonitorCommonInfo runMonitorCommonInfo);
}
