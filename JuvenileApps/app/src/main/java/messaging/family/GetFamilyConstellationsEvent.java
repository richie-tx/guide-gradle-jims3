/*
 * Project: JIMS2
 * Class:   messaging.juvenileFamily.GetFamilyConstellationsEvent
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
 * Class GetFamilyConstellationsEvent.
 *  
 * @author  Anand Thorat
 * @version  1.0.0
 */
public class GetFamilyConstellationsEvent extends mojo.km.messaging.RequestEvent {

    // ------------------------------------------------------------------------
    // --- fields                                                           ---
    // ------------------------------------------------------------------------

    public String juvenileNum;

    public String familyNum;


    // ------------------------------------------------------------------------
    // --- constructor                                                      ---
    // ------------------------------------------------------------------------

    /**
     * @roseuid 43299A4D0157
     */
    public GetFamilyConstellationsEvent() {

    }//end of messaging.juvenileFamily.GetFamilyConstellationsEvent.GetFamilyConstellationsEvent


    // ------------------------------------------------------------------------
    // --- methods                                                          ---
    // ------------------------------------------------------------------------

    /**
     *  
     * @param juvenileNum @roseuid 432997A90263
     */
    public void setJuvenileNum(String juvenileNum) {
        this.juvenileNum = juvenileNum;
    }//end of messaging.juvenileFamily.GetFamilyConstellationsEvent.setJuvenileNum

    /**
     *  
     * @return  String
     * @roseuid 432997A90265
     */
    public String getJuvenileNum() {
        return juvenileNum;
    }//end of messaging.juvenileFamily.GetFamilyConstellationsEvent.getJuvenileNum

    /**
     *  
     * @return  The family num.
     */
    public String getFamilyNum() {
        return familyNum;
    }//end of messaging.juvenileFamily.GetFamilyConstellationsEvent.getFamilyNum

    /**
     *  
     * @param string The family num.
     */
    public void setFamilyNum(String string) {
        familyNum = string;
    }//end of messaging.juvenileFamily.GetFamilyConstellationsEvent.setFamilyNum

} // end GetFamilyConstellationsEvent
