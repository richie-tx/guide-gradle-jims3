//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\system\\codetable\\action\\SubmitCodeTableRecordUpdateAction.java

package ui.codetable.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.UpdateCodetableDataEvent;
import messaging.codetable.reply.CodetableAttributeResponseEvent;
import messaging.codetable.reply.CodetableDataResponseEvent;
import messaging.codetable.reply.CodetableRecordResponseEvent;
import messaging.error.reply.CodetableErrorResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.codetable.form.CodetableForm;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SubmitCodetableRecordUpdateAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 45B959620370
    */
   public SubmitCodetableRecordUpdateAction() 
   {
    
   }
   
   public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		return aMapping.findForward(UIConstants.BACK);
   	
   }
   
   public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		return aMapping.findForward(UIConstants.CANCEL);
   	
   }
   public ActionForward codeTableDataList(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   	   return aMapping.findForward(UIConstants.MAIN_PAGE);
   	
   }
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 45B94F4F03A3
    */
   public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   	    CodetableForm cForm = (CodetableForm) aForm;
   	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		UpdateCodetableDataEvent request =
			(UpdateCodetableDataEvent) EventFactory.getInstance(CodeTableControllerServiceNames.UPDATECODETABLEDATA);
		
		CodetableDataResponseEvent currentCode = cForm.getCurrentCode();
		request.setCodetableDataId(currentCode.getCodetableDataId());
		request.setValueMap(currentCode.getValueMap());
		Map valueMap = new TreeMap();
		for(Iterator iter = cForm.getCodetableAttributes().iterator();iter.hasNext();)
		{
			CodetableAttributeResponseEvent header = 
				(CodetableAttributeResponseEvent)iter.next();
			
			if(header.isAudit())
			{
				continue;
			}
			String data =
				(String) currentCode.getValueMap().get(new Integer(header.getDisplayOrder()));
			if ( "INACTIVEIND".equals( header.getDbColumn() )
				&& data.length() == 0
				&& "FACILITY".equals( cForm.getCodetableName() )) {
			    data = null;
			}
			valueMap.put(header.getCodetableRegAttributeId(),data);
			
		}
		
		request.setCodetableType(cForm.getCodetableType());
		request.setContextKey(cForm.getCodetableContextKey());
		request.setEntityName(cForm.getCodetableEntityName());
		request.setValueMap(valueMap);
		dispatch.postEvent(request);
		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		CodetableErrorResponseEvent resp =
			   (CodetableErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, CodetableErrorResponseEvent.class);
		if (resp != null)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(resp.getErrorKey(), resp.getParameterValue()));
			saveErrors(aRequest, errors);
		   	return aMapping.findForward(UIConstants.VALIDATE_FAILURE);
		}
		aRequest.setAttribute("opStatus","confirm");
		return aMapping.findForward(UIConstants.UPDATE_CONFIRM_SUCCESS);
   }
   
   public ActionForward returnToSearchResults(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.BACK_TO_SEARCH);
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		Map buttonMap = new HashMap();
		buttonMap.put("button.finish", "finish");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.back", "back");
		buttonMap.put("button.codeTableDataList", "codeTableDataList");
		buttonMap.put("button.returnToSearchResults", "returnToSearchResults");
		return buttonMap;
	}
}
