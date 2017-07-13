import java.util.HashMap;
import java.util.Vector;

import org.jfree.data.xml.DatasetTags;

public class ThemeDataset {
private Vector<newsItem> newsList = new Vector();
private HashMap Dataset = new HashMap(); 
private String theTheme;
//说明，输入主题，得到相应数据集
public ThemeDataset(Vector<newsItem> allnews,String theme){
	//初始化数据集哈希表
	Dataset.put("2012",0);
	this.theTheme = theme;
	//tag为最后一次点击选中的主题或标签,allnews为所有新闻
	
 //遍历一下所有新闻
	for(newsItem anews: allnews){
		if(anews.getIsDelete()==true || anews.getTheTags().equals(""))
			continue;
		else{
//将拥有该标签（主题）的新闻放入一个newsList；
			if(anews.theTheme.contains(theme+" ")){
				newsList.add(anews);
			}
		}
	}
}
public HashMap getDataset(){
	Vector<newsItem> NewsList = this.getNewsList();
	
	if(this.gettheTheme().equals("报纸类")){
		Dataset.put("中央党报", 0);
		Dataset.put("省委机关报", 0);
		Dataset.put("都市类党报", 0);
		for(newsItem anews:NewsList){ 
			
			if(anews.theTags.contains("中央党报")){
				Dataset.put("中央党报", (int)Dataset.get("中央党报")+1);  //若该年份已有其它新闻，则数量加一
				System.out.println("############################");
			}
			
			if(anews.getTheTags().contains("省委机关报")){
				Dataset.put("省委机关报", (int)Dataset.get("省委机关报")+1);  //若该年份已有其它新闻，则数量加一
			}
			
			if(anews.getTheTags().contains("都市类党报")){
				Dataset.put("都市类党报", (int)Dataset.get("都市类党报")+1);  //若该年份已有其它新闻，则数量加一
			}
			
		}
        
	    
	}
	
	else if(this.gettheTheme().equals("新闻类")){
		Dataset.put("纯净新闻", 0);
		Dataset.put("特稿与特写", 0);
		Dataset.put("评论", 0);
		Dataset.put("其他", 0);
        for(newsItem anews:NewsList){ 
			
			if(anews.getTheTags().contains("纯净新闻")){
				Dataset.put("纯净新闻", (int)Dataset.get("纯净新闻")+1);  //若该年份已有其它新闻，则数量加一
			}
			

			if(anews.getTheTags().contains("特稿与特写")){
				Dataset.put("特稿与特写", (int)Dataset.get("特稿与特写")+1);  //若该年份已有其它新闻，则数量加一
			}

			if(anews.getTheTags().contains("评论")){
				Dataset.put("评论", (int)Dataset.get("评论")+1);  //若该年份已有其它新闻，则数量加一
			}

			if(anews.getTheTags().contains("其他")){
				Dataset.put("其他", (int)Dataset.get("其他")+1);  //若该年份已有其它新闻，则数量加一
			}
			
        }
	    
	   
	 }
	
	else if(this.gettheTheme().equals("性别")){
		Dataset.put("男", 0);
		Dataset.put("女", 0);
        for(newsItem anews:NewsList){ //将新闻化为某年有几条的数据集
			
			if(anews.getTheTags().contains("男")){
				Dataset.put("男", (int)Dataset.get("男")+1);  //若该年份已有其它新闻，则数量加一
			}
			
			if(anews.getTheTags().contains("女")){
				Dataset.put("女", (int)Dataset.get("女")+1);  //若该年份已有其它新闻，则数量加一
			}
        }
	    
	   
	 }
	
	else if(this.gettheTheme().equals("新闻报道信息来源")){
		Dataset.put("社会各界帮助关爱", 0);
		Dataset.put("社会对留守儿童现象的建议和看法", 0);
		Dataset.put("表彰帮助关爱留守儿童的单位和个人", 0);
		Dataset.put("留守儿童遭受暴力（排除性侵）", 0);
		Dataset.put("留守儿童被性侵", 0);
		Dataset.put("留守儿童的犯罪", 0);
		Dataset.put("留守儿童的以外死亡", 0);
		Dataset.put("留守儿童的努力上进", 0);
		Dataset.put("打工父母在城市的艰难生活", 0);
		Dataset.put("其他", 0);
		
        for(newsItem anews:NewsList){ //将新闻化为某年有几条的数据集
			
			if(anews.getTheTags().contains("社会各界帮助关爱")){
				Dataset.put("社会各界帮助关爱", (int)Dataset.get("社会各界帮助关爱")+1);  //若该年份已有其它新闻，则数量加一
			}
			
			if(anews.getTheTags().contains("社会对留守儿童现象的建议和看法")){
				Dataset.put("社会对留守儿童现象的建议和看法", (int)Dataset.get("社会对留守儿童现象的建议和看法")+1);  //若该年份已有其它新闻，则数量加一
			}
			if(anews.getTheTags().contains("表彰帮助关爱留守儿童的单位和个人")){
				Dataset.put("表彰帮助关爱留守儿童的单位和个人", (int)Dataset.get("表彰帮助关爱留守儿童的单位和个人")+1);  //若该年份已有其它新闻，则数量加一
			}
			if(anews.getTheTags().contains("留守儿童遭受暴力（排除性侵）")){
				Dataset.put("留守儿童遭受暴力（排除性侵）", (int)Dataset.get("留守儿童遭受暴力（排除性侵）")+1);  //若该年份已有其它新闻，则数量加一
			}
			if(anews.getTheTags().contains("留守儿童被性侵")){
				Dataset.put("留守儿童被性侵", (int)Dataset.get("留守儿童被性侵")+1);  //若该年份已有其它新闻，则数量加一
			}
			if(anews.getTheTags().contains("留守儿童的犯罪")){
				Dataset.put("留守儿童的犯罪", (int)Dataset.get("留守儿童的犯罪")+1);  //若该年份已有其它新闻，则数量加一
			}
			if(anews.getTheTags().contains("留守儿童的以外死亡")){
				Dataset.put("留守儿童的以外死亡", (int)Dataset.get("留守儿童的以外死亡")+1);  //若该年份已有其它新闻，则数量加一
			}
			if(anews.getTheTags().contains("留守儿童的努力上进")){
				Dataset.put("留守儿童的努力上进", (int)Dataset.get("留守儿童的努力上进")+1);  //若该年份已有其它新闻，则数量加一
			}
			if(anews.getTheTags().contains("打工父母在城市的艰难生活")){
				Dataset.put("打工父母在城市的艰难生活", (int)Dataset.get("打工父母在城市的艰难生活")+1);  //若该年份已有其它新闻，则数量加一
			}
			if(anews.getTheTags().contains("其他")){
				Dataset.put("其他", (int)Dataset.get("其他")+1);  //若该年份已有其它新闻，则数量加一
			}
			
			
        }
	   
	   
	 }
	
	else if(this.gettheTheme().equals("新闻报道来源")){
		Dataset.put("记者", 0);
		Dataset.put("政府", 0);
		Dataset.put("企业", 0);
		Dataset.put("事业单位", 0);
		Dataset.put("公益团体", 0);
		Dataset.put("专家学者（没有官职）", 0);
		Dataset.put("政府领导、政协或人大代表", 0);
		Dataset.put("其他", 0);
        for(newsItem anews:NewsList){ //将新闻化为某年有几条的数据集
			
			if(anews.getTheTags().contains("记者")){
				Dataset.put("记者", (int)Dataset.get("记者")+1);  //若该年份已有其它新闻，则数量加一
			}
			
			
			if(anews.getTheTags().contains("政府")){
				Dataset.put("政府", (int)Dataset.get("政府")+1);  //若该年份已有其它新闻，则数量加一
			}
			
			if(anews.getTheTags().contains("企业")){
				Dataset.put("企业", (int)Dataset.get("企业")+1);  //若该年份已有其它新闻，则数量加一
			}
			
			if(anews.getTheTags().contains("事业单位")){
				Dataset.put("事业单位", (int)Dataset.get("事业单位")+1);  //若该年份已有其它新闻，则数量加一
			}
			
			if(anews.getTheTags().contains("公益团体")){
				Dataset.put("公益团体", (int)Dataset.get("公益团体")+1);  //若该年份已有其它新闻，则数量加一
			}
			
			if(anews.getTheTags().contains("专家学者（没有官职）")){
				Dataset.put("专家学者（没有官职）", (int)Dataset.get("专家学者（没有官职）")+1);  //若该年份已有其它新闻，则数量加一
			}
			
			if(anews.getTheTags().contains("政府领导、政协或人大代表")){
				Dataset.put("政府领导、政协或人大代表", (int)Dataset.get("政府领导、政协或人大代表")+1);  //若该年份已有其它新闻，则数量加一
			}
			
			if(anews.getTheTags().contains("其他")){
				Dataset.put("其他", (int)Dataset.get("其他")+1);  //若该年份已有其它新闻，则数量加一
			}
			
        }
	    
	    
	 }
	
	else if(this.gettheTheme().equals("媒介形象呈现")){
		Dataset.put("积极健康", 0);
		Dataset.put("可怜悲惨", 0);
		Dataset.put("沐恩幸福", 0);
		Dataset.put("问题儿童", 0);
		Dataset.put("其他", 0);
		
        for(newsItem anews:NewsList){ //将新闻化为某年有几条的数据集
			
			if(anews.getTheTags().contains("积极健康")){
				Dataset.put("积极健康", (int)Dataset.get("积极健康")+1);  //若该年份已有其它新闻，则数量加一
			}
			

			if(anews.getTheTags().contains("可怜悲惨")){
				Dataset.put("可怜悲惨", (int)Dataset.get("可怜悲惨")+1);  //若该年份已有其它新闻，则数量加一
			}

			if(anews.getTheTags().contains("沐恩幸福")){
				Dataset.put("沐恩幸福", (int)Dataset.get("沐恩幸福")+1);  //若该年份已有其它新闻，则数量加一
			}

			if(anews.getTheTags().contains("问题儿童")){
				Dataset.put("问题儿童", (int)Dataset.get("问题儿童")+1);  //若该年份已有其它新闻，则数量加一
			}

			if(anews.getTheTags().contains("其他")){
				Dataset.put("其他", (int)Dataset.get("其他")+1);  //若该年份已有其它新闻，则数量加一
			}
        }
	    
	   
	 }
	
	else if(this.gettheTheme().equals("帮助新闻的具体种类")){
		Dataset.put("单纯一次捐款捐物", 0);
		Dataset.put("旅游活动安排的项目", 0);
		Dataset.put("免费开放", 0);
		Dataset.put("设立长期资助项目", 0);
		Dataset.put("其他", 0);
        for(newsItem anews:NewsList){ //将新闻化为某年有几条的数据集
			
			if(anews.getTheTags().contains("单纯一次捐款捐物")){
				Dataset.put("单纯一次捐款捐物", (int)Dataset.get("单纯一次捐款捐物")+1);  //若该年份已有其它新闻，则数量加一
			}
			
			if(anews.getTheTags().contains("旅游活动安排的项目")){
				Dataset.put("旅游活动安排的项目", (int)Dataset.get("旅游活动安排的项目")+1);  //若该年份已有其它新闻，则数量加一
			}
			if(anews.getTheTags().contains("免费开放")){
				Dataset.put("免费开放", (int)Dataset.get("免费开放")+1);  //若该年份已有其它新闻，则数量加一
			}
			if(anews.getTheTags().contains("设立长期资助项目")){
				Dataset.put("设立长期资助项目", (int)Dataset.get("设立长期资助项目")+1);  //若该年份已有其它新闻，则数量加一
			}
			if(anews.getTheTags().contains("其他")){
				Dataset.put("其他", (int)Dataset.get("其他")+1);  //若该年份已有其它新闻，则数量加一
			}
        }
	    
	   
	 }
	
	else if(this.gettheTheme().equals("帮助类新闻的主体")){
		Dataset.put("政府部门", 0);
		Dataset.put("企业", 0);
		Dataset.put("免费开放", 0);
		Dataset.put("事业单位", 0);
		Dataset.put("个人", 0);
        for(newsItem anews:NewsList){ //将新闻化为某年有几条的数据集
			
			if(anews.getTheTags().contains("政府部门")){
				Dataset.put("政府部门", (int)Dataset.get("政府部门")+1);  //若该年份已有其它新闻，则数量加一
			}
			if(anews.getTheTags().contains("企业")){
				Dataset.put("企业", (int)Dataset.get("企业")+1);  //若该年份已有其它新闻，则数量加一
			}
			if(anews.getTheTags().contains("免费开放")){
				Dataset.put("免费开放", (int)Dataset.get("免费开放")+1);  //若该年份已有其它新闻，则数量加一
			}
			if(anews.getTheTags().contains("事业单位")){
				Dataset.put("事业单位", (int)Dataset.get("事业单位")+1);  //若该年份已有其它新闻，则数量加一
			}
			if(anews.getTheTags().contains("个人")){
				Dataset.put("个人", (int)Dataset.get("个人")+1);  //若该年份已有其它新闻，则数量加一
			}
			
			
        }
	   
	    
	 }
	
	else if(this.gettheTheme().equals("农民工子女不能留在城市读书的原因")){
		Dataset.put("无本地户籍难入公立学校", 0);
		Dataset.put("私立学校学费高", 0);
		Dataset.put("私立学校教学质量没保障", 0);
		Dataset.put("小型私立学校逐渐被国家取消办学资格", 0);
		Dataset.put("其他", 0);
		
        for(newsItem anews:NewsList){ //将新闻化为某年有几条的数据集
			
			if(anews.getTheTags().contains("无本地户籍难入公立学校")){
				Dataset.put("无本地户籍难入公立学校", (int)Dataset.get("无本地户籍难入公立学校")+1);  //若该年份已有其它新闻，则数量加一
			}
			
			if(anews.getTheTags().contains("私立学校学费高")){
				Dataset.put("私立学校学费高", (int)Dataset.get("私立学校学费高")+1);  //若该年份已有其它新闻，则数量加一
			}
			if(anews.getTheTags().contains("私立学校教学质量没保障")){
				Dataset.put("私立学校教学质量没保障", (int)Dataset.get("私立学校教学质量没保障")+1);  //若该年份已有其它新闻，则数量加一
			}
			if(anews.getTheTags().contains("小型私立学校逐渐被国家取消办学资格")){
				Dataset.put("小型私立学校逐渐被国家取消办学资格", (int)Dataset.get("小型私立学校逐渐被国家取消办学资格")+1);  //若该年份已有其它新闻，则数量加一
			}
			if(anews.getTheTags().contains("其他")){
				Dataset.put("其他", (int)Dataset.get("其他")+1);  //若该年份已有其它新闻，则数量加一
			}
			
        }
	   
	    
	 }
	
	
	else if(this.gettheTheme().equals("表彰奖励的新闻主题")){
		Dataset.put("政府部门", 0);
		Dataset.put("企业", 0);
		Dataset.put("免费开放", 0);
		Dataset.put("事业单位", 0);
		Dataset.put("个人", 0);
        for(newsItem anews:NewsList){ //将新闻化为某年有几条的数据集
			
			if(anews.getTheTags().contains("政府部门")){
				Dataset.put("政府部门", (int)Dataset.get("政府部门")+1);  //若该年份已有其它新闻，则数量加一
			}
			
			if(anews.getTheTags().contains("企业")){
				Dataset.put("企业", (int)Dataset.get("企业")+1);  //若该年份已有其它新闻，则数量加一
			}
			if(anews.getTheTags().contains("免费开放")){
				Dataset.put("免费开放", (int)Dataset.get("免费开放")+1);  //若该年份已有其它新闻，则数量加一
			}
			if(anews.getTheTags().contains("事业单位")){
				Dataset.put("事业单位", (int)Dataset.get("事业单位")+1);  //若该年份已有其它新闻，则数量加一
			}
			if(anews.getTheTags().contains("个人")){
				Dataset.put("个人", (int)Dataset.get("个人")+1);  //若该年份已有其它新闻，则数量加一
			}
			
        }
	    
	    
	 }
	Dataset.remove("2012");
	return this.Dataset;
}

public Vector<newsItem> getNewsList(){
	return this.newsList;
}
public String gettheTheme(){
	return this.theTheme;
}
}
