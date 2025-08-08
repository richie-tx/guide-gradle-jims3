/*
 * Created on Jan 20, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.codetable;

import mojo.km.messaging.RequestEvent;

/**
 * @author glyons
 *  
 */
public class GetCodesEvent extends RequestEvent
{
    private boolean thin;
    
    private String[] codes;

    private String codeTableName;

    public void setCodeTableName(String codeTableName)
    {
        this.codeTableName = codeTableName;
    }

    public String getCodeTableName()
    {
        return this.codeTableName;
    }
    /**
     * @return Returns the codes.
     */
    public String[] getCodes()
    {
        return codes;
    }
    /**
     * @param codes The codes to set.
     */
    public void setCodes(String[] codes)
    {
        this.codes = codes;
    }
    /**
     * @return Returns the thin.
     */
    public boolean isThin()
    {
        return thin;
    }
    /**
     * @param thin The thin to set.
     */
    public void setThin(boolean thin)
    {
        this.thin = thin;
    }
}
