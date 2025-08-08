/*
 * Created on Jan 18, 2006
 *
 */
package pd.supervision.supervisionorder;

import gnu.regexp.RE;
import gnu.regexp.REException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import naming.PDCodeTableConstants;
import naming.PDCommonSupervisionConstants;
import naming.PDConstants;
import naming.SupervisionConstants;
import naming.UIConstants;

import pd.codetable.Code;
import pd.codetable.criminal.OffenseCode;
import pd.common.util.PDUtil;
import pd.criminalcase.CostBillSummary;
import pd.criminalcase.CriminalCase;
import pd.criminalcase.Supervision;
import pd.security.PDSecurityHelper;
import pd.supervision.Court;
import pd.supervision.Group;
import pd.supervision.Factory.OutOfCountyCaseFactory;
import pd.supervision.managesupervisioncase.OutOfCountyProbationCase;
import pd.supervision.suggestedorder.SuggestedOrderHelper;
import pd.supervision.supervisionoptions.Condition;
import pd.supervision.supervisionoptions.ConditionCourtVariableElement;
import pd.supervision.supervisionoptions.VariableElementType;
import pd.supervision.transfers.CSTransfer;
import messaging.administersupervisee.GetTransfersEvent;
import messaging.managesupervisioncase.GetOutOfCountyCaseEvent;
import messaging.spnsplit.reply.SpnSplitErrorResponseEvent;
import messaging.spnsplit.reply.SpnSplitOrderDetailsResponseEvent;
import messaging.spnsplit.reply.SpnSplitOrderPeriodResponseEvent;
import messaging.supervisionoptions.reply.ConditionDetailResponseEvent;
import messaging.supervisionorder.CreateSupervisionOrderEvent;
import messaging.supervisionorder.GetActiveSupervisionOrdersEvent;
import messaging.supervisionorder.GetAllSupervisionOrderVersionsEvent;
import messaging.supervisionorder.GetCaseSupervisionPeriodEvent;
import messaging.supervisionorder.GetLatestInactiveSupervisionOrderEvent;
import messaging.supervisionorder.GetLatestSupervisionPeriodEvent;
import messaging.supervisionorder.GetMostCurrentSupervisionOrderForCaseEvent;
import messaging.supervisionorder.GetPeriodForSupervisionOrderEvent;
import messaging.supervisionorder.GetSupervisionOrderDetailsEvent;
import messaging.supervisionorder.GetSupervisionOrderVersionsEvent;
import messaging.supervisionorder.reply.CaseOrderResponseEvent;
import messaging.supervisionorder.reply.OrderCreateErrorResponseEvent;
import messaging.supervisionorder.reply.SupOrderConditionRelValueResponseEvent;
import messaging.supervisionorder.reply.SupOrderConditionResponseEvent;
import messaging.supervisionorder.reply.SupervisionOrderDetailResponseEvent;
import messaging.supervisionorder.reply.SupervisionPeriodResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.ResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.Name;

/**
 * @author dgibler
 *  
 */
public class SupervisionOrderHelper {

	private static String DEFENDANT = "DEF";
	private static String MMDDYY_FORMAT = "MMddyy";
	private static int ZERO = 0;
	private static final String SIX_ZEROES = "000000";
	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final String YEARS = "Y";
	private static final String MONTHS = "M";
	private static final String DAYS = "D";
	private static String MIGRATED = "MIG-";
	public static final String currencyUSSymbol="$";

	/**
	 * @param collection
	 * @return
	 */
	public static Map buildResponseEventMap(Collection collection) {
		HashMap map = new HashMap();
		Iterator iter = collection.iterator();
		while (iter.hasNext()) {
			ResponseEvent re = (ResponseEvent) iter.next();
			map.put(re.getTopic(), re);
		}
		return map;
	}

	private static SupOrderConditionResponseEvent createConditionResponseEvent(
			SupervisionOrderConditionRel condRel, List orderCondRelValues) {
		SupervisionOrderCondition soCondition = condRel
				.getSupervisionOrderCondition();
		if (soCondition == null){
			return null;
		}
		SupOrderConditionResponseEvent reply = new SupOrderConditionResponseEvent();
		reply.setConditionId(soCondition.getConditionId());
		reply.setSequenceNum(condRel.getSequenceNum());
		reply.setSpecialCondition(soCondition.getIsSpecialCondition());
		reply.setDescription(soCondition.getDescription());
		reply.setResolvedDescription(soCondition.getResolvedDescription());
		reply.setName(soCondition.getName());
		reply.setTopic(soCondition.getConditionId());
		reply.setStandard(soCondition.getIsStandard());
		if(soCondition.getCondition()!=null){
			reply.setStatus(soCondition.getCondition().getStatus());
			Object obj=soCondition.getCondition().getAllCourtIds();
			if(obj!=null){
				reply.setAllCourtIds((HashSet)obj);
			}else{
				reply.setAllCourtIds(null);
			}
		}

		//		//Retrieve group names for display purposes
		Group group = soCondition.getGroup();
		if (group != null) {
			Group[] groups = group.getGroupList();
			if (groups[0] != null) {
				reply.setGroup1Name(groups[0].getGroupName());
			}
			if (groups[1] != null) {
				reply.setGroup2Name(groups[1].getGroupName());
			}
			if (groups[2] != null) {
				reply.setGroup3Name(groups[2].getGroupName());
			}
		}

		//Collection orderCondRelValues = condRel.getOrderConditionRelValues();
		if (orderCondRelValues != null) {
			for (Iterator i = orderCondRelValues.iterator(); i.hasNext();) {
				SupervisionOrderConditionRelValue orderCondRelValue = (SupervisionOrderConditionRelValue) i.next();
				SupOrderConditionRelValueResponseEvent orderCondRelValEvent = new SupOrderConditionRelValueResponseEvent();
				//populateSupOrderConditionRelValueResponse(orderCondRelValue, orderCondRelValEvent);
				populateSupOrderConditionRelValueResponse(orderCondRelValue, orderCondRelValEvent);
				reply.addSupOrderConditionRelValue(orderCondRelValEvent);
			}
		}
		return reply;
	}

	private static SupOrderConditionResponseEvent createOrderVersionConditionResponseEvent(
			SupervisionOrderConditionRel condRel) {
		SupervisionOrderCondition soCondition = condRel.getSupervisionOrderCondition();
		SupOrderConditionResponseEvent reply = new SupOrderConditionResponseEvent();
		reply.setConditionId(soCondition.getConditionId());
		reply.setResolvedDescription(soCondition.getResolvedDescription());
		reply.setName(soCondition.getName());
		reply.setSequenceNum(condRel.getSequenceNum());
		reply.setDescription(soCondition.getDescription());
		return reply;
	}

	private static String extractSupLength(String supLengthNum,
			String supLengthTP) {
		StringBuffer supLength = new StringBuffer();
		if (supLengthNum == null) {
			supLengthNum = "0";
		}
		try {
			supLength.append(Integer.toString(Integer.parseInt(supLengthNum)));
		} catch (NumberFormatException nfe) {
			supLength.append(supLengthNum);
		}

		if (supLengthTP.equals("Y") || supLengthTP.equals("0")) {
			supLength.append(" Years");
		} else if (supLengthNum == null || supLengthTP.equals("")) {
			supLength.append(" Years");
		}
		if (supLengthTP.equals("M")) {
			supLength.append(" Months");
		} else if (supLengthTP.equals("D")) {
			supLength.append(" Days");
		}
		return supLength.toString();
	}

	/**
	 * @param supervisionOrder
	 * @return
	 */
	public static CaseOrderResponseEvent getCaseOrderResponseEvent(
			SupervisionOrder supervisionOrder) {
		CaseOrderResponseEvent re = new CaseOrderResponseEvent();
		re.setOrderId(supervisionOrder.getOID().toString());
		re.setOrderStatusId(supervisionOrder.getOrderStatusId());
		re.setOrderTitleId(supervisionOrder.getOrderTitleId());
		re.setIsHistoricalOrder(supervisionOrder.getIsHistoricalOrder());
		re.setVersionNum(supervisionOrder.getVersionNum());
		re.setOrderChainNum(supervisionOrder.getOrderChainNum());
		re.setOrderFileDate(supervisionOrder.getOrderFiledDate());
		re.setStatusChangeDate(supervisionOrder.getStatusChangeDate()); 
		//The following were added as part of the print signature page.
		Court aCourt = supervisionOrder.getCurrentCourt();
		if (aCourt != null){
			re.setCurrentCourtCategory(aCourt.getCourtCategory());
			re.setCurrentCourtId(supervisionOrder.getCurrentCourtId());
			re.setCurrentCourtNum(aCourt.getCourtNumber());
		}
		aCourt = supervisionOrder.getOrderCourt();
		if (aCourt != null){
			re.setCourtCategory(aCourt.getCourtCategory());
			re.setCourtId(supervisionOrder.getCurrentCourtId());
			re.setCourtNum(aCourt.getCourtNumber());
		}
		return re;
	}

	/**
	 * @param criminalCase
	 * @return
	 */
	public static CaseOrderResponseEvent getCaseResponseEvent(
			CriminalCase criminalCase) {
		CaseOrderResponseEvent re = new CaseOrderResponseEvent();
		//TODO:Replace this code once Framework handles date formatting
		// correctly. Remember to change mapping also.
		Date date = null;
		if (criminalCase.getFilingDate() != null
				&& !criminalCase.getFilingDate().trim().equals(PDConstants.BLANK)) {
			date = DateUtil.stringToDate(criminalCase.getFilingDate(),
					MMDDYY_FORMAT);
			if (date != null) {
				re.setCaseFileDate(date);
			}
		}

		re.setCaseNum(criminalCase.getCaseNum());
		re.setCdi(criminalCase.getCourtDivisionId());
		re.setConnectionId(DEFENDANT);
		re.setCourtId(criminalCase.getCourtId());
		Court court = Court.find(criminalCase.getCourtId());
		if (court != null) {
			re.setCourtNum(court.getCourtNumber());
			re.setCourtCategory(court.getCourtCategory());
		}
		re.setCurrentCourtId(re.getCourtId());
		re.setCurrentCourtCategory(re.getCourtCategory());
		re.setCurrentCourtNum(re.getCourtNum());
		re.setName(criminalCase.getDefendantName());
		re.setOffenseId(criminalCase.getOffenseCodeId());
		re.setOrderFileDate(null);
		re.setOrderStatusId(PDConstants.BLANK);
		re.setVersionTypeId(PDConstants.BLANK);
		re.setVersionNum(ZERO);
		re.setOrderVersion(PDConstants.BLANK);
		re.setSpn(criminalCase.getDefendantId());
		Supervision supervision = Supervision.find(criminalCase.getOID());
		
		String cdi = criminalCase.getCourtDivisionId();
		if(cdi.equalsIgnoreCase(PDCodeTableConstants.CSCD))
		{
			OutOfCountyProbationCase oocCase = (OutOfCountyProbationCase)OutOfCountyCaseFactory.find(criminalCase.getCaseNum(), cdi);
			if (oocCase != null){
//				set supervision begin and end dates
				re.setSupervisionOrderBeginDate(oocCase.getSupervisionBeginDate());
				re.setSupervisionOrderEndDate(oocCase.getSupervisionEndDate());
			
//				set pre trial intervention begin and end dates
				re.setProbationStartDate(oocCase.getPretrialInterventionBeginDate());
				re.setProbationEndDate(oocCase.getPretrialInterventionEndDate());
			
//				set supervision length
				re.setSupervisionLengthYears(Integer.toString(oocCase.getSupervisionYears()));
				re.setSupervisionLengthMonths(Integer.toString(oocCase.getSupervisionMonths()));
				re.setSupervisionLengthDays(Integer.toString(oocCase.getSupervisionDays()));
			
//				set confinement length
				re.setConfinementLengthYears(Integer.toString(oocCase.getConfinementYears()));
				re.setConfinementLengthMonths(Integer.toString(oocCase.getConfinementMonths()));
				re.setConfinementLengthDays(Integer.toString(oocCase.getConfinementDays()));
			} else {
				String aString = padInt(ZERO, TWO);
				re.setConfinementLengthDays(aString);
				re.setConfinementLengthMonths(aString);
				re.setConfinementLengthYears(aString);
				re.setSupervisionLengthDays(aString);
				re.setSupervisionLengthMonths(aString);
				re.setSupervisionLengthYears(aString);
				re.setSupervisionLengthNum(aString);
				re.setJuvSupervisionLengthMonths(aString);
				re.setJuvSupervisionLengthYears(aString);
				aString = padInt(ZERO, THREE);
				re.setJuvSupervisionLengthDays(aString);
			}
		}
		else
		{
			if (supervision != null) {
				SupervisionOrderHelper.setSupervisionInfo(re, supervision);
			} else {
				String aString = padInt(ZERO, TWO);
				re.setConfinementLengthDays(aString);
				re.setConfinementLengthMonths(aString);
				re.setConfinementLengthYears(aString);
				re.setSupervisionLengthDays(aString);
				re.setSupervisionLengthMonths(aString);
				re.setSupervisionLengthYears(aString);
				re.setSupervisionLengthNum(aString);
				re.setJuvSupervisionLengthMonths(aString);
				re.setJuvSupervisionLengthYears(aString);
				aString = padInt(ZERO, THREE);
				re.setJuvSupervisionLengthDays(aString);
			}
		}
		
		CostBillSummary cbs = CostBillSummary.find((String) criminalCase.getOID());
		if (cbs != null && !cbs.getFineAmountTotal().equals(PDConstants.BLANK)){
           try {
                double aDouble = Double.parseDouble(cbs.getFineAmountTotal());
                aDouble = (aDouble / 100);
    	        re.setFineAmountTotal(aDouble); 
           } catch (NumberFormatException e) {
                re.setFineAmountTotal(0.0);
             }
		}

		return re;
	}

	/**
	 * @param condRels
	 * @param courtNum
	 * @return
	 */
	public static Collection getConditionDetailResponseEvents(
			SupervisionOrder order) {
		Collection condRels = order.getSupervisionOrderConditionRels();
		// Performance Fix. Get Values for a list of RelIds
		Map relToValuesMap = getRelToValueMapFromRelIds(condRels);

		Collection responses = new ArrayList();
		if (condRels != null) {
			// get a collection of like conditions for this Order
			Set likeConditions = ImpactedOrderHelper.getLikeConditions(order);
			Iterator iter = condRels.iterator();
			SupervisionOrderConditionRel condRel = null;
			while (iter.hasNext()) {
				condRel = (SupervisionOrderConditionRel) iter.next();
				List orderCondRelValues = (List) relToValuesMap.get(condRel.getOID());
				SupOrderConditionResponseEvent socre = createConditionResponseEvent(condRel, orderCondRelValues);
				if (socre != null){
					if (likeConditions.contains(socre.getConditionId())) {
						socre.setLikeConditionInd(true);
					}
					responses.add(socre);
				}
			}
		}
		return responses;
	}
	/**
	 * @param supervisionOrder
	 * @return
	 */
	public static CaseOrderResponseEvent getLightCaseOrderResponseEvent(SupervisionOrder supervisionOrder, CriminalCase criminalCase) 
	{
		CaseOrderResponseEvent re = new CaseOrderResponseEvent();

		if (criminalCase.getFilingDate() != null && !criminalCase.getFilingDate().trim().equals(PDConstants.BLANK)) 
		{
			Date date = DateUtil.stringToDate(criminalCase.getFilingDate(), MMDDYY_FORMAT);
			if (date != null) 
			{
				re.setCaseFileDate(date);
			}
		}
//		if (supervisionOrder.getOffenseId() == null || supervisionOrder.getOffenseId().equals("")) 
//		{
			re.setOffenseId(criminalCase.getOffenseCodeId());
//		} 
//		else 
//		{
//			re.setOffenseId(supervisionOrder.getOffenseId());
//		}

		re.setName(criminalCase.getDefendantName());
		re.setCaseNum(criminalCase.getCaseNum());
		re.setCdi(criminalCase.getCourtDivisionId());
		if (criminalCase.getCourtId() != null)
		{
			String currentCourtId = criminalCase.getCourtId();
			int space_indx = currentCourtId.indexOf(' ');			
			re.setCurrentCourtCategory(currentCourtId.substring(0, space_indx));
			re.setCurrentCourtNum(currentCourtId.substring(space_indx + 1));
		}
		re.setComments(supervisionOrder.getComments());
		re.setConnectionId(DEFENDANT);
		re.setOrderId(supervisionOrder.getOID());
		re.setCourtId(supervisionOrder.getOrderCourtId());
		re.setDispositionTypeId(supervisionOrder.getDispositionTypeId());
		Court court = Court.find(supervisionOrder.getOrderCourtId());
		if (court != null) 
		{
			re.setCourtNum(court.getCourtNumber());
			re.setCourtCategory(court.getCourtCategory());
		}
		if (supervisionOrder.getCurrentCourtId() != null){
			re.setCurrentCourtId(supervisionOrder.getCurrentCourtId());
			court = Court.find(supervisionOrder.getCurrentCourtId());
		} else {
			re.setCurrentCourtId(supervisionOrder.getOrderCourtId());
			court = Court.find(supervisionOrder.getOrderCourtId());
		}
		if (supervisionOrder.getOrigJudgeFirstName() != null){
			re.setOrigJudgeFirstName(supervisionOrder.getOrigJudgeFirstName().trim());
		}
		if (supervisionOrder.getOrigJudgeLastName() != null){
			re.setOrigJudgeLastName(supervisionOrder.getOrigJudgeLastName().trim());
		}
		//Retrieve data entered on print signature page.
		re.setSignedByDefendantDate(supervisionOrder.getOrderSignedByDefendantDate());
		re.setSignedByDefendant(supervisionOrder.isOrderSignedByDefendant());
		re.setSignedByOfficerDate(supervisionOrder.getOrderSignedDate());
		re.setSignedByJudgeDate(supervisionOrder.getOrderSignedByJudgeDate());
		re.setPresentedByFirstName(supervisionOrder.getOrderPresentorFirstName());
		re.setPresentedByLastName(supervisionOrder.getOrderPresentorLastName());
		re.setJudgeFirstName(supervisionOrder.getSignedByFirstName());
		re.setJudgeLastName(supervisionOrder.getSignedByLastName());
		re.setJuvenileCourtId(supervisionOrder.getJuvenileCourtId());
		re.setJuvSupervisionOrderBeginDate(supervisionOrder.getJuvSupervisionBeginDate());
		String aString = padInt(supervisionOrder.getJuvSupervisionLenDays(), THREE);
		re.setJuvSupervisionLengthDays(aString);
		aString = padInt(supervisionOrder.getJuvSupervisionLenMonths(), TWO);
		re.setJuvSupervisionLengthMonths(aString);
		aString = padInt(supervisionOrder.getJuvSupervisionLenYears(), TWO);
		re.setJuvSupervisionLengthYears(aString);

		re.setSpecialCourtCd(supervisionOrder.getSpecialCourtCd());
		
		//Need to change this to Revision Date when column is added to table
		//and PD is changed.
		//		re.setSupOrderRevisionDate(supervisionOrder.getRevisionDate());
		re.setIsHistoricalOrder(supervisionOrder.getIsHistoricalOrder());
		re.setOrderFileDate(supervisionOrder.getOrderFiledDate());
		re.setOrderStatusId(supervisionOrder.getOrderStatusId());
		re.setVersionNum(supervisionOrder.getVersionNum());
		re.setOrderChainNum(supervisionOrder.getOrderChainNum());
		re.setVersionTypeId(supervisionOrder.getVersionTypeId());
		re.setOrderVersion(getOrderVersionLiteral(supervisionOrder));
		re.setOrderTitleId(supervisionOrder.getOrderTitleId());
		re.setSummaryChanges(supervisionOrder.getSummaryChanges());
		re.setSupervisionOrderBeginDate(supervisionOrder.getCaseSupervisionBeginDate());
		re.setSupervisionOrderEndDate(supervisionOrder.getCaseSupervisionEndDate());
		re.setPlea(supervisionOrder.getPlea());
		re.setPrintedName(supervisionOrder.getPrintedName());
		re.setPrintedOffenseDesc(supervisionOrder.getPrintedOffenseDesc());
		re.setSpn(supervisionOrder.getDefendantId());
		if (supervisionOrder.getCreateJIMS2UserID() != null && (supervisionOrder.getCreateJIMS2UserID().startsWith(MIGRATED)) ){
			re.setMigratedOrder(true);
		}

		if (supervisionOrder.isOrderInProgress()){
		    re.setFineAmountTotal(supervisionOrder.getFineAmount());
		} else {
		    CostBillSummary cbs = CostBillSummary.find((String) criminalCase.getOID());
		    if (cbs != null && !cbs.getFineAmountTotal().equals(PDConstants.BLANK)){
               try {
                    double aDouble = Double.parseDouble(cbs.getFineAmountTotal());
                    aDouble = (aDouble / 100);
    		        re.setFineAmountTotal(aDouble); 
                } catch (NumberFormatException e) {
                    re.setFineAmountTotal(0.0);
                 }
		    }
		}
		if (supervisionOrder.hasSupervisionInfo()) 
		{
			re = SupervisionOrderHelper.setSupervisionInfoFromOrder(re, supervisionOrder);
		} 
		else 
		{
			Supervision supervision = Supervision.find((String) criminalCase.getOID());
			if (supervision != null) 
			{
				re = SupervisionOrderHelper.setSupervisionInfo(re, supervision);
			}
		}
		if (supervisionOrder.getAgencyId().equals(UIConstants.PTR) && PDCodeTableConstants.PTS.equals(re.getCdi())) {
			re.setOutOfCountyCase(true);
		}
		else if (supervisionOrder.getAgencyId().equals(UIConstants.CSC) && PDCodeTableConstants.CSCD.equals(re.getCdi())) {
			re.setOutOfCountyCase(true);
		}
		if ( supervisionOrder.getInactivationDate() != null ) {
			re.setStatusChangeDate(supervisionOrder.getInactivationDate());
			//re.setUpdateDate( supervisionOrder.getInactivationDate() ); 
		//} else if ( supervisionOrder.getActivationDate() != null ) {
			//re.setStatusChangeDate(supervisionOrder.getActivationDate());
			//re.setUpdateDate( supervisionOrder.getActivationDate() ); 
		}else if (supervisionOrder.getStatusChangeDate() != null){   
			re.setStatusChangeDate( supervisionOrder.getStatusChangeDate()); 
		} 
		/*else if ( supervisionOrder.getUpdateDate() != null ) { 
			re.setUpdateDate(supervisionOrder.getUpdateDate());
		}*/
			
		
		
		return re;
	}

	/**
	 * @param re
	 * @param supervision
	 */
	private static CaseOrderResponseEvent setSupervisionInfoFromOrder(CaseOrderResponseEvent re, SupervisionOrder order) 
	{
		String aString = padInt(order.getConfinementLengthDays(), TWO);
		re.setConfinementLengthDays(aString);
		aString = padInt(order.getConfinementLengthMonths(), TWO);
		re.setConfinementLengthMonths(aString);
		aString = padInt(order.getConfinementLengthYears(), TWO);
		re.setConfinementLengthYears(aString);
		aString = padInt(order.getSupervisionLengthDays(), TWO);
		re.setSupervisionLengthDays(aString);
		aString = padInt(order.getSupervisionLengthMonths(), TWO);
		re.setSupervisionLengthMonths(aString);
		aString = padInt(order.getSupervisionLengthYears(), TWO);
		re.setSupervisionLengthYears(aString);
		
		return re;
	}

	/**
	 * @param re
	 * @param supervision
	 */
	private static CaseOrderResponseEvent setSupervisionInfo(CaseOrderResponseEvent re, Supervision supervision) 
	{
		
		if (supervision.getProbationStartDate() != null 
		        && !supervision.getProbationStartDate().trim().equals(SIX_ZEROES)) 
		{
			re.setProbationStartDate(DateUtil.stringToDate(supervision.getProbationStartDate(), PDConstants.DATE_FORMAT_MMDDYY));
		}
		if (supervision.getProbationEndDate() != null
				&& !supervision.getProbationEndDate().trim().equals(PDConstants.SIX_ZEROES)) 
		{
			re.setProbationEndDate(DateUtil.stringToDate(supervision.getProbationEndDate(), PDConstants.DATE_FORMAT_MMDDYY));
		}
		String aString = null;
		Integer anInteger = null;
		String supLenNumAsString = supervision.getSupervisionLengthNum();
		if (supLenNumAsString != null && !supLenNumAsString.trim().equals(PDConstants.BLANK)) 
		{
			anInteger = new Integer(supLenNumAsString);
			aString = padInt(anInteger.intValue(), TWO);
			re.setSupervisionLengthNum(aString);
			String timePeriod = supervision.supervisionLengthTimePeriod;
			if (timePeriod.equals(YEARS)) 
			{
				re.setSupervisionLengthYears(aString);
				re.setSupervisionLengthMonths(padInt(ZERO, TWO));
				re.setSupervisionLengthDays(padInt(ZERO, TWO));
			} 
			else if (timePeriod.equals(MONTHS)) 
			{
				re.setSupervisionLengthMonths(aString);
				re.setSupervisionLengthDays(padInt(ZERO, TWO));
				re.setSupervisionLengthYears(padInt(ZERO, TWO));
			} 
			else if (timePeriod.equals(DAYS)) 
			{
				re.setSupervisionLengthDays(aString);
				re.setSupervisionLengthMonths(padInt(ZERO, TWO));
				re.setSupervisionLengthYears(padInt(ZERO, TWO));
			} else {
				aString = padInt(ZERO, TWO);
				re.setSupervisionLengthNum(aString);
				re.setSupervisionLengthMonths(aString);
				re.setSupervisionLengthDays(aString);
				re.setSupervisionLengthYears(aString);
			}
		} 
		else 
		{
			aString = padInt(ZERO, TWO);
			re.setSupervisionLengthNum(aString);
			re.setSupervisionLengthMonths(aString);
			re.setSupervisionLengthDays(aString);
			re.setSupervisionLengthYears(aString);
		}

		if (supervision.getStateJailFelonyConfinementDays() != null
				&& !supervision.getStateJailFelonyConfinementDays().trim().equals(PDConstants.BLANK)) 
		{
			anInteger = new Integer(supervision.getStateJailFelonyConfinementDays());
			aString = padInt(anInteger.intValue(), TWO);
			re.setConfinementLengthDays(aString);
		} else {
			re.setConfinementLengthDays(padInt(ZERO, TWO));
		}
		if (supervision.getStateJailFelonyConfinementMonths() != null
				&& !supervision.getStateJailFelonyConfinementMonths().trim().equals(PDConstants.BLANK)) 
		{
			anInteger = new Integer(supervision.getStateJailFelonyConfinementMonths());
			aString = padInt(anInteger.intValue(), TWO);
			re.setConfinementLengthMonths(aString);
		} else 
		{
			re.setConfinementLengthMonths(padInt(ZERO, TWO));
		}
		if (supervision.getStateJailFelonyConfinementYears() != null
				&& !supervision.getStateJailFelonyConfinementYears().trim().equals(PDConstants.BLANK)) 
		{
			anInteger = new Integer(supervision.getStateJailFelonyConfinementYears());
			aString = padInt(anInteger.intValue(), TWO);
			re.setConfinementLengthYears(aString);
		} 
		else 
		{
			re.setConfinementLengthYears(padInt(ZERO, TWO));
		}
		return re;
	}

	/**
	 * @return
	 */
	public static Collection getOrderVersionConditionDetails(
			SupervisionOrder order) {
		// get like conditions for Order
		Set likeConditionIds = ImpactedOrderHelper.getLikeConditions(order);

		Collection condRels = order.getSupervisionOrderConditionRels();
		Collection responses = new ArrayList();
		if (condRels != null) {
			Iterator iter = condRels.iterator();
			SupervisionOrderConditionRel condRel = null;
			while (iter.hasNext()) {
				condRel = (SupervisionOrderConditionRel) iter.next();
				SupOrderConditionResponseEvent socre = createOrderVersionConditionResponseEvent(condRel);
				//check if it is a like condition
				if (likeConditionIds.contains(socre.getConditionId())) {
					socre.setLikeConditionInd(true);
				}
				responses.add(socre);
			}
		}
		return responses;
	}

	/**
	 * @param versionTypeId
	 * @param versionNum
	 * @return
	 */
	public static String getOrderVersionLiteral(String versionTypeId,
			int versionNum) {
		StringBuffer versionLit = new StringBuffer();
		if (versionTypeId != null && !versionTypeId.equals(PDConstants.BLANK)) {
			Code code = Code.find(PDCodeTableConstants.VERSION_TYPE, versionTypeId);
			if (code != null) {
				versionLit.append(code.getDescription());
			} else {
				versionLit.append(versionTypeId);
			}
			if (!versionTypeId.equals(SupervisionConstants.ORIGINAL)
					&& versionNum > 0) {
				versionLit.append(" v. ");
				versionLit.append(versionNum);
			}
		}
		return versionLit.toString();
	}

	/**
	 * @param supervisionOrder
	 * @return
	 */
	public static String getOrderVersionLiteral(
			SupervisionOrder supervisionOrder) {
		StringBuffer versionLit = new StringBuffer();
		if (supervisionOrder.getVersionTypeId() != null
				&& !supervisionOrder.getVersionTypeId().equals(PDConstants.BLANK)) {
			Code code = supervisionOrder.getVersionType();
			if (code != null) {
				versionLit.append(code.getDescription());
			} else {
				versionLit.append(supervisionOrder.getVersionTypeId());
			}
			if (!supervisionOrder.getVersionTypeId().equals(SupervisionConstants.ORIGINAL)
					&& supervisionOrder.getVersionNum() > 0) {
				versionLit.append(" v. ");
				versionLit.append(supervisionOrder.getVersionNum());
			}
		}
		return versionLit.toString();
	}

	public static Iterator getOrderVersions(
			GetSupervisionOrderVersionsEvent requestEvent) {
		Collection orders = new HashSet();

		IHome home = new Home();
		// search for the previous versions (Withdrawn orders not considered)
		// 2. status: !Withdrraw
		requestEvent.setStatusId(PDCodeTableConstants.STATUS_WITHDRAWN_ID);
		Iterator orderVersions=null;
		if(requestEvent.isAllOrderChains()){
			GetAllSupervisionOrderVersionsEvent requestAllEvent=new GetAllSupervisionOrderVersionsEvent();
			requestAllEvent.setAgencyId(requestEvent.getAgencyId());
			requestAllEvent.setAllOrderChains(requestEvent.isAllOrderChains());
			requestAllEvent.setCaseNum(requestEvent.getCaseNum());
			requestAllEvent.setStatusId(requestEvent.getStatusId());
			orderVersions = home.findAll(requestAllEvent, SupervisionOrder.class);
		}
		else{
		orderVersions = home.findAll(requestEvent, SupervisionOrder.class);
		}
		// add in the collection
		while (orderVersions.hasNext()) {
			SupervisionOrder supervisionOrder = (SupervisionOrder) orderVersions.next();
			// get ResponseEvent
			SupervisionOrderDetailResponseEvent orderRespEvent = getSupervisionOrderVersionResponseEvent(supervisionOrder);
			orders.add(orderRespEvent);
		}

		return orders.iterator();
	}

	public static Map getRelToValueMapFromRelIds(Collection condRels) {
		Map relToValuesMap = new HashMap();
		// get ConditionRelValues from ConditionRels
		if (condRels != null) {
			List condRelIds = new ArrayList();
			for (Iterator iter = condRels.iterator(); iter.hasNext();) {
				condRelIds.add(((SupervisionOrderConditionRel) iter.next()).getOID().toString());
			}
			if (condRelIds.size() > 0) {
				Iterator condRelValues = SupervisionOrderConditionRelValue.findAllByRelIds(condRelIds);
				while (condRelValues.hasNext()) {
					SupervisionOrderConditionRelValue orderRelValue = (SupervisionOrderConditionRelValue) condRelValues.next();
					List orderRelValues = (List) relToValuesMap.get(orderRelValue
									.getSupervisionOrderConditionRelId());
					if (orderRelValues == null) {
						orderRelValues = new ArrayList();
						relToValuesMap.put(orderRelValue.getSupervisionOrderConditionRelId(),orderRelValues);
					}
					orderRelValues.add(orderRelValue);
				}
			}
		}
		return relToValuesMap;
	}
	/**
	 * @param order
	 * @return
	 */
	public static SupervisionOrderDetailResponseEvent getSupervisionOrderDetailResponseEvent(
			SupervisionOrder order, GetSupervisionOrderDetailsEvent requestEvent) {
		SupervisionOrderDetailResponseEvent sodre = new SupervisionOrderDetailResponseEvent();
		sodre.setCdi(order.getCriminalCaseId().substring(ZERO,THREE));
		sodre.setCaseNum(order.getCriminalCaseId().substring(THREE));
		sodre.setCriminalCaseid(order.getCriminalCaseId());

		// ------------ SupervisionOrder attributes -----------------------//
		sodre.setOrderId(order.getOID());
		sodre.setAgencyId(order.getAgencyId());
		sodre.setComments(order.getComments());
		sodre.setOrderCourtId(order.getOrderCourtId());
		sodre.setCriminalCaseid(order.getCriminalCaseId());
		sodre.setCurrentCourtId(order.getCurrentCourtId());
		sodre.setDefendantId(order.getDefendantId());
		sodre.setHistoricalOrder(order.getIsHistoricalOrder());
		if (order.getCreateJIMS2UserID() != null && (order.getCreateJIMS2UserID().startsWith(MIGRATED)) ){
			sodre.setMigrated(true);
		}
		sodre.setJuvenileCourtId(order.getJuvenileCourtId());
		sodre.setSpecialCourtCd(order.getSpecialCourtCd());
		sodre.setPlea(order.getPlea());
		sodre.setPrintedName(order.getPrintedName());
		sodre.setPrintedOffenseDesc(order.getPrintedOffenseDesc());
		sodre.setSignedByDefendant(order.isOrderSignedByDefendant());
		sodre.setSignedByDefendantDate(order.getOrderSignedByDefendantDate());
		sodre.setSignedByJudgeDate(order.getOrderSignedByJudgeDate());
		sodre.setLimitedSupervisionPeriod(order.isLimitedSupervisionPeriod());
		CriminalCase criminalCase = order.getCriminalCase();
		if (criminalCase != null){
			sodre.setOffenseId(criminalCase.getOffenseCodeId());
		} else {
			sodre.setOffenseId(PDConstants.BLANK);
		}
		sodre.setOrderFileDate(order.getOrderFiledDate());
		sodre.setOrderInProgress(order.isOrderInProgress());
		sodre.setOrderSignedDate(order.getOrderSignedDate());
		sodre.setOrderStatusId(order.getOrderStatusId());
		sodre.setOrderTitleId(order.getOrderTitleId());
		String fName = "";
		String lName = "";
		if (order.getOrigJudgeFirstName() != null){
			fName = order.getOrigJudgeFirstName().trim();
		}
		if (order.getOrigJudgeLastName() != null){
			lName = order.getOrigJudgeLastName().trim();
		}
		Name name = new Name(fName, PDConstants.BLANK, lName);
		sodre.setOrigJudgeName(name);

		sodre.setParentSupervisionOrderId(order.getParentSupervisionOrderId());
		sodre.setPrintSpanish(order.isPrintSpanish());
		sodre.setPrintTemplateId(order.getPrintTemplateId());
		sodre.setSignedByTypeId(order.getSignedByTypeId());
		name = new Name(order.getSignedByFirstName(), PDConstants.BLANK, order.getSignedByLastName());
		sodre.setSignedByName(name);
		sodre.setSupOrderRevisionDate(order.getRevisionDate());
		sodre.setModificationReason(order.getModificationReason());
		sodre.setSummaryOfChanges(order.getSummaryChanges());
		sodre.setSuggestedOrderId(order.getSuggestedOrderId());
		sodre.setSupervisionOrderBeginDate(order.getLimitedSupervisionBeginDate());
		sodre.setSupervisionOrderEndDate(order.getLimitedSupervisionEndDate());
		//sodre.setUpdateDate(order.getUpdateDate());  
		sodre.setStatusChangeDate(order.getStatusChangeDate()); 
		sodre.setOrderVersion(SupervisionOrderHelper.getOrderVersionLiteral(order));
		sodre.setVersionNum(order.getVersionNum());
		sodre.setOrderChainNum(order.getOrderChainNum());
		sodre.setVersionTypeId(order.getVersionTypeId());

		sodre.setCaseSupervisionBeginDate(order.getCaseSupervisionBeginDate());
		sodre.setCaseSupervisionEndDate(order.getCaseSupervisionEndDate());
		
		String aString = padInt(order.getConfinementLengthDays(), TWO);
		sodre.setConfinementLengthDays(aString);
		
		aString = padInt(order.getConfinementLengthMonths(), TWO);
		sodre.setConfinementLengthMonths(aString);
		
		aString = padInt(order.getConfinementLengthYears(), TWO);
		sodre.setConfinementLengthYears(aString);

		sodre.setDispositionTypeId(order.getDispositionTypeId());
		if (order.getDispositionType() != null)
			sodre.setDispositionType(order.getDispositionType().getDescription());

		sodre.setJuvSupervisionOrderBeginDate(order.getJuvSupervisionBeginDate());
		aString = padInt(order.getSupervisionLengthDays(), TWO);
		sodre.setSupervisionLengthDays(aString);

		aString = padInt(order.getSupervisionLengthMonths(), TWO);
		sodre.setSupervisionLengthMonths(aString);

		aString = padInt(order.getSupervisionLengthYears(), TWO);
		sodre.setSupervisionLengthYears(aString);
		
		aString = padInt(order.getJuvSupervisionLenDays(), TWO);
		sodre.setJuvSupervisionLengthDays(aString);
		
		aString = padInt(order.getJuvSupervisionLenMonths(), TWO);
		sodre.setJuvSupervisionLengthMonths(aString);

		aString = padInt(order.getJuvSupervisionLenYears(), TWO);
		sodre.setJuvSupervisionLengthYears(aString);

		name = new Name(order.getOrderPresentorFirstName(), PDConstants.BLANK, order
				.getOrderPresentorLastName());
		sodre.setOrderPresentorName(name);
		sodre.setFineAmountTotal(PDUtil.formatCurrency(new Double(order.getFineAmount()), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT));		
		if (order.getCreateJIMS2UserID() != null && order.getCreateJIMS2UserID().startsWith(MIGRATED)){
			sodre.setMigrated(true);
		}
		
		// conditions
		Collection conditionResponses = SupervisionOrderHelper.getConditionDetailResponseEvents(order);
		sodre.setConditions(conditionResponses);
		if (!requestEvent.isDeleteAction()){
			SupervisionOrderHelper.finishDetails(sodre, order);
		} else {
			SupervisionOrderHelper.finishDetailsForDelete(sodre, order);
		}

		return sodre;
	}
	
	public static SupervisionOrderDetailResponseEvent getLightSupervisionOrderResponseEvent(
			SupervisionOrder order) {
		SupervisionOrderDetailResponseEvent sodre = new SupervisionOrderDetailResponseEvent();
		sodre.setCdi(order.getCriminalCaseId().substring(ZERO,THREE));
		sodre.setCaseNum(order.getCriminalCaseId().substring(THREE));
		sodre.setCriminalCaseid(order.getCriminalCaseId());

		// ------------ SupervisionOrder attributes -----------------------//
		sodre.setOrderId(order.getOID());
		sodre.setAgencyId(order.getAgencyId());
		sodre.setComments(order.getComments());
		sodre.setOrderCourtId(order.getOrderCourtId());
		sodre.setCriminalCaseid(order.getCriminalCaseId());
		sodre.setCurrentCourtId(order.getCurrentCourtId());
		sodre.setDefendantId(order.getDefendantId());
		sodre.setHistoricalOrder(order.getIsHistoricalOrder());
		if (order.getCreateJIMS2UserID() != null && (order.getCreateJIMS2UserID().startsWith(MIGRATED)) ){
			sodre.setMigrated(true);
		}
		sodre.setJuvenileCourtId(order.getJuvenileCourtId());
		sodre.setSpecialCourtCd(order.getSpecialCourtCd());
		sodre.setPlea(order.getPlea());
		sodre.setPrintedName(order.getPrintedName());
		sodre.setPrintedOffenseDesc(order.getPrintedOffenseDesc());
		sodre.setSignedByDefendant(order.isOrderSignedByDefendant());
		sodre.setSignedByDefendantDate(order.getOrderSignedByDefendantDate());
		sodre.setSignedByJudgeDate(order.getOrderSignedByJudgeDate());
		sodre.setLimitedSupervisionPeriod(order.isLimitedSupervisionPeriod());
		/*CriminalCase criminalCase = order.getCriminalCase();
		if (criminalCase != null){
			sodre.setOffenseId(criminalCase.getOffenseCodeId());
		} else {
			sodre.setOffenseId(PDConstants.BLANK);
		}*/
		sodre.setOrderFileDate(order.getOrderFiledDate());
		sodre.setOrderInProgress(order.isOrderInProgress());
		sodre.setOrderSignedDate(order.getOrderSignedDate());
		sodre.setOrderStatusId(order.getOrderStatusId());
		sodre.setOrderTitleId(order.getOrderTitleId());
		String fName = "";
		String lName = "";
		if (order.getOrigJudgeFirstName() != null){
			fName = order.getOrigJudgeFirstName().trim();
		}
		if (order.getOrigJudgeLastName() != null){
			lName = order.getOrigJudgeLastName().trim();
		}
		Name name = new Name(fName, PDConstants.BLANK, lName);
		sodre.setOrigJudgeName(name);

		sodre.setParentSupervisionOrderId(order.getParentSupervisionOrderId());
		sodre.setPrintSpanish(order.isPrintSpanish());
		sodre.setPrintTemplateId(order.getPrintTemplateId());
		sodre.setSignedByTypeId(order.getSignedByTypeId());
		name = new Name(order.getSignedByFirstName(), PDConstants.BLANK, order.getSignedByLastName());
		sodre.setSignedByName(name);
		sodre.setSupOrderRevisionDate(order.getRevisionDate());
		sodre.setModificationReason(order.getModificationReason());
		sodre.setSummaryOfChanges(order.getSummaryChanges());
		sodre.setSuggestedOrderId(order.getSuggestedOrderId());
		sodre.setSupervisionOrderBeginDate(order.getLimitedSupervisionBeginDate());
		sodre.setSupervisionOrderEndDate(order.getLimitedSupervisionEndDate());
		//sodre.setUpdateDate(order.getUpdateDate()); 
		sodre.setStatusChangeDate(order.getStatusChangeDate()); 
		sodre.setOrderVersion(SupervisionOrderHelper.getOrderVersionLiteral(order));
		sodre.setVersionNum(order.getVersionNum());
		sodre.setOrderChainNum(order.getOrderChainNum());
		sodre.setVersionTypeId(order.getVersionTypeId());

		sodre.setCaseSupervisionBeginDate(order.getCaseSupervisionBeginDate());
		sodre.setCaseSupervisionEndDate(order.getCaseSupervisionEndDate());
		
		String aString = padInt(order.getConfinementLengthDays(), TWO);
		sodre.setConfinementLengthDays(aString);
		
		aString = padInt(order.getConfinementLengthMonths(), TWO);
		sodre.setConfinementLengthMonths(aString);
		
		aString = padInt(order.getConfinementLengthYears(), TWO);
		sodre.setConfinementLengthYears(aString);

		sodre.setDispositionTypeId(order.getDispositionTypeId());
		if (order.getDispositionType() != null)
			sodre.setDispositionType(order.getDispositionType().getDescription());

		sodre.setJuvSupervisionOrderBeginDate(order.getJuvSupervisionBeginDate());
		aString = padInt(order.getSupervisionLengthDays(), TWO);
		sodre.setSupervisionLengthDays(aString);

		aString = padInt(order.getSupervisionLengthMonths(), TWO);
		sodre.setSupervisionLengthMonths(aString);

		aString = padInt(order.getSupervisionLengthYears(), TWO);
		sodre.setSupervisionLengthYears(aString);
		
		aString = padInt(order.getJuvSupervisionLenDays(), TWO);
		sodre.setJuvSupervisionLengthDays(aString);
		
		aString = padInt(order.getJuvSupervisionLenMonths(), TWO);
		sodre.setJuvSupervisionLengthMonths(aString);

		aString = padInt(order.getJuvSupervisionLenYears(), TWO);
		sodre.setJuvSupervisionLengthYears(aString);

		name = new Name(order.getOrderPresentorFirstName(), PDConstants.BLANK, order
				.getOrderPresentorLastName());
		sodre.setOrderPresentorName(name);
		sodre.setFineAmountTotal(PDUtil.formatCurrency(new Double(order.getFineAmount()), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT));		
		if (order.getCreateJIMS2UserID() != null && order.getCreateJIMS2UserID().startsWith(MIGRATED)){
			sodre.setMigrated(true);
		}
		return sodre;
	}

	private static void finishDetailsForDelete(SupervisionOrderDetailResponseEvent sodre, SupervisionOrder order) {
		if (order.getCriminalCaseId() != null) {
			CriminalCase criminalCase = order.getCriminalCase();
			if (criminalCase != null && criminalCase.getFilingDate() != null
					&& !criminalCase.getFilingDate().equals(PDConstants.BLANK)) {
				Date date = DateUtil.stringToDate(criminalCase.getFilingDate(),	MMDDYY_FORMAT);
				if (date != null) {
					sodre.setCaseFileDate(date);
				}
			}		
		}
	}

	private static void finishDetails(SupervisionOrderDetailResponseEvent sodre, SupervisionOrder order) {
		if (order.getCriminalCaseId() != null) {
			CriminalCase criminalCase = order.getCriminalCase();
			if (criminalCase != null) {
				Name name = new Name(PDConstants.BLANK, PDConstants.BLANK, criminalCase.getDefendantName());
				sodre.setDefendantName(name);
			}
			if (criminalCase != null && criminalCase.getFilingDate() != null
					&& !criminalCase.getFilingDate().equals(PDConstants.BLANK)) {
				Date date = DateUtil.stringToDate(criminalCase.getFilingDate(),	MMDDYY_FORMAT);
				if (date != null) {
					sodre.setCaseFileDate(date);
				}
			}
			if (criminalCase != null){
				sodre.setCaseNum(criminalCase.getCaseNum());
				sodre.setCdi(criminalCase.getCourtDivisionId());				
				sodre.setOffenseId(criminalCase.getOffenseCodeId());
				if (criminalCase.getOffenseCode() != null){
						sodre.setOffenseLevel(criminalCase.getOffenseCode()
								.getLevel());					
					}
				} else {
					sodre.setOffenseLevel(PDConstants.BLANK);
				}
		}

		// ------------ Supervision attributes -----------------------//
		Supervision sup = Supervision.find(order.getCriminalCaseId());
		if (sup != null) {
			sodre.setDeferredSupervisionLength(extractSupLength(sup
					.getDeferredSupervisionLength(), sup
					.getFormattedDeferredTermPeriod()));
		}
		//Override legacy supervision info with that on the supervision order.
		sodre.setProbationStartDate(DateUtil.dateToString(order.getCaseSupervisionBeginDate(), DateUtil.DATE_FMT_1));
		sodre.setProbationEndDate(DateUtil.dateToString(order.getCaseSupervisionEndDate(), DateUtil.DATE_FMT_1));
		sodre.setJuvSupervisionLengthString(extractSupLen(order.getJuvSupervisionLenYears(), order.getJuvSupervisionLenMonths(), order.getJuvSupervisionLenDays()));
		sodre.setSupervisionLengthNum(extractSupLen(order.getSupervisionLengthYears(), order.getSupervisionLengthMonths(), order.getSupervisionLengthDays()));
		sodre.setJailTime(extractSupLen(order.getConfinementLengthYears(), order.getConfinementLengthMonths(), order.getConfinementLengthDays()));
		// ------------ Court attributes -----------------------//
		Court court = Court.find(order.getOrderCourtId());
		if (court != null) {
			sodre.setOrderCourtCategory(court.getCourtCategory());
			sodre.setOrderCourtNum(court.getCourtNumber());
		}
		if (order.getCurrentCourtId() != null){
			court = Court.find(order.getCurrentCourtId());
			if (court != null) {
				sodre.setCurrentCourtCategory(court.getCourtCategory());
				sodre.setCurrentCourtNum(court.getCourtNumber());
			}
		} else if (order.getOrderCourtId() != null){
			sodre.setCurrentCourtCategory(sodre.getOrderCourtCategory());
			sodre.setCurrentCourtNum(sodre.getOrderCourtNum());
		}
		sodre.setSupervisionPeriodId(order.getSupervisionPeriodId());
		if (sodre.getAgencyId().equals(UIConstants.PTR) && PDCodeTableConstants.PTS.equals(sodre.getCdi())) {
			sodre.setOutOfCountyCase(true);
		}
		else if (sodre.getAgencyId().equals(UIConstants.CSC) && PDCodeTableConstants.CSCD.equals(sodre.getCdi())) {
			sodre.setOutOfCountyCase(true);
		}	
	}

	public static String padInt(int anInt, int length) {
		String aString = String.valueOf(anInt);
		StringBuffer sb = new StringBuffer(aString);
		while (sb.length() < length){
			sb.insert(0, "0");
		}
		return sb.toString();
	}

	/**
     * @param supervisionLengthYears
     * @param supervisionLengthMonths
     * @param supervisionLengthDays
     */
    private static String extractSupLen(int years, int months, int days) {
		StringBuffer supLen = new StringBuffer();
		if (years > 0){
		    supLen.append(years);
		    if (years > 1){
		        supLen.append(" Years");
		    } else {
		        supLen.append(" Year");
		    }
		}
		if (months > 0){
		    if (supLen.length() > 0){
		        supLen.append(", ");
		    }
		    supLen.append(months);
		    if (months > 1){
		        supLen.append(" Months");
		    } else {
		        supLen.append(" Month");
		    }
		}
		if (days > 0){
		    if (supLen.length() > 0){
		        supLen.append(", ");
		    }
		    supLen.append(days);
		    if (days > 1){
		        supLen.append(" Days");
		    } else {
		        supLen.append(" Day");
		    }
		}     
		return supLen.toString();
    }

    /**
	 * @param order
	 * @return
	 */
	public static SupervisionOrderDetailResponseEvent getSupervisionOrderVersionResponseEvent(
			SupervisionOrder order) {
		SupervisionOrderDetailResponseEvent sodre = new SupervisionOrderDetailResponseEvent();
		sodre.setOrderId(order.getOID().toString());
		sodre.setAgencyId(order.getAgencyId());
		sodre.setOrderChainNum(order.getOrderChainNum());
		sodre.setCriminalCaseid(order.getCriminalCaseId());
		sodre.setOrderStatusId(order.getOrderStatusId());
		sodre.setOrderTitleId(order.getOrderTitleId());
		if (order.getCreateJIMS2UserID() != null && (order.getCreateJIMS2UserID().startsWith(MIGRATED)) ){
			sodre.setMigrated(true);
		}
		try {
            double fineAmt = order.getFineAmount();
            sodre.setFineAmountTotal(PDUtil.formatCurrency(new Double(fineAmt), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT));
        } catch (NumberFormatException e) {
            sodre.setFineAmountTotal("0.00");
        }
		sodre.setOrderVersion(SupervisionOrderHelper.getOrderVersionLiteral(order));
		sodre.setVersionTypeId(order.getVersionTypeId());
		sodre.setModificationReason(order.getModificationReason());
		sodre.setActivationDate(order.getActivationDate());
		sodre.setPlea(order.getPlea());
		sodre.setPrintedOffenseDesc(order.getPrintedOffenseDesc());
		sodre.setCaseSupervisionBeginDate(order.getCaseSupervisionBeginDate());
		sodre.setCaseSupervisionEndDate(order.getCaseSupervisionEndDate());
		if (order.getConfinementLengthDays() == 0){
			sodre.setConfinementLengthDays("");
		} else {
			sodre.setConfinementLengthDays(String.valueOf(order.getConfinementLengthDays()));
		}	
		if (order.getConfinementLengthMonths() == 0){
			sodre.setConfinementLengthMonths("");
		} else {
			sodre.setConfinementLengthMonths(String.valueOf(order.getConfinementLengthMonths()));
		}	
		if (order.getConfinementLengthYears() == 0){
			sodre.setConfinementLengthYears("");
		} else {
			sodre.setConfinementLengthYears(String.valueOf(order.getConfinementLengthYears()));
		}	
		sodre.setDispositionTypeId(order.getDispositionTypeId());
		if (order.getDispositionType() != null)
			sodre.setDispositionType(order.getDispositionType().getDescription());
		CriminalCase criminalCase = order.getCriminalCase();
		if (criminalCase != null){
			sodre.setOffenseId(criminalCase.getOffenseCodeId());
			OffenseCode offenseCode = criminalCase.getOffenseCode();
			if (offenseCode != null) {
				sodre.setOffense(offenseCode.getDescription());
			}
		}
		if (order.getSupervisionLengthDays() == 0)
			sodre.setSupervisionLengthDays("");
		else
			sodre.setSupervisionLengthDays(String.valueOf(order.getSupervisionLengthDays()));
		if (order.getSupervisionLengthMonths() == 0)
			sodre.setSupervisionLengthMonths("");
		else
			sodre.setSupervisionLengthMonths(String.valueOf(order.getSupervisionLengthMonths()));
		if (order.getSupervisionLengthYears() == 0)
			sodre.setSupervisionLengthYears("");
		else
			sodre.setSupervisionLengthYears(String.valueOf(order.getSupervisionLengthYears()));

// defect #61848 
		sodre.setPrintedName(order.getPrintedName());
		String fName = null;
		String lName = null;
		if (order.getOrigJudgeFirstName() != null){
			fName = order.getOrigJudgeFirstName().trim();
		}
		if (order.getOrigJudgeLastName() != null){
			lName = order.getOrigJudgeLastName().trim();
		}
		Name name = new Name(fName, PDConstants.BLANK, lName);
		sodre.setOrigJudgeName(name);
		sodre.setOrderSignedDate(order.getOrderSignedDate());;
		name = new Name(order.getOrderPresentorFirstName(), PDConstants.BLANK, order.getOrderPresentorLastName());
		sodre.setOrderPresentorName(name);
		name = new Name(order.getSignedByFirstName(), PDConstants.BLANK, order.getSignedByLastName());
		sodre.setSignedByName(name);
		sodre.setSignedByDefendant(order.isOrderSignedByDefendant());
		sodre.setSignedByDefendantDate(order.getOrderSignedByDefendantDate());
		sodre.setSignedByJudgeDate(order.getOrderSignedByJudgeDate());
		sodre.setComments(order.getComments());
// end defect adds			
		Collection conditionResponses = SupervisionOrderHelper.getOrderVersionConditionDetails(order);
		sodre.setConditions(conditionResponses);
		sodre.setSpecialCourtCd(order.getSpecialCourtCd());
		if ("J".equals(order.getSignedByTypeId()) ) {
			sodre.setWhoSignedOrder("Judge");
		}	
		if ("O".equals(order.getSignedByTypeId()) ) {
			sodre.setWhoSignedOrder("Other");
		}	
		if (sodre.isSignedByDefendant() == true ) {
			sodre.setDefendantSignature("Signed");
		} 
		if (sodre.isSignedByDefendant() == false ) {
			sodre.setDefendantSignature("Not Signed");
		} 
		return sodre;
	}

	/**
	 * @param origNameString
	 * @return
	 */
	public static Name parseName(String origNameString) {
		Name parsedName = new Name();
		String aName = null;
		int pos = origNameString.indexOf(",");
		if (pos > 0) {
			aName = origNameString.substring(0, pos);
			parsedName.setLastName(aName);
		}
		String nameString = null;
		if (pos + 2 <= origNameString.length()) {
			nameString = origNameString.substring(pos + 2);
			pos = nameString.indexOf(" ");
			aName = nameString.substring(0, pos);
			parsedName.setFirstName(aName);
		}
		if (pos + 2 <= nameString.length()) {
			aName = nameString.substring(pos + 1);
			parsedName.setMiddleName(aName);
		}
		return parsedName;

	}

	public static void populateSupOrderConditionRelValueResponse(
			SupervisionOrderConditionRelValue orderCondRelValue,
			SupOrderConditionRelValueResponseEvent orderCondRelValEvent) {

		orderCondRelValEvent.setFixed(orderCondRelValue.getIsFixed());

		// get VariableElementType to get name
		VariableElementType veType = orderCondRelValue.getVariableElementType();
		orderCondRelValEvent.setName(veType.getName());
		orderCondRelValEvent.setReference(veType.getIsReference());
		orderCondRelValEvent.setVariableElementTypeId(veType.getOID().toString());
		orderCondRelValEvent.setValueType(veType.getType());
		orderCondRelValEvent.setEnumerationTypeId(veType.getEnumerationTypeId());

		if (veType.getIsCalculated()){ 
			orderCondRelValEvent.setEnumerated(false);
			orderCondRelValEvent.setValue(orderCondRelValue.getValue());
			orderCondRelValEvent.setValueType(veType.getType());
			orderCondRelValEvent.setEnumerationTypeId(veType.getDataType());
		} else if (veType.isEnumeration()) {
				orderCondRelValEvent.setEnumerated(true);
				orderCondRelValEvent.setCodeTableName(veType.getElementCodeTableId());
				// if it is of type enumeration, its the code that we store
				String valueId = orderCondRelValue.getValue();
				orderCondRelValEvent.setValueId(valueId);
				if (valueId != null && !valueId.equals("")) {
					Code valueCode = Code.find(orderCondRelValEvent.getCodeTableName(), valueId);
					if (valueCode != null) {
						orderCondRelValEvent.setValue(valueCode.getDescription());
					}
				}  else {
					orderCondRelValEvent.setValue(orderCondRelValue.getValue());
				}
			} else {
				orderCondRelValEvent.setValue(orderCondRelValue.getValue());
			}
		}

	/**
	 * @param condition
	 * @param courtId
	 */
	public static void postConditionDetailRespEvent(SupervisionOrder order, Condition condition,
			String courtId, Map variableElementRefererenceMap) {
		ConditionDetailResponseEvent reply = SuggestedOrderHelper
				.getConditionDetailResponseEvent(order, condition, courtId,variableElementRefererenceMap);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(reply);
	}

	/**
	 * @param supervisionOrder
	 */
	public static void postOrderRespEvent(SupervisionOrder supervisionOrder) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		CaseOrderResponseEvent respEvent = getCaseOrderResponseEvent(supervisionOrder);
		if (respEvent != null) {
			//get like conditions
			respEvent.setLikeConditionIds(ImpactedOrderHelper.getLikeConditions(supervisionOrder));
			// post reply
			dispatch.postEvent(respEvent);
		}
	}

    /**
     *  @param defendantId
     */
    public static void retrieveAllSupPeriods(String defendantId)
    {
         GetLatestSupervisionPeriodEvent getSupervisionPeriodEvent = new GetLatestSupervisionPeriodEvent();
         getSupervisionPeriodEvent.setDefendantId(padSpn(defendantId));
         Iterator sup = SupervisionSplitOrder.findAll(getSupervisionPeriodEvent);
        
         if(!sup.hasNext())
         {
         	returnNoSupOrdersError(defendantId);
            return;
         }
         
         SpnSplitOrderPeriodResponseEvent noSupPeriodResponeEvent = null;
         HashMap supPeriodMap = new HashMap();
         while(sup.hasNext())
         {
            SpnSplitOrderPeriodResponseEvent supPeriodResponseEvent = null;
            SupervisionSplitOrder supPeriod = (SupervisionSplitOrder)sup.next();
            String supPeriodId = supPeriod.getSupPeriodId();
            
            /**
             * If there is Sup Period begin date then
             * check if the begin dates are the same so that all the cases with
             * the same begin date can be put into a list.
             * Different Sup Period begin dates will have a new response event created
             * and cases related to that period will be added to it.
             * */
            if(!"".equals(supPeriodId))
            {
                
                supPeriodResponseEvent = (SpnSplitOrderPeriodResponseEvent)supPeriodMap.get(supPeriodId);
                
                if(supPeriodResponseEvent == null)
                {
                    supPeriodResponseEvent = new SpnSplitOrderPeriodResponseEvent();
                    supPeriodMap.put(supPeriodId, supPeriodResponseEvent);
                    prepareSupPeriodEvent(supPeriodResponseEvent, supPeriod);                    
                }
                supPeriodResponseEvent.addToSpnSplitOrderDetailsList(prepareSupOrderDetailsEvent(supPeriod));
            }
            //If there is no Sup Period begin date then add the cases to noSupPeriod response            
            else
            {
                if(noSupPeriodResponeEvent==null)
                {
                	noSupPeriodResponeEvent = new SpnSplitOrderPeriodResponseEvent();
                }
                //prepareSupPeriodEvent(noSupPeriodResponeEvent, supPeriod);
                noSupPeriodResponeEvent.addToSpnSplitOrderDetailsList(prepareSupOrderDetailsEvent(supPeriod));
                
            }
            
         }
         
         Iterator iter = supPeriodMap.values().iterator();
         IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
         if(noSupPeriodResponeEvent != null)
         {
         	dispatch.postEvent(noSupPeriodResponeEvent);
         }
         while(iter.hasNext())
         {
             dispatch.postEvent((SpnSplitOrderPeriodResponseEvent)iter.next());
         }
         
         
    }

    private static String padSpn(String spn){
        if (spn != null && spn.length() < 8) {
            StringBuffer sb = new StringBuffer(spn);
            for (int i = 0; i < 8 - spn.length(); i++) {
                sb.insert(0, ZERO);
            }
            spn = sb.toString();
        }
        return spn;
    }
    
    /**
     * @param defendantId
     */
    private static void returnNoSupOrdersError(String defendantId)
    {
           IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REPLY);
           SpnSplitErrorResponseEvent spnError =  new SpnSplitErrorResponseEvent();
           spnError.setErroneousSpn(defendantId);
           spnError.setMsg("No Supervision Order Periods found for the given spn");
           dispatch1.postEvent(spnError);
           return;
    }

    /**
     * @param spnSplitPeriodEvent
     * @param supOrderPeriod
     */
    private static void prepareSupPeriodEvent(SpnSplitOrderPeriodResponseEvent spnSplitPeriodEvent, SupervisionSplitOrder supOrderPeriod)
    {
        spnSplitPeriodEvent.setSupPeriodId(supOrderPeriod.getSupPeriodId());
       	spnSplitPeriodEvent.setSupPeriodEndDate(supOrderPeriod.getSupPeriodEndDate());
        spnSplitPeriodEvent.setSupPeriodBeginDate(supOrderPeriod.getSupPeriodBeginDate());
    }
            
    /**
     * @param supOrderPeriod
     * @return
     */
    private static SpnSplitOrderDetailsResponseEvent prepareSupOrderDetailsEvent(SupervisionSplitOrder supOrderPeriod)
    {
        SpnSplitOrderDetailsResponseEvent spnSplitOrderDetails = new SpnSplitOrderDetailsResponseEvent();
        spnSplitOrderDetails.setAgencyId(supOrderPeriod.getAgencyId());
        
        String cdi = supOrderPeriod.getCriminalCaseId().substring(ZERO,THREE);
        String caseNum = supOrderPeriod.getCriminalCaseId().substring(THREE); 
        spnSplitOrderDetails.setSupOrderId((String)supOrderPeriod.getOID());
        spnSplitOrderDetails.setCaseNumber(caseNum);
        spnSplitOrderDetails.setCdi(cdi);
        
        spnSplitOrderDetails.setCourtId(supOrderPeriod.getCourtId());
        spnSplitOrderDetails.setOrderFiledDate(supOrderPeriod.getOrderFiledDate());
        spnSplitOrderDetails.setOrderStatus(supOrderPeriod.getOrderStatusCd());
        spnSplitOrderDetails.setVersionType(supOrderPeriod.getVersionTypeCd());
        spnSplitOrderDetails.setVersionNumber(supOrderPeriod.getVersionNum());
        
        Date caseEndDate = supOrderPeriod.getCaseEndDate();
        Date caseBeginDate = supOrderPeriod.getCaseBeginDate();
       
        spnSplitOrderDetails.setSupOrderEndDate(caseEndDate);
        spnSplitOrderDetails.setSupOrderBeginDate(caseBeginDate);
        
        return spnSplitOrderDetails;
    }
    
	/**
	 * @param agencyId
	 * @param criminalCaseId
	 * @return
	 */
	public static SupervisionPeriodResponseEvent getCaseSupervisionPeriod(
			String agencyId, String criminalCaseId) {

		GetCaseSupervisionPeriodEvent getOrdersEvent = new GetCaseSupervisionPeriodEvent();
		getOrdersEvent.setAgencyId(agencyId);
		getOrdersEvent.setCriminalCaseId(criminalCaseId);
		// Search for active and inactive orders
		Iterator iter = SupervisionOrder.findAll(getOrdersEvent);
		Collection coll = new ArrayList();
		SupervisionOrder so = null;
		if (iter != null && iter.hasNext()) {
			while (iter.hasNext()) {
				so = (SupervisionOrder) iter.next();
				coll.add(so);
			}
		}
		SupervisionPeriodResponseEvent spre = null;
		if (coll.size() > 0) {
			Collections.sort((List) coll);
			spre = new SupervisionPeriodResponseEvent();
			List list = (List) coll;
			if (coll.size() > 0) {
				//set supervision end date to inactivation date of most recent order.
				so = (SupervisionOrder) list.get(0);
				spre.setSupervisionEndDate(so.getInactivationDate());
				GetPeriodForSupervisionOrderEvent getSupPeriodEvent = new GetPeriodForSupervisionOrderEvent();
				getSupPeriodEvent.setSupervisionOrderId(so.getOID().toString());
				Iterator iter2 = SupervisionPeriod.findAll(getSupPeriodEvent);
				if (iter2 != null && iter2.hasNext()) {
					SupervisionPeriod supervisionPeriod = (SupervisionPeriod) iter2.next();
//					spre.setSupervisionEndDate(supervisionPeriod.getSupervisionEndDate());
					spre.setSupervisionPeriodId(supervisionPeriod.getOID().toString());
				}
				//set supervision begin date to activation date of oldest order.
				so = (SupervisionOrder) list.get(list.size() - 1);
				spre.setSupervisionBeginDate(so.getActivationDate());
			}
		}

		return spre;
	}

	static public void setCaseActivityRuleStatus(SupervisionOrder order,
			CaseOrderResponseEvent responseEvent, boolean hasMulti) {
		if (responseEvent == null || responseEvent.getOrderStatusId() == null
				|| responseEvent.getOrderStatusId().equalsIgnoreCase(""))
			return;

		String orderStatusId = responseEvent.getOrderStatusId();
		String caseActivityRuleStatus = PDCommonSupervisionConstants.BTN_GRP_STATUS_PASO_CASEFILE_NO_ORDERS;
		if (order.getAgencyId().equals(UIConstants.PTR) && PDCodeTableConstants.PTS.equals(responseEvent.getCdi())) {
			responseEvent.setOutOfCountyCase(true);
		}
		else if (order.getAgencyId().equals(UIConstants.CSC) && PDCodeTableConstants.CSCD.equals(responseEvent.getCdi())) {
			responseEvent.setOutOfCountyCase(true);
		}
		if (orderStatusId.equalsIgnoreCase(PDCodeTableConstants.STATUS_ACTIVE_ID)) {
			if (hasMulti)
				caseActivityRuleStatus = PDCommonSupervisionConstants.BTN_GRP_STATUS_PASO_CASEFILE_MULTI_ACTIVE;
			else
				caseActivityRuleStatus = PDCommonSupervisionConstants.BTN_GRP_STATUS_PASO_CASEFILE_SINGLE_ACTIVE;
		} else if (orderStatusId
				.equalsIgnoreCase(PDCodeTableConstants.STATUS_INACTIVE_ID)) {
			if (hasMulti) {
				caseActivityRuleStatus = PDCommonSupervisionConstants.BTN_GRP_STATUS_PASO_CASEFILE_MULTI_INACTIVE;
			} else {
				boolean reinstateAllowed = false;
				// agency
				if (order.getAgencyId().equals(UIConstants.JUV)) {
					reinstateAllowed = false;
				} else {
					// get SupervisionPeriod from Order
					SupervisionPeriod prevPeriod = order.getSupervisionPeriod();
					if (prevPeriod!=null && prevPeriod.getSupervisionEndDate() != null) {
						// if the currently associated SupervisionPeriod has an
						// end date, there is a new active SupervisionPeriod
						// find the active SupervisionPeriod
						SupervisionPeriod currPeriod =
							SupervisionPeriod.findActiveSupervisionPeriod(order.getDefendantId(), order.getAgencyId());
						// check to see if its previous is the
						// SupervisionOrder's SupervisionPeriod
						if (currPeriod == null
								|| (currPeriod.getPreviousSupervisionPeriodId() != null 
										&& currPeriod.getPreviousSupervisionPeriodId()
										.equals(prevPeriod.getOID().toString()))) {
							reinstateAllowed = true;
						} else if (currPeriod != null && isOutOfCountyCase(order.getCriminalCaseId()) ){
							reinstateAllowed = true;//Make skip supervision periods.
						}
					} else {
						reinstateAllowed = true;
					}
				}
				if (reinstateAllowed) {
					caseActivityRuleStatus = PDCommonSupervisionConstants.BTN_GRP_STATUS_PASO_CASEFILE_SINGLE_INACTIVE;
				} else {
					caseActivityRuleStatus = PDCommonSupervisionConstants.BTN_GRP_STATUS_PASO_CASEFILE_SINGLE_INACTIVE_NOREINSTATE;
				}
			}
		} else if (orderStatusId
				.equalsIgnoreCase(PDCodeTableConstants.STATUS_INCOMPLETE_ID)) {
			caseActivityRuleStatus = PDCommonSupervisionConstants.BTN_GRP_STATUS_PASO_CASEFILE_SINGLE_INCOMPLETE;
		} else if (orderStatusId
				.equalsIgnoreCase(PDCodeTableConstants.STATUS_DRAFT_ID)) {
			caseActivityRuleStatus = PDCommonSupervisionConstants.BTN_GRP_STATUS_PASO_CASEFILE_SINGLE_DRAFT;
		} else if (orderStatusId
				.equalsIgnoreCase(PDCodeTableConstants.STATUS_PENDING_ID)) {
			caseActivityRuleStatus = PDCommonSupervisionConstants.BTN_GRP_STATUS_PASO_CASEFILE_SINGLE_PENDING;
		}
		responseEvent.setCaseActivityRuleStatus(PDCommonSupervisionConstants.BTN_GRP_NAME_PASO_CASEFILE
						+ PDCommonSupervisionConstants.BTN_GRP_NAME_SEPARATOR
						+ caseActivityRuleStatus);
	}
	/**
	 * @param defendantId
	 * @return
	 */
	public static List getActiveSupervisionOrders(String defendantId){
	    return SupervisionOrderHelper.getActiveSupervisionOrders(defendantId, null);
	}
	/**
	 * @param defendantId
	 * @param agencyId
	 * @return
	 */
	public static List getActiveSupervisionOrders(String defendantId, String agencyId){
	    
	    if (agencyId == null || agencyId.equals(PDConstants.BLANK)){
	        agencyId = PDSecurityHelper.getUserAgencyId();
	    } 
	    
	    GetActiveSupervisionOrdersEvent reqEvent = new GetActiveSupervisionOrdersEvent();
	    reqEvent.setAgencyId(agencyId);
	    reqEvent.setDefendantId(padSpn(defendantId));
	    Iterator iter = SupervisionOrder.findAll(reqEvent);
	    
	    return CollectionUtil.iteratorToList(iter);
	}
	/**
	 * @param defendantId
	 * @return
	 */
	public static SupervisionOrder getActiveOrder(String defendantId){
	 	List activeCases = getActiveSupervisionOrders(defendantId);
	 	SupervisionOrder activeCase = null;
	 	if (activeCases != null && activeCases.size() > 0){
	   		activeCase = (SupervisionOrder) activeCases.get(0);
	   	}
	   	return activeCase;
	 }
	/**
	 * @param defendantId
	 * @return
	 */
	public static String getActiveCaseId(String defendantId){
	   	SupervisionOrder activeCase = getActiveOrder(defendantId);
	   	String caseId = null;
	   	if (activeCase != null){
	   		caseId = activeCase.getCriminalCaseId();
	   	}
	   	return caseId;
	}

	/**
	 *  
	 */
	private SupervisionOrderHelper() {
		super();
	}
	public static String calculateDate(Date aDate, String aString){
		int intValue = new Integer(aString).intValue();
		Calendar cal = Calendar.getInstance();
		cal.setTime(aDate);
		cal.add(Calendar.MONTH, intValue);
		String stringDate = DateUtil.dateToString(cal.getTime(), DateUtil.DATE_FMT_1);
		return stringDate;
	}
	
	/**
	 * 
	 * @param closeCaseAssignment
	 */
	public static String inactivateOrder(String defendantId, String criminalCaseId, Date terminationDate)
	{
		String supervisionOrderId = null;
		
		GetMostCurrentSupervisionOrderForCaseEvent event = new GetMostCurrentSupervisionOrderForCaseEvent();
		
//		find the ACTIVE order for a particular case of a defendant
		event.setAgencyId(PDSecurityHelper.getUserAgencyId());
		event.setSpn(defendantId);
		event.setOrderStatusId(PDCodeTableConstants.STATUS_ACTIVE_ID);
		event.setCaseId(criminalCaseId);
		
		Iterator iterator = SupervisionOrder.findAll(event);
		if(iterator != null && iterator.hasNext())
		{
			SupervisionOrder supervisionOrder = (SupervisionOrder)iterator.next();
			
//			Inactivate the order
			supervisionOrder.setOrderStatusId(PDCodeTableConstants.STATUS_INACTIVE_ID);
			supervisionOrder.setInactivationDate(terminationDate);
			supervisionOrder.setOrderFiledDate(terminationDate);
			
			supervisionOrderId = supervisionOrder.getOID();
		}
		
		return supervisionOrderId;
		
	}//end of inactivateOrder()
	
	
	
	/**
	 * 
	 * @param defendantId
	 * @return
	 */
	public static boolean isLastCase(String defendantId)
	{
		GetActiveSupervisionOrdersEvent supOrdersEvt = new GetActiveSupervisionOrdersEvent();
		supOrdersEvt.setAgencyId(PDSecurityHelper.getUserAgencyId());
		supOrdersEvt.setDefendantId(defendantId);
		
		Iterator iterator = SupervisionOrder.findAll(supOrdersEvt);
		if(iterator != null && iterator.hasNext())
		{
			return false;
		}
		return true;
	}//end of isLastCase()
	
	
	
	/**
	 * 
	 * @param defendantId
	 * @param terminationDate
	 */
	public static void endSupervisionPeriod(String defendantId)
	{
		String agencyId = PDSecurityHelper.getUserAgencyId();
		SupervisionPeriod supervisionPeriod = SupervisionPeriod.findActiveSupervisionPeriod(defendantId, agencyId);
		if(supervisionPeriod != null)
		{
			GetLatestInactiveSupervisionOrderEvent event = new GetLatestInactiveSupervisionOrderEvent();
			event.setSpn(defendantId);
			event.setAgencyId(agencyId);
			event.setSupervisionPeriodId(supervisionPeriod.getOID());
			Iterator iter = SupervisionOrder.findAll(event);
			if(iter != null && iter.hasNext())
			{
				SupervisionOrder supervisionOrder = (SupervisionOrder)iter.next();
				Date terminationDate = supervisionOrder.getInactivationDate();
				supervisionPeriod.setSupervisionEndDate(terminationDate);
			}
		}
	}//end of endSupervisionPeriod()
	
	public static void createSupervisionOrder(CreateSupervisionOrderEvent reqEvent){
		SupervisionOrder supervisionOrder = SupervisionOrder.create(reqEvent);
		if(supervisionOrder == null){
		    // error situation for the case when there is already a valid original order existing
		    OrderCreateErrorResponseEvent orderCreateErrorResponseEvent = new OrderCreateErrorResponseEvent();
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(orderCreateErrorResponseEvent);

		}
		else{
			// send CaseOrderResponseEvent 
			SupervisionOrderHelper.postOrderRespEvent(supervisionOrder);
		}
	
	}
	public static String getModifiedOrderTitleId(){
		String agencyId = PDSecurityHelper.getUserAgencyId();
		List aList = CollectionUtil.iteratorToList(SupervisionOrderPrintTemplate.findAll("versionType", "MODIFIED"));
		SupervisionOrderPrintTemplate aPrintTemplate = null;
		String orderTitleId = null;
		for (int i = 0; i < aList.size(); i++) {
			aPrintTemplate = (SupervisionOrderPrintTemplate) aList.get(i);
			if (aPrintTemplate.getAgencyID().equals(agencyId)){
				orderTitleId = aPrintTemplate.getOID();
				break;
			}
		}
		return orderTitleId;
	}
	 /**
	 * @param criminalCaseId
	 * @return
	 */
	public static boolean isOutOfCountyCase(String criminalCaseId){
	     String cdi = criminalCaseId.substring(0,3);
	     boolean isOutOfCountyCase = false;
	     if (cdi.equals("010")){
	         isOutOfCountyCase = true;
	     }
	     return isOutOfCountyCase;
	 }
	public static Date getOutOfCountyTransferInDate(String spn, String criminalCaseId) {
		GetOutOfCountyCaseEvent findEvent = new GetOutOfCountyCaseEvent();
		findEvent.setCaseNum(criminalCaseId.substring(3));
		findEvent.setCourtDivisionId(criminalCaseId.substring(0, 3));

		Iterator iter = new Home().findAll(findEvent, OutOfCountyProbationCase.class);
		List caseList = CollectionUtil.iteratorToList(iter);
		Date transferInDate = null;
		if (caseList != null && caseList.size() > 0){
			OutOfCountyProbationCase oocCase = (OutOfCountyProbationCase) caseList.get(0);
			transferInDate = oocCase.getTransferInDate();
		}

		if (transferInDate == null){
			//look for transfer date in CICS
			GetTransfersEvent getTransfersEvent = new GetTransfersEvent();
			getTransfersEvent.setSuperviseeId(spn);
			getTransfersEvent.setSearchType("H");
			iter = CSTransfer.findAll(getTransfersEvent);
			List transfers = CollectionUtil.iteratorToList(iter);
			int lowestSnu = 0;
			for (int i = 0; i < transfers.size(); i++) {
				CSTransfer csTransfer = (CSTransfer) transfers.get(i);
				if (csTransfer.getCaseNum().equals(findEvent.getCaseNum())){
					if (lowestSnu > 0){
						if (csTransfer.getSubclassificationSeqNumForTransferIn() != null
								&& !csTransfer.getSubclassificationSeqNumForTransferIn().trim().equals(PDConstants.BLANK)){
							int thisSnu = new Integer(csTransfer.getSubclassificationSeqNumForTransferIn().trim()).intValue();
							if ((csTransfer.getOutOfCountyTransferInDate() != null 
									&& !csTransfer.getOutOfCountyTransferInDate().trim().equals(PDConstants.BLANK))
									&& thisSnu < lowestSnu){
								lowestSnu = thisSnu;
								transferInDate = DateUtil.stringToDate(csTransfer.getOutOfCountyTransferInDate().trim(), "yyyyMMdd");
							}
						}
					} else if ((csTransfer.getSubclassificationSeqNumForTransferIn() != null
								&& !csTransfer.getSubclassificationSeqNumForTransferIn().trim().equals(PDConstants.BLANK))
							    && (csTransfer.getOutOfCountyTransferInDate() != null 
									|| !csTransfer.getOutOfCountyTransferInDate().trim().equals(PDConstants.BLANK))){
							lowestSnu = new Integer(csTransfer.getSubclassificationSeqNumForTransferIn().trim()).intValue();
							transferInDate = DateUtil.stringToDate(csTransfer.getOutOfCountyTransferInDate().trim(), "yyyyMMdd");
					}
				}
			}
		}
		return transferInDate;
	}
	public static String htmlNBSP = "&nbsp;";
	public static String createResolvedDescription(String description, List relValList){
		ConditionCourtVariableElement condCrtVarElement = null;
		String resolvedDesc = description;
		String aValue = null;
		Code aCode = null;
		for (int i = 0; i < relValList.size(); i++) {
			condCrtVarElement = (ConditionCourtVariableElement) relValList.get(i);
			aValue = condCrtVarElement.getValue();
			//09/09/2010-added check for !isCalculated()
			if (condCrtVarElement.getIsEnumeration()
					&& !condCrtVarElement.getIsCalculated()
					&& condCrtVarElement.getElementCodeTableId() != null 
					&& !condCrtVarElement.getElementCodeTableId().equals(PDConstants.BLANK)){
				aCode = Code.find(condCrtVarElement.getElementCodeTableId(), condCrtVarElement.getValue());
				if (aCode != null){
					aValue = aCode.getDescription();
				}
			}
			if (aValue == null){
				aValue = PDConstants.BLANK;
			}
			boolean isBlank = false;
			if (aValue.trim().equals(PDConstants.BLANK)) {
				isBlank = true;
				if (aValue.equals(PDConstants.BLANK) && condCrtVarElement.getName().equals(UIConstants.VARIABLE_ELEMENT_NAME_COMMENTS)){
				} else {
					aValue = getDefaultUnderlineSpaces(aValue, 10);
				}
			}
			if (aValue != null) {
				aValue = getVariableFormattedText(condCrtVarElement.getEnumerationTypeId(), condCrtVarElement.getEnumerationTypeId(), aValue, false);
				String token = null;
				if (condCrtVarElement.getIsReference()) { // reference variables
					// shown as [[varName]]
					if (!isBlank)
						token = "[" + condCrtVarElement.getName() + "]";
					else {
						token = "<u>[" + condCrtVarElement.getName() + "]</u>";
					}

				} else { // other variables shown as {{varName}}
					if (!isBlank)
						token = "{{" + condCrtVarElement.getName() + "}}";
					else {
						token = "<u>{{" + condCrtVarElement.getName() + "}}</u>";
					}
				}
				token = regExSpecCharEscapeFix(token);
				RE regex = null;
				try {
					regex = new RE(token, RE.REG_ICASE);
					//The dollar sign denotes an anchor in RegEx.
					if (aValue != null) {
						aValue = aValue.replace('$', '^');
						resolvedDesc = regex.substituteAll(resolvedDesc, aValue);

						RE regexSpaceBr = new RE(htmlNBSP,RE.REG_ICASE);
						resolvedDesc = regexSpaceBr.substituteAll(resolvedDesc,	" ");
						resolvedDesc = resolvedDesc.replace('^', '$');
						resolvedDesc = removeStarting_BR_P_XMLtags(resolvedDesc);
					}
				} catch (REException e) {
					e.printStackTrace();
				}
			}
		}
		return resolvedDesc;
	}
	public static String regExSpecCharEscapeFix(String aNonFixedRegexPattern) {
			String specialCharReplString = "\\\\"; // all four slashes are necessary
												   // for this to work correctly
			if (aNonFixedRegexPattern == null || aNonFixedRegexPattern.equals(""))
				return aNonFixedRegexPattern;
			String fixedValue = aNonFixedRegexPattern.replaceAll("\\(", "\\\\(");
			fixedValue = fixedValue.replaceAll("\\)", "\\\\)");
			fixedValue = fixedValue.replaceAll("\\[", "\\\\[");
			fixedValue = fixedValue.replaceAll("\\^", "\\\\^");
			fixedValue = fixedValue.replaceAll("\\$", "\\\\$");
			fixedValue = fixedValue.replaceAll("\\.", "\\\\.");
			fixedValue = fixedValue.replaceAll("\\|", "\\\\|");
			fixedValue = fixedValue.replaceAll("\\?", "\\\\?");
			fixedValue = fixedValue.replaceAll("\\*", "\\\\*");
			fixedValue = fixedValue.replaceAll("\\+", "\\\\+");
			return fixedValue;
		}
	private static String xmlPrefix = "<";
	private static String xmlSuffix = ">";

	public static String removeStarting_BR_P_XMLtags(String aStringWithXMLtags) {

		String result = "";
		if (aStringWithXMLtags == null || aStringWithXMLtags.equals("")){
			return "";
		}	
		StringBuffer myBuffer = new StringBuffer(aStringWithXMLtags);
		int endPos = -1;
		String XML_BR_OPENTAG = "<br>";
		String XML_BR_CLOSETAG = "</br>";
		String XML_BR_OPENCLOSETAG = "<br/>";
		String XML_P_OPENTAG = "<p>";
		String XML_P_CLOSETAG = "</p>";
		String XML_P_OPENCLOSETAG = "<p/>";
		String XML_P_OPENTAGWITHATTRIBS = "<p";
		boolean found = true;
		while (found) {
			found = false;
			if (myBuffer.indexOf(xmlPrefix) != -1) {
				int startPos = myBuffer.indexOf(XML_BR_OPENTAG);
				if (startPos == 0) {
					int posOcc = countPosOccurence(myBuffer, XML_BR_OPENTAG, XML_BR_CLOSETAG);
					endPos = startPos + XML_BR_OPENTAG.length();
					myBuffer.delete(startPos, endPos);
					myBuffer = removeCountOccurence(myBuffer, XML_BR_CLOSETAG, posOcc);
					found = true;
				}
				startPos = myBuffer.indexOf(XML_BR_OPENCLOSETAG);
				if (startPos == 0 && !found) {
					endPos = startPos + XML_BR_OPENCLOSETAG.length();
					myBuffer.delete(startPos, endPos);
					if (!found)
						found = true;
				}
				// P TAG evaluation
				startPos = myBuffer.indexOf(XML_P_OPENTAG);
				if (startPos == 0 && !found) {
					int posOcc = countPosOccurence(myBuffer, XML_P_OPENTAG,	XML_P_CLOSETAG);
					endPos = startPos + XML_P_OPENTAG.length();
					myBuffer.delete(startPos, endPos);
					myBuffer = removeCountOccurence(myBuffer, XML_P_CLOSETAG, posOcc);
					if (!found){
						found = true;
					}	
				}
				startPos = myBuffer.indexOf(XML_P_OPENCLOSETAG);
				if (startPos == 0 && !found) {
					endPos = startPos + XML_P_OPENCLOSETAG.length();
					myBuffer.delete(startPos, endPos);
					if (!found)
						found = true;
					;
				}
				startPos = myBuffer.indexOf(XML_P_OPENTAGWITHATTRIBS);
				if (startPos == 0 && !found) {
					endPos = myBuffer.indexOf(xmlSuffix);
					endPos++; // deleting the entire p start tag
					int posOcc = countPosOccurence(myBuffer, XML_P_OPENTAGWITHATTRIBS, XML_P_CLOSETAG);
					myBuffer.delete(startPos, endPos);
					myBuffer = removeCountOccurence(myBuffer, XML_P_CLOSETAG, posOcc);
					if (!found){
						found = true;
					}	
				}

			}
		}
		return fixBRtags(myBuffer.toString());
	}
	private static int countPosOccurence(StringBuffer aIncomingBuffer,
			String aOpenSeq, String aClosingSeq) {
		int count = 0;
		int openCount = 0;
		int closeCount = 0;
		int pos = 0;
		if (aIncomingBuffer == null || aOpenSeq == null || aOpenSeq.equals("")) {
			return count;
		}
		StringBuffer aStrBuffer = new StringBuffer(aIncomingBuffer.toString());
		while (aStrBuffer.indexOf(aOpenSeq, pos) != -1) {
			count++;
			openCount++;
			pos = aStrBuffer.indexOf(aOpenSeq, pos);
			int oldPos = pos;
			pos = pos + aOpenSeq.length();
			aStrBuffer.delete(oldPos, pos);
			pos = 0;
			if (aStrBuffer.indexOf(aOpenSeq, pos) != -1
					&& aStrBuffer.indexOf(aClosingSeq, pos) < aStrBuffer
							.indexOf(aOpenSeq, pos)) {
				closeCount++;
			} else if (aStrBuffer.indexOf(aOpenSeq, pos) == -1) {
				closeCount++;
				break;
			} else {

			}
			if (openCount == closeCount)
				return count;
		}
		return count;
	}
	private static StringBuffer removeCountOccurence(
			StringBuffer aIncomingBuffer, String aSeq, int aDeleteCount) {
		int count = 0;
		int startPos = 0;
		int endPos = 0;
		if (aIncomingBuffer == null || aSeq == null || aSeq.equals("") || aDeleteCount < 0) {
			return aIncomingBuffer;
		}
		StringBuffer aStrBuffer = new StringBuffer(aIncomingBuffer.toString());
		while (aStrBuffer.indexOf(aSeq) != -1 && count < aDeleteCount) {
			count++;
			startPos = aStrBuffer.indexOf(aSeq);
			endPos = startPos + aSeq.length();
			if (count == 1) {
				aIncomingBuffer = new StringBuffer();
			}
			if (count == aDeleteCount) {
				aStrBuffer.delete(startPos, endPos);
				break;
			}

			aIncomingBuffer.append(aStrBuffer.substring(0, endPos));
			aStrBuffer.delete(0, endPos);
			startPos = 0;
			endPos = 0;
		}
		if (count > 0) {
			aIncomingBuffer.append(aStrBuffer.substring(0, aStrBuffer.length()));
		}
		return aIncomingBuffer;
	}
	public static String fixBRtags(String aStringToFix) {
		try {
			String tempStr = aStringToFix;
			RE regexSpaceBr = new RE("<br>", RE.REG_ICASE);
			tempStr = regexSpaceBr.substituteAll(tempStr, "<br/>");
			regexSpaceBr = new RE("<br >", RE.REG_ICASE);
			tempStr = regexSpaceBr.substituteAll(tempStr, "<br/>");
			regexSpaceBr = new RE("</br>", RE.REG_ICASE);
			tempStr = regexSpaceBr.substituteAll(tempStr, "");
			return tempStr;
		} catch (REException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return aStringToFix;
		}
	}
	public static String getDefaultUnderlineSpaces(String aValueText, int aNumOfSpaces){
		if (aValueText==null || aValueText.trim().equals("")){
			StringBuffer strBuf=new StringBuffer();
			int x=0;
			while(x<aNumOfSpaces && aNumOfSpaces >0){
				strBuf.append("_");
				x++;
			}
			return strBuf.toString();
		}
		else
			return aValueText;
	}
	public static String VARIABLE_TEXT_PRE="PRE";
	public static String VARIABLE_TEXT_ACTUAL="ACTUAL";
	public static String VARIABLE_TEXT_POST="POST";
	public static final String emptyString="";
	
	public static String getVariableFormattedText(String aElemTypeCategory,String aElemTypeDetailGroup, String aValueText,boolean aIsForEditing){
		return (getVariableText(aElemTypeCategory,aElemTypeDetailGroup, aValueText, VARIABLE_TEXT_PRE,false)+ 
				getVariableText(aElemTypeCategory,aElemTypeDetailGroup, aValueText, VARIABLE_TEXT_ACTUAL,aIsForEditing) + 
				getVariableText(aElemTypeCategory,aElemTypeDetailGroup, aValueText, VARIABLE_TEXT_POST,false));
	}
	private static String getVariableText(String aElemTypeCategory,String aElemTypeDetailGroup, String aValueText, String aTextLocation, boolean aIsForEditing){
		if(aIsForEditing) { // if the variable is for editing we don't want to do any formatting to it.
			if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_DATE)){
				// FIX the case where the month may be off by one.
				if(aValueText!=null){
					int myIndex=aValueText.indexOf('/');
					if(myIndex==1){
						aValueText="0" + aValueText;
					}
					
				}
			}
			return aValueText;
		}
		
		if(aElemTypeCategory==null || aElemTypeCategory.equals("") || aElemTypeDetailGroup==null || aElemTypeDetailGroup.equals("")){
			if(aTextLocation.equals(VARIABLE_TEXT_PRE) || aTextLocation.equals(VARIABLE_TEXT_POST))
				return "";
			else
				return aValueText; 
		}
		try{
			if(aTextLocation.equals(VARIABLE_TEXT_PRE)){
				if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_CURRENCY)){
					return currencyUSSymbol;
				}
				else{
					return emptyString;
				}
			}
			else if(aTextLocation.equals(VARIABLE_TEXT_POST)){
				return emptyString;
			}
			else{
				if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_CURRENCY)){
					if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_CURRENCY_DEFAULT))
						return formatCurrency(aValueText,UIConstants.CURRENCY_US_DEFAULT_POSITIVE_FORMAT,false,"");
					else if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_CURRENCY_COMMAS))
						return formatCurrency(aValueText,UIConstants.CURRENCY_US_DEFAULT_POSITIVE_FORMAT,false,"");
					else if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_CURRENCY_NOCOMMAS))
						return formatCurrency(aValueText,UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT,false,"");
				}
				else if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_DATE)){ 
					if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_DATE_DAY_N_SUFFIX))
						return getDayOfMonthWithSuffix(DateUtil.stringToDate(aValueText,UIConstants.DATE_FMT_1));
					else if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_DATE_DAY_ONLY)){
						return getDayOfMonth(DateUtil.stringToDate(aValueText,UIConstants.DATE_FMT_1));}
					else if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_DATE_DEFAULT)){
							Date myDate=DateUtil.stringToDate(aValueText,UIConstants.DATE_FMT_1);
						if(myDate!=null)
							return DateUtil.dateToString(myDate,UIConstants.DATE_FMT_1);
						else
							return aValueText;
					}
					else if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_DATE_FORMAL))
						return getFormalDateWithSuffix(DateUtil.stringToDate(aValueText,UIConstants.DATE_FMT_1));
					else if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_DATE_FULLMONTHNAME))
						return getMonthLiteral(DateUtil.stringToDate(aValueText,UIConstants.DATE_FMT_1));
					else if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_DATE_REVERSE_FORMAL))
						return getFormalReversedDate(DateUtil.stringToDate(aValueText,UIConstants.DATE_FMT_1));
					else if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_DATE_YEAR_ONLY))
						return getYear(DateUtil.stringToDate(aValueText,UIConstants.DATE_FMT_1));
				}
				else if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_NUMERIC)){
					if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_NUMERIC_COMMAS))
						return formatNumber(aValueText,UIConstants.NUMBER_DEFAULT_POSITIVE_FORMAT,false,"");
					else if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_NUMERIC_DECIMAL_DEFAULT))
						return formatNumber(aValueText,UIConstants.DECIMAL_DEFAULT_DECIMAL_FORMAT,false,"");
					else if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_NUMERIC_NOCOMMAS))
						return formatNumber(aValueText,UIConstants.NUMBER_NOCOMMAS_POSITIVE_FORMAT,false,"");
				}
				return aValueText;
			}
		}
		catch(Exception e){
			if(aTextLocation.equals(VARIABLE_TEXT_PRE) || aTextLocation.equals(VARIABLE_TEXT_POST))
				return "";
			else
				return aValueText;
				//return "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		}
	}
	public static String getFormalReversedDate(Date date) {
		if (date == null){
			return "";
		}	
		String pattern = null;
		String dateThPart = getDayOfMonthWithSuffix(date);
		if (dateThPart == null){
			dateThPart = "";
		}	
		pattern = "' day of 'MMMM, yyyy";
		String finalDateStr = DateUtil.dateToString(date, pattern);
		if (finalDateStr == null){
			finalDateStr = "";
		}	
		return dateThPart + finalDateStr;

	}

	public static String formatNumber(String aValue, String aPatternMask,
			boolean blanksToZero, String spacesWhenBlank) {
		if (aValue == null || aValue.trim().equals("")) {
			if (blanksToZero)
				aValue = "0";
			else {
				if (spacesWhenBlank == null)
					spacesWhenBlank = "";
				return spacesWhenBlank;
			}

		}
		try {
			double myTempDouble = getDoubleFromString(aValue);
			Double myObjDouble = new Double(myTempDouble);
			return formatNumber(myObjDouble, aPatternMask);
		} catch (Exception exc) {
			return aValue;
		}
	}
	public static double getDoubleFromString(String aValue) {
		if (aValue == null || aValue.trim().equals(""))
			return 0.0;
		else {
			return Double.parseDouble(aValue);
		}
	}

	public static String formatNumber(Double aValue, String aPatternMask) {
		if (aPatternMask == null || aPatternMask.equals("")) {
			aPatternMask = UIConstants.NUMBER_DEFAULT_POSITIVE_FORMAT;
		}
		DecimalFormat myFormatter = new DecimalFormat(aPatternMask);
		String output = myFormatter.format(aValue);
		return output;

	}


	public static String getDayOfMonth(Date aDate) {
		String aDay = DateUtil.dateToString(aDate, D);
		Integer dayInteger = new Integer(aDay);
		int day = dayInteger.intValue();
		StringBuffer sbDay = new StringBuffer(dayInteger.toString());
		String dayString = dayInteger.toString();

		return dayString;
	}

	private static String ST = "st";
	private static String ND = "nd";
	private static String RD = "rd";
	private static String TH = "th";
	private static String D = "d";
	private static String MMMM = "MMMM";
	private static String YYYY = "yyyy";

	public static String getDayOfMonthWithSuffix(Date aDate) {
		String aDay = DateUtil.dateToString(aDate, D);
		Integer dayInteger = new Integer(aDay);
		int day = dayInteger.intValue();
		StringBuffer sbDay = new StringBuffer(dayInteger.toString());
		String dayString = dayInteger.toString();
		if (day > 10 && day < 20) {
			sbDay.append(TH);
		} else {
			if (dayString.length() > 1) {
				dayString = dayString.substring(1);
				dayInteger = new Integer(dayString);
				day = dayInteger.intValue();
			}
			switch (day) {
			case 1:
				sbDay.append(ST);
				break;
			case 2:
				sbDay.append(ND);
				break;
			case 3:
				sbDay.append(RD);
				break;
			case 21:
				sbDay.append(ST);
				break;
			case 31:
				sbDay.append(ST);
				break;

			default:
				sbDay.append(TH);
			}
		}
		return sbDay.toString();
	}
	public static String getYear(Date aDate) {
		String dateStr = DateUtil.dateToString(aDate, YYYY);
		return dateStr;
	}
	public static String getMonthLiteral(Date aDate) {
		String dateStr = DateUtil.dateToString(aDate, MMMM);
		return dateStr;
	}

	public static String formatCurrency(String aValue, String aCurrencyMask,
			boolean blanksToZero, String spacesWhenBlank) {
		if(aValue != null) {
			try {
				String aTemp = aValue.replace('$', ',');
				RE regexSpaceBr = new RE(",", RE.REG_ICASE);
				aValue = regexSpaceBr.substituteAll(aTemp, "");
			} catch (REException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (aValue == null || aValue.trim().equals("")) {
			if (blanksToZero)
				aValue = "0.00";
			else {
				if (spacesWhenBlank == null)
					spacesWhenBlank = "";
				return spacesWhenBlank;
			}
		}

		try {
			double myTempDouble = getDoubleFromString(aValue);
			Double myObjDouble = new Double(myTempDouble);
			return formatCurrency(myObjDouble, aCurrencyMask);
		} catch (Exception exc) {
			return aValue;
		}
	}
	public static String formatCurrency(Double aValue, String aCurrencyMask) {
		if (aCurrencyMask == null || aCurrencyMask.equals("")) {
			aCurrencyMask = UIConstants.CURRENCY_US_DEFAULT_POSITIVE_FORMAT;
		}
		DecimalFormat myFormatter = new DecimalFormat(aCurrencyMask);
		String output = myFormatter.format(aValue);
		return output;
	}

	public static String getFormalDateWithSuffix(Date date) {
		if (date == null)
			return "";
		String pattern = null;
		String dateThPart = getDayOfMonthWithSuffix(date);
		if (dateThPart == null){
			dateThPart = "";
		}	
		String strYear = getYear(date);
		if (strYear == null){
			strYear = "";
		}	
		String strFullMonth = getMonthLiteral(date);
		if (strFullMonth == null){
			strFullMonth = "";
		}	
		if (strFullMonth.equals("") || strYear.equals("") || dateThPart.equals("")) {
			return "";
		}
		String finalDateStr = strFullMonth + " " + dateThPart + ", " + strYear;
		if (finalDateStr == null){
			finalDateStr = "";
		}	
		return finalDateStr;
	}
}