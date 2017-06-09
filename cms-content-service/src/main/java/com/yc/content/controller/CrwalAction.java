package com.yc.content.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.content.mapper.TbproductMapper;
import com.yc.pojo.JDProduction;
import com.yc.utils.HtmlUnitUtils;


@Controller
public class CrwalAction {
	
	
	
	@Autowired
	private  TbproductMapper productMapper;
	
	@RequestMapping(value="/crawl/test",method=RequestMethod.POST)
	@ResponseBody
	public String crawlTest(String url,Long count){
		int pageCount = 0;
		System.out.println("进来啦:"+ url);
		if(count!=null){
			pageCount = count.intValue();
		}
		if(!StringUtils.isBlank(url)){
			List<JDProduction> list = HtmlUnitUtils.jdDataGenerator(url,pageCount);
			productMapper.insertJDItemInfo(list);
		}
		
		
		return "OK";
	}
	
	
	
	
	
}
