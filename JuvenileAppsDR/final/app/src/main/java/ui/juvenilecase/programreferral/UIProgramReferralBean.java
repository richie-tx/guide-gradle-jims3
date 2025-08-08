/*
 * Created on May 15, 2007
 *
 */
package ui.juvenilecase.programreferral;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.administerlocation.reply.LocationNotificationResponseEvent;
import messaging.juvenilecase.GetJuvenileCasefileEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.notification.CreateNotificationEvent;
import messaging.programreferral.SaveProgramReferralEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import messaging.task.CreateTaskEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.naming.NotificationControllerSerivceNames;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileProgramReferralControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.TaskControllerServiceNames;
import naming.UIConstants;
import ui.common.Name;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.UIJuvenileCasefileClosingHelper;
import ui.security.SecurityUIHelper;

/**
 */
public class UIProgramReferralBean
{
	private String referralId = null ;

	private String assignedHours ;
	private float creditHours; 
	private float totalCreditHours;

	private String beginDateStr ;

	private String endDateStr ;

	private boolean courtOrdered ;
	//Bug Fix 14417
	private String courtOrderedVal;

	private String comments ;
		
	private Date currentDate ;

	private String currentUserName ;

	private Date sentDate ;

	private Date lastActionDate ;

	private Date acknowledgementDate ;

	private String outComeCd ;

	private String outComeDescription ;
	
	private String outComeSubcategoryCd ;

	private String outComeSubcategoryDescription ;

	private String statusReason ;

	private String casefileId ;

	private String juvenileId ;

	private String juvenileLastName ;

	private String juvenileFirstName ;

	private String juvenileMiddleName ;

	private String officerId ;

	private String officerLastName ;

	private String officerMiddleName ;

	private String officerFirstName ;

	private String providerProgramId ;

	private String providerProgramName ;

	private String juvServiceProviderId ;

	private String juvServiceProviderName ;

	private ProgramReferralState referralState ;

	private List nextPossibleActions ;

	private String currentUserType ;

	private List referralComments ;

	private List existingReferrals ;

	private List juvenileReferralHistory ;

	private List juvenileEvents ;
	
	private List prAssignmentHistoryList;
	
	private List casefiles;

	private ProgramReferralAction currentAction ;

	private ProgramReferralTaskInfo referralTaskInfo ;

	private ProgramReferralNoticeInfo referralNoticeInfo ;
	
	private String referralStatus ;
	
	private String controllingReferralNum;
	
	private String referralSubstatusCd;
	
	private String referralSubstatusDesc;
	private boolean isTentativeReferred = false;
	private boolean matchedSelectedSupervisionCategory = false;

	public UIProgramReferralBean( )
	{
		referralId = null ;
		beginDateStr = "" ;
		endDateStr = "" ;
		courtOrdered=true;
		//Bug Fix 14417
		courtOrderedVal="";
		assignedHours = "" ;
		//creditHours = 0;
		//totalCreditHours = 0;
		comments = "" ;
		currentUserName = "" ;
		currentDate = new Date( ) ;
		outComeCd = "" ;
		outComeDescription = "" ;
		outComeSubcategoryCd = "" ;
		outComeSubcategoryDescription = "" ;

		statusReason = "" ;

		juvenileId = "" ;
		juvenileLastName = "" ;
		juvenileFirstName = "" ;
		juvenileMiddleName = "" ;

		officerId = "" ;
		officerLastName = "" ;
		officerMiddleName = "" ;
		officerFirstName = "" ;

		providerProgramId = "" ;
		providerProgramName = "" ;

		juvServiceProviderId = "" ;
		juvServiceProviderName = "" ;

		casefileId = "" ;
		currentAction = null ;

		referralTaskInfo = null ;
		referralNoticeInfo = null ;
		referralStatus = "";
		setReferralSubstatusCd("");
		setReferralSubstatusDesc("");
	}
	
	public void reset(){
	    
	    this.setOutComeCd(null);
	    this.setOutComeDescription(null);
	    this.setOutComeSubcategoryCd(null);
	    this.setOutComeSubcategoryDescription(null);
	    
	    //add other items to be reset here...
	}

	public UIProgramReferralBean( ProgramReferralResponseEvent resp )
	{
		this.setCurrentAction( null ) ;
		this.setReferralTaskInfo( null ) ;
		this.setReferralNoticeInfo( null ) ;
		this.setReferralId( resp.getReferralId( ) ) ;
		
		if( resp.getBeginDate( ) != null )
		{
			this.setBeginDateStr( DateUtil.dateToString( resp.getBeginDate( ), DateUtil.DATE_FMT_1 ) ) ;
		}

		if( resp.getEndDate( ) != null )
		{
			this.setEndDateStr( DateUtil.dateToString( resp.getEndDate( ), DateUtil.DATE_FMT_1 ) ) ;
		}

		this.setAssignedHours( resp.getAssignedHours( ) ) ;
		this.setCreditHours(resp.getCreditHours()) ;
		this.setCourtOrdered( resp.isCourtOrdered( ) ) ;
		this.setReferralState( ProgramReferralStateManager.getReferralState( resp.getReferralStatusCd( ), resp.getReferralSubStatusCd( ) ) ) ;
		this.setReferralComments( resp.getReferralComments( ) ) ;
		
		this.setCurrentDate( new Date( ) ) ;

		this.setJuvenileId( resp.getJuvenileId( ) ) ;
		this.setJuvenileFirstName( resp.getJuvenileFirstName( ) ) ;
		this.setJuvenileLastName( resp.getJuvenileLastName( ) ) ;
		this.setJuvenileMiddleName( resp.getJuvenileMiddleName( ) ) ;

		this.setOfficerId( resp.getOfficerId( ) ) ;
		this.setOfficerFirstName( resp.getOfficerFirstName( ) ) ;
		this.setOfficerMiddleName( resp.getOfficerMiddleName( ) ) ;
		this.setOfficerLastName( resp.getOfficerLastName( ) ) ;
		this.setReferralStatus(resp.getReferralStatusCd()); //ADDED FOR BUG #40488
		this.setIsTentativeReferred(this.getIsTentativeReferred());

		this.setProviderProgramId( resp.getProvProgramId( ) ) ;
		this.setProviderProgramName( resp.getProviderProgramName( ) ) ;
		this.setJuvServiceProviderId( resp.getJuvServiceProviderId( ) ) ;
		this.setJuvServiceProviderName( resp.getJuvServiceProviderName( ) ) ;

		this.setSentDate( resp.getSentDate( ) ) ;
		this.setAcknowledgementDate( resp.getAcknowledgementDate( ) ) ;
		this.setLastActionDate( resp.getLastActionDate( ) ) ;

		this.setOutComeCd( resp.getOutComeCd( ) ) ;
		this.setOutComeSubcategoryCd(resp.getOutComeSubcategoryCd() );
		this.setOutComeSubcategoryDescription("");
		if (resp.getOutComeSubcategoryCd() != null)
		{
		    //bug fix for 145144 while updating in casefile view codetable referencing was SUPERVISION_OUTCOME_REF_DESC but here it was SUPERVISION_OUTCOME_DESC so modified to reference from SUPERVISION_OUTCOME_REF_DESC created a new fun getSupervisionOutcomeRefDescCodesDescription
			this.setOutComeSubcategoryDescription(UIJuvenileCasefileClosingHelper.getSupervisionOutcomeRefDescCodesDescription( 
					resp.getOutComeSubcategoryCd() ));
			//
		}
		
		this.setCasefileId( resp.getCasefileId( ) ) ;
		this.setReferralStatus(resp.getReferralStatusCd( ));
		this.setReferralSubstatusCd(resp.getReferralSubStatusCd());
		this.setAcknowledgementDate(resp.getAcknowledgementDate());
		
		//set the controlling referralnumber
		GetJuvenileCasefileEvent getCasefileEvent = (GetJuvenileCasefileEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASEFILE);
		getCasefileEvent.setSupervisionNumber(resp.getCasefileId());
		CompositeResponse response = MessageUtil.postRequest(getCasefileEvent);
		JuvenileCasefileResponseEvent casefile = (JuvenileCasefileResponseEvent) MessageUtil.filterComposite(response, JuvenileCasefileResponseEvent.class);
		if(casefile.getControllingReferral()!=null){
			this.setControllingReferralNum(casefile.getControllingReferral());
		}else{
			this.setControllingReferralNum(casefile.getControllingReferralId());
		}
	}

	public SaveProgramReferralEvent getSaveProgramReferralEvent( )
	{
		SaveProgramReferralEvent saveEvent = (SaveProgramReferralEvent)
				EventFactory.getInstance( JuvenileProgramReferralControllerServiceNames.SAVEPROGRAMREFERRAL ) ;

		if( this.getReferralId( ) != null )
		{
			saveEvent.setReferralId( this.getReferralId( ) ) ;
		}
		
		saveEvent.setBeginDate( DateUtil.stringToDate( this.getBeginDateStr( ), DateUtil.DATE_FMT_1 ) ) ;
		
		if( this.getEndDateStr( ) != null && !"".equals( this.getEndDateStr( ) ) )
		{
			saveEvent.setEndDate( DateUtil.stringToDate( this.getEndDateStr( ), DateUtil.DATE_FMT_1 ) ) ;
		}

		saveEvent.setAssignedHours( this.getAssignedHours( ) ) ;
		saveEvent.setCourtOrdered( this.isCourtOrdered( ) ) ;
		saveEvent.setReferralStatusCd( this.getReferralState( ).getStatus( ) ) ;
		saveEvent.setReferralSubStatusCd( this.getReferralState( ).getSubStatus( ) ) ;
		saveEvent.setComments( this.getComments( ) ) ;

		if( this.getCurrentUserName( ) == null || !"".equals( this.getCurrentUserName( ) ) )
		{
			IUserInfo user = SecurityUIHelper.getUser( ) ;
			Name userName = new Name( user.getFirstName( ), "", user.getLastName( ) ) ;

			this.setCurrentUserName( userName.getFormattedName( ) ) ;
			saveEvent.setCurrentUserName( this.getCurrentUserName( ) ) ;
			this.setCurrentDate( new Date( ) ) ;
		}

		saveEvent.setLastActionDate( this.getLastActionDate( ) ) ;
		saveEvent.setSentDate( this.getSentDate( ) ) ;
		saveEvent.setAcknowledementDate( this.getAcknowledgementDate( ) ) ;
		saveEvent.setOutComeCd( this.getOutComeCd( ) ) ;
		saveEvent.setOutComeSubcategoryCd( this.getOutComeSubcategoryCd() );
		//bug fix 22872 starts
		String	controlRefNo = this.getControllingReferralNum();
		if(controlRefNo!=null && !controlRefNo.isEmpty()){
	 		saveEvent.setControllingReferralNum(controlRefNo.substring(0,4));
		}
		//bug fix 22872 ends
		//<KISHORE>JIMS200057235 MJCW Sch Cal Even and View Cal - Attend Status is incorrect
		saveEvent.setAttachedEvents(this.getJuvenileEvents());
		return saveEvent ;
	}

	/**
	 * @return Returns the assignedHours.
	 */
	public String getAssignedHours( )
	{
		return assignedHours ;
	}
	
	/**
	 * @param assignedHours
	 * The assignedHours to set.
	 */
	public void setAssignedHours( String assignedHours )
	{
		this.assignedHours = assignedHours ;
	}
	
	public float getCreditHours( )
	{
		return creditHours;
	}
	
	public void setCreditHours(float credithours){
	    
	    this.creditHours = credithours;
	}
	
	public float getTotalCreditHours( )
	{
		return this.totalCreditHours;
	}
	
	public void setTotalCreditHours(float totalCreditHours){
	    
	    this.totalCreditHours = totalCreditHours;
	}

	/**
	 * @return Returns the beginDateStr.
	 */
	public String getBeginDateStr( )
	{
		return beginDateStr ;
	}

	/**
	 * @param beginDateStr
	 * The beginDateStr to set.
	 */
	public void setBeginDateStr( String beginDateStr )
	{
		this.beginDateStr = beginDateStr ;
	}

	/**
	 * @return Returns the courtOrdered.
	 */
	public boolean isCourtOrdered( )
	{
		return courtOrdered ;
	}

	/**
	 * @param courtOrdered
	 * The courtOrdered to set.
	 */
	public void setCourtOrdered( boolean courtOrdered )
	{
		this.courtOrdered = courtOrdered ;
	}
	
	

	/**
	 * @return Returns the comments.
	 */
	public String getComments( )
	{
		return comments ;
	}

	/**
	 * @param comments
	 * The comments to set.
	 */
	public void setComments( String comments )
	{
		this.comments = comments ;
	}

	/**
	 * @return Returns the referralComments.
	 */
	public List getReferralComments( )
	{
		return referralComments ;
	}

	/**
	 * @param referralComments
	 * The referralComments to set.
	 */
	public void setReferralComments( List referralComments )
	{
		this.referralComments = referralComments ;
	}

	/**
	 * @return Returns the referralState.
	 */
	public ProgramReferralState getReferralState( )
	{
		return referralState ;
	}

	/**
	 * @param referralState
	 * The referralState to set.
	 */
	public void setReferralState( ProgramReferralState referralState )
	{
	    	if(referralState != null && referralState.getStatus()!= null && referralState.getSubStatus() != null){
	    	    if(referralState.getStatus().equalsIgnoreCase("TN") 
	    		    && referralState.getSubStatus().equalsIgnoreCase("REF")){ //"REFERRED"
	    		this.isTentativeReferred = true;
	    	    }
	    	}
	    	
		this.referralState = referralState ;
	}
	
	public boolean getIsTentativeReferred(){
	    return isTentativeReferred;
	}
	
	public void setIsTentativeReferred(boolean tentativeReferred){
	    this.isTentativeReferred = tentativeReferred;
	}
	
	public boolean getMatchedSelectedSupervisionCategory(){
	    return this.matchedSelectedSupervisionCategory;
	}
	
	public void setMatchedSelectedSupervisionCategory(boolean matchedSelectedSupervisionCategory){
	    this.matchedSelectedSupervisionCategory = matchedSelectedSupervisionCategory;
	}

	/**
	 * @return Returns the existingReferrals.
	 */
	public List getExistingReferrals( )
	{
		return existingReferrals ;
	}

	/**
	 * @param existingReferrals
	 * The existingReferrals to set.
	 */
	public void setExistingReferrals( List existingReferrals )
	{
		this.existingReferrals = existingReferrals ;
	}

	/**
	 * @return Returns the currentUserName.
	 */
	public String getCurrentUserName( )
	{
		return currentUserName ;
	}

	/**
	 * @param currentUserName
	 * The currentUserName to set.
	 */
	public void setCurrentUserName( String currentUserName )
	{
		this.currentUserName = currentUserName ;
	}

	/**
	 * @return Returns the referralId.
	 */
	public String getReferralId( )
	{
		return referralId ;
	}

	/**
	 * @param referralId
	 * The referralId to set.
	 */
	public void setReferralId( String referralId )
	{
		this.referralId = referralId ;
	}

	/**
	 * @return Returns the currentDate.
	 */
	public Date getCurrentDate( )
	{
		return currentDate ;
	}

	/**
	 * @param currentDate
	 * The currentDate to set.
	 */
	public void setCurrentDate( Date currentDate )
	{
		this.currentDate = currentDate ;
	}

	/**
	 * @return Returns the juvenileFirstName.
	 */
	public String getJuvenileFirstName( )
	{
		return juvenileFirstName ;
	}

	/**
	 * @param juvenileFirstName
	 * The juvenileFirstName to set.
	 */
	public void setJuvenileFirstName( String juvenileFirstName )
	{
		this.juvenileFirstName = juvenileFirstName ;
	}

	/**
	 * @return Returns the juvenileId.
	 */
	public String getJuvenileId( )
	{
		return juvenileId ;
	}

	/**
	 * @param juvenileId
	 * The juvenileId to set.
	 */
	public void setJuvenileId( String juvenileId )
	{
		this.juvenileId = juvenileId ;
	}

	/**
	 * @return Returns the juvenileLastName.
	 */
	public String getJuvenileLastName( )
	{
		return juvenileLastName ;
	}

	/**
	 * @param juvenileLastName
	 * The juvenileLastName to set.
	 */
	public void setJuvenileLastName( String juvenileLastName )
	{
		this.juvenileLastName = juvenileLastName ;
	}

	/**
	 * @return Returns the juvenileMiddleName.
	 */
	public String getJuvenileMiddleName( )
	{
		return juvenileMiddleName ;
	}

	/**
	 * @param juvenileMiddleName
	 * The juvenileMiddleName to set.
	 */
	public void setJuvenileMiddleName( String juvenileMiddleName )
	{
		this.juvenileMiddleName = juvenileMiddleName ;
	}

	/**
	 * @return Returns the juvServiceProviderId.
	 */
	public String getJuvServiceProviderId( )
	{
		return juvServiceProviderId ;
	}

	/**
	 * @param juvServiceProviderId
	 * The juvServiceProviderId to set.
	 */
	public void setJuvServiceProviderId( String juvServiceProviderId )
	{
		this.juvServiceProviderId = juvServiceProviderId ;
	}

	/**
	 * @return Returns the juvServiceProviderName.
	 */
	public String getJuvServiceProviderName( )
	{
		return juvServiceProviderName ;
	}

	/**
	 * @param juvServiceProviderName
	 * The juvServiceProviderName to set.
	 */
	public void setJuvServiceProviderName( String juvServiceProviderName )
	{
		this.juvServiceProviderName = juvServiceProviderName ;
	}

	/**
	 * @return Returns the lastActionDate.
	 */
	public Date getLastActionDate( )
	{
		return lastActionDate ;
	}

	/**
	 * @param lastActionDate
	 * The lastActionDate to set.
	 */
	public void setLastActionDate( Date lastActionDate )
	{
		this.lastActionDate = lastActionDate ;
	}

	/**
	 * @return Returns the officerFirstName.
	 */
	public String getOfficerFirstName( )
	{
		return officerFirstName ;
	}

	/**
	 * @param officerFirstName
	 * The officerFirstName to set.
	 */
	public void setOfficerFirstName( String officerFirstName )
	{
		this.officerFirstName = officerFirstName ;
	}

	/**
	 * @return Returns the officerId.
	 */
	public String getOfficerId( )
	{
		return officerId ;
	}

	/**
	 * @param officerId
	 * The officerId to set.
	 */
	public void setOfficerId( String officerId )
	{
		this.officerId = officerId ;
	}

	/**
	 * @return Returns the officerLastName.
	 */
	public String getOfficerLastName( )
	{
		return officerLastName ;
	}

	/**
	 * @param officerLastName
	 * The officerLastName to set.
	 */
	public void setOfficerLastName( String officerLastName )
	{
		this.officerLastName = officerLastName ;
	}

	/**
	 * @return Returns the officerMiddleName.
	 */
	public String getOfficerMiddleName( )
	{
		return officerMiddleName ;
	}

	/**
	 * @param officerMiddleName
	 * The officerMiddleName to set.
	 */
	public void setOfficerMiddleName( String officerMiddleName )
	{
		this.officerMiddleName = officerMiddleName ;
	}

	/**
	 * @return Returns the outComeCd.
	 */
	public String getOutComeCd( )
	{
		return outComeCd ;
	}

	/**
	 * @param outComeCd
	 * The outComeCd to set.
	 */
	public void setOutComeCd( String outComeCd )
	{
		this.outComeCd = outComeCd ;
		if( outComeCd != null && !"".equals( outComeCd ) )
		{
			this.setOutComeDescription( SimpleCodeTableHelper.getDescrByCode( PDCodeTableConstants.PROGRAM_REFERRAL_OUTCOME, outComeCd ) ) ;
		}
	}

	/**
	 * @return Returns the outComeDescription.
	 */
	public String getOutComeDescription( )
	{
		return outComeDescription ;
	}

	/**
	 * @param outComeDescription
	 * The outComeDescription to set.
	 */
	public void setOutComeDescription( String outComeDescription )
	{
		this.outComeDescription = outComeDescription ;
	}

	/**
	 * @return the outComeSubcategoryCd
	 */
	public String getOutComeSubcategoryCd() {
		return outComeSubcategoryCd;
	}

	/**
	 * @param outComeSubcategoryCd the outComeSubcategoryCd to set
	 */
	public void setOutComeSubcategoryCd(String outComeSubcategoryCd) {
		this.outComeSubcategoryCd = outComeSubcategoryCd;
	}

	/**
	 * @return the outComeSubcategoryDescription
	 */
	public String getOutComeSubcategoryDescription() {
		return outComeSubcategoryDescription;
	}

	/**
	 * @param outComeSubcategoryDescription the outComeSubcategoryDescription to set
	 */
	public void setOutComeSubcategoryDescription( String outComeSubcategoryDescription) {
		this.outComeSubcategoryDescription = outComeSubcategoryDescription;
	}

	/**
	 * @return Returns the providerProgramName.
	 */
	public String getProviderProgramName( )
	{
		return providerProgramName ;
	}

	/**
	 * @param providerProgramName
	 * The providerProgramName to set.
	 */
	public void setProviderProgramName( String providerProgramName )
	{
		this.providerProgramName = providerProgramName ;
	}

	/**
	 * @return Returns the sentDate.
	 */
	public Date getSentDate( )
	{
		return sentDate ;
	}

	/**
	 * @param sentDate
	 * The sentDate to set.
	 */
	public void setSentDate( Date sentDate )
	{
		this.sentDate = sentDate ;
	}

	/**
	 * @return Returns the statusReason.
	 */
	public String getStatusReason( )
	{
		return statusReason ;
	}

	/**
	 * @param statusReason
	 * The statusReason to set.
	 */
	public void setStatusReason( String statusReason )
	{
		this.statusReason = statusReason ;
	}

	/**
	 * @return Returns the providerProgramId.
	 */
	public String getProviderProgramId( )
	{
		return providerProgramId ;
	}

	/**
	 * @param providerProgramId
	 * The providerProgramId to set.
	 */
	public void setProviderProgramId( String providerProgramId )
	{
		this.providerProgramId = providerProgramId ;
	}

	/**
	 * @return Returns the juvenileReferralHistory.
	 */
	public List getJuvenileReferralHistory( )
	{
		return juvenileReferralHistory ;
	}

	/**
	 * @param juvenileReferralHistory
	 * The juvenileReferralHistory to set.
	 */
	public void setJuvenileReferralHistory( List juvenileReferralHistory )
	{
		this.juvenileReferralHistory = juvenileReferralHistory ;
	}

	/**
	 * @return Returns the juvenileEvents.
	 */
	public List getJuvenileEvents( )
	{
		return juvenileEvents ;
	}

	/**
	 * @param juvenileEvents
	 * The juvenileEvents to set.
	 */
	public void setJuvenileEvents( List juvenileEvents )
	{
		this.juvenileEvents = juvenileEvents ;
	}

	/**
	 * @return Returns the acknowledgementDate.
	 */
	public Date getAcknowledgementDate( )
	{
		return acknowledgementDate ;
	}

	/**
	 * @param acknowledgementDate
	 * The acknowledgementDate to set.
	 */
	public void setAcknowledgementDate( Date acknowledgementDate )
	{
		this.acknowledgementDate = acknowledgementDate ;
	}

	/**
	 * @return Returns the casefileId.
	 */
	public String getCasefileId( )
	{
		return casefileId ;
	}

	/**
	 * @param casefileId
	 * The casefileId to set.
	 */
	public void setCasefileId( String casefileId )
	{
		this.casefileId = casefileId ;
	}

	public String getJuvenileName( )
	{
		Name name = new Name( this.getJuvenileFirstName( ), this.getJuvenileMiddleName( ), this.getJuvenileLastName( ) ) ;
		return name.getFormattedName( ) ;
	}

	public String getOfficerName( )
	{
		Name name = new Name( this.getOfficerFirstName( ), this.getOfficerMiddleName( ), this.getOfficerLastName( ) ) ;
		return name.getFormattedName( ) ;
	}

	/**
	 * @return Returns the currentUserType.
	 */
	public String getCurrentUserType( )
	{
		return currentUserType ;
	}

	/**
	 * @param currentUserType
	 * The currentUserType to set.
	 */
	public void setCurrentUserType( String currentUserType )
	{
		this.currentUserType = currentUserType ;
	}

	/**
	 * @return Returns the nextPossibleActions.
	 */
	public List getNextPossibleActions( )
	{
		return nextPossibleActions ;
	}

	/**
	 * @param nextPossibleActions
	 * The nextPossibleActions to set.
	 */
	public void setNextPossibleActions( List nextPossibleActions )
	{
		this.nextPossibleActions = nextPossibleActions ;
	}

	/**
	 * @return Returns the currentAction.
	 */
	public ProgramReferralAction getCurrentAction( )
	{
		return currentAction ;
	}

	/**
	 * @param currentAction
	 * The currentAction to set.
	 */
	public void setCurrentAction( ProgramReferralAction currentAction )
	{
		this.currentAction = currentAction ;
	}

	public void executeAction( )
	{
		getCurrentAction( ).execute( this ) ;
	}

	public static class ProgramReferralNoticeInfo
	{

		private String notificationTopic ;

		private String subject ;

		private String identity ;

		private String message ;

		public void sendNotification( )
		{
			// LoadNotificationDefinitions.main(new String[0]);
			CreateNotificationEvent notificationEvent = (CreateNotificationEvent)
					EventFactory.getInstance( NotificationControllerSerivceNames.CREATENOTIFICATION ) ;

			LocationNotificationResponseEvent bean = new LocationNotificationResponseEvent( ) ;
			
			bean.setNotificationMessage( message ) ;
			bean.setIdentity( identity ) ;
			
			notificationEvent.setNotificationTopic( notificationTopic ) ;
			notificationEvent.setSubject( subject ) ;
			notificationEvent.addIdentity( UIConstants.NOTIFICATON_RESPONSE_EVENT_CONTEXT, bean ) ;
			notificationEvent.addContentBean( bean ) ;
			
			EventManager.getSharedInstance( EventManager.REQUEST ).postEvent( notificationEvent ) ;
		}

		/**
		 * @return Returns the identity.
		 */
		public String getIdentity( )
		{
			return identity ;
		}

		/**
		 * @param identity
		 * The identity to set.
		 */
		public void setIdentity( String identity )
		{
			this.identity = identity ;
		}

		/**
		 * @return Returns the message.
		 */
		public String getMessage( )
		{
			return message ;
		}

		/**
		 * @param message
		 * The message to set.
		 */
		public void setMessage( String message )
		{
			this.message = message ;
		}

		/**
		 * @return Returns the notificationTopic.
		 */
		public String getNotificationTopic( )
		{
			return notificationTopic ;
		}

		/**
		 * @param notificationTopic
		 * The notificationTopic to set.
		 */
		public void setNotificationTopic( String notificationTopic )
		{
			this.notificationTopic = notificationTopic ;
		}

		/**
		 * @return Returns the subject.
		 */
		public String getSubject( )
		{
			return subject ;
		}

		/**
		 * @param subject
		 * The subject to set.
		 */
		public void setSubject( String subject )
		{
			this.subject = subject ;
		}
	}

	public static class ProgramReferralTaskInfo
	{
		private Map parameterMap ;

		private String taskUserId ;

		private String taskName ;

		private String title ;

		/**
		 * 
		 */
		public ProgramReferralTaskInfo( )
		{
			title = "" ;
			parameterMap = new HashMap( ) ;
			taskName = "" ;
			taskUserId = "" ;
			title = "" ;
		}

		/**
		 * @return Returns the parameterMap.
		 */
		public Map getParameterMap( )
		{
			return parameterMap ;
		}

		/**
		 * @param parameterMap
		 * The parameterMap to set.
		 */
		public void setParameterMap( Map parameterMap )
		{
			this.parameterMap = parameterMap ;
		}

		/**
		 * @return Returns the taskName.
		 */
		public String getTaskName( )
		{
			return taskName ;
		}

		/**
		 * @param taskName
		 * The taskName to set.
		 */
		public void setTaskName( String taskName )
		{
			this.taskName = taskName ;
		}

		/**
		 * @return Returns the taskUserId.
		 */
		public String getTaskUserId( )
		{
			return taskUserId ;
		}

		/**
		 * @param taskUserId
		 * The taskUserId to set.
		 */
		public void setTaskUserId( String taskUserId )
		{
			this.taskUserId = taskUserId ;
		}

		/**
		 * @return Returns the title.
		 */
		public String getTitle( )
		{
			return title ;
		}

		/**
		 * @param title
		 * The title to set.
		 */
		public void setTitle( String title )
		{
			this.title = title ;
		}

		public void createTask( )
		{
			CreateTaskEvent createTask = (CreateTaskEvent)
					EventFactory.getInstance( TaskControllerServiceNames.CREATETASK ) ;

			createTask.setTaskTopic( taskName ) ;
			createTask.setOwnerId( taskUserId ) ;
			createTask.setTaskSubject( title ) ;

			Iterator mapIter = parameterMap.entrySet( ).iterator( ) ;
			while( mapIter.hasNext( ) )
			{
				Map.Entry entry = (Map.Entry)mapIter.next( ) ;
				createTask.addTaskStateItem( (String)entry.getKey( ), (String)entry.getValue( ) ) ;
			}

			MessageUtil.postRequest( createTask ) ;
		}
	}// end public static class ProgramReferralTaskInfo

	
	/**
	 * @return Returns the referralTaskInfo.
	 */
	public ProgramReferralTaskInfo getReferralTaskInfo( )
	{
		return referralTaskInfo ;
	}

	/**
	 * @param referralTaskInfo The referralTaskInfo to set.
	 */
	public void setReferralTaskInfo( ProgramReferralTaskInfo referralTaskInfo )
	{
		this.referralTaskInfo = referralTaskInfo ;
	}

	/**
	 * @return Returns the referralNoticeInfo.
	 */
	public ProgramReferralNoticeInfo getReferralNoticeInfo( )
	{
		return referralNoticeInfo ;
	}

	/**
	 * @param referralNoticeInfo The referralNoticeInfo to set.
	 */
	public void setReferralNoticeInfo( ProgramReferralNoticeInfo referralNoticeInfo )
	{
		this.referralNoticeInfo = referralNoticeInfo ;
	}

	/**
	 * @return Returns the endDateStr.
	 */
	public String getEndDateStr( )
	{
		return endDateStr ;
	}

	/**
	 * @param endDateStr The endDateStr to set.
	 */
	public void setEndDateStr( String endDateStr )
	{
		this.endDateStr = endDateStr ;
	}

	public void setPrAssignmentHistoryList(List prAssignmentHistoryList) {
		this.prAssignmentHistoryList = prAssignmentHistoryList;
	}

	public List getPrAssignmentHistoryList() {
		return prAssignmentHistoryList;
	}

	public List getCasefiles() {
		return casefiles;
	}

	public void setCasefiles(List casefiles) {
		this.casefiles = casefiles;
	}

	/**
	 * @return the referralStatus
	 */
	public String getReferralStatus() {
		return referralStatus;
	}

	/**
	 * @param referralStatus the referralStatus to set
	 */
	public void setReferralStatus(String referralStatus) {
		this.referralStatus = referralStatus;
	}

	/**
	 * @return the controllingReferralNum
	 */
	public String getControllingReferralNum() {
		return controllingReferralNum;
	}

	/**
	 * @param controllingReferralNum the controllingReferralNum to set
	 */
	public void setControllingReferralNum(String controllingReferralNum) {
		this.controllingReferralNum = controllingReferralNum;
	}

	/**
	 * @param courtOrderedVal the courtOrderedVal to set
	 */
	public void setCourtOrderedVal(String courtOrderedVal) {
		this.courtOrderedVal = courtOrderedVal;
	}

	/**
	 * @return the courtOrderedVal
	 */
	public String getCourtOrderedVal() {
		return courtOrderedVal;
	}

	/**
	 * @return the referralSubstatusCd
	 */
	public String getReferralSubstatusCd() {
		return referralSubstatusCd;
	}

	/**
	 * @param referralSubstatusCd the referralSubstatusCd to set
	 */
	public void setReferralSubstatusCd(String referralSubstatusCd) {
		this.referralSubstatusCd = referralSubstatusCd;
	}

	/**
	 * @return the referralSubstatusDesc
	 */
	public String getReferralSubstatusDesc() {
		return referralSubstatusDesc;
	}

	/**
	 * @param referralSubstatusDesc the referralSubstatusDesc to set
	 */
	public void setReferralSubstatusDesc(String referralSubstatusDesc) {
		this.referralSubstatusDesc = referralSubstatusDesc;
	}
	
}