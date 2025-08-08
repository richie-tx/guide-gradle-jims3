package pd.juvenilecase.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import naming.PDCodeTableConstants;
import messaging.juvenilecase.GetAllCasefilesForReferralsEvent;
import messaging.juvenilecase.GetJuvenileCasefileAssignmentsEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.util.DateUtil;
import pd.codetable.Code;
import pd.juvenilecase.JuvenileCasefileReferral;
import pd.juvenilecase.SupervisionTypeMap;
import pd.juvenilecase.referral.JJSSVIntakeHistory;
import ui.common.SimpleCodeTableHelper;

/**
 * 
 * 
 * 
 */
public class GetAllCasefilesForReferralsCommand implements ICommand
{

    /**
     * @roseuid 42791F9003A9
     */
    public GetAllCasefilesForReferralsCommand()
    {

    }

    /**
     * @param event
     * @roseuid 42791D5702FF
     */
    public void execute(IEvent event)
    {

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	GetAllCasefilesForReferralsEvent searchEvent = (GetAllCasefilesForReferralsEvent) event;
	JuvenileProfileReferralListResponseEvent resp = new JuvenileProfileReferralListResponseEvent();
	resp.setReferralNumber(searchEvent.getReferralNum());
	Collection<JuvenileCasefileReferral> coll = new ArrayList<JuvenileCasefileReferral>();
	SupervisionTypeMap sMap = null;
	String supervisionCategory = null;
	
	Iterator<JuvenileCasefileReferral> iter = JuvenileCasefileReferral.findAll(searchEvent);
	//US 71177 - if no assignments found - check intake history
	if(!iter.hasNext())
	{
	    GetJuvenileCasefileAssignmentsEvent intakeEvt = new GetJuvenileCasefileAssignmentsEvent();
	    intakeEvt.setReferralNum(searchEvent.getReferralNum());
	    intakeEvt.setJuvenileNum(searchEvent.getJuvenileNum());
	    Iterator intakeIter = JJSSVIntakeHistory.findAll(intakeEvt);
	    if(intakeIter.hasNext())
	    {
		JJSSVIntakeHistory intakeHistory = (JJSSVIntakeHistory) intakeIter.next();
		resp.setAssignmentType(intakeHistory.getAssignmentType());
		resp.setAssignmentDate(DateUtil.dateToString(intakeHistory.getAssignmentDate(), DateUtil.DATE_FMT_1));		
		resp.setJpo(intakeHistory.getJpoId());	
		if(intakeHistory.getAssignmentType()!= null && intakeHistory.getAssignmentType().equalsIgnoreCase("REC"))
		{
		    resp.setSupervisionCategoryId("RC");
		    resp.setSupervisionCategory("RECEIVING");
		    resp.setSupervisionTypeId("RCV");
		    resp.setSupervisionType("RECEIVING");
		    resp.setJpo("UVREC");
		}
		else if(intakeHistory.getAssignmentType()!= null && intakeHistory.getAssignmentType().equalsIgnoreCase("ADM"))
		{
		    resp.setSupervisionCategoryId("PP");
		    resp.setSupervisionCategory("PRE PETITION");
		    resp.setSupervisionTypeId("ISS");	
		    resp.setSupervisionType("INTAKE SCREENING SUPERVISION");
		    resp.setJpo("UVANC");
		}
		else if(intakeHistory.getAssignmentType()!= null)
		{
		    resp.setSupervisionTypeId(intakeHistory.getSupervisionTypeId());
		    Code code = Code.find(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_TYPE, intakeHistory.getSupervisionTypeId());
		    if (code != null)
		    {
			resp.setSupervisionType(code.getDescription());
		    }
		    Iterator<SupervisionTypeMap> supervisioncategoryIter = SupervisionTypeMap.findAll("supervisionTypeId", intakeHistory.getSupervisionTypeId());
		    if (supervisioncategoryIter.hasNext())
		    {
			    sMap = supervisioncategoryIter.next();
			    if (sMap != null)
			    {
				supervisionCategory = sMap.getSupervisionCatId();				
				resp.setSupervisionCategoryId(supervisionCategory); // category.
				resp.setSupervisionCategory(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_CATEGORY, supervisionCategory));
			    }
		    }
		}
	    }
	}
	while (iter.hasNext())
	{
	    JuvenileCasefileReferral caseRef = (JuvenileCasefileReferral) iter.next();
	    if (caseRef != null)
	    {
		Iterator<SupervisionTypeMap> supervisioncategoryIter = SupervisionTypeMap.findAll("supervisionTypeId", caseRef.getSupervisionTypeCd());
		Code code = Code.find(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_TYPE, caseRef.getSupervisionTypeCd());
		if (code != null)
		{
		    caseRef.setSupervisionType(code.getDescription()); //set Supervision type desc
		}
		if (supervisioncategoryIter.hasNext())
		{
		    sMap = supervisioncategoryIter.next();
		    if (sMap != null)
		    {
			supervisionCategory = sMap.getSupervisionCatId();
			caseRef.setSupervisionCat(supervisionCategory); // category.
		    }
		}

	    }
	    //
	    coll.add(caseRef);
	}
	resp.setCasefileReferrals(coll);
	dispatch.postEvent(resp);
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
