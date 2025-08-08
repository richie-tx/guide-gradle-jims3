/*
 * Created on Jun 1, 2004
 */
package ui.supervision.SupervisionOptions.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.supervisionoptions.DeleteSupervisionConditionEvent;
import messaging.supervisionoptions.reply.CourtResponseEvent;
import messaging.supervisionoptions.reply.GroupResponseEvent;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.common.UIUtil;
import ui.security.SecurityUIHelper;
import ui.supervision.SupervisionOptions.UISupervisionOptionHelper;

/**
 * @author awidjaja
 *
 * This form contains all the attributes needed to 
 * add, modify, copy and delete supervision condition.
 */
public class SupervisionConditionForm extends ActionForm
{
	public final static String VARIABLE = "Variable";
	public final static String FIXED = "Fixed";
	public final static String STANDARD = "STANDARD";
	public final static String NON_STANDARD = "NON-STANDARD";

	private Collection groups;

	private Collection standards;

	//TODO  get the actual value
	private boolean showArchived;
	private String agencyId;
	private String conditionId;

	private String group1Id;
	private String group2Id;
	private String group3Id;

	private String group1Name;
	private String group2Name;
	private String group3Name;

	private boolean standard;
	private boolean inUse=false;
	private boolean specialCondition;
	private String standardSearchCriteria; //it can be true, false, or unspecified.
	private boolean allCourtsSelected;
	private String conditionLiteral;
	//this variable is used for the preview of the condition literal replaced with sample values
	private String conditionLiteralPreview;
	private String conditionSpanishLiteral;
	//this variable is used for the preview of the condition literal replaced with sample values
	private String conditionSpanishLiteralPreview;

	//only for special condition
	private String fixedLiteral;
	private String fixedSpanishLiteral;

	private String severityLevelId;
	private String severityLevel;
	private String jurisdictionId;
	private String jurisdiction;
	private String effectiveDate;
	private String inactiveDate;
	private String notes;

	private String conditionStatusId = PDCodeTableConstants.STATUS_ACTIVE;
	private String conditionStatus;

	private String eventCount;
	private String eventCountDesc;
	private String periodValue;
	private String periodId;
	private String period;

	private CSTaskConfiguration tasks = null; //new CSTaskConfiguration(true);
	
	private String selectedValue="";

	private String taskSubject;
	private Collection recipientList;
	private Collection taskListType;
	private String taskDueBy;

	private String notifyTaskViaEmailTo;
	private String conditionName;
	private Collection courts;

	private Collection conditionDetails;

	private Collection detailDictionary;
	private Collection filteredDetailDictionary;
	private Collection variableElementResponseEvents;
	private Collection variableElementNames;
	private HashMap detailDictionaryNameIdHashMap;

	private Collection selectedCourts;
	private Collection exceptionCourtVarElemBeans = new ArrayList();

	private Collection conditionSearchResults;

	private String selectedEventTypes;
	private String[] selectedEventTypeIds;
	private String[] selDocumentIds;
	private String[] selectedVariableElementTypes;
	private String[] selExceptionCourts;
	private String[] selSupervisionTypes;
	private String[] oldSelSupervisionTypes;
	
	private String selDocuments;
	private String dispSelSupTypes;
	private String oldDispSelSupTypes;

	private String ddName;
	private String ddDescription;
	private String ddType;

	private String action;
	private String pageType;

	private String deleteNote;

	private HashMap severityLevelsMap;
	private HashMap jurisdictionsMap;
	private HashMap documentsMap;
	private HashMap eventTypesMap;
	private HashMap periodsMap;
	private HashMap statusMap;

	//these are added to aid jsp display easier
	private Collection group2;
	private Collection group3;

	private Collection associatedCourtPolicies = new ArrayList();
	private Collection associatedDeptPolicies = new ArrayList();

	private Collection removedAssociations = new ArrayList();

	// Associate Court/Dept Policy attributes
	private AssociateBean assocSearchCriteria = new AssociateBean();
	private String searchResultText;
	private Collection assocSearchResults = new ArrayList(); // of AssociateBean
	private String[] selectInd;
	private Map assocSearchResultsMap = new HashMap();

	private boolean viewOnly; //is the view page view only, or can you update it

	private Map supervisionTypesMap = new HashMap();
	
	// Used just for clearing the form
	private String clearUpdateHidden="";
	private String effectiveDateSpecial;
	private String clearEmailTasks="";
	private DeleteSupervisionConditionEvent deleteSupervisionConditionEvent;
	private String supervisionTypeSummary;
	
	/**
	 * Constructor for the RoleGroupForm 
	 */
	public SupervisionConditionForm()
	{
		super();
	}
	
	public void clearDetailDictionary(){
		ddName="";
		ddDescription="";
		ddType="All";
	}
	/**
	 * @param aRequest
	 */
	public void reset(ActionMapping aMapping, HttpServletRequest aRequest)
	{
		Object obj = aRequest.getParameter("clearSelectedCourts");
		if (obj != null)
		{
			String clearSelectedCourts = (String) obj;
			if (clearSelectedCourts.equals("true"));
			{
				selectedCourts = new ArrayList();
			}
		}

		obj = aRequest.getParameter("clearExceptionCourts");
		if (obj != null)
		{
			String clearExceptionCourts = (String) obj;
			if (clearExceptionCourts.equals("true"));
			{
				if (selectedCourts != null && selectedCourts.size() > 0)
				{
					Iterator categoryIter = selectedCourts.iterator();
					while (categoryIter.hasNext())
					{
						CourtBean cb = (CourtBean) categoryIter.next();
						Collection courts = cb.getCourts();
						if (courts != null && courts.size() > 0)
						{
							Iterator courtsIter = courts.iterator();
							while (courtsIter.hasNext())
							{
								CourtResponseEvent cre = (CourtResponseEvent) courtsIter.next();
								;
								cre.setExceptionCourt(false);
							}
						}
					}
				}

			}
		}
		
		obj = aRequest.getParameter("clearSelectInd");
		if (obj != null)
		{
			String clearSelectInd = (String) obj;
			if (clearSelectInd.equals("true"));
			{
				this.setSelectInd(null);
			}
		}
		obj = aRequest.getParameter("clearUpdateHidden");
		if (obj != null)
		{
			clearUpdateHidden();
		}
		obj = aRequest.getParameter("clearEmailTasks");
		if (obj != null)
		{
			if(tasks!=null){
				tasks.clearEmailTaskItemCheckBoxes();
			}
		}
		

	}
	
	public void clearTaskConfiguation(){
		taskSubject = null;
		recipientList = null;
		taskListType = null;
		taskDueBy = null;
		if(tasks!=null)
		{
			tasks.clearAll();
		}
	}
	
	/**
	 * @param aRequest
	 */
	public void clear()
	{

		specialCondition=false;
		showArchived=false;
		groups = null;
		standards = null;

		//TODO  get the actual value
		agencyId = null;
		showArchived = false;
		conditionId = null;

		group1Id = null;
		group2Id = null;
		group3Id = null;

		group1Name = null;
		group2Name = null;
		group3Name = null;

		standard = false;
		standardSearchCriteria = null;
		allCourtsSelected = false;
		conditionLiteral = null;
		conditionSpanishLiteral = null;

		conditionLiteralPreview = null;
		conditionSpanishLiteralPreview = null;
		severityLevelId = null;
		severityLevel = null;
		jurisdictionId = null;
		jurisdiction = null;
		effectiveDate = null;
		inactiveDate = null;
		notes = null;

		conditionStatusId = PDCodeTableConstants.STATUS_ACTIVE;
		conditionStatus = null;

		eventCount = null;
		eventCountDesc=null;
		periodValue = null;
		periodId = null;
		period = null;

		clearTaskConfiguation();

		notifyTaskViaEmailTo = null;
		conditionName = null;
		courts = null;

		conditionDetails = null;

		detailDictionary = null;
		filteredDetailDictionary = null;
		variableElementResponseEvents = null;
		variableElementNames = null;
		detailDictionaryNameIdHashMap = null;

		selectedCourts = null;
		exceptionCourtVarElemBeans = null;

		conditionSearchResults = null;

		selectedEventTypes = null;
		selectedEventTypeIds = null;
		selDocumentIds = null;
		selectedVariableElementTypes = null;
		selExceptionCourts = null;
		selDocuments = null;
		selSupervisionTypes = null;
		oldSelSupervisionTypes=null;

		ddName = null;
		ddType = null;
		ddDescription = null;

		action = null;
		pageType = null;

		deleteNote = null;

		severityLevelsMap = null;
		jurisdictionsMap = null;
		documentsMap = null;
		eventTypesMap = null;
		periodsMap = null;
		statusMap = null;
		supervisionTypesMap = null;
		//these are added to aid jsp display easier
		group2 = null;
		group3 = null;

		//TODO:courts = null; - list of courts are static, no need to clear
		//do not clear because these are database look-up values, and will not change
		//- detailDictionary, groups, standards, detailDictionaryNameIdHashMap
		//- courts
		//- conditionName = db generated name, cache it until session ends

		// Associate Court/Dept Policy attributes
		assocSearchCriteria.clear();
		assocSearchResults.clear();
		associatedCourtPolicies.clear();
		associatedDeptPolicies.clear();

		removedAssociations.clear();

		searchResultText = "";

		viewOnly = false;
		inUse=false;

	}
	
	public void clearUpdateHidden(){
			group1Id = null;
			group2Id = null;
			group3Id = null;

			group1Name = "";
			group2Name = "";
			group3Name = "";

			effectiveDate = null;
			inactiveDate = null;

			//eventCount = "";
			eventCountDesc="";
			periodValue = "";
			periodId = "";
			period = "";

			selectedEventTypes = "";
			//selectedEventTypeIds = new String[0];
			selDocumentIds = new String[0];;
			selectedVariableElementTypes = new String[0];;
			selDocuments = "";

	}

	/**
	 * @return
	 */
	public Collection getConditionDetails()
	{
		return conditionDetails;
	}

	/**
	 * @return
	 */
	public String getConditionLiteral()
	{
		return conditionLiteral;
	}

	/**
	 * @return
	 */
	public String getConditionName()
	{
		return conditionName;
	}

	/**
	 * @return
	 */
	public Collection getCourts()
	{
		if (courts == null || courts.size() == 0)
		{
			courts = UISupervisionOptionHelper.getFilteredCourtBeans();
			if(this.getAgencyId().equals(UIConstants.JUV)){
				courts = UISupervisionOptionHelper.getUnFilteredCourtBeans();
				courts=UISupervisionOptionHelper.filterCourtBeansForCrtCategory(UIConstants.JUVENILE_COURT_CATEGORY,courts);
			}
		}
		return courts;
	}

	/**
	 * @return
	 */
	public String getEffectiveDate()
	{
		return effectiveDate;
	}

	/**
	 * @return
	 */
	public String getEventCountDesc()
	{
		
		return eventCountDesc;
	}

	/**
	 * @return
	 */
	public Collection getGroups()
	{
		if (groups == null || groups.size() == 0)
		{
			// get groups	
			groups = UISupervisionOptionHelper.fetchGroups(getAgencyId());
		}
		return groups;
	}

	/**
	 * @return
	 */
	public String getJurisdiction()
	{
		return jurisdiction;
	}

	/**
	 * @return
	 */
	public String getNotes()
	{
		return notes;
	}

	/**
	 * @return
	 */
	public String getNotifyTaskViaEmailTo()
	{
		return notifyTaskViaEmailTo;
	}

	/**
	 * @return
	 */
	public String getPeriod()
	{
		return period;
	}

	/**
	 * @return
	 */
	public String getPeriodValue()
	{
		return periodValue;
	}

	/**
	 * @return
	 */
	public Collection getRecipientList()
	{
		return recipientList;
	}

	/**
	 * @return
	 */
	public String getSeverityLevel()
	{
		return severityLevel;
	}

	/**
	 * @return
	 */
	public boolean getStandard()
	{
		return standard;
	}

	/**
	 * @return
	 */
	public Collection getStandards()
	{
		return standards;
	}

	/**
	 * @return
	 */
	public String getTaskDueBy()
	{
		return taskDueBy;
	}

	/**
	 * @return
	 */
	public Collection getTaskListType()
	{
		return taskListType;
	}

	/**
	 * @return
	 */
	public String getTaskSubject()
	{
		return taskSubject;
	}

	/**
	 * @param collection
	 */
	public void setConditionDetails(Collection collection)
	{
		conditionDetails = collection;
	}

	/**
	 * @param string
	 */
	public void setConditionLiteral(String string)
	{
		if(string!=null )
			string=UIUtil.removeStarting_BR_P_XMLtags(string);
		conditionLiteral = string;
	}

	/**
	 * @param string
	 */
	public void setConditionName(String string)
	{
		if (string != null)
			conditionName = string.trim();
	}

	/**
	 * @param collection
	 */
	public void setCourts(Collection collection)
	{
		courts = collection;
	}

	/**
	 * @param string
	 */
	public void setEffectiveDate(String string)
	{
		effectiveDate = string;
	}

	/**
	 * @param string
	 */
	public void setEventCountDesc(String string)
	{
		eventCountDesc = string;
	}

	/**
	 * @param collection
	 */
	public void setGroups(Collection collection)
	{
		groups = collection;
	}

	/**
	 * @param string
	 */
	public void setJurisdiction(String string)
	{
		jurisdiction = string;
	}

	/**
	 * @param string
	 */
	public void setNotes(String string)
	{
		notes = string;
	}

	/**
	 * @param string
	 */
	public void setNotifyTaskViaEmailTo(String string)
	{
		notifyTaskViaEmailTo = string;
	}

	/**
	 * @param string
	 */
	public void setPeriod(String string)
	{
		period = string;
	}

	/**
	 * @param string
	 */
	public void setPeriodValue(String string)
	{
		periodValue = string;
	}

	/**
	 * @param collection
	 */
	public void setRecipientList(Collection collection)
	{
		recipientList = collection;
	}

	/**
	 * @param string
	 */
	public void setSeverityLevel(String string)
	{
		severityLevel = string;
	}

	/**
	 * @param string
	 */
	public void setStandard(boolean string)
	{
		standard = string;
	}

	/**
	 * @param collection
	 */
	public void setStandards(Collection collection)
	{
		standards = collection;
	}

	/**
	 * @param string
	 */
	public void setTaskDueBy(String string)
	{
		taskDueBy = string;
	}

	/**
	 * @param collection
	 */
	public void setTaskListType(Collection collection)
	{
		taskListType = collection;
	}

	/**
	 * @param string
	 */
	public void setTaskSubject(String string)
	{
		taskSubject = string;
	}

	/**
	 * @return
	 */
	public String getGroup1Name()
	{
		return group1Name;
	}

	/**
	 * @return
	 */
	public String getGroup2Name()
	{
		return group2Name;
	}

	/**
	 * @return
	 */
	public String getGroup3Name()
	{
		return group3Name;
	}

	/**
	 * @param string
	 */
	public void setGroup1Name(String string)
	{
		group1Name = string;
	}

	/**
	 * @param string
	 */
	public void setGroup2Name(String string)
	{
		group2Name = string;
	}

	/**
	 * @param string
	 */
	public void setGroup3Name(String string)
	{
		group3Name = string;
	}

	/**
	 * @return
	 */
	public String getGroup1Id()
	{
		return group1Id;
	}

	/**
	 * @return
	 */
	public String getGroup2Id()
	{
		return group2Id;
	}

	/**
	 * @return
	 */
	public String getGroup3Id()
	{
		return group3Id;
	}

	/**
	 * @param string
	 */
	public void setGroup1Id(String string)
	{
		group1Id = string;
		setGroupNames(groups);
	}

	/**
	 * @param string
	 */
	public void setGroup2Id(String string)
	{
		group2Id = string;
		setGroupNames(groups);
	}

	/**
	 * @param string
	 */
	public void setGroup3Id(String string)
	{
		group3Id = string;
		setGroupNames(groups);
	}

	/**
	 * @return
	 */
	public Collection getDetailDictionary()
	{
		if (detailDictionary == null || detailDictionary.size() == 0)
		{
			// get detail dictionary
			detailDictionary = UISupervisionOptionHelper.fetchDetailDictionary(getAgencyId());
		}
		return detailDictionary;
	}

	/**
	 * @param collection
	 */
	public void setDetailDictionary(Collection collection)
	{
		detailDictionary = collection;
	}

	/**
	 * @return
	 */
	public String getDdDescription()
	{
		return ddDescription;
	}

	/**
	 * @return
	 */
	public String getDdName()
	{
		return ddName;
	}

	/**
		 * @return
		 */
	public String getDdType()
	{
		return ddType;
	}

	/**
	 * @param string
	 */
	public void setDdDescription(String string)
	{
		ddDescription = string;
	}

	/**
	 * @param string
	 */
	public void setDdName(String string)
	{
		ddName = string;
	}

	/**
		 * @param string
		 */
	public void setDdType(String string)
	{
		ddType = string;
	}

	/**
	 * @return
	 */
	public Collection getFilteredDetailDictionary()
	{
		return filteredDetailDictionary;
	}

	/**
	 * @param collection
	 */
	public void setFilteredDetailDictionary(Collection collection)
	{
		filteredDetailDictionary = collection;
	}

	/**
	 * @return
	 */
	public Collection getVariableElementResponseEvents()
	{
		return variableElementResponseEvents;
	}

	/**
	 * @param collection
	 */
	public void setVariableElementResponseEvents(Collection collection)
	{
		variableElementResponseEvents = collection;
	}

	/**
	 * @return
	 */
	public String getJurisdictionId()
	{
		return jurisdictionId;
	}

	/**
	 * @param string
	 */
	public void setJurisdictionId(String string)
	{
		jurisdictionId = string;
		if(this.getJurisdictionId() != null && !this.getJurisdictionId().equals(""))
		{
			Map map = this.getJurisdictionsMap();
			if(map == null)
				this.getJurisdictions();
			String jurisdiction = (String) this.getJurisdictionsMap().get(this.getJurisdictionId());
			this.setJurisdiction(jurisdiction);
		} 
		else{
			jurisdiction="";
		}
	}

	/**
	 * @return
	 */
	public String getPeriodId()
	{
		return periodId;
	}

	/**
	 * @param string
	 */
	public void setPeriodId(String string)
	{
		periodId = string;
		if(this.getPeriodId() != null && !this.getPeriodId().equals(""))
					{
						Map map = this.getPeriodsMap();
						if(map == null)
							this.getPeriods();
				
						String periodType = (String) this.getPeriodsMap().get(this.getPeriodId());
				
							this.setPeriod(periodType);
					} 
					else{
						period="";
					}
	}

	/**
	 * @return
	 */
	public String getSeverityLevelId()
	{
		return severityLevelId;
	}

	/**
	 * @param string
	 */
	public void setSeverityLevelId(String string)
	{
		severityLevelId = string;
		if(this.getSeverityLevelId() != null && !this.getSeverityLevelId().equals(""))
			{
				Map map = this.getSeverityLevelsMap();
				if(map == null)
					this.getSeverityLevels();
				
				String severityLevelDescription = (String) this.getSeverityLevelsMap().get(this.getSeverityLevelId());
				
					this.setSeverityLevel(severityLevelDescription);
			} 
			else{
				severityLevel="";
			}
	}

	// get collections
	public Collection getJurisdictions()
	{
		Collection jurisdictions = CodeHelper.getJurisdictions();
		setJurisdictionsMap(UISupervisionOptionHelper.createCodeTableMapping(jurisdictions));
		Collections.sort((ArrayList) jurisdictions);

		return jurisdictions;
	}

	public Collection getPeriods()
	{
		Collection periods = CodeHelper.getPeriods();
		setPeriodsMap(UISupervisionOptionHelper.createCodeTableMapping(periods));

		return periods;
	}

	public Collection getSeverityLevels()
	{
		Collection severityLevels = CodeHelper.getSeverityLevels(getAgencyId());
		setSeverityLevelsMap(UISupervisionOptionHelper.createCodeTableMapping(severityLevels));
		Collections.sort((ArrayList) severityLevels);
		return severityLevels;
	}

	public Collection getEventTypes()
	{
		Collection eventTypes = CodeHelper.getEventTypes(getAgencyId());
		setEventTypesMap(UISupervisionOptionHelper.createCodeTableMapping(eventTypes));
		Collections.sort((ArrayList) eventTypes);

		return eventTypes;
	}

	public Collection getStatusCodes()
	{
		Collection statusCodes = CodeHelper.getSupervisionConditionStatuses();
		setStatusMap(UISupervisionOptionHelper.createCodeTableMapping(statusCodes));
		Collections.sort((ArrayList) statusCodes);

		return statusCodes;
	}

	public Collection getDocuments()
	{
		Collection documents = CodeHelper.getDocuments(getAgencyId());
		setDocumentsMap(UISupervisionOptionHelper.createCodeTableMapping(documents));
		Collections.sort((ArrayList) documents);

		return documents;
	}

	/**
	 * @return
	 */
	public Collection getSupervisionTypes()
	{
		Collection supTypes = CodeHelper.getSupervisionTypes();
		setSupervisionTypesMap(UISupervisionOptionHelper.createCodeTableMapping(supTypes));

		Iterator iter = supTypes.iterator();
		while (iter.hasNext())
		{
			CodeResponseEvent re = (CodeResponseEvent) iter.next();
			supervisionTypesMap.put(re.getCode(), re.getDescription());

		}

		Collections.sort((ArrayList) supTypes);
		return supTypes;
	}

	public Collection getVarElementTypes()
	{
		Collection col = new ArrayList();

		CodeResponseEvent cr = new CodeResponseEvent();
		cr.setCode(VARIABLE);
		cr.setDescription(VARIABLE);
		col.add(cr);
		cr = new CodeResponseEvent();
		cr.setCode(FIXED);
		cr.setDescription(FIXED);
		col.add(cr);
		return col;
	}

	public Collection getStandardOptions()
	{
		Collection col = new ArrayList();

		CodeResponseEvent cr = new CodeResponseEvent();
		cr.setCode("");
		cr.setDescription("ALL");
		col.add(cr);

		cr = new CodeResponseEvent();
		cr.setCode("false");
		cr.setDescription(NON_STANDARD);
		col.add(cr);
		
		cr = new CodeResponseEvent();
		cr.setCode("true");
		cr.setDescription(STANDARD);
		col.add(cr);

		return col;
	}

	/**
	 * @return
	 */
	public HashMap getDetailDictionaryNameIdHashMap()
	{
		if(detailDictionaryNameIdHashMap == null){
			detailDictionaryNameIdHashMap = UISupervisionOptionHelper.createDetailDictionaryNameIdMapping(getDetailDictionary());
		}
		return detailDictionaryNameIdHashMap;
	}

	/**
	 * @param map
	 */
	public void setDetailDictionaryNameIdHashMap(HashMap map)
	{
		detailDictionaryNameIdHashMap = map;
	}

	/**
	 * @return
	 */
	public Collection getVariableElementNames()
	{
		return variableElementNames;
	}

	/**
	 * @param collection
	 */
	public void setVariableElementNames(Collection collection)
	{
		variableElementNames = collection;
	}

	/**
	 * @return
	 */
	public String getFixed()
	{
		return "";
	}

	/**
	 * @param collection
	 */
	public String getVariable()
	{
		return "";
	}

	/**
	 * @return
	 */
	public String getInactiveDate()
	{
		return inactiveDate;
	}

	/**
	 * @param string
	 */
	public void setInactiveDate(String string)
	{
		inactiveDate = string;
	}

	/**
	 * @return
	 */
	public String getConditionStatus()
	{
		return conditionStatus;
	}

	/**
	 * @param string
	 */
	public void setConditionStatus(String string)
	{
		conditionStatus = string;
	}

	/**
	 * @return
	 */
	public Collection getConditionSearchResults()
	{
		return conditionSearchResults;
	}

	/**
	 * @param collection
	 */
	public void setConditionSearchResults(Collection collection)
	{
		conditionSearchResults = collection;
	}

	/**
	 * @return
	 */
	public String[] getSelDocumentIds()
	{
		return selDocumentIds;
	}

	/**
	 * @param strings
	 */
	public void setSelDocumentIds(String[] strings)
	{
		selDocumentIds = strings;
		
	}

	/**
	 * @return
	 */
	public String getAction()
	{
		return action;
	}

	/**
	 * @param string
	 */
	public void setAction(String string)
	{
		action = string;
	}

	/**
	 * @return
	 */
	public String getConditionId()
	{
		return conditionId;
	}

	/**
	 * @param string
	 */
	public void setConditionId(String string)
	{
		conditionId = string;
	}

	/**
	 * @return
	 */
	public HashMap getSeverityLevelsMap()
	{
		return severityLevelsMap;
	}

	/**
	 * @param map
	 */
	public void setSeverityLevelsMap(HashMap map)
	{
		severityLevelsMap = map;
	}

	/**
	 * @return
	 */
	public HashMap getJurisdictionsMap()
	{
		return jurisdictionsMap;
	}

	/**
	 * @param map
	 */
	public void setJurisdictionsMap(HashMap map)
	{
		jurisdictionsMap = map;
	}

	/**
	 * @return
	 */
	public HashMap getDocumentsMap()
	{
		return documentsMap;
	}

	/**
	 * @param map
	 */
	public void setDocumentsMap(HashMap map)
	{
		documentsMap = map;
	}

	/**
	 * @return
	 */
	public HashMap getEventTypesMap()
	{
		return eventTypesMap;
	}

	/**
	 * @param map
	 */
	public void setEventTypesMap(HashMap map)
	{
		eventTypesMap = map;
	}

	/**
	 * @return
	 */
	public HashMap getPeriodsMap()
	{
		return periodsMap;
	}

	/**
	 * @param map
	 */
	public void setPeriodsMap(HashMap map)
	{
		periodsMap = map;
	}

	/**
	 * @return
	 */
	public String[] getSelectedEventTypeIds()
	{
		return selectedEventTypeIds;
	}

	/**
	 * @param strings
	 */
	public void setSelectedEventTypeIds(String[] strings)
	{
		selectedEventTypeIds = strings;
		if(strings!=null && strings.length>0){
			boolean hasNonBlank=false;
			for(int loopX=0; loopX<strings.length; loopX++){
				if(strings[loopX]!=null && !(strings[loopX].equals(""))){
					hasNonBlank=true;
					break;	
				}	
			}
			if(hasNonBlank){
				selectedEventTypeIds=strings;
			}
			else{
				selectedEventTypeIds=new String[0];	
			}
		}
		else
			selectedEventTypeIds=new String[0];
			
		if(this.getSelectedEventTypeIds() != null && this.getSelectedEventTypeIds().length > 0)
		{
			StringBuffer selectedEventTypesTemp = new StringBuffer();

			if(this.getEventTypesMap()==null){
				this.getEventTypes();
			}
			if(this.getEventTypesMap()!=null){
				for(int i=0;i<selectedEventTypeIds.length;i++)
				{
					selectedEventTypesTemp.append((String)this.getEventTypesMap().get(selectedEventTypeIds[i]));
					if(i<selectedEventTypeIds.length-1)
					selectedEventTypesTemp.append(", ");
				}
			}
			selectedEventTypes=selectedEventTypesTemp.toString();
		}
		else{
			selectedEventTypes="";
			
		}
	}

	/**
	 * @return
	 */
	public String getSelDocuments()
	{
		if(selDocuments==null)
			selDocuments="";
		return selDocuments;
	}

	/**
	 * @param string
	 */
	public void setSelDocuments(String string)
	{
		selDocuments = string;
	}

	// TODO get real agency
	public String getAgencyId()
	{
		if (agencyId == null || agencyId.equals(""))
		{
			agencyId = SecurityUIHelper.getUserAgencyId();
		}
		return agencyId;
		//		return "CSC";

	}
	/**
	 * @return
	 */
	public String getSelectedEventTypes()
	{
		return selectedEventTypes;
	}

	/**
	 * @param strings
	 */
	public void setSelectedEventTypes(String string)
	{
		selectedEventTypes = string;
	}

	/**
	 * @return
	 */
	public String getPageType()
	{
		return pageType;
	}

	/**
	 * @param string
	 */
	public void setPageType(String string)
	{
		pageType = string;
	}

	/**
	 * @return
	 */
	public Collection getGroup2()
	{
		return group2;
	}

	/**
	 * @return
	 */
	public Collection getGroup3()
	{
		return group3;
	}

	/**
	 * @param collection
	 */
	public void setGroup2(Collection collection)
	{
		group2 = collection;
	}

	/**
	 * @param collection
	 */
	public void setGroup3(Collection collection)
	{
		group3 = collection;
	}

	/**
	 * @return
	 */
	public String[] getSelectedVariableElementTypes()
	{
		return selectedVariableElementTypes;
	}

	/**
	 * @param strings
	 */
	public void setSelectedVariableElementTypes(String[] strings)
	{
		selectedVariableElementTypes = strings;
	}

	/**
	 * @return
	 */
	public HashMap getStatusMap()
	{
		return statusMap;
	}

	/**
	 * @param map
	 */
	public void setStatusMap(HashMap map)
	{
		statusMap = map;
	}

	/**
	 * @return
	 */
	public String getConditionStatusId()
	{
		return conditionStatusId;
	}

	/**
	 * @param string
	 */
	public void setConditionStatusId(String string)
	{
		conditionStatusId = string;
	}

	/**
	 * @return
	 */
	public String getDeleteNote()
	{
		return deleteNote;
	}

	/**
	 * @param string
	 */
	public void setDeleteNote(String string)
	{
		deleteNote = string;
	}

	/**
	 * @return
	 */
	public Collection getSelectedCourts()
	{
		return selectedCourts;
	}

	/**
	 * @param collection
	 */
	public void setSelectedCourts(Collection collection)
	{
		selectedCourts = collection;
	}

	/**
	 * @return
	 */
	public String[] getSelExceptionCourts()
	{
		return selExceptionCourts;
	}

	/**
	 * @param strings
	 */
	public void setSelExceptionCourts(String[] strings)
	{
		selExceptionCourts = strings;

	}

	/**
	 * @return
	 */
	public Collection getExceptionCourtVarElemBeans()
	{
		return exceptionCourtVarElemBeans;
	}

	/**
	 * @param collection
	 */
	public void setExceptionCourtVarElemBeans(Collection collection)
	{
		exceptionCourtVarElemBeans = collection;
	}

	public Object[] getECVE()
	{

		return this.exceptionCourtVarElemBeans.toArray();
	}

	public Object[] getSelectedCourtsArray()
	{
		return this.selectedCourts.toArray();
	}

	public Object[] getVariableElementResponseEventsArray()
	{
		return this.variableElementResponseEvents.toArray();
	}

	/**
	 * @return
	 */
	public Collection getAssociatedCourtPolicies()
	{
		return associatedCourtPolicies;
	}

	/**
	 * @return
	 */
	public Collection getAssociatedDeptPolicies()
	{
		return associatedDeptPolicies;
	}

	/**
	 * @param collection
	 */
	public void setAssociatedCourtPolicies(Collection collection)
	{
		associatedCourtPolicies = collection;
	}

	/**
	 * @param collection
	 */
	public void insertAssociatedCourtPolicy(AssociateBean associateBean)
	{
		associatedCourtPolicies.add(associateBean);
	}

	/**
	 * @param collection
	 */
	public void clearAssociatedCourtPolicies()
	{
		associatedCourtPolicies.clear();
	}

	/**
	 * @param collection
	 */
	public void removeAssociatedCourtPolicy(AssociateBean associateBean)
	{
		associatedCourtPolicies.remove(associateBean);
	}

	/**
	 * @param collection
	 */
	public void setAssociatedDeptPolicies(Collection collection)
	{
		associatedDeptPolicies = collection;
	}

	/**
	 * @param collection
	 */
	public void insertAssociatedDeptPolicy(AssociateBean associateBean)
	{
		associatedDeptPolicies.add(associateBean);
	}

	/**
	 * @param collection
	 */
	public void removeAssociatedDeptPolicy(AssociateBean associateBean)
	{
		associatedDeptPolicies.remove(associateBean);
	}

	/**
	 * @param collection
	 */
	public void clearAssociatedDeptPolicies()
	{
		associatedDeptPolicies.clear();
	}

	/**
	 * @return
	 */
	public AssociateBean getAssocSearchCriteria()
	{
		return assocSearchCriteria;
	}

	/**
	 * @return
	 */
	public Collection getAssocSearchResults()
	{
		return assocSearchResults;
	}

	/**
	 * @param bean
	 */
	public void setAssocSearchCriteria(AssociateBean bean)
	{
		assocSearchCriteria = bean;
	}

	/**
	 * @param collection
	 */
	public void setAssocSearchResults(Collection collection)
	{
		assocSearchResults = collection;
	}

	/**
	 * @param collection
	 */
	public void insertAssocSearchResult(AssociateBean associateBean)
	{
		assocSearchResults.add(associateBean);
	}

	/**
	 * @param collection
	 */
	public void clearAssocSearchResults()
	{
		assocSearchCriteria = new AssociateBean();
		assocSearchResults.clear();
		assocSearchResultsMap.clear();
	}

	/**
	 * @param collection
	 */
	public void removeAssocSearchResult(AssociateBean associateBean)
	{
		assocSearchResults.remove(associateBean);
		assocSearchResultsMap.remove(associateBean.getObjId());
	}

	/**
	 * @return
	 */
	public boolean isAllCourtsSelected()
	{
		return allCourtsSelected;
	}

	/**
	 * @param b
	 */
	public void setAllCourtsSelected(boolean b)
	{
		allCourtsSelected = b;
	}

	/**
	 * @return
	 */
	public String getSearchResultText()
	{
		return searchResultText;
	}

	/**
	 * @param string
	 */
	public void setSearchResultText(String string)
	{
		searchResultText = string;
	}

	/**
	 * @return
	 */
	public Map getAssocSearchResultsMap()
	{
		return assocSearchResultsMap;
	}

	/**
	 * @param map
	 */
	public void setAssocSearchResultsMap(Map map)
	{
		assocSearchResultsMap = map;
	}

	/**
	 * @return
	 */
	public String[] getSelectInd()
	{
		return selectInd;
	}

	/**
	 * @param strings
	 */
	public void setSelectInd(String[] strings)
	{
		selectInd = strings;
	}

	/**
	 * @return
	 */
	public String getConditionLiteralPreview()
	{
		return conditionLiteralPreview;
	}

	/**
	 * @param string
	 */
	public void setConditionLiteralPreview(String string)
	{
		conditionLiteralPreview = string;
	}

	/*
	 * This is to get different display depending on which agency the user belongs to
	 */
	/*public String getConditionLiteralCaption()
	{
		this.getAgencyId();
		if(agencyId.equalsIgnoreCase(UIConstants.JUV))
			return "prompt.literal";
		
		return "prompt.condition";
	} */

	public String getConditionGroup1Caption()
	{
		this.getAgencyId();
		if (agencyId.equalsIgnoreCase(UIConstants.JUV))
			return "prompt.category";
		else
			if (agencyId.equalsIgnoreCase(UIConstants.PTR))
				return "prompt.conditionType";

		return "prompt.group1";
	}

	public String getConditionGroup2Caption()
	{
		this.getAgencyId();
		if (agencyId.equalsIgnoreCase(UIConstants.JUV))
			return "prompt.type";
		else
			if (agencyId.equalsIgnoreCase(UIConstants.PTR))
				return "prompt.conditionSubType";

		return "prompt.group2";
	}

	public String getConditionGroup3Caption()
	{
		this.getAgencyId();
		if (agencyId.equalsIgnoreCase(UIConstants.JUV))
			return "prompt.subtype";
		else
			if (agencyId.equalsIgnoreCase(UIConstants.PTR))
				return "prompt.subTypeDetail";

		return "prompt.group3";
	}
	
	public String getCourtPolicyTitle()
	{
		if (getAgencyId().equalsIgnoreCase(UIConstants.JUV))
			return "title.consequence";
		else
			if (agencyId.equalsIgnoreCase(UIConstants.PTR))
				return "title.complianceStandards";

		return "title.CSCourtPolicy";

	}
	
	public String getCourtPolicyAssoTitle()
	{
		if (getAgencyId().equalsIgnoreCase(UIConstants.JUV))
			return "prompt.consequences";
		else
			if (agencyId.equalsIgnoreCase(UIConstants.PTR))
				return "title.complianceStandards";

		return "prompt.courtPolicies";

	}	

	public String getCourtGroup1Caption()
	{
		this.getAgencyId();
		if (agencyId.equalsIgnoreCase(UIConstants.JUV))
			return "prompt.category";
		else
			if (agencyId.equalsIgnoreCase(UIConstants.PTR))
				return "prompt.complianceType";

		return "prompt.group1";
	}

	public String getCourtGroup2Caption()
	{
		this.getAgencyId();
		if (agencyId.equalsIgnoreCase(UIConstants.JUV))
			return "prompt.type";
		else
			if (agencyId.equalsIgnoreCase(UIConstants.PTR))
				return "prompt.complianceSubType";

		return "prompt.group2";
	}

	public String getCourtGroup3Caption()
	{
		this.getAgencyId();
		if (agencyId.equalsIgnoreCase(UIConstants.JUV))
			return "prompt.subtype";
		else
			if (agencyId.equalsIgnoreCase(UIConstants.PTR))
				return "prompt.subTypeDetail";

		return "prompt.group3";
	}
	
	public String getDeptPolicyTitle()
	{
		if (getAgencyId().equalsIgnoreCase(UIConstants.PTR))
			return "title.procedures";
		return "prompt.policyTableTitle";

	}
		
	public String getDeptPolicyAssoTitle()
	{
		if (getAgencyId().equalsIgnoreCase(UIConstants.PTR))
			return "title.procedures";
		return "prompt.departmentPolicies";

	}

	public String getDepartmentGroup1Caption()
	{
		this.getAgencyId();
		if (agencyId.equalsIgnoreCase(UIConstants.JUV))
			return "prompt.category";
		else
			if (agencyId.equalsIgnoreCase(UIConstants.PTR))
				return "prompt.procedureType";

		return "prompt.group1";
	}

	public String getDepartmentGroup2Caption()
	{
		this.getAgencyId();
		if (agencyId.equalsIgnoreCase(UIConstants.JUV))
			return "prompt.type";
		else
			if (agencyId.equalsIgnoreCase(UIConstants.PTR))
				return "prompt.procedureSubType";

		return "prompt.group2";
	}

	public String getDepartmentGroup3Caption()
	{
		this.getAgencyId();
		if (agencyId.equalsIgnoreCase(UIConstants.JUV))
			return "prompt.subtype";
		else
			if (agencyId.equalsIgnoreCase(UIConstants.PTR))
				return "prompt.subTypeDetail";

		return "prompt.group3";
	}

	public Collection getSelectedCourtIds()
	{
		ArrayList courtIds = new ArrayList();

		Iterator selectedCourtIter = getSelectedCourts().iterator();
		while (selectedCourtIter.hasNext())
		{
			CourtBean courtBean = (CourtBean) selectedCourtIter.next();
			Iterator courtIter = courtBean.getCourts().iterator();
			while (courtIter.hasNext())
			{
				CourtResponseEvent evt = (CourtResponseEvent) courtIter.next();
				courtIds.add(evt.getCourtId());
			}
		}

		return courtIds;
	}

	/**
	 * @return
	 */
	public String getStandardSearchCriteria()
	{
		return standardSearchCriteria;
	}

	/**
	 * @param string
	 */
	public void setStandardSearchCriteria(String string)
	{
		standardSearchCriteria = string;
	}

	/**
	 * @return
	 */
	public boolean isViewOnly()
	{
		return viewOnly;
	}

	/**
	 * @param b
	 */
	public void setViewOnly(boolean b)
	{
		viewOnly = b;
	}

	/**
	 * @return
	 */
	public Collection getRemovedAssociations()
	{
		return removedAssociations;
	}

	/**
	 * @param collection
	 */
	public void setRemovedAssociations(Collection collection)
	{
		removedAssociations = collection;
	}

	/**
	 * @return
	 */
	public Map getSupervisionTypesMap()
	{
		return supervisionTypesMap;
	}

	/**
	 * @param map
	 */
	public void setSupervisionTypesMap(Map map)
	{
		supervisionTypesMap = map;
	}

	/**
	 * @return
	 */
	public boolean isAgencyJUV()
	{
		if (UIConstants.JUV.equals(getAgencyId()))
		{
			return true;
		}
		return false;
	}

	/**
		 * @return
		 */
	public boolean isAgencyCSC()
	{
		if (UIConstants.CSC.equals(getAgencyId()))
		{
			return true;
		}
		return false;
	}

	/**
	 * @return
	 */
	public String[] getSelSupervisionTypes()
	{
		return selSupervisionTypes;
	}

	/**
	 * @param strings
	 */
	public void setSelSupervisionTypes(String[] strings)
	{
		selSupervisionTypes = strings;
	}

	/**
	 * @return
	 */
	public String getDispSelSupTypes()
	{
		return dispSelSupTypes;
	}

	/**
	 * @param string
	 */
	public void setDispSelSupTypes(String string)
	{
		dispSelSupTypes = string;
	}

	/**
	 * @return
	 */
	public boolean isSpecialCondition()
	{
		return specialCondition;
	}

	/**
	 * @param b
	 */
	public void setSpecialCondition(boolean b)
	{
		specialCondition = b;
	}

	/**
	 * @return
	 */
	public String getFixedLiteral()
	{
		return fixedLiteral;
	}

	/**
	 * @param string
	 */
	public void setFixedLiteral(String string)
	{
		fixedLiteral = string;
	}

	/**
	 * @return
	 */
	public boolean isShowArchived()
	{
		return showArchived;
	}

	/**
	 * @param b
	 */
	public void setShowArchived(boolean b)
	{
		showArchived = b;
	}

	private void setGroupNames(Collection aGroups)
			{

				//recursion breaking condition 
				if(aGroups == null)
					return;
			
				Iterator groupsIter = aGroups.iterator();
				if(this.getGroup1Id()==null || this.getGroup1Id().equalsIgnoreCase(""))
				this.setGroup1Name("");
						if(this.getGroup2Id()==null || this.getGroup2Id().equalsIgnoreCase(""))
				this.setGroup2Name("");
						if(this.getGroup3Id()==null || this.getGroup3Id().equalsIgnoreCase(""))
				this.setGroup3Name("");
				while(groupsIter.hasNext())
				{
					 GroupResponseEvent eachGroup = (GroupResponseEvent) groupsIter.next();

					if(this.getGroup1Id() != null && this.getGroup1Id().equals(eachGroup.getGroupId()))
					{
						this.setGroup1Name(eachGroup.getName());
					}
					if(this.getGroup2Id() != null && this.getGroup2Id().equals(eachGroup.getGroupId()))
					{
						this.setGroup2Name(eachGroup.getName());
					}
					if(this.getGroup3Id() != null && this.getGroup3Id().equals(eachGroup.getGroupId()))
					{
						this.setGroup3Name(eachGroup.getName());
					}

					//recursion here
						 setGroupNames(eachGroup.getSubgroups());

					}
   
					return;
			}	  
	/**
	 * @return
	 */
	public CSTaskConfiguration getTasks()
	{
		if(tasks==null)
			tasks=new CSTaskConfiguration(true,this.agencyId);
		return tasks;
	}

	/**
	 * @param configuration
	 */
	public void setTasks(CSTaskConfiguration configuration)
	{
		tasks = configuration;
	}

	/**
	 * @return
	 */
	public String getEventCount()
	{
		return eventCount;
	}

	/**
	 * @param string
	 */
	public void setEventCount(String string)
	{
		eventCount= string;
		if(eventCount==null){
			eventCountDesc="";
					return;	
				}
		if(eventCount.equalsIgnoreCase("0"))
					eventCountDesc= "";
				else if(eventCount.equalsIgnoreCase("1")){
					eventCountDesc= "Immediate";
				}
			else
		eventCountDesc=eventCount;
	}

	/**
	 * @return
	 */
	public String getClearUpdateHidden()
	{
		return clearUpdateHidden;
	}

	/**
	 * @param string
	 */
	public void setClearUpdateHidden(String string)
	{
		clearUpdateHidden = string;
	}

	/**
	 * @return
	 */
	public String getEffectiveDateSpecial()
	{
		return effectiveDateSpecial;
	}

	/**
	 * @param string
	 */
	public void setEffectiveDateSpecial(String string)
	{
		effectiveDateSpecial = string;
	}

	/**
	 * @return
	 */
	public String getSelectedValue()
	{
		return selectedValue;
	}

	/**
	 * @param string
	 */
	public void setSelectedValue(String string)
	{
		selectedValue = string;
	}

	/**
	 * @return
	 */
	public String getClearEmailTasks()
	{
		return clearEmailTasks;
	}

	/**
	 * @param string
	 */
	public void setClearEmailTasks(String string)
	{
		clearEmailTasks = string;
	}

	/**
	 * @return
	 */
	public String getConditionSpanishLiteral()
	{
		return conditionSpanishLiteral;
	}

	/**
	 * @return
	 */
	public String getConditionSpanishLiteralPreview()
	{
		return conditionSpanishLiteralPreview;
	}

	/**
	 * @return
	 */
	public String getFixedSpanishLiteral()
	{
		return fixedSpanishLiteral;
	}

	/**
	 * @param string
	 */
	public void setConditionSpanishLiteral(String string)
	{
		conditionSpanishLiteral = string;
	}

	/**
	 * @param string
	 */
	public void setConditionSpanishLiteralPreview(String string)
	{
		conditionSpanishLiteralPreview = string;
	}

	/**
	 * @param string
	 */
	public void setFixedSpanishLiteral(String string)
	{
		fixedSpanishLiteral = string;
	}

	/**
	 * @return
	 */
	public boolean isInUse()
	{
		return inUse;
	}

	/**
	 * @param b
	 */
	public void setInUse(boolean b)
	{
		inUse = b;
	}

	/**
	 * @return Returns the deleteSupervisionConditionEvent.
	 */
	public DeleteSupervisionConditionEvent getDeleteSupervisionConditionEvent() {
		return deleteSupervisionConditionEvent;
	}
	/**
	 * @param deleteSupervisionConditionEvent The deleteSupervisionConditionEvent to set.
	 */
	public void setDeleteSupervisionConditionEvent(DeleteSupervisionConditionEvent deleteSupervisionConditionEvent) {
		this.deleteSupervisionConditionEvent = deleteSupervisionConditionEvent;
	}
	/**
	 * @return Returns the oldSelSupervisionTypes.
	 */
	public String[] getOldSelSupervisionTypes() {
		return oldSelSupervisionTypes;
	}
	/**
	 * @param oldSelSupervisionTypes The oldSelSupervisionTypes to set.
	 */
	public void setOldSelSupervisionTypes(String[] oldSelSupervisionTypes) {
		this.oldSelSupervisionTypes = oldSelSupervisionTypes;
		String[] oldSelSupTypeIds1 = this.getOldSelSupervisionTypes();
		StringBuffer oldSelSupTypes1 = new StringBuffer();

	    for(int i=0;i<oldSelSupTypeIds1.length;i++)
	    {
	      if(this.getSupervisionTypesMap().containsKey(oldSelSupTypeIds1[i])){
	      	oldSelSupTypes1.append((String)this.getSupervisionTypesMap().get(oldSelSupTypeIds1[i]));
			  if(i<oldSelSupTypeIds1.length-1)
			  	oldSelSupTypes1.append(", ");
	      }
	    }
	    this.setOldDispSelSupTypes(oldSelSupTypes1.toString());
	}
	/**
	 * @return Returns the oldDispSelSupTypes.
	 */
	public String getOldDispSelSupTypes() {
		return oldDispSelSupTypes;
	}
	/**
	 * @param oldDispSelSupTypes The oldDispSelSupTypes to set.
	 */
	public void setOldDispSelSupTypes(String oldDispSelSupTypes) {
		this.oldDispSelSupTypes = oldDispSelSupTypes;
	}

	public String getSupervisionTypeSummary() {
		return supervisionTypeSummary;
	}

	public void setSupervisionTypeSummary(String supervisionTypeSummary) {
		this.supervisionTypeSummary = supervisionTypeSummary;
	}
	
	
}
