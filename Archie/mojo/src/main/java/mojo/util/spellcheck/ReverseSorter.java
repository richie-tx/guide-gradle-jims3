package mojo.util.spellcheck;

import java.util.ArrayList;
import java.util.Comparator;

class ReverseSorter
    implements Comparator
{

    ReverseSorter()
    {
    }

    public int compare(Object obj, Object obj1)
    {
        return reverse((String)obj).compareTo(reverse((String)obj1));
    }

    public static String reverse(String s)
    {
        int i = s.length();
        char ac[] = new char[i];
        char ac1[] = new char[i];
        s.getChars(0, i, ac1, 0);
        for(int j = i; j > 0; j--)
            ac[i - j] = ac1[j - 1];

        return new String(ac);
    }

    public static ArrayList reverseList(ArrayList arraylist)
    {
        ArrayList arraylist1 = new ArrayList(arraylist.size());
        for(int i = 0; i < arraylist.size(); i++)
            arraylist1.add(reverse((String)arraylist.get(i)));

        return arraylist1;
    }
}
