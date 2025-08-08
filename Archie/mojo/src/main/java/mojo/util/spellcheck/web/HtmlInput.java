package mojo.util.spellcheck.web;

import java.io.IOException;
import java.io.Writer;

abstract class HtmlInput extends HtmlElement
{

    HtmlInput()
    {
        tabIndex = -2;
        value = "";
        size = -1;
    }

    public void renderControl(Writer writer)
        throws IOException
    {
        String s = getAttributesString();
        String s1 = getStyleString();
        writer.write("<input " + (super.name == null || super.name.equals("") ? "" : "name=\"" + super.name + "\" ") + (super.id == null || super.id.equals("") ? "" : "ID=\"" + super.id + "\" ") + " " + (size <= -1 ? "" : "size=\"" + size + "\"") + " type=\"" + type + "\" " + (value == null || value.equals("") ? "" : " value=\"" + value + "\" ") + s + " " + (tabIndex == -2 ? "" : "TabIndex=\"" + tabIndex + "\"") + " " + s1 + " >\n");
    }

    public int tabIndex;
    public String value;
    public int size;
    protected String type;
}
