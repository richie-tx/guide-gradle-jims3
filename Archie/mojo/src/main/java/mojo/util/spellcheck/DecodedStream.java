package mojo.util.spellcheck;

import java.io.IOException;
import java.io.InputStream;

class DecodedStream extends InputStream
{

    public DecodedStream(InputStream inputstream)
    {
        s = inputstream;
    }

    public int available()
        throws IOException
    {
        return s.available();
    }

    public void close()
        throws IOException
    {
        s.close();
    }

    public void mark(int i)
    {
        s.mark(i);
    }

    public boolean markSupported()
    {
        return s.markSupported();
    }

    public int read()
        throws IOException
    {
        int i = s.read();
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
        int k = s.read(abyte0, i, j);
        for(int l = i; l < j; l++)
            abyte0[l] = (byte)((abyte0[l] - 50) % 256);

        return k;
    }

    public void reset()
        throws IOException
    {
        s.reset();
    }

    public long skip(long l)
        throws IOException
    {
        return s.skip(l);
    }

    InputStream s;
}
