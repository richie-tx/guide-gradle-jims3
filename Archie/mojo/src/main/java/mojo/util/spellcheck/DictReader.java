package mojo.util.spellcheck;

import java.io.*;
import java.util.ArrayList;

class DictReader
{

    DictReader()
    {
        autoCloseReader = true;
    }

    public int readWordListStream(ArrayList arraylist, int i, DictFile dictfile, String s)
        throws IOException
    {
        InputStream inputstream = dictfile.getDecodedFileStream();
        int ai[] = dictfile.getStreamDimensions(i);
        inputstream.reset();
        inputstream.skip(ai[0]);
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream, s));
        String s1;
        int j;
        for(j = 0; j < ai[1] && (s1 = bufferedreader.readLine()) != null; j++)
            arraylist.add(s1);

        return j;
    }

    public boolean autoCloseReader;
}
