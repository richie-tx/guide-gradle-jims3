package mojo.tools.code;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import mojo.tools.code.KeyWord;

/**
 * This class is the base-class for any Java code-level element.
 * 
 * @author Saleem Shafi
 */
public abstract class CodeElement implements Acceptable
{
    /**
     * Helper method that indents the given String of code by one block.
     * 
     * @param String Java code to indent
     */
    protected static String indent(String aBlock)
    {
        StringBuffer lText = new StringBuffer();
        StringTokenizer lLines = new StringTokenizer(aBlock, KeyWord.NEWLINE);
        while (lLines.hasMoreTokens())
        {
            String line = lLines.nextToken();
            if (line.trim().length() > 0)
            {
                lText.append(KeyWord.INDENTION).append(line).append(KeyWord.NEWLINE);
            }
        }
        return lText.toString();
    }
    private int beginLineNumber;

    private JavaComment comment;
    
    private int length;

    private CodeElement parent;

    private Map properties;

    /**
     * 
     */
    public CodeElement()
    {
        this.properties = new HashMap();
    }

    /**
     * Enable a visitor implementation to visit an element type.
     * 
     * @param visitor
     */
    abstract public void accept(IElementVisitor visitor);

    /**
     * Adds a value for the given key.
     * 
     * @param String the name of the property
     * @param String an other value for the property
     */
    public void addPropertyValue(String aKey, String anOtherValue)
    {
        Object lValue = properties.get(aKey);
        List lList = null;
        if (lValue instanceof List)
        {
            lList = (List) lValue;
        }
        else
        {
            lList = new Vector();
            properties.put(aKey, lList);
            if (lValue != null)
            {
                lList.add(lValue);
            }
        }
        lList.add(anOtherValue);
    }
    
    /**
     * @return Returns the beginLineNumber.
     */
    public int getBeginLineNumber()
    {
        return beginLineNumber;
    }

    /**
     * Returns the comment surrounding this code element.
     * 
     * @return this element's comment
     */
    public Comment getComment()
    {
        return comment;
    }
    /**
     * @return Returns the endLineNumber.
     */
    public int getLength()
    {
        return length;
    }

    /**
     * @return Returns the parent.
     */
    public CodeElement getParent()
    {
        return parent;
    }

    /**
     * Returns the list of custom properties of the code element.
     * 
     * @return Iterator to the list of custom properties defined for this
     *         element.
     */
    public Iterator getProperties()
    {
        return properties.keySet().iterator();
    }

    /**
     * Returns the values of the property with the given name. If this property
     * is not defined for this code element, an empty Iterator is returned.
     * 
     * @param String the name of the property to retrieve
     */
    public Iterator getProperties(String aKey)
    {
        Object lValue = properties.get(aKey);
        if (lValue instanceof List)
        {
            return ((List) lValue).iterator();
        }
        else
        {
            List lList = new Vector();
            if (lValue != null)
            {
                lList.add(lValue);
            }
            return lList.iterator();
        }
    }

    /**
     * Returns the value of the property with the given name. If this property
     * is not defined for this code element, null is returned.
     * 
     * @param String the name of the property to retrieve
     */
    public String getProperty(String aKey)
    {
        Iterator lProps = getProperties(aKey);
        if (lProps.hasNext())
        {
            return (String) lProps.next();
        }
        else
        {
            return null;
        }
    }

    /**
     * Removes the property of the given name of this code element.
     * 
     * @param String the name of the property to remove
     */
    public void removeProperty(String aKey)
    {
        properties.remove(aKey);
    }
    /**
     * @param beginLineNumber The beginLineNumber to set.
     */
    public void setBeginLineNumber(int beginLineNumber)
    {
        this.beginLineNumber = beginLineNumber;
    }

    /**
     * Sets/Adds a Javadoc comment for this element
     * 
     * @param JavaComment the JavaComment object for this element
     */
    public void setComment(JavaComment aComment)
    {
        if (comment != null)
        {
            comment.unloadProperties();
        }
        comment = aComment;
        if (comment != null)
        {
            comment.setParent(this);
            comment.loadProperties();
        }
    }

    /**
     * Sets/Adds a Javadoc comment for this element.
     * 
     * @param String the comment text to add to this element
     */
    public void setComment(String aComment)
    {
        JavaComment lComment = new JavaComment(aComment);
        setComment(lComment);
    }
    /**
     * @param endLineNumber The endLineNumber to set.
     */
    public void setLength(int endLineNumber)
    {
        this.length = endLineNumber;
    }

    /**
     * @param parent The parent to set.
     */
    public void setParent(CodeElement parent)
    {
        this.parent = parent;
    }

    /**
     * Sets the value of a custom property for this code element.
     * 
     * @param String the name of the property
     * @param String the value of the property
     */
    public void setProperty(String aKey, String aValue)
    {
        properties.put(aKey, aValue);
    }

    /**
     * Returns the Java code representation of the code element.
     * 
     * @return String representation of the code element
     */
    abstract public String toString();
}
