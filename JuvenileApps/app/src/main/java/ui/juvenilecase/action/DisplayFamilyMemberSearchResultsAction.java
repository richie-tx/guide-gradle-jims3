/*
 * Created on Oct 4, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.GetFamilyMembersEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileFamilyControllerServiceNames;
import naming.PDJuvenileFamilyConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.juvenilecase.form.JuvenileMemberSearchForm;
/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplayFamilyMemberSearchResultsAction extends LookupDispatchAction
{

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.submit", "submit");
		keyMap.put("button.refresh", "refresh");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		keyMap.put("button.addSelectedMemberToConstellation", "addToConstellation");
		return keyMap;
	}

	public ActionForward refresh(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			
			ActionForward forward = aMapping.findForward(UIConstants.REFRESH_SUCCESS);
			return forward;
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

	public ActionForward submit(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		JuvenileMemberSearchForm mySearchForm = (JuvenileMemberSearchForm) aForm;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		// Sending PD Request Event
		GetFamilyMembersEvent event =
			(GetFamilyMembersEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERS);
		event = populateRequest(event, mySearchForm);
		if (mySearchForm.getSearchById().equalsIgnoreCase("ALL") == false)
		{
			event.setJuvenileNumber(mySearchForm.getJuvenileNumber());
		}
		dispatch.postEvent(event);

		// Getting PD Response Event	
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		// Perform Error handling
		MessageUtil.processReturnException(response);
		Map dataMap = MessageUtil.groupByTopic(response);
		mySearchForm.clearSearchResults();
		if (dataMap != null)
		{
			Collection members = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_MEMBER_LIST_TOPIC);
			UIJuvenileHelper.setJuvMemberSearchFormFROMMemberListRespEvt(mySearchForm, members);
		}
		if (mySearchForm.getMemberSearchResults() == null || mySearchForm.getMemberSearchResults().size() <= 0)
		{
			sendToErrorPage(aRequest, "error.no.search.results.found");
			return aMapping.findForward( UIConstants.SEARCH_NORESULTS );
		}
		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
		return forward;
	}

	public ActionForward addToConstellation(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		JuvenileMemberSearchForm mySearchForm = (JuvenileMemberSearchForm) aForm;
		JuvenileFamilyForm myFamForm = UIJuvenileHelper.getFamilyForm(aRequest);
		JuvenileFamilyForm.Constellation myConstellation = myFamForm.getCurrentConstellation();
		ArrayList membersList = (ArrayList) myConstellation.getMemberList();
		Collection selectedMembers = mySearchForm.getMemberSearchResults();
		JuvenileFamilyForm.MemberList myFamilyMember = null;
		JuvenileMemberSearchForm.MemberSearchResult myMemberSearchResult = null;
		if (selectedMembers != null && selectedMembers.size() > 0)
		{
			Iterator iter = selectedMembers.iterator();
			while (iter.hasNext())
			{
				myMemberSearchResult = (JuvenileMemberSearchForm.MemberSearchResult) iter.next();
				boolean found = false;
				if (myMemberSearchResult.isChecked())
				{
					if (membersList != null && membersList.size() > 0)
					{
						Iterator memberIter = membersList.iterator();
						while (memberIter.hasNext() && !found)
						{
							myFamilyMember = (JuvenileFamilyForm.MemberList) memberIter.next();
							if (myFamilyMember.getMemberNumber().equals(myMemberSearchResult.getMemberNumber()))
							{
								found = true;
							}
						}
					}
					if (!found)
					{
						myFamilyMember = new JuvenileFamilyForm.MemberList();
						myFamilyMember.setMemberNumber(myMemberSearchResult.getMemberNumber());
						myFamilyMember.setMemberName(myMemberSearchResult.getName());
						myFamilyMember.setGuardian(false);
						myFamilyMember.setRelationshipToJuvId(myMemberSearchResult.getRelationshipId());
						myFamilyMember.setDeceased(myMemberSearchResult.isDeceased());
						myFamilyMember.setSuspiciousMember(myMemberSearchResult.getSuspiciousMember());
						myFamilyMember.setOver21(myMemberSearchResult.isOver21());
						myFamilyMember.setDetentionVisitation(false);
						membersList.add(myFamilyMember);
					}
				}
			}
		}
		ActionForward forward = aMapping.findForward("addSuccess");
		return forward;
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

	/**
	* @param aRequest
	*/
	private void sendToErrorPage(HttpServletRequest aRequest, String msg)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
		saveErrors(aRequest, errors);
	}

}
