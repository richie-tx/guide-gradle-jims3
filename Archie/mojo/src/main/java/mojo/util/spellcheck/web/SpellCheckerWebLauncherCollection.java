package mojo.util.spellcheck.web;

import java.util.Enumeration;
import java.util.Vector;

public class SpellCheckerWebLauncherCollection
{

    public SpellCheckerWebLauncherCollection()
    {
        list = new Vector();
    }

    public SpellCheckerWebLauncherCollection(String s)
    {
        list = new Vector();
        if(s != null)
        {
            String as[] = splitHashList_Code(s, ",");
            for(int i = 0; i < as.length; i++)
                list.add(as[i]);

        }
    }

    public void Add(String s)
    {
        list.add(s);
    }

    public int Count()
    {
        return list.size();
    }

    public Enumeration GetEnumerator()
    {
        return list.elements();
    }

    public void Remove(String s)
    {
        list.remove(s);
    }

    public Object get(int i)
    {
        return list.get(i);
    }

    private static final String[] splitHashList_Code(String s, String s1)
    {
        if(s.equals(""))
            return new String[0];
        int j = 0;
        int j1 = 0;
        do
        {
            int l = s.indexOf(s1, j);
            if(l == -1)
                break;
            j1++;
            j = l + s1.length();
        } while(true);
        j1++;
        String as[] = new String[j1];
        if(j1 == 1)
        {
            as[0] = s;
        } else
        {
            int i = 0;
            int k = 0;
            boolean flag = false;
            for(int k1 = 0; k1 < j1; k1++)
            {
                int i1 = s.indexOf(s1, k);
                if(i1 == -1)
                    as[k1] = s.substring(i + s1.length(), s.length());
                else
                if(i1 == 0)
                    as[k1] = "";
                else
                    as[k1] = s.substring(k, i1);
                i = i1;
                k = i1 + s1.length();
            }

        }
        return as;
    }

    public String toString()
    {
        String s = "";
        for(int i = 0; i < Count(); i++)
        {
            s = s + get(i);
            if(i < Count() - 1)
                s = s + ",";
        }

        return s;
    }

    Vector list;
}
