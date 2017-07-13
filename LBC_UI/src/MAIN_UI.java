import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartPanel;


class dnewsItem {
	newsItem item;
	int number=-1;//��ɾ����������NewsList�еı��
	
	int getnumber()
	{
		return number;
	}
	void setnumber(int i) {
		number=i;
	}
	
    void setItem(newsItem item) {
    	this.item=item;
    }
    String getTitle()
    {
    	return item.getTheTitle();
    }
}

public class MAIN_UI {
  String address;
  String delete_title;
  String return_title;
  Vector<newsItem> NewsList = new Vector<newsItem>();
  Vector<dnewsItem> deList=new Vector<dnewsItem>();
  Vector<newsItem> Sorted = new Vector<newsItem>();
  int nowselect;
  String Tag;
  String selecttheme="";
  private static final Logger lg= Logger.getLogger(MAIN_UI.class);
  
  public void CreateFrame(){
    JFrame main = new JFrame();
    Container container = main.getContentPane();
    container.setBackground(Color.gray);
    container.setLayout(null);    
    main.setSize(1080, 620);
    main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    //�˵���
    JMenuBar menubar = new JMenuBar();
    JMenu menu = new JMenu("�ļ�");
    JMenuItem file = new JMenuItem("�����ļ�");
    JMenuItem filesave = new JMenuItem("�����¼");
    JMenuItem fileload = new JMenuItem("��ȡ��¼"); 
    JMenu menu1 = new JMenu("�û�");
    JMenuItem item1 = new JMenuItem("�ϲ���ǩ");
    JMenuItem item2 = new JMenuItem("�û�ѵ��"); 
    JButton bsort = new JButton("�ѷ���");
    JButton bunsort = new JButton("δ����");
    JButton brecycle = new JButton("����վ");
    JButton bcount = new JButton("ͳ��");
    JButton bdel = new JButton("ɾ��");
    JButton breturn = new JButton("��ԭ");
    JButton bcount_1 = new JButton("��ͼ");
    JButton bcount_2 = new JButton("��״ͼ");
    JButton before = new JButton("before");
    JButton next = new JButton("next");
    JLabel train = new JLabel("����ģʽ");
    String tags = "";
    TagGroupDataset instance = new TagGroupDataset();
    DefaultListModel<String> modellist = new DefaultListModel<>();
    JList<String> newslist = new JList<String>(modellist);
    file.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        JFileChooser jfc=new JFileChooser();  
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
        jfc.showDialog(new JLabel(), "ѡ��");  
        File file=jfc.getSelectedFile();  
        if(file.isDirectory()){  
            System.out.println("�ļ���:"+file.getAbsolutePath());  
        }else if(file.isFile()){  
            System.out.println("�ļ�:"+file.getAbsolutePath());  
        }  
        System.out.println(jfc.getSelectedFile().getName()); 
        if(!jfc.getSelectedFile().getName().contains(".xml")){
          System.out.println("��ȡ�ļ���ʽ����");
        }
        else{
          address = file.getAbsolutePath();
          javaRxm newslist = new javaRxm();
          modellist.removeAllElements();
          Vector<newsItem> item = new Vector<newsItem>();
          item.clear();
          item.addAll(newslist.returnvector(address));
          if(train.getText().equals("����ģʽ")||item.size()<=100)
            NewsList.addAll(item);
          else{
            for(int i=0;i<100;i++)
              NewsList.add(item.get(i));
          }
          newsItem value = null;
          if(NewsList!=null){
            int size = NewsList.size();
            for (int i=0; i<size; i++) {
                value = NewsList.get(i);
                if(true!=value.getIsDelete()) {
                	modellist.addElement(value.theTitle);
                }  
            }
          }
        }
      }
    });

    filesave.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e){
    		lg.info("�����¼");
    		JFileChooser jfc=new JFileChooser();  
            jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
            jfc.showDialog(new JLabel(), "ѡ��");  
            File file=jfc.getSelectedFile();  
            if(file.isDirectory()){  
                System.out.println("�ļ���:"+file.getAbsolutePath());  
            }else if(file.isFile()){  
                System.out.println("�ļ�:"+file.getAbsolutePath());  
            }  
            System.out.println(jfc.getSelectedFile().getName()); 
            if(!jfc.getSelectedFile().getName().contains(".txt")){
              System.out.println("��ȡ�ļ���ʽ����");
            }else {
              JPasswordField pwd = new JPasswordField(); 
              Object[] message = {"����������:", pwd}; 
              int res = JOptionPane.showConfirmDialog(null, message, "����������:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE); 
              String password = new String(pwd.getPassword());
              System.out.println("��������Ϊ��"+password);
              String address1 = file.getAbsolutePath();
              File file1 = new File(address1);  
    		  try  
    		  {  
    		    if(!file1.exists()) {
    	          file1.createNewFile();
    	        }
  	            FileWriter fileWriter =new FileWriter(file1);
  	            fileWriter.write("");
  	            fileWriter.flush();
  	            fileWriter.close();  
    		  }  
    		  catch(IOException e1)  
    		  {  
    		   e1.printStackTrace();  
    		  }  
    		  try  
    		  {  
        		  //Student�������л�����  
        		  FileOutputStream fos = new FileOutputStream(file); 
        		  ObjectOutputStream oos = new ObjectOutputStream(fos);
        		  String x1="ѵ��ģʽ",x2 = "����ģʽ";
        		  if(train.getText().equals(x1)){
        		    oos.writeObject(x1);
        		  }
        		  else
        		    oos.writeObject(x2);
        		  oos.writeObject(password);
        		  newsItem value = null;
    	          if(NewsList!=null){
    	            int size = NewsList.size();
    	            for (int i=0; i<size; i++) {
    	            	value = NewsList.get(i);
    	            	oos.writeObject(value); 
    	            }
    	            System.out.println("�������");
    	          }
    		   oos.flush();  
    		   oos.close();  
    		   fos.close(); 
    		  }
    		  catch(IOException e1)  
    		  {  
    		   e1.printStackTrace();  
    		  }  
    		  
            }  
    		  
    	}
    });

    fileload.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e){
    		lg.info("��ȡ��¼");
    		JFileChooser jfc=new JFileChooser();  
            jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
            jfc.showDialog(new JLabel(), "ѡ��");  
            File file=jfc.getSelectedFile();  
            if(file.isDirectory()){  
                System.out.println("�ļ���:"+file.getAbsolutePath());  
            }else if(file.isFile()){  
                System.out.println("�ļ�:"+file.getAbsolutePath());  
            }  
            System.out.println(jfc.getSelectedFile().getName()); 
            if(!jfc.getSelectedFile().getName().contains(".txt")){
              System.out.println("��ȡ�ļ���ʽ����");
            }else {
            	String address1 = file.getAbsolutePath();
			 try
   		  {
	           JPasswordField pwd = new JPasswordField();        
   			   FileInputStream fis = new FileInputStream(address1);
               ObjectInputStream ois = new ObjectInputStream(fis);
               String trainmethod = (String)ois.readObject();
               if(trainmethod.equals("ѵ��ģʽ")&&train.getText().equals("����ģʽ")){
                 JOptionPane.showMessageDialog(null, "��ǰ��������ģʽ���޷���ȡѵ��ģʽ����");   
                 return;
               }
               else if(trainmethod.equals("����ģʽ")&&train.getText().equals("ѵ��ģʽ")){
                 JOptionPane.showMessageDialog(null, "��ǰ����ѵ��ģʽ���޷���ȡ����ģʽ����");   
                 return;
               }
               String password = (String)ois.readObject();
               Object[] message = {"����������:", pwd}; 
               int res = JOptionPane.showConfirmDialog(null, message, "�������¼������:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE); 
               String inputword = new String(pwd.getPassword());
               System.out.println("����Ϊ��"+password);
               if(inputword.equals(password))
                 JOptionPane.showMessageDialog(null, "������ȷ");
               else{
                 JOptionPane.showMessageDialog(null, "�������");
                 return;
               }
   			   newsItem st1=null;
   			   int i=0;
   			   NewsList.clear();
   			  while((st1 = (newsItem) ois.readObject())!=null) {
   				  newsItem obj=new newsItem();
   				  obj.setTheTitle(st1.getTheTitle());
			      obj.setTheDate(st1.getTheDate());
			      obj.setTheLocation(st1.getTheLocation());
			      obj.setTheUrl(st1.getTheUrl());
			      obj.setTheTureUrl(st1.getTheTrueUrl());
			      obj.setTheType(st1.getTheType());
			      obj.setTheWordcount(st1.getTheWordcount());
			      obj.setTheId(st1.getTheId());
			      obj.setTheTags(st1.getTheTags());
			      obj.setTheEncodeContent(st1.getTheEncodeContent());
			      obj.setTheBodyContent(st1.getTheBodyContent());
			      obj.setIsDelete(st1.getIsDelete());
			      NewsList.add(obj);
   			  }
   			
   			   ois.close();  
   			   fis.close(); 
   		  }
   	
			 catch (IOException e1) {
					// TODO Auto-generated catch block
				    DefaultListModel dlm = new DefaultListModel();
					newsItem value = null;
					if(NewsList!=null){
				          int size = NewsList.size();
				          System.out.println(size);
				          for (int i=0; i<size; i++) {
				              value = NewsList.get(i);
				             
				              if(true!=value.getIsDelete()) {
				            	  dlm.addElement(value.theTitle);
				              }           
				          }
				        }
				        newslist.setModel(dlm);
					 System.out.println("�ļ�����");
					//e1.printStackTrace();
				}
   		  catch (ClassNotFoundException e1)  
   		  {  
   		   e1.printStackTrace();  
   		  }     
    		
            }	 
    	}
    	
    });


    
    menu.add(file);
    menu.add(filesave);
    menu.add(fileload);
    menubar.add(menu);    
    menu1.add(item1);
    menu1.add(item2);
    menubar.add(menu1);
    main.setJMenuBar(menubar);

    
    item1.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        new MERGE_UI().CreateFrame();
      }
    });
    
    item2.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        if(item2.getText().equals("�û�ѵ��")){
          train.setText("ѵ��ģʽ");
          item2.setText("�˳�ѵ��");
          NewsList.clear();
        }
        else{
          train.setText("����ģʽ");
          item2.setText("�û�ѵ��");
          NewsList.clear();
        }
        ((DefaultListModel)newslist.getModel()).removeAllElements();
      }
    });
    
    
    Vector<String> a = new Vector<String>();
    a.add("1");
    a.add("2");
    a.add("3");
    
    //�������vector
    ///JTree
    JTree nodes = new JTree();
    CheckBoxTreeNode themes = new CheckBoxTreeNode("��������");
    CheckBoxTreeNode theme1 = new CheckBoxTreeNode("��ֽ��");
    theme1.add(new CheckBoxTreeNode("���뵳��"));
    theme1.add(new CheckBoxTreeNode("ʡί���ر�"));
    theme1.add(new CheckBoxTreeNode("�����൳��"));
    CheckBoxTreeNode theme2 = new CheckBoxTreeNode("������");
    theme2.add(new CheckBoxTreeNode("��������"));
    theme2.add(new CheckBoxTreeNode("�ظ�����д"));
    theme2.add(new CheckBoxTreeNode("����"));
    theme2.add(new CheckBoxTreeNode("����"));
    CheckBoxTreeNode theme3 = new CheckBoxTreeNode("�Ա�");
    theme3.add(new CheckBoxTreeNode("��"));
    theme3.add(new CheckBoxTreeNode("Ů"));
    CheckBoxTreeNode theme4 = new CheckBoxTreeNode("���ű�����Ϣ��Դ");
    theme4.add(new CheckBoxTreeNode("����������ذ�"));
    theme4.add(new CheckBoxTreeNode("�������ض�ͯ����Ľ���Ϳ���"));
    theme4.add(new CheckBoxTreeNode("���ð����ذ����ض�ͯ�ĵ�λ�͸���"));
    theme4.add(new CheckBoxTreeNode("���ض�ͯ���ܱ������ų����֣�"));
    theme4.add(new CheckBoxTreeNode("���ض�ͯ������"));
    theme4.add(new CheckBoxTreeNode("���ض�ͯ�ķ���"));
    theme4.add(new CheckBoxTreeNode("���ض�ͯ����������"));
    theme4.add(new CheckBoxTreeNode("���ض�ͯ��Ŭ���Ͻ�"));
    theme4.add(new CheckBoxTreeNode("�򹤸�ĸ�ڳ��еļ�������"));
    theme4.add(new CheckBoxTreeNode("����"));
    CheckBoxTreeNode theme5 = new CheckBoxTreeNode("���ű�����Դ");
    theme5.add(new CheckBoxTreeNode("����"));
    theme5.add(new CheckBoxTreeNode("����"));
    theme5.add(new CheckBoxTreeNode("��ҵ"));
    theme5.add(new CheckBoxTreeNode("��ҵ��λ"));
    theme5.add(new CheckBoxTreeNode("��������"));
    theme5.add(new CheckBoxTreeNode("ר��ѧ�ߣ�û�й�ְ��"));
    theme5.add(new CheckBoxTreeNode("�����쵼����Э���˴����"));
    theme5.add(new CheckBoxTreeNode("����"));
    CheckBoxTreeNode theme6 = new CheckBoxTreeNode("ý���������");
    theme6.add(new CheckBoxTreeNode("��������"));
    theme6.add(new CheckBoxTreeNode("��������"));
    theme6.add(new CheckBoxTreeNode("����Ҹ�"));
    theme6.add(new CheckBoxTreeNode("�����ͯ"));
    theme6.add(new CheckBoxTreeNode("����"));
    CheckBoxTreeNode theme7 = new CheckBoxTreeNode("�������ŵľ�������");
    theme7.add(new CheckBoxTreeNode("����һ�ξ�����"));
    theme7.add(new CheckBoxTreeNode("���λ���ŵ���Ŀ"));
    theme7.add(new CheckBoxTreeNode("��ѿ���"));
    theme7.add(new CheckBoxTreeNode("��������������Ŀ"));
    theme7.add(new CheckBoxTreeNode("����"));
    CheckBoxTreeNode theme8 = new CheckBoxTreeNode("���������ŵ�����");
    theme8.add(new CheckBoxTreeNode("��������"));
    theme8.add(new CheckBoxTreeNode("��ҵ"));
    theme8.add(new CheckBoxTreeNode("��ѿ���"));
    theme8.add(new CheckBoxTreeNode("��ҵ��λ"));
    theme8.add(new CheckBoxTreeNode("����"));
    CheckBoxTreeNode theme9 = new CheckBoxTreeNode("���ý�������������");
    theme9.add(new CheckBoxTreeNode("��������"));
    theme9.add(new CheckBoxTreeNode("��ҵ"));
    theme9.add(new CheckBoxTreeNode("��ѿ���"));
    theme9.add(new CheckBoxTreeNode("��ҵ��λ"));
    theme9.add(new CheckBoxTreeNode("����"));
    CheckBoxTreeNode theme10 = new CheckBoxTreeNode("ũ����Ů�������ڳ��ж����ԭ��");
    theme10.add(new CheckBoxTreeNode("�ޱ��ػ������빫��ѧУ"));
    theme10.add(new CheckBoxTreeNode("˽��ѧУѧ�Ѹ�"));
    theme10.add(new CheckBoxTreeNode("˽��ѧУ��ѧ����û����"));
    theme10.add(new CheckBoxTreeNode("С��˽��ѧУ�𽥱�����ȡ����ѧ�ʸ�"));
    theme10.add(new CheckBoxTreeNode("����"));
    themes.add(theme1);
    themes.add(theme2);
    themes.add(theme3);
    themes.add(theme4);
    themes.add(theme5);
    themes.add(theme6);
    themes.add(theme7);
    themes.add(theme8);
    themes.add(theme9);
    themes.add(theme10);
    
    bsort.setBounds(10,10,80,20);
    bunsort.setEnabled(false);
    bdel.setEnabled(true);
    breturn.setEnabled(false);
    bcount_1.setEnabled(false);
    bcount_2.setEnabled(false);
    bsort.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        // �ѷ��ఴť
        bsort.setEnabled(false);
        bunsort.setEnabled(true);
        brecycle.setEnabled(true);
        bcount.setEnabled(true);
        bdel.setEnabled(true);
        breturn.setEnabled(false);
        bcount_1.setEnabled(false);
        bcount_2.setEnabled(false);
        newslist.setListData(a);
        DefaultListModel dlm = new DefaultListModel();
        newsItem value = null;
        if(NewsList!=null){
          int size = NewsList.size();
          for (int i=0; i<size; i++) {
              value = NewsList.get(i);
              if(!value.theTags.equals("")&&true!=value.getIsDelete()) {
                System.out.println(i);
                System.out.println(value.theTags);
                System.out.println("�ѷ���");
                dlm.addElement(value.theTitle);
              } 
          }
        }
        newslist.setModel(dlm);
      }
    });
    
    bunsort.setBounds(100,10,80,20);
    bunsort.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        // δ���ఴť
        bsort.setEnabled(true);
        bunsort.setEnabled(false);
        brecycle.setEnabled(true);
        bcount.setEnabled(true);
        bdel.setEnabled(true);
        breturn.setEnabled(false);
        bcount_1.setEnabled(false);
        bcount_2.setEnabled(false);
        newslist.setListData(a);
        DefaultListModel dlm = new DefaultListModel();
        newsItem value = null;
        if(NewsList!=null){
          int size = NewsList.size();
          for (int i=0; i<size; i++) {
              value = NewsList.get(i);
              if(true!=value.getIsDelete()) {
            	  dlm.addElement(value.theTitle);
              }           
          }
        }
        newslist.setModel(dlm);
      }
    });
    
    brecycle.setBounds(10,40,80,20);
    brecycle.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        // ����վ
        bsort.setEnabled(true);
        bunsort.setEnabled(true);
        brecycle.setEnabled(false);
        bcount.setEnabled(true);
        bdel.setEnabled(false);
        breturn.setEnabled(true);
        bcount_1.setEnabled(false);
        bcount_2.setEnabled(false);
        newslist.setListData(a);      
        DefaultListModel dlm = new DefaultListModel();
        dnewsItem value = null;
        if(deList!=null){
          int size = deList.size();
          for (int i=0; i<size; i++) {
              dlm.addElement(deList.get(i).getTitle());
          }
        }
        newslist.setModel(dlm);
      }
    });
    
    bcount.setBounds(100,40,80,20);
    bcount.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
    	  lg.info("ͳ��");
        bsort.setEnabled(true);
        bunsort.setEnabled(true);
        brecycle.setEnabled(true);
        bcount.setEnabled(false);
        bdel.setEnabled(false);
        breturn.setEnabled(false);
        bcount_1.setEnabled(true);
        bcount_2.setEnabled(true);
        newslist.setListData(a);
        Enumeration<?> enumeration1,enumeration2;
        enumeration1 = themes.children();
        while(enumeration1.hasMoreElements()){ //����ö�ٶ���
          CheckBoxTreeNode  node=(CheckBoxTreeNode) enumeration1.nextElement();
          enumeration2 = node.children();
          node.isSelected=false;
          while(enumeration2.hasMoreElements()){ //����ö�ٶ���
            CheckBoxTreeNode node2=(CheckBoxTreeNode) enumeration2.nextElement();
             node2.isSelected=false;
          }
        }
        DefaultListModel dlm = new DefaultListModel();
        newsItem value = null;
        if(NewsList!=null){
          int size = NewsList.size();
          for (int i=0; i<size; i++) {
              value = NewsList.get(i);
              if(!value.theTags.equals("")&&true!=value.getIsDelete()) {
                  dlm.addElement(value.theTitle);
              }           
          }
        }
        newslist.setModel(dlm);
      }
    });
    

    
   

    bcount_1.setBounds(880,40,80,20);
    bcount_1.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        // ��ͼ
        if(!selecttheme.equals("")){
          System.out.println(selecttheme+"%%%%%%%%%%");
          ThemeDataset instance = new ThemeDataset(NewsList, selecttheme);      
          PieChart h = new PieChart(instance.getDataset());
          JFrame j = new JFrame();
          ChartPanel p = h.getChartPanel();
          j.add(p);
          j.setSize(400, 400);
          j.setVisible(true);
        }
      }
    });

    bcount_2.setBounds(970,40,80,20);
    bcount_2.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        // 
      }
    });
    
    JTextPane shownew = new JTextPane();
    //shownew.setBounds(280,10,500,540);
    shownew.setEditable(false);
    shownew.setText("");
    //���ñ���ĸ�ʽ
    SimpleAttributeSet aSet = new SimpleAttributeSet();   
    StyleConstants.setAlignment(aSet, StyleConstants.ALIGN_CENTER);  
    StyleConstants.setFontSize(aSet, 19); 
    StyleConstants.setBold(aSet,true);
    //�������ߺ����ڵĸ�ʽ
    SimpleAttributeSet bSet = new SimpleAttributeSet();   
    StyleConstants.setAlignment(bSet, StyleConstants.ALIGN_CENTER);  
    StyleConstants.setFontSize(bSet, 15); 
    StyleConstants.setBold(bSet,true);
    //�������ĵĸ�ʽ
    SimpleAttributeSet cSet = new SimpleAttributeSet();   
    StyleConstants.setAlignment(cSet, StyleConstants.ALIGN_CENTER);  
    StyleConstants.setFontSize(cSet, 14); 
    JScrollPane showscroller = new JScrollPane(shownew);
    showscroller.setBounds(280,10,500,540);
    //showscroller.getVerticalScrollBar().setValue(0); 
    
    JScrollPane newsscroller = new JScrollPane(newslist);
    newsscroller.setBounds(10, 70, 260, 480);
    //shownew.set
    
    newslist.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        Enumeration<?> enumeration1,enumeration2;
        enumeration1 = themes.children();
        while(enumeration1.hasMoreElements()){ //����ö�ٶ���
          CheckBoxTreeNode  node=(CheckBoxTreeNode) enumeration1.nextElement();
          enumeration2 = node.children();
          node.setSelected(false);
          while(enumeration2.hasMoreElements()){ //����ö�ٶ���
            CheckBoxTreeNode node2=(CheckBoxTreeNode) enumeration2.nextElement();
             node2.setSelected(false);
          }
        }
        DefaultTreeModel model = new DefaultTreeModel(themes);
        nodes.setModel(model);
        if(NewsList!=null){
        newsItem value = null;
        int size = NewsList.size();
          for (int i=0; i<size; i++) {
            value = NewsList.get(i);
            if(value.getTheTitle().equals(newslist.getSelectedValue())){
              delete_title=value.getTheTitle();
              nowselect=i;
              shownew.setText("");
              Document docs = shownew.getDocument();
              try {
                docs.insertString(docs.getLength(),value.getTheTitle()+'\n', aSet);//���ı�����׷��
                docs.insertString(docs.getLength(),value.getTheLocation()+value.getTheType()+value.getTheTags()+value.getTheDate()+'\n',bSet);
                docs.insertString(docs.getLength(), value.getTheBodyContent(), cSet);
                //showscroller.getVerticalScrollBar().setValue(0);
              } catch (BadLocationException ze) {
                ze.printStackTrace();
              }  
              if(bcount.isEnabled()){
                if(value.theTags!=""){
                  System.out.println(value.theTags);
                  String tags[] = value.theTags.split("\\s+");
                  for(String temp:tags){
                    enumeration1 = themes.children();
                    while(enumeration1.hasMoreElements()){ //����ö�ٶ���
                      CheckBoxTreeNode  node=(CheckBoxTreeNode) enumeration1.nextElement();
                      if(node.a.equals(temp)){
                        node.setSelected(true);
                        System.out.println("�ҵ�"+temp);
                        break;
                      }
                      enumeration2 = node.children();
                      while(enumeration2.hasMoreElements()){ //����ö�ٶ���
                        CheckBoxTreeNode node2=(CheckBoxTreeNode) enumeration2.nextElement();
                        if(node2.a.equals(temp)){
                          node2.setSelected(true);
                          System.out.println("�ҵ�"+temp);
                          break;
                        }
                      }
                    }
                  }
                  model = new DefaultTreeModel(themes);
                  nodes.setModel(model);
                  break;
                }
              }
            }
          }
        }
      }
    });

    train.setBounds(200,10,80,50);
    
    before.setBounds(790,10,80,20);
    before.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        // 
    	  lg.info("��һ������");
        int selected = newslist.getSelectedIndex();
        selected--;
        if(selected >=0 ){
          String title = newslist.getModel().getElementAt(selected);
          newslist.setSelectedIndex(selected);
          if(NewsList!=null){
            newsItem value = null;
            int size = NewsList.size();
            for (int i=0; i<size; i++) {
              value = NewsList.get(i);
              if(value.getTheTitle().equals(newslist.getSelectedValue())){
                shownew.setText("");
                Document docs = shownew.getDocument();
                try {
                  docs.insertString(docs.getLength(),value.getTheTitle()+'\n', aSet);//���ı�����׷��
                  docs.insertString(docs.getLength(),value.getTheLocation()+value.getTheType()+value.getTheTags()+value.getTheDate()+'\n',bSet);
                  docs.insertString(docs.getLength(), value.getTheBodyContent(), cSet);
                } catch (BadLocationException ze) {
                  ze.printStackTrace();
                } 
                break; 
              } 
            }
          }
        }
      }
    });
        
    next.setBounds(790,40,80,20);
    next.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        // 
    	  lg.info("��һ������");
        int selected = newslist.getSelectedIndex();
        selected++;
        if(selected < newslist.getModel().getSize() ){
          String title = newslist.getModel().getElementAt(selected);
          newslist.setSelectedIndex(selected);
          if(NewsList!=null){
            newsItem value = null;
            int size = NewsList.size();
            for (int i=0; i<size; i++) {
              value = NewsList.get(i);
              if(value.getTheTitle().equals(newslist.getSelectedValue())){
                shownew.setText("");
                Document docs = shownew.getDocument();
                try {
                  docs.insertString(docs.getLength(),value.getTheTitle()+'\n', aSet);//���ı�����׷��
                  docs.insertString(docs.getLength(),value.getTheLocation()+value.getTheType()+value.getTheTags()+value.getTheDate()+'\n',bSet);
                  docs.insertString(docs.getLength(), value.getTheBodyContent(), cSet);
                } catch (BadLocationException ze) {
                  ze.printStackTrace();
                } 
                break; 
              } 
            }
          }
        }
      }
    });
    
    bdel.setBounds(880,10,80,20);
    bdel.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
          lg.info("ɾ������");
          if(!bunsort.isEnabled()){
        	  if(NewsList!=null) {
        		  newsItem value = null;
        		  int size = NewsList.size();
        		  for (int i=0; i<size; i++) {
        			  value = NewsList.get(i);			  
        			  if(value.getTheTitle().equals(delete_title)) {    				  
        				  NewsList.get(i).setIsDelete(true);
        				  dnewsItem ditem=new dnewsItem();
        				  ditem.setnumber(i);
        				  ditem.item=NewsList.get(i);
        				  deList.addElement(ditem);
        				  break;
        			  }
        		  }    		  
        		  shownew.setText("");    		  
        		  //���¼���NewsList
        		  DefaultListModel dlm = new DefaultListModel();
        	      value = null;
        	        if(NewsList!=null){
        	          size = NewsList.size();
        	          for (int i=0; i<size; i++) {
        	              value = NewsList.get(i);
        	              if(true!=value.getIsDelete()) {
        	            	  dlm.addElement(value.theTitle);
        	              }           
        	          }
        	        }
        	       newslist.setModel(dlm);
        	  }
          }
          else if(!bsort.isEnabled()){
            if(NewsList!=null) {
              newsItem value = null;
              int size = NewsList.size();
              for (int i=0; i<size; i++) {
                  value = NewsList.get(i);            
                  if(value.getTheTitle().equals(delete_title)) {                      
                      NewsList.get(i).setIsDelete(true);
                      dnewsItem ditem=new dnewsItem();
                      ditem.setnumber(i);
                      ditem.item=NewsList.get(i);
                      deList.addElement(ditem);
                      break;
                  }
              }           
              shownew.setText("");            
              //���¼���NewsList
              DefaultListModel dlm = new DefaultListModel();
              value = null;
                if(NewsList!=null){
                  size = NewsList.size();
                  for (int i=0; i<size; i++) {
                      value = NewsList.get(i);
                      if(true!=value.getIsDelete()&&value.theTags!="") {
                          dlm.addElement(value.theTitle);
                      }           
                  }
                }
               newslist.setModel(dlm);
            }
          }
      }
    });
    
    breturn.setBounds(970,10,80,20);
    breturn.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        // 
    	  lg.info("��ԭ��ɾ������");
    	  if(deList!=null) {
    		  dnewsItem value = null;
    		  int size = deList.size();
    		  for (int i=0; i<size; i++) {
    			  value = deList.get(i);
    			  
    			  if(value.getTitle().equals(delete_title)) {
    				  
    				  NewsList.get(value.getnumber()).setIsDelete(false);
    				  deList.remove(i);
    				  break;
    			  }
    		  }
    		  shownew.setText("");
    		  
    		  DefaultListModel dlm = new DefaultListModel();
    	        value = null;
    	        if(deList!=null){
    	          size = deList.size();
    	          for (int i=0; i<size; i++) {
    	              value = deList.get(i);
    	              dlm.addElement(value.getTitle());
    	          }
    	        }
    	        newslist.setModel(dlm);
    	  }
      }
    });
    
    
    DefaultTreeModel model = new DefaultTreeModel(themes);
    nodes.setModel(model);
    nodes.addMouseListener(new MouseAdapter(){//��ѡ���ѡ��
      @Override  
      public void mouseClicked(MouseEvent event)   { 
    	  lg.info("�޸ı�ǩ");
          String choose=null;
          String unchoose = null;
          JTree tree = (JTree)event.getSource();  
          int x = event.getX();  
          int y = event.getY();  
          int row = tree.getRowForLocation(x, y);  
          TreePath path = tree.getPathForRow(row);  
          if(path != null)  
          {  
              CheckBoxTreeNode node = (CheckBoxTreeNode)path.getLastPathComponent();  
              if(node != null)  
              {  
                  boolean isSelected = !node.isSelected();  
                  node.setSelected(isSelected);  
                  ((DefaultTreeModel)tree.getModel()).nodeStructureChanged(node); 
                  if(!bunsort.isEnabled()||!bsort.isEnabled()){
                    if(node.isSelected()&&!((String)node.a_f).equals("��������")&&!((String)node.a).equals("��������")){
                      NewsList.get(nowselect).theTags += ((String)node.a+" ");
                      System.out.println("����ǩ"+(String)node.a);
                      if(!NewsList.get(nowselect).theTheme.contains((String)node.a_f)){
                        NewsList.get(nowselect).theTheme += ((String)node.a_f+" ");
                        System.out.println("������"+(String)node.a_f);
                      }
                      else{
                        System.out.println("����������"+(String)node.a_f);
                      }
                    }
                    else if(!node.isSelected()&&!((String)node.a_f).equals("��������")&&!((String)node.a).equals("��������")){
                      NewsList.get(nowselect).theTags = NewsList.get(nowselect).theTags.replaceAll((String)node.a+" ", "");
                      System.out.println("ɾ����ǩ"+(String)node.a);
                      if(node.deltheme){
                        NewsList.get(nowselect).theTheme = NewsList.get(nowselect).theTheme.replaceAll((String)node.a_f+" ", "");
                        System.out.println("ɾ������"+(String)node.a_f);
                      }
                    }
                  }
                  if(!bsort.isEnabled()){
                    DefaultListModel dlm = new DefaultListModel();
                    newsItem value = null;
                    if(NewsList!=null){
                      int size = NewsList.size();
                      for (int i=0; i<size; i++) {
                          value = NewsList.get(i);
                          if(!value.theTags.equals("")&&!value.getIsDelete())
                            dlm.addElement(value.getTheTitle());
                      }
                    }
                    newslist.setModel(dlm);
                  }
                  if(!bcount.isEnabled()){//��ѡ���ͳ����Ӧ
                    ((DefaultTreeModel)tree.getModel()).nodeStructureChanged(node); 
                    if(node.isSelected()&&((String)node.a_f).equals("��������")){
                      selecttheme = (String)node.a;
                      System.out.println(selecttheme);
                    }
                    else if(node.isSelected()&&!((String)node.a_f).equals("��������")&&!((String)node.a).equals("��������")){
                      //tagDataset instance = new tagDataset(NewsList, (String)node.a);   
                      instance.tags +=((String)node.a+" ");
                      instance.setallnews(NewsList);
                      BarChart h = new BarChart("����","����",instance.getDataset());
                      JFrame j = new JFrame();
                      ChartPanel p = h.getChartPanel();
                      j.add(p);
                      j.setSize(400, 400);
                      j.setVisible(true);
                    }
                    else if(!node.isSelected()&&!((String)node.a_f).equals("��������")&&!((String)node.a).equals("��������")){
                      //�����ȡ����ǩ�Ĵ���
                      selecttheme = "";
                    }
                  }
              }  
          }  
      }
    }); 

    nodes.setCellRenderer(new CheckBoxTreeCellRenderer()); 
    JScrollPane scroll = new JScrollPane(nodes);
    scroll.setBounds(790,70,260,480);
    container.add(bsort);
    container.add(bunsort);
    container.add(brecycle);
    container.add(bcount);
    container.add(newsscroller);
    container.add(showscroller);
    container.add(scroll);
    container.add(bdel);
    container.add(breturn);
    container.add(bcount_1);
    container.add(bcount_2);
    container.add(before);
    container.add(next);
    container.add(train);
    main.setVisible(true);
  }
  public static void main(String arg[]){
     
       new MAIN_UI().CreateFrame();     
       
  }
}
