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
import messaging.productionsupport.UpdateProductionSupportJuvenileSealEvent;
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


public class PerformJuvenileSealAction extends Action
{

    private Logger log = Logger.getLogger("PerformJuvenileSealAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;		
		ActionErrors errors = new ActionErrors();
		
		String juvenileId = regform.getFromJuvenileId();
		String sealComments = regform.getSealComments();
		String sealedDate   = regform.getSealedDate();
		
		if (juvenileId == null || juvenileId.equals("")){
			regform.setMsg("PerformJuvenileSealAction.java (63) - JuvenileID was null.");
			return (mapping.findForward("error"));
		}
		
		log.info("BEGIN JUVENILE SEAL - " + " LogonId: " + SecurityUIHelper.getLogonId());
		
		/** First, log all the IDs of the dependent files that will be deleted automatically via RI **/ 
		writeLogEntries(regform, SecurityUIHelper.getLogonId(), juvenileId);
		
		/* Display error messages via pop out box.  User must confirm each error message.
		
		1)	The juvenile must not have an active facility booking.  Locate the juveniles facility header record.
		If the juvenile’s JUVENILE_FACILITY_ADMISSION_HEADER.FacilityStatus is populated, display on screen notice:  Juvenile is currently booked in a facility.  Juvenile cannot be SEALED.

		2)	All associated referral numbers must be CLOSED to process the sealing.
		If the juvenile status = CLOSED, this indicates all referral numbers have been closed. If Juvenile Status is not equal to CLOSED, then display on screen notice:  
		Juvenile has one or more ACTIVE referral numbers.  All referrals must be CLOSED to process sealing.

		3)	All associated supervisions/casefiles must be CLOSED to process the sealing.  Locate the juvenile’s supervision history.
		If the juvenile has one or more supervisions not equal to CLOSED or CLOSING APPROVED {JUVENILE_CASEFILE.CaseStatus <> CLOSED}, then display on screen notice:  
		Juvenile has one or more supervisions not equal to CLOSED.  All casefiles must be CLOSED to process sealing.

		Casefile status = CLOSING PENDING or CLOSING SUBMITTED is considered active and must not be considered as a closed option.
		*/

		
		/** Update Juvenile to S.JUVENILE record (and children by default)	**/
		UpdateProductionSupportJuvenileSealEvent updateJuvenileSealEvent = (UpdateProductionSupportJuvenileSealEvent) 
			EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTJUVENILESEAL) ;		
		
		log.info(" SEALED USING JUVENILE NUM: " + juvenileId + "Logon Id: " + 
				SecurityUIHelper.getLogonId());
		updateJuvenileSealEvent.setJuvenileId(juvenileId);
		updateJuvenileSealEvent.setSealComments(sealComments);
		updateJuvenileSealEvent.setSealedDate(sealedDate);
		updateJuvenileSealEvent.setLastUpdateId( SecurityUIHelper.getLogonId() );
		CompositeResponse event =  MessageUtil.postRequest(updateJuvenileSealEvent);

			
		/** Log for auditing purposes **/
		log.info("SCENARIO JUVENILE SEAL - Performed a JUVENILE SEAL for juvenileID="
				+ regform.getFromJuvenileId() + "LogonId: " + SecurityUIHelper.getLogonId());
		
		regform.setMsg("");
		Object errResp = MessageUtil.filterComposite(event, ErrorResponseEvent.class);
		if (errResp != null)
		{
			
        		/*log.info("SCENARIO JUVENILE SEAL - Performed a JUVENILE SEAL for juvenileID="
        				+ regform.getFromJuvenileId() + "LogonId: " + SecurityUIHelper.getLogonId());		
        		regform.setMsg("");*/
		    	log.info("SEAL JUVENILE MASTER: " + SecurityUIHelper.getLogonId());
			//ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile failed to seal."));
			saveErrors(request, errors);
			return mapping.findForward("error");
		}
		//seal associated records task 129601
		else
		{
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
				    updateCourtSeal.setAction("seal");
				    CompositeResponse compRes = MessageUtil.postRequest(updateCourtSeal);
				    Object errRes = MessageUtil.filterComposite(compRes, ErrorResponseEvent.class);
				    if (errRes != null)
				    {

					log.info("UPDATE COURT RECORD: " + SecurityUIHelper.getLogonId());

					//ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Court Records failed to Seal"));
					saveErrors(request, errors);
					return mapping.findForward("error");
				    }

				}
			    }
			}
			//detention records sealing
			
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
				    updateDetCourtSeal.setAction("seal");
				    
				    CompositeResponse compRes = MessageUtil.postRequest( updateDetCourtSeal );
					Object errRes = MessageUtil.filterComposite(compRes, ErrorResponseEvent.class);
					if (errRes != null){
					    
						log.info("UPDATE DETENTION COURT RECORD: " + SecurityUIHelper.getLogonId());
						
						//ActionErrors errors = new ActionErrors();
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Detention Records failed to Seal."));
						saveErrors(request, errors);
						return mapping.findForward("error");
					}					
				   
				}
			    }
			}
			//petition records sealing
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
				    updatePetSeal.setAction("seal");
				    CompositeResponse compRes = MessageUtil.postRequest( updatePetSeal );
					Object errRes = MessageUtil.filterComposite(compRes, ErrorResponseEvent.class);
					if (errRes != null){
					    
						log.info("UPDATE PETITION RECORD: " + SecurityUIHelper.getLogonId());
						
						//ActionErrors errors = new ActionErrors();
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Petition Records failed to Seal."));
						saveErrors(request, errors);
						return mapping.findForward("listError");
					}					
				   
				}
			    }
			}
		}
//
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
