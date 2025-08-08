/*
 * Project: JIMS2
 * Class:   messaging.security.UpdateUserTypeAndDepartmentContraintsEvent
 * Version: 1.0.0
 *
 * Date:    2005-04-29
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package messaging.security;

import mojo.km.messaging.Composite.CompositeRequest;

/**
 * Class UpdateUserTypeAndDepartmentContraintsEvent.
 *  
 * @author  Anand Thorat
 * @version  1.0.0
 */
public class UpdateUserTypeAndDepartmentConstraintsEvent extends CompositeRequest
{
    // ------------------------------------------------------------------------
    // --- fields                                                           ---
    // ------------------------------------------------------------------------

    private String logonId;
	private String userTypeId;

    // ------------------------------------------------------------------------
    // --- constructor                                                      ---
    // ------------------------------------------------------------------------

    /**
     * @roseuid 42712E4A004E
     */
    public UpdateUserTypeAndDepartmentConstraintsEvent()
    {

    }//end of messaging.security.UpdateUserTypeAndDepartmentsContraintsEvent.UpdateUserTypeAndDepartmentsContraintsEvent


    // ------------------------------------------------------------------------
    // --- methods                                                          ---
    // ------------------------------------------------------------------------

    /**
     *  
     * @param logonId @roseuid 42712ADE0189
     */
    public void setLogonId(String logonId)
    {
		this.logonId = logonId;
    }//end of messaging.security.UpdateUserTypeAndDepartmentContraintsEvent.setLogonId

    /**
     *  
     * @return  java.lang.String
     * @roseuid 42712ADE018B
     */
    public String getLogonId()
    {
        return this.logonId ;
    }//end of messaging.security.UpdateUserTypeAndDepartmentContraintsEvent.getLogonId


	/**
	 * @return
	 */
	public String getUserTypeId()
	{
		return userTypeId;
	}

	/**
	 * @param string
	 */
	public void setUserTypeId(String string)
	{
		userTypeId = string;
	}

} // end UpdateUserTypeAndDepartmentContraintsEvent
