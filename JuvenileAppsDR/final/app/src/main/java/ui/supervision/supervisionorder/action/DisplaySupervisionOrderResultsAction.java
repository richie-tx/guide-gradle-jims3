//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\supervisionorder\\action\\DisplaySupervisionOrderResultsAction.java

package ui.supervision.supervisionorder.action;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import naming.PDCodeTableConstants;
import naming.PartyControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.common.SimpleCodeTableHelper;
import ui.common.SocialSecurity;
import ui.common.UIUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.supervisionorder.form.SupervisionOrderForm;
import ui.supervision.supervisionorder.form.SupervisionOrderSearchForm;

/**
 * @author dgibler
 *
 */
public class DisplaySupervisionOrderResultsAction extends JIMSBaseAction
{

	/**
	 * @roseuid 438F23ED02A6
	 */
	public DisplaySupervisionOrderResultsAction()
	{

	}
	/**
	* @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	* @return Map
	*/
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.submit", "submit");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		return keyMap;
	}

	public ActionForward submit(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		ActionForward forward = new ActionForward();
		SupervisionOrderForm mySof=(SupervisionOrderForm)getSessionForm(aMapping,aRequest,"supervisionOrderForm",true);
		mySof.setTaskId(null);
		SupervisionOrderSearchForm sof = (SupervisionOrderSearchForm) aForm;

		//		String size = "1";
		//		if (size == "1")
		//		{
		//			forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CASE_SEARCH_SUCCESS);
		//		}
		//		else
		//		{
		//			forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SEARCH_SUCCESS);
		//		}
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		GetPartiesEvent requestEvent =
			(GetPartiesEvent) EventFactory.getInstance(PartyControllerServiceNames.GETPARTIES);

		requestEvent.setBirthDate(sof.getDateOfBirth());
		requestEvent.setCjisNum(sof.getCjis());
		requestEvent.setConnectionId(sof.getConnectionId());
		requestEvent.setDriverLicenseNum(sof.getDriverLicenseNum());
		requestEvent.setDriverLicenseStateId(sof.getStateId());
		requestEvent.setFbiNum(sof.getFbiNum());
		requestEvent.setFirstName(sof.getFirstName());
		requestEvent.setLastName(sof.getLastName());
		requestEvent.setMiddleName(sof.getMiddleName());
		requestEvent.setRaceId(sof.getRaceId());
		requestEvent.setSexId(sof.getSexId());
		requestEvent.setSidNum(sof.getSid());
		//TODO: sof.ssn stored as ssn? jsp has ssn1,2,3
		if (sof.getSsn() != null)
		{
			SocialSecurity socialSecurity = sof.getSsn();
			requestEvent.setSsn(socialSecurity.getSSN());
		}
		dispatch.postEvent(requestEvent);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		
		if (MessageUtil.filterComposite(compositeResponse, CountInfoMessage.class) != null){
		    sendToErrorPage(aRequest, "error.max.limit.exceeded");
		    forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SEARCH_SUCCESS, 
		            	UIUtil.getCurrentUserAgencyID()));
		    }else{
		
		MessageUtil.processReturnException(compositeResponse);

		Collection parties = MessageUtil.compositeToCollection(compositeResponse, PartyListResponseEvent.class);
		sof.setSuperviseeList(parties);
		Integer listSize = new Integer(parties.size());
		sof.setSuperviseeListSize(listSize.toString());
		if(parties==null || parties.size()<=0){
			sendToErrorPage(aRequest,"error.no.search.results.found");
			forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("noResults", UIUtil.getCurrentUserAgencyID()));
		}
		else
		{

			StringBuffer padSpn = null;
			for (Iterator iter = parties.iterator(); iter.hasNext();) {
				PartyListResponseEvent responseEvent = (PartyListResponseEvent) iter.next();				
				padSpn = new StringBuffer(responseEvent.getSpn());
			    if (padSpn.length() < 8){
			    	while (padSpn.length() < 8){
			    		padSpn.insert(0, "0");
			    	}
			    	responseEvent.setSpn(padSpn.toString());
			    }
			    String race = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.RACE,responseEvent.getRaceId());
		    	responseEvent.setRace(race);
			}    
			    
			
			Collections.sort((List)parties);
			forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SEARCH_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		}
    }
		return forward;

	}

	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK, UIUtil.getCurrentUserAgencyID()));
	}

	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
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
