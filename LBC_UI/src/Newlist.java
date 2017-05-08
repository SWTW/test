import javax.swing.AbstractListModel;

public class Newlist extends AbstractListModel {
  private String[] contents={"1","2","3"};
  public Object getElementAt(int x){
    if(x<contents.length)
      return contents[x++];
    else
      return null;
  }
  public int getSize(){
    return contents.length;
  }
}
