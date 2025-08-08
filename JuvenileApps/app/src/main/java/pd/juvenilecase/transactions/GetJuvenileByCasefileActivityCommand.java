package pd.juvenilecase.transactions;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import messaging.juvenilecase.GetJuvenileByCasefileActivityEvent;
import messaging.juvenilecase.GetJuvenileCasefileControllingReferralsEvent;
import messaging.juvenilecase.reply.JuvenileCasefileActivityResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import pd.codetable.criminal.JuvenileActivityTypeCode;
import pd.contact.officer.OfficerProfile;
import pd.contact.officer.PDOfficerProfileHelper;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.JuvenileCasefileReferral;
import pd.juvenilecase.casefile.ActivityHelper;
import pd.km.util.Name;
import ui.common.CodeHelper;

/**
 * 
 * 
 * 
 */
public class GetJuvenileByCasefileActivityCommand implements ICommand
{

    /**
     * @roseuid 42791F9003A9
     */
    public GetJuvenileByCasefileActivityCommand()
    {

    }

    /**
     * @param event
     * @roseuid 42791D5702FF
     */
    public void execute(IEvent event)
    {

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	GetJuvenileByCasefileActivityEvent searchEvent = (GetJuvenileByCasefileActivityEvent) event;

	Collection activitiesCategories = CodeHelper.getActivityCategory(true);	
	Collection activitiesTypes = CodeHelper.getActivityType(true);
	Iterator<JuvenileCasefile> iter = JuvenileCasefile.findAll(searchEvent);
	while (iter.hasNext())
	{
	    JuvenileCasefile casefile = (JuvenileCasefile) iter.next();
	    JuvenileCasefileActivityResponseEvent resp = new JuvenileCasefileActivityResponseEvent();
	    //resp = casefile.get	get the response event.	 
	    resp.setCasefileId(casefile.getCasefileId());
	    resp.setJuvenileId(casefile.getJuvenileId());
	    resp.setSupervisionTypeId(casefile.getSupervisionTypeId());
	    resp.setOfficerLogonIdData(casefile.getOfficerLogonIdData());
	    resp.setActivityCodeId(casefile.getActivityCodeId());
	    JuvenileActivityTypeCode  activityTypeCode= ActivityHelper.getActivityTypeCode(casefile.getActivityCodeId());
	    if (activityTypeCode!=null &&!activityTypeCode.getActivityTypeId().isEmpty())
		resp.setActivitycodeDesc(activityTypeCode.getDescription());
	    resp.setActivityDate(casefile.getActivityDate());
	    
	    //US 157007 - format activity time
	    SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
	    if(casefile.getActivityTime() != null && !casefile.getActivityTime().equals(""))
	    {
		 Date activitytime = DateUtil.stringToDate(casefile.getActivityTime(), DateUtil.TIME_FMT_1);
		 String actime = dateFormat.format(activitytime);
		 resp.setActivityTime(actime);
	    }
		 
	    resp.setTypeId(casefile.getTypeId());
	    resp.setTypeDesc(CodeHelper.getCodeDescriptionByCode(activitiesTypes, casefile.getTypeId()));
	    resp.setCategoryId(casefile.getCategoryId());
	    resp.setCategoryDesc(CodeHelper.getCodeDescriptionByCode(activitiesCategories, casefile.getCategoryId()));
	    resp.setPermissionId(casefile.getPermissionId());
	    resp.setJuvenilefirstName(casefile.getJuvenilefirstName());
	    resp.setJuvenilemiddleName(casefile.getJuvenilemiddleName());
	    resp.setJuvenilelastName(casefile.getJuvenilelastName());
	    resp.setOfficerLastNameData(casefile.getOfficerLastNameData());
	    resp.setOfficerFirstNameData(casefile.getOfficerFirstNameData());
	    resp.setOfficerMiddleNameData(casefile.getOfficerMiddleNameData());
	    Name officerName = new Name(casefile.getOfficerFirstNameData(), casefile.getOfficerMiddleNameData(), casefile.getOfficerLastNameData());
            resp.setOfficerfullName(officerName.getFormattedName());
	    resp.setJuvLocationId(casefile.getJuvLocationId());
	    if(casefile.getLogonId()!=null)
		resp.setLogonId(casefile.getLogonId().toUpperCase());
	    else
		resp.setLogonId(null);
	    if(casefile.getCreateUser()!=null)
		resp.setCreateUser(casefile.getCreateUser().toUpperCase());
	    else
		resp.setCreateUser(null);
	    Iterator i= OfficerProfile.findAll("logonId", casefile.getCreateUser());
	    while(i != null && i.hasNext())
		{
			OfficerProfile officerProfile = (OfficerProfile) i.next();
			if (!officerProfile.getStatusId().equals("I") && !officerProfile.getStatusId().equals("INACTIVE"))
			{
			    Name userName = new Name(officerProfile.getFirstName(), officerProfile.getMiddleName(), officerProfile.getLastName());
			    resp.setCreateuserLiteral(userName.getFormattedName());
			}
		}
	    
	    Date   actEndTime = null;
	    String activityTimePattern = new String("hh:mm a");
	    String activityTime24Pattern = new String ("HH:mm");
	    if ( casefile.getActivityEndTime() != null ) {
		    actEndTime = DateUtil.stringToDate( casefile.getActivityEndTime(), activityTime24Pattern);
		    resp.setActivityEndTime(DateUtil.dateToString(actEndTime, activityTimePattern));
		}
	    resp.setLatitude( casefile.getLatitude());
	    resp.setLongitude( casefile.getLongitude());
	    
	    if(casefile.getDaysDiff() != null && !casefile.getDaysDiff().equals("")){
		resp.setDaysDiff(Long.parseLong(casefile.getDaysDiff()));
	    }
	    
	    resp.setActivityCreateDate(casefile.getCreateDate());
	    
	    
	    dispatch.postEvent(resp);
	}
    }

    /**
     * @param event
     * @roseuid 42791D570301
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 42791D570303
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 42791D57030E
     */
    public void update(Object anObject)
    {

    }

}
