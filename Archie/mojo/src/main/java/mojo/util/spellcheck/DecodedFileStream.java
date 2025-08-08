package mojo.util.spellcheck;

import java.io.*;

class DecodedFileStream extends FileInputStream
{

    public DecodedFileStream(String s)
        throws FileNotFoundException
    {
        super(s);
    }

    public int read()
        throws IOException
    {
        int i = super.read();
        if(i != -1)
            return (i - 50) % 256;
        else
            return -1;
    }

    public int read(byte abyte0[])
        throws IOException
    {
        return read(abyte0, 0, abyte0.length);
    }

    public int read(byte abyte0[], int i, int j)
        throws IOException
    {
        int k = super.read(abyte0, i, j);
        for(int l = i; l < j; l++)
            abyte0[l] = (byte)((abyte0[l] - 50) % 256);

        return k;
    }
}
