package com.yc.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
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
import com.gargoylesoftware.htmlunit.html.HtmlBold;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.yc.pojo.JDProduction;

/**
 * 正式版
 * @author yuanchen
 *
 */
public class HtmlUnitUtils {
	
	
	/**
	 * //HtmlImage(img) . HtmlDivision(div) , HtmlItalic(i) ,HtmlEmphasis(em),HtmlAnchor(a)
	 * HtmlBold("<b>")
	 * HtmlListItem(li)
	 * @param args
	 */
	public static void main(String[] args) {
		List<JDProduction> list  = jdDataGenerator("https://list.jd.com/list.html?cat=1315,1343,9708",1);
		System.out.println(list);
	}
	
	/**
	 * jd 数据采集 主入口
	 * @param beginLink  要爬取的起始url
	 * @param startNum   要爬取多少页
	 * @return
	 */
	public  static List<JDProduction> jdDataGenerator(String beginLink,int startNum){
		//int startNum = 1; //执行次数(即点多少次下一页)
		//String beginLink = "https://list.jd.com/list.html?cat=9855,9856,9900";  //开始执行起始link
		
		List<JDProduction> main_jdInfoList  = new ArrayList<JDProduction>();
		long startTime1 = System.currentTimeMillis();
		List<String>  urls = genURLList(startNum,beginLink);
		long endTime1 = System.currentTimeMillis();
		System.out.println((endTime1 - startTime1)/1000+"秒");
		
		System.out.println("=======================");
		long startTime = System.currentTimeMillis();
		
		String supCateName = genSupCateName(beginLink);//生成大类名称(只生成一遍)
		String subCateName = genSubCateName(beginLink);//生成小类名称(只生成一遍)
		
		for(int k = 0 ; k < urls.size() ; k++){
			List<JDProduction> li = handleEachPageInfo(urls.get(k),supCateName,subCateName);
			main_jdInfoList.addAll(li);
		}
		System.out.println(main_jdInfoList);
		long endTime = System.currentTimeMillis();
		System.out.println((endTime - startTime)/1000+"秒");
		return main_jdInfoList;
	}
	
	
	/**
	 * 生成大类名称
	 * @return
	 */
	private static String genSupCateName(String url) {
		
		String supCateName = "";
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
		    supCateName  =  supCateDom.asText();
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			webClient.close();
		}
		return supCateName;
	}

	
	
	/**
	 * 生成小类名称
	 * @return
	 */
	private static String genSubCateName(String url) {
		
		
		String subCateName = "";
		WebClient webClient=new WebClient();
		webClient.getOptions().setCssEnabled(false); 
		webClient.getOptions().setJavaScriptEnabled(false); 
		HtmlPage page = null ; //这里是 htmlPage 
		try {
			page = webClient.getPage(url);
			List<?> list2 = page.getByXPath("//div[@class='s-title']/h3/b");
			HtmlBold subCateDom = (HtmlBold) list2.get(0);
			subCateName = subCateDom.asText();  
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			webClient.close();
		}
		return subCateName;
		
		
	}

	
	
	
	/**
	 * 生成需要生成的 url 的 地址list,(即要爬取多少个页面)
	 * @param len  要爬取的页面总数
	 * @return
	 */
	private static List<String> genURLList(Integer len,String beginLink){
		
		List<String> urlLists = new ArrayList<String>();
		String link = beginLink;
		if(len  == null){
			len = 10;
		}
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
		
		return urlLists;
		
	}
	
	
	/**
	 * 执行 爬取 每一页的 数据
	 * @param link
	 * @return
	 */
	private static List<JDProduction> handleEachPageInfo(String link,String supCateName,String subCateName){
		List<JDProduction> jdcollectiontList  = new ArrayList<JDProduction>();
		
		WebClient webClient=new WebClient();
		webClient.getOptions().setCssEnabled(false); 
		webClient.getOptions().setJavaScriptEnabled(false); 
		HtmlPage page; //这里是 htmlPage  
		try {
			page = webClient.getPage(link);
			System.out.println(page);
			//System.out.println("html:"+page.asXml());
			List<?> divLists= page.getByXPath("//ul[starts-with(@class,'gl-warp')]"     //  "//ul[@class='gl-warp clearfix ']"
					+ "/li[@class='gl-item']/div[1]");
			//System.out.println(divLists);
			JDProduction jd = null;
			for(int j = 0 ; j < divLists.size() ; j++){
				jd = new JDProduction(supCateName,subCateName);
				
				HtmlDivision div =  (HtmlDivision)divLists.get(j);
				String skuid = div.getAttribute("data-sku");
				if(StringUtils.isBlank(skuid)){
					List<?> specSkuid =  div.getByXPath("//div[@class='gl-i-wrap']/div[1]/div[2]/div[1]"); //特殊房间号
					 HtmlDivision specDiv = (HtmlDivision)(specSkuid.get(0));
					 //System.out.println(specDiv.asXml());
					 skuid = specDiv.getAttribute("data-sku");
				}
				genPic(skuid,webClient,jd); // collect pic and productName and and genShopName and brandName
				genPrice(div,webClient,skuid,jd); //collect price 
				genCommentAmount(div,webClient,skuid,jd); //collect the commentAmount
				jdcollectiontList.add(jd);
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
		
		return jdcollectiontList;
		
	}
	
	
	//进店 获取图片信息 
	private static void genPic(String skuid,WebClient client,JDProduction JD){
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
			//System.out.println(imgDom);
			
			JD.setImgPath(imgDom); 
			genproductionName(page,JD); ////进店   获取商品名称 
			genShopName(page,JD); //// 进店 获取  经销商店名称
			genBrandName(page,JD);  //// 进店 获取  商品品牌名称
			
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 获取商品名称
	 * @param page
	 */
	private static void genproductionName(HtmlPage page,JDProduction JD){
		List<?> nameLists= page.getByXPath("//div[@class='sku-name']");
		HtmlDivision nameDom = (HtmlDivision) nameLists.get(0);
		System.out.println(nameDom.asText());

		JD.setProductName(nameDom.asText());
	}
	
	
	
	/**
	 * 获取商店名称
	 * @param page
	 */
	private static void genShopName(HtmlPage page,JDProduction JD){
			
			List<?> shopNameLists= page.getByXPath("//div[@class='aside']/div[1]/div[1]/div[@class='mt']/h3[1]/a[1]");
			if(shopNameLists.size()>0){
				HtmlAnchor shopNameDom = (HtmlAnchor) shopNameLists.get(0);
				System.out.println(shopNameDom.getAttribute("title"));
				
				JD.setCompanyName(shopNameDom.getAttribute("title"));
			}else{
				System.out.println("nothing!");
				JD.setCompanyName("");
			}
	}
	
	
	private static void genBrandName(HtmlPage page,JDProduction JD){
		
		try{
			List<?> brandNameList = page.getByXPath("//ul[@id='parameter-brand']/li[1]");
			HtmlListItem barandNameDom = (HtmlListItem) brandNameList.get(0);
			//System.out.println(page.getUrl().toString());
			//System.out.println(barandNameDom.getAttribute("title"));
			JD.setBrandName(barandNameDom.getAttribute("title"));
		}catch(Exception e){
			JD.setBrandName("");
		}
	}
	
	
	
	    //===================================jsonp====================================
	
		/**
		 * jsonp 返回 评论总数
		 * @param div
		 * @param client
		 * @param skuid
		 */
	private static void genCommentAmount(HtmlDivision div,WebClient client,String skuid,JDProduction JD){
				String link = "https://club.jd.com/comment/productCommentSummaries.action?referenceIds="+skuid;
				System.out.println(skuid);
				String commentStr_amount = "";
				try{
					commentStr_amount = getDataByJsonp_Common(link,"CommentCountStr",client,"commentAmount");
				}catch(Exception e){
					e.printStackTrace();
					System.out.println("==========================================================");
				}
				System.out.println(commentStr_amount);
				
				JD.setCommentNum(commentStr_amount);
		}
	
	
		//jd 的 price 通过 jsonp  返回数据(所以我们要模拟 请求)
	private static void genPrice(HtmlDivision div,WebClient client,String skuid,JDProduction JD){
			//String link = "https://p.3.cn/prices/mgets?skuIds=J_"+skuid+"%2C"; //old 
			String link = "https://p.3.cn/prices/mgets?pduid=33769083&skuIds=J_"+skuid+"%2C"; //0618
		
			String price = getDataByJsonp_Common(link,"p",client,"price");
			
			System.out.println(price);
			
			JD.setPrice(price);
		
		}
		
		
		/**
		 * 共通的方法，目的是来获取 jsonp 返回回来的信息 进行收集 
		 * @param currentLink  我们要模拟jsonp的所要发送的 link 连接
		 * @param keyword   关键词 ：即我们获取到 一个 list之后 需要具体的 获取 某个key
		 * @param client  上面传下来的 webClient
		 * @param kind   由于 jsonp 返回的数据格式是多饰多样的 所以我们通过 kind 获取 具体是要获取那种数据 {比如: price 或 commentAmount }
		 * @return
		 */
	private static String  getDataByJsonp_Common(String currentLink,String keyword,WebClient client,String kind){
			String returnValue = "";
			Page page; //这里是 Page（爬json数据的）
			try {
				page = client.getPage(currentLink);
				WebResponse response = page.getWebResponse();
				if (response.getContentType().equals("application/json") ||  response.getContentType().equals("text/html")) {
					//获取json
					String json = response.getContentAsString();
					
					/**
					 * 1. [返回的数据为 application/json 格式]
					 */
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
					
					
					
					/**
					 * 2. [返回的数据为 text/html 格式]
					 */
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
						    //System.out.println(commentStrMap.get(keyword).toString());
						    returnValue = commentStrMap.get(keyword).toString();
						    
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
	
	

	
}
