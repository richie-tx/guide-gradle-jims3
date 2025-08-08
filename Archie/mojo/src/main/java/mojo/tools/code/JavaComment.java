package mojo.tools.code;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import mojo.tools.code.KeyWord;

/**
 * Represents a Javadoc comment. The Javadoc attributes (indicated by '@') are represented by custom
 * properties on the code element associated with the comment.
 * 
 * @author Saleem Shafi
 */
public class JavaComment extends Comment
{
    private List tags;

    private Map tagMap;

    /**
     * Creates Javadoc comment with the given text as the content of the comment.
     * 
     * @param String
     *            the text of the comment
     */
    public JavaComment(String aComment)
    {
        super(aComment);
        this.tagMap = new HashMap();
        this.tags = new Vector();
        this.setComment(aComment);
    }

    public List getTags()
    {
        return this.tags;
    }

    public String getTagValue(String key)
    {
        return (String) tagMap.get(key);
    }

    public void addTag(String tag)
    {
        tag = tag.trim();
        int asteriskIndex = tag.indexOf('*');
        if (asteriskIndex != -1)
        {
            tag = tag.substring(asteriskIndex, tag.length());
            tag = tag.trim();
        }
        if (tag.startsWith(KeyWord.AT))
        {
            String key;
            String value;
            int endIndex = tag.indexOf(" ");
            if (endIndex != -1)
            {
                key = tag.substring(0, endIndex);
                value = tag.substring(endIndex + 1, tag.length());
            }
            else
            {
                key = tag;
                value = "";
            }
            tagMap.put(key, value);
        }
        this.tags.add(tag);
    }

    /**
     * Sets the content of the comment with the given text. Any Javadoc attributes are stored as
     * custom properties in the associated code element when the loadProperties() method is called.
     * 
     * @param String
     *            Javadoc comment
     * @see #loadProperties()
     * @modelguid {3F0062A4-11BB-40C8-8056-6D4C80EB512B}
     */
    public void setComment(String aComment)
    {
        if (aComment.startsWith(KeyWord.JAVADOCCOMMENT_OPEN))
        {
            aComment = aComment.substring(3).trim();
        }
        if (aComment.endsWith(KeyWord.JAVADOCCOMMENT_CLOSE))
        {
            aComment = aComment.substring(0, aComment.length() - 2).trim();
        }
        StringBuffer buffer = new StringBuffer();
        StringTokenizer lTokens = new StringTokenizer(aComment, KeyWord.NEWLINE);
        while (lTokens.hasMoreTokens())
        {
            String line = lTokens.nextToken().trim();
            while (line.startsWith(KeyWord.ASTERIX))
            {
                line = line.substring(1).trim();
            }
            buffer.append(KeyWord.INDENTION);
            buffer.append(line);
            buffer.append(KeyWord.NEWLINE);

            this.addTag(line);
        }
        super.setComment(buffer.toString());
    }

    public String getCommentWithoutTags()
    {
        StringBuffer text = new StringBuffer();
        text.append(KeyWord.JAVADOCCOMMENT_OPEN);
        text.append(KeyWord.NEWLINE);
        if (this.getComment() != null)
        {
            String[] comments = getComment().split(KeyWord.NEWLINE);
            int len = comments.length;
            for (int i = 0; i < len; i++)
            {
                text.append(KeyWord.ASTERIX);
                text.append(KeyWord.SPACE);
                text.append(comments[i]);
                text.append(KeyWord.NEWLINE);
            }
        }
        text.append(KeyWord.JAVADOCCOMMENT_CLOSE);
        text.append(KeyWord.NEWLINE);
        return text.toString();
    }

    /**
     * Returns the Java code representation of the Javadoc comment. Any custom properties set on the
     * parent code element will be represented as Javadoc attributes.
     * 
     * @return String representation of the Javadoc comment
     * @modelguid {118B46D1-DB77-4FDA-83B2-F8B652DE08CA}
     */
    public String toString()
    {
        StringBuffer text = new StringBuffer();
        text.append(KeyWord.JAVADOCCOMMENT_OPEN);
        text.append(KeyWord.NEWLINE);
        if (this.getComment() != null)
        {
            String[] comments = getComment().split(KeyWord.NEWLINE);
            int len = comments.length;
            for (int i = 0; i < len; i++)
            {
                text.append(KeyWord.ASTERIX);
                text.append(KeyWord.SPACE);
                text.append(comments[i]);
                text.append(KeyWord.NEWLINE);
            }
        }
        CodeElement lParent = getParent();
        if (lParent != null)
        {
            for (Iterator i = lParent.getProperties(); i.hasNext();)
            {
                String lKey = (String) i.next();
                for (Iterator j = lParent.getProperties(lKey); j.hasNext();)
                {
                    text.append(KeyWord.ASTERIX);
                    text.append(KeyWord.SPACE);
                    text.append(KeyWord.AT);
                    text.append(lKey);
                    text.append(KeyWord.SPACE);
                    text.append((String) j.next());
                    text.append(KeyWord.NEWLINE);
                }
            }
        }
        text.append(KeyWord.JAVADOCCOMMENT_CLOSE);
        text.append(KeyWord.NEWLINE);
        return text.toString();
    }

    /**
     * Loads the custom properties of the parent code element from the Javadoc attributes of this
     * Javadoc comment.
     * 
     * @modelguid {88C97446-A457-4E51-9E21-7E3DD2DA280C}
     */
    void loadProperties()
    {
        if (getComment() == null || getParent() == null)
            return;
        StringBuffer lBuff = new StringBuffer();
        StringTokenizer lTokens = new StringTokenizer(getComment(), KeyWord.NEWLINE);
        String lLine = null;
        if (lTokens.hasMoreTokens())
        {
            lLine = lTokens.nextToken().trim();
        }

        if (lLine != null)
        {
            for (;;)
            {
                if (lLine.startsWith(KeyWord.AT))
                    break;
                lBuff.append(lLine).append(KeyWord.NEWLINE);
                if (lTokens.hasMoreTokens())
                {
                    lLine = lTokens.nextToken().trim();
                }
                else
                {
                    lLine = null;
                    break;
                }
            }
            super.setComment(lBuff.toString());
        }

        if (lLine != null)
        {
            String lKey = null;
            String lValue = null;
            for (;;)
            {
                if (lLine.startsWith(KeyWord.AT))
                {
                    if (lKey != null)
                    {
                        getParent().addPropertyValue(lKey, lValue);
                    }
                    int index = lLine.indexOf(KeyWord.SPACE);
                    if (index == -1)
                    {
                        lKey = lLine.substring(1).trim();
                        lValue = KeyWord.EMPTY_STRING;
                    }
                    else
                    {
                        lKey = lLine.substring(1, index).trim();
                        lValue = lLine.substring(index).trim();
                    }
                }
                else
                {
                    lValue += KeyWord.NEWLINE + lLine;
                    lValue = lValue.trim();
                }

                if (lTokens.hasMoreTokens())
                {
                    lLine = lTokens.nextToken().trim();
                }
                else
                {
                    if (lKey != null)
                    {
                        getParent().addPropertyValue(lKey, lValue);
                    }
                    break;
                }
            }
        }
    }

    /**
     * Removes any custom properties on the parent code element.
     * 
     * @modelguid {BB6B6060-3C0F-4363-9B8E-4E7AE7027744}
     */
    void unloadProperties()
    {
        CodeElement lParent = getParent();
        for (Iterator i = lParent.getProperties(); i.hasNext();)
        {
            String lProperty = (String) i.next();
            lParent.removeProperty(lProperty);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.tools.code.Acceptable#accept(mojo.tools.code.IElementVisitor)
     */
    public void accept(IElementVisitor visitor)
    {
        visitor.visit(this);
    }

}
