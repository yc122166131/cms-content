package com.yc.content.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
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
	
	@Test
	public void test2(){
		List<JDProduction> list1 = new ArrayList<JDProduction>();
		JDProduction jd1 = new JDProduction("aaaa","kafi");
		list1.add(jd1);
		JDProduction jd2 = new JDProduction("bbbb","fff");
		list1.add(jd2);
		productMapper.insertJDItemInfo(list1);
		List<JDProduction> count = productMapper.getAll();
		System.out.println(count);
	
	}
	
}
