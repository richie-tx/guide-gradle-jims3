/*
 * Created on Oct 10, 2005
 *
 */
package messaging.supervisionoptions.reply;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import mojo.km.messaging.ResponseEvent;

/**
 * @author bschwartz
 *
 */
public class ConditionResponseEvent extends ResponseEvent implements Comparable
{
	private String agencyId;
	private String conditionId;
	private String conditionName;
	private Date effectiveDate;
	private String group1Id;
	private String group1Name;
	private String group2Id;
	private String group2Name;
	private String group3Id;
	private String group3Name;
	private Date inactiveDate;
	private boolean isStandard;
	private String name;
	private String status;
	private String description;
	// Attributes for Special Condition
	private String cdi;
	private String caseNum;
	private String superviseeName;	
	private String resolvedDescription;
	private String notes;
	private String courtId;
	private String courtCategory;
	private boolean isArchived;
	private boolean isSpecialCondition;
	private String conditionLiteralPreview;
	private boolean isDeleted=false;
	private List supervisionType;
	private String SupervisionTypeSummary;
	private String listSize;
	

	public String getListSize() {
		return listSize;
	}

	public void setListSize(String listSize) {
		this.listSize = listSize;
	}

	public List getSupervisionType() {
		return supervisionType;
	}

	public void setSupervisionType(List supervisionType) {
		this.supervisionType = supervisionType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object arg0)
	{
		String newString = "";
		ConditionResponseEvent cre = (ConditionResponseEvent) arg0;
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
		if (cre.name == null)
		{
			cre.name = newString;
		}
		if (cre.group1Name == null)
		{
			cre.group1Name = newString;
		}
		if (cre.getGroup2Name() == null)
		{
			cre.group2Name = newString;
		}
		if (cre.getGroup3Name() == null)
		{
			cre.group3Name = newString;
		}
		int comparisonResult = 0;
		if (this.name.compareTo(cre.getName()) == 0)
		{
			if (this.group1Name.compareTo(cre.getGroup1Name()) == 0)
			{
				if (this.group2Name.compareTo(cre.getGroup2Name()) == 0)
				{
					comparisonResult = this.group3Name.compareTo(cre.getGroup3Name());
				}
				else
				{
					comparisonResult = this.group2Name.compareTo(cre.getGroup2Name());
				}
			}
			else
			{
				comparisonResult = this.group1Name.compareTo(cre.getGroup1Name());
			}
		}
		else
		{
			comparisonResult = this.name.compareTo(cre.getName());
		}
		
		return comparisonResult;
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
	public String getConditionId()
	{
		return conditionId;
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
	public Date getEffectiveDate()
	{
		return effectiveDate;
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
	public String getGroup1Name()
	{
		return group1Name;
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
	public String getGroup2Name()
	{
		return group2Name;
	}

	/**
	 * @return
	 */
	public String getGroup3Id()
	{
		return group3Id;
	}

	/**
	 * @return
	 */
	public String getGroup3Name()
	{
		return group3Name;
	}

	/**
	 * @return
	 */
	public Date getInactiveDate()
	{
		return inactiveDate;
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
	 * @return
	 */
	public boolean isStandard()
	{
		return isStandard;
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
	public void setConditionId(String string)
	{
		conditionId = string;
	}

	/**
	 * @param string
	 */
	public void setConditionName(String string)
	{
		conditionName = string;
	}

	/**
	 * @param date
	 */
	public void setEffectiveDate(Date date)
	{
		effectiveDate = date;
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
	public void setGroup1Name(String string)
	{
		group1Name = string;
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
	public void setGroup2Name(String string)
	{
		group2Name = string;
	}

	/**
	 * @param string
	 */
	public void setGroup3Id(String string)
	{
		group3Id = string;
	}

	/**
	 * @param string
	 */
	public void setGroup3Name(String string)
	{
		group3Name = string;
	}

	/**
	 * @param date
	 */
	public void setInactiveDate(Date date)
	{
		inactiveDate = date;
	}

	/**
	 * 
	 */
	public void setName(String aName)
	{
		name = aName;
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
	public void setStatus(String string)
	{
		status = string;
	}

	/**
	 * @return
	 */
	public String getCaseNum()
	{
		return caseNum;
	}

	/**
	 * @return
	 */
	public String getCdi()
	{
		return cdi;
	}

	/**
	 * @return
	 */
	public String getSuperviseeName()
	{
		return superviseeName;
	}

	/**
	 * @param string
	 */
	public void setCaseNum(String string)
	{
		caseNum = string;
	}

	/**
	 * @param string
	 */
	public void setCdi(String string)
	{
		cdi = string;
	}

	/**
	 * @param string
	 */
	public void setSuperviseeName(String string)
	{
		superviseeName = string;
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
	public String getNotes()
	{
		return notes;
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
	public String getCourtCategory()
	{
		return courtCategory;
	}

	/**
	 * @return
	 */
	public String getCourtId()
	{
		return courtId;
	}

	/**
	 * @param string
	 */
	public void setCourtCategory(String string)
	{
		courtCategory = string;
	}

	/**
	 * @param string
	 */
	public void setCourtId(String string)
	{
		courtId = string;
	}

	/**
	 * @return
	 */
	public boolean isArchived()
	{
		return isArchived;
	}

	/**
	 * @param b
	 */
	public void setArchived(boolean b)
	{
		isArchived = b;
	}

	/**
	 * @return
	 */
	public boolean isSpecialCondition()
	{
		return isSpecialCondition;
	}

	/**
	 * @param b
	 */
	public void setSpecialCondition(boolean b)
	{
		isSpecialCondition = b;
	}

	/**
	 * @return
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param string
	 */
	public void setDescription(String string)
	{
		description = string;
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

	public String getSupervisionTypeSummary() {
		return SupervisionTypeSummary;
	}

	public void setSupervisionTypeSummary(String supervisionTypeSummary) {
		SupervisionTypeSummary = supervisionTypeSummary;
	}
	
}
