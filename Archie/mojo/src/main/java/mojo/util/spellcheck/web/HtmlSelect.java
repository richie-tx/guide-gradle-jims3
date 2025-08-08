package mojo.util.spellcheck.web;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class HtmlSelect extends HtmlElement
{

    public HtmlSelect()
    {
        tabIndex = -2;
        items = new ArrayList();
        size = 1;
        multiple = false;
    }

    public void renderControl(Writer writer)
        throws IOException
    {
        String s = getAttributesString();
        String s1 = getStyleString();
        writer.write("<select name=\"" + super.name + "\" " + (super.id.equals("") ? "" : "ID=\"" + super.id + "\"") + " " + (multiple ? "multiple" : "") + " " + "size=\"" + size + "\" " + (tabIndex == -2 ? "" : "TabIndex=\"" + tabIndex + "\"") + " " + s + " " + s1 + ">");
        writer.write("</select>\n");
    }

    public int tabIndex;
    public ArrayList items;
    public int size;
    public boolean multiple;
}
