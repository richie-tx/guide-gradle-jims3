package ui.juvenilecase.prodsupport.action.query;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.reply.CasefileDocumentsResponseEvent;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.juvenilecase.casefile.PDCasefileDocumentsHelper;
import pd.juvenilewarrant.JJSPetition;

import ui.juvenilecase.form.ProdSupportForm;

public class MoveCommonAppReportAction extends Action
{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

	    ProdSupportForm regform = (ProdSupportForm) form;
	    String clrChk = request.getParameter("clr");
	    regform.setMsg("");
	    if (clrChk!=null && clrChk.equalsIgnoreCase("Y")) {
		regform.clearAllResults();
		regform.setMsg("");
		return mapping.findForward("error");		
	    }
	    
	    String cassefileId = regform.getCasefileId();
	    if ( cassefileId != null
		    && cassefileId.length() > 0 ) {
		List<CasefileDocumentsResponseEvent> documents = PDCasefileDocumentsHelper.getCommonAppReports(cassefileId);
		if ( documents != null
			&& documents.size() > 0 ){
		    Collections.sort((List<CasefileDocumentsResponseEvent>)documents,new Comparator<CasefileDocumentsResponseEvent>() {
			@Override
			public int compare(CasefileDocumentsResponseEvent d1, CasefileDocumentsResponseEvent d2) {
				return Integer.valueOf(d1.getReportId()).compareTo(Integer.valueOf(d2.getReportId()));
			}
		    });
		    regform.setCommonAppDocument( documents.get(documents.size()-1) );
		} else {
		    regform.setMsg("No Records found for this casefile Id");
		    return mapping.findForward("error");	
		}
		
	    }
		
	    return mapping.findForward("success");
	}

}
