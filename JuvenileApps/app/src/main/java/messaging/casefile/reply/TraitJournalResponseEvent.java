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
public class TraitJournalResponseEvent extends ResponseEvent implements Comparable{
	
	private String traitId;
	private String traitStatus;	
	private String comments;
	private String traitName;
	private String traitTypeId;
	private Date traitDate;
	private String createdBy;
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getTraitId() {
		return traitId;
	}
	public void setTraitId(String traitId) {
		this.traitId = traitId;
	}
	public String getTraitName() {
		return traitName;
	}
	public void setTraitName(String traitName) {
		this.traitName = traitName;
	}
	
	public String getTraitTypeId() {
		return traitTypeId;
	}
	public void setTraitTypeId(String traitTypeId) {
		this.traitTypeId = traitTypeId;
	}
	public String getTraitStatus() {
		return traitStatus;
	}
	public void setTraitStatus(String traitStatus) {
		this.traitStatus = traitStatus;
	}
	public Date getTraitDate() {
		return traitDate;
	}
	public void setTraitDate(Date traitDate) {
		this.traitDate = traitDate;
	};
	
	/* 
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object obj)
    {
        if (obj == null)
            return -1;
        TraitJournalResponseEvent evt = (TraitJournalResponseEvent) obj;
        int result = 0;

        try
        {
            if (this.getTraitDate() != null || evt.getTraitDate() != null)
            {
                if (this.getTraitDate() == null)
                    return -1; // this makes any null objects go to the bottom change this to 1 if
                               // you want the top of the list
                if (evt.getTraitDate() == null)
                    return 1; // this makes any null objects go to the bottom change this to -1 if
                              // you want the top of the list
                //result = this.getTraitDate().compareTo(evt.getTraitDate()); 
                result = evt.getTraitDate().compareTo(this.getTraitDate());  // backwards in order to
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
