package com.meteor.electric.service.start.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meteor.electric.dao.start.ElectricTextDao;
import com.meteor.electric.domain.start.ElectricText;
import com.meteor.electric.service.start.ElectricTextService;
import com.meteor.electric.util.GenericTypeConversion;

@Service(ElectricTextService.SERVICE_NAME)
@Transactional(readOnly=true)
public class ElectricTextServiceImpl implements ElectricTextService {

	private static final Logger logger = LogManager.getLogger(ElectricTextServiceImpl.class);
	
	@Resource(name=ElectricTextDao.DAO_NAME)
	private ElectricTextDao electricTextDao;
	
	public ElectricTextDao getElectricTextDao() {
		return electricTextDao;
	}
	
	@Transactional(readOnly=false)
	@Override
	public void saveElectric(ElectricText electricText) {
		this.electricTextDao.save(electricText);
	}

	@Override
	public ElectricText findElectricById(Serializable id) {
		return this.electricTextDao.findSingleObjectById(id);
	}

	@Override
	public List<ElectricText> findCollectionCustomCondition(ElectricText electricText) {
		if(null == electricText){
			logger.error("no instances class " + ElectricText.class.getName(), new NullPointerException());
			return null;
		}
		Map<String,Object[]> assembleCondition = GenericTypeConversion.cunstomConditionFindAssemble(electricText,false);
		return	electricTextDao.customConditionFind(assembleCondition);
	}

}
