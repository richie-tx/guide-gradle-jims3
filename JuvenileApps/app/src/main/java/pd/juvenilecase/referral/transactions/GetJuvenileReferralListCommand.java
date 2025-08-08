//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\GetJuvenileProfileReferralListCommand.java

package pd.juvenilecase.referral.transactions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import messaging.codetable.criminal.reply.JuvenileDispositionCodeResponseEvent;
import messaging.juvenilecase.GetJJSCourtEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.referral.GetJuvenileProfileReferralListEvent;
import messaging.referral.GetJuvenileReferralListEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.PDJuvenileConstants;
import pd.codetable.Code;
import pd.codetable.criminal.JuvenileDispositionCode;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.codetable.criminal.JuvenileReferralSource;
import pd.juvenile.Juvenile;
import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilewarrant.JJSPetition;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;

public class GetJuvenileReferralListCommand implements ICommand
{

    /**
     * @roseuid 4328435B0083
     */
    public GetJuvenileReferralListCommand()
    {

    }

    /**
     * @param event
     * @roseuid 432836080271
     */
    public void execute(IEvent event)
    {
	GetJuvenileReferralListEvent refEvent = (GetJuvenileReferralListEvent) event;
	Iterator<JJSReferral> referralList = JJSReferral.findAll("juvenileNum", refEvent.getJuvenileNum());

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	while (referralList.hasNext())
	{
	    JJSReferral ref = (JJSReferral) referralList.next();
	    JuvenileProfileReferralListResponseEvent resp = new JuvenileProfileReferralListResponseEvent();
	    resp.setTopic(PDJuvenileConstants.JUVENILE_PROFILE_REFERRAL_LIST_TOPIC);
	    resp.setCourtDisposition(ref.getCourtDispositionId());
	    resp.setCourtResult(ref.getCourtResultId());
	    resp.setCloseDate(ref.getCloseDate());
	    resp.setJpoId(ref.getJpoid());
	    resp.setIntakeDecDate(DateUtil.dateToString(ref.getIntakeDate(), DateUtil.DATE_FMT_1));
	    if (ref.getIntakeDecisionId() != null && !ref.getIntakeDecisionId().equals(""))
	    {
		Code intakeDescision = ref.getIntakeDecision();
		resp.setIntakeDecision(intakeDescision.getDescription());
		resp.setIntakeDecisionId(intakeDescision.getCode());
	    }

	    resp.setReferralDate(ref.getReferralDate());
	    resp.setReferralNumber(ref.getReferralNum());
	    resp.setCourtDate(ref.getCourtDate());
	    resp.setCourtId(ref.getCourtId());
	    resp.setCtAssignJpoId(ref.getCtAssignJPOId());
	    resp.setCtAssignLevel(ref.getCtAssignLevel());
	    resp.setPiaStatus(ref.getPIACode());
	    resp.setTJJDDate(DateUtil.dateToString(ref.getTJJDReferralDate(), DateUtil.DATE_FMT_1));
	    resp.setProbationJPOId(ref.getProbJPOId());
	    resp.setProbationLevel(ref.getProbLevel());
	    resp.setProbationStartDate(ref.getProbationStartDate());
	    resp.setProbationEndDate(ref.getProbationEndDate());
	    resp.setRecType(ref.getRecType());
	    resp.setDaLogNum(ref.getDaLogNum());
	    resp.setLcUser(ref.getLcUser());
	    if (ref.getCloseDate() != null)
		resp.setReferralStatus("CLOSED");
	    else
		resp.setReferralStatus("ACTIVE");
	    if (ref.getReferralTypeInd() != null)
	    {
		resp.setReferralTypeInd(ref.getReferralTypeInd());
		//ER changes 11054
		resp.setReferralTypeDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.REFERRAL_TYPE, ref.getReferralTypeInd()));
	    }	   
	    dispatch.postEvent(resp);
	}

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
