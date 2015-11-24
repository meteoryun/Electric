package com.meteor.electric.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ReflectUtils {
	
	private static final Logger logger = LogManager.getLogger(ReflectUtils.class);

	/**
	 * 获取指定类中声明的成员变量,并按照名称和类型的方式
	 * 规则：排除所有静态的成员变量
	 * 返回一个Map集合
	 * @param clazz
	 * @return Map<String,Object>
	 */
	public static <T> Map<String,Object> obtainClassDeclarsFields(Class<T> clazz){
		Map<String,Object> fieldTypeNameMappings = new HashMap<String,Object>();
		if(null == clazz){
			logger.error("clazz is empty!");
			return null;
		}
		Field [] fields  = clazz.getDeclaredFields();
		if(null == fields || 0 == fields.length){
			logger.error("clazz:" + clazz.getName() + " is not any declared fields!");
			return null;
		}
		for(Field field : fields){
			if(Modifier.isStatic(field.getModifiers())) continue;
			fieldTypeNameMappings.put(field.getName(), field.getType().getName());
		}
		
		logger.info(clazz.getName() + "-" + fieldTypeNameMappings);
		return fieldTypeNameMappings;
	}
	
	/**
	 * 调用给定的方法,并返回对应值
	 * @param methodName
	 * @param t
	 * @return
	 */
	public static <T> Object invokeSpecificMethod(String methodName,T t,Class<?> [] parameterTypes,Object... args){
		if(StringUtils.isBlank(methodName) || null == t){
			logger.warn("please make sure params is not empty!" + "methodName=[" + methodName +"] " + "t=[" + t + "]");
			return null;
		}
		Method method = null;
		Object obj = null;
		try {
			 method = t.getClass().getMethod(methodName, parameterTypes);
			obj = method.invoke(t, args);
		} catch (NoSuchMethodException e) {
			logger.error("give methodName is not exist! " + "methodName=[" + methodName + "]");
			e.printStackTrace();
		} catch (SecurityException e) {
			logger.error("give methodName is not access,please check method isPublic! " + "methodName=[" + methodName + "]");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			logger.error("give methodName is not access,please check method isPublic! " + "methodName=[" + methodName + "]");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			logger.error("please make sure giving params and giving method is matched!" + 
					"args=" + Arrays.toString(args) + "," + 
					" methodName=[" + obtainMethodDeclare(method) + "]",e);
		} catch (InvocationTargetException e) {
			logger.error(e);
		}
		if(null == obj){
			logger.warn(t.getClass().getName() + "-" + obtainMethodDeclare(method) + "-->return value:[" + obj + "]");
		}else{
			logger.info(t.getClass().getName() + "-" + obtainMethodDeclare(method) + "-->return value:[" + obj + "]");
		}
		return obj;
	}
	
	/**
	 * 根据成员属性,生成对应getters方法
	 * @param fieldName
	 * @return
	 */
	public static String gettersMethodNames(String fieldName){
		return "get" + fieldName.substring(0, 1).toUpperCase() 
				+ fieldName.substring(1);
	}
	
	/**
	 * 根据成员属性,生成对应setters方法
	 * @param fieldName
	 * @return
	 */
	public static String settersMethodNames(String fieldName){
		return "set" + fieldName.substring(0, 1).toUpperCase() 
				+ fieldName.substring(1);
	}
	
	/**
	 * 通过给定Method对象,返回其对应的方法声明
	 * @param method
	 * @return
	 */
	public static String obtainMethodDeclare(Method method){
		if(null == method){
			logger.warn("method is empty! " + "method=[" + method + "]");
			return null;
		}
		StringBuilder methodDeclare = new StringBuilder();
		methodDeclare.append(Modifier.toString(method.getModifiers()) + " ")
		.append(method.getReturnType().getSimpleName() + " ")
		.append(method.getName() + "(");
		
		Class<?> [] parametersType = method.getParameterTypes();
		if(null == parametersType || parametersType.length == 0){
			methodDeclare.append(")");
		}else {
			for(int i=0; i<parametersType.length; i++){
				if(i > 0){
					methodDeclare.append(", ");
				}
				methodDeclare.append(parametersType[i].getSimpleName() + " args" + i);
			}
			methodDeclare.append(")");
		}
		//logger.info("返回方法声明： " + methodDeclare);
		return methodDeclare.toString();
	}
}
