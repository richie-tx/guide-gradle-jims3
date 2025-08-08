/*
 * Created on May 4, 2004
 *
 */
package ui.appshell.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import mojo.struts.AbstractAction;

/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 * Comments
 */
public class DisplayNavigationHeaderAction extends AbstractAction
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        return mapping.findForward("displayNavigationHeader");
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.struts.AbstractAction#addButtonMapping(java.util.Map)
     */
    protected void addButtonMapping(Map keyMap)
    {
        // TODO Auto-generated method stub

    }

}
