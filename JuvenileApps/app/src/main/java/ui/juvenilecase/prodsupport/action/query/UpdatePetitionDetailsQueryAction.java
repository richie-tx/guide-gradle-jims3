package ui.juvenilecase.prodsupport.action.query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.JuvenileCasefileOffenseCodeResponseEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.productionsupport.UpdateProductionSupportPetitionDetailsEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ProductionSupportControllerServiceNames;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import pd.security.PDSecurityHelper;
import ui.juvenilecase.form.ProdSupportForm;

public class UpdatePetitionDetailsQueryAction extends LookupDispatchAction
{
    private Logger log = Logger.getLogger("UpdatePetitionDetailsQueryAction");

    protected Map<String, String> getKeyMethodMap()
    {
	Map<String, String> keyMap = new HashMap<String, String>();
	keyMap.put("button.updateRecord", "updatePetitionDetail");
	keyMap.put("button.updateOffense", "updateOffense");
	keyMap.put("button.petitionUpdate", "returnSelect");


	return keyMap;
    }

    /**
     * 
     */
    public ActionForward returnSelect(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
	ProdSupportForm regform = (ProdSupportForm) form;

	Collection petitionList = regform.getPetitionDetails();
	Iterator<PetitionResponseEvent> iter = petitionList.iterator();
	while (iter.hasNext())
	{
	    PetitionResponseEvent resp = (PetitionResponseEvent) iter.next();
	    String referralOID	= regform.getReferralOID();
	    System.out.println("referralOID:" + referralOID);
	    if (referralOID.equals("")){
		referralOID = request.getParameter("altOID");
	    }
	    
	    if (resp.getOID().equals(referralOID))
	    {

		List<PetitionResponseEvent> tempList = new ArrayList<PetitionResponseEvent>();
		tempList.add(resp);
		regform.setPetitionDetails(new ArrayList(tempList));

		// set response back on form
		regform.setPetitionAllegation(resp.getPetitionAllegation());
		regform.setPetitionAmended(resp.getAmend());
		regform.setNewPetitionDate(resp.getPetition_Date());
		regform.setNewReferralNum( resp.getReferralNum());
		regform.setPetitionNum(resp.getPetitionNum());
		regform.setPetitionSeverity(resp.getSeverity());
		regform.setPetitionStatus(resp.getPetitionStatus());
		regform.setPetitionType(resp.getPetitionType());
		regform.setSequenceNumber(resp.getSequenceNum());
		regform.setTerminationDate(resp.getTerminationDate());
		regform.setNewTerminationDate(resp.getTermination_Date());
		regform.setTerminationCreateDate(resp.getTerminationCreateDate());
		regform.setNewTerminationCreateDate(resp.getTermination_CreateDate());
		regform.setCJISNumber(resp.getPetCJISNum());	
		regform.setDPSCode(resp.getDPSCode());
		break;
	    }
//		else{
//		
//		// error
//		regform.setMsg("Failed to retrieve record for update.");
//		regform.setPetitionDetails(null);
//		return mapping.findForward("error");
//	    }
	}

	regform.setLastChangeDate(new SimpleDateFormat("MM/dd/yyyy").format(DateUtil.getCurrentDate()));
	regform.setLastChangeUser(PDSecurityHelper.getLogonId().toUpperCase());

	return mapping.findForward("success");

    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
   /* public ActionForward updateOffense(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
	ProdSupportForm psform = (ProdSupportForm) form;

	String newOffenseCd = psform.getAssignmentId();

	Iterator<JuvenileCasefileOffenseCodeResponseEvent> offenseIter = psform.getOffenseCodes().iterator();
	while (offenseIter.hasNext())
	{

	    JuvenileCasefileOffenseCodeResponseEvent offenseCode = offenseIter.next();
	    if (offenseCode.getAlphaCode().equalsIgnoreCase(newOffenseCd))
	    {

		psform.setPetitionAllegation(newOffenseCd);
		psform.setPetitionSeverity(offenseCode.getSeverity());
		break;
	    }
	}
	return mapping.findForward("success");
    }*/

    /**
     * @param form
     * @throws Exception
     */
    public ActionForward updatePetitionDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {

	ProdSupportForm psform = (ProdSupportForm) form;
	psform.setMsg("");
	String returnVal = "updatesuccess";
	boolean anyError = false;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	List<PetitionResponseEvent> details = (List) psform.getPetitionDetails();
	PetitionResponseEvent originalPetition = (PetitionResponseEvent) details.get(0);
	
	if( StringUtils.isNotEmpty(psform.getAssignmentId()) ){
	    psform.setPetitionAllegation(psform.getAssignmentId());
	}

	if (((originalPetition.getPetition_Date() == null && psform.getNewPetitionDate() == null || originalPetition.getPetition_Date() != null && originalPetition.getPetition_Date().isEmpty() && psform.getNewPetitionDate() == null) || (originalPetition.getPetition_Date() != null && psform.getNewPetitionDate() != null && originalPetition.getPetition_Date().equalsIgnoreCase(psform.getNewPetitionDate())))
	&& ((originalPetition.getJuvenileNum() == null && psform.getJuvenileId() == null || originalPetition.getJuvenileNum() != null && originalPetition.getJuvenileNum().isEmpty() && psform.getJuvenileId() == null) || (originalPetition.getJuvenileNum() != null && psform.getJuvenileId() != null && originalPetition.getJuvenileNum().equalsIgnoreCase(psform.getJuvenileId())))
	&& ((originalPetition.getPetitionAllegation() == null && psform.getPetitionAllegation() == null || originalPetition.getPetitionAllegation() != null && originalPetition.getPetitionAllegation().isEmpty() && psform.getPetitionAllegation() == null) || (originalPetition.getPetitionAllegation() != null && psform.getPetitionAllegation() != null && originalPetition.getPetitionAllegation().equalsIgnoreCase(psform.getPetitionAllegation())))	
	&& ((originalPetition.getAmend() == null && psform.getPetitionAmended().isEmpty() || originalPetition.getAmend() != null && originalPetition.getAmend().isEmpty() && psform.getPetitionAmended() == null) || (originalPetition.getAmend() != null && psform.getPetitionAmended() != null && originalPetition.getAmend().equalsIgnoreCase(psform.getPetitionAmended())))
	&& ((originalPetition.getReferralNum() == null && psform.getNewReferralNum() == null || originalPetition.getReferralNum() != null && originalPetition.getReferralNum().isEmpty() && psform.getNewReferralNum() == null) || (originalPetition.getReferralNum() != null && psform.getNewReferralNum() != null && originalPetition.getReferralNum().equalsIgnoreCase(psform.getNewReferralNum())))
	&& ((originalPetition.getPetitionNum() == null && psform.getPetitionNum() == null || originalPetition.getPetitionNum() != null && originalPetition.getPetitionNum().isEmpty() && psform.getPetitionNum() == null) || (originalPetition.getPetitionNum() != null && psform.getPetitionNum() != null && originalPetition.getPetitionNum().equalsIgnoreCase(psform.getPetitionNum()))) 
	&& ((originalPetition.getPetitionStatus() == null && psform.getPetitionStatus() == null || originalPetition.getPetitionStatus() != null && originalPetition.getPetitionStatus().isEmpty() && psform.getPetitionStatus() == null) || (originalPetition.getPetitionStatus() != null && psform.getPetitionStatus() != null && originalPetition.getPetitionStatus().equalsIgnoreCase(psform.getPetitionStatus()))) 
	&& ((originalPetition.getPetitionType() == null && psform.getPetitionType() == null || originalPetition.getPetitionType() != null && originalPetition.getPetitionType().isEmpty() && psform.getPetitionType() == null) || (originalPetition.getPetitionType() != null && psform.getPetitionType() != null && originalPetition.getPetitionType().equalsIgnoreCase(psform.getPetitionType()))) 
	&& ((originalPetition.getSequenceNum() == null && psform.getSequenceNumber() == null || originalPetition.getSequenceNum() != null && originalPetition.getSequenceNum().isEmpty() && psform.getSequenceNumber() == null) || (originalPetition.getSequenceNum() != null && psform.getSequenceNumber() != null && originalPetition.getSequenceNum().equalsIgnoreCase(psform.getSequenceNumber())))
	&& ((originalPetition.getSeverity() == null && psform.getPetitionSeverity() == null || originalPetition.getSeverity() != null && originalPetition.getSeverity().isEmpty() && psform.getPetitionSeverity() == null) || (originalPetition.getSeverity() != null && psform.getPetitionSeverity() != null && originalPetition.getSeverity().equalsIgnoreCase(psform.getPetitionSeverity())))
	&& ((originalPetition.getPetCJISNum() == null && psform.getCJISNumber() == null || originalPetition.getPetCJISNum() != null && originalPetition.getPetCJISNum().isEmpty() && psform.getCJISNumber() == null) || (originalPetition.getPetCJISNum() != null && psform.getCJISNumber() != null && originalPetition.getPetCJISNum().equalsIgnoreCase(psform.getCJISNumber())))
	&& ((originalPetition.getDPSCode() == null && psform.getDPSCode() == null || originalPetition.getDPSCode() != null && originalPetition.getDPSCode().isEmpty() && psform.getDPSCode() == null) || (originalPetition.getDPSCode() != null && psform.getDPSCode() != null && originalPetition.getDPSCode().equalsIgnoreCase(psform.getDPSCode())))
	&& ((originalPetition.getTermination_Date() == null && psform.getNewTerminationDate() == null || originalPetition.getTermination_Date() != null && originalPetition.getTermination_Date().isEmpty() && psform.getNewTerminationDate() == null) || (originalPetition.getTermination_Date() != null && psform.getNewTerminationDate() != null && originalPetition.getTermination_Date().equalsIgnoreCase(psform.getNewTerminationDate())))
	&& ((originalPetition.getTermination_CreateDate() == null && psform.getNewTerminationCreateDate() == null || originalPetition.getTerminationCreateDate() != null && originalPetition.getTermination_CreateDate().isEmpty() && psform.getNewTerminationCreateDate() == null) || 
		(originalPetition.getTermination_CreateDate() != null && psform.getNewTerminationCreateDate() != null && originalPetition.getTermination_CreateDate().equalsIgnoreCase(psform.getNewTerminationCreateDate()))))
	    
	{
	    psform.setMsg("No Changes Detected for Update.");
	    anyError = true;
    	    returnVal = "error";
	}
	else
	{
	    if( !psform.getPetitionAmended().isEmpty() ){
		
		if(!psform.getPetitionAmended().matches("[0-9]")){
		    
		   psform.setMsg("Valid options are 0-9. Please correct Amendment.");
		   anyError = true;
            	   returnVal = "error";
		}
	    }
	    
	    if( !psform.getNewPetitionDate().equals(originalPetition.getPetition_Date())){
		
		if( psform.getNewPetitionDate()!= null && psform.getReferralDate()!= null){
		    
		    Date newDate = DateUtil.stringToDate(psform.getNewPetitionDate(), DateUtil.DATE_FMT_1);
		    Date origDate = DateUtil.stringToDate(psform.getReferralDate(), DateUtil.DATE_FMT_1);
			
			if(newDate.before(origDate)){
			    
			   psform.setMsg("Petition Date cannot be less than current referral date.");
			   anyError = true;
	            	   returnVal = "error";
			}
		}
		
	    }
	    
	    if(StringUtils.isEmpty( psform.getNewReferralNum() )){
		
		psform.setMsg("Referral Number cannot be  blank.");
		anyError = true;
         	returnVal = "error";
	    }
	    
	    if( !originalPetition.getPetitionAllegation().equalsIgnoreCase( psform.getPetitionAllegation() )){
		
		if( originalPetition.getSeverity().equalsIgnoreCase( psform.getPetitionSeverity() )){
		
		Iterator<JuvenileCasefileOffenseCodeResponseEvent> offenseIter = psform.getOffenseCodes().iterator();
		while (offenseIter.hasNext())
		{

		    JuvenileCasefileOffenseCodeResponseEvent offenseCode = offenseIter.next();
		    if (offenseCode.getAlphaCode().equalsIgnoreCase( psform.getPetitionAllegation() ))
		    {

			//psform.setPetitionAllegation(psform.getPetitionAllegation());
			psform.setPetitionSeverity(offenseCode.getSeverity());
			break;
		    }
		}
	      }
		
	    }
	    
	    if( originalPetition.getSeverity() != psform.getPetitionSeverity())
	    {
		originalPetition.setSeverity(psform.getPetitionSeverity());
		    psform.setPetitionDetails(details);
	    }
	    
	    if( anyError ){
		
		return mapping.findForward(returnVal);
	    }
		
	    UpdateProductionSupportPetitionDetailsEvent updatePetitionRequestEvent = (UpdateProductionSupportPetitionDetailsEvent) 
	    		EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTPETITIONDETAILS);

                updatePetitionRequestEvent.setReferralNum(psform.getNewReferralNum());
                updatePetitionRequestEvent.setStatus(psform.getPetitionStatus());
                updatePetitionRequestEvent.setType(psform.getPetitionType());
                updatePetitionRequestEvent.setPetitionDate(DateUtil.stringToDate(psform.getNewPetitionDate(), DateUtil.DATE_FMT_1));
                updatePetitionRequestEvent.setPetitionNum(psform.getPetitionNum());
                updatePetitionRequestEvent.setAmend(psform.getPetitionAmended());
                updatePetitionRequestEvent.setOffenseCodeId(psform.getPetitionAllegation());
                updatePetitionRequestEvent.setSeverity(psform.getPetitionSeverity());
                updatePetitionRequestEvent.setSequenceNum(psform.getSequenceNumber());
                updatePetitionRequestEvent.setTerminationDate(DateUtil.stringToDate(psform.getNewTerminationDate(), DateUtil.DATE_FMT_1));
                updatePetitionRequestEvent.setTerminationCreateDate(DateUtil.stringToDate(psform.getNewTerminationCreateDate(), DateUtil.DATE_FMT_1));
                updatePetitionRequestEvent.setOID(psform.getOID());
                updatePetitionRequestEvent.setCJISNum(psform.getCJISNumber());
                updatePetitionRequestEvent.setDPSCode(psform.getDPSCode());
                updatePetitionRequestEvent.setJuvenileNum(psform.getJuvenileId());
                
                if("0".equals( psform.getHiddenForward() )){
                    
                    MessageUtil.postRequest(updatePetitionRequestEvent);
                    psform.setMsg("The petition detail record is updated successfully.");     
                    psform.setHiddenForward("9");
                    updatePetitionRequestEvent = null;
                }
                

	}
	return mapping.findForward(returnVal);

    }

    public boolean isDefined(String value)
    {
	return (value != null && value.length() > 0);
    }

    

}
