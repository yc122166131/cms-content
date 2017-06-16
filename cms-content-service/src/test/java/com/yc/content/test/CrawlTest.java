package com.yc.content.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yc.content.mapper.TbproductMapper;
import com.yc.content.mapper.TbproductTypeMapper;
import com.yc.pojo.dto.ProductLeftMenuDto;
import com.yc.pojo.product.JDProduction;
import com.yc.pojo.product.SubType;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:spring/applicationContext-dao.xml"})  
public class CrawlTest {

	@Autowired
	private  TbproductMapper productMapper;
	
	/**
	 * 测试  查询 商品
	 */
	@Test
	public void testCrawl(){
		List<JDProduction> count = productMapper.getAll();
		/*Assert.assertEquals(count, 1);*/
		System.out.println(count);
	}
	
	
	/**
	 * 测试 插入 商品
	 */
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
	
	
	
	/**
	 * 测试 根据 typeId 查询出 product 商品
	 */
	@Test
	public void testProductByTypeId(){
		
		List<JDProduction> list1 = productMapper.getProductInfoByProductTypeId(new Long(11));
		System.out.println(list1);
	
	}
	
	
	
	
}
