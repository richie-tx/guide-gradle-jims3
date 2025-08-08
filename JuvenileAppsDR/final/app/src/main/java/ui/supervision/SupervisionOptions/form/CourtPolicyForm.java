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
 * add, modify, copy and delete Court Policy.
 */
public class CourtPolicyForm extends ActionForm
{
	public final static String VARIABLE = "Variable";
	public final static String FIXED = "Fixed";
	public final static String STANDARD = "Standard";
	public final static String NON_STANDARD = "Non-Standard";
	public final static String COURT = "Court";
	public final static String DEPARTMENT = "Department";

	private Collection groups;
	private String agencyId;
	private Collection standards;

	private boolean inUse=false;

	//TODO  get the actual value
	private String policyId;
	private String group1Id;
	private String group2Id;
	private String group3Id;
	private String courtPolicyId; //id selected to be operated on (search codition policy use-case)

	private String group1Name;
	private String group2Name;
	private String group3Name;

	private String severityLevelId;
	private String severityLevel;
	private String jurisdictionId;
	private String jurisdiction;

	private boolean standard;
	private String standardSearchCriteria;
	private String courtPolicyLiteral;
	private String courtPolicyLiteralPreview;
	private String effectiveDate;
	private String inactiveDate;
	private String notes;

	private String courtPolicyStatus = PDCodeTableConstants.STATUS_ACTIVE;

	private String eventCount;
	private String eventCountDesc;
	private String periodValue;
	private String periodId;
	private String period;

	private CSTaskConfiguration tasks = null;// new CSTaskConfiguration(true);
	
	private String selectedValue="";

	private String taskSubject;
	private Collection recipientList;
	private Collection taskListType;
	private String taskDueBy;

	private String notifyTaskViaEmailTo;
	private String courtPolicyName;
	private String eventTypesAsString;
	private Collection courts;
	private boolean allCourtsSelected;
	private boolean isClearEventTypes = false;
	private boolean onlyMatch = false;

	private Collection courtPolicyDetails;

	private Collection detailDictionary;
	private Collection filteredDetailDictionary;
	private Collection variableElementResponseEvents;
	private Collection variableElementNames;
	private HashMap detailDictionaryNameIdHashMap;

	private HashMap severityLevelsMap;
	private HashMap jurisdictionsMap;
	private HashMap documentsMap;
	private HashMap eventTypesMap;
	private HashMap periodsMap;

	private boolean isDepartmentPolicy;

	private Collection selectedCourts;
	private Collection exceptionCourtVarElemBeans;

	private Collection courtPolicySearchResults;

	private String selectedEventTypes;
	private String[] selVarElementTypes;
	private String[] selectedVariableElementTypes;
	private String[] varElementValues;

	private String[] selectedEventTypeIds;
	private String[] selDocumentIds;
	private String selEventTypes;

	private String ddName;
	private String ddDescription;
	private String ddType;

	private String action;
	private String pageType;

	private String deleteNote;

	//these are added to aid jsp display easier
	private Collection group2;
	private Collection group3;

	private Collection associatedConditions = new ArrayList();
	private Collection removedAssociations = new ArrayList();

	// Associate Court/Dept Policy attributes
	private AssociateBean assocSearchCriteria = new AssociateBean();
	private String searchResultText;
	private Collection assocSearchResults = new ArrayList(); // of AssociateBean
	private String[] selectInd;
	private Map assocSearchResultsMap = new HashMap();

	private boolean viewOnly; //is the view page view only, or can you update it

	//	Used just for clearing the form
	 private String clearUpdateHidden="";
	private String clearEmailTasks="";

	/**
	 * Constructor for the RoleGroupForm 
	 */
	public CourtPolicyForm()
	{
		super();
	}
	public void clearDetailDictionary(){
		ddName="";
		ddDescription="";
		ddType="All";
	}
	
	/**
	 * @param collection
	 */
	public void clearAssocSearchResults()
	{
		assocSearchResults.clear();
		assocSearchResultsMap.clear();
	}
	
	/**
	 * @param aRequest
	 */
	public void reset(ActionMapping aMapping, HttpServletRequest aRequest)
	{
		resetEventTypes(aMapping, aRequest);
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

	public void resetEventTypes(ActionMapping mapping, HttpServletRequest request)
	{
		Object obj = request.getAttribute("clearEventTypes");
		if (obj != null)
		{
			selectedEventTypeIds = null;
			selectedEventTypes = null;
			selEventTypes = "";
		}
		obj = null;
		obj = request.getParameter("clearEventTypes");
		if (obj != null)
		{
			selectedEventTypeIds = null;
			selectedEventTypes = null;
			selEventTypes = "";
		}
	}

	/**
	 * @param aRequest
	 */
	public void clear()
	{
		groups = null;

		standards = null;

		policyId = null;
		group1Id = null;
		group2Id = null;
		group3Id = null;
		courtPolicyId = null; //id selected to be operated on (search codition policy use-case)

		group1Name = null;
		group2Name = null;
		group3Name = null;

		severityLevelId = null;
		severityLevel = null;
		jurisdictionId = null;
		jurisdiction = null;

		standard = false;
		standardSearchCriteria = null;

		courtPolicyLiteral = null;
		effectiveDate = null;
		inactiveDate = null;
		notes = null;

		courtPolicyStatus = PDCodeTableConstants.STATUS_ACTIVE;

		eventCount = null;
		eventCountDesc=null;
		periodValue = null;
		periodId = null;
		period = null;

		clearTaskConfiguation();

		notifyTaskViaEmailTo = null;
		courtPolicyName = null;
		eventTypesAsString = null;
		courts = null;
		allCourtsSelected = false;
		onlyMatch = false;
		courtPolicyDetails = null;

		//detailDictionary = null; - detail dictionary doesnt really change
		filteredDetailDictionary = null;
		variableElementResponseEvents = null;
		variableElementNames = null;
		detailDictionaryNameIdHashMap = null;

		severityLevelsMap = null;
		jurisdictionsMap = null;
		documentsMap = null;
		eventTypesMap = null;
		periodsMap = null;

		selectedCourts = null;
		exceptionCourtVarElemBeans = null;

		courtPolicySearchResults = null;

		selectedEventTypes = null;
		selVarElementTypes = null;
		selectedVariableElementTypes = null;
		varElementValues = null;

		selectedEventTypeIds = null;
		selDocumentIds = null;
		selEventTypes = null;

		ddName = null;
		ddDescription = null;

		action = null;
		pageType = null;

		deleteNote = null;

		group2 = null;
		group3 = null;

		viewOnly = false;

		isDepartmentPolicy = false;

		associatedConditions.clear();
		removedAssociations.clear();

		assocSearchCriteria.clear();
		searchResultText = "";
		assocSearchResults.clear(); // of AssociateBean
		selectInd = new String[0];
		assocSearchResultsMap.clear();
		inUse=false;

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
		eventCount = string;
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

	public Collection getSeverityLevels()
		{
			Collection severityLevels = CodeHelper.getSeverityLevels(getAgencyId());
			setSeverityLevelsMap(UISupervisionOptionHelper.createCodeTableMapping(severityLevels));
			Collections.sort((ArrayList) severityLevels);
			return severityLevels;
		}

	/**
	 * @return
	 */
	public String getAgencyId()
	{
		if (agencyId == null || agencyId.equals(""))
		{
			agencyId = SecurityUIHelper.getUserAgencyId();
		}
		return agencyId;
	}

	// get collections
	public Collection getPeriods()
	{
		Collection periods = CodeHelper.getPeriods();
		setPeriodsMap(UISupervisionOptionHelper.createCodeTableMapping(periods));

		return periods;
	}

	public Collection getEventTypes()
	{
		Collection eventTypes = CodeHelper.getEventTypes(getAgencyId());
		setEventTypesMap(UISupervisionOptionHelper.createCodeTableMapping(eventTypes));

		return eventTypes;
	}

	public Collection getStatusCodes()
	{
		Collection statuses = CodeHelper.getPolicyStatuses();
		return statuses;
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
		cr.setDescription("All");
		col.add(cr);

		cr = new CodeResponseEvent();
		cr.setCode("true");
		cr.setDescription(STANDARD);
		col.add(cr);

		cr = new CodeResponseEvent();
		cr.setCode("false");
		cr.setDescription(NON_STANDARD);
		col.add(cr);
		return col;
	}

	/**
	 * @return
	 */
	public HashMap getDetailDictionaryNameIdHashMap()
	{
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
	public String[] getSelVarElementTypes()
	{
		return selVarElementTypes;
	}

	/**
	 * @param strings
	 */
	public void setSelVarElementTypes(String[] strings)
	{
		selVarElementTypes = strings;
	}

	/**
	 * @return
	 */
	public String getPolicyId()
	{
		return policyId;
	}

	/**
	 * @param string
	 */
	public void setPolicyId(String string)
	{
		policyId = string;
	}

	/**
	 * @return
	 */
	public Collection getCourtPolicyDetails()
	{
		return courtPolicyDetails;
	}

	/**
	 * @return
	 */
	public String getCourtPolicyLiteral()
	{
		return courtPolicyLiteral;
	}

	/**
	 * @return
	 */
	public Collection getCourtPolicySearchResults()
	{
		return courtPolicySearchResults;
	}

	/**
	 * @return
	 */
	public String getCourtPolicyStatus()
	{
		return courtPolicyStatus;
	}

	/**
	 * @param collection
	 */
	public void setCourtPolicyDetails(Collection collection)
	{
		courtPolicyDetails = collection;
	}

	/**
	 * @param string
	 */
	public void setCourtPolicyLiteral(String string)
	{
		if(string!=null )
			string=UIUtil.removeStarting_BR_P_XMLtags(string);
		courtPolicyLiteral = string;
	}

	/**
	 * @param collection
	 */
	public void setCourtPolicySearchResults(Collection collection)
	{
		courtPolicySearchResults = collection;
	}

	/**
	 * @param string
	 */
	public void setCourtPolicyStatus(String string)
	{
		courtPolicyStatus = string;
	}

	/**
	 * @return
	 */
	public String getCourtPolicyName()
	{
		return courtPolicyName;
	}

	/**
	 * @param string
	 */
	public void setCourtPolicyName(String string)
	{
		if (string != null)
			courtPolicyName = string.trim();
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
	public String getCourtPolicyId()
	{
		return courtPolicyId;
	}

	/**
	 * @param string
	 */
	public void setCourtPolicyId(String string)
	{
		courtPolicyId = string;
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
	public String getJurisdictionId()
	{
		return jurisdictionId;
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
	public String getSeverityLevelId()
	{
		return severityLevelId;
	}

	/**
	 * @param string
	 */
	public void setJurisdiction(String string)
	{
		jurisdiction = string;
	}
	
//	get collections
	 public Collection getJurisdictions()
	 {
		 Collection jurisdictions = CodeHelper.getJurisdictions();
		 setJurisdictionsMap(UISupervisionOptionHelper.createCodeTableMapping(jurisdictions));
		 Collections.sort((ArrayList) jurisdictions);

		 return jurisdictions;
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
	 * @param string
	 */
	public void setSeverityLevel(String string)
	{
		severityLevel = string;
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

	/**
	 * @return
	 */
	public HashMap getDocumentsMap()
	{
		return documentsMap;
	}

	/**
	 * @return
	 */
	public HashMap getEventTypesMap()
	{
		return eventTypesMap;
	}

	/**
	 * @return
	 */
	public HashMap getJurisdictionsMap()
	{
		return jurisdictionsMap;
	}

	/**
	 * @return
	 */
	public HashMap getPeriodsMap()
	{
		return periodsMap;
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
	public void setDocumentsMap(HashMap map)
	{
		documentsMap = map;
	}

	/**
	 * @param map
	 */
	public void setEventTypesMap(HashMap map)
	{
		eventTypesMap = map;
	}

	/**
	 * @param map
	 */
	public void setJurisdictionsMap(HashMap map)
	{
		jurisdictionsMap = map;
	}

	/**
	 * @param map
	 */
	public void setPeriodsMap(HashMap map)
	{
		periodsMap = map;
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
	public String[] getSelDocumentIds()
	{
		return selDocumentIds;
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
	public void setSelDocumentIds(String[] strings)
	{
		selDocumentIds = strings;
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
	
	public void clearUpdateHidden(){
				group1Id = null;
				group2Id = null;
				group3Id = null;

				group1Name = "";
				group2Name = "";
				group3Name = "";

				effectiveDate = null;
				inactiveDate = null;

				eventCount = "";
				eventCountDesc="";
				periodValue = "";
				periodId = "";
				period = "";

				selectedEventTypes = "";
				selectedEventTypeIds = new String[0];
				selDocumentIds = new String[0];;
				selectedVariableElementTypes = new String[0];;

		}

	/**
	 * @return
	 */
	public String getSelEventTypes()
	{
		return selEventTypes;
	}

	/**
	 * @param string
	 */
	public void setSelEventTypes(String string)
	{
		selEventTypes = string;
	}

	/**
	 * @return
	 */
	public boolean isStandard()
	{
		return standard;
	}

	/**
	 * @param b
	 */
	public void setStandard(boolean b)
	{
		standard = b;
	}

	/**
	 * @return
	 */
	public String getEventTypesAsString()
	{
		return eventTypesAsString;
	}

	/**
	 * @param string
	 */
	public void setEventTypesAsString(String string)
	{
		eventTypesAsString = string;
	}

	/**
	 * @return
	 */
	public String getSelectedEventTypes()
	{
		return selectedEventTypes;
	}

	/**
	 * @param string
	 */
	public void setSelectedEventTypes(String string)
	{
		selectedEventTypes = string;
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
	 * @return
	 */
	public String getPageType()
	{
		return pageType;
	}

	/**
	 * @return
	 */
	public String[] getSelectedVariableElementTypes()
	{
		return selectedVariableElementTypes;
	}

	/**
	 * @return
	 */
	public String[] getVarElementValues()
	{
		return varElementValues;
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
	 * @param string
	 */
	public void setPageType(String string)
	{
		pageType = string;
	}

	/**
	 * @param strings
	 */
	public void setSelectedVariableElementTypes(String[] strings)
	{
		selectedVariableElementTypes = strings;
	}

	/**
	 * @param strings
	 */
	public void setVarElementValues(String[] strings)
	{
		varElementValues = strings;
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
	public Collection getExceptionCourtVarElemBeans()
	{
		return exceptionCourtVarElemBeans;
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
	public void setExceptionCourtVarElemBeans(Collection collection)
	{
		exceptionCourtVarElemBeans = collection;
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
	public Collection getAssociatedConditions()
	{
		return associatedConditions;
	}

	/**
	 * @param collection
	 */
	public void setAssociatedConditions(Collection collection)
	{
		associatedConditions = collection;
	}

	public void removeAssociatedCondition(AssociateBean asscBean)
	{
		associatedConditions.remove(asscBean);
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
	 * @return
	 */
	public String getSearchResultText()
	{
		return searchResultText;
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
	 * @param string
	 */
	public void setSearchResultText(String string)
	{
		searchResultText = string;
	}

	/**
	 * @return
	 */
	public String getCourtPolicyLiteralPreview()
	{
		return courtPolicyLiteralPreview;
	}

	/**
	 * @param string
	 */
	public void setCourtPolicyLiteralPreview(String string)
	{
		courtPolicyLiteralPreview = string;
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
	public void insertAssociatedCondition(AssociateBean associateBean)
	{
		associatedConditions.add(associateBean);
	}

	/**
	 * @param collection
	 */
	public void clearAssociatedConditions()
	{
		associatedConditions.clear();
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
	public Map getAssocSearchResultsMap()
	{
		return assocSearchResultsMap;
	}

	/**
	 * @return
	 */
	public String[] getSelectInd()
	{
		return selectInd;
	}

	/**
	 * @param map
	 */
	public void setAssocSearchResultsMap(Map map)
	{
		assocSearchResultsMap = map;
	}

	/**
	 * @param strings
	 */
	public void setSelectInd(String[] strings)
	{
		selectInd = strings;
	}

	/*
	 * This is to get different display depending on which agency the user belongs to
	 */
	public String getCourtPolicyTitle()
	{
		if (getAgencyId().equalsIgnoreCase(UIConstants.JUV))
			return "title.consequence";
		else
			if (agencyId.equalsIgnoreCase(UIConstants.PTR))
				return "title.complianceStandards";

		return "title.CSCourtPolicy";

	}

	public String getCourtPolicyLiteralCaption()
	{
		if (getAgencyId().equalsIgnoreCase(UIConstants.JUV))
			return "prompt.literal";
		else
			if (agencyId.equalsIgnoreCase(UIConstants.PTR))
				return "prompt.literal";

		return "title.CSCourtPolicy";
	}

	public String getCourtGroup1Caption()
	{
		if (getAgencyId().equalsIgnoreCase(UIConstants.JUV))
			return "prompt.category";
		else
			if (agencyId.equalsIgnoreCase(UIConstants.PTR))
				return "prompt.complianceType";

		return "prompt.group1";
	}

	public String getCourtGroup2Caption()
	{
		if (getAgencyId().equalsIgnoreCase(UIConstants.JUV))
			return "prompt.type";
		else
			if (agencyId.equalsIgnoreCase(UIConstants.PTR))
				return "prompt.complianceSubType";

		return "prompt.group2";
	}

	public String getCourtGroup3Caption()
	{
		if (getAgencyId().equalsIgnoreCase(UIConstants.JUV))
			return "prompt.subtype";
		else
			if (agencyId.equalsIgnoreCase(UIConstants.PTR))
				return "prompt.subTypeDetail";

		return "prompt.group3";
	}

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
			public boolean isAgencyJUV()
			{
				if(UIConstants.JUV.equals(getAgencyId())){
					return true;
				}
				return false;
			}




		/**
				 * @return
				 */
				public boolean isAgencyCSC()
				{
					if(UIConstants.CSC.equals(getAgencyId())){
						return true;
					}
					return false;
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
	public boolean isDepartmentPolicy()
	{
		return isDepartmentPolicy;
	}

	/**
	 * @param b
	 */
	public void setDepartmentPolicy(boolean b)
	{
		isDepartmentPolicy = b;
	}

	/**
	 * @return
	 */
	public boolean isClearEventTypes()
	{
		return isClearEventTypes;
	}

	/**
	 * @param b
	 */
	public void setClearEventTypes(boolean b)
	{
		isClearEventTypes = b;
	}

	/**
	 * @return
	 */
	public boolean isOnlyMatch()
	{
		return onlyMatch;
	}

	/**
	 * @param b
	 */
	public void setOnlyMatch(boolean b)
	{
		onlyMatch = b;
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
	public void setDdType(String string)
	{
		ddType = string;
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
	public String getEventCount()
	{
		return eventCount;
	}

	/**
	 * @param string
	 */
	public void setEventCount(String string)
	{
		eventCount = string;
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
	
	public void populateGroupsForDisplay()
	 {
	
		Iterator group1Iter = getGroups().iterator();
		while(group1Iter.hasNext())
		{
			GroupResponseEvent group1 = (GroupResponseEvent)group1Iter.next();
			if(group1.getGroupId().equals(getGroup1Id()))
			{
				Collection group2s = group1.getSubgroups();
	
				if(group2s != null && group2s.size() > 0)
				{
					setGroup2(group2s); //save group2 collection in the form
					Iterator group2Iter = group2s.iterator();
					while(group2Iter.hasNext())
					{
						GroupResponseEvent group2 = (GroupResponseEvent)group2Iter.next();
						if(group2.getGroupId().equals(getGroup2Id()))
						{
							Collection group3s = group2.getSubgroups();
							if(group3s != null && group3s.size() > 0)
							{
								setGroup3(group3s); //save group3 collection in the form
							}
						}
					}
				}
			}
		}
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

	public CSTaskConfiguration getTasks()
	{
		if(tasks==null)
			tasks=new CSTaskConfiguration(true, this.agencyId);
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

}
