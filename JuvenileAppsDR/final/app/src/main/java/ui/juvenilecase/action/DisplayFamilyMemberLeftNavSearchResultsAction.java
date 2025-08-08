/*
 * Created on Jul 26, 2012
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.GetFamilyMembersAdvancedEvent;
import messaging.family.GetFamilyMembersEvent;
import messaging.juvenilecase.reply.FamilyMemberListResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileFamilyControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.form.JuvenileMemberSearchForm;
/**
 * @author CShimek
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplayFamilyMemberLeftNavSearchResultsAction extends JIMSBaseAction
{

	/**
	 *  
	 */
	public DisplayFamilyMemberLeftNavSearchResultsAction() {

	}
	
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.submit", "submit");
		keyMap.put("button.refresh", "refresh");
		keyMap.put("button.cancel", "cancel");
	}

	public ActionForward submit( ActionMapping aMapping,ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		String forwardStr = UIConstants.SUCCESS;
		JuvenileMemberSearchForm jmSearchForm = (JuvenileMemberSearchForm) aForm;
		//IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		// Sending PD Request Event
		/*GetFamilyMembersEvent event =
			(GetFamilyMembersEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERS);
		event = populateRequest(event, jmSearchForm);
		event.setLoadAssocatiedJuveniles(true); 
		dispatch.postEvent(event);*/

		GetFamilyMembersAdvancedEvent event = (GetFamilyMembersAdvancedEvent) 
				EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERSADVANCED);
		
        	if (jmSearchForm.getSearchById() != null
        		&& !jmSearchForm.getSearchById().equals(""))
        	{
        	    event.setSearchById(jmSearchForm.getSearchById());
        	}
        	else if ( jmSearchForm.getDriverLicenseNum() != null
        		&& jmSearchForm.getDriverLicenseNum().length() > 0 ) {
        	    event.setDriverLicenseNum( jmSearchForm.getDriverLicenseNum() );
        	}
        	else
        	{
        	    event.setLastName(jmSearchForm.getName().getLastName());
        	    event.setFirstName(jmSearchForm.getName().getFirstName());
        	    event.setMiddleName(jmSearchForm.getName().getMiddleName());
        	    event.setSex(jmSearchForm.getSexId());
        	    if (jmSearchForm.getDateOfBirth() != null
        		    && !jmSearchForm.getDateOfBirth().equals(""))
        	    {
        		event.setDateOfBirth(jmSearchForm.getDateOfBirth());
        	    }
        	    event.setSsn(jmSearchForm.getSsn().getSSN());
        	}
		List memList = MessageUtil.postRequestListFilter( event, FamilyMemberListResponseEvent.class );
		// Getting PD Response Event	
		//CompositeResponse response = (CompositeResponse) dispatch.getReply();
		// Perform Error handling
		//MessageUtil.processReturnException(response);
		//Map dataMap = MessageUtil.groupByTopic(response);
		jmSearchForm.clearSearchResults();
		/*if (dataMap != null)
		{
			Collection members = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_MEMBER_LIST_TOPIC);
			if(members!=null)
			{
				List memList = new ArrayList(members);
				for (int x=0; x<memList.size(); x++){
					FamilyMemberListResponseEvent fmrEvent = (FamilyMemberListResponseEvent) memList.get(x);
					if (fmrEvent.getAssociatedJuveniles() == null){
						fmrEvent.setAssociatedJuveniles(new ArrayList());
					}
					fmrEvent.setEthnicityDesc( SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_ETHNICITY,fmrEvent.getEthnicityCd() ) );
				}
				Collections.sort( (List) memList );
				jmSearchForm.setMemberSearchResults((List) memList);
				if (jmSearchForm.getMemberSearchResults().size() > 0) {
					forwardStr = UIConstants.LIST_SUCCESS;
				} 
			}
		}*/
		
		jmSearchForm.setMemberSearchResults( memList );
		if ( jmSearchForm.getMemberSearchResults().size() > 0 ) {
			forwardStr = UIConstants.LIST_SUCCESS;
		} else{
		    sendToErrorPage(aRequest, "error.no.search.results.found");
		    forwardStr = UIConstants.SEARCH_NORESULTS;		    
		}
		
		return aMapping.findForward(forwardStr);
	}

	/**
	 * @param event
	 * @param mySearchForm
	 * @return
	 */
	private GetFamilyMembersEvent populateRequest(GetFamilyMembersEvent event, JuvenileMemberSearchForm mySearchForm)
	{
		event.setMemberLastName(mySearchForm.getName().getLastName());
		event.setMemberFirstName(mySearchForm.getName().getFirstName());
		event.setMemberMiddleName(mySearchForm.getName().getMiddleName());
		event.setMemberSexId(mySearchForm.getSexId());
		if(mySearchForm.getDateOfBirth() != null && !mySearchForm.getDateOfBirth().equals("")){
		    event.setMemberDateOfBirth(DateUtil.stringToDate(mySearchForm.getDateOfBirth(), UIConstants.DATE_FMT_1));
		}
		event.setMemberSsn(mySearchForm.getSsn().getSSN());
		return event;
	}

	public ActionForward refresh(ActionMapping aMapping,ActionForm aForm,
				HttpServletRequest aRequest, HttpServletResponse aResponse)
		{
		JuvenileMemberSearchForm jmSearchForm = (JuvenileMemberSearchForm) aForm;
		jmSearchForm.clear();
		jmSearchForm.clearSearchResults();
		return aMapping.findForward(UIConstants.SEARCH_NORESULTS);
		}
	
	public ActionForward cancel( ActionMapping aMapping,ActionForm aForm,
			HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		JuvenileMemberSearchForm jmSearchForm = (JuvenileMemberSearchForm) aForm;
		jmSearchForm.clear();
		jmSearchForm.clearSearchResults();
		return aMapping.findForward(UIConstants.CANCEL);
	}	
}