/*
 * Created on Jul 28, 2005
 *
 */
package mojo.km.util;

import mojo.tools.code.KeyWord;

/**
 * @author eamundson
 *  
 */
public class TextUtil
{
    public static void removeStrings(StringBuffer buffer, String aString)
    {
        int index = buffer.indexOf(aString);
        if (index != -1)
        {
            buffer = buffer.delete(index, index + KeyWord.NEWLINE.length());
            TextUtil.removeStrings(buffer, aString);
        }
    }

    public static String upperFirstChar(String s)
    {
        char[] chars = s.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return String.valueOf(chars);
    }

    static public byte[] searchAndReplace(byte[] inBuffer, String replacedString, String replacementString)
    {
        String inString = new String(inBuffer);
        String outString = "";
        for (int whatsLeft = inString.indexOf(replacedString); whatsLeft > -1; whatsLeft = inString.indexOf(replacedString))
        {
            outString = outString + inString.substring(0, whatsLeft) + replacementString;
            inString = inString.substring(whatsLeft + replacedString.length());
        }
        if (inString.length() > 0)
        {
            outString = outString + inString;
        }
        return outString.getBytes();
    }

    static public String searchAndReplace(String inString, String replacedString, String replacementString, int beginIndex)
    {
        String outString = "";
        for (int whatsLeft = inString.indexOf(replacedString); whatsLeft > -1; whatsLeft = inString.indexOf(replacedString))
        {
            outString = outString + inString.substring(0, whatsLeft) + replacementString;
            inString = inString.substring(whatsLeft + replacedString.length());
        }
        if (inString.length() > 0)
        {
            outString = outString + inString;
        }
        return outString;
    }

    static public String searchAndReplace(String inString, String replacedString, String replacementString)
    {
        return searchAndReplace(inString, replacedString, replacementString, 0);
    }

    static public void searchAndReplace(StringBuffer inBuffer, String replacedString, String replacementString)
    {
        searchAndReplace(inBuffer, replacedString, replacementString, 0);
    }

    static public void searchAndReplace(StringBuffer inBuffer, String replacedString, String replacementString, int beginIndex)
    {
        int replaceStringLen = replacedString.length();

        boolean done = false;
        while (done == false)
        {
            beginIndex = inBuffer.indexOf(replacedString, beginIndex);
            if (beginIndex == -1)
            {
                done = true;
            }
            else
            {
                int endIndex = beginIndex + replaceStringLen;

                inBuffer = inBuffer.replace(beginIndex, endIndex, replacementString);

                beginIndex = endIndex;
            }
        }
    }

    static public int replace(StringBuffer inBuffer, String replacedString, String replacementString, int beginIndex)
    {
        // TODO Convert to using StringBuffer when JDK1.4 is available
        String inString = inBuffer.toString();

        int replaceStringLen = replacedString.length();

        beginIndex = inString.indexOf(replacedString, beginIndex);
        if (beginIndex != -1)
        {
            int endIndex = beginIndex + replaceStringLen;

            inBuffer.replace(beginIndex, endIndex, replacementString);

            // TODO Remove when JDK1.4 is available
            inString = inBuffer.toString();

            beginIndex = endIndex;
        }
        return beginIndex;
    }

    static public int reverseIndexOf(StringBuffer buffer, String searchStr, int beginIndex)
    {
        StringBuffer reverseBuffer = new StringBuffer(buffer.toString());

        reverseBuffer.reverse();

        String revStr = reverseBuffer.toString();

        StringBuffer revSearchBuffer = new StringBuffer(searchStr).reverse();

        String revSearchStr = revSearchBuffer.toString();

        int index = revStr.indexOf(revSearchStr, revStr.length() - beginIndex);

        if (index != -1)
        {
            index = revStr.length() - index - revSearchStr.length();
        }

        return index;
    }

    /**
     * @param buffer
     * @param period
     * @param underscore
     */
    public static void replaceAll(StringBuffer buffer, String replacedString, String replacementString)
    {
        int i = 0;
        while (i != -1)
        {
            i = TextUtil.replace(buffer, replacedString, replacementString, i);
            if (i != -1)
            {
                i += replacementString.length();
            }
        }
    }

    public static int countChar(String aString, int startIndex, int endIndex, char aChar)
    {
        String substring = aString.substring(startIndex, endIndex);

        boolean done = false;
        
        char[] chars = substring.toCharArray();

        int len = chars.length;

        int count = 0;

        for (int i = 0; i < len ; i++)
        {
            if (chars[i] == aChar)
            {
                count++;
            }
        }

        return count;
    }

    public static int lineOf(String aString, int beginIndex, String searchString)
    {
        int line = -1;
        int index = aString.indexOf(searchString, beginIndex);

        if (index > -1)
        {
            line = TextUtil.countChar(aString, beginIndex, index, '\n');
        }

        return line;
    }

    public static String removeLeadingChars(String str, char ch)
    {
        if (str == null)
        {
            return null;
        }
        char[] chars = str.toCharArray();
        int index = 0;
        for (; index < str.length(); index++)
        {
            if (chars[index] != ch)
            {
                break;
            }
        }
        return (index == 0) ? str : str.substring(index);
    }

    public static void main(String args[])
    {
        int index = TextUtil.lineOf(TextUtil.code, 0, "markModified()");
        
        System.out.println("line no: "+index);
    }

    private static String code = "public void method() {\ntest;\nmarkModified(); \ntest2;\n}\n";

}
