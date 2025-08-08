package mojo.util.spellcheck.web;

public class RSButton extends HtmlInput
{

    public RSButton()
    {
        this("button");
    }

    public RSButton(String s)
    {
        super.type = s;
    }
}
