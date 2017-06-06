package com.yc.temp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * 爬虫 模拟下一页/上一页点击
 * 返回 下一页的 url地址
 * @author yuanchen
 *
 */
public class nextPageTest {

	public static void main(String[] args) {

		
		List<String> urlLists = new ArrayList<String>();
		String link = "https://list.jd.com/list.html?cat=670,686,690";
		
		int len =10 ;
		WebClient webClient=new WebClient();
		webClient.getOptions().setCssEnabled(false); 
		webClient.getOptions().setJavaScriptEnabled(false); 
		HtmlPage page = null ; //这里是 htmlPage 
		urlLists.add(link);
		try {
			for(int i = 0 ; i < len ; i++){
				page = webClient.getPage(link);
				//System.out.println(page.asXml());
			    List<?> list = page.getByXPath("//div[starts-with(@class,'page')]");
			    HtmlDivision pageBtnDom = (HtmlDivision)list.get(0);
				//System.out.println(pageBtnDom.asXml());
				List<?> l = pageBtnDom.getByXPath("//a[starts-with(@class,'pn-next')]");
				HtmlAnchor pageBtnPrevAnchor = (HtmlAnchor)l.get(0);
				if((pageBtnPrevAnchor.getAttribute("class")).indexOf("disabled") != -1){
					System.out.println("这是最后一页");
				}else{
					//System.out.println("not end page!");
					Page nextpage = pageBtnPrevAnchor.click();
					//System.out.println(nextpage.getUrl().toString()); //获取url 
					link = nextpage.getUrl().toString();
					//System.out.println(nextpage); //HtmlPage(https://list.jd.com/list.html?cat=670,686,690&page=2&sort=sort_totalsales15_desc&trans=1&JL=6_0_0)@1831177243
					urlLists.add(nextpage.getUrl().toString());
				}
			}
			
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			 webClient.close();
		}
		
		System.out.println(urlLists);
		
	}

}
