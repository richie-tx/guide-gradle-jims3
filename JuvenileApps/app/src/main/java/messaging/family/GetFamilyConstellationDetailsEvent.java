/*
 * Project: JIMS2
 * Class:   messaging.juvenileFamily.GetFamilyConstellationDetailsEvent
 * Version: 0.8.15 
 *
 * Date:    2005-09-28
 *
 * Author:  ANAND THORAT
 * Email:   athorat@jims.hctx.net
 */

package messaging.family;

import mojo.km.messaging.RequestEvent;

/**
 * Class GetFamilyConstellationDetailsEvent.
 *  
 * @author  Anand Thorat
 * @version  1.0.0
 */
public class GetFamilyConstellationDetailsEvent extends mojo.km.messaging.RequestEvent {

    // ------------------------------------------------------------------------
    // --- field                                                            ---
    // ------------------------------------------------------------------------

    public String constellationNum;


    // ------------------------------------------------------------------------
    // --- constructor                                                      ---
    // ------------------------------------------------------------------------

    /**
     * @roseuid 43299A7E02FD
     */
    public GetFamilyConstellationDetailsEvent() {

    }//end of messaging.juvenileFamily.GetFamilyConstellationDetailsEvent.GetFamilyConstellationDetailsEvent


    // ------------------------------------------------------------------------
    // --- methods                                                          ---
    // ------------------------------------------------------------------------

    /**
     *  
     * @param constellationNum @roseuid 432997A902AF
     */
    public void setConstellationNum(String familyNum) {
        this.constellationNum = familyNum;

    }//end of messaging.juvenileFamily.GetFamilyConstellationDetailsEvent.setFamilyNum

    /**
     *  
     * @return  java.lang.String
     * @roseuid 432997A902B1
     */
    public String getConstellationNum() {
        return constellationNum;
    }//end of messaging.juvenileFamily.GetFamilyConstellationDetailsEvent.getFamilyNum

} // end GetFamilyConstellationDetailsEvent
