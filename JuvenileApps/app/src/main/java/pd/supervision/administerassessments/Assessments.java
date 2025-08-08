package pd.supervision.administerassessments;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.CollectionUtil;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Date on which changes were made to assessment.
 */
public class Assessments extends PersistentObject 
{
	/**
	 * @param attrName
	 * @param attrValue
	 * @return
	 */
	public static List findAll(String attrName, String attrValue){
	    IHome home = new Home();
	    Iterator iter = home.findAll(attrName, attrValue, Assessments.class);
	    return CollectionUtil.iteratorToList(iter);
	}
    /**
	 * @param anEvent
	 * @return
	 */
	public static List findAll(IEvent anEvent){
	    
	    IHome home = new Home();
	    Iterator iter = home.findAll(anEvent, Assessments.class);
	    return CollectionUtil.iteratorToList(iter);
	}
	private Date assessmentDate;
	private Date migratedAssessmentDate;
	private String assessmentId;
	private String assessmentTypeId;
	private String statusCd;
	private String defendantId;
	private String forceFieldId;
	private String lsirComments;
	private Date lsirDueDate;
	private String lsirId;
	private boolean lsirIReassessment;
	private int lsirNeedsScore;
	private int lsirRiskScore;
	private String masterAssessmentId;
	private int scsCcTotal;
	private String scsComments;
	private int scsEsTotal;
	private String scsForceFieldId;
	private String scsId;
	private int scsIsTotal;
	private String scsPrimaryClassificationCd;
	private String scsSecondaryClassificationCd;
	private int scsSiTotal;
	private String scsInterviewId;
	private String scsIntrvwForceFieldId;
	/**
	 * Date on which changes were made to assessment.
	 */
	private Date transactionDate;
	private int versionNum;
	private Date wiscAssessmentDueDate;
	private boolean wiscIsPending;
	private boolean wiscIsReassessment;
	private int wiscNeedsLevel;
	private String wisconsinId;
	private int wiscRiskLevel;
	private int wiscTotalNeedsScore;
	private int wiscTotalRiskScore;

	private boolean scsInventoryFrceFld = false;
	private boolean scsInterviewFrceFld = false;
	
	/**
	 * @roseuid 47ACD7020030
	 */
	public Assessments()
	{
	}

	/**
	 * 
	 * @return Returns the assessmentDate.
	 */
	public Date getAssessmentDate()
	{
		fetch();
		return assessmentDate;
	}

	/**
	 * 
	 * @return Returns the assessmentId.
	 */
	public String getAssessmentId()
	{
		fetch();
		return assessmentId;
	}
    /**
     * @return Returns the assessmentTypeId.
     */
    public String getAssessmentTypeId() {
        return assessmentTypeId;
    }

	/**
	 * 
	 * @return Returns the defendantId.
	 */
	public String getDefendantId()
	{
		fetch();
		return defendantId;
	}

	/**
	 * 
	 * @return Returns the lsirComments.
	 */
	public String getLsirComments()
	{
		fetch();
		return lsirComments;
	}

	/**
	 * 
	 * @return Returns the lsirDueDate.
	 */
	public Date getLsirDueDate()
	{
		fetch();
		return lsirDueDate;
	}

	/**
	 * 
	 * @return Returns the lsirId.
	 */
	public String getLsirId()
	{
		fetch();
		return lsirId;
	}

	/**
	 * 
	 * @return Returns the lsirNeedsScore.
	 */
	public int getLsirNeedsScore()
	{
		fetch();
		return lsirNeedsScore;
	}

	/**
	 * 
	 * @return Returns the lsirRiskScore.
	 */
	public int getLsirRiskScore()
	{
		fetch();
		return lsirRiskScore;
	}
    /**
     * @return Returns the masterAssessmentId.
     */
    public String getMasterAssessmentId() {
        fetch();
        return masterAssessmentId;
    }
	/**
	 * 
	 * @return Returns the scsCcTotal.
	 */
	public int getScsCcTotal()
	{
		fetch();
		return scsCcTotal;
	}

	/**
	 * 
	 * @return Returns the scsComments.
	 */
	public String getScsComments()
	{
		fetch();
		return scsComments;
	}

	/**
	 * 
	 * @return Returns the scsEsTotal.
	 */
	public int getScsEsTotal()
	{
		fetch();
		return scsEsTotal;
	}

	/**
	 * 
	 * @return Returns the scsForceFieldId.
	 */
	public String getScsForceFieldId()
	{
		fetch();
		return scsForceFieldId;
	}

	/**
	 * 
	 * @return Returns the scsId.
	 */
	public String getScsId()
	{
		fetch();
		return scsId;
	}

	/**
	 * 
	 * @return Returns the scsIsTotal.
	 */
	public int getScsIsTotal()
	{
		fetch();
		return scsIsTotal;
	}

	/**
	 * 
	 * @return Returns the scsPrimaryClassificationCd.
	 */
	public String getScsPrimaryClassificationCd()
	{
		fetch();
		return scsPrimaryClassificationCd;
	}

	/**
	 * 
	 * @return Returns the scsSecondaryClassificationCd.
	 */
	public String getScsSecondaryClassificationCd()
	{
		fetch();
		return scsSecondaryClassificationCd;
	}

	/**
	 * 
	 * @return Returns the scsSiTotal.
	 */
	public int getScsSiTotal()
	{
		fetch();
		return scsSiTotal;
	}

	/**
	 * 
	 * @return Returns the transactionDate.
	 */
	public Date getTransactionDate()
	{
		fetch();
		return transactionDate;
	}

	/**
	 * 
	 * @return Returns the versionNum.
	 */
	public int getVersionNum()
	{
		fetch();
		return versionNum;
	}

	/**
	 * 
	 * @return Returns the wiscAssessmentDueDate.
	 */
	public Date getWiscAssessmentDueDate()
	{
		fetch();
		return wiscAssessmentDueDate;
	}

	/**
	 * 
	 * @return Returns the wiscNeedsLevel.
	 */
	public int getWiscNeedsLevel()
	{
		fetch();
		return wiscNeedsLevel;
	}

	/**
	 * 
	 * @return Returns the wisconsinId.
	 */
	public String getWisconsinId()
	{
		fetch();
		return wisconsinId;
	}

	/**
	 * 
	 * @return Returns the wiscRiskLevel.
	 */
	public int getWiscRiskLevel()
	{
		fetch();
		return wiscRiskLevel;
	}

	/**
	 * 
	 * @return Returns the wiscTotalNeedsScore.
	 */
	public int getWiscTotalNeedsScore()
	{
		fetch();
		return wiscTotalNeedsScore;
	}

	/**
	 * 
	 * @return Returns the wiscTotalRiskScore.
	 */
	public int getWiscTotalRiskScore()
	{
		fetch();
		return wiscTotalRiskScore;
	}

	/**
	 * 
	 * @return Returns the lsirIReassessment.
	 */
	public boolean isLsirIReassessment()
	{
		fetch();
		return lsirIReassessment;
	}

	/**
	 * 
	 * @return Returns the wiscIsPending.
	 */
	public boolean isWiscIsPending()
	{
		fetch();
		return wiscIsPending;
	}

	/**
	 * 
	 * @return Returns the wiscIsReassessment.
	 */
	public boolean isWiscIsReassessment()
	{
		fetch();
		return wiscIsReassessment;
	}

	/**
	 * 
	 * @param assessmentDate The assessmentDate to set.
	 */
	public void setAssessmentDate(Date assessmentDate)
	{
		if (this.assessmentDate == null || !this.assessmentDate.equals(assessmentDate))
		{
			markModified();
		}
		this.assessmentDate = assessmentDate;
	}

	/**
	 * 
	 * @param assessmentId The assessmentId to set.
	 */
	public void setAssessmentId(String assessmentId)
	{
		if (this.assessmentId == null || !this.assessmentId.equals(assessmentId))
		{
			markModified();
		}
		this.assessmentId = assessmentId;
	}
    /**
     * @param assessmentTypeId The assessmentTypeId to set.
     */
    public void setAssessmentTypeId(String assessmentTypeId) {
        this.assessmentTypeId = assessmentTypeId;
    }

	/**
	 * 
	 * @param defendantId The defendantId to set.
	 */
	public void setDefendantId(String defendantId)
	{
		if (this.defendantId == null || !this.defendantId.equals(defendantId))
		{
			markModified();
		}
		this.defendantId = defendantId;
	}

	/**
	 * 
	 * @param lsirComments The lsirComments to set.
	 */
	public void setLsirComments(String lsirComments)
	{
		if (this.lsirComments == null || !this.lsirComments.equals(lsirComments))
		{
			markModified();
		}
		this.lsirComments = lsirComments;
	}

	/**
	 * 
	 * @param lsirDueDate The lsirDueDate to set.
	 */
	public void setLsirDueDate(Date lsirDueDate)
	{
		if (this.lsirDueDate == null || !this.lsirDueDate.equals(lsirDueDate))
		{
			markModified();
		}
		this.lsirDueDate = lsirDueDate;
	}

	/**
	 * 
	 * @param lsirId The lsirId to set.
	 */
	public void setLsirId(String lsirId)
	{
		if (this.lsirId == null || !this.lsirId.equals(lsirId))
		{
			markModified();
		}
		this.lsirId = lsirId;
	}

	/**
	 * 
	 * @param lsirIReassessment The lsirIReassessment to set.
	 */
	public void setLsirIReassessment(boolean lsirIReassessment)
	{
		if (this.lsirIReassessment != lsirIReassessment)
		{
			markModified();
		}
		this.lsirIReassessment = lsirIReassessment;
	}

	/**
	 * 
	 * @param lsirNeedsScore The lsirNeedsScore to set.
	 */
	public void setLsirNeedsScore(int lsirNeedsScore)
	{
		if (this.lsirNeedsScore != lsirNeedsScore)
		{
			markModified();
		}
		this.lsirNeedsScore = lsirNeedsScore;
	}

	/**
	 * 
	 * @param lsirRiskScore The lsirRiskScore to set.
	 */
	public void setLsirRiskScore(int lsirRiskScore)
	{
		if (this.lsirRiskScore != lsirRiskScore)
		{
			markModified();
		}
		this.lsirRiskScore = lsirRiskScore;
	}
    /**
     * @param masterAssessmentId The masterAssessmentId to set.
     */
    public void setMasterAssessmentId(String masterAssessmentId) {
		if (this.masterAssessmentId == null || !this.masterAssessmentId.equals(masterAssessmentId))
		{
			markModified();
		}

        this.masterAssessmentId = masterAssessmentId;
    }

	/**
	 * 
	 * @param scsCcTotal The scsCcTotal to set.
	 */
	public void setScsCcTotal(int scsCcTotal)
	{
		if (this.scsCcTotal != scsCcTotal)
		{
			markModified();
		}
		this.scsCcTotal = scsCcTotal;
	}

	/**
	 * 
	 * @param scsComments The scsComments to set.
	 */
	public void setScsComments(String scsComments)
	{
		if (this.scsComments == null || !this.scsComments.equals(scsComments))
		{
			markModified();
		}
		this.scsComments = scsComments;
	}

	/**
	 * 
	 * @param scsEsTotal The scsEsTotal to set.
	 */
	public void setScsEsTotal(int scsEsTotal)
	{
		if (this.scsEsTotal != scsEsTotal)
		{
			markModified();
		}
		this.scsEsTotal = scsEsTotal;
	}

	/**
	 * 
	 * @param scsForceFieldId The scsForceFieldId to set.
	 */
	public void setScsForceFieldId(String scsForceFieldId)
	{
		if (this.scsForceFieldId == null || !this.scsForceFieldId.equals(scsForceFieldId))
		{
			markModified();
		}
		this.scsForceFieldId = scsForceFieldId;
	}

	/**
	 * 
	 * @param scsId The scsId to set.
	 */
	public void setScsId(String scsId)
	{
		if (this.scsId == null || !this.scsId.equals(scsId))
		{
			markModified();
		}
		this.scsId = scsId;
	}

	/**
	 * 
	 * @param scsIsTotal The scsIsTotal to set.
	 */
	public void setScsIsTotal(int scsIsTotal)
	{
		if (this.scsIsTotal != scsIsTotal)
		{
			markModified();
		}
		this.scsIsTotal = scsIsTotal;
	}

	/**
	 * 
	 * @param scsPrimaryClassificationCd The scsPrimaryClassificationCd to set.
	 */
	public void setScsPrimaryClassificationCd(String scsPrimaryClassificationCd)
	{
		if (this.scsPrimaryClassificationCd == null
				|| !this.scsPrimaryClassificationCd.equals(scsPrimaryClassificationCd))
		{
			markModified();
		}
		this.scsPrimaryClassificationCd = scsPrimaryClassificationCd;
	}

	/**
	 * 
	 * @param scsSecondaryClassificationCd The scsSecondaryClassificationCd to set.
	 */
	public void setScsSecondaryClassificationCd(String scsSecondaryClassificationCd)
	{
		if (this.scsSecondaryClassificationCd == null
				|| !this.scsSecondaryClassificationCd.equals(scsSecondaryClassificationCd))
		{
			markModified();
		}
		this.scsSecondaryClassificationCd = scsSecondaryClassificationCd;
	}

	/**
	 * 
	 * @param scsSiTotal The scsSiTotal to set.
	 */
	public void setScsSiTotal(int scsSiTotal)
	{
		if (this.scsSiTotal != scsSiTotal)
		{
			markModified();
		}
		this.scsSiTotal = scsSiTotal;
	}

	/**
	 * 
	 * @param transactionDate The transactionDate to set.
	 */
	public void setTransactionDate(Date transactionDate)
	{
		if (this.transactionDate == null || !this.transactionDate.equals(transactionDate))
		{
			markModified();
		}
		this.transactionDate = transactionDate;
	}

	/**
	 * 
	 * @param versionNum The versionNum to set.
	 */
	public void setVersionNum(int versionNum)
	{
		if (this.versionNum != versionNum)
		{
			markModified();
		}
		this.versionNum = versionNum;
	}

	/**
	 * 
	 * @param wiscAssessmentDueDate The wiscAssessmentDueDate to set.
	 */
	public void setWiscAssessmentDueDate(Date wiscAssessmentDueDate)
	{
		if (this.wiscAssessmentDueDate == null || !this.wiscAssessmentDueDate.equals(wiscAssessmentDueDate))
		{
			markModified();
		}
		this.wiscAssessmentDueDate = wiscAssessmentDueDate;
	}

	/**
	 * 
	 * @param wiscIsPending The wiscIsPending to set.
	 */
	public void setWiscIsPending(boolean wiscIsPending)
	{
		if (this.wiscIsPending != wiscIsPending)
		{
			markModified();
		}
		this.wiscIsPending = wiscIsPending;
	}

	/**
	 * 
	 * @param wiscIsReassessment The wiscIsReassessment to set.
	 */
	public void setWiscIsReassessment(boolean wiscIsReassessment)
	{
		if (this.wiscIsReassessment != wiscIsReassessment)
		{
			markModified();
		}
		this.wiscIsReassessment = wiscIsReassessment;
	}

	/**
	 * 
	 * @param wiscNeedsLevel The wiscNeedsLevel to set.
	 */
	public void setWiscNeedsLevel(int wiscNeedsLevel)
	{
		if (this.wiscNeedsLevel != wiscNeedsLevel)
		{
			markModified();
		}
		this.wiscNeedsLevel = wiscNeedsLevel;
	}

	/**
	 * 
	 * @param wisconsinId The wisconsinId to set.
	 */
	public void setWisconsinId(String wisconsinId)
	{
		if (this.wisconsinId == null || !this.wisconsinId.equals(wisconsinId))
		{
			markModified();
		}
		this.wisconsinId = wisconsinId;
	}

	/**
	 * 
	 * @param wiscRiskLevel The wiscRiskLevel to set.
	 */
	public void setWiscRiskLevel(int wiscRiskLevel)
	{
		if (this.wiscRiskLevel != wiscRiskLevel)
		{
			markModified();
		}
		this.wiscRiskLevel = wiscRiskLevel;
	}

	/**
	 * 
	 * @param wiscTotalNeedsScore The wiscTotalNeedsScore to set.
	 */
	public void setWiscTotalNeedsScore(int wiscTotalNeedsScore)
	{
		if (this.wiscTotalNeedsScore != wiscTotalNeedsScore)
		{
			markModified();
		}
		this.wiscTotalNeedsScore = wiscTotalNeedsScore;
	}

	/**
	 * 
	 * @param wiscTotalRiskScore The wiscTotalRiskScore to set.
	 */
	public void setWiscTotalRiskScore(int wiscTotalRiskScore)
	{
		if (this.wiscTotalRiskScore != wiscTotalRiskScore)
		{
			markModified();
		}
		this.wiscTotalRiskScore = wiscTotalRiskScore;
	}
    /**
     * @return Returns the forceFieldId.
     */
    public String getForceFieldId() {
        fetch();
        return forceFieldId;
    }
    /**
     * @param forceFieldId The forceFieldId to set.
     */
    public void setForceFieldId(String forceFieldId) {
		if (this.forceFieldId != forceFieldId)
		{
			markModified();
		}
       this.forceFieldId = forceFieldId;
    }
    /**
	 * @return the statusCd
	 */
	public String getStatusCd() {
		fetch();
		return statusCd;
	}

	/**
	 * @param statusCd the statusCd to set
	 */
	public void setStatusCd(String statusCd) {
		if (this.statusCd == null || !this.statusCd.equals(statusCd))
		{
			markModified();
		}
		this.statusCd = statusCd;
	}
	
	public Date getMigratedAssessmentDate() {
		fetch();
		return migratedAssessmentDate;
	}

	public void setMigratedAssessmentDate(Date migratedAssessmentDate) {
		if (this.migratedAssessmentDate == null || !this.migratedAssessmentDate.equals(migratedAssessmentDate))
		{
			markModified();
		}
		this.migratedAssessmentDate = migratedAssessmentDate;
	}
	/**
	 * @return the scsInterviewId
	 */
	public String getScsInterviewId() {
		fetch();
		return scsInterviewId;
	}
	/**
	 * @param scsInterviewId the scsInterviewId to set
	 */
	public void setScsInterviewId(String scsInterviewId) 
	{
		if (this.scsInterviewId == null || !this.scsInterviewId.equals(scsInterviewId))
		{
			markModified();
		}
		this.scsInterviewId = scsInterviewId;
	}
	/**
	 * @return the scsIntrvwForceFieldId
	 */
	public String getScsIntrvwForceFieldId() {
		fetch();
		return scsIntrvwForceFieldId;
	}
	/**
	 * @param scsIntrvwForceFieldId the scsIntrvwForceFieldId to set
	 */
	public void setScsIntrvwForceFieldId(String scsIntrvwForceFieldId) 
	{
		if (this.scsIntrvwForceFieldId == null || !this.scsIntrvwForceFieldId.equals(scsIntrvwForceFieldId))
		{
			markModified();
		}
		this.scsIntrvwForceFieldId = scsIntrvwForceFieldId;
	}
	
	/**
	 * @return the scsInventoryFrceFld
	 */
	public boolean isScsInventoryFrceFld() {
		fetch();
		return scsInventoryFrceFld;
	}

	/**
	 * @param scsInventoryFrceFld the scsInventoryFrceFld to set
	 */
	public void setScsInventoryFrceFld(boolean scsInventoryFrceFld) {
		if (this.scsInventoryFrceFld != scsInventoryFrceFld)
		{
			markModified();
		}
		this.scsInventoryFrceFld = scsInventoryFrceFld;
	}

	/**
	 * @return the scsInterviewFrceFld
	 */
	public boolean isScsInterviewFrceFld() {
		fetch();
		return scsInterviewFrceFld;
	}

	/**
	 * @param scsInterviewFrceFld the scsInterviewFrceFld to set
	 */
	public void setScsInterviewFrceFld(boolean scsInterviewFrceFld) {
		if (this.scsInterviewFrceFld != scsInterviewFrceFld)
		{
			markModified();
		}
		this.scsInterviewFrceFld = scsInterviewFrceFld;
	}
}
