/*
 * Created on Sep 13, 2005
 *
 */
package pd.supervision.supervisionoptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import naming.PDConstants;

import pd.codetable.Code;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionOrderHelper;

import messaging.supervisionoptions.reply.VariableElementResponseEvent;
import mojo.km.utilities.DateUtil;

/**
 * @author bschwartz
 *  
 */
public class VariableElementHelper {

    public static boolean saveCourtPolicyVariableElements(CourtPolicy courtPolicy, Collection passedVariableElements, Class optionType) {
        boolean courtsRemoved = false;

        // Populate a collection of events with the existing entity courts
        // selection.
        // Performance change: use db view to get to variable elements
        Collection existingVarElements = createCourtPolicyVariableElementResponses(courtPolicy);
        courtsRemoved = saveVariableElements(existingVarElements, passedVariableElements, courtPolicy.getPolicySupervisionOptions(), optionType);
        
        return courtsRemoved;
    }
    
    public static boolean saveConditionVariableElements(Condition condition, Collection passedVariableElements, Class optionType) {
        boolean courtsRemoved = false;

        // Populate a collection of events with the existing entity courts
        // selection.
        // Performance change: use db view to get to variable elements
        List existingVarElements = createConditionVariableElementResponses(condition);
        courtsRemoved = saveVariableElements(existingVarElements, passedVariableElements, condition.getConditionSupervisionOptions(),  optionType);
        
        return courtsRemoved;
    }
    
    /**
     * 
     * @param options
     *            The entities collection of CourtSupervisionOption's.
     * @param responses
     *            A collection of VariableElementResponseEvent's from the UI.
     */
    private static boolean saveVariableElements(Collection existingVarElements, Collection passedVariableElements, Collection currentSupOptions, Class optionType) {
        boolean courtsRemoved = false;

        //----------------------------------------------
        // Setup the four working sets.

        // Existing standard courts
        StringSet stdCourts = stdCourtSet(existingVarElements);

        // Existing exception courts
        StringSet expCourts = expCourtSet(existingVarElements);

        // Changed standard courts
        StringSet stdCourtsChanges = stdCourtSet(passedVariableElements);

        // Changed exception courts
        StringSet expCourtsChanges = expCourtSet(passedVariableElements);

        //----------------------------------------------
        // Translate to the differnce sets we neeed to modify the original sets.

        // A set of courts that have been moved from the standard set to the
        // exception set.
        Set exceptedCourts = stdCourts.intersection(expCourtsChanges);

        // A set of courts that have been moved from the exception set to the
        // standard set.
        Set unexceptedCourts = expCourts.intersection(stdCourtsChanges);

        // New court selections
        Set newStdCourts = stdCourtsChanges.complement(stdCourts.union(expCourts));

        // New exception court selections
        Set newExpCourts = expCourtsChanges.complement(stdCourts.union(expCourts));

        // All deleted court selections
        Set deletedCourts = stdCourts.union(expCourts).complement(stdCourtsChanges.union(expCourtsChanges));

        // Index existing courts (CourtSupervisionOption)
        Map courtOptionsMap = new HashMap();
//        Collection currentSupOptions = condition.getConditionSupervisionOptions();
        Iterator optIter = currentSupOptions.iterator();
        while (optIter.hasNext()) {
            CourtSupervisionOption opt = (CourtSupervisionOption) optIter.next();
            courtOptionsMap.put(opt.getCourtId(), opt);
        }

        // Handle courts that have been removed
        Iterator crtIter = deletedCourts.iterator();
        while (crtIter.hasNext()) {
            String courtId = (String) crtIter.next();
            CourtSupervisionOption sel = (CourtSupervisionOption) courtOptionsMap.remove(courtId);
            currentSupOptions.remove(sel);
            courtsRemoved = true;
        }

        // Handle courts that have been excepted
        crtIter = exceptedCourts.iterator();
        while (crtIter.hasNext()) {
            String courtId = (String) crtIter.next();
            CourtSupervisionOption opt = (CourtSupervisionOption) courtOptionsMap.get(courtId);
            opt.setIsExceptionCourt(true);
//            opt.clearVariableElements();
        }

        // Handle courts that have been unexcepted
        crtIter = unexceptedCourts.iterator();
        while (crtIter.hasNext()) {
            String courtId = (String) crtIter.next();
            CourtSupervisionOption opt = (CourtSupervisionOption) courtOptionsMap.get(courtId);
            opt.setIsExceptionCourt(false);
//            opt.clearVariableElements();
        }

        // Handle new standard courts
        crtIter = newStdCourts.iterator();
        while (crtIter.hasNext()) {
            String courtId = (String) crtIter.next();
            try {
                CourtSupervisionOption opt = (CourtSupervisionOption) optionType.newInstance();
                opt.setCourtId(courtId);
                opt.setIsExceptionCourt(false);
                courtOptionsMap.put(courtId, opt);
                currentSupOptions.add(opt);
            } catch (Exception ex) {
                //IllegalAccessException
                //InstantiationException
                throw new RuntimeException(ex.getMessage());
            }
        }

        // Handle new exception courts
        crtIter = newExpCourts.iterator();
        while (crtIter.hasNext()) {
            String courtId = (String) crtIter.next();
            try {
                CourtSupervisionOption opt = (CourtSupervisionOption) optionType.newInstance();
                opt.setCourtId(courtId);
                opt.setIsExceptionCourt(true);
                courtOptionsMap.put(courtId, opt);
                currentSupOptions.add(opt);
            } catch (Exception ex) {
                //IllegalAccessException
                //InstantiationException
                throw new RuntimeException(ex.getMessage());
            }
        }

//        updateVariableElements(currentSupOptions, varElemEventMap, typeIds);
        updateVariableElements(currentSupOptions, passedVariableElements);

        return courtsRemoved;
    }

    private static void updateVariableElements(Collection courtSupervisionOptions, Collection passedVariableElements) {
        
        // Index VariableElementResponseEvents and determine typeIds.
        Map varElemEventMap = new HashMap();
        Set typeIds = new HashSet();
        for(Iterator varElemIter = passedVariableElements.iterator(); varElemIter.hasNext(); ) {
            VariableElementResponseEvent evt = (VariableElementResponseEvent) varElemIter.next();
            varElemEventMap.put(evt.getCourtId() + "/" + evt.getVariableElementTypeId(), evt);
            if (evt.getVariableElementTypeId() != null) {
                typeIds.add(evt.getVariableElementTypeId());
            }
        }

        for(Iterator opts = courtSupervisionOptions.iterator(); opts.hasNext(); ) {
            CourtSupervisionOption opt = (CourtSupervisionOption) opts.next();
            // Index VariableElements and remove the deleted ones.
            Map varElemMap = new HashMap();
            Iterator varElemIter = opt.getVariableElements().iterator();
            List removedVarElems = new ArrayList();
            
            while (varElemIter.hasNext()) {
                VariableElement elem = (VariableElement) varElemIter.next();

                if (!typeIds.contains(elem.getVariableElementTypeId())) {
                	removedVarElems.add(elem);
                    varElemIter.remove();
                } else {
                    varElemMap.put(elem.getVariableElementTypeId(), elem);
                }
            }

            if (opt.getIsExceptionCourt()) {
                // Update values and make additions
                Iterator varElemIdIter = typeIds.iterator();
                while (varElemIdIter.hasNext()) {
                    String id = (String) varElemIdIter.next();
                    VariableElement varElem = (VariableElement) varElemMap.get(id);
                    if (varElem == null) {
                        varElem = new VariableElement();
                        varElem.setVariableElementTypeId(id);
                        // set value/ValueId based on isEnumerated flag
                        VariableElementResponseEvent evt = (VariableElementResponseEvent) varElemEventMap.get(opt.getCourtId() + "/"
                                + varElem.getVariableElementTypeId());
                        VariableElementType veType = varElem.getVariableElementType();
                        if (veType.isEnumeration()) {
                            varElem.setValue(evt.getValueId());
                        } else {
                            varElem.setValue(evt.getValue());
                        }
                        if (veType.isReference()){
                        	varElem.setIsFixed(true);
                        	if (!veType.isEnumeration()){
                        		varElem.setValue(PDConstants.BLANK);
                        	}
                        } else {
                        	varElem.setIsFixed(evt.isFixed());
                        }
                        opt.insertVariableElements(varElem);
                    } else {//value and isFixed may need to be updated
                        VariableElementResponseEvent evt = (VariableElementResponseEvent) varElemEventMap.get(opt.getCourtId() + "/"
                                + varElem.getVariableElementTypeId());
                        VariableElementType veType = varElem.getVariableElementType();
                        if (veType.isEnumeration()) {
                            varElem.setValue(evt.getValueId());
                        } else {
                            varElem.setValue(evt.getValue());
                        }
                        if (veType.isReference()){
                        	varElem.setIsFixed(true);
                        	if (!veType.isEnumeration()){
                        		varElem.setValue(PDConstants.BLANK);
                        	}
                        } else {
                        	varElem.setIsFixed(evt.isFixed());
                        }
                    }
               }
            } else {
                // Setup standard courts one time

                // Update values and make additions
                Iterator varElemIdIter = typeIds.iterator();
                while (varElemIdIter.hasNext()) {
                    String id = (String) varElemIdIter.next();
                    VariableElement varElem = (VariableElement) varElemMap.get(id);
                    if (varElem == null) {
                        varElem = new VariableElement();
                        varElem.setVariableElementTypeId(id);
                        VariableElementResponseEvent evt = (VariableElementResponseEvent) varElemEventMap.get(opt.getCourtId() + "/"
                                + varElem.getVariableElementTypeId());

                        // set value/ValueId based on isEnumerated flag
                        //note to dawn - i think this code should be moved up before insertVariableElements(varElem)
                        VariableElementType veType = varElem.getVariableElementType();
                        if (veType.isEnumeration()) {
                            varElem.setValue(evt.getValueId());
                        } else {
                            varElem.setValue(evt.getValue());
                        }
                        if (veType.isReference()){
                        	varElem.setIsFixed(true);
                        	if (!veType.isEnumeration()){
                        		varElem.setValue(PDConstants.BLANK);
                        	}
                        } else {
                        	varElem.setIsFixed(evt.isFixed());
                        }
                        opt.insertVariableElements(varElem);
                    } else {//value and isFixed may need to be updated
                        VariableElementResponseEvent evt = (VariableElementResponseEvent) varElemEventMap.get(opt.getCourtId() + "/"
                                + varElem.getVariableElementTypeId());
                        VariableElementType veType = varElem.getVariableElementType();
                        if (veType.isEnumeration()) {
                            varElem.setValue(evt.getValueId());
                        } else {
                            varElem.setValue(evt.getValue());
                        }
                        if (veType.isReference()){
                        	varElem.setIsFixed(true);
                        	if (!veType.isEnumeration()){
                        		varElem.setValue(PDConstants.BLANK);
                        	}
                        } else {
                        	varElem.setIsFixed(evt.isFixed());
                        }
                    }
                }
            } 
            //Remove Variable elements that no longer exist in the condition.
            for (int i = 0; i < removedVarElems.size(); i++) {
				VariableElement varElem = (VariableElement) removedVarElems.get(i);
				opt.removeVariableElements(varElem);
			}
        }
    }
    
    public static List createConditionVariableElementResponses(Condition condition) {
        // Performance change: use db view to get to variable elements
        Map courtOptionVariableElementMap = getConditionVariableElements(condition.getOID().toString(), condition.getAgencyId());
        List responses = createVariableElementResponses(courtOptionVariableElementMap);
        return responses;
    }

    public static List createCourtPolicyVariableElementResponses(CourtPolicy courtPolicy) {
        // Performance change: use db view to get to variable elements
        Map courtOptionVariableElementMap = getCourtPolicyVariableElements(courtPolicy.getOID().toString(), courtPolicy.getAgencyId());
        List responses = createVariableElementResponses(courtOptionVariableElementMap);
        return responses;
    }
    
    private static List createVariableElementResponses(Map courtOptionVariableElementMap) {
        List responses = new ArrayList();
        Set supOptionIds = courtOptionVariableElementMap.keySet();
        for (Iterator iter = supOptionIds.iterator(); iter.hasNext();) {
            String supOptionId = (String) iter.next();
            ArrayList conditionCourtVariableElements = (ArrayList) courtOptionVariableElementMap.get(supOptionId);
            for (Iterator varElementsIter = conditionCourtVariableElements.iterator(); varElementsIter.hasNext();) {
                ConditionCourtVariableElement conditionCourtVariableElement = (ConditionCourtVariableElement) varElementsIter.next();
                if (conditionCourtVariableElement.getVariableElementId() == null) {
                    // create an empty VariableElementResponseEvent for
                    // conditions which have no variable elements
                    VariableElementResponseEvent varElemEvent = new VariableElementResponseEvent();
                    varElemEvent.setCourtId(conditionCourtVariableElement.getCourtId());
                    varElemEvent.setExceptionCourt(conditionCourtVariableElement.getIsExceptionCourt());
                    responses.add(varElemEvent);
                } else {
                    VariableElementResponseEvent varElemEvent = new VariableElementResponseEvent();
                    varElemEvent.setCourtId(conditionCourtVariableElement.getCourtId());
                    varElemEvent.setExceptionCourt(conditionCourtVariableElement.getIsExceptionCourt());
                    populateVariableElemResponseEvent(conditionCourtVariableElement, varElemEvent);
                    responses.add(varElemEvent);
                }

            }
        }
        return responses;
    }

    public static void populateVariableElemResponseEvent(ConditionCourtVariableElement conditionCourtVariableElement, VariableElementResponseEvent varElemEvent) {
        varElemEvent.setVariableElementTypeId(conditionCourtVariableElement.getVariableElementTypeId());
        varElemEvent.setVariableElementId(conditionCourtVariableElement.getVariableElementId());
        varElemEvent.setVariableElementTypeId(conditionCourtVariableElement.getVariableElementTypeId());
        varElemEvent.setFixed(conditionCourtVariableElement.getIsFixed());
        varElemEvent.setName(conditionCourtVariableElement.getName());
        if (conditionCourtVariableElement.getIsEnumeration()) {
            varElemEvent.setEnumeration(true);
            // if type is enumaration, get codetable name
            varElemEvent.setCodeTableName(conditionCourtVariableElement.getElementCodeTableId());
            // if it is of type enumeration, its the code that we store
            String valueId = conditionCourtVariableElement.getValue();
            if (valueId != null && !valueId.equals("")) {
                varElemEvent.setValueId(valueId);
                Code valueCode = Code.find(varElemEvent.getCodeTableName(), valueId);
                if (valueCode !=  null){
                	varElemEvent.setValue(valueCode.getDescription());
                } else {
                	varElemEvent.setValue(null);
                }
            }
        } else {
            varElemEvent.setValue(conditionCourtVariableElement.getValue());
        }
        varElemEvent.setValueType(conditionCourtVariableElement.getType());
        varElemEvent.setEnumerationTypeId(conditionCourtVariableElement.getEnumerationTypeId());
        varElemEvent.setReference(conditionCourtVariableElement.getIsReference());
    }

    static public Map getConditionVariableElements(String conditionId, String agencyId) {
        Map optionVarElementMap = new HashMap();
        Iterator conditionCourtVariableElements = ConditionCourtVariableElement.findAllByCondition(conditionId, agencyId);
        while (conditionCourtVariableElements.hasNext()) {
            ConditionCourtVariableElement conditionCourtVariableElement = (ConditionCourtVariableElement) conditionCourtVariableElements.next();
            ArrayList varElements = (ArrayList) optionVarElementMap.get(conditionCourtVariableElement.getCourtSupOptionId());
            if (varElements == null) {
                varElements = new ArrayList();
                optionVarElementMap.put(conditionCourtVariableElement.getCourtSupOptionId(), varElements);
            }
            varElements.add(conditionCourtVariableElement);
        }
        return optionVarElementMap;
    }

    static public Map getCourtPolicyVariableElements(String courtPolicyId, String agencyId) {
        Map optionVarElementMap = new HashMap();
        Iterator courtPolicyCourtVariableElements = ConditionCourtVariableElement.findAllByCourtPolicy(courtPolicyId, agencyId);
        while (courtPolicyCourtVariableElements.hasNext()) {
            ConditionCourtVariableElement conditionCourtVariableElement = (ConditionCourtVariableElement)courtPolicyCourtVariableElements.next();
            ArrayList varElements = (ArrayList)optionVarElementMap.get(conditionCourtVariableElement.getCourtSupOptionId());
            if (varElements == null) {
                varElements = new ArrayList();
                optionVarElementMap.put(conditionCourtVariableElement.getCourtSupOptionId(), varElements);
            }
            varElements.add(conditionCourtVariableElement);
        }
        return optionVarElementMap;
    }
    
    public static void populateVariableElemResponseEvent(VariableElement varElem, VariableElementResponseEvent varElemEvent, Map referenceVariableMap) {
        if (referenceVariableMap == null) {
            referenceVariableMap = new HashMap();
        }
        varElemEvent.setVariableElementTypeId(varElem.getVariableElementTypeId());
        varElemEvent.setVariableElementId(varElem.getOID().toString());
        varElemEvent.setVariableElementTypeId(varElem.getVariableElementTypeId());
        varElemEvent.setFixed(varElem.getIsFixed());

        VariableElementType veType = varElem.getVariableElementType();
        if (veType != null) {
            varElemEvent.setName(veType.getName());
            if (veType.isEnumeration() && !veType.getIsCalculated()) {
                varElemEvent.setEnumeration(true);
                // if type is enumaration ; get codetable name
                //				VariableElementTypeEnumerationInfo enumerationInfo =
                // veType.getVariableElementTypeCodeTable();
                //				varElemEvent.setCodeTableName(enumerationInfo.getElementCodeTableId());
                //				varElemEvent.setEnumerationTypeId(enumerationInfo.getEnumerationTypeId());
                varElemEvent.setCodeTableName(veType.getElementCodeTableId());
                // if it is of type enumeration, its the code that we store
                String valueId = varElem.getValue();
                //122107-Dawn added so that drop-down will have selected value if it exists in ref var.
                if (veType.isReference() && varElem.getIsFixed()){
                    valueId = (String) referenceVariableMap.get(veType.getName());
                }
                if (valueId != null && !valueId.equals("")) {
                    varElemEvent.setValueId(valueId);
                    Code valueCode = Code.find(varElemEvent.getCodeTableName(), valueId);
                    varElemEvent.setValue(valueCode.getDescription());
                }
            } else if (veType.isReference() && varElem.getIsFixed()) { 
                String value = (String) referenceVariableMap.get(veType.getName()); 
                varElemEvent.setValue(value);
            } else {
                varElemEvent.setValue(varElem.getValue());
            }
            varElemEvent.setValueType(veType.getType());
            varElemEvent.setEnumerationTypeId(veType.getEnumerationTypeId());
            varElemEvent.setReference(veType.isReference());
        }
    }
    public static void populateVariableElemResponseEvent(SupervisionOrder order, VariableElement varElem, VariableElementResponseEvent varElemEvent, Map referenceVariableMap) {
        if (referenceVariableMap == null) {
            referenceVariableMap = new HashMap();
        }
        varElemEvent.setVariableElementTypeId(varElem.getVariableElementTypeId());
        varElemEvent.setVariableElementId(varElem.getOID().toString());
        varElemEvent.setVariableElementTypeId(varElem.getVariableElementTypeId());
        varElemEvent.setFixed(varElem.getIsFixed());

        VariableElementType veType = varElem.getVariableElementType();
        if (veType != null) {
            varElemEvent.setName(veType.getName());
            if (veType.isEnumeration() && !veType.getIsCalculated()) {
                varElemEvent.setEnumeration(true);
                // if type is enumaration ; get codetable name
                //				VariableElementTypeEnumerationInfo enumerationInfo =
                // veType.getVariableElementTypeCodeTable();
                //				varElemEvent.setCodeTableName(enumerationInfo.getElementCodeTableId());
                //				varElemEvent.setEnumerationTypeId(enumerationInfo.getEnumerationTypeId());
                varElemEvent.setCodeTableName(veType.getElementCodeTableId());
                // if it is of type enumeration, its the code that we store
                String valueId = varElem.getValue();
                //122107-Dawn added so that drop-down will have selected value if it exists in ref var.
                if (veType.isReference() && varElem.getIsFixed()){
                    valueId = (String) referenceVariableMap.get(veType.getName());
                }
                if (valueId != null && !valueId.equals("")) {
                    varElemEvent.setValueId(valueId);
                    Code valueCode = Code.find(varElemEvent.getCodeTableName(), valueId);
                    varElemEvent.setValue(valueCode.getDescription());
                }
            } else if (veType.isReference() && varElem.getIsFixed()) { 
                String value = (String) referenceVariableMap.get(veType.getName()); 
                varElemEvent.setValue(value);
            } else {
                varElemEvent.setValue(varElem.getValue());
            }
            if (veType.getIsCalculated()){
            	
    			Code aCode = Code.find(veType.getElementCodeTableId(), varElem.getValue());
    			String dateString = null;
    			if (aCode != null){
    				dateString = SupervisionOrderHelper.calculateDate(order.getCaseSupervisionBeginDate(), aCode.getCode());
    			} else {
    				dateString = DateUtil.dateToString(order.getCaseSupervisionBeginDate(), DateUtil.DATE_FMT_1);
    			}
    			varElemEvent.setEnumerationTypeId(veType.getDataType());
    			varElemEvent.setValue(dateString);
            } else {
            	varElemEvent.setEnumerationTypeId(veType.getEnumerationTypeId());
            }
            varElemEvent.setValueType(veType.getType());
            //varElemEvent.setEnumerationTypeId(veType.getEnumerationTypeId());
            varElemEvent.setReference(veType.isReference());
        }
    }
    public static void populateVariableElemResponseEvent(VariableElementResponseEvent varElemEvent, ConditionCourtVariableElement condCrtVarElement,
            Map referenceVariableMap) {
        if (referenceVariableMap == null) {
            referenceVariableMap = new HashMap();
        }
        varElemEvent.setVariableElementTypeId(condCrtVarElement.getVariableElementTypeId());
        varElemEvent.setVariableElementId(condCrtVarElement.getVariableElementId());
        varElemEvent.setFixed(condCrtVarElement.getIsFixed());
        varElemEvent.setName(condCrtVarElement.getName());
        varElemEvent.setCalculated(condCrtVarElement.getIsCalculated());
        varElemEvent.setDataType(condCrtVarElement.getDataType());
        if (condCrtVarElement.getIsEnumeration() && !condCrtVarElement.getIsCalculated()) {
            varElemEvent.setEnumeration(true);
            varElemEvent.setCodeTableName(condCrtVarElement.getElementCodeTableId());
            // if it is of type enumeration, its the code that we store
            String valueId = null;
            if (condCrtVarElement.getIsReference()){
                valueId = (String) referenceVariableMap.get(condCrtVarElement.getName());
                //2008-07-22 DAG is this going to work if value of VE is changed and should be FIXED?
                //varElemEvent.setFixed(false);
             } else {
                valueId = condCrtVarElement.getValue();
            }
            if (valueId != null && !valueId.equals("") && !condCrtVarElement.getIsCalculated()) {
                varElemEvent.setValueId(valueId);
                Code valueCode = Code.find(varElemEvent.getCodeTableName(), valueId);
                varElemEvent.setValue(valueCode.getDescription());
            }
        //12/29/06 this method is called on the create only, so no need to check isfixed for ref vars.
        //} else if (condCrtVarElement.getIsReference() && condCrtVarElement.getIsFixed()) {     
        } else if (condCrtVarElement.getIsReference()) {
//            String valueId = condCrtVarElement.getValue();
//            if (valueId == null || valueId.equals("")) {
//                valueId = (String) referenceVariableMap.get(condCrtVarElement.getName());
              String valueId = (String) referenceVariableMap.get(condCrtVarElement.getName());
              //2008-07-22 DAG is this going to work if value of VE is changed and should be FIXED?
              //varElemEvent.setFixed(false);
//            }
            varElemEvent.setValue(valueId);
        } else {
            varElemEvent.setValue(condCrtVarElement.getValue());
        }
        if (condCrtVarElement.getIsCalculated()){
        	varElemEvent.setEnumeration(false);
        	varElemEvent.setEnumerationTypeId(condCrtVarElement.getDataType());
        	varElemEvent.setCodeTableName(condCrtVarElement.getElementCodeTableId());
        	varElemEvent.setValue(condCrtVarElement.getValue());
        } else {
        	varElemEvent.setEnumerationTypeId(condCrtVarElement.getEnumerationTypeId());
        }
        varElemEvent.setValueType(condCrtVarElement.getType());
        //varElemEvent.setEnumerationTypeId(condCrtVarElement.getEnumerationTypeId());
        varElemEvent.setReference(condCrtVarElement.getIsReference());
    }
    public static void populateVariableElemResponseEvent(
    		SupervisionOrder order, 
    		VariableElementResponseEvent varElemEvent, 
    		ConditionCourtVariableElement condCrtVarElement,
            Map referenceVariableMap) {
        if (referenceVariableMap == null) {
            referenceVariableMap = new HashMap();
        }
        varElemEvent.setVariableElementTypeId(condCrtVarElement.getVariableElementTypeId());
        varElemEvent.setVariableElementId(condCrtVarElement.getVariableElementId());
        varElemEvent.setFixed(condCrtVarElement.getIsFixed());
        varElemEvent.setName(condCrtVarElement.getName());
        varElemEvent.setCalculated(condCrtVarElement.getIsCalculated());
        varElemEvent.setDataType(condCrtVarElement.getDataType());
        if (condCrtVarElement.getIsEnumeration() && !condCrtVarElement.getIsCalculated()) {
            varElemEvent.setEnumeration(true);
            varElemEvent.setCodeTableName(condCrtVarElement.getElementCodeTableId());
            // if it is of type enumeration, its the code that we store
            String valueId = null;
            if (condCrtVarElement.getIsReference()){
                valueId = (String) referenceVariableMap.get(condCrtVarElement.getName());
                //2008-07-22 DAG is this going to work if value of VE is changed and should be FIXED?
                //varElemEvent.setFixed(false);
             } else {
                valueId = condCrtVarElement.getValue();
            }
            if (valueId != null && !valueId.equals("") && !condCrtVarElement.getIsCalculated()) {
                varElemEvent.setValueId(valueId);
                Code valueCode = Code.find(varElemEvent.getCodeTableName(), valueId);
                varElemEvent.setValue(valueCode.getDescription());
            }
        //12/29/06 this method is called on the create only, so no need to check isfixed for ref vars.
        //} else if (condCrtVarElement.getIsReference() && condCrtVarElement.getIsFixed()) {     
        } else if (condCrtVarElement.getIsReference()) {
//            String valueId = condCrtVarElement.getValue();
//            if (valueId == null || valueId.equals("")) {
//                valueId = (String) referenceVariableMap.get(condCrtVarElement.getName());
              String valueId = (String) referenceVariableMap.get(condCrtVarElement.getName());
              //2008-07-22 DAG is this going to work if value of VE is changed and should be FIXED?
              //varElemEvent.setFixed(false);
//            }
            varElemEvent.setValue(valueId);
        } else {
            varElemEvent.setValue(condCrtVarElement.getValue());
        }
        if (condCrtVarElement.getIsCalculated()){
        	varElemEvent.setEnumeration(false);
        	varElemEvent.setEnumerationTypeId(condCrtVarElement.getDataType());
        	varElemEvent.setCodeTableName(condCrtVarElement.getElementCodeTableId());
        	if (order != null){
        		Code aCode = Code.find(condCrtVarElement.getElementCodeTableId());
        		String dateString = null;
        		if (aCode != null){
        			dateString = SupervisionOrderHelper.calculateDate(order.getCaseSupervisionBeginDate(), aCode.getCode());
        		} else {
        			dateString = DateUtil.dateToString(order.getCaseSupervisionBeginDate(), DateUtil.DATE_FMT_1);
        		}
        		varElemEvent.setValue(dateString);
        	} else {
        		
        	}
        } else {
        	varElemEvent.setEnumerationTypeId(condCrtVarElement.getEnumerationTypeId());
        }
        varElemEvent.setValueType(condCrtVarElement.getType());
        //varElemEvent.setEnumerationTypeId(condCrtVarElement.getEnumerationTypeId());
        varElemEvent.setReference(condCrtVarElement.getIsReference());
    }

    /*
     * Private
     */

    // Create a set of courts (as String) from the
    // VariableElementResponseEvents.
    private static StringSet stdCourtSet(Collection varElems) {
        StringSet set = new StringSet(varElems, new StringSet.Stringizer() {
            public String toString(Object obj) {
                VariableElementResponseEvent varElem = (VariableElementResponseEvent) obj;
                if (!varElem.isExceptionCourt()) {
                    return varElem.getCourtId();
                }
                return null;
            }
        });

        return set;
    }

    // Create a set of courts (as String) from the
    // VariableElementResponseEvents.
    private static StringSet expCourtSet(Collection varElems) {
        StringSet set = new StringSet(varElems, new StringSet.Stringizer() {
            public String toString(Object obj) {
                VariableElementResponseEvent varElem = (VariableElementResponseEvent) obj;
                if (varElem.isExceptionCourt()) {
                    return varElem.getCourtId();
                }
                return null;
            }
        });

        return set;
    }

//  /**
//  * 
//  * @param options
//  *            The entities collection of CourtSupervisionOption's.
//  * @param responses
//  *            A collection of VariableElementResponseEvent's from the UI.
//  */
// public static boolean saveVariableElements(Collection currentSelection, Collection changedSelection, Class optionType) {
//     boolean courtsRemoved = false;
//
//     // Populate a collection of events with the existing entity courts
//     // selection.
//     Collection allSelectedCourts = new ArrayList();
//     createVariableElementResponses(currentSelection, allSelectedCourts);
//
//     //----------------------------------------------
//     // Setup the four working sets.
//
//     // Existing standard courts
//     StringSet stdCourts = stdCourtSet(allSelectedCourts);
//
//     // Existing exception courts
//     StringSet expCourts = expCourtSet(allSelectedCourts);
//
//     // Changed standard courts
//     StringSet stdCourtsChanges = stdCourtSet(changedSelection);
//
//     // Changed exception courts
//     StringSet expCourtsChanges = expCourtSet(changedSelection);
//
//     //----------------------------------------------
//     // Translate to the differnce sets we neeed to modify the original sets.
//
//     // A set of courts that have been moved from the standard set to the
//     // exception set.
//     Set exceptedCourts = stdCourts.intersection(expCourtsChanges);
//
//     // A set of courts that have been moved from the exception set to the
//     // standard set.
//     Set unexceptedCourts = expCourts.intersection(stdCourtsChanges);
//
//     // New court selections
//     Set newStdCourts = stdCourtsChanges.complement(stdCourts.union(expCourts));
//
//     // New exception court selections
//     Set newExpCourts = expCourtsChanges.complement(stdCourts.union(expCourts));
//
//     // All deleted court selections
//     Set deletedCourts = stdCourts.union(expCourts).complement(stdCourtsChanges.union(expCourtsChanges));
//
//     // Index VariableElementResponseEvents and determine typeIds.
//     Map varElemEventMap = new HashMap();
//     Set typeIds = new HashSet();
//     Iterator varElemIter = changedSelection.iterator();
//     while (varElemIter.hasNext()) {
//         VariableElementResponseEvent evt = (VariableElementResponseEvent) varElemIter.next();
//         varElemEventMap.put(evt.getCourtId() + "/" + evt.getVariableElementTypeId(), evt);
//         if (evt.getVariableElementTypeId() != null) {
//             typeIds.add(evt.getVariableElementTypeId());
//         }
//     }
//
//     // Index existing courts (CourtSupervisionOption)
//     Map courtOptionsMap = new HashMap();
//     Iterator optIter = currentSelection.iterator();
//     while (optIter.hasNext()) {
//         CourtSupervisionOption opt = (CourtSupervisionOption) optIter.next();
//         courtOptionsMap.put(opt.getCourtId(), opt);
//     }
//
//     // Handle courts that have been removed
//     Iterator crtIter = deletedCourts.iterator();
//     while (crtIter.hasNext()) {
//         String courtId = (String) crtIter.next();
//         CourtSupervisionOption sel = (CourtSupervisionOption) courtOptionsMap.remove(courtId);
//         currentSelection.remove(sel);
//         courtsRemoved = true;
//     }
//
//     // Handle courts that have been excepted
//     crtIter = exceptedCourts.iterator();
//     while (crtIter.hasNext()) {
//         String courtId = (String) crtIter.next();
//         CourtSupervisionOption opt = (CourtSupervisionOption) courtOptionsMap.get(courtId);
//         opt.setIsExceptionCourt(true);
//         opt.clearVariableElements();
//     }
//
//     // Handle courts that have been unexcepted
//     crtIter = unexceptedCourts.iterator();
//     while (crtIter.hasNext()) {
//         String courtId = (String) crtIter.next();
//         CourtSupervisionOption opt = (CourtSupervisionOption) courtOptionsMap.get(courtId);
//         opt.setIsExceptionCourt(false);
//         opt.clearVariableElements();
//     }
//
//     // Handle new standard courts
//     crtIter = newStdCourts.iterator();
//     while (crtIter.hasNext()) {
//         String courtId = (String) crtIter.next();
//         try {
//             CourtSupervisionOption opt = (CourtSupervisionOption) optionType.newInstance();
//             opt.setCourtId(courtId);
//             opt.setIsExceptionCourt(false);
//             courtOptionsMap.put(courtId, opt);
//             currentSelection.add(opt);
//         } catch (Exception ex) {
//             //IllegalAccessException
//             //InstantiationException
//             throw new RuntimeException(ex.getMessage());
//         }
//     }
//
//     // Handle new exception courts
//     crtIter = newExpCourts.iterator();
//     while (crtIter.hasNext()) {
//         String courtId = (String) crtIter.next();
//         try {
//             CourtSupervisionOption opt = (CourtSupervisionOption) optionType.newInstance();
//             opt.setCourtId(courtId);
//             opt.setIsExceptionCourt(true);
//             courtOptionsMap.put(courtId, opt);
//             currentSelection.add(opt);
//         } catch (Exception ex) {
//             //IllegalAccessException
//             //InstantiationException
//             throw new RuntimeException(ex.getMessage());
//         }
//     }
//
//     //updateVariableElements(currentSelection, varElemEventMap, typeIds);
//
//     return courtsRemoved;
// }
    
//  private static void updateVariableElements(Collection courtSupervisionOptions, Map varElemEventMap, Set typeIds) {
//  Collection stdVarElems = null;
//  Iterator opts = courtSupervisionOptions.iterator();
//  while (opts.hasNext()) {
//      CourtSupervisionOption opt = (CourtSupervisionOption) opts.next();
//
//      // Index VariableElements and remove the deleted ones.
//      Map varElemMap = new HashMap();
//      Iterator varElemIter = opt.getVariableElements().iterator();
//      while (varElemIter.hasNext()) {
//          VariableElement elem = (VariableElement) varElemIter.next();
//
//          if (!typeIds.contains(elem.getVariableElementTypeId())) {
//              varElemIter.remove();
//          } else {
//              varElemMap.put(elem.getVariableElementTypeId(), elem);
//          }
//      }
//
//      if (opt.getIsExceptionCourt()) {
//          // Update values and make additions
//          Iterator varElemIdIter = typeIds.iterator();
//          while (varElemIdIter.hasNext()) {
//              String id = (String) varElemIdIter.next();
//              VariableElement varElem = (VariableElement) varElemMap.get(id);
//              if (varElem == null) {
//                  varElem = new VariableElement();
//                  varElem.setVariableElementTypeId(id);
//                  opt.insertVariableElements(varElem);
//              }
//
//              VariableElementResponseEvent evt = (VariableElementResponseEvent) varElemEventMap.get(opt.getCourtId() + "/"
//                      + varElem.getVariableElementTypeId());
//
//              // set value/ValueId based on isEnumerated flag
//              VariableElementType veType = varElem.getVariableElementType();
//              if (veType.isEnumeration()) {
//                  varElem.setValue(evt.getValueId());
//              } else {
//                  varElem.setValue(evt.getValue());
//              }
//              varElem.setIsFixed(evt.isFixed());
//          }
//      } else {
//          // Setup standard courts one time
//          if (stdVarElems == null) {
//              stdVarElems = new ArrayList();
//
//              // Update values and make additions
//              Iterator varElemIdIter = typeIds.iterator();
//              while (varElemIdIter.hasNext()) {
//                  String id = (String) varElemIdIter.next();
//                  VariableElement varElem = (VariableElement) varElemMap.get(id);
//                  if (varElem == null) {
//                      varElem = new VariableElement();
//                      varElem.setVariableElementTypeId(id);
//                  }
//
//                  VariableElementResponseEvent evt = (VariableElementResponseEvent) varElemEventMap.get(opt.getCourtId() + "/"
//                          + varElem.getVariableElementTypeId());
//
//                  // set value/ValueId based on isEnumerated flag
//                  VariableElementType veType = varElem.getVariableElementType();
//                  if (veType.isEnumeration()) {
//                      varElem.setValue(evt.getValueId());
//                  } else {
//                      varElem.setValue(evt.getValue());
//                  }
//                  varElem.setIsFixed(evt.isFixed());
//
//                  stdVarElems.add(varElem);
//              }
//          }
//
//          // Update the variableElement List.
//          opt.clearVariableElements();
//          Iterator stdVarElemIter = stdVarElems.iterator();
//          while (stdVarElemIter.hasNext()) {
//              opt.insertVariableElements((VariableElement) stdVarElemIter.next());
//          }
//      }
//  }
//}

//  public static void createVariableElementResponses(Collection options, Collection responses) {
//  Iterator optionIter = options.iterator();
//  while (optionIter.hasNext()) {
//      CourtSupervisionOption option = (CourtSupervisionOption) optionIter.next();
//
//      if (option.getVariableElements().isEmpty()) {
//          VariableElementResponseEvent varElemEvent = new VariableElementResponseEvent();
//
//          varElemEvent.setCourtId(option.getCourtId());
//          varElemEvent.setExceptionCourt(option.getIsExceptionCourt());
//          responses.add(varElemEvent);
//      } else {
//          Iterator varElemIter = option.getVariableElements().iterator();
//          while (varElemIter.hasNext()) {
//              VariableElement varElem = (VariableElement) varElemIter.next();
//
//              if (varElem != null) {
//                  VariableElementResponseEvent varElemEvent = new VariableElementResponseEvent();
//                  varElemEvent.setCourtId(option.getCourtId());
//                  varElemEvent.setExceptionCourt(option.getIsExceptionCourt());
//                  populateVariableElemResponseEvent(varElem, varElemEvent);
//                  responses.add(varElemEvent);
//              }
//          }
//      }
//  }
//}

//  public static void populateVariableElemResponseEvent(VariableElement varElem, VariableElementResponseEvent varElemEvent) {
//  varElemEvent.setVariableElementTypeId(varElem.getVariableElementTypeId());
//  varElemEvent.setVariableElementId(varElem.getOID().toString());
//  varElemEvent.setVariableElementTypeId(varElem.getVariableElementTypeId());
//  varElemEvent.setFixed(varElem.getIsFixed());
//
//  VariableElementType veType = varElem.getVariableElementType();
//  if (veType != null) {
//      varElemEvent.setName(veType.getName());
//      if (veType.isEnumeration()) {
//          varElemEvent.setEnumeration(true);
//          // if type is enumaration ; get codetable name
//          //				VariableElementTypeEnumerationInfo enumerationInfo =
//          // veType.getVariableElementTypeCodeTable();
//          //				varElemEvent.setCodeTableName(enumerationInfo.getElementCodeTableId());
//          //				varElemEvent.setEnumerationTypeId(enumerationInfo.getEnumerationTypeId());
//          varElemEvent.setCodeTableName(veType.getElementCodeTableId());
//          // if it is of type enumeration, its the code that we store
//          String valueId = varElem.getValue();
//          if (valueId != null && !valueId.equals("")) {
//              varElemEvent.setValueId(valueId);
//              Code valueCode = Code.find(varElemEvent.getCodeTableName(), valueId);
//              varElemEvent.setValue(valueCode.getDescription());
//          }
//      } else {
//          varElemEvent.setValue(varElem.getValue());
//      }
//      varElemEvent.setValueType(veType.getType());
//      varElemEvent.setEnumerationTypeId(veType.getEnumerationTypeId());
//      varElemEvent.setReference(veType.isReference());
//  }
//}

    
}
