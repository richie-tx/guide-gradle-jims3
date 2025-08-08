/*
 * Created on Nov 10, 2004
 *
 */
package messaging.codetable.person.reply;

import messaging.codetable.reply.ICode;
import mojo.km.messaging.ResponseEvent;

/**
 * @author ryoung
 *  
 */
public class ScarsMarksTattoosCodeResponseEvent extends ResponseEvent implements Comparable, ICode
{
    private String category;

    private String code;

    private String description;

    public int compareTo(Object obj) throws ClassCastException
    {
        ScarsMarksTattoosCodeResponseEvent evt = (ScarsMarksTattoosCodeResponseEvent) obj;
        return description.compareTo(evt.getDescription());
    }

    /**
     * @return Returns the category.
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
     * @return
     */
    public String getDescription()
    {
        return description;
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
     * @param string
     */
    public void setCode(String code)
    {
        this.code = code;
    }

    /**
     * @param string
     */
    public void setDescription(String description)
    {
        this.description = description;
    }
}
