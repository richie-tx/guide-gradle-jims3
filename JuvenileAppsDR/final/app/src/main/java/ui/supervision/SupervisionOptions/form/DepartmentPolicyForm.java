/*
 * Created on Jun 1, 2004
 */
package ui.supervision.SupervisionOptions.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
public class DepartmentPolicyForm extends ActionForm
{

	private Collection groups;

	//TODO  get the actual value
	private String agencyId;
	private String policyId;

	private String group1Id;
	private String group2Id;
	private String group3Id;

	private String group1Name;
	private String group2Name;
	private String group3Name;

	private String departmentPolicy; // 29aug2005, mjt, changed from 'conditionLiteral' 
	private String effectiveDate;
	private String inactiveDate;
	private String notes;

	private String deleteNotes; // 29aug2005, mjt, added

	private String departmentPolicyStatus;
	private String status = PDCodeTableConstants.STATUS_ACTIVE;
	private String name;

	private String action; // 31aug2005, mjt, added - page 
	private String pageType; // 31aug2005, mjt, added - page 
	private String deleteNote;

	private Collection courtsIds;

	private Collection searchResults;

	private Collection courts;
	private boolean allCourtsSelected;
	private Collection selectedCourts;

	private Map statusMap;
	
	private boolean inUse=false;

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

	/**
	 * Constructor for the RoleGroupForm 
	 */
	public DepartmentPolicyForm()
	{
		super();
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
		
		
		obj = aRequest.getParameter("clearSelectInd");
		if (obj != null)
		{
			String clearSelectInd = (String) obj;
			if (clearSelectInd.equals("true"));
			{
				this.setSelectInd(null);
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
	}
	/**
	 * @param aRequest
	 */
	public void clear()
	{
		groups = null;

		group1Id = null;
		group2Id = null;
		group3Id = null;
		group1Name = null;
		group2Name = null;
		group3Name = null;

		departmentPolicy = null; // 29aug2005, mjt, changed from 'conditionLiteral'
		effectiveDate = null;
		inactiveDate = null;
		notes = null;
		deleteNotes = null; // 29aug2005, mjt, added
		deleteNote = null;
		name = null;
		inactiveDate = null; // 31aug2005, mjt, added
		setAction(UIConstants.CREATE);
		selectedCourts = null;
		searchResults = null;

		viewOnly = false;

		searchResultText = "";
		selectInd = new String[0];
		assocSearchCriteria.clear();
		assocSearchResultsMap.clear();
		assocSearchResults.clear();
		associatedConditions.clear();
		removedAssociations.clear();
		inUse=false;
	}

	/**
	 * @return
	 */
	public String getDepartmentPolicy() // 29aug2005, mjt, changed from 'getConditionLiteral'
	{
		return departmentPolicy; // 29aug2005, mjt, changed from 'conditionLiteral'
	}

	/**
	 * @return
	 */
	public Collection getCourtsIds()
	{
		return courtsIds;
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
	public Collection getGroups()
	{
		if (groups == null || groups.size() == 0)
		{
			// get groups	
			groups = UISupervisionOptionHelper.fetchGroups(agencyId);
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

	/** 29aug2005, mjt, added
	 * @return
	 */
	public String getDeleteNotes()
	{
		return deleteNotes;
	}

	/**
	 * @param string
	 */
	public void setDepartmentPolicy(String string) // 29aug2005, mjt, changed from 'setConditionLiteral'
	{
		if(string!=null )
			string=UIUtil.removeStarting_BR_P_XMLtags(string);
		departmentPolicy = string; // 29aug2005, mjt, changed from 'conditionLiteral'
	}

	/**
	 * @param collection
	 */
	public void setCourtsIds(Collection collection)
	{
		courtsIds = collection;
	}

	/**
	 * @param string
	 */
	public void setEffectiveDate(String string)
	{
		effectiveDate = string;
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

	/**  29aug2005, mjt, added
	 * @param string
	 */
	public void setDeleteNotes(String string)
	{
		deleteNotes = string;
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
	public String getAgencyId()
	{
		if (agencyId == null || agencyId.equals(""))
		{
			agencyId = SecurityUIHelper.getUserAgencyId();
		}
		return agencyId;
	}

	/**
	 * @param string
	 */
	public void setAgencyId(String string)
	{
		agencyId = string;
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
	public String getName()
	{
		return name;
	}

	/**
	 * @return
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * @param string
	 */
	public void setName(String string)
	{
		if (string != null)
			name = string.trim();
	}

	/**
	 * @param string
	 */
	public void setStatus(String string)
	{
		status = string;
	}

	/**
	 * @return
	 */
	public Collection getSearchResults()
	{
		return searchResults;
	}

	/**
	 * @param collection
	 */
	public void setSearchResults(Collection collection)
	{
		searchResults = collection;
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
	 * @param collection
	 */
	public void setCourts(Collection collection)
	{
		courts = collection;
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
	public String getDepartmentPolicyStatus()
	{
		return departmentPolicyStatus;
	}

	/**
	 * @param string
	 */
	public void setDepartmentPolicyStatus(String string)
	{
		departmentPolicyStatus = string;
	}

	public Collection getStatusCodes()
	{
		Collection statusCodes = CodeHelper.getSupervisionConditionStatuses();
		setStatusMap(UISupervisionOptionHelper.createCodeTableMapping(statusCodes));

		return statusCodes;
	}

	/**
	 * @return
	 */
	public Map getStatusMap()
	{
		return statusMap;
	}

	/**
	 * @param map
	 */
	public void setStatusMap(Map map)
	{
		statusMap = map;
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
	public void removeAssociatedCondition(AssociateBean associateBean)
	{
		associatedConditions.remove(associateBean);
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

	/*
	 * This is to get different display depending on which agency the user belongs to
	 */
	public String getDeptPolicyTitle()
	{
		if (getAgencyId().equalsIgnoreCase(UIConstants.PTR))
			return "title.procedures";
		return "prompt.policyTableTitle";

	}

	public String getDepartmentPolicyLiteralCaption()
	{
		if (getAgencyId().equalsIgnoreCase(UIConstants.JUV))
			return "prompt.procedure";

		return "prompt.policyTableTitle";
	}

	public String getDepartmentGroup1Caption()
	{
		if (getAgencyId().equalsIgnoreCase(UIConstants.JUV))
			return "prompt.category";
		else
			if (agencyId.equalsIgnoreCase(UIConstants.PTR))
				return "prompt.procedureType";

		return "prompt.group1";
	}

	public String getDepartmentGroup2Caption()
	{
		if (getAgencyId().equalsIgnoreCase(UIConstants.JUV))
			return "prompt.type";
		else
			if (agencyId.equalsIgnoreCase(UIConstants.PTR))
				return "prompt.procedureSubType";

		return "prompt.group2";
	}

	public String getDepartmentGroup3Caption()
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
	 * @return Returns the inUse.
	 */
	public boolean isInUse() {
		return inUse;
	}
	/**
	 * @param inUse The inUse to set.
	 */
	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}
}
