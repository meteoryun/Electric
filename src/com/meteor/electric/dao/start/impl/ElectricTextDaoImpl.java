package com.meteor.electric.dao.start.impl;

import org.springframework.stereotype.Repository;

import com.meteor.electric.dao.commons.impl.CommonDaoImpls;
import com.meteor.electric.dao.start.ElectricTextDao;
import com.meteor.electric.domain.start.ElectricText;

@Repository(ElectricTextDao.DAO_NAME)
public class ElectricTextDaoImpl extends CommonDaoImpls<ElectricText> implements ElectricTextDao{
	
}
