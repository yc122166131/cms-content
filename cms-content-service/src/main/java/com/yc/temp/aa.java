package com.yc.temp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



import com.cn.yc.utils.GsonUtils;
import com.cn.yc.utils.JsonUtils;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;


public class aa {

	public static void main(String[] args) {
		WebClient client = new WebClient();
		Page page;
		String skuId = "2341892";
		try {
			page = client.getPage("http://p.3.cn/prices/mgets?skuIds=J_"+skuId+"%2C");
			WebResponse response = page.getWebResponse();
			if (response.getContentType().equals("application/json")) {
			 String json = response.getContentAsString();
			 //System.out.println(json);//json.getClass().getName()
			 List list =  GsonUtils.parseDataToList(json);
			 for(int i = 0 ; i < list.size() ; i++){
				 if(list.get(i) instanceof Map){
					 Map m = (Map) list.get(i);
					 System.out.println(m.get("p"));
				 }
			 }
			}
		} catch (FailingHttpStatusCodeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	

	
	
	

}
