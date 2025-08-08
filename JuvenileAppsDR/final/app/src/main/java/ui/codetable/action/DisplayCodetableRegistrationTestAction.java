/*
 * Created on Dec 14, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.codetable.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.GetCodetableRecordEvent;
import messaging.codetable.SearchCodetableRecordsEvent;
import messaging.codetable.reply.CodetableAttributeResponseEvent;
import messaging.codetable.reply.CodetableCompoundListResponseEvent;
import messaging.codetable.reply.CodetableDataResponseEvent;
import messaging.codetable.reply.CodetableRecordResponseEvent;
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

import ui.action.JIMSBaseAction;
import ui.codetable.form.CodetableForm;

/**
 * @author Nagalla
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayCodetableRegistrationTestAction extends JIMSBaseAction {
	
	   /**
	    * @roseuid 
	    */
	   public DisplayCodetableRegistrationTestAction() {
	    
	   }
	   
	   /**
		 * @return Map
		 * @roseuid 
		 */
	   protected void addButtonMapping(Map keyMap) {
	   		keyMap.put("button.link", "link");	   		
		}	

	   /**
	    * @param aMapping
	    * @param aForm
	    * @param aRequest
	    * @param aResponse
	    * @return ActionForward
	    * @roseuid 45B94F500095
	    */
	   public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	   {
		   	CodetableForm cForm = (CodetableForm) aForm;
		   	
		   	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			SearchCodetableRecordsEvent request =
				(SearchCodetableRecordsEvent) EventFactory.getInstance(CodeTableControllerServiceNames.SEARCHCODETABLERECORDS);
			cForm.setPromptCodetableName(cForm.getCodetableName());
			String promptCodetableName = cForm.getPromptCodetableName();
			if (promptCodetableName != null)
			{
				promptCodetableName = promptCodetableName.trim().toUpperCase();
			}
			request.setCodeTableName(promptCodetableName);
			dispatch.postEvent(request);

			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(dataMap);

			Collection codetables =
				   MessageUtil.compositeToCollection(compositeResponse, CodetableRecordResponseEvent.class);
//			if (codetables == null || codetables.isEmpty()) {
//				ActionErrors errors = new ActionErrors();
//				errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage("error.no.search.results.found"));
//				saveErrors(aRequest, errors);
//			   	cForm.setCodetables(new ArrayList());
//				return aMapping.findForward(UIConstants.SEARCH_FAILURE);
//			}
			
			Collections.sort((List) codetables);
			cForm.setCodetables(codetables);
			cForm.setSearchResultsCount("" + codetables.size());
			
			
			GetCodetableRecordEvent requestRecord =
				(GetCodetableRecordEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETCODETABLERECORD);
		
			requestRecord.setCodetableRegId(cForm.getCodetableRegId());
			requestRecord.setType(cForm.getCodetableType());
			
			dispatch.postEvent(requestRecord);
		
			compositeResponse = (CompositeResponse) dispatch.getReply();
			dataMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(dataMap);
		
			Collection codetableAttributes =
				   MessageUtil.compositeToCollection(compositeResponse, CodetableAttributeResponseEvent.class);
			if (codetableAttributes == null || codetableAttributes.isEmpty())
			{
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.genericaccount.found"));
				saveErrors(aRequest, errors);
			   	cForm.setCodetables(new ArrayList());
				return aMapping.findForward(UIConstants.VALIDATE_FAILURE);
			}
			Collections.sort((ArrayList)codetableAttributes);
			cForm.setCodetableAttributes(codetableAttributes);
			
			Collection codetableCompoundList =
				   MessageUtil.compositeToCollection(compositeResponse, CodetableCompoundListResponseEvent.class);
			if (codetableCompoundList != null && !codetableCompoundList.isEmpty())
			{
				cForm.setCodetableCompoundList(codetableCompoundList);		
			}
			
	        Collection codetableDataList =
				   MessageUtil.compositeToCollection(compositeResponse, CodetableDataResponseEvent.class);
			
			if (codetableDataList == null || codetableDataList.isEmpty())
			{
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.codetable.emptyList"));
				saveErrors(aRequest, errors);
			   	cForm.setCodetables(new ArrayList());
				return aMapping.findForward(UIConstants.VALIDATE_FAILURE);
			}
			Collections.sort((ArrayList)codetableDataList);
			cForm.setCodetableDataList(codetableDataList);
			cForm.setFilteredCodetables(codetableDataList);
			cForm.resetFilterCriteria();
	   		return aMapping.findForward(UIConstants.SUCCESS);
	   }
		
	}