package mojo.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.InvalidCancelException;

import java.io.IOException;
import javax.servlet.ServletException;

/**
 * Overrides the default Struts request processing to include processing specific to the JIMS2
 * application.
 */
public class RequestProcessor extends org.apache.struts.action.RequestProcessor
{

    /**
     * Extends the default Struts validation processing. This method first invokes
     * super.processValidate then ensures that the proper moJo components are initialized.
     * 
     * @param aRequest
     *            is the http servlet request we are processing
     * @param aResponse
     *            is the http servlet response we are creating
     * @param aForm
     *            is the action form instance we are creating
     * @param aForm
     *            is the action mapping we are using
     * @exception IOException
     *                if an input/output error occurs
     * @exception ServletException
     *                if a servlet exception occurs
     */
    protected boolean processValidate(HttpServletRequest aRequest, HttpServletResponse aResonse, ActionForm aForm,
            ActionMapping aMapping) throws IOException, ServletException
    {
        // Call super first
        boolean continueProcess = false;
		try {
			continueProcess = super.processValidate(aRequest, aResonse, aForm, aMapping);
		} catch (InvalidCancelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        /*
         * until this does something, no reason to call it if (lContinue == true) { continueProcess =
         * processMojoInitialization(aRequest, aResonse, aForm, aMapping); }
         */

        return continueProcess;
    }

    /**
     * Initializes moJo for request processing.
     * 
     * @param aRequest
     *            is the http servlet request we are processing
     * @param aResponse
     *            is the http servlet response we are creating
     * @param aForm
     *            is the action form instance we are creating
     * @param aForm
     *            is the action mapping we are using
     * @exception IOException
     *                if an input/output error occurs
     * @exception ServletException
     *                if a servlet exception occurs
     */
    protected boolean processMojoInitialization(HttpServletRequest aRequest, HttpServletResponse aResonse, ActionForm aForm,
            ActionMapping aMapping) throws IOException, ServletException
    {

        return true;
    }
}
