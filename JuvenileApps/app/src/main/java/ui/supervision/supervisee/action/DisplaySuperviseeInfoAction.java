/*
 * Created on Feb 12, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.supervisee.action;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administersupervisee.GetSuperviseePhotoEvent;
import messaging.administersupervisee.reply.SuperviseePhotoResponseEvent;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.supervisee.form.SuperviseeForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;
import ui.supervision.administersupervisee.UIAdministerSuperviseeHelper;

/**
 * @author jjose
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class DisplaySuperviseeInfoAction extends JIMSBaseAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.link", "link");

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequloest
	 * @param aResponse
	 * @return ActionForward
	 * @throws GeneralFeedbackMessageException 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public ActionForward link(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException {

		String myForward = UIConstants.SUCCESS;
		SuperviseeForm superviseeForm = (SuperviseeForm) aForm;
		superviseeForm.setSuperviseePhoto(null);  // was not being cleared in caseload flow
		if ("".equals(superviseeForm.getFromPage()) ){
			String fromPage = aRequest.getParameter("fromPage");
			if (fromPage == null ){
				superviseeForm.setFromPage("");
			} else {
				superviseeForm.setFromPage(fromPage);
			}	
		}
		aRequest.setAttribute("fromPage","");
		String superviseeId = superviseeForm.getSelectedValue();
		// tabLink should only have a value if this action is access from the Supervisee tab which uses/sets superviseeId on from
		if ("Y".equals(superviseeForm.getTabLink()) ){
			superviseeId = "";
		}
		superviseeForm.setTabLink("");
		// some href calls are using superviseeId to pass superviseeId value		
		if (superviseeId == null || superviseeId.equals("")){
			superviseeId = superviseeForm.getSuperviseeId();
		}
		superviseeForm.setSuperviseeId(superviseeId);
		
		SuperviseeHeaderForm myHeaderForm = (SuperviseeHeaderForm) getSessionForm(
				aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
		// 9-13-10 needs to load header each time in case compliance status has changed		
		//		if (!superviseeId.equals(myHeaderForm.getSuperviseeId()) ){
			myHeaderForm.setSuperviseeId(superviseeId);
			UICommonSupervisionHelper.populateSuperviseeHeaderForm(myHeaderForm);
		//		}
		UIAdministerSuperviseeHelper.populateSuperviseeForm(superviseeForm);
   		
   		//retrieve photo data from DB
		SuperviseePhotoResponseEvent photo_response_event = null;

		GetSuperviseePhotoEvent get_photo_event = new GetSuperviseePhotoEvent();
		get_photo_event.setSpn( superviseeId );
		photo_response_event = (SuperviseePhotoResponseEvent) MessageUtil.postRequest( get_photo_event, SuperviseePhotoResponseEvent.class );
		
		//set form data to indicate whether or not photo data found
		if (photo_response_event != null)
		{			
			myHeaderForm.setSuperviseePhoto(photo_response_event.getSuperviseePhoto());
			myHeaderForm.setSuperviseePhotoCreateDate(
					DateUtil.dateToString(photo_response_event.getDateCreated(), 
							DateUtil.DATE_FMT_1));
			myHeaderForm.setHasPhoto(true);
		}
		else
		{
			myHeaderForm.setSuperviseePhoto(null);
			myHeaderForm.setHasPhoto(false);			
		}
		//System.out.println("Elapsed Time1= " + (System.currentTimeMillis()-startTime)/1000F);
		return aMapping.findForward(myForward);
	}


}// END CLASS
