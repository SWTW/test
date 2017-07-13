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
	int number=-1;//被删除的新闻在NewsList中的编号
	
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
    //菜单栏
    JMenuBar menubar = new JMenuBar();
    JMenu menu = new JMenu("文件");
    JMenuItem file = new JMenuItem("导入文件");
    JMenuItem filesave = new JMenuItem("保存记录");
    JMenuItem fileload = new JMenuItem("读取记录"); 
    JMenu menu1 = new JMenu("用户");
    JMenuItem item1 = new JMenuItem("合并标签");
    JMenuItem item2 = new JMenuItem("用户训练"); 
    JButton bsort = new JButton("已分类");
    JButton bunsort = new JButton("未分类");
    JButton brecycle = new JButton("回收站");
    JButton bcount = new JButton("统计");
    JButton bdel = new JButton("删除");
    JButton breturn = new JButton("还原");
    JButton bcount_1 = new JButton("饼图");
    JButton bcount_2 = new JButton("柱状图");
    JButton before = new JButton("before");
    JButton next = new JButton("next");
    JLabel train = new JLabel("正常模式");
    String tags = "";
    TagGroupDataset instance = new TagGroupDataset();
    DefaultListModel<String> modellist = new DefaultListModel<>();
    JList<String> newslist = new JList<String>(modellist);
    file.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        JFileChooser jfc=new JFileChooser();  
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
        jfc.showDialog(new JLabel(), "选择");  
        File file=jfc.getSelectedFile();  
        if(file.isDirectory()){  
            System.out.println("文件夹:"+file.getAbsolutePath());  
        }else if(file.isFile()){  
            System.out.println("文件:"+file.getAbsolutePath());  
        }  
        System.out.println(jfc.getSelectedFile().getName()); 
        if(!jfc.getSelectedFile().getName().contains(".xml")){
          System.out.println("读取文件格式不对");
        }
        else{
          address = file.getAbsolutePath();
          javaRxm newslist = new javaRxm();
          modellist.removeAllElements();
          Vector<newsItem> item = new Vector<newsItem>();
          item.clear();
          item.addAll(newslist.returnvector(address));
          if(train.getText().equals("正常模式")||item.size()<=100)
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
    		lg.info("保存记录");
    		JFileChooser jfc=new JFileChooser();  
            jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
            jfc.showDialog(new JLabel(), "选择");  
            File file=jfc.getSelectedFile();  
            if(file.isDirectory()){  
                System.out.println("文件夹:"+file.getAbsolutePath());  
            }else if(file.isFile()){  
                System.out.println("文件:"+file.getAbsolutePath());  
            }  
            System.out.println(jfc.getSelectedFile().getName()); 
            if(!jfc.getSelectedFile().getName().contains(".txt")){
              System.out.println("读取文件格式不对");
            }else {
              JPasswordField pwd = new JPasswordField(); 
              Object[] message = {"请输入密码:", pwd}; 
              int res = JOptionPane.showConfirmDialog(null, message, "请输入密码:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE); 
              String password = new String(pwd.getPassword());
              System.out.println("输入密码为："+password);
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
        		  //Student对象序列化过程  
        		  FileOutputStream fos = new FileOutputStream(file); 
        		  ObjectOutputStream oos = new ObjectOutputStream(fos);
        		  String x1="训练模式",x2 = "正常模式";
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
    	            System.out.println("保存完毕");
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
    		lg.info("读取记录");
    		JFileChooser jfc=new JFileChooser();  
            jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
            jfc.showDialog(new JLabel(), "选择");  
            File file=jfc.getSelectedFile();  
            if(file.isDirectory()){  
                System.out.println("文件夹:"+file.getAbsolutePath());  
            }else if(file.isFile()){  
                System.out.println("文件:"+file.getAbsolutePath());  
            }  
            System.out.println(jfc.getSelectedFile().getName()); 
            if(!jfc.getSelectedFile().getName().contains(".txt")){
              System.out.println("读取文件格式不对");
            }else {
            	String address1 = file.getAbsolutePath();
			 try
   		  {
	           JPasswordField pwd = new JPasswordField();        
   			   FileInputStream fis = new FileInputStream(address1);
               ObjectInputStream ois = new ObjectInputStream(fis);
               String trainmethod = (String)ois.readObject();
               if(trainmethod.equals("训练模式")&&train.getText().equals("正常模式")){
                 JOptionPane.showMessageDialog(null, "当前处于正常模式，无法读取训练模式数据");   
                 return;
               }
               else if(trainmethod.equals("正常模式")&&train.getText().equals("训练模式")){
                 JOptionPane.showMessageDialog(null, "当前处于训练模式，无法读取正常模式数据");   
                 return;
               }
               String password = (String)ois.readObject();
               Object[] message = {"请输入密码:", pwd}; 
               int res = JOptionPane.showConfirmDialog(null, message, "请输入记录的密码:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE); 
               String inputword = new String(pwd.getPassword());
               System.out.println("密码为："+password);
               if(inputword.equals(password))
                 JOptionPane.showMessageDialog(null, "密码正确");
               else{
                 JOptionPane.showMessageDialog(null, "密码错误");
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
					 System.out.println("文件已完");
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
        if(item2.getText().equals("用户训练")){
          train.setText("训练模式");
          item2.setText("退出训练");
          NewsList.clear();
        }
        else{
          train.setText("正常模式");
          item2.setText("用户训练");
          NewsList.clear();
        }
        ((DefaultListModel)newslist.getModel()).removeAllElements();
      }
    });
    
    
    Vector<String> a = new Vector<String>();
    a.add("1");
    a.add("2");
    a.add("3");
    
    //随机访问vector
    ///JTree
    JTree nodes = new JTree();
    CheckBoxTreeNode themes = new CheckBoxTreeNode("所有主题");
    CheckBoxTreeNode theme1 = new CheckBoxTreeNode("报纸类");
    theme1.add(new CheckBoxTreeNode("中央党报"));
    theme1.add(new CheckBoxTreeNode("省委机关报"));
    theme1.add(new CheckBoxTreeNode("都市类党报"));
    CheckBoxTreeNode theme2 = new CheckBoxTreeNode("新闻类");
    theme2.add(new CheckBoxTreeNode("纯净新闻"));
    theme2.add(new CheckBoxTreeNode("特稿与特写"));
    theme2.add(new CheckBoxTreeNode("评论"));
    theme2.add(new CheckBoxTreeNode("其他"));
    CheckBoxTreeNode theme3 = new CheckBoxTreeNode("性别");
    theme3.add(new CheckBoxTreeNode("男"));
    theme3.add(new CheckBoxTreeNode("女"));
    CheckBoxTreeNode theme4 = new CheckBoxTreeNode("新闻报道信息来源");
    theme4.add(new CheckBoxTreeNode("社会各界帮助关爱"));
    theme4.add(new CheckBoxTreeNode("社会对留守儿童现象的建议和看法"));
    theme4.add(new CheckBoxTreeNode("表彰帮助关爱留守儿童的单位和个人"));
    theme4.add(new CheckBoxTreeNode("留守儿童遭受暴力（排除性侵）"));
    theme4.add(new CheckBoxTreeNode("留守儿童被性侵"));
    theme4.add(new CheckBoxTreeNode("留守儿童的犯罪"));
    theme4.add(new CheckBoxTreeNode("留守儿童的以外死亡"));
    theme4.add(new CheckBoxTreeNode("留守儿童的努力上进"));
    theme4.add(new CheckBoxTreeNode("打工父母在城市的艰难生活"));
    theme4.add(new CheckBoxTreeNode("其他"));
    CheckBoxTreeNode theme5 = new CheckBoxTreeNode("新闻报道来源");
    theme5.add(new CheckBoxTreeNode("记者"));
    theme5.add(new CheckBoxTreeNode("政府"));
    theme5.add(new CheckBoxTreeNode("企业"));
    theme5.add(new CheckBoxTreeNode("事业单位"));
    theme5.add(new CheckBoxTreeNode("公益团体"));
    theme5.add(new CheckBoxTreeNode("专家学者（没有官职）"));
    theme5.add(new CheckBoxTreeNode("政府领导、政协或人大代表"));
    theme5.add(new CheckBoxTreeNode("其他"));
    CheckBoxTreeNode theme6 = new CheckBoxTreeNode("媒介形象呈现");
    theme6.add(new CheckBoxTreeNode("积极健康"));
    theme6.add(new CheckBoxTreeNode("可怜悲惨"));
    theme6.add(new CheckBoxTreeNode("沐恩幸福"));
    theme6.add(new CheckBoxTreeNode("问题儿童"));
    theme6.add(new CheckBoxTreeNode("其他"));
    CheckBoxTreeNode theme7 = new CheckBoxTreeNode("帮助新闻的具体种类");
    theme7.add(new CheckBoxTreeNode("单纯一次捐款捐物"));
    theme7.add(new CheckBoxTreeNode("旅游活动安排的项目"));
    theme7.add(new CheckBoxTreeNode("免费开放"));
    theme7.add(new CheckBoxTreeNode("设立长期资助项目"));
    theme7.add(new CheckBoxTreeNode("其他"));
    CheckBoxTreeNode theme8 = new CheckBoxTreeNode("帮助类新闻的主体");
    theme8.add(new CheckBoxTreeNode("政府部门"));
    theme8.add(new CheckBoxTreeNode("企业"));
    theme8.add(new CheckBoxTreeNode("免费开放"));
    theme8.add(new CheckBoxTreeNode("事业单位"));
    theme8.add(new CheckBoxTreeNode("个人"));
    CheckBoxTreeNode theme9 = new CheckBoxTreeNode("表彰奖励的新闻主题");
    theme9.add(new CheckBoxTreeNode("政府部门"));
    theme9.add(new CheckBoxTreeNode("企业"));
    theme9.add(new CheckBoxTreeNode("免费开放"));
    theme9.add(new CheckBoxTreeNode("事业单位"));
    theme9.add(new CheckBoxTreeNode("个人"));
    CheckBoxTreeNode theme10 = new CheckBoxTreeNode("农民工子女不能留在城市读书的原因");
    theme10.add(new CheckBoxTreeNode("无本地户籍难入公立学校"));
    theme10.add(new CheckBoxTreeNode("私立学校学费高"));
    theme10.add(new CheckBoxTreeNode("私立学校教学质量没保障"));
    theme10.add(new CheckBoxTreeNode("小型私立学校逐渐被国家取消办学资格"));
    theme10.add(new CheckBoxTreeNode("其他"));
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
        // 已分类按钮
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
                System.out.println("已分类");
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
        // 未分类按钮
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
        // 回收站
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
    	  lg.info("统计");
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
        while(enumeration1.hasMoreElements()){ //遍历枚举对象
          CheckBoxTreeNode  node=(CheckBoxTreeNode) enumeration1.nextElement();
          enumeration2 = node.children();
          node.isSelected=false;
          while(enumeration2.hasMoreElements()){ //遍历枚举对象
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
        // 饼图
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
    //设置标题的格式
    SimpleAttributeSet aSet = new SimpleAttributeSet();   
    StyleConstants.setAlignment(aSet, StyleConstants.ALIGN_CENTER);  
    StyleConstants.setFontSize(aSet, 19); 
    StyleConstants.setBold(aSet,true);
    //设置作者和日期的格式
    SimpleAttributeSet bSet = new SimpleAttributeSet();   
    StyleConstants.setAlignment(bSet, StyleConstants.ALIGN_CENTER);  
    StyleConstants.setFontSize(bSet, 15); 
    StyleConstants.setBold(bSet,true);
    //设置正文的格式
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
        while(enumeration1.hasMoreElements()){ //遍历枚举对象
          CheckBoxTreeNode  node=(CheckBoxTreeNode) enumeration1.nextElement();
          enumeration2 = node.children();
          node.setSelected(false);
          while(enumeration2.hasMoreElements()){ //遍历枚举对象
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
                docs.insertString(docs.getLength(),value.getTheTitle()+'\n', aSet);//对文本进行追加
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
                    while(enumeration1.hasMoreElements()){ //遍历枚举对象
                      CheckBoxTreeNode  node=(CheckBoxTreeNode) enumeration1.nextElement();
                      if(node.a.equals(temp)){
                        node.setSelected(true);
                        System.out.println("找到"+temp);
                        break;
                      }
                      enumeration2 = node.children();
                      while(enumeration2.hasMoreElements()){ //遍历枚举对象
                        CheckBoxTreeNode node2=(CheckBoxTreeNode) enumeration2.nextElement();
                        if(node2.a.equals(temp)){
                          node2.setSelected(true);
                          System.out.println("找到"+temp);
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
    	  lg.info("上一条新闻");
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
                  docs.insertString(docs.getLength(),value.getTheTitle()+'\n', aSet);//对文本进行追加
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
    	  lg.info("下一条新闻");
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
                  docs.insertString(docs.getLength(),value.getTheTitle()+'\n', aSet);//对文本进行追加
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
          lg.info("删除新闻");
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
        		  //重新加载NewsList
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
              //重新加载NewsList
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
    	  lg.info("还原已删除新闻");
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
    nodes.addMouseListener(new MouseAdapter(){//复选框的选择
      @Override  
      public void mouseClicked(MouseEvent event)   { 
    	  lg.info("修改标签");
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
                    if(node.isSelected()&&!((String)node.a_f).equals("所有主题")&&!((String)node.a).equals("所有主题")){
                      NewsList.get(nowselect).theTags += ((String)node.a+" ");
                      System.out.println("贴标签"+(String)node.a);
                      if(!NewsList.get(nowselect).theTheme.contains((String)node.a_f)){
                        NewsList.get(nowselect).theTheme += ((String)node.a_f+" ");
                        System.out.println("贴主题"+(String)node.a_f);
                      }
                      else{
                        System.out.println("已贴过主题"+(String)node.a_f);
                      }
                    }
                    else if(!node.isSelected()&&!((String)node.a_f).equals("所有主题")&&!((String)node.a).equals("所有主题")){
                      NewsList.get(nowselect).theTags = NewsList.get(nowselect).theTags.replaceAll((String)node.a+" ", "");
                      System.out.println("删除标签"+(String)node.a);
                      if(node.deltheme){
                        NewsList.get(nowselect).theTheme = NewsList.get(nowselect).theTheme.replaceAll((String)node.a_f+" ", "");
                        System.out.println("删除主题"+(String)node.a_f);
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
                  if(!bcount.isEnabled()){//复选框的统计响应
                    ((DefaultTreeModel)tree.getModel()).nodeStructureChanged(node); 
                    if(node.isSelected()&&((String)node.a_f).equals("所有主题")){
                      selecttheme = (String)node.a;
                      System.out.println(selecttheme);
                    }
                    else if(node.isSelected()&&!((String)node.a_f).equals("所有主题")&&!((String)node.a).equals("所有主题")){
                      //tagDataset instance = new tagDataset(NewsList, (String)node.a);   
                      instance.tags +=((String)node.a+" ");
                      instance.setallnews(NewsList);
                      BarChart h = new BarChart("属性","数量",instance.getDataset());
                      JFrame j = new JFrame();
                      ChartPanel p = h.getChartPanel();
                      j.add(p);
                      j.setSize(400, 400);
                      j.setVisible(true);
                    }
                    else if(!node.isSelected()&&!((String)node.a_f).equals("所有主题")&&!((String)node.a).equals("所有主题")){
                      //这里加取消标签的代码
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
