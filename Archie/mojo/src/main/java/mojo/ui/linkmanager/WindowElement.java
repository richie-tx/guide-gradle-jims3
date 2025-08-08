package mojo.ui.linkmanager;

import mojo.ui.common.ValueLinkable;

/**
 * <B>WindowElement</B> is the basic data type used by the LinkManagerDialog
 * lists. This data type is used as the datamodel for the LinkManagerDialog
 * as well as elements in the windows lists.
 *
 * @author R. Ratliff
 * @modelguid {07CD5B01-E6E1-49E9-9A5B-80EE959FDC0A}
 */
class WindowElement
{
  /**
   * This is the only defined constructor for this class.
   * @param title is the UTII window title.
   * @param window is the reference to the UTII window. Only windows
   * that implement the SymbolLinkable interface are managed by this package.
   * @modelguid {F125E232-F711-4523-B61B-A6117962D403}
   */
  WindowElement(String title, ValueLinkable window)
  {
    mTitle = title;
    mWindow = window;
    setInitialGroupName();
  }
  
  /**
   * This method is used to initialize the group name to the current group
   * name (if any) used by the UTII window.
   * @modelguid {B51FE083-B6AC-4B25-9CB2-3B99A6C39220}
   */
  private void setInitialGroupName()
  {
    mGroup = null;
    String[] groups = mWindow.getValueChangeGroups();
    if (groups != null && groups.length > 0)
    {
      mGroup = groups[0];
    }
  }
  
  /**
   * This method is used to return the current window title.
   * @return window title.
   * @modelguid {B090B017-DABC-4A14-96B2-C912503E9A95}
   */
  String getTitle()
  {
    return mTitle;
  }
  
  /**
   * This method is used to return the current link group name.
   * @return the current link group name. If this window is not a member
   * of a group, then this method will return null.
   * @modelguid {B2EF52B8-AC3C-4E0B-A0E1-62F741EF0EFB}
   */
  String getGroup()
  {
    return mGroup;
  }
  
  /**
   * This method is used to set the current link group name.
   * @param group is the new group name. null should be used to clear
   * the current group name.
   * @modelguid {701F7C3B-08BA-406B-BCF4-053465DDE487}
   */
  void setGroup(String group)
  {
    mGroup = group;
  }
  
  /**
   * This method is used to update the window with the current group.
   * @modelguid {6708C357-B72A-4FD9-9CCD-297A72F59D01}
   */
  void maybeUpdateWindowWithGroup()
  {
    String[] groups = mWindow.getValueChangeGroups();
    if (groups != null
        && groups.length > 0 
        && groups[0] != null 
        && !(groups[0].equals(mGroup)))
    {
      mWindow.quitValueChangeGroup(groups[0]);
      if (mGroup != null)
      {
        mWindow.joinValueChangeGroup(mGroup);
      }
    }
    else if ((groups == null || groups.length == 0 || groups[0] == null) 
             && mGroup != null)
    {
      mWindow.joinValueChangeGroup(mGroup);
    }
  }
  
  /**
   * This holds the current window title.
   * @modelguid {AD3CD66C-E523-42BA-9126-787A4419F910}
   */
  private String mTitle;
  
  /**
   * This holds the current window reference.
   * @modelguid {64C2E324-75A8-44EA-AEB7-9FAE33F99832}
   */
  private ValueLinkable mWindow;
  
  /**
   * This holds the current link group name.
   * @modelguid {13AD290A-FD31-4E8E-9495-704FA6696F04}
   */
  private String mGroup;
}