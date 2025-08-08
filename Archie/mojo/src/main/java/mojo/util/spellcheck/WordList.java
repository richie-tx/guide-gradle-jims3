package mojo.util.spellcheck;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

class WordList
{

    WordList()
    {
    }

    static Class _mthclass$(String s)
    {
        try
        {
            return Class.forName(s);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new NoClassDefFoundError(classnotfoundexception.getMessage());
        }
    }

    static synchronized int readAll(ArrayList arraylist)
    {
        return readAll(arraylist, "wordlist");
    }

    static synchronized int readAll(ArrayList arraylist, String s)
    {
        try
        {
            Class cl = WordList.class;
            if(cl != null)
            {
	        	URL url = cl. getResource("/" + s);
	        	System.out.println("URL :" + url);
            	connection = url.openConnection();
	            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(new DecodedInputStream(connection.getInputStream()), "Unicode"));
	            arraylist.clear();
	            for(String s1 = bufferedreader.readLine(); s1 != null; s1 = bufferedreader.readLine())
	                arraylist.add(s1);
	
	            bufferedreader.close();
            }
            else
            {
            	System.out.println("Class is null");
            }
        }
        catch(IOException ioexception)
        {
            System.err.println("Can't open wordlist: " + ioexception);
        }
        catch(NullPointerException nullpointerexception)
        {
            System.err.println("Null pointer:" + nullpointerexception);
            nullpointerexception.printStackTrace();
        }
        return arraylist.size();
    }

    protected static int ptr;
    static URLConnection connection;
    
}
