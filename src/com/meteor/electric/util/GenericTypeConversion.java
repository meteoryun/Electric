package com.meteor.electric.util;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class GenericTypeConversion {
	
	private static final Logger logger = LogManager.getLogger(GenericTypeConversion.class);

	/**
	 * 使用参数化范型,获取范型实际参数类型
	 * @param <T>
	 * @param clazz
	 * @return 实际参数类型的Class<?>对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getActualTypeArguments(Class<?> clazz){
		ParameterizedType parameterType = (ParameterizedType) clazz.getGenericSuperclass();			
		return (Class<T>) parameterType.getActualTypeArguments()[0];
	}
	
	/**
	 * 根据传入Class对象调用默认构造方法生成一个对象实例
	 * @param clazz
	 * @return
	 */
	public static <T> T getInstance(Class<T> clazz){
		T t = null;
		try {
			   t = clazz.newInstance();
		} catch (InstantiationException e) {
			System.out.println("该clazz=" + clazz.getName() + "是一个不能实例化的对象（无默认构造函数、接口、抽象类等）");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("默认的构造方法无法访问clazz" + clazz.getName());
			e.printStackTrace();
		}
		return  t;
	}
	
	/*
	 * 给定一个字符序列,首字母转换成大写
	 */
	public static String firstToLowerCase(String str){
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}
	
	/**
	 * 根据业务层业务,生成自定义条件查询
	 * 规则：and alis.colunm like %% and ... like %%
	 * @param t
	 * @return
	 */
	public static <T> Map<String,Object[]> cunstomConditionFindAssemble(T t,boolean flag){
		//查询条件
		StringBuilder condition = new StringBuilder();
		//取得规则之内成员变量
		Map<String,String> fieldsName = ReflectUtils.obtainClassDeclarsFields(t.getClass());
		//可变参数
		List<Object> params = new ArrayList<Object>();
		Set<String> keyNames = fieldsName.keySet();
		Iterator<String> iterators = keyNames.iterator();
		while(iterators.hasNext()){
			String fieldName = iterators.next();
			String methodName = ReflectUtils.gettersMethodNames(fieldName);
			Object methodReturnValue = ReflectUtils.invokeSpecificMethod(methodName, t, new Class<?>[]{} , new Object[]{});
			if(null == methodReturnValue) continue;
			else{
				if(flag){
					condition.append(" and " + GenericTypeConversion.firstToLowerCase(t.getClass().getSimpleName()) + "." + fieldName + "=?");
					params.add(methodReturnValue);
				}else{
					condition.append(" and " + GenericTypeConversion.firstToLowerCase(t.getClass().getSimpleName()) + "." + fieldName + " like ?");
					params.add("%" + methodReturnValue + "%");
				}
			}
		}
		
		//声明一个Map集合将最后的组装的条件及参数添加进去
		Map<String,Object[]> result = new HashMap<>();
		if(null == params || params.size() == 0){
			logger.warn("condition is empty[" + condition + "] or params is null" + params);
			return result;
		}
		result.put(condition.toString(), params.toArray());
		logger.info("generator condition:[" + t.getClass().getName() + ":" +  condition.toString() + "]-" + params);
		return result;
	}
	
	public static Object byTypeObtainGenerate(String key,Map<String,String> maps,Object value){
		
		switch(maps.get(key)){
		case "java.lang.String" :
			return "'" + value + "'";
		case "java.util.Date" :
			return "'" + value + "'";
		case "java.lang.Character" :
			return "'" + value + "'";
		default :
			return value;
		}
	}
}
