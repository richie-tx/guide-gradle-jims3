//Source file: C:\\views\\dev\\app\\src\\ui\\juvenilecase\\action\\DisplayJuvenileCasefileSearchAction.java

package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.officer.GetOfficerProfilesByAttributeEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.OfficerProfileControllerServiceNames;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.CasefileSearchForm;
import ui.security.SecurityUIHelper;

/**
 * 
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplayJuvenileCasefileSearchAction extends Action
{

	/**
	 * @roseuid 4278CA1C002D
	 */
	public DisplayJuvenileCasefileSearchAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4278C7B803C9
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		CasefileSearchForm form = (CasefileSearchForm) aForm;
		String action = (String)aRequest.getParameter("action");
		//<KISHORE>JIMS200059601 : Emulate CSCD Caseload Search-Search CF (UI) - KK
		if(StringUtils.isEmpty(action) || StringUtils.equalsIgnoreCase(action, "refreshCaseload")){
			/* Clear the form and then set the default
			 * search to be by Juvenile Num
			 */
			form.clear();
			// If the logged in user is a CLM, then default his officers 
			ArrayList<OfficerProfileResponseEvent> profiles = (ArrayList)getOfficerProfiles(UIConstants.OFFICER_SUBTYPE, UIConstants.OFFICER_SUBTYPE_CLM);
			for(OfficerProfileResponseEvent event:profiles){
				if(StringUtils.equalsIgnoreCase(SecurityUIHelper.getLogonId(),event.getUserId())){
					form.setCaseLoadMgr(event.getUserId());
					form.setOfficers(getOfficerProfiles(UIConstants.OFFICER_MANAGER_USERID, form.getCaseLoadMgr()));
					break;
				}
			}

			form.setCaseLoadMgrs(profiles);
			form.setSearchTypeId(StringUtils.isEmpty(action)?PDJuvenileCaseConstants.SEARCH_JUVENILE_NUMBER:PDJuvenileCaseConstants.SEARCH_CASE_LOAD);
		}else if(StringUtils.equalsIgnoreCase(action, "getOfficers")){
			List officers = getOfficerProfiles(UIConstants.OFFICER_MANAGER_USERID, form.getCaseLoadMgr());
			List clms = form.getCaseLoadMgrs();
			String clm = form.getCaseLoadMgr();
			form.clear();
			form.setCaseLoadMgr(clm);
			form.setCaseLoadMgrs(clms);
			form.setSearchTypeId(PDJuvenileCaseConstants.SEARCH_CASE_LOAD);
			form.setOfficers(officers);
			if(officers.size() == 0){
				// zero results: forward 'searchFailure'
				ActionErrors errors = new ActionErrors();
				errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionMessage( "error.noRecords" ) );
				saveErrors( aRequest, errors );
				return aMapping.findForward( UIConstants.SEARCH_FAILURE );
			}
		}
		
		return aMapping.findForward("success"); 
	}

	private List<OfficerProfileResponseEvent> getOfficerProfiles(String attrName,String attrValue) {
		List<OfficerProfileResponseEvent> profiles = new ArrayList<OfficerProfileResponseEvent>();
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetOfficerProfilesByAttributeEvent event = (GetOfficerProfilesByAttributeEvent) EventFactory.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILESBYATTRIBUTE);
		event.setAttributeName(attrName);
		event.setAttributeValue(attrValue);
		dispatch.postEvent(event);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		List<OfficerProfileResponseEvent> officerprofiles = MessageUtil.compositeToList(response, OfficerProfileResponseEvent.class);
		
		if (officerprofiles != null && officerprofiles.size() > 0)
		{
			Collections.sort(officerprofiles);
			Iterator<OfficerProfileResponseEvent> events = officerprofiles.iterator();
			while(events.hasNext()){
				OfficerProfileResponseEvent resp = events.next();
				// Here we need to retrieve only the officers who are of type Juvenile
				if(UIConstants.OFFICER_TYPE_JUVENILE.equalsIgnoreCase(resp.getOfficerTypeId())){
					profiles.add(resp);
				}
			}
		}

		return profiles;
	}
}
