package pd.juvenilecase.mentalhealth;

import java.util.Date;
import java.util.Iterator;
import pd.codetable.Code;
import pd.contact.user.UserProfile;
import pd.transferobjects.helper.UserProfileHelper;
import messaging.contact.domintf.IName;
import messaging.contact.to.NameBean;
import messaging.mentalhealth.reply.MAYSISubAssessResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.DateUtil;

/**
* @author athorat
To change the template for this generated type comment go to
Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
*/
public class JuvenileSubMAYSI extends PersistentObject {
	
	private Date reviewDate;
	private String reviewerId;
	private String juvenileMAYSIAssessId;
	private String reviewComments;
	private String providerTypeReferredId;
	private boolean subReferral;
	private boolean assessComplete;
	
	
	private Code providerTypeReferred;
	/**
	* Properties for reviewer
	*/
	private UserProfile reviewer = null;
	/**
	* Properties for juvenileMAYSIDetail
	*/
	private JuvenileMAYSIMetadata juvenileMAYSIAssess = null;
	
	/**
	* @return java.util.Iterator
	* @param attrName
	* @param attrValue
	* @roseuid 4236ED950316
	*/
	static public Iterator findAll(String attrName, String attrValue) {
		IHome home = new Home();
		return (Iterator) home.findAll(attrName, attrValue, JuvenileSubMAYSI.class);
	}
	/**
	*/
	public JuvenileSubMAYSI() {
	}
	
	/**
	* @return 
	*/
	public String getReviewComments() {
		fetch();
		return reviewComments;
	}
	/**
	* @return 
	*/
	public Date getReviewDate() {
		fetch();
		return reviewDate;
	}
	
	/**
	* @param string
	*/
	public void setReviewComments(String string) {
		if (this.reviewComments == null || !this.reviewComments.equals(string)) {
			markModified();
		}
		reviewComments = string;
	}
	/**
	* @param date
	*/
	public void setReviewDate(Date date) {
		if (this.reviewDate == null || !this.reviewDate.equals(date)) {
			markModified();
		}
		reviewDate = date;
	}
	/**
	* Set the reference value to class :: pd.juvenilecase.JuvenileMAYSI
	*/
	public void setJuvenileMAYSIAssessId(String aJuvenileMAYSIAssessId) {
		if (this.juvenileMAYSIAssessId == null || !this.juvenileMAYSIAssessId.equals(aJuvenileMAYSIAssessId)) {
			markModified();
		}
		juvenileMAYSIAssess = null;
		this.juvenileMAYSIAssessId = aJuvenileMAYSIAssessId;
	}
	/**
	* Get the reference value to class :: pd.juvenilecase.JuvenileMAYSI
	*/
	public String getJuvenileMAYSIAssessId() {
		fetch();
		return juvenileMAYSIAssessId;
	}
	/**
	* Initialize class relationship to class pd.juvenilecase.JuvenileMAYSI
	*/
	private void initJuvenileMAYSIAssess() {
		if (juvenileMAYSIAssess == null) {
			try {
				juvenileMAYSIAssess = (JuvenileMAYSIMetadata) new mojo.km.persistence.Reference(juvenileMAYSIAssessId, JuvenileMAYSIMetadata.class).getObject();
			} catch (Throwable t) {
				juvenileMAYSIAssess = null;
			}
		}
	}
	/**
	* Gets referenced type pd.juvenilecase.JuvenileMAYSI
	*/
	public JuvenileMAYSIMetadata getJuvenileMAYSIAssess() {
		fetch();
		initJuvenileMAYSIAssess();
		return juvenileMAYSIAssess;
	}
	/**
	* set the type reference for class member juvenileMAYSIDetail
	*/
	public void setJuvenileMAYSIAssess(JuvenileMAYSIMetadata aJuvenileMAYSIAssess) {
		if (this.juvenileMAYSIAssess == null || !this.juvenileMAYSIAssess.equals(aJuvenileMAYSIAssess)) {
			markModified();
		}
		if (aJuvenileMAYSIAssess.getOID() == null) {
			new mojo.km.persistence.Home().bind(aJuvenileMAYSIAssess);
		}
		setJuvenileMAYSIAssessId("" + aJuvenileMAYSIAssess.getOID());
		this.juvenileMAYSIAssess = (JuvenileMAYSIMetadata) new mojo.km.persistence.Reference(aJuvenileMAYSIAssess).getObject();
	}
	/**
	* Set the reference value to class :: pd.contact.user.UserProfile
	*/
	public void setReviewerId(String aReviewerId) {
		if (this.reviewerId == null || !this.reviewerId.equals(aReviewerId)) {
			markModified();
		}
		reviewer = null;
		this.reviewerId = aReviewerId;
	}
	/**
	* Get the reference value to class :: pd.contact.user.UserProfile
	*/
	public String getReviewerId() {
		fetch();
		return reviewerId;
	}
	/**
	* Initialize class relationship to class pd.contact.user.UserProfile
	*/
	private void initReviewer() {
		if (reviewer == null) {
			try {
			//87191
				reviewer = UserProfile.find(reviewerId);//(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(reviewerId, pd.contact.user.UserProfile.class).getObject();
			} catch (Throwable t) {
				reviewer = null;
			}
		}
	}
	/**
	* Gets referenced type pd.contact.user.UserProfile
	*/
	public UserProfile getReviewer() {
		fetch();
		initReviewer();
		return reviewer;
	}
	/**
	* set the type reference for class member reviewer
	*/
	//87191
	public void setReviewer(UserProfile aReviewer) {
		/*if (this.reviewer == null || !this.reviewer.equals(aReviewer)) {
			markModified();
		}
		if (aReviewer.getOID() == null) {
			new mojo.km.persistence.Home().bind(aReviewer);
		}*/
		setReviewerId("" + aReviewer.getUserID());
		this.reviewer = aReviewer;//(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(aReviewer).getObject();
	}
	
	/**
	 * @return
	 */
	public String getProviderTypeReferredId()
	{
		fetch();
		return providerTypeReferredId;
	}

	/**
	 * @param string
	 */
	public void setProviderTypeReferredId(String string)
	{
		if ( this.providerTypeReferredId == null || ! this.providerTypeReferredId.equals(string) ) {
			markModified();
		}
		providerTypeReferredId = string;
	}

	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initProviderTypeReferred() {
		if (providerTypeReferred == null) {
			try {
				providerTypeReferred = (Code) new mojo.km.persistence.Reference(providerTypeReferredId, Code.class, "MAYSI_PROVIDER_TYPE_REFERRED").getObject();
				         
			} catch (Throwable t) {
				providerTypeReferred = null;
			}
		}
	}

	/**
	 * @return
	 */
	public Code getProviderTypeReferred()
	{
		initProviderTypeReferred();
		return providerTypeReferred;
	}

	/**
	 * @param string
	 */
	public void setProviderTypeReferred( Code aCode )
	{
		setProviderTypeReferredId( aCode.getOID().toString() );
		providerTypeReferred = null;
	}

	/**
	* @return The boolean.
	*/
	public boolean isSubReferral() {
		fetch();
		return subReferral;
	}
	
	/**
	* @param b The has previous m a y s i.
	*/
	public void setSubReferral(boolean b) {
		if (this.subReferral != b) {
			markModified();
		}
		subReferral = b;
	}
	
	/**
	* @return The boolean.
	*/
	public boolean isAssessComplete() {
		fetch();
		return assessComplete;
	}
	
	/**
	* @param b The has previous m a y s i.
	*/
	public void setAssessComplete(boolean b) {
		if (this.assessComplete != b) {
			markModified();
		}
		assessComplete = b;
	}
	public MAYSISubAssessResponseEvent getResponseEvent(){
		MAYSISubAssessResponseEvent myRespEvt=new MAYSISubAssessResponseEvent();
		myRespEvt.setSubAssessId(this.getOID().toString());
		myRespEvt.setAssessmentReviewdate(this.getReviewDate());
		if(this.getReviewDate()!=null){
			myRespEvt.setAssessmentReviewtime(DateUtil.getHHMMSSWithColonFromDate(this.getReviewDate()));
		}
		myRespEvt.setAssessComplete(this.isAssessComplete());
		myRespEvt.setProviderTypeId(this.getProviderTypeReferredId());
		if(this.getProviderTypeReferred()!=null)
			myRespEvt.setProviderType(this.getProviderTypeReferred().getDescription());
		
		myRespEvt.setReviewComments(this.getReviewComments());
		myRespEvt.setSubAssessOfficerId(this.getReviewerId());
		IName myName1=new NameBean();
		myRespEvt.setSubAssessOfficerName(myName1);
		if(this.getReviewer()!=null){
			myName1.setFirstName(this.getReviewer().getFirstName());
			myName1.setMiddleName(this.getReviewer().getMiddleName());
			myName1.setLastName(this.getReviewer().getLastName());
			
		}
		myRespEvt.setSubReferral(this.isSubReferral());
		return myRespEvt;
	}
}
