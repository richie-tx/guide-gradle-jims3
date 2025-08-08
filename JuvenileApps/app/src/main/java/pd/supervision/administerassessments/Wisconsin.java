package pd.supervision.administerassessments;

import mojo.km.persistence.IHome;
import java.util.Date;
import java.util.List;
import mojo.km.context.multidatasource.Home;
import java.util.Iterator;
import pd.supervision.administercaseload.SupervisionLevelOfServiceCode;
import mojo.km.utilities.CollectionUtil;
import mojo.km.messaging.IEvent;

/**
 * @author dgibler
 */
public class Wisconsin extends Assessment
{
	public void bind(){
	    IHome home = new Home();
	    home.bind(this);
	}
    /**
	 * @param oid
	 * @return
	 */
	static public Wisconsin findByOid(String oid){
	    IHome home = new Home();
	    return (Wisconsin) home.find(oid, Wisconsin.class);
	}
    /* (non-Javadoc)
	 * @see pd.supervision.administerassessments.Assessment#findAll(mojo.km.messaging.IEvent)
	 */
	static public List findAll(IEvent anEvent)
	{
		IHome home = new Home();
		Iterator iter = home.findAll(anEvent, Wisconsin.class);
		return CollectionUtil.iteratorToList(iter);
	}
	/* (non-Javadoc)
	 * @see pd.supervision.administerassessments.Assessment#findAll(java.lang.String, java.lang.String)
	 */
	static public List findAll(String attrName, String attrValue){
	    IHome home = new Home();
	    Iterator iter = home.findAll(attrName, attrValue, Wisconsin.class);
	    return CollectionUtil.iteratorToList(iter);
	}
	/**
	 * Properties for assessment
	 */
	private Assessment assessment = null;
	private Date assessmentDueDate;
	private String assessmentId;
	private boolean isReassessment;
	private boolean isPending;
	private int needsLevel;
	private int riskLevel;
	/**
	 * Properties for suggestedLosCd
	 */
	private SupervisionLevelOfServiceCode suggestedLosCd = null;
	private String suggestedLosCdId;
	private int totalNeedsScore;
	private int totalRiskScore;

	/**
	 * Gets referenced type pd.supervision.administerassessments.Assessment
	 */
	public Assessment getAssessment()
	{
		fetch();
		initAssessment();
		return assessment;
	}

	/**
	 * @return Returns the assessmentDueDate.
	 */
	public Date getAssessmentDueDate()
	{
		fetch();
		return assessmentDueDate;
	}

	/**
	 * Get the reference value to class :: pd.supervision.administerassessments.Assessment
	 */
	public String getAssessmentId()
	{
		fetch();
		return assessmentId;
	}

	/**
	 * 
	 * @return Returns the isReassessment.
	 */
	public boolean getIsReassessment()
	{
		fetch();
		return isReassessment;
	}

	/**
	 * @return Returns the isPending.
	 */
	public boolean getIsPending()
	{
		fetch();
		return isPending;
	}

	/**
	 * @return Returns the needsLevel.
	 */
	public int getNeedsLevel()
	{
		fetch();
		return needsLevel;
	}

	/**
	 * @return Returns the riskLevel.
	 */
	public int getRiskLevel()
	{
		fetch();
		return riskLevel;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public SupervisionLevelOfServiceCode getSuggestedLosCd()
	{
		fetch();
		initSuggestedLosCd();
		return suggestedLosCd;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getSuggestedLosCdId()
	{
		fetch();
		return suggestedLosCdId;
	}

	/**
	 * @return Returns the totalNeedsScore.
	 */
	public int getTotalNeedsScore()
	{
		fetch();
		return totalNeedsScore;
	}

	/**
	 * @return Returns the totalRiskScore.
	 */
	public int getTotalRiskScore()
	{
		fetch();
		return totalRiskScore;
	}

	/**
	 * Initialize class relationship to class pd.supervision.administerassessments.Assessment
	 */
	private void initAssessment()
	{
		if (assessment == null)
		{
			assessment = (Assessment) new mojo.km.persistence.Reference(
					assessmentId, Assessment.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initSuggestedLosCd()
	{
		if (suggestedLosCd == null)
		{
			suggestedLosCd = (SupervisionLevelOfServiceCode) new mojo.km.persistence.Reference(suggestedLosCdId,
			        SupervisionLevelOfServiceCode.class).getObject();
		}
	}

	/**
	 * set the type reference for class member assessment
	 */
	public void setAssessment(Assessment assessment)
	{
		if (this.assessment == null || !this.assessment.equals(assessment))
		{
			markModified();
		}
		if (assessment.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(assessment);
		}
		setAssessmentId("" + assessment.getOID());
		this.assessment = (Assessment) new mojo.km.persistence.Reference(
				assessment).getObject();
	}

	/**
	 * @param assessmentDueDate The assessmentDueDate to set.
	 */
	public void setAssessmentDueDate(Date assessmentDueDate)
	{
		if (this.assessmentDueDate == null || !this.assessmentDueDate.equals(assessmentDueDate))
		{
			markModified();
		}
		this.assessmentDueDate = assessmentDueDate;
	}

	/**
	 * Set the reference value to class :: pd.supervision.administerassessments.Assessment
	 */
	public void setAssessmentId(String assessmentId)
	{
		if (this.assessmentId == null || !this.assessmentId.equals(assessmentId))
		{
			markModified();
		}
		assessment = null;
		this.assessmentId = assessmentId;
	}

	/**
	 * 
	 * @param isReassessment The isReassessment to set.
	 */
	public void setIsReassessment(boolean isInitial)
	{
		if (this.isReassessment != isInitial)
		{
			markModified();
		}
		this.isReassessment = isInitial;
	}

	/**
	 * @param isPending The isPending to set.
	 */
	public void setIsPending(boolean isPending)
	{
		if (this.isPending != isPending)
		{
			markModified();
		}
		this.isPending = isPending;
	}

	/**
	 * @param needsLevel The needsLevel to set.
	 */
	public void setNeedsLevel(int needsLevel)
	{
		if (this.needsLevel != needsLevel)
		{
			markModified();
		}
		this.needsLevel = needsLevel;
	}

	/**
	 * @param riskLevel The riskLevel to set.
	 */
	public void setRiskLevel(int riskLevel)
	{
		if (this.riskLevel != riskLevel)
		{
			markModified();
		}
		this.riskLevel = riskLevel;
	}

	/**
	 * set the type reference for class member suggestedLosCd
	 */
	public void setSuggestedLosCd(SupervisionLevelOfServiceCode suggestedLosCd)
	{
		if (this.suggestedLosCd == null || !this.suggestedLosCd.equals(suggestedLosCd))
		{
			markModified();
		}
		if (suggestedLosCd.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(suggestedLosCd);
		}
		setSuggestedLosCdId("" + suggestedLosCd.getOID());
		this.suggestedLosCd = (SupervisionLevelOfServiceCode) new mojo.km.persistence.Reference(suggestedLosCd).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setSuggestedLosCdId(String suggestedLosCdId)
	{
		if (this.suggestedLosCdId == null || !this.suggestedLosCdId.equals(suggestedLosCdId))
		{
			markModified();
		}
		suggestedLosCd = null;
		this.suggestedLosCdId = suggestedLosCdId;
	}

	/**
	 * @param totalNeedsScore The totalNeedsScore to set.
	 */
	public void setTotalNeedsScore(int totalNeedsScore)
	{
		if (this.totalNeedsScore != totalNeedsScore)
		{
			markModified();
		}
		this.totalNeedsScore = totalNeedsScore;
	}

	/**
	 * @param totalRiskScore The totalRiskScore to set.
	 */
	public void setTotalRiskScore(int totalRiskScore)
	{
		if (this.totalRiskScore != totalRiskScore)
		{
			markModified();
		}
		this.totalRiskScore = totalRiskScore;
	}
}
