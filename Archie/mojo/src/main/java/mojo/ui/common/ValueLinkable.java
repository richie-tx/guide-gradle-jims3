package mojo.ui.common;

/**
 * <B>SymbolLinkable</B> is the interface that all components must implement
 * if they are to receive symbol change events.
 *
 * @author R. Ratliff
 * @modelguid {642479E0-47B9-437A-846D-85177ABA560C}
 */
public interface ValueLinkable
{
  /**
   * This method is used to add the component to the specified symbol
   * change group.
   * @param group is the name of the group to which the component is 
   * joining.
   * @modelguid {7827E28E-D569-482F-9E85-224B270B85B8}
   */
  public void joinValueChangeGroup(String group);
  
  /**
   * This method is used to remove the component from the specified
   * symbol change group.
   * @param group is the name of the group from which the component is
   * quitting.
   * @modelguid {2B880975-7250-4BBE-8971-6CBBFEA35295}
   */
  public void quitValueChangeGroup(String group);
  
  /**
   * This method is used to obtain the list of all groups to which this
   * component belongs.
   * @return the list of all groups to which this component is a member.
   * @modelguid {798BEB5C-EFE4-43A5-973E-F6E901D097EB}
   */
  public String[] getValueChangeGroups();
  
  /**
   * This method is used to notify the component of a value change. Each
   * component should implement this method in such a way that this method
   * is a noop if the value parameter equals the component's current
   * symbol.
   * @param value is the new value to display.
   * @modelguid {79B0C473-FD98-4A5A-A2C1-A4BF31D623F6}
   */
  public void changeValue(String value);
}