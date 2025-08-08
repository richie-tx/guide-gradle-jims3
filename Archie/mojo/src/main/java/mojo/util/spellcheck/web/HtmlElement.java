package mojo.util.spellcheck.web;

import java.util.Enumeration;
import java.util.Hashtable;

abstract class HtmlElement
{

    HtmlElement()
    {
        attributes = new Hashtable();
        style = new Hashtable();
    }

    protected String getAttributesString()
    {
        String s = "";
        for(Enumeration enumeration = attributes.keys(); enumeration.hasMoreElements();)
        {
            String s1 = (String)enumeration.nextElement();
            String s2 = (String)attributes.get(s1);
            s = s + " " + s1 + "=\"" + s2 + "\" ";
        }

        return s;
    }

    protected String getStyleString()
    {
        String s = "";
        for(Enumeration enumeration = style.keys(); enumeration.hasMoreElements();)
        {
            String s1 = (String)enumeration.nextElement();
            String s2 = (String)style.get(s1);
            s = s + " " + s1 + ":" + s2 + ";";
        }

        if(s.length() > 0)
            s = "style=\"" + s + "\"";
        return s;
    }

    public Hashtable attributes;
    public Hashtable style;
    public String name;
    public String id;
}
