package com.meteor.electric.dao.commons;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface CommonDaos<T> {

	/**
	 * DAO层公共保存方法
	 * @param t
	 */
	public void save(T t);
	
	/**
	 * DAO层公共更新方法
	 * @param t
	 */
	public void update(T t);
	
	/**
	 * DAO层公共删除方法,根据对象
	 * @param t
	 */
	public void delete(T t);
	
	/**
	 * DAO层公共删除方法,根据主键ID
	 * @param t
	 */
	public void delete(Serializable id);
	
	/**
	 * DAO层公共批量删除方法,根据主键ID
	 * @param t
	 */
	public void delete(Serializable... id);
	
	/**
	 * DAO层公共批量删除方法,根据对象集合
	 * @param t
	 */
	public void delete(List<T> lists);
	
	/**
	 * DAO层公共查询方法，针对ID，返回一个唯一的实体对象
	 * @param t
	 */
	public T findSingleObjectById(Serializable id);
	
	/**
	 * DAO层公共查询方法,默认查询表中所有内容
	 * @param t
	 */
	public List<T> findAllEntityObject();
	
	/**
	 * 
	 * @author		meteor-yun
	 * @version		1.0
	 * @createTime	2016年2月1日
	 * @methodName	findSingleColumsBySpecifyCondition
	 * @description	通过指定指定的查询条件进行查询,限制必须查询单独的某一列
	 * @params		sql,指定的查询条件
	 * @result		List<String>
	 */
	public List<String> findSingleColumsBySpecifyCondition(String fieldName);
	
	/**
	 * DAO层公共查询方法,根据前端选择,由各自业务组装条件语句 
	 * 规则:select * from tableName t where 1=1 #DAO层封装
	 *      and t.xxx like %% #service层封装
	 *      and t.xxx like %% #service层封装
	 *      order by t.xxx asc,t.xxx desc #service层封装
	 * @param assembleCondition
	 * @return List<T>
	 */
	public List<T> customConditionFind(Map<String, Object[]> assembleCondition);

	/**
	 * 
	 * @author meteor-yun
	 * @version v1.0
	 * @createTime 2016年5月26日
	 * @description: 查找指定条件中所有实体数据,限制指定某个属性作为条件
	 * @param fieldName
	 * @return List<T>
	 */
	public List<T> findSepecifyConditionAllEntityBySingleColums(String fieldName,String value);
}
