package messaging.codetable;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileHearingTypesEvent extends RequestEvent
{
    private String code;
    private String category;
    private String docketType;
    /**
     * @return Returns the code.
     */
    public String getCode()
    {
	return code;
    }

    /**
     * @param code
     *            The code to set.
     */
    public void setCode(String code)
    {
	this.code = code;
    }

    /**
     * @return the category
     */
    public String getCategory()
    {
	return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category)
    {
	this.category = category;
    }

    /**
     * @return the docketType
     */
    public String getDocketType()
    {
	return docketType;
    }

    /**
     * @param docketType the docketType to set
     */
    public void setDocketType(String docketType)
    {
	this.docketType = docketType;
    }
}
