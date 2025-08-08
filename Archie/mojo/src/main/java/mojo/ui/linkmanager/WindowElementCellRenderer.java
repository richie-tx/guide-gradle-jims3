package mojo.ui.linkmanager;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * <B>WindowElementCellRenderer</B> is used as a list cell renderer for
 * lists that contain a vector of WindowElement objects.
 *
 * @author R. Ratliff
 * @modelguid {79535896-4A2C-4E81-8784-B9DB26A4547A}
 */
class WindowElementCellRenderer extends JLabel implements ListCellRenderer
{
  /**
   * This is the only defined constructor for this class.
   * @modelguid {84FBB673-8AD9-4C27-922E-917F64AB6D0A}
   */
  WindowElementCellRenderer()
  {
    setOpaque(true);
  }
  
  /**
   * Return a component that has been configured to display the specified
   * value. That component's <code>paint</code> method is then called to
   * "render" the cell.  If it is necessary to compute the dimensions
   * of a list because the list cells do not have a fixed size, this method
   * is called to generate a component on which <code>getPreferredSize</code>
   * can be invoked.
   *
   * @param list The JList we're painting.
   * @param value The value returned by list.getModel().getElementAt(index).
   * @param index The cells index.
   * @param isSelected True if the specified cell was selected.
   * @param cellHasFocus True if the specified cell has the focus.
   * @return A component whose paint() method will render the specified value.
   *
   * @see <{JList}>
   * @see ListSelectionModel
   * @see ListModel
   * @modelguid {A052F0C2-1480-4E65-A75D-7D6648338904}
   */
  public Component getListCellRendererComponent(JList list,
                                                Object value,
                                                int index,
                                                boolean isSelected,
                                                boolean cellHasFocus)
  {
    setText(((WindowElement)value).getTitle());
    setBackground(isSelected ? DEFAULT_FOREGROUND : DEFAULT_BACKGROUND);
    setForeground(isSelected ? DEFAULT_BACKGROUND : DEFAULT_FOREGROUND);
    
    return this;
  }
  
  // internal class constants.
  //
	/** @modelguid {CBEBB809-84BB-4B4C-8562-94D8FBF13A75} */
  private static final Color DEFAULT_FOREGROUND = Color.black;
	/** @modelguid {36674244-2DAF-4441-9A14-6BA3FE4FF412} */
  private static final Color DEFAULT_BACKGROUND = Color.white;
}