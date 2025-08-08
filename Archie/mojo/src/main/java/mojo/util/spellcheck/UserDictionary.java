package mojo.util.spellcheck;

import java.io.*;
import java.util.ArrayList;

public class UserDictionary
{

    public UserDictionary()
    {
        valid = false;
        endPtr = 0;
    }

    public UserDictionary(File file)
    {
        this(file, 5000);
    }

    public UserDictionary(File file, int i)
    {
        valid = false;
        endPtr = 0;
        if(i > 13000 || i <= 0)
            i = 13000;
        try
        {
            if(file.isDirectory())
            {
                System.err.println("RapidSpellChecker Error: The user dictionary file specified is a directory, it must be a file - user dictionary will be ignored");
                valid = false;
            } else
            if(!file.exists())
            {
                file.createNewFile();
                if(file.exists())
                    valid = true;
                else
                    valid = false;
            } else
            {
                valid = true;
            }
        }
        catch(IOException ioexception)
        {
            System.err.println("IOException occured working with user dictionary file: " + ioexception);
            valid = false;
        }
        catch(SecurityException securityexception)
        {
            System.err.println("SecurityException occured working with user dictionary file: " + securityexception);
            valid = false;
        }
        wordLimit = i;
        wordList = new String[i];
        dictFile = file;
        if(valid)
            readDict();
    }

    public boolean addWord(String s)
    {
        if(isValid() && s.length() > 0)
        {
            if(endPtr < wordLimit)
            {
                wordList[endPtr] = s;
                endPtr++;
                return writeDict();
            } else
            {
                return false;
            }
        } else
        {
            return false;
        }
    }

    public boolean isValid()
    {
        return valid;
    }

    public int readAll(ArrayList arraylist)
    {
        for(int i = 0; i < endPtr; i++)
            arraylist.add(i, wordList[i]);

        return endPtr;
    }

    private boolean readDict()
    {
        int i = 0;
        endPtr = 0;
        try
        {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(dictFile), "UTF8"));
            String s;
            while((s = reader.readLine()) != null && i < wordLimit) 
            {
                wordList[i] = s;
                i++;
                endPtr++;
            }
        }
        catch(IOException ioexception)
        {
            System.err.println("UserDictionary IOException " + ioexception);
            valid = false;
            return false;
        }
        return true;
    }

    private boolean writeDict()
    {
        try
        {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dictFile), "UTF8")));
            for(int i = 0; i < endPtr; i++)
                writer.println(wordList[i]);

            writer.flush();
            return true;
        }
        catch(IOException ioexception)
        {
            System.err.println("UserDictionary IOException " + ioexception);
        }
        valid = false;
        return false;
    }

    public File dictFile;
    private boolean valid;
    private BufferedReader reader;
    private PrintWriter writer;
    private String wordList[];
    private int wordLimit;
    private int endPtr;
}
