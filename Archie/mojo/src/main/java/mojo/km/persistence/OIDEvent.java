package mojo.km.persistence;

/** @modelguid {54E9B7C8-42E8-48BE-B7E0-A31798EE7809} */
public class OIDEvent extends mojo.km.messaging.RequestEvent
{
    /** @modelguid {20733BA4-4995-4CA5-B2A7-921D93CE0056} */
    private String OID;

    /** @modelguid {9AE61AFD-49E3-4D75-8ED7-885508514CD8} */
    public OIDEvent()
    {
    }

    /** @modelguid {BDA402A1-3B5C-4BE1-AF3B-2E5BC7B3D55E} */
    public String getOID()
    {
        return OID;
    }

    /** @modelguid {CD7A8850-E64B-4679-BCE7-6382EF4AA2C4} */
    public void setOID(String value)
    {
        OID = value;
    }

}
