package mojo.util.spellcheck;

import java.text.BreakIterator;
import java.text.CharacterIterator;

class CustomWordIterator extends BreakIterator
{

    public CustomWordIterator(BreakIterator breakiterator)
    {
        this(breakiterator, false);
    }

    public CustomWordIterator(BreakIterator breakiterator, boolean flag)
    {
        iterator = breakiterator;
        treatEmailsAsWords = flag;
    }

    public Object clone()
    {
        return iterator.clone();
    }

    public int current()
    {
        return iterator.current();
    }

    public int first()
    {
        return iterator.first();
    }

    public int following(int i)
    {
        if(treatEmailsAsWords && i < text.length() && !Character.isWhitespace(text.charAt(i)))
            return followingEmailSpaceBreak(i);
        else
            return iterator.following(i);
    }

    public int followingEmailSpaceBreak(int i)
    {
        int j = i;
        boolean flag = false;
        for(; j + 1 <= text.length() && (isEmailWhitespace(text.charAt(j)) && !isEmailWhitespace(text.charAt(j + 1)) || !Character.isWhitespace(text.charAt(j))); j = iterator.following(j))
            if(text.charAt(j) == '@' && !Character.isWhitespace(text.charAt(j + 1)))
                flag = true;

        if(flag)
            return j;
        else
            return iterator.following(i);
    }

    public CharacterIterator getText()
    {
        return iterator.getText();
    }

    public boolean getTreatEmailsAsWords()
    {
        return treatEmailsAsWords;
    }

    public boolean isBoundary(int i)
    {
        return iterator.isBoundary(i);
    }

    boolean isEmailWhitespace(char c)
    {
        return c == '@' || c == '.';
    }

    public int last()
    {
        return iterator.last();
    }

    public int next()
    {
        return iterator.next();
    }

    public int next(int i)
    {
        return iterator.next(i);
    }

    public int preceding(int i)
    {
        if(treatEmailsAsWords && i < text.length() && !Character.isWhitespace(text.charAt(i)))
            return precedingEmailSpaceBreak(i);
        else
            return iterator.preceding(i);
    }

    public int precedingEmailSpaceBreak(int i)
    {
        int j = i;
        boolean flag = false;
        for(; j > 0 && j < text.length() - 1 && (!Character.isWhitespace(text.charAt(j + 1)) && isEmailWhitespace(text.charAt(j)) && !isEmailWhitespace(text.charAt(j - 1)) && !Character.isWhitespace(text.charAt(j - 1)) || !Character.isWhitespace(text.charAt(j)) && (!Character.isWhitespace(text.charAt(j - 1)) || isEmailWhitespace(text.charAt(j - 1)))); j = iterator.preceding(j))
            if(text.charAt(j) == '@' && !Character.isWhitespace(text.charAt(j - 1)))
                flag = true;

        if(flag)
            return j;
        else
            return iterator.preceding(i);
    }

    public int previous()
    {
        return iterator.previous();
    }

    public void setText(String s)
    {
        text = s;
        iterator.setText(s);
    }

    public void setText(CharacterIterator characteriterator)
    {
        iterator.setText(characteriterator);
    }

    public void setTreatEmailsAsWords(boolean flag)
    {
        treatEmailsAsWords = flag;
    }

    BreakIterator iterator;
    String text;
    boolean treatEmailsAsWords;
}
