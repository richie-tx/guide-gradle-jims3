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
 * <B>FontTracker</B> is a class used by the FontDialog to create
 * an ok listener that will return the seleted font when the ok button
 * is clicked.
 *
 * @author R. Ratliff
 * @modelguid {B6078E07-6ACC-45D1-B50D-AB94C5FA302D}
 */
class FontTracker implements ActionListener
{
  /**
   * This is the only define constructor for this class.
   * @param chooser is the font chooser to obtain fonts from.
   * @modelguid {26844FE4-5C7F-4CC2-8D18-47C4B21ED2E8}
   */
  public FontTracker(FontChooser chooser) 
  {
    mChooser = chooser;
    mFont = null;
  }

  /**
   * Invoked when an action occurs.
   * @modelguid {70E9DF42-1F10-469A-9C37-BC428DDDFF4A}
   */
  public void actionPerformed(ActionEvent e) 
  {
    mFont = mChooser.getFont();
  }

  /**
   * This method is used to obtain the font that was selected when the
   * button was clicked. If the button was not clicked, then this method
   * will return null.
   * @return the selected font.
   * @modelguid {7A359D6A-AC91-469D-B298-D390C986233F}
   */
  public Font getFont() 
  {
    return mFont;
  }
  
  /**
   * This holds the font chooser used to obtain the selected font.
   * @modelguid {7F661DFD-E59B-4C4E-B40F-380D1D91896D}
   */
  private FontChooser mChooser;
  
  /**
   * This holds the selected font.
   * @modelguid {4C97AD9F-7131-41A2-AF24-54CE5CB40504}
   */
  private Font mFont;
}