package pd.juvenilecase;

import java.util.Date;
import java.util.Iterator;

import messaging.facility.GetAllJuvFacAdmissionCommentsEvent;
import messaging.facility.GetJuvenileFacilityAdmissionCommentsEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * 
 * @author sthyagarajan
 * //added for u.s #51737
 *
 */
public class JJSFacilityAdmissionComments extends PersistentObject{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** attributes **/
	private String juvenileNum;
	private String comments;
	private Date createDate;
	private String detentionId;
	private String createUserID;
	
	
	/**
	 * 
	 */
	public JJSFacilityAdmissionComments() {
	}

	
	/**
	 * findAll
	 * @param attributeName
	 * @param attributeValue
	 * @return Iterator
	 */
	public static Iterator<JJSFacilityAdmissionComments> findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		return home.findAll(attributeName,attributeValue,JJSFacilityAdmissionComments.class);
	}	
	
	/**
	* @return 
	* @param event
	*/
	static public Iterator<JJSFacilityAdmissionComments> findAll(GetJuvenileFacilityAdmissionCommentsEvent event) {
		IHome home = new Home();
		return home.findAll(event, JJSFacilityAdmissionComments.class);	
	}
	
	
	/**
	* @return 
	* @param event
	*/
	static public Iterator<JJSFacilityAdmissionComments> findAll(GetAllJuvFacAdmissionCommentsEvent event) {
		IHome home = new Home();
		return home.findAll(event, JJSFacilityAdmissionComments.class);	
	}
	
	/**
	* @return
	* 
	*/
	static public Iterator<JJSFacilityAdmissionComments> findAll()
	{
		IHome home = new Home();
		return home.findAll(JJSFacilityAdmissionComments.class);
	}
	
		
	/**
	 * @return the juvenileNum
	 */
	public String getJuvenileNum() {
		fetch();
		return juvenileNum;
	}
	/**
	 * @param juvenileNum the juvenileNum to set
	 */
	public void setJuvenileNum(String juvenileNum) {
		if(this.juvenileNum==null || !this.juvenileNum.equals(juvenileNum))
		{
			markModified();
		}
		this.juvenileNum = juvenileNum;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		fetch();
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		if(this.comments==null || !this.comments.equals(comments))
		{
			markModified();
		}
		this.comments = comments;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		fetch();
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the detentionId
	 */
	public String getDetentionId() {
		fetch();
		return detentionId;
	}

	/**
	 * @param detentionId the detentionId to set
	 */
	public void setDetentionId(String detentionId) {
		if(this.detentionId==null || !this.detentionId.equals(detentionId))
		{
			markModified();
		}
		this.detentionId = detentionId;
	}


	/**
	 * @return the createUserID
	 */
	public String getCreateUserID() {
		fetch();
		return createUserID;
	}


	/**
	 * @param createUserID the createUserID to set
	 */
	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}
}
