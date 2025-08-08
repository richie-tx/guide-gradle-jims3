//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\casefile\\action\\DisplayCommonAppCourtOrderUpdateAction.java

package ui.juvenilecase.casefile.action;

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
import javax.servlet.http.HttpSession;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.GetJuvenileCourtsEvent;
import messaging.codetable.GetJuvenileFacilityByCodeEvent;
import messaging.codetable.GetNonDetentionJuvenileFacilitiesEvent;
import messaging.codetable.criminal.reply.JuvenileCourtResponseEvent;
import messaging.codetable.criminal.reply.JuvenileFacilityResponseEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenilecase.GetJuvenileDetentionFacilitiesEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileCaseControllerServiceNames;
import naming.UIConstants;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ujac.util.BeanComparator;

import pd.juvenilecase.JJSCourt;
import pd.juvenilewarrant.JJSPetition;
import ui.action.JIMSBaseAction;
import ui.common.GradeLevelComparator;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.UIJuvenileCasefileClosingHelper;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.casefile.form.CasefileClosingForm;
import ui.juvenilecase.casefile.form.CommonAppForm;
import ui.juvenilecase.form.JuvenileCasefileForm;

/*
 * 
 */
public class DisplayCommonAppCourtOrderUpdateAction extends JIMSBaseAction
{
	private final int			DAY_CONVERSION						= (1000 * 3600 * 24);
	private final String	MONTH_DAY_YEAR_STR				= "MM/dd/yyyy";
	private final String	DAYS_STR									= " Days";
	private final String	CASEFILE_CLOSING_FORM_STR	= "casefileClosingForm";
	private final String	COMMON_APP_FORM_STR				= "commonAppForm";

	/**
	 * @roseuid 46D42214032D
	 */
	public DisplayCommonAppCourtOrderUpdateAction()
	{
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 46D41EAD01C0
	 */
	public ActionForward link( ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CommonAppForm commonAppForm = (CommonAppForm)aForm;
		JuvenileCasefileForm casefileForm = UIJuvenileHelper.getJuvenileCasefileForm( aRequest, true );

		commonAppForm.setAction( UIConstants.EMPTY_STRING );

		commonAppForm.setJuvenileCourts( getJuvenileCourts() );
		commonAppForm.getCourtOrder().setGangRelated( "Unknown" );
		if(commonAppForm.getCurrentOffenses()==null || commonAppForm.getCurrentOffenses().isEmpty()){
			Collection<JJSOffenseResponseEvent>  coll = getCurrentOffenses( aRequest.getSession() );
   			commonAppForm.setCurrentOffenses( coll );
		}
		commonAppForm.setNumericTime(getSortedTime());

		if( casefileForm != null )
		{
			commonAppForm.setCaseStatus( casefileForm.getCaseStatusId() );
		}

		// get the referrals
		CasefileClosingForm closingForm = (CasefileClosingForm)aRequest.getSession().getAttribute( CASEFILE_CLOSING_FORM_STR );
		if( closingForm != null )
		{
			if( closingForm.getReferrals() != null )
			{
				commonAppForm.setDetentionFacilities( getJuvenileDetentionFacilities( closingForm.getReferrals(), closingForm.getJuvenileNum() ) );
			}
			
			commonAppForm.setCasefilePostAdjCommOrRes( false ) ;

			{ Collection coll = UIJuvenileCasefileClosingHelper.getMostSeriousOffenseForCurrentCasefile( 
						closingForm.getJuvenileNum(), casefileForm, commonAppForm ) ;
				commonAppForm.setControllingReferrals( coll ) ;
			}

			{ Collection coll = UIJuvenileCasefileClosingHelper.getMostSeriousOffensesForAllCasefiles( 
						closingForm.getJuvenileNum(), casefileForm, commonAppForm ) ;
				commonAppForm.setMostSeriousOffenses( coll );
				
				String caseStatus = casefileForm.getCaseStatus() ;
				if( ! caseStatus.equals( UIConstants.CASEFILE_CASE_STATUS_ACTIVE_DESCRIPTION ) &&
						! caseStatus.equals( UIConstants.CASEFILE_CASE_STATUS_PENDING_DESCRIPTION ))
				{
					
					//Might have to clear the following two
					//commonAppForm.getCourtOrder().setOffenseLevelId(null);
					//commonAppForm.getCourtOrder().setOffenseLevel(null);
					
					//Gets Controlling Referral with Severity Level and sets it on commonAppForm
					String controllingReferral = closingForm.getControllingReferral();
					commonAppForm.setControllingReferralFromClosing( controllingReferral ) ;
					
					//Removes the 
					String selectedControllingReferral = controllingReferral;
					//A dash should always separate the controlling referral and it's severity
					int index = selectedControllingReferral.indexOf("-");
					
					//If a dash is found, remove severity and whitespaces
					if (index != -1) {
						selectedControllingReferral = selectedControllingReferral.substring(0, index);
						selectedControllingReferral = selectedControllingReferral.trim();
					//If dash is not found, it should mean a severity was not attached.
					//However, whitespaces are still removed
					} else {
						selectedControllingReferral = selectedControllingReferral.trim();
					}
					
					//Set selectedControllingReferral on commonAppForm
					commonAppForm.setSelectedControllingReferral(selectedControllingReferral);
					
					//Create collection of offenses for form
					Collection currentOffensesBag = new ArrayList() ;
					Collection<JJSOffenseResponseEvent> offenses = commonAppForm.getMostSeriousOffenses() ;
					if( notNullNotEmptyCollection( offenses ) )
					{
						String selectedReferral = commonAppForm.getSelectedControllingReferral() ;
						for( JJSOffenseResponseEvent anOffense : offenses )
						{
							if( anOffense.getReferralNum().equalsIgnoreCase( selectedReferral ))
							{
								currentOffensesBag.add( anOffense ) ;
							}
						}
						commonAppForm.setCurrentOffenses( currentOffensesBag ) ;
					}
					
				}
			}

			commonAppForm.setJuvenileNum( closingForm.getJuvenileNum() );
		}
		//11030 changes starts
		Collection referrals = UIJuvenileCaseworkHelper.fetchJuvenileProfileReferralsList(closingForm.getJuvenileNum());
		
		boolean matchFound=false;
		
		//Sort by referralDate 
		List sortFields = new ArrayList();
		sortFields.add(new ReverseComparator(new BeanComparator("referralDate")));
		ComparatorChain multiSort = new ComparatorChain(sortFields);
		Collections.sort((List)referrals, multiSort);
		//Sort by referralDate
		
		Iterator<JuvenileProfileReferralListResponseEvent> referralItr = referrals.iterator();
		while(referralItr.hasNext()){
			JuvenileProfileReferralListResponseEvent referral = (JuvenileProfileReferralListResponseEvent)referralItr.next();
				if(referral.getCourtDisposition()!=null && referral.getJuvDispositionCode()!=null && referral.getCourtDisposition().equals(referral.getJuvDispositionCode().getCodeAlpha()))
				{
					if(referral.getJuvDispositionCode()!=null && referral.getJuvDispositionCode().getDispositionCode()!=null && referral.getJuvDispositionCode().getDispositionCode().equals("AT")){
							//Commitment Date
							String commitmentDate = commonAppForm.getCourtOrder().getCommitmentDate();
							String courtDate =  DateUtil.dateToString(referral.getCourtDate(),DateUtil.DATE_FMT_1);
							
							if(commitmentDate!=null && commitmentDate.equals(courtDate)){
								continue;
							}
							
							if(referral.getCourtDate()!=null && referral.getCourtDate().before(DateUtil.getCurrentDate())){
								if(commonAppForm.getCourtOrder().getCommitmentDate()==null){
									commonAppForm.getCourtOrder().setCommitmentDate(courtDate);
									commitmentDate=courtDate; //reset the court date based on the condition.
								}
							}
							
							//Prior Commitment Date (date which previous referral got committed.)
							if(referrals.size()>1 && commitmentDate!=null && !courtDate.equals(commitmentDate)){
								commonAppForm.getCourtOrder().setPriorTJJDCommitmentDate(courtDate);
							}
							
							if(matchFound)
								break;//out of referral after setting prior commitment date.
							
							//set determinate sentence - ER changes 11449
							//Condition to check the subgroup to be D
							if(referral.getJuvDispositionCode().getSubGroupInd()!=null && referral.getJuvDispositionCode().getSubGroupInd().equals("D")){
								commonAppForm.getCourtOrder().setDeterminateSentence(true);
							}else{
								commonAppForm.getCourtOrder().setDeterminateSentence(false);
							}
							//set the offense code and offense description starts.
							List<DocketEventResponseEvent> courtEvent = (ArrayList<DocketEventResponseEvent>)referral.getCourts();
							if(courtEvent!=null){
								//Sort by courtDate 
								sortFields = new ArrayList();
								sortFields.add(new ReverseComparator(new BeanComparator("courtDate")));
								multiSort = new ComparatorChain(sortFields);
								Collections.sort((List)courtEvent, multiSort);
								//Sort by courtDate 
							}
							Iterator courtItr =courtEvent.iterator();
							while(courtItr.hasNext()){
								JJSCourt court = (JJSCourt)courtItr.next();
								if(court!=null){
										//Get court information from the court table.
										GetJuvenileCourtsEvent getJuvCourtsEvent = (GetJuvenileCourtsEvent)EventFactory.getInstance( CodeTableControllerServiceNames.GETJUVENILECOURTS );
										getJuvCourtsEvent.setCourt(court.getCourtId());
										IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
										dispatch.postEvent( getJuvCourtsEvent );
										CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply();
										MessageUtil.processReturnException( compositeResponse );
										
										JuvenileCourtResponseEvent respObj = (JuvenileCourtResponseEvent) MessageUtil.filterComposite(compositeResponse,JuvenileCourtResponseEvent.class);
										commonAppForm.getCourtOrder().setCourtNameId(respObj.getCode());//set court name-ER changes 11449
										commonAppForm.getCourtOrder().setCourtName(respObj.getDescription());
										commonAppForm.getCourtOrder().setJudgeName(respObj.getJudgeName());
										
										commonAppForm.getCourtOrder().setProsecutingAttorneyName("CHIEF PROS ATTY");//ER changes 11449
										
										Collection<JJSPetition> petitions = referral.getPetitions();
										if(petitions!=null && !petitions.isEmpty()){	
											Iterator<JJSPetition> petitionIter = petitions.iterator();
											while(petitionIter.hasNext())
											{
												JJSPetition petition = petitionIter.next();
												if(petition!=null && petition.getPetitionNum()!=null && petition.getPetitionNum().equalsIgnoreCase(court.getPetitionNumber())){
													commonAppForm.getCourtOrder().setOffenseCode(petition.getOffenseCode().getCode());
													commonAppForm.getCourtOrder().setOffenseDescription(petition.getOffenseCode().getDescription());
													//ER changes 11449 ends
													matchFound=true;
													break; //out of petition
												}
											}
										}
									}
								if(matchFound)
								break; //out of court
							}//set the offense code and offense description ends.
					}
			}
		}//11030 changes ends
		
		commonAppForm.setCourtOrderExists( true );

		return aMapping.findForward( UIConstants.SUCCESS );
	}

	/*
	 * 
	 */
	public ActionForward setOffenses( ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CommonAppForm commonAppForm = (CommonAppForm)aForm;

		Collection coll = getCurrentOffenses( aRequest.getSession() );
		commonAppForm.setCurrentOffenses( coll );

		return aMapping.findForward( UIConstants.SUCCESS );
	}

	/*
	 * 
	 */
	public ActionForward commonApp( ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return link( aMapping, aForm, aRequest, aResponse );
	}

	/*
	 * 
	 */
	private Collection getJuvenileCourts()
	{
		GetJuvenileCourtsEvent getJuvCourtsEvent = (GetJuvenileCourtsEvent)EventFactory.getInstance( CodeTableControllerServiceNames.GETJUVENILECOURTS );

		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
		dispatch.postEvent( getJuvCourtsEvent );

		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply();
		MessageUtil.processReturnException( compositeResponse );

		Collection juvenileCourts = MessageUtil.compositeToCollection( compositeResponse, JuvenileCourtResponseEvent.class );
		Collections.sort( (List)juvenileCourts );

		return juvenileCourts;
	}

	/*
	 * 
	 */
	private Collection<JJSOffenseResponseEvent>  getCurrentOffenses( HttpSession session )
	{
		CommonAppForm commonAppForm = (CommonAppForm)session.getAttribute( COMMON_APP_FORM_STR );

		if( commonAppForm != null )
		{
			String referralNumStr = commonAppForm.getCourtOrder().getMostSeriousOffenseId();
			if( StringUtils.isNotEmpty( referralNumStr ) )
			{
				StringTokenizer tokens = new StringTokenizer( referralNumStr, " " );
				if( tokens.hasMoreTokens() )
				{
					String referralNum = tokens.nextToken();
					// get the offenses
					Collection<JJSOffenseResponseEvent> offenses = UIJuvenileCasefileClosingHelper.getOffenses( commonAppForm.getJuvenileNum(), referralNum );
					if( offenses != null )
					{
						// remove duplicates
						HashMap offenseMap = new HashMap();
						for(JJSOffenseResponseEvent resp : offenses)
						{
							if( !offenseMap.containsKey( resp.getCatagory() ) )
							{
								offenseMap.put( resp.getCatagory(), resp );
							}
						}

						offenses = new ArrayList();
						Collection<JJSOffenseResponseEvent> offensesMap = offenseMap.values();
						for(JJSOffenseResponseEvent offResp : offensesMap)
						{
							offenses.add( offResp );
						}

						Collections.sort( (List)offenses, JJSOffenseResponseEvent.OffenseComparator );
						return offenses;
					}
				}
			} // if there's a referralNumStr
		} // if( closingForm != null )

		return null;
	}

	/**
	 * Return Court Codes
	 * 
	 * @return Collection
	 */
	public List<CommonAppForm.Placement>  getJuvenileDetentionFacilities( Collection<CasefileClosingForm.Refferal> referrals, String juvenileNum )
	{
		GetNonDetentionJuvenileFacilitiesEvent facilityEvent = new GetNonDetentionJuvenileFacilitiesEvent();
		CompositeResponse response = MessageUtil.postRequest(facilityEvent);
		List<JuvenileFacilityResponseEvent> facilityColl = MessageUtil.compositeToList(response, JuvenileFacilityResponseEvent.class);
		
		List<CommonAppForm.Placement> detentionFacilities = new ArrayList<CommonAppForm.Placement> ();
		// for each referral get the detention facilities
		for(CasefileClosingForm.Refferal referral : referrals)
		{
			GetJuvenileDetentionFacilitiesEvent det = (GetJuvenileDetentionFacilitiesEvent)EventFactory.getInstance( JuvenileCaseControllerServiceNames.GETJUVENILEDETENTIONFACILITIES );
			String refNo = null;
			if(referral.getReferralNumber()!=null){
				if(referral.getReferralNumber().contains("-")){
					refNo = referral.getReferralNumber().split("-")[0];
				}else{
					refNo = referral.getReferralNumber();
				}
			}
			det.setReferralNum(refNo);
			det.setJuvenileNum( juvenileNum );

			IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
			dispatch.postEvent( det );
			CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply();
			MessageUtil.processReturnException( compositeResponse );

			Collection<JuvenileDetentionFacilitiesResponseEvent> facilities = MessageUtil.compositeToCollection( compositeResponse, JuvenileDetentionFacilitiesResponseEvent.class );
			if( facilities != null )
			{
				for(JuvenileDetentionFacilitiesResponseEvent detentionResp : facilities)
				{
					Iterator<JuvenileFacilityResponseEvent> iter = facilityColl.iterator();
					while(iter.hasNext())
					{
					  JuvenileFacilityResponseEvent resp = iter.next();
					  if(resp.getCode().equals(detentionResp.getDetainedFacility())){
				    	detentionFacilities.add( getFacilityFromResp( detentionResp) );
					  }
				   }
				}
			}
		}
		return detentionFacilities;
	}

	/*
	 * 
	 */
	private CommonAppForm.Placement getFacilityFromResp(JuvenileDetentionFacilitiesResponseEvent resp)
	{
		CommonAppForm.Placement place = new CommonAppForm.Placement();

		place.setReferralNumber( resp.getReferralNumber() );
		place.setFacilityName( resp.getDetainedFacilityDesc());
		place.setAdmitDate( DateUtil.dateToString( resp.getAdmitDate(), MONTH_DAY_YEAR_STR ) );
		place.setAdmitTime( resp.getAdmitTime());

		if( StringUtils.isNotEmpty( resp.getReleaseTime() ) )
		{
			place.setReleaseTime(resp.getReleaseTime());
		}

		place.setReleaseDate( DateUtil.dateToString( resp.getReleaseDate(), MONTH_DAY_YEAR_STR ) );
		place.setStayed( false );

		if( resp.getReleaseDate() != null )
		{
			long days = ((resp.getReleaseDate().getTime() - resp.getAdmitDate().getTime()) / (DAY_CONVERSION)) + 1;
			place.setDetTime( days );
			place.setTotalTime( new StringBuffer( UIConstants.EMPTY_STRING ).append( days ).append( DAYS_STR ).toString() );
		}
		else
		{
			long days = ((DateUtil.getCurrentDate().getTime() - resp.getAdmitDate().getTime()) / (DAY_CONVERSION)) + 1;
			place.setDetTime( days );
			place.setTotalTime( new StringBuffer( UIConstants.EMPTY_STRING ).append( days ).append( DAYS_STR ).toString() );
		}
		//Bug #58612 - get the juvTJPCPlacementType for the detention rec and add rec to the placement
		GetJuvenileFacilityByCodeEvent facilityEvent = (GetJuvenileFacilityByCodeEvent)EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEFACILITYBYCODE);
		facilityEvent.setCode(resp.getDetainedFacility());
		CompositeResponse response = MessageUtil.postRequest(facilityEvent);
		JuvenileFacilityResponseEvent juvFacRespEvnt =  (JuvenileFacilityResponseEvent) MessageUtil.filterComposite(response, JuvenileFacilityResponseEvent.class);
		if(juvFacRespEvnt!=null)
			place.setFacilityInfo(juvFacRespEvnt);
		
		return place;
	}

	/*
	 * 
	 */
	private Collection getSortedTime()
	{
		Collection coll = SimpleCodeTableHelper.getUnsortedCodeRespEvts( "TIME_NUMERIC" );
		Collections.sort( (List)coll, new GradeLevelComparator() );

		return coll;
	}
	
	/*
	 * 
	 */
	private boolean notNullNotEmptyCollection( Collection bag )
	{
		return( bag != null &&  ! bag.isEmpty() ) ;
	}

	/*
	 * (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping( Map keyMap )
	{
		keyMap.put( "button.commonApp", "commonApp" );
		keyMap.put( "button.link", "link" );
		keyMap.put( "button.find", "setOffenses" );
	}
}
