package mojo.ui.common;

import java.awt.Toolkit;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;

/**
  * <B>WholeNumberDocument</B> is a specialization of the PlainDocument
  * class that only accepts digit characters.
  * @modelguid {261328CA-FFFF-4A52-BF4C-DF2B433AADCD}
  */
public class WholeNumberDocument extends PlainDocument 
{
	/** @modelguid {CEA2469C-537C-4F7C-99FC-1E71146CDAEE} */
  public WholeNumberDocument()
  {
    mToolkit = Toolkit.getDefaultToolkit();
    mMax = 0;
  }
  
	/** @modelguid {4925EDD9-270A-46DE-A690-3D97DFB3F6E2} */
  public void setMax(long max)
  {
    mMax = max;
  }
  
  /**
    * Inserts a string of content.  This will cause a DocumentEvent
    * of type DocumentEvent.EventType.INSERT to be sent to the
    * registered DocumentListers, unless an exception is thrown.
    * The DocumentEvent will be delivered by calling the
    * insertUpdate method on the DocumentListener.
    * The offset and length of the generated DocumentEvent
    * will indicate what change was actually made to the Document.
    * <p align=center><img src="doc-files/Document-insert.gif">
    * <p>
    * If the Document structure changed as result of the insertion,
    * the details of what Elements were inserted and removed in
    * response to the change will also be contained in the generated
    * DocumentEvent.  It is up to the implementation of a Document
    * to decide how the structure should change in response to an
    * insertion.
    * <p>
    * If the Document supports undo/redo, an UndoableEditEvent will
    * also be generated.  
    *
    * @param offset the offset into the document to insert the content >= 0.
    *    All positions that track change at or after the given location 
    *    will move.  
    * @param str the string to insert
    * @param a the attributes to associate with the inserted
    *   content.  This may be null if there are no attributes.
    * @exception BadLocationException  the given insert position is not a valid 
    * position within the document
    * @see <{DocumentEvent}>
    * @see <{DocumentListener}>
    * @see <{UndoableEditEvent}>
    * @see <{UndoableEditListener}>
    * @modelguid {3A261B9C-A39C-4933-A695-288A5B898497}
    */
  public void insertString(int offset, 
                           String str,
                           AttributeSet a) throws BadLocationException 
  {
    char[] source = str.toCharArray();
    char[] result = new char[source.length];
    int j = 0;

    for (int i = 0; i < result.length; i++) 
    {
      if (Character.isDigit(source[i]))
      {
        result[j++] = source[i];
      }
      else if (source[i] != ',') 
      {
        mToolkit.beep();
      }
    }
    if (resultingStringLessThanMax(String.copyValueOf(result, 0, j)))
    {
      super.insertString(offset, new String(result, 0, j), a);
    }
  }
  
	/** @modelguid {B8FB03C6-AE48-4431-A52C-288D39781601} */
  private boolean resultingStringLessThanMax(String insert)throws 
    BadLocationException
  {
    boolean resultingStringLessThanMax = true;
    if (mMax > 0)
    {
      String value = getText(0, getLength()) + insert;
      if (Long.parseLong(value) > mMax)
      {
        resultingStringLessThanMax = false;
      }
    }
    
    return resultingStringLessThanMax;
  }
  
	/** @modelguid {EDEA9AC7-3AE2-4D46-8826-33DE2BF9CF31} */
  private Toolkit mToolkit;
	/** @modelguid {78AB9E49-728E-4FFA-8799-8DAFF3C797C7} */
  private long mMax;
}
