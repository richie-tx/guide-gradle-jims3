package mojo.util.spellcheck;

import java.util.Calendar;
import java.util.GregorianCalendar;

class K
{

    K()
    {
        licensedServerName = "";
        LSN = "";
        LIPS = new String[0];
        x = 7;
        c = 0;
    }

    String[] GetV(String s)
    {
        String s1 = rk(s);
        if(s1 == null)
        {
            return null;
        } else
        {
            String as[] = splitHashList_Code(s1, "|");
            return as;
        }
    }

    boolean cLic(String s)
    {
        if(s.equals("RSWEBJAVADEVMODE"))
            return true;
        boolean flag = false;
        Object obj = null;
        String as[] = GetV(s);
        if(as != null)
        {
            licensedServerName = as[0];
            LSN = licensedServerName;
            licensedIPs = splitHashList_Code(as[1], ",");
            LIPS = licensedIPs;
        }
        if(licensedServerName.equals("KEYOTITLTDTRIAL"))
            try
            {
                String as1[] = splitHashList_Code(licensedIPs[0], "-");
                GregorianCalendar gregoriancalendar = new GregorianCalendar();
                GregorianCalendar gregoriancalendar1 = new GregorianCalendar(Integer.parseInt(as1[3]), Integer.parseInt(as1[2]) - 1, Integer.parseInt(as1[1]));
                licensedServerName = "Evaluation (time limit)";
                LSN = licensedServerName;
                licensedIPs = (new String[] {
                    "expires " + gregoriancalendar1.get(5) + "/" + (gregoriancalendar1.get(2) + 1) + "/" + gregoriancalendar1.get(1) + "(dd/mm/yyyy)"
                });
                LIPS = licensedIPs;
                if(gregoriancalendar1.before(gregoriancalendar))
                    flag = false;
                else
                    flag = true;
            }
            catch(Exception _ex)
            {
                flag = false;
            }
        else
        if(licensedServerName != null && !licensedServerName.equals(""))
            flag = true;
        else
            flag = false;
        return flag;
    }

    byte invtransform(byte byte0)
    {
        c++;
        if(c > 20)
            c = 0;
        int i = byte0;
        i = (i -= 32) - (x + c);
        if(i < 0)
            i = 95 + i;
        return (byte)(i += 32);
    }

    String rk(String s)
    {
        try
        {
            c = 1;
            int i = Integer.parseInt(s.substring(s.length() - 1, s.length()));
            byte abyte0[] = new byte[(s.length() - 1) / 2];
            char ac[] = new char[abyte0.length];
            int j = 0;
            for(int k = 0; k < abyte0.length; k++)
            {
                abyte0[k] = (byte)Integer.parseInt(s.substring(k * 2, k * 2 + 2), 16);
                j += abyte0[k];
                abyte0[k] = invtransform(abyte0[k]);
            }

            if(j % 10 != i)
                return null;
            else
                return new String(abyte0);
        }
        catch(Exception _ex)
        {
            return null;
        }
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

    byte transform(byte byte0)
    {
        c++;
        if(c > 20)
            c = 0;
        return (byte)(((byte0 - 32) + x + c) % 95 + 32);
    }

    String licensedServerName;
    String licensedIPs[];
    public String LSN;
    public String LIPS[];
    int x;
    int c;
}
