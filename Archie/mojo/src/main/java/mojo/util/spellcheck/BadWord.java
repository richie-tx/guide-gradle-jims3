package mojo.util.spellcheck;


public class BadWord
{

    public BadWord(String s, int i, int j)
    {
        word = s;
        caretStart = i;
        caretEnd = j;
        reason = REASON_SPELLING;
    }

    public BadWord(String s, int i, int j, int k)
    {
        word = s;
        caretStart = i;
        caretEnd = j;
        reason = k;
    }

    public int getEndPosition()
    {
        return caretEnd;
    }

    public int getReason()
    {
        return reason;
    }

    public int getStartPosition()
    {
        return caretStart;
    }

    public String getWord()
    {
        return word;
    }

    private int caretStart;
    private int caretEnd;
    private String word;
    int reason;
    public static int REASON_DUPLICATE = 1;
    public static int REASON_SPELLING = 2;

}
