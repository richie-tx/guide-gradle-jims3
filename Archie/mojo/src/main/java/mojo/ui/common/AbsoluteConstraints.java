/** AbsoluteConstraints.java
* 
* Copyright (C) 1998  NetBeans, Inc.
* 
* This program is free software; you can redistribute it and/or
* modify it under the terms of the GNU General Public License
* as published by the Free Software Foundation; either version 2
* of the License, or (at your option) any later version.
* 
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
* 
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA. 
*/

package mojo.ui.common;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.peer.FontPeer;
 
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.text.JTextComponent;
import java.util.Hashtable;
import java.io.Serializable;


/** An object that encapsulates position and (optionally) size for
* Absolute positioning of components.
*
* @see <{AbsoluteLayout}>
* @version 1.01, Aug 19, 1998
* @modelguid {93538709-6F37-4145-BEC7-A9D90732F15C}
*/
public class AbsoluteConstraints implements Serializable {
  /** generated Serialized Version UID 
   * @modelguid {C5E166E3-8F4E-4428-9A55-4A77F4A17367}
   */
  static final long serialVersionUID = 5261460716622152494L;
  
  // The FontMetric class has a method called getMaxAdvance that returns the
  // maximum width a character in a particular font would use but this value
  // is too large as it includes characters that we are not interested in
  // By controling the character set and calculating the largest character
  // in the set, we can obtain a smaller size field.
	/** @modelguid {298F5222-F831-4123-A080-B666D8CE3DE3} */
  public static final String MARKET_VALUE_CHARACTER_SET_ID="Market Char Set";
	/** @modelguid {4F5CE190-2855-4021-A852-C696025511D0} */
  public static final String ALPHA_VALUE_CHARACTER_SET_ID= "Alpha Char Set";
	/** @modelguid {F75F0B79-9BAC-4698-8019-1850BEBEA1B0} */
  public static final String PLUS_MINUS_CHARACTER_SET_ID="PlusMinus Set";
	/** @modelguid {9DE55E77-4373-492F-8A22-7C32BFF5EEC1} */
  public static final String CURRENCY_CHARACTER_SET_ID="Currency Set";
  
	/** @modelguid {70F17AF5-B97B-4BD5-A578-62E2E08F87BF} */
  private static final char[] MARKET_VALUE_CHARACTER_LIST={'0','1','2','3','4','5','6','7','8','9','/'};
	/** @modelguid {DBA85F40-57EE-4600-8CDE-9A34346E6363} */
  private static final char[] CURRENCY_CHARACTER_LIST={'0','1','2','3','4','5','6','7','8','9','$','(',')',','};
	/** @modelguid {491B556C-4A80-4A21-8D02-FE7D359D9FBC} */
  private static final char[] ALPHABETIC_CHARACTER_LIST = 
    {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z' ,'$'};
	/** @modelguid {83286269-D1B3-4E2F-B26A-A9B5CC4F8E04} */
  private static final char[] PLUS_MINUS_CHARACTER_LIST= {'+','-'};
  
  
  // Contains a list of character sets of interest.        
	/** @modelguid {C59C3618-7E54-4455-A33D-8A0871291A5C} */
  static Hashtable mCharacterSetVector;                                      
  static {           
    mCharacterSetVector=new Hashtable();
    mCharacterSetVector.put(MARKET_VALUE_CHARACTER_SET_ID,MARKET_VALUE_CHARACTER_LIST);
    mCharacterSetVector.put(ALPHA_VALUE_CHARACTER_SET_ID,ALPHABETIC_CHARACTER_LIST); 
    mCharacterSetVector.put(PLUS_MINUS_CHARACTER_SET_ID,PLUS_MINUS_CHARACTER_LIST);
    mCharacterSetVector.put(CURRENCY_CHARACTER_SET_ID,CURRENCY_CHARACTER_LIST);
  }
  
  /** The X position of the component 
   * @modelguid {D1331A6C-0FA9-450D-AB3B-B8D1184BBE3B}
   */
  public int x;
  /** The Y position of the component 
   * @modelguid {B737FA6C-9C9D-4D4B-AD03-09FA62FEAC8A}
   */
  public int y;
  /** The width of the component or -1 if the component's preferred width should be used 
   * @modelguid {6FA6B17D-411F-4E16-9896-19843D1C8676}
   */
  public int width = -1;
  /** The height of the component or -1 if the component's preferred height should be used 
   * @modelguid {31E5EA4A-4DBC-43B4-96C8-B59A538E7F19}
   */
  public int height = -1;

  /** Creates a new AbsoluteConstraints for specified position.
  * @param pos The position to be represented by this AbsoluteConstraints
  * @modelguid {8BD6EED2-C253-4D64-A20E-85D785B66562}
  */
  public AbsoluteConstraints(Point pos) {
    this (pos.x, pos.y);
  }

  /** Creates a new AbsoluteConstraints for specified position.
  * @param x The X position to be represented by this AbsoluteConstraints
  * @param y The Y position to be represented by this AbsoluteConstraints
  * @modelguid {E9894295-D8A9-43AF-AE7E-9B539D0AC1B2}
  */
  public AbsoluteConstraints(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /** Creates a new AbsoluteConstraints for specified position and size.
  * @param pos  The position to be represented by this AbsoluteConstraints
  * @param size The size to be represented by this AbsoluteConstraints or null
  *             if the component's preferred size should be used
  * @modelguid {60160511-4F44-415B-9149-09ADF1C6A59B}
  */
  public AbsoluteConstraints(Point pos, Dimension size) {
    this.x = pos.x;
    this.y = pos.y;
    if (size != null) {
      this.width = size.width;
      this.height = size.height;
    }
  }

  /** Creates a new AbsoluteConstraints for specified position and size.
  * @param x      The X position to be represented by this AbsoluteConstraints
  * @param y      The Y position to be represented by this AbsoluteConstraints
  * @param width  The width to be represented by this AbsoluteConstraints or -1 if the 
  *               component's preferred width should be used  
  * @param height The height to be represented by this AbsoluteConstraints or -1 if the
  *               component's preferred height should be used  
  * @modelguid {E5D663B3-F1EF-44BA-93F6-6B51F3489F9F}
  */
  public AbsoluteConstraints(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }
  
  /** Creates a new AbsoluteConstraints for specified position and size.
  * @param x            The X position to be represented by this AbsoluteConstraints
  * @param y            The Y position to be represented by this AbsoluteConstraints
  * @param fontMetrics  The metrics of a given font.  Used in calculating the  
  *                     necessary field width/height for a component.
  * @param displayText  The text that is to be displayed in a field.
  *
  * @modelguid {126052DB-418F-4F61-9AB9-894B9A9DB166}
  */
  public AbsoluteConstraints(int x, int y, FontMetrics fontMetrics, String displayText) {
    this.x = x;
    this.y = y;
    this.width = fontMetrics.stringWidth(displayText);
    this.height = fontMetrics.getAscent() + fontMetrics.getDescent();
  }
  
  
 /** Creates a new AbsoluteConstraints for specified position and size.
  * @param x            The X position to be represented by this AbsoluteConstraints
  * @param y            The Y position to be represented by this AbsoluteConstraints
  * @param fontMetrics  The metrics of a given font.  Used in calculating the  
  *                     necessary field height for a component.
  * @param maxFieldSize The field size as specified by the user of this class
  *
  * @modelguid {D2FEE4F4-B3F3-4AC9-A521-F81F327093FC}
  */
  public AbsoluteConstraints(int x, int y, FontMetrics fontMetrics,int maxFieldSize) {
    this.x = x;
    this.y = y;
    this.width = maxFieldSize;
    this.height = fontMetrics.getAscent() + fontMetrics.getDescent();
  }
  

  /**
   * This method returns the maximum width needed for a given
   * font for a given character set.
   * 
   * @param fontMetrics Metrics for the font that is to be used
   * @param characterSetKey key into mCharSet vector
   * @return The maximum character width needed for a particular font.
   * @modelguid {A3317284-D099-45D4-9730-44E6617C4194}
   */
  static public int getMaxCharacterAdvance(FontMetrics fontMetrics,String characterSetKey) {
     int maxWidth=0;
     int charWidth;
    
     char characterSet[] = (char []) mCharacterSetVector.get(characterSetKey);
     for (int i=0;i<characterSet.length;++i) {
        charWidth = fontMetrics.charWidth(characterSet[i]);
        if (charWidth > maxWidth)
          maxWidth=charWidth;
     }
    
     return maxWidth; 
  }
    
  /** @return The X position represented by this AbsoluteConstraints 
   * @modelguid {2AC0A7DA-954E-44F2-8AB2-9928E67EAF64}
   */
  public int getX () {
    return x;
  }

  /** @return The Y position represented by this AbsoluteConstraints 
   * @modelguid {E13F4D30-A69A-4C83-AA27-C88891EBF2C7}
   */
  public int getY () {
    return y;
  }

  /** @return The width represented by this AbsoluteConstraints or -1 if the 
  * component's preferred width should be used 
  * @modelguid {EC6BC7E8-6AE0-4563-8350-67E3C92709E0}
  */
  public int getWidth () {
    return width;
  }

  /** @return The height represented by this AbsoluteConstraints or -1 if the 
  * component's preferred height should be used 
  * @modelguid {EA73F7AC-856B-4B12-BC1F-CDF8BB4F7115}
  */
  public int getHeight () {
    return height;
  }

	/** @modelguid {AC9C0FEA-A55C-4D94-810E-775E0399DFFF} */
  public String toString () {
    return super.toString () +" [x="+x+", y="+y+", width="+width+", height="+height+"]";
  }

}

