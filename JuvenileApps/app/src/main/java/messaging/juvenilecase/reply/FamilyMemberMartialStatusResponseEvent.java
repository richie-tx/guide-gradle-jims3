/*
 * Project: JIMS2
 * Class:   messaging.juvenileFamily.reply.FamilyMemberMartialStatusResponseEvent
 * Version: 0.8.15 
 *
 * Date:    2005-09-28
 *
 * Author:  ANAND THORAT
 * Email:   athorat@jims.hctx.net
 */

package messaging.juvenilecase.reply;

import java.util.Date;

import naming.PDJuvenileFamilyConstants;

/**
 *  
 * @author  athorat
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 * @version  1.0.0
 */
public class FamilyMemberMartialStatusResponseEvent extends mojo.km.messaging.ResponseEvent implements Comparable  {

    // ------------------------------------------------------------------------
    // --- fields                                                           ---
    // ------------------------------------------------------------------------

    private String martialStatusId = null;

	private String martialId = null;

    private Date marriageDate = null;

    private Date divorceDate = null;

    private String numberOfChildren = null;

	private Date entryDate	= null;
	
	private String relatedFamMemId;
	private String relatedFamMemFirstName;
	private String relatedFamMemMiddleName;
	private String relatedFamMemLastName;
	

    // ------------------------------------------------------------------------
    // --- constructor                                                      ---
    // ------------------------------------------------------------------------

    /**
     */
    public FamilyMemberMartialStatusResponseEvent() {
        this.setTopic(PDJuvenileFamilyConstants.FAMILY_MEMBER_MARITAL_STATUS_TOPIC);
        // TODO Auto-generated constructor stub
    }//end of messaging.juvenileFamily.reply.FamilyMemberMartialStatusResponseEvent.FamilyMemberMartialStatusResponseEvent


    // ------------------------------------------------------------------------
    // --- methods                                                          ---
    // ------------------------------------------------------------------------

    /**
     *  
     * @return  The divorce date.
     */
    public Date getDivorceDate() {
        return divorceDate;
    }//end of messaging.juvenileFamily.reply.FamilyMemberMartialStatusResponseEvent.getDivorceDate

    /**
     *  
     * @return  The marriage date.
     */
    public Date getMarriageDate() {
        return marriageDate;
    }//end of messaging.juvenileFamily.reply.FamilyMemberMartialStatusResponseEvent.getMarriageDate

    /**
     *  
     * @return  The martial status.
     */
    public String getMartialStatusId() {
        return martialStatusId;
    }//end of messaging.juvenileFamily.reply.FamilyMemberMartialStatusResponseEvent.getMartialStatus

    /**
     *  
     * @return  The number of children.
     */
    public String getNumberOfChildren() {
        return numberOfChildren;
    }//end of messaging.juvenileFamily.reply.FamilyMemberMartialStatusResponseEvent.getNumberOfChildren

    /**
     *  
     * @param date The divorce date.
     */
    public void setDivorceDate(Date date) {
        divorceDate = date;
    }//end of messaging.juvenileFamily.reply.FamilyMemberMartialStatusResponseEvent.setDivorceDate

    /**
     *  
     * @param date The marriage date.
     */
    public void setMarriageDate(Date date) {
        marriageDate = date;
    }//end of messaging.juvenileFamily.reply.FamilyMemberMartialStatusResponseEvent.setMarriageDate

    /**
     *  
     * @param string The martial status.
     */
    public void setMartialStatusId(String string) {
        martialStatusId = string;
    }//end of messaging.juvenileFamily.reply.FamilyMemberMartialStatusResponseEvent.setMartialStatus

    /**
     *  
     * @param string The number of children.
     */
    public void setNumberOfChildren(String string) {
        numberOfChildren = string;
    }//end of messaging.juvenileFamily.reply.FamilyMemberMartialStatusResponseEvent.setNumberOfChildren

	/**
	 * @return
	 */
	public Date getEntryDate()
	{
		return entryDate;
	}

	/**
	 * @param string
	 */
	public void setEntryDate(Date date)
	{
		entryDate = date;
	}

	/**
	 * @return
	 */
	public String getMartialId()
	{
		return martialId;
	}

	/**
	 * @param string
	 */
	public void setMartialId(String string)
	{
		martialId = string;
	}

	/**
	 * @return Returns the relatedFamMemFirstName.
	 */
	public String getRelatedFamMemFirstName() {
		return relatedFamMemFirstName;
	}
	/**
	 * @param relatedFamMemFirstName The relatedFamMemFirstName to set.
	 */
	public void setRelatedFamMemFirstName(String relatedFamMemFirstName) {
		this.relatedFamMemFirstName = relatedFamMemFirstName;
	}
	/**
	 * @return Returns the relatedFamMemId.
	 */
	public String getRelatedFamMemId() {
		return relatedFamMemId;
	}
	/**
	 * @param relatedFamMemId The relatedFamMemId to set.
	 */
	public void setRelatedFamMemId(String relatedFamMemId) {
		this.relatedFamMemId = relatedFamMemId;
	}
	/**
	 * @return Returns the relatedFamMemLastName.
	 */
	public String getRelatedFamMemLastName() {
		return relatedFamMemLastName;
	}
	/**
	 * @param relatedFamMemLastName The relatedFamMemLastName to set.
	 */
	public void setRelatedFamMemLastName(String relatedFamMemLastName) {
		this.relatedFamMemLastName = relatedFamMemLastName;
	}
	/**
	 * @return Returns the relatedFamMemMiddleName.
	 */
	public String getRelatedFamMemMiddleName() {
		return relatedFamMemMiddleName;
	}
	/**
	 * @param relatedFamMemMiddleName The relatedFamMemMiddleName to set.
	 */
	public void setRelatedFamMemMiddleName(String relatedFamMemMiddleName) {
		this.relatedFamMemMiddleName = relatedFamMemMiddleName;
	}


	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj) throws ClassCastException
	{
		if(obj==null)
			return -1;
		FamilyMemberMartialStatusResponseEvent evt = (FamilyMemberMartialStatusResponseEvent) obj;
		int result = 0;
		if(getEntryDate() != null && evt.getEntryDate() != null )
		{
			try{
				if(this.entryDate!=null || evt.getEntryDate()!=null){
					if(evt.getEntryDate()==null)
						return 1; // this makes any null objects go to the bottom change this to -1 if you want the top of the list
					if(this.entryDate==null)
						return -1; // this makes any null objects go to the bottom change this to 1 if you want the top of the list
					
					if(this.entryDate==null)
						return -1;
					if(evt.getEntryDate()==null)
						return 1;
					result= evt.getEntryDate().compareTo(this.entryDate); // backwards in order to get list to show up most recent first
				}
			}
			catch(NumberFormatException e){
				result = 0;
			}
		}
		return result;
	}

} // end FamilyMemberMartialStatusResponseEvent
