package mojo.km.persistence;

/**
 * Responsible for requests related to member attributs of a persisten type.
 * 
 * @modelguid {DFEEB2FF-9D23-4EC8-BF7F-CF134FBE680D}
 */
public class AttributeEvent extends mojo.km.messaging.RequestEvent
{
    /**
     * 
     * @modelguid {5958E5F3-65F9-435C-85BE-551EC5EF4013}
     */
    private String attributeName;

    /** @modelguid {1DF7B4E1-1CD5-4177-9F55-A82E727CC3A0} */
    private Object attributeValue;

    /** @modelguid {669E7547-9E5A-4886-9E0E-61BD6DEDA110} */
    public AttributeEvent()
    {
    }

    /** @modelguid {3D567258-4164-4189-A062-920918EA9530} */
    public String getAttributeName()
    {
        /* Begin Template Expansion{E7C2A923-82A4-486B-B18B-17F0C82C56E8} */

        return attributeName;
        /* End Template Expansion{E7C2A923-82A4-486B-B18B-17F0C82C56E8} */
    }

    /** @modelguid {8DFC1924-ADD8-46D3-8AE2-DD36D25B55D0} */
    public void setAttributeName(String aAttributeName)
    {
        /* Begin Template Expansion{29A86966-D8DE-43F4-8076-EC7F8A2869AE} */
        attributeName = aAttributeName;
        /* End Template Expansion{29A86966-D8DE-43F4-8076-EC7F8A2869AE} */
    }

    /** @modelguid {B8705C68-166C-4379-B778-3E6FBFE7A126} */
    public Object getAttributeValue()
    {
        /* Begin Template Expansion{E7C2A923-82A4-486B-B18B-17F0C82C56E8} */

        return attributeValue;
        /* End Template Expansion{E7C2A923-82A4-486B-B18B-17F0C82C56E8} */
    }

    /** @modelguid {253EA776-C6F5-4A6E-9202-1EFD3308F5C3} */
    public void setAttributeValue(Object aAttributeValue)
    {
        /* Begin Template Expansion{29A86966-D8DE-43F4-8076-EC7F8A2869AE} */
        attributeValue = aAttributeValue;
        /* End Template Expansion{29A86966-D8DE-43F4-8076-EC7F8A2869AE} */
    }

}
