package jsoup;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.io.BufferedReader;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileOutputStream;

import java.io.FileWriter;
import javax.xml.ws.Response;
/** dom jiexi **/

public class javaRxm {
	
	public static void main(String args[]){
		javaRxm newslist = new javaRxm();
		Vector<newsItem> newslis = newslist.returnvector();
		for(newsItem b :newslis){
				if(b.theEncodeContent==null&&b.theTrueUrl==null){continue;}
				System.out.println(
						"========================================================================================================================");
				System.out.println("标题: " + b.theTitle);
				System.out.println("日期: " + b.theDate);
				System.out.println("出版社: " + b.theLocation);
				System.out.println("链接: " + b.theUrl);
				System.out.println("新闻类型及作者: " + b.theType);
				System.out.println("字数: " + b.theWordcount);
				System.out.println("ID: " + b.theId);
				System.out.println("标签： " + b.theTags);
				System.out.println("EncodedCotent： " + b.theEncodeContent);
				System.out.println("链接（正文出处）" + b.theTrueUrl);
				System.out.println("正文:"+b.theBodyContent);
			   }
		   
		}
		       //读第n+1条新闻的标题
	
	
	
	//读取并录入新闻，返回一个容器。
	public Vector returnvector() {
		Vector<newsItem> NewsList = new Vector<newsItem>(); // 

		// 解析器工厂
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	
		try {
			// 解析器对象
			DocumentBuilder db = dbf.newDocumentBuilder();
			// 解析
			Document[] document= {db.parse("guangming.xml"),db.parse("nanfangdaily.xml"),db.parse("sichuan.xml")};
			//以newsdata为单位查找 新闻
		//	NodeList newsList = null;
		//	System.out.println("总共有"+newsList.getLength()+"条新闻");

			// 遍历新闻
       for(int dindex=2;dindex<3;dindex++){
    	   NodeList newsList=document[dindex].getElementsByTagName("NewsData");
			for (int i = 0; i <newsList.getLength(); i++) {

				Element element = (Element) newsList.item(i); // element
				newsItem anews = new newsItem();// �每次都new一下，否则使用add(anews)时会指向相同内容
												
				if (element.getElementsByTagName("TrueUrl").item(0).getFirstChild() != null) {
					//	if( element.getElementsByTagName("TrueUrl").item(0).getFirstChild().getNodeValue()==null){continue;}//为什么这条信息不起作用
						anews.theTrueUrl = element.getElementsByTagName("TrueUrl").item(0).getFirstChild().getNodeValue();
						
					}
			//	else{continue;}
				// 一个一个匹配
				if (element.getElementsByTagName("Title").item(0).getFirstChild() != null) {
					anews.theTitle = element.getElementsByTagName("Title").item(0).getFirstChild().getNodeValue();
				}
				
				if (element.getElementsByTagName("Date").item(0).getFirstChild() != null) {
					anews.theDate = element.getElementsByTagName("Date").item(0).getFirstChild().getNodeValue();
				}
				
				if (element.getElementsByTagName("Location").item(0).getFirstChild() != null) {
					anews.theLocation = element.getElementsByTagName("Location").item(0).getFirstChild().getNodeValue();
				}
				
				if (element.getElementsByTagName("Url").item(0).getFirstChild() != null) {
					anews.theUrl = element.getElementsByTagName("Url").item(0).getFirstChild().getNodeValue();
				}
				
				
				if (element.getElementsByTagName("Type").item(0).getFirstChild() != null) {
					anews.theType = element.getElementsByTagName("Type").item(0).getFirstChild().getNodeValue();
				}
				
				if (element.getElementsByTagName("WordCount").item(0).getFirstChild() != null) {
				   String teststring =element.getElementsByTagName("WordCount").item(0).getFirstChild().getNodeValue();
				   javaRxm textobject = new javaRxm();
				   boolean testresult = textobject.isNumeric(teststring);                    //这里用了一个正则表达对wordcout是否是个纯字符串进行了判断。因为sichuan.xml存在非纯字符串的，有的带“-”
				   if(testresult){
					   {anews.theWordcount = Integer
							.parseInt(element.getElementsByTagName("WordCount").item(0).getFirstChild().getNodeValue()); // 将字符串转化成了数字
					   }
				}
				   
				if (element.getElementsByTagName("ID").item(0).getFirstChild() != null) {
					anews.theId = element.getElementsByTagName("ID").item(0).getFirstChild().getNodeValue();
				}
				
				if (element.getElementsByTagName("Tags").item(0).getFirstChild() != null) {
					// 进行非空检查，否则会出现空指针异常
					anews.theTags = element.getElementsByTagName("Tags").item(0).getFirstChild().getNodeValue();
				}
				
				if (element.getElementsByTagName("EncodedContent").item(0).getFirstChild() != null) {
					
					anews.theEncodeContent = element.getElementsByTagName("EncodedContent").item(0).getFirstChild()
							.getNodeValue();
				} 
				
				
				//读取encodecontent内容
		    if(anews.theEncodeContent!=null){
		    	Encodecontent jiemi = new Encodecontent();
		    	String result = jiemi.reContent(anews.theEncodeContent);
		    	if(result!=null)
		    		anews.theBodyContent=result;
		    	
		    }    

				//调用javaUrl 将网页正文赋予theBodyContent;目前只能读取trueurl给的链接。利用encodecontent要那解密，不会。
		    else  if(anews.theTrueUrl!=null&&anews.theTrueUrl.equals("")){
		    	 NewsUpdater newone = new NewsUpdater();
					if(newone.connect(anews.theTrueUrl)){
						anews.theBodyContent=newone.getContent();
					}
				   } 
		     
			   
			
				NewsList.add(anews);// 加入新闻列表
                
				}
			}
		}
	//		System.out.println("总共有"+NewsList.size()+"条新闻");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	
		return NewsList;
	}
	
	//用正则表达式判断数字
	public boolean isNumeric(String str){ 
		   Pattern pattern = Pattern.compile("[0-9]*"); 
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
		}

	
}

// 创建新闻类
class newsItem {
	public String theTitle;
	public String theDate;
	public String theLocation;
	public String theUrl;
	public String theTrueUrl;
	public String theType;
	public int theWordcount;
	public String theId;
	public String theTags;
	public String theEncodeContent;
    public String theBodyContent;
    String getTheBodyContent(){
    	return this.theBodyContent;
    }
    void setTheBodyContent(String thebodycontent) {
		this.theBodyContent = thebodycontent;
	}
	String getTheTitle() {
		return this.theTitle;
	}

	String getTheDate() {
		return this.theDate;
	}

	String getTheLocation() {
		return this.theLocation;
	}

	String getTheUrl() {
		return this.theUrl;
	}

	String getTheTrueUrl() {
		return this.theTrueUrl;
	}

	String getTheType() {
		return this.theType;
	}

	int getTheWordcount() {
		return this.theWordcount;
	}

	String getTheId() {
		return this.theId;
	}

	String getTheTags() {
		return this.theTags;
	}

	String getTheEncodeContent() {
		return this.theEncodeContent;
	}

	void setTheTitle(String thetitle) {
		this.theTitle = thetitle;
	}

	void setTheDate(String thedate) {
		this.theDate = thedate;
	}

	void setTheLocation(String thelocation) {
		this.theLocation = thelocation;
	}

	void setTheUrl(String theurl) {
		this.theUrl = theurl;
	}

	void setTheTureUrl(String thetrueurl) {
		this.theTrueUrl = thetrueurl;
	}

	void setTheType(String thetype) {
		this.theType = thetype;
	}

	void setTheWordcount(int thewordcount) {
		this.theWordcount = thewordcount;
	}

	void setTheId(String theid) {
		this.theId = theid;
	}

	void setTheTags(String thetags) {
		this.theTags = thetags;
	}

	void setTheEncodeContent(String theencodecontent) {
		this.theEncodeContent = theencodecontent;
	}
}


