package ui.juvenilecase.caseplan.action;


import java.util.Map ;

import javax.servlet.http.HttpServletRequest ;
import javax.servlet.http.HttpServletResponse ;


import messaging.caseplan.SaveCaseplanAcknowledgementEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCasePlanControllerServiceNames;
import naming.UIConstants ;

import org.apache.struts.action.ActionForm ;
import org.apache.struts.action.ActionForward ;
import org.apache.struts.action.ActionMapping ;

import ui.action.JIMSBaseAction ;
import ui.juvenilecase.caseplan.form.CaseplanForm ;


/**
 * 
 * @author ugopinath
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SubmitCaseplanAcknowledgementAction extends JIMSBaseAction
{
	
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward finish( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CaseplanForm form = (CaseplanForm)aForm ;
		SaveCaseplanAcknowledgementEvent evt = (SaveCaseplanAcknowledgementEvent)EventFactory.getInstance(JuvenileCasePlanControllerServiceNames.SAVECASEPLANACKNOWLEDGEMENT);
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		//if(form.getCurrentGuardianAcknowledgment().getSignatureStatus()!=null && !form.getCurrentGuardianAcknowledgment().getSignatureStatus().equals(""))
		//{
			evt.setCaseplanID(form.getCurrentCaseplan().getCaseplanId());
			evt.setEntryDate(form.getCurrentGuardianAcknowledgment().getEntryDate());
			
			if(form.getCurrentGuardianAcknowledgment().getSignatureStatus()== null ||form.getCurrentGuardianAcknowledgment().getSignatureStatus().equals(""))
			{
				evt.setSignatureStatus("Guardian Signature Not Applicable");
				evt.setExplanation("");
			}
			
			else if(form.getCurrentGuardianAcknowledgment().getSignatureStatus().equals("false"))
			{
				evt.setSignatureStatus("Guardian refused to sign");
				evt.setExplanation(form.getCurrentGuardianAcknowledgment().getExplanation());
			}
			else if(form.getCurrentGuardianAcknowledgment().getSignatureStatus().equals("true"))
			{
				evt.setSignatureStatus("Guardian Signed");
				evt.setExplanation("");
			}
			//added the extra status as per ER JIMS200077422
			else if(form.getCurrentGuardianAcknowledgment().getSignatureStatus().equals("unavailable"))
			{
				evt.setSignatureStatus("Not Available for Guardian Signature");
				evt.setExplanation("");
			}
		
			
			dispatch.postEvent( evt ) ;
			CompositeResponse compositeResponseGueardianSign = (CompositeResponse)dispatch.getReply() ;
			MessageUtil.processReturnException( compositeResponseGueardianSign ) ;
		//}
		//if(form.getCurrentJuvenileAcknowledgment().getSignatureStatus() !=null && !form.getCurrentJuvenileAcknowledgment().getSignatureStatus().equals(""))
		//{
			evt = new SaveCaseplanAcknowledgementEvent();
			evt.setCaseplanID(form.getCurrentCaseplan().getCaseplanId());
			evt.setEntryDate(form.getCurrentJuvenileAcknowledgment().getEntryDate());
			if(form.getCurrentJuvenileAcknowledgment().getSignatureStatus() ==null || form.getCurrentJuvenileAcknowledgment().getSignatureStatus().equals(""))
			{
				evt.setSignatureStatus("Juvenile Signature Not Applicable");
				evt.setExplanation("");
			}
			else if(form.getCurrentJuvenileAcknowledgment().getSignatureStatus().equals("false"))
			{
				evt.setSignatureStatus("Juvenile refused to sign");
				evt.setExplanation(form.getCurrentJuvenileAcknowledgment().getExplanation());
			}
			else
			{
				evt.setSignatureStatus("Juvenile Signed");
				evt.setExplanation("");
			}
			
			dispatch.postEvent( evt ) ;
			CompositeResponse compositeResponseJuvSign = (CompositeResponse)dispatch.getReply() ;
			MessageUtil.processReturnException( compositeResponseJuvSign ) ;
		//}
		// Adding record in activity table
		//UIJuvenileHelper.createActivity( form.getCasefileId(), ActivityConstants.CASE_PLAN_MODIFIED, form.getComments() ) ;
		//aRequest.setAttribute( STATUS_STR, CONFIRM_STR ) ;
		form.setStatus(UIConstants.CONFIRM);
		return aMapping.findForward( UIConstants.SUCCESS ) ;
	}


	
	/*
	 * (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#cancel(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward cancel( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ActionForward forward = aMapping.findForward( UIConstants.CANCEL ) ;
		return forward ;
	}

	/*
	 * (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#back(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward back( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return aMapping.findForward( UIConstants.BACK ) ;
	}

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping( Map keyMap )
	{
		keyMap.put( "button.finish", "finish" ) ;
		keyMap.put( "button.cancel", "cancel" ) ;
		keyMap.put( "button.back", "back" ) ;
	}
}