package mojo.util.spellcheck;


class SimpleTextBoundary
{

    SimpleTextBoundary()
    {
        separateHyphenWords = false;
        languageParsing = 10001;
    }

    public int following(int i)
    {
        if(i < 0 || i > theText.length())
            throw new IllegalArgumentException("Following(" + i + ") offset out of bounds");
        pos = i;
        for(boolean flag = isAtNonWhiteSpace(pos); pos < theText.length() && isAtNonWhiteSpace(pos) == flag; pos++);
        return pos;
    }

    protected boolean isAtNonWhiteSpace(int i)
    {
        return Character.isLetterOrDigit(theText.charAt(i)) || languageParsing == 10001 && theText.charAt(i) == '\'' && i + 1 != theText.length() && Character.isLetterOrDigit(theText.charAt(i + 1)) && i > 0 && Character.isLetterOrDigit(theText.charAt(i - 1)) || !separateHyphenWords && theText.charAt(i) == '-' && i + 1 != theText.length() && Character.isLetterOrDigit(theText.charAt(i + 1)) && i > 0 && Character.isLetterOrDigit(theText.charAt(i - 1));
    }

    public boolean isBoundary(int i)
    {
        if(i < 0 || i >= theText.length())
            throw new IllegalArgumentException("IsBoundary offset out of bounds " + i + " >= " + theText.length());
        if(i == 0 || i == theText.length())
            return true;
        else
            return following(i - 1) == i;
    }

    public boolean isBoundaryLeft(int i)
    {
        if(i < theText.length() && isBoundary(i) && i > 0)
            return !isAtNonWhiteSpace(i - 1) && isAtNonWhiteSpace(i);
        if(i == 0 && theText.length() > 0)
            return Character.isLetterOrDigit(theText.charAt(0));
        else
            return false;
    }

    public boolean isBoundaryRight(int i)
    {
        if(i < theText.length() && isBoundary(i) && i > 0 || i == theText.length() && i > 0)
            return isAtNonWhiteSpace(i - 1);
        else
            return false;
    }

    public int last()
    {
        pos = theText.length();
        return pos;
    }

    public int preceding(int i)
    {
        if(i < 0 || i > theText.length())
            throw new IllegalArgumentException("Preceding(offset) [" + i + "] offset out of bounds");
        if(i == 0)
            return -1;
        pos = i - 1;
        for(boolean flag = isAtNonWhiteSpace(pos); pos >= 0 && isAtNonWhiteSpace(pos) == flag; pos--);
        pos++;
        return pos;
    }

    public void setText(String s)
    {
        theText = s;
        pos = 0;
    }

    protected String theText;
    int pos;
    public boolean separateHyphenWords;
    public int languageParsing;
}
