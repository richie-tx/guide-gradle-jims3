package mojo.util.spellcheck;

import java.io.*;

class DictFile
{

    public DictFile(InputStream inputstream)
        throws IOException
    {
        dictFilePath = null;
        listStart = 0;
        dictFile = new BufferedInputStream(inputstream);
        init();
    }

    public DictFile(String s)
        throws FileNotFoundException, IOException
    {
        dictFilePath = null;
        listStart = 0;
        dictFile = new BufferedInputStream(new FileInputStream(s));
        dictFilePath = s;
        init();
    }

    public void close()
        throws IOException
    {
        dictFile.close();
    }

    public InputStream getDecodedFileStream()
        throws FileNotFoundException
    {
        return new DecodedStream(dictFile);
    }

    public int[] getStreamDimensions(int i)
    {
        int j = 0;
        int k = 0;
        if(i == WordList)
        {
            j = listStart;
            k = listLength;
        } else
        if(i == ReverseList)
        {
            j = listStart + listBytesLength;
            k = listLength;
        }
        int ai[] = {
            j, k
        };
        return ai;
    }

    void init()
        throws IOException
    {
        dictFile.mark(0x7ffffffe);
        byte abyte0[] = new byte[50];
        byte byte0 = 0;
        int i;
        while((i = dictFile.read()) != -1 && i != 58) 
            listStart++;
        listStart++;
        while((i = dictFile.read()) != -1 && i != 58) 
        {
            abyte0[byte0] = (byte)i;
            listStart++;
            byte0++;
        }
        listStart++;
        listLength = Integer.parseInt((new String(abyte0, "ASCII")).substring(0, byte0));
        int j;
        for(byte0 = 0; (j = dictFile.read()) != -1 && j != 58; byte0++)
        {
            abyte0[byte0] = (byte)j;
            listStart++;
        }

        listStart++;
        listBytesLength = Integer.parseInt((new String(abyte0, "ASCII")).substring(0, byte0));
    }

    public static int WordList = 1;
    public static int ReverseList = 2;
    BufferedInputStream dictFile;
    String dictFilePath;
    int listLength;
    int revLength;
    int listBytesLength;
    int listStart;

}
