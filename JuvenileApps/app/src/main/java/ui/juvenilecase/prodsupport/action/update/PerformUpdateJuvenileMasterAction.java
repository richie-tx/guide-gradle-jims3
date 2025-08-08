package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.criminal.reply.JuvenileAdmitReasonsResponseEvent;
import messaging.codetable.criminal.reply.JuvenileFacilityResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.productionsupport.GetProdSupportJJSJuvenileEvent;
import messaging.productionsupport.UpdateProductionSupportJuvenileMasterEvent;
import messaging.productionsupport.reply.ProductionSupportFacilityDetentionResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.form.ProdSupportForm;

/**
 * @author rcarter
 * 
 * 	Perform Update of Facility Detention Fields
 */

public class PerformUpdateJuvenileMasterAction extends Action {

	private Logger log = Logger.getLogger("PerformUpdateFacilityDetentionAction");
	public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) aForm;	
		
		UpdateProductionSupportJuvenileMasterEvent updateJJSDetentionDetailEvent = (UpdateProductionSupportJuvenileMasterEvent)
			EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTJUVENILEMASTER);		
		updateJJSDetentionDetailEvent = setUpdateDetails(regform);
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(updateJJSDetentionDetailEvent);
		regform.setMsg("");
		
		ArrayList juveniledetails = retrieveJuvenileDetails(regform.getJuvenileId());
		regform.setJuveniles(juveniledetails);
		return aMapping.findForward("success");
	}
	
	
	
	/**
	 * 
	 * @param regform
	 * @return
	 */
	private UpdateProductionSupportJuvenileMasterEvent setUpdateDetails(ProdSupportForm regform)
	{
	    JuvenileProfileDetailResponseEvent resp = (JuvenileProfileDetailResponseEvent)regform.getJuveniles().get(0);
	    UpdateProductionSupportJuvenileMasterEvent updateJuv = new UpdateProductionSupportJuvenileMasterEvent();
	    updateJuv.setJuvenileId(regform.getJuvenileId());
	    updateJuv.setRaceId(regform.getRaceId());
	    updateJuv.setOriginalRaceId(regform.getOriginalRaceId());
	    updateJuv.setSexId(regform.getSexId());
	    updateJuv.setBirthDate(DateUtil.stringToDate(regform.getMasterDOB(), DateUtil.DATE_FMT_1));
	    updateJuv.setRealDOB(DateUtil.stringToDate(regform.getRealDOB(), DateUtil.DATE_FMT_1));
	    updateJuv.setJuvenileSsn(regform.getNewSSN().getSSN());
	    updateJuv.setLastReferral(regform.getLastReferral());
	    //if(regform.getOldStatusId()!=null && !regform.getOldStatusId().equals(""))
		//updateJuv.setMasterStatusId(regform.getOldStatusId());
	    //else
		//updateJuv.setMasterStatusId(null);
	    if (regform.getStatusId() != null 
		    && !"".equals( regform.getStatusId() ) ){
		updateJuv.setMasterStatusId(regform.getStatusId() );
	    } else {
		updateJuv.setMasterStatusId(null);
	    }
	    
	    if(regform.getCaseNotePart1()!=null && !regform.getCaseNotePart1().equals(""))
		updateJuv.setCaseNotePart1(regform.getCaseNotePart1());
	    else
		updateJuv.setCaseNotePart1(null);
	    if(regform.getCheckedOutTo()!=null && !regform.getCheckedOutTo().equals(""))
		updateJuv.setCheckedOutTo(regform.getCheckedOutTo());
	    else
		updateJuv.setCheckedOutTo(null);
	    if(regform.getCheckedOutDate()!=null && !regform.getCheckedOutDate().equals(""))
		updateJuv.setCheckedOutDate(DateUtil.stringToDate(regform.getCheckedOutDate(), DateUtil.DATE_FMT_1));
	    else
		updateJuv.setCheckedOutDate(null);
	    if(regform.getDetentionFacilityId()!=null && !regform.getDetentionFacilityId().equals(""))
		updateJuv.setDetentionFacilityId(regform.getDetentionFacilityId());
	    else
		updateJuv.setDetentionFacilityId(null);
	    if(regform.getDetentionStatusId()!=null && !regform.getDetentionStatusId().equals(""))
		updateJuv.setDetentionStatusId(regform.getDetentionStatusId());
	    else
		updateJuv.setDetentionStatusId(null);
	    if(regform.getPurgeBoxNum()!=null && !regform.getPurgeBoxNum().equals(""))
		updateJuv.setPurgeBoxNum(regform.getPurgeBoxNum());
	    else
		updateJuv.setPurgeBoxNum(null);
	    if(regform.getPurgeFlag()!=null && !regform.getPurgeFlag().equals(""))
		updateJuv.setPurgeFlag(regform.getPurgeFlag());
	    else
		updateJuv.setPurgeFlag(null);
	    if(regform.getPurgeSerNum()!=null && !regform.getPurgeSerNum().equals(""))
		updateJuv.setPurgeSerNum(regform.getPurgeSerNum());
	    else
		updateJuv.setPurgeSerNum(null);
	    if(regform.getPurgeDate()!=null && !regform.getPurgeDate().equals(""))
		updateJuv.setPurgeDate(regform.getPurgeDate());
	    else
		updateJuv.setPurgeDate(null);
	    updateJuv.setJuvenileFName(regform.getJuvenileFName());
	    updateJuv.setJuvenileLName(regform.getJuvenileLName());
	    updateJuv.setJuvenileMName(regform.getJuvenileMName());
	    updateJuv.setJuvenileNameSuffix(regform.getJuvenileNameSuffix());
	    //US 190449 
	    updateJuv.setRecType(regform.getRectype());
	    updateJuv.setAgeAtDeath(regform.getAgeAtDeath());
	    updateJuv.setSealComments(regform.getSealComments());
	    updateJuv.setSealedDate(regform.getSealedDate());
	    updateJuv.setLivewith(regform.getLivewith());
	    updateJuv.setCauseOfDeath(regform.getCauseOfDeath());
	    updateJuv.setDeathVerification(regform.getDeathVerification());
	    updateJuv.setDateOfDeath(regform.getDateOfDeath());
	    updateJuv.setJuvExcluded(regform.getJuvExcluded());
	    return updateJuv;
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
	private ArrayList retrieveJuvenileDetails( String juvenileId ){

		/**
		 * Search for JCMAYSIDETAIL.
		 */
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetProdSupportJJSJuvenileEvent juvProfileEvent = (GetProdSupportJJSJuvenileEvent)
			EventFactory.getInstance( ProductionSupportControllerServiceNames.GETPRODSUPPORTJJSJUVENILE ) ;
		juvProfileEvent.setJuvenileId(juvenileId);

		ArrayList juveniles = (ArrayList) MessageUtil.postRequestListFilter(juvProfileEvent, JuvenileProfileDetailResponseEvent.class);

		if (juveniles!=null && juveniles.size() > 0){
			//maysiEventsList.addAll(maysiEvents);
		}
		return juveniles;	
	}
	
}
