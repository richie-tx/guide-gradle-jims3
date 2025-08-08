/*
 * Created on Apr 3, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.cscdcalendar.fieldVisit.action;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mojo.km.context.ContextManager;
import mojo.km.security.ISecurityManager;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.cscdcalendar.form.CSCalendarDisplayForm;
import ui.supervision.cscdcalendar.form.CSCalendarFVForm;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayCSFVItinerarySummaryAction extends JIMSBaseAction {

	public ActionForward next(
            ActionMapping aMapping,
            ActionForm aForm, 
            HttpServletRequest aRequest, 
            HttpServletResponse aResponse) 
                  throws GeneralFeedbackMessageException{
      CSCalendarFVForm form = (CSCalendarFVForm)aForm;
      ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
      Set userFeatures = securityManager.getFeatures(); 
      String forward = UIConstants.SUCCESS;
      //Create Field Visit does not need summary page, so it will
      //jump to caseload search page.
      if(form.getActivityFlow().equals(UIConstants.CREATE)) {
            if ("CCO".equals(form.getJobTitleCd()) && userFeatures.contains(naming.Features.CSCD_CALFV_QUAD_SEARCH)){
                  CSCalendarDisplayForm displayForm = (CSCalendarDisplayForm) getSessionForm(aMapping, aRequest,  "csCalendarDisplayForm", true);     
                  displayForm.setSelectedQuadrantId(form.getCurrentItinerary().getQuadrantCd());
                  displayForm.setQuadrantDescription(form.getCurrentItinerary().getQuadrantDesc());
 //                 displayForm.setQuadrantSearch("SOQUAD");  // commented out as part of fix for defect #61763  9-2-09 
            }
            forward = "caseloadSearch";
      } else {
            aRequest.setAttribute("status", "summary");
      }
      return aMapping.findForward(forward);
}

	
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");

	}

}
