import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class MAIN_UI {
  public void CreateFrame(){
    JFrame main = new JFrame();
    Container container = main.getContentPane();
    container.setBackground(Color.gray);
    container.setLayout(null);    
    main.setSize(1020, 620);
    main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    //菜单栏
    JMenuBar menubar = new JMenuBar();
    JMenu menu = new JMenu("文件");
    JMenuItem file = new JMenuItem("导入文件");
    JMenuItem filesave = new JMenuItem("保存记录");
    JMenuItem fileload = new JMenuItem("读取记录");
    JFileChooser jfc=new JFileChooser();  
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
      }
    });
    menu.add(file);
    menu.add(filesave);
    menu.add(fileload);
    menubar.add(menu);    
    main.setJMenuBar(menubar);
    JButton bsort = new JButton("已分类");
    JButton bunsort = new JButton("未分类");
    JButton brecycle = new JButton("回收站");
    JButton bcount = new JButton("统计");
    JButton bdel = new JButton("删除");
    JButton breturn = new JButton("还原");
    JButton bcount_1 = new JButton("饼图");
    JButton bcount_2 = new JButton("柱状图");

    Vector<String> a = new Vector<String>();
    a.add("1");
    a.add("2");
    a.add("3");
    JList newslist = new JList(a);
    
    bsort.setBounds(10,10,80,20);
    bsort.setEnabled(false);
    bdel.setEnabled(true);
    breturn.setEnabled(false);
    bcount_1.setEnabled(false);
    bcount_2.setEnabled(false);
    bsort.addActionListener(new ActionListener(){
      @SuppressWarnings("unchecked")
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
      }
    });
    
    bcount.setBounds(100,40,80,20);
    bcount.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        //统计 
        bsort.setEnabled(true);
        bunsort.setEnabled(true);
        brecycle.setEnabled(true);
        bcount.setEnabled(false);
        bdel.setEnabled(false);
        breturn.setEnabled(false);
        bcount_1.setEnabled(true);
        bcount_2.setEnabled(true);
        newslist.setListData(a);
      }
    });
    

    bdel.setBounds(820,10,80,20);
    bdel.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        // 
      }
    });

    breturn.setBounds(910,10,80,20);
    breturn.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        // 
      }
    });

    bcount_1.setBounds(820,40,80,20);
    bcount_1.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        // 
      }
    });

    bcount_2.setBounds(910,40,80,20);
    bcount_2.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        // 
      }
    });
    
    
    
    JTextArea shownew = new JTextArea("aaa");
    shownew.setBounds(190,10,620,540);
    

    JScrollPane newsscroller = new JScrollPane(newslist);
    newsscroller.setBounds(10, 70, 170, 480);
    
    newslist.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        
      }
    });
    
    //JTree
    JTree nodes = new JTree();
    CheckBoxTreeNode themes = new CheckBoxTreeNode("所有主题");
    CheckBoxTreeNode theme1 = new CheckBoxTreeNode("主题一");
    theme1.add(new CheckBoxTreeNode("标签一"));
    theme1.add(new CheckBoxTreeNode("标签二"));
    theme1.add(new CheckBoxTreeNode("标签三"));
    theme1.add(new CheckBoxTreeNode("标签四"));
    CheckBoxTreeNode theme2 = new CheckBoxTreeNode("主题二");
    theme2.add(new CheckBoxTreeNode("标签一"));
    theme2.add(new CheckBoxTreeNode("标签二"));
    theme2.add(new CheckBoxTreeNode("标签三"));
    theme2.add(new CheckBoxTreeNode("标签四"));
    themes.add(theme1);
    themes.add(theme2);
    DefaultTreeModel model = new DefaultTreeModel(themes);
    nodes.addMouseListener(new CheckBoxTreeNodeSelectionListener()); 
    nodes.setModel(model);
    nodes.setCellRenderer(new CheckBoxTreeCellRenderer()); 
    JScrollPane scroll = new JScrollPane(nodes);
    scroll.setBounds(820,70,170,480);
    container.add(bsort);
    container.add(bunsort);
    container.add(brecycle);
    container.add(bcount);
    container.add(newsscroller);
    container.add(shownew);
    container.add(scroll);
    container.add(bdel);
    container.add(breturn);
    container.add(bcount_1);
    container.add(bcount_2);
    main.setVisible(true);
  }
  public static void main(String arg[]){
    new MAIN_UI().CreateFrame();
  }
}
