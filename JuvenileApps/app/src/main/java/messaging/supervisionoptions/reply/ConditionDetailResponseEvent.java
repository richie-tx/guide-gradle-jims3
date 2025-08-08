/*
 * Created on Oct 10, 2005
 *
 */
package messaging.supervisionoptions.reply;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import naming.SupervisionConstants;

import mojo.km.messaging.ResponseEvent;

/**
 * @author bschwartz
 *
 */
public class ConditionDetailResponseEvent extends ResponseEvent implements Comparable
{
	private String agencyId;
	private String conditionId;
	private String name;
	private String group1Id;
	private String group1Name;
	private String group2Id;
	private String group2Name;
	private String group3Id;
	private String group3Name;
	private String description;
    private String spanishDescription;
	private Date effectiveDate;
	private Date inactiveDate;
	private boolean isStandard;
	private boolean isDeleted=false;
	private String notes;
	private String severityLevelId;
	private Collection documents = new ArrayList();
	private String jurisdictionId;
	private Collection eventTypes = new ArrayList();
	private int eventCount;
	private int eventPeriodValue;
	private String eventPeriodTypeId;
	private Collection taskRecipients = new ArrayList();
	private String taskSubject;
	private int taskDueBy;
	private String emailNotificationTo;
	private Collection variableElements = new ArrayList();
	private Collection supervisionTypes = new ArrayList();
	private String compareToPreviousVersion = "";
	private boolean likeConditionInd;
	private String resolvedDescription;
	private boolean specialCondition;
	private String specialConditionDesc;
	private int sequenceNum;
	private String conditionLiteralPreview;
	private String status;
	private String inactiveReason;
	private boolean conditionEmpty=false;
	private boolean nonCourtApplicable=false;
	private String statusId="";
	private HashSet allCourtIds;


	public String getStandardDesc()
		{
			String string = "";
			if(isStandard){
				string = SupervisionConstants.STANDARD_ONLY_CONDITION_DESC;
			}else {
				string = SupervisionConstants.NON_STANDARD_ONLY_CONDITION_DESC;
			}
			return string;
		}
	/**
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * 
	 */
	public void setName(String aName)
	{
		name = aName;
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
	}

	/**
	 * @param string
	 */
	public void setGroup2Id(String string)
	{
		group2Id = string;
	}

	/**
	 * @param string
	 */
	public void setGroup3Id(String string)
	{
		group3Id = string;
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
	public String getAgencyId()
	{
		return agencyId;
	}

	/**
	 * @return
	 */
	public String getDescription()
	{
		return description;
	}

    /**
     * @return
     */
    public String getSpanishDescription()
    {
        return spanishDescription;
    }
    
	/**
	 * @return
	 */
	public Date getEffectiveDate()
	{
		return effectiveDate;
	}

	/**
	 * @return
	 */
	public boolean isStandard()
	{
		return isStandard;
	}

	/**
	 * @return
	 */
	public String getNotes()
	{
		return notes;
	}

	/**
	 * @param string
	 */
	public void setAgencyId(String string)
	{
		agencyId = string;
	}

	/**
	 * @param string
	 */
	public void setDescription(String string)
	{
		description = string;
	}
    
    /**
     * @param string
     */
    public void setSpanishDescription(String string)
    {
        spanishDescription = string;
    }    

	/**
	 * @param date
	 */
	public void setEffectiveDate(Date date)
	{
		effectiveDate = date;
	}

	/**
	 * @param b
	 */
	public void setStandard(boolean b)
	{
		isStandard = b;
	}

	/**
	 * @param string
	 */
	public void setNotes(String string)
	{
		notes = string;
	}

	/**
	 * @return
	 */
	public Collection getDocuments()
	{
		return documents;
	}

	/**
	 * @return
	 */
	public String getSeverityLevelId()
	{
		return severityLevelId;
	}

	/**
	 * @param collection
	 */
	public void addDocument(String docId)
	{
		documents.add(docId);
		;
	}

	/**
	 * @param string
	 */
	public void setSeverityLevelId(String string)
	{
		severityLevelId = string;
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
	}

	/**
	 * @return
	 */
	public Collection getEventTypes()
	{
		return eventTypes;
	}

	/**
	 * @param collection
	 */
	public void addEventType(String eventTypeId)
	{
		eventTypes.add(eventTypeId);
	}

	/**
	 * @return
	 */
	public int getEventCount()
	{
		return eventCount;
	}

	/**
	 * @param string
	 */
	public void setEventCount(int count)
	{
		eventCount = count;
	}

	/**
	 * @return
	 */
	public int getEventPeriodValue()
	{
		return eventPeriodValue;
	}

	/**
	 * @param i
	 */
	public void setEventPeriodValue(int i)
	{
		eventPeriodValue = i;
	}

	/**
	 * @return
	 */
	public String getEventPeriodTypeId()
	{
		return eventPeriodTypeId;
	}

	/**
	 * @param string
	 */
	public void setEventPeriodTypeId(String string)
	{
		eventPeriodTypeId = string;
	}

	/**
	 * @return
	 */
	public Collection getTaskRecipients()
	{
		return taskRecipients;
	}

	/**
	 * @param collection
	 */
	public void setTaskRecipients(Collection collection)
	{
		taskRecipients = collection;
	}

	/**
	 * @return
	 */
	public String getTaskSubject()
	{
		return taskSubject;
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
	public int getTaskDueBy()
	{
		return taskDueBy;
	}

	/**
	 * @param i
	 */
	public void setTaskDueBy(int i)
	{
		taskDueBy = i;
	}

	/**
	 * @return
	 */
	public String getEmailNotificationTo()
	{
		return emailNotificationTo;
	}

	/**
	 * @param string
	 */
	public void setEmailNotificationTo(String string)
	{
		emailNotificationTo = string;
	}

	/**
	 * @return
	 */
	public Collection getVariableElements()
	{
		return variableElements;
	}

	/**
	 * @param collection
	 */
	public void addVariableElement(VariableElementResponseEvent varElem)
	{
		variableElements.add(varElem);
		if(this.variableElements!=null && this.variableElements.size()>0 && varElem!=null){
			Collections.sort((List)this.variableElements);
		}
	}

	/**
	 * @return
	 */
	public void setVariableElements(Collection veres)
	{
		
		this.variableElements.addAll(veres);
		if(this.variableElements!=null && this.variableElements.size()>0){
			Collections.sort((List)this.variableElements);
		}
	}

	/**
	 * @return
	 */
	public Date getInactiveDate()
	{
		return inactiveDate;
	}

	/**
	 * @param date
	 */
	public void setInactiveDate(Date date)
	{
		inactiveDate = date;
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
	public Collection getSupervisionTypes()
	{
		return supervisionTypes;
	}

	/**
	 * @param collection
	 */
	public void setSupervisionTypes(Collection collection)
	{
		supervisionTypes = collection;
	}

	public void addSupervisionType(String supervisionTypeId)
	{
		supervisionTypes.add(supervisionTypeId);
	}

	/**
	 * @return
	 */
	public String getCompareToPreviousVersion()
	{
		return compareToPreviousVersion;
	}

	/**
	 * @param string
	 */
	public void setCompareToPreviousVersion(String string)
	{
		compareToPreviousVersion = string;
	}

	/**
	 * @return
	 */
	public boolean getLikeConditionInd()
	{
		return likeConditionInd;
	}

	/**
	 * @param b
	 */
	public void setLikeConditionInd(boolean b)
	{
		likeConditionInd = b;
	}

	/**
	 * @return
	 */
	public String getResolvedDescription()
	{
		return resolvedDescription;
	}

	/**
	 * @param string
	 */
	public void setResolvedDescription(String string)
	{
		resolvedDescription = string;
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
	public String getSpecialConditionDesc()
	{
		return specialConditionDesc;
	}

	/**
	 * @param string
	 */
	public void setSpecialConditionDesc(String string)
	{
		specialConditionDesc = string;
	}


	/**
	 * @return
	 */
	public int getSequenceNum()
	{
		return sequenceNum;
	}

	/**
	 * @param i
	 */
	public void setSequenceNum(int i)
	{
		sequenceNum = i;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object arg0)
	{
		String newString = "";
		ConditionDetailResponseEvent cdre = (ConditionDetailResponseEvent) arg0;
		if (this.name == null)
		{
			this.name = newString;
		}
		if (this.group1Name == null)
		{
			this.group1Name = newString;
		}
		if (this.group2Name == null)
		{
			this.group2Name = newString;
		}
		if (this.group3Name == null)
		{
			this.group3Name = newString;
		}
		if (cdre.name == null)
		{
			cdre.name = newString;
		}
		if (cdre.group1Name == null)
		{
			cdre.group1Name = newString;
		}
		if (cdre.getGroup2Name() == null)
		{
			cdre.group2Name = newString;
		}
		if (cdre.getGroup3Name() == null)
		{
			cdre.group3Name = newString;
		}
		int comparisonResult = 0;
		if (this.name.compareTo(cdre.getName()) == 0)
		{
			if (this.group1Name.compareTo(cdre.getGroup1Name()) == 0)
			{
				if (this.group2Name.compareTo(cdre.getGroup2Name()) == 0)
				{
					comparisonResult = this.group3Name.compareTo(cdre.getGroup3Name());
				}
				else
				{
					comparisonResult = this.group2Name.compareTo(cdre.getGroup2Name());
				}
			}
			else
			{
				comparisonResult = this.group1Name.compareTo(cdre.getGroup1Name());
			}
		}
		else
		{
			comparisonResult = this.name.compareTo(cdre.getName());
		}
		
		return comparisonResult;
	}

	public static Comparator SeqNumComparator = new Comparator() {
		public int compare(Object conditionDetailRE, Object otherConditionDetailRE) {
		  int seqNum = ((ConditionDetailResponseEvent)conditionDetailRE).getSequenceNum();
		  int otherSeqNum = ((ConditionDetailResponseEvent)otherConditionDetailRE).getSequenceNum();
		  
		  Integer seqNumInt = new Integer(seqNum);
		  Integer otherSeqNumInt = new Integer(otherSeqNum);
		  return seqNumInt.compareTo(otherSeqNumInt);
		}	
	};

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

	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return Returns the isDeleted.
	 */
	public boolean isDeleted() {
		return isDeleted;
	}
	/**
	 * @param isDeleted The isDeleted to set.
	 */
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	/**
	 * @return Returns the inactiveReason.
	 */
	public String getInactiveReason() {
		return inactiveReason;
	}
	/**
	 * @param inactiveReason The inactiveReason to set.
	 */
	public void setInactiveReason(String inactiveReason) {
		this.inactiveReason = inactiveReason;
	}
	/**
	 * @return Returns the conditionEmpty.
	 */
	public boolean isConditionEmpty() {
		return conditionEmpty;
	}
	/**
	 * @param conditionEmpty The conditionEmpty to set.
	 */
	public void setConditionEmpty(boolean conditionEmpty) {
		this.conditionEmpty = conditionEmpty;
	}
	/**
	 * @return Returns the nonCourtApplicable.
	 */
	public boolean isNonCourtApplicable() {
		return nonCourtApplicable;
	}
	/**
	 * @param nonCourtApplicable The nonCourtApplicable to set.
	 */
	public void setNonCourtApplicable(boolean nonCourtApplicable) {
		this.nonCourtApplicable = nonCourtApplicable;
	}
	/**
	 * @return Returns the statusId.
	 */
	public String getStatusId() {
		return statusId;
	}
	/**
	 * @param statusId The statusId to set.
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	/**
	 * @return Returns the allCourtIds.
	 */
	public HashSet getAllCourtIds() {
		return allCourtIds;
	}
	/**
	 * @param allCourtIds The allCourtIds to set.
	 */
	public void setAllCourtIds(HashSet allCourtIds) {
		this.allCourtIds = allCourtIds;
	}
}
