package ui.juvenilecase.prodsupport.action.update;
//created by NM
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByJuvNumRefNumEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.facility.GetJuvenileFacilityDetailsEvent;
import messaging.juvenilecase.GetCasefileWithReferralsEvent;
import messaging.juvenilecase.GetJJSCourtEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralDetailResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilewarrant.GetJJSPetitionsEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.productionsupport.GetProductionSupportJuvenileReferralsEvent;
import messaging.productionsupport.UpdateCourtRecordSealOnReferralSealEvent;
import messaging.productionsupport.UpdateDetentionCourtRecordSealOnReferralSealEvent;
import messaging.productionsupport.UpdatePetitionRecordSealOnReferralSealEvent;
import messaging.productionsupport.UpdateProductionSupportJuvenileSealEvent;
import messaging.productionsupport.UpdateProductionSupportReferralSealEvent;
import messaging.productionsupport.UpdateReleaseRecordOnReferralSealEvent;
import messaging.productionsupport.reply.ProductionSupportJuvenileReferralResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileDetentionHearingControllerServiceNames;
import naming.JuvenileFacilityControllerServiceNames;
import naming.JuvenileWarrantControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import pd.codetable.Code;
import pd.juvenilecase.JJSHeader;
import pd.juvenilecase.JuvenileCasefile;
import ui.action.JIMSBaseAction;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;


public class PerformJuvenileReferralUnSealAction extends JIMSBaseAction
        {
        
            private Logger log = Logger.getLogger("PerformJuvenileSealAction");
        
            public ActionForward unsealJuvenile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
            {
        
        	ProdSupportForm regform = (ProdSupportForm) form;
        	ActionErrors errors = new ActionErrors();
        	regform.setAction("unSealJuv");
        	String juvenileId = regform.getFromJuvenileId();
        	/*String sealComments = regform.getSealComments();
        	String sealedDate   = regform.getSealedDate();*/
        
        	if (juvenileId == null || juvenileId.equals(""))
        	{
        	    regform.setMsg("PerformJuvenileReferralUnSealAction.java  - JuvenileID was null.");
        	    return (mapping.findForward("error"));
        	}
        
        	log.info("BEGIN JUVENILE UN-SEAL - " + " LogonId: "
        		+ SecurityUIHelper.getLogonId());
        
        	/**
        	 * First, log all the IDs of the dependent files that will be deleted
        	 * automatically via RI
        	 **/
        	writeLogEntries(regform, SecurityUIHelper.getLogonId(), juvenileId);
        
        
        	/** Update Juvenile from S.JUVENILE to JUVENILE record (and children by default) **/
        	UpdateProductionSupportJuvenileSealEvent updateJuvenileSealEvent = (UpdateProductionSupportJuvenileSealEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTJUVENILESEAL);
        
        	log.info(" SEALED USING JUVENILE NUM: " + juvenileId + "Logon Id: "
        		+ SecurityUIHelper.getLogonId());
        	updateJuvenileSealEvent.setJuvenileId(juvenileId);
        	updateJuvenileSealEvent.setAction("unSealJuv");
        	updateJuvenileSealEvent.setLastUpdateId(SecurityUIHelper.getLogonId());
        	CompositeResponse event = MessageUtil.postRequest(updateJuvenileSealEvent);
        
        	/** Log for auditing purposes **/
        	log.info("SCENARIO JUVENILE SEAL - Performed a JUVENILE SEAL for juvenileID="
        		+ regform.getFromJuvenileId()
        		+ "LogonId: "
        		+ SecurityUIHelper.getLogonId());
        
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
        			updateCourtSeal.setAction("unSealJuv");
        			CompositeResponse compRes = MessageUtil.postRequest(updateCourtSeal);
        			Object errRes = MessageUtil.filterComposite(compRes, ErrorResponseEvent.class);
        			if (errRes != null)
        			{
        
        			    log.info("UPDATE COURT RECORD: "
        				    + SecurityUIHelper.getLogonId());
        
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
        	    disp.postEvent(detentionEvt);
        	    CompositeResponse detentionResp = (CompositeResponse) disp.getReply();
        
        	    List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(detentionResp, DocketEventResponseEvent.class);
        
        	    if (docketResponses != null && !docketResponses.isEmpty())
        	    {
        		Iterator<DocketEventResponseEvent> crtdetdktRespEvtsItr = docketResponses.iterator();
        		while (crtdetdktRespEvtsItr.hasNext())
        		{
        		    DocketEventResponseEvent crtDetDocResp = (DocketEventResponseEvent) crtdetdktRespEvtsItr.next();
        		    if (crtDetDocResp != null)
        		    {
        			String docketId = crtDetDocResp.getDocketEventId();
        			UpdateDetentionCourtRecordSealOnReferralSealEvent updateDetCourtSeal = (UpdateDetentionCourtRecordSealOnReferralSealEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEDETENTIONCOURTRECORDSEALONREFERRALSEAL);
        			updateDetCourtSeal.setDocketId(docketId);
        			updateDetCourtSeal.setAction("unSealJuv");
        
        			CompositeResponse compRes = MessageUtil.postRequest(updateDetCourtSeal);
        			Object errRes = MessageUtil.filterComposite(compRes, ErrorResponseEvent.class);
        			if (errRes != null)
        			{
        			    log.info("UPDATE DETENTION COURT RECORD: " + SecurityUIHelper.getLogonId());
        
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
        	    disp.postEvent(petitionEvent);
        	    CompositeResponse compositeResp = MessageUtil.postRequest(petitionEvent);
        	    List<PetitionResponseEvent> petResponses = MessageUtil.compositeToList(compositeResp, PetitionResponseEvent.class);
        	    if (petResponses != null && !petResponses.isEmpty())
        	    {
        		Iterator<PetitionResponseEvent> petRespEvtsItr = petResponses.iterator();
        		while (petRespEvtsItr.hasNext())
        		{
        		    PetitionResponseEvent petResp = (PetitionResponseEvent) petRespEvtsItr.next();
        		    if (petResp != null)
        		    {
        			String oid = petResp.getOID();
        			UpdatePetitionRecordSealOnReferralSealEvent updatePetSeal = (UpdatePetitionRecordSealOnReferralSealEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPETITIONRECORDSEALONREFERRALSEAL);
        			updatePetSeal.setOID(oid);
        			updatePetSeal.setAction("unSealJuv");
        			CompositeResponse compRes = MessageUtil.postRequest(updatePetSeal);
        			Object errRes = MessageUtil.filterComposite(compRes, ErrorResponseEvent.class);
        			if (errRes != null)
        			{
        			    log.info("UPDATE PETITION RECORD: " + SecurityUIHelper.getLogonId());
        			    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Petition Records failed to Seal."));
        			    saveErrors(request, errors);
        			    return mapping.findForward("listError");
        			}
        
        		    }
        		}
        	    }
        	    //referrals un-sealing
        	    
        	    String juvenileNum = regform.getJuvenileId();
        	    String reffNum = "";
        	  //get all the sealed referral and loop through and unseal them all
        	    //ArrayList juvprogrefs = regform.getJuvprogrefs();
        	    ArrayList juvprogrefs = regform.getProgramReferrals(); //BUG 182442
        	    if (juvprogrefs != null){
        	    for (int i = 0; i < juvprogrefs.size(); i++)
        		{
        		    ProductionSupportJuvenileReferralResponseEvent refResp = (ProductionSupportJuvenileReferralResponseEvent) juvprogrefs.get(i);
        		    if (refResp.getRectype().equalsIgnoreCase("S.REFERRAL") || refResp.getRectype().equalsIgnoreCase("I.REFERRAL")) //BUG 182442 I.REFERRAL Added
        		    {
        			reffNum = refResp.getReferralNum();
        			 ProductionSupportJuvenileReferralResponseEvent record = retrieveRecord(regform, reffNum);
        	        	    UpdateProductionSupportReferralSealEvent updateReferralSeal = (UpdateProductionSupportReferralSealEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTREFERRALSEAL);

        	        	    updateReferralSeal.setJuvenileId(juvenileNum);
        	        	    updateReferralSeal.setReferralNum(reffNum);
        	        	    updateReferralSeal.setOID(record.getReferralOID());
        	        	    updateReferralSeal.setAction("unSealJuv");

        	        	    CompositeResponse compResp = MessageUtil.postRequest(updateReferralSeal);
        	        	     errResp = MessageUtil.filterComposite(compResp, ErrorResponseEvent.class);
        	        	    if (errResp != null)
        	        	    {
        	        		log.info("UPDATE JUVENILE REFERRAL MASTER: " + SecurityUIHelper.getLogonId());
        	        		 errors = new ActionErrors();
        	        		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Referral failed to un-seal."));
        	        		saveErrors(request, errors);
        	        		return mapping.findForward("listError");
        	        	    }
        		    }

        		}
        	    }
        	   
        	    
        	}
        	return mapping.findForward("success");
        
            }
        
            @Override
            protected Map getKeyMethodMap()
            {
        	Map keyMap = new HashMap();
        	keyMap.put("button.UnSealJuvenile", "unsealJuvenile");
        	keyMap.put("button.UnSealReferral", "unSealReferral");
        
        	return keyMap;
            }
        
            @Override
            protected void addButtonMapping(Map keyMap)
            {
        	// TODO Auto-generated method stub
        
            }
        
            /**
             * log all the records that are going to be deleted with casefile
             * 
             * @param regform
             * @param logonid
             */
            private void writeLogEntries(ProdSupportForm regform, String logonid, String casefileId)
            {
        
        	if (regform.getCasefiles() != null)
        	{
        	    Iterator iter = regform.getCasefiles().iterator();
        	    while (iter.hasNext())
        	    {
        		iter.next();
        		log.info("**** START LOGGING THE DELETE PROCESS FOR CASEFILE ****");
        		log.info("DELETE - JCCASEFILE ID: " + regform.getFromCasefile()
        			+ " TO casefileID=" + casefileId + "Logon Id: "
        			+ SecurityUIHelper.getLogonId());
        	    }
        	}
        
            }
            
            public ActionForward unSealReferral(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
            {
        	ProdSupportForm regform = (ProdSupportForm) aForm;
        	String forward = "success";
        	String reffNum = "";
        	String juvenileNum = "";
        	reffNum = regform.getReferralNum();
                
        	regform.setAction("unSealRef");
        	
        	if (reffNum != null && !reffNum.equals(""))
        	{
        	    juvenileNum = regform.getFromJuvenileId().trim();
        	    ArrayList juvReferrals = null;
        	          	    
        	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

        	    ProductionSupportJuvenileReferralResponseEvent record = retrieveRefRecord(regform, reffNum);
        	    UpdateProductionSupportReferralSealEvent updateReferralSeal = (UpdateProductionSupportReferralSealEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTREFERRALSEAL);

        	    String sealComments = regform.getSealComments();
        	    String sealDate = regform.getSealedDate();
        	    updateReferralSeal.setJuvenileId(juvenileNum);
        	    updateReferralSeal.setReferralNum(reffNum);
        	    updateReferralSeal.setSealedComments(sealComments);
        	    updateReferralSeal.setSealedDate(sealDate);
        	    updateReferralSeal.setOID(record.getReferralOID());
        	    updateReferralSeal.setAction("unSealRef");
        	    CompositeResponse compResp = MessageUtil.postRequest(updateReferralSeal);
        	    Object errResp = MessageUtil.filterComposite(compResp, ErrorResponseEvent.class);
        	    if (errResp != null)
        	    {
        		log.info("UPDATE JUVENILE REFERRAL MASTER: " + SecurityUIHelper.getLogonId());
        		ActionErrors errors = new ActionErrors();
        		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Referral failed to un-seal."));
        		saveErrors(aRequest, errors);
        		return aMapping.findForward("listError");
        	    }
        	    else
        	    {
        		//court records sealing
        		IDispatch disp = EventManager.getSharedInstance(EventManager.REQUEST);
        		GetJJSCourtEvent courtEvt = (GetJJSCourtEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSCOURT);
        		courtEvt.setJuvenileNumber(juvenileNum);
        		courtEvt.setReferralNumber(reffNum);
        		disp.postEvent(courtEvt);
        		CompositeResponse response = (CompositeResponse) disp.getReply();
        		//Court Response.
        		List<DocketEventResponseEvent> crtdktRespEvts = MessageUtil.compositeToList(response, DocketEventResponseEvent.class);

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
        			    updateCourtSeal.setAction("unSealRef");
        			    CompositeResponse compRes = MessageUtil.postRequest(updateCourtSeal);
        			    Object errRes = MessageUtil.filterComposite(compRes, ErrorResponseEvent.class);
        			    if (errRes != null)
        			    {
        				log.info("UPDATE COURT RECORD: " + SecurityUIHelper.getLogonId());
        				ActionErrors errors = new ActionErrors();
        				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Court Records failed to Seal."));
        				saveErrors(aRequest, errors);
        				return aMapping.findForward("listError");
        			    }

        			}
        		    }
        		}
        		//detention records sealing
        		
        		GetJJSCLDetentionByJuvNumRefNumEvent detentionEvt = (GetJJSCLDetentionByJuvNumRefNumEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYJUVNUMREFNUM);
        		detentionEvt.setJuvenileNumber(juvenileNum);
        		detentionEvt.setReferralNumber(reffNum);
        		dispatch.postEvent(detentionEvt);
        		CompositeResponse detentionResp = (CompositeResponse) dispatch.getReply();

        		List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(detentionResp, DocketEventResponseEvent.class);
        		
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
        			    updateDetCourtSeal.setAction("unSealRef");
        			    CompositeResponse compRes = MessageUtil.postRequest( updateDetCourtSeal );
        				Object errRes = MessageUtil.filterComposite(compRes, ErrorResponseEvent.class);
        				if (errRes != null){
        				    
        					log.info("UPDATE DETENTION COURT RECORD: " + SecurityUIHelper.getLogonId());
        					
        					ActionErrors errors = new ActionErrors();
        					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Detention Records failed to Seal."));
        					saveErrors(aRequest, errors);
        					return aMapping.findForward("listError");
        				}					
        			   
        			}
        		    }
        		}
        		//petition records sealing
        		GetJJSPetitionsEvent petitionEvent = (GetJJSPetitionsEvent) EventFactory.getInstance(JuvenileWarrantControllerServiceNames.GETJJSPETITIONS);
        		petitionEvent.setJuvenileNum(juvenileNum);
        		petitionEvent.setReferralNum(reffNum);
        		dispatch.postEvent(petitionEvent);
        		CompositeResponse compositeResp = MessageUtil.postRequest(petitionEvent);
        		List<PetitionResponseEvent> petResponses = MessageUtil.compositeToList(compositeResp, PetitionResponseEvent.class);
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
        			    updatePetSeal.setAction("unSealRef");
        			    CompositeResponse compRes = MessageUtil.postRequest( updatePetSeal );
        			    Object errRes = MessageUtil.filterComposite(compRes, ErrorResponseEvent.class);
            			    if (errRes != null)
            			    {
            				log.info("UPDATE PETITION RECORD: " + SecurityUIHelper.getLogonId());
            				ActionErrors errors = new ActionErrors();
            				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Petition Records failed to Seal."));
            				saveErrors(aRequest, errors);
            				return aMapping.findForward("listError");
            			    }					
        			   
        			}
        		    }
        		}
        		
        		ActionMessages messageHolder = new ActionMessages();
        		messageHolder.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("prompt.referralNumberUnSealed",reffNum, juvenileNum));
        		aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
        		saveMessages(aRequest, messageHolder);
        		regform.setMsg("Referral# "+reffNum+" has been un-sealed for "+juvenileNum+"");
        		//return submitSearch(aMapping, aForm, aRequest, aResponse);
        		return aMapping.findForward("success");
        	    }
        	}
        	return aMapping.findForward(forward);
            }

            private ProductionSupportJuvenileReferralResponseEvent retrieveRecord(ProdSupportForm regform, String reffNum)
            {

        	//ArrayList juvprogrefs = regform.getJuvprogrefs(); // commented for  182442
        	ArrayList juvprogrefs = regform.getProgramReferrals(); //need both i.and s. //BUG 182442
        	ProductionSupportJuvenileReferralResponseEvent record = null;
        	for (int i = 0; i < juvprogrefs.size(); i++)
        	{
        	    ProductionSupportJuvenileReferralResponseEvent resp = (ProductionSupportJuvenileReferralResponseEvent) juvprogrefs.get(i);
        	    if (reffNum.equals(resp.getReferralNum()))
        	    {
        		record = (ProductionSupportJuvenileReferralResponseEvent) juvprogrefs.get(i);
        	    }

        	}
        	return record;

            }
            
            private ProductionSupportJuvenileReferralResponseEvent retrieveRefRecord(ProdSupportForm regform, String reffNum)
            {

        	ArrayList juvprogrefs = regform.getJuvprogrefs(); 
        	ProductionSupportJuvenileReferralResponseEvent record = null;
        	for (int i = 0; i < juvprogrefs.size(); i++)
        	{
        	    ProductionSupportJuvenileReferralResponseEvent resp = (ProductionSupportJuvenileReferralResponseEvent) juvprogrefs.get(i);
        	    if (reffNum.equals(resp.getReferralNum()))
        	    {
        		record = (ProductionSupportJuvenileReferralResponseEvent) juvprogrefs.get(i);
        	    }

        	}
        	return record;

            }



}
