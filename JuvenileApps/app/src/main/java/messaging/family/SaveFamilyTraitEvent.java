/*
 * Project: JIMS2
 * Class:   messaging.juvenileFamily.CreateFamilyTraitEvent
 * Version: 0.8.15 
 *
 * Date:    2005-09-28
 *
 * Author:  ANAND THORAT
 * Email:   athorat@jims.hctx.net
 */

package messaging.family;

import java.util.Date;
import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeRequest;

/**
 *  
 * @author  athorat
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 * @version  0.8.15 
 */
public class SaveFamilyTraitEvent extends RequestEvent{

    // ------------------------------------------------------------------------
    // --- fields                                                           ---
    // ------------------------------------------------------------------------

    private String dynamicTraitId;

    private Date entryDate;

    private String statusId;

    private String levelId;


    private String comments;


    // ------------------------------------------------------------------------
    // --- constructor                                                      ---
    // ------------------------------------------------------------------------

    /**
     */
    public SaveFamilyTraitEvent() {
        super();
        // TODO Auto-generated constructor stub
    }//end of messaging.juvenileFamily.CreateFamilyTraitEvent.CreateFamilyTraitEvent


    // ------------------------------------------------------------------------
    // --- methods                                                          ---
    // ------------------------------------------------------------------------

    /**
     *  
     * @return  The dynamic trait id.
     */
    public String getDynamicTraitId() {
        return dynamicTraitId;
    }//end of messaging.juvenileFamily.CreateFamilyTraitEvent.getDynamicTraitId

    /**
     *  
     * @return  The entry date.
     */
    public Date getEntryDate() {
        return entryDate;
    }//end of messaging.juvenileFamily.CreateFamilyTraitEvent.getEntryDate


    /**
     *  
     * @return  The level id.
     */
    public String getLevelId() {
        return levelId;
    }//end of messaging.juvenileFamily.SaveFamilyTraitEvent.getLevelId

    /**
     *  
     * @return  The status id.
     */
    public String getStatusId() {
        return statusId;
    }//end of messaging.juvenileFamily.SaveFamilyTraitEvent.getStatusId

    /**
     *  
     * @param string The dynamic trait id.
     */
    public void setDynamicTraitId(String string) {
        dynamicTraitId = string;
    }//end of messaging.juvenileFamily.SaveFamilyTraitEvent.setDynamicTraitId

    /**
     *  
     * @param date The entry date.
     */
    public void setEntryDate(Date date) {
        entryDate = date;
    }//end of messaging.juvenileFamily.SaveFamilyTraitEvent.setEntryDate


    /**
     *  
     * @param string The level id.
     */
    public void setLevelId(String string) {
        levelId = string;
    }//end of messaging.juvenileFamily.SaveFamilyTraitEvent.setLevelId

    /**
     *  
     * @param string The status id.
     */
    public void setStatusId(String string) {
        statusId = string;
    }//end of messaging.juvenileFamily.SaveFamilyTraitEvent.setStatusId

    /**
     *  
     * @return  The comments.
     */
    public String getComments() {
        return comments;
    }//end of messaging.juvenileFamily.SaveFamilyTraitEvent.getComments

    /**
     *  
     * @param string The comments.
     */
    public void setComments(String string) {
        comments = string;
    }//end of messaging.juvenileFamily.SaveFamilyTraitEvent.setComments

} // end SaveFamilyTraitEvent
