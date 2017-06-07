package com.yc.content.test;

import static org.junit.Assert.*;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yc.content.mapper.TbproductMapper;
import com.yc.pojo.JDProduction;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:spring/applicationContext-dao.xml"})  
public class CrawlTest {

	@Autowired
	private  TbproductMapper productMapper;
	
	@Test
	public void testCrawl(){
		List<JDProduction> count = productMapper.getAll();
		/*Assert.assertEquals(count, 1);*/
		System.out.println(count);
	}
	
	
	
}
