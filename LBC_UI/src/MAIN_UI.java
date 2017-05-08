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
    //�˵���
    JMenuBar menubar = new JMenuBar();
    JMenu menu = new JMenu("�ļ�");
    JMenuItem file = new JMenuItem("�����ļ�");
    JMenuItem filesave = new JMenuItem("�����¼");
    JMenuItem fileload = new JMenuItem("��ȡ��¼"); 
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
    
    Vector<String> a = new Vector<String>();
    a.add("1");
    a.add("2");
    a.add("3");
    
    //�������vector
    
    
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
      }
    });
    
    bcount.setBounds(100,40,80,20);
    bcount.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        //ͳ�� 
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
    //���ñ���ĸ�ʽ
    SimpleAttributeSet aSet = new SimpleAttributeSet();   
    StyleConstants.setAlignment(aSet, StyleConstants.ALIGN_CENTER);  
    StyleConstants.setFontSize(aSet, 19); 
    StyleConstants.setBold(aSet,true);
    //�������ߺ����ڵĸ�ʽ
    SimpleAttributeSet bSet = new SimpleAttributeSet();   
    StyleConstants.setAlignment(bSet, StyleConstants.ALIGN_CENTER);  
    StyleConstants.setFontSize(bSet, 15); 
    //�������ĵĸ�ʽ
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
                docs.insertString(docs.getLength(),value.getTheTitle()+'\n', aSet);//���ı�����׷��
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
