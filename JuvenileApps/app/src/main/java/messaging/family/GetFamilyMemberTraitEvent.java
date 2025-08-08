/*
 * Project: JIMS2
 * Class:   messaging.juvenileFamily.GetFamilyMemberTraitEvent
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
public class GetFamilyMemberTraitEvent extends mojo.km.messaging.RequestEvent {

    // ------------------------------------------------------------------------
    // --- field                                                            ---
    // ------------------------------------------------------------------------

    private String familyMemberNum;
	private String familyMemberTraitNum;


    // ------------------------------------------------------------------------
    // --- constructor                                                      ---
    // ------------------------------------------------------------------------

    /**
     */
    public GetFamilyMemberTraitEvent() {
        super();
        // TODO Auto-generated constructor stub
    }//end of messaging.juvenileFamily.GetFamilyMemberTraitEvent.GetFamilyMemberTraitEvent


    // ------------------------------------------------------------------------
    // --- methods                                                          ---
    // ------------------------------------------------------------------------

    /**
     *  
     * @return  The family member num.
     */
    public String getFamilyMemberNum() {
        return familyMemberNum;
    }//end of messaging.juvenileFamily.GetFamilyMemberTraitEvent.getFamilyMemberNum

    /**
     *  
     * @param string The family member num.
     */
    public void setFamilyMemberNum(String string) {
        familyMemberNum = string;
    }//end of messaging.juvenileFamily.GetFamilyMemberTraitEvent.setFamilyMemberNum

	/**
	 * @return
	 */
	public String getFamilyMemberTraitNum()
	{
		return familyMemberTraitNum;
	}

	/**
	 * @param string
	 */
	public void setFamilyMemberTraitNum(String string)
	{
		familyMemberTraitNum = string;
	}

} // end GetFamilyMemberTraitEvent
