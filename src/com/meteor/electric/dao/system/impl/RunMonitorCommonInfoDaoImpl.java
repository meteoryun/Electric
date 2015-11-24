package com.meteor.electric.dao.system.impl;

import org.springframework.stereotype.Repository;

import com.meteor.electric.dao.commons.impl.CommonDaoImpls;
import com.meteor.electric.dao.system.RunMonitorCommonInfoDao;
import com.meteor.electric.domain.system.RunMonitorCommonInfo;

@Repository(value=RunMonitorCommonInfoDao.DAO_NAME)
public class RunMonitorCommonInfoDaoImpl extends CommonDaoImpls<RunMonitorCommonInfo> implements RunMonitorCommonInfoDao {


}
