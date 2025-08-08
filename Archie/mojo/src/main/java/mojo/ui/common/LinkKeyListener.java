package mojo.ui.common;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * <B>SymbolKeyListener</B> is a KeyListener that ensures characters typed
 * into a text field are legal stock ticker symbols.
 * <P>
 * All rules pertaining to the legal formation of a stock ticker symbol
 * string should be implemented here.
 *
 * @author R. Ratliff
 * @modelguid {5A06AD31-F38E-4975-A9C8-392D92934E79}
 */
public class LinkKeyListener extends KeyAdapter
{
  /**
   * Invoked when a key has been typed.
   * This event occurs when a key press is followed by a key release.
   * @param event contains the key press information, such as character
   * typed.
   * @modelguid {911745B7-86E3-4726-B78B-63C712A0762C}
   */  
  public void keyTyped(KeyEvent event)
  {
    char c = event.getKeyChar();
    if (tooLong(event))
    {
      warnAndConsume(event);
    }
    else if (illegalCharacter(c))
    {
      warnAndConsume(event);
    }
    else if (Character.isLowerCase(c))
    {
      event.setKeyChar(Character.toUpperCase(c));
    }
  }
  
  /**
   * This method is invoked to determine if the adding the current character
   * would make the symbol string longer than the allowable length.
   * @param event contains the text field containing the current symbol text.
   * @return true if adding a character would make the symbol exceed the
   * maximum length. 
   * @modelguid {574801E3-E14D-42AB-ABA4-28A326973A79}
   */
  private boolean tooLong(KeyEvent event)
  {
    boolean tooLong = false;
    JTextField tf = (JTextField)event.getSource();
    if (!Character.isISOControl(event.getKeyChar())
        && tf.getText().length() >= MAX_LENGTH)
    {
      tooLong = true;
    }
    
    return tooLong;
  }
  
  /**
   * This method is invoked to issue a warning sound and consume the
   * character typed. The user will not see consumed characters, because
   * this listener has access to the event data before it is displayed.
   * @param event is the key pressed event containing the key data.
   * @modelguid {4D322FF4-940B-441B-A77B-1E86165B0F6D}
   */
  private void warnAndConsume(KeyEvent event)
  {
    Toolkit.getDefaultToolkit().beep();
    event.consume();
  }
  
  /**
   * This method is invoked to determine if the character is a legal
   * ticker symbol character.
   * @param c is the character to validate.
   * @return true if the character is illegal.
   * @modelguid {0CA1B897-A3AA-4E99-8688-5DC96D2D717F}
   */
  private boolean illegalCharacter(char c)
  {
    boolean illegalCharacter = false;
    Character typedCharacter = new Character(c);
    if (!Character.isISOControl(c) 
        && !Character.isLetterOrDigit(c)
        && !typedCharacter.equals(PLUS_SIGN)
        && !typedCharacter.equals(DOLLAR_SIGN)
        && !typedCharacter.equals(SLASH))
    {
        illegalCharacter = true;
    }
    
    return illegalCharacter;
  }
  
  // internal class constants
  //
  // Make allowable symbol length 12 as per request from N.Y
	/** @modelguid {3E5E232D-99F4-4B81-AD25-B15000C910EA} */
  private static final int MAX_LENGTH = 12;
	/** @modelguid {F32F72C3-D887-4DEF-843D-F98FF73EE3A4} */
  private static final Character PLUS_SIGN = new Character('+');
	/** @modelguid {F2351D7D-68D9-4908-8C03-FCB9BF140645} */
  private static final Character DOLLAR_SIGN = new Character('$');
	/** @modelguid {EFA09CE0-2A04-434C-901E-8D366F75D413} */
  private static final Character SLASH = new Character('/');
}