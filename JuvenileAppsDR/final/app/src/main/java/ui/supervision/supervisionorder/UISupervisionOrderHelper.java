/*
 * Created on Jan 23, 2006 
 *
 */
package ui.supervision.supervisionorder;

import gnu.regexp.RE;
import gnu.regexp.REException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import messaging.managesupervisioncase.GetOutOfCountyCasesEvent;
import messaging.managesupervisioncase.reply.CaseListResponseEvent;
import messaging.managesupervisioncase.reply.OutOfCountyCaseTO;
import messaging.suggestedorder.reply.SuggestedOrderConditionResponseEvent;
import messaging.suggestedorder.reply.SuggestedOrderResponseEvent;
import messaging.supervision.reply.SupervisionStaffResponseEvent;
import messaging.supervisionoptions.GetSupervisionConditionDetailsEvent;
import messaging.supervisionoptions.reply.ConditionDetailResponseEvent;
import messaging.supervisionoptions.reply.ConditionResponseEvent;
import messaging.supervisionoptions.reply.VariableElementResponseEvent;
import messaging.supervisionorder.CreateSupervisionOrderEvent;
import messaging.supervisionorder.DeleteSupervisionOrderEvent;
import messaging.supervisionorder.GetSupervisionOrderDetailsEvent;
import messaging.supervisionorder.GetSupervisionOrderVariableElementReferencesEvent;
import messaging.supervisionorder.SupervisionOrderConditionEvent;
import messaging.supervisionorder.SupervisionOrderConditionRelValueEvent;
import messaging.supervisionorder.UpdateSupervisionOrderEvent;
import messaging.supervisionorder.UpdateSupervisionOrderStatusEvent;
import messaging.supervisionorder.reply.CaseOrderResponseEvent;
import messaging.supervisionorder.reply.JudgeResponseEvent;
import messaging.supervisionorder.reply.MagistrateResponseEvent;
import messaging.supervisionorder.reply.SupOrderConditionRelValueResponseEvent;
import messaging.supervisionorder.reply.SupOrderConditionResponseEvent;
import messaging.supervisionorder.reply.SupervisionOrderDetailResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.ResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.km.utilities.Name;
import naming.OutOfCountyCaseControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import naming.SupervisionOptionsControllerServiceNames;
import naming.SupervisionOrderControllerServiceNames;
import naming.UIConstants;
import ui.common.CodeHelper;
import ui.common.SimpleCodeTableHelper;
import ui.common.UIUtil;
import ui.security.SecurityUIHelper;
import ui.supervision.CommonUtilites;
import ui.supervision.SupervisionOptions.UISupervisionOptionHelper;
import ui.supervision.suggestedorder.form.SuggestedOrderForm;
import ui.supervision.supervisionorder.form.SupervisionOrderForm;
import ui.supervision.supervisionorder.form.SupervisionOrderSearchForm;

/**
 * @author asrvastava
 *  
 */
public final class UISupervisionOrderHelper {

	/**
	 * Builds map of response events setting key as topic.
	 * 
	 * @param responseEvents
	 * @return
	 */
	public static Map buildResponseEventMap(Collection responseEvents) {
		Map map = new HashMap();

		if (responseEvents != null) {
			for (Iterator iter = responseEvents.iterator(); iter.hasNext();) {
				ResponseEvent re = (ResponseEvent) iter.next();
				map.put(re.getTopic(), re);
			}
		}
		return map;
	}

	public static Collection convert(boolean isNewVersion, boolean isRefreshable,
			Collection supOrderConditions, Map referenceVariableMap) {
		Collection conditions = new ArrayList();
		for (Iterator it = supOrderConditions.iterator(); it.hasNext();) {
			SupOrderConditionResponseEvent orderCondRespEvent = (SupOrderConditionResponseEvent) it
					.next();
			//			ConditionDetailResponseEvent condDetailRespEvent = convert(
			//					orderCondRespEvent, referenceVariableMap);
			ConditionDetailResponseEvent condDetailRespEvent = convert(
					isNewVersion, isRefreshable, orderCondRespEvent, referenceVariableMap);
			conditions.add(condDetailRespEvent);
		}
		return conditions;
	}

	public static SupOrderConditionResponseEvent convert(
			ConditionDetailResponseEvent condDetailRespEvent,
			Map impOrderValueMap) {
		SupOrderConditionResponseEvent orderCondRespEvent = new SupOrderConditionResponseEvent();
		orderCondRespEvent.setConditionId(condDetailRespEvent.getConditionId());
		orderCondRespEvent.setResolvedDescription(condDetailRespEvent.getResolvedDescription());
		orderCondRespEvent.setName(condDetailRespEvent.getName());
		orderCondRespEvent.setLikeConditionInd(condDetailRespEvent.getLikeConditionInd());
		// VariableElementResponseEvents
		for (Iterator it = condDetailRespEvent.getVariableElements().iterator(); it.hasNext();) {
			VariableElementResponseEvent vere = (VariableElementResponseEvent) it.next();
			SupOrderConditionRelValueResponseEvent orderCondRelValEvent = new SupOrderConditionRelValueResponseEvent();
			orderCondRelValEvent.setName(vere.getName());
			orderCondRelValEvent.setVariableElementTypeId(vere.getVariableElementTypeId());
			// enumerated
			if (orderCondRelValEvent.isEnumerated()) {
				orderCondRelValEvent.setValueId(vere.getValueId());
			}
			orderCondRelValEvent.setValue(vere.getValue());

			SupOrderConditionRelValueResponseEvent impOrderValue = (SupOrderConditionRelValueResponseEvent) impOrderValueMap
					.get(condDetailRespEvent.getConditionId() + "^"
							+ orderCondRelValEvent.getVariableElementTypeId());
			if (impOrderValue != null && impOrderValue.isLikeConditionInd()) {
				orderCondRelValEvent.setLikeConditionInd(true);
			}
			orderCondRespEvent.addSupOrderConditionRelValue(orderCondRelValEvent);
		}
		return orderCondRespEvent;
	}

	
	public  static Map getAllCourtsMap(Set mySet){
		if(mySet==null)
			return null;
		else{
			HashMap myMap=new HashMap(mySet.size());
			Iterator iter = mySet.iterator();
			while ( iter.hasNext() )
			{
				String str = (String)iter.next();
				myMap.put(str,str);
			}
			return myMap;
		}
	}
	public static boolean isAtLeastOneCourtTheSame(Set courts1, Map courts2){
		return isAtLeastOneCourtTheSame(getAllCourtsMap(courts1),courts2);
	}
	
	public static boolean isAtLeastOneCourtTheSame(Map courts1, Set courts2){
		return isAtLeastOneCourtTheSame(courts1,getAllCourtsMap(courts2));
	}
	
	public static boolean isAtLeastOneCourtTheSame(Set courts1, Set courts2){
		return isAtLeastOneCourtTheSame(getAllCourtsMap(courts1),getAllCourtsMap(courts2));
	}
	
	public static boolean isAtLeastOneCourtTheSame(Map courts1, Map courts2){
		if(courts1==null || courts2==null)
			return false;
		else{
			Iterator court1Iter=courts1.keySet().iterator();
			while(court1Iter.hasNext()){
				String myCrt=(String)court1Iter.next();
				if(courts2.containsKey(myCrt))
					return true;
			}
		}
		return false;
	}
	public static boolean isAtLeastOneCourtTheSame(Set allCourts, String crtToCheck){
		if(crtToCheck!=null && allCourts!=null && getAllCourtsMap(allCourts).containsKey(crtToCheck))
			return true;
		return false;
	}
	
	public static boolean isAtLeastOneCourtTheSame(String crtToCheck, Set allCourts){
		if(crtToCheck!=null && allCourts!=null && getAllCourtsMap(allCourts).containsKey(crtToCheck))
			return true;
		return false;
	}
	
	public static boolean isAtLeastOneCourtTheSame(String crtToCheck, Map allCourts){
		if(crtToCheck!=null && allCourts!=null && allCourts.containsKey(crtToCheck))
			return true;
		return false;
	}
	
	public static boolean isAtLeastOneCourtTheSame(Map allCourts, String crtToCheck){
		if(crtToCheck!=null && allCourts!=null && allCourts.containsKey(crtToCheck))
			return true;
		return false;
	}
	
	private static ConditionDetailResponseEvent convert(boolean isNewVersion,
			boolean isRefreshable,
			SupOrderConditionResponseEvent orderCondRespEvent,
			Map referenceVariableMap) {
		ConditionDetailResponseEvent condDetailRespEvent = new ConditionDetailResponseEvent();
		condDetailRespEvent.setConditionId(orderCondRespEvent.getConditionId());
		condDetailRespEvent.setDescription(orderCondRespEvent.getDescription());
		condDetailRespEvent.setResolvedDescription(orderCondRespEvent.getResolvedDescription());
		condDetailRespEvent.setName(orderCondRespEvent.getName());
		condDetailRespEvent.setGroup1Name(orderCondRespEvent.getGroup1Name());
		condDetailRespEvent.setGroup2Name(orderCondRespEvent.getGroup2Name());
		condDetailRespEvent.setGroup3Name(orderCondRespEvent.getGroup3Name());
		condDetailRespEvent.setLikeConditionInd(orderCondRespEvent.isLikeConditionInd());
		condDetailRespEvent.setSpecialCondition(orderCondRespEvent.isSpecialCondition());
		condDetailRespEvent.setSequenceNum(orderCondRespEvent.getSequenceNum());
		condDetailRespEvent.setStandard(orderCondRespEvent.isStandard());
        condDetailRespEvent.setStatus(orderCondRespEvent.getStatus());
        condDetailRespEvent.setAllCourtIds(orderCondRespEvent.getAllCourtIds());
		String value = null;
		// VariableElementResponseEvents
		if(orderCondRespEvent.getSupOrderConditionRelValues()==null || orderCondRespEvent.getSupOrderConditionRelValues().size()<=0){
			condDetailRespEvent.setConditionEmpty(false);
		}
		for (Iterator it = orderCondRespEvent.getSupOrderConditionRelValues().iterator(); it.hasNext();) {
			SupOrderConditionRelValueResponseEvent orderCondRelValEvent = (SupOrderConditionRelValueResponseEvent) it.next();
			VariableElementResponseEvent vere = new VariableElementResponseEvent();
			vere.setFixed(orderCondRelValEvent.isFixed());
			vere.setName(orderCondRelValEvent.getName());
			vere.setReference(orderCondRelValEvent.isReference());
			vere.setVariableElementTypeId(orderCondRelValEvent.getVariableElementTypeId());
			// enumerated
			if(!orderCondRelValEvent.isReference()){
				if(orderCondRelValEvent.isEnumerated()){
					if((orderCondRelValEvent.getValueId()==null || orderCondRelValEvent.getValueId().trim().equals("")) && 
							(orderCondRelValEvent.getValue()==null || orderCondRelValEvent.getValue().trim().equals(""))){
						condDetailRespEvent.setConditionEmpty(true);
					} else {
					}
				}
				else{
					if(orderCondRelValEvent.getValue()==null || orderCondRelValEvent.getValue().trim().equals("")){
						if (!UIConstants.VARIABLE_ELEMENT_NAME_COMMENTS.equals(vere.getName())
								&& !UIConstants.VARIABLE_ELEMENT_NAME_CLAIMAINT_ADDRESS.equals(vere.getName())){
							condDetailRespEvent.setConditionEmpty(true);
						}
					}
				}
			}
			if (orderCondRelValEvent.isEnumerated()) {
				vere.setEnumeration(true);
				// if type is enumaration ; get codetable name
				vere.setCodeTableName(orderCondRelValEvent.getCodeTableName());
				vere.setEnumerationTypeId(orderCondRelValEvent.getEnumerationTypeId());
				vere.setValueId(orderCondRelValEvent.getValueId());
				if (orderCondRelValEvent.getValue() != null && !orderCondRelValEvent.getValue().equals(PDConstants.BLANK)){
					vere.setValue(orderCondRelValEvent.getValue());
				} else {
					String codeDesc = CodeHelper.getCodeDescription(orderCondRelValEvent.getCodeTableName(), orderCondRelValEvent.getValueId());
					vere.setValue(codeDesc);
				}
			} else if (orderCondRelValEvent.isReference()
					&& orderCondRelValEvent.isFixed()
					|| (orderCondRelValEvent.isReference() && isNewVersion)
					|| (orderCondRelValEvent.isReference() 
							&& !isNewVersion 
							&& orderCondRelValEvent.isFixed())) {
				//Refresh reference variables that have not been changed
				// (isFixed = true).
				//Refresh reference variables on create of new order version.
				vere.setEnumerationTypeId(orderCondRelValEvent.getEnumerationTypeId());
				value = (String) referenceVariableMap.get(vere.getName());
				vere.setValue(value);
			} else {
				vere.setEnumerationTypeId(orderCondRelValEvent.getEnumerationTypeId());
				vere.setValue(orderCondRelValEvent.getValue());
			}
			vere.setValueType(orderCondRelValEvent.getValueType());

			condDetailRespEvent.addVariableElement(vere);
		}
		condDetailRespEvent.setTopic(orderCondRespEvent.getConditionId());
		return condDetailRespEvent;
	}

	public static SupervisionOrderConditionEvent convertToSave(
			ConditionDetailResponseEvent cdre, Map referenceVariableMap) {
		SupervisionOrderConditionEvent conditionOrderEvent = new SupervisionOrderConditionEvent();
		conditionOrderEvent.setConditionId(cdre.getConditionId());
		conditionOrderEvent.setSequenceNum(cdre.getSequenceNum());
		conditionOrderEvent.setResolvedDescription(cdre.getResolvedDescription());
		if (cdre.isSpecialCondition()) {
			conditionOrderEvent.setSpecialCondition(true);
			conditionOrderEvent.setResolvedDescription(cdre.getResolvedDescription());
			return conditionOrderEvent;
		}
		if (cdre.getVariableElements() != null) {
			for (Iterator iter = cdre.getVariableElements().iterator(); iter.hasNext();) {
				VariableElementResponseEvent varElement = (VariableElementResponseEvent) iter.next();
				// create ConditionRelValue event
				SupervisionOrderConditionRelValueEvent orderCondRelValueEvent = new SupervisionOrderConditionRelValueEvent();
				orderCondRelValueEvent.setVariableElementTypeId(varElement.getVariableElementTypeId());
				orderCondRelValueEvent.setReference(varElement.isReference());
				String selectedValue = null;
				if (varElement.isFixed() && !varElement.isReference()) {
					orderCondRelValueEvent.setFixed(true);
					if (varElement.isEnumeration()) {
						selectedValue = varElement.getValueId();
					} else {
						selectedValue = varElement.getValue();
					}
				}
				else if (varElement.isEnumeration()) {
					selectedValue = varElement.getValueId();
				} else { // variable: value entered on previous page
					selectedValue = varElement.getValue();
					varElement.setValue(selectedValue);
//do I have to do something special for calculated values?
					if (varElement.isReference()) {
						if (varElement.isFixed()) {//isFixed = true by default
							String refreshedRefVar = (String) referenceVariableMap.get(varElement.getName());
							if (refreshedRefVar != null){
								refreshedRefVar.trim();
							} else {
								refreshedRefVar = "";
							}
							if (selectedValue != null){
								selectedValue.trim();
							} else {
								selectedValue = "";
							}
							if (!refreshedRefVar.equals(selectedValue)){
								selectedValue = refreshedRefVar;
							}
						}
					}
				}

				orderCondRelValueEvent.setValue(selectedValue);
				conditionOrderEvent.addOrderConditionRelValue(orderCondRelValueEvent);
			}
		}
		return conditionOrderEvent;
	}

	/**
	 * Adds conditionId as topic.
	 * 
	 * @para m conditionResponseEvents
	 * @return
	 */
/*	public static void createConditionObjects(Collection conditionResponseEvents) {
		if (conditionResponseEvents != null) {
			for (Iterator iter = conditionResponseEvents.iterator(); iter
					.hasNext();) {
				ConditionDetailResponseEvent socre = (ConditionDetailResponseEvent) iter
						.next();
				// get variable elements
			}
		}
	}*/

	public static Map createDetailDictionaryNameIdMapping(
			Collection varCollection) {
		Map detailDictionaryHashMap = new HashMap();
		if (varCollection != null) {
			Iterator detailDictionaryIter = varCollection.iterator();
			while (detailDictionaryIter.hasNext()) {
				VariableElementResponseEvent varElement = (VariableElementResponseEvent) detailDictionaryIter.next();

				detailDictionaryHashMap.put(varElement.getName(), varElement);
			}
		}
		return detailDictionaryHashMap;
	}

	public static SupervisionOrderConditionEvent createOrderConditionEvent(
			ConditionDetailResponseEvent condition, Map referenceVariableMap) {
		SupervisionOrderConditionEvent conditionOrderEvent = new SupervisionOrderConditionEvent();
		conditionOrderEvent.setConditionId(condition.getConditionId());
		conditionOrderEvent.setSequenceNum(condition.getSequenceNum());
		conditionOrderEvent.setConditionName(condition.getName());
		if (condition.isSpecialCondition()) {
			conditionOrderEvent.setSpecialCondition(true);
			conditionOrderEvent.setResolvedDescription(condition.getResolvedDescription());
			return conditionOrderEvent;
		}
		Map variableNameMap = createDetailDictionaryNameIdMapping(condition.getVariableElements());
		Set variableNameSet = variableNameMap.entrySet();
		String conditionDescription = condition.getDescription();
		if (variableNameSet.iterator().hasNext()) {
			for (Iterator i = variableNameSet.iterator(); i.hasNext();) {
				boolean isBlank = false;
				Map.Entry map = (Map.Entry) i.next();
				VariableElementResponseEvent varElement = (VariableElementResponseEvent) map.getValue();
				String varElementName = (String) map.getKey();

				if (varElement != null) {
					// create ConditionRelValue event
					SupervisionOrderConditionRelValueEvent orderCondRelValueEvent = new SupervisionOrderConditionRelValueEvent();
					orderCondRelValueEvent.setVariableElementTypeId(varElement.getVariableElementTypeId());
					orderCondRelValueEvent.setReference(varElement.isReference());
					String selectedValue = null;

					if (varElement.isFixed() && !varElement.isReference()) {
						selectedValue = varElement.getValue();
						orderCondRelValueEvent.setFixed(true);
						if (varElement.isEnumeration()) {
							selectedValue = CodeHelper.getCodeDescription(varElement.getCodeTableName(), varElement.getValueId());
							orderCondRelValueEvent.setValue(varElement.getValueId());
						} else {
							orderCondRelValueEvent.setValue(selectedValue);
						}
					} else if (varElement.isEnumeration() && !varElement.isCalculated()) {
						selectedValue = CodeHelper.getCodeDescription(varElement.getCodeTableName(), varElement.getValueId());
						orderCondRelValueEvent.setValue(varElement.getValueId());
					} else if (varElement.isCalculated()){
						//Was not being saved.
						//orderCondRelValueEvent.setVariableElementTypeId(varElement.getDataType());
						orderCondRelValueEvent.setValue(varElement.getValue());
						selectedValue = varElement.getValue();
					} else { // variable: value entered on previous page
						selectedValue = varElement.getValue();
						orderCondRelValueEvent.setValue(selectedValue);
						if (varElement.isReference()) {
							if (varElement.isFixed()) {
								String refreshedRefVar = (String) referenceVariableMap.get(varElement.getName());
								if (refreshedRefVar != null){
									refreshedRefVar.trim();
								} else {
									refreshedRefVar = "";
								}
								if (selectedValue != null){
									selectedValue.trim();
								} else {
									selectedValue = "";
								}
								if (!refreshedRefVar.equals(selectedValue)){
									varElement.setFixed(false);
									orderCondRelValueEvent.setFixed(false);
								} else {
									selectedValue = refreshedRefVar;
									orderCondRelValueEvent.setValue(selectedValue);
									orderCondRelValueEvent.setFixed(true);
								}
							} else {
								//selectedValue should be set to previous value;???
							}
						}
					}
					if (selectedValue == null) {
						selectedValue = "";
					}
					if (selectedValue.trim().equals("")) {
						isBlank = true;
						if (selectedValue.equals("") && varElement.getName().equals(UIConstants.VARIABLE_ELEMENT_NAME_COMMENTS)){
						} else {
							selectedValue = CommonUtilites
								.getDefaultUnderlineSpaces(selectedValue, 10);
						}
					}
					if (selectedValue != null) {
						selectedValue = CommonUtilites.getVariableFormattedText(varElement.getEnumerationTypeId(), varElement.getValueType(), selectedValue, false);
						String token = null;
						if (varElement.isReference()) { // reference variables
							// shown as [[varName]]
							if (!isBlank)
								token = "[" + varElementName + "]";
							else {
								token = "<u>[" + varElementName + "]</u>";
							}

						} else { // other variables shown as {{varName}}
							if (!isBlank)
								token = "{{" + varElementName + "}}";
							else {
								token = "<u>{{" + varElementName + "}}</u>";
							}
						}
						token = UIUtil.regExSpecCharEscapeFix(token);
						RE regex = null;
						try {
							regex = new RE(token, RE.REG_ICASE);
							//The dollar sign denotes an anchor in RegEx.
							if (selectedValue != null) {
								selectedValue = selectedValue.replace('$', '^');
								conditionDescription = regex.substituteAll(conditionDescription, selectedValue);

								RE regexSpaceBr = new RE(UIUtil.htmlNBSP,RE.REG_ICASE);
								conditionDescription = regexSpaceBr.substituteAll(conditionDescription,	" ");
								conditionDescription = conditionDescription.replace('^', '$');
								conditionDescription = UIUtil.removeStarting_BR_P_XMLtags(conditionDescription);
							}
						} catch (REException e) {
							e.printStackTrace();
						}
					}
					conditionOrderEvent.addOrderConditionRelValue(orderCondRelValueEvent);
				}
			}
			conditionOrderEvent.setResolvedDescription(conditionDescription);
		} else {
			conditionOrderEvent.setResolvedDescription(conditionDescription);
		}
		variableNameMap = null;
		variableNameSet = null;
		
		return conditionOrderEvent;
	}

	/**
	 * This method posts event to pd to create SupervisionOrder
	 * 
	 * @param sof
	 */
	public static CreateSupervisionOrderEvent createSupervisionOrder(SupervisionOrderForm sof) {
		CreateSupervisionOrderEvent supervisionOrder = new CreateSupervisionOrderEvent();

		//User may have selected suggested order, hit next and then hit back
		if (sof.getOrderId() != null && !sof.getOrderId().equals("")) {
			//Don't want to overlay active order.
			if (!sof.getOrderStatusId().equals(UIConstants.ACTIVE_STATUS_ID)){
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				DeleteSupervisionOrderEvent event = (DeleteSupervisionOrderEvent) EventFactory.getInstance(SupervisionOrderControllerServiceNames.DELETESUPERVISIONORDER);
				event.setSupervisionOrderId(sof.getOrderId());
				dispatch.postEvent(event);
			}

		}
		supervisionOrder.setAgencyId(sof.getAgencyId());
		supervisionOrder.setOrderCourtId(sof.getCourtId());
		supervisionOrder.setCurrentCourtId(sof.getCurrentCourtId());
		supervisionOrder.setCriminalCaseId(sof.getCaseId());
		supervisionOrder.setVersionNum(sof.getVersionNum());
		supervisionOrder.setOrderChainNum(sof.getOrderChainNum());
		
		String defendantId = sof.getDefendantId();
		if (defendantId != null && !defendantId.equals("")&& defendantId.length() < 8) {
			StringBuffer sb = new StringBuffer(defendantId);
			for (int i = 0; i < 8 - defendantId.length(); i++) {
				sb.insert(0, "0");
			}
			defendantId = sb.toString();
		}
		supervisionOrder.setDefendantId(defendantId);
		supervisionOrder.setMigratedOrder(sof.getIsMigratedOrder());
		supervisionOrder.setHistoricalOrder(sof.getIsPretrialInterventionOrder());
		
		// for creating an historical order amended or modified
		if ( sof.getIsPretrialInterventionOrder() ){
			supervisionOrder.setModificationReason( sof.getCasenotes() );
		}

		supervisionOrder.setStatus(PDCodeTableConstants.STATUS_INCOMPLETE_ID);
		supervisionOrder.setOrderTitle(sof.getOrderTitleId());
		supervisionOrder.setOrigJudgeFirstName(sof.getOriginalJudgeFirstName());
		supervisionOrder.setOrigJudgeLastName(sof.getOriginalJudgeLastName());
		supervisionOrder.setPrintedName(sof.getPrintedName());
				
		if ( sof.getFineAmountTotal() == null || sof.getFineAmountTotal().trim().equals( PDConstants.BLANK ) ){
          supervisionOrder.setFineAmount( 0 );
		} else {
          double fa = new Double( sof.getFineAmountTotal() ).doubleValue();
          supervisionOrder.setFineAmount( fa );
		}

		supervisionOrder.setPlea(sof.getPleaId());
		supervisionOrder.setJuvCourtId(sof.getJuvCourtId());
		supervisionOrder.setJuvSupervisionBeginDate(sof.getJuvSupervisionBeginDate());
		if (sof.getJuvSupervisionLengthDays() != null && !sof.getJuvSupervisionLengthDays().equals(PDConstants.BLANK)){
			supervisionOrder.setJuvSupervisionLengthDays(new Integer(sof.getJuvSupervisionLengthDays()));
		} else {
			supervisionOrder.setJuvSupervisionLengthDays(0);
		}
		if (sof.getJuvSupervisionLengthMonths() != null && !sof.getJuvSupervisionLengthMonths().equals(PDConstants.BLANK)){
			supervisionOrder.setJuvSupervisionLengthMonths(new Integer(sof.getJuvSupervisionLengthMonths()));
		} else {
			supervisionOrder.setJuvSupervisionLengthMonths(0);
		}
		if (sof.getJuvSupervisionLengthYears() != null && !sof.getJuvSupervisionLengthYears().equals(PDConstants.BLANK)){
			supervisionOrder.setJuvSupervisionLengthYears(new Integer(sof.getJuvSupervisionLengthYears()));
		} else {
			supervisionOrder.setJuvSupervisionLengthYears(0);
		}

		supervisionOrder.setSpecialCourtCd(sof.getSpecialCourtCd());
		supervisionOrder.setComments(sof.getComments());
		supervisionOrder.setSummaryOfChanges( sof.getSummaryOfChanges() );
		supervisionOrder.setPrintedOffenseDesc(sof.getPrintedOffenseDesc());
		supervisionOrder.setVersionType(sof.getVersionTypeId());
		supervisionOrder.setSuggestedOrderId(sof.getSuggestedOrderId());
		boolean limitedSupPeriod = sof.isLimitedSupervisonPeriod();
		//		boolean limitedSupPeriod = sof.isLimitedSupervisionOrderTemp();
		if (limitedSupPeriod) {
			supervisionOrder.setLimitedSupervisionPeriod(limitedSupPeriod);
			supervisionOrder.setLimitedSupervisionBeginDate(sof.getSupervisionBeginDate());
			supervisionOrder.setLimitedSupervisionEndDate(sof.getSupervisionEndDate());
		} else {
			supervisionOrder.setLimitedSupervisionBeginDate(null);
			supervisionOrder.setLimitedSupervisionEndDate(null);
		}
		supervisionOrder.setVariableElementReferenceMap(sof.getReferenceVariableMap());
		supervisionOrder.setComments(sof.getComments());
		//dag 01/19/07
		supervisionOrder.setCaseSupervisionBeginDate(sof.getCaseSupervisionBeginDate());
		supervisionOrder.setCaseSupervisionEndDate(sof.getCaseSupervisionEndDate());
		if (sof.getConfinementLengthDays() != null
				&& !sof.getConfinementLengthDays().equals("")) {
			supervisionOrder.setConfinementLengthDays(new Integer(sof.getConfinementLengthDays()).intValue());
		} else {
			supervisionOrder.setConfinementLengthDays(0);
		}
		if (sof.getConfinementLengthMonths() != null && !sof.getConfinementLengthMonths().equals("")) {
			supervisionOrder.setConfinementLengthMonths(new Integer(sof.getConfinementLengthMonths()).intValue());
		} else {
			supervisionOrder.setConfinementLengthMonths(0);
		}
		if (sof.getConfinementLengthYears() != null && !sof.getConfinementLengthYears().equals("")) {
			supervisionOrder.setConfinementLengthYears(new Integer(sof.getConfinementLengthYears()).intValue());
		} else {
			supervisionOrder.setConfinementLengthYears(0);
		}
		supervisionOrder.setDispositionTypeId(sof.getDispositionTypeId());
		//supervisionOrder.setOffenseId(sof.getOffenseId());
		if (sof.getSupervisionLengthDays() != null && !sof.getSupervisionLengthDays().equals("")) {
			supervisionOrder.setSupervisionLengthDays(new Integer(sof.getSupervisionLengthDays()).intValue());
		} else {
			supervisionOrder.setSupervisionLengthDays(0);
		}
		if (sof.getSupervisionLengthMonths() != null && !sof.getSupervisionLengthMonths().equals("")) {
			supervisionOrder.setSupervisionLengthMonths(new Integer(sof.getSupervisionLengthMonths()).intValue());
		} else {
			supervisionOrder.setSupervisionLengthMonths(0);
		}
		if (sof.getSupervisionLengthYears() != null && !sof.getSupervisionLengthYears().equals("")) {
			supervisionOrder.setSupervisionLengthYears(new Integer(sof.getSupervisionLengthYears()).intValue());
		} else {
			supervisionOrder.setSupervisionLengthYears(0);
		}
		//dag 01/19/07 end
		// get selected suggested order from the collection
		SuggestedOrderResponseEvent suggOrderRespEvt = null;
		Collection suggOrders = sof.getSuggestedOrderList();
		if (suggOrders != null) {
			Iterator suggOrdersIter = suggOrders.iterator();
			while (suggOrdersIter.hasNext()) {
				suggOrderRespEvt = (SuggestedOrderResponseEvent) suggOrdersIter.next();
				// check if this one is selected
				if (suggOrderRespEvt.getSuggestedOrderId().equals(sof.getSuggestedOrderId())) {
					break;
				}
			}
		} 
		if (sof.getIsPretrialInterventionOrder()){
		    supervisionOrder.setHistoricalOrder(true);
			Collection conditions = sof.getConditionSelectedList();
			if (conditions != null) {
				Iterator condIter = conditions.iterator();
				while (condIter.hasNext()) {
					ConditionDetailResponseEvent cdre = (ConditionDetailResponseEvent) condIter.next();

					SupervisionOrderConditionEvent conditionOrderEvent = UISupervisionOrderHelper.createOrderConditionEvent(cdre, sof.getReferenceVariableMap());
					cdre.setResolvedDescription(conditionOrderEvent.getResolvedDescription());

					supervisionOrder.addHistOrderCondition(conditionOrderEvent);
				}
			}
		}
		return supervisionOrder;

	}

	/**
	 * Retrieves CompositeResonse from request dispatcher and checks for
	 * exception.
	 * 
	 * @param dispatch
	 * @return
	 */
	public static CompositeResponse getCompositeResponse(IDispatch dispatch) {
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map responseMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(responseMap);

		return compositeResponse;
	}

	/**
	 * @param cre
	 * @return
	 */
	public static ConditionDetailResponseEvent getConditionDetailResponseEvent(
			String conditionId) {
		GetSupervisionConditionDetailsEvent getConditionDetailsEvent = (GetSupervisionConditionDetailsEvent) EventFactory
				.getInstance(SupervisionOptionsControllerServiceNames.GETSUPERVISIONCONDITIONDETAILS);

		getConditionDetailsEvent.setConditionId(conditionId);

		IDispatch requestDispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		requestDispatch.postEvent(getConditionDetailsEvent);

		CompositeResponse compositeResponse = UISupervisionOrderHelper.getCompositeResponse(requestDispatch);
		Map responseMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(responseMap);

		ConditionDetailResponseEvent cdre = (ConditionDetailResponseEvent) MessageUtil.filterComposite(compositeResponse,ConditionDetailResponseEvent.class);

		cdre.setTopic(cdre.getConditionId());
		return cdre;
	}

	private static Map getConditionDetailResponseMap(Collection conditions) {
		Map map = new HashMap();
		for (Iterator condIter = conditions.iterator(); condIter.hasNext();) {
			ConditionDetailResponseEvent cdre = (ConditionDetailResponseEvent) condIter.next();
			map.put(cdre.getConditionId(), cdre);
		}
		return map;
	}

	private static Map getOrderValueMap(SupervisionOrderDetailResponseEvent currImpactedOrder) {
		Map map = new HashMap();
		// current order
		Collection conds = currImpactedOrder.getConditions();
		for (Iterator condIter = conds.iterator(); condIter.hasNext();) {
			SupOrderConditionResponseEvent cdre = (SupOrderConditionResponseEvent) condIter.next();
			Collection veres = cdre.getSupOrderConditionRelValues();
			for (Iterator vereIter = veres.iterator(); vereIter.hasNext();) {
				SupOrderConditionRelValueResponseEvent vere = (SupOrderConditionRelValueResponseEvent) vereIter.next();
				map.put(cdre.getConditionId() + "^"	+ vere.getVariableElementTypeId(), vere);
			}
		}

		return map;
	}

	/**
	 * Retrieves reference variable values for a given list of reference
	 * variable names.
	 * 
	 * @param varElementNames
	 * @param orderId
	 * @param criminalCaseId
	 * @param action
	 * @return
	 */
	public static Map getReferenceVariableMap(Collection varElementNames,
			String orderId, String criminalCaseId, boolean refreshable) {

		Map referenceVariableMap = new HashMap();
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		GetSupervisionOrderVariableElementReferencesEvent requestEvent = (GetSupervisionOrderVariableElementReferencesEvent) EventFactory
				.getInstance(SupervisionOrderControllerServiceNames.GETSUPERVISIONORDERVARIABLEELEMENTREFERENCES);

		requestEvent.setVariableElementNames(varElementNames);
		requestEvent.setOrderId(orderId);
		requestEvent.setCriminalCaseId(criminalCaseId);
		//requestEvent.setFormAction(refreshData);
		requestEvent.setRefreshable(refreshable);
		dispatch.postEvent(requestEvent);

		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(response);

		Collection vers = MessageUtil.compositeToCollection(response, VariableElementResponseEvent.class);

		if (vers != null) {
			Iterator iter = vers.iterator();
			VariableElementResponseEvent vere = null;
			while (iter.hasNext()) {
				vere = (VariableElementResponseEvent) iter.next();
				referenceVariableMap.put(vere.getName(), vere.getValue());
			}
		}

		return referenceVariableMap;
	}

	/**
	 * @param sof
	 */
	public static void getSupervisionOrderDetails(SupervisionOrderForm sof) {
		
		GetSupervisionOrderDetailsEvent requestEvent = new GetSupervisionOrderDetailsEvent();
		
		requestEvent.setSupervisionOrderId(sof.getOrderId());	
		//requestEvent.setLegacyDataRefreshable(sof.isRefreshRefVarsAllowed());
		requestEvent.setLegacyDataRefreshable(sof.isVersionTypeChangeAllowed());

		boolean isDelete = false;
		if (sof.getAction().equals(UIConstants.DELETE)){
			requestEvent.setDeleteAction(true);
			isDelete = true;
		}

		// get OrderResponseEvent
		SupervisionOrderDetailResponseEvent orderDetailResponseEvent = 
		    (SupervisionOrderDetailResponseEvent) MessageUtil.postRequest(requestEvent, SupervisionOrderDetailResponseEvent.class);

		if (orderDetailResponseEvent != null) 
		{			
			if (isDelete){
				UISupervisionOrderHelper.setFormOnDelete(sof, orderDetailResponseEvent);
			} else {
				UISupervisionOrderHelper.setFormFromOrderDetails(sof, orderDetailResponseEvent);
			}

			Collection conditions = UISupervisionOrderHelper.convert(sof.isVersionTypeChangeAllowed(),
					sof.isRefreshRefVarsAllowed(), orderDetailResponseEvent.getConditions(), sof.getReferenceVariableMap());
			// sort it by sequence num
			Collections.sort((List) conditions, ConditionDetailResponseEvent.SeqNumComparator);
			String orderCourt=orderDetailResponseEvent.getOrderCourtId();
		
			sof.setDispositionTypeId(orderDetailResponseEvent.getDispositionTypeId());
			sof.setConditionSelectedList(conditions);
			if(conditions!=null){
				Iterator condIter=conditions.iterator();
				while(condIter.hasNext()){
					ConditionDetailResponseEvent myRespEvt=(ConditionDetailResponseEvent)condIter.next();
					boolean courtValid=true;
					if(!myRespEvt.isSpecialCondition()){
						courtValid=UISupervisionOrderHelper.isAtLeastOneCourtTheSame(myRespEvt.getAllCourtIds(),orderCourt);
					}
					myRespEvt.setNonCourtApplicable(!courtValid);
				}
			}		
			
		}

	}
	private static void setFormOnDelete(SupervisionOrderForm sof, SupervisionOrderDetailResponseEvent orderDetailResponseEvent){
		sof.setDefendantId(orderDetailResponseEvent.getDefendantId());
		sof.setCdi(orderDetailResponseEvent.getCdi());
		sof.setCaseNum(orderDetailResponseEvent.getCaseNum());
		sof.setPrimaryCaseOrderKey(sof.getCdi() + sof.getCaseNum());
		sof.setSpn(orderDetailResponseEvent.getDefendantId());
		sof.setCourtId(orderDetailResponseEvent.getOrderCourtId());
		sof.setCourtNum(orderDetailResponseEvent.getOrderCourtNum());
		sof.setCurrentCourtId(orderDetailResponseEvent.getCurrentCourtId());
		sof.setCurrentCourtNum(formatCourtNumber(orderDetailResponseEvent.getCurrentCourtNum()));
		sof.setOffenseId(orderDetailResponseEvent.getOffenseId());
		sof.setCaseFileDate(orderDetailResponseEvent.getCaseFileDate());
		if (orderDetailResponseEvent.getPlea() == null){
			sof.setPleaId("");
			sof.setPlea("");
		} else {
			sof.setPleaId(orderDetailResponseEvent.getPlea());
			if (!orderDetailResponseEvent.getPlea().equals("")){
				sof.setPlea(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.PLEA,orderDetailResponseEvent.getPlea()));
			}else {
				sof.setPlea("");
			}
		}	
		sof.setOrderStatusId(orderDetailResponseEvent.getOrderStatusId());
		sof.setStatusChangeDate(orderDetailResponseEvent.getStatusChangeDate()); 
		sof.setOrderVersion(orderDetailResponseEvent.getOrderVersion());
		sof.setVersionNum(orderDetailResponseEvent.getVersionNum());
		sof.setOrderFileDate(orderDetailResponseEvent.getOrderFileDate());
		sof.setIsPretrialInterventionOrder(orderDetailResponseEvent.isHistoricalOrder());
		sof.setSupOrderRevisionDate(orderDetailResponseEvent.getSupOrderRevisionDate());
		if(orderDetailResponseEvent.isSignedByDefendant()){
			sof.setDefendantSignature("Signed");
		}
		else{
			sof.setDefendantSignature("Not Signed");
		}
		sof.setLimitedSupervisonPeriod(orderDetailResponseEvent.isLimitedSupervisionPeriod());
		sof.setSupervisionBeginDate(orderDetailResponseEvent.getSupervisionOrderBeginDate());
		sof.setSupervisionEndDate(orderDetailResponseEvent.getSupervisionOrderEndDate());
		sof.setVersionTypeId(orderDetailResponseEvent.getVersionTypeId());
		sof.setSuggestedOrderId(orderDetailResponseEvent.getSuggestedOrderId());
		sof.setCasenotes(orderDetailResponseEvent.getModificationReason());
		sof.setSummaryOfChanges(orderDetailResponseEvent.getSummaryOfChanges());
		sof.setFineAmountTotal(orderDetailResponseEvent.getFineAmountTotal());
		sof.setProbationEndDate(orderDetailResponseEvent.getProbationEndDate());
		sof.setProbationStartDate(orderDetailResponseEvent.getProbationStartDate());
		sof.setJailTime(orderDetailResponseEvent.getJailTime());
		if (orderDetailResponseEvent.getPlea() == null){
			sof.setPleaId("");
			sof.setPlea("");
		} else {
			sof.setPleaId(orderDetailResponseEvent.getPlea());
			if (!orderDetailResponseEvent.getPlea().equals("")){
				sof.setPlea(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.PLEA,orderDetailResponseEvent.getPlea()));
			}else {
				sof.setPlea("");
			}
		}	
		sof.setDeferredSupervisionLength(orderDetailResponseEvent.getDeferredSupervisionLength());
		sof.setJudgeSelectId(orderDetailResponseEvent.getJudgeId());
		sof.setSignedByDefendantDate(orderDetailResponseEvent.getSignedByDefendantDate());
		sof.setSignedByJudgeDate(orderDetailResponseEvent.getSignedByJudgeDate());
		sof.setCaseSupervisionBeginDate(orderDetailResponseEvent.getCaseSupervisionBeginDate());
		sof.setCaseSupervisionEndDate(orderDetailResponseEvent.getCaseSupervisionEndDate());
		sof.setConfinementLengthDays(orderDetailResponseEvent.getConfinementLengthDays());
		sof.setConfinementLengthMonths(orderDetailResponseEvent.getConfinementLengthMonths());
		sof.setConfinementLengthYears(orderDetailResponseEvent.getConfinementLengthYears());
		sof.setDispositionTypeId(orderDetailResponseEvent.getDispositionTypeId());
		sof.setDispositionType(orderDetailResponseEvent.getDispositionType());
		sof.setSupervisionLengthDays(orderDetailResponseEvent.getSupervisionLengthDays());
		sof.setSupervisionLengthMonths(orderDetailResponseEvent.getSupervisionLengthMonths());
		sof.setSupervisionLengthYears(orderDetailResponseEvent.getSupervisionLengthYears());
		sof.setOutOfCountyCase(orderDetailResponseEvent.isOutOfCountyCase());
	}
	
	private static void setFormFromOrderDetails(SupervisionOrderForm sof, SupervisionOrderDetailResponseEvent orderDetailResponseEvent) {

		Name defendant_name_obj = orderDetailResponseEvent.getDefendantName();
		String defendant_name = (defendant_name_obj != null)?defendant_name_obj.getFormattedName():PDConstants.BLANK;
		sof.setName(defendant_name);
		
		sof.setDefendantId(orderDetailResponseEvent.getDefendantId());
		sof.setCdi(orderDetailResponseEvent.getCdi());
		sof.setCaseNum(orderDetailResponseEvent.getCaseNum());
		sof.setPrimaryCaseOrderKey(sof.getCdi() + sof.getCaseNum());
		sof.setSpn(orderDetailResponseEvent.getDefendantId());
		sof.setCourtId(orderDetailResponseEvent.getOrderCourtId());
		sof.setCourtNum(orderDetailResponseEvent.getOrderCourtNum());
		sof.setCourtCategory(orderDetailResponseEvent.getOrderCourtCategory());
		sof.setCurrentCourtId(orderDetailResponseEvent.getCurrentCourtId());
		sof.setCurrentCourtNum(formatCourtNumber(orderDetailResponseEvent.getCurrentCourtNum()));
		sof.setCurrentCourtCategory(orderDetailResponseEvent.getCurrentCourtCategory());
		sof.setOffenseId(orderDetailResponseEvent.getOffenseId());
		sof.setCaseFileDate(orderDetailResponseEvent.getCaseFileDate());
		if (orderDetailResponseEvent.getPrintedName() == null || orderDetailResponseEvent.getPrintedName().equals(PDConstants.BLANK)){
			sof.setPrintedName(UISupervisionOrderHelper.parsePrintedName(sof.getName()));
		} else {
			sof.setPrintedName(orderDetailResponseEvent.getPrintedName());
		}
		sof.setPrintedOffenseDesc(orderDetailResponseEvent.getPrintedOffenseDesc());
		sof.setOrderStatusId(orderDetailResponseEvent.getOrderStatusId());
		sof.setStatusChangeDate(orderDetailResponseEvent.getStatusChangeDate()); 
		sof.setOrderVersion(orderDetailResponseEvent.getOrderVersion());
		sof.setVersionNum(orderDetailResponseEvent.getVersionNum());
		sof.setOrderFileDate(orderDetailResponseEvent.getOrderFileDate());
		sof.setIsPretrialInterventionOrder(orderDetailResponseEvent.isHistoricalOrder());
		sof.setSupOrderRevisionDate(orderDetailResponseEvent.getSupOrderRevisionDate());
		if(orderDetailResponseEvent.isSignedByDefendant()){
			sof.setDefendantSignature("Signed");
		}
		else{
			sof.setDefendantSignature("Not Signed");
		}
		sof.setLimitedSupervisonPeriod(orderDetailResponseEvent.isLimitedSupervisionPeriod());
		sof.setSupervisionBeginDate(orderDetailResponseEvent.getSupervisionOrderBeginDate());
		sof.setSupervisionEndDate(orderDetailResponseEvent.getSupervisionOrderEndDate());
		sof.setVersionTypeId(orderDetailResponseEvent.getVersionTypeId());
		sof.setSuggestedOrderId(orderDetailResponseEvent.getSuggestedOrderId());
		sof.setCasenotes(orderDetailResponseEvent.getModificationReason());
		sof.setSummaryOfChanges(orderDetailResponseEvent.getSummaryOfChanges());
		sof.setFineAmountTotal(orderDetailResponseEvent.getFineAmountTotal());
		sof.setProbationEndDate(orderDetailResponseEvent.getProbationEndDate());
		sof.setProbationStartDate(orderDetailResponseEvent.getProbationStartDate());
		//sof.setStatusChangeDate(orderDetailResponseEvent.getUpdateDate()); //added for Defect JIMS200075044
		sof.setJailTime(orderDetailResponseEvent.getJailTime());
		if (orderDetailResponseEvent.getPlea() == null){
			sof.setPleaId("");
			sof.setPlea("");
		} else {
			sof.setPleaId(orderDetailResponseEvent.getPlea());
			if (!orderDetailResponseEvent.getPlea().equals("")){
				sof.setPlea(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.PLEA,orderDetailResponseEvent.getPlea()));
			}else {
				sof.setPlea("");
			}
		}	
/**		if (orderDetailResponseEvent.getSpecialCourtCd() == null){
			sof.setSpecialCourtCd("");
			sof.setSpecialCourt("");
		} else { */
		if (orderDetailResponseEvent.getSpecialCourtCd() != null) {
			sof.setSpecialCourtCd(orderDetailResponseEvent.getSpecialCourtCd());
			if (!orderDetailResponseEvent.getSpecialCourtCd().equals("")){
				sof.setSpecialCourt(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SPECIAL_COURT,orderDetailResponseEvent.getSpecialCourtCd()));
			}else {
				sof.setSpecialCourt("");
			}
		}

		sof.setPrintedOffenseDesc(orderDetailResponseEvent.getPrintedOffenseDesc());
		sof.setDeferredSupervisionLength(orderDetailResponseEvent.getDeferredSupervisionLength());
		Name aName = orderDetailResponseEvent.getSignedByName();
		if (aName != null 
				&& aName.getLastName() != null 
				&& !aName.getLastName().equals(BLANK)){
			sof.setJudgeFirstName(aName.getFirstName());
			sof.setJudgeLastName(aName.getLastName());
		}
		aName = orderDetailResponseEvent.getOrderPresentorName();
		if (aName != null 
				&& aName.getLastName() != null 
				&& !aName.getLastName().equals(BLANK)){
			sof.setPresentedByFirstName(aName.getFirstName());
			sof.setPresentedByLastName(aName.getLastName());
		}
		aName = orderDetailResponseEvent.getOrigJudgeName();
		if (aName != null 
				&& aName.getLastName() != null 
				&& !aName.getLastName().equals(BLANK)){
			sof.setOriginalJudgeFirstName(aName.getFirstName());
			sof.setOriginalJudgeLastName(aName.getLastName());
		}

		sof.setJudgeSelectId(orderDetailResponseEvent.getJudgeId());
		sof.setOrderChainNum(orderDetailResponseEvent.getOrderChainNum());
		
		if (!UIConstants.CONFIRM_PREPARE_TO_FILE.equals(sof.getPrintAction())) 
		{
			sof.setJudgeSelectId(orderDetailResponseEvent.getJudgeId());
		}
		sof.setOffenseLevel(orderDetailResponseEvent.getOffenseLevel());
		sof.setSignedByDefendantDate(orderDetailResponseEvent.getSignedByDefendantDate());
		sof.setSignedByJudgeDate(orderDetailResponseEvent.getSignedByJudgeDate());
		
		// TODO THIS CODE SEEMS TO BE BAD:
		//This sets the ordertitle list to be displayed in the drop down
		//based on court category and version type
		if (sof.getIsPretrialInterventionOrder() && sof.getAction().equals(UIConstants.UPDATE)){
		    sof.setOrderTitleList(SupervisionOrderListHelper.groupOrderTitleList(
		    		sof.getAllOrderTitleList(), 
		    		sof.getCourtCategory(), 
		    		sof.getCourtNum(),
		    		BLANK));
		} else {
		    sof.setOrderTitleList(SupervisionOrderListHelper.groupOrderTitleList(
		    		sof.getAllOrderTitleList(), 
		    		sof.getCourtCategory(),
		    		sof.getCourtNum(),
		    		sof.getVersionType()));
		}
		//sof.setOrderTitleList(sof.getAllOrderTitleList());
		// order update

		sof.setCaseSupervisionBeginDate(orderDetailResponseEvent.getCaseSupervisionBeginDate());
		sof.setCaseSupervisionEndDate(orderDetailResponseEvent.getCaseSupervisionEndDate());
		sof.setConfinementLengthDays(orderDetailResponseEvent.getConfinementLengthDays());
		sof.setConfinementLengthMonths(orderDetailResponseEvent.getConfinementLengthMonths());
		sof.setConfinementLengthYears(orderDetailResponseEvent.getConfinementLengthYears());
		sof.setDispositionTypeId(orderDetailResponseEvent.getDispositionTypeId());
		sof.setDispositionType(orderDetailResponseEvent.getDispositionType());
		sof.setOffenseId(orderDetailResponseEvent.getOffenseId());
		sof.setOffense(orderDetailResponseEvent.getOffense());
		sof.setSupervisionLengthDays(orderDetailResponseEvent.getSupervisionLengthDays());
		sof.setSupervisionLengthMonths(orderDetailResponseEvent.getSupervisionLengthMonths());
		sof.setSupervisionLengthYears(orderDetailResponseEvent.getSupervisionLengthYears());
		sof.setJuvCourtId(orderDetailResponseEvent.getJuvenileCourtId());
		sof.setJuvSupervisionBeginDate(orderDetailResponseEvent.getJuvSupervisionOrderBeginDate());
		sof.setJuvSupervisionLengthDays(orderDetailResponseEvent.getJuvSupervisionLengthDays());
		sof.setJuvSupervisionLengthMonths(orderDetailResponseEvent.getJuvSupervisionLengthMonths());
		sof.setJuvSupervisionLengthYears(orderDetailResponseEvent.getJuvSupervisionLengthYears());

		if (!UIConstants.CONFIRM_UPDATE.equals(sof.getPrintAction())
				&& !UIConstants.CONFIRM_CREATE.equals(sof.getPrintAction())
				&& !UIConstants.CONFIRM_PREPARE_TO_FILE.equals(sof
						.getPrintAction())) {
			sof.setOrderTitleId(orderDetailResponseEvent.getOrderTitleId());				
		} 
		sof.setOutOfCountyCase(orderDetailResponseEvent.isOutOfCountyCase());
		
	}

	public static boolean isOOCcase(String agencyId, String cdiVal){
		boolean isOOC=false;
		if (agencyId.equals(UIConstants.PTR) && PDCodeTableConstants.PTS.equals(cdiVal)) {
			isOOC=true;
		}
		else if (agencyId.equals(UIConstants.CSC) && PDCodeTableConstants.CSCD.equals(cdiVal)) {
			isOOC=true;
		}
		return isOOC;
	}
	
	/**
	 * Takes only a collection of ConditionDetailResponseEvents 
	 * @param conditions
	 * @return  true if the condition has missing variable elements or false if there are no missing elements
	 */
	public static boolean hasMissingVariableElements(Collection conditions){
		boolean retValue=false;
		if(conditions==null || conditions.size()<1){
			
		}
		else{
			Iterator iter=conditions.iterator();
			while(iter.hasNext()){
				ConditionDetailResponseEvent myCondRespEvt=(ConditionDetailResponseEvent)iter.next();
				if(myCondRespEvt.isConditionEmpty()){
					return true;
				}
			}
		}
			
		return retValue;
	}
	/**
	 * @param sof
	 */
	public static SupervisionOrderDetailResponseEvent getSupervisionOrderDetailsForReporting(
			String orderId) {
		GetSupervisionOrderDetailsEvent requestEvent = new GetSupervisionOrderDetailsEvent();
		requestEvent.setSupervisionOrderId(orderId);
		IDispatch dispatch = EventManager
				.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);
		SupervisionOrderDetailResponseEvent soDetails = null;
		CompositeResponse compositeResponse = UISupervisionOrderHelper.getCompositeResponse(dispatch);
		Map responseMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(responseMap);

		// get OrderResponseEvent
		Collection coll = MessageUtil.compositeToCollection(compositeResponse,
				SupervisionOrderDetailResponseEvent.class);
		coll = MessageUtil.processEmptyCollection(coll);
		// there is only one event
		Iterator it = coll.iterator();
		if (it.hasNext()) {
			soDetails = (SupervisionOrderDetailResponseEvent) it.next();
		}
		return soDetails;
	}

	public static String getVarElementId(
			ConditionDetailResponseEvent condition,
			VariableElementResponseEvent varElement) {
		return condition.getConditionId() + "_" + varElement.getName() + "_"
				+ "ID";
	}

	/**
	 * @param aForm
	 * @param aRequest
	 */
	public static void populateMiniSupervisionOrderForm(
			SupervisionOrderForm sof, HttpServletRequest aRequest) {
		sof.setPleas(SimpleCodeTableHelper.getCodesSortedByCode(PDCodeTableConstants.PLEA));
		sof.setJuvCourts(SupervisionOrderListHelper.getJuvCourts());   
		sof.setSpecialCourtCds(SimpleCodeTableHelper.getCodesSortedByCode(PDCodeTableConstants.SPECIAL_COURT));
		String primKey = sof.getPrimaryCaseOrderKey();
		
		 //storing the statusId in the session--start  
        String origOrderStatusId = sof.getOrderStatusId();
		aRequest.getSession().setAttribute("originalOrderStatusId",origOrderStatusId);
		//end
		
		if (primKey != null) {
			// get SupervisionOrderSearchForm from the session to get the list
			// of case orders
			SupervisionOrderSearchForm orderSearchForm = (SupervisionOrderSearchForm) aRequest
					.getSession().getAttribute("supervisionOrderSearchForm");
			if (orderSearchForm != null) {
				Collection orderList = orderSearchForm.getCaseOrderList();
				if (orderList != null) {
					Iterator orderIter = orderList.iterator();
					while (orderIter.hasNext()) {
						CaseOrderResponseEvent caseOrder = (CaseOrderResponseEvent) orderIter.next();
						if (caseOrder.getPrimaryKey().equals(primKey)) {
							orderSearchForm.setSpn(caseOrder.getSpn());
							if (!UIConstants.CONFIRM_CREATE.equals(sof.getPrintAction())
									&& !UIConstants.CONFIRM_PREPARE_TO_FILE.equals(sof.getPrintAction())) {
								sof.setOrderId(caseOrder.getOrderId());
							}
							sof.setCdi(caseOrder.getCdi());
							sof.setCaseNum(caseOrder.getCaseNum());
							sof.setComments(caseOrder.getComments());
							sof.setCourtId(caseOrder.getCourtId());
							sof.setCourtNum(caseOrder.getCourtNum());
							sof.setCourtCategory(caseOrder.getCourtCategory());
							sof.setCurrentCourtId(caseOrder.getCurrentCourtId());
							sof.setCurrentCourtNum(formatCourtNumber(caseOrder.getCurrentCourtNum()));
							sof.setCurrentCourtCategory(caseOrder.getCurrentCourtCategory());
							sof.setDispositionTypeId(caseOrder.getDispositionTypeId());
							sof.setDefendantId(caseOrder.getSpn());
							sof.setOffenseId(caseOrder.getOffenseId());
							sof.setCaseFileDate(caseOrder.getCaseFileDate());
							sof.setOrderTitleId(caseOrder.getOrderTitleId());
							sof.setOriginalJudgeFirstName(caseOrder.getOrigJudgeFirstName());
							sof.setOriginalJudgeLastName(caseOrder.getOrigJudgeLastName());
							if (caseOrder.getPlea() == null){
								sof.setPleaId("");
								sof.setPlea("");
							} else {
								sof.setPleaId(caseOrder.getPlea());
								if (!caseOrder.getPlea().equals("")){
									sof.setPlea(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.PLEA,caseOrder.getPlea()));
								}else {
									sof.setPlea("");
								}
							}	
							sof.setPrintedOffenseDesc(caseOrder.getPrintedOffenseDesc());
							sof.setOutOfCountyCase(caseOrder.isOutOfCountyCase());
							sof.setIsPretrialInterventionOrder(caseOrder.getIsHistoricalOrder());
							sof.setIsMigratedOrder(caseOrder.isMigratedOrder());
							sof.setJuvCourtId(caseOrder.getJuvenileCourtId());
							sof.setJuvSupervisionLengthDays(caseOrder.getJuvSupervisionLengthDays());
							sof.setJuvSupervisionLengthMonths(caseOrder.getJuvSupervisionLengthMonths());
							sof.setJuvSupervisionLengthYears(caseOrder.getJuvSupervisionLengthYears());
							String superBeginDate = DateUtil.dateToString(caseOrder.getJuvSupervisionOrderBeginDate(), UIConstants.DATE_FMT_1);
							sof.setJuvSupervisionBeginDateAsString(superBeginDate);
							sof.setSpecialCourtCd(caseOrder.getSpecialCourtCd());
							sof.setName(caseOrder.getName());
							sof.setConnectionId(caseOrder.getConnectionId());
							sof.setLikeConditionInd(caseOrder.getLikeConditionInd());
							sof.setSpn(caseOrder.getSpn());
							sof.setSummaryOfChanges(caseOrder.getSummaryChanges());
							Double aDouble = new Double(caseOrder.getFineAmountTotal());
							sof.setFineAmountTotal(UIUtil.formatCurrency(aDouble, UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT));
							sof.setJailTime(caseOrder.getJailTime());
							sof.setDeferredSupervisionLength(caseOrder.getDeferredSupervisionLength());
							sof.setOrderStatusId(caseOrder.getOrderStatusId());
							sof.setStatusChangeDate(caseOrder.getStatusChangeDate()); 
							sof.setConfinementLengthDays(caseOrder.getConfinementLengthDays());
							sof.setConfinementLengthMonths(caseOrder.getConfinementLengthMonths());
							sof.setConfinementLengthYears(caseOrder.getConfinementLengthYears());
							sof.setSupervisionLengthDays(caseOrder.getSupervisionLengthDays());
							sof.setSupervisionLengthMonths(caseOrder.getSupervisionLengthMonths());
							sof.setSupervisionLengthYears(caseOrder.getSupervisionLengthYears());
							superBeginDate = DateUtil.dateToString(caseOrder.getSupervisionOrderBeginDate(), UIConstants.DATE_FMT_1);
							String superEndDate = DateUtil.dateToString(caseOrder.getSupervisionOrderEndDate(), UIConstants.DATE_FMT_1);
							sof.setCaseSupervisionBeginDateAsString(superBeginDate);
							sof.setCaseSupervisionEndDateAsString(superEndDate);
							sof.setOrderFileDate(caseOrder.getOrderFileDate());
							sof.setOrderVersion(caseOrder.getOrderVersion());
							sof.setVersionNum(caseOrder.getVersionNum());
							sof.setVersionTypeId(caseOrder.getVersionTypeId());
							sof.setPrintedName(caseOrder.getPrintedName());

							if (!UIConstants.CONFIRM_PREPARE_TO_FILE.equals(sof
									.getPrintAction())) {
								sof.setJudgeSelectId(caseOrder.getJudgeName());
								sof.setSignedDate(caseOrder.getSignedByOfficerDate());
								//sof.setPresentedById(caseOrder.getOrderPresentorId());
							}
							sof.setSignedByDefendantDate(caseOrder
									.getSignedByDefendantDate());
							sof.setSignedByJudgeDate(caseOrder.getSignedByJudgeDate());
							// Build dropdown list for order Titles
							if (sof.getIsPretrialInterventionOrder() && sof.getAction().equals(UIConstants.UPDATE)){
							    sof.setOrderTitleList(SupervisionOrderListHelper.groupOrderTitleList(
							    		sof.getAllOrderTitleList(), 
							    		sof.getCourtCategory(), 
							    		sof.getCourtNum(),
							    		BLANK));
							} else {
							    sof.setOrderTitleList(SupervisionOrderListHelper.groupOrderTitleList(
							    		sof.getAllOrderTitleList(), 
							    		sof.getCourtCategory(), 
							    		sof.getCourtNum(),
							    		sof.getVersionType()));
							}
							
							break;
						}
					}
				}
			}
		}
	}

	/**
	 * @param aForm
	 * @param aRequest
	 */
	public static void populateSupervisionOrderForm(SupervisionOrderForm sof,
			HttpServletRequest aRequest) {
		sof.setPleas(SimpleCodeTableHelper.getCodesSortedByCode(PDCodeTableConstants.PLEA));
		sof.setJuvCourts(SupervisionOrderListHelper.getJuvCourts());
		sof.setSpecialCourtCds(SimpleCodeTableHelper.getCodesSortedByCode(PDCodeTableConstants.SPECIAL_COURT));
		String primKey = sof.getPrimaryCaseOrderKey();
		if (primKey != null) {
			// get SupervisionOrderSearchForm from the session to get the list
			// of case orders
			SupervisionOrderSearchForm orderSearchForm = (SupervisionOrderSearchForm) aRequest
					.getSession().getAttribute("supervisionOrderSearchForm");
			if (orderSearchForm != null) {
				Collection orderList = orderSearchForm.getCaseOrderList();
				if (orderList != null) {
					Iterator orderIter = orderList.iterator();
					while (orderIter.hasNext()) {
						CaseOrderResponseEvent caseOrder = (CaseOrderResponseEvent) orderIter.next();
						if (caseOrder.getPrimaryKey().equals(primKey)) {
							orderSearchForm.setSpn(caseOrder.getSpn());
							if (!UIConstants.CONFIRM_CREATE.equals(sof.getPrintAction())
								&& !UIConstants.CONFIRM_PREPARE_TO_FILE.equals(sof.getPrintAction())) {
									sof.setOrderId(caseOrder.getOrderId());
							}
							sof.setOutOfCountyCase(caseOrder.isOutOfCountyCase());
							sof.setName(caseOrder.getName());
							sof.setOrderChainNum(caseOrder.getOrderChainNum());
							//sof.setSpn(orderSearchForm.getSpn());
							sof.setDefendantId(caseOrder.getSpn());
							sof.setComments(caseOrder.getComments());
							sof.setConnectionId(caseOrder.getConnectionId());
							sof.setCdi(caseOrder.getCdi());
							sof.setCaseNum(caseOrder.getCaseNum());
							sof.setSpn(caseOrder.getSpn());
							
							// defect #69546
							String ctId = "";
							if ( "010".equals( sof.getCdi() ) ){
								
								ctId = getOutOfCountyCaseCourt(sof.getCaseNum() , sof.getDefendantId());
								sof.setCurrentCourtNum( ctId );
								String courtId = caseOrder.getCurrentCourtId();
								if ( courtId.length() > 3 ){
									sof.setCurrentCourtId( courtId.substring( 0, 3).concat( ctId ));
								}
							}else{
								sof.setCurrentCourtNum(formatCourtNumber(caseOrder.getCurrentCourtNum()));
								sof.setCurrentCourtId(caseOrder.getCurrentCourtId());
							}
							sof.setCourtId(caseOrder.getCourtId());
							sof.setCourtNum(caseOrder.getCourtNum());
							sof.setCourtCategory(caseOrder.getCourtCategory());
							sof.setCurrentCourtCategory(caseOrder.getCurrentCourtCategory());
							sof.setOffenseId(caseOrder.getOffenseId());
							sof.setCaseFileDate(caseOrder.getCaseFileDate());
							if (caseOrder.getPlea() == null){
								sof.setPleaId("");
								sof.setPlea("");
							} else {
								sof.setPleaId(caseOrder.getPlea());
								if (!caseOrder.getPlea().equals("")){
									sof.setPlea(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.PLEA,caseOrder.getPlea()));
								}else {
									sof.setPlea("");
								}
							}	
							sof.setPrintedOffenseDesc(caseOrder.getPrintedOffenseDesc());
							sof.setOrderFileDate(caseOrder.getOrderFileDate());
							sof.setIsPretrialInterventionOrder(caseOrder.getIsHistoricalOrder());
							sof.setIsMigratedOrder(caseOrder.isMigratedOrder());
							sof.setOrderStatusId(caseOrder.getOrderStatusId());
							sof.setOrderVersion(caseOrder.getOrderVersion());
							sof.setVersionNum(caseOrder.getVersionNum());
							sof.setLikeConditionInd(caseOrder.getLikeConditionInd());
							sof.setSupOrderRevisionDate(caseOrder.getSupOrderRevisionDate());
							sof.setVersionTypeId(caseOrder.getVersionTypeId());
							sof.setSuggestedOrderId(caseOrder.getSuggestedOrderId());
							sof.setCasenotes(caseOrder.getModificationReason());
							sof.setSummaryOfChanges(caseOrder.getSummaryChanges());
							if (caseOrder.isSignedByDefendant()){
								sof.setDefendantSignature("Signed");
							} else {
								sof.setDefendantSignature("Not Signed");
							}
							sof.setSignedByDefendantDate(caseOrder.getSignedByDefendantDate());
							sof.setSignedByJudgeDate(caseOrder.getSignedByJudgeDate());
							if (UIConstants.VIEW.equals(sof.getAction())) {
								sof.setOrderTitleList(sof.getAllOrderTitleList());
							} else if ((UIConstants.CREATE.equals(sof.getAction()) 
							        && UIConstants.INACTIVE_STATUS_ID.equals(sof.getOrderStatusId()))
							        || (UIConstants.UPDATE.equals(sof.getAction())
							                && sof.getIsPretrialInterventionOrder()) || (UIConstants.CREATE.equals(sof.getAction()) 
											        && UIConstants.ACTIVE_STATUS_ID.equals(sof.getOrderStatusId()))){
							    //We're creating an original when a create is done off of an inactive order.
							    //Show all original order titles on the update of an historical order.
								/* sof.setOrderTitleList(SupervisionOrderListHelper.groupOrderTitleList(
										sof.getAllOrderTitleList(),
										sof.getCourtCategory(),
										sof.getCourtNum(),
										BLANK));*/
								sof.setOrderTitleList(SupervisionOrderListHelper.groupOrderTitleList(
										sof.getAllOrderTitleList(),
										sof.getCurrentCourtCategory(),
										sof.getCourtNum(),
										BLANK));

							    } 
							else {
								/* sof.setOrderTitleList(SupervisionOrderListHelper.groupOrderTitleList(
														sof.getAllOrderTitleList(),
														sof.getCourtCategory(),
														sof.getCourtNum(),
														sof.getVersionType()));*/
								sof.setOrderTitleList(SupervisionOrderListHelper.groupOrderTitleList(
										sof.getAllOrderTitleList(),
										sof.getCurrentCourtCategory(),
										sof.getCourtNum(),
										sof.getVersionType()));
							
							}

							// order update
							if (!UIConstants.CONFIRM_UPDATE.equals(sof.getPrintAction())
								&& !UIConstants.CONFIRM_CREATE.equals(sof.getPrintAction())
								&& !UIConstants.CONFIRM_PREPARE_TO_FILE.equals(sof.getPrintAction())) {
									sof.setOrderTitleId(caseOrder.getOrderTitleId());
							}

							//sof.setStatusChangeDate(caseOrder.getUpdateDate()); commented 							
							sof.setStatusChangeDate(caseOrder.getStatusChangeDate()); 
							
							sof.setLimitedSupervisonPeriod(caseOrder.isLimitedSupervisionPeriod());
							
							Date supervisionBeginDate = caseOrder.getSupervisionOrderBeginDate();
							Date supervisionEndDate = caseOrder.getSupervisionOrderEndDate();
							
							if((supervisionBeginDate==null) && (supervisionEndDate == null))
							{
								supervisionBeginDate = caseOrder.getProbationStartDate();
								supervisionEndDate = caseOrder.getProbationEndDate();
							}
							
							sof.setCaseSupervisionBeginDate(supervisionBeginDate);
							sof.setCaseSupervisionEndDate(supervisionEndDate);
							
							//sof.setVersionTypeId(caseOrder.getVersionTypeId());
							
							Double aDouble = new Double(caseOrder.getFineAmountTotal());
							sof.setFineAmountTotal(UIUtil.formatCurrency(aDouble, UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT));
							sof.setJailTime(caseOrder.getJailTime());
							sof.setDeferredSupervisionLength(caseOrder
									.getDeferredSupervisionLength());
							if (!UIConstants.CONFIRM_PREPARE_TO_FILE.equals(sof.getPrintAction())) {
								sof.setJudgeSelectId(caseOrder.getJudgeName());
								sof.setSignedDate(caseOrder.getSignedByOfficerDate());
								//sof.setPresentedById(caseOrder.getOrderPresentorId());
							}
							sof.setOffenseLevel(caseOrder.getOffenseLevel());
							sof.setSuggestedOrderId(caseOrder.getSuggestedOrderId());
							sof.setCasenotes(caseOrder.getModificationReason());
							sof.setSummaryOfChanges(caseOrder.getSummaryChanges());
							sof.setSignedByDefendantDate(caseOrder.getSignedByDefendantDate());
							sof.setSignedByJudgeDate(caseOrder.getSignedByJudgeDate());
						
							sof.setConfinementLengthDays(caseOrder.getConfinementLengthDays());
							sof.setConfinementLengthMonths(caseOrder.getConfinementLengthMonths());
							sof.setConfinementLengthYears(caseOrder.getConfinementLengthYears());
							
							sof.setSupervisionLengthDays(caseOrder.getSupervisionLengthDays());
							sof.setSupervisionLengthMonths(caseOrder.getSupervisionLengthMonths());
							sof.setSupervisionLengthYears(caseOrder.getSupervisionLengthYears());
							
							break;
						}
					}
				}
			}
		}
	}

	public static void postUpdateOrderStatusEvent(String orderId, String origStatus, Date statChageDate, String status) {
		UpdateSupervisionOrderStatusEvent requestEvent = (UpdateSupervisionOrderStatusEvent) EventFactory
				.getInstance(SupervisionOrderControllerServiceNames.UPDATESUPERVISIONORDERSTATUS);

		requestEvent.setSupervisionOrderId(orderId);		
		requestEvent.setStatus(status);	
		if(origStatus != null && !origStatus.equalsIgnoreCase(status)){
			requestEvent.setStatusChangeDate(new Date()); 
		}
		else{
			requestEvent.setStatusChangeDate(statChageDate);
		}
		
		

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map responseMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(responseMap);
	}

	private static void setCurrentOrder(SupervisionOrderForm sof,
			SupervisionOrderDetailResponseEvent impactedOrder) {
		SupervisionOrderDetailResponseEvent currentOrder = new SupervisionOrderDetailResponseEvent();
		// set order properties
		currentOrder.setOrderId(sof.getOrderId());
		currentOrder.setCaseNum(sof.getCaseNum());
		currentOrder.setOrderTitle(sof.getOrderTitle());
		currentOrder.setOrderVersion(sof.getOrderVersion());

		// get order value map
		Map impOrderValueMap = getOrderValueMap(impactedOrder);

		// get condition map
		Map condDetailMap = getConditionDetailResponseMap(sof.getConditionSelectedList());
		// set conditions
		Collection conds = impactedOrder.getConditions();
		for (Iterator condIter = conds.iterator(); condIter.hasNext();) {
			SupOrderConditionResponseEvent impactedScre = (SupOrderConditionResponseEvent) condIter.next();
			//get ConditionDetailResponseEvent
			ConditionDetailResponseEvent cdre = (ConditionDetailResponseEvent) condDetailMap
					.get(impactedScre.getConditionId());
			if (cdre != null) {
				SupOrderConditionResponseEvent currentScre = convert(cdre,impOrderValueMap);
				currentOrder.insertCondition(currentScre);
			}
		}
		sof.setCurrentOrder(currentOrder);
		
		impOrderValueMap = null;
		condDetailMap = null;
		
	}

	public static void setImpactedOrder(SupervisionOrderForm sof,
			int impactedOrderIndex) {
		List impactedOrders = (List) sof.getImpactedOrderList();

		if (impactedOrders.size() > impactedOrderIndex) {
			SupervisionOrderDetailResponseEvent currImpactedOrder = (SupervisionOrderDetailResponseEvent) (impactedOrders
					.get(impactedOrderIndex));
			sof.setImpactedOrder(currImpactedOrder);
			sof.setCurrImpactedOrderIndex(impactedOrderIndex);
			setCurrentOrder(sof, currImpactedOrder);
		}

	}

	/**
	 * @param codes
	 * @param codeId
	 * @return
	 */
	public static void setOrderDescriptions(
			Collection orderDetailResponseEvents, SupervisionOrderForm sof) {
		// set order titles
		for (Iterator iter = orderDetailResponseEvents.iterator(); iter.hasNext();) {
			SupervisionOrderDetailResponseEvent orderEvent = (SupervisionOrderDetailResponseEvent) iter
					.next();
			orderEvent.setOrderTitle(SupervisionOrderListHelper.getOrderTitleName(sof.getOrderTitleList(), orderEvent
							.getOrderTitleId()));
			// set versionType
			orderEvent.setVersionType(CodeHelper.getCodeDescriptionByCode(sof
					.getVersionTypeList(), orderEvent.getVersionTypeId()));
		}
	}

	public static void setPreviewSample(SuggestedOrderForm form,
			Collection selectedList) {

		String conditionLiteral = "";
		String conditionLiteralSample = "";
		SuggestedOrderConditionResponseEvent response2 = null;
		Collection tokens = null;
		Collection referenceTokens = null;
		HashMap detailDictionaryNameIdMapping = null;
		Iterator iter = selectedList.iterator();
		while (iter.hasNext()) {

			response2 = (SuggestedOrderConditionResponseEvent) iter.next();
			conditionLiteral = response2.getConditionLiteral();

			if (conditionLiteral != null) {
				tokens = UISupervisionOptionHelper.tokenizeVariables(
						conditionLiteral, "{{", "}}");
				referenceTokens = UISupervisionOptionHelper.tokenizeVariables(
						conditionLiteral, "\\[", "\\]");
				tokens.addAll(referenceTokens);

				detailDictionaryNameIdMapping = UISupervisionOptionHelper
						.createDetailDictionaryNameIdMapping(form.getDetailDictionary());

				form.setDetailDictionaryNameIdHashMap(detailDictionaryNameIdMapping);
				conditionLiteralSample = UISupervisionOptionHelper.createLiteralSample(conditionLiteral, tokens,
								detailDictionaryNameIdMapping);
				if (conditionLiteralSample == null 	|| conditionLiteralSample.trim().equals("")) {
					conditionLiteralSample = conditionLiteral;
				}

				response2.setConditionLiteralPreview(conditionLiteralSample);

			} else {

				response2.setConditionLiteralPreview("");

			}
			response2 = null;
		}
	}

	public static void setPreviewSample(SupervisionOrderForm form,
			Collection selectedList, boolean isConditionDetailResp) {

		String conditionLiteral = "";
		String conditionLiteralSample = "";
		ConditionDetailResponseEvent response = null;
		ConditionResponseEvent response2 = null;
		Collection tokens = null;
		Collection referenceTokens = null;
		HashMap detailDictionaryNameIdMapping = null;
		Iterator iter = selectedList.iterator();
		while (iter.hasNext()) {

			if (isConditionDetailResp) {
				response = (ConditionDetailResponseEvent) iter.next();

				conditionLiteral = response.getDescription();
				if (response.isSpecialCondition()
						&& (conditionLiteral == null || conditionLiteral.trim().equals(""))) {
					conditionLiteral = response.getResolvedDescription();
				}
			}

			else {
				response2 = (ConditionResponseEvent) iter.next();
				conditionLiteral = response2.getDescription();
				if (response2.isSpecialCondition()
						&& (conditionLiteral == null || conditionLiteral.trim().equals(""))) {
					conditionLiteral = response2.getResolvedDescription();
				}
			}

			if (conditionLiteral != null) {
				tokens = UISupervisionOptionHelper.tokenizeVariables(conditionLiteral, "{{", "}}");
				referenceTokens = UISupervisionOptionHelper.tokenizeVariables(conditionLiteral, "\\[", "\\]");
				tokens.addAll(referenceTokens);

				detailDictionaryNameIdMapping = UISupervisionOptionHelper
						.createDetailDictionaryNameIdMapping(form.getDetailDictionary());

				form.setDetailDictionaryNameIdHashMap(detailDictionaryNameIdMapping);
				conditionLiteralSample = UISupervisionOptionHelper.createLiteralSample(conditionLiteral, tokens,
								detailDictionaryNameIdMapping);

				if (conditionLiteralSample == null || conditionLiteralSample.trim().equals("")) {
					conditionLiteralSample = conditionLiteral;
				}
				if (isConditionDetailResp) {
					response.setConditionLiteralPreview(conditionLiteralSample);
				}

				else {
					response2.setConditionLiteralPreview(conditionLiteralSample);
				}

			} else {
				if (isConditionDetailResp) {
					response.setConditionLiteralPreview("");
				}

				else {
					response2.setConditionLiteralPreview("");
				}
			}
			response = null;
			response2 = null;
		}
	}

	public static void add10LightConditions(Collection myColl){
		Date today=new Date();
		String myDATETIME24_FMT = "MMddyyyyHHmmss";
		Random myRandom=new Random();
		String baseName= "SpecialCondition_";
		
		for(int x=0; x<10; x++){
			int myRandInt=myRandom.nextInt(2000);
			ConditionDetailResponseEvent myRespEvt=new ConditionDetailResponseEvent();
			myRespEvt.setEffectiveDate(today);
			myRespEvt.setDeleted(false);
			myRespEvt.setName(baseName + DateUtil.dateToString(new Date(),myDATETIME24_FMT) + "_" + myRandInt);
			myRespEvt.setSpecialCondition(true);
			myColl.add(myRespEvt);
		}
	}
	
	public static void addNewPasoLightConditions(SupervisionOrderForm sof){
		
		Collection newConditions = sof.getConditionResultList();
		Collection existingConditions=sof.getConditionSelectedList();
		if(existingConditions==null){
			existingConditions=new ArrayList();
		}

		if(newConditions==null || newConditions.size()<1){
		}
		else{
			Iterator myIter=newConditions.iterator();
			List newCondList = CollectionUtil.iteratorToList(myIter);
			
			for (int i = 0; i < newCondList.size(); i++) {
				ConditionDetailResponseEvent condRespEvt = (ConditionDetailResponseEvent) newCondList.get(i);

				if(condRespEvt!=null){
					String val=condRespEvt.getDescription();
					if(val!=null && !(val.trim().equals(""))){
						condRespEvt.setSequenceNum(existingConditions.size()+1);
						condRespEvt.setConditionLiteralPreview(val);
						condRespEvt.setResolvedDescription(val);
						condRespEvt.setNotes( condRespEvt.getNotes() );
						sof.addConditionSelectedList(condRespEvt);
					}
				}
			}
		}
		newConditions=new ArrayList();
		sof.setConditionResultList(newConditions);
		UISupervisionOrderHelper.add10LightConditions(newConditions);
	}

	public static Collection setResequenceCondition(Collection aOriginalList,
			String aSequence, boolean aIsPreview) {

		if (aOriginalList != null) {
			if (aSequence == null || aSequence.trim().equals("")) {
				return aOriginalList;
			}
			ArrayList newPreviewList = new ArrayList(aOriginalList.size());
			Iterator originalListIter = aOriginalList.iterator();
			HashMap originalMap = new HashMap();
			while (originalListIter.hasNext()) {
				ConditionDetailResponseEvent response = (ConditionDetailResponseEvent) originalListIter.next();
				String newStr = Integer.toString(response.getSequenceNum());
				originalMap.put(newStr, response);
				newPreviewList.add(new ConditionDetailResponseEvent());
				newStr = null;
				response = null;
			}
			StringTokenizer myTokenizer = new StringTokenizer(aSequence, ",");
			originalListIter = null;
			originalListIter = aOriginalList.iterator();
			int counter = 0;
			while (originalListIter.hasNext()) {
				ConditionDetailResponseEvent response = (ConditionDetailResponseEvent) originalListIter.next();
				ConditionDetailResponseEvent newResponse = new ConditionDetailResponseEvent();
				String myNewSequenceNum = myTokenizer.nextToken();
				int mySequenceNum = (Integer.parseInt(myNewSequenceNum));
				if (aIsPreview) {
					ConditionDetailResponseEvent mappedResponse = (ConditionDetailResponseEvent) originalMap.get(Integer.toString(mySequenceNum));
					newResponse.setSequenceNum(counter + 1);
					if(mappedResponse!=null){
						newResponse.setStandard(mappedResponse.isStandard());
						newResponse.setConditionLiteralPreview(mappedResponse.getConditionLiteralPreview());
						newPreviewList.set(counter, newResponse);
					}
				} else {
					ConditionDetailResponseEvent mappedResponse = (ConditionDetailResponseEvent) originalMap.get(Integer.toString(mySequenceNum));
					if(mappedResponse!=null){
						mappedResponse.setSequenceNum(counter + 1);
						newPreviewList.set(counter, mappedResponse);
					}
				}
				counter++;
			}
			return newPreviewList;
		} else
			return null;

	}

	/**
	 * @param updatedValuesMap
	 * @param referenceVariableMap
	 * @return
	 */
	public static Map updateReferenceVariableMap(Map updatedValuesMap,
			Map referenceVariableMap) {

		Set updatedSet = updatedValuesMap.keySet();
		Iterator iter = updatedSet.iterator();
		String name = null;
		String value = null;
		while (iter.hasNext()) {
			name = (String) iter.next();
			value = (String) updatedValuesMap.get(name);
			referenceVariableMap.put(name, value);
		}

		return referenceVariableMap;
	}

	/**
	 * This method posts event to pd to update SupervisionOrder
	 * 
	 * @param sof
	 */
	public static UpdateSupervisionOrderEvent updateSupervisionOrder(SupervisionOrderForm sof) {
		UpdateSupervisionOrderEvent supervisionOrder = new UpdateSupervisionOrderEvent();
		if(sof.getIsPretrialInterventionOrder()){
			supervisionOrder.setIsHistoricalOrder(true);
			supervisionOrder.setVersionNum(sof.getVersionNum());
		} else if (sof.getIsMigratedOrder()){
			supervisionOrder.setVersionNum(sof.getVersionNum());
		} else if (sof.getVersionNum() > 0){
			supervisionOrder.setVersionNum(sof.getVersionNum());
		}
		supervisionOrder.setVersionType(sof.getVersionTypeId());
		supervisionOrder.setMigratedOrder(sof.getIsMigratedOrder());
		supervisionOrder.setOrderId(sof.getOrderId());
		supervisionOrder.setOrderStatusId(sof.getOrderStatusId());
		supervisionOrder.setModificationReason(sof.getCasenotes());
		supervisionOrder.setSummaryChanges(sof.getSummaryOfChanges());
		supervisionOrder.setOrderTitle(sof.getOrderTitleId());
		supervisionOrder.setOrigJudgeFirstName(sof.getOriginalJudgeFirstName());
		supervisionOrder.setOrigJudgeLastName(sof.getOriginalJudgeLastName());
		supervisionOrder.setPlea(sof.getPleaId());
		supervisionOrder.setJuvCourtId(sof.getJuvCourtId());
		if (sof.getJuvSupervisionLengthYears() != null && !sof.getJuvSupervisionLengthYears().equals("")) {
			supervisionOrder.setJuvSupervisionLengthYears(new Integer(sof.getJuvSupervisionLengthYears()).intValue());
		} else {
			supervisionOrder.setJuvSupervisionLengthYears(0);
		}
		if (sof.getJuvSupervisionLengthMonths() != null && !sof.getJuvSupervisionLengthMonths().equals("")) {
			supervisionOrder.setJuvSupervisionLengthMonths(new Integer(sof.getJuvSupervisionLengthMonths()).intValue());
		} else {
			supervisionOrder.setJuvSupervisionLengthMonths(0);
		}
		if (sof.getJuvSupervisionLengthDays() != null && !sof.getJuvSupervisionLengthDays().equals("")) {
			supervisionOrder.setJuvSupervisionLengthDays(new Integer(sof.getJuvSupervisionLengthDays()).intValue());
		} else {
			supervisionOrder.setJuvSupervisionLengthDays(0);
		}
		supervisionOrder.setJuvSupervisionBeginDate(sof.getJuvSupervisionBeginDate());
		supervisionOrder.setSpecialCourtCd(sof.getSpecialCourtCd());
		
		String validComments = replaceBRTags(sof.getComments());
		supervisionOrder.setComments( validComments );
		supervisionOrder.setPrintedName(sof.getPrintedName());
		String printedOffense = replaceBRTags(sof.getPrintedOffenseDesc());		
		supervisionOrder.setPrintedOffenseDesc(printedOffense);
		if (sof.getFineAmountTotal() != null && !sof.getFineAmountTotal().trim().equals(BLANK)){
			double fa = new Double(sof.getFineAmountTotal()).doubleValue();
			supervisionOrder.setFineAmount(fa);
		}
		boolean limitedSupPeriod = sof.isLimitedSupervisonPeriod();
		//	boolean limitedSupPeriod = sof.isLimitedSupervisionOrderTemp();
		if (limitedSupPeriod) {
			supervisionOrder.setLimitedSupervisionPeriod(limitedSupPeriod);
			supervisionOrder.setLimitedSupervisionBeginDate(sof.getSupervisionBeginDate());
			supervisionOrder.setLimitedSupervisionEndDate(sof.getSupervisionEndDate());
		} else {
			supervisionOrder.setLimitedSupervisionBeginDate(null);
			supervisionOrder.setLimitedSupervisionEndDate(null);
		}
		//get conditions
		Collection conditions = sof.getConditionSelectedList();
		if (conditions != null) {
			Iterator condIter = conditions.iterator();
			while (condIter.hasNext()) {
				ConditionDetailResponseEvent cdre = (ConditionDetailResponseEvent) condIter.next();
				UISupervisionOrderHelper.validateConditionValues(cdre, false);
				SupervisionOrderConditionEvent conditionOrderEvent = 
					UISupervisionOrderHelper.createOrderConditionEvent(cdre, sof.getReferenceVariableMap());
				cdre.setResolvedDescription(conditionOrderEvent.getResolvedDescription());

				// add Condition Rel values (Variable Elements)
				supervisionOrder.addCondition(conditionOrderEvent);
			}
			//dag 01/19/07
			//supervisionOrder.setOffenseId(sof.getOffenseId());
			supervisionOrder.setCaseSupervisionBeginDate(sof.getCaseSupervisionBeginDate());
			supervisionOrder.setCaseSupervisionEndDate(sof.getCaseSupervisionEndDate());
			supervisionOrder.setComments(sof.getComments());
			if (sof.getConfinementLengthDays() != null && !sof.getConfinementLengthDays().equals("")) {
				supervisionOrder.setConfinementLengthDays(new Integer(sof.getConfinementLengthDays()).intValue());
			} else {
				supervisionOrder.setConfinementLengthDays(0);
			}
			if (sof.getConfinementLengthMonths() != null && !sof.getConfinementLengthMonths().equals("")) {
				supervisionOrder.setConfinementLengthMonths(new Integer(sof.getConfinementLengthMonths()).intValue());
			} else {
				supervisionOrder.setConfinementLengthMonths(0);
			}
			if (sof.getConfinementLengthYears() != null && !sof.getConfinementLengthYears().equals("")) {
				supervisionOrder.setConfinementLengthYears(new Integer(sof.getConfinementLengthYears()).intValue());
			} else {
				supervisionOrder.setConfinementLengthYears(0);
			}
			supervisionOrder.setDispositionTypeId(sof.getDispositionTypeId());
			if (sof.getSupervisionLengthDays() != null && !sof.getSupervisionLengthDays().equals("")) {
				supervisionOrder.setSupervisionLengthDays(new Integer(sof.getSupervisionLengthDays()).intValue());
			} else {
				supervisionOrder.setSupervisionLengthDays(0);
			}
			if (sof.getSupervisionLengthMonths() != null && !sof.getSupervisionLengthMonths().equals("")) {
				supervisionOrder.setSupervisionLengthMonths(new Integer(sof.getSupervisionLengthMonths()).intValue());
			} else {
				supervisionOrder.setSupervisionLengthMonths(0);
			}
			if (sof.getSupervisionLengthYears() != null && !sof.getSupervisionLengthYears().equals("")) {
				supervisionOrder.setSupervisionLengthYears(new Integer(sof.getSupervisionLengthYears()).intValue());
			} else {
				supervisionOrder.setSupervisionLengthYears(0);
			}
			//dag 01/19/07 end

		}
		return supervisionOrder;
	}

	public static String validateConditionValues(Collection conditions,
			boolean refMustBeSet) {
		if (conditions != null) {
			Iterator condIter = conditions.iterator();
			while (condIter.hasNext()) {
				ConditionDetailResponseEvent cdre = (ConditionDetailResponseEvent) condIter.next();
				cdre.setConditionEmpty(false);
				if (cdre.isSpecialCondition()) {
					// ignore as there are no values to set
				} else {
					Collection varElements = cdre.getVariableElements();
					if (varElements != null && varElements.size() > 0) {
						Iterator iter = varElements.iterator();
						while (iter.hasNext()) {
							VariableElementResponseEvent varElement = (VariableElementResponseEvent) iter
									.next();
							if (varElement != null) {
								if (varElement.isReference() && !refMustBeSet) {
									//ignore
								} else {
									if (varElement.isEnumeration() &&
									   (varElement.getValueId() == null || varElement.getValueId().trim().equals(""))) {
										return varElement.getName();
									} else if (!varElement.isEnumeration()
											&& (varElement.getValue() == null || varElement.getValue().trim().equals(""))) {
										if ((!varElement.getName().equals(UIConstants.VARIABLE_ELEMENT_NAME_COMMENTS)) && (!varElement.getName().equals(UIConstants.VARIABLE_ELEMENT_NAME_CLAIMAINT_ADDRESS))) {
											cdre.setConditionEmpty(true);
											return varElement.getName();
										} else {
											cdre.setConditionEmpty(false);
										}
									}
								}
							}
						}
					}
				}
			}
		}

		return null;
	}
	public static boolean validateConditionValues(ConditionDetailResponseEvent cdre,
			boolean refMustBeSet) {
		boolean hasEmptyVars = false;
		if (cdre != null) {
			cdre.setConditionEmpty(false);
				if (cdre.isSpecialCondition()) {
					// ignore as there are no values to set
				} else {
					Collection varElements = cdre.getVariableElements();
					if (varElements != null && varElements.size() > 0) {
						Iterator iter = varElements.iterator();
						while (iter.hasNext()) {
							VariableElementResponseEvent varElement = (VariableElementResponseEvent) iter.next();
							if (varElement != null) {
								if (varElement.isReference() && !refMustBeSet) {
									//ignore
								} else {
									if (varElement.isEnumeration()&&
									   (varElement.getValueId() == null || varElement.getValueId().trim().equals(""))) {
										hasEmptyVars = true;
										break;
									} else if (!varElement.isEnumeration() &&
											  (varElement.getValue() == null || varElement.getValue().trim().equals(""))) {
										      if ((!varElement.getName().equals(UIConstants.VARIABLE_ELEMENT_NAME_COMMENTS)) && (!varElement.getName().equals(UIConstants.VARIABLE_ELEMENT_NAME_CLAIMAINT_ADDRESS))) {
											     cdre.setConditionEmpty(true);
											     hasEmptyVars = true;
											     break;
										      } 
									} 
								}
							}
						}
					}
				}
			} 
		return hasEmptyVars;
	}
	public static boolean validateConditionValues(Map values) {

		boolean result = true;
		Set keys = values.keySet();
		if (keys != null) {
			for (Iterator iter = keys.iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String value = (String) values.get(key);
				if (value == null || value.equals("")) {
					result = false;
					break;
				}
			}
		}
		return result;
	}
	public static SupervisionStaffResponseEvent getSupervisionStaffByName(Collection staff, String lName, String fName){
        SupervisionStaffResponseEvent ssre = null;
        if (staff != null 
        		&& lName != null && !lName.equals(BLANK) 
        		&& fName != null && !fName.equals(BLANK)) {
            List staffList = CollectionUtil.iteratorToList(staff.iterator());

            for (int i = 0; i < staffList.size(); i++) {
                ssre = (SupervisionStaffResponseEvent) staffList.get(i);
                if (ssre.getLastName().equals(lName) && ssre.getFirstName().equals(fName)) {
                    break;
                } else {
                    ssre = null;
                }
            }
        }
        return ssre;

	}
	public static SupervisionStaffResponseEvent getSupervisionStaff(Collection staff) {
	        SupervisionStaffResponseEvent ssre = null;
	        IUserInfo userInfo = SecurityUIHelper.getUser();
	        String userId = userInfo.getJIMSLogonId();
	        if (staff != null && userId != null) {
	            Iterator iter = staff.iterator();

	            while (iter.hasNext()) {
	                ssre = (SupervisionStaffResponseEvent) iter.next();
	                if (ssre.getLogonId().equals(userId)) {
	                    break;
	                } else {
	                    ssre = null;
	                }
	            }
	        }
	        return ssre;
    }
    /**
     * @param judgeList
     * @param courtId
     * @return
     */
    public static JudgeResponseEvent getJudge(Collection judgeList, String courtId) {
        JudgeResponseEvent jre = null;
        if (judgeList != null) {
            Iterator iter = judgeList.iterator();

            while (iter.hasNext()) {
                jre = (JudgeResponseEvent) iter.next();
                if (jre.getCourtId().equals(courtId)) {
                    break;
                } else {
                	jre = null;
                }
            }
        }
        return jre;
    }
    public static JudgeResponseEvent getJudgeByName(Collection judgeList, String lName, String fName){
        JudgeResponseEvent jre = null;
        if (judgeList != null 
        		&& lName != null && !lName.equals(BLANK)
        		&& fName != null && !fName.equals(BLANK)) {
            List aList = CollectionUtil.iteratorToList(judgeList.iterator());

            for (int i = 0; i < aList.size(); i++) {
                jre = (JudgeResponseEvent) aList.get(i);
                if (jre.getFirstName().equals(fName)
                		&& jre.getLastName().equals(lName)) {
                    break;
                } else {
                	jre = null;
                }
            }
        }
        return jre;   	
    }
	/**
	 *  
	 */
	private UISupervisionOrderHelper() {
		super();
	}
	private static Name getPresentorName(Collection presentors, String presentorId)
	{
		Name name = new Name();
		Iterator iter = presentors.iterator();
		while (iter.hasNext())
		{
			SupervisionStaffResponseEvent re = (SupervisionStaffResponseEvent) iter.next();
			if (re.getSupervisionStaffId().equals(presentorId))
			{
				name.setFirstName(re.getFirstName());
				name.setMiddleName(re.getMiddleName());
				name.setLastName(re.getLastName());
				break;
			}
		}
		return name;
	}
	/**
	 * @param judges
	 * @param courtNum
	 * @return
	 */
	private static Name getJudgeName(Collection <JudgeResponseEvent> judges, String courtNum) {
		Name name = new Name();
		
		if (judges != null) {
			for (JudgeResponseEvent re : judges) {

				if (re.getCourtId().equals(courtNum)) {
					name.setFirstName(re.getFirstName());
					name.setMiddleName("");
					name.setLastName(re.getLastName());
					break;
				}
			}
		}
		return name;
	}
	public static void setPresentorAndJudgeInfo(SupervisionOrderForm sof){
		Name name = null;

		if (sof.getPresentedById() != null && !sof.getPresentedById().equals(OTHER))
		{
			name = getPresentorName(sof.getPresentedByList(), sof.getPresentedById());
			sof.setPresentedByFirstName(name.getFirstName());
			sof.setPresentedByLastName(name.getLastName());
			sof.setPresentedBy(name.getFormattedName());
		}
		else
		{
			name = new Name(sof.getPresentedByFirstName(), BLANK, sof.getPresentedByLastName());
			sof.setPresentedBy(name.getFormattedName());
		}
		if (sof.getJudgeSelectId() != null && sof.getJudgeSelectId().equals(OTHER))
		{
			name = new Name(sof.getJudgeFirstName(), BLANK, sof.getJudgeLastName());
			sof.setWhoSignedOrder(name.getFormattedName());
		}
		else
			if (sof.getJudgeSelectId() != null && !sof.getJudgeSelectId().equals(BLANK))
			{
				name = getJudgeName(sof.getJudgeSelectList(), sof.getJudgeSelectId());
				sof.setJudgeFirstName(name.getFirstName());
				sof.setJudgeLastName(name.getLastName());
				sof.setWhoSignedOrder(name.getFormattedName());
			}
		if (sof.getMagistrateSelectId() != null && sof.getMagistrateSelectId().equals(OTHER))
		{
			name = new Name(sof.getMagFirstName(), BLANK, sof.getMagLastName());
			sof.setWhoSignedOrder(name.getFormattedName());
		}
		else
			if (sof.getMagistrateSelectId() != null && !sof.getMagistrateSelectId().equals(BLANK))
			{
				name = getMagistrateName(sof.getMagistrateSelectList(), sof.getMagistrateSelectId());
				sof.setMagFirstName(name.getFirstName());
				sof.setMagLastName(name.getLastName());
				sof.setWhoSignedOrder(name.getFormattedName());
			}		
	}
	private static String OTHER = "OTHER";
	private static String BLANK = "";

	/**
	 * @param magistrates
	 * @param magistrateId
	 * @return
	 */
	private static Name getMagistrateName(Collection magistrates, String magistrateId)
	{
		Name name = new Name();
		Iterator iter = magistrates.iterator();
		while (iter.hasNext())
		{
			MagistrateResponseEvent re = (MagistrateResponseEvent) iter.next();
			if (re.getMagistrateId().equals(magistrateId))
			{
				name.setFirstName(re.getFirstName());
				name.setMiddleName(re.getMiddleName());
				name.setLastName(re.getLastName());
				break;
			}
		}
		return name;
	}

    public static void setPresentedByInfo(SupervisionOrderForm sof) {
    	SupervisionStaffResponseEvent ssre = null;
    	if (sof.getPresentedByLastName() != null 
    			&& !sof.getPresentedByLastName().equals(BLANK)){
    		
    		ssre = UISupervisionOrderHelper.getSupervisionStaffByName(sof.getPresentedByList(), sof.getPresentedByLastName(), sof.getPresentedByFirstName());
    		if (ssre != null){
    			sof.setPresentedById(ssre.getSupervisionStaffId());
    		} else {
    			sof.setPresentedById(OTHER);
    		}
    	} else {
    		ssre = UISupervisionOrderHelper.getSupervisionStaff(sof.getPresentedByList());
    	
    		if (ssre != null) {
    			sof.setPresentedById(ssre.getSupervisionStaffId());
    		}
    	}
    	JudgeResponseEvent jre = null;
    	if (sof.getJudgeLastName() != null && !sof.getJudgeLastName().equals(BLANK)){
    		jre = UISupervisionOrderHelper.getJudgeByName(sof.getJudgeSelectList(), sof.getJudgeLastName(), sof.getJudgeFirstName());
    		if (jre != null){
    			sof.setJudgeSelectId(jre.getCourtId());
    		} else {
    			sof.setJudgeSelectId(OTHER);
    		}
    	} else {
    		jre = UISupervisionOrderHelper.getJudge(sof.getJudgeSelectList(), sof.getCourtId());
    		if (jre != null) {
    			sof.setJudgeSelectId(jre.getCourtId());
    		}
    	}
	}
    
    /**
	 * reformats supervisee name for default printed name
	 * @param name
	 * @return printedName
	 */
	public static String parsePrintedName(String name)
	{
		String aName = null;
        String lastName=null;
        String firstName=null;
        String middleName=null;
        String printedName = null;
		int pos = name.indexOf(",");
		if (pos > 0) {
			aName = name.substring(0, pos);
			lastName=aName.trim();
		}
		String nameString = null;
		if (pos + 2 <= name.length()) {
			nameString = name.substring(pos + 2);
			pos = nameString.indexOf(" ");
			aName = nameString.substring(0, pos);
			firstName=aName.trim();			
		}
		if (pos + 2 <= nameString.length()) {
			aName = nameString.substring(pos + 1);
			middleName=aName.trim();			
		}
		printedName = firstName + " " + middleName + " " + lastName;
		return printedName;
	}
	
	private static String replaceBRTags( String badComments ){
		
    	
    	String formattedStr = "";
    	
    	if ( badComments != "" ){
    		
    		formattedStr = badComments.replaceAll( "<br>", "" ).replaceAll("</br>", "").replaceAll("<br />", "")
    								.replaceAll("&#60;", "<").replaceAll("&#62;", ">").replaceAll("\\\\+", "&#92;&#92;");
    	}
    	return formattedStr;
    }
	
	/**
	 * Used to refresh the OOC case court
	 * @param caseId
	 * @param defendant_id
	 * @return
	 */
	private static String getOutOfCountyCaseCourt( String caseId, String defendant_id )
	{
		
		String court = "";
		// post the request to PD
		GetOutOfCountyCasesEvent request = (GetOutOfCountyCasesEvent) EventFactory
				.getInstance(OutOfCountyCaseControllerServiceNames.GETOUTOFCOUNTYCASES);
		// set the search criteria
		request.setSpn( defendant_id );
		request.setUserAgencyId( SecurityUIHelper.getUserAgencyId() );
		request.setCaseNum( caseId );

		CaseListResponseEvent caseResp = (CaseListResponseEvent) MessageUtil.postRequest( request, CaseListResponseEvent.class );
		
		if ( caseResp != null ){
			
			List cases = (List) caseResp.getCases();
			
			for ( int x =0; x < cases.size();x++ ){
				
				OutOfCountyCaseTO obj = (OutOfCountyCaseTO) cases.get(x);
				court = obj.getCourtNum();
				
			}
		}
		return court;
	}
	
	/**
	 * format the court number to be 3 digits
	 * @param originalCourtId
	 * @return
	 */
	private static String formatCourtNumber( String originalCourtId)
	{
		String courtNumber = "";
		if(originalCourtId == null){
			originalCourtId = "";
		}
		StringBuffer padCrt = null;
		if(originalCourtId.length() < 3){
			padCrt = new StringBuffer(originalCourtId);
	    	while (padCrt.length() < 3){
	    		padCrt.insert(0, "0");
	    	}
		}else if(originalCourtId.length() > 3){
			padCrt = new StringBuffer(originalCourtId);
	    	while (padCrt.length() > 3 && padCrt.substring(0, 1).equals("0")){
	    		padCrt = padCrt.delete(0, 1);
	    	}
		}else{
			padCrt = new StringBuffer(originalCourtId);
		}
		courtNumber = padCrt.toString();
		return courtNumber;
	}
}