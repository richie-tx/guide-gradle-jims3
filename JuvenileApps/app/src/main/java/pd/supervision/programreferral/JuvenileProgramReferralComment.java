// Source file:
// C:\\views\\MJCW\\app\\src\\pd\\supervision\\calendar\\JuvenileProgramReferralComment.java

package pd.supervision.programreferral;

import java.util.Date;
import java.util.Iterator;

import messaging.programreferral.reply.ProgramReferralCommentResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class JuvenileProgramReferralComment extends PersistentObject {
			
	private String commentText;

	private String userName;
	
	private String programReferralId;
	
	private Date commentDate;

	/**
	 * @roseuid 463BA4EF0365
	 */
	public JuvenileProgramReferralComment() {

	}

	/**
	 * @return Returns the commentText.
	 */
	public String getCommentText() {
		fetch();
		return commentText;
	}

	/**
	 * @return Returns the userName.
	 */
	public String getUserName() {
		fetch();
		return userName;
	}

	/**
	 * @param commentText
	 *            The commentText to set.
	 */
	public void setCommentText(String commentText) {
		if (this.commentText == null || !this.commentText.equals(commentText)){
			markModified();
		}
		this.commentText = commentText;
	}

	/**
	 * @param userName
	 *            The userName to set.
	 */
	public void setUserName(String userName) {
		if (this.userName == null || !this.userName.equals(userName)){
			markModified();
		}		
		this.userName = userName;
	}
	/**
	 * @return Returns the commentsDate.
	 */
	public Date getCommentDate() {
		fetch();
		return commentDate;
	}
	/**
	 * @param commentsDate The commentsDate to set.
	 */
	public void setCommentDate(Date commentDate) {
		if (this.commentDate == null || !this.commentDate.equals(commentDate)){
			markModified();
		}
		this.commentDate = commentDate;
	}
	/**
	 * @return Returns the programReferralId.
	 */
	public String getProgramReferralId() {
		fetch();
		return programReferralId;
	}
	/**
	 * @param programReferralId The programReferralId to set.
	 */
	public void setProgramReferralId(String programReferralId) {
		if (this.programReferralId == null || !this.programReferralId.equals(programReferralId)){
			markModified();
		}	
		this.programReferralId = programReferralId;
	}
	
	public ProgramReferralCommentResponseEvent getValue(){
		ProgramReferralCommentResponseEvent resp = new ProgramReferralCommentResponseEvent();
		resp.setProgramReferralId(this.getProgramReferralId());
		resp.setCommentsDate(this.getCommentDate());
		resp.setCommentText(this.getCommentText());
		resp.setUserName(this.getUserName());
		return resp;
	}
	
	/**
	* @return Iterator referaalComments
	* @param attrName name for the attribute for where clause
	* @param attrValue value to be checked in the where clause
	*/
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		Iterator referralComments = home.findAll(attrName, attrValue, JuvenileProgramReferralComment.class);
		return referralComments;
	}
	/**
	     * @return
	     * @param event
	     */
	    static public Iterator<JuvenileProgramReferralComment> findAll(IEvent event)
	    {
		IHome home = new Home();		
		 return home.findAll(event, JuvenileProgramReferralComment.class);
		
	    }

}
