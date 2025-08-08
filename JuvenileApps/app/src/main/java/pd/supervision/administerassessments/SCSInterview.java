/*
 * Created on Jun 25, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerassessments;

import java.util.Iterator;
import java.util.List;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.CollectionUtil;

/**
 * @author cc_bjangay
 *
 */
public class SCSInterview extends Assessment
{
	/**
	 * Properties for assessment
	 */
	private Assessment assessment = null;
	private String assessmentId;
	/**
	 * Properties for forceFieldAnalysis
	 */
	private ForceFieldAnalysis forceFieldAnalysis = null;
	private String forceFieldAnalysisId;
	
	private boolean scsInterviewFrceFld = false;
	
	/**
	 * 
	 * @return 
	 * @param anEvent
	 */
	static public List findAll(IEvent anEvent)
	{
		IHome home = new Home();
		Iterator iter = home.findAll(anEvent, SCSInterview.class);
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
		Iterator iter = home.findAll(attrName, attrValue, SCSInterview.class);
		return CollectionUtil.iteratorToList(iter);
	}

	/**
	 * 
	 * @return 
	 * @param oid
	 */
	static public SCSInterview findByOid(String oid)
	{
		IHome home = new Home();
		return (SCSInterview) home.find(oid, SCSInterview.class);
	}
	
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
