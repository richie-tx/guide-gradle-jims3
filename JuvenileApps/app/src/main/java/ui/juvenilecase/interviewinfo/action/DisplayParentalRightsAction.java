package ui.juvenilecase.interviewinfo.action;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.error.reply.ErrorResponseEvent;
import messaging.interviewinfo.CreateHireAttorneyReportEvent;
import messaging.interviewinfo.to.HireAttorneyReportDataTO;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.reporting.IReport;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ActivityConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import pd.juvenilecase.interviewinfo.InterviewDocument;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.report.transactions.PDFReporting;

import ui.action.JIMSBaseAction;
import ui.common.Name;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.UIJuvenileInterviewInfoHelper;
import ui.juvenilecase.interviewinfo.form.InterviewGuardian;
import ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm;
import ui.security.SecurityUIHelper;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayParentalRightsAction extends JIMSBaseAction
{
	private final String contactAdmin = "Please contact your System Administrator with a description of this problem." ;
	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.generateParentalRightsForm", "generateParentalRightsForm");
		keyMap.put("button.parentalRights", "parentalRights");
		keyMap.put("button.next", "next");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
	}
	
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)	
	{
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}
		
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, 
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}
	
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, 
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileInterviewForm form = (JuvenileInterviewForm) aForm;
		String comments = form.getVictimsPhysicalInjuries();
		if (!comments.equals("") && comments != null) {
			IUserInfo user = SecurityUIHelper.getUser();
			Name userName = new Name(user.getFirstName(),"",user.getLastName());
			form.setVictimsPhysicalInjuries(comments + " [" + DateUtil.getCurrentDateString(UIConstants.DATETIME24_FMT_1) + " - " + userName.getFormattedName() + "]");
		}		
		aRequest.setAttribute("state", "summary");
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	public ActionForward parentalRights(ActionMapping aMapping, ActionForm aForm, 
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		
		JuvenileInterviewForm form = (JuvenileInterviewForm) aForm;
		form.setGuardians(
			UIJuvenileInterviewInfoHelper.getJuvenileGuardians(form.getJuvenileNum()));
		
		return aMapping.findForward("success");
	}	
	
	public ActionForward generateParentalRightsForm(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
	{
		JuvenileInterviewForm form = (JuvenileInterviewForm)aForm;
		
		CreateHireAttorneyReportEvent reqEvent = new CreateHireAttorneyReportEvent();
		reqEvent.setCasefileId( form.getCasefileId() );
		reqEvent.setJuvenileNum( form.getJuvenileNum() );
		reqEvent.setVictimsPhysicalInjuries( form.getVictimsPhysicalInjuries() );
		
		for(Iterator iter = form.getGuardians().iterator(); iter.hasNext();)
		{
			InterviewGuardian guardian = (InterviewGuardian) iter.next();
			if(guardian.isSelected())
			{
				reqEvent.addGuardianInfo(
						guardian.getFamilyConstellationMemberNum(), 
						guardian.getExplanationMethod());
			}
		}		
		// Adding record in activity table
		UIJuvenileHelper.createActivity(form.getCasefileId(), ActivityConstants.PARENTAL_RIGHTS_WORKSHEET_COMPLETED, "");		
		
	   // MessageUtil.postRequest(reqEvent);
		HireAttorneyReportDataTO reportTO = InterviewHelper.buildHireAttorneyReportData( reqEvent );
		
		aRequest.getSession().setAttribute("reportInfo", reportTO);
		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		//let the pdfManager know that the report should be saved in the request during report creation
		aRequest.setAttribute("isPdfSaveNeeded", "true");
		pdfManager.createPDFReport(aRequest, aResponse, PDFReport.HIRE_ATTORNEY_REPORT);        
        //no need to forward since response has already been committed.
		
		reqEvent.addDataObject(reportTO);
		byte[] pdfDocument = (byte[]) aRequest.getAttribute("pdfSavedReport");
		if( pdfDocument == null || pdfDocument.length < 1 )
		{
			sendToErrorPage( aRequest, "error.generic", "Problems generating report. " + contactAdmin ) ;
			return aMapping.findForward(UIConstants.FAILURE);
		}
		IReport report  = PDFReporting.getInstance();
		IDispatch dispatch 	= EventManager.getSharedInstance(EventManager.REPLY);
		ReportResponseEvent respEvent = new ReportResponseEvent();
		respEvent.setContent(pdfDocument);
		respEvent.setContentType(report.getContentType());
		respEvent.setFileName(report.getTemplate());
		
		InterviewDocument document = new InterviewDocument();
		document.setDocument( respEvent.getContent() );
		document.setCasefileId( reqEvent.getCasefileId() );
		document.setDocumentTypeCodeId( "RPW" );	// CODE_TABLE_NAME = INTERVIEW_DOCTYPE 
		
		dispatch.postEvent(respEvent);
		return null;
	}
	
	
	public ActionForward generateParentalRightsUJACForm(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileInterviewForm form = (JuvenileInterviewForm)aForm;
		
		CreateHireAttorneyReportEvent reqEvent = new CreateHireAttorneyReportEvent();
		reqEvent.setCasefileId( form.getCasefileId() );
		reqEvent.setJuvenileNum( form.getJuvenileNum() );
		reqEvent.setVictimsPhysicalInjuries( form.getVictimsPhysicalInjuries() );
		
		for(Iterator iter = form.getGuardians().iterator(); iter.hasNext();)
		{
			InterviewGuardian guardian = (InterviewGuardian) iter.next();
			if(guardian.isSelected())
			{
				reqEvent.addGuardianInfo(
						guardian.getFamilyConstellationMemberNum(), 
						guardian.getExplanationMethod());
			}
		}
		
		// Adding record in activity table
		UIJuvenileHelper.createActivity(form.getCasefileId(), ActivityConstants.PARENTAL_RIGHTS_WORKSHEET_COMPLETED, "");		
		
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        MessageUtil.postRequest(reqEvent);
        
        CompositeResponse compResponse = (CompositeResponse) dispatch.getReply();
        
        ErrorResponseEvent errorRE = 
			(ErrorResponseEvent)MessageUtil.filterComposite(
					(CompositeResponse)dispatch.getReply(), ErrorResponseEvent.class );
		if(errorRE != null)
		{
			ActionErrors errors = new ActionErrors();
   			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorRE.getMessage()));
   			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		}
		
        ReportResponseEvent aRespEvent = (ReportResponseEvent) MessageUtil.filterComposite(compResponse, ReportResponseEvent.class);

        try{
            aResponse.setContentType("application/x-file-download");
            aResponse.setHeader("Content-disposition", "attachment; filename=" + aRespEvent.getFileName().substring(aRespEvent.getFileName().lastIndexOf("/")+1) + ".pdf");
            aResponse.setHeader("Cache-Control", "max-age=" + 1200);
            aResponse.setContentLength(aRespEvent.getContent().length);
            aResponse.resetBuffer();
			OutputStream os = aResponse.getOutputStream();
			os.write(aRespEvent.getContent(), 0, aRespEvent.getContent().length);
			os.flush();
			os.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        //no need to forward since response has already been committed.
		return null;
	}
	
	
	
}