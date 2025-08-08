/*
 * Project: JIMS2
 * Class:   messaging.security.UpdateDepartmentConstraintEvent
 * Version: 1.0.0
 *
 * Date:    2005-05-03
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package messaging.security;

/**
 *  
 * @author  athorat
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 * @version  1.0.0
 */
public class UpdateDepartmentConstraintEvent extends mojo.km.messaging.RequestEvent
{
    // ------------------------------------------------------------------------
    // --- field                                                            ---
    // ------------------------------------------------------------------------

    private String departmentId;


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
    }//end of messaging.security.UpdateDepartmentConstraintEvent.getDepartmentId

    /**
     *  
     * @param string The department id.
     */
    public void setDepartmentId(String string)
    {
        departmentId = string;
    }//end of messaging.security.UpdateDepartmentConstraintEvent.setDepartmentId

} // end UpdateDepartmentConstraintEvent
