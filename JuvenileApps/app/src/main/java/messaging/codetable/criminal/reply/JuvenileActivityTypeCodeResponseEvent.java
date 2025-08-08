package messaging.codetable.criminal.reply;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import messaging.codetable.reply.ICode;
import mojo.km.messaging.ResponseEvent;

/**
 * @author C_NAggarwal
 */
public class JuvenileActivityTypeCodeResponseEvent extends ResponseEvent implements Comparable, ICode
{
    private String code;

    private String description;

    public List subTypes = new ArrayList();

    private List returnValues = new ArrayList();

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
     * @return Returns the description.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description
     *            The description to set.
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @return Returns the returnValues.
     */
    public List getReturnValues()
    {        
        return returnValues;
    }

    /**
     * @param returnValues
     *            The returnValues to set.
     */
    public void setReturnValues(List returnValues)
    {
        Collections.sort(returnValues);
        this.returnValues = returnValues;
    }

    /**
     * @return Returns the subTypes.
     */
    public List getSubTypes()
    {
        Collections.sort(subTypes);
        return subTypes;
    }    

    public int compareTo(Object obj) throws ClassCastException
    {
        JuvenileActivityTypeCodeResponseEvent evt = (JuvenileActivityTypeCodeResponseEvent) obj;
        return description.compareTo(evt.getDescription());
    }
}
