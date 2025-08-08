package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.criminal.reply.JuvenileAdmitReasonsResponseEvent;
import messaging.codetable.criminal.reply.JuvenileFacilityResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.productionsupport.GetProductionSupportFacilityDetentionEvent;
import messaging.productionsupport.GetProductionSupportJuvenileReferralsEvent;
import messaging.productionsupport.UpdateProdSupportJJSDetentionEvent;
import messaging.productionsupport.reply.ProductionSupportFacilityDetentionResponseEvent;
import messaging.productionsupport.reply.ProductionSupportJuvenileReferralResponseEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ProductionSupportControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import pd.juvenilecase.referral.JJSOffense;
import ui.juvenilecase.UIJuvenileTransferredOffenseReferralHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author rcarter
 * 
 * 	Perform Update of Facility Detention Fields
 */

public class PerformUpdateFacilityDetentionAction extends Action {

	private Logger log = Logger.getLogger("PerformUpdateFacilityDetentionAction");
	public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) aForm;
		ProductionSupportFacilityDetentionResponseEvent resp = (ProductionSupportFacilityDetentionResponseEvent)regform.getFacilityDetentionList().get(0);
		if( regform.getCurrentOffense()!=null && !regform.getCurrentOffense().equals("") && !regform.getCurrentOffense().equalsIgnoreCase( resp.getCurrentOffense()))
		{
        		boolean currentOffenseFine=false;
        		if( (regform.getCurrentReferral()!=null && !regform.getCurrentReferral().equals(""))
        			||
        		(resp.getCurrentReferralNum()!=null && !resp.getCurrentReferralNum().equals(""))
        			)
        		{
                		
                		currentOffenseFine = isCurrentOffenseFine(regform);
                		
        		}
        		
        		if(!currentOffenseFine)
        		{
        		    ActionErrors errors = new ActionErrors();
                	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "The Current Offense can only be replaced by an offense associated to the Current Referral. Please modify"));
                	    saveErrors(aRequest, errors);
                	    return aMapping.findForward(UIConstants.ERROR);
        		}		
		}
		UpdateProdSupportJJSDetentionEvent updateJJSDetentionDetailEvent = (UpdateProdSupportJJSDetentionEvent)
			EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODSUPPORTJJSDETENTION);		
		updateJJSDetentionDetailEvent = setUpdateDetails(regform);
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(updateJJSDetentionDetailEvent);
		regform.setMsg("");
		
		ArrayList singleDetentionRecordList = retrieveSingleDetentionRecord(regform.getDetentionId(), SecurityUIHelper.getLogonId());
		loadCodeDescriptions(singleDetentionRecordList);
		regform.setFacilityDetentionList(singleDetentionRecordList);
		ProductionSupportFacilityDetentionResponseEvent newResp = (ProductionSupportFacilityDetentionResponseEvent)singleDetentionRecordList.get(0);
		regform.setFacilityStatus(newResp.getFacilityStatusCode());					
		regform.setReturnDate(DateUtil.dateToString(newResp.getReturnDate(), DateUtil.DATE_FMT_1));
		regform.setReturnTime(newResp.getReturnTime());
		regform.setDetainedDate(DateUtil.dateToString(newResp.getDetainedDate(), DateUtil.DATE_FMT_1));
		regform.setFloorLocation(newResp.getLocationFloorNumber());
		regform.setUnitLocation(newResp.getLocationUnit());
		regform.setRoomLocation(newResp.getRoomNumber());
		regform.setMultipleOccupancyUnit(newResp.getMultipleOccupanyUnit());
		regform.setLocker(newResp.getLockerNumber());
		regform.setAdmitAuthority(newResp.getAdmittedAuthorizeJPO());
		regform.setDetentionId(newResp.getDetentionId());
		regform.setRiskAnalysisId(newResp.getRiskAnalysisId());
		newResp.setAdmittedTime(JuvenileFacilityHelper.getHoursMinsFromTime(newResp.getAdmittedTime()));
		newResp.setReleaseTime(JuvenileFacilityHelper.getHoursMinsFromTime(newResp.getReleaseTime()));
		newResp.setReturnTime(JuvenileFacilityHelper.getHoursMinsFromTime(newResp.getReturnTime()));
		newResp.setOriginalAdmittedTime(JuvenileFacilityHelper.getHoursMinsFromTime(newResp.getOriginalAdmittedTime()));		
		/*regform.setAdmittedTime(JuvenileFacilityHelper.getHoursMinsFromTime(newResp.getAdmittedTime()));
		regform.setReleaseTime(JuvenileFacilityHelper.getHoursMinsFromTime(newResp.getReleaseTime()));
		regform.setReturnTime(JuvenileFacilityHelper.getHoursMinsFromTime(newResp.getReturnTime()));
		regform.setOriginalAdmittedTime(JuvenileFacilityHelper.getHoursMinsFromTime(newResp.getOriginalAdmittedTime()));*/
		return aMapping.findForward("success");
	}
	
	/**
	 * Retrieve single detention record based on unique detention record id
	 * @param detentionId
	 * @param logonId
	 * @return
	 */
	private ArrayList retrieveSingleDetentionRecord(String detentionId, String logonId){

		/**
		 * Search for FacilityDetention.
		 */
		log.info("Retrieve Single Detention Record - Facility Detention Detail ID: " + detentionId + " LogonId: " + logonId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetProductionSupportFacilityDetentionEvent getFacilityDetentionEvent = (GetProductionSupportFacilityDetentionEvent)
				EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTFACILITYDETENTION);
		getFacilityDetentionEvent.setHeaderId(detentionId);
		dispatch.postEvent(getFacilityDetentionEvent);		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		Collection<ProductionSupportFacilityDetentionResponseEvent> detentionEventsList =
			MessageUtil.compositeToCollection(compositeResponse, ProductionSupportFacilityDetentionResponseEvent.class);
		ArrayList<ProductionSupportFacilityDetentionResponseEvent> facilityDetentionList =  new ArrayList<ProductionSupportFacilityDetentionResponseEvent>();
		facilityDetentionList.addAll(detentionEventsList);		
		
		return facilityDetentionList;
		
	}
	
	/**
	 * 
	 * @param regform
	 * @return
	 */
	private UpdateProdSupportJJSDetentionEvent setUpdateDetails(ProdSupportForm regform)
	{
	    UpdateProdSupportJJSDetentionEvent updateDet = new UpdateProdSupportJJSDetentionEvent();
	    updateDet.setJuvenileNum(regform.getJuvenileId());
	    updateDet.setDetentionId(regform.getDetentionId());	    
	    if(regform.getBookingReferral()!=null && !regform.getBookingReferral().equals(""))
		updateDet.setBookingReferral(regform.getBookingReferral());
	    if(regform.getBookingSupervisionNum()!=null && !regform.getBookingSupervisionNum().equals(""))
		updateDet.setBookingSupervisionNum(regform.getBookingSupervisionNum());	 
	    if(regform.getCurrentReferral()!=null && !regform.getCurrentReferral().equals(""))
		updateDet.setCurrentReferral(regform.getCurrentReferral());
	    if(regform.getCurrentOffense()!=null && !regform.getCurrentOffense().equals(""))
		updateDet.setCurrentOffense(regform.getCurrentOffense());
	    if(regform.getCurrentSupervisionNum()!=null && !regform.getCurrentSupervisionNum().equals(""))
		updateDet.setCurrentSupervisionNum(regform.getCurrentSupervisionNum());
	    if(regform.getActiveFacilityCd()!=null && !regform.getActiveFacilityCd().equals("")) //if the facility has changed
		updateDet.setDetainedFacility(regform.getActiveFacilityCd());
	   // updateDet.setfac
	    if(regform.getNewadmitReasonCd()!=null && !regform.getNewadmitReasonCd().equals(""))
		updateDet.setReasonCode(regform.getNewadmitReasonCd());
	    if(regform.getAdmitDate()!=null && !regform.getAdmitDate().equals(""))
		updateDet.setAdmitDate(DateUtil.stringToDate(regform.getAdmitDate(), DateUtil.DATE_FMT_1));
	    if(regform.getAdmitTime()!=null && !regform.getAdmitTime().equals(""))
		updateDet.setAdmitTime(regform.getAdmitTime());
	    /*if(regform.getNewTransferToFacility()!=null && !regform.getNewTransferToFacility().equals(""))*/ //Commented for BUG 176350
	    if(regform.getNewTransferToFacility()!=null)
		updateDet.setTransferToFacility(regform.getNewTransferToFacility());
	    if(regform.getAdmitBy()!=null && !regform.getAdmitBy().equals(""))
		updateDet.setAdmitBy(regform.getAdmitBy());
	    updateDet.setAdmitAuthority(regform.getAdmitAuthority());
	    if(regform.getValuablesReceipt()!=null && !regform.getValuablesReceipt().equals(""))
		updateDet.setValuablesReceipt(regform.getValuablesReceipt());
	    updateDet.setLocker(regform.getLocker());	  
	    updateDet.setFloorLocation(regform.getFloorLocation());
	    updateDet.setUnitLocation(regform.getUnitLocation());
	    updateDet.setRoomLocation(regform.getRoomLocation());
	    updateDet.setAdmitAuthority(regform.getAdmitAuthority());
	    updateDet.setMultipleOccupyUnit(regform.getMultipleOccupancyUnit());	  
	    if(regform.getOriginalAdmitDate()!=null && !regform.getOriginalAdmitDate().equals(""))
		updateDet.setOriginalAdmitDate(DateUtil.stringToDate(regform.getOriginalAdmitDate(), DateUtil.DATE_FMT_1));
	    else
		updateDet.setOriginalAdmitDate(null);
	    if(regform.getOriginalAdmitTime()!=null && !regform.getOriginalAdmitTime().equals(""))
		updateDet.setOriginalAdmitTime(regform.getOriginalAdmitTime());	
	    else
		updateDet.setOriginalAdmitTime(null);
	    updateDet.setDetainedDate(DateUtil.stringToDate(regform.getDetainedDate(), DateUtil.DATE_FMT_1));
	    String logonid = SecurityUIHelper.getJIMSLogonId();
	    updateDet.setLcuser(logonid);
	    updateDet.setLcDate(DateUtil.getCurrentDate());
	    //allowed the user to empty the release details as part of 92675
	    //if(regform.getOutcome()!=null && !regform.getOutcome().equals(""))
	 	updateDet.setOutcome(regform.getOutcome());
	    //if(regform.getReleaseDate()!=null && !regform.getReleaseDate().equals(""))
	 	updateDet.setReleaseDate(DateUtil.stringToDate(regform.getReleaseDate(), DateUtil.DATE_FMT_1));
	    if(regform.getReleaseTime()==null || regform.getReleaseTime().equals(""))
		updateDet.setReleaseTime(null);
	    else
	 	updateDet.setReleaseTime(regform.getReleaseTime());
	    //if(regform.getReleasedBy()!=null && !regform.getReleasedBy().equals(""))
	 	updateDet.setReleasedBy(regform.getReleasedBy());
	    //if(regform.getReleasedTo()!=null && !regform.getReleasedTo().equals(""))
	 	updateDet.setReleasedTo(regform.getReleasedTo());
	    //if(regform.getReleaseAuthority()!=null && !regform.getReleaseAuthority().equals(""))
	 	updateDet.setReleaseAuthority(regform.getReleaseAuthority());
	    //if(regform.getReleaseReason()!=null && !regform.getReleaseReason().equals(""))
	 	updateDet.setReleaseReason(regform.getReleaseReason());	   
	    if(regform.getReturnDate()!=null && !regform.getReturnDate().equals(""))
	 	updateDet.setReturnDate(DateUtil.stringToDate(regform.getReturnDate(), DateUtil.DATE_FMT_1));
	    if(regform.getReturnTime()!=null && !regform.getReturnTime().equals(""))
	 	updateDet.setReturnTime(regform.getReturnTime());
	    if(regform.getReturnReason()!=null && !regform.getReturnReason().equals(""))
	 	updateDet.setReturnReason(regform.getReturnReason());
	    if(regform.getTempReleaseReason()!=null && !regform.getTempReleaseReason().equals(""))
	 	updateDet.setTempReleaseReason(regform.getTempReleaseReason());
	    if(regform.getFacilitySeqNum()!=null && !regform.getFacilitySeqNum().equals(""))
		updateDet.setFacilitySeqNum(regform.getFacilitySeqNum());
	    //added as part of 99361
	    if(regform.getFacilityStatus()==null || regform.getFacilityStatus().equals(""))
		updateDet.setFacilityStatus(null);
	    else
		updateDet.setFacilityStatus(regform.getFacilityStatus());
	    if(regform.getReturnStatus()==null || regform.getReturnStatus().equals(""))
		updateDet.setReturnStatus(null);
	    else
		updateDet.setReturnStatus(regform.getReturnStatus());
	    //
	    
	    
	    if (regform.getOriginalAdmitOID() == null || regform.getOriginalAdmitOID().equals(""))
		    updateDet.setOriginalAdmitOID(null);
		else
		    updateDet.setOriginalAdmitOID(regform.getOriginalAdmitOID());

		if (regform.getAvgCostPerDay() == null || regform.getAvgCostPerDay().equals(""))
		    updateDet.setAvgCostPerDay(null);
		else
		    updateDet.setAvgCostPerDay(regform.getAvgCostPerDay());

		if (regform.getDetainedByInd() == null || regform.getDetainedByInd().equals(""))
		    updateDet.setDetainedByInd(null);
		else
		    updateDet.setDetainedByInd(regform.getDetainedByInd());

		if (regform.getTjjdFundingSrc() == null || regform.getTjjdFundingSrc().equals(""))
		    updateDet.setTjjdFundingSrc(null);
		else
		    updateDet.setTjjdFundingSrc(regform.getTjjdFundingSrc());

		if (regform.getOriginaladmitSEQNUM() == null || regform.getOriginaladmitSEQNUM().equals(""))
		    updateDet.setOriginaladmitSEQNUM(null);
		else
		    updateDet.setOriginaladmitSEQNUM(regform.getOriginaladmitSEQNUM());

		if (regform.getPostAdmitOID() == null || regform.getPostAdmitOID().equals(""))
		    updateDet.setPostAdmitOID(null);
		else
		    updateDet.setPostAdmitOID(regform.getPostAdmitOID());

		if (regform.getTjjdFacilityId() == 0)
		    updateDet.setTjjdFacilityId(0); // Assuming zero is your null-equivalent fallback for int
		else
		    updateDet.setTjjdFacilityId(regform.getTjjdFacilityId());

		if (regform.getCustodyfirstName() == null || regform.getCustodyfirstName().equals(""))
		    updateDet.setCustodyfirstName(null);
		else
		    updateDet.setCustodyfirstName(regform.getCustodyfirstName());

		if (regform.getCustodylastName() == null || regform.getCustodylastName().equals(""))
		    updateDet.setCustodylastName(null);
		else
		    updateDet.setCustodylastName(regform.getCustodylastName());

	    
	    
	    if(regform.getRiskAnalysisId() !=null && !regform.getRiskAnalysisId().equals(""));
		updateDet.setRiskAnalysisId(regform.getRiskAnalysisId());
	    
	    return updateDet;
	}
	
	/**
	 * find and load descriptions for each code shown on the form
	 * @param detentionResponseEventList
	 */
	private void loadCodeDescriptions(ArrayList<ProductionSupportFacilityDetentionResponseEvent> detentionResponseEventList){
		for(ProductionSupportFacilityDetentionResponseEvent responseEvent : detentionResponseEventList){	
			// active facility code description
			JuvenileFacilityResponseEvent activeFacilityResponseEvent =  JuvenileFacilityHelper.getFacilityByCode(responseEvent.getFacilityCode());
			if(activeFacilityResponseEvent != null){
				responseEvent.setFacilityName(activeFacilityResponseEvent.getDescription());
			}else{
				responseEvent.setFacilityName("");
			}
			// admittance reason code description
			JuvenileAdmitReasonsResponseEvent admitResponseEvent =  JuvenileFacilityHelper.getAdmitReasonByCode(responseEvent.getAdmittanceReasonCode());
			if(admitResponseEvent != null){
				responseEvent.setAdmittanceReasonCodeDesc(admitResponseEvent.getDescription());
			}else{
				responseEvent.setAdmittanceReasonCodeDesc("");
			}
		}

	}
	
	private boolean isCurrentOffenseFine(ProdSupportForm regform){
	    boolean currentOffenseIsFine = false;
	    String referralType		 = "";
	    
	    GetJuvenileCasefileOffensesEvent offEvent = new GetJuvenileCasefileOffensesEvent();
	    offEvent.setJuvenileNum(regform.getJuvenileId());
	    
	    if(regform.getCurrentReferral()!=null && !regform.getCurrentReferral().equals("")){
		    offEvent.setReferralNum(regform.getCurrentReferral());
	    } else {
		    offEvent.setReferralNum(((ProductionSupportFacilityDetentionResponseEvent)regform.getFacilityDetentionList().get(0)).getCurrentReferralNum());
	    }
	    
	    GetProductionSupportJuvenileReferralsEvent getJuvenileRerralsEvent = (GetProductionSupportJuvenileReferralsEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTJUVENILEREFERRALS);
	    getJuvenileRerralsEvent.setJuvenileId(offEvent.getJuvenileNum());
	    getJuvenileRerralsEvent.setReferralNum(offEvent.getReferralNum());
	    CompositeResponse juvenileReferralsResp = MessageUtil.postRequest(getJuvenileRerralsEvent);
	    Collection <ProductionSupportJuvenileReferralResponseEvent> juvReferrals = MessageUtil.compositeToCollection(juvenileReferralsResp, ProductionSupportJuvenileReferralResponseEvent.class);
	    
	    Iterator<ProductionSupportJuvenileReferralResponseEvent> referralIter = juvReferrals.iterator();

	    while ( referralIter.hasNext() ) {
		referralType = referralIter.next().getReferralTypeInd();
		break;
	    }
	    
	    System.out.println("Referral type: " + referralType );
	    if ( "IC".equals( referralType )
		    || "TR".equals(referralType) ) {
		List<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenses = UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(regform.getJuvenileId());
		  for ( JuvenileCasefileTransferredOffenseResponseEvent transferredOffense : transferredOffenses ) {
		      System.out.println("Transferred offense: " + transferredOffense.getOffenseCode() );
		      System.out.println("Current  offense: " + regform.getCurrentOffense());
		      if ( offEvent.getReferralNum().equals(transferredOffense.getReferralNum())
			      && transferredOffense.getOffenseCode().equals(regform.getCurrentOffense())){
			  currentOffenseIsFine=true;
			break;
		      }
		  }
	    
	    } else {
		
		Iterator<JJSOffense> offenseItr = JJSOffense.findAll(offEvent);
		
		while(offenseItr.hasNext())
		{	
		    JJSOffense offense = (JJSOffense)offenseItr.next();
		    if(offense.getOffenseCodeId().equalsIgnoreCase(regform.getCurrentOffense()))
		    {
			currentOffenseIsFine=true;
			break;
		    }
		}
	    }
	    
	  
	    
	    return  currentOffenseIsFine;
	}
	
}
