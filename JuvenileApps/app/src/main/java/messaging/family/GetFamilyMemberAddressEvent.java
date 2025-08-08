/*
 * Project: JIMS2
 * Class:   messaging.juvenileFamily.GetFamilyMemberAddressEvent
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
public class GetFamilyMemberAddressEvent extends mojo.km.messaging.RequestEvent {

    // ------------------------------------------------------------------------
    // --- field                                                            ---
    // ------------------------------------------------------------------------

	private String memberNum;
    private String memberAddressId;
    private String constellationId;


    // ------------------------------------------------------------------------
    // --- constructor                                                      ---
    // ------------------------------------------------------------------------

    /**
     */
    public GetFamilyMemberAddressEvent() {
        super();
        // TODO Auto-generated constructor stub
    }//end of messaging.juvenileFamily.GetFamilyMemberAddressEvent.GetFamilyMemberAddressEvent


    // ------------------------------------------------------------------------
    // --- methods                                                          ---
    // ------------------------------------------------------------------------

    /**
     *  
     * @return  The member num.
     */
    public String getMemberNum() {
        return memberNum;
    }//end of messaging.juvenileFamily.GetFamilyMemberAddressEvent.getMemberNum

    /**
     *  
     * @param string The member num.
     */
    public void setMemberNum(String string) {
        memberNum = string;
    }//end of messaging.juvenileFamily.GetFamilyMemberAddressEvent.setMemberNum

	/**
	 * @return
	 */
	public String getMemberAddressId()
	{
		return memberAddressId;
	}

	/**
	 * @param string
	 */
	public void setMemberAddressId(String string)
	{
		memberAddressId = string;
	}

	/**
	 * @return
	 */
	public String getConstellationId()
	{
		return constellationId;
	}

	/**
	 * @param string
	 */
	public void setConstellationId(String string)
	{
		constellationId = string;
	}

} // end GetFamilyMemberAddressEvent
