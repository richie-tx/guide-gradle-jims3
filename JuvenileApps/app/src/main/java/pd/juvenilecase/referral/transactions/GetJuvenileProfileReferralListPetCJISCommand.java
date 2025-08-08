//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\GetJuvenileProfileReferralListCommand.java

package pd.juvenilecase.referral.transactions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import messaging.codetable.criminal.reply.JuvenileDispositionCodeResponseEvent;
import messaging.juvenilecase.GetJJSCourtEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.referral.GetJuvenileProfileReferralListEvent;
import messaging.referral.GetJuvenileProfileReferralListPetCJISEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.PDJuvenileConstants;
import pd.codetable.criminal.JuvenileDispositionCode;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.codetable.criminal.JuvenileReferralDispositionCode;
import pd.codetable.criminal.JuvenileReferralSource;
import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilewarrant.JJSPetition;
import pd.juvenilewarrant.JuvenileOffenderTracking;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;

public class GetJuvenileProfileReferralListPetCJISCommand implements ICommand
{

    /**
     * @roseuid 4328435B0083
     */
    public GetJuvenileProfileReferralListPetCJISCommand()
    {

    }

    /**
     * @param event
     * @roseuid 432836080271
     */
    public void execute(IEvent event)
    {
	GetJuvenileProfileReferralListPetCJISEvent refEvent = (GetJuvenileProfileReferralListPetCJISEvent) event;
	//Iterator<JJSReferral> referralList = JJSReferral.findAll(refEvent);
	Collection<PetitionResponseEvent> openRefsPetition = new ArrayList<PetitionResponseEvent>();
	
	PetitionResponseEvent resp = new PetitionResponseEvent();
	
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);	
	
		Iterator<JJSReferral> referralList = JJSReferral.findAll(refEvent);
		if (referralList.hasNext())
		{
		    JJSReferral ref = (JJSReferral) referralList.next();
		    resp.setReferralNum(ref.getReferralNum());
		    resp.setReferralDate(ref.getReferralDate());
		    resp.setCourtId(ref.getCourtId());
		    resp.setCourtDate(DateUtil.dateToString(ref.getCourtDate(), DateUtil.DATE_FMT_1));
		    if (ref.getCourtResultId() != null)
		    {
			resp.setCourtResult(ref.getCourtResultId());
			JuvenileDispositionCode courtResult = ref.getCourtResult();
			if (courtResult != null)			
			    resp.setCourtResultDesc(courtResult.getLongDesc());
			    
		    }
		    
		    if (ref.getCourtDispositionId() != null)
		    {
			resp.setFinalDisposition(ref.getCourtDispositionId());
			JuvenileDispositionCode courtDisposition = ref.getCourtDisposition();
			if (courtDisposition != null)
			    resp.setFinalDispositionDescription(courtDisposition.getLongDesc());
		    }
		}
		List<JJSOffense> offenses = InterviewHelper.getOffensesForReferral(refEvent.getJuvenileNum(), refEvent.getRefNum());
		if (offenses.size() > 0)
		{
			resp.setOffenses(offenses);
		}
	    dispatch.postEvent(resp);	
    }
	

	
    /**
     * @param event
     * @roseuid 432836080273
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 432836080275
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param updateObject
     * @roseuid 4328435B0093
     */
    public void update(Object updateObject)
    {

    }
}
