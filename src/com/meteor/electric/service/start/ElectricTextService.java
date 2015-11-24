package com.meteor.electric.service.start;

import java.io.Serializable;
import java.util.List;

import com.meteor.electric.domain.start.ElectricText;

public interface ElectricTextService {

	public static final String SERVICE_NAME = "com.meteor.electric.service.start.impl.ElectricTextServiceImpl";
	
	public void saveElectric(ElectricText electricText);
	
	public ElectricText findElectricById(Serializable id);
	
	public List<ElectricText> findCollectionCustomCondition(ElectricText electricText);
}
