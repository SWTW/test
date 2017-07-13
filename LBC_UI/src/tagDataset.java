import java.util.HashMap;
import java.util.Vector;

public class tagDataset {
private Vector<newsItem> newsList = new Vector();
private HashMap Dataset = new HashMap(); 

//说明，输入那个存新闻 的vector和最后一次点击的主题或标签，得到相应的直方图
public tagDataset(Vector<newsItem> allnews,String tag){
	//初始化数据集哈希表
	Dataset.put("2012",0);
	
	//tag为最后一次点击选中的主题或标签,allnews为所有新闻
	
 //遍历一下所有新闻
	for(newsItem anews: allnews){
		if(anews.getIsDelete()==true || anews.getTheTags().equals(""))
			continue;
		else{
//将拥有该标签（主题）的新闻放入一个newsList；
			if(anews.getTheTags().contains(tag+" ")){
				newsList.add(anews);
			}
		}
	}
}
public HashMap getDataset(){
	Vector<newsItem> NewsList = this.getNewsList();
	
	for(newsItem anews:NewsList){ //将新闻化为某年有几条的数据集
		String year = anews.getTheDate().substring(0, 4);
		if(Dataset.containsKey(year)){
			Dataset.put(year, (int)Dataset.get(year)+1);  //若该年份已有其它新闻，则数量加一
		}
		else{
			Dataset.put(year, 1);
		}
	}
	if((int)Dataset.get("2012")==0){ //若初始化年份不存在新闻，则删除，防止被绘制
		Dataset.remove("2012");
	}
	return this.Dataset;
}

public Vector<newsItem> getNewsList(){
	return this.newsList;
}

}
