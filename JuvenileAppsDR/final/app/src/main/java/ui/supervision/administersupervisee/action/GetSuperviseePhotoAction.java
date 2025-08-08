/**
 * 
 */
package ui.supervision.administersupervisee.action;

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
import ui.common.PhotoHelper;
import ui.supervision.supervisee.form.SuperviseeForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

/**
 * @author cc_cwalters
 *
 */
public class GetSuperviseePhotoAction  extends JIMSBaseAction
{

	/**
	 * Display a supervisee photo
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws Exception
	 */
	public ActionForward getPhotoDetail(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws Exception
		{
				//retrieve photo data off header form
			SuperviseeHeaderForm 
				myHeaderForm =
					(SuperviseeHeaderForm)getSessionForm(
							aMapping, aRequest, 
								UIConstants.SUPERVISEE_HEADER_FORM,true);

			if (myHeaderForm.isHasPhoto())
			{
				//call helper method to display photo
				PhotoHelper.returnPhoto(aResponse, 
						myHeaderForm.getSuperviseePhoto());									
			}
			
			// RRY Caused an java.lang.IllegalStateException: Cannot forward. Response already committed.
			return null;
		}//end of getPhotoDetail()

	/**
	 * Display a supervisee photo thumbnail image
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws Exception
	 */
	public ActionForward getThumbNail(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws Exception
		{
			SuperviseeForm supervisee_form = (SuperviseeForm)aForm;
			
				//retrieve photo data from DB
			GetSuperviseePhotoEvent get_photo_event = new GetSuperviseePhotoEvent();
			get_photo_event.setSpn(supervisee_form.getSuperviseeId()); //hardcoded for proof of concept
			
			SuperviseePhotoResponseEvent photo_response_event = 
				(SuperviseePhotoResponseEvent)
					MessageUtil.postRequest(get_photo_event, 
							SuperviseePhotoResponseEvent.class);
			
			supervisee_form.setSuperviseePhoto(photo_response_event.getSuperviseePhoto());
			supervisee_form.setSuperviseePhotoCreateDate(
					DateUtil.dateToString(photo_response_event.getDateCreated(), 
							DateUtil.DATE_FMT_1));
			
				//call helper method to display photo
			PhotoHelper.returnPhoto(aResponse, 
					photo_response_event.getSuperviseeThumbnail());		
		
			return aMapping.findForward("success");
		}//end of getThumbNail()
	
	@Override
	protected void addButtonMapping(Map keyMap) 
	{
		keyMap.put("button.photoDetail", "getPhotoDetail");
		keyMap.put("button.photoThumbnail", "getThumbNail");	
	}
	
}
