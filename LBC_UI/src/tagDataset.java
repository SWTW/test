import java.util.HashMap;
import java.util.Vector;

public class tagDataset {
private Vector<newsItem> newsList = new Vector();
private HashMap Dataset = new HashMap(); 

//˵���������Ǹ������� ��vector�����һ�ε����������ǩ���õ���Ӧ��ֱ��ͼ
public tagDataset(Vector<newsItem> allnews,String tag){
	//��ʼ�����ݼ���ϣ��
	Dataset.put("2012",0);
	
	//tagΪ���һ�ε��ѡ�е�������ǩ,allnewsΪ��������
	
 //����һ����������
	for(newsItem anews: allnews){
		if(anews.getIsDelete()==true || anews.getTheTags().equals(""))
			continue;
		else{
//��ӵ�иñ�ǩ�����⣩�����ŷ���һ��newsList��
			if(anews.getTheTags().contains(tag+" ")){
				newsList.add(anews);
			}
		}
	}
}
public HashMap getDataset(){
	Vector<newsItem> NewsList = this.getNewsList();
	
	for(newsItem anews:NewsList){ //�����Ż�Ϊĳ���м��������ݼ�
		String year = anews.getTheDate().substring(0, 4);
		if(Dataset.containsKey(year)){
			Dataset.put(year, (int)Dataset.get(year)+1);  //������������������ţ���������һ
		}
		else{
			Dataset.put(year, 1);
		}
	}
	if((int)Dataset.get("2012")==0){ //����ʼ����ݲ��������ţ���ɾ������ֹ������
		Dataset.remove("2012");
	}
	return this.Dataset;
}

public Vector<newsItem> getNewsList(){
	return this.newsList;
}

}
