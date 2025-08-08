/*
 * Project: JIMS2
 * Class:   messaging.security.GetDepartmentConstraintsForUserAdministrationEvent
 * Version: 1.0.0
 *
 * Date:    2005-05-03
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package messaging.security;


/**
 * Class GetDepartmentConstraintsForUserAdministrationEvent.
 *  
 * @author  Anand Thorat
 * @version  1.0.0
 */
public class GetDepartmentConstraintsForUserAdministrationEvent extends mojo.km.messaging.RequestEvent
{
    // ------------------------------------------------------------------------
    // --- field                                                            ---
    // ------------------------------------------------------------------------

    public String logonId;


    // ------------------------------------------------------------------------
    // --- constructor                                                      ---
    // ------------------------------------------------------------------------

    /**
     * @roseuid 42712F71008C
     */
    public GetDepartmentConstraintsForUserAdministrationEvent()
    {

    }//end of messaging.security.GetDepartmentConstraintsForUserAdministrationEvent.GetDivisionContraintsForUserAdminstrationEvent


    // ------------------------------------------------------------------------
    // --- methods                                                          ---
    // ------------------------------------------------------------------------

    /**
     *  
     * @param loginId @roseuid 42711B0D01FA
     */
    public void setLogonId(String logonId)
    {
        this.logonId = logonId;

    }//end of messaging.security.GetDepartmentConstraintsForUserAdministrationEvent.setlogonId

    /**
     *  
     * @return  java.lang.String
     * @roseuid 42711B0D01FC
     */
    public String getLogonId()
    {
        return logonId;
    }//end of messaging.security.GetDepartmentConstraintsForUserAdministrationEvent.getlogonId

} // end GetDepartmentConstraintsForUserAdministrationEvent
