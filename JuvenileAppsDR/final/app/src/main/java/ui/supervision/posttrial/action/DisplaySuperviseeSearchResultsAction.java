//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\action\\DisplayCaseHistoryListAction.java

package ui.supervision.posttrial.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.domintf.ISocialSecurity;
import messaging.contact.party.reply.PartyListResponseEvent;
import messaging.posttrial.GetSuperviseesEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.Name;
import ui.common.SocialSecurity;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.administercasenotes.form.SuperviseeSearchForm;

/*
 * 
 * @author mchowdhury
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class DisplaySuperviseeSearchResultsAction extends JIMSBaseAction {

	/**
	 *  
	 */
	public DisplaySuperviseeSearchResultsAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.submit", "submit");
		keyMap.put("button.link", "link");
		keyMap.put("button.refresh", "refresh");
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		SuperviseeSearchForm form = (SuperviseeSearchForm) aForm;
				
		GetSuperviseesEvent sEvent = new GetSuperviseesEvent();
		sEvent.setDateOfBirth(form.getDateOfBirth());
	    sEvent.setCjisNumber(form.getCJIS());
	    sEvent.setDlNumber(form.getDlNum());
	    sEvent.setDlStateId(form.getDlStateId());
	    sEvent.setFbiNumber(form.getFBI());
	    sEvent.setFirstName(form.getSuperviseeName().getFirstName());
	    sEvent.setLastName(form.getSuperviseeName().getLastName());
	    sEvent.setMiddleName(form.getSuperviseeName().getMiddleName());
	    sEvent.setRaceId(form.getRaceId());
	    sEvent.setSexId(form.getSexId());
	    sEvent.setSidNumber(form.getSID());
	    ISocialSecurity ss = form.getSsn();
	    sEvent.setSsn((ss.getSsn1().concat(ss.getSsn2()).concat(ss.getSsn3())));
	    sEvent.setSpn(form.getSpn());
	    sEvent.setCaseNum(form.getCaseNum());
	    sEvent.setCdi(form.getCdi());
	    
	    CompositeResponse response = MessageUtil.postRequest(sEvent);
	    Collection col = MessageUtil.compositeToCollection(response, PartyListResponseEvent.class);
	   
	    if(col == null || col.isEmpty()){
	    	sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"No search results found");
	    	if(UIConstants.ADVANCED.equalsIgnoreCase(form.getAction())){
	    		return aMapping.findForward(UIConstants.ADVANCED_SEARCH_FAILURE);
	    	}else{
	    	  	return aMapping.findForward(UIConstants.FAILURE);
	    	}
	    }
	    form.clearSearchCriteria();
    	Map sexMap = this.getMap(form.getSexList());
    	Map raceMap = this.getMap(form.getRaceList());
    	
    	Iterator iterator = col.iterator();
    	while(iterator.hasNext()){
    		PartyListResponseEvent pResp = (PartyListResponseEvent) iterator.next();
    		if(raceMap.containsKey(pResp.getRaceId())){
    		    pResp.setRace((String) raceMap.get(pResp.getRaceId()));
    		}
    		if(sexMap.containsKey(pResp.getSexId())){
    			pResp.setSex((String) sexMap.get(pResp.getSexId()));
    		}
    	}    
        form.setSearchResults(col);
        return aMapping.findForward(UIConstants.SUBMIT);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		SuperviseeSearchForm form = (SuperviseeSearchForm) aForm;
				
		GetSuperviseesEvent sEvent = new GetSuperviseesEvent();
		sEvent.setDateOfBirth(form.getDateOfBirth());
	    sEvent.setCjisNumber(form.getCJIS());
	    sEvent.setDlNumber(form.getDlNum());
	    sEvent.setDlStateId(form.getDlStateId());
	    sEvent.setFbiNumber(form.getFBI());
	    sEvent.setFirstName(form.getSuperviseeName().getFirstName());
	    sEvent.setLastName(form.getSuperviseeName().getLastName());
	    sEvent.setMiddleName(form.getSuperviseeName().getMiddleName());
	    sEvent.setRaceId(form.getRaceId());
	    sEvent.setSexId(form.getSexId());
	    sEvent.setSidNumber(form.getSID());
	    sEvent.setSsn(form.getSsn().toString());
	    sEvent.setSpn(form.getSpn());
	    
	    CompositeResponse response = MessageUtil.postRequest(sEvent);
	    Collection col = MessageUtil.compositeToCollection(response, PartyListResponseEvent.class);
	    if(col == null || col.isEmpty()){
	    	return aMapping.findForward(UIConstants.FAILURE);
	    }else{
	    	Map sexMap = this.getMap(form.getSexList());
	    	Map raceMap = this.getMap(form.getRaceList());
	    	
	    	Iterator iterator = col.iterator();
	    	while(iterator.hasNext()){
	    		PartyListResponseEvent pResp = (PartyListResponseEvent) iterator.next();
	    		if(raceMap.containsKey(pResp.getRaceId())){
	    		    pResp.setRace((String) raceMap.get(pResp.getRaceId()));
	    		}
	    		if(sexMap.containsKey(pResp.getSexId())){
	    			pResp.setSex((String) sexMap.get(pResp.getSexId()));
	    		}
	    	}
	    }
	    form.setSearchResults(col);
	    return aMapping.findForward(UIConstants.SUBMIT);
	}
	
    private Map getMap(Collection col){
		Map map = new HashMap();
		Iterator iter = col.iterator();
		while(iter.hasNext()){
			CodeResponseEvent resp = (CodeResponseEvent) iter.next();
			map.put(resp.getCode(), resp.getDescription());
		}
    	return map;   	
    }
    
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward refresh(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		SuperviseeSearchForm ssform = (SuperviseeSearchForm) aForm;
		String forward = "refresh";
		if (UIConstants.ADVANCED.equalsIgnoreCase(ssform.getAction())) {
			forward = "advancedRefresh";
		}
		ssform.setDateOfBirth(null);
		ssform.setCJIS("");
	    ssform.setDlNum("");
	    ssform.setDlStateId("");
	    ssform.setFBI("");
	    ssform.setSuperviseeName(new Name());
	    ssform.setRaceId("");
	    ssform.setSexId("");
	    ssform.setSID("");
	    ssform.setSsn(new SocialSecurity(""));
	    ssform.setSpn("");
	    ssform.setCaseNum("");
	    ssform.setCdi("");
	    return aMapping.findForward(forward);
	}  
}
