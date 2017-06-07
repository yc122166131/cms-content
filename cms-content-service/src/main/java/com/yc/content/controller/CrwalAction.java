package com.yc.content.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class CrwalAction {

	@RequestMapping(value="/crawl/test",method=RequestMethod.POST)
	@ResponseBody
	public String crawlTest(){
		
		
		
		return null;
	}
	
	
	
	
	
}
