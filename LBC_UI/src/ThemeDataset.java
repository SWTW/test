import java.util.HashMap;
import java.util.Vector;

import org.jfree.data.xml.DatasetTags;

public class ThemeDataset {
private Vector<newsItem> newsList = new Vector();
private HashMap Dataset = new HashMap(); 
private String theTheme;
//˵�����������⣬�õ���Ӧ���ݼ�
public ThemeDataset(Vector<newsItem> allnews,String theme){
	//��ʼ�����ݼ���ϣ��
	Dataset.put("2012",0);
	this.theTheme = theme;
	//tagΪ���һ�ε��ѡ�е�������ǩ,allnewsΪ��������
	
 //����һ����������
	for(newsItem anews: allnews){
		if(anews.getIsDelete()==true || anews.getTheTags().equals(""))
			continue;
		else{
//��ӵ�иñ�ǩ�����⣩�����ŷ���һ��newsList��
			if(anews.theTheme.contains(theme+" ")){
				newsList.add(anews);
			}
		}
	}
}
public HashMap getDataset(){
	Vector<newsItem> NewsList = this.getNewsList();
	
	if(this.gettheTheme().equals("��ֽ��")){
		Dataset.put("���뵳��", 0);
		Dataset.put("ʡί���ر�", 0);
		Dataset.put("�����൳��", 0);
		for(newsItem anews:NewsList){ 
			
			if(anews.theTags.contains("���뵳��")){
				Dataset.put("���뵳��", (int)Dataset.get("���뵳��")+1);  //������������������ţ���������һ
				System.out.println("############################");
			}
			
			if(anews.getTheTags().contains("ʡί���ر�")){
				Dataset.put("ʡί���ر�", (int)Dataset.get("ʡί���ر�")+1);  //������������������ţ���������һ
			}
			
			if(anews.getTheTags().contains("�����൳��")){
				Dataset.put("�����൳��", (int)Dataset.get("�����൳��")+1);  //������������������ţ���������һ
			}
			
		}
        
	    
	}
	
	else if(this.gettheTheme().equals("������")){
		Dataset.put("��������", 0);
		Dataset.put("�ظ�����д", 0);
		Dataset.put("����", 0);
		Dataset.put("����", 0);
        for(newsItem anews:NewsList){ 
			
			if(anews.getTheTags().contains("��������")){
				Dataset.put("��������", (int)Dataset.get("��������")+1);  //������������������ţ���������һ
			}
			

			if(anews.getTheTags().contains("�ظ�����д")){
				Dataset.put("�ظ�����д", (int)Dataset.get("�ظ�����д")+1);  //������������������ţ���������һ
			}

			if(anews.getTheTags().contains("����")){
				Dataset.put("����", (int)Dataset.get("����")+1);  //������������������ţ���������һ
			}

			if(anews.getTheTags().contains("����")){
				Dataset.put("����", (int)Dataset.get("����")+1);  //������������������ţ���������һ
			}
			
        }
	    
	   
	 }
	
	else if(this.gettheTheme().equals("�Ա�")){
		Dataset.put("��", 0);
		Dataset.put("Ů", 0);
        for(newsItem anews:NewsList){ //�����Ż�Ϊĳ���м��������ݼ�
			
			if(anews.getTheTags().contains("��")){
				Dataset.put("��", (int)Dataset.get("��")+1);  //������������������ţ���������һ
			}
			
			if(anews.getTheTags().contains("Ů")){
				Dataset.put("Ů", (int)Dataset.get("Ů")+1);  //������������������ţ���������һ
			}
        }
	    
	   
	 }
	
	else if(this.gettheTheme().equals("���ű�����Ϣ��Դ")){
		Dataset.put("����������ذ�", 0);
		Dataset.put("�������ض�ͯ����Ľ���Ϳ���", 0);
		Dataset.put("���ð����ذ����ض�ͯ�ĵ�λ�͸���", 0);
		Dataset.put("���ض�ͯ���ܱ������ų����֣�", 0);
		Dataset.put("���ض�ͯ������", 0);
		Dataset.put("���ض�ͯ�ķ���", 0);
		Dataset.put("���ض�ͯ����������", 0);
		Dataset.put("���ض�ͯ��Ŭ���Ͻ�", 0);
		Dataset.put("�򹤸�ĸ�ڳ��еļ�������", 0);
		Dataset.put("����", 0);
		
        for(newsItem anews:NewsList){ //�����Ż�Ϊĳ���м��������ݼ�
			
			if(anews.getTheTags().contains("����������ذ�")){
				Dataset.put("����������ذ�", (int)Dataset.get("����������ذ�")+1);  //������������������ţ���������һ
			}
			
			if(anews.getTheTags().contains("�������ض�ͯ����Ľ���Ϳ���")){
				Dataset.put("�������ض�ͯ����Ľ���Ϳ���", (int)Dataset.get("�������ض�ͯ����Ľ���Ϳ���")+1);  //������������������ţ���������һ
			}
			if(anews.getTheTags().contains("���ð����ذ����ض�ͯ�ĵ�λ�͸���")){
				Dataset.put("���ð����ذ����ض�ͯ�ĵ�λ�͸���", (int)Dataset.get("���ð����ذ����ض�ͯ�ĵ�λ�͸���")+1);  //������������������ţ���������һ
			}
			if(anews.getTheTags().contains("���ض�ͯ���ܱ������ų����֣�")){
				Dataset.put("���ض�ͯ���ܱ������ų����֣�", (int)Dataset.get("���ض�ͯ���ܱ������ų����֣�")+1);  //������������������ţ���������һ
			}
			if(anews.getTheTags().contains("���ض�ͯ������")){
				Dataset.put("���ض�ͯ������", (int)Dataset.get("���ض�ͯ������")+1);  //������������������ţ���������һ
			}
			if(anews.getTheTags().contains("���ض�ͯ�ķ���")){
				Dataset.put("���ض�ͯ�ķ���", (int)Dataset.get("���ض�ͯ�ķ���")+1);  //������������������ţ���������һ
			}
			if(anews.getTheTags().contains("���ض�ͯ����������")){
				Dataset.put("���ض�ͯ����������", (int)Dataset.get("���ض�ͯ����������")+1);  //������������������ţ���������һ
			}
			if(anews.getTheTags().contains("���ض�ͯ��Ŭ���Ͻ�")){
				Dataset.put("���ض�ͯ��Ŭ���Ͻ�", (int)Dataset.get("���ض�ͯ��Ŭ���Ͻ�")+1);  //������������������ţ���������һ
			}
			if(anews.getTheTags().contains("�򹤸�ĸ�ڳ��еļ�������")){
				Dataset.put("�򹤸�ĸ�ڳ��еļ�������", (int)Dataset.get("�򹤸�ĸ�ڳ��еļ�������")+1);  //������������������ţ���������һ
			}
			if(anews.getTheTags().contains("����")){
				Dataset.put("����", (int)Dataset.get("����")+1);  //������������������ţ���������һ
			}
			
			
        }
	   
	   
	 }
	
	else if(this.gettheTheme().equals("���ű�����Դ")){
		Dataset.put("����", 0);
		Dataset.put("����", 0);
		Dataset.put("��ҵ", 0);
		Dataset.put("��ҵ��λ", 0);
		Dataset.put("��������", 0);
		Dataset.put("ר��ѧ�ߣ�û�й�ְ��", 0);
		Dataset.put("�����쵼����Э���˴����", 0);
		Dataset.put("����", 0);
        for(newsItem anews:NewsList){ //�����Ż�Ϊĳ���м��������ݼ�
			
			if(anews.getTheTags().contains("����")){
				Dataset.put("����", (int)Dataset.get("����")+1);  //������������������ţ���������һ
			}
			
			
			if(anews.getTheTags().contains("����")){
				Dataset.put("����", (int)Dataset.get("����")+1);  //������������������ţ���������һ
			}
			
			if(anews.getTheTags().contains("��ҵ")){
				Dataset.put("��ҵ", (int)Dataset.get("��ҵ")+1);  //������������������ţ���������һ
			}
			
			if(anews.getTheTags().contains("��ҵ��λ")){
				Dataset.put("��ҵ��λ", (int)Dataset.get("��ҵ��λ")+1);  //������������������ţ���������һ
			}
			
			if(anews.getTheTags().contains("��������")){
				Dataset.put("��������", (int)Dataset.get("��������")+1);  //������������������ţ���������һ
			}
			
			if(anews.getTheTags().contains("ר��ѧ�ߣ�û�й�ְ��")){
				Dataset.put("ר��ѧ�ߣ�û�й�ְ��", (int)Dataset.get("ר��ѧ�ߣ�û�й�ְ��")+1);  //������������������ţ���������һ
			}
			
			if(anews.getTheTags().contains("�����쵼����Э���˴����")){
				Dataset.put("�����쵼����Э���˴����", (int)Dataset.get("�����쵼����Э���˴����")+1);  //������������������ţ���������һ
			}
			
			if(anews.getTheTags().contains("����")){
				Dataset.put("����", (int)Dataset.get("����")+1);  //������������������ţ���������һ
			}
			
        }
	    
	    
	 }
	
	else if(this.gettheTheme().equals("ý���������")){
		Dataset.put("��������", 0);
		Dataset.put("��������", 0);
		Dataset.put("����Ҹ�", 0);
		Dataset.put("�����ͯ", 0);
		Dataset.put("����", 0);
		
        for(newsItem anews:NewsList){ //�����Ż�Ϊĳ���м��������ݼ�
			
			if(anews.getTheTags().contains("��������")){
				Dataset.put("��������", (int)Dataset.get("��������")+1);  //������������������ţ���������һ
			}
			

			if(anews.getTheTags().contains("��������")){
				Dataset.put("��������", (int)Dataset.get("��������")+1);  //������������������ţ���������һ
			}

			if(anews.getTheTags().contains("����Ҹ�")){
				Dataset.put("����Ҹ�", (int)Dataset.get("����Ҹ�")+1);  //������������������ţ���������һ
			}

			if(anews.getTheTags().contains("�����ͯ")){
				Dataset.put("�����ͯ", (int)Dataset.get("�����ͯ")+1);  //������������������ţ���������һ
			}

			if(anews.getTheTags().contains("����")){
				Dataset.put("����", (int)Dataset.get("����")+1);  //������������������ţ���������һ
			}
        }
	    
	   
	 }
	
	else if(this.gettheTheme().equals("�������ŵľ�������")){
		Dataset.put("����һ�ξ�����", 0);
		Dataset.put("���λ���ŵ���Ŀ", 0);
		Dataset.put("��ѿ���", 0);
		Dataset.put("��������������Ŀ", 0);
		Dataset.put("����", 0);
        for(newsItem anews:NewsList){ //�����Ż�Ϊĳ���м��������ݼ�
			
			if(anews.getTheTags().contains("����һ�ξ�����")){
				Dataset.put("����һ�ξ�����", (int)Dataset.get("����һ�ξ�����")+1);  //������������������ţ���������һ
			}
			
			if(anews.getTheTags().contains("���λ���ŵ���Ŀ")){
				Dataset.put("���λ���ŵ���Ŀ", (int)Dataset.get("���λ���ŵ���Ŀ")+1);  //������������������ţ���������һ
			}
			if(anews.getTheTags().contains("��ѿ���")){
				Dataset.put("��ѿ���", (int)Dataset.get("��ѿ���")+1);  //������������������ţ���������һ
			}
			if(anews.getTheTags().contains("��������������Ŀ")){
				Dataset.put("��������������Ŀ", (int)Dataset.get("��������������Ŀ")+1);  //������������������ţ���������һ
			}
			if(anews.getTheTags().contains("����")){
				Dataset.put("����", (int)Dataset.get("����")+1);  //������������������ţ���������һ
			}
        }
	    
	   
	 }
	
	else if(this.gettheTheme().equals("���������ŵ�����")){
		Dataset.put("��������", 0);
		Dataset.put("��ҵ", 0);
		Dataset.put("��ѿ���", 0);
		Dataset.put("��ҵ��λ", 0);
		Dataset.put("����", 0);
        for(newsItem anews:NewsList){ //�����Ż�Ϊĳ���м��������ݼ�
			
			if(anews.getTheTags().contains("��������")){
				Dataset.put("��������", (int)Dataset.get("��������")+1);  //������������������ţ���������һ
			}
			if(anews.getTheTags().contains("��ҵ")){
				Dataset.put("��ҵ", (int)Dataset.get("��ҵ")+1);  //������������������ţ���������һ
			}
			if(anews.getTheTags().contains("��ѿ���")){
				Dataset.put("��ѿ���", (int)Dataset.get("��ѿ���")+1);  //������������������ţ���������һ
			}
			if(anews.getTheTags().contains("��ҵ��λ")){
				Dataset.put("��ҵ��λ", (int)Dataset.get("��ҵ��λ")+1);  //������������������ţ���������һ
			}
			if(anews.getTheTags().contains("����")){
				Dataset.put("����", (int)Dataset.get("����")+1);  //������������������ţ���������һ
			}
			
			
        }
	   
	    
	 }
	
	else if(this.gettheTheme().equals("ũ����Ů�������ڳ��ж����ԭ��")){
		Dataset.put("�ޱ��ػ������빫��ѧУ", 0);
		Dataset.put("˽��ѧУѧ�Ѹ�", 0);
		Dataset.put("˽��ѧУ��ѧ����û����", 0);
		Dataset.put("С��˽��ѧУ�𽥱�����ȡ����ѧ�ʸ�", 0);
		Dataset.put("����", 0);
		
        for(newsItem anews:NewsList){ //�����Ż�Ϊĳ���м��������ݼ�
			
			if(anews.getTheTags().contains("�ޱ��ػ������빫��ѧУ")){
				Dataset.put("�ޱ��ػ������빫��ѧУ", (int)Dataset.get("�ޱ��ػ������빫��ѧУ")+1);  //������������������ţ���������һ
			}
			
			if(anews.getTheTags().contains("˽��ѧУѧ�Ѹ�")){
				Dataset.put("˽��ѧУѧ�Ѹ�", (int)Dataset.get("˽��ѧУѧ�Ѹ�")+1);  //������������������ţ���������һ
			}
			if(anews.getTheTags().contains("˽��ѧУ��ѧ����û����")){
				Dataset.put("˽��ѧУ��ѧ����û����", (int)Dataset.get("˽��ѧУ��ѧ����û����")+1);  //������������������ţ���������һ
			}
			if(anews.getTheTags().contains("С��˽��ѧУ�𽥱�����ȡ����ѧ�ʸ�")){
				Dataset.put("С��˽��ѧУ�𽥱�����ȡ����ѧ�ʸ�", (int)Dataset.get("С��˽��ѧУ�𽥱�����ȡ����ѧ�ʸ�")+1);  //������������������ţ���������һ
			}
			if(anews.getTheTags().contains("����")){
				Dataset.put("����", (int)Dataset.get("����")+1);  //������������������ţ���������һ
			}
			
        }
	   
	    
	 }
	
	
	else if(this.gettheTheme().equals("���ý�������������")){
		Dataset.put("��������", 0);
		Dataset.put("��ҵ", 0);
		Dataset.put("��ѿ���", 0);
		Dataset.put("��ҵ��λ", 0);
		Dataset.put("����", 0);
        for(newsItem anews:NewsList){ //�����Ż�Ϊĳ���м��������ݼ�
			
			if(anews.getTheTags().contains("��������")){
				Dataset.put("��������", (int)Dataset.get("��������")+1);  //������������������ţ���������һ
			}
			
			if(anews.getTheTags().contains("��ҵ")){
				Dataset.put("��ҵ", (int)Dataset.get("��ҵ")+1);  //������������������ţ���������һ
			}
			if(anews.getTheTags().contains("��ѿ���")){
				Dataset.put("��ѿ���", (int)Dataset.get("��ѿ���")+1);  //������������������ţ���������һ
			}
			if(anews.getTheTags().contains("��ҵ��λ")){
				Dataset.put("��ҵ��λ", (int)Dataset.get("��ҵ��λ")+1);  //������������������ţ���������һ
			}
			if(anews.getTheTags().contains("����")){
				Dataset.put("����", (int)Dataset.get("����")+1);  //������������������ţ���������һ
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
