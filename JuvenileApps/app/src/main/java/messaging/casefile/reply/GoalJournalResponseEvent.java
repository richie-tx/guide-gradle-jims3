/*
 * Created on May 13, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.casefile.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author ugopinath
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GoalJournalResponseEvent extends ResponseEvent implements Comparable{
	private String goalID;
	private Date entryDate;	
	private String goalDescription;
	private String endRecommendations;
	private String progressNotes;
	private String createdBy;
	
	public String getEndRecommendations() {
		return endRecommendations;
	}
	public void setEndRecommendations(String endRecommendations) {
		this.endRecommendations = endRecommendations;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public String getGoalDescription() {
		return goalDescription;
	}
	public void setGoalDescription(String goalDescription) {
		this.goalDescription = goalDescription;
	}
	public String getGoalID() {
		return goalID;
	}
	public void setGoalID(String goalID) {
		this.goalID = goalID;
	}
	public String getProgressNotes() {
		return progressNotes;
	}
	public void setProgressNotes(String progressNotes) {
		this.progressNotes = progressNotes;
	}
	/* 
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object obj)
    {
        if (obj == null)
            return -1;
        GoalJournalResponseEvent evt = (GoalJournalResponseEvent) obj;
        int result = 0;

        try
        {
            if (this.getEntryDate()!= null || evt.getEntryDate()!= null)
            {
                if (this.getEntryDate() == null)
                    return -1; // this makes any null objects go to the bottom change this to 1 if
                               // you want the top of the list
                if (evt.getEntryDate() == null)
                    return 1; // this makes any null objects go to the bottom change this to -1 if
                              // you want the top of the list
                //result = this.getEntryDate().compareTo(evt.getEntryDate()); 
                result = evt.getEntryDate().compareTo(this.getEntryDate()); // backwards in order to
                															// get list to show up
                															// most recent first
                
            }

        }
        catch (NumberFormatException e)
        {
            result = 0;
        }

        return result;
    }
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
}
