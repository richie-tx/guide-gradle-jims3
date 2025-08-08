/*
 * Project: JIMS2
 * Class:   messaging.juvenileFamily.GetFamilyMemberDetailsEvent
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
public class GetFamilyMemberDetailsEvent extends mojo.km.messaging.RequestEvent {

    // ------------------------------------------------------------------------
    // --- fields                                                           ---
    // ------------------------------------------------------------------------

    public String familyNum;

    public String memberNum;


    // ------------------------------------------------------------------------
    // --- constructor                                                      ---
    // ------------------------------------------------------------------------

    /**
     */
    public GetFamilyMemberDetailsEvent() {
        super();
        // TODO Auto-generated constructor stub
    }//end of messaging.juvenileFamily.GetFamilyMemberDetailsEvent.GetFamilyMemberDetailsEvent


    // ------------------------------------------------------------------------
    // --- methods                                                          ---
    // ------------------------------------------------------------------------

    /**
     *  
     * @param constelltionNum @roseuid 432997A902AF
     */
    public void setFamilyNum(String familyNum) {
        this.familyNum = familyNum;

    }//end of messaging.juvenileFamily.GetFamilyMemberDetailsEvent.setFamilyNum

    /**
     *  
     * @return  java.lang.String
     * @roseuid 432997A902B1
     */
    public String getFamilyNum() {
        return familyNum;
    }//end of messaging.juvenileFamily.GetFamilyMemberDetailsEvent.getFamilyNum

    /**
     *  
     * @return  The member num.
     */
    public String getMemberNum() {
        return memberNum;
    }//end of messaging.juvenileFamily.GetFamilyMemberDetailsEvent.getMemberNum

    /**
     *  
     * @param string The member num.
     */
    public void setMemberNum(String string) {
        memberNum = string;
    }//end of messaging.juvenileFamily.GetFamilyMemberDetailsEvent.setMemberNum

} // end GetFamilyMemberDetailsEvent
