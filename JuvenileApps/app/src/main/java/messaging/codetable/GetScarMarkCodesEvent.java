/*
 * Created on Mar 21, 2006
 *
 */
package messaging.codetable;

import mojo.km.messaging.RequestEvent;

/**
 * @author Jim Fisher
 *  
 */
public class GetScarMarkCodesEvent extends RequestEvent
{
    private String category;

    private String[] codes;

    /**
     * @return Returns the category.
     */
    public String getCategory()
    {
        return category;
    }

    /**
     * @return Returns the codes.
     */
    public String[] getCodes()
    {
        return codes;
    }

    /**
     * @param category
     *            The category to set.
     */
    public void setCategory(String category)
    {
        this.category = category;
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
