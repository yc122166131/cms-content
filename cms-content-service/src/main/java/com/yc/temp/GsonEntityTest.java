package com.yc.temp;


import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cn.yc.pojo.Person;
import com.cn.yc.utils.GsonUtils;
import com.cn.yc.utils.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class GsonEntityTest {

	public static void main(String[] args) {
		
		String jsonStr="{'1': {'id': '1','code': 'bj','name': '北京','map': '39.90403, 116.40752599999996'},'2': {'id': '2','code': 'sz','name': '深圳','map': '22.543099, 114.05786799999998'},'9': {'id': '9','code': 'sh','name': '上海','map': '31.230393,121.473704'},'10': {'id': '10','code': 'gz','name': '广州','map': '23.129163,113.26443500000005'}}";  
		Map citys = GsonUtils.parseDatatoMap(jsonStr,"String");
		
		
	}

	
	 
	   
	 
}


class MiddleWareFather<T>{
	
	public void  testJ(){
		 System.out.println(getClass());
		 Class clazz = Gson111.getSuperClassGenricType(getClass());
		 System.out.println(clazz);
	}
}


class GsonMiddleware<T> extends MiddleWareFather<T>{
	
	
	 
}
