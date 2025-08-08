package mojo.util.spellcheck;

import java.io.*;
import java.util.*;

public class SpellChecker
    implements ICheckerEngine
{
    class CompareL
        implements Comparator
    {

        public int compare(Object obj, Object obj1)
        {
            return (int)(100D * (suggestionScore2b(topWord, (String)obj1) - suggestionScore2b(topWord, (String)obj)));
        }

        private int numberDifferentChars(String s, String s1)
        {
            int i = s.length();
            int j = s1.length();
            int k = 0;
            for(int l = 0; l < i; l++)
            {
                boolean flag = false;
                for(int i1 = 0; i1 < j; i1++)
                    if(s.charAt(l) == s1.charAt(i1))
                        flag = true;

                if(!flag)
                    k++;
            }

            return k;
        }

        public void with(String s)
        {
            topWord = s;
        }

        private String topWord;

        CompareL()
        {
        }
    }


    public SpellChecker()
    {
        this("none specified");
    }

    public SpellChecker(String s)
    {
        currentBadWordEnd = -1;
        currentBadWordStart = -1;
        wordIterator = new AdvancedTextBoundary();
        previousWord = "";
        mainDictionary = new ArrayList(0x27100);
        userDicList = new ArrayList(5000);
        ignoreWordsWithDigits = true;
        warnDuplicates = true;
        numWords = 0;
        maximumAnagramLength = 8;
        compare = new CompareL();
        optimization = OPTIMIZE_FOR_SPEED;
        suggestionsMethod = HASHING_SUGGESTIONS;
        dictFileStream = null;
        dictFile = null;
        allowMixedCase = false;
        allowAnyCase = false;
        qryWrd = new char[100];
        loadedQryWrd = "";
        includeUserDictionaryInSuggestions = false;
        ignoreCapitalizedWords = false;
        ignoreEmails = false;
        lookIntoHyphenatedText = true;
        ignoreXML = false;
        checkCompoundWords = false;
        suggestSplitWords = true;
        taken = new int[15];
        reverseSortedDictionary = null;
        sizeSortedDicLookup = new int[50];
        radius = 80;
        dictionaryIsLoaded = false;
        dictReader = new DictReader();
        K k = new K();
//        if(!k.cLic(s))
//        {
//            throw new RuntimeException("\n*********************************\nRapidSpellChecker licenseKey not specified, or invalid.  Ensure that it is set in the class constructor.\nEvaluation license keys can be obtained from http://keyoti.com/products/evaluation-key-generator\nSpecified key:" + s + "\n*********************************\n");
//        } else
//        {
            init();
            return;
//        }
    }

    public boolean addWord(String s)
        throws NullPointerException
    {
        if(s == null)
            throw new NullPointerException("Null String passed to addWord() - ensure word parameter is not null.");
        ignoreAll(s);
        if(userDictionary == null || !userDictionary.isValid())
            return false;
        if(lookUpUserDictionary(s))
        {
            return true;
        } else
        {
            userDicList.add(s);
            return userDictionary.addWord(s);
        }
    }

    public void changeBadWord(String s)
        throws NoCurrentBadWordException, NullPointerException
    {
        if(s == null)
            throw new NullPointerException("Null String passed to changeBadWord() - ensure newWord parameter is not null");
        if(currentBadWordStart == -1 || currentBadWordEnd == -1)
        {
            throw new NoCurrentBadWordException("No word currently selected, use nextBadWord() first.");
        } else
        {
            theText.delete(currentBadWordStart, currentBadWordEnd);
            theText.insert(currentBadWordStart, s);
            wordIterator.setText(theText.toString());
            wordEnd = currentBadWordStart + s.length();
            return;
        }
    }

    public void check(String s)
        throws NullPointerException
    {
        if(s == null)
        {
            throw new NullPointerException("Null String passed to check() - ensure text parameter is not null.");
        } else
        {
            check(s, 0);
            return;
        }
    }

    public void check(String s, int i)
        throws NullPointerException
    {
        if(s == null)
            throw new NullPointerException("Null String passed to check() - ensure text parameter is not null.");
        try
        {
            loadLexicon();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        if(i < 0)
            i = 0;
        wordStart = i;
        wordEnd = wordStart;
        theText = new StringBuffer(s);
        wordIterator.setText(s);
        preCheck();
    }

    public void findAnagrams(String s, List list)
    {
        int ai[] = new int[s.length()];
        Permuter permuter = new Permuter(s.length());
        StringBuffer stringbuffer = new StringBuffer(s);
        for(int i = 0; i < permuter.getNumberOfPermutations(); i++)
        {
            for(int j = 0; j < permuter.getPermutation(i).length; j++)
                stringbuffer.setCharAt(j, s.charAt(permuter.getPermutation(i)[j] - 1));

            if(lookUp(stringbuffer.toString()) && !list.contains(stringbuffer.toString()))
            {
                boolean flag = false;
                for(int k = 0; k < dontSuggest.length; k++)
                    if(stringbuffer.toString().equals(dontSuggest[k]))
                        flag = true;

                if(!flag)
                    list.add(stringbuffer.toString());
            }
        }

    }

    public boolean findCompoundWords(String s, List list)
    {
        byte byte0 = 2;
        if(getLanguageParser() == 10006)
            byte0 = 3;
        for(int i = s.length(); i > byte0; i--)
            if(lookUpMainDictionary(s.substring(0, i)) || lookUpUserDictionary(s.substring(0, i)))
            {
                String s1 = s.substring(i);
                if(s.length() - i > byte0 && (lookUpMainDictionary(s1) || lookUpUserDictionary(s1)))
                {
                    if(list != null)
                    {
                        list.add(s1);
                        list.add(s.substring(0, i));
                    }
                    return true;
                }
                if(findCompoundWords(s1, list))
                {
                    if(list != null)
                        list.add(s.substring(0, i));
                    return true;
                }
            }

        return false;
    }

    public synchronized Vector findSuggestions()
        throws NoCurrentBadWordException
    {
        if(currentBadWordStart == -1 || currentBadWordEnd == -1)
            throw new NoCurrentBadWordException("No word currently selected, use nextBadWord() first.");
        else
            return findSuggestions(theText.toString().substring(currentBadWordStart, currentBadWordEnd));
    }

    public synchronized Vector findSuggestions(String s)
        throws NullPointerException
    {
        if(s == null)
            throw new NullPointerException("Null String passed to findSuggestions() - ensure word parameter is not null.");
        try
        {
            loadLexicon();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        Vector vector = new Vector(20, 1);
        if(s.equals(""))
            return vector;
        int i;
        int j;
        if(ignoreXML)
            while((i = s.indexOf('<')) > -1 && (j = s.indexOf('>', i)) > -1) 
                s = s.substring(0, i) + s.substring(j + 1);
        boolean flag = false;
        boolean flag1 = true;
        if(s.toLowerCase().charAt(0) == s.charAt(0))
            flag1 = false;
        if(flag1)
        {
            vector = findSuggestions(s.toLowerCase());
            for(int k = 0; k < vector.size(); k++)
                vector.set(k, Character.toUpperCase(((String)vector.get(k)).charAt(0)) + ((String)vector.get(k)).substring(1));

            if(getSuggestSplitWords())
            {
                ArrayList arraylist2 = new ArrayList(3);
                StringBuffer stringbuffer1 = new StringBuffer("");
                if(findCompoundWords(s, arraylist2))
                {
                    for(int i3 = arraylist2.size() - 1; i3 >= 0; i3--)
                        stringbuffer1.append((String)arraylist2.get(i3)).append(" ");

                    if(stringbuffer1.length() > 1)
                    {
                        stringbuffer1.replace(stringbuffer1.length() - 1, stringbuffer1.length(), "");
                        if(!vector.contains(stringbuffer1.toString()))
                            vector.add(stringbuffer1.toString());
                    }
                }
            }
        }
        if(suggestionsMethod == HASHING_SUGGESTIONS)
        {
            ArrayList arraylist = null;
            if(reverseSortedDictionary == null)
                loadReverseList();
            int l = Collections.binarySearch(mainDictionary, s);
            if(l < 0)
                l *= -1;
            int l1 = Collections.binarySearch(reverseSortedDictionary, s, new ReverseSorter());
            if(l1 < 0)
                l1 *= -1;
            if(l > numWords - radius)
                l = numWords - radius;
            if(l < radius)
                l = radius;
            int j3 = radius * 2;
            if(j3 > mainDictionary.size())
                j3 = mainDictionary.size() - (l - radius);
            arraylist = new ArrayList(mainDictionary.subList(l - radius, (l - radius) + j3));
            if(l1 < radius)
                l1 = radius;
            else
            if(l1 > reverseSortedDictionary.size() - radius)
                l1 = reverseSortedDictionary.size() - radius;
            if(j3 > reverseSortedDictionary.size())
                j3 = reverseSortedDictionary.size() - (l1 - radius);
            arraylist.addAll(reverseSortedDictionary.subList(l1 - radius, (l1 - radius) + j3));
            for(int k3 = 0; k3 < arraylist.size(); k3++)
            {
                String s1 = (String)arraylist.get(k3);
                if(isSuggestion(s1, s, 0.69999999999999996D))
                {
                    if(flag1)
                        s1 = makeCap(s1);
                    for(int l3 = 0; l3 < dontSuggest.length; l3++)
                        if(s1.equals(dontSuggest[l3]))
                            flag = true;

                    if(!flag && !vector.contains(s1))
                        vector.addElement(s1);
                    else
                        flag = false;
                }
            }

            if(includeUserDictionaryInSuggestions)
            {
                for(int i4 = 0; i4 < userDicList.size(); i4++)
                {
                    String s2 = (String)userDicList.get(i4);
                    if(isSuggestion(s2, s, 0.80000000000000004D))
                    {
                        if(flag1)
                            s2 = makeCap(s2);
                        for(int j4 = 0; j4 < dontSuggest.length; j4++)
                            if(s2.equals(dontSuggest[j4]))
                                flag = true;

                        if(!flag && !vector.contains(s2))
                            vector.addElement(s2);
                        else
                            flag = false;
                    }
                }

            }
        } else
        {
            String s7 = PhoneticsProcessor.metaPhone(s);
            if(optimization == OPTIMIZE_FOR_MEMORY)
            {
                for(int i1 = 0; i1 < numWords; i1++)
                {
                    String s3 = (String)mainDictionary.get(i1);
                    if(PhoneticsProcessor.hasSameMetaPhone(s3, s7))
                    {
                        if(flag1)
                            s3 = makeCap(s3);
                        for(int i2 = 0; i2 < dontSuggest.length; i2++)
                            if(s3.equals(dontSuggest[i2]))
                                flag = true;

                        if(!flag)
                            vector.addElement(s3);
                        else
                            flag = false;
                    }
                }

            } else
            {
                for(int j1 = 0; j1 < numWords; j1++)
                {
                    if(mainDicListPhonetic == null)
                        generatePhoneticList();
                    String s6 = (String)mainDicListPhonetic.get(j1);
                    if(s6.equals(s7))
                    {
                        String s4 = (String)mainDictionary.get(j1);
                        if(flag1)
                            s4 = makeCap(s4);
                        for(int j2 = 0; j2 < dontSuggest.length; j2++)
                            if(s4.equals(dontSuggest[j2]))
                                flag = true;

                        if(!flag)
                            vector.addElement(s4);
                        else
                            flag = false;
                    }
                }

            }
            if(includeUserDictionaryInSuggestions)
            {
                for(int k1 = 0; k1 < userDicList.size(); k1++)
                {
                    String s5 = (String)userDicList.get(k1);
                    if(PhoneticsProcessor.hasSameMetaPhone(s5, s7))
                    {
                        if(flag1)
                            s5 = makeCap(s5);
                        for(int k2 = 0; k2 < dontSuggest.length; k2++)
                            if(s5.equals(dontSuggest[k2]))
                                flag = true;

                        if(!flag)
                            vector.addElement(s5);
                        else
                            flag = false;
                    }
                }

            }
        }
        if(getSuggestSplitWords())
        {
            ArrayList arraylist1 = new ArrayList(3);
            StringBuffer stringbuffer = new StringBuffer("");
            if(findCompoundWords(s, arraylist1))
            {
                for(int l2 = arraylist1.size() - 1; l2 >= 0; l2--)
                    stringbuffer.append((String)arraylist1.get(l2)).append(" ");

                if(stringbuffer.length() > 1)
                {
                    stringbuffer.replace(stringbuffer.length() - 1, stringbuffer.length(), "");
                    if(!vector.contains(stringbuffer.toString()))
                        vector.add(stringbuffer.toString());
                }
            }
        }
        if(s.length() <= maximumAnagramLength)
            findAnagrams(s, vector);
        compare.with(s);
        Collections.sort(vector, compare);
        return vector;
    }

    public boolean flagged(String s)
    {
        return badWordList.contains(s);
    }

    private void generatePhoneticList()
    {
        mainDicListPhonetic = new ArrayList(0x27100);
        for(int i = 0; i < numWords; i++)
            mainDicListPhonetic.add(PhoneticsProcessor.metaPhone((String)mainDictionary.get(i)));

    }

    public boolean getAllowAnyCase()
    {
        return allowAnyCase;
    }

    public boolean getAllowMixedCase()
    {
        return allowMixedCase;
    }

    public String getAmendedText()
    {
        if(theText != null)
            return theText.toString();
        else
            return null;
    }

    public boolean getCheckCompoundWords()
    {
        return checkCompoundWords;
    }

    public int getConsiderationRange()
    {
        return radius;
    }

    public String getDictFilePath()
    {
        return dictFilePath;
    }

    public boolean getIgnoreCapitalizedWords()
    {
        return ignoreCapitalizedWords;
    }

    public boolean getIgnoreWordsWithDigits()
    {
        return ignoreWordsWithDigits;
    }

    public boolean getIgnoreXML()
    {
        return wordIterator.ignoreXML;
    }

    public boolean getIncludeUserDictionaryInSuggestions()
    {
        return includeUserDictionaryInSuggestions;
    }

    public int getLanguageParser()
    {
        return ((SimpleTextBoundary) (wordIterator)).languageParsing;
    }

    public boolean getLookIntoHyphenatedText()
    {
        return lookIntoHyphenatedText;
    }

    public int getMaximumAnagramLength()
    {
        return maximumAnagramLength;
    }

    public String getNextWord()
    {
        wordStart = wordEnd;
        if(wordEnd < wordIterator.last())
        {
            wordEnd = wordIterator.following(wordEnd);
            if(wordEnd - wordStart <= 0 || !Character.isLetterOrDigit(theText.charAt(wordStart)))
                return getNextWord();
            else
                return theText.toString().substring(wordStart, wordEnd);
        } else
        {
            return null;
        }
    }

    public int getOptimization()
    {
        return optimization;
    }

    public boolean getSeparateHyphenWords()
    {
        return ((SimpleTextBoundary) (wordIterator)).separateHyphenWords;
    }

    public boolean getSuggestSplitWords()
    {
        return suggestSplitWords;
    }

    public int getSuggestionsMethod()
    {
        return suggestionsMethod;
    }

    public UserDictionary getUserDictionary()
    {
        return userDictionary;
    }

    public boolean getWarnDuplicates()
    {
        return warnDuplicates;
    }

    public int getWordEnd()
    {
        return wordEnd;
    }

    public int getWordStart()
    {
        return wordStart;
    }

    public void ignoreAll(String s)
        throws NullPointerException
    {
        if(s == null)
        {
            throw new NullPointerException("Null String passed to ignoreAll() - ensure word parameter is not null.");
        } else
        {
            ignoreList.addElement(s);
            return;
        }
    }

    private void init()
    {
        badWordList = new Vector(50, 5);
        ignoreList = new Vector(10, 2);
        resourceDecoder = new ResourceDecoder();
    }

    boolean isAllCaps(String s)
    {
        for(int i = 0; i < s.length(); i++)
            if(Character.isLowerCase(s.charAt(i)))
                return false;

        return true;
    }

    boolean isMixedCase(String s)
    {
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = Character.isUpperCase(s.charAt(0));
        for(int i = ((flag2) ? 1 : 0); i < s.length(); i++)
            if(Character.isLowerCase(s.charAt(i)))
            {
                flag1 = true;
                if(flag)
                    return true;
            } else
            if(Character.isUpperCase(s.charAt(i)))
            {
                flag = true;
                if(flag1)
                    return true;
            }

        return false;
    }

    private boolean isSuggestion(String s, String s1, double d)
    {
        return suggestionScore2(s, s1) >= d;
    }

    private void loadLexicon()
        throws IOException
    {
        if(!dictionaryIsLoaded)
        {
            if(dictFile == null)
                numWords = WordList.readAll(mainDictionary, "wordlist");
            else
                numWords = dictReader.readWordListStream(mainDictionary, DictFile.WordList, dictFile, "UTF8");
            dictionaryIsLoaded = true;
        }
    }

    private void loadReverseList()
    {
        if(reverseSortedDictionary == null)
            try
            {
                reverseSortedDictionary = new ArrayList(0x186a0);
                if(dictFile == null)
                {
                    if(numWords != WordList.readAll(reverseSortedDictionary, "blist"))
                        throw new RuntimeException("rlist.Length != wlist.Length");
                } else
                {
                    int i = dictReader.readWordListStream(reverseSortedDictionary, DictFile.ReverseList, dictFile, "UTF8");
                    if(numWords != i)
                        throw new RuntimeException("rlist.Length (" + i + ":" + reverseSortedDictionary.get(0) + ") != wlist.Length (" + numWords + ")");
                    dictFile.close();
                }
            }
            catch(IOException ioexception)
            {
                ioexception.printStackTrace();
                if(numWords != WordList.readAll(reverseSortedDictionary, "blist"))
                    throw new RuntimeException("rlist.Length != wlist.Length");
            }
    }

    public synchronized boolean lookUp(String s)
        throws NullPointerException
    {
        if(s == null)
            throw new NullPointerException("Null String passed to lookup() - ensure query parameter is not null.");
        if(lookUpMainDictionary(s))
            return true;
        return lookUpUserDictionary(s);
    }

    private boolean lookUpMainDictionary(String s)
    {
        try
        {
            loadLexicon();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        if(s.length() <= 1)
            return true;
        int i;
        int j;
        if(ignoreXML)
            while((i = s.indexOf('<')) > -1 && (j = s.indexOf('>', i)) > -1) 
                s = s.substring(0, i) + s.substring(j + 1);
        if(allowAnyCase)
            s = s.toUpperCase();
        if(isAllCaps(s))
        {
            if(ignoreCapitalizedWords)
                return true;
            if(Collections.binarySearch(mainDictionary, s) >= 0)
                return true;
            s = s.charAt(0) + s.substring(1).toLowerCase();
        }
        if(Collections.binarySearch(mainDictionary, s) >= 0)
            return true;
        if((!isMixedCase(s) || allowMixedCase) && Collections.binarySearch(mainDictionary, s.toLowerCase()) >= 0)
            return true;
        if(lookIntoHyphenatedText && (i = s.indexOf('-')) > -1)
            return lookUpMainDictionary(s.substring(0, i)) && lookUpMainDictionary(s.substring(i + 1));
        else
            return false;
    }

    private boolean lookUpUserDictionary(String s)
    {
        if(s == null || Key.ver.equals("evaluation"))
            return false;
        if(s.length() <= 1)
            return true;
        int i;
        int k;
        if(ignoreXML)
            while((i = s.indexOf('<')) > -1 && (k = s.indexOf('>', i)) > -1) 
                s = s.substring(0, i) + s.substring(k + 1);
        if(userDictionary != null && userDictionary.isValid())
        {
            for(int j = 0; j < userDicList.size(); j++)
                if(((String)userDicList.get(j)).equals(s) || !s.equals(s.toLowerCase()) && ((String)userDicList.get(j)).equals(s.toLowerCase()))
                    return true;

        }
        return false;
    }

    private String makeCap(String s)
    {
        if(s.length() > 1)
            s = s.toUpperCase().charAt(0) + s.substring(1);
        else
            s = s.toUpperCase();
        return s;
    }

    public BadWord nextBadWord()
    {
        boolean flag = false;
        boolean flag1 = false;
        String s = getNextWord();
        currentBadWordStart = -1;
        currentBadWordEnd = -1;
        if(s == null)
            return null;
        if(getWarnDuplicates() && previousWord.toLowerCase().equals(s.toLowerCase()) && !wordIsDigits(previousWord))
        {
            int i = wordStart > 4 ? 4 : wordStart;
            flag = theText.charAt(wordStart - 1) == ' ' && theText.toString().substring(wordStart - i, wordStart).lastIndexOf('.', wordStart) == -1 && theText.toString().substring(wordStart - i, wordStart).lastIndexOf(',', wordStart) == -1;
        }
        while((!flagged(s) || ignoreList.contains(s)) && !flag) 
        {
            previousWord = s;
            s = getNextWord();
            if(s == null)
                return null;
            if(getWarnDuplicates() && previousWord.toLowerCase().equals(s.toLowerCase()) && !wordIsDigits(previousWord))
            {
                int j = wordStart > 4 ? 4 : wordStart;
                flag = theText.charAt(wordStart - 1) == ' ' && theText.toString().substring(wordStart - j, wordStart).lastIndexOf('.', wordStart) == -1 && theText.toString().substring(wordStart - j, wordStart).lastIndexOf(',', wordStart) == -1;
            }
        }
        previousWord = s;
        currentBadWordStart = wordStart;
        currentBadWordEnd = wordEnd;
        int k;
        int l;
        if(ignoreXML)
            while((k = s.indexOf('<')) > -1 && (l = s.indexOf('>', k)) > -1) 
                s = s.substring(0, k) + s.substring(l + 1);
        BadWord badword;
        if(!flag)
            badword = new BadWord(s, currentBadWordStart, currentBadWordEnd);
        else
            badword = new BadWord(s, currentBadWordStart, currentBadWordEnd, BadWord.REASON_DUPLICATE);
        return badword;
    }

    private void preCheck()
    {
        String s;
        while((s = getNextWord()) != null) 
            if(!badWordList.contains(s) && !lookUpMainDictionary(s) && (!getIgnoreWordsWithDigits() || !wordHasDigits(s)) && !wordIsDigits(s))
                badWordList.add(s);
        reset();
        for(Enumeration enumeration = ((Vector)badWordList.clone()).elements(); enumeration.hasMoreElements();)
        {
            String s1 = (String)enumeration.nextElement();
            if(lookUpUserDictionary(s1))
                badWordList.remove(s1);
            else
            if(checkCompoundWords && findCompoundWords(s1, null))
                badWordList.remove(s1);
        }

    }

    private void reset()
    {
        setPosition(0);
    }

    public void setAllowAnyCase(boolean flag)
    {
        allowAnyCase = flag;
    }

    public void setAllowMixedCase(boolean flag)
    {
        allowMixedCase = flag;
    }

    public void setCheckCompoundWords(boolean flag)
    {
        checkCompoundWords = flag;
    }

    public void setConsiderationRange(int i)
    {
        radius = i;
    }

    public void setDictFilePath(String s)
    {
        try
        {
            dictFilePath = s;
            if(dictFilePath != null)
                dictFile = new DictFile(dictFilePath);
            else
                dictFile = null;
            reverseSortedDictionary = null;
            dictionaryIsLoaded = false;
        }
        catch(FileNotFoundException _ex)
        {
            System.err.println("Main Dictionary .Dict File not found: " + s);
            dictFile = null;
            dictFilePath = null;
        }
        catch(IOException ioexception)
        {
            System.err.println("Couldn't read dictionary file: " + s + " \n" + ioexception);
            dictFile = null;
            dictFilePath = null;
        }
    }

    public void setDictFileStream(InputStream inputstream)
        throws IOException, IllegalStateException
    {
        if(dictionaryIsLoaded)
            throw new IllegalStateException("setDictFileStream called after dictionary has been loaded.");
        dictFileStream = inputstream;
        if(inputstream != null)
            dictFile = new DictFile(inputstream);
        else
            dictFile = null;
        dictReader.autoCloseReader = false;
        reverseSortedDictionary = null;
        dictionaryIsLoaded = false;
        loadLexicon();
    }

    public void setIgnoreCapitalizedWords(boolean flag)
    {
        ignoreCapitalizedWords = flag;
    }

    public void setIgnoreWordsWithDigits(boolean flag)
    {
        ignoreWordsWithDigits = flag;
    }

    public void setIgnoreXML(boolean flag)
    {
        wordIterator.ignoreXML = flag;
        ignoreXML = flag;
    }

    public void setIncludeUserDictionaryInSuggestions(boolean flag)
    {
        includeUserDictionaryInSuggestions = flag;
    }

    public void setLanguageParser(int i)
    {
        wordIterator.languageParsing = i;
    }

    public void setLookIntoHyphenatedText(boolean flag)
    {
        lookIntoHyphenatedText = flag;
    }

    public void setMaximumAnagramLength(int i)
    {
        maximumAnagramLength = i;
    }

    public void setOptimization(int i)
    {
        if(i == OPTIMIZE_FOR_MEMORY || i == OPTIMIZE_FOR_SPEED)
            optimization = i;
    }

    public void setPosition(int i)
    {
        if(i < 0)
            i = 0;
        if(i > theText.length())
            i = theText.length();
        wordEnd = i;
    }

    public void setSeparateHyphenWords(boolean flag)
    {
        wordIterator.separateHyphenWords = flag;
    }

    public void setSuggestSplitWords(boolean flag)
    {
        suggestSplitWords = flag;
    }

    public void setSuggestionsMethod(int i)
    {
        if(i == PHONETIC_SUGGESTIONS || i == HASHING_SUGGESTIONS)
            suggestionsMethod = i;
    }

    public void setUserDictionary(UserDictionary userdictionary)
        throws NullPointerException
    {
        if(userdictionary == null)
        {
            throw new NullPointerException("Null UserDictionary object passed to setUserDictionary().");
        } else
        {
            userDictionary = userdictionary;
            userDictionary.readAll(userDicList);
            return;
        }
    }

    public void setUserDictionary(File file)
        throws NullPointerException
    {
        if(file == null)
        {
            throw new NullPointerException("Null user dictionary file passed to setUserDictionary().");
        } else
        {
            userDictionary = new UserDictionary(file);
            userDictionary.readAll(userDicList);
            return;
        }
    }

    public void setWarnDuplicates(boolean flag)
    {
        warnDuplicates = flag;
    }

    static double suggestionScore(String s, String s1)
    {
        double d = 0.29999999999999999D;
        double d1 = 0.25D;
        double d2 = 1.0D;
        double d3 = 0.5D;
        double d4 = 1.0D;
        double d5 = 0.5D;
        double d6 = 0.0D;
        double d7 = s1.length();
        int i = (int)d7 * 2;
        for(int l = 0; l < s1.length(); l++)
        {
            char c = s1.charAt(l);
            int j = -1;
            boolean flag = false;
            while(j < s.length() && (j = s.indexOf(c, j + 1)) > -1) 
            {
                if(!flag)
                {
                    d6 += d4;
                    flag = true;
                } else
                {
                    d6 -= d5;
                }
                if(l > 0)
                {
                    if(j > 0)
                        if(s1.charAt(l - 1) == s.charAt(j - 1))
                            d6 += d2;
                        else
                            d6 -= d1;
                } else
                if(s1.charAt(0) == s.charAt(0))
                    d6 += d2;
            }
        }

        for(int k = 0; k < s.length(); k++)
            if(s1.indexOf(s.charAt(k)) == -1)
                d6 -= d3;

        d6 -= (double)Math.abs(s1.length() - s.length()) * d;
        return d6 / (double)i;
    }

    double suggestionScore2(String s, String s1)
    {
        if(loadedQryWrd == null || !loadedQryWrd.equals(s1))
        {
            loadedQryWrd = s1;
            qryLenI = s1.length();
            qryLen = qryLenI;
            qryWrd = s1.toLowerCase().toCharArray();
        }
        maxScore = s.length();
        tail = maxScore;
        if(maxScore >= qryLenI)
        {
            if((double)(maxScore - qryLenI) > qryLen * 0.5D)
                return 0.0D;
        } else
        if((double)(qryLenI - maxScore) > qryLen * 0.5D)
            return 0.0D;
        tally = 0;
        for(int i = 0; i < qryLenI; i++)
            if(s.indexOf(qryWrd[i], 0) > -1)
                tally++;

        if((double)tally < (double)qryLenI - (double)qryLenI * 0.38D)
            return 0.0D;
        else
            return suggestionScore2b(s, s1);
    }

    double suggestionScore2b(String s, String s1)
    {
        if(loadedQryWrd == null || !loadedQryWrd.equals(s1))
        {
            loadedQryWrd = s1;
            qryLenI = s1.length();
            qryLen = qryLenI;
            qryWrd = s1.toLowerCase().toCharArray();
        }
        maxScore = s.length();
        tail = maxScore;
        agreement = 0.0D;
        taken = new int[maxScore];
        p = 0;
        for(int i = 0; i < maxScore; i++)
        {
            smallestDistance = tail;
            cChar = s.charAt(i);
            for(int j = 0; j < qryLenI; j++)
            {
                isTaken = false;
                if(cChar == qryWrd[j])
                {
                    for(int k = 0; k < p; k++)
                    {
                        if(taken[k] != j)
                            continue;
                        isTaken = true;
                        break;
                    }

                    if(!isTaken)
                    {
                        if(j > i)
                            distance = j - i;
                        else
                            distance = i - j;
                        if(distance < smallestDistance)
                        {
                            smallestDistance = distance;
                            chosenCharPos = j;
                        }
                    }
                }
            }

            if(smallestDistance != tail)
            {
                taken[p] = chosenCharPos;
                if(p < 15)
                    p++;
                agreement += (double)(tail - smallestDistance) / (double)tail;
            }
        }

        return agreement / (double)s1.length();
    }

    double suggestionScore2bc(String s, String s1)
    {
        s = s.toLowerCase();
        if(loadedQryWrd == null || !loadedQryWrd.equals(s1))
        {
            loadedQryWrd = s1;
            qryLenI = s1.length();
            qryLen = qryLenI;
            qryWrd = s1.toLowerCase().toCharArray();
        }
        maxScore = s.length();
        tail = qryLenI;
        agreement = 0.0D;
        taken = new int[qryLenI];
        p = 0;
        for(int i = 0; i < qryLenI; i++)
        {
            smallestDistance = tail;
            for(int j = 0; j < maxScore; j++)
            {
                cChar = s.charAt(j);
                isTaken = false;
                if(cChar == qryWrd[i])
                {
                    for(int k = 0; k < p; k++)
                    {
                        if(taken[k] != i)
                            continue;
                        isTaken = true;
                        break;
                    }

                    if(!isTaken)
                    {
                        if(i > j)
                            distance = i - j;
                        else
                            distance = j - i;
                        if(distance < smallestDistance)
                        {
                            smallestDistance = distance;
                            chosenCharPos = i;
                        }
                    }
                }
            }

            if(smallestDistance != tail)
            {
                taken[p] = chosenCharPos;
                if(p < 15)
                    p++;
                agreement += (double)(tail - smallestDistance) / (double)tail;
            }
        }

        return agreement / (double)qryLenI;
    }

    private boolean wordHasDigits(String s)
    {
        for(int i = 0; i < s.length(); i++)
            if(Character.isDigit(s.charAt(i)))
                return true;

        return false;
    }

    private boolean wordIsDigits(String s)
    {
        for(int i = 0; i < s.length(); i++)
            if(!Character.isDigit(s.charAt(i)))
                return false;

        return true;
    }

    int wordStart;
    int wordEnd;
    public Vector ignoreList;
    private Vector badWordList;
    public int currentBadWordEnd;
    public int currentBadWordStart;
    private StringBuffer theText;
    private AdvancedTextBoundary wordIterator;
    String previousWord;
    private ArrayList mainDictionary;
    private ArrayList mainDicListPhonetic;
    private ArrayList userDicList;
    boolean ignoreWordsWithDigits;
    boolean warnDuplicates;
    private int numWords;
    private int maximumAnagramLength;
    private CompareL compare;
    private static int OPTIMIZE_FOR_SPEED = 1;
    private static int OPTIMIZE_FOR_MEMORY = 2;
    private int optimization;
    public static int PHONETIC_SUGGESTIONS = 1;
    public static int HASHING_SUGGESTIONS = 2;
    private int suggestionsMethod;
    protected String dontSuggest[] = {
        "fart", "farted", "farting", "farts", "fuck", "fuck-all", "fucked", "fucker", "fuckers", "fucking", 
        "fucks", "shit", "shits", "shitted", "shitting", "fucked", "fucked-up", "piss", "pissed", "pisses", 
        "pissing", "wank", "wanked", "wanking", "wanks", "arse", "arsehole", "arseholes", "arses", "ass-head", 
        "asshole", "assholes", "orgy", "orgies", "slut"
    };
    InputStream dictFileStream;
    public UserDictionary userDictionary;
    DictFile dictFile;
    String dictFilePath;
    boolean allowMixedCase;
    boolean allowAnyCase;
    char qryWrd[];
    String loadedQryWrd;
    private boolean includeUserDictionaryInSuggestions;
    private boolean ignoreCapitalizedWords;
    private boolean ignoreEmails;
    private boolean lookIntoHyphenatedText;
    private boolean ignoreXML;
    boolean checkCompoundWords;
    boolean suggestSplitWords;
    int taken[];
    int p;
    int pt;
    int maxScore;
    int tail;
    char cChar;
    char qChar;
    int smallestDistance;
    int distance;
    double agreement;
    boolean isTaken;
    int qryLenI;
    double qryLen;
    int chosenCharPos;
    int tally;
    private ArrayList sizeSortedDictionary;
    private ArrayList reverseSortedDictionary;
    private int sizeSortedDicLookup[];
    private int radius;
    boolean dictionaryIsLoaded;
    ResourceDecoder resourceDecoder;
    DictReader dictReader;

}
