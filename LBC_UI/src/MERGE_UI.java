import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;


public class MERGE_UI {
  Vector<newsItem> NewsList = new Vector<newsItem>();
  Vector<newsItem> NewsList1 = new Vector<newsItem>();
  public void CreateFrame(){
    JFrame main = new JFrame();
    Container container = main.getContentPane();
    container.setBackground(Color.gray);
    container.setLayout(null);
    main.setSize(660, 540);
    main.setVisible(true);
    DefaultListModel<String> modellist = new DefaultListModel<>();
    JList<String> newslist = new JList<String>(modellist);
    JScrollPane newsscroller = new JScrollPane(newslist);
    DefaultListModel<String> modellist1 = new DefaultListModel<>();
    JList<String> newslist1 = new JList<String>(modellist1);
    JScrollPane newsscroller1 = new JScrollPane(newslist1);
    newsscroller.setBounds(10, 10, 260, 480);
    newsscroller1.setBounds(370,10,260,480);
    JButton open1 = new JButton("记录一");
    JButton open2 = new JButton("记录二");
    JButton merge = new JButton("合并");
    JButton out = new JButton("导出");
    JButton compare = new JButton("相似度");
    open1.setBounds(280,100,80,20);
    open2.setBounds(280,150,80,20);
    merge.setBounds(280,200,80,20);
    out.setBounds(280,250,80,20);
    compare.setBounds(280,300,80,20);
    
    open1.addActionListener(new ActionListener(){
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
        if(!jfc.getSelectedFile().getName().contains(".txt")){
          System.out.println("读取文件格式不对");
        }else {
            String address1 = file.getAbsolutePath();
         try
      {
           JPasswordField pwd = new JPasswordField(); 
           Object[] message = {"请输入密码:", pwd}; 
           int res = JOptionPane.showConfirmDialog(null, message, "请输入记录的密码:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE); 
           String inputword = new String(pwd.getPassword());
           FileInputStream fis = new FileInputStream(address1);
           ObjectInputStream ois = new ObjectInputStream(fis);
           String trainmethod = (String)ois.readObject();
           String password = (String)ois.readObject();
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

    open2.addActionListener(new ActionListener(){
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
        if(!jfc.getSelectedFile().getName().contains(".txt")){
          System.out.println("读取文件格式不对");
        }else {
            String address1 = file.getAbsolutePath();
         try
      {
           JPasswordField pwd = new JPasswordField(); 
           Object[] message = {"请输入密码:", pwd}; 
           int res = JOptionPane.showConfirmDialog(null, message, "请输入记录的密码:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE); 
           String inputword = new String(pwd.getPassword());
           FileInputStream fis = new FileInputStream(address1);
           ObjectInputStream ois = new ObjectInputStream(fis);
           String trainmethod = (String)ois.readObject();
           String password = (String)ois.readObject();
           System.out.println("密码为："+password);
           if(inputword.equals(password))
             JOptionPane.showMessageDialog(null, "密码正确");
           else{
             JOptionPane.showMessageDialog(null, "密码错误");
             return;
           }
           newsItem st1=null;
           int i=0;
           NewsList1.clear();
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
              
          
              NewsList1.add(obj);
          }
        
           ois.close();  
           fis.close(); 
      }

         catch (IOException e1) {
                // TODO Auto-generated catch block
                DefaultListModel dlm = new DefaultListModel();
                newsItem value = null;
                if(NewsList1!=null){
                      int size = NewsList1.size();
                      System.out.println(size);
                      for (int i=0; i<size; i++) {
                          value = NewsList1.get(i);
                         
                          if(true!=value.getIsDelete()) {
                              dlm.addElement(value.theTitle);
                          }           
                      }
                    }
                    newslist1.setModel(dlm);
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

    merge.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        int i = 0;
        int j = 0;
        for(;i<NewsList.size();i++){
          for(j=0;j<NewsList1.size();j++){
            if(NewsList.get(i).getTheTitle().equals(NewsList1.get(j).getTheTitle())){
              NewsList1.get(j).theTags = union(NewsList.get(i).theTags,NewsList1.get(j).theTags);
              break;
            }
          }
          if(j == NewsList1.size()){//说明记录一中没有
            NewsList1.add(NewsList.get(i));
          }
        }//合并完成
        JOptionPane.showMessageDialog(null, "合并完成");  
        DefaultListModel dlm = new DefaultListModel();
        DefaultListModel dlm1 = new DefaultListModel();
        newsItem value = null;
        if(NewsList1!=null){
          int size = NewsList1.size();
          System.out.println(size);
          for (i=0; i<size; i++) {
            value = NewsList1.get(i);
            if(true!=value.getIsDelete()) {
              dlm.addElement(value.theTitle);
            }           
          }
         }
         newslist.setModel(dlm);
         newslist1.setModel(dlm1);
      }
    });

    out.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e) {
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
           newsItem value = null;
              if(NewsList1!=null){
                int size = NewsList1.size();
                for (int i=0; i<size; i++) {
                    value = NewsList1.get(i);
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
    
    compare.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e) {
        float err = 0;
        for(int i=0;i<NewsList.size();i++){
          if(!NewsList.get(i).theTags.equals(NewsList1.get(i).theTags)){
            err++;
            System.out.println("标签不同的新闻："+NewsList.get(i).getTheTitle());
          }
        }
        float rate = (NewsList.size()-err)*100/NewsList.size();
        JOptionPane.showMessageDialog(null, "相似度为"+String.valueOf(rate)+"%");
      }
    });
    
    container.add(newsscroller);
    container.add(newsscroller1);
    container.add(open1);
    container.add(open2);
    container.add(merge);
    container.add(out);
    container.add(compare);
  }
  
  public String union(String one , String two){
      System.out.println(one);
      System.out.println(two);
      String[] array1 = one.split(" ");
      String[] array2 = two.split(" ");
      String tags="";
      Set<String> set = new HashSet<String>(); 
      for (String str : array1) {  
        set.add(str);   
      }   
      for (String str : array2) {   
        set.add(str);   
      }   
      String[] result = {};   
      String[] result_union = set.toArray(result);
      for(String str:result_union){
        tags = tags + str;
        tags = tags + " ";
      }
      return tags;
  }
}
