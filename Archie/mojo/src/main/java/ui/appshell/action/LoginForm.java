/*
 * Created on Apr 20, 2004
 *
 */
package ui.appshell.action;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.validator.ValidatorForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author Dhanashree
 */
public class LoginForm extends ValidatorForm
{
    private String userID;

    private String password;

    public String getUserID()
    {
        return userID;
    }

    public void setUserID(String uid)
    {
        userID = uid;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String pwd)
    {
        password = pwd;
    }

    public void reset(ActionMapping mapping, HttpServletRequest req)
    {
        userID = null;
        password = null;
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest req)
    {
        return null;
    }

}
