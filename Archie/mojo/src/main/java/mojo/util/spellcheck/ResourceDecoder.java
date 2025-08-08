package mojo.util.spellcheck;

import java.util.ArrayList;

class ResourceDecoder
{

    ResourceDecoder()
    {
    }

    public int decodeWordList(ArrayList arraylist, byte abyte0[])
    {
        return decodeWordList(arraylist, abyte0, "Unicode");
    }

    public int decodeWordList(ArrayList arraylist, byte abyte0[], String s)
    {
        arraylist.clear();
        for(int i = 0; i < abyte0.length; i++)
            abyte0[i] = (byte)((abyte0[i] - 50) % 256);

        try
        {
            String s1 = new String(abyte0, s);
            byte byte0 = -1;
            int j;
            int k;
            for(k = 0; (j = s1.indexOf('\n', k)) > -1; k = j + 1)
                arraylist.add(s1.substring(k, j));

            arraylist.add(s1.substring(k));
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return arraylist.size();
    }
}
