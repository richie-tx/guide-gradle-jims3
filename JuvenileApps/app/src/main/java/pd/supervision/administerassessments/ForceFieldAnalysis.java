package pd.supervision.administerassessments;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import java.util.List;
import java.util.Iterator;
import mojo.km.utilities.CollectionUtil;

/**
 * 
 * @author dgibler
 */
public class ForceFieldAnalysis extends Assessment
{
	/**
	 * Properties for assessment
	 */
	private Assessment assessment = null;
	private String assessmentId;
	
	/**
	 * Gets referenced type pd.supervision.administerassessments.Assessment
	 */
	public Assessment getAssessment()
	{
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
	 * @param oid
	 * @return 
	 */
	static public ForceFieldAnalysis findByOid(String oid)
	{
		IHome home = new Home();
		return (ForceFieldAnalysis) home.find(oid, ForceFieldAnalysis.class);
	}

	/**
	 * @param attrName
	 * @param attrValue
	 * @return 
	 */
	static public List findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		Iterator iter = home.findAll(attrName, attrValue, ForceFieldAnalysis.class);
		return CollectionUtil.iteratorToList(iter);
	}

	/**
	 * @param anOid
	 * @return 
	 */
	static public ForceFieldAnalysis findAByOid(String anOid)
	{
		IHome home = new Home();
		return (ForceFieldAnalysis) home.find(anOid, ForceFieldAnalysis.class);
	}
	
	/**
	 * 
	 */
	public void bind(){
	    IHome home = new Home();
	    home.bind(this);
	}
	/* (non-Javadoc)
	 * @see pd.supervision.administerassessments.Assessment#findAll(mojo.km.messaging.IEvent)
	 */
	public static List findAll(IEvent anEvent){
	    IHome home = new Home();
	    Iterator iter = home.findAll(anEvent, ForceFieldAnalysis.class);
	    return CollectionUtil.iteratorToList(iter);
	}
}
