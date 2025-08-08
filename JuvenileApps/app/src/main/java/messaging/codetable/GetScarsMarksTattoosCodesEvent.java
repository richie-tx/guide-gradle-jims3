/*
 * Created on Jul 15, 2005
 *
 */
package messaging.codetable;

import mojo.km.messaging.RequestEvent;

/**
 * @author jfisher
 *  
 */
public class GetScarsMarksTattoosCodesEvent extends RequestEvent
{
    private String category;

    private String code;

    private String[] codes;

    /**
     * @return
     */
    public String getCategory()
    {
        return category;
    }

    /**
     * @return
     */
    public String getCode()
    {
        return code;
    }

    /**
     * @return Returns the codes.
     */
    public String[] getCodes()
    {
        return codes;
    }

    /**
     * @return
     */
    public String getOIDDerived()
    {
        String categoryPadded = paddingString(this.category, 3, ' ');
        String codePadded = paddingString(this.code, 10, ' ');

        return categoryPadded + codePadded;
    }

    public String paddingString(String s, int n, char c)
    {
        if (s == null)
        {
            s = new String();
        }
        StringBuffer str = new StringBuffer(s);
        int strLength = str.length();
        if (n > 0 && n > strLength)
        {
            for (int i = 0; i <= n; i++)
            {
                if (i > strLength)
                {
                    str.append(c);
                }

            }
        }
        return str.toString();
    }

    /**
     * @param string
     */
    public void setCategory(String string)
    {
        category = string;
    }

    /**
     * @param string
     */
    public void setCode(String string)
    {
        code = string;
    }

    /**
     * @param codes
     *            The codes to set.
     */
    public void setCodes(String[] codes)
    {
        this.codes = codes;
    }
}
