package mojo.util.spellcheck.web;

import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Hashtable;

class HtmlInputHidden
{

    HtmlInputHidden()
    {
        attributes = new Hashtable();
    }

    public void renderControl(Writer writer)
        throws IOException
    {
        String s = "";
        for(Enumeration enumeration = attributes.keys(); enumeration.hasMoreElements();)
        {
            String s1 = (String)enumeration.nextElement();
            String s2 = (String)attributes.get(s1);
            s = s + s1 + "=\"" + s2 + "\"";
        }

        writer.write("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\" " + s + " >\n");
    }

    public String id;
    public String name;
    public String value;
    public Hashtable attributes;
}
