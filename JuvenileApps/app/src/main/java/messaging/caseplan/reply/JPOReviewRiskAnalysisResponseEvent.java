/*
 * Created on Jun 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.caseplan.reply;

import java.util.Date;

import messaging.casefile.reply.ActivityResponseEvent;
import mojo.km.messaging.ResponseEvent;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JPOReviewRiskAnalysisResponseEvent extends ResponseEvent implements Comparable{
	private Date entryDate;
	private String comments;
	private String recommendation;
	
	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return Returns the recommendation.
	 */
	public String getRecommendation() {
		return recommendation;
	}
	/**
	 * @param recommendation The recommendation to set.
	 */
	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		if(o == null)
			return 1; // this makes any null objects go to the bottom change this to -1 if you want the top of the list
		if(this.entryDate==null)
			return -1; // this makes any null objects go to the bottom change this to 1 if you want the top of the list
		JPOReviewRiskAnalysisResponseEvent evt = (JPOReviewRiskAnalysisResponseEvent)o;
		return evt.getEntryDate().compareTo(entryDate);
	}
}
