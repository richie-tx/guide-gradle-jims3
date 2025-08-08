package mojo.ui.common;

import java.awt.Toolkit;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import javax.swing.JTextField;
import javax.swing.text.Document;

/**
 * <B>WholeNumberTextField</B> is a specialized JTextField that is a
 * change-validated text field. This field accepts digit characters only.
 *
 * @author R. Ratliff
 * @modelguid {811368D8-8F0E-474C-96DC-33EB5C6BE1A0}
 */
public class WholeNumberTextField extends JTextField 
{
  /**
   * This is the only defined constructor for this class.
   * @param value is the initial value of the text field.
   * @param columns is the width of the text field.
   * @modelguid {2BD6B1C5-F52F-44FE-BDCE-6935C4176DED}
   */
  public WholeNumberTextField(long value, int columns) 
  {
    super(columns);
    mToolkit = Toolkit.getDefaultToolkit();
    mFormatter = NumberFormat.getNumberInstance(Locale.US);
    mFormatter.setParseIntegerOnly(true);
    setValue(value);
  }

  /**
   * This method is used to return the current value of the text field.
   * @return the current value of the text field as an int.
   * @modelguid {F68F253B-20A5-4C52-AC21-0DD274FC51F6}
   */
  public long getValue() 
  {
    long value = 0L;
    try 
    {
      value = mFormatter.parse(getText()).longValue();
    } 
    catch (ParseException e) 
    {
      // This should never happen because insertString allows
      // only properly formatted data to get in the field.
      mToolkit.beep();
    }
      
    return value;
  }

  /**
   * This method is used to set the current value of the text field.
   * @param value is the new int value of the text field.
   * @modelguid {D3D18DCF-3377-47A5-B81A-706F99CE7049}
   */
  public void setValue(long value) 
  {
    setText(mFormatter.format(value));
  }

  
  /**
   * Creates the default implementation of the model
   * to be used at construction if one isn't explicitly 
   * given.  An instance of PlainDocument is returned.
   *
   * @return the default model implementation
   * @modelguid {382235B9-1091-4842-8C6F-49EAD6D7F2D8}
   */
  protected Document createDefaultModel() 
  {
    return new WholeNumberDocument();
  }
  
  /**
   * This holds the toolkit used to generate system beeps.
   * @modelguid {E6FDDE79-AB98-49E4-8B0A-08789EA72B6D}
   */
  private Toolkit mToolkit;
  
  /**
   * This holds the integer formatter used to render and parse the text
   * field characters.
   * @modelguid {63230A33-BC5F-4B32-8AB2-B86FF7D4EC74}
   */
  private NumberFormat mFormatter;
}
