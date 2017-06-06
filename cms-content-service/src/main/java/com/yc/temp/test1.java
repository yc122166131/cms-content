package com.yc.temp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlBold;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class test1 {

	
	
	public static void main(String[] args) {
		
		String url  =  "https://list.jd.com/list.html?cat=1316,1383,1404";
		WebClient webClient=new WebClient();
		webClient.getOptions().setCssEnabled(false); 
		webClient.getOptions().setJavaScriptEnabled(false); 
		HtmlPage page = null ; //这里是 htmlPage 
		try {
			page = webClient.getPage(url);
		    //List<?> list = page.getByXPath("//div[end-with(@class,'one-level')]");
			//ends-with 不起作用的时候用下面的 方法获取 !
		    List<?> list1 = page.getByXPath("//div"
		    		+ "[substring(@class, string-length(@class)"
		    		+ " - string-length('one-level') +1) = 'one-level']"
		    		+ "/a");
		    HtmlAnchor supCateDom = (HtmlAnchor) list1.get(0);
			System.out.println( supCateDom.asText()); //美妆个护
			
			List<?> list2 = page.getByXPath("//div[@class='s-title']/h3/b");
			HtmlBold subCateDom = (HtmlBold) list2.get(0);
			System.out.println(subCateDom.asText());  // 润肤
		   
			
			
			
		
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			webClient.close();
		}
		
		
	}
}
