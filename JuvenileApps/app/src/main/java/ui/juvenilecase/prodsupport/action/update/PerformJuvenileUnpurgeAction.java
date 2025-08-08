package ui.juvenilecase.prodsupport.action.update;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByJuvNumRefNumEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenilecase.GetJJSCourtEvent;
import messaging.juvenilewarrant.GetJJSPetitionsEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.productionsupport.UpdateCourtRecordSealOnReferralSealEvent;
import messaging.productionsupport.UpdateDetentionCourtRecordSealOnReferralSealEvent;
import messaging.productionsupport.UpdatePetitionRecordSealOnReferralSealEvent;
import messaging.productionsupport.UpdateProductionSupportJuvenilePurgeEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileDetentionHearingControllerServiceNames;
import naming.JuvenileWarrantControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;


public class PerformJuvenileUnpurgeAction extends Action
{

    private Logger log = Logger.getLogger("PerformJuvenileSealAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;		
		ActionErrors errors = new ActionErrors();
		
		String juvenileId = regform.getFromJuvenileId();
		String purgeComments = regform.getPurgeComments();
		//String sealedDate   = regform.getSealedDate();
		String purgeSeries = regform.getPurgeSerNum();
		String purgeBox = regform.getPurgeBoxNum();
		
		if (juvenileId == null || juvenileId.equals("")){
			regform.setMsg("PerformJuvenileSealAction.java (63) - JuvenileID was null.");
			return (mapping.findForward("error"));
		}
		
		log.info("BEGIN JUVENILE UN-PURGE - " + " LogonId: " + SecurityUIHelper.getLogonId());
		
		writeLogEntries(regform, SecurityUIHelper.getLogonId(), juvenileId);
		
		UpdateProductionSupportJuvenilePurgeEvent updateJuvenilePurgeEvent = (UpdateProductionSupportJuvenilePurgeEvent) 
			EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTJUVENILEPURGE) ;		
		
		log.info(" UN-PURGED USING JUVENILE NUM: " + juvenileId + "Logon Id: " + 
				SecurityUIHelper.getLogonId());
		updateJuvenilePurgeEvent.setJuvenileId(juvenileId);
		updateJuvenilePurgeEvent.setPurgeComments(purgeComments);
		updateJuvenilePurgeEvent.setPurgeBox(purgeBox);
		updateJuvenilePurgeEvent.setPurgeSeries(purgeSeries);
		updateJuvenilePurgeEvent.setAction("unpurge");
		updateJuvenilePurgeEvent.setLastUpdateId( SecurityUIHelper.getLogonId() );
		CompositeResponse event =  MessageUtil.postRequest(updateJuvenilePurgeEvent);
		Object errResp = MessageUtil.filterComposite(event, ErrorResponseEvent.class);
		if (errResp != null)
		{
			
        		/*log.info("SCENARIO JUVENILE SEAL - Performed a JUVENILE SEAL for juvenileID="
        				+ regform.getFromJuvenileId() + "LogonId: " + SecurityUIHelper.getLogonId());		
        		regform.setMsg("");*/
		    	log.info("UN-PURGE JUVENILE MASTER: " + SecurityUIHelper.getLogonId());
			//ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile failed to Un-Purge."));
			saveErrors(request, errors);
			return mapping.findForward("error");
		}
		else
		{
		    //purge associated records
		  //court records purging
			IDispatch disp = EventManager.getSharedInstance(EventManager.REQUEST);
			GetJJSCourtEvent courtEvt = (GetJJSCourtEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSCOURT);
			courtEvt.setJuvenileNumber(juvenileId);
			//courtEvt.setReferralNumber("%");
			disp.postEvent(courtEvt);
			CompositeResponse resp = (CompositeResponse) disp.getReply();
			//Court Response.
			List<DocketEventResponseEvent> crtdktRespEvts = MessageUtil.compositeToList(resp, DocketEventResponseEvent.class);

			if (crtdktRespEvts != null && !crtdktRespEvts.isEmpty())
			{
			    Iterator<DocketEventResponseEvent> crtdktRespEvtsItr = crtdktRespEvts.iterator();
			    while (crtdktRespEvtsItr.hasNext())
			    {
				DocketEventResponseEvent crtDocResp = (DocketEventResponseEvent) crtdktRespEvtsItr.next();
				if (crtDocResp != null)
				{
				    String docketId = crtDocResp.getDocketEventId();
				    UpdateCourtRecordSealOnReferralSealEvent updateCourtSeal = (UpdateCourtRecordSealOnReferralSealEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATECOURTRECORDSEALONREFERRALSEAL);
				    updateCourtSeal.setDocketId(docketId);
				    updateCourtSeal.setAction("unpurge");
				    CompositeResponse compRes = MessageUtil.postRequest(updateCourtSeal);
				    Object errRes = MessageUtil.filterComposite(compRes, ErrorResponseEvent.class);
				    if (errRes != null)
				    {

					log.info("UPDATE COURT RECORD: " + SecurityUIHelper.getLogonId());

					//ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Court Records failed to Un-Purge"));
					saveErrors(request, errors);
					return mapping.findForward("error");
				    }

				}
			    }
			}
			//detention records purging
			
			GetJJSCLDetentionByJuvNumRefNumEvent detentionEvt = (GetJJSCLDetentionByJuvNumRefNumEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYJUVNUMREFNUM);
			detentionEvt.setJuvenileNumber(juvenileId);
			//detentionEvt.setReferralNumber(null);
			disp.postEvent(detentionEvt);
			CompositeResponse detentionResp = (CompositeResponse) disp.getReply();

			List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(detentionResp, DocketEventResponseEvent.class);
			//Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();
			
			if (docketResponses != null && !docketResponses.isEmpty())
			{
			    Iterator<DocketEventResponseEvent> crtdetdktRespEvtsItr = docketResponses.iterator();
			    while (crtdetdktRespEvtsItr.hasNext())
			    {
				DocketEventResponseEvent crtDetDocResp = (DocketEventResponseEvent) crtdetdktRespEvtsItr.next();
				if (crtDetDocResp != null)
				{
				    String docketId=crtDetDocResp.getDocketEventId();
				    UpdateDetentionCourtRecordSealOnReferralSealEvent updateDetCourtSeal = (UpdateDetentionCourtRecordSealOnReferralSealEvent) 
					EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEDETENTIONCOURTRECORDSEALONREFERRALSEAL) ;
				    updateDetCourtSeal.setDocketId(docketId);
				    updateDetCourtSeal.setAction("unpurge");
				    
				    CompositeResponse compRes = MessageUtil.postRequest( updateDetCourtSeal );
					Object errRes = MessageUtil.filterComposite(compRes, ErrorResponseEvent.class);
					if (errRes != null){
					    
						log.info("UPDATE DETENTION COURT RECORD: " + SecurityUIHelper.getLogonId());
						
						//ActionErrors errors = new ActionErrors();
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Detention Records failed to Un-Purge."));
						saveErrors(request, errors);
						return mapping.findForward("error");
					}					
				   
				}
			    }
			}
			//petition records purging
			GetJJSPetitionsEvent petitionEvent = (GetJJSPetitionsEvent) EventFactory.getInstance(JuvenileWarrantControllerServiceNames.GETJJSPETITIONS);
			petitionEvent.setJuvenileNum(juvenileId);
			//petitionEvent.setReferralNum(null);
			disp.postEvent(petitionEvent);
			CompositeResponse compositeResp = MessageUtil.postRequest(petitionEvent);
			List<PetitionResponseEvent> petResponses = MessageUtil.compositeToList(compositeResp, PetitionResponseEvent.class);
			//PetitionResponseEvent petitionResponseEvent = (PetitionResponseEvent) MessageUtil.filterComposite(compositeResp, PetitionResponseEvent.class);
			if (petResponses != null && !petResponses.isEmpty())
			{
			    Iterator<PetitionResponseEvent> petRespEvtsItr = petResponses.iterator();
			    while (petRespEvtsItr.hasNext())
			    {
				PetitionResponseEvent petResp = (PetitionResponseEvent) petRespEvtsItr.next();
				if (petResp != null)
				{
				    String oid=petResp.getOID();
				    UpdatePetitionRecordSealOnReferralSealEvent updatePetSeal = (UpdatePetitionRecordSealOnReferralSealEvent) 
					EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPETITIONRECORDSEALONREFERRALSEAL) ;
				    updatePetSeal.setOID(oid);
				    updatePetSeal.setAction("unpurge");
				    CompositeResponse compRes = MessageUtil.postRequest( updatePetSeal );
					Object errRes = MessageUtil.filterComposite(compRes, ErrorResponseEvent.class);
					if (errRes != null){
					    
						log.info("UPDATE PETITION RECORD: " + SecurityUIHelper.getLogonId());
						
						//ActionErrors errors = new ActionErrors();
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Petition Records failed to Un-Purge."));
						saveErrors(request, errors);
						return mapping.findForward("listError");
					}					
				   
				}
			    }
			}
		}
		return mapping.findForward("success");	
		
	}
	
	/**
	 * log all the records that are going to be deleted with casefile
	 * @param regform
	 * @param logonid
	 */
	private void writeLogEntries(ProdSupportForm regform, String logonid, String casefileId){
	
		if (regform.getCasefiles() != null){
			Iterator iter = regform.getCasefiles().iterator();
				while (iter.hasNext()){
					iter.next();
					log.info("**** START LOGGING THE DELETE PROCESS FOR CASEFILE ****");
					log.info("DELETE - JCCASEFILE ID: "
							+ regform.getFromCasefile() + " TO casefileID=" + casefileId + "Logon Id: " + 
							SecurityUIHelper.getLogonId());					
				}
		}
		
		
	}
}
