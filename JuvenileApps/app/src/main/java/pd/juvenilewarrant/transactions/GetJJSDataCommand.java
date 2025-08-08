package pd.juvenilewarrant.transactions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.commons.lang.StringUtils;
import org.ujac.util.BeanComparator;

import naming.PDConstants;
import naming.PDJuvenileWarrantConstants;
import pd.juvenile.JuvenileSchoolHistory;
import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilewarrant.JJSPetition;
import pd.juvenilewarrant.JuvenileJusticeSystem;
import pd.juvenilewarrant.JuvenileOffenderTracking;
import messaging.juvenile.GetJuvenileSchoolEvent;
import messaging.juvenilewarrant.GetJJSDataEvent;
import messaging.juvenilewarrant.GetPetitionBySequenceEvent;
import messaging.juvenilewarrant.reply.ActiveWarrantErrorEvent;
import messaging.juvenilewarrant.reply.JuvenileJusticeSystemResponseEvent;
import messaging.referral.GetJJSReferralEvent;
import mojo.km.messaging.ResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.util.CollectionUtil;
import pd.juvenilewarrant.PDJuvenileWarrantHelper;
import pd.codetable.person.JuvenileSchoolDistrictCode;
import pd.exception.ActiveWarrantException;

/**
 * @author ryoung
 */
public class GetJJSDataCommand implements ICommand
{

    private final boolean PRODUCE_ERROR = false;

    /**
     * @roseuid 41952151011A
     */
    public GetJJSDataCommand()
    {

    }

    /**
     * @param event
     * @roseuid 4195213701B8
     */
    public void execute(IEvent event) throws ActiveWarrantException
    {
	GetJJSDataEvent dataEvent = (GetJJSDataEvent) event;

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	this.setRequestEvent(dataEvent);

	JuvenileJusticeSystem jjs = JuvenileJusticeSystem.find(dataEvent);

	if (jjs != null || PRODUCE_ERROR == true)
	{
	    //			// See if there is an active warrant for this Juvenile (Petition# or Juv# and Ref#)
	    //			// If not found, can proceed, as there is no ACTIVE juvenile warrant
	    //			// for this juvenile under this referral.  So nothing needs to be 
	    //			// caught or thrown.
	    //			boolean activeWarrantExists = PDJuvenileWarrantHelper.checkForActiveWarrant(jjs.getJuvenileNum());

	    // See if there is an active warrant for this Juvenile (Petition# or Juv# and Ref#)
	    // If not found, can proceed, as there is no ACTIVE juvenile warrant
	    // for this juvenile under this referral.  So nothing needs to be
	    // caught or thrown.

	    Integer juvenileNum = null;
	    if (jjs.getJuvenileNum() != null && PDConstants.BLANK.equals(jjs.getJuvenileNum()) == false)
	    {
		juvenileNum = new Integer(jjs.getJuvenileNum());

		if (dataEvent.getPetitionNum() == null || PDConstants.BLANK.equals(dataEvent.getPetitionNum()) == true)
		{
		    //0386741201705883J
		    String oidVal = jjs.getOID();
		    if (oidVal.length() > 16)
		    {
			dataEvent.setPetitionNum(oidVal.substring(7));
		    }

		}
	    }

	    Integer referralNum = null;
	    if (dataEvent.getReferralNum() != null && PDConstants.BLANK.equals(dataEvent.getReferralNum()) == false)
	    {
		referralNum = new Integer(dataEvent.getReferralNum());
	    }

	    boolean activeWarrantExists = false;

	    if (juvenileNum != null)
	    {

		activeWarrantExists = PDJuvenileWarrantHelper.findExistingWarrant(juvenileNum, null, jjs.getDateOfBirth(), jjs.getFirstName(), jjs.getLastName(), referralNum, dataEvent.getPetitionNum());
	    }

	    if (activeWarrantExists == true)
	    {
		ActiveWarrantErrorEvent error = new ActiveWarrantErrorEvent();
		error.setJuvenileNum(jjs.getJuvenileNum());
		dispatch.postEvent(error);
	    }
	    else
	    {
		this.sendJJSValues(dispatch, jjs, dataEvent);
		PDJuvenileWarrantHelper.sendAssociateValues(dispatch, jjs);
	    }

	}
    }

    /**
     * @param jjs
     */
    private void sendJJSValues(IDispatch dispatch, JuvenileJusticeSystem jjs, GetJJSDataEvent dataEvent)
    {
	JuvenileJusticeSystemResponseEvent responseEvent = (JuvenileJusticeSystemResponseEvent) jjs.valueObject();

	JuvenileSchoolHistory jsh = this.getCurrentJuvenileSchoolHistory(jjs.getJuvenileNum());

	JuvenileSchoolDistrictCode schoolCode = null;

	if (jsh == null)
	{
	    if (!jjsSchoolDataExists(responseEvent))
	    {
		GetJJSReferralEvent jjsRefEvt = new GetJJSReferralEvent();
		jjsRefEvt.setJuvenileNum(jjs.getJuvenileNum());
		jjsRefEvt.setReferralNum(dataEvent.getReferralNum());
		List<JJSReferral> referralList = CollectionUtil.iteratorToList(JJSReferral.findAll(jjsRefEvt));

		if (referralList.size() > 0)
		{
		    JJSReferral jjsReferral = referralList.get(0);
		    JuvenileOffenderTracking jot = JuvenileOffenderTracking.find(jjsReferral.getDaLogNum());
		    if (jot != null)
		    {
			schoolCode = jot.getSchoolName();
			if (schoolCode != null)
			{//rry added this check

			    responseEvent.setSchoolDistrictId(schoolCode.getDistrictCode());
			    responseEvent.setSchoolDistrict(schoolCode.getDistrictDescription());
			    responseEvent.setSchoolCodeId(schoolCode.getSchoolCode());
			    responseEvent.setSchoolName(schoolCode.getSchoolDescription());
			}
		    }
		    jot = null;
		    jjsReferral = null;
		}
		jjsRefEvt = null;
		referralList = null;
	    }
	}
	else
	{
	    schoolCode = jsh.getSchool();
	    responseEvent.setSchoolDistrictId(StringUtils.removeStart(jsh.getSchoolDistrictId(), "0"));
	    responseEvent.setSchoolCodeId(StringUtils.removeStart(jsh.getSchoolId(),"0"));
	    if (schoolCode != null)
	    {//rry added this check

		responseEvent.setSchoolDistrict(schoolCode.getDistrictDescription());
		responseEvent.setSchoolName(schoolCode.getSchoolDescription());
	    }
	}

	dispatch.postEvent(responseEvent);

	GetPetitionBySequenceEvent requestEvent = new GetPetitionBySequenceEvent();

	requestEvent.setJuvenileNum(jjs.getJuvenileNum());
	requestEvent.setReferralNum(dataEvent.getReferralNum());
	requestEvent.setSequenceNum("1");
	
	Iterator p = JJSPetition.findAll(requestEvent);
	while (p.hasNext())
	{
	    JJSPetition pet = (JJSPetition) p.next();
	    //Only allow the first sequence for each petition
	    ResponseEvent petitionEvent = pet.valueObject();
	    dispatch.postEvent(petitionEvent);
	    pet = null;
	    petitionEvent = null;

	}

	jsh = null;
	schoolCode = null;
    }

    private boolean jjsSchoolDataExists(JuvenileJusticeSystemResponseEvent jjsResponseEvent)
    {

	boolean jjsSchoolDataExists = false;

	if (jjsResponseEvent.getSchoolCodeId() != null && !jjsResponseEvent.getSchoolCodeId().equals(PDConstants.BLANK))
	{
	    jjsSchoolDataExists = true;
	}

	return jjsSchoolDataExists;
    }

    private JuvenileSchoolHistory getCurrentJuvenileSchoolHistory(String juvenileNum)
    {

	GetJuvenileSchoolEvent schoolEvent = new GetJuvenileSchoolEvent();
	schoolEvent.setJuvenileNum(juvenileNum);
	JuvenileSchoolHistory jsh = null;

	List<JuvenileSchoolHistory> schoolList = CollectionUtil.iteratorToList(JuvenileSchoolHistory.findJuvenileSchoolHistory(schoolEvent));

	if (schoolList.size() > 0)
	{
	    ArrayList sortFields = new ArrayList();
	    sortFields.add(new ReverseComparator(new BeanComparator("createTimestamp")));
	    ComparatorChain multiSort = new ComparatorChain(sortFields);
	    Collections.sort(schoolList, multiSort);
	    jsh = schoolList.get(0);

	    sortFields = null;
	    multiSort = null;
	}

	schoolList = null;
	schoolEvent = null;

	return jsh;
    }

    /**
     * @param dataEvent
     */
    private void setRequestEvent(GetJJSDataEvent dataEvent)
    {
	if (PDJuvenileWarrantConstants.WARRANT_TYPE_OIC.equals(dataEvent.getWarrantType()))
	{
	    dataEvent.setJuvenileNum(null);
	}
	else
	    if (PDJuvenileWarrantConstants.WARRANT_TYPE_VOP.equals(dataEvent.getWarrantType()))
	    {
		dataEvent.setPetitionNum(null);
	    }

    }

    /**
     * @param event
     * @roseuid 4195213701BA
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 4195213701BC
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param updateObject
     * @roseuid 41952151012A
     */
    public void update(Object updateObject)
    {

    }
}
