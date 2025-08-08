// Source file:
// C:\\views\\MJCW\\app\\src\\ui\\juvenilecase\\programreferral\\action\\DisplayProgramReferralListAction.java

package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.programreferral.reply.ProgramReferralResponseEvent;
import naming.PDJuvenileCaseConstants;
import naming.ProgramReferralConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileProfileForm;
import ui.juvenilecase.programreferral.UIProgramReferralHelper;
import ui.juvenilecase.programreferral.form.ProgramReferralForm;
import ui.security.SecurityUIHelper;

public class DisplayJuvenileProfileProgramReferralListAction extends JIMSBaseAction {

	/**
	 * @roseuid 463BA5730398
	 */
	public DisplayJuvenileProfileProgramReferralListAction() {

	}

	/** 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward displayReferralList(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ProgramReferralForm form = (ProgramReferralForm) aForm;
		String forward = null;
		form.clearAll();
				
		String juvenileNum = null;
		juvenileNum = aRequest.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM);
		if (juvenileNum != null) {
			UIJuvenileHelper.populateJuvenileProfileHeaderForm(aRequest, juvenileNum);
		}
		//Get Juvenile Number from Header Form		
		JuvenileProfileForm headerForm = UIJuvenileHelper.getHeaderForm(aRequest);
	  	if(headerForm != null && headerForm.getJuvenileNum() != null && headerForm.getJuvenileNum().trim().length() > 0)
	  	{
	  		juvenileNum = headerForm.getJuvenileNum();
	  		List referrals = UIProgramReferralHelper.getJuvenileProgramReferrals(juvenileNum);
	  		
	  		if(referrals != null && !referrals.isEmpty()){
		  		List activeReferrals = new ArrayList();
		  		List closedReferrals = new ArrayList();
		  		List allReferralList = new ArrayList();	  			  		

	  			for(int i=0;i<referrals.size();i++){
	  				ProgramReferralResponseEvent resp = (ProgramReferralResponseEvent) referrals.get(i);
	  				if(ProgramReferralConstants.CLOSED.equals(resp.getReferralStatusCd())){
	  					closedReferrals.add(resp);
	  				}else{
	  					activeReferrals.add(resp);
	  				}
	  			}
	  			
	  			if (activeReferrals!=null){
		  			Collections.sort(activeReferrals,ProgramReferralResponseEvent.CasefileIdComparator);
		  			Iterator iter = activeReferrals.iterator();
		  			
		  			ProgramReferralForm.CasefileReferral casefileReferral=null;
		  			while (iter.hasNext()){
		  				ProgramReferralResponseEvent resp = (ProgramReferralResponseEvent)iter.next();
		  				if (casefileReferral!=null && resp.getCasefileId().equals(casefileReferral.getCasefileId())){
		  					casefileReferral.getProgramReferralList().add(resp);
		  				}else{	  					
		  					casefileReferral = new ProgramReferralForm.CasefileReferral();
		  					casefileReferral.setCasefileId(resp.getCasefileId());
		  					casefileReferral.setSupervisionName(resp.getSupervisionName());		  					
		  					casefileReferral.getProgramReferralList().add(resp);	  					
							allReferralList.add(casefileReferral);	  							  					
		  				}
		  			}
		  		}
		    	form.setActiveReferralList(allReferralList);
	  			
		    	if(closedReferrals != null && !closedReferrals.isEmpty()){
			    	Collections.sort(closedReferrals);
		    	}
		    	form.setClosedReferralList(closedReferrals);
		    	forward = UIConstants.SUCCESS;
	  		} else {
	  			forward = UIConstants.SUCCESS;
	  		}
	  	}
	  	else 
	  	{
			this.saveErrors(aRequest,"error.serviceProvider.invalidUser");
			forward = UIConstants.FAILURE;	  		
	  	}		
		return aMapping.findForward(forward);
	}
	
	
	/**
	 * @param aRequest
	 * @param errorkey
	 */
	private void saveErrors(HttpServletRequest aRequest, String errorKey)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorKey, SecurityUIHelper.getLogonId()));
		saveErrors(aRequest, errors);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.link", "displayReferralList");
	}

}
