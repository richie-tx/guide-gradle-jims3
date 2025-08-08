/*
 * Project: JIMS2
 * Class:   messaging.juvenileFamily.reply.FamilyConstellationListResponseEvent
 * Version: 0.8.15 
 *
 * Date:    2005-09-28
 *
 * Author:  ANAND THORAT
 * Email:   athorat@jims.hctx.net
 */

package messaging.juvenilecase.reply;

import java.util.Date;
import mojo.km.messaging.ResponseEvent;

/**
 *  
 * @author  athorat
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 * @version  1.0.0
 */
public class FamilyConstellationListResponseEvent extends mojo.km.messaging.ResponseEvent{

    // ------------------------------------------------------------------------
    // --- fields                                                           ---
    // ------------------------------------------------------------------------

    private String familyNum;

    private Date entryDate;

    private boolean active;

    private String guardiansNames;


    // ------------------------------------------------------------------------
    // --- constructor                                                      ---
    // ------------------------------------------------------------------------

    /**
     */
    public FamilyConstellationListResponseEvent() {
        super();
        // TODO Auto-generated constructor stub
    }//end of messaging.juvenileFamily.reply.FamilyConstellationListResponseEvent.FamilyConstellationListResponseEvent


    // ------------------------------------------------------------------------
    // --- methods                                                          ---
    // ------------------------------------------------------------------------

    /**
     *  
     * @return  The boolean.
     */
    public boolean isActive() {
        return active;
    }//end of messaging.juvenileFamily.reply.FamilyConstellationListResponseEvent.isActive

    /**
     *  
     * @return  The entry date.
     */
    public Date getEntryDate() {
        return entryDate;
    }//end of messaging.juvenileFamily.reply.FamilyConstellationListResponseEvent.getEntryDate

    /**
     *  
     * @return  The family num.
     */
    public String getFamilyNum() {
        return familyNum;
    }//end of messaging.juvenileFamily.reply.FamilyConstellationListResponseEvent.getFamilyNum

    /**
     *  
     * @param b The active.
     */
    public void setActive(boolean b) {
        active = b;
    }//end of messaging.juvenileFamily.reply.FamilyConstellationListResponseEvent.setActive

    /**
     *  
     * @param date The entry date.
     */
    public void setEntryDate(Date date) {
        entryDate = date;
    }//end of messaging.juvenileFamily.reply.FamilyConstellationListResponseEvent.setEntryDate

    /**
     *  
     * @param string The family num.
     */
    public void setFamilyNum(String string) {
        familyNum = string;
    }//end of messaging.juvenileFamily.reply.FamilyConstellationListResponseEvent.setFamilyNum

    /**
     *  
     * @return  The guardians names.
     */
    public String getGuardiansNames() {
        return guardiansNames;
    }//end of messaging.juvenileFamily.reply.FamilyConstellationListResponseEvent.getGuardiansNames

    /**
     *  
     * @param string The guardians names.
     */
    public void setGuardiansNames(String string) {
        guardiansNames = string;
    }//end of messaging.juvenileFamily.reply.FamilyConstellationListResponseEvent.setGuardiansNames

} // end FamilyConstellationListResponseEvent
