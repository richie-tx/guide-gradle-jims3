package mojo.struts;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.messaging.reporting.ReportRequestEvent;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.utilities.MessageUtil;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.LookupDispatchAction;
import org.apache.struts.config.FormBeanConfig;

import mojo.km.exceptionhandling.GeneralFeedbackMessageException;

public abstract class AbstractAction extends LookupDispatchAction
{
    private static final String BACK = "back";

    private static final String CANCEL = "cancel";

    private static final String CANCEL_MAIN_PAGE_HOME = "cancelMainPageHome";

    private static final String GENERIC_ERROR_MSG_KEY = "error.generic";

    private static final int PRINT_AS_PDF_DOC = 2;

    private static final int PRINT_AS_WORD_DOC = 1;

    /**
     * This method is the method that is used to add or overide existing the map in the normal key Method Map. the
     * purpose of this method was to ensure that all actions had provisions for back, cancel, and mainPage
     * functionality. Also, allowing us the ability to add a single entry in the map at this base level which all
     * actions would then have the ability to use.
     * 
     * @param keyMap
     */
    protected abstract void addButtonMapping(Map keyMap);

    /**
     * Default Back method implementation
     * 
     * @param aMapping -
     *            the struts mapping
     * @param aForm --
     *            the struts form
     * @param aRequest --
     *            the request object
     * @param aResponse --
     *            the response object
     * @return -- an action forward for "BACK"
     */
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
        return aMapping.findForward(BACK);
    }

    /**
     * Default Cancel method implementation
     * 
     * @param aMapping -
     *            the struts mapping
     * @param aForm --
     *            the struts form
     * @param aRequest --
     *            the request object
     * @param aResponse --
     *            the response object
     * @return -- an action forward for "Cancel"
     */
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(CANCEL);
    }

    /**
     * This method is the basic method required for a lookupDispatchAction in order to function, it is implemented in
     * this class because that allows for a defautl set up button mapping support functions to be supported and it is
     * expected that all extending concrete classes utilize the "addButtonMapping" method to add additional mappings.
     */
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();
        keyMap.put("button.back", "back");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.mainPage", "mainPage");
        addButtonMapping(keyMap);
        return keyMap;
    }

    /**
     * Trieds to get the specified form from the header and if it can't may create a new instance of the form and place
     * this in the session.
     * 
     * @param aRequest -
     *            the request
     * @param aFormName --
     *            the name of the form key
     * @param aClass --
     *            the fully qualified java class path of the form
     * @param isCreate --
     *            if true, creates the form if it is not found, if false doesn't
     * @return
     */
    private ActionForm getOurForm(HttpServletRequest aRequest, String aFormName, Class aClass, boolean isCreate) throws Exception
    {
        ActionForm myForm = getOurForm(aFormName, aRequest);
        if (myForm == null)
        {
            HttpSession session = aRequest.getSession();
            myForm = (ActionForm) aClass.newInstance();
            session.setAttribute(aFormName, myForm);
        }
        return myForm;
    }

    /**
     * Tries to get the specified form from the header.
     * 
     * @return HttpServletRequest
     */
    private ActionForm getOurForm(String aFormName, HttpServletRequest aRequest)
    {
        HttpSession session = aRequest.getSession();
        Object aObj = session.getAttribute(aFormName);
        if (aObj != null)
            return (ActionForm) aObj;
        return null;
    }

    /**
     * This method is used to get other forms from the session. The forms are accessed by the form name key which is
     * verified against the struts mapping file. The user can specify whether they want the form created or not if it is
     * not found
     * 
     * @param aMapping --
     *            the action mappings from which the struts mappings will be used to see if the form key is valid
     * @param aRequest -
     *            the request
     * @param aFormName --
     *            the name of the form key as defined in struts Config
     * @param aCreateIfNeeded --
     *            if true, creates the form if it is not found, if false doesn't
     * @return
     */
    protected ActionForm getSessionForm(ActionMapping aMapping, HttpServletRequest aRequest, String aFormName,
            boolean aCreateIfNeeded) throws GeneralFeedbackMessageException
    {
        Object myObj = aMapping.getModuleConfig().findFormBeanConfig(aFormName);
        if (myObj != null && myObj instanceof FormBeanConfig)
        { // couldn't find the object
            FormBeanConfig myFormConfig = (FormBeanConfig) myObj;
            String myFormName = myFormConfig.getName();
            String myFormClassName = myFormConfig.getType();
            try
            {
                Class myClass = Class.forName(myFormClassName);
                return getOurForm(aRequest, myFormName, myClass, aCreateIfNeeded);
            }
            catch (Exception e)
            {
                throw new GeneralFeedbackMessageException("The requested  form name: " + aFormName
                        + " could not be found while validating against the struts-config file. " + e.getMessage());
            }

        }
        throw new GeneralFeedbackMessageException("The requested  form name: " + aFormName
                + " could not be found while validating against the struts-config file. ");
    }

    /**
     * Method designed to handle exceptions that could be thrown by the PD resulting in non critical failures that can
     * be displayed back to the user and potentially remedied-- i.e. duplicate value exists, etc....
     * 
     * @param aRequest
     * @param aExc
     */
    protected void handleNonFatalUnexpectedException(HttpServletRequest aRequest, Exception aExc)
    {
        sendToErrorPage(aRequest, GENERIC_ERROR_MSG_KEY, aExc.getMessage());
    }

    /**
     * Default Main Page method implementation idea is to take the user back to a standard main page
     * 
     * @param aMapping -
     *            the struts mapping
     * @param aForm --
     *            the struts form
     * @param aRequest --
     *            the request object
     * @param aResponse --
     *            the response object
     * @return -- an action forward for "MainPage"
     */
    public ActionForward mainPage(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(CANCEL_MAIN_PAGE_HOME);
    }

    /**
     * Used primarily to set the file name for printing pdf documents
     * 
     * @param response
     * @param fileName
     */
    private void printPDF(HttpServletResponse response, String fileName)
    {
        response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".pdf");
    }

    /**
     * Used primarily to sett the file name for printing word documents
     * 
     * @param response
     * @param fileName
     */
    private void printWord(HttpServletResponse response, String fileName)
    {
        response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".jims");
    }

    /**
     * This method is designed to be a convenience method to help send print requests. It basically takes in the report
     * context key ex: REPORTING::pd.juvenile.reporting.wordxml.MSWordReporting::RESIDENTIAL_EXIT_PLAN and one of two
     * data object beans and send the request to the back end framework. Either data object is optional but one is
     * required.
     * 
     * @param aReportNameContextKey --
     *            the report context key as defined in the XML file
     * @param aFirstDataObjectBean --
     *            the data object bean for the report
     * @param aSecondDataObjectBean --
     *            the additional data object bean for the report
     * @return
     */
    protected CompositeResponse sendPrintRequest(String aReportNameContextKey, Object aFirstDataObjectBean,
            Object aSecondDataObjectBean)
    {
        ReportRequestEvent repRequestEvent = new ReportRequestEvent();
        if (aFirstDataObjectBean != null)
            repRequestEvent.addDataObject(aFirstDataObjectBean);
        if (aSecondDataObjectBean != null)
            repRequestEvent.addDataObject(aSecondDataObjectBean);
        repRequestEvent.setReportName(aReportNameContextKey);
        
        CompositeResponse resp = MessageUtil.postRequest(repRequestEvent);

        return null;
    }

    /**
     * Method that adds a message to the request for sending back to the user takes no parameters, message key's message
     * is diplayed as is to the user
     * 
     * @param aRequest
     * @param msgKey --
     *            error message key to add
     */
    protected void sendToErrorPage(HttpServletRequest aRequest, String msgKey)
    {
        ActionErrors errors = new ActionErrors();
        errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msgKey));
        saveErrors(aRequest, errors);
    }

    /**
     * Method that adds a message to the request for sending back to the user takes 1 parameter, message key's message
     * is diplayed as is to the user after substitution of the parameter values
     * 
     * @param aRequest --
     *            a Request
     * @param msgKey --
     *            error message key to add
     * @param param --
     *            a single parameter value contained in the message key
     */
    protected void sendToErrorPage(HttpServletRequest aRequest, String msgKey, String param)
    {
        ActionErrors errors = new ActionErrors();
        errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msgKey, param));
        saveErrors(aRequest, errors);
    }

    /**
     * Method that adds a message to the request for sending back to the user takes 2 parameter, message key's message
     * is diplayed as is to the user after substitution of the parameter values
     * 
     * @param aRequest --
     *            a Request
     * @param msgKey --
     *            error message key to add
     * @param param1 --
     *            the first parameter value contained in the message key
     * @param param2 --
     *            the second parameter value contained in the message key
     */
    protected void sendToErrorPage(HttpServletRequest aRequest, String msgKey, String param1, String param2)
    {
        ActionErrors errors = new ActionErrors();
        errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msgKey, param1, param2));
        saveErrors(aRequest, errors);
    }

    /**
     * This method is used to return the appropriate content type back to the user of a web based system
     * 
     * @param response --
     *            The HTTP Servlet Response object
     * @param aCompRespEvt --
     *            the Retport Response Event Object is expected to be within this evt containing the
     * @param aPrintType --
     *            a Consatnt from UIConstants such as UIConstants.PRINT_AS_PDF_DOC
     * @throws GeneralFeedbackMessageException --
     *             thrown in the event any kind of exception occurs -- required of a caller to handle this
     */
    protected void setPrintContentResp(HttpServletResponse response, CompositeResponse aCompRespEvt, String aFileName,
            int aPrintType) throws GeneralFeedbackMessageException
    {

        try
        {
            ReportResponseEvent aReportRespEvt = (ReportResponseEvent) MessageUtil.filterComposite(aCompRespEvt,
                    ReportResponseEvent.class);
            if (aReportRespEvt == null)
            {
                ReturnException returnException = (ReturnException) MessageUtil.filterComposite(aCompRespEvt,
                        ReturnException.class);
                if (returnException != null)
                {
                    throw returnException;
                }
                else
                {
                    throw new GeneralFeedbackMessageException("No Report Response Event Found: No report generated");
                }
            }
            response.setContentType("application/x-file-download");
            
            switch (aPrintType)
            {            
            case PRINT_AS_WORD_DOC:
                printWord(response, aFileName);
                break;
            case PRINT_AS_PDF_DOC:
                printPDF(response, aFileName);
                break;
            default:
                printWord(response, aFileName);
                break;
            }
            
            response.setHeader("Cache-Control", "max-age=" + 1200);
            response.setContentLength(aReportRespEvt.getContent().length);
            response.resetBuffer();
            OutputStream os = response.getOutputStream();
            os.write(aReportRespEvt.getContent(), 0, aReportRespEvt.getContent().length);
            os.flush();
            os.close();
            return;
        }
        catch (Exception e)
        {
            throw new GeneralFeedbackMessageException(e.getMessage());
        }
    }

    /**
     * This method is the default method called when the parameter value needed in the action mapping is not found.
     */
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request,
            javax.servlet.http.HttpServletResponse response) throws Exception
    {
        String msgAddOn = "";
        String errorMsg = "The parameter has not been specified in the action path or there is no method to handle the parameter. i.e there is no submitAction parameter defined and thus the Action does not know where to go. PATH:";
        if (request.getParameter("submitAction") != null)
        {
            String myVal = (String) request.getParameter("submitAction");
            if (myVal == null || myVal.equals(""))
            {
                errorMsg = "The parameter submitAction has not been specified for the PATH: ";
            }
            else
            {
                errorMsg = "The parameter submitAction has been specified, and most likely the method that is mapped to this parameter is not in the action class for the PATH: ";
            }
            msgAddOn = "SUBMITACTION: " + ((String) request.getParameter("submitAction"));
        }
        throw new Exception(errorMsg + " " + mapping.getPath() + " " + msgAddOn);
    }

}