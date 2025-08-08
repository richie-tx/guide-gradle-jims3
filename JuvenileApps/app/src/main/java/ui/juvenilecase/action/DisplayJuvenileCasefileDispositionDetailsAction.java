//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\referral\\action\\DisplayJuvenileCasefileDispositionDetailsAction.java

package ui.juvenilecase.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingDispositionResponseEvent;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.form.PetitionDetailsForm;


public class DisplayJuvenileCasefileDispositionDetailsAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 467FB567008F
    */
   public DisplayJuvenileCasefileDispositionDetailsAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 467AD34E03E8
    */
   public ActionForward view(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		PetitionDetailsForm form = (PetitionDetailsForm) aForm;
   		if (form.getDispositions() != null) {
   			Collection coll = form.getDispositions();
   			Iterator iter = coll.iterator();
   			while(iter.hasNext())
   			{
   				JuvenileOffenderTrackingDispositionResponseEvent resp = (JuvenileOffenderTrackingDispositionResponseEvent)iter.next();
   				if(resp.getSeqNum().equals(form.getSelectedSeqNum()) && resp.getDaLogNum().equals(form.getSelectedDALogNum()))
   				{
   					PetitionDetailsForm.Disposition disp = form.getDispositionRec();
   					disp.setDispositionDate(resp.getDispositionDate());
   					disp.setDisposition(resp.getDisposition());
   					disp.setJudgementDate(resp.getJudgementDate());
   					disp.setJudgement(resp.getJudgement());
   					disp.setProbationMonths(resp.getProbationTime());
   					disp.setDeviationReason(resp.getDeviationReason());
   					disp.setTycMonths(resp.getTYCTime());
   					disp.setGuidelineSanction(resp.getGuidelineSanction());
   					disp.setAssignedSanction(resp.getAssignedSanction());
   				}
   			}
		}
   		return aMapping.findForward("success");   		
   }
   
   public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}

	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.BACK);
		return forward;
	}
   
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();		
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		keyMap.put("button.view", "view");
		
		return keyMap;
	}
}
