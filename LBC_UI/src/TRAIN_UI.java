import java.awt.Color;
import java.awt.Container;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class TRAIN_UI {
  public void CreateFrame(){
    JFrame main = new JFrame();
    Container container = main.getContentPane();
    container.setBackground(Color.gray);
    container.setLayout(null);
    main.setSize(660, 590);
    main.setVisible(true);
    DefaultListModel<String> modellist = new DefaultListModel<>();
    JList<String> newslist = new JList<String>(modellist);
    JScrollPane newsscroller = new JScrollPane(newslist);
    newsscroller.setBounds(10, 70, 260, 480);
    container.add(newsscroller);
  }
}
