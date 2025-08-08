package mojo.util.spellcheck;


public class LanguageType
{

    public LanguageType()
    {
    }

    public static String getLanguageNameFromType(int i)
    {
        if(i == 10001)
            return "ENGLISH";
        if(i == 10002)
            return "FRENCH";
        if(i == 10003)
            return "GERMAN";
        if(i == 10004)
            return "ITALIAN";
        if(i == 10005)
            return "SPANISH";
        if(i == 10006)
            return "DUTCH";
        if(i == 10007)
            return "PORTUGUESE";
        else
            return "ENGLISH";
    }

    public static int getLanguageTypeFromString(String s)
    {
        if(s.toLowerCase().equals("english"))
            return 10001;
        if(s.toLowerCase().equals("french"))
            return 10002;
        if(s.toLowerCase().equals("german"))
            return 10003;
        if(s.toLowerCase().equals("italian"))
            return 10004;
        if(s.toLowerCase().equals("spanish"))
            return 10005;
        if(s.toLowerCase().equals("dutch"))
            return 10006;
        return !s.toLowerCase().equals("portuguese") ? 10001 : 10007;
    }

    public static final int ENGLISH = 10001;
    public static final int FRENCH = 10002;
    public static final int GERMAN = 10003;
    public static final int ITALIAN = 10004;
    public static final int SPANISH = 10005;
    public static final int DUTCH = 10006;
    public static final int PORTUGUESE = 10007;
}
