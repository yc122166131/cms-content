package com.yc.temp;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.cn.yc.pojo.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Gson111 {

	
   /**
   	* parseDataToList(strJson,Person.class);  
    * 函数名称: parseDataToList
    * 函数描述: 将json字符串转换为List , 返回相对应的  泛型 class 实体对象
    * @param data
    * @return
    */
   private static  <T> List<T> parseDataToList(String data){
       GsonBuilder gb = new GsonBuilder();
       Gson g = gb.create();
       List<T> list = g.fromJson(data, new TypeToken<List<Person>>(){}.getType());
       return list;
   }
   
   
   @SuppressWarnings("unchecked")
	public static <T> Class<T> getSuperClassGenricType(final Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}
   
	@SuppressWarnings("unchecked")
	public static Class getSuperClassGenricType(final Class clazz, final int index) {

		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}

		return (Class) params[index];
	}
	
	
	
}
