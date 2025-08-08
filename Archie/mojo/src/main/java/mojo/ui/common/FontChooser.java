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
 * <B>FontChooser</B> provides a pane of controls designed to allow
 * a user to manipulate and select a font.
 *
 * This class provides 3 levels of API:
 * <ol>
 * <li>A static convenience method which shows a modal font-chooser
 * dialog and returns the font selected by the user.
 * <li>A static convenience method for creating a font-chooser dialog
 * where ActionListeners can be specified to be invoked when
 * the user presses one of the dialog buttons.
 * <li>The ability to create instances of FontChooser panes
 * directly (within any container).
 * <P>
 * This class was modeled after the JColorChooser class.
 *
 * @author R. Ratliff
 * @author Kurt Jacobs
 *
 * @modelguid {85090BB3-8450-4687-8D8B-8A32E2F91B73}
 */
public class FontChooser extends JPanel
{
  /**
   * This constructor is used to initialize a FontChooser with a default
   * font.
   * @modelguid {B3AEC1AF-1E96-4F14-8812-ED00DE1D0D36}
   */
  public FontChooser()
  {
    this(DEFAULT_FONT);
  }
  
  /**
   * This constructor is used to initialize a FontChooser with the specified
   * font.
   * @param initialFont is the font initially selected by the FontChooser.
   * @modelguid {438385F9-78E5-4310-A8D3-E7DB34D174DC}
   */
  public FontChooser(Font initialFont)
  {
    super(new BorderLayout());
    mFontName = initialFont.getName();
    mFontStyle = initialFont.getStyle();
    mFontSize = initialFont.getSize();
     
    add(createSelectionPanel(), BorderLayout.NORTH);
    add(createSamplePanel(), BorderLayout.CENTER);
  
    // Add listener before setting selected font so that the
    // sample will show the correct font the first time.
    // Fix for BUGS00000332
    setupNameComboBoxToUpdateNameAndSample();
    mFontNameComboBox.setSelectedItem(mFontName);
  }
  
  /**
   * This method is used to create the panel containing the font name,
   * font style, and font size combo boxes.
   * @return the font selection panel.
   * @modelguid {FC22952E-80EF-4A14-A4CF-578671041A76}
   */
  private JComponent createSelectionPanel()
  {
    JPanel panel = new JPanel();
    panel.add(createFontNamePanel());
    panel.add(createFontStylePanel());
    panel.add(createFontSizePanel());
    
    return panel;
  }
  
  /**
   * This method is used to create the panel containing the font name
   * selection combo box.
   * @return the font name combo box.
   * @modelguid {04F965B6-1E65-4D80-8BCA-1DEDE52F973E}
   */
  private JComponent createFontNamePanel()
  {
    JPanel panel = new JPanel(new BorderLayout());
    mFontNameComboBox = new JComboBox(buildNameList());
    panel.add(mFontNameComboBox, BorderLayout.CENTER);
    
    return panel;
  }
  
  /**
   * This method is invoked to generate a vector list of all available
   * font names.
   * @return list of font names.
   * @modelguid {8877EEBD-C7CC-4109-82A4-D1734A3C740B}
   */
  private Vector buildNameList()
  { 
     
   	GraphicsEnvironment ge       
   	  = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Font      fonts[]   = ge.getAllFonts();
		TreeSet   fontNames = new TreeSet();
		
		for (int i = 0; i < fonts.length; i++)  
		{
		    String name = fonts[i].getFamily();
		    
		    if (name.equalsIgnoreCase("dialog"))
 	 	       name = "Dialog";
		    if (!Character.isUpperCase(name.charAt(0)))  
		      break;
		    
		    fontNames.add(name);
		}
    return new Vector(fontNames);
  }
  
  /**
   * This method is invoked to setup the font name combo box so that
   * when a selection is made, the font name and sample text are updated.
   * @modelguid {D6B77F79-9D00-472D-92C9-6F3B52BFB0E5}
   */
  private void setupNameComboBoxToUpdateNameAndSample()
  {
    mFontNameComboBox.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        mFontName = (String)mFontNameComboBox.getSelectedItem();
        mSampleLabel.setFont(getFont());
      }
    });
  }
  
  /**
   * This method is used to create the panel containing the font style
   * selection combo box.
   * @return the font style combo box.
   * @modelguid {9A0C56AA-3246-4605-BB20-0BB224A30B33}
   */
  private JComponent createFontStylePanel()
  {
    JPanel panel = new JPanel(new BorderLayout());
    mFontStyleComboBox = new JComboBox(STYLE_LIST);
    mFontStyleComboBox.setSelectedItem(mapFontStyleToString(mFontStyle));
    setupStyleComboBoxToUpdateStyleAndSample();
    panel.add(mFontStyleComboBox, BorderLayout.CENTER);
    
    return panel;
  }
  
  /**
   * This method is used to map a font style into a string used to
   * set the current style selection in the font style combo box.
   * The font styles are ints, but the style combo box displays names
   * such as "Bold". This method will convert Font.PLAIN into "Plain".
   * @param style is the font style to be mapped.
   * @return the font style name.
   * @modelguid {D4ED0204-0FE7-473B-BED7-A7819A7C526A}
   */
  private String mapFontStyleToString(int style)
  {
    int styleIndex = 0;
    if (style == Font.PLAIN)
    {
      styleIndex = PLAIN_STYLE_INDEX;
    }
    else if (style == Font.BOLD)
    {
      styleIndex = BOLD_STYLE_INDEX;
    }
    else if (style == Font.ITALIC)
    {
      styleIndex = ITALIC_STYLE_INDEX;
    }
    else if (style == Font.BOLD + Font.ITALIC)
    {
      styleIndex = BOLD_ITALIC_STYLE_INDEX;
    }
    
    return STYLE_LIST[styleIndex];
  }
  
  /**
   * This method is invoked to setup the font style combo box so that
   * when a selection is made, the font style and sample text are updated.
   * @modelguid {52E3B803-9D74-4861-B57C-B9D0D54CE2FD}
   */
  private void setupStyleComboBoxToUpdateStyleAndSample()
  {
    mFontStyleComboBox.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        mFontStyle = 
          mapStringToFontStyle((String)mFontStyleComboBox.getSelectedItem());
        mSampleLabel.setFont(getFont());
      }
    });
  }
  
  /**
   * This method is used to map a font style name into a font style used
   * to create a Font instance. This method will map the string "Bold" into
   * the int value Font.BOLD.
   * @param styleString is the font style name to be mapped.
   * @return a font style code.
   * @modelguid {155BBBD4-8758-44C0-9D84-3EB62F2FB593}
   */
  private int mapStringToFontStyle(String styleString)
  {
    int style = Font.PLAIN;
    
    if (styleString.equals(STYLE_LIST[BOLD_STYLE_INDEX]))
    {
      style = Font.BOLD;
    }
    else if (styleString.equals(STYLE_LIST[ITALIC_STYLE_INDEX]))
    {
      style = Font.ITALIC;
    }
    else if (styleString.equals(STYLE_LIST[BOLD_ITALIC_STYLE_INDEX]))
    {
      style = Font.BOLD + Font.ITALIC;
    }
    
    return style;
  }
  
  /**
   * This method is used to create the panel containing the font size
   * selection combo box.
   * @return the font size combo box.
   * @modelguid {0637AC57-F7BD-4880-BE72-1673AFC06B5C}
   */
  private JComponent createFontSizePanel()
  {
    JPanel panel = new JPanel(new BorderLayout());
    mFontSizeComboBox = new JComboBox(SIZE_LIST);
    mFontSizeComboBox.setSelectedItem(new Integer(mFontSize));
    setupSizeComboBoxToUpdateSizeAndSample();
    panel.add(mFontSizeComboBox, BorderLayout.CENTER);
    
    return panel;
  }
  
  /**
   * This method is invoked to setup the font size combo box so that
   * when a selection is made, the font size and sample text are updated.
   * @modelguid {832C2ACC-E84F-4D16-A5ED-B0EE1A8DFA22}
   */
  private void setupSizeComboBoxToUpdateSizeAndSample()
  {
    mFontSizeComboBox.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        mFontSize = ((Integer)mFontSizeComboBox.getSelectedItem()).intValue();
        mSampleLabel.setFont(getFont());
      }
    });
  }
  
  /**
   * This method is invoked to create the pane that contains sample text
   * rendered using the current selected font.
   * @return the sample text pane.
   * @modelguid {688C1B20-6522-4DAD-B384-AEC564ACE6F0}
   */
  private JComponent createSamplePanel()
  {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(new TitledBorder("Sample"));
    mSampleLabel = new JLabel(SAMPLE_TEXT, JLabel.CENTER);
    mSampleLabel.setFont(getFont());
    panel.add(mSampleLabel, BorderLayout.CENTER);
    
    return panel;
  }
  
  /**
   * This public method is used to set the current font.
   * @param font is the font to select in the chooser.
   * @modelguid {BDEF887B-2D06-4E53-93BD-8793A107206B}
   */
  public void setFont(Font font)
  {
    mFontName = font.getName();
    mFontNameComboBox.setSelectedItem(mFontName);
    mFontStyle = font.getStyle();
    mFontStyleComboBox.setSelectedItem(mapFontStyleToString(mFontStyle));
    mFontSize = font.getSize();
    mFontSizeComboBox.setSelectedItem(new Integer(mFontSize));
  }
  
  /**
   * This method is used to obtain a font that is represented by the 
   * current chooser settings.
   * @return the selected font.
   * @modelguid {58D6CE7A-5DD1-4DFE-921D-084CB3EE189A}
   */
  public Font getFont()
  {
       
    return new Font(mFontName, mFontStyle, mFontSize);
  }
  
  /**
   * Shows a modal font-chooser dialog and blocks until the
   * dialog is hidden.  If the user presses the "OK" button, then
   * this method hides/disposes the dialog and returns the selected font.
   * If the user presses the "Cancel" button or closes the dialog without
   * pressing "OK", then this method hides/disposes the dialog and returns
   * null.
   * @param component is the parent Component for the dialog.
   * @param title is the String containing the dialog's title.
   * @param initialFont is the initial Font set when the font-chooser is 
   * shown.
   * @modelguid {AD61BA8D-5D38-4C9F-901E-B03E3BF5096B}
   */
  public static Font showDialog(Component owner,
                                String title,
                                Font initialFont)
  {
    final FontChooser chooser = new FontChooser(initialFont);
    final FontTracker ok = new FontTracker(chooser);
    JDialog dialog = createDialog(owner, title, true, chooser, ok, null);
    dialog.show();
    
    return ok.getFont();
  }
  
  /**
   * Creates and returns a new dialog containing the specified
   * FontChooser pane along with "OK", "Cancel" buttons.
   * If the "OK" or "Cancel" buttons are pressed, the dialog is
   * automatically hidden (but not disposed).
   * @param owner is the parent component for the dialog.
   * @param title is the title for the dialog.
   * @param modal is a boolean. When true, the remainder of the program
   * is inactive until the dialog is closed.
   * @param chooserPane is the font-chooser to be placed inside the dialog.
   * @param okListener is the ActionListener invoked when "OK" is pressed.
   * @param cancelListener is the ActionListener invoked when "Cancel" is 
   * pressed.
   * @modelguid {6036FF21-7E6B-4DB1-B74B-A4089EE3C6AC}
   */
  public static JDialog createDialog(Component owner,
                                     String title,
                                     boolean modal,
                                     FontChooser chooserPane,
                                     ActionListener okListener,
                                     ActionListener cancelListener)
  {
    return new FontChooserDialog(owner, 
                                 title, 
                                 modal, 
                                 chooserPane,
                                 okListener, 
                                 cancelListener);
  }
  
  /**
   * This holds the current selected font name. The name along with the
   * style and size are used to construct a Font instance.
   * @modelguid {B05080A9-A396-4F9F-A1F7-8CE75A668B7A}
   */
  private String mFontName;
  
  /**
   * This holds the combo box containing the font names.
   * @modelguid {C565F6DE-32C2-4222-A8F8-34D96E2F9A62}
   */
  private JComboBox mFontNameComboBox;
  
  /**
   * This holds the current selected font style. The name along with the
   * style and size are used to construct a Font instance.
   * @modelguid {FAB198B4-4AE2-4EA8-87C5-2519D2CCEA6F}
   */
  private int mFontStyle;
  
  /**
   * This holds the combo box containing the font styles.
   * @modelguid {6013317B-A775-4079-9367-5747AFFF463B}
   */
  private JComboBox mFontStyleComboBox;
  
  /**
   * This holds the current selected font size. The name along with the
   * style and size are used to construct a Font instance.
   * @modelguid {6629DD5B-7670-4F9F-BAAC-C63C000A46F2}
   */
  private int mFontSize;
  
  /**
   * This holds the combo box containing the font sizes.
   * @modelguid {7B46AE8C-C34B-4E85-998D-BC4E1F884256}
   */
  private JComboBox mFontSizeComboBox;
  
  /**
   * This holds the component were sample text is rendered using the current
   * font.
   * @modelguid {BE94862A-9BED-4C0E-8CFE-D9ED26F67507}
   */
  private JLabel mSampleLabel;
  
  // internal class constants
  //
	/** @modelguid {3C1F26C9-41AD-4D55-AFF0-ECD30C454C75} */
  private static final String[] STYLE_LIST = {"Plain",
                                              "Bold",
                                              "Italic",
                                              "Bold Italic"};
	/** @modelguid {56FCC590-D1F1-4DF7-BA27-CF3675B6B1F9} */
  private static final int PLAIN_STYLE_INDEX = 0;
	/** @modelguid {2354E7E4-08F8-4733-9E41-FC8DE9A34621} */
  private static final int BOLD_STYLE_INDEX = 1;
	/** @modelguid {7D0F1B56-7068-41C7-A3E4-FF11011AEB66} */
  private static final int ITALIC_STYLE_INDEX = 2;
	/** @modelguid {C57F162F-68F2-48B4-99A4-7F3FB79C1363} */
  private static final int BOLD_ITALIC_STYLE_INDEX = 3;
	/** @modelguid {C4E38F1A-3F2C-4ED9-BA6A-64D337CE5C20} */
  private static final Integer[] SIZE_LIST = {new Integer(8),
                                              new Integer(10),
                                              new Integer(12),
                                              new Integer(14),
                                              new Integer(16),
                                              new Integer(18),
                                              new Integer(20),
                                              new Integer(24)};
                                                                                           
	/** @modelguid {382FDD7F-264C-41EA-9FEA-81CA6B8BA677} */
  private static final Font DEFAULT_FONT = new Font("dialog", 
                                                    Font.PLAIN, 
                                                    14);
	/** @modelguid {1B211D48-A03C-4BE1-89EF-A54B9A4AC831} */
  private static final String SAMPLE_TEXT = "AaBbYyZz0123456789";
  
  //
  // BEGIN Unit test code and test data
  //
	/** @modelguid {08AEB1A5-28E9-4CC6-AD3F-D38CE2926525} */
  public static void main(String[] args)
  {
    try
    {
 	    javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
	  }
	  catch (Exception e) {}

    javax.swing.JFrame frame = 
                     new javax.swing.JFrame("FontChooserTest");
    frame.setSize(100, 100);
    frame.addWindowListener(new java.awt.event.WindowAdapter() 
    {
      public void windowClosing(java.awt.event.WindowEvent e)
      {
        System.exit(0);
      }
    });
    frame.setVisible(true);    
    
    JDialog owner = new JDialog(frame);
    Font font = FontChooser.showDialog(owner, "Select Font", DEFAULT_FONT);
  }
  //
  // END Unit test code and test data
  //
}


