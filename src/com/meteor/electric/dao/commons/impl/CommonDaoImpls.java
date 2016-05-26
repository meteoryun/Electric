package com.meteor.electric.dao.commons.impl;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.catalina.tribes.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.meteor.electric.dao.commons.CommonDaos;
import com.meteor.electric.util.GenericTypeConversion;

public abstract class CommonDaoImpls<T> implements CommonDaos<T>{

	private static final Logger logger = LogManager.getLogger(CommonDaoImpls.class);
	
	//参数化类型的class对象
	protected Class<T> clazz;
	@Resource(name="hibernateTemplate")
	public HibernateTemplate hibernateTemplate;
	
	public CommonDaoImpls() {
		this.clazz = GenericTypeConversion.getActualTypeArguments(this.getClass());
	}
	
	@Override
	public void save(T t) {
		this.hibernateTemplate.save(t);
	}

	@Override
	public void update(T t) {
		this.hibernateTemplate.update(t);
	}

	@Override
	public void delete(T t) {
		this.hibernateTemplate.delete(t);
	}
	
	@Override
	public void delete(Serializable id) {
		T t = this.findSingleObjectById(id);
		if(null == t) 
			throw new NullPointerException("ID: " + id +"在数据库中未找到对应的记录存在......");
		this.hibernateTemplate.delete(t);
	}
	
	@Override
	public void delete(Serializable... id) {
		if(null == id || id.length == 0)
			throw new NullPointerException("没有传递任何参数ID: " + Arrays.toString(id));
		StringBuilder ids  = new StringBuilder();
		for(int i=0; i<id.length; i++){
			T t = this.findSingleObjectById(id[i]);
			if(null == t) {
				ids.append(id[i] + ",");
				continue;
			}
			this.hibernateTemplate.delete(t);	
		}
		System.out.println("数据库中不存在记录ID：[" + ids.substring(0, ids.lastIndexOf(",")) + "]");
	}
	
	@Override
	public void delete(List<T> lists) {
		if(null == lists || lists.size() == 0){
			System.out.println("集合中不存在任何元素: " + lists);
			return;
		}
		
		this.hibernateTemplate.deleteAll(lists);
		this.hibernateTemplate.flush();
	}
	
	@Override
	public T findSingleObjectById(Serializable id) {
		T t = clazz.cast(this.hibernateTemplate.get(this.clazz, id));
		return t;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllEntityObject() {
		String entityName = this.clazz.getSimpleName();
		String hql = " from " + entityName + " " + GenericTypeConversion.firstToLowerCase(entityName);
		List<?> lists = this.hibernateTemplate.find(hql);
		return (List<T>) lists;
	}
	
	@Override
	public List<T> customConditionFind(Map<String, Object[]> assembleCondition) {
		List<T> lists = null;
		String entityName = this.clazz.getSimpleName();
		String  hql = " from " + entityName + " " 
						+ GenericTypeConversion.firstToLowerCase(entityName)
						+ " where 1=1";
		if(null == assembleCondition || assembleCondition.size() == 0){
			lists = this.findAllEntityObject();
		}else{
			String condittion = "";
			Object [] params = null;
			Set<Entry<String,Object[]>> entrys = assembleCondition.entrySet();
			Iterator<Entry<String,Object[]>> iterators = entrys.iterator();
			while(iterators.hasNext()){
				Entry<String,Object[]> entry = iterators.next();
				condittion = entry.getKey();
				params = entry.getValue();
			}
			final String queryString = hql + condittion;
			final Object[] finalParms = params;
			logger.info("finalCustomCondition:[" + queryString + "]");
			logger.info("finalParams:" + Arrays.toString(finalParms));
			lists = this.hibernateTemplate.execute(new HibernateCallback<List<T>>() {
				@SuppressWarnings("unchecked")
				@Override
				public List<T> doInHibernate(Session session) throws HibernateException {
					Query query = session.createQuery(queryString);
					for(int i=0; i<finalParms.length; i++){
						query.setParameter(i, finalParms[i]);
					}
					return query.list();
				}
			});
		}
		logger.info(lists);
		return lists;
	}

	@Override
	public List<String> findSingleColumsBySpecifyCondition(String fieldName) {
		String alias = GenericTypeConversion.firstToLowerCase(this.clazz.getSimpleName());
		String sql = "select distinct " + alias + "." + fieldName +  " from " + this.clazz.getSimpleName() + " " + alias;
		logger.info("findSingleColumsBySpecifyCondition() sql: " + sql);
		@SuppressWarnings("unchecked")
		List<String> lists = (List<String>) this.hibernateTemplate.find(sql);
		logger.info("return data: " + lists);
		return lists;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findSepecifyConditionAllEntityBySingleColums(String fieldName,String value) {
		String alias = GenericTypeConversion.firstToLowerCase(this.clazz.getSimpleName());
		String sql = " from " + this.clazz.getSimpleName() + " " + alias + " where " + alias + "." + fieldName + "='" + value + "'";
		logger.info("findSepecifyConditionAllEntityBySingleColums() sql:" + sql);
		List<?> lists = this.hibernateTemplate.find(sql);
		return (List<T>) lists;
	}
}
