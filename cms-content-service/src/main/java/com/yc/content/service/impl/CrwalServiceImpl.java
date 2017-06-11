package com.yc.content.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.content.jedis.JedisClient;
import com.yc.content.service.CrwalService;
import com.yc.pojo.JDProduction;

@Service(value="crwalService")
public class CrwalServiceImpl implements CrwalService {

	@Autowired
	private JedisClient jedisClient;

	@Override
	public List<JDProduction> addProductId(List<JDProduction> list) {
		
		List<JDProduction> jdLists = new ArrayList<JDProduction>();
		for(JDProduction jp : list){
			//  Product p = new Product();
			Long currId = jedisClient.incr("yc_productId");
            //  BeanUtils.copyProperties(jp, p);
			jp.setProductId(currId);
		}
		return jdLists;
	}
	
	
	
	
}
