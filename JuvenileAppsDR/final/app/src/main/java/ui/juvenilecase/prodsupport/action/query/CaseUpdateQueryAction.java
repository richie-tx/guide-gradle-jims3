package ui.juvenilecase.prodsupport.action.query;


import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.GetJuvenileCasefileByCasefileIdEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;


/**
 * @author jims2
 * 
 */

public class CaseUpdateQueryAction extends Action {

	private Logger log = Logger.getLogger("CaseUpdateQueryAction");
	public ActionForward execute(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) aForm;	
		/** Check for initial load of this page **/
		String clrChk = aRequest.getParameter("clr");
		if (clrChk!=null && clrChk.equalsIgnoreCase("Y"))
		{
			regform.clearAllResults();
			regform.setMsg("");
			return aMapping.findForward("error");		
		}
		
		
		String casefileId = regform.getCasefileId();

		if (casefileId == null || casefileId.equals(" ")) {
			regform.setMsg("CasefileId was null.");
			return aMapping.findForward("error");
		}
		
		//Clear the form
		regform.clearAllResults();
	
		//Log the query attempt				
		GetJuvenileCasefileByCasefileIdEvent getCasefileEvent = (GetJuvenileCasefileByCasefileIdEvent)
			EventFactory.getInstance( JuvenileCaseControllerServiceNames.GETJUVENILECASEFILEBYCASEFILEID ) ;
		getCasefileEvent.setSupervisionNumber( casefileId ) ;
			
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		dispatch.postEvent( getCasefileEvent ) ;
		
		CompositeResponse response = (CompositeResponse)dispatch.getReply( ) ;
		
		JuvenileCasefileResponseEvent casefile = (JuvenileCasefileResponseEvent)
				MessageUtil.filterComposite( response, JuvenileCasefileResponseEvent.class ) ;
		log.info("Casefile Query ID: " + casefileId + " LogonId: " + SecurityUIHelper.getLogonId().toUpperCase());
		// Populate casefile found
		if( casefile != null )
		{
			regform.setCasefileDet(casefile);
			regform.setOriginalCasefile(casefile);
			regform.setActdate(DateUtil.dateToString(casefile.getActivationDate(), DateUtil.DATE_FMT_1));
			regform.setCreatedate(DateUtil.dateToString(casefile.getCreateDate(), DateUtil.DATE_FMT_1));
			regform.setEnddate(DateUtil.dateToString(casefile.getSupervisionEndDate(), DateUtil.DATE_FMT_1));
		}else{
			regform.setCasefileDet(null);
			regform.setOriginalCasefile(null);
			regform.setActdate(null);
			regform.setCreatedate(null);
			regform.setEnddate(null);
		}
		regform.setMsg("");
		return aMapping.findForward("success");
	}
}


