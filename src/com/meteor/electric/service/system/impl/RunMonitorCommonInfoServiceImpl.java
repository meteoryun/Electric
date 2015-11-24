package com.meteor.electric.service.system.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meteor.electric.dao.system.RunMonitorCommonInfoDao;
import com.meteor.electric.domain.system.RunMonitorCommonInfo;
import com.meteor.electric.service.system.RunMonitorCommonInfoService;

@Service(value=RunMonitorCommonInfoService.SERVICE_NAME)
@Transactional(readOnly=true)
public class RunMonitorCommonInfoServiceImpl implements RunMonitorCommonInfoService {

	private static final Logger logger = LogManager.getLogger(RunMonitorCommonInfoServiceImpl.class);
	@Resource(name=RunMonitorCommonInfoDao.DAO_NAME)
	private RunMonitorCommonInfoDao runMonitorCommonInfoDao;
	
	public RunMonitorCommonInfoDao getRunMonitorCommonInfoDao() {
		return runMonitorCommonInfoDao;
	}
	
	@Override
	public List<RunMonitorCommonInfo> findRunMonitorMsg() {
		return this.runMonitorCommonInfoDao.findAllEntityObject();
	}

	/**
	 * @method:saveRunMonitorMsg()
	 * @description:保存运行监控信息如果存在则更新,在更新时将使用快照进行更新
	 * 如果直接update更新会产生错误,因为在Hibernate中 使用对象OID进行缓存
	 * 首先已经从数据库中查询,那么session缓存中就存在了一个对象
	 * 这时又将该对象ID赋值给另一个对象的ID,此时进行直接更新session会往缓存加入
	 * 但是在session缓存中已经存在一个相同OID的对象
	 * @Vsersion:V1.0.0
	 * @Author:xuyunfei
	 * @createTime:2015-11-01
	 * @params: none
	 * @return:<result>/WEB-INF/pages/system/actingIndex.jsp</result>
	 */
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	@Override
	public void saveRunMonitorMsg(RunMonitorCommonInfo runMonitorCommonInfo) {
		if(null == runMonitorCommonInfo){
			logger.error("formal parameter is null [runMonitorCommonInfo=" + runMonitorCommonInfo + "]",new NullPointerException("runMonitorCommonInfo is null"));
			return;
		}
		String stationRunMsg = runMonitorCommonInfo.getStationRunMsg();
		String deviceRunMsg = runMonitorCommonInfo.getDeviceRunMsg();
		if(StringUtils.isBlank(stationRunMsg) && StringUtils.isBlank(deviceRunMsg)){
			logger.error("content is empty [stationRunMsg=" + stationRunMsg + ",deviceRunMsg=" + deviceRunMsg + "]",new NullPointerException("content is empty"));
			return;
		}
		List<RunMonitorCommonInfo> lists = this.runMonitorCommonInfoDao.findAllEntityObject();
		if(null == lists || lists.size() == 0){
			runMonitorCommonInfo.setCreateDate(new Date());
			System.out.println("Instantiation Bean:" + runMonitorCommonInfo);
			this.runMonitorCommonInfoDao.save(runMonitorCommonInfo);
		}else {
			RunMonitorCommonInfo temp = lists.get(0);
			//使用快照进行更新
			temp.setStationRunMsg(stationRunMsg);
			temp.setDeviceRunMsg(deviceRunMsg);
			temp.setCreateDate(new Date());
			System.out.println("Instantiation Bean:" + temp);
		}
	}

}
