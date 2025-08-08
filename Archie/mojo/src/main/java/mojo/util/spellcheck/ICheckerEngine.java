package mojo.util.spellcheck;

import java.io.File;
import java.util.Vector;

public interface ICheckerEngine
{

    public abstract boolean addWord(String s);

    public abstract void changeBadWord(String s)
        throws NoCurrentBadWordException, NullPointerException;

    public abstract void check(String s);

    public abstract Vector findSuggestions()
        throws NoCurrentBadWordException, NullPointerException;

    public abstract boolean getAllowAnyCase();

    public abstract boolean getAllowMixedCase();

    public abstract boolean getCheckCompoundWords();

    public abstract int getConsiderationRange();

    public abstract String getDictFilePath();

    public abstract boolean getIgnoreCapitalizedWords();

    public abstract boolean getIgnoreWordsWithDigits();

    public abstract boolean getIgnoreXML();

    public abstract boolean getIncludeUserDictionaryInSuggestions();

    public abstract int getLanguageParser();

    public abstract boolean getLookIntoHyphenatedText();

    public abstract boolean getSuggestSplitWords();

    public abstract int getSuggestionsMethod();

    public abstract UserDictionary getUserDictionary();

    public abstract boolean getWarnDuplicates();

    public abstract void ignoreAll(String s);

    public abstract BadWord nextBadWord();

    public abstract void setAllowAnyCase(boolean flag);

    public abstract void setAllowMixedCase(boolean flag);

    public abstract void setCheckCompoundWords(boolean flag);

    public abstract void setConsiderationRange(int i);

    public abstract void setDictFilePath(String s);

    public abstract void setIgnoreCapitalizedWords(boolean flag);

    public abstract void setIgnoreWordsWithDigits(boolean flag);

    public abstract void setIgnoreXML(boolean flag);

    public abstract void setIncludeUserDictionaryInSuggestions(boolean flag);

    public abstract void setLanguageParser(int i);

    public abstract void setLookIntoHyphenatedText(boolean flag);

    public abstract void setMaximumAnagramLength(int i);

    public abstract void setPosition(int i);

    public abstract void setSeparateHyphenWords(boolean flag);

    public abstract void setSuggestSplitWords(boolean flag);

    public abstract void setSuggestionsMethod(int i);

    public abstract void setUserDictionary(UserDictionary userdictionary);

    public abstract void setUserDictionary(File file);

    public abstract void setWarnDuplicates(boolean flag);
}
