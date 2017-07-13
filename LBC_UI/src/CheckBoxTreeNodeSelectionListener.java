import java.awt.event.MouseAdapter;  
import java.awt.event.MouseEvent;  
  
import javax.swing.JTree;  
import javax.swing.tree.TreePath;  
import javax.swing.tree.DefaultTreeModel;  
  
public class CheckBoxTreeNodeSelectionListener extends MouseAdapter  
{  
    private String choose=null;
    private String unchoose = null;
    @Override  
    public void mouseClicked(MouseEvent event)  
    {  
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
                if(node.isSelected()){
                  choose = (String)node.a;
                  System.out.println(choose);
                }
                else{
                  unchoose = (String)node.a;
                }
            }  
        }  
    } 
    public String getChoose(){
      return choose;
    }
    public String getUnchoose(){
      return unchoose;
    }
}  