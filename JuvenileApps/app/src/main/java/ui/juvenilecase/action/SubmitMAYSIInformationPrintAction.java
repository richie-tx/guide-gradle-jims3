package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.SearchJuvenileCasefilesEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.ActivityConstants;
import naming.JuvenileCaseControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.MAYSIInformationReportingBean;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileProfileForm;
import ui.juvenilecase.form.MentalHealthForm;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

/**
 * @author dgibler
 *  
 */
public class SubmitMAYSIInformationPrintAction extends JIMSBaseAction
{

    /**
     * @roseuid 42791FD100BB
     */
    public SubmitMAYSIInformationPrintAction()
    {

    }

    protected void addButtonMapping(Map keyMap)
    {
        keyMap.put("button.print", "print");
        keyMap.put("button.maysiList", "resultList");
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42791D5702D0
     */
    public ActionForward printUJAC(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        MentalHealthForm mhForm = (MentalHealthForm) aForm;
        JuvenileProfileForm headerForm = UIJuvenileHelper.getHeaderForm(aRequest);
        MAYSIInformationReportingBean reportInfo = new MAYSIInformationReportingBean();

        reportInfo.setJuvenileNum(mhForm.getJuvenileNum());
        reportInfo.setPlacementType(mhForm.getFacilityType());
        reportInfo.setPreviousMAYSI(mhForm.isHasPreviousMAYSI() ? "YES" : "NO");
        reportInfo.setLength(mhForm.getLengthOfStay());
        reportInfo.setLocation(mhForm.getLocationUnit());
        reportInfo.setReferralNum(mhForm.getReferralNum());
        reportInfo.setGender(mhForm.getSex());
        
        //U.S 88526
        if ((mhForm.getHispanic()!=null && mhForm.getHispanic().equalsIgnoreCase("Y")) && (mhForm.getRace().equalsIgnoreCase("WHITE"))) {
        		reportInfo.setRace("HISPANIC");
        } else {
        	reportInfo.setRace(mhForm.getRace());
        }

        if (headerForm != null)
        {
            reportInfo.setCurrentAge(headerForm.getAge());

        }
        else
        {
            reportInfo.setCurrentAge("UNKNOWN");
        }
        CompositeResponse myResp = this.sendPrintRequest("REPORTING::MAYSI_REQUEST_INFO", reportInfo, null);
        try
        {
            this.setPrintContentResp(aResponse, myResp, "maysiInfo.pdf", UIConstants.PRINT_AS_PDF_DOC);
        }
        catch (Exception e)
        {
            sendToErrorPage(aRequest, "error.generic",
                    "A problem with generating the MAYSI print document has been encountered");
        }
        return null;
    }
    
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42791D5702D0
     */
    public ActionForward print(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        MentalHealthForm mhForm = (MentalHealthForm) aForm;
        JuvenileProfileForm headerForm = UIJuvenileHelper.getHeaderForm(aRequest);
        MAYSIInformationReportingBean reportInfo = new MAYSIInformationReportingBean();
        
        reportInfo.setJuvenileNum(mhForm.getJuvenileNum());
        reportInfo.setPlacementType(mhForm.getFacilityType());
        reportInfo.setPreviousMAYSI(mhForm.isHasPreviousMAYSI() ? "YES" : "NO");
        reportInfo.setLength(mhForm.getLengthOfStay());
        reportInfo.setLocation(mhForm.getLocationUnit());
        reportInfo.setReferralNum(mhForm.getReferralNum());
        reportInfo.setGender(mhForm.getSex());
        
        if ((mhForm.getHispanic()!=null && mhForm.getHispanic().equalsIgnoreCase("Y")) && (mhForm.getRace().equalsIgnoreCase("WHITE"))) {
        		reportInfo.setRace("HISPANIC");
        } else {
        	reportInfo.setRace(mhForm.getRace());
        }

        if (headerForm != null)
        {
            reportInfo.setCurrentAge(headerForm.getAge());
        }
        else
        {
            reportInfo.setCurrentAge("UNKNOWN");
        }
        
        aRequest.getSession().setAttribute("reportInfo", reportInfo);
	BFOPdfManager pdfManager = BFOPdfManager.getInstance();
	pdfManager.createPDFReport(aRequest, aResponse, PDFReport.MAYSI_REQUEST_INFO);
	
	// US:156675 Create MAYSI Print record in activity table 
	SearchJuvenileCasefilesEvent search = (SearchJuvenileCasefilesEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.SEARCHJUVENILECASEFILES);
	search.setSearchType("JNUM");
	search.setJuvenileNum(mhForm.getJuvenileNum());
	CompositeResponse responses = MessageUtil.postRequest(search);
	List casefiles = MessageUtil.compositeToList(responses, JuvenileCasefileSearchResponseEvent.class);

	String caseFileId = null;
	List activeCasefiles = new ArrayList();
	List closedCasefiles = new ArrayList();
	if (casefiles.size() > 0)
	{
	    Collections.sort(casefiles); //this sorted based on create date

	    Iterator<JuvenileCasefileSearchResponseEvent> caseFileRefItr = casefiles.iterator();
	    while (caseFileRefItr.hasNext())
	    {
		JuvenileCasefileSearchResponseEvent caseFileReferral = caseFileRefItr.next();
		if (caseFileReferral.getCaseStatus().equals("ACTIVE"))
		{
		    activeCasefiles.add(caseFileReferral);
		}
		else
		    if (caseFileReferral.getCaseStatus().equals("CLOSED"))
		    {
			closedCasefiles.add(caseFileReferral);
		    }
	    }
	    if (activeCasefiles.size() > 0)
	    {
		Collections.sort(activeCasefiles);
		Iterator<JuvenileCasefileSearchResponseEvent> activeCaseFileItr = activeCasefiles.iterator();
		while (activeCaseFileItr.hasNext())
		{
		    JuvenileCasefileSearchResponseEvent activecaseFile = activeCaseFileItr.next();
		    caseFileId = activecaseFile.getSupervisionNum();
		    UIJuvenileHelper.createActivity(caseFileId, ActivityConstants.MAYSI_IDENTIFICATION_SHEET_PRINTED, "");
		    break;
		}
	    }
	    else
		if (closedCasefiles.size() > 0)
		{
		    Collections.sort(closedCasefiles);
		    Iterator<JuvenileCasefileSearchResponseEvent> closeddCaseFileItr = closedCasefiles.iterator();
		    while (closeddCaseFileItr.hasNext())
		    {
			JuvenileCasefileSearchResponseEvent closedcaseFile = closeddCaseFileItr.next();
			caseFileId = closedcaseFile.getSupervisionNum();
			UIJuvenileHelper.createActivity(caseFileId, ActivityConstants.MAYSI_IDENTIFICATION_SHEET_PRINTED, "");
			break;
		    }
		}
	}
	return null;
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42791D56036C
     */
    public ActionForward resultList(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {

        DisplayMAYSIResultsAction myAction = new DisplayMAYSIResultsAction();
        return myAction.execute(aMapping, aForm, aRequest, aResponse);

    }
}
