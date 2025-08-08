package messaging.codetable;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileDispositionCodeByValueEvent extends RequestEvent{
	
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String attributeValue;
    private String attributeName;

    public String getAttributeValue()
    {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue)
    {
        this.attributeValue = attributeValue;
    }

    public String getAttributeName()
    {
        return attributeName;
    }

    public void setAttributeName(String attributeName)
    {
        this.attributeName = attributeName;
    }    
   
}
