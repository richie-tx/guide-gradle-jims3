package ui.juvenilecase.caseplan.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.caseplan.GenerateNewCasePlanVersionEvent;
import messaging.caseplan.GetCaseplanAcknowledgementByCaseplanIdEvent;
import messaging.caseplan.GetGenerateCaseplanDetailsEvent;
import messaging.caseplan.GetGoalRuleDetailsByCaseplanIdEvent;
import messaging.caseplan.RetrieveCasePlanDocumentDataEvent;
import messaging.caseplan.SaveCaseplanDataEvent;
import messaging.caseplan.reply.CaseplanAcknowledgementResponseEvent;
import messaging.caseplan.reply.CaseplanDocJuvDetailsResponseEvent;
import messaging.caseplan.reply.GoalRuleDetailsResponseEvent;
import messaging.caseplan.reply.PlacementInfoResponseEvent;
import messaging.caseplan.reply.SaveCaseplanDataResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.interviewinfo.reply.JuvenileBenefitResponseEvent;
import messaging.juvenile.GetMedicalHealthIssueDataEvent;
import messaging.juvenile.GetMedicalMedicationDataEvent;
import messaging.juvenile.SearchJuvenileProfileCasefileListEvent;
import messaging.juvenile.reply.JuvenileContactResponseEvent;
import messaging.juvenile.reply.JuvenileHealthIssueResponseEvent;
import messaging.juvenile.reply.JuvenileMedicationResponseEvent;
import messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import messaging.juvenilecase.reply.RuleDetailResponseEvent;
import messaging.rules.GetJuvenileCasefileSupervisionRuleDetailsEvent;
import messaging.supervisionoptions.reply.VariableElementResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCasePlanControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ujac.util.BeanComparator;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.SimpleCodeTableHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.Rule;
import ui.juvenilecase.UIJuvenileCasefileSupervisionRulesHelper;
import ui.juvenilecase.UIJuvenileCaseplanHelper;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.caseplan.form.CaseplanForm;
import ui.juvenilecase.caseplan.form.CaseplanForm.GoalInfo;
import ui.juvenilecase.caseplan.form.CaseplanForm.PersonResponsible;
import ui.juvenilecase.caseplan.form.UIJuvenileContact;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.JuvenileMemberForm;
import ui.juvenilecase.medical.UIJuvenileProfileMedicalHelper;


/**
 * 
 * @author awidjaja
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class HandleCaseplanAction extends JIMSBaseAction
{
	private void createNewCaseplanFromOldOne( CaseplanForm form )
	{
		GenerateNewCasePlanVersionEvent evt = new GenerateNewCasePlanVersionEvent( ) ;
		evt.setExistingCaseplanId( form.getCurrentCaseplan( ).getCaseplanId( ) ) ;
		evt.setCasefileId( form.getCasefileId( ) ) ;

		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		dispatch.postEvent( evt ) ;
		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply( ) ;

		MessageUtil.processReturnException( compositeResponse ) ;
		UIJuvenileCaseplanHelper.fetchCaseplanDetails( form ) ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward addNewGoal( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse ) 
			throws GeneralFeedbackMessageException
	{
		CaseplanForm form = (CaseplanForm)aForm ;
		// check if adding a goal is allowed...
		if( form.getCaseplanExist( ).equals( "Y" ) )
		{
			if( form.getCurrentCaseplan( ).getStatus( ).equalsIgnoreCase( "ACKNOWLEDGED" ) )
			{
				createNewCaseplanFromOldOne( form ) ;
			}

			if( form.getCurrentCaseplan( ).getStatus( ).equalsIgnoreCase( "IN REVIEW" ) )
			{
				ActionErrors errors = new ActionErrors( ) ;
				errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionMessage( "error.incorrectState" ) ) ;
				saveErrors( aRequest, errors ) ;

				return aMapping.findForward( "cancel" ) ;
			}
		}

		form.setAction( UIConstants.CREATE ) ;
		form.setGoalInfoEditable( true ) ;
		form.setCurrentGoalInfo( new GoalInfo( ) ) ;
		
		
		// added to get residential caseplan and show domain type accordingly ERJIMS200076784
		String supervisionTypeCurrentCaseplan = form.getSupervisionType();
		String supervisionCatCurrentCaseplan = UIJuvenileCaseworkHelper.getSupCatFromType(supervisionTypeCurrentCaseplan);
		Collection coll = CodeHelper.getCodes( "GOAL_DOMAIN_TYPE", true ) ;
		
		//ADDED as a part of ER JIMS200077469
		Iterator domainTypeCodeItr = coll.iterator();
		while(domainTypeCodeItr.hasNext()){
			CodeResponseEvent domainTypeCdRespEvt = (CodeResponseEvent)domainTypeCodeItr.next();
			String currentDomainTypeStatus = domainTypeCdRespEvt.getStatus();
			//String currentDomainTypeCd = domainTypeCdRespEvt.getCode();
			//if(currentDomainTypeCd.equals(UIConstants.GOAL_DOMAIN_TYPE_CONTACT_FREQUENCY) || currentDomainTypeCd.equals(UIConstants.GOAL_DOMAIN_TYPE_FAMILY_PARTICIPATION) || currentDomainTypeCd.equals(UIConstants.GOAL_DOMAIN_TYPE_SOCIALIZATION) || currentDomainTypeCd.equals(UIConstants.GOAL_DOMAIN_TYPE_SUPPORT_SERVICES))
			//added to remove all inactive goal domain types ER 11214
			if(currentDomainTypeStatus != null && currentDomainTypeStatus.equalsIgnoreCase("Inactive"))
			{
				domainTypeCodeItr.remove();
			}
		}
		
		//----ended
		
		if(supervisionCatCurrentCaseplan !=null && !supervisionCatCurrentCaseplan.equals(UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES)){
			
			Iterator codeIter = coll.iterator();
            //String supTypeId = "";
            while(codeIter.hasNext()){
            	CodeResponseEvent respEvent = (CodeResponseEvent)codeIter.next();
                String currentCode = respEvent.getCode();
                if(currentCode.equals(UIConstants.GOAL_DOMAIN_TYPE_RECREATIONAL)){                    	
                	codeIter.remove();
                	break;
                }
            }			
		}
		form.setDormainTypeList( coll ) ;
		/*else{
			ArrayList domainTypesNonResList = new ArrayList();			
			Collection codes = CodeHelper.getCodes("GOAL_DOMAIN_TYPE");
			Iterator codeIter = codes.iterator();
            //String supTypeId = "";
            while(codeIter.hasNext()){
            	CodeResponseEvent respEvent = (CodeResponseEvent)codeIter.next();
                    String currentCode = respEvent.getCode();
                    if(!currentCode.equals(UIConstants.GOAL_DOMAIN_TYPE_RECREATIONAL)){                    	
                    	
                    	domainTypesNonResList.add(respEvent);
                    }
            }
            form.setDormainTypeList(domainTypesNonResList);
		}*/
		
		// set the values from the code table
		//Collection coll = CodeHelper.getCodes( "GOAL_DOMAIN_TYPE", true ) ;
		//form.setDormainTypeList( coll ) ;
		Collection timeFrame = CodeHelper.getCodes( "GOAL_TIMEFRAME", true ) ;
		form.setTimeFrameList( timeFrame ) ;

		// get the family members
		Collection members = UIJuvenileCaseplanHelper.getFamilyMembers( form ) ;
		Collection<PersonResponsible> contactNames =
				UIJuvenileCaseplanHelper.getContactNames( form ) ;

		// add the contactNames to the members
		if( ! contactNames.isEmpty() )
		{
			for( PersonResponsible personResp : contactNames )
			{
				members.add( personResp ) ;
			}
		}
		
		JuvenileCasefileForm myCasefileForm = (JuvenileCasefileForm)
				this.getSessionForm( aMapping, aRequest, UIJuvenileHelper.JUVENILE_CASEFILE_FORM, true ) ;

		//add probation officer also in the responsible person list ER JIMS200075816 - start 
		Name probOffcerName = myCasefileForm.getProbationOfficerName();
		
			if(probOffcerName != null){
			PersonResponsible probOfficer = new PersonResponsible();
			String str1 = probOffcerName.getCompleteFullNameLast() + "(JPO)";
			probOfficer.setName(str1.trim());
			members.add(probOfficer) ;
		}
		//add probation officer also in the responsible person list ER JIMS200075816 - end 
			
		PersonResponsible selfPR = new PersonResponsible( ) ;
		
		StringBuffer str = new StringBuffer( ) ;
		if( myCasefileForm.getJuvenileName( ).getLastName( ) != null )
			str.append( myCasefileForm.getJuvenileName( ).getLastName( ) ) ;
		if( myCasefileForm.getJuvenileName( ).getFirstName( ) != null )
			str.append( ", " + myCasefileForm.getJuvenileName( ).getFirstName( ) ) ;
		if( myCasefileForm.getJuvenileName( ).getMiddleName( ) != null )
			str.append( " " + myCasefileForm.getJuvenileName( ).getMiddleName( ) ) ;

		str.append( " (SELF)" ) ;

		selfPR.setName( str.toString( ) ) ;
		selfPR.setType( "S" ) ;
		members.add( selfPR ) ;
		Collections.sort( (ArrayList)members ) ;
		form.setPersonsResponsibleList( members ) ;
		form.setAction( "create" ) ;

		return aMapping.findForward( "addNewGoal" ) ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward addAnotherGoal( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse ) 
			throws GeneralFeedbackMessageException
	{
		return addNewGoal( aMapping, aForm, aRequest, aResponse ) ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward modifyGoal( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CaseplanForm form = (CaseplanForm)aForm ;
		form.setAction( UIConstants.MODIFY ) ;

		return aMapping.findForward( "modifyGoal" ) ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward review( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CaseplanForm form = (CaseplanForm)aForm ;
		Collection activities = UIJuvenileCaseplanHelper.getCaseplanActivitiesList( form.getCasefileId( ) ) ;
		
		form.setCpActivities( activities ) ;
		Collection goalSummaryList = UIJuvenileCaseplanHelper.getGoalSummaryList( form.getCurrentCaseplan( ).getCaseplanId( ) ) ;
		form.setJpoReviewGoalList( goalSummaryList ) ;
		form.setAction( "review" ) ;

		return aMapping.findForward( "review" ) ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward clmReview( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CaseplanForm form = (CaseplanForm)aForm ;
		form.setAction( "requestReview" ) ;
		form.setComments( "" ) ;
		
		return aMapping.findForward( "requestReview" ) ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward addPlacement( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CaseplanForm form = (CaseplanForm)aForm ;
		if( form.getCaseplanExist( ).equals( "Y" ) )
		{
			if( form.getCurrentCaseplan( ).getStatus( ).equalsIgnoreCase( "ACKNOWLEDGED" ) )
			{
				createNewCaseplanFromOldOne( form ) ;
			}
		}
		
		form.setAction( "create" ) ;
		form.setCurrentPlacementInfo( UIJuvenileCaseplanHelper.getCurrentPlacementInfo(form.getCasefileId(),form.getJuvenileNum()) ) ;
		UIJuvenileCaseplanHelper.getPlacementList( form ) ;
		// coll = CodeHelper.getCodes("FACILITY",true);
		
		return aMapping.findForward( "addPlacement" ) ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward modifyPlacement( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CaseplanForm form = (CaseplanForm)aForm ;
		if( form.getCaseplanExist( ).equals( "Y" ) )
		{
			if( form.getCurrentCaseplan( ).getStatus( ).equalsIgnoreCase( "ACKNOWLEDGED" ) )
			{
				createNewCaseplanFromOldOne( form ) ;
			}
		}
		
		form.setAction( "update" ) ;
		UIJuvenileCaseplanHelper.getPlacementList( form ) ;
		
		return aMapping.findForward( "modifyPlacement" ) ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward viewPreviousCaseplanVersions( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{

		return aMapping.findForward( "viewPreviousCaseplanVersions" ) ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @roseuid 42F79A090282
	 */
	public ActionForward saveGenerateCasePlanDetails( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		
			//--------------added to save data 
		
	        boolean update = false;
	        CaseplanForm form = (CaseplanForm) aForm;
	        //CaseplanForm.GoalInfo newGoal = form.getCurrentGoalInfo();
	        
	        SaveCaseplanDataEvent cpNwData = (SaveCaseplanDataEvent) EventFactory
	                .getInstance(JuvenileCasePlanControllerServiceNames.SAVECASEPLANDATA);
	        
	        cpNwData.setCaseplanID( form.getCurrentCaseplan( ).getCaseplanId( ) ) ;
	        
	        cpNwData.setPriorServices(form.getPriorServices());
	        cpNwData.setContactInfo(form.getContactInformation());
	        cpNwData.setSupLevelId(form.getSupervisionLevelId());
	        //added for User story 11191 Add Title IV in caseplan
	        cpNwData.setJuvFosterCareCandidate(form.isJuvFosterCareCandidate());
	        cpNwData.setSocialHistDated(DateUtil.stringToDate(form.getSocialHistDated(), "MM/dd/yyyy"));
	        cpNwData.setPsychologicalRepDated(DateUtil.stringToDate(form.getPsychologicalRepDated(), "MM/dd/yyyy"));
	        cpNwData.setRiskAssesmentDated(DateUtil.stringToDate(form.getRiskAssesmentDated(), "MM/dd/yyyy"));
	        cpNwData.setOtherDated(DateUtil.stringToDate(form.getOtherDated(), "MM/dd/yyyy"));
	        cpNwData.setExplanation(form.getExplanation());
	        cpNwData.setTitleIVEComment(form.getTitleIVEComment());
	        cpNwData.setDtDeterminationMade(DateUtil.stringToDate(form.getDtDeterminationMade(), "MM/dd/yyyy"));
	        //ended
	        //added for User Story 11146
	        cpNwData.setOthername(form.getOthername());
	        cpNwData.setChildDtNotified(DateUtil.stringToDate(form.getChildDtNotified(),"MM/dd/yyyy"));
	        cpNwData.setFamilyDtNotified(DateUtil.stringToDate(form.getFamilyDtNotified(),"MM/dd/yyyy"));
	        cpNwData.setCaregiverDtNotified(DateUtil.stringToDate(form.getCaregiverDtNotified(),"MM/dd/yyyy"));
	        cpNwData.setOtherDtNotified(DateUtil.stringToDate(form.getOtherDtNotified(),"MM/dd/yyyy"));
	        cpNwData.setChildNotifMethod(form.getChildNotifMethod());
	        cpNwData.setFamilyNotifMethod(form.getFamilyNotifMethod());
	        cpNwData.setCaregiverNotifMethod(form.getCaregiverNotifMethod());
	        cpNwData.setOtherNameNotifMethod(form.getOtherNameNotifMethod());
	        cpNwData.setChildDtOfParticipation(DateUtil.stringToDate(form.getChildDtOfParticipation(),"MM/dd/yyyy"));
	        cpNwData.setFamilyDtOfParticipation(DateUtil.stringToDate(form.getFamilyDtOfParticipation(),"MM/dd/yyyy"));
	        cpNwData.setCaregiverDtOfParticipation(DateUtil.stringToDate(form.getCaregiverDtOfParticipation(),"MM/dd/yyyy"));
	        cpNwData.setOtherNameDtOfParticipation(DateUtil.stringToDate(form.getOtherNameDtOfParticipation(),"MM/dd/yyyy"));
	        cpNwData.setChildMailedDt(DateUtil.stringToDate(form.getChildMailedDt(),"MM/dd/yyyy"));
	        cpNwData.setFamilyMailedDt(DateUtil.stringToDate(form.getFamilyMailedDt(),"MM/dd/yyyy"));
	        cpNwData.setCaregiverMailedDt(DateUtil.stringToDate(form.getCaregiverMailedDt(),"MM/dd/yyyy"));
	        cpNwData.setOtherNameMailedDt(DateUtil.stringToDate(form.getOtherNameMailedDt(),"MM/dd/yyyy"));
	        
	        //ended
	        String casefileId = form.getCasefileId();
	        if (casefileId == null || casefileId.equals(""))
	        {
	            casefileId = UIJuvenileCaseworkHelper.getHeaderForm(aRequest).getSupervisionNum();
	        }
	        cpNwData.setSupervisionNumber(casefileId);
	        
	
	        CompositeResponse compositeResponse = MessageUtil.postRequest(cpNwData);
	        
	        Object errorResponse = MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
	        if (errorResponse != null)
	        {
	            ErrorResponseEvent myEvt = (ErrorResponseEvent) errorResponse;
	            try {
					handleFatalUnexpectedException(myEvt.getMessage());
				} catch (GeneralFeedbackMessageException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }	
	        return aMapping.findForward( "saveCaseplan" ) ;
		//Ended to save data 
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward generateCaseplan( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		
		//--------------added to save data 
		
		saveGenerateCasePlanDetails(aMapping, aForm, aRequest,  aResponse);		
		//Ended to save data 
		CaseplanForm form = (CaseplanForm)aForm ;
		form.clearMedicalContact();
		UIJuvenileCaseworkHelper.populateJuvenileCasefileForm( UIJuvenileCaseworkHelper.getHeaderForm( aRequest, true ), form.getCasefileId( ) ) ;
		RetrieveCasePlanDocumentDataEvent request = (RetrieveCasePlanDocumentDataEvent)
				EventFactory.getInstance( JuvenileCasePlanControllerServiceNames.RETRIEVECASEPLANDOCUMENTDATA ) ;
		request.setCasefileID( form.getCasefileId( ) ) ;
		request.setCaseplanID( form.getCurrentCaseplan( ).getCaseplanId( ) ) ;
		request.setJuvenileNum( form.getJuvenileNum( ) ) ;
		request.setResidential( form.isResidential( ) ) ;
		
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		dispatch.postEvent( request ) ;

		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply( ) ;
		MessageUtil.processReturnException( compositeResponse ) ;
		CaseplanDocJuvDetailsResponseEvent juvDetails = (CaseplanDocJuvDetailsResponseEvent)
				MessageUtil.filterComposite( compositeResponse, CaseplanDocJuvDetailsResponseEvent.class ) ;

		if( !form.isResidential( ) )
		{
			ArrayList<JuvenileBenefitResponseEvent> juvenileBenefitREs = (ArrayList)
					MessageUtil.compositeToCollection( compositeResponse, JuvenileBenefitResponseEvent.class ) ;
			ArrayList juvenileBenefits = new ArrayList( ) ;
			for( JuvenileBenefitResponseEvent re : juvenileBenefitREs )
			{
				JuvenileMemberForm.MemberBenefit memberBenefit = UIJuvenileHelper.getJuvMemberFormMemberBenefitFROMJuvenileBenefitRespEvt( re ) ;
				juvenileBenefits.add( memberBenefit ) ;
			}

			form.setTypesOfMedicalCoverage( juvenileBenefits ) ;
		}

		PlacementInfoResponseEvent placementInfo = (PlacementInfoResponseEvent)MessageUtil.filterComposite( compositeResponse, PlacementInfoResponseEvent.class ) ;

		ArrayList<JuvenileContactResponseEvent> juvenileContactList = (ArrayList)MessageUtil.compositeToCollection( compositeResponse, JuvenileContactResponseEvent.class ) ;
		ArrayList juvenileSchoolHistory = (ArrayList)MessageUtil.compositeToCollection( compositeResponse, JuvenileSchoolHistoryResponseEvent.class ) ;
		form.setJuvDetails( juvDetails ) ;
		
	    
		//form.setGoalDetailsList(goalDetailsList);
		if( placementInfo == null )
		{
			form.setPlacementInfo( new PlacementInfoResponseEvent( ) ) ;
		}
		else
		{
			form.setPlacementInfo( placementInfo ) ;
		}
		
		if( juvenileContactList != null )
		{
			for( JuvenileContactResponseEvent jcre : juvenileContactList )
			{
				if( jcre.getRelationshipId( ).equals( "DN" ) )
				{
					form.setDentistContact( new UIJuvenileContact( jcre ) ) ;
				}
				else if( jcre.getRelationshipId( ).equals( "DR" ) )
				{
					form.setMedicalContact( new UIJuvenileContact( jcre ) ) ;
				}
			}
		}

		// Getting all the goals, as well as rules associated to this Caseplan
		GetGoalRuleDetailsByCaseplanIdEvent evt = (GetGoalRuleDetailsByCaseplanIdEvent)
				EventFactory.getInstance( JuvenileCasePlanControllerServiceNames.GETGOALRULEDETAILSBYCASEPLANID ) ;
		evt.setCaseplanId( form.getCurrentCaseplan( ).getCaseplanId( ) ) ;
		CompositeResponse compResp = postRequestEvent( evt ) ;
		Collection<GoalRuleDetailsResponseEvent> goalRuleDetails = (Collection)
				MessageUtil.compositeToCollection( compResp, GoalRuleDetailsResponseEvent.class ) ;

		// use a map to filter out duplicated data, due to flattened raw data
		Map goalInfoMap = new HashMap( ) ;

		// First iteration is to sort relationship between goal-rule
		for( GoalRuleDetailsResponseEvent rawData : goalRuleDetails )
		{
			GoalInfo goal = (GoalInfo)goalInfoMap.get( rawData.getCaseGoalId( ) ) ;

			if( goal == null )
			{
				goal = new GoalInfo( ) ;
				goal.setGoalId( rawData.getCaseGoalId( ) ) ;
				goal.setGoal( trimTag(rawData.getGoalDesc( ) ) ) ;
				goal.setTimeFrameCd( rawData.getGoalTimeFrameCd( ) ) ;
				List personResponsibleList = new ArrayList( ) ;
				personResponsibleList.add( rawData.getResponsibleName( ) ) ;
				goal.setIntervention( trimTag(rawData.getIntervention( ) ) ); //added for ER JIMS200075816 
				goal.setPersonsResponsibleDisplay( personResponsibleList ) ;
				goal.setDomainTypeCd( rawData.getGoalDomainTypeCd( ) ) ;
				goal.setStatusCd( rawData.getGoalStatusCd( ) ) ;
				goalInfoMap.put( goal.getGoalId( ), goal ) ;
			}

			if( !goal.getPersonsResponsibleDisplay( ).contains( rawData.getResponsibleName( ) ) )
			{
				goal.getPersonsResponsibleDisplay( ).add( rawData.getResponsibleName( ) ) ;
			}

			if( rawData.getSupRuleId( ) != null )
			{
				List<Rule> rules = (List)goal.getAssociatedRules( ) ;
				if( rules == null )
				{
					rules = new ArrayList( ) ;
					goal.setAssociatedRules( rules ) ;
				}

				Rule rule = null ;
				for( Rule eachRule : rules )
				{
					if( eachRule.getRuleId( ).equals( rawData.getSupRuleId( ) ) )
					{
						rule = eachRule ;
					}
				}
				
				if( rule == null )
				{
					rule = new Rule( ) ;
					rule.setRuleId( rawData.getSupRuleId( ) ) ;
					rule.setUnformattedDesc( rawData.getUnformattedDesc( ) ) ;
					rule.setResolvedDesc( rawData.getResolveDesc( ) ) ;
					if( notNullNotEmptyString( rawData.getRuleMonitorFreqId( ) ) )
					{
						rule.setRuleMonitorFreqDesc( SimpleCodeTableHelper.getDescrByCode( 
								PDCodeTableConstants.MONITOR_FREQUENCY, rawData.getRuleMonitorFreqId( ) ) ) ;
					}
					rules.add( rule ) ;
				}
			}
		}

		Collection goals = goalInfoMap.values( ) ;
		for( Iterator goalsIter = goals.iterator( ); goalsIter.hasNext( ); )
		{
			GoalInfo goal = (GoalInfo)goalsIter.next( ) ;
			Collection associatedRules = goal.getAssociatedRules( ) ;
			for( Iterator rulesIter = associatedRules.iterator( ); rulesIter.hasNext( ); )
			{
				Rule eachRule = (Rule)rulesIter.next( ) ;
				// Added as per #JIMS200051421
				if( goal.getDomainTypeCd( ) != null && 
						goal.getDomainTypeCd( ).equals( CaseplanForm.CONTACT_FREQUENCY ) )
					renderRuleLiteral( eachRule ) ;
			}
		}
		
		Collection tmp = goalInfoMap.values( ) ;
		ArrayList tmp2 = new ArrayList( ) ;
		tmp2.addAll( tmp ) ;
		form.setGoalDetailsList( tmp2 ) ;
		
		ArrayList medicationList = new ArrayList( ) ;
		ArrayList activeHealthIssuesList = new ArrayList(); 


		//Collection healthIssueList = UIJuvenileProfileMedicalHelper.getHealthIssuesList( form.getJuvenileNum( ) ) ;
		Collection<JuvenileHealthIssueResponseEvent> healthIssueList = 
			UIJuvenileProfileMedicalHelper.getHealthIssuesList( form.getJuvenileNum( ) ) ;
	for( JuvenileHealthIssueResponseEvent eachHealthIssue : healthIssueList )
	{
		GetMedicalHealthIssueDataEvent getHealthIssueData = new GetMedicalHealthIssueDataEvent( ) ;
		getHealthIssueData.setHealthIssueId( eachHealthIssue.getHealthIssueId()) ;
		CompositeResponse healthIssueResp = postRequestEvent( getHealthIssueData ) ;

		JuvenileHealthIssueResponseEvent healthIssueDetail = (JuvenileHealthIssueResponseEvent)MessageUtil.filterComposite( healthIssueResp, JuvenileHealthIssueResponseEvent.class ) ;
		//healthIssueDetail.setMedicationName( eachHealthIssue.getMedicationName( ) ) ;
		String currentHealthIssueDetail = healthIssueDetail.getHealthStatusId();		
		if(currentHealthIssueDetail !=null && currentHealthIssueDetail.equals(UIConstants.CURRENT_HEALTH_ISSUE_ACTIVE)){ //added to print only active health Issues ER JIMS200076774
		String issue = SimpleCodeTableHelper.getDescrByCode("HEALTH_ISSUE", healthIssueDetail.getIssueId());
		healthIssueDetail.setIssueId(issue);
		activeHealthIssuesList.add( healthIssueDetail ) ;
		}
	}
		
		form.setHealthIssueList(activeHealthIssuesList);	
		Collection<JuvenileMedicationResponseEvent> medicationIssueList = 
				UIJuvenileProfileMedicalHelper.getMedicationList( form.getJuvenileNum( ) ) ;
		for( JuvenileMedicationResponseEvent eachMedicationIssue : medicationIssueList )
		{
			GetMedicalMedicationDataEvent getMedicationData = new GetMedicalMedicationDataEvent( ) ;
			getMedicationData.setMedicationId( eachMedicationIssue.getMedicationId( ) ) ;
			CompositeResponse medicationResp = postRequestEvent( getMedicationData ) ;

			JuvenileMedicationResponseEvent medicationDetail = (JuvenileMedicationResponseEvent)MessageUtil.filterComposite( medicationResp, JuvenileMedicationResponseEvent.class ) ;
			medicationDetail.setMedicationName( eachMedicationIssue.getMedicationName( ) ) ;
			if(medicationDetail.getCurrentlyTakingMedication().equals(UIConstants.CURRENTLY_TAKING_MEDICATION_YES)){ //added to print only currently taking medicine
				String medicationReason = medicationDetail.getReasonForMedication();
				//remove history details and show NOT APPLICABLE in the report ERJIMS200076779
				if(medicationReason != null && medicationReason.length()>0){
					int startIndex = medicationReason.indexOf("[");
					//int endIndex = medicationReason.indexOf("]");
					if(startIndex != -1){
						medicationReason = medicationReason.substring(0, startIndex);
						if(medicationReason != null && medicationReason.trim().length()>0){
							medicationDetail.setReasonForMedication(medicationReason);
						}else if(medicationReason.trim().length()== 0){
							medicationDetail.setReasonForMedication("N/A");
						}
					}
				}
				medicationList.add( medicationDetail ) ;
			}
		}		

		form.setMedicationList( medicationList ) ;
		if (juvenileSchoolHistory.size() > 0 )
		{	
			JuvenileSchoolHistoryResponseEvent jsrEvent = (JuvenileSchoolHistoryResponseEvent)juvenileSchoolHistory.get(0);
			if (jsrEvent != null)
			{
// load street address
				String tempStr = UIConstants.EMPTY_STRING;
				if (jsrEvent.getSchoolStreetNum() != null)
				{	
				   tempStr += jsrEvent.getSchoolStreetNum() + " ";
				}
				if (jsrEvent.getSchoolStreetName() != null)
				{	
				   tempStr += jsrEvent.getSchoolStreetName() + " ";
				}
				if (jsrEvent.getSchoolStreet() != null)
				{	
				   tempStr += jsrEvent.getSchoolStreet();
				}
				jsrEvent.setSchoolAddress(tempStr);
// load city and state				
				tempStr = UIConstants.EMPTY_STRING;
				if (jsrEvent.getSchoolCity() != null)
				{	
				   tempStr = jsrEvent.getSchoolCity();
					if (!"".equals(tempStr))
					{	
						tempStr += ", ";
					}
				}
				if (jsrEvent.getSchoolState() != null)
				{	
					tempStr += " " + jsrEvent.getSchoolState();
				}
				jsrEvent.setSchoolCityState(tempStr);
			
				tempStr = null;
			}
		}	
		form.setJuvenileSchoolHistory( juvenileSchoolHistory ) ;
		
		//added for Title IV Candidacy: to show checked and unchecked checkbox in the report
		
		String tempJuvFosterCareCandidate = form.getJuvFosterCareCandidateStr();
		if(tempJuvFosterCareCandidate != null && tempJuvFosterCareCandidate.equalsIgnoreCase("No")){
			form.setJuvFosterCareCandidateStr2("Yes");
		}else{
			form.setJuvFosterCareCandidateStr2("No");
		}
		return aMapping.findForward( "generateCaseplan" ) ;
	}

	/**
	 * @param rawRule
	 * @return #JIMS200051421
	 */
	private void renderRuleLiteral( Rule rawRule )
	{
		Rule rule = null ;
		if( notNullNotEmptyString( rawRule.getRuleId( ) ) )
		{
			GetJuvenileCasefileSupervisionRuleDetailsEvent event = new GetJuvenileCasefileSupervisionRuleDetailsEvent( ) ;
			event.setRuleId( rawRule.getRuleId( ) ) ;
			
			IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
			dispatch.postEvent( event ) ;
			CompositeResponse response = (CompositeResponse)dispatch.getReply( ) ;
			RuleDetailResponseEvent ruleResponse = (RuleDetailResponseEvent)
					MessageUtil.filterComposite( response, RuleDetailResponseEvent.class ) ;
			// Populate rule found
			if( ruleResponse != null )
			{
				rule = UIJuvenileCasefileSupervisionRulesHelper.getDetailRule( ruleResponse ) ;
				Iterator ite = rule.getVariables( ).iterator( ) ;
				while( ite.hasNext( ) )
				{
					rawRule.addVariable( (VariableElementResponseEvent)ite.next( ) ) ;
				}
				rawRule.setRuleLiteral( rule.getRuleLiteral( ) ) ; // Setting rule
																														// literal.
				rawRule.setCompletionStatusId( rule.getCompletionStatusId( ) ) ;
			}
		}
	}

	/*
	 * @param str
	 * @return
	 */
	private boolean notNullNotEmptyString( String str )
	{
		return( str != null && ( !str.trim( ).equals( "" ) ) ) ;
	}
	
	/*
	 * @param str
	 * @return
	 */
	// note user can update/add text after a tag [] 
	private String trimTag( String str )
	{
		boolean addChar = true;
		char val = ' ';
		char val2 = ' ';
		String temp = "";
		if (str != null)
		{
			for (int x=0; x<str.length(); x++)
			{
				val = str.charAt(x);
				if (val == '['){
					val2 = str.charAt(x + 1);
					if (val2 == '0' || val2 == '1')
					addChar = false;
				}
				if (addChar){
					temp += val;
				}
				if (val == ']'){
					addChar = true;
				}
			}
			str = temp;
			
		}
		temp = null;
		return str ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward displaySummary( ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CaseplanForm form = (CaseplanForm)aForm ;
        
		String [ ] persons = form.getCurrentGoalInfo( ).getPersonsResponsibleIds( ) ;
		String [ ] buf = new String [ persons.length ] ;
		for( int i = 0; i < persons.length; i++ )
		{
			StringTokenizer strtok = new StringTokenizer( persons[ i ], "(" ) ;
			int count = 0 ;
			while( strtok.hasMoreTokens( ) )
			{
				String str = strtok.nextToken( ) ;
				if( count++ == 0 )
					buf[ i ] = str ;
			}
		}
		form.getCurrentGoalInfo( ).setPersonsResponsibleIds( buf ) ;
		form.getCurrentGoalInfo( ).setDomainTypeStr( UIJuvenileCaseplanHelper.getDomainType( form ) ) ;
		form.getCurrentGoalInfo( ).setTimeFrameStr( UIJuvenileCaseplanHelper.getTimeFrame( form ) ) ;
		
		
		aRequest.setAttribute( "status", "summary" ) ;

		return aMapping.findForward( UIConstants.SUMMARY ) ;
	}

	/*
	 * (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#cancel(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward cancel( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CaseplanForm form = (CaseplanForm)aForm ;
		String forwardString = UIConstants.CANCEL ;
		if( form.isJuvProfile( ) )
		{
			forwardString = "profileCancel" ;
		}

		ActionForward forward = aMapping.findForward( forwardString ) ;
		return forward ;
	}

	/*
	 * (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#back(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward back( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return aMapping.findForward( UIConstants.BACK ) ;
	}

	/*
	 * (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#back(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward copy( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CaseplanForm cpForm = (CaseplanForm)aForm;
		cpForm.setAction("copy");
		cpForm.setSubmitAction("");
		cpForm.setSupervisionTypeDescription(CodeHelper.getCodeDescription("SUPERVISION_TYPE", cpForm.getSupervisionType()));
		SearchJuvenileProfileCasefileListEvent casefileList = (SearchJuvenileProfileCasefileListEvent)
			EventFactory.getInstance(JuvenileControllerServiceNames.SEARCHJUVENILEPROFILECASEFILELIST);
		casefileList.setJuvenileId(cpForm.getJuvenileNum());
		IDispatch dispatch=EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(casefileList);	
		CompositeResponse response=(CompositeResponse)dispatch.getReply();
		Collection <JuvenileProfileCasefileListResponseEvent> listResponse = (Collection)MessageUtil.compositeToCollection(response, JuvenileProfileCasefileListResponseEvent.class);
		Iterator casefilesIter = listResponse.iterator();
		ArrayList casefiles = new ArrayList();
		String currentCasefileSupervisionCategory = UIConstants.EMPTY_STRING;
		
		while(casefilesIter.hasNext())
		{
			JuvenileProfileCasefileListResponseEvent resp = (JuvenileProfileCasefileListResponseEvent)casefilesIter.next();			
			if (resp.getSupervisionNum().equals(cpForm.getCasefileId() ) ) {
				currentCasefileSupervisionCategory = resp.getSupervisionCategory();
			}
			if(resp.getCaseStatus().equals("ACTIVE") &&(!resp.getSupervisionNum().equals(cpForm.getCasefileId()))){		
				casefiles.add(resp);
				if(listResponse.size()==1)
					cpForm.setSelectedValue(resp.getSupervisionNum());
				//check if caseplan is approved
				if((cpForm.isResidential() && resp.getSupervisionCategory().equals(UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES))
						||(!cpForm.isResidential()&& !resp.getSupervisionCategory().equals(UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES)))				
					cpForm.setFound(true);
					
			}
			if(cpForm.isFound())
				cpForm.setSubmitAction("copy");
		
		}
		// narrow results so supervison type is same category as current casefile (value in jsp heading)
		ArrayList temp = new ArrayList();
		int match=0;
		if (casefiles.size() > 0 && !UIConstants.EMPTY_STRING.equals(currentCasefileSupervisionCategory) ){
			for (int x=0; x<casefiles.size(); x++) {
				JuvenileProfileCasefileListResponseEvent resp2 = (JuvenileProfileCasefileListResponseEvent) casefiles.get(x);
				if (currentCasefileSupervisionCategory.equals(resp2.getSupervisionCategory() ) ) {
					temp.add(resp2);
					if(match==0)
						cpForm.setSelectedValue(resp2.getSupervisionNum());
					match++;
				}
			}
		}
		Collections.sort((ArrayList)temp);
		cpForm.setCasefiles(temp);
		casefiles = null;
		currentCasefileSupervisionCategory = null;
		return aMapping.findForward( UIConstants.COPY) ;
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward jpoTaskReview( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CaseplanForm form = (CaseplanForm)aForm ;
		String caseFileId = (String)aRequest.getParameter( "casefileID" ) ;
		String casePlanId = (String)aRequest.getParameter( "caseplanID" ) ;
		String juvenileId = (String)aRequest.getParameter( "juvenileID" ) ;

		form.setCasefileId( caseFileId ) ;
		form.setJuvenileNum( juvenileId ) ;
		form.getCurrentCaseplan( ).setCaseplanId( casePlanId ) ;
		
		UIJuvenileCaseworkHelper.populateJuvenileCasefileForm( UIJuvenileCaseworkHelper.getHeaderForm( aRequest, true ), caseFileId ) ;
		Collection activities = UIJuvenileCaseplanHelper.getCaseplanActivitiesList( caseFileId ) ;
		form.setCpActivities( activities ) ;
		
		Collection goalSummaryList = UIJuvenileCaseplanHelper.getGoalSummaryList( casePlanId ) ;
		form.setJpoReviewGoalList( goalSummaryList ) ;
		form.setAction( "review" ) ;
		
		return aMapping.findForward( "review" ) ;
	}
	
	/*
	 * (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#back(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward submit( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return aMapping.findForward( UIConstants.SUBMIT ) ;
	}
	
	/*
	 * (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#back(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward finish( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return aMapping.findForward( UIConstants.FINISH ) ;
	}

	/*
	 * (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#back(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward backToCaseplan( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CaseplanForm cpForm=(CaseplanForm)aForm;
		cpForm.setAction(UIConstants.VIEW_DETAIL);
		return aMapping.findForward( "backToCaseplan" ) ;
	}
	
	public ActionForward addAck(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		CaseplanForm cpForm = (CaseplanForm) aForm;
		String caseplanId=cpForm.getCurrentCaseplan().getCaseplanId();
	   if (caseplanId !=null && caseplanId != "")
        {
			//get previous acknowledgements
			GetCaseplanAcknowledgementByCaseplanIdEvent ackEvent = (GetCaseplanAcknowledgementByCaseplanIdEvent)EventFactory.getInstance(JuvenileCasePlanControllerServiceNames.GETCASEPLANACKNOWLEDGEMENTBYCASEPLANID);
			ackEvent.setCaseplanId(cpForm.getCurrentCaseplan().getCaseplanId());
			IDispatch dispatch=EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(ackEvent);	
			CompositeResponse response=(CompositeResponse)dispatch.getReply();
			Collection <CaseplanAcknowledgementResponseEvent> listResponse = (Collection)MessageUtil.compositeToCollection(response, CaseplanAcknowledgementResponseEvent.class);
		
			 List sortedList = new ArrayList(listResponse);
			  ArrayList sortFields = new ArrayList();
	          sortFields.add(new ReverseComparator(new BeanComparator("entryDate")));   
	          sortFields.add(new ReverseComparator(new BeanComparator("createDate")));
	          ComparatorChain multiSort = new ComparatorChain(sortFields);
	          Collections.sort(sortedList, multiSort);
			//Collections.sort((List)listResponse);
			cpForm.setPreviousAcknowledgements(sortedList);
			cpForm.setStatus("");
			cpForm.getCurrentJuvenileAcknowledgment().setExplanation("");
			cpForm.getCurrentGuardianAcknowledgment().setExplanation("");
			cpForm.getCurrentJuvenileAcknowledgment().setSignatureStatus("");
			cpForm.getCurrentGuardianAcknowledgment().setSignatureStatus("");
			cpForm.getCurrentJuvenileAcknowledgment().setEntryDateStr(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
			cpForm.getCurrentGuardianAcknowledgment().setEntryDateStr(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
        }
	   else{
   			cpForm.setCaseplanExist("N");
   		}
		return aMapping.findForward("addAcknowledgement");
	}

	
	 public ActionForward updateCaseplan( 
				ActionMapping aMapping,
				 ActionForm aForm,
				 HttpServletRequest aRequest, 
				HttpServletResponse aResponse)
	    {	    	

	    CaseplanForm form = (CaseplanForm) aForm;
	    form.setPriorServices(null); 
	    form.setContactInformation(null);
	    Collection coll = CodeHelper.getCodes( "JUV_SUPERVISION_LEVEL", true ) ;
		form.setSupervisionLevelList(coll);
		//added for User story 11191 Add Title IV in caseplan
		form.setJuvFosterCareCandidateStr(null);
		form.setPsychologicalRepDated(null);
		form.setRiskAssesmentDated(null);
		form.setOtherDated(null);
		form.setExplanation(null);
		form.setSocialHistDated(null);
		form.setTitleIVEComment(null);
	    
	    
	    GetGenerateCaseplanDetailsEvent evt = new GetGenerateCaseplanDetailsEvent();
	    evt.setCaseplanId(form.getCurrentCaseplan().getCaseplanId()); 
	    evt.setCasefileId(form.getCasefileId()); 
	    
	    CompositeResponse replyEvent = MessageUtil.postRequest(evt);

		        
	    SaveCaseplanDataResponseEvent respEvt = 
	    	(SaveCaseplanDataResponseEvent)MessageUtil.filterComposite(replyEvent,SaveCaseplanDataResponseEvent.class);
		
		

	    form.setPriorServices(respEvt.getPriorServices()); 
	    form.setContactInformation(respEvt.getContactInfo());
	    form.setSupervisionLevelId(respEvt.getSupLevelId());
	    form.setJuvFosterCareCandidate(respEvt.isJuvFosterCareCandidate());
	    form.setPsychologicalRepDated(respEvt.getPsychologicalRepDated());
	    form.setRiskAssesmentDated(respEvt.getRiskAssesmentDated());
	    form.setOtherDated(respEvt.getOtherDated());
	    form.setExplanation(respEvt.getExplanation());
	    form.setSocialHistDated(respEvt.getSocialHistDated());
	    form.setTitleIVEComment(respEvt.getTitleIVEComment());
	    form.setDtDeterminationMade(respEvt.getDtDeterminationMade());
	    return aMapping.findForward("updateCaseplan");
	}

	    
	    /**
	     * @param aMapping
	     * @param aForm
	     * @param aRequest
	     * @param aResponse
	     * @return ActionForward
	     * @roseuid 42F79A090282
	     */
	    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
	            HttpServletResponse aResponse)
		{
	        CaseplanForm form = (CaseplanForm) aForm;
	        form.setSecondaryAction(UIConstants.EMPTY_STRING);
	        
	    
	        form.setSecondaryAction("CASEPLANFLOW");        
	        aRequest.setAttribute("status", "summary");
	        form.setCaseplanInfoFrmDb( "notDb" );
	        
	        return aMapping.findForward("next");
	    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping( Map keyMap )
	{
		keyMap.put( "button.addNewGoal", "addNewGoal" ) ;
		keyMap.put( "button.copyCaseplan", "copy" ) ;
		keyMap.put( "button.addAnotherGoal", "addAnotherGoal" ) ;
		keyMap.put( "button.modifyGoal", "modifyGoal" ) ;
		keyMap.put( "button.jpoReview", "review" ) ;
		keyMap.put( "button.requestCLMReview", "clmReview" ) ;
		keyMap.put( "button.addPlacement", "addPlacement" ) ;
		keyMap.put( "button.modifyPlacement", "modifyPlacement" ) ;
		keyMap.put( "button.viewPreviousCaseplanVersions", "viewPreviousCaseplanVersions" ) ;
		keyMap.put( "button.generateCaseplan", "generateCaseplan" ) ;
		keyMap.put( "button.addCaseplanAcknowledgement", "addAck" ) ;
		keyMap.put( "button.link", "jpoTaskReview" ) ;
		//keyMap.put( "button.next", "displaySummary" ) ;
		keyMap.put( "button.cancel", "cancel" ) ;
		keyMap.put( "button.back", "back" ) ; 
		keyMap.put( "button.submit", "submit" ) ;
		keyMap.put( "button.finish", "finish" ) ;
		keyMap.put( "button.backToCaseplanDetails", "backToCaseplan" ) ;
		keyMap.put( "button.update", "updateCaseplan" ) ;
		keyMap.put( "button.nextFrmTab", "next" ) ;
		keyMap.put( "button.submitCaseplan", "saveGenerateCasePlanDetails" ) ;
	}

}