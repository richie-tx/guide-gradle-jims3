package mojo.util.spellcheck.web;

import java.io.IOException;
import java.io.Writer;

public class Label extends HtmlElement
{

    public Label()
    {
        text = "";
    }

    public void renderControl(Writer writer)
        throws IOException
    {
        writer.write("<span " + getStyleString() + " " + getAttributesString() + " >" + text + "</span>\n");
    }

    public String text;
}
