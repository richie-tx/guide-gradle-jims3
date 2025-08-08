package mojo.ui.common;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.util.Vector;
import java.util.Collections;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;

import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * <B>FontChooserDialog</B> is a specialized JDialog that contains a
 * FontChooser and control buttons.
 *
 * @author R. Ratliff
 * @modelguid {B2FCEDB4-3323-4A7D-9BBA-7239320AB39F}
 */
class FontChooserDialog extends JDialog
{
  /**
   * This is the only define constructor for this class.
   * @param owner is the parent component for the dialog.
   * @param title is the title for the dialog.
   * @param modal is a boolean. When true, the remainder of the program
   * is inactive until the dialog is closed.
   * @param chooserPane is the font-chooser to be placed inside the dialog.
   * @param okListener is the ActionListener invoked when "OK" is pressed.
   * @param cancelListener is the ActionListener invoked when "Cancel" is 
   * pressed.
   * @modelguid {0354F14C-5DE7-44C1-AD5A-89FEC11A81B4}
   */
  public FontChooserDialog(Component owner, 
                           String title, 
                           boolean modal, 
                           FontChooser chooserPane,
                           ActionListener okListener, 
                           ActionListener cancelListener)
  {
    super(JOptionPane.getFrameForComponent(owner), title, modal);
    mOkListener = okListener;
    mCancelListener = cancelListener;
    mHideListener = createHideListener();
    chooserPane.setBorder(new TitledBorder("Current"));
    getContentPane().add(chooserPane, BorderLayout.CENTER);
    getContentPane().add(createControlButtonPanel(), BorderLayout.SOUTH);
    setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
  }
  
  /**
   * This method is used to create a listener that will hide the dialog
   * when notified.
   * @return the action listener that will hide the dialog.
   * @modelguid {37938674-7EC2-422E-827E-2E7469D6F2A3}
   */
  private ActionListener createHideListener()
  {
    return new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        hide();
      }
    };
  }
  
  /**
   * This method is used to create a set of control buttons used to
   * accept or reject the changes to the current font.
   * @return the panel of control buttons.
   * @modelguid {A31B874B-C411-4D2A-A6D7-5EFF0E245A22}
   */
  private JComponent createControlButtonPanel()
  {
    JPanel container = new JPanel();
    JPanel panel = new JPanel(new GridLayout(1, 2, 10, 0));
    JButton button = new JButton(OK_LABEL);
    addOkListeners(button);
    panel.add(button);
    button = new JButton(CANCEL_LABEL);
    addCancelListeners(button);
    panel.add(button);
    container.add(panel);
    
    return container;
  }
  
  /**
   * This method is used to register listeners for the ok button.
   * @param ok is the button to which listeners are added.
   * @modelguid {DEEFA052-852B-4EFD-A4AD-36289804E20D}
   */
  private void addOkListeners(JButton ok)
  {
    // Don't swap the addActionListener calls. This implementation depends 
    // on the implementation of AbstractButton. The AbstractButton class
    // invokes actionPerformed on its listeners in the reverse order in 
    // which they are registered. We need the ok listener to process the
    // data before the hide listener allows a return from show().
    //
    ok.addActionListener(mHideListener);
    if (mOkListener != null)
    {
      ok.addActionListener(mOkListener);
    }
  }
  
  /**
   * This method is used to register listeners for the cancel button.
   * @param cancel is the button to which listeners are added.
   * @modelguid {2AF1A0BD-2C24-49EF-AE4C-E2949B9FC14A}
   */
  private void addCancelListeners(JButton cancel)
  {
    // Don't swap the addActionListener calls. This implementation depends 
    // on the implementation of AbstractButton. The AbstractButton class
    // invokes actionPerformed on its listeners in the reverse order in 
    // which they are registered. We need the cancel listener to process the
    // data before the hide listener allows a return from show().
    //
    cancel.addActionListener(mHideListener);
    if (mCancelListener != null)
    {
      cancel.addActionListener(mCancelListener);
    }
  }
  
  /**
   * This holds the ok listener supplied during construction. This listener
   * is invoke first and is user implementation specific.
   * @modelguid {DCAC82A3-6912-45CE-96E7-E302503FF933}
   */
  private ActionListener mOkListener;
  
  /**
   * This holds the cancel listener supplied during construction. This 
   * listener is invoke first and is user implementation specific.
   * @modelguid {E344D9DB-F013-4AC2-A5BA-C96939CBC59F}
   */
  private ActionListener mCancelListener;
  
  /**
   * This holds the listenter used to hide the dialog when the user has made
   * their font selection.
   * @modelguid {B14133D3-0291-44DA-BB16-BF8A21AAFCD7}
   */
  private ActionListener mHideListener;
  
  // internal class constants
  //
	/** @modelguid {DC657C6E-8630-4FFB-ACF3-C6B8039806D0} */
  private static final int DIALOG_WIDTH = 400;
	/** @modelguid {442CD165-8D12-4F0D-84CF-6057FD9FFAEB} */
  private static final int DIALOG_HEIGHT = 200;
	/** @modelguid {21852F60-979A-480E-929A-D4CB552B1FBF} */
  private static final String OK_LABEL = "OK";
	/** @modelguid {39CE08EA-C05F-4491-95B9-2751420BFAEE} */
  private static final String CANCEL_LABEL = "Cancel";
}


