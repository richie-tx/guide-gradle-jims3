package mojo.km.config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** @modelguid {2051E413-46AE-4009-B5CF-B0D5413A15DF} */
public class DispatchProperties extends GenericProperties
{
    /** @modelguid {411683DF-BC64-43D0-8E0A-D57FD9B37C81} */
    public static final String REQUEST = "Request";

    /** @modelguid {BB7EDB35-EE50-4ACB-8B34-C58BC72EA05D} */
    public static final String REPLY = "Reply";

    /** @modelguid {FD00E6B2-7E56-4238-BD4A-2B6DFD39C546} */
    public static final String ASYNC = "Async";

    /** @modelguid {4F192CD8-D82B-4D25-9B5F-C91ACCC74308} */
    public static final String PUBSUB = "PubSub";

    /** @modelguid {1F247E68-3674-45DC-B0FC-6BB998E72FE4} */
    public static final String QUEUE = "Queue";

    /** @modelguid {A02E4B72-9A63-4A59-87D6-09B5E7A66F0F} */
    public static final String CONTEXT = "Context";

    /** @modelguid {B3B6A48E-3942-461E-98F9-4EA803ED2ECA} */
    public static DispatchProperties getInstance()
    {
        ServerProperties lServer = ServerProperties.getInstance();
        if (lServer != null)
        {
            return lServer.getDispatchProperties();
        }
        else
        {
            return null;
        }
    }

    /** @modelguid {9327A4EE-A8CC-40D0-9853-E84D6F5915D3} */
    public static DispatchProperties getInstance(String aServerName)
    {
        ServerProperties lServer = ServerProperties.getInstance(aServerName);
        if (lServer != null)
        {
            return lServer.getDispatchProperties();
        }
        else
        {
            return null;
        }
    }

    /** @modelguid {9BF8D2C1-5C7D-40AA-9F97-9BD3044F7C57} */
    public List getDispatchNames()
    {
        List lDispatches = new ArrayList();
        for (Iterator i = getProperties(); i.hasNext();)
        {
            String lProp = (String) i.next();
            String lValue = getProperty(lProp);
            lDispatches.add(getProperty(lProp));
        }
        return lDispatches;
    }
}