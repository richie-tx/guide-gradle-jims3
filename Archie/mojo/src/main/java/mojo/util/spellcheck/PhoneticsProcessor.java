package mojo.util.spellcheck;


class PhoneticsProcessor
{

    PhoneticsProcessor()
    {
    }

    public static boolean hasSameMetaPhone(String s, String s1)
    {
        int i = 0;
        boolean flag = false;
        if(s == null || s.length() == 0)
            return false;
        if(s.length() == 1 && s.toUpperCase().equals(s1))
            return true;
        if(s.length() == 1)
            return false;
        char ac[] = s.toUpperCase().toCharArray();
        StringBuffer stringbuffer = new StringBuffer(40);
        StringBuffer stringbuffer1 = new StringBuffer(10);
        switch(ac[0])
        {
        case 71: // 'G'
        case 75: // 'K'
        case 80: // 'P'
            if(ac[1] == 'N')
                stringbuffer.append(ac, 1, ac.length - 1);
            else
                stringbuffer.append(ac);
            break;

        case 65: // 'A'
            if(ac[1] == 'E')
                stringbuffer.append(ac, 1, ac.length - 1);
            else
                stringbuffer.append(ac);
            break;

        case 87: // 'W'
            if(ac[1] == 'R')
            {
                stringbuffer.append(ac, 1, ac.length - 1);
                break;
            }
            if(ac[1] == 'H')
            {
                stringbuffer.append(ac, 1, ac.length - 1);
                stringbuffer.setCharAt(0, 'W');
            } else
            {
                stringbuffer.append(ac);
            }
            break;

        case 88: // 'X'
            ac[0] = 'S';
            stringbuffer.append(ac);
            break;

        default:
            stringbuffer.append(ac);
            break;
        }
        int j = stringbuffer.length();
        for(int k = 0; i < 6 && k < j;)
        {
            if(stringbuffer1.length() > s1.length())
                return false;
            if(stringbuffer1.length() > 0 && stringbuffer1.charAt(stringbuffer1.length() - 1) != s1.charAt(stringbuffer1.length() - 1))
                return false;
            char c = stringbuffer.charAt(k);
            if(c != 'C' && k > 0 && stringbuffer.charAt(k - 1) == c)
            {
                k++;
            } else
            {
                switch(c)
                {
                default:
                    break;

                case 65: // 'A'
                case 69: // 'E'
                case 73: // 'I'
                case 79: // 'O'
                case 85: // 'U'
                    if(k == 0)
                    {
                        stringbuffer1.append(c);
                        i++;
                    }
                    break;

                case 66: // 'B'
                    if(k > 0 && k + 1 != j && stringbuffer.charAt(k - 1) == 'M')
                        stringbuffer1.append(c);
                    else
                        stringbuffer1.append(c);
                    i++;
                    break;

                case 67: // 'C'
                    if(k > 0 && stringbuffer.charAt(k - 1) == 'S' && k + 1 < j && frontv.indexOf(stringbuffer.charAt(k + 1)) >= 0)
                        break;
                    String s2 = stringbuffer.toString();
                    if(s2.indexOf("CIA", k) == k)
                    {
                        stringbuffer1.append('X');
                        i++;
                        break;
                    }
                    if(k + 1 < j && frontv.indexOf(stringbuffer.charAt(k + 1)) >= 0)
                    {
                        stringbuffer1.append('S');
                        i++;
                        break;
                    }
                    if(k > 0 && s2.indexOf("SCH", k - 1) == k - 1)
                    {
                        stringbuffer1.append('K');
                        i++;
                        break;
                    }
                    if(s2.indexOf("CH", k) == k)
                    {
                        if(k == 0 && j >= 3 && vowels.indexOf(stringbuffer.charAt(2)) < 0)
                            stringbuffer1.append('K');
                        else
                            stringbuffer1.append('X');
                        i++;
                    } else
                    {
                        stringbuffer1.append('K');
                        i++;
                    }
                    break;

                case 68: // 'D'
                    if(k + 2 < j && stringbuffer.charAt(k + 1) == 'G' && frontv.indexOf(stringbuffer.charAt(k + 2)) >= 0)
                    {
                        stringbuffer1.append('J');
                        k += 2;
                    } else
                    {
                        stringbuffer1.append('T');
                    }
                    i++;
                    break;

                case 71: // 'G'
                    if(k + 2 == j && stringbuffer.charAt(k + 1) == 'H' || k + 2 < j && stringbuffer.charAt(k + 1) == 'H' && vowels.indexOf(stringbuffer.charAt(k + 2)) < 0)
                        break;
                    String s3 = stringbuffer.toString();
                    if(k > 0 && s3.indexOf("GN", k) == k || s3.indexOf("GNED", k) == k)
                        break;
                    boolean flag1;
                    if(k > 0 && stringbuffer.charAt(k - 1) == 'G')
                        flag1 = true;
                    else
                        flag1 = false;
                    if(k + 1 < j && frontv.indexOf(stringbuffer.charAt(k + 1)) >= 0 && !flag1)
                        stringbuffer1.append('J');
                    else
                        stringbuffer1.append('K');
                    i++;
                    break;

                case 72: // 'H'
                    if(k + 1 != j && (k <= 0 || varson.indexOf(stringbuffer.charAt(k - 1)) < 0) && vowels.indexOf(stringbuffer.charAt(k + 1)) >= 0)
                    {
                        stringbuffer1.append('H');
                        i++;
                    }
                    break;

                case 70: // 'F'
                case 74: // 'J'
                case 76: // 'L'
                case 77: // 'M'
                case 78: // 'N'
                case 82: // 'R'
                    stringbuffer1.append(c);
                    i++;
                    break;

                case 75: // 'K'
                    if(k > 0)
                    {
                        if(stringbuffer.charAt(k - 1) != 'C')
                            stringbuffer1.append(c);
                    } else
                    {
                        stringbuffer1.append(c);
                    }
                    i++;
                    break;

                case 80: // 'P'
                    if(k + 1 < j && stringbuffer.charAt(k + 1) == 'H')
                        stringbuffer1.append('F');
                    else
                        stringbuffer1.append(c);
                    i++;
                    break;

                case 81: // 'Q'
                    stringbuffer1.append('K');
                    i++;
                    break;

                case 83: // 'S'
                    String s4 = stringbuffer.toString();
                    if(s4.indexOf("SH", k) == k || s4.indexOf("SIO", k) == k || s4.indexOf("SIA", k) == k)
                        stringbuffer1.append('X');
                    else
                        stringbuffer1.append('S');
                    i++;
                    break;

                case 84: // 'T'
                    String s5 = stringbuffer.toString();
                    if(s5.indexOf("TIA", k) == k || s5.indexOf("TIO", k) == k)
                    {
                        stringbuffer1.append('X');
                        i++;
                        break;
                    }
                    if(s5.indexOf("TCH", k) == k)
                        break;
                    if(s5.indexOf("TH", k) == k)
                        stringbuffer1.append('0');
                    else
                        stringbuffer1.append('T');
                    i++;
                    break;

                case 86: // 'V'
                    stringbuffer1.append('F');
                    i++;
                    break;

                case 87: // 'W'
                case 89: // 'Y'
                    if(k + 1 < j && vowels.indexOf(stringbuffer.charAt(k + 1)) >= 0)
                    {
                        stringbuffer1.append(c);
                        i++;
                    }
                    break;

                case 88: // 'X'
                    stringbuffer1.append('K');
                    stringbuffer1.append('S');
                    i += 2;
                    break;

                case 90: // 'Z'
                    stringbuffer1.append('S');
                    i++;
                    break;
                }
                k++;
            }
            if(i > 6)
                stringbuffer1.setLength(6);
        }

        return stringbuffer1.toString().equals(s1);
    }

    private static boolean hasSimilarMetaPhone(String s, String s1)
    {
        int i = 0;
        boolean flag = false;
        if(s == null || s.length() == 0)
            return false;
        if(s.length() == 1 && s.toUpperCase().equals(s1))
            return true;
        if(s.length() == 1)
            return false;
        char ac[] = s.toUpperCase().toCharArray();
        StringBuffer stringbuffer = new StringBuffer(40);
        StringBuffer stringbuffer1 = new StringBuffer(10);
        switch(ac[0])
        {
        case 71: // 'G'
        case 75: // 'K'
        case 80: // 'P'
            if(ac[1] == 'N')
                stringbuffer.append(ac, 1, ac.length - 1);
            else
                stringbuffer.append(ac);
            break;

        case 65: // 'A'
            if(ac[1] == 'E')
                stringbuffer.append(ac, 1, ac.length - 1);
            else
                stringbuffer.append(ac);
            break;

        case 87: // 'W'
            if(ac[1] == 'R')
            {
                stringbuffer.append(ac, 1, ac.length - 1);
                break;
            }
            if(ac[1] == 'H')
            {
                stringbuffer.append(ac, 1, ac.length - 1);
                stringbuffer.setCharAt(0, 'W');
            } else
            {
                stringbuffer.append(ac);
            }
            break;

        case 88: // 'X'
            ac[0] = 'S';
            stringbuffer.append(ac);
            break;

        default:
            stringbuffer.append(ac);
            break;
        }
        int j = stringbuffer.length();
        for(int k = 0; i < 6 && k < j;)
        {
            char c = stringbuffer.charAt(k);
            if(c != 'C' && k > 0 && stringbuffer.charAt(k - 1) == c)
            {
                k++;
            } else
            {
                switch(c)
                {
                default:
                    break;

                case 65: // 'A'
                case 69: // 'E'
                case 73: // 'I'
                case 79: // 'O'
                case 85: // 'U'
                    if(k == 0)
                    {
                        stringbuffer1.append(c);
                        i++;
                    }
                    break;

                case 66: // 'B'
                    if(k > 0 && k + 1 != j && stringbuffer.charAt(k - 1) == 'M')
                        stringbuffer1.append(c);
                    else
                        stringbuffer1.append(c);
                    i++;
                    break;

                case 67: // 'C'
                    if(k > 0 && stringbuffer.charAt(k - 1) == 'S' && k + 1 < j && frontv.indexOf(stringbuffer.charAt(k + 1)) >= 0)
                        break;
                    String s2 = stringbuffer.toString();
                    if(s2.indexOf("CIA", k) == k)
                    {
                        stringbuffer1.append('X');
                        i++;
                        break;
                    }
                    if(k + 1 < j && frontv.indexOf(stringbuffer.charAt(k + 1)) >= 0)
                    {
                        stringbuffer1.append('S');
                        i++;
                        break;
                    }
                    if(k > 0 && s2.indexOf("SCH", k - 1) == k - 1)
                    {
                        stringbuffer1.append('K');
                        i++;
                        break;
                    }
                    if(s2.indexOf("CH", k) == k)
                    {
                        if(k == 0 && j >= 3 && vowels.indexOf(stringbuffer.charAt(2)) < 0)
                            stringbuffer1.append('K');
                        else
                            stringbuffer1.append('X');
                        i++;
                    } else
                    {
                        stringbuffer1.append('K');
                        i++;
                    }
                    break;

                case 68: // 'D'
                    if(k + 2 < j && stringbuffer.charAt(k + 1) == 'G' && frontv.indexOf(stringbuffer.charAt(k + 2)) >= 0)
                    {
                        stringbuffer1.append('J');
                        k += 2;
                    } else
                    {
                        stringbuffer1.append('T');
                    }
                    i++;
                    break;

                case 71: // 'G'
                    if(k + 2 == j && stringbuffer.charAt(k + 1) == 'H' || k + 2 < j && stringbuffer.charAt(k + 1) == 'H' && vowels.indexOf(stringbuffer.charAt(k + 2)) < 0)
                        break;
                    String s3 = stringbuffer.toString();
                    if(k > 0 && s3.indexOf("GN", k) == k || s3.indexOf("GNED", k) == k)
                        break;
                    boolean flag1;
                    if(k > 0 && stringbuffer.charAt(k - 1) == 'G')
                        flag1 = true;
                    else
                        flag1 = false;
                    if(k + 1 < j && frontv.indexOf(stringbuffer.charAt(k + 1)) >= 0 && !flag1)
                        stringbuffer1.append('J');
                    else
                        stringbuffer1.append('K');
                    i++;
                    break;

                case 72: // 'H'
                    if(k + 1 != j && (k <= 0 || varson.indexOf(stringbuffer.charAt(k - 1)) < 0) && vowels.indexOf(stringbuffer.charAt(k + 1)) >= 0)
                    {
                        stringbuffer1.append('H');
                        i++;
                    }
                    break;

                case 70: // 'F'
                case 74: // 'J'
                case 76: // 'L'
                case 77: // 'M'
                case 78: // 'N'
                case 82: // 'R'
                    stringbuffer1.append(c);
                    i++;
                    break;

                case 75: // 'K'
                    if(k > 0)
                    {
                        if(stringbuffer.charAt(k - 1) != 'C')
                            stringbuffer1.append(c);
                    } else
                    {
                        stringbuffer1.append(c);
                    }
                    i++;
                    break;

                case 80: // 'P'
                    if(k + 1 < j && stringbuffer.charAt(k + 1) == 'H')
                        stringbuffer1.append('F');
                    else
                        stringbuffer1.append(c);
                    i++;
                    break;

                case 81: // 'Q'
                    stringbuffer1.append('K');
                    i++;
                    break;

                case 83: // 'S'
                    String s4 = stringbuffer.toString();
                    if(s4.indexOf("SH", k) == k || s4.indexOf("SIO", k) == k || s4.indexOf("SIA", k) == k)
                        stringbuffer1.append('X');
                    else
                        stringbuffer1.append('S');
                    i++;
                    break;

                case 84: // 'T'
                    String s5 = stringbuffer.toString();
                    if(s5.indexOf("TIA", k) == k || s5.indexOf("TIO", k) == k)
                    {
                        stringbuffer1.append('X');
                        i++;
                        break;
                    }
                    if(s5.indexOf("TCH", k) == k)
                        break;
                    if(s5.indexOf("TH", k) == k)
                        stringbuffer1.append('0');
                    else
                        stringbuffer1.append('T');
                    i++;
                    break;

                case 86: // 'V'
                    stringbuffer1.append('F');
                    i++;
                    break;

                case 87: // 'W'
                case 89: // 'Y'
                    if(k + 1 < j && vowels.indexOf(stringbuffer.charAt(k + 1)) >= 0)
                    {
                        stringbuffer1.append(c);
                        i++;
                    }
                    break;

                case 88: // 'X'
                    stringbuffer1.append('K');
                    stringbuffer1.append('S');
                    i += 2;
                    break;

                case 90: // 'Z'
                    stringbuffer1.append('S');
                    i++;
                    break;
                }
                k++;
            }
            if(i > 6)
                stringbuffer1.setLength(6);
        }

        return isSimilar(stringbuffer1.toString(), s1);
    }

    private static boolean isSimilar(String s, String s1)
    {
        if(Math.abs(s.length() - s1.length()) > 1)
            return false;
        if(s.length() < s1.length())
        {
            for(int i = 0; i <= s1.length() - s.length(); i++)
                if(s1.regionMatches(i, s, 0, s.length()))
                    return true;

        } else
        {
            for(int j = 0; j <= s.length() - s1.length(); j++)
                if(s.regionMatches(j, s1, 0, s1.length()))
                    return true;

        }
        return false;
    }

    public static String metaPhone(String s)
    {
        int i = 0;
        boolean flag = false;
        if(s == null || s.length() == 0)
            return "";
        if(s.length() == 1)
            return s.toUpperCase();
        char ac[] = s.toUpperCase().toCharArray();
        StringBuffer stringbuffer = new StringBuffer(40);
        StringBuffer stringbuffer1 = new StringBuffer(10);
        switch(ac[0])
        {
        case 71: // 'G'
        case 75: // 'K'
        case 80: // 'P'
            if(ac[1] == 'N')
                stringbuffer.append(ac, 1, ac.length - 1);
            else
                stringbuffer.append(ac);
            break;

        case 65: // 'A'
            if(ac[1] == 'E')
                stringbuffer.append(ac, 1, ac.length - 1);
            else
                stringbuffer.append(ac);
            break;

        case 87: // 'W'
            if(ac[1] == 'R')
            {
                stringbuffer.append(ac, 1, ac.length - 1);
                break;
            }
            if(ac[1] == 'H')
            {
                stringbuffer.append(ac, 1, ac.length - 1);
                stringbuffer.setCharAt(0, 'W');
            } else
            {
                stringbuffer.append(ac);
            }
            break;

        case 88: // 'X'
            ac[0] = 'S';
            stringbuffer.append(ac);
            break;

        default:
            stringbuffer.append(ac);
            break;
        }
        int j = stringbuffer.length();
        for(int k = 0; i < 6 && k < j;)
        {
            char c = stringbuffer.charAt(k);
            if(c != 'C' && k > 0 && stringbuffer.charAt(k - 1) == c)
            {
                k++;
            } else
            {
                switch(c)
                {
                default:
                    break;

                case 65: // 'A'
                case 69: // 'E'
                case 73: // 'I'
                case 79: // 'O'
                case 85: // 'U'
                    if(k == 0)
                    {
                        stringbuffer1.append(c);
                        i++;
                    }
                    break;

                case 66: // 'B'
                    if(k > 0 && k + 1 != j && stringbuffer.charAt(k - 1) == 'M')
                        stringbuffer1.append(c);
                    else
                        stringbuffer1.append(c);
                    i++;
                    break;

                case 67: // 'C'
                    if(k > 0 && stringbuffer.charAt(k - 1) == 'S' && k + 1 < j && frontv.indexOf(stringbuffer.charAt(k + 1)) >= 0)
                        break;
                    String s1 = stringbuffer.toString();
                    if(s1.indexOf("CIA", k) == k)
                    {
                        stringbuffer1.append('X');
                        i++;
                        break;
                    }
                    if(k + 1 < j && frontv.indexOf(stringbuffer.charAt(k + 1)) >= 0)
                    {
                        stringbuffer1.append('S');
                        i++;
                        break;
                    }
                    if(k > 0 && s1.indexOf("SCH", k - 1) == k - 1)
                    {
                        stringbuffer1.append('K');
                        i++;
                        break;
                    }
                    if(s1.indexOf("CH", k) == k)
                    {
                        if(k == 0 && j >= 3 && vowels.indexOf(stringbuffer.charAt(2)) < 0)
                            stringbuffer1.append('K');
                        else
                            stringbuffer1.append('X');
                        i++;
                    } else
                    {
                        stringbuffer1.append('K');
                        i++;
                    }
                    break;

                case 68: // 'D'
                    if(k + 2 < j && stringbuffer.charAt(k + 1) == 'G' && frontv.indexOf(stringbuffer.charAt(k + 2)) >= 0)
                    {
                        stringbuffer1.append('J');
                        k += 2;
                    } else
                    {
                        stringbuffer1.append('T');
                    }
                    i++;
                    break;

                case 71: // 'G'
                    if(k + 2 == j && stringbuffer.charAt(k + 1) == 'H' || k + 2 < j && stringbuffer.charAt(k + 1) == 'H' && vowels.indexOf(stringbuffer.charAt(k + 2)) < 0)
                        break;
                    String s2 = stringbuffer.toString();
                    if(k > 0 && s2.indexOf("GN", k) == k || s2.indexOf("GNED", k) == k)
                        break;
                    boolean flag1;
                    if(k > 0 && stringbuffer.charAt(k - 1) == 'G')
                        flag1 = true;
                    else
                        flag1 = false;
                    if(k + 1 < j && frontv.indexOf(stringbuffer.charAt(k + 1)) >= 0 && !flag1)
                        stringbuffer1.append('J');
                    else
                        stringbuffer1.append('K');
                    i++;
                    break;

                case 72: // 'H'
                    if(k + 1 != j && (k <= 0 || varson.indexOf(stringbuffer.charAt(k - 1)) < 0) && vowels.indexOf(stringbuffer.charAt(k + 1)) >= 0)
                    {
                        stringbuffer1.append('H');
                        i++;
                    }
                    break;

                case 70: // 'F'
                case 74: // 'J'
                case 76: // 'L'
                case 77: // 'M'
                case 78: // 'N'
                case 82: // 'R'
                    stringbuffer1.append(c);
                    i++;
                    break;

                case 75: // 'K'
                    if(k > 0)
                    {
                        if(stringbuffer.charAt(k - 1) != 'C')
                            stringbuffer1.append(c);
                    } else
                    {
                        stringbuffer1.append(c);
                    }
                    i++;
                    break;

                case 80: // 'P'
                    if(k + 1 < j && stringbuffer.charAt(k + 1) == 'H')
                        stringbuffer1.append('F');
                    else
                        stringbuffer1.append(c);
                    i++;
                    break;

                case 81: // 'Q'
                    stringbuffer1.append('K');
                    i++;
                    break;

                case 83: // 'S'
                    String s3 = stringbuffer.toString();
                    if(s3.indexOf("SH", k) == k || s3.indexOf("SIO", k) == k || s3.indexOf("SIA", k) == k)
                        stringbuffer1.append('X');
                    else
                        stringbuffer1.append('S');
                    i++;
                    break;

                case 84: // 'T'
                    String s4 = stringbuffer.toString();
                    if(s4.indexOf("TIA", k) == k || s4.indexOf("TIO", k) == k)
                    {
                        stringbuffer1.append('X');
                        i++;
                        break;
                    }
                    if(s4.indexOf("TCH", k) == k)
                        break;
                    if(s4.indexOf("TH", k) == k)
                        stringbuffer1.append('0');
                    else
                        stringbuffer1.append('T');
                    i++;
                    break;

                case 86: // 'V'
                    stringbuffer1.append('F');
                    i++;
                    break;

                case 87: // 'W'
                case 89: // 'Y'
                    if(k + 1 < j && vowels.indexOf(stringbuffer.charAt(k + 1)) >= 0)
                    {
                        stringbuffer1.append(c);
                        i++;
                    }
                    break;

                case 88: // 'X'
                    stringbuffer1.append('K');
                    stringbuffer1.append('S');
                    i += 2;
                    break;

                case 90: // 'Z'
                    stringbuffer1.append('S');
                    i++;
                    break;
                }
                k++;
            }
            if(i > 6)
                stringbuffer1.setLength(6);
        }

        return stringbuffer1.toString();
    }

    static final int maxCodeLen = 6;
    static String vowels = "AEIOU";
    static String frontv = "EIY";
    static String varson = "CSPTG";

}
