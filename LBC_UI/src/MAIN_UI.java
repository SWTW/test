import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.tree.DefaultTreeModel;

public class MAIN_UI {
  String address;
  Vector<newsItem> NewsList = new Vector<newsItem>();
  public void CreateFrame(){
    JFrame main = new JFrame();
    Container container = main.getContentPane();
    container.setBackground(Color.gray);
    container.setLayout(null);    
    main.setSize(1200, 620);
    main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    //菜单栏
    JMenuBar menubar = new JMenuBar();
    JMenu menu = new JMenu("文件");
    JMenuItem file = new JMenuItem("导入文件");
    JMenuItem filesave = new JMenuItem("保存记录");
    JMenuItem fileload = new JMenuItem("读取记录"); 
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
          NewsList.addAll(newslist.returnvector(address));
          newsItem value = null;
          if(NewsList!=null){
            int size = NewsList.size();
            for (int i=0; i<size; i++) {
                value = NewsList.get(i);
                modellist.addElement(value.theTitle);
            }
          }
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
    JButton before = new JButton("before");
    JButton next = new JButton("next");
    
    Vector<String> a = new Vector<String>();
    a.add("1");
    a.add("2");
    a.add("3");
    
    //随机访问vector
    
    
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
              dlm.addElement(value.theTitle);
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
    

    bdel.setBounds(1000,10,80,20);
    bdel.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        // 
      }
    });

    breturn.setBounds(1090,10,80,20);
    breturn.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        // 
      }
    });

    bcount_1.setBounds(1000,40,80,20);
    bcount_1.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        // 
      }
    });

    bcount_2.setBounds(1090,40,80,20);
    bcount_2.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        // 
      }
    });
    
    before.setBounds(190,10,80,50);
    before.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        // 
      }
    });
    
    next.setBounds(910,10,80,50);
    next.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        // 
      }
    });
    
    
    JTextPane shownew = new JTextPane();
    shownew.setBounds(280,10,620,540);
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
    //设置正文的格式
    SimpleAttributeSet cSet = new SimpleAttributeSet();   
    StyleConstants.setAlignment(cSet, StyleConstants.ALIGN_CENTER);  
    StyleConstants.setFontSize(cSet, 16); 
    
    JScrollPane newsscroller = new JScrollPane(newslist);
    newsscroller.setBounds(10, 70, 260, 480);
    //shownew.set
    
    newslist.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
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
            }
          }
        }
      }
    });
    
    //JTree
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
    
    DefaultTreeModel model = new DefaultTreeModel(themes);
    nodes.addMouseListener(new CheckBoxTreeNodeSelectionListener()); 
    nodes.setModel(model);
    nodes.setCellRenderer(new CheckBoxTreeCellRenderer()); 
    JScrollPane scroll = new JScrollPane(nodes);
    scroll.setBounds(910,70,260,480);
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
    container.add(before);
    container.add(next);
    main.setVisible(true);
  }
  public static void main(String arg[]){
       new MAIN_UI().CreateFrame();
  }
}
