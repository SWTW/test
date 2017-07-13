import javax.swing.tree.DefaultMutableTreeNode;  
  
public class CheckBoxTreeNode extends DefaultMutableTreeNode  
{  
    protected boolean isSelected;  
    Object a;
    Object a_f=null;
    boolean deltheme = false;
    public CheckBoxTreeNode()  
    {  
        this(null);  
    }  
      
    public CheckBoxTreeNode(Object userObject)  
    {  
        this(userObject, true, false);  
    }  
      
    public CheckBoxTreeNode(Object userObject, boolean allowsChildren, boolean isSelected)  
    {  
        super(userObject, allowsChildren);  
        this.isSelected = isSelected; 
        this.a = userObject;
    }  
  
    public boolean isSelected()  
    {  
        return isSelected;  
    }  
      
    public void setSelected(boolean _isSelected)  
    {  
        this.isSelected = _isSelected;  
          
        if(_isSelected)  
        {  
            // ���ѡ�У��������е��ӽ�㶼ѡ��  
            /*if(children == null)  
            {  
              CheckBoxTreeNode pNode = (CheckBoxTreeNode)parent;  
              if(pNode != null)  
              {  
                  int index = 0;  
                  for(; index < pNode.children.size(); ++ index)  
                  {  
                      CheckBoxTreeNode pChildNode = (CheckBoxTreeNode)pNode.children.get(index);  
                      if(pChildNode.isSelected()&&!((String)pChildNode.a).equals(a))  {
                        pChildNode.setSelected(false);
                      }
                  }  
              }
            }  */
            // ���ϼ�飬��������������ӽ�㶼��ѡ�У���ô�������Ҳѡ��  
            CheckBoxTreeNode pNode = (CheckBoxTreeNode)parent;  
            // ��ʼ���pNode�������ӽڵ��Ƿ񶼱�ѡ��  
            if(pNode != null)  
            {  
                int index = 0;  
                for(; index < pNode.children.size(); ++ index)  
                {  
                    CheckBoxTreeNode pChildNode = (CheckBoxTreeNode)pNode.children.get(index);  
                    if(pChildNode.isSelected())  {
                        deltheme = false;
                        pNode.setSelected(true);
                        a_f = pNode.a;
                        break;  
                    }
                }  
                /*  
                 * ����pNode�����ӽ�㶼�Ѿ�ѡ�У���ѡ�и���㣬 
                 * �÷�����һ���ݹ鷽��������ڴ˲���Ҫ���е�������Ϊ 
                 * ��ѡ�и����󣬸���㱾������ϼ��ġ� 
                 */  
                /*if(index == pNode.children.size())  
                {  
                    if(pNode.isSelected() != _isSelected)  
                        pNode.setSelected(_isSelected);  
                }  */
            }  
        }  
        else   
        {  
            /* 
             * �����ȡ������㵼���ӽ��ȡ������ô��ʱ���е��ӽ�㶼Ӧ����ѡ���ϵģ� 
             * ��������ӽ��ȡ�����¸����ȡ����Ȼ�󸸽��ȡ��������Ҫȡ���ӽ�㣬�� 
             * ����ʱ���ǲ���Ҫȡ���ӽ��ġ� 
             */  
          /*if(children != null)  
          {  
              int index = 0;  
              for(; index < children.size(); ++ index)  
              {  
                  CheckBoxTreeNode childNode = (CheckBoxTreeNode)children.get(index);  
                  if(childNode.isSelected()){  
                      childNode.setSelected(_isSelected);
                  }
              }  
          }  */
          if(parent!=null){
            CheckBoxTreeNode pNode = (CheckBoxTreeNode)parent;  
            // ��ʼ���pNode�������ӽڵ��Ƿ񶼱�ѡ��  
            if(pNode != null)  
            {  
                int index = 0;  
                for(; index < pNode.children.size(); ++ index)  
                {  
                    CheckBoxTreeNode pChildNode = (CheckBoxTreeNode)pNode.children.get(index);  
                    if(pChildNode.isSelected())  {
                        break;  
                    }
                }  
                if(index == pNode.children.size()){
                  pNode.setSelected(_isSelected);
                  deltheme = true;
                }
            }
          }
            

            // ����ȡ����ֻҪ����һ���ӽڵ㲻��ѡ�ϵģ���ô���ڵ�Ͳ�Ӧ�ñ�ѡ�ϡ�  
            /*CheckBoxTreeNode pNode = (CheckBoxTreeNode)parent;  
            if(pNode != null && pNode.isSelected() != _isSelected)  
                pNode.setSelected(_isSelected);  */
        }  
    }  
}  