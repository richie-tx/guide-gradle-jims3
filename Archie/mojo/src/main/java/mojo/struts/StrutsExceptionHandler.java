package mojo.struts;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

/**
 * @author jfisher
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class StrutsExceptionHandler extends ExceptionHandler {

	/**
	 * @see org.apache.struts.action.ExceptionHandler#execute(Exception, ExceptionConfig, ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)
	 */
	public ActionForward execute(
		Exception anException,
		ExceptionConfig anExceptionConfig,
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
					throws ServletException {

		
		// where should this constant go?
		// naming.ManageUtilityServiceNames??
		// ApplicationResources.properties
		
		aRequest.setAttribute("exceptionattribute",anException);
		
		ActionForward lForward = aMapping.findForward("handleException");
		
		return lForward;
	}

}
