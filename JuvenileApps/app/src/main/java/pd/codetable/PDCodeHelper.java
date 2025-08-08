package pd.codetable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import messaging.codetable.GetCodeEvent;
import messaging.codetable.reply.ICode;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.PDCodeTableConstants;

public final class PDCodeHelper
{
    private static PDCodeHelper instance;

    private Hashtable cacheExpiration = new Hashtable();

    private Hashtable codes = new Hashtable();

    private static long expirationTime = 600000; /* 10 minutes */

    private PDCodeHelper()
    {
    }

    /**
     * @return PDCodeHelper
     */
    public static PDCodeHelper getInstance()
    {
        if (instance == null)
        {
            instance = new PDCodeHelper();
        }
        return instance;
    }

    public static Collection getCodes(String codeTableName, boolean sort)
    {
        Collection codes = getCodeCollection(codeTableName);
        if (sort == true)
        {
            Collections.sort((List) codes);
        }
        return codes;
    }

    public static String getCodeDescriptionByCode(Collection codes, String code)
    {
        String description = null;
        boolean done = false;
        if ((codes != null) && (code != null))
        {
            Iterator i = codes.iterator();
            while (done == false && i.hasNext())
            {
                Object codeObj = i.next();
                if (codeObj instanceof ICode)
                {
                    ICode codeEvent = (ICode) codeObj;
                    if (codeEvent.getCode().equals(code))
                    {
                        description = codeEvent.getDescription();
                        done = true;
                    }
                }
            }
        }
        return description;
    }

    /**
     * Retrieves codes for a given code table.
     * 
     * @param codeTableName
     * @return java.util.Collection
     */
    public Collection getCodes(String codeTableName)
    {
        synchronized (codeTableName.toLowerCase().intern())
        {
            checkLastCachedDateTime(codeTableName);
            Collection codeList = (Collection) codes.get(codeTableName);
            if (codeList == null || codeList.size() == 0)
            {
                codeList = getCodeCollection(codeTableName);
                codes.put(codeTableName, codeList);
                cacheExpiration.put(codeTableName, new Long(System.currentTimeMillis()));
            }

            // Create a new instance of the collection so that if there is any
            // tampering with the collection it does not throw a
            // ConcurrentException
            // Another way to change this would be to return the iterator of the
            // collection. As the collection is not accesible on the iterator
            ArrayList clone = new ArrayList();
            clone.addAll(codeList);
            return clone;
        }
    }

    /**
     * Returns RelationshipsToJuvenile Codes
     * 
     * @return Collection
     */
    public static Collection getRelationshipsToJuvenileCodes()
    {
        // TODO Update Juvenile Profile to pass in sort
        // TODO Remove once Juvenile Profile has been updated to pass in sort
        return PDCodeHelper.getCodes(PDCodeTableConstants.RELATIONSHIP_JUVENILE, false);
    }

    /**
     * Retrieves a code table by name.
     * 
     * @param codeTableName
     * @return java.util.Collection
     */
    private static Collection getCodeCollection(String codeTableName)
    {
        Collection theCodes = Code.findAll(codeTableName);
        return theCodes;
    }

    /**
     * Checks to see if the expiration time has passed for the codetablename. If
     * so, it removes it out of the codes hashtable so it will retrieve a fresh
     * set of codes. Expiration Time is a static value on this object set in
     * milliseconds.
     * 
     * @param codeTableName
     */
    private void checkLastCachedDateTime(String codeTableName)
    {
        Long last = (Long) cacheExpiration.get(codeTableName);
        if (last == null)
        {
            last = new Long(System.currentTimeMillis());
            cacheExpiration.put(codeTableName, last);
        }
        else
        {
            if (System.currentTimeMillis() - last.longValue() > expirationTime)
            {
                codes.remove(codeTableName);
            }
        }
    }

    public static String[] getCodesAsStringArray(Iterator codes)
    {
        List codeList = new ArrayList();
        while(codes.hasNext())
        {
            ICode code = (ICode) codes.next();
            codeList.add(code.getCode());
        }
        String[] codeArray = new String[codeList.size()];
        Iterator i = codeList.iterator();
        int index = 0;
        while(i.hasNext())
        {
            codeArray[index] = (String) i.next();
            index++;
        }
        return codeArray;
    }
    
    public static String[] getCodesAsStringArray(List codes)
    {
        String[] codeArray = new String[codes.size()];        
        int index = 0;
        int len = codes.size();
        for(int i=0;i<len;i++)
        {
            ICode code = (ICode) codes.get(i);
            codeArray[index] = code.getCode();
            index++;
        }
        return codeArray;
    }
    
	public static String getCodeDescription(String codeTableName, String code)
	{
		GetCodeEvent codeEvent = (GetCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETCODE);
		codeEvent.setCodeTableName(codeTableName);
		codeEvent.setCode(code);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(codeEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		ICode codeResponse = (ICode) MessageUtil.filterComposite(response, ICode.class);
		String description = null;
		if (codeResponse != null)
		{
			description = codeResponse.getDescription();
		}
		return description;
	}


}
