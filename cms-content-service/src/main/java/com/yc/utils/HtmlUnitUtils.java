package com.yc.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.cn.yc.utils.GsonUtils;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HtmlUnitUtils {
	
	
	/**
	 * //HtmlImage(img) . HtmlDivision(div) , HtmlItalic(i) ,HtmlEmphasis(em)
	 * @param args
	 */
	public static void main(String[] args) {
		String link = "http://list.jd.com/list.html?cat=670,677,688";
		//https://list.jd.com/list.html?cat=670,686,690&page=2
		
		WebClient webClient=new WebClient();
		webClient.getOptions().setCssEnabled(false); 
		webClient.getOptions().setJavaScriptEnabled(false); 
		HtmlPage page; //这里是 htmlPage  
		try {
			page = webClient.getPage(link);
			//System.out.println("html:"+page.asXml());
			List<?> divLists= page.getByXPath("//ul[starts-with(@class,'gl-warp')]"     //  "//ul[@class='gl-warp clearfix ']"
					+ "/li[@class='gl-item']/div[1]");
			//System.out.println(divLists);
			
			for(int j = 0 ; j < divLists.size() ; j++){
				HtmlDivision div =  (HtmlDivision)divLists.get(j);
				String skuid = div.getAttribute("data-sku");
				if(StringUtils.isBlank(skuid)){
					List<?> specSkuid =  div.getByXPath("//div[@class='gl-i-wrap']/div[1]/div[2]/div[1]"); //特殊房间号
					 HtmlDivision specDiv = (HtmlDivision)(specSkuid.get(0));
					 //System.out.println(specDiv.asXml());
					 skuid = specDiv.getAttribute("data-sku");
				}
				genPic(skuid,webClient); // collect pic and productName
				genPrice(div,webClient,skuid); //collect price 
				genCommentAmount(div,webClient,skuid); //collect the commentAmount
			
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
	}
	
	
	//进店 pic 
	public static void genPic(String skuid,WebClient client){
		String TM_href = "http://item.jd.com/"+skuid+".html";
		HtmlPage page;
		try {
			page = client.getPage(TM_href);
			List<?> imgLists= page.getByXPath("//div[@id='preview']"   
					+ "/div[1]/img[1]");
			HtmlImage himg = (HtmlImage) imgLists.get(0);
			String imgDom =  himg.getAttribute("data-origin");
			if(imgDom.isEmpty()){
				imgDom = himg.getAttribute("src"); //特殊情况
			}
			System.out.println(imgDom);
			genproductionName(page); ////进店   获取商品名称 
			genShopName(page); //// 进店 获取  经销商店名称
			
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//jd 的 price 通过 jsonp  返回数据(所以我们要模拟 请求)
	public static void genPrice(HtmlDivision div,WebClient client,String skuid){
		String link = "https://p.3.cn/prices/mgets?skuIds=J_"+skuid+"%2C";
		String price = getDataByJsonp_Common(link,"p",client,"price");
		System.out.println(price);
	
	}
	
	
	/**
	 * 共通的方法，目的是来获取 jsonp 返回回来的信息 进行收集 
	 * @param currentLink  我们要模拟jsonp的所要发送的 link 连接
	 * @param keyword   关键词 ：即我们获取到 一个 list之后 需要具体的 获取 某个key
	 * @param client  上面传下来的 webClient
	 * @param kind   由于 jsonp 返回的数据格式是多饰多样的 所以我们通过 kind 获取 具体是要获取那种数据 {比如: price 或 commentAmount }
	 * @return
	 */
	public static String  getDataByJsonp_Common(String currentLink,String keyword,WebClient client,String kind){
		String returnValue = "";
		Page page; //这里是 Page（爬json数据的）
		try {
			page = client.getPage(currentLink);
			WebResponse response = page.getWebResponse();
			if (response.getContentType().equals("application/json") ||  response.getContentType().equals("text/html")) {
				//获取json
				String json = response.getContentAsString();
				
				
				if("price".equals(kind)){
					//获取商品价格   [{"id":"J_11617676063","p":"49.00","m":"49.00","op":"49.00"}]
					 List list =  GsonUtils.parseDataToList(json);
					 for(int i = 0 ; i < list.size() ; i++){
						 if(list.get(i) instanceof Map){
							 Map m = (Map) list.get(i);
							 returnValue = m.get(keyword).toString();
							 break;
						 }
					 }
				}
				if("commentAmount".equals(kind)){
					
					/*Gson 将其text/html 文本进行 解析 成  
					 * {CommentsCount=
					 * [
					 * {SkuId=582599.0,
					 * ProductId=582599.0, 
					 * ShowCount=5500.0, 
					 * ShowCountStr=5500+, 
					 * CommentCountStr=9.3万+, 
					 * PoorRateStyle=2.0}
					 * ]}*/
					
					
					Map comeentDescMap = GsonUtils.parseDatatoMap(json,"list"); //先转换成 map 形式  (如上格式)
					
					
					Iterator entries =  comeentDescMap.entrySet().iterator();  //遍历map 获取value  (List格式)
					  
					while (entries.hasNext()) {  
					  
					    Map.Entry entry = (Map.Entry) entries.next();  

					    // String key = String.valueOf(entry.getKey()); 
					    //System.out.println(entry.getValue().getClass());  //ArrayList  （java.util.ArrayList）
					    List li = (List) entry.getValue();
					    //System.out.println(li.get(0).getClass().getName());  //com.google.gson.internal.LinkedTreeMap
					    //一般我们gson 解开获取到的 {} 对象的 类型 就是为 LinkedTreeMap;
					    Map commentStrMap = (Map)li.get(0);
					    System.out.println(commentStrMap.get(keyword).toString());
					    
					    
					}  
					 
				}
				return returnValue;
			}
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnValue;
	}
	

	public static void genproductionName(HtmlPage page){
		List<?> nameLists= page.getByXPath("//div[@class='sku-name']");
		HtmlDivision nameDom = (HtmlDivision) nameLists.get(0);
		System.out.println(nameDom.asText());
	}
	
	
	
	public static void genShopName(HtmlPage page){
			
			List<?> shopNameLists= page.getByXPath("//div[@class='aside']/div[1]/div[1]/div[@class='mt']/h3[1]/a[1]");
			if(shopNameLists.size()>0){
				HtmlAnchor shopNameDom = (HtmlAnchor) shopNameLists.get(0);
				System.out.println(shopNameDom.getAttribute("title"));
			}else{
				System.out.println("nothing!");
			}
	}
	
	/**
	 * jsonp 返回 评论总数
	 * @param div
	 * @param client
	 * @param skuid
	 */
	public static void genCommentAmount(HtmlDivision div,WebClient client,String skuid){
		
			String link = "https://club.jd.com/comment/productCommentSummaries.action?referenceIds="+skuid;
			String commentStr_amount = getDataByJsonp_Common(link,"CommentCountStr",client,"commentAmount");
			System.out.println(commentStr_amount);
	}
	
	
	

	
}
