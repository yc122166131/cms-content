package com.yc.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class aa {

	public static void main(String[] args) {

		
		String link = "https://list.jd.com/list.html?cat=670,686,690";
		
		WebClient webClient=new WebClient();
		webClient.getOptions().setCssEnabled(false); 
		webClient.getOptions().setJavaScriptEnabled(false); 
		HtmlPage page = null ; //这里是 htmlPage 
		try {
			page = webClient.getPage(link);
			System.out.println(page.asXml());
		/*	List<?> list = page.getByXPath("//div[starts-with(@class,'path')]");
			System.out.println(list.get(0));*/
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
