package mojo.ui.common;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;

/**
 * <B>PopupMenuListener</B> is a MouseListener that will display
 * the popup list when called.
 * @modelguid {B75DEE36-C5C9-444D-8A57-2A299478BE73}
 */
public class PopupMenuListener extends MouseAdapter
{
  /**
   * This is the only defined constructor for this class.
   * @param popup is the popup menu to display when this listener is called.
   * @modelguid {9CAB1CBA-7334-44EB-AABF-9D8A8C917313}
   */
  public PopupMenuListener(JPopupMenu popup)
  {
    mPopup = popup;
  }
  
  /**
   * Invoked when a mouse button has been pressed on a component.
   * @modelguid {02E81146-60F0-4BFE-8349-93B5619B02BF}
   */
  public void mousePressed(MouseEvent e) 
  {
    maybeShowPopup(e);
  }

  /**
   * Invoked when a mouse button has been released on a component.
   * @modelguid {E800F98D-4E0E-4933-88D3-D9AEE4244C2C}
   */
  public void mouseReleased(MouseEvent e)
  {
    maybeShowPopup(e);
  }

  /**
   * This method is called whenever a mouse event is processed to determine
   * if the popup should be displayed.
   * @modelguid {9A2AC272-CEDA-4711-9626-06104DC5B3C7}
   */
  private void maybeShowPopup(MouseEvent e) 
  {
    if (e.isPopupTrigger()) 
    {
      mPopup.show(e.getComponent(), e.getX(), e.getY());
    }
  }
  
  /**
   * This is the popup menu to display when a mouse event is processed.
   * @modelguid {0E1E9111-EC0D-4946-9124-FE5FA20667D3}
   */
  private JPopupMenu mPopup;
}