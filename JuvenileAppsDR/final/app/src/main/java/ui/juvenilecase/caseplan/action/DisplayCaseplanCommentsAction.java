package ui.juvenilecase.caseplan.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.caseplan.GetGenerateCaseplanDetailsEvent;
import messaging.caseplan.reply.SaveCaseplanDataResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.CodeHelper;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.caseplan.form.CaseplanForm;

/**
 * 
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayCaseplanCommentsAction extends LookupDispatchAction
{
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward addCaseplanComments(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		CaseplanForm form = (CaseplanForm) aForm;
		form.clearComments();
		return aMapping.findForward("success");
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
	    	String clrChk = aRequest.getParameter("clr");
		
		CaseplanForm form = (CaseplanForm) aForm;
		form.setAction("review");
		
		//added for User story 11146, fetching the data from generate caseplan tab for printing in the report
		String caseplanId = form.getCurrentCaseplan().getCaseplanId();
		if (caseplanId !=null && caseplanId != "")
        {
			GetGenerateCaseplanDetailsEvent evt = new GetGenerateCaseplanDetailsEvent();
       
        	
        	evt.setCaseplanId(form.getCurrentCaseplan().getCaseplanId()); 
	        evt.setCasefileId(form.getCasefileId()); 
	        
	        CompositeResponse replyEvent = MessageUtil.postRequest(evt);
	
			        
	        SaveCaseplanDataResponseEvent respEvt = 
	        	(SaveCaseplanDataResponseEvent)MessageUtil.filterComposite(replyEvent,SaveCaseplanDataResponseEvent.class);
			
			
		    form.setCaseplanExist("Y");
		    form.setSupervisionLevelId(respEvt.getSupLevelId());
	        form.setSupervisionLevel(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUV_SUPERVISION_LEVEL, respEvt.getSupLevelId()));	        
			form.setContactInformation(respEvt.getContactInfo());
	        Collection coll = CodeHelper.getCodes( "JUV_SUPERVISION_LEVEL", true ) ;
			form.setSupervisionLevelList(coll);
			
			if (clrChk!=null && clrChk.equalsIgnoreCase("Y"))
			{
			    System.out.println("Set null value");
			    form.setSupLevelApproStr(null); 
			    form.setRecomSupervisionLevelId(null);			
			    form.setJpoMaintainContactStr(null);
			    form.setJpoMaintainExplain(null);
			    form.clearComments();
			}
			
			
        }
		else{
			form.setCaseplanExist("N");
		}
		//ended
		//form.clearComments();
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
		
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, 
	HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward requestReview(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		CaseplanForm form = (CaseplanForm) aForm;
		form.setAction("requestReview");
		form.clearComments();
		return aMapping.findForward("success");
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();
		keyMap.put("button.next", "next");
		keyMap.put("button.addCaseplanComments", "addCaseplanComments");
		keyMap.put("button.requestReview", "requestReview");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		return keyMap;
	}
	
	
}