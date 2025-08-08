package pd.supervision.administercompliance;

import java.util.Iterator;

import messaging.administercompliance.UpdateNCResponseEvent;
import messaging.administercompliance.reply.NCCommentResponseEvent;
import messaging.administercompliance.reply.NCResponseResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.MessageUtil;
import naming.ViolationReportConstants;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class Comment extends PersistentObject
{
	private String type;
	private String comments;
	private int ncResponseId; // this is the oid of violation report FK
	private String commentType;
	
    
	
	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		fetch();
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		if (this.comments == null || !this.comments.equals(comments))
		{
			markModified();
		}
		this.comments = comments;
	}
	/**
	 * @return Returns the ncResponseId.
	 */
	public int getNcResponseId() {
		fetch();
		return ncResponseId;
	}
	/**
	 * @param ncResponseId The ncResponseId to set.
	 */
	public void setNcResponseId(int ncResponseId) {
		if (this.ncResponseId != ncResponseId)
		{
			markModified();
		}
		this.ncResponseId = ncResponseId;
	}
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		fetch();
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		if (this.type == null || !this.type.equals(type))
		{
			markModified();
		}
		this.type = type;
	}
	public static Iterator findAll(IEvent anEvent){
        IHome home = new Home();
        return home.findAll(anEvent, Comment.class);
    }
    public static Iterator findAll(String attrName, String attrValue){
        IHome home = new Home();
        return home.findAll(attrName, attrValue, Comment.class);
    }
    
    public static Iterator findAllByNumericParam(String attrName, String attrValue){
        IHome home = new Home();
        return home.findAll(attrName, new Integer(attrValue), Comment.class);
    }
	/**
	 * @return
	 */
	public NCResponseResponseEvent getResponse() {
		NCResponseResponseEvent response = new NCResponseResponseEvent();
		response.setNcResponseId(new StringBuffer("").append(this.getNcResponseId()).toString());
		response.setComments(this.getComments());	
		response.setCommentType(this.getCommentType());	
		if(this.type.endsWith("V")){
			response.setCommentType(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_VIOLATION);
		}else if(this.type.endsWith("M")){
			response.setCommentType(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_MOTION);
		}else{
			response.setCommentType(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_OTHER);
		}
		return response;
	}
	 
	/**
	 * @param reqEvent
	 */
	public void setComments(UpdateNCResponseEvent reqEvent) {
			this.setComments(reqEvent.getComments());
			this.setNcResponseId(Integer.parseInt(reqEvent.getNcResponseId()));	
			this.setType(reqEvent.getRequestType());
	}	
	
	/**
	 * @param reqEvent
	 */
	public void setComments(String ncResponseId, String requestType, String comments, String commentsType) {
			this.setComments(comments);
			this.setNcResponseId((Integer.parseInt(ncResponseId)));	
			this.setType(requestType);
			this.setCommentType(commentsType);
	}
	
	/**
	 * @param ncResponseId
	 */
	public static void delete(String ncResponseId){
		Iterator iterator = Comment.findAll(ViolationReportConstants.PARAM_NCRESPONSE_ID, ncResponseId);
		while(iterator.hasNext()){
			Comment comment = (Comment) iterator.next();
			comment.delete();
		}
	}
	
	private NCCommentResponseEvent getResponseEvent(){
		NCCommentResponseEvent resp = new NCCommentResponseEvent();
		resp.setComment(this.getComments());
		resp.setNcResponseId(new StringBuffer("").append(this.getNcResponseId()).toString());
		resp.setReportType(this.getType());
		
		if(ViolationReportConstants.REQUEST_RECOMMENDATION.equalsIgnoreCase(this.getType())){
			resp.setCommentType(this.getCommentType());
		}else if(ViolationReportConstants.REQUEST_PREVIOUS_COURT_ACTIVITY.equalsIgnoreCase(this.getType())){
			if(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_VIOLATION.equalsIgnoreCase(this.getCommentType())){
				resp.setCommentType(this.getCommentType());
			}else if(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_MOTION.equalsIgnoreCase(this.getCommentType())){
				resp.setCommentType(this.getCommentType());
			}else{
				resp.setCommentType(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_OTHER);
			}
		}	
		return resp;
	}
	
	public static void post(String ncResponseId){
		Iterator iterator = Comment.findAll(ViolationReportConstants.PARAM_NCRESPONSE_ID, ncResponseId);
		while(iterator.hasNext()){
			Comment comment = (Comment) iterator.next();
			MessageUtil.postReply(comment.getResponseEvent());
		}
	}
	/**
	 * @return the commentType
	 */
	public String getCommentType() {
		fetch();
		return commentType;
	}
	/**
	 * @param commentType the commentType to set
	 */
	public void setCommentType(String commentType) {
		if (this.commentType == null || !this.commentType.equals(commentType))
		{
			markModified();
		}
		this.commentType = commentType;
	}
}
