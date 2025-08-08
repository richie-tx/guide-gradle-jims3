package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.GetJuvenileTraitsByJuvenileIdEvent;
import messaging.juvenilecase.GetJuvenileTraitsByParentTypeEvent;
import messaging.juvenilecase.SearchJuvenileCasefilesEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.CodeHelper;
import ui.juvenilecase.UIJuvenileTraitsHelper;
import ui.juvenilecase.form.TraitsForm;

public class HandleJuvenileProfileTraitsAction extends LookupDispatchAction
{
    protected Map getKeyMethodMap()
    {
	Map keyMap = new HashMap();
	keyMap.put("button.link", "addTraits");
	keyMap.put("button.addMoreTraits", "addTraits");
	keyMap.put("button.viewTraits", "viewTraits");
	keyMap.put("button.updateTraitStatus", "updateTraitStatus");
	return keyMap;
    }

    public ActionForward viewTraits(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
    {
	TraitsForm form = (TraitsForm) aForm;

	form.setAction(UIConstants.VIEW);

	form.initializeTraitForm(true);
	if ("all".equals(form.getTraitTypeId()))
	{

	    GetJuvenileTraitsByJuvenileIdEvent allTraitsEvent = (GetJuvenileTraitsByJuvenileIdEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITSBYJUVENILEID);
	    allTraitsEvent.setJuvenileNum(form.getJuvenileNumber());

	    Collection<JuvenileTraitResponseEvent> allTraits = MessageUtil.postRequestListFilter(allTraitsEvent, JuvenileTraitResponseEvent.class);

	    Collections.sort((List<JuvenileTraitResponseEvent>) allTraits, new Comparator<JuvenileTraitResponseEvent>() {
		@Override
		public int compare(JuvenileTraitResponseEvent evt1, JuvenileTraitResponseEvent evt2)
		{
		    if (evt1.getJuvenileTraitId() != null && evt2.getJuvenileTraitId() != null)
			return evt2.getJuvenileTraitId().compareTo(evt1.getJuvenileTraitId());
		    else
			return -1;
		}
	    });

	    if (allTraits.size() > 0)
	    {

		form.setTraitsSearchResult(allTraits);
	    }

	}
	else
	{

	    GetJuvenileTraitsByParentTypeEvent traitByParentEvent = (GetJuvenileTraitsByParentTypeEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITSBYPARENTTYPE);
	    traitByParentEvent.setJuvenileNum(form.getJuvenileNumber());
	    traitByParentEvent.setTraitType(form.getTraitTypeId());

	    Collection<JuvenileTraitResponseEvent> juvTraits = MessageUtil.postRequestListFilter(traitByParentEvent, JuvenileTraitResponseEvent.class);

	    Collections.sort((List<JuvenileTraitResponseEvent>) juvTraits, new Comparator<JuvenileTraitResponseEvent>() {
		@Override
		public int compare(JuvenileTraitResponseEvent evt1, JuvenileTraitResponseEvent evt2)
		{
		    if (evt1.getJuvenileTraitId() != null && evt2.getJuvenileTraitId() != null)
			return evt2.getJuvenileTraitId().compareTo(evt1.getJuvenileTraitId());
		    else
			return -1;
		}
	    });

	    if (juvTraits.size() > 0)
	    {

		form.setTraitsSearchResult(juvTraits);
	    }
	}
	form.setExpandTraits(1);
	String myForward = setForwardMapping(UIConstants.VIEW_SUCCESS, form.getCategoryName());

	return aMapping.findForward(myForward);

    }

    public ActionForward addTraits(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
    {

	TraitsForm traitsForm = (TraitsForm) aForm;

	traitsForm.setAction(UIConstants.VIEW);
	traitsForm.clearNewTraits();
	traitsForm.setInformationSrcCd("");
	traitsForm.setInformationSources(CodeHelper.getActiveCodes(PDCodeTableConstants.INFORMATION_SOURCE, false));
	//get casefiles list so that user can choose which one to operate on
	SearchJuvenileCasefilesEvent searchEvent = (SearchJuvenileCasefilesEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.SEARCHJUVENILECASEFILES);

	searchEvent.setSearchType(PDJuvenileCaseConstants.SEARCH_JUVENILE_NUMBER);

	searchEvent.setJuvenileNum(traitsForm.getJuvenileNumber());

	List casefiles = MessageUtil.postRequestListFilter(searchEvent, JuvenileCasefileSearchResponseEvent.class);

	List activeCasefiles = new ArrayList();

	Iterator iter = casefiles.iterator();
	while (iter.hasNext())
	{
	    JuvenileCasefileSearchResponseEvent casefileResp = (JuvenileCasefileSearchResponseEvent) iter.next();
	    //			if (casefileResp.getCaseStatus().equals(PDJuvenileCaseConstants.CASESTATUS_ACTIVE_DESCRIPTION)) {
	    // 04/23/2012 Activity #3232 -- allow all casefile status except Closed and Closed Approved 
	    if (!casefileResp.getCaseStatus().equals(PDJuvenileCaseConstants.CASESTATUS_CLOSED_DESCRIPTION) && !casefileResp.getCaseStatus().equals(PDJuvenileCaseConstants.CASESTATUS_CLOSED_APPROVED_DESCRIPTION))
	    {
		activeCasefiles.add(casefileResp);
	    }
	}

	Collections.sort(activeCasefiles);

	traitsForm.setCasefiles(activeCasefiles); // Any update operations work with this Map object
	traitsForm.setCasefilesAbuse(activeCasefiles); //38834 to sort by sequence number
	traitsForm.setSupervisionNum("");
	traitsForm.setTraitTypeDescriptionId("");
	traitsForm.setTraitTypeId("");
	return (aMapping.findForward(UIConstants.ADD_SUCCESS));

    }

    public ActionForward updateTraitStatus(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
    {
	TraitsForm traitsForm = (TraitsForm) aForm;
	traitsForm.setNewStatusId(UIConstants.EMPTY_STRING);
	traitsForm.setConfirmMessage(UIConstants.EMPTY_STRING);
	String forwardStr = UIConstants.UPDATE_SUCCESS;
	List temp1 = new ArrayList(traitsForm.getTraitsSearchResult());
	List temp2 = new ArrayList();
	traitsForm.setUpdateTraitCasefile(new ArrayList());
	traitsForm.setUpdateTraitStatuses(new ArrayList());
	traitsForm.setUpdateTraitsList(new ArrayList());
	for (int x = 0; x < temp1.size(); x++)
	{
	    JuvenileTraitResponseEvent jtrEvent = (JuvenileTraitResponseEvent) temp1.get(x);
	    if (traitsForm.getSelectedValue().equals(jtrEvent.getJuvenileTraitId()))
	    {
		temp2.add(jtrEvent);
		traitsForm.setUpdateTraitsList(temp2);
		// load casefile info for casefile info display    			
		String supervisionNum = jtrEvent.getSupervisionNum();
		if (supervisionNum != null && !"".equals(supervisionNum))
		{
		    SearchJuvenileCasefilesEvent searchEvent = (SearchJuvenileCasefilesEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.SEARCHJUVENILECASEFILES);

		    searchEvent.setSearchType(PDJuvenileCaseConstants.SEARCH_JUVENILE_NUMBER);
		    searchEvent.setJuvenileNum(traitsForm.getJuvenileNumber());

		    List casefiles = MessageUtil.postRequestListFilter(searchEvent, JuvenileCasefileSearchResponseEvent.class);

		    Iterator iter = casefiles.iterator();
		    while (iter.hasNext())
		    {
			JuvenileCasefileSearchResponseEvent casefileResp = (JuvenileCasefileSearchResponseEvent) iter.next();
			if (supervisionNum.equals(casefileResp.getSupervisionNum()))
			{
			    temp2 = new ArrayList();
			    temp2.add(casefileResp);
			    traitsForm.setUpdateTraitCasefile(temp2);
			    traitsForm.setUpdateTraitStatuses(UIJuvenileTraitsHelper.loadUpdateTraitStatuses());
			    break;
			}
		    }
		}
		supervisionNum = null;
		break;
	    }
	}
	traitsForm.setAction(UIConstants.EMPTY_STRING);
	temp1 = null;
	temp2 = null;
	return aMapping.findForward(forwardStr);

    }

    private String setForwardMapping(String aForward, String aCategoryForward)
    {
	if (aCategoryForward != null && aCategoryForward.length() > 0)
	    return aForward + aCategoryForward;
	else
	    return aForward;
    }

}
