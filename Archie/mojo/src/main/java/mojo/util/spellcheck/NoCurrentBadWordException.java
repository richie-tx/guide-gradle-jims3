package mojo.util.spellcheck;


public class NoCurrentBadWordException extends Exception
{

    NoCurrentBadWordException()
    {
    }

    NoCurrentBadWordException(String s)
    {
        super(s);
    }
}
