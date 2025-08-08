package ui.juvenilecase.prodsupport.action.update;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import messaging.productionsupport.SaveProductionSupportJuvenileSchoolHistoryEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;

/**
 * @author Jims2
 */

public class PerformUpdateSchoolHistAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;				
		String schoolHistId = regform.getSchoolhistId();		
		// save the school history changes
		SaveProductionSupportJuvenileSchoolHistoryEvent saveEvent =
			(SaveProductionSupportJuvenileSchoolHistoryEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.SAVEPRODUCTIONSUPPORTJUVENILESCHOOLHISTORY);

		if (schoolHistId == null || schoolHistId.equals(" ")) {
			regform.setMsg("SchoolHistID was null.");
			return mapping.findForward("error");
		}
		
		String newattDate = null;
		
		String attday = regform.getAttday();
		String attmonth = regform.getAttmonth();
		String attyear = regform.getAttyear();
		
		//Check for intentional Blank value
		if (!attday.equals("999")&&!attmonth.equals("999")&&!attyear.equals("999")){
			if (!attday.equals("")&&!attmonth.equals("")&&!attyear.equals("")){
				newattDate = attyear+"-"+attmonth+"-"+attday+"-01.01.01.000000";
				/**Update LASTATTENDEDDATE **/
				String justDateStringFormat = attyear+"-"+attmonth+"-"+attday; 
				regform.setNewAttDate(justDateStringFormat);
				DateFormat fullDateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd-01.01.01.000000");
				saveEvent.setLastAttendedDate(fullDateTimeFormatter.parse(newattDate));
			}			
		}else{	
			regform.setNewAttDate("");
		}
	
		saveEvent.setSchoolHistoryId(regform.getSchoolhistId());
		if(regform.getAppropGradeBox() != null && !regform.getAppropGradeBox().equalsIgnoreCase("")){
			/**Update APPROPGRADECD **/
			if(regform.getAppropGradeBox().equalsIgnoreCase("NV")){
				saveEvent.setAppropriateLevelCode("");
				//regform.setAppropGradeBox("");
			}else{
				saveEvent.setAppropriateLevelCode(regform.getAppropGradeBox());
			}			
		}
		if(regform.getExitTypeBox() != null && !regform.getExitTypeBox().equalsIgnoreCase("")){
			/**Update EXITTYPECD **/
			if(regform.getExitTypeBox().equalsIgnoreCase("NV")){
				saveEvent.setExitTypeCode("");
				//regform.setExitTypeBox("");
			}else{
				saveEvent.setExitTypeCode(regform.getExitTypeBox());
			}
		}
		if(regform.getCurrentGradeBox() != null && !regform.getCurrentGradeBox().equalsIgnoreCase("")){
			/**Update CURRENTGRADECD **/
			if(regform.getCurrentGradeBox().equalsIgnoreCase("NV")){
				saveEvent.setGradeLevelCode("");
				//regform.setCurrentGradeBox("");
			}else{
				saveEvent.setGradeLevelCode(regform.getCurrentGradeBox());
			}
		}
		if(regform.getGradesRepeatCodeBox() != null && !regform.getGradesRepeatCodeBox().equalsIgnoreCase("")){		
			/**Update GRADESREPEATCD **/
			if(regform.getGradesRepeatCodeBox().equalsIgnoreCase("NV")){
				saveEvent.setGradesRepeatedCode("");
				//regform.setGradesRepeatCodeBox("");
			}else{
				saveEvent.setGradesRepeatedCode(regform.getGradesRepeatCodeBox());
			}
		}
		
	ArrayList<JuvenileSchoolHistoryResponseEvent> schoolHists = regform.getSchoolhists();
	String oldJuvNum = "";
	for (JuvenileSchoolHistoryResponseEvent event : schoolHists)
	{
	    oldJuvNum = event.getJuvenileNum();
	    break;
	}

	if (oldJuvNum != null && oldJuvNum != regform.getJuvenileId())
	{
	    GetJuvenileProfileMainEvent getJuvProfileEvent = new GetJuvenileProfileMainEvent();
	    getJuvProfileEvent.setJuvenileNum(regform.getJuvenileId());
	    CompositeResponse response1 = MessageUtil.postRequest(getJuvProfileEvent);
	    JuvenileProfileDetailResponseEvent juvProfileRE = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(response1, JuvenileProfileDetailResponseEvent.class);
	    if (juvProfileRE == null)
	    {
		regform.setMsg("Update Failed! No juvenile record found for the specified juvenile number.");
		return mapping.findForward("error");
	    }
	    else
	    {
		regform.setMsg("");
	    }

	    for (JuvenileSchoolHistoryResponseEvent event : schoolHists)
	    {
		event.setJuvenileNum(regform.getJuvenileId());
	    }

	    saveEvent.setJuvenileNum(regform.getJuvenileId());
	    regform.setSchoolhists(schoolHists);
	}
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(saveEvent);	

		return mapping.findForward("success");

	}
	
}
