package pd.productionsupport.transactions;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.facility.GetJuvenileFacilityDetailsEvent;
import messaging.juvenile.SearchJuvenileProfilesEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import messaging.productionsupport.GetProductionSupportFacilityDetentionEvent;
import messaging.productionsupport.reply.ProductionSupportFacilityDetentionResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenile.Juvenile;
import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.referral.JJSReferral;


/**
 * @author rcarter
 */

public class GetProductionSupportFacilityDetentionCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public GetProductionSupportFacilityDetentionCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
	   
	   IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	   System.out.println("GetProductionSupportFacilityDetentionCommand");
	   GetProductionSupportFacilityDetentionEvent getDetentionEvent = (GetProductionSupportFacilityDetentionEvent) event;
	   Iterator<JJSFacility> facilityDetentionIter = null;
	   if((getDetentionEvent.getJuvenileId() != null && !getDetentionEvent.getJuvenileId().equals("")) || 
			   (getDetentionEvent.getHeaderId() != null && !getDetentionEvent.getHeaderId().equals(""))){
		   
		   GetJuvenileFacilityDetailsEvent getDetentionResultsEvent = new GetJuvenileFacilityDetailsEvent();
		   if(getDetentionEvent.getJuvenileId() != null && !getDetentionEvent.getJuvenileId().equals("")){
			   getDetentionResultsEvent.setJuvenileNum(getDetentionEvent.getJuvenileId());	
		   }else{
			   getDetentionResultsEvent.setJuvenileNum(null);	
		   }
		   if(getDetentionEvent.getReferralNum() != null && !getDetentionEvent.getReferralNum().equals("")){
			   getDetentionResultsEvent.setReferralNum(getDetentionEvent.getReferralNum());	
		   }else{
			   getDetentionResultsEvent.setReferralNum(null);	
		   }
		   if(getDetentionEvent.getSequenceNum() != null && !getDetentionEvent.getSequenceNum().equals("")){
			   getDetentionResultsEvent.setFacilitySequenceNum(getDetentionEvent.getSequenceNum());	
		   }else{
			   getDetentionResultsEvent.setFacilitySequenceNum(null);	
		   }
		   if(getDetentionEvent.getHeaderId() != null && !getDetentionEvent.getHeaderId().equals("")){
			   getDetentionResultsEvent.setDetentionRecordId(getDetentionEvent.getHeaderId());	
		   }else{
			   getDetentionResultsEvent.setDetentionRecordId(null);	
		   }
		   getDetentionResultsEvent.setFacilitySequenceNum(getDetentionEvent.getSequenceNum());
		   getDetentionResultsEvent.setDetentionRecordId(getDetentionEvent.getHeaderId());
		   facilityDetentionIter = JJSFacility.findAll(getDetentionResultsEvent);
		   if(facilityDetentionIter != null){
			   while(facilityDetentionIter.hasNext()){
				   JJSFacility facilityDetention = facilityDetentionIter.next();
				   if(facilityDetention != null){
					   ProductionSupportFacilityDetentionResponseEvent detentionResponseEvent = new ProductionSupportFacilityDetentionResponseEvent();
					   detentionResponseEvent.setDetentionId(facilityDetention.getOID());
					   detentionResponseEvent.setJuvenileId(facilityDetention.getJuvenileNumber());				   
					   //Juvenile juv = Juvenile.findDetailJuvenile(facilityDetention.getJuvenileNumber());
					   Juvenile juv = null;
					  SearchJuvenileProfilesEvent pEvent = new SearchJuvenileProfilesEvent();

					  pEvent.setJuvenileNum(facilityDetention.getJuvenileNumber());
						
					  Iterator juvIter = Juvenile.findAll(pEvent);
					  if (juvIter != null && juvIter.hasNext()) 
					  {
						juv = (Juvenile) juvIter.next();
					  }						
						
					   if(juv != null){
						   detentionResponseEvent.setFirstName(juv.getFirstName());
						   detentionResponseEvent.setMiddleName(juv.getMiddleName());
						   detentionResponseEvent.setLastName(juv.getLastName());
						   detentionResponseEvent.setSuffixName(juv.getNameSuffix());
					   }
					   detentionResponseEvent.setBookingReferralNum(facilityDetention.getReferralNumber());
					   detentionResponseEvent.setCurrentReferralNum(facilityDetention.getCurrentReferral());
					   detentionResponseEvent.setBookingSupervisionNum(facilityDetention.getBookingSupervisionNum());
					   detentionResponseEvent.setCurrentSupervisionNum(facilityDetention.getCurrentSupervisionNum());
					   detentionResponseEvent.setSequenceNum(facilityDetention.getFacilitySequenceNumber());
					   detentionResponseEvent.setFacilityCode(facilityDetention.getDetainedFacility());
					   detentionResponseEvent.setTjpcSequenceNum(facilityDetention.getTjpcseqnum());
					   detentionResponseEvent.setCurrentOffense(facilityDetention.getCurrentOffense());
					   detentionResponseEvent.setFacilityStatusCode(facilityDetention.getDetentionStatus());
					   detentionResponseEvent.setAdmittanceReasonCode(facilityDetention.getAdmitReason());
					   detentionResponseEvent.setSecureStatus(facilityDetention.getSecureStatus());
					   detentionResponseEvent.setTransferFacilityCode(facilityDetention.getTransferToFacility());					   
					   detentionResponseEvent.setLocationUnit(facilityDetention.getUnit());
					   detentionResponseEvent.setLocationFloorNumber(facilityDetention.getFloorNum());
					   detentionResponseEvent.setRoomNumber(facilityDetention.getRoomNum());
					   detentionResponseEvent.setMultipleOccupanyUnit(facilityDetention.getMultipleOccupyUnit());
					   detentionResponseEvent.setLockerNumber(facilityDetention.getLockerNumber());
					   detentionResponseEvent.setValuablesReceiptNumber(facilityDetention.getReceiptNumber());
					   detentionResponseEvent.setDetainedDate(facilityDetention.getDetainedDate());
					   // mapping reversed in xml - admitted is JPO and visa versa
					   detentionResponseEvent.setAdmittedByAuthority(facilityDetention.getAdmittedByJPO());
					   // mapping reversed in xml - admitted is JPO and visa versa
					   detentionResponseEvent.setAdmittedAuthorizeJPO(facilityDetention.getAdmitAuthority());
					   detentionResponseEvent.setAdmittedDate(facilityDetention.getAdmitDate());
					   detentionResponseEvent.setAdmittedTime(facilityDetention.getAdmitTime());					   
					   detentionResponseEvent.setOriginalAdmittedDate(facilityDetention.getOriginalAdmitDate());
					   detentionResponseEvent.setOriginalAdmittedTime(facilityDetention.getOriginalAdmitTime());
					   
					   detentionResponseEvent.setOriginalAdmitOID(facilityDetention.getOriginalAdmitOID());
					   detentionResponseEvent.setAvgCostPerDay(facilityDetention.getAvgCostPerDay());
					   detentionResponseEvent.setDetainedByInd(facilityDetention.getDetainedByInd());
					   detentionResponseEvent.setTjjdFundingSrc(facilityDetention.getTjjdFundingSrc());
					   detentionResponseEvent.setOriginaladmitSEQNUM(facilityDetention.getOriginaladmitSEQNUM());
					   detentionResponseEvent.setPostAdmitOID(facilityDetention.getPostAdmitOID());
					   detentionResponseEvent.setTjjdFacilityId(facilityDetention.getTjjdFacilityId());
					   detentionResponseEvent.setCustodyfirstName(facilityDetention.getCustodyfirstName());
					   detentionResponseEvent.setCustodylastName(facilityDetention.getCustodylastName());
					   
					   detentionResponseEvent.setChangeExplanation(facilityDetention.getChangeExplanation());
					   detentionResponseEvent.setComments(facilityDetention.getComments());
					   detentionResponseEvent.setEscapeAttempts(facilityDetention.getEscapeAttempts());					   
					   detentionResponseEvent.setEscapeCode(facilityDetention.getEscapeCode());
					   detentionResponseEvent.setEscapeAttemptComments(facilityDetention.getEscapeAttemptComments()); 

					   detentionResponseEvent.setOutcome(facilityDetention.getOutcome()); 
					   detentionResponseEvent.setReleaseDate(facilityDetention.getReleaseDate()); 
					   detentionResponseEvent.setReleaseTime(facilityDetention.getReleaseTime()); 
					   detentionResponseEvent.setReleaseBy(facilityDetention.getReleaseBy()); 
					   detentionResponseEvent.setReleaseTo(facilityDetention.getReleaseTo()); 
					   detentionResponseEvent.setReleaseAuthorizedBy(facilityDetention.getReleaseAuthorizedBy()); 
					   detentionResponseEvent.setReleaseReason(facilityDetention.getReleaseReason()); 
					   detentionResponseEvent.setReturnDate(facilityDetention.getReturnDate()); 
					   detentionResponseEvent.setReturnTime(facilityDetention.getReturnTime()); 
					   detentionResponseEvent.setReturnReason(facilityDetention.getReturnReason()); 
					   detentionResponseEvent.setReturnStatus(facilityDetention.getReturnStatus()); 
					   detentionResponseEvent.setSpecialAttention(facilityDetention.getSpecialAttention()); 
					   detentionResponseEvent.setSpecialAttentionReason(facilityDetention.getSaReasonCode()); 
					   detentionResponseEvent.setSpecialAttentionOtherComments(facilityDetention.getSaReasonOtherComments()); 
					   detentionResponseEvent.setTempReleaseReasonCode(facilityDetention.getTempReleaseReasonCd()); 
					   detentionResponseEvent.setTempReleaseOtherComments(facilityDetention.getTempReleaseOtherComments()); 
					   
					   detentionResponseEvent.setRiskAnalysisId(facilityDetention.getRiskAnalysisId()); //US 121214
					   
					   // audit data
					   if(facilityDetention.getCreateUserID() != null){
						   detentionResponseEvent.setCreateUserID(facilityDetention.getCreateUserID());
						}
						if(facilityDetention.getCreateTimestamp() != null){
							detentionResponseEvent.setCreateDate(new Date(facilityDetention.getCreateTimestamp().getTime()));
						}
						if(facilityDetention.getUpdateUserID() != null){
							detentionResponseEvent.setUpdateUser(facilityDetention.getUpdateUserID());
						}
						if(facilityDetention.getUpdateTimestamp() != null){
							detentionResponseEvent.setUpdateDate(new Date(facilityDetention.getUpdateTimestamp().getTime()));
						}
						if(facilityDetention.getCreateJIMS2UserID() != null){
							detentionResponseEvent.setCreateJIMS2UserID(facilityDetention.getCreateJIMS2UserID());
						}
						if(facilityDetention.getUpdateJIMS2UserID() != null){
							detentionResponseEvent.setUpdateJIMS2UserID(facilityDetention.getUpdateJIMS2UserID());
						}
					//get all active referrals for the Juvenile						
					Iterator<JJSReferral> refIter = JJSReferral.findAll( "juvenileNum", facilityDetention.getJuvenileNumber() );
					ArrayList<JJSReferral> refNums = new ArrayList<JJSReferral>();
					while(refIter.hasNext())
					{
						JJSReferral aRefferal = refIter.next();
						//if(aRefferal.getCloseDate()==null) bug no #89976
						    refNums.add(aRefferal);
						    
					}
					Collections.sort((List<JJSReferral>)refNums,Collections.reverseOrder(new Comparator<JJSReferral>() {
						@Override
						public int compare(JJSReferral evt1, JJSReferral evt2) {
							return Integer.valueOf(evt1.getReferralNum()).compareTo(Integer.valueOf(evt2.getReferralNum()));
						}
				}));
					detentionResponseEvent.setReferralNumbers(refNums);
					
					//get all casefiles for the Juvenile
					Iterator casefilesItr = JuvenileCasefile.findAll("juvenileId", facilityDetention.getJuvenileNumber());
					JuvenileCasefileSearchResponseEvent respEvt=new JuvenileCasefileSearchResponseEvent();
					ArrayList<JuvenileCasefileSearchResponseEvent> casefiles = new ArrayList<JuvenileCasefileSearchResponseEvent>();
					while(casefilesItr.hasNext())
					{					    
					    JuvenileCasefile juvenileCasefile = (JuvenileCasefile) casefilesItr.next();
					    if(juvenileCasefile.getCaseStatusId()!=null && !juvenileCasefile.getCaseStatusId().equalsIgnoreCase("P"))
					    {
						respEvt = new JuvenileCasefileSearchResponseEvent();
						respEvt.setSupervisionNum(juvenileCasefile.getCasefileId());						
					    }
					    casefiles.add(respEvt);
					}
					detentionResponseEvent.setCurrentCasefiles(casefiles);
					dispatch.postEvent(detentionResponseEvent);
				   }
			   }
			   
		   }
	   }
   }
   
   /**
    * @param event
    * @roseuid 44C8E0DB02F0
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 44C8E0DB02FD
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 44C8E0DB02FF
    */
   public void update(Object anObject) 
   {
    
   } 
}
