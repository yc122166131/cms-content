package com.yc.content.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yc.content.mapper.TbproductMapper;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:spring/applicationContext-dao.xml"})  
public class CrawlTest {

	@Autowired
	private  TbproductMapper productMapper;
	
	
	@Test
	public void testCrawl(){
		
	}
	
	
	
}
