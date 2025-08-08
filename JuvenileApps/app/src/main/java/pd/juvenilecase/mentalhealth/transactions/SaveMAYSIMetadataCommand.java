package pd.juvenilecase.mentalhealth.transactions;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import messaging.juvenilecase.GetCasefileByJuvNumRefNum;
import messaging.mentalhealth.SaveMAYSIMetadataEvent;
import messaging.mentalhealth.reply.MAYSIAssessResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import naming.PDJuvenileCaseConstants;
import pd.codetable.criminal.JuvenileActivityTypeCode;
import pd.codetable.person.JuvenileLevelUnitCode;
import pd.codetable.person.ReasonNotDone;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.JuvenileCasefileReferral;
import pd.juvenilecase.mentalhealth.JuvenileMAYSIMetadata;

/**
 * 
 * @author dapte
 *
 * This command saves the MAYSI request(metadata) for a New MAYSI Assessment.
 * It creates a new instance of the JuvenileMAYSIMetadata entity. It does not 
 * affect the JuvenileMAYSI entity in any way. 
 * 
 * It responds with a ReturnException whenever system failure causes the save
 * to abort.
 * 
 * Name of the ResponseEvent: 
 * 1. messaging.juvenilecase.reply.MAYSISuccessEvent if save was successful
 * 2. mojo.km.exception.ReturnException: If save failed. 
 * 
 */
public class SaveMAYSIMetadataCommand implements ICommand
{

	/**
	 * @roseuid 42791F9003A9
	 */
	public SaveMAYSIMetadataCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42791D5702FF
	 */
	public void execute(IEvent event)
	{
		SaveMAYSIMetadataEvent sEvent = (SaveMAYSIMetadataEvent) event;
		JuvenileMAYSIMetadata juvMAYSIMetadata=null;
		if(sEvent.getMaysiId()!=null && !sEvent.getMaysiId().equals(""))
			juvMAYSIMetadata=JuvenileMAYSIMetadata.find(sEvent.getMaysiId());
		else
		{
			juvMAYSIMetadata = new JuvenileMAYSIMetadata();
			juvMAYSIMetadata.setFacilityTypeId(sEvent.getFacilityType());
			juvMAYSIMetadata.setHasPreviousMAYSI(sEvent.isHasPreviousMAYSI());
			juvMAYSIMetadata.setJuvenileNumber(sEvent.getJuvenileNumber());
			juvMAYSIMetadata.setLengthOfStayId(String.valueOf(sEvent.getLengthOfStay()));
			juvMAYSIMetadata.setLocationUnitId(sEvent.getLocationId());
			juvMAYSIMetadata.setReferralNumber(sEvent.getReferralNumber());
			juvMAYSIMetadata.setRequestDate(sEvent.getRequestDate());
			juvMAYSIMetadata.setRequestingOfficerId(sEvent.getRequestingOfficerId());
			juvMAYSIMetadata.setRaceId(sEvent.getRaceId());
			juvMAYSIMetadata.setScheduledOffIntDate(sEvent.getScheduledOffIntDate());
			juvMAYSIMetadata.setSexId(sEvent.getSexId());
			juvMAYSIMetadata.setCreateUserID(sEvent.getRequestingOfficerId());
			juvMAYSIMetadata.setCreateTimestamp(new Timestamp((new Date()).getTime()));
			juvMAYSIMetadata.setAdministered(sEvent.isAdministered());
			juvMAYSIMetadata.setTestAge(sEvent.getTestAge());
		}		
		if(sEvent.getReasonNotDone()!=null && !sEvent.getReasonNotDone().equals("")) {
			juvMAYSIMetadata.setAssessmentOptionId(PDJuvenileCaseConstants.TEST_NOT_ADMINISTERED_OPTION);
			juvMAYSIMetadata.setAdministered(false);
		} else {
			juvMAYSIMetadata.setAssessmentOptionId(PDJuvenileCaseConstants.NEW_MAYSI_ASSESSMENT_OPTION);
		}	
		juvMAYSIMetadata.setReasonNotDoneId(sEvent.getReasonNotDone());	
		juvMAYSIMetadata.setOtherReasonNotDone(sEvent.getOthReasonNotDone() );
		IHome home=new Home();
   		home.bind(juvMAYSIMetadata);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		MAYSIAssessResponseEvent maysiEvent = juvMAYSIMetadata.getResponseEvent();
		dispatch.postEvent(maysiEvent);
		GetCasefileByJuvNumRefNum searchEvent = new GetCasefileByJuvNumRefNum();
		searchEvent.setJuvenileNum(sEvent.getJuvenileNumber());
		searchEvent.setReferralNum(sEvent.getReferralNumber());
		
		Iterator iter = JuvenileCasefileReferral.findAll(searchEvent);
		while (iter.hasNext())
		{
			JuvenileCasefileReferral caseRef= (JuvenileCasefileReferral)iter.next();
			if(caseRef.getCaseFileId()!=null && !caseRef.getCaseFileId().equals("")){
			    JuvenileCasefile juvenileCasefile = (JuvenileCasefile) caseRef.getCaseFile();
			    /*check for assessoption A--check if juvMAYSIMetadata.getAssessmentOptionId!='A' task 172536
			    if(juvMAYSIMetadata.getAssessmentOptionId()!=null && juvMAYSIMetadata.getAssessmentOptionId().equalsIgnoreCase("A"))
				juvenileCasefile.setIsMAYSINeeded(true);
			    else*/
				juvenileCasefile.setIsMAYSINeeded(false);
			    //commenting out below as the existing casefile do not have to look for final reason its 0 always
			    //if sEvent.getReasonNotDone() has value
			    /*if(sEvent.getReasonNotDone()!=null &&!sEvent.getReasonNotDone().isEmpty())
			    {
				ReasonNotDone rnd =null;
				Iterator<ReasonNotDone> it = ReasonNotDone.findAll("code", sEvent.getReasonNotDoneId());
				while (it.hasNext()) 
				{
				    rnd = (ReasonNotDone) it.next();
				    break;
				}
				if(rnd.getFinalReason()!=null)
				{
				    if(rnd.getFinalReason().equalsIgnoreCase("YES"))
					juvenileCasefile.setIsMAYSINeeded(false);//for active casefiles-check the final reason of sEvent.getReasonNotDone()and then set if its no then set true else set false
				    else
					juvenileCasefile.setIsMAYSINeeded(true);
				}
			    }
			    //if sEvent.getReasonNotDone() not entered then juvenileCasefile.setIsMAYSINeeded(true);
			    else
				juvenileCasefile.setIsMAYSINeeded(true);*/
			}
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
