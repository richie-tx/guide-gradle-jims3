/*
 * Project: JIMS2
 * Class:   messaging.juvenileFamily.reply.FamilyConstellationTraitsResponseEvent
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
 * @version  0.8.15 
 */
public class FamilyConstellationTraitsResponseEvent extends mojo.km.messaging.ResponseEvent implements Comparable {

    // ------------------------------------------------------------------------
    // --- fields                                                           ---
    // ------------------------------------------------------------------------

    private String dynamicTypeId;

    private String id;

    private Date entryDate;

    private String statusId;

    private String levelId;
	
	private String comments;

    // ------------------------------------------------------------------------
    // --- constructor                                                      ---
    // ------------------------------------------------------------------------

    /**
     */
    public FamilyConstellationTraitsResponseEvent() {
        super();
        this.setTopic(PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_TRAIT_LIST_TOPIC);
    }//end of messaging.juvenileFamily.reply.FamilyConstellationTraitsResponseEvent.FamilyConstellationTraitsResponseEvent


    // ------------------------------------------------------------------------
    // --- methods                                                          ---
    // ------------------------------------------------------------------------

    /**
     *  
     * @return  The dynamic trait id.
     */
    public String getDynamicTypeId() {
        return dynamicTypeId;
    }//end of messaging.juvenileFamily.reply.FamilyConstellationTraitsResponseEvent.getDynamicTraitId

    /**
     *  
     * @return  The entry date.
     */
    public Date getEntryDate() {
        return entryDate;
    }//end of messaging.juvenileFamily.reply.FamilyConstellationTraitsResponseEvent.getEntryDate

    /**
     *  
     * @return  The id.
     */
    public String getId() {
        return id;
    }//end of messaging.juvenileFamily.reply.FamilyConstellationTraitsResponseEvent.getId

    /**
     *  
     * @return  The level id.
     */
    public String getLevelId() {
        return levelId;
    }//end of messaging.juvenileFamily.reply.FamilyConstellationTraitsResponseEvent.getLevelId

    /**
     *  
     * @return  The status id.
     */
    public String getStatusId() {
        return statusId;
    }//end of messaging.juvenileFamily.reply.FamilyConstellationTraitsResponseEvent.getStatusId

    /**
     *  
     * @param string The dynamic trait id.
     */
    public void setDynamicTypeId(String string) {
        dynamicTypeId = string;
    }//end of messaging.juvenileFamily.reply.FamilyConstellationTraitsResponseEvent.setDynamicTraitId

    /**
     *  
     * @param date The entry date.
     */
    public void setEntryDate(Date date) {
        entryDate = date;
    }//end of messaging.juvenileFamily.reply.FamilyConstellationTraitsResponseEvent.setEntryDate

    /**
     *  
     * @param string The id.
     */
    public void setId(String string) {
        id = string;
    }//end of messaging.juvenileFamily.reply.FamilyConstellationTraitsResponseEvent.setId

    /**
     *  
     * @param string The level id.
     */
    public void setLevelId(String string) {
        levelId = string;
    }//end of messaging.juvenileFamily.reply.FamilyConstellationTraitsResponseEvent.setLevelId

    /**
     *  
     * @param string The status id.
     */
    public void setStatusId(String string) {
        statusId = string;
    }//end of messaging.juvenileFamily.reply.FamilyConstellationTraitsResponseEvent.setStatusId

	/**
	 * @return
	 */
	public String getComments()
	{
		return comments;
	}

	/**
	 * @param string
	 */
	public void setComments(String string)
	{
		comments = string;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		if(o == null)
			return 1; // this makes any null objects go to the bottom change this to -1 if you want the top of the list
		if(this.entryDate==null)
			return -1; // this makes any null objects go to the bottom change this to 1 if you want the top of the list
		FamilyConstellationTraitsResponseEvent evt = (FamilyConstellationTraitsResponseEvent)o;
		return evt.getEntryDate().compareTo(entryDate);
	}

} // end FamilyConstellationTraitsResponseEvent
