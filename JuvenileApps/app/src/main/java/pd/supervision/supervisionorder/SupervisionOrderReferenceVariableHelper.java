/*
 * Created on Mar 29, 2006
 *
 */
package pd.supervision.supervisionorder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import naming.PDCodeTableConstants;
import naming.PDConstants;

import pd.codetable.criminal.OffenseCode;
import pd.criminalcase.CostBillSummary;
import pd.criminalcase.CriminalCase;
import pd.criminalcase.Supervision;
import pd.security.PDSecurityHelper;
import pd.supervision.Court;
import pd.supervision.supervisionoptions.VariableElementType;

import messaging.supervisionoptions.GetDetailDictionaryEvent;
import messaging.supervisionoptions.GetDetailDictionaryReferenceVariablesEvent;
import messaging.supervisionoptions.reply.VariableElementResponseEvent;
import messaging.supervisionorder.GetSupervisionOrderVariableElementReferencesEvent;
import mojo.km.config.EntityMappingProperties;
import mojo.km.config.MojoProperties;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.mapping.BufferMapping;
import mojo.km.persistence.PersistentObject;

/**
 * @author dgibler
 *  
 */
public class SupervisionOrderReferenceVariableHelper {
	/**
	 *  
	 */
	private SupervisionOrderReferenceVariableHelper() {
		super();
	}

	/**
	 * @param criminalCaseId
	 */
	public static void postSupervisionVariableElementReferenceResponseEvents(
			GetSupervisionOrderVariableElementReferencesEvent anEvent) {

		SupervisionOrder supervisionOrder = null;
		String orderId = anEvent.getOrderId();
		String criminalCaseId = anEvent.getCriminalCaseId();
		//String formAction = anEvent.getFormAction();

		if (orderId != null && !orderId.equals("")) {
			supervisionOrder = SupervisionOrder.find(orderId);
		}
//		if (supervisionOrder != null
//				&& supervisionOrder.getOrderStatusId().equals(
//						PDCodeTableConstants.STATUS_ACTIVE_ID)
//				&& anEvent.isRefreshable()) {
		//Update of an active order is actually a create of an ammended or modified order.
	    //Update of an inactive order is actually a create of a new original order.

		if (supervisionOrder != null
				&& (supervisionOrder.getOrderStatusId().equals(
						PDCodeTableConstants.STATUS_ACTIVE_ID) 
						|| supervisionOrder.getOrderStatusId().equals(PDCodeTableConstants.STATUS_INACTIVE_ID))
				&& anEvent.isRefreshable()) {
//			SupervisionOrderReferenceVariableHelper
//					.postRefreshedReferenceVariables(supervisionOrder,
//							criminalCaseId);
			SupervisionOrderReferenceVariableHelper
			.postRefreshedReferenceVariables(supervisionOrder,
					criminalCaseId, anEvent.getVariableElementNames());

		} else if (supervisionOrder != null
				&& !supervisionOrder.isOrderInProgress()) {
			SupervisionOrderReferenceVariableHelper
					.postReferenceVariablesFromOrder(supervisionOrder
							.getSupervisionOrderConditionRels());
		} else {
			SupervisionOrderReferenceVariableHelper
					.postRefreshedReferenceVariables(supervisionOrder,
							criminalCaseId, anEvent.getVariableElementNames());
		}

	}

	/**
	 * @param orderId
	 * @param criminalCaseId
	 * @return
	 */
	/* private static void postRefreshedReferenceVariables(
			SupervisionOrder supervisionOrder, String criminalCaseId) {
		Iterator variableElementTypeIter = SupervisionOrderReferenceVariableHelper
				.getSupervisionVariableElementReferences();

		if (variableElementTypeIter != null
				&& variableElementTypeIter.hasNext()) {
			Collection responseEvents = new ArrayList();
			Map referenceMap = new HashMap();
			BufferMapping mapper = BufferMapping.getInstance();

			Supervision supervision = null;
			CostBillSummary costBillSummary = null;
			Court court = null;
			String courtId = null;
			OffenseCode offenseCode = null;

			if (criminalCaseId == null || criminalCaseId.equals("")
					&& supervisionOrder != null) {
				criminalCaseId = supervisionOrder.getCriminalCaseId();
				courtId = supervisionOrder.getCurrentCourtId();
			}
			if (criminalCaseId != null && !criminalCaseId.equals("")) {
				supervision = Supervision.find(criminalCaseId);
				costBillSummary = CostBillSummary.find(criminalCaseId);
				CriminalCase theCase = CriminalCase.find(criminalCaseId);
				//Get court off of criminal case if order has not yet been
				// created.
				if (courtId == null || courtId.equals("")) {
					courtId = theCase.getCourtId();
				}
				offenseCode = theCase.getOffenseCode();
			}

			if (courtId != null && !courtId.equals("")) {
				court = Court.find(courtId);
			}

			String name = null;
			String value = null;
			VariableElementType variableElementType = null;
			String formattedName = null;
			boolean found = false;

			while (variableElementTypeIter.hasNext()) {
				variableElementType = (VariableElementType) variableElementTypeIter
						.next();
				name = variableElementType.getName();
				formattedName = SupervisionOrderReferenceVariableHelper
						.formatName(name);
				found = false;
				if (supervision != null) {
					value = mapper.updateBuffer(formattedName, supervision);
					if (!formattedName.equals(value)
							&& !value.equals(PDConstants.NONE)) {
						SupervisionOrderReferenceVariableHelper
								.postVariableElementResponseEvent(name, value,
										true, false);
						found = true;
					}
				}
				if (!found && costBillSummary != null) {
					value = mapper.updateBuffer(formattedName, costBillSummary);
					if (!formattedName.equals(value)
							&& !value.equals(PDConstants.NONE)) {
						SupervisionOrderReferenceVariableHelper
								.postVariableElementResponseEvent(name, value,
										true, false);
						found = true;
					}
				}
				if (!found && supervisionOrder != null) {
					value = mapper
							.updateBuffer(formattedName, supervisionOrder);
					if (!formattedName.equals(value)
							&& !value.equals(PDConstants.NONE)) {
						SupervisionOrderReferenceVariableHelper
								.postVariableElementResponseEvent(name, value,
										true, false);
						found = true;
					}
				}
				if (!found && court != null) {
					value = mapper.updateBuffer(formattedName, court);
					if (!formattedName.equals(value)
							&& !value.equals(PDConstants.NONE)) {
						SupervisionOrderReferenceVariableHelper
								.postVariableElementResponseEvent(name, value,
										true, false);
						found = true;
					}
				}
				if (!found && offenseCode != null) {
					value = mapper.updateBuffer(formattedName, offenseCode);
					if (!formattedName.equals(value)
							&& !value.equals(PDConstants.NONE)) {
						SupervisionOrderReferenceVariableHelper
								.postVariableElementResponseEvent(name, value,
										true, false);
						found = true;
					}
				}
			}
		}
	} */
	public static Map getRefreshedReferenceVariables(SupervisionOrder supervisionOrder, Collection variableElementNames){
		Iterator variableElementNameIter = variableElementNames.iterator();

		Court court = null;
		OffenseCode offenseCode = null;
		String variableElementName = null;
		String formattedName = null;
		String OFFENSE_CODE_CLASSNAME = OffenseCode.class.getName();
		String COURT_CLASSNAME = Court.class.getName();
		String SUPERVISIONORDER_CLASSNAME = SupervisionOrder.class.getName();
		String entityName = null;
		MojoProperties mojoProps = MojoProperties.getInstance();
		EntityMappingProperties eProps = null;
		String value = null;
		VariableElementType vet = null;
		Map aMap = new HashMap();
		if (variableElementNameIter != null
				&& variableElementNameIter.hasNext()) {

			if (supervisionOrder != null){	
				court = supervisionOrder.getCurrentCourt();
				CriminalCase criminalCase = supervisionOrder.getCriminalCase();
				if (criminalCase != null){
					offenseCode = criminalCase.getOffenseCode();
				}
			}

			BufferMapping mapper = BufferMapping.getInstance();

			while (variableElementNameIter.hasNext()) {
			    variableElementName = (String) variableElementNameIter.next();
				formattedName = SupervisionOrderReferenceVariableHelper
						.formatName(variableElementName);

				//Determine if BufferMapping exists for entity
				eProps = mojoProps.getBufferMappingByFieldName(formattedName);
				if (eProps != null) {
					entityName = eProps.getEntity();
					//Retrieve entity data
					if (SUPERVISIONORDER_CLASSNAME.equals(entityName)) {  
					    value = mapper.updateBuffer(formattedName, supervisionOrder);
					} else if (OFFENSE_CODE_CLASSNAME.equals(entityName)) {
					    value = mapper.updateBuffer(formattedName, offenseCode);	
					} else if (COURT_CLASSNAME.equals(entityName)) {
						value = mapper.updateBuffer(formattedName, court);
					} 
					if (!formattedName.equals(value) && !value.equals(PDConstants.NONE)) {
					    aMap.put(variableElementName, value);
					}
				}
			}
		}
		return aMap;
	}
	/**
	 * @param orderId
	 * @param criminalCaseId
	 * @return
	 */
	public static void postRefreshedReferenceVariables(
			SupervisionOrder supervisionOrder, String criminalCaseId,
			Collection variableElementNames) {
		
		Supervision supervision = null;
		CostBillSummary costBillSummary = null;
		Court court = null;
		String courtId = null;
		OffenseCode offenseCode = null;
		CriminalCase criminalCase = null;
		boolean supervisionRetrievalDone = false;
		boolean costBillSummaryRetrievalDone = false;
		String SUPERVISION_CLASSNAME = Supervision.class.getName();
		String COSTBILL_SUMMARY_CLASSNAME = CostBillSummary.class.getName();
		String OFFENSE_CODE_CLASSNAME = OffenseCode.class.getName();
		String COURT_CLASSNAME = Court.class.getName();
		String SUPERVISIONORDER_CLASSNAME = SupervisionOrder.class
				.getName();
		String CRIMINAL_CASE = CriminalCase.class.getName();
		String variableElementName = null;
		String formattedName = null;
		String entityName = null;
		MojoProperties mojoProps = MojoProperties.getInstance();
		EntityMappingProperties eProps = null;

		Iterator variableElementNameIter = variableElementNames.iterator();

		if (variableElementNameIter != null
				&& variableElementNameIter.hasNext()) {

			if (criminalCaseId == null || criminalCaseId.equals("")){
			    criminalCaseId = supervisionOrder.getCriminalCaseId();
			}
			if (supervisionOrder != null){	
			    criminalCaseId = supervisionOrder.getCriminalCaseId();
				courtId = supervisionOrder.getCurrentCourtId();
				//offenseCode = supervisionOrder.getOffense();
			}

			BufferMapping mapper = BufferMapping.getInstance();

			while (variableElementNameIter.hasNext()) {
				variableElementName = (String) variableElementNameIter.next();
				formattedName = SupervisionOrderReferenceVariableHelper
						.formatName(variableElementName);

				//Determine if BufferMapping exists for entity
				eProps = mojoProps.getBufferMappingByFieldName(formattedName);
				if (eProps != null) {
					entityName = eProps.getEntity();
					//Retrieve entity data
					if (SUPERVISION_CLASSNAME.equals(entityName)) {
						//Supervision may not exist for a case. Do
						// not re-execute if there are more than one
						// reference variable mapped to an entity.
						if (supervision == null && !supervisionRetrievalDone) {
							supervision = Supervision.find(criminalCaseId);
							supervisionRetrievalDone = true;
						}
						SupervisionOrderReferenceVariableHelper.postValue(
								mapper, variableElementName, supervision);
					} else if (COSTBILL_SUMMARY_CLASSNAME.equals(entityName)) {
						//CostBillSummary may not exist for a case. Do not
						// re-execute if there are more than one reference
						// variable mapped to an entity.
						if (costBillSummary == null
								&& !costBillSummaryRetrievalDone) {
							costBillSummary = CostBillSummary
									.find(criminalCaseId);
							costBillSummaryRetrievalDone = true;
						}
						SupervisionOrderReferenceVariableHelper.postValue(
								mapper, variableElementName, costBillSummary);
					} else if (SUPERVISIONORDER_CLASSNAME.equals(entityName)) {  
					    //If selecting a suggested order on a create, a supervision order objected has not yet been created.  
					    //In this case reference variables tied to a supervision order will not be populated.
						SupervisionOrderReferenceVariableHelper.postValue(
								mapper, variableElementName, supervisionOrder);
					} else if (OFFENSE_CODE_CLASSNAME.equals(entityName)) {
						if ((offenseCode == null && criminalCase == null)) {
								criminalCase = CriminalCase
										.find(criminalCaseId);
								offenseCode = criminalCase.getOffenseCode();
							}
						SupervisionOrderReferenceVariableHelper.postValue(
								mapper, variableElementName, offenseCode);

					} else if (COURT_CLASSNAME.equals(entityName)) {
						if (court == null) {
							if (courtId == null || courtId.equals("")) {
								if (criminalCase == null) {
									criminalCase = CriminalCase
											.find(criminalCaseId);
									courtId = criminalCase.getCourtId();
								}
							}
							court = Court.find(courtId);
						}
						SupervisionOrderReferenceVariableHelper.postValue(
								mapper, variableElementName, court);
					} else if (CRIMINAL_CASE.equals(entityName)){
						if (criminalCase == null){
							criminalCase = CriminalCase
							.find(criminalCaseId);
							SupervisionOrderReferenceVariableHelper.postValue(
									mapper, variableElementName, criminalCase);
						}
					}
				} else {
					StringBuffer sb = new StringBuffer(
							"Reference variable not mapped! ");
					sb.append(variableElementName);
					System.out.println(sb.toString());
				}
			}
		}
	}

	/**
	 * @param mapper
	 * @param formattedName
	 * @param supervision
	 */
	public static void postValue(BufferMapping mapper,
			String variableElementName, PersistentObject persistentObject) {

		if (persistentObject != null) {
			String value = "";

			String formattedName = SupervisionOrderReferenceVariableHelper
					.formatName(variableElementName);

			if (persistentObject instanceof Supervision) {
				Supervision supervision = (Supervision) persistentObject;
				value = mapper.updateBuffer(formattedName, supervision);
			} else if (persistentObject instanceof CostBillSummary) {
				CostBillSummary costBillSummary = (CostBillSummary) persistentObject;
				value = mapper.updateBuffer(formattedName, costBillSummary);
			} else if (persistentObject instanceof OffenseCode) {
				OffenseCode offenseCode = (OffenseCode) persistentObject;
				value = mapper.updateBuffer(formattedName, offenseCode);
			} else if (persistentObject instanceof SupervisionOrder) {
				SupervisionOrder supervisionOrder = (SupervisionOrder) persistentObject;
				value = mapper.updateBuffer(formattedName, supervisionOrder);
			} else if (persistentObject instanceof Court) {
				Court court = (Court) persistentObject;
				value = mapper.updateBuffer(formattedName, court);
			} else if (persistentObject instanceof CriminalCase){
				CriminalCase criminalCase = (CriminalCase) persistentObject;
				value = mapper.updateBuffer(formattedName, criminalCase);
			}

			if (!formattedName.equals(value) && !value.equals(PDConstants.NONE)) {
				SupervisionOrderReferenceVariableHelper
						.postVariableElementResponseEvent(variableElementName,
								value, true, true);
			}
		} else {
			SupervisionOrderReferenceVariableHelper
			.postVariableElementResponseEvent(variableElementName,
					"", true, true);
		}
	}

	/**
	 * @param collection
	 */
	private static void postReferenceVariablesFromOrder(
			Collection SupervisionOrderConditionRels) {
		Iterator soRelIter = SupervisionOrderConditionRels.iterator();
		if (soRelIter != null && soRelIter.hasNext()) {
			SupervisionOrderConditionRel soRel = null;
			SupervisionOrderConditionRelValue soRelVal = null;
			VariableElementType variableElementType = null;
			Collection soRelValues = null;
			Iterator soRelValIter = null;

			while (soRelIter.hasNext()) {
				soRel = (SupervisionOrderConditionRel) soRelIter.next();
				soRelValues = soRel.getOrderConditionRelValues();
				if (soRelValues != null) {
					soRelValIter = soRelValues.iterator();
					while (soRelValIter.hasNext()) {
						soRelVal = (SupervisionOrderConditionRelValue) soRelValIter
								.next();
						variableElementType = soRelVal.getVariableElementType();
						SupervisionOrderReferenceVariableHelper
								.postVariableElementResponseEvent(
										variableElementType.getName(), soRelVal
												.getValue(),
										variableElementType.getIsReference(), soRelVal.getIsFixed());
					}
				}
			}
		}
	}

	/**
	 * Returns a string surrounded by brackets.
	 * 
	 * @param name
	 * @return
	 */
	private static String formatName(String name) {
		StringBuffer sbName = new StringBuffer();
		if (name != null) {
			sbName = new StringBuffer(name);
			sbName.insert(0, "[");
			sbName.append("]");
		}
		return sbName.toString();
	}

	/**
	 * @param name
	 * @param value
	 */
	public static void postVariableElementResponseEvent(String name,
			String value, boolean reference, boolean fixed) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		VariableElementResponseEvent response = new VariableElementResponseEvent();
		response.setName(name);
		response.setValue(value);
		response.setValueId(value);
		response.setReference(reference);
		response.setFixed(fixed);
		dispatch.postEvent(response);
	}

	/**
	 * Resolve supervision order reference fields.
	 * 
	 * @param map
	 * @param supervisionOrder
	 * @return
	 */
	public static Map resolveSupervisionOrderReferenceVariables(Map map,
			SupervisionOrder supervisionOrder) {
		//This will need to be implemented if there are any reference variables
		// used from SupervisionOrder.

		//		Iterator variableElementTypeIter =
		// SupervisionOrderReferenceVariableHelper.getSupervisionVariableElementReferences();
		//		BufferMapping mapper = BufferMapping.getInstance();
		//		String name = null;
		//		String value = null;
		//		VariableElementType variableElementType = null;
		//		String formattedName = null;

		//Iterate thru VariableElementResponseEvents
		//		if (variableElementTypeIter != null &&
		// variableElementTypeIter.hasNext())
		//			while (variableElementTypeIter.hasNext())
		//			{
		//				variableElementType = (VariableElementType)
		// variableElementTypeIter.next();
		//				name = variableElementType.getName();
		//				formattedName =
		// SupervisionOrderReferenceVariableHelper.formatName(name);
		//		
		//				if (supervisionOrder != null)
		//				{
		//					value = mapper.updateBuffer(formattedName, supervisionOrder);
		//					if (!formattedName.equals(value) && !value.equals(PDConstants.NONE))
		//					{
		//						map.put(name, value);
		//						found = true;
		//					}
		//				}
		//			}
		//		}

		return map;
	}

	public static Iterator getSupervisionVariableElementReferences() {
		//Get reference variable names for agency
		GetDetailDictionaryReferenceVariablesEvent dictEvent = new GetDetailDictionaryReferenceVariablesEvent();
		String agencyId = PDSecurityHelper.getUserAgencyId();
		dictEvent.setAgencyId(agencyId);
		Iterator iter = VariableElementType.findAll(dictEvent);

		return iter;
	}

	/**
	 * Converts a given time period indicator to year(s), month(s) or day(s)
	 * depending on it's value.
	 * 
	 * @param aTimePeriod
	 * @return
	 */
	public static String getFormattedTimePeriod(String aTimePeriod, String aTime) {
	    if (aTimePeriod != null && aTimePeriod.length() > 1){
	        aTimePeriod = aTimePeriod.substring(0,1);
	    }
		String formattedTimePeriod = PDConstants.BLANK;
		Integer timeInt = null;
		if (aTimePeriod != null && !aTimePeriod.equals(PDConstants.BLANK)
				&& !aTimePeriod.equals(PDConstants.NONE)) {
			timeInt = new Integer(aTime);
			if (aTimePeriod.equalsIgnoreCase("Y")) {
				if (timeInt.intValue() > 1) {
					formattedTimePeriod = PDConstants.YEARS;
				} else {
					formattedTimePeriod = PDConstants.YEAR;
				}
			} else if (aTimePeriod.equalsIgnoreCase("M")) {
				if (timeInt.intValue() > 1) {
					formattedTimePeriod = PDConstants.MONTHS;
				} else {
					formattedTimePeriod = PDConstants.MONTH;
				}
			} else if (aTimePeriod.equalsIgnoreCase("D")) {
				if (timeInt.intValue() > 1) {
					formattedTimePeriod = PDConstants.DAYS;
				} else {
					formattedTimePeriod = PDConstants.DAY;
				}
			}else if (aTimePeriod.equalsIgnoreCase("H")) {
				if (timeInt.intValue() > 1) {
					formattedTimePeriod = PDConstants.HOURS;
				} else {
					formattedTimePeriod = PDConstants.HOUR;
				}
			}
		}
		return formattedTimePeriod;
	}

	/**
	 * @param aString
	 * @return
	 */
	public static String getDerivedTimePeriod(String aString) {
		String formattedTimePeriod = PDConstants.BLANK;
		if (aString != null && !aString.equals("000000000000")
				&& aString.length() == 12) {
			String days = aString.substring(0, 4);
			String months = aString.substring(4, 8);
			String years = aString.substring(8, 12);
			if (!days.equals("0000")) {
				formattedTimePeriod = PDConstants.DAYS;
			} else if (!months.equals("0000")) {
				formattedTimePeriod = PDConstants.MONTHS;
			} else {
				formattedTimePeriod = PDConstants.YEARS;
			}
		}
		return formattedTimePeriod;
	}

	/**
	 * @param aString
	 * @return
	 */
	public static String getDerivedTime(String aString) {
		String dervedTime = PDConstants.BLANK;
		if (aString != null && !aString.equals("000000000000")
				&& aString.length() == 12) {
			String days = aString.substring(0, 4);
			String months = aString.substring(4, 8);
			String years = aString.substring(8, 12);
			if (!days.equals("0000")) {
				dervedTime = days;
			} else if (!months.equals("0000")) {
				dervedTime = months;
			} else {
				dervedTime = years;
			}
		}
		return dervedTime;
	}
	public static Map getNewValuesForVolatileRefVars(String agencyId, SupervisionOrder supervisionOrder){
		GetDetailDictionaryEvent groupsEvent = new GetDetailDictionaryEvent();
		groupsEvent.setAgencyId(agencyId);
		Collection volatileRefVars = new ArrayList();	
		Iterator iter = VariableElementType.findAll( groupsEvent );
		if (iter != null && iter.hasNext()){
		    VariableElementType vet = null;
		    while (iter.hasNext()){
		        vet = (VariableElementType) iter.next();
		        if (vet.getIsVolatile()){
		            volatileRefVars.add(vet.getName());
		        }
		    }
		}
		Map aMap = SupervisionOrderReferenceVariableHelper.getRefreshedReferenceVariables(supervisionOrder, volatileRefVars);
		return aMap;
	}

    /**
     * @param variableElementReferenceMap
     * @param updatedRefVarsMap
     */
    public static Map updateRefVars(Map variableElementReferenceMap, Map updatedRefVarsMap) {
        Set keySet = updatedRefVarsMap.keySet();
        Iterator iter = keySet.iterator();
        String key = null;
        String value = null;
        while (iter.hasNext()){
            key = (String) iter.next();
            value = (String) updatedRefVarsMap.get(key);
            variableElementReferenceMap.put(key, value);
        }
        return variableElementReferenceMap;
    }
}
