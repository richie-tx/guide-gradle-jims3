package pd.supervision.administerassessments;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import java.util.List;
import java.util.Iterator;
import pd.codetable.Code;
import mojo.km.utilities.CollectionUtil;
import mojo.km.messaging.IEvent;

/**
 * 
 * @author dgibler
 */
public class StrategiesForCaseSupervision extends Assessment
{

	/**
	 * 
	 * @return 
	 * @param anEvent
	 */
	static public List findAll(IEvent anEvent)
	{
		IHome home = new Home();
		Iterator iter = home.findAll(anEvent, StrategiesForCaseSupervision.class);
		return CollectionUtil.iteratorToList(iter);
	}

	/**
	 * 
	 * @return 
	 * @param attrName
	 * @param attrValue
	 */
	static public List findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		Iterator iter = home.findAll(attrName, attrValue, StrategiesForCaseSupervision.class);
		return CollectionUtil.iteratorToList(iter);
	}

	/**
	 * 
	 * @return 
	 * @param oid
	 */
	static public StrategiesForCaseSupervision findByOid(String oid)
	{
		IHome home = new Home();
		return (StrategiesForCaseSupervision) home.find(oid, StrategiesForCaseSupervision.class);
	}
	/**
	 * Properties for assessment
	 */
	private Assessment assessment = null;
	private String assessmentId;
	private int ccTotal;
	private String comments;
	private int esTotal;
	/**
	 * Properties for forceFieldAnalysis
	 */
	private ForceFieldAnalysis forceFieldAnalysis = null;
	private String forceFieldAnalysisId;
	private int lsTotal;
	/**
	 * Properties for primaryClassification
	 */
	private Code primaryClassification = null;
	private String primaryClassificationId;
	/**
	 * Properties for secondaryClassification
	 */
	private Code secondaryClassification = null;
	private String secondaryClassificationId;
	private int siTotal;
	
	private boolean scsInventoryFrceFld = false;
	
	public void bind()
	{
		markModified();
		IHome home = new Home();
		home.bind(this);
	}

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
	 * Get the reference value to class :: pd.supervision.administerassessments.Assessment
	 */
	public String getAssessmentId()
	{
		fetch();
		return assessmentId;
	}

	/**
	 * 
	 * @return Returns the ccTotal.
	 */
	public int getCcTotal()
	{
		fetch();
		return ccTotal;
	}

	/**
	 * 
	 * @return Returns the comments.
	 */
	public String getComments()
	{
		fetch();
		return comments;
	}

	/**
	 * 
	 * @return Returns the esTotal.
	 */
	public int getEsTotal()
	{
		fetch();
		return esTotal;
	}

	/**
	 * Gets referenced type pd.supervision.administerassessments.ForceFieldAnalysis
	 */
	public ForceFieldAnalysis getForceFieldAnalysis()
	{
		initForceFieldAnalysis();
		return forceFieldAnalysis;
	}

	/**
	 * Get the reference value to class :: pd.supervision.administerassessments.ForceFieldAnalysis
	 */
	public String getForceFieldAnalysisId()
	{
		fetch();
		return forceFieldAnalysisId;
	}

	/**
	 * 
	 * @return Returns the lsTotal.
	 */
	public int getLsTotal()
	{
		fetch();
		return lsTotal;
	}

	/**
	 * 
	 * @return Returns the primaryClassification.
	 */
	public Code getPrimaryClassification()
	{
		fetch();
		initPrimaryClassification();
		return primaryClassification;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getPrimaryClassificationId()
	{
		fetch();
		return primaryClassificationId;
	}

	/**
	 * 
	 * @return Returns the secondaryClassification.
	 */
	public Code getSecondaryClassification()
	{
		fetch();
		initSecondaryClassification();
		return secondaryClassification;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getSecondaryClassificationId()
	{
		fetch();
		return secondaryClassificationId;
	}

	/**
	 * /**
	 * @return Returns the siTotal.
	 */
	public int getSiTotal()
	{
		fetch();
		return siTotal;
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
	 * Initialize class relationship to class pd.supervision.administerassessments.ForceFieldAnalysis
	 */
	private void initForceFieldAnalysis()
	{
		if (forceFieldAnalysis == null)
		{
			forceFieldAnalysis = (ForceFieldAnalysis) new mojo.km.persistence.Reference(
					forceFieldAnalysisId, ForceFieldAnalysis.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initPrimaryClassification()
	{
		if (primaryClassification == null)
		{
			primaryClassification = (Code) new mojo.km.persistence.Reference(primaryClassificationId,
					Code.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initSecondaryClassification()
	{
		if (secondaryClassification == null)
		{
			secondaryClassification = (Code) new mojo.km.persistence.Reference(secondaryClassificationId,
					Code.class).getObject();
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
	 * @param ccTotal The ccTotal to set.
	 */
	public void setCcTotal(int ccTotal)
	{
		if (this.ccTotal != ccTotal)
		{
			markModified();
		}
		this.ccTotal = ccTotal;
	}

	/**
	 * 
	 * @param comments The comments to set.
	 */
	public void setComments(String comments)
	{
		if (this.comments == null || !this.comments.equals(comments))
		{
			markModified();
		}
		this.comments = comments;
	}

	/**
	 * 
	 * @param esTotal The esTotal to set.
	 */
	public void setEsTotal(int esTotal)
	{
		if (this.esTotal != esTotal)
		{
			markModified();
		}
		this.esTotal = esTotal;
	}

	/**
	 * set the type reference for class member forceFieldAnalysis
	 */
	public void setForceFieldAnalysis(ForceFieldAnalysis forceFieldAnalysis)
	{
		if (this.forceFieldAnalysis == null || !this.forceFieldAnalysis.equals(forceFieldAnalysis))
		{
			markModified();
		}
		if (forceFieldAnalysis.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(forceFieldAnalysis);
		}
		setForceFieldAnalysisId("" + forceFieldAnalysis.getOID());
		this.forceFieldAnalysis = (ForceFieldAnalysis) new mojo.km.persistence.Reference(
				forceFieldAnalysis).getObject();
	}

	/**
	 * Set the reference value to class :: pd.supervision.administerassessments.ForceFieldAnalysis
	 */
	public void setForceFieldAnalysisId(String forceFieldAnalysisId)
	{
		if (this.forceFieldAnalysisId == null || !this.forceFieldAnalysisId.equals(forceFieldAnalysisId))
		{
			markModified();
		}
		forceFieldAnalysis = null;
		this.forceFieldAnalysisId = forceFieldAnalysisId;
	}

	/**
	 * 
	 * @param lsTotal The lsTotal to set.
	 */
	public void setLsTotal(int isTotal)
	{
		if (this.lsTotal != isTotal)
		{
			markModified();
		}
		this.lsTotal = isTotal;
	}

	/**
	 * set the type reference for class member primaryClassification
	 */
	public void setPrimaryClassification(Code primaryClassification)
	{
		if (this.primaryClassification == null || !this.primaryClassification.equals(primaryClassification))
		{
			markModified();
		}
		if (primaryClassification.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(primaryClassification);
		}
		setPrimaryClassificationId("" + primaryClassification.getOID());
		this.primaryClassification = (Code) new mojo.km.persistence.Reference(primaryClassification)
				.getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setPrimaryClassificationId(String primaryClassificationId)
	{
		if (this.primaryClassificationId == null || !this.primaryClassificationId.equals(primaryClassificationId))
		{
			markModified();
		}
		primaryClassification = null;
		this.primaryClassificationId = primaryClassificationId;
	}

	/**
	 * set the type reference for class member secondaryClassification
	 */
	public void setSecondaryClassification(Code secondaryClassification)
	{
		if (this.secondaryClassification == null || !this.secondaryClassification.equals(secondaryClassification))
		{
			markModified();
		}
		if (secondaryClassification.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(secondaryClassification);
		}
		setSecondaryClassificationId("" + secondaryClassification.getOID());
		this.secondaryClassification = (Code) new mojo.km.persistence.Reference(secondaryClassification)
				.getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setSecondaryClassificationId(String secondaryClassificationId)
	{
		if (this.secondaryClassificationId == null || !this.secondaryClassificationId.equals(secondaryClassificationId))
		{
			markModified();
		}
		secondaryClassification = null;
		this.secondaryClassificationId = secondaryClassificationId;
	}

	/**
	 * 
	 * @param siTotal The siTotal to set.
	 */
	public void setSiTotal(int siTotal)
	{
		if (this.siTotal != siTotal)
		{
			markModified();
		}
		this.siTotal = siTotal;
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

}
