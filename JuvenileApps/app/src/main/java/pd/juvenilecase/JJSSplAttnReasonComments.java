/**
 * 
 */
package pd.juvenilecase;

import java.util.Date;
import java.util.Iterator;

import messaging.facility.GetAllJuvFacSplAttnReasonCommentsEvent;
import messaging.facility.GetJuvenileFacilitySplAttnReasonCommentsEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author sthyagarajan
 *
 */
public class JJSSplAttnReasonComments extends PersistentObject {

	
	/** attributes **/
	private String juvenileNum;
	private String comments;
	private Date createDate;
	private String detentionId;
	private String createUserID;
	
	
	/**
	 * 
	 */
	public JJSSplAttnReasonComments() {
	}

	
	/**
	 * findAll
	 * @param attributeName
	 * @param attributeValue
	 * @return Iterator
	 */
	public static Iterator<JJSSplAttnReasonComments> findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		return home.findAll(attributeName,attributeValue,JJSSplAttnReasonComments.class);
	}	
	
	/**
	* @return 
	* @param event
	*/
	static public Iterator<JJSSplAttnReasonComments> findAll(GetJuvenileFacilitySplAttnReasonCommentsEvent event) {
		IHome home = new Home();
		return home.findAll(event, JJSSplAttnReasonComments.class);	
	}
	
	
	/**
	* @return 
	* @param event
	*/
	static public Iterator<JJSSplAttnReasonComments> findAll(GetAllJuvFacSplAttnReasonCommentsEvent event) {
		IHome home = new Home();
		return home.findAll(event, JJSSplAttnReasonComments.class);	
	}
	
	/**
	* @return
	* 
	*/
	static public Iterator<JJSSplAttnReasonComments> findAll()
	{
		IHome home = new Home();
		return home.findAll(JJSSplAttnReasonComments.class);
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
