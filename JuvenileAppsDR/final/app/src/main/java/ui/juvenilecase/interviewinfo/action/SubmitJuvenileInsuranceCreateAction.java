package ui.juvenilecase.interviewinfo.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.interviewinfo.UpdateJuvenileInsuranceEvent;
//import messaging.interviewinfo.reply.JuvenileBenefitResponseEvent;
import messaging.interviewinfo.reply.JuvenileInsuranceResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.form.JuvenileBenefitsInsuranceForm;
import ui.juvenilecase.form.JuvenileMemberForm;


public class SubmitJuvenileInsuranceCreateAction extends LookupDispatchAction
{
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.addToList", "addToList");
		keyMap.put("button.back", "back");
		keyMap.put("button.next", "next");
		keyMap.put("button.finish", "finish");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.remove", "remove");
		
		return keyMap;
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		return aMapping.findForward(UIConstants.BACK);
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		return aMapping.findForward(UIConstants.CANCEL);
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	public ActionForward addToList(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		JuvenileBenefitsInsuranceForm form = (JuvenileBenefitsInsuranceForm)aForm; 
		String forward = UIConstants.ADD_TO_LIST_SUCCESS; //happy path scenario
		
		JuvenileMemberForm.MemberInsurance currentInsurance = form.getCurrentInsurance();
		JuvenileMemberForm.MemberInsurance tmpInsurance = new JuvenileMemberForm.MemberInsurance();
		tmpInsurance.setEntryDate(currentInsurance.getEntryDate());
		tmpInsurance.setInsuranceCarrier(currentInsurance.getInsuranceCarrier());
		tmpInsurance.setInsuranceTypeId(currentInsurance.getInsuranceTypeId());
		tmpInsurance.setPolicyNumber(currentInsurance.getPolicyNumber());
		
		form.getNewJuvInsurances().add(tmpInsurance);
		
		//reset entry
		form.setCurrentInsurance(new JuvenileMemberForm.MemberInsurance());
		
		return aMapping.findForward(forward);
		
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	public ActionForward remove(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		JuvenileBenefitsInsuranceForm form = (JuvenileBenefitsInsuranceForm)aForm; 
		String forward = "removeSuccess"; //happy path scenario
		
		String myInsurancePos = form.getSelectedValue();
		
		if(myInsurancePos!=null && !(myInsurancePos.equals(""))){
				if(form.getNewJuvInsurances()!=null & form.getNewJuvInsurances().size()>0)
					 ((List)form.getNewJuvInsurances()).remove((Integer.valueOf(myInsurancePos)).intValue());
		}
	
		return aMapping.findForward(forward);
	
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		JuvenileBenefitsInsuranceForm form = (JuvenileBenefitsInsuranceForm)aForm; 
		String forward = UIConstants.SUCCESS; //happy path scenario
	

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		//translate data from JuvenileMemberForm.MemberInsurance -> UpdateJuvenileInsuranceEvent
		
		UpdateJuvenileInsuranceEvent event = new UpdateJuvenileInsuranceEvent();
		event.setJuvenileNum(form.getJuvNumber());
		
		//ArrayList events = new ArrayList();
		Iterator newInsuranceIter = form.getNewJuvInsurances().iterator();
		
		while(newInsuranceIter.hasNext())
		{
			JuvenileMemberForm.MemberInsurance newInsurance = (JuvenileMemberForm.MemberInsurance)newInsuranceIter.next();
		
			JuvenileInsuranceResponseEvent re = new JuvenileInsuranceResponseEvent();
			re.setCarrier(newInsurance.getInsuranceCarrier());
			//re.setEntryDate(newInsurance.getEntryDate());
			re.setTypeId(newInsurance.getInsuranceTypeId());
			re.setPolicyNum(newInsurance.getPolicyNumber());
			event.addUpdateEvents(re);
		}
		
		dispatch.postEvent(event);
		aRequest.setAttribute("confirm", "true");
		return aMapping.findForward("saveAndContinue");
	
	}	
	
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		return aMapping.findForward(UIConstants.NEXT);
	}

}