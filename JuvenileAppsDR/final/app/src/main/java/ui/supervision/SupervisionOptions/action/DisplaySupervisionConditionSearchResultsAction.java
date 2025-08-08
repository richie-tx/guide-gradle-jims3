//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\DisplaySupervisionConditionSearchResultsAction.java

package ui.supervision.SupervisionOptions.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.info.reply.CountInfoMessage;
import messaging.supervisionoptions.GetSupervisionConditionsEvent;
import messaging.supervisionoptions.reply.ConditionResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import ui.common.StringUtil;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.UISupervisionOptionHelper;
import ui.supervision.SupervisionOptions.form.CourtBean;
import ui.supervision.SupervisionOptions.form.SupervisionConditionForm;

public class DisplaySupervisionConditionSearchResultsAction extends Action
{

	/**
	 * @roseuid 42F7C49401B5
	 */
	public DisplaySupervisionConditionSearchResultsAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A39010B
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SupervisionConditionForm form = (SupervisionConditionForm) aForm;
		GetSupervisionConditionsEvent postEvt = new GetSupervisionConditionsEvent();

		postEvt.setAgencyId(form.getAgencyId());
		postEvt.setName(StringUtil.trimToEmpty(form.getConditionName()));
		postEvt.setUnformattedDesc(StringUtil.trimToEmpty(form.getConditionLiteral()));

		postEvt.setGroup1(StringUtil.trimToEmpty(form.getGroup1Id()));
		postEvt.setGroup2(StringUtil.trimToEmpty(form.getGroup2Id()));
		postEvt.setGroup3(StringUtil.trimToEmpty(form.getGroup3Id()));
		postEvt.setSpecialCondition(form.isSpecialCondition());
		if (form.getSelSupervisionTypes() != null
				&& form.getSelSupervisionTypes().length > 0) {
			String[] suptervisionType = form.getSelSupervisionTypes();
			postEvt.setSupervisionTypeCd(suptervisionType[0]);
		}
		//TODO How should an invalid date be handled?

		postEvt.setEffectiveDate(UISupervisionOptionHelper.parseDate(form.getEffectiveDate()));
		postEvt.setInactiveDate(UISupervisionOptionHelper.parseDate(form.getInactiveDate()));

		String standardSearchCriteria = form.getStandardSearchCriteria();
		if (standardSearchCriteria != null && standardSearchCriteria.length() > 0)
		{
			postEvt.setStandardSelected(true);
			postEvt.setStandard(Boolean.valueOf(standardSearchCriteria).booleanValue());
		}
		else
		{
			postEvt.setStandardSelected(false);
		}

		postEvt.setJurisdiction(form.getJurisdictionId());
		postEvt.setStatus(form.getConditionStatusId());

		// check if All courts have been selected
		boolean allSelected = form.isAllCourtsSelected();
		ArrayList selectedCourts = new ArrayList();
		if (!allSelected)
		{
			// create a CourtRespEvent map to make search fast for the selected courts
			//			Map courtMap = createCourtMap(form.getCourts());
			Collection courtBeans = form.getCourts();
			if (courtBeans != null)
			{
				Iterator it = courtBeans.iterator();
				while (it.hasNext())
				{
					CourtBean courtBean = (CourtBean) it.next();
					String[] selCourts = aRequest.getParameterValues(courtBean.getCategory());
					if (selCourts != null)
					{
						selectedCourts.addAll(Arrays.asList(selCourts));
					}
				}
			}
			postEvt.setCourts(selectedCourts);
		}
		postEvt.setLimitSearchResults(true);

		CompositeResponse compositeResponse = MessageUtil.postRequest(postEvt);
		CountInfoMessage infomsg = (CountInfoMessage) MessageUtil.filterComposite(compositeResponse,
				CountInfoMessage.class);

		if (infomsg != null)
		{
			ActionErrors errors = new ActionErrors();
			ActionMessage error = new ActionMessage("error.max.limit.exceeded");
			errors.add(ActionErrors.GLOBAL_MESSAGE, error);
			saveErrors(aRequest, errors);
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SEARCH_FAILURE,form.getAgencyId()));
		}
		
		Collection searchResults = MessageUtil.compositeToCollection(compositeResponse, ConditionResponseEvent.class);

		form.setConditionSearchResults(searchResults);

		if (searchResults == null || searchResults.size() == 0)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(
				ActionErrors.GLOBAL_MESSAGE,
				new ActionMessage("error.no.search.results.found", "No search result found"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SEARCH_FAILURE,form.getAgencyId()));

		}
		else{
			Collections.sort((List)searchResults);
		}
		//		set values on form to display in results total line
		//		Collection results = form.getConditionSearchResults();
		Iterator it = searchResults.iterator();
		while (it.hasNext())
		{
			ConditionResponseEvent resultBean = (ConditionResponseEvent) it.next();
			resultBean.setListSize("0");
			if(resultBean.getSupervisionType() != null && resultBean.getSupervisionType().size() > 0) {
				resultBean.setSupervisionTypeSummary((String) resultBean.getSupervisionType().get(0));
				resultBean.setListSize(resultBean.getSupervisionType().size()+"");
			}
			//form.setGroup1Name(resultBean.getGroup1Name());
		}
		// begin result string create
		String searchInputStr = "";
		String commaSpace = "";
		String selectLit = "";
		//conditionName
		if (form.getConditionName() != null && !form.getConditionName().equals("") ){
			searchInputStr = "Name=" + form.getConditionName();
		}
		//conditionLiteral
		if (form.getConditionLiteral() != null && !form.getConditionLiteral().equals("")){
			if (searchInputStr.length() > 0){
				commaSpace = ", ";
			}	
			searchInputStr += commaSpace + "Literal=" + form.getConditionLiteral();
		}
		//groupd1Id
		if (form.getGroup1Id() != null && !form.getGroup1Id().equals("")){
			if (searchInputStr.length() > 0){
				commaSpace = ", ";
			}	
			searchInputStr += commaSpace + "Group 1=" + form.getGroup1Name();
		}
		//groupd2Id
		if (form.getGroup2Id() != null && !form.getGroup2Id().equals("")){
			if (searchInputStr.length() > 0){
				commaSpace = ", ";
			}	
			searchInputStr += commaSpace + "Group 2=" + form.getGroup2Name();
		}
		//groupd3Id
		if (form.getGroup3Id() != null && !form.getGroup3Id().equals("")){
			if (searchInputStr.length() > 0){
				commaSpace = ", ";
			}	
			searchInputStr += commaSpace + "Group 3=" + form.getGroup3Name();
		}
		if (searchInputStr.length() > 0 ){
			commaSpace = ", ";
		}
		//standardSearchCriteria
		selectLit = "ALL";
		if (form.getStandardSearchCriteria().equalsIgnoreCase("true")){
			selectLit = "STANDARD";
		} else if (form.getStandardSearchCriteria().equalsIgnoreCase("false")) {
			selectLit = "NON-STANDARD";
		}
		searchInputStr += commaSpace + "Standard=" + selectLit;
		commaSpace = ", ";
		//jurisdictionId
		selectLit = "ALL";
		if (form.getStandardSearchCriteria().equalsIgnoreCase("HC")){
			selectLit = "STANDARD";
			} else if (form.getStandardSearchCriteria().equalsIgnoreCase("OC")) {
				selectLit = "OUT OF COUNTY";
				}else if (form.getStandardSearchCriteria().equalsIgnoreCase("OC")) {
					selectLit = "OUT OF STATE";
		}
		searchInputStr += commaSpace + "Jurisdiction=" + selectLit;
		//effectiveDate
		if (form.getEffectiveDate() != null && !form.getEffectiveDate().equals("")){
			if (searchInputStr.length() > 0){
				commaSpace = ", ";
			}	
			searchInputStr += commaSpace + "Effective Date=" + form.getEffectiveDate();
		}		
		//inactiveDate
		if (form.getInactiveDate() != null && !form.getInactiveDate().equals("")){
			if (searchInputStr.length() > 0){
				commaSpace = ", ";
			}	
			searchInputStr += commaSpace + "Inactive Date=" + form.getInactiveDate();
		}
		//conditionStatusId
		selectLit = "ALL";
		if (form.getConditionStatusId().equalsIgnoreCase("A")){
			selectLit = "ACTIVE";
			} else if (form.getConditionStatusId().equalsIgnoreCase("C")) {
				selectLit = "CREATED";
				}else if (form.getConditionStatusId().equalsIgnoreCase("I")) {
					selectLit = "INACTIVE";
		}
		searchInputStr += commaSpace + "Status=" + selectLit;
		//specialCondition
		selectLit = "Normal";
		if (form.isSpecialCondition()){
			selectLit = "Special";
		} 
		searchInputStr += commaSpace + "Type=" + selectLit;
		//County Criminal Courts
		//District Courts
		//Other Courts
		String courtLitCC = "";
		String courtLitCR = "";
		String courtLitOC = "";
		// can not use allSelected as value is always false even when all courts are selected
		if (selectedCourts.size() > 0){
			String[] flds = new String[2];
			String fldx = "";
			int ccSize = 0;
			int crSize = 0;
			int ocSize = 0;
			int ccCtr = 0;
			int crCtr = 0;
			int ocCtr = 0;
			// get total selectable courts for each category
			ArrayList courts = new ArrayList(form.getCourts()); 
			for (int g = 0; g < courts.size(); g++){
				CourtBean cbean = (CourtBean) courts.get(g);
				if ("CC".equals(cbean.getCategory()) ) {
					ccSize = cbean.getCourts().size();
				} else if ("CR".equals(cbean.getCategory()) ) {
					crSize = cbean.getCourts().size();
					} else if ("OC".equals(cbean.getCategory()) ) {
					ocSize = cbean.getCourts().size();
					}
			}
			int num = 0;
			for (int x =0; x< selectedCourts.size(); x++){
				flds = selectedCourts.get(x).toString().split(" ");
				try {
				  num = Integer.parseInt(flds[1]);
				  fldx = new Integer(num).toString().trim();
				} catch (NumberFormatException e){
					fldx = flds[1].trim();	
				}
				if (flds[0].equals("CC")){
					ccCtr++;
					courtLitCC += fldx + commaSpace;
				}
				if (flds[0].equals("CR")){
					crCtr++;
					courtLitCR += fldx + commaSpace;;
				}				
				if (flds[0].equals("OC")){
					ocCtr++;
					courtLitOC += fldx + commaSpace;;
				}				
			}
			if (ccCtr == ccSize){
				courtLitCC = "All";
			}
			if (crCtr == crSize){
				courtLitCR = "All";
			}
			if (ocCtr == ocSize){
				courtLitOC = "All";
			}
		} 
		if (courtLitCC != ""){
			selectLit = "All";
			if (!"All".equals(courtLitCC)){
				selectLit = courtLitCC.substring(0, courtLitCC.length() - 2);
			}	
			searchInputStr += commaSpace + "Criminal Courts=" + selectLit;
		}
		if (courtLitCR != ""){
			selectLit = "All";
			if (!"All".equals(courtLitCR)){
				selectLit = courtLitCR.substring(0, courtLitCR.length() - 2);
			}	
			searchInputStr += commaSpace + "District Courts=" + selectLit;
		}
		if (courtLitOC != ""){
			selectLit = "All";
			if (!"All".equals(courtLitOC)){
				selectLit = courtLitOC.substring(0, courtLitOC.length() - 2);
			}	
			searchInputStr += commaSpace + "Other Courts=" + selectLit;
		}
		// end result string create
		form.setSearchResultText(searchInputStr);
		//	check if Special Condition
		if (form.isSpecialCondition())
		{
			form.setShowArchived(true);
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SPECIAL_CONDITION_SUCCESS,form.getAgencyId()));
		}
		else
		{
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SEARCH_SUCCESS,form.getAgencyId()));
		}
	}

}
