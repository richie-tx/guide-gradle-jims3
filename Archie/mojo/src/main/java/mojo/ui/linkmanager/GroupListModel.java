package mojo.ui.linkmanager;

import java.util.Iterator;
import java.util.Vector;
import javax.swing.AbstractListModel;

/**
 * <B>GroupListModel</B> is a list model that implements methods for
 * changing the list elements.
 *
 * @author R. Ratliff
 * @modelguid {39E3F81B-06A9-4ABD-BF11-31458EA9B8B9}
 */
class GroupListModel extends AbstractListModel
{
  /**
   * This is the only defined constructor for this class.
   * @modelguid {3AD1E6FA-FE09-4E8C-B7F0-2A7600CD284F}
   */
  GroupListModel()
  {
    mGroups = new Vector();
  }
  
  /**
   * This method is used to add the specified group to the group list.
   * @param group is the new group to add.
   * @return true if the group was added and false if the group already 
   * exists in the list.
   * @modelguid {30BD8F60-278F-47E9-8813-10934D67C65B}
   */
  boolean addGroup(String group)
  {
    boolean groupAdded = true;
    if (group != null)
    {
      Iterator groups = mGroups.iterator();
      while (groups.hasNext())
      {
        if (group.equals((String)groups.next()))
        {
          groupAdded = false;
        }
      }
      if (groupAdded)
      {
        mGroups.addElement(group);
        fireIntervalAdded(this, 0, getSize());
      }
    }
    
    return groupAdded;
  }
  
  /**
   * This method is used to remove a group from the list of groups.
   * @param group is the group to remove.
   * @return true if the group was removed and false if the group does
   * not exist in the group list.
   * @modelguid {2123B431-19BD-4C04-9EB0-E04303CED8FC}
   */
  boolean removeGroup(String group)
  {
    boolean groupRemoved = false;
    Iterator groups = mGroups.iterator();
    while (groups.hasNext())
    {
      if (group.equals((String)groups.next()))
      {
        groups.remove();
        groupRemoved = true;
        
      }
    }
    if (groupRemoved)
    {
       mGroups.removeElement(group);
       fireIntervalRemoved(this, 0, getSize());
    }
    
    return groupRemoved;
  }
  
  // implement ListModel methods
  //
  
  /** 
   * Returns the length of the list.
   * @return the current list of the list.
   * @modelguid {BD565AA6-D90E-4AD5-960E-B1C7DC475EAC}
   */
  public int getSize()
  {
    return mGroups.size();
  }

  /**
   * Returns the value at the specified index. 
   * @param index is the list index.
   * @return the element found at list(index);
   * @modelguid {D442F2A1-AD47-4F04-B6F2-344D8EB0E20E}
   */
  public Object getElementAt(int index)
  {
    return mGroups.elementAt(index);
  }
  
  /**
   * This holds the current list of groups.
   * @modelguid {FCAEE746-A0A9-4B6F-B359-F8CB71C302B6}
   */
  private Vector mGroups;
}