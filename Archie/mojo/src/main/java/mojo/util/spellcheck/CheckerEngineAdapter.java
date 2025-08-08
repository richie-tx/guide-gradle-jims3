package mojo.util.spellcheck;

import java.io.File;
import java.util.Vector;

public abstract class CheckerEngineAdapter
    implements ICheckerEngine
{

    public CheckerEngineAdapter()
    {
    }

    public boolean addWord(String s)
    {
        return false;
    }

    public void changeBadWord(String s)
    {
    }

    public void check(String s)
    {
    }

    public Vector findSuggestions()
    {
        return new Vector();
    }

    public abstract boolean getAllowAnyCase();

    public boolean getAllowMixedCase()
    {
        return false;
    }

    public boolean getAnyMixedCase()
    {
        return false;
    }

    public boolean getCheckCompoundWords()
    {
        return false;
    }

    public int getConsiderationRange()
    {
        return 0;
    }

    public String getDictFilePath()
    {
        return null;
    }

    public boolean getIgnoreCapitalizedWords()
    {
        return false;
    }

    public abstract boolean getIgnoreWordsWithDigits();

    public boolean getIgnoreXML()
    {
        return false;
    }

    public boolean getIncludeUserDictionaryInSuggestions()
    {
        return false;
    }

    public int getLanguageParser()
    {
        return 10001;
    }

    public boolean getLookIntoHyphenatedText()
    {
        return false;
    }

    public boolean getSuggestSplitWords()
    {
        return true;
    }

    public int getSuggestionsMethod()
    {
        return 0;
    }

    public UserDictionary getUserDictionary()
    {
        return null;
    }

    public abstract boolean getWarnDuplicates();

    public void ignoreAll(String s)
    {
    }

    public BadWord nextBadWord()
    {
        return null;
    }

    public abstract void setAllowAnyCase(boolean flag);

    public void setAllowMixedCase(boolean flag)
    {
    }

    public void setAnyMixedCase(boolean flag)
    {
    }

    public void setCheckCompoundWords(boolean flag)
    {
    }

    public void setConsiderationRange(int i)
    {
    }

    public void setDictFilePath(String s)
    {
    }

    public void setIgnoreCapitalizedWords(boolean flag)
    {
    }

    public abstract void setIgnoreWordsWithDigits(boolean flag);

    public void setIgnoreXML(boolean flag)
    {
    }

    public void setIncludeUserDictionaryInSuggestions(boolean flag)
    {
    }

    public void setLanguageParser(int i)
    {
    }

    public void setLookIntoHyphenatedText(boolean flag)
    {
    }

    public abstract void setMaximumAnagramLength(int i);

    public void setPosition(int i)
    {
    }

    public void setSeparateHyphenWords(boolean flag)
    {
    }

    public void setSuggestSplitWords(boolean flag)
    {
    }

    public void setSuggestionsMethod(int i)
    {
    }

    public void setUserDictionary(UserDictionary userdictionary)
    {
    }

    public void setUserDictionary(File file)
    {
    }

    public void setUserDictionary(String s)
    {
    }

    public abstract void setWarnDuplicates(boolean flag);
}
