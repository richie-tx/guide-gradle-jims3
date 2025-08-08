

package ui.supervision.administerassessments.action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.report.AssessmentsPrintTemplateRequestEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.reporting.ReportRequestEvent;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.CSCQuestion;
import ui.common.CSCQuestionGroup;
import ui.common.UIUtil;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.administerassessments.form.ForceFieldAssessmentForm;
import ui.supervision.administersupervisionplan.ForceFieldAnalysisQuestionBean;
import ui.supervision.administersupervisionplan.ForceFieldAnalysisReportingBean;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

/**
 * @author rcapestani
 *  
 */
public class SubmitForceFieldAnalysisPrintAction extends JIMSBaseAction
{
	    public static final String FILEEXT = ".pdf";
	    public static final String CONTEXTKEYPREFIX = "REPORTING::";

	    /**
	     * 
	     */
	    public SubmitForceFieldAnalysisPrintAction()
	    {

	    }

	    /**
	     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	     * @return Map
	     */
	    /* (non-Javadoc)
		 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
		 */
		protected void addButtonMapping(Map keyMap)
		{
			keyMap.put("button.print", "printOrder");
	        keyMap.put("button.printDraft", "printDraftOrder");
	        keyMap.put("button.printSpanish", "printSpanishOrder");
	        keyMap.put("button.printSpanishVersion", "printSpanishVersion");
	        keyMap.put("button.back", "back");
	        keyMap.put("button.cancel", "cancel");
		}

	    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
	            HttpServletResponse aResponse)
	    {
	        return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK, UIUtil
	                .getCurrentUserAgencyID()));
	    }

	    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
	            HttpServletResponse aResponse)
	    {
	        ActionForward forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(
	                UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
	        return forward;
	    }

	    public ActionForward printOrder(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
	            HttpServletResponse aResponse) throws Exception
	    {
	        this.processReport(aMapping, aForm, aRequest, aResponse);
	        return null;
	    }

	    public ActionForward printDraftOrder(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
	            HttpServletResponse aResponse) throws Exception
	    {
	        processReport(aMapping, aForm, aRequest, aResponse);
	        return (null);
	    }

	    public ActionForward printSpanishOrder(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
	            HttpServletResponse aResponse) throws Exception
	    {
	        processReport(aMapping, aForm, aRequest, aResponse);
	        return (null);
	    }

	    public ActionForward printSpanishVersion(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
	            HttpServletResponse aResponse) throws Exception
	    {
	        processReport(aMapping, aForm, aRequest, aResponse);
	        return (null);
	    }

	    private String processReport(ActionMapping aMapping, ActionForm form, HttpServletRequest aRequest, HttpServletResponse response)
	            throws Exception
	    {
//			to obtain the Force Field Analysis Details	    	
	        ForceFieldAssessmentForm forceFieldForm = (ForceFieldAssessmentForm) form;
//	        to obtain the Supervisee Details
	        SuperviseeHeaderForm superviseeHeaderForm=(SuperviseeHeaderForm)this.getSessionForm(aMapping,aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);
	        ForceFieldAnalysisReportingBean reportBean = new ForceFieldAnalysisReportingBean();
	        reportBean.setAssessementDate(forceFieldForm.getAssessmentDateStr());
	        reportBean.setSpn(superviseeHeaderForm.getSuperviseeId());
	        String name = formatName(superviseeHeaderForm.getSuperviseeNameDesc());
			reportBean.setDefendantName(name);
			List questionList = null;
			questionList = new ArrayList();
			Collection forceField = forceFieldForm.getForceFieldQuestionsList();
			Iterator iter = forceField.iterator();
			while (iter.hasNext()) 
	        {
				CSCQuestionGroup group = (CSCQuestionGroup) iter.next();
				
				ForceFieldAnalysisQuestionBean question = new ForceFieldAnalysisQuestionBean();
				
				question.setTopic( group.getGroupText());
				
				List quesList = (List) group.getQuestions();
				
				for ( int i =0; i < quesList.size(); i ++ ){
					
					CSCQuestion quest = (CSCQuestion) quesList.get(i);
					if(quest.getQuestionText().equals("Strength/Resource"))
					{
						question.setStrength(quest.getResponseText());
					}
					if(quest.getQuestionText().equals("Problem/Weakness"))
					{
						question.setProblem(quest.getResponseText());
					}
					if(quest.getQuestionText().equals("Rank"))
					{
						question.setRank(quest.getResponseText());
					}
				}
				questionList.add(question);
	        }
			reportBean.setForceFieldsList( questionList );
	        ReportRequestEvent assessmentsReportEvent = new AssessmentsPrintTemplateRequestEvent();
	        assessmentsReportEvent.setReportName( new StringBuffer( CONTEXTKEYPREFIX )
            .append("FORCE FIELD ANALYSIS").toString());
	        assessmentsReportEvent.addDataObject(reportBean);
	        
	        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	        dispatch.postEvent(assessmentsReportEvent);
	        
	        CompositeResponse resp = (CompositeResponse) dispatch.getReply();
	        MessageUtil.processReturnException(resp);
	        
	        ReportResponseEvent aRespEvent = (ReportResponseEvent) MessageUtil.filterComposite(resp, ReportResponseEvent.class);
	        response.setContentType("application/x-file-download");	        response.setHeader("Content-disposition", "attachment; filename=" 
	        					+ aRespEvent.getFileName().substring(aRespEvent.getFileName().lastIndexOf("/") + 1) + FILEEXT);
	        response.setHeader("Cache-Control", "must-revalidate");
	        response.setContentLength(aRespEvent.getContent().length);
	        response.resetBuffer();
	        OutputStream os = response.getOutputStream();
	        os.write(aRespEvent.getContent(), 0, aRespEvent.getContent().length);
	        os.flush();
	        os.close();
	        return null;
	    }
	    
	    private String formatName(String name)
	    {
	        //Need to do this since the name comes from Criminal case as ENGLAND, STEPHAN ERIK 
	        //Its not separately stored in the table
	        StringBuffer sb = new StringBuffer(name.substring(name.indexOf(',')+1));
	        sb.append(" ").append(name.substring(0,name.indexOf(',')));
	        return sb.toString();
	    }
	    
}
