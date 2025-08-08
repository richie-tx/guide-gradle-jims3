package pd.codetable.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.codetable.GetJuvenileActivityTypeCodesEvent;
import messaging.codetable.criminal.reply.JuvenileActivityTypeCodeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.logging.LogUtil;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;

import org.apache.commons.collections.FastHashMap;
import org.apache.log4j.Level;

import pd.codetable.Code;
import pd.codetable.criminal.JuvenileActivityTypeCode;

public class GetJuvenileActivityTypeCodesCommand implements ICommand
{

    /**
     * @roseuid 45771D15009E
     */
    public GetJuvenileActivityTypeCodesCommand()
    {
    }

    /**
     * @param event
     * @roseuid 45771956034C
     */

    public void execute(IEvent event)
    {
    	
    	GetJuvenileActivityTypeCodesEvent evt = (GetJuvenileActivityTypeCodesEvent)event;
        Map categoryCodeMap = new FastHashMap();
        Map typeCodeMap = new FastHashMap();
        this.buildCodeLookups(categoryCodeMap, typeCodeMap);

        // key: "categoryId" values: "category responses"
        Map categoryListMap = new FastHashMap();

        // key: "categoryId + typeId" values: "type responses"
        Map typeListMap = new FastHashMap();

        IHome home = new Home();
        Iterator i = home.findAll(JuvenileActivityTypeCode.class);

        // loop through categories, build activityTypes and activities
        while (i.hasNext())
        {
            JuvenileActivityTypeCode activityCode = (JuvenileActivityTypeCode) i.next();
         	String permissionId = activityCode.getPermissionId();
         	//added for U.S 11097
         	if (permissionId!=null && evt.getAction().equals("add") && !"M".equals( evt.getPermissionType() ) ){
         		if(permissionId.equals("S")){
         			continue;
         		}
         	}
         	
	    if (permissionId != null && evt.getAction().equals("add") && "M".equals(evt.getPermissionType())){
         		if( !permissionId.equals("M")){
         			continue;
         		}
         	}
            if (!PDCodeTableConstants.STATUS_INACTIVE.equals(activityCode.getStatus())) {
            	// set local key vars for convenience and code readability
            	String categoryId = activityCode.getCategoryId();
            	String typeId = activityCode.getTypeId();
            	String categoryType = categoryId + typeId;

            	JuvenileActivityTypeCodeResponseEvent categoryResp = (JuvenileActivityTypeCodeResponseEvent) categoryListMap
                    .get(categoryId);
            	if (categoryResp == null)
            	{
                	categoryResp = this.buildResponse("category", categoryCodeMap, categoryId);
                	categoryListMap.put(categoryId, categoryResp);
            	}
            	// build this category's child type responses
            	JuvenileActivityTypeCodeResponseEvent typeResp = (JuvenileActivityTypeCodeResponseEvent) typeListMap
                    .get(categoryType);
            	if (typeResp == null)
            	{
                	typeResp = this.buildResponse("type", typeCodeMap, typeId);
                	typeListMap.put(categoryType, typeResp);
                	categoryResp.subTypes.add(typeResp);
            	}

            	JuvenileActivityTypeCodeResponseEvent activityResp = new JuvenileActivityTypeCodeResponseEvent();
            	activityResp.setCode(activityCode.getCode());
            	activityResp.setDescription(activityCode.getDescription());
            	typeResp.subTypes.add(activityResp);
            }
        }

        List categories = new ArrayList(categoryListMap.values());

        JuvenileActivityTypeCodeResponseEvent resp = new JuvenileActivityTypeCodeResponseEvent();
        resp.setReturnValues(categories);
        MessageUtil.postReply(resp);
    }

    private JuvenileActivityTypeCodeResponseEvent buildResponse(String lookupType, Map lookup, String aCode)
    {
        JuvenileActivityTypeCodeResponseEvent resp = new JuvenileActivityTypeCodeResponseEvent();
        Code code = (Code) lookup.get(aCode);
        if (code == null)
        {
            String msg = "JuvenileActivityCode lookup for: " + lookupType + " was not found: " + aCode;
            LogUtil.log(Level.ERROR, msg);
        }
        resp.setCode(code.getCode());
        resp.setDescription(code.getDescription());
        return resp;
    }

    /**
     * @return
     */
    private void buildCodeLookups(Map categoryMap, Map typeMap)
    {
        // build category lookup
        Collection codes = Code.findAll("ACTIVITY_CATEGORY");
        Iterator i = codes.iterator();
        while (i.hasNext())
        {
            Code code = (Code) i.next();
            categoryMap.put(code.getCode(), code);
        }

        // build type lookup
        codes = Code.findAll("ACTIVITY_TYPE");
        i = codes.iterator();
        while (i.hasNext())
        {
            Code code = (Code) i.next();
            typeMap.put(code.getCode(), code);
        }
    }

    /**
     * @param event
     * @roseuid 45771956034E
     */
    public void onRegister(IEvent event)
    {
    }

    /**
     * @param event
     * @roseuid 45771956035C
     */
    public void onUnregister(IEvent event)
    {
    }

    /**
     * @param anObject
     * @roseuid 45771956035E
     */
    public void update(Object anObject)
    {
    }
}