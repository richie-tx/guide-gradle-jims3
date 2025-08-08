package mojo.util.spellcheck.web;


public class TextComponentInterfaceType
{

    public TextComponentInterfaceType()
    {
    }

    public static int getIntFor(String s)
    {
        if(s.toLowerCase().equals("standard"))
            return 10001;
        if(s.toLowerCase().equals("htmltextbox"))
            return 10002;
        return !s.toLowerCase().equals("custom") ? 10001 : 10003;
    }

    public static String getNameFor(int i)
    {
        if(i == 10001)
            return "Standard";
        if(i == 10002)
            return "HTMLTextBox";
        if(i == 10003)
            return "Custom";
        else
            return "Standard";
    }

    public static final int Standard = 10001;
    public static final int HTMLTextBox = 10002;
    public static final int Custom = 10003;
}
