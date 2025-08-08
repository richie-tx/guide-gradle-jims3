package pd.productionsupport.transactions;


import java.util.Iterator;

import pd.juvenilecase.JJSFacility;
import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;
import messaging.facility.GetJuvenileFacilityDetailsEvent;
import messaging.productionsupport.UpdateProdSupportJJSDetentionEvent;
import messaging.productionsupport.UpdateProductionSupportAssignmentEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.DateUtil;

public class UpdateProdSupportJJSDetentionCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public UpdateProdSupportJJSDetentionCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
	   System.out.println("updateProductionSupportAssignment");
	   UpdateProdSupportJJSDetentionEvent updateEvent = (UpdateProdSupportJJSDetentionEvent) event;
	   
	   GetJuvenileFacilityDetailsEvent getDetentionResultsEvent = new GetJuvenileFacilityDetailsEvent();
	   getDetentionResultsEvent.setDetentionRecordId(updateEvent.getDetentionId());
	   Iterator facIter = JJSFacility.findAll(getDetentionResultsEvent);
	   if(facIter.hasNext())
	   {
	       JJSFacility fac = (JJSFacility)facIter.next();
	       	    if(updateEvent.getBookingReferral()!=null && !updateEvent.getBookingReferral().equals(""))
			fac.setReferralNumber(updateEvent.getBookingReferral());
		    if(updateEvent.getBookingSupervisionNum()!=null && !updateEvent.getBookingSupervisionNum().equals(""))
			fac.setBookingSupervisionNum(updateEvent.getBookingSupervisionNum());	 
		    if(updateEvent.getCurrentReferral()!=null && !updateEvent.getCurrentReferral().equals(""))
			fac.setCurrentReferral(updateEvent.getCurrentReferral());
		    if(updateEvent.getCurrentOffense()!=null && !updateEvent.getCurrentOffense().equals(""))
			fac.setCurrentOffense(updateEvent.getCurrentOffense());
		    if(updateEvent.getCurrentSupervisionNum()!=null && !updateEvent.getCurrentSupervisionNum().equals(""))
			fac.setCurrentSupervisionNum(updateEvent.getCurrentSupervisionNum());
		    if(updateEvent.getDetainedFacility()!=null && !updateEvent.getDetainedFacility().equals("")) //if the facility has changed
			fac.setDetainedFacility(updateEvent.getDetainedFacility());
		   // updateDet.setfac
		    if(updateEvent.getReasonCode()!=null && !updateEvent.getReasonCode().equals(""))
			fac.setAdmitReason(updateEvent.getReasonCode());
		    if(updateEvent.getAdmitDate()!=null && !updateEvent.getAdmitDate().equals(""))
			fac.setAdmitDate(updateEvent.getAdmitDate());
		    if(updateEvent.getAdmitTime()!=null && !updateEvent.getAdmitTime().equals(""))
			fac.setAdmitTime(updateEvent.getAdmitTime());
		    /*if(updateEvent.getTransferToFacility()!=null && !updateEvent.getTransferToFacility().equals(""))*/ //Commented for BUG 176350 
		    if(updateEvent.getTransferToFacility()!=null)
			fac.setTransferToFacility(updateEvent.getTransferToFacility());
		    if(updateEvent.getAdmitBy()!=null && !updateEvent.getAdmitBy().equals(""))
			fac.setAdmittedByJPO(updateEvent.getAdmitBy());
		    fac.setAdmitAuthority(updateEvent.getAdmitAuthority());
		    if(updateEvent.getValuablesReceipt()!=null && !updateEvent.getValuablesReceipt().equals(""))
			fac.setReceiptNumber(updateEvent.getValuablesReceipt());
		    fac.setLockerNumber(updateEvent.getLocker());	  
		    fac.setFloorNum(updateEvent.getFloorLocation());
		    fac.setUnit(updateEvent.getUnitLocation());
		    fac.setRoomNum(updateEvent.getRoomLocation());
		    fac.setAdmitAuthority(updateEvent.getAdmitAuthority());
		    fac.setMultipleOccupyUnit(updateEvent.getMultipleOccupyUnit());		  
		    //if(updateEvent.getOriginalAdmitDate()!=null && !updateEvent.getOriginalAdmitDate().equals(""))
			fac.setOriginalAdmitDate(updateEvent.getOriginalAdmitDate());	
		    //if(updateEvent.getOriginalAdmitTime()!=null && !updateEvent.getOriginalAdmitTime().equals(""))
			fac.setOriginalAdmitTime(updateEvent.getOriginalAdmitTime());		
		    fac.setDetainedDate(updateEvent.getDetainedDate());
		    fac.setLcUser(updateEvent.getLcuser());
		    fac.setLastChangeDate(updateEvent.getLcDate());
		    //if(updateEvent.getOutcome()!=null && !updateEvent.getOutcome().equals(""))
		 	fac.setOutcome(updateEvent.getOutcome());
		    //if(updateEvent.getReleaseDate()!=null && !updateEvent.getReleaseDate().equals(""))
		 	fac.setReleaseDate(updateEvent.getReleaseDate());
		    //if(updateEvent.getReleaseTime()!=null && !updateEvent.getReleaseTime().equals(""))
		 	fac.setReleaseTime(updateEvent.getReleaseTime());
		    //if(updateEvent.getReleasedBy()!=null && !updateEvent.getReleasedBy().equals(""))
		 	fac.setReleaseBy(updateEvent.getReleasedBy());
		     //if(updateEvent.getReleasedTo()!=null && !updateEvent.getReleasedTo().equals("")){ //Bug 168607
		 	fac.setReleaseTo(updateEvent.getReleasedTo());
		     //}
		    //if(updateEvent.getReleaseAuthority()!=null && !updateEvent.getReleaseAuthority().equals(""))
		 	fac.setReleaseAuthorizedBy(updateEvent.getReleaseAuthority());
		    //if(updateEvent.getReleaseReason()!=null && !updateEvent.getReleaseReason().equals(""))
		 	fac.setReleaseReason(updateEvent.getReleaseReason());		  
		    if(updateEvent.getReturnDate()!=null && !updateEvent.getReturnDate().equals(""))
		 	fac.setReturnDate(updateEvent.getReturnDate());
		    if(updateEvent.getReturnTime()!=null && !updateEvent.getReturnTime().equals(""))
		 	fac.setReturnTime(updateEvent.getReturnTime());
		    if(updateEvent.getReturnReason()!=null && !updateEvent.getReturnReason().equals(""))
		 	fac.setReturnReason(updateEvent.getReturnReason());
		    if(updateEvent.getTempReleaseReason()!=null && !updateEvent.getTempReleaseReason().equals(""))
		 	fac.setTempReleaseReasonCd(updateEvent.getTempReleaseReason());
		    if(updateEvent.getFacilitySeqNum()!=null && !updateEvent.getFacilitySeqNum().equals(""))
		 	fac.setFacilitySequenceNumber(updateEvent.getFacilitySeqNum());
		    
		    if(updateEvent.getRiskAnalysisId() !=null && !updateEvent.getRiskAnalysisId().equals(""));
		 	fac.setRiskAnalysisId(updateEvent.getRiskAnalysisId());
		 	
		    if(updateEvent.getJuvenileNum()!=null && !updateEvent.getJuvenileNum().equals(""))
		 	fac.setJuvenileNumber(updateEvent.getJuvenileNum());
		    
		    // added as part of 99361
		    fac.setDetentionStatus(updateEvent.getFacilityStatus());
		    fac.setReturnStatus(updateEvent.getReturnStatus());		    
		    //
		    
		    fac.setOriginalAdmitOID(updateEvent.getOriginalAdmitOID());
		    fac.setAvgCostPerDay(updateEvent.getAvgCostPerDay());
		    fac.setDetainedByInd(updateEvent.getDetainedByInd());
		    fac.setTjjdFundingSrc(updateEvent.getTjjdFundingSrc());
		    fac.setOriginaladmitSEQNUM(updateEvent.getOriginaladmitSEQNUM());
		    fac.setPostAdmitOID(updateEvent.getPostAdmitOID());
		    fac.setTjjdFacilityId(updateEvent.getTjjdFacilityId());
		    fac.setCustodyfirstName(updateEvent.getCustodyfirstName());
		    fac.setCustodylastName(updateEvent.getCustodylastName());

		    
		    IHome home= new Home();
		    home.bind(fac);
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
