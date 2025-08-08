//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\managesupervisioncase\\action\\DisplayOutOfCountyCaseAdvancedSearchResultsAction.java

package ui.supervision.managesupervisioncase.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.party.reply.PartyListResponseEvent;
import messaging.info.reply.CountInfoMessage;
import messaging.party.GetPartiesEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PartyControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.managesupervisioncase.form.OutOfCountyCaseSearchForm;

public class DisplayOutOfCountyCaseAdvancedSearchResultsAction extends JIMSBaseAction
{

	/**
	 * @roseuid 4443EFCD0114
	 */
	public DisplayOutOfCountyCaseAdvancedSearchResultsAction()
	{

	}
	/**
	* @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	* @return Map
	*/
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.back", "back");
		keyMap.put("button.submit", "submit");
		keyMap.put("button.refresh", "refresh");
		keyMap.put("button.cancel", "cancel");
		return keyMap;
	}

	public ActionForward submit(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
	
	    ActionForward forward = new ActionForward();
		OutOfCountyCaseSearchForm oocSearchForm = (OutOfCountyCaseSearchForm) aForm;
		// post the request to PD
		GetPartiesEvent request =
			(GetPartiesEvent) EventFactory.getInstance(PartyControllerServiceNames.GETPARTIES);
		
		request.setBirthDate(oocSearchForm.getDateOfBirth());
		request.setCjisNum(oocSearchForm.getCjis());
		request.setDriverLicenseNum(oocSearchForm.getDriverLicenseNum());
		request.setDriverLicenseStateId(oocSearchForm.getStateId());
		request.setFbiNum(oocSearchForm.getFbiNum());
		request.setFirstName(oocSearchForm.getFirstName());
		request.setMiddleName(oocSearchForm.getMiddleName());
		request.setLastName(oocSearchForm.getLastName());
		request.setRaceId(oocSearchForm.getRaceId());
		request.setSexId(oocSearchForm.getSexId());
		request.setSidNum(oocSearchForm.getSid());
		request.setSsn(oocSearchForm.getSsn().getSSN());

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(request);
		// clear the form prior to posting the search results
		 oocSearchForm.clear(true, false);
		 
		  CompositeResponse response = (CompositeResponse) dispatch.getReply();
		
		//	RRY added to filter the over the records over the count
		if (MessageUtil.filterComposite(response, CountInfoMessage.class) != null){
		    sendToErrorPage(aRequest, "error.max.limit.exceeded");
		    forward = aMapping.findForward(UIConstants.SEARCH_SUCCESS);
		    }else{
		    
			// check for errors
			MessageUtil.processReturnException(response);
						
			// process results from lookup
			Collection parties = MessageUtil.compositeToCollection(response, PartyListResponseEvent.class);
			if(parties==null || parties.size()<=0){
				sendToErrorPage(aRequest,"error.no.search.results.found");
				forward= aMapping.findForward("noResults");
			}else
			{
			    forward = aMapping.findForward(UIConstants.SEARCH_SUCCESS);
			    oocSearchForm.setPartyList(parties);
				if (parties.size() == 1)
				{
					PartyListResponseEvent party = (PartyListResponseEvent)parties.iterator().next();
					// set the spn for the single party that was found
					oocSearchForm.setSpn(party.getSpn());
					forward = aMapping.findForward(UIConstants.CASE_SEARCH_SUCCESS);
				}
			}
		    
		}
	
		return forward;

	}

	public ActionForward refresh(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		forward = aMapping.findForward(UIConstants.REFRESH_SUCCESS);
		return forward;

	}

	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
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

	 /* (non-Javadoc)
     * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
     */
    protected void addButtonMapping(Map keyMap)
    {
        // TODO Auto-generated method stub
        
    }
}
