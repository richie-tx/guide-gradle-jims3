// Source file: C:\\views\\dev\\app\\src\\messaging\\codetable\\GetSupervisionCodesEvent.java

package messaging.codetable;

import mojo.km.messaging.RequestEvent;

public class GetSupervisionCodesEvent extends RequestEvent
{
    private String agencyId;

    private String code;

    private String codeTableName;

    /**
     * @roseuid 43039DED0271
     */
    public GetSupervisionCodesEvent()
    {

    }

    /**
     * @return
     */
    public String getAgencyId()
    {
        return agencyId;
    }

    /**
     * @return
     */
    public String getCodeTableName()
    {
        return codeTableName;
    }

    /**
     * @param string
     */
    public void setAgencyId(String string)
    {
        agencyId = string;
    }

    /**
     * @param string
     */
    public void setCodeTableName(String string)
    {
        codeTableName = string;
    }

    /**
     * @return Returns the code.
     */
    public String getCode()
    {
        return code;
    }

    /**
     * @param code
     *        The code to set.
     */
    public void setCode(String code)
    {
        this.code = code;
    }
}
