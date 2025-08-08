package mojo.util.spellcheck;

import java.io.*;
import java.text.BreakIterator;
import java.util.*;

public class Test
{

    public Test()
    {
        key = "544F645B6157635C655667665E5763354E504D3B3541373C3C3A403F40453F484A474A3";
        testIgnoreNumbers();
        testEmailIterator();
        testSuggestionMapping();
        testSimpleTextBoundary();
        testAdvancedTextBoundary();
        testSimpleTextBoundaryWithIgnoreXML();
        testUserDictionary();
        testSpeed();
        testMetaPhone();
        testReplace();
        testWildInput();
        testExceptions();
        testDictionaryOrder();
        testSuggestionsSpeed();
        testSuggestionsSpeedIncUD();
        testSuggestions();
        testDictionaryAccess();
        testInstantiateSpeed();
    }

    private void assertTrue(boolean flag, String s)
    {
        if(!flag)
        {
            System.out.println("assertion failed: " + s);
            System.exit(1);
        }
    }

    public static void main(String args[])
    {
        Test test = new Test();
    }

    private void mesg(String s)
    {
        System.out.println(s);
    }

    void testAdvancedTextBoundary()
    {
        System.out.println("Testing AdvancedTextBoundary:");
        AdvancedTextBoundary advancedtextboundary = new AdvancedTextBoundary();
        advancedtextboundary.setText("one two three");
        assertTrue(advancedtextboundary.following(0) == 3, "s.following() 0");
        assertTrue(advancedtextboundary.isBoundary(advancedtextboundary.following(0)), "isBoundary");
        assertTrue(advancedtextboundary.following(3) == 4, "s.following() 3");
        assertTrue(advancedtextboundary.isBoundary(advancedtextboundary.following(3)), "isBoundary");
        assertTrue(advancedtextboundary.following(4) == 7, "s.following() 4");
        assertTrue(advancedtextboundary.isBoundary(advancedtextboundary.following(4)), "isBoundary");
        assertTrue(advancedtextboundary.following(7) == 8, "s.following() 7");
        assertTrue(advancedtextboundary.isBoundary(advancedtextboundary.following(7)), "isBoundary");
        assertTrue(advancedtextboundary.following(8) == 13, "s.following() 8");
        assertTrue(advancedtextboundary.preceding(0) == -1, "s.preceding() 0");
        assertTrue(advancedtextboundary.preceding(1) == 0, "s.preceding() 1");
        assertTrue(advancedtextboundary.preceding(6) == 4, "s.preceding() 6");
        assertTrue(advancedtextboundary.preceding(7) == 4, "s.preceding() 7");
        assertTrue(advancedtextboundary.preceding(8) == 7, "s.preceding() 8");
        assertTrue(advancedtextboundary.preceding(9) == 8, "s.preceding() 9");
        try
        {
            assertTrue(advancedtextboundary.following(13) == 13, "s.following() 13");
            assertTrue(false, "no exception");
        }
        catch(Exception exception)
        {
            assertTrue(true, "correct exception " + exception);
        }
        advancedtextboundary.setText("one! 'two three?");
        assertTrue(advancedtextboundary.following(0) == 3, "s.following() 0");
        assertTrue(advancedtextboundary.following(3) == 6, "s.following() 0");
        advancedtextboundary.setText("  one two three");
        assertTrue(advancedtextboundary.following(0) == 2, "spaces");
    }

    void testDictFile()
    {
        System.out.println("Testing Dict File:");
        SpellChecker rapidspellchecker = new SpellChecker(key);
        rapidspellchecker.setDictFilePath("tests/DictManagerTest.dict");
        StringBuffer stringbuffer = new StringBuffer();
        try
        {
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(new FileInputStream("tests/DictManagerTestSource.txt"), "Unicode"));
            String s;
            while((s = bufferedreader.readLine()) != null) 
                stringbuffer.append(s).append(" ");
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        rapidspellchecker.check(stringbuffer.toString());
        BadWord badword;
        while((badword = rapidspellchecker.nextBadWord()) != null) 
        {
            System.out.println(badword.getWord());
            assertTrue(false, badword.getWord());
        }
        rapidspellchecker = null;
        System.gc();
    }

    void testDictionaryAccess()
    {
        System.out.println("Testing Dictionary Access:");
        ArrayList arraylist = new ArrayList(0x186a0);
        WordList.readAll(arraylist);
        assertTrue(((String)arraylist.get(0)).equals("AA"), "AA not first. " + (String)arraylist.get(0));
    }

    void testDictionaryOrder()
    {
        System.out.println("Testing Dictionary Order:");
        ArrayList arraylist = new ArrayList();
        WordList.readAll(arraylist);
        ArrayList arraylist1 = (ArrayList)arraylist.clone();
        Collections.sort(arraylist1);
        for(int i = 0; i < arraylist.size(); i++)
        {
            if(arraylist1.get(i).equals(arraylist.get(i)))
                continue;
            assertTrue(false, "Not Sorted");
            System.out.println("Dictionary not sorted!");
            break;
        }

    }

    void testEmailIterator()
    {
        System.out.println("Testing Email Iterator");
        CustomWordIterator customworditerator = new CustomWordIterator(BreakIterator.getWordInstance(), true);
        customworditerator.setText("jim@keyoti.com is my");
        assertTrue(customworditerator.following(0) == 14, "Email iterator is not working");
        customworditerator.setText("this is my email jim@keyoti.com is my");
        assertTrue(customworditerator.following(17) == 31, "Email iterator is not working");
        assertTrue(customworditerator.preceding(19) == 17, "Email iterator (preceding) is not working");
        assertTrue(customworditerator.preceding(28) == 17, "Email iterator (preceding) is not working");
    }

    void testExceptions()
    {
        mesg("Testing Exceptions:");
        SpellChecker rapidspellchecker = new SpellChecker(key);
        try
        {
            rapidspellchecker.changeBadWord("word");
            assertTrue(false, "didnt throw correct exception");
        }
        catch(NoCurrentBadWordException _ex)
        {
            assertTrue(true, "threw correct exception");
        }
        try
        {
            String s = "";
            s = null;
            rapidspellchecker.changeBadWord(s);
            assertTrue(false, "didnt throw correct exception");
        }
        catch(Exception _ex)
        {
            assertTrue(true, "threw correct exception");
        }
    }

    void testIgnoreNumbers()
    {
        System.out.println("Test ignore numbers:");
        SpellChecker rapidspellchecker = new SpellChecker(key);
        rapidspellchecker.setIgnoreWordsWithDigits(true);
        rapidspellchecker.check("7878uhsdiuf shdfiui87878");
        BadWord badword;
        while((badword = rapidspellchecker.nextBadWord()) != null) 
            System.out.println(badword.getWord());
    }

    void testInstantiateSpeed()
    {
        System.out.println("Testing Startup Speed With MDict:");
        long l = System.currentTimeMillis();
        SpellChecker rapidspellchecker = new SpellChecker(key);
        rapidspellchecker.lookUp("a");
        long l1 = System.currentTimeMillis();
        double d = l1 - l;
        double d1 = d;
        System.out.println(d1 + "ms ");
        System.out.println("Testing Startup Speed With Dict File:");
        l = System.currentTimeMillis();
        rapidspellchecker = new SpellChecker(key);
        rapidspellchecker.setDictFilePath("G:/work/components/rapidspell/lexicons/packaging/whole/uk-english-v2-whole/dist/lib/java/uk-english-v2-whole.dict");
        rapidspellchecker.lookUp("a");
        l1 = System.currentTimeMillis();
        d = l1 - l;
        d1 = d;
        System.out.println(d1 + "ms ");
    }

    void testIteratorSpeeds()
    {
        System.out.println("Test built in:");
        Object obj = BreakIterator.getWordInstance();
        StringBuffer stringbuffer = new StringBuffer("");
        for(int i = 0; i < 0xf4240; i++)
            stringbuffer.append("word ");

        ((BreakIterator) (obj)).setText(stringbuffer.toString());
        long l = System.currentTimeMillis();
        int k = 0;
        for(int i1 = stringbuffer.length(); (k = ((BreakIterator) (obj)).following(k)) < i1;);
        long l1 = System.currentTimeMillis();
        double d = l1 - l;
        double d1 = d;
        System.out.println(d1 + "ms = " + 1000000D / d1 + " w/ms");
        System.gc();
        System.out.println("Test custom:");
        obj = new SimpleTextBoundary();
        stringbuffer = new StringBuffer("");
        for(int j = 0; j < 0xf4240; j++)
            stringbuffer.append("word ");

        ((SimpleTextBoundary) (obj)).setText(stringbuffer.toString());
        l = System.currentTimeMillis();
        k = 0;
        for(int j1 = stringbuffer.length(); (k = ((SimpleTextBoundary) (obj)).following(k)) < j1;);
        l1 = System.currentTimeMillis();
        d = l1 - l;
        d1 = d;
        System.out.println(d1 + "ms = " + 1000000D / d1 + " w/ms");
    }

    void testMetaPhone()
    {
    }

    void testReplace()
    {
        mesg("Testing Replace:");
        SpellChecker rapidspellchecker = new SpellChecker(key);
        rapidspellchecker.check("this is the input text missspelt");
        try
        {
            BadWord badword;
            while((badword = rapidspellchecker.nextBadWord()) != null) 
                rapidspellchecker.changeBadWord("REPLaCeMENT");
            assertTrue(rapidspellchecker.getAmendedText().equals("this is the input text REPLaCeMENT"), "replacement failed " + rapidspellchecker.getAmendedText());
        }
        catch(Exception exception)
        {
            assertTrue(false, "Exception occured " + exception);
        }
    }

    void testSimpleTextBoundary()
    {
        System.out.println("Testing SimpleTextBoundary:");
        SimpleTextBoundary simpletextboundary = new SimpleTextBoundary();
        simpletextboundary.setText("one two three");
        assertTrue(simpletextboundary.following(0) == 3, "s.following() 0");
        assertTrue(simpletextboundary.isBoundary(simpletextboundary.following(0)), "isBoundary");
        assertTrue(simpletextboundary.following(3) == 4, "s.following() 3");
        assertTrue(simpletextboundary.isBoundary(simpletextboundary.following(3)), "isBoundary");
        assertTrue(simpletextboundary.following(4) == 7, "s.following() 4");
        assertTrue(simpletextboundary.isBoundary(simpletextboundary.following(4)), "isBoundary");
        assertTrue(simpletextboundary.following(7) == 8, "s.following() 7");
        assertTrue(simpletextboundary.isBoundary(simpletextboundary.following(7)), "isBoundary");
        assertTrue(simpletextboundary.following(8) == 13, "s.following() 8");
        assertTrue(simpletextboundary.preceding(0) == -1, "s.preceding() 0");
        assertTrue(simpletextboundary.preceding(1) == 0, "s.preceding() 1");
        assertTrue(simpletextboundary.preceding(6) == 4, "s.preceding() 6");
        assertTrue(simpletextboundary.preceding(7) == 4, "s.preceding() 7");
        assertTrue(simpletextboundary.preceding(8) == 7, "s.preceding() 8");
        assertTrue(simpletextboundary.preceding(9) == 8, "s.preceding() 9");
        try
        {
            assertTrue(simpletextboundary.following(13) == 13, "s.following() 13");
            assertTrue(false, "no exception");
        }
        catch(Exception exception)
        {
            assertTrue(true, "correct exception " + exception);
        }
        simpletextboundary.setText("one! 'two three?");
        assertTrue(simpletextboundary.following(0) == 3, "s.following() 0");
        assertTrue(simpletextboundary.following(3) == 6, "s.following() 0");
        simpletextboundary.setText("  one two three");
        assertTrue(simpletextboundary.following(0) == 2, "spaces");
        simpletextboundary.setText("don't   couldn't 'quote'");
        assertTrue(simpletextboundary.following(0) == 5, "s.following() 0'1");
        assertTrue(simpletextboundary.following(8) == 16, "s.following() 8'1");
        assertTrue(simpletextboundary.following(16) == 18, "s.following() 16'1");
        assertTrue(simpletextboundary.following(18) == 23, "s.following() 18'1");
        simpletextboundary.setText("don't   couldn't ");
        assertTrue(simpletextboundary.preceding(5) == 0, "s.preceding() 0'2");
        assertTrue(simpletextboundary.preceding(16) == 8, "s.preceding() 8'2");
        simpletextboundary.setText("sho'ufld");
        assertTrue(simpletextboundary.preceding(4) == 0, "s.preceding() 0'2");
        assertTrue(simpletextboundary.following(4) == 8, "s.following() 4'2");
        assertTrue(!simpletextboundary.isBoundary(4), "s.isBoundary 4'");
        simpletextboundary.setText("'dogn't'");
        assertTrue(simpletextboundary.following(0) == 1, "s.following() 0'3");
        assertTrue(simpletextboundary.preceding(7) == 1, "s.preceding() 7'3");
        assertTrue(simpletextboundary.preceding(8) == 7, "s.preceding() 8'3");
        assertTrue(simpletextboundary.isBoundary(1), "s.isBoundary 1'3");
        simpletextboundary.setText("wordEnd. word");
        assertTrue(simpletextboundary.following(2) == 7, "s.following() 2." + simpletextboundary.following(2));
        assertTrue(simpletextboundary.preceding(10) == 9, "s.preceding() 10." + simpletextboundary.preceding(9));
        assertTrue(simpletextboundary.isBoundary(7), "s.isBoundary 7.");
        simpletextboundary.setText("word-end. word");
        assertTrue(simpletextboundary.following(0) == 8, "s.following() 0." + simpletextboundary.following(8));
        assertTrue(simpletextboundary.preceding(8) == 0, "s.preceding() 8." + simpletextboundary.preceding(8));
        assertTrue(simpletextboundary.isBoundary(8), "s.isBoundary 0.");
        assertTrue(!simpletextboundary.isBoundary(4), "s.isBoundary 4.");
        simpletextboundary.setText("word-d word");
        assertTrue(simpletextboundary.following(0) == 6, "s.following() 0." + simpletextboundary.following(6));
        assertTrue(simpletextboundary.preceding(5) == 0, "s.preceding() 5." + simpletextboundary.preceding(5));
        simpletextboundary.setText("word word word word");
        assertTrue(simpletextboundary.isBoundaryLeft(0), "s.isBoundaryLeft(0).");
        assertTrue(simpletextboundary.isBoundaryLeft(5), "s.isBoundaryLeft(5).");
        assertTrue(simpletextboundary.isBoundaryLeft(10), "s.isBoundaryLeft(10).");
        assertTrue(simpletextboundary.isBoundaryLeft(15), "s.isBoundaryLeft(15).");
        assertTrue(simpletextboundary.isBoundaryRight(0) ^ true, "!s.isBoundaryRight(0).");
        assertTrue(simpletextboundary.isBoundaryRight(5) ^ true, "!s.isBoundaryRight(5).");
        assertTrue(simpletextboundary.isBoundaryRight(10) ^ true, "!s.isBoundaryRight(10).");
        assertTrue(simpletextboundary.isBoundaryRight(15) ^ true, "!s.isBoundaryRight(15).");
        assertTrue(simpletextboundary.isBoundaryRight(4), "s.isBoundaryRight(4).");
        assertTrue(simpletextboundary.isBoundaryRight(9), "s.isBoundaryRight(9).");
        assertTrue(simpletextboundary.isBoundaryRight(14), "s.isBoundaryRight(14).");
        assertTrue(simpletextboundary.isBoundaryRight(19), "s.isBoundaryRight(19).");
        assertTrue(simpletextboundary.isBoundaryRight(18) ^ true, "!s.isBoundaryRight(18).");
    }

    void testSimpleTextBoundaryWithIgnoreXML()
    {
        System.out.println("Testing AdvancedTextBoundary With IgnoreXML:");
        AdvancedTextBoundary advancedtextboundary = new AdvancedTextBoundary();
        advancedtextboundary.ignoreXML = true;
        advancedtextboundary.setText("one two <three> four <five>six seven");
        assertTrue(advancedtextboundary.following(0) == 3, "s.following() 0 " + advancedtextboundary.following(0));
        assertTrue(advancedtextboundary.isBoundary(advancedtextboundary.following(0)), "isBoundary");
        assertTrue(advancedtextboundary.following(3) == 4, "s.following() 3");
        assertTrue(advancedtextboundary.isBoundary(advancedtextboundary.following(3)), "isBoundary");
        assertTrue(advancedtextboundary.following(4) == 7, "s.following() 4 " + advancedtextboundary.following(4));
        assertTrue(advancedtextboundary.isBoundary(advancedtextboundary.following(4)), "isBoundary");
        assertTrue(advancedtextboundary.following(7) == 16, "s.following() 7 " + advancedtextboundary.following(7));
        assertTrue(advancedtextboundary.isBoundary(advancedtextboundary.following(7)), "isBoundary");
        assertTrue(advancedtextboundary.following(21) == 27, "s.following() 21 " + advancedtextboundary.following(21));
        advancedtextboundary.setText("one two w<three>oo four five<six>seven<eight>nine ten<font> eleven<b>twelve <ijiji>text word<b>word");
        assertTrue(advancedtextboundary.following(8) == 9, "s.following()a 8 " + advancedtextboundary.following(8));
        assertTrue(advancedtextboundary.following(24) == 28, "s.following() 24 " + advancedtextboundary.following(24));
        assertTrue(advancedtextboundary.following(50) == 53, "s.following() 50 " + advancedtextboundary.following(50));
        assertTrue(advancedtextboundary.following(60) == 75, "s.following() 60 " + advancedtextboundary.following(60));
        assertTrue(advancedtextboundary.preceding(86) == 83, "s.preceding() 86 " + advancedtextboundary.preceding(86));
        assertTrue(advancedtextboundary.preceding(80) == 75, "s.preceding() 80 " + advancedtextboundary.preceding(80));
        assertTrue(advancedtextboundary.preceding(71) == 60, "s.preceding() 71 " + advancedtextboundary.preceding(71));
        assertTrue(advancedtextboundary.preceding(74) == 60, "s.preceding() 74 " + advancedtextboundary.preceding(74));
        assertTrue(advancedtextboundary.preceding(97) == 88, "s.preceding() 97 " + advancedtextboundary.preceding(97));
        advancedtextboundary.setText("one two &nbsp; sep &amp; AT&amp;T ending &nbsp;");
        assertTrue(advancedtextboundary.following(8) == 15, "s.following()b 8 " + advancedtextboundary.following(8));
        assertTrue(advancedtextboundary.following(4) == 7, "s.following() 4 " + advancedtextboundary.following(4));
        assertTrue(advancedtextboundary.following(15) == 18, "s.following() 15 " + advancedtextboundary.following(15));
        assertTrue(advancedtextboundary.following(19) == 25, "s.following() 19 " + advancedtextboundary.following(19));
        assertTrue(advancedtextboundary.following(25) == 27, "s.following() 25 " + advancedtextboundary.following(25));
        assertTrue(advancedtextboundary.following(19) == 25, "s.following() 19 " + advancedtextboundary.following(19));
        assertTrue(advancedtextboundary.following(34) == 40, "s.following() 34 " + advancedtextboundary.following(34));
        assertTrue(advancedtextboundary.following(41) == 47, "s.following() 41 " + advancedtextboundary.following(41));
        advancedtextboundary.setText("one two</font> three</font>");
        assertTrue(advancedtextboundary.following(4) == 7, "s.following()c 4 " + advancedtextboundary.following(4));
        assertTrue(advancedtextboundary.following(15) == 20, "s.following()c 15 " + advancedtextboundary.following(15));
        advancedtextboundary.setText("one two<script> three</script> hello");
        assertTrue(advancedtextboundary.following(7) == 31, "s.following()d 7 " + advancedtextboundary.following(7));
        advancedtextboundary.setText("<script> three</script>");
        assertTrue(advancedtextboundary.following(0) == 23, "s.following()d 0 " + advancedtextboundary.following(0));
        advancedtextboundary.setText("<script>three</script>");
        assertTrue(advancedtextboundary.following(0) == 22, "s.following()d 0 " + advancedtextboundary.following(0));
        advancedtextboundary.setText("<script>three");
        assertTrue(advancedtextboundary.following(0) == 8, "s.following()d 0 " + advancedtextboundary.following(0));
    }

    void testSpeed()
    {
        System.out.print("Testing Speed:");
        SpellChecker rapidspellchecker = new SpellChecker(key);
        rapidspellchecker.lookUp("load dic");
        String s1 = "";
        try
        {
            BufferedReader bufferedreader = new BufferedReader(new FileReader("tests/versailles treaty 1-26.txt"));
            String s;
            while((s = bufferedreader.readLine()) != null) 
                s1 = s1 + s;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        long l = System.currentTimeMillis();
        rapidspellchecker.check(s1);
        BadWord badword;
        while((badword = rapidspellchecker.nextBadWord()) != null) ;
        long l1 = System.currentTimeMillis();
        double d = l1 - l;
        double d1 = d / 1000D;
        double d2 = 4167D / d1;
        System.out.println(d1 + "s " + d2 + "w/s");
        assertTrue(d2 > 3000D, "too slow!");
        rapidspellchecker = null;
        System.gc();
    }

    void testSuggestionMapping()
    {
        SpellChecker rapidspellchecker = new SpellChecker(key);
        System.out.println(rapidspellchecker.suggestionScore2("aspish", "nesessary"));
        System.out.println(rapidspellchecker.suggestionScore2("necessary", "nesessary"));
        System.out.println(rapidspellchecker.suggestionScore2("necessary", "necessary"));
    }

    void testSuggestions()
    {
        System.out.println("Testing suggestions:");
        SpellChecker rapidspellchecker = new SpellChecker(key);
        Vector vector = rapidspellchecker.findSuggestions("");
        assertTrue(vector.size() == 0, "Suggestions returned for empty string!");
        vector = rapidspellchecker.findSuggestions("nesessary");
        assertTrue(((String)vector.get(0)).equals("necessary"), "Didnt return correct suggestions for 'nesessary'");
    }

    void testSuggestionsSpeed()
    {
        SpellChecker rapidspellchecker = new SpellChecker(key);
        rapidspellchecker.lookUp("load dic");
        rapidspellchecker.findSuggestions("load rev dic");
        System.out.print("Testing Suggestion Speed:");
        String s = "cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck ";
        rapidspellchecker.check(s);
        long l = System.currentTimeMillis();
        try
        {
            for(; rapidspellchecker.nextBadWord() != null; rapidspellchecker.findSuggestions());
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        long l1 = System.currentTimeMillis();
        double d = l1 - l;
        double d1 = d / 1000D;
        double d2 = 100D / d1;
        System.out.println(d1 + "s " + d2 + "w/s");
        System.out.print("Testing Suggestion Speed Different Words:");
        rapidspellchecker.lookUp("load dic");
        rapidspellchecker.findSuggestions("load rev dic");
        s = "nesessary werer porttch qween derck pitie caq piw poty asd poy mun vurm zoox cerv sere qop retchw oire itr";
        rapidspellchecker.check(s);
        l = System.currentTimeMillis();
        try
        {
            for(; rapidspellchecker.nextBadWord() != null; rapidspellchecker.findSuggestions());
        }
        catch(Exception exception1)
        {
            exception1.printStackTrace();
        }
        l1 = System.currentTimeMillis();
        d = l1 - l;
        d1 = d / 1000D;
        d2 = 20D / d1;
        System.out.println(d1 + "s " + d2 + "w/s");
        System.out.print("Testing Suggestion Speed 3 Letter Words:");
        rapidspellchecker.lookUp("load dic");
        rapidspellchecker.findSuggestions("load rev dic");
        s = "fha fha fha fha fha fha fha fha fha fha fha fha fha fha fha fha fha fha fha fha ";
        rapidspellchecker.check(s);
        l = System.currentTimeMillis();
        try
        {
            for(; rapidspellchecker.nextBadWord() != null; rapidspellchecker.findSuggestions());
        }
        catch(Exception exception2)
        {
            exception2.printStackTrace();
        }
        l1 = System.currentTimeMillis();
        d = l1 - l;
        d1 = d / 1000D;
        d2 = 20000D / d;
        System.out.println(d1 + "s " + d2 + "w/s");
    }

    void testSuggestionsSpeedIncUD()
    {
        File file = new File("mydict.txt");
        SpellChecker rapidspellchecker = new SpellChecker(key);
        rapidspellchecker.setUserDictionary(file);
        rapidspellchecker.setIncludeUserDictionaryInSuggestions(true);
        System.out.print("Testing Suggestion Speed (inc user dic):");
        String s = "cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck cheeck";
        rapidspellchecker.check(s);
        long l = System.currentTimeMillis();
        try
        {
            for(; rapidspellchecker.nextBadWord() != null; rapidspellchecker.findSuggestions());
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        long l1 = System.currentTimeMillis();
        double d = l1 - l;
        double d1 = d / 1000D;
        double d2 = 20D / d1;
        System.out.println(d1 + "s " + d2 + "w/s");
        rapidspellchecker = null;
        System.gc();
    }

    void testUserDictionary()
    {
    }

    void testWildInput()
    {
        mesg("Testing Wild Input:");
        SpellChecker rapidspellchecker = new SpellChecker(key);
        rapidspellchecker.check("");
        try
        {
            BadWord badword;
            while((badword = rapidspellchecker.nextBadWord()) != null) 
                rapidspellchecker.changeBadWord("REPLaCeMENT");
            assertTrue(rapidspellchecker.getAmendedText().equals(""), "replacement failed " + rapidspellchecker.getAmendedText());
        }
        catch(Exception exception)
        {
            assertTrue(false, "Exception occured " + exception);
        }
        rapidspellchecker.check("1");
        try
        {
            BadWord badword1;
            while((badword1 = rapidspellchecker.nextBadWord()) != null) 
                rapidspellchecker.changeBadWord("REPLaCeMENT");
            assertTrue(rapidspellchecker.getAmendedText().equals("1"), "replacement failed " + rapidspellchecker.getAmendedText());
        }
        catch(Exception exception1)
        {
            assertTrue(false, "Exception occured " + exception1);
        }
    }

    String key;
}
