package pd.juvenilecase.referral.transactions;

import java.util.Iterator;
import java.util.List;

import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.productionsupport.GetTransOffenseReferralsQueryEvent;
import messaging.referral.GetJuvenileProfileReferralDetailsEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.PDJuvenileConstants;

import org.apache.commons.lang.StringUtils;

import pd.codetable.Code;
import pd.codetable.criminal.JuvenileDispositionCode;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.contact.officer.OfficerProfile;
import pd.juvenilecase.JuvenileCasefileReferral;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.referral.JJSTransferredOffenseReferral;
import pd.km.util.Name;

public class GetJuvenileProfileReferralDetailsCommand implements ICommand
{

    /**
     * @roseuid 4328435B0083
     */
    public GetJuvenileProfileReferralDetailsCommand()
    {

    }

    public void execute(IEvent event)
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	GetJuvenileProfileReferralDetailsEvent refEvent = (GetJuvenileProfileReferralDetailsEvent) event;

	Iterator<JuvenileCasefileReferral> iter = JuvenileCasefileReferral.findAll(refEvent);

	while (iter.hasNext())
	{

	    JuvenileCasefileReferral ref = iter.next();

	    JuvenileProfileReferralListResponseEvent resp = new JuvenileProfileReferralListResponseEvent();
	    //resp.setCasefileId( ref.getCaseFileId() );
	    resp.setTopic(PDJuvenileConstants.JUVENILE_PROFILE_REFERRAL_LIST_TOPIC);
	    resp.setAssignmentDate(DateUtil.dateToString(ref.getAssignmentDate(), DateUtil.DATE_FMT_1));
	    resp.setCaseStatus(ref.getCaseStatusCd());
	    resp.setFinalDisposition(ref.getDispositionCd());
	    if (StringUtils.isNotEmpty(ref.getCourtDispositionId()))
	    {
		resp.setFinalDisposition(ref.getCourtDispositionId());
		JuvenileDispositionCode juvDispCode = JuvenileDispositionCode.find("codeAlpha", ref.getCourtDispositionId());
		if (juvDispCode != null)
		{

		    resp.setFinalDispositionDescription(juvDispCode.getLongDesc());
		}
	    }

	    resp.setCourtDate(ref.getDecisionDate());
	    resp.setCourtId(ref.getCourtId());
	    if (StringUtils.isNotEmpty(ref.getCourtResultId()))
	    {
		resp.setCourtResult(ref.getCourtResultId());
		JuvenileDispositionCode resultCode = JuvenileDispositionCode.find("codeAlpha", ref.getCourtResultId());
		if (resultCode != null)
		{

		    resp.setCourtResultDesc(resultCode.getLongDesc());
		}
	    }

	    resp.setOffense(ref.getOffenseCodeId());
	    resp.setOffenseCategory(ref.getCategory());
	    resp.setInactiveInd(ref.getInactiveInd());
	    resp.setSeveritySubtype(ref.getSeveritySubtype());
	    resp.setPetitionNumber(ref.getPetitionNum());
	    resp.setPetitionAllegation(ref.getPetitionAllegation());
	    
	    if (StringUtils.isNotEmpty(ref.getPetitionAllegation()))
	    {
		resp.setPetitionsAvailable(true);
		//change for US180514
		GetTransOffenseReferralsQueryEvent getEvent = new GetTransOffenseReferralsQueryEvent();
	    	getEvent.setJuvenileNumber(ref.getJuvenileNum());
	    	getEvent.setReferralNumber(ref.getReferralNumber());
            	Iterator<JJSTransferredOffenseReferral> transOffenseReferralsIter = JJSTransferredOffenseReferral.findAll(getEvent);  
		if(ref.getReferraltypeInd()!=null&&transOffenseReferralsIter.hasNext()&&(ref.getReferraltypeInd().equalsIgnoreCase("TR")||ref.getReferraltypeInd().equalsIgnoreCase("IC")))
		{
		    	    JJSTransferredOffenseReferral transOffenseReferral = (JJSTransferredOffenseReferral) transOffenseReferralsIter.next();                	    
                	    if (transOffenseReferral.getOffenseCode() != null)
                	    {
                		JuvenileOffenseCode offenseCode = JuvenileOffenseCode.find("offenseCode", transOffenseReferral.getOffenseCode());
                		if (offenseCode != null)
                		{
                		    resp.setPetitionAllegationDescr(offenseCode.getLongDescription());                		    
                		}
                	    }
                	    resp.setPetitionallegationCategory(transOffenseReferral.getCategoryCode());
        		    resp.setPetitionAllegationDescrExtended(resp.getPetitionAllegationDescr() + ", " + resp.getPetitionallegationCategory());
		}
		else
		{
        		JuvenileOffenseCode offenseCode = JuvenileOffenseCode.find("offenseCode", ref.getPetitionAllegation());
        		if (offenseCode != null)		{
        		    
        		    resp.setPetitionAllegationDescr(offenseCode.getLongDescription());		    
        		    resp.setPetitionallegationCategory(offenseCode.getCategory());
        		    resp.setPetitionAllegationDescrExtended(resp.getPetitionAllegationDescr() + ", " + resp.getPetitionallegationCategory());
        		}
		}
		
	    }
	    resp.setPetitionStatus(ref.getPetitionStatus());
	    if ("R".equalsIgnoreCase(ref.getPetitionStatus()))
	    {
		resp.setPetitionStatusDescr("REOPENED");
	    }
	    else
		if ("F".equalsIgnoreCase(ref.getPetitionStatus()))
		{
		    resp.setPetitionStatusDescr("FILED");
		}
	    resp.setProbationStartDate(ref.getProbationStartDate());
	    resp.setProbationEndDate(ref.getProbationEndDate());
	    resp.setReferralNumber(ref.getReferralNumber());
	    resp.setReferralDate(ref.getReferralDate());
	    resp.setReferralOID(ref.getOID());
	    resp.setRefSeqNum(ref.getRefSeqNum());
	    resp.setReferralSource(ref.getReferralSource());
	    Name officer = new Name(ref.getOfficerFirstName(), ref.getOfficerMiddleName(), ref.getOfficerLastName());
	    resp.setJpo(officer.getFormattedName());
	    resp.setJpoId(ref.getOfficerCode());

	    if ( StringUtils.isEmpty( ref.getOfficerCode()) || ref.getOfficerCode()== null )
	    {
		resp.setJpoId(ref.getIntakeJPO());
		resp.setJpo("");
	    }

	    if (ref.getCloseDate() != null)
	    {
		resp.setReferralStatus("CLOSED");
		resp.setCloseDate(ref.getCloseDate());
	    }
	    else
	    {
		resp.setReferralStatus("ACTIVE");
	    }

	    resp.setSupervisionCategoryId(ref.getSupervisionCat());
	    resp.setSupervisionCategory(ref.getSupervisionDesc());

	    resp.setSupervisionTypeId(ref.getSupervisionTypeCd());
	    Code typeCD = Code.find(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_TYPE, ref.getSupervisionTypeCd());
	    if (typeCD != null)
	    {
		resp.setSupervisionType(typeCD.getDescription()); //need to get description here(code.getDescription());
	    }
	    resp.setIntakeDecDate(DateUtil.dateToString(ref.getIntakeDate(), DateUtil.DATE_FMT_1));
	    resp.setIntakeDecisionId(ref.getIntakeDecisionId());
	    Code intake = Code.find("REFERRAL.DECISION", ref.getIntakeDecisionId());
	    if (intake != null)
	    {
		resp.setIntakeDecision(intake.getDescription()); //need to get description here(code.getDescription());
	    }
	    resp.setAssignmentType(ref.getAssignmentType());
	    resp.setRecType(ref.getRecType());
	    resp.setControllingReferralId(ref.getControllingReferralId());

	    int numericCode = 0;
	    List<JJSOffense> offenses = InterviewHelper.getOffensesForReferral(refEvent.getJuvenileNum(), ref.getReferralNumber());

	    if (offenses != null)
	    {
		Iterator<JJSOffense> offensesItr = offenses.iterator();
		while (offensesItr.hasNext())
		{
		    JJSOffense offense = (JJSOffense) offensesItr.next();
		    JuvenileOffenseCode offenseCode = JuvenileOffenseCode.find("offenseCode", offense.getOffenseCodeId());
		    if (offenseCode != null)
		    {
			JuvenileOffenseCodeResponseEvent replyEvent = new JuvenileOffenseCodeResponseEvent();
			replyEvent.setOffenseCode(offenseCode.getOffenseCode());
			replyEvent.setNumericCode(offenseCode.getNumericCode());
			replyEvent.setShortDescription(offenseCode.getShortDescription());
			//if TR or IC get description from transferred offense
			GetTransOffenseReferralsQueryEvent getEvent = new GetTransOffenseReferralsQueryEvent();
		    	getEvent.setJuvenileNumber(ref.getJuvenileNum());
		    	getEvent.setReferralNumber(ref.getReferralNumber());
		    	Iterator<JJSTransferredOffenseReferral> transOffenseReferralsIter = JJSTransferredOffenseReferral.findAll(getEvent);   
			if(ref.getReferraltypeInd()!=null&&transOffenseReferralsIter.hasNext()&&(ref.getReferraltypeInd().equalsIgnoreCase("TR")||ref.getReferraltypeInd().equalsIgnoreCase("IC")))
			{
	                	    JJSTransferredOffenseReferral transOffenseReferral = (JJSTransferredOffenseReferral) transOffenseReferralsIter.next();                	    
	                	    if (transOffenseReferral.getOffenseCode() != null)
	                	    {
	                		JuvenileOffenseCode offCode = JuvenileOffenseCode.find("offenseCode", transOffenseReferral.getOffenseCode());
	                		if (offCode != null)
	                		{
	                		    replyEvent.setLongDescription(offCode.getLongDescription()+ ", " + transOffenseReferral.getCategoryCode());  
	                		    //offenseCode.setLongDescription(offCode.getLongDescription()+ ", " + transOffenseReferral.getCategoryCode());
	                		}
	                	    }
			}
			else
			    replyEvent.setLongDescription(offenseCode.getLongDescription());
			replyEvent.setCategory(offenseCode.getCategory());
			replyEvent.setSeverity(offenseCode.getSeverity());
			replyEvent.setSeveritySubtype(offenseCode.getSeveritySubtype());			
			resp.getOffenseCodes().add(replyEvent);			
		    }
		} //end of while(1)
	    }

	    if (offenses.size() > 0)
	    {
		resp.setOffensesAvailable(true);
		resp.setOffenses(offenses);
	    }
	    else
	    {
		resp.setOffensesAvailable(false);
	    }
	    dispatch.postEvent(resp);

	}
    }

    /**
     * @param status
     * @return
     */
    public String getStatusDescripton(String status)
    {
	String description = "";

	switch (status)
	{
	case "A":
	    description = "DA ACCEPT";
	    break;
	case "C":
	    description = "PRINTED";
	    break;
	case "F":
	    description = "REFER TO FIRST PROGRAM";
	    break;
	case "J":
	    description = "JUVENILE IDENTIFIED";
	    break;
	case "M":
	    description = "MENTAL HEALTH HOLD";
	    break;
	case "D":
	    description = "DISTRICT CLERK";
	    break;
	case "O":
	    description = "DIVERSION 180";
	    break;
	case "P":
	    description = "REFERRED TO JUV PROB DP90";
	    break;
	case "Q":
	    description = "REFERRED TO JUV PROB DP180";
	    break;
	case "R":
	    description = "REJECTED";
	    break;
	case "S":
	    description = "SECOND CHANCE PROGRAM";
	    break;
	case "V":
	    description = "VOP/PUNISHMENT";
	    break;
	case "W":
	    description = "5TH WARD PROGRAM";
	    break;
	default:
	    description = "";
	    break;
	}
	return description;
    }
}
