package pd.supervision.administerassessments;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import mojo.km.context.multidatasource.Home;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.IHome;
import mojo.km.utilities.CollectionUtil;

/**
 * 
 * @author dgibler
 */
public class LSIR extends Assessment
{
	/**
	 * Properties for assessment
	 */
	private Assessment assessment = null;
	private String assessmentId;
	private String comments;
	private Date dueDate;
	private boolean isReassessment;
	/**
	 * @param oid
	 * @return
	 */
	public static LSIR findByOid(String oid){
	    IHome home = new Home();
	    return (LSIR) home.find(oid, LSIR.class);
	}
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
	 * @return Returns the dueDate.
	 */
	public Date getDueDate()
	{
		fetch();
		return dueDate;
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
	 * @param dueDate The dueDate to set.
	 */
	public void setDueDate(Date dueDate)
	{
		if (this.dueDate == null || !this.dueDate.equals(dueDate))
		{
			markModified();
		}
		this.dueDate = dueDate;
	}

	/**
	 * 
	 * @param isReassessment The isReassessment to set.
	 */
	public void setIsReassessment(boolean isReassessment)
	{
		if (this.isReassessment != isReassessment)
		{
			markModified();
		}
		this.isReassessment = isReassessment;
	}

	/* (non-Javadoc)
	 * @see pd.supervision.administerassessments.Assessment#findAll(java.lang.String, java.lang.String)
	 */
	public static List findAll(String attrName, String attrValue){
	    IHome home = new Home();
	    Iterator iter = home.findAll(attrName, attrValue, LSIR.class);
	    return CollectionUtil.iteratorToList(iter);
	}
	public void bind(){
	    IHome home = new Home();
	    home.bind(this);
	}
	public static List findAll(IEvent anEvent){
		IHome home = new Home();
		Iterator iter = home.findAll(anEvent, LSIR.class);
		return CollectionUtil.iteratorToList(iter);
	}
}
