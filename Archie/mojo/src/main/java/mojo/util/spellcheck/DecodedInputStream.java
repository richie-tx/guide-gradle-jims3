package mojo.util.spellcheck;

import java.io.IOException;
import java.io.InputStream;

class DecodedInputStream extends InputStream
{

    public DecodedInputStream(InputStream inputstream)
    {
        is = inputstream;
    }

    public int available()
        throws IOException
    {
        return is.available();
    }

    public void close()
        throws IOException
    {
        is.close();
    }

    byte deencodeByte(int i)
    {
        return (byte)((i - 50) % 256);
    }

    public void mark(int i)
    {
        is.mark(i);
    }

    public boolean markSupported()
    {
        return markSupported();
    }

    public int read()
        throws IOException
    {
        int i = is.read();
        if(i != -1)
            return deencodeByte(i);
        else
            return -1;
    }

    public int read(byte abyte0[])
        throws IOException
    {
        int i = is.read(abyte0);
        for(int j = 0; j < i; j++)
            abyte0[j] = deencodeByte(abyte0[j]);

        return i;
    }

    public int read(byte abyte0[], int i, int j)
        throws IOException
    {
        int k = is.read(abyte0, i, j);
        for(int l = i; l < k; l++)
            abyte0[l] = deencodeByte(abyte0[l]);

        return k;
    }

    public void reset()
        throws IOException
    {
        is.reset();
    }

    public long skip(long l)
        throws IOException
    {
        return is.skip(l);
    }

    InputStream is;
}
