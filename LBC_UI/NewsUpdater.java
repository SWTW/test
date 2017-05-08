package jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class NewsUpdater {
	private Document doc;
	
	public boolean connect(String url){
		try {
			doc = Jsoup.connect(url).get();
		} 
		catch (IOException e) {
			return false;
		}
		
		return true;
	}
	
	public String getContent(){
		String str=doc.html().replace("&nbsp;","$").replace("<strong>", "*").replace("</strong>", "");
		//替换成"$"的原因是多个空格解析时会被处理成一个空格
		doc=Jsoup.parse(str);
		
		Element content = null;
		
		if(doc.getElementById("articleContent") != null){
			content=doc.getElementById("articleContent");
			
		}
		else{
			content=doc.getElementById("page_a");
		}
		
		String text=content.text().replace("$", " ").replace("     ","\n    ").replace("    *","")+"\n";
		
		return text;
	}
}
