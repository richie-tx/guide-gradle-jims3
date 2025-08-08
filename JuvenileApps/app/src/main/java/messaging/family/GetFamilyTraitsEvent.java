/*
 * Project: JIMS2
 * Class:   messaging.juvenileFamily.GetFamilyTraitsEvent
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
 *  
 * @author  athorat
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 * @version  0.8.15 
 */
public class GetFamilyTraitsEvent extends mojo.km.messaging.RequestEvent {

    // ------------------------------------------------------------------------
    // --- field                                                            ---
    // ------------------------------------------------------------------------

    private String familyNum;


    // ------------------------------------------------------------------------
    // --- constructor                                                      ---
    // ------------------------------------------------------------------------

    /**
     */
    public GetFamilyTraitsEvent() {
        super();
        // TODO Auto-generated constructor stub
    }//end of messaging.juvenileFamily.GetFamilyTraitsEvent.GetFamilyTraitsEvent


    // ------------------------------------------------------------------------
    // --- methods                                                          ---
    // ------------------------------------------------------------------------

    /**
     *  
     * @return  The family num.
     */
    public String getFamilyNum() {
        return familyNum;
    }//end of messaging.juvenileFamily.GetFamilyTraitsEvent.getFamilyNum

    /**
     *  
     * @param string The family num.
     */
    public void setFamilyNum(String string) {
        familyNum = string;
    }//end of messaging.juvenileFamily.GetFamilyTraitsEvent.setFamilyNum

} // end GetFamilyTraitsEvent
