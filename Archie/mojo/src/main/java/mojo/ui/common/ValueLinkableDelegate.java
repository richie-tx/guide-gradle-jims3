package mojo.ui.common;

/**
 * <B>SymbolLinkableDelegate</B> is an implementation of the
 * the SymbolLinkable class that can be used by classes that wish to
 * use the functionality of AbstractValueLinkable, but already extend
 * a class such as JDialog. The top level dialog can operate like so:
 * <p>
 * <code>
 * public class MyDialog extends JDialog implements ValueLinkable
 * {
 *   private ValueLinkableDelegate mValueChangeDelegate;
 *
 *   public MyDialog() 
 *   {
 *     mValueChangeDelegate = new ValueLinkableDelegate(this);
 *   }
 *
 *   public void joinValueChangeGroup(String group)
 *   {
 *     mValueChangeDelegate.joinValueChangeGroup(group);
 *   }
 *
 *   public void quitValueChangeGroup(String group) 
 *   {
 *     mValueChangeDelegate.quitValueChangeGroup(group);
 *   }
 *
 *   public String[] getValueChangeGroups()
 *   {
 *     return mValueChangeDelegate.getValueChangeGroups();
 *   }
 *
 *   public void changeValue(String Value)
 *   {
 *     // do what ever is necessary to process a new Value
 *     mValueChangeDelegate.notifyAllMembersOfValueChange(String Value);
 *   }
 * }
 * </code>
 *
 * @author R. Ratliff
 * @modelguid {34229602-2ED5-4FDF-BDDE-794DA957C075}
 */
public class ValueLinkableDelegate extends AbstractValueLinkable
{
  /**
   * This is the only define constructor for this class.
   * @param callback is a ValueLinkable instance that is notified
   * when this instance has its changeValue method invoked.
   * @modelguid {5530F4B0-2DDA-4894-AAEA-8EC8FC24B3D7}
   */
  public ValueLinkableDelegate(ValueLinkable callback)
  {
    super();
    mCallback = callback;
  }
  
  /**
   * This method is used to notify the component of a Value change. Each
   * component should implement this method in such a way that this method
   * is a noop if the Value parameter equals the component's current
   * Value.
   * @param Value is the new Value to display.
   * @modelguid {976289F8-7CAB-42FD-B4E5-2C1213236704}
   */
  public void changeValue(String value)
  {
    mCallback.changeValue(value);
  }
  
  /**
   * This holds the instance that is notified when changeValue is called.
   * @modelguid {A9FB331A-CF6F-4438-B111-AC7F4CE1592F}
   */
  private ValueLinkable mCallback;
}