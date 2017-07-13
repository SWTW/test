import java.util.HashMap;
import java.util.Vector;

public class TagGroupDataset {
	String[] thetag;
	Vector<newsItem> allnews = new Vector<newsItem>();
	/*public TagGroupDataset(Vector<newsItem> allnews,String[] thetag){ //传入所有新闻和标签
	    this.setthetag(thetag);
	    this.setallnews(allnews);
	}*/
    String tags;
	
	
	public HashMap getDataset(){
	    thetag = tags.split(" ");
		HashMap taggroupdataset = new HashMap();
		for(String atag : thetag){
			tagDataset atagdataset = new tagDataset(this.getallnews(),atag);
			HashMap tagdataset = atagdataset.getDataset();
			taggroupdataset.put(atag, tagdataset);
		}
		return taggroupdataset;
	}
	public Vector<newsItem> getallnews(){
		return this.allnews;
	}
	public void setallnews(Vector<newsItem> news){
		this.allnews=news;
	}
}
