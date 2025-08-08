//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administercompliance\\GetNonCompliantEventsEvent.java

package messaging.administercompliance;

import mojo.km.messaging.RequestEvent;

public class UpdateNCCommentEvent extends RequestEvent 
{
   private String ncResponseId;
   private String comments;
   private String commentsType;
   private String requestType;
   
   /**
 * @return the requestType
 */
public String getRequestType() {
	return requestType;
}

/**
 * @param requestType the requestType to set
 */
public void setRequestType(String requestType) {
	this.requestType = requestType;
}

/**
    * @roseuid 473B8997017F
    */
   public UpdateNCCommentEvent() 
   {
	  
   }

	public String getComments() {
		return comments;
	}
	
	public String getCommentsType() {
		return commentsType;
	}
	
	public String getNcResponseId() {
		return ncResponseId;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public void setCommentsType(String commentsType) {
		this.commentsType = commentsType;
	}
	
	public void setNcResponseId(String ncResponseId) {
		this.ncResponseId = ncResponseId;
	} 
}
