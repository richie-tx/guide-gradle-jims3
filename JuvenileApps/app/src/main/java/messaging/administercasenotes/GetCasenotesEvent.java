// Source file:
// C:\\views\\CommonSupervision\\app\\src\\messaging\\administercasenotes\\GetCasenotesEvent.java

package messaging.administercasenotes;

import java.util.Collection;
import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class GetCasenotesEvent extends RequestEvent
{
	private String supervisionPeriodId;
	private String superviseeId;
	// search Types and criteria
	private String searchBy;
	// indicates ALL selected for a search type
	private boolean all;
	private Collection subjects;
	private String casenoteTypeId;
	private String collateralId;
	// populated when searchType is 'Cases'
	private Collection caseIds;
	// populated when collateralId indicates 'Associates'
	private Collection associateIds;
	private String courtNum;
	private Date beginDate;
	private Date endDate;
	private String howGeneratedId;
	private Date createDate;
	
	
	/**
	 * @roseuid 44F4617101BB
	 */
	public GetCasenotesEvent()
	{
	}

	/**
	 * @return Returns the searchBy.
	 */
	public String getSearchBy()
	{
		return searchBy;
	}

	/**
	 * @return
	 */
	public String getSuperviseeId()
	{
		return superviseeId;
	}

	/**
	 * @return
	 */
	public String getSupervisionPeriodId()
	{
		return supervisionPeriodId;
	}

	/**
	 * @return Returns the all.
	 */
	public boolean isAll()
	{
		return all;
	}
	
	/**
	 * @return Returns the associateIds.
	 */
	public Collection getAssociateIds()
	{
		return associateIds;
	}
	
	/**
	 * @return Returns the beginDate.
	 */
	public Date getBeginDate()
	{
		return beginDate;
	}
	
	/**
	 * @return Returns the casenoteTypeId.
	 */
	public String getCasenoteTypeId()
	{
		return casenoteTypeId;
	}
	
	/**
	 * @return Returns the collateralId.
	 */
	public String getCollateralId()
	{
		return collateralId;
	}
	
	/**
	 * @return Returns the courtNum.
	 */
	public String getCourtNum()
	{
		return courtNum;
	}
	
	/**
	 * @return Returns the endDate.
	 */
	public Date getEndDate()
	{
		return endDate;
	}
	
	/**
	 * @return Returns the howGeneratedId.
	 */
	public String getHowGeneratedId()
	{
		return howGeneratedId;
	}
	
	/**
	 * @return Returns the subjects.
	 */
	public Collection getSubjects()
	{
		return subjects;
	}
	
	/**
	 * @param all The all to set.
	 */
	public void setAll(boolean all)
	{
		this.all = all;
	}
	
	/**
	 * @param associateIds The associateIds to set.
	 */
	public void setAssociateIds(Collection associateIds)
	{
		this.associateIds = associateIds;
	}
	
	/**
	 * @param beginDate The beginDate to set.
	 */
	public void setBeginDate(Date beginDate)
	{
		this.beginDate = beginDate;
	}
	
	/**
	 * @param casenoteTypeId The casenoteTypeId to set.
	 */
	public void setCasenoteTypeId(String casenoteTypeId)
	{
		this.casenoteTypeId = casenoteTypeId;
	}
	
	/**
	 * @param collateralId The collateralId to set.
	 */
	public void setCollateralId(String collateralId)
	{
		this.collateralId = collateralId;
	}
	
	/**
	 * @param courtNum The courtNum to set.
	 */
	public void setCourtNum(String courtNum)
	{
		this.courtNum = courtNum;
	}
	
	/**
	 * @param endDate The endDate to set.
	 */
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}
	
	/**
	 * @param howGeneratedId The howGeneratedId to set.
	 */
	public void setHowGeneratedId(String howGeneratedId)
	{
		this.howGeneratedId = howGeneratedId;
	}
	
	/**
	 * @param subjects The subjects to set.
	 */
	public void setSubjects(Collection subjects)
	{
		this.subjects = subjects;
	}

	/**
	 * @param aSuperviseeId
	 */
	public void setSuperviseeId(String aSuperviseeId)
	{
		superviseeId = aSuperviseeId;
	}

	/**
	 * @param aPeriodId
	 */
	public void setSupervisionPeriodId(String aPeriodId)
	{
		supervisionPeriodId = aPeriodId;
	}

	/**
	 * @param searchBy The searchBy to set.
	 */
	public void setSearchBy(String searchBy)
	{
		this.searchBy = searchBy;
	}
	/**
	 * @return Returns the caseIds.
	 */
	public Collection getCaseIds()
	{
		return caseIds;
	}
	/**
	 * @param caseIds The caseIds to set.
	 */
	public void setCaseIds(Collection caseIds)
	{
		this.caseIds = caseIds;
	}
	
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
