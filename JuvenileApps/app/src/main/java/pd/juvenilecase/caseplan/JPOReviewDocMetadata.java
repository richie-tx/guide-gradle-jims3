package pd.juvenilecase.caseplan;

import messaging.caseplan.reply.JPOReviewReportResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import java.util.Date;
import java.util.Iterator;

/**
 * @roseuid 4533B7E60369
 */
public class JPOReviewDocMetadata extends PersistentObject
{
	private String casePlanId;
	private String reviewUserId;
	private Date reviewDate;
	private String reviewComments;
	private Object report;
	private String casefileId;
	//added for User story 11146 for jpo review report
	private boolean supLevelAppro;	
	private String recomSupLevelId;
	private boolean jpoMaintainContact;
	private String jpoMaintainExplain;

	/**
	 * @roseuid 4533B7E60369
	 */
	public JPOReviewDocMetadata()
	{
	}
	
	public Date getReviewDate()
	{
		fetch();
		return reviewDate;
	}

	/**
	 * @param reviewDate The reviewDate to set.
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setReviewDate(Date reviewDate)
	{
		if (this.reviewDate == null || !this.reviewDate.equals(reviewDate))
		{
			markModified();
		}
		this.reviewDate = reviewDate;
	}
	
	public Object getReport() 
	{
		fetch();
		return report;
	}
	
	/**
	* 
	*/
	public void setReport( Object aReport ) 
	{
		if ( report == null || ! report.equals(aReport) ) 
		{
			report = aReport;
			markModified();
		}
	}
	
	public void setReviewComments(String reviewComments)
	{
		if (this.reviewComments == null || !this.reviewComments.equals(reviewComments))
		{
			markModified();
		}
		this.reviewComments = reviewComments;
	}

	public String getReviewComments()
	{
		fetch();
		return reviewComments;
	}
	/**
	 * @return
	 */
	public boolean isSupLevelAppro() {
		fetch();
		return supLevelAppro;
	}
	/**
	 * @param b
	 */
	public void setSupLevelAppro(boolean supLevelAppro) {
		if (this.supLevelAppro != supLevelAppro) {
			markModified();
		}
		this.supLevelAppro = supLevelAppro;
	}

	/**
	 * @return Returns the recomSupLevelId.
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getRecomSupLevelId()
	{
		fetch();
		return recomSupLevelId;
	}

	/**
	 * @param supLevelId The supLevelId to set.
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setRecomSupLevelId(String recomSupLevelId)
	{
		if (this.recomSupLevelId == null || !this.recomSupLevelId.equals(recomSupLevelId))
		{
			markModified();
		}
		this.recomSupLevelId = recomSupLevelId;
	}
	
	/**
	 * @return
	 */
	public boolean isJpoMaintainContact() {
		fetch();
		return jpoMaintainContact;
	}
	/**
	 * @param b
	 */
	public void setJpoMaintainContact(boolean jpoMaintainContact) {
		if (this.jpoMaintainContact != jpoMaintainContact) {
			markModified();
		}
		this.jpoMaintainContact = jpoMaintainContact;
	}

	/**
	 * @return Returns the jpoMaintainExplain.
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getJpoMaintainExplain()
	{
		fetch();
		return jpoMaintainExplain;
	}

	/**
	 * @param supLevelId The jpoMaintainExplain to set.
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setJpoMaintainExplain(String jpoMaintainExplain)
	{
		if (this.jpoMaintainExplain == null || !this.jpoMaintainExplain.equals(jpoMaintainExplain))
		{
			markModified();
		}
		this.jpoMaintainExplain = jpoMaintainExplain;
	}
	
	public void setReviewUserId(String reviewUserId)
	{
		if (this.reviewUserId == null || !this.reviewUserId.equals(reviewUserId))
		{
			markModified();
		}
		this.reviewUserId = reviewUserId;
	}

	public String getReviewUserId()
	{
		fetch();
		return reviewUserId;
	}
	
	public void setCasePlanId(String casePlanId)
	{
		if (this.casePlanId == null || !this.casePlanId.equals(casePlanId))
		{
			markModified();
		}
		this.casePlanId = casePlanId;
	}

	public String getCasePlanId()
	{
		fetch();
		return casePlanId;
	}

	/**
	 * @roseuid 452FE43002C7
	 */
	public void bind()
	{
	}
	
	static public JPOReviewDocMetadata find(String jpoReviewId)
	{
		JPOReviewDocMetadata jpoReview = null;
		IHome home = new Home();
		jpoReview = (JPOReviewDocMetadata) home.find(jpoReviewId, JPOReviewDocMetadata.class);
		return jpoReview;
	}
	
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, JPOReviewDocMetadata.class);
	}

	public JPOReviewReportResponseEvent getRespEvt(){
		JPOReviewReportResponseEvent myEvt=new JPOReviewReportResponseEvent();
		myEvt.setCaseplanId(this.getCasePlanId());
		myEvt.setCaseplanRevId(this.getOID().toString());
		myEvt.setReport(this.getReport());
		myEvt.setSupLevelAppro(this.isSupLevelAppro());
		myEvt.setRecomSupLevelId(this.getRecomSupLevelId());
		myEvt.setJpoMaintainContact(this.isJpoMaintainContact());
		myEvt.setJpoMaintainExplain(this.getJpoMaintainExplain());
		myEvt.setReviewComments(this.getReviewComments());
		myEvt.setReviewDate(this.getReviewDate());
		myEvt.setReviewUserId(this.getReviewUserId());
		myEvt.setCasefileId(this.getCasefileId());
		return myEvt;
	}
	
	/**
	 * @return Returns the casefileId.
	 */
	public String getCasefileId() {
		return casefileId;
	}
	/**
	 * @param casefileId The casefileId to set.
	 */
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
}

