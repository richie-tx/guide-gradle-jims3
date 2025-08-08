/*
 * Created on Mar 21, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.codetable;

import naming.PDCodeTableConstants;
import mojo.km.messaging.RequestEvent;

/**
 * @author Jim Fisher
 *  
 */
public class GetTattooCodesEvent extends RequestEvent
{
    private String[] codes;

    public String getCategory()
    {
        return PDCodeTableConstants.TATTOO_CATEGORY;
    }

    /**
     * @return Returns the codes.
     */
    public String[] getCodes()
    {
        return codes;
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
