/*
 * Project: JIMS2
 * Class:   messaging.security.reply.DepartmentConstraintsForUserAdministrationResponseEvent
 * Version: 1.0.0
 *
 * Date:    2005-05-03
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package messaging.security.reply;

/**
 *  
 * @author  athorat
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 * @version  1.0.0
 */
public class DepartmentConstraintsForUserAdministrationResponseEvent extends mojo.km.messaging.ResponseEvent
{
    // ------------------------------------------------------------------------
    // --- field                                                            ---
    // ------------------------------------------------------------------------

    private String departmentId;
    private String departmentName;


    // ------------------------------------------------------------------------
    // --- methods                                                          ---
    // ------------------------------------------------------------------------

    /**
     *  
     * @return  The department id.
     */
    public String getDepartmentId()
    {
        return departmentId;
    }//end of messaging.security.reply.DepartmentConstraintsForUserAdministrationResponseEvent.getDepartmentId

    /**
     *  
     * @param string The department id.
     */
    public void setDepartmentId(String string)
    {
        departmentId = string;
    }//end of messaging.security.reply.DepartmentConstraintsForUserAdministrationResponseEvent.setDepartmentId

	/**
	 * @return Returns the departmentName.
	 */
	public String getDepartmentName() {
		return departmentName;
	}
	/**
	 * @param departmentName The departmentName to set.
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
} // end DepartmentConstraintsForUserAdministrationResponseEvent
