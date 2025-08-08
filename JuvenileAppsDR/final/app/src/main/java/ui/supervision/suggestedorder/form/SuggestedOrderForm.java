/*
 * Created on Sep 30, 2005
 *
 */
package ui.supervision.suggestedorder.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import messaging.suggestedorder.reply.SuggestedOrderConditionResponseEvent;
import messaging.suggestedorder.reply.SuggestedOrderResponseEvent;
import messaging.supervisionoptions.reply.CourtResponseEvent;
import naming.SupervisionConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.security.SecurityUIHelper;
import ui.supervision.SupervisionOptions.UISupervisionOptionHelper;
import ui.supervision.SupervisionOptions.form.CourtBean;
import ui.supervision.supervisionorder.UISupervisionOrderHelper;

/**
 * @author dgibler
 *  
 */
public class SuggestedOrderForm extends ActionForm {

	private static HashMap offenseMap;

	private static Map allCourtsMap;

	private String action;

	private String agencyId;

	private Collection allCourts;

	private boolean allCourtsSelected; 

	private String conditionId;

	private String conditionLiteral;

	//	this variable is used for the preview of the condition literal replaced
	// with sample values
	private String conditionLiteralPreview;

	private String conditionName;

	//	Collection
	//private Collection conditionLiteralPreviewList;
	private Collection conditionResultList = new ArrayList();

	private Collection conditionSelectedList = new ArrayList();

	private String conditionSequenceNum;

	private String conditionStatus;

	private String courtDivisionId;

	private Collection courtDivisions; // List of Criminal Court Division code

	// table

	private String courtId;

	private Collection courts;

	private String degreeCodeId;

	private Collection detailDictionary;

	private HashMap detailDictionaryNameIdHashMap;

	private String group1Id;

	private String group2Id;

	private String group3Id;

	private Collection groups;

	private String jurisdictionId;

	private Collection jurisdictions;

	private String levelCodeId;

	private String offenseId;

	private String offenseLiteral;

	private Collection offenseResultList;

	private Collection offenseSelectedList;

	private boolean order;

	private String orderDescription;

	private String orderId;

	private String orderName;

	private String penalCodeId;
	
	 private Collection previewConditionSelectedList;

	private String previousAction;
	private boolean hasOffenses = false;
	
	private String previousOrderName;
	
	private String resequencedOrderValue;

	private String searchOffenseId;

	private Collection selectedCourts;
	private Collection copyOfSelectedCourts;

	private String selectedInd;

	private String seqNum;

	private String standardId;

	private String standardLiteral;

	private String stateOffenseCodeId;

	private Collection subgroups;

	private Collection suggestedOrderList;

	private String suggestedOrderSize;

	private Collection variableElementNames;	
	
	private boolean hasNonStandardConditions;

	private int nonStandardCount;
	
	

	public int getNonStandardCount() {
		nonStandardCount=0;
		SuggestedOrderConditionResponseEvent socre = null;
		Iterator iter = conditionSelectedList.iterator();
		
		while (iter.hasNext())
		{
			socre = (SuggestedOrderConditionResponseEvent) iter.next();
			if (socre.getStandardId().equals(SupervisionConstants.NON_STANDARD_ONLY_CONDITION)){
				nonStandardCount++;
			}
		}
		return nonStandardCount;
	}

	public void setNonStandardCount(int nonStandardCount) {
		this.nonStandardCount = nonStandardCount;
	}
	
	public boolean isHasOffenses() {
		return hasOffenses;
	}

	public void setHasOffenses(boolean hasOffenses) {
		this.hasOffenses = hasOffenses;
	}

	public SuggestedOrderForm() {
		clear();
	}

	public void clear() {
		// Never clear the action
		//		action = "";
		agencyId = "";
		courtDivisionId = "";
		courtId = "";
		conditionId = "";
		conditionLiteral = "";
		conditionLiteralPreview = "";
		conditionName = "";
		conditionSequenceNum = "";
		conditionStatus = "";
		degreeCodeId = "";
		group1Id = "";
		group2Id = "";
		group3Id = "";
		jurisdictionId = "";
		levelCodeId = "";
		hasNonStandardConditions = false;
		hasOffenses = false;
		offenseId = "";
		offenseLiteral = "";
		orderId = "";
		orderName = "";
		orderDescription = "";
		nonStandardCount=0;
		penalCodeId = "";
		previousAction = "";
		previousOrderName = "";
		resequencedOrderValue = "";
		searchOffenseId = "";
		selectedInd = "";
		seqNum = "";
		standardId = "";
		standardLiteral = "";
		stateOffenseCodeId = "";
		suggestedOrderSize = "";

		allCourtsSelected = false;
		order = false;

		conditionResultList = new ArrayList();
		conditionSelectedList = new ArrayList();
		courtDivisions = new ArrayList();
		courts = new ArrayList();
		detailDictionary = new ArrayList();
		jurisdictions = new ArrayList();
		offenseSelectedList = new ArrayList();
		offenseResultList = new ArrayList();
		selectedCourts = new ArrayList();
		suggestedOrderList = new ArrayList();
		variableElementNames = new ArrayList();

		//Clear out selected indicator on CourtBeans and courts
		CourtBean courtBean = null;
		CourtResponseEvent cre = null;

		if (getAllCourts() != null && getAllCourts().size() > 0) {
			Iterator courtBeanIter = getAllCourts().iterator();
			Iterator courtIter = null;
			while (courtBeanIter.hasNext()) {
				courtBean = (CourtBean) courtBeanIter.next();
				courtBean.setIsSelected(false);
				if (courtBean.getCourts() != null) {
					courtIter = courtBean.getCourts().iterator();
					while (courtIter.hasNext()) {
						cre = (CourtResponseEvent) courtIter.next();
						cre.setIsSelected(false);
					}
				}
			}
		}

		detailDictionaryNameIdHashMap = null;

	}

	/**
	 * @param collection
	 */
	public void clearConditionResultList() {
		conditionResultList.clear();
	}

	public void clearConditionSearchElements() {
		conditionName = "";
		conditionLiteral = "";
		group1Id = "";
		group2Id = "";
		group3Id = "";
		jurisdictionId = "";
	}

	/**
	 * @param collection
	 */
	public void clearConditionSelectedList() {
		conditionSelectedList.clear();
	}

	/**
	 * @return
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @return
	 */
	public String getAgencyId() {
		if (agencyId == null || agencyId.equals("")) {
			agencyId = SecurityUIHelper.getUserAgencyId();
		}
		return agencyId;
	}

	/**
	 * @param collection
	 */
	/*
	 * public void setConditionLiteralPreviewList(Collection collection) {
	 * conditionLiteralPreviewList = collection; }
	 */
	/**
	 * @return
	 */
	public Collection getAllCourts() {
		return allCourts;
	}

	/**
	 * @return
	 */
	public boolean getAllCourtsSelected() {
		return allCourtsSelected;
	}

	/**
	 * @return
	 */
	public String getConditionId() {
		return conditionId;
	}

	/**
	 * @return
	 */
	public String getConditionLiteral() {
		return conditionLiteral;
	}

	/*
	 * This is to get different display depending on which agency the user
	 * belongs to
	 */
	public String getConditionLiteralCaption() {
		this.getAgencyId();
		if ((UIConstants.JUV).equalsIgnoreCase(this.getAgencyId())) {
			return "prompt.literal";
		}

		return "prompt.condition";
	}

	/**
	 * @return
	 */
	public String getConditionLiteralPreview() {
		return conditionLiteralPreview;
	}

	/**
	 * @return
	 */
	public String getConditionName() {
		return conditionName;
	}

	/**
	 * @return
	 */
	public Collection getConditionResultList() {
		return conditionResultList;
	}

	/**
	 * @return
	 */
	public Collection getConditionSelectedList() {		
		return conditionSelectedList;
	}

	/**
	 * @return
	 */
	public String getConditionSequenceNum() {
		return conditionSequenceNum;
	}

	/**
	 * @return
	 */
	public String getConditionStatus() {
		return conditionStatus;
	}

	/**
	 * @return
	 */
	public String getCourtDivisionId() {
		return courtDivisionId;
	}

	/**
	 * @return
	 */
	public Collection getCourtDivisions() {
		return courtDivisions;
	}

	/**
	 * @return
	 */
	public String getCourtId() {
		return courtId;
	}

	/**
	 * @return
	 */
	public Collection getCourts() {
		return courts;
	}

	/**
	 * @return
	 */
	public String getDegreeCodeId() {
		return degreeCodeId;
	}

	/**
	 * @return
	 */
	public Collection getDetailDictionary() {
		if (detailDictionary == null || detailDictionary.size() == 0) {
			// get detail dictionary
			detailDictionary = UISupervisionOptionHelper
					.fetchDetailDictionary(getAgencyId());
		}
		return detailDictionary;
	}

	/**
	 * @return
	 */
	public HashMap getDetailDictionaryNameIdHashMap() {
		return detailDictionaryNameIdHashMap;
	}

	public String getGroup1Caption() {
		this.getAgencyId();
		if (agencyId.equalsIgnoreCase(UIConstants.JUV)) {
			return "prompt.category";
		} else if (agencyId.equalsIgnoreCase(UIConstants.PTR)) {
			return "prompt.conditionType";
		}

		return "prompt.group1";
	}

	/**
	 * @return
	 */
	public String getGroup1Id() {
		return group1Id;
	}

	public String getGroup2Caption() {
		this.getAgencyId();
		if (agencyId.equalsIgnoreCase(UIConstants.JUV)) {
			return "prompt.type";
		} else if (agencyId.equalsIgnoreCase(UIConstants.PTR)) {
			return "prompt.conditionSubType";
		}

		return "prompt.group2";
	}

	/**
	 * @return
	 */
	public String getGroup2Id() {
		return group2Id;
	}

	public String getGroup3Caption() {
		this.getAgencyId();
		if (agencyId.equalsIgnoreCase(UIConstants.JUV)) {
			return "prompt.subtype";
		} else if (agencyId.equalsIgnoreCase(UIConstants.PTR)) {
			return "prompt.subTypeDetail";
		}

		return "prompt.group3";
	}

	/**
	 * @return
	 */
	public String getGroup3Id() {
		return group3Id;
	}

	/**
	 * @return
	 */
	public Collection getGroups() {
		return groups;
	}
	
	public boolean isHasNonStandardConditions() {	
		hasNonStandardConditions = false;
		if (this.getConditionSelectedList()!=null && !this.getConditionSelectedList().isEmpty()){			
			for(Iterator iter = this.getConditionSelectedList().iterator(); iter.hasNext(); ){
				SuggestedOrderConditionResponseEvent socre = (SuggestedOrderConditionResponseEvent)iter.next();
				if (SupervisionConstants.NON_STANDARD_ONLY_CONDITION.equals(socre.getStandardId())){
					hasNonStandardConditions = true;
					break;
				}
			}			
		}
		return hasNonStandardConditions;
	}


	/**
	 * @return
	 */
	public String getJurisdictionId() {
		return jurisdictionId;
	}

	/**
	 * @return
	 */
	public Collection getJurisdictions() {
		return jurisdictions;
	}

	/**
	 * @return
	 */
	public String getLevelCodeId() {
		return levelCodeId;
	}
	
	
	/**
	 * @return
	 */
	public String getOffenseId() {
		return offenseId;
	}

	/**
	 * @return
	 */
	public String getOffenseLiteral() {
		return offenseLiteral;
	}

	/**
	 * @return
	 */
//	public HashMap getOffenseMap() {
//		if (offenseMap == null) {
//			offenseMap = new HashMap();
//			Collection offenses = CodeHelper.getOffenseCodes(false);
//			if (offenses != null && offenses.size() > 0) {
//				Iterator iter = offenses.iterator();
//				OffenseCodeResponseEvent offenseCode = null;
//				while (iter.hasNext()) {
//					offenseCode = (OffenseCodeResponseEvent) iter.next();
//					offenseMap.put(offenseCode.getOffenseCodeId(), offenseCode);
//				}
//			}
//		}
//		return offenseMap;
//	}

	/**
	 * @return
	 */
	public Collection getOffenseResultList() {
		return offenseResultList;
	}

	/**
	 * @return
	 */
	public Collection getOffenseSelectedList() {
		return offenseSelectedList;
	}

	/**
	 * @return
	 */
	public String getOrderDescription() {
		return orderDescription;
	}

	/**
	 * @return
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @return
	 */
	public String getOrderName() {
		return orderName;
	}

	/**
	 * @return
	 */
	public String getPenalCodeId() {
		return penalCodeId;
	}

	/**
	 * @return
	 */
	public String getPreviousAction() {
		return previousAction;
	}

	/**
	 * @return
	 */
	public String getPreviousOrderName() {
		return previousOrderName;
	}

	/**
	 * @return
	 */
	public String getSearchOffenseId() {
		return searchOffenseId;
	}

	/**
	 * @return
	 */
	public Collection getSelectedCourts() {
		return selectedCourts;
	}

	/**
	 * @return
	 */
	public String getSelectedInd() {
		return selectedInd;
	}

	/**
	 * @return
	 */
	public String getSeqNum() {
		return seqNum;
	}

	/**
	 * @return
	 */
	public String getStandardId() {
		return standardId;
	}

	/**
	 * @return
	 */
	public String getStandardLiteral() {
		String aStandardLiteral = CodeHelper.getCodeDescriptionByCode(
				CodeHelper.getConditionTypeCodes(false), this.standardId);

		return aStandardLiteral;
	}

	/**
	 * @return
	 */
	public String getStateOffenseCodeId() {
		return stateOffenseCodeId;
	}

	/**
	 * @return
	 */
	public Collection getSubgroups() {
		return subgroups;
	}

	/**
	 * @return
	 */
	public Collection getSuggestedOrderList() {
		return suggestedOrderList;
	}

	/**
	 * @return
	 */
	public String getSuggestedOrderSize() {
		return suggestedOrderSize;
	}

	/**
	 * @return
	 */
	/*
	 * public Collection getConditionLiteralPreviewList() { return
	 * conditionLiteralPreviewList; }
	 */
	/**
	 * @return
	 */
	public Collection getVariableElementNames() {
		return variableElementNames;
	}

	/**
	 * @return
	 */
	public boolean isOrder() {
		return order;
	}

	/**
	 * @param aRequest
	 */
	public void reset(ActionMapping aMapping, HttpServletRequest aRequest) {
		Object obj = aRequest.getParameter("clearSelectedCourts");
		if (obj != null) {
			String clearSelectedCourts = (String) obj;
			if (clearSelectedCourts.equals("true")) {
				selectedCourts = new ArrayList();
			}
		}
	}

	/**
	 * @param string
	 */
	public void setAction(String string) {
		action = string;
	}

	/**
	 * @param string
	 */
	public void setAgencyId(String string) {
		agencyId = string;
	}

	/**
	 * @param collection
	 */
	public void setAllCourts(Collection collection) {
		allCourts = collection;
	}

	/**
	 * @param b
	 */
	public void setAllCourtsSelected(boolean b) {
		allCourtsSelected = b;
	}

	/**
	 * @param string
	 */
	public void setConditionId(String string) {
		conditionId = string;
	}

	/**
	 * @param string
	 */
	public void setConditionLiteral(String string) {
		conditionLiteral = string;
	}

	/**
	 * @param string
	 */
	public void setConditionLiteralPreview(String string) {
		conditionLiteralPreview = string;
	}

	/**
	 * @param string
	 */
	public void setConditionName(String string) {
		conditionName = string;
	}

	/**
	 * @param collection
	 */
	public void setConditionResultList(Collection collection) {
		if (collection != null) {
			Collections.sort((ArrayList) collection);
			if (collection.size() > 0)
				UISupervisionOrderHelper.setPreviewSample(this, collection);
		}
		conditionResultList = collection;
	}

	/**
	 * @param collection
	 */
	public void setConditionSelectedList(Collection collection) {
		if (collection != null) {
			Collections.sort((ArrayList) collection);
			if (!UIConstants.CREATE.equals(this.action)){
				//the following code resequences the conditions (this is necessary if 1 or more of the conditions in the suggested order is deleted)
				Iterator collIter = collection.iterator();
				int count=1;
				while (collIter.hasNext()){
					SuggestedOrderConditionResponseEvent soc = (SuggestedOrderConditionResponseEvent) collIter.next();
					if (soc.getSeqNum()!=null && !soc.getSeqNum().equals("")){
						int seqNum = Integer.parseInt(soc.getSeqNum());
						if (seqNum != count){
							soc.setSeqNum(Integer.toString(count));
						}
					}
					count++;
				}
			}
			if (collection.size() > 0)
				UISupervisionOrderHelper.setPreviewSample(this, collection);
		}
		conditionSelectedList = collection;
	}
	

	/**
	 * @param string
	 */
	public void setConditionSequenceNum(String string) {
		conditionSequenceNum = string;
	}

	/**
	 * @param string
	 */
	public void setConditionStatus(String string) {
		conditionStatus = string;
	}

	/**
	 * @param string
	 */
	public void setCourtDivisionId(String string) {
		courtDivisionId = string;
	}

	/**
	 * @param collection
	 */
	public void setCourtDivisions(Collection collection) {
		courtDivisions = collection;
	}

	/**
	 * @param string
	 */
	public void setCourtId(String string) {
		courtId = string;
	}

	/**
	 * @param collection
	 */
	public void setCourts(Collection collection) {
		courts = collection;
	}

	/**
	 * @param string
	 */
	public void setDegreeCodeId(String string) {
		degreeCodeId = string;
	}

	/**
	 * @param collection
	 */
	public void setDetailDictionary(Collection collection) {
		detailDictionary = collection;
	}

	/**
	 * @param map
	 */
	public void setDetailDictionaryNameIdHashMap(HashMap map) {
		detailDictionaryNameIdHashMap = map;
	}

	/**
	 * @param string
	 */
	public void setGroup1Id(String string) {
		group1Id = string;
	}

	/**
	 * @param string
	 */
	public void setGroup2Id(String string) {
		group2Id = string;
	}

	/**
	 * @param string
	 */
	public void setGroup3Id(String string) {
		group3Id = string;
	}

	/**
	 * @param collection
	 */
	public void setGroups(Collection collection) {
		groups = collection;
	}
	

	public void setHasNonStandardConditions(boolean hasNonStandardConditions) {
		this.hasNonStandardConditions = hasNonStandardConditions;
	}

	/**
	 * @param string
	 */
	public void setJurisdictionId(String string) {
		jurisdictionId = string;
	}

	/**
	 * @param collection
	 */
	public void setJurisdictions(Collection collection) {
		jurisdictions = collection;
	}

	/**
	 * @param string
	 */
	public void setLevelCodeId(String string) {
		levelCodeId = string;
	}
	
	
	/**
	 * @param string
	 */
	public void setOffenseId(String string) {
		offenseId = string;
	}

	/**
	 * @param string
	 */
	public void setOffenseLiteral(String string) {
		offenseLiteral = string;
	}

	/**
	 * @param collection
	 */
	public void setOffenseResultList(Collection collection) {
		if (collection != null) {
			Collections.sort((ArrayList) collection);
		}
		offenseResultList = collection;
	}

	/**
	 * @param collection
	 */
	public void setOffenseSelectedList(Collection collection) {
//		this.getOffenseMap();
//		if (collection != null) {
//			Iterator iter = collection.iterator();
//			OffenseCodeResponseEvent partialOffenseRe = null;
//			OffenseCodeResponseEvent fullOffenseRe = null;
//			while (iter.hasNext()) {
//				partialOffenseRe = (OffenseCodeResponseEvent) iter.next();
//				if (partialOffenseRe.getDescription() != null
//						&& !partialOffenseRe.getDescription().equals("")) {
//					break;
//				}
//				fullOffenseRe = (OffenseCodeResponseEvent) offenseMap
//						.get(partialOffenseRe.getOffenseCodeId());
//				if (fullOffenseRe != null) {
//					partialOffenseRe.setDescription(fullOffenseRe
//							.getDescription());
//					partialOffenseRe.setLevel(fullOffenseRe.getLevel());
//					partialOffenseRe.setPenalCode(fullOffenseRe.getPenalCode());
//					partialOffenseRe.setDegree(fullOffenseRe.getDegree());
//					partialOffenseRe.setStateCodeNum(fullOffenseRe
//							.getStateCodeNum());
//				}
//			}
//	}
//		if (collection != null) {
//			Collections.sort((ArrayList) collection);
//		}
		offenseSelectedList = collection;
	}

	/**
	 * @param b
	 */
	public void setOrder(boolean b) {
		order = b;
	}

	/**
	 * @param string
	 */
	public void setOrderDescription(String string) {
		orderDescription = string;
	}

	/**
	 * @param string
	 */
	public void setOrderId(String string) {
		orderId = string;
	}

	/**
	 * @param string
	 */
	public void setOrderName(String string) {
		orderName = string;
	}

	/**
	 * @param string
	 */
	public void setPenalCodeId(String string) {
		penalCodeId = string;
	}

	/**
	 * @param aPreviousAction
	 */
	public void setPreviousAction(String aPreviousAction) {
		previousAction = aPreviousAction;
	}

	/**
	 * @param aPreviousOrderName
	 */
	public void setPreviousOrderName(String aPreviousOrderName) {
		previousOrderName = aPreviousOrderName;
	}

	/**
	 * @param string
	 */
	public void setSearchOffenseId(String string) {
		searchOffenseId = string;
	}

	/**
	 * @param collection
	 */
	public void setSelectedCourts(Collection collection) {

		selectedCourts = collection;
		copyOfSelectedCourts=selectedCourts;
	}

	/**
	 * @param string
	 */
	public void setSelectedInd(String string) {
		selectedInd = string;
	}

	/**
	 * @param string
	 */
	public void setSeqNum(String string) {
		seqNum = string;
	}

	/**
	 * @param string
	 */
	public void setStandardId(String string) {
		standardId = string;
	}

	/**
	 * @param string
	 */
	public void setStandardLiteral(String string) {
		standardLiteral = string;
	}

	/**
	 * @param string
	 */
	public void setStateOffenseCodeId(String string) {
		stateOffenseCodeId = string;
	}

	/**
	 * @param collection
	 */
	public void setSubgroups(Collection collection) {
		subgroups = collection;
	}

	/**
	 * @param collection
	 */
	public void setSuggestedOrderList(Collection collection) {
		suggestedOrderList = collection;
	}

	/**
	 * @param string
	 */
	public void setSuggestedOrderSize(String string) {
		suggestedOrderSize = string;
	}

	/**
	 * @param tokens
	 */
	public void setVariableElementNames(Collection tokens) {
	}
	/**
	 * @return Returns the previewConditionSelectedList.
	 */
	public Collection getPreviewConditionSelectedList() {
		return previewConditionSelectedList;
	}
	/**
	 * @param previewConditionSelectedList The previewConditionSelectedList to set.
	 */
	public void setPreviewConditionSelectedList(Collection previewConditionSelectedList) {
		this.previewConditionSelectedList = previewConditionSelectedList;
	}
	/**
	 * @return Returns the resequencedOrderValue.
	 */
	public String getResequencedOrderValue() {
		return resequencedOrderValue;
	}
	/**
	 * @param resequencedOrderValue The resequencedOrderValue to set.
	 */
	public void setResequencedOrderValue(String resequencedOrderValue) {
		this.resequencedOrderValue = resequencedOrderValue;
	}
	/**
	 * @return Returns the copyOfSelectedCourts.
	 */
	public Collection getCopyOfSelectedCourts() {
		return copyOfSelectedCourts;
	}
}
