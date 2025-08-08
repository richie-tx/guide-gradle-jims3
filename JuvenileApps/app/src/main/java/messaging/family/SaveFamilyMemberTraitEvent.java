/*
 * Project: JIMS2
 * Class:   messaging.juvenileFamily.SaveFamilyMemberTraitEvent
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
public class SaveFamilyMemberTraitEvent  extends RequestEvent {

    // ------------------------------------------------------------------------
    // --- constructor                                                      ---
    // ------------------------------------------------------------------------
	private String 	traitid;
	private String 	traitTypeId;
	private String 	levelId;
	private String 	comments;
	private String 	statusId;

    /**
     */
    public SaveFamilyMemberTraitEvent() {
        super();
        // TODO Auto-generated constructor stub
    }//end of messaging.juvenileFamily.SaveFamilyMemberTraitEvent.SaveFamilyMemberTraitEvent


	/**
	 * @return
	 */
	public String getComments()
	{
		return comments;
	}

	/**
	 * @return
	 */
	public String getLevelId()
	{
		return levelId;
	}

	/**
	 * @return
	 */
	public String getStatusId()
	{
		return statusId;
	}

	/**
	 * @return
	 */
	public String getTraitid()
	{
		return traitid;
	}

	/**
	 * @return
	 */
	public String getTraitTypeId()
	{
		return traitTypeId;
	}

	/**
	 * @param string
	 */
	public void setComments(String string)
	{
		comments = string;
	}

	/**
	 * @param string
	 */
	public void setLevelId(String string)
	{
		levelId = string;
	}

	/**
	 * @param string
	 */
	public void setStatusId(String string)
	{
		statusId = string;
	}

	/**
	 * @param string
	 */
	public void setTraitid(String string)
	{
		traitid = string;
	}

	/**
	 * @param string
	 */
	public void setTraitTypeId(String string)
	{
		traitTypeId = string;
	}

} // end SaveFamilyMemberTraitEvent
