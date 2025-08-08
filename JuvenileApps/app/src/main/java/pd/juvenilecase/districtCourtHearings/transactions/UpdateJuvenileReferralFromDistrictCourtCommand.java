package pd.juvenilecase.districtCourtHearings.transactions;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.districtCourtHearings.UpdateJuvenileReferralFromDistrictCourtEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenilewarrant.SaveJuvenilePetitionInformationEvent;
import messaging.juvenilewarrant.UpdateJuvenilePetitionInformationEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.referral.GetJJSReferralEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileWarrantControllerServiceNames;
import pd.codetable.criminal.JuvenileDispositionCode;
import pd.juvenilecase.referral.JJSReferral;
import pd.security.PDSecurityHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;

/**
 * @author sthyagarajan
 */
public class UpdateJuvenileReferralFromDistrictCourtCommand implements ICommand
{
    @Override
    public void execute(IEvent event) throws Exception
    {
	UpdateJuvenileReferralFromDistrictCourtEvent juvenileReferralEvt = (UpdateJuvenileReferralFromDistrictCourtEvent) event;

	IHome home = new Home();
	boolean updateFlag = false;
	
	GetJJSReferralEvent jjsEvt = new GetJJSReferralEvent();
	jjsEvt.setJuvenileNum(juvenileReferralEvt.getJuvenileNumber());
	jjsEvt.setReferralNum(juvenileReferralEvt.getReferralNumber());

	Iterator<JJSReferral> refIter = JJSReferral.findAll(jjsEvt);
	JJSReferral ref = null;
	if (refIter.hasNext())
	{
	    ref = refIter.next();
	}
	if (ref != null)
	{
	    //delete flag conditions.
	    if (juvenileReferralEvt.getDeleteFlag() != null && juvenileReferralEvt.getDeleteFlag().equalsIgnoreCase("true"))
	    {
		if (juvenileReferralEvt.getIsOnlySetting()!=null && juvenileReferralEvt.getIsOnlySetting().equalsIgnoreCase("true"))
		{
		    ref.setCourtDate(null);
		    ref.setCourtDispositionId(null);
		    ref.setCourtId(null);
		    ref.setPIACode(null);
		    ref.setCourtResultId(null);
		    ref.setDecisionType(null);
		    ref.setLcDate(DateUtil.getCurrentDate());
		    ref.setLcTime(Calendar.getInstance().getTime());
		    ref.setLcUser(PDSecurityHelper.getLogonId());
		    home = new Home();
		    home.bind(ref);
		}
		else
		{
		    String conditionCheck="";
		    Iterator<DocketEventResponseEvent> docketResponsesFirstItr = juvenileReferralEvt.getCourtSettings().iterator();
		    while (docketResponsesFirstItr.hasNext())
		    {
			DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesFirstItr.next();
			if (docResp != null)
			{
			    JuvenileDispositionCode juvDispCode = null;
			    if (docResp.getCourtResult() != null && !docResp.getCourtResult().isEmpty())
			    {
				juvDispCode = JuvenileDispositionCode.find("codeAlpha",docResp.getCourtResult());
				if (juvDispCode != null && juvDispCode.getInactiveInd() != null && !juvDispCode.getInactiveInd().equalsIgnoreCase("Y"))
				{
				    /**
				     * 1) If the Result�s Category code = �P�
				     * and Option = �A�, the calendar record
				     * that is currently being processed will be
				     * used to update the referral�s
				     * CourtDisposition (using
				     * HearingDecisionCode); CourtDate should be
				     * populated with the screen inquiry date if
				     * Court Disposition is not null;
				     * CourtResult (using CourtResult); and
				     * CourtResultType (if result�s option =
				     * �A�, set CourtResult to �A�; otherwise
				     * null ).
				     */
				  
				    if (juvDispCode.getCategoryCode()!=null &&juvDispCode.getOptionCode()!=null &&  juvDispCode.getCategoryCode().equalsIgnoreCase("P") && (juvDispCode.getOptionCode().equalsIgnoreCase("A")||juvDispCode.getCodeNum().equalsIgnoreCase("99")))//task 183246
				    {
					ref.setDecisionType("A");
					ref.setCourtDispositionId(docResp.getDisposition());
					if (docResp.getDisposition() != null && !docResp.getDisposition().isEmpty())
					{
					    ref.setCourtDate(DateUtil.stringToDate(docResp.getCourtDate(), DateUtil.DATE_FMT_1));
					}
					ref.setCourtResultId(docResp.getCourtResult());
					ref.setPIACode("P");
					ref.setLcDate(DateUtil.getCurrentDate());
					ref.setLcTime(Calendar.getInstance().getTime());
					ref.setLcUser(PDSecurityHelper.getLogonId());
					home = new Home();
					home.bind(ref);
					conditionCheck = "first";
					break;
				    }
				}
			    }
			}
		    }
		    //second condition.
		    if (conditionCheck.isEmpty())
		    {
			Iterator<DocketEventResponseEvent> docketResponsesSecondItr = juvenileReferralEvt.getCourtSettings().iterator();
			while (docketResponsesSecondItr.hasNext())
			{
			    DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesSecondItr.next();
			    if (docResp != null)
			    {
				JuvenileDispositionCode juvDispCode = null;
				if (docResp.getCourtResult() != null && !docResp.getCourtResult().isEmpty())
				{
				    juvDispCode = JuvenileDispositionCode.find("codeAlpha",docResp.getCourtResult());
				    if (juvDispCode != null && juvDispCode.getInactiveInd() != null && !juvDispCode.getInactiveInd().equalsIgnoreCase("Y"))
				    {
					/**
					 * 2) If no other setting has Result where Category =
					 * �P� and Option = �A�, but the Result of the calendar
					 * record that is currently being processed has Category
					 * = �N�, and option = �A�, this calendar record will be
					 * used to update the referral�s the referral�s
					 * CourtDisposition; CourtDate; CourtResult; and
					 * CourtResultType.
					 */
					if (juvDispCode.getCategoryCode()!=null && juvDispCode.getOptionCode()!=null && juvDispCode.getCategoryCode().equalsIgnoreCase("N") && juvDispCode.getOptionCode().equalsIgnoreCase("A"))
					{
					    ref.setCourtDispositionId(docResp.getDisposition());
					    ref.setCourtDate(DateUtil.stringToDate(docResp.getCourtDate(), DateUtil.DATE_FMT_1));
					    ref.setCourtResultId(docResp.getCourtResult());
					    ref.setDecisionType(null);
					    ref.setLcDate(DateUtil.getCurrentDate());
					    ref.setLcTime(Calendar.getInstance().getTime());
					    ref.setLcUser(PDSecurityHelper.getLogonId());
					    home = new Home();
					    home.bind(ref);
					    conditionCheck = "second";
					    break;
					}
				    }
				}
			    }
			}
		    }
		    //fifth condition.
		    if (conditionCheck.isEmpty())
		    {
			Iterator<DocketEventResponseEvent> docketResponsesFifthItr = juvenileReferralEvt.getCourtSettings().iterator();
			while (docketResponsesFifthItr.hasNext())
			{
			    DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesFifthItr.next();
			    if (docResp != null)
			    {
				JuvenileDispositionCode juvDispCode = null;
				if (docResp.getCourtResult() != null && !docResp.getCourtResult().isEmpty())
				{
				    juvDispCode = JuvenileDispositionCode.find("codeAlpha",docResp.getCourtResult());
				    if (juvDispCode != null && juvDispCode.getInactiveInd() != null && !juvDispCode.getInactiveInd().equalsIgnoreCase("Y"))
				    {
					if (juvDispCode.getCategoryCode()!=null && juvDispCode.getOptionCode()!=null && juvDispCode.getCategoryCode().equalsIgnoreCase("B") && juvDispCode.getOptionCode().equalsIgnoreCase("R"))
					{
					    ref.setCourtDispositionId(docResp.getDisposition());
					    ref.setCourtDate(DateUtil.stringToDate(docResp.getCourtDate(), DateUtil.DATE_FMT_1));
					    ref.setCourtResultId(docResp.getCourtResult());
					    ref.setDecisionType(null);
					    ref.setLcDate(DateUtil.getCurrentDate());
					    ref.setLcTime(Calendar.getInstance().getTime());
					    ref.setLcUser(PDSecurityHelper.getLogonId());
					    home = new Home();
					    home.bind(ref);
					    conditionCheck = "fifth";
					    break;
					}
				    }
				}
			    }
			}
		    }
		    //6th condition
		    if (conditionCheck!=null && conditionCheck.isEmpty())
		    {
			/*Per Carla�s suggestion, I will add the following condition to the Update Referral Record for a deleted setting:
			    Bug 833202:  If no Result category/option match and disposition exists, update referral with associated fields*/ 
			if(juvenileReferralEvt.getCourtDisposition()!=null && !juvenileReferralEvt.getCourtDisposition().isEmpty()){
			    ref.setCourtDispositionId(juvenileReferralEvt.getCourtDisposition());
			    ref.setCourtDate(DateUtil.stringToDate(juvenileReferralEvt.getCourtDate(), DateUtil.DATE_FMT_1));
			    ref.setCourtResultId(juvenileReferralEvt.getCourtResult());
			    ref.setDecisionType(null); 
			    ref.setLcDate(DateUtil.getCurrentDate());
			    ref.setLcTime(Calendar.getInstance().getTime());
			    ref.setLcUser(PDSecurityHelper.getLogonId());
			    home = new Home();
			    home.bind(ref); 
			}
			else
			{
			    ref.setCourtDispositionId(null);
			    ref.setDecisionType(null);
			    ref.setLcDate(DateUtil.getCurrentDate());
			    ref.setLcTime(Calendar.getInstance().getTime());
			    ref.setLcUser(PDSecurityHelper.getLogonId());
			    home = new Home();
			    home.bind(ref);
			}
		    }
		    
		/*    JuvenileDispositionCode juvDispCode = null;
		    if(juvenileReferralEvt.getCourtResult()!=null && !juvenileReferralEvt.getCourtResult().isEmpty()){
			juvDispCode = JuvenileDispositionCode.find(juvenileReferralEvt.getCourtResult());
		    }
		    if (juvDispCode != null && juvDispCode.getInactiveInd() != null && !juvDispCode.getInactiveInd().equalsIgnoreCase("Y"))
		    {
			
			*//**
			 * 1) If the Result�s Category code = �P� and Option =
			 * �A�, the calendar record that is currently being
			 * processed will be used to update the referral�s
			 * CourtDisposition (using HearingDecisionCode);
			 * CourtDate should be populated with the screen inquiry
			 * date if Court Disposition is not null; CourtResult
			 * (using CourtResult); and CourtResultType (if result�s
			 * option = �A�, set CourtResult to �A�; otherwise null
			 * ).
			 *//*
			if (juvDispCode.getCategoryCode().equalsIgnoreCase("P") && juvDispCode.getOptionCode().equalsIgnoreCase("A"))
			{
			    if (ref != null)
			    {

				ref.setDecisionType("A");
				ref.setCourtDispositionId(juvenileReferralEvt.getCourtDisposition());
				if (juvenileReferralEvt.getCourtDisposition() != null && !juvenileReferralEvt.getCourtDisposition().isEmpty())
				{
				    ref.setCourtDate(DateUtil.stringToDate(juvenileReferralEvt.getCourtDate(), DateUtil.DATE_FMT_1));
				}
				ref.setCourtResultId(juvenileReferralEvt.getCourtResult());
				ref.setPIACode("P");
				ref.setLcDate(DateUtil.getCurrentDate());
				ref.setLcTime(Calendar.getInstance().getTime());
				ref.setLcUser(PDSecurityHelper.getLogonId());
				home = new Home();
				home.bind(ref);
			    }

			}

			*//**
			 * 2) If no other setting has Result where Category =
			 * �P� and Option = �A�, but the Result of the calendar
			 * record that is currently being processed has Category
			 * = �N�, and option = �A�, this calendar record will be
			 * used to update the referral�s the referral�s
			 * CourtDisposition; CourtDate; CourtResult; and
			 * CourtResultType.
			 *//*
			else if (juvDispCode.getCategoryCode().equalsIgnoreCase("N") && juvDispCode.getOptionCode().equalsIgnoreCase("A"))
			{
			    if (ref != null )
			    {
				ref.setCourtDispositionId(juvenileReferralEvt.getCourtDisposition());
				if (juvenileReferralEvt.getCourtDisposition() != null && !juvenileReferralEvt.getCourtDisposition().isEmpty())
				{
				    ref.setCourtDate(DateUtil.stringToDate(juvenileReferralEvt.getCourtDate(), DateUtil.DATE_FMT_1));
				}
				ref.setCourtResultId(juvenileReferralEvt.getCourtResult());
				ref.setDecisionType(juvenileReferralEvt.getDecisionType());
				ref.setLcDate(DateUtil.getCurrentDate());
				ref.setLcTime(Calendar.getInstance().getTime());
				ref.setLcUser(PDSecurityHelper.getLogonId());
				home = new Home();
				home.bind(ref);
			    }
			}

			*//**
			 * 3) If no other setting has Result where Category =
			 * �N� and Option = A�, but the Result of the calendar
			 * record that is currently being processed has Category
			 * code = �N�, and Option = �R�, this calendar record
			 * will be used to update the referral�s
			 * CourtDisposition; CourtDate; CourtResult; and
			 * CourtResultType
			 *//*
			else if (juvDispCode.getCategoryCode().equalsIgnoreCase("N") && juvDispCode.getOptionCode().equalsIgnoreCase("R"))
			{
			    if (ref != null )
			    {
				ref.setCourtDispositionId(juvenileReferralEvt.getCourtDisposition());
				if (juvenileReferralEvt.getCourtDisposition() != null && !juvenileReferralEvt.getCourtDisposition().isEmpty())
				{
				    ref.setCourtDate(DateUtil.stringToDate(juvenileReferralEvt.getCourtDate(), DateUtil.DATE_FMT_1));
				}
				ref.setCourtResultId(juvenileReferralEvt.getCourtResult());
				ref.setDecisionType(juvenileReferralEvt.getDecisionType());
				ref.setLcDate(DateUtil.getCurrentDate());
				ref.setLcTime(Calendar.getInstance().getTime());
				ref.setLcUser(PDSecurityHelper.getLogonId());
				home = new Home();
				home.bind(ref);
			    }
			}
			*//**
			 * 4) If no other setting has Result where Category =
			 * �N� and Option =�R�, but the Result of the calendar
			 * record that is currently being processed has Category
			 * code = �P�, and Option = �R�, this calendar record
			 * will be used to update the the referral�s
			 * CourtDisposition; CourtDate; CourtResult; and
			 * CourtResultType.
			 *//*
			else if (juvDispCode.getCategoryCode().equalsIgnoreCase("P") && juvDispCode.getOptionCode().equalsIgnoreCase("R"))
			{
			    if (ref != null )
			    {
				ref.setCourtDispositionId(juvenileReferralEvt.getCourtDisposition());
				if (juvenileReferralEvt.getCourtDisposition() != null && !juvenileReferralEvt.getCourtDisposition().isEmpty())
				{
				    ref.setCourtDate(DateUtil.stringToDate(juvenileReferralEvt.getCourtDate(), DateUtil.DATE_FMT_1));
				}
				ref.setCourtResultId(juvenileReferralEvt.getCourtResult());
				ref.setDecisionType(juvenileReferralEvt.getDecisionType());
				ref.setLcDate(DateUtil.getCurrentDate());
				ref.setLcTime(Calendar.getInstance().getTime());
				ref.setLcUser(PDSecurityHelper.getLogonId());
				home = new Home();
				home.bind(ref);
			    }
			}
			*//**
			 * 5) If no other setting has Result where Category =
			 * �P� and Option =R�, but the Result of the calendar
			 * record that is currently being processed has Category
			 * code = �B�, and Option = �R�, this calendar record
			 * will be used to update the the referral�s
			 * CourtDisposition; CourtDate; CourtResult; and
			 * CourtResultType
			 *//*
			else if (juvDispCode.getCategoryCode().equalsIgnoreCase("B") && juvDispCode.getOptionCode().equalsIgnoreCase("R"))
			{
			    if (ref != null)
			    {
				ref.setCourtDispositionId(juvenileReferralEvt.getCourtDisposition());
				if (juvenileReferralEvt.getCourtDisposition() != null && !juvenileReferralEvt.getCourtDisposition().isEmpty())
				{
				    ref.setCourtDate(DateUtil.stringToDate(juvenileReferralEvt.getCourtDate(), DateUtil.DATE_FMT_1));
				}
				ref.setCourtResultId(juvenileReferralEvt.getCourtResult());
				ref.setDecisionType(juvenileReferralEvt.getDecisionType());
				ref.setLcDate(DateUtil.getCurrentDate());
				ref.setLcTime(Calendar.getInstance().getTime());
				ref.setLcUser(PDSecurityHelper.getLogonId());
				home = new Home();
				home.bind(ref);
			    }
			}
			*//**
			 * 
			 6) If there are no settings where the Result�s
			 * Category/Option combination meets the requirements
			 * above, null the associated referral�s Disposition and
			 * Court Result Type.
			 *//*
			else
			{
			    if (ref != null)
			    {
				ref.setCourtDispositionId(null);
				ref.setDecisionType(null);
				ref.setLcDate(DateUtil.getCurrentDate());
				ref.setLcTime(Calendar.getInstance().getTime());
				ref.setLcUser(PDSecurityHelper.getLogonId());
				home = new Home();
				home.bind(ref);
			    }
			}
		    }*/
		}// more than one setting check 
	    }
	    else
	    {
		
		/*Bug 83581:  For any disposition category and option combination not addressed, update the CourtDisposition with the current setting (disposition/court decision) entry. 
		 *Update the referral record with the updated court decision entered in the current setting�in ALL instances.*/
		if(juvenileReferralEvt.getCourtDisposition()!=null && !juvenileReferralEvt.getCourtDisposition().isEmpty()){
		    ref.setCourtDispositionId(juvenileReferralEvt.getCourtDisposition());
		    updateFlag= true;
		}
		//bug fix for 122806 to null out disposition if no value selected
		else
		{
		    ref.setCourtDispositionId(null);
		    updateFlag= true;
		}
		//
		if (juvenileReferralEvt.getCourtResult() == null || (juvenileReferralEvt.getCourtResult() != null && juvenileReferralEvt.getCourtResult().isEmpty()))
		{

		    /**
		     * For now combine both. May change acccording to regina. If
		     * user has erased/removed the retrieved Result value and
		     * has not re-populated the Result, null the referral�s
		     * CourtResult (legacy: decision) and CourtResultType
		     * (legacy: decision type).
		     */
		    /**
		     * If user erased the setting�s retrieved Result, and the
		     * referral�s CourtResult (formerly Decision) is the same as
		     * the setting�s retrieved Result, null the referral�s
		     * current CourtResult and CourtResultType.
		     */
		    if (ref.getCourtResultId()!=null && ref.getCourtResultId().equals(juvenileReferralEvt.getOriginalCourtResult()))
		    {
			ref.setCourtResultId(null);
			ref.setDecisionType(null);
			updateFlag= true;
		    }

		}
		/**
		 * If the setting�s new Disposition has Option = �F�, set the
		 * referral�s CourtDate, CourtDisposition, CourtID, and
		 * CourtResult to the values in the correlating fields of the
		 * current setting: CourtDate = Court or Hearing Date;
		 * CourtDisposition = Disposition/CourtDisposition; CourtID =
		 * AssignedCourt/CourtID; and CourtResult =
		 * HearingDecisionCode/CourtResultCode).
		 */
		JuvenileDispositionCode juvDispCode =null;
		
		if(juvenileReferralEvt.getCourtDisposition()!=null && !juvenileReferralEvt.getCourtDisposition().isEmpty() && !juvenileReferralEvt.getCourtDisposition().equalsIgnoreCase(juvenileReferralEvt.getOriginalCourtDisposition())){
		    juvDispCode = JuvenileDispositionCode.find("codeAlpha",juvenileReferralEvt.getCourtDisposition());
		  // new changes for DPS code here US 184409	commenting it from this section as new US186995 wants it to be done with termination date addition	    
		    //if (juvDispCode != null && juvDispCode.getDpsCode() != null && !juvDispCode.getDpsCode().isEmpty()&& !juvDispCode.getDpsCode().equalsIgnoreCase("000")&&juvenileReferralEvt.getPetitionStatus()!=null && juvenileReferralEvt.getPetitionStatus().equalsIgnoreCase("FILED"))
		    if (juvDispCode != null && juvDispCode.getDpsCode() != null &&ref.getSeverityType()!=null&& !juvDispCode.getDpsCode().isEmpty()&& juvDispCode.getDpsCode().equalsIgnoreCase("643")&&ref.getSeverityType().equalsIgnoreCase("98"))
		    			{
        					UpdateJuvenilePetitionInformationEvent evt = (UpdateJuvenilePetitionInformationEvent) EventFactory.getInstance(JuvenileWarrantControllerServiceNames.UPDATEJUVENILEPETITIONINFORMATION);
        					evt.setJuvenileNum(juvenileReferralEvt.getJuvenileNumber());
        					//evt.setReferralNum(juvenileReferralEvt.getReferralNumber());        					
        					evt.setPetitionNum(juvenileReferralEvt.getPetitionNum());
        					evt.setDPSCode(juvDispCode.getDpsCode());
        					CompositeResponse compositeResponse = MessageUtil.postRequest(evt);
        					PetitionResponseEvent resp = (PetitionResponseEvent) MessageUtil.filterComposite(compositeResponse, PetitionResponseEvent.class);
        					Object errorResponse = MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
        					if (errorResponse != null)
        					{
        					    ErrorResponseEvent myEvt = (ErrorResponseEvent) errorResponse;					   
        					    System.out.println(myEvt.getMessage());
        					}
		    			}	
		    			 /*When the JJS_MS_REFERRAL.dispositioncode is entered and JJS_MS_REFERRAL.severitytype=98, then retrieve the JJS_COD_COURT_DECISION.codealpha for the DPScode.  
		    			 If the DPScode = 643, then locate the Filed petition for that juvenilenum/referralnum’s reopened petition and once located, 
		    			 overwrite the existing JJSMSPETITION.dpscode on the Filed petition. 
		    			 */			   
		}
		
		if (juvDispCode != null && juvDispCode.getInactiveInd() != null && !juvDispCode.getInactiveInd().equalsIgnoreCase("Y"))
		{
		    /*If the Action date has been populated/modified when Result and/or Disposition has an Option = �R� or �F�, the Action Date will populate the Referral�s Court Date.  */
		    if (juvDispCode.getOptionCode()!=null && juvDispCode.getOptionCode().equalsIgnoreCase("R") && juvenileReferralEvt.getActionDate()!=null && !juvenileReferralEvt.getActionDate().isEmpty())
		    {
			//other cases not vac
			ref.setCourtDate(DateUtil.stringToDate(juvenileReferralEvt.getActionDate(), DateUtil.DATE_FMT_1));
			ref.setCourtResultId(juvenileReferralEvt.getCourtResult());
			ref.setLcDate(DateUtil.getCurrentDate());
			ref.setLcTime(Calendar.getInstance().getTime());
			ref.setLcUser(PDSecurityHelper.getLogonId());
			updateFlag= true;
		    }
		    //section 3.4.6.17
		    if (juvDispCode.getOptionCode() != null && juvDispCode.getOptionCode().equalsIgnoreCase("F"))
		    {
			ref.setCourtDate(DateUtil.stringToDate(juvenileReferralEvt.getCourtDate(), DateUtil.DATE_FMT_1));
			ref.setCourtDispositionId(juvenileReferralEvt.getCourtDisposition());
			ref.setCourtResultId(juvenileReferralEvt.getCourtResult());
			ref.setCourtId(juvenileReferralEvt.getCourtId());
			ref.setLcDate(DateUtil.getCurrentDate());
			ref.setLcTime(Calendar.getInstance().getTime());
			ref.setLcUser(PDSecurityHelper.getLogonId());
			updateFlag= true;
			
			// Task 80795 TJJD Paper Formalized
			if( ref.getAdmitDate() != null  && ref.getFinalReleaseDate() == null){
			    
			    // set 
			}
		    }
		    /**
		     * If a court Calendar record�s Disposition Category that is
		     * �P� or �C�, then populate the Referral�s PIAstatus set
		     * %PROB.FLAG with �P.� From: (B200.FD.COURT.DECISION)
		     */		    
		    
		    /*Update Referral Record for a new setting:
			Bug 83302:  If the setting�s new Disposition/CourtDecision has Category = B and Option = N, populate the CourtDate, CourtDisposition, CourtID and CourtResult to the values in the correlating fields of the current setting: CourtDate = Court or Hearing Date; CourtDisposition = Disposition/CourtDisposition; CourtID = AssignedCourt/CourtID; and CourtResult = HearingDecisionCode/CourtResultCode).*/ 
		    if (juvDispCode.getCategoryCode()!=null && juvDispCode.getOptionCode()!=null && !juvDispCode.getOptionCode().equalsIgnoreCase("R"))//commented to remove B & N && juvDispCode.getCategoryCode().equalsIgnoreCase("B")&& juvDispCode.getOptionCode().equalsIgnoreCase("N")
		    //if (juvDispCode.getCategoryCode()!=null && juvDispCode.getOptionCode()!=null && juvDispCode.getCategoryCode().equalsIgnoreCase("B")&& juvDispCode.getOptionCode().equalsIgnoreCase("N"))
		    {
			//vac case
			ref.setCourtDate(DateUtil.stringToDate(juvenileReferralEvt.getCourtDate(), DateUtil.DATE_FMT_1));//action date other places
			ref.setCourtDispositionId(juvenileReferralEvt.getCourtDisposition());
			ref.setCourtResultId(juvenileReferralEvt.getCourtResult());
			ref.setCourtId(juvenileReferralEvt.getCourtId());
			ref.setLcDate(DateUtil.getCurrentDate());
			ref.setLcTime(Calendar.getInstance().getTime());
			ref.setLcUser(PDSecurityHelper.getLogonId());
			updateFlag= true;
			
			// Task 80795 TJJD Paper Formalized
			if( ref.getAdmitDate() != null ){
						    
			// set 
			}
		    }
		    //task 124904
		    if (juvDispCode.getSubGroupInd() != null && juvDispCode.getSubGroupInd().equalsIgnoreCase("I"))
			{
			    ref.setPDADate(DateUtil.stringToDate(juvenileReferralEvt.getCourtDate(), DateUtil.DATE_FMT_1));
			    //US 124265
			    ref.setPIACode("I");
			    updateFlag= true;
			}
		    //
		}
		/*If Calendar record�s new disposition =�OFF�, null the referral�s CourtDate (disposition.date), CourtDisposition (disposition.code) and CourtResult (decision). From (C002.CONTINUE)*/
		if (juvenileReferralEvt.getOriginalCourtDisposition() != null && juvenileReferralEvt.getCourtDisposition() != null && !juvenileReferralEvt.getOriginalCourtDisposition().equalsIgnoreCase(juvenileReferralEvt.getCourtDisposition()))
		{
		    if (juvenileReferralEvt.getCourtDisposition().equalsIgnoreCase("OFF"))
		    {
			ref.setCourtDispositionId(null);
			ref.setCourtResultId(null);
			ref.setCourtDate(null);
			ref.setCourtId(null);
			updateFlag= true;
		    }
		}
		/**
		 * If a court Calendar record�s new Result has Category = �P�
		 * and Option = �A�, the calendar record�s Result will populate
		 * the Referral�s PIAstatus with �P� (adjudicated to probation).
		 * {Sample: 381105/1050) NOTE: DDS as result code for updating
		 * referral record was not in legacy. From:
		 * (C000.FD.COURT.DECISION and C002.CONTINUE)
		 */
		JuvenileDispositionCode juvResultCode = null;
		if(juvenileReferralEvt.getCourtResult()!=null && !juvenileReferralEvt.getCourtResult().isEmpty() && !juvenileReferralEvt.getCourtResult().equalsIgnoreCase(juvenileReferralEvt.getOriginalCourtResult())){
		     juvResultCode = JuvenileDispositionCode.find("codeAlpha",juvenileReferralEvt.getCourtResult());
		}
		if (juvenileReferralEvt.getCourtResult() != null &&!juvenileReferralEvt.getCourtResult().isEmpty())
		{
		    if (juvResultCode != null && juvResultCode.getInactiveInd() != null && !juvResultCode.getInactiveInd().equalsIgnoreCase("Y"))
		    {
			/*If the Action date has been populated/modified when Result and/or Disposition has an Option = �R� or �F�, the Action Date will populate the Referral�s Court Date.  */
			if (juvResultCode.getOptionCode()!=null && juvResultCode.getOptionCode().equalsIgnoreCase("R")  && juvenileReferralEvt.getActionDate()!=null && !juvenileReferralEvt.getActionDate().isEmpty())//ask carla for CON it wont go inside as action date is null
			{
			    ref.setCourtDate(DateUtil.stringToDate(juvenileReferralEvt.getActionDate(), DateUtil.DATE_FMT_1));
			    ref.setCourtResultId(juvenileReferralEvt.getCourtResult());
			    ref.setLcDate(DateUtil.getCurrentDate());
			    ref.setLcTime(Calendar.getInstance().getTime());
			    ref.setLcUser(PDSecurityHelper.getLogonId());
			    updateFlag= true;
			}
			//bug #83343
			if (juvResultCode.getCategoryCode() != null && (juvResultCode.getCategoryCode().equalsIgnoreCase("P") && (juvResultCode.getOptionCode().equalsIgnoreCase("A")||juvResultCode.getCodeNum().equalsIgnoreCase("99"))))//task 183246
			{
			    ref.setPIACode("P");
			    ref.setDecisionType("A");
			    ref.setCourtDate(DateUtil.stringToDate(juvenileReferralEvt.getCourtDate(), DateUtil.DATE_FMT_1));
			    ref.setCourtResultId(juvenileReferralEvt.getCourtResult());
			    ref.setLcDate(DateUtil.getCurrentDate());
			    ref.setLcTime(Calendar.getInstance().getTime());
			    ref.setLcUser(PDSecurityHelper.getLogonId());
			    updateFlag= true;
			}
			/**
			 * If the setting�s new Result has Subgroup = �I�
			 * {formerly, only �INF�}, change the referral�s
			 * CaseType (PIAstatus) to �I.� NOTE: In LEGACY, only
			 * INF was used to change PIAstatus {PIAstatus has 3
			 * possible values, P (Probation), I (Informal/Deferred
			 * Adjudication) or blank/null}. From: C0002.CONTINUE
			 * with JIMS2 change
			 */
			////commented as part of US 124265 and moved to disposition section
			/*if (juvResultCode.getSubGroupInd() != null && juvResultCode.getSubGroupInd().equalsIgnoreCase("I"))
			{
			    ref.setPIACode("I");
			    updateFlag= true;
			}*/
			//bug fix for 122407			    
			if (juvResultCode.getCategoryCode() != null && (juvResultCode.getCategoryCode().equalsIgnoreCase("P") || juvResultCode.getCategoryCode().equalsIgnoreCase("C")))
			{
			    ref.setPIACode("P");
			    updateFlag= true;
			}
			//
		    }

		    /**
		     * If a court Calendar record�s new/updated Result = �TRN�,
		     * and the �Transfer To� (JJS process: Disposition field)
		     * and Action Date fields have also been populated, change
		     * the referral�s CourtID to the TransferTo value. From:
		     * (C001.OPT.CHECK)
		     */

		    //if (juvenileReferralEvt.getCourtResult() != null &&!juvenileReferralEvt.getCourtResult().isEmpty()&&juvenileReferralEvt.getCourtResult().equalsIgnoreCase("TRN") && juvenileReferralEvt.getTransferTo() != null //checkbox change
		    if (juvenileReferralEvt.getCourtResult() != null &&juvenileReferralEvt.getTransferFlag().equalsIgnoreCase("1") && juvenileReferralEvt.getTransferTo() != null //checkbox change
			    && !juvenileReferralEvt.getTransferTo().isEmpty() 
			    && juvenileReferralEvt.getActionDate() != null && !juvenileReferralEvt.getActionDate().isEmpty())
		    {
			ref.setCourtId(juvenileReferralEvt.getTransferTo()); //82071
			updateFlag= true;
		    }
		    /**
		     * If original Result for the court or detention setting
		     * record was �TRN� and user has supplied a different
		     * Result, set COURTID to the court used in the search. If
		     * Disposition was also modified, but IS not �OFF�, change:
		     * DISPOSITION.CODE to setting�s new disposition; referral�s
		     * Decision to setting�s. From: (C001.OPT.CHECK)
		     */
		    if (juvenileReferralEvt.getOriginalCourtResult()!=null && juvenileReferralEvt.getOriginalCourtResult().equalsIgnoreCase("TRN"))
		    {
			ref.setCourtId(juvenileReferralEvt.getCourtId());
			if (juvenileReferralEvt.getOriginalCourtDisposition() != null && juvenileReferralEvt.getCourtDisposition() != null
				&& !juvenileReferralEvt.getOriginalCourtDisposition().equalsIgnoreCase(juvenileReferralEvt.getCourtDisposition()))
			{
			    if (juvenileReferralEvt.getCourtDisposition()!=null && !juvenileReferralEvt.getCourtDisposition().equalsIgnoreCase("OFF"))
			    {
				ref.setCourtDispositionId(juvenileReferralEvt.getCourtDisposition());
				ref.setCourtResultId(juvenileReferralEvt.getCourtResult());
				updateFlag= true;
			    }
			}
			updateFlag= true;
		    }

		    /**
		     * bug 82071
		     */
		    if (juvenileReferralEvt.getCourtResult() != null && !juvenileReferralEvt.getCourtResult().isEmpty() && (juvenileReferralEvt.getCourtResult().equalsIgnoreCase("A/R") || juvenileReferralEvt.getCourtResult().equalsIgnoreCase("CON")))
		    {
			if(juvenileReferralEvt.getActionDate()!=null && !juvenileReferralEvt.getActionDate().isEmpty()){
			    ref.setCourtDate(DateUtil.stringToDate(juvenileReferralEvt.getActionDate(), DateUtil.DATE_FMT_1));
			    updateFlag= true;
			}
		    }
		}
		
		if(updateFlag){
		    home.bind(ref);// update  the referral
		}
	    }//ref not null
	    
	    //add new changes for DPS code here US 184409
	}
    }
}
