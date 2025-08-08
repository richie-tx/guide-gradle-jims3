package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.mentalhealth.reply.MAYSIDetailsResponseEvent;
import messaging.productionsupport.SaveProductionSupportMaysiAssessmentEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.DateUtil;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;

/**
 * @author jims2
 */

public class PerformUpdateMaysiAssessAction extends Action {

	private Logger log = Logger.getLogger("PerformUpdateMaysiAssessAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Boolean isChange = false;
		ProdSupportForm regform = (ProdSupportForm) form;	
		String maysiassessmntId = regform.getMaysiassessmntId();

		if (maysiassessmntId == null || maysiassessmntId.equals("")) {
			regform.setMsg("PerformUpdateMaysiAssessAction.java (59) - Maysi Assessment ID was null.");
			return mapping.findForward("error");
		}
		
		String newAssessoption = regform.getAssessoptionBox();
		String newReferralnum = regform.getNewReferralNum();
		String newassoofId = regform.getAssessOfficerId();
		boolean hasPrevmaysi = regform.getHasPreviousMaysi();
		boolean isAdminstred = regform.getIsAdministered();
		String newReasonCode = regform.getReasonNotDone();
		String newOtherReason = regform.getOtherReasonNotDone();
	
		MAYSIDetailsResponseEvent record = retrieveRecord(regform);
				
		if (record==null){
			regform.setMsg("PerformUpdateMaysiAssessAction.java (63) - Could not retrieve record.");
			return (mapping.findForward("error"));
		}
	
		// Call to update the maysi assessment record
		SaveProductionSupportMaysiAssessmentEvent maysiAssessmentEvent = 
			(SaveProductionSupportMaysiAssessmentEvent)EventFactory.getInstance(ProductionSupportControllerServiceNames.SAVEPRODUCTIONSUPPORTMAYSIASSESSMENT);
		
		maysiAssessmentEvent.setMaysiAssessmentId(maysiassessmntId);	
		if (newAssessoption != null && newAssessoption.equals("") == false){			
			if (newAssessoption.equals(record.getAssessmentOption()) == false){
				maysiAssessmentEvent.setAssessmentOption(newAssessoption);
				isChange = true;
			}
				//else{
//				regform.setMsg("Duplicate assess option selected. Please try again.");
//				return mapping.findForward("error");
//			}
		}
		
		if (newassoofId != null &&  newassoofId.equals(record.getAssessOfficerId()) == false){	
		    maysiAssessmentEvent.setAssessOfficerId(newassoofId);
			isChange = true;
		}
		
		if( hasPrevmaysi != record.isHasPreviousMAYSI() )
		{
		    maysiAssessmentEvent.setHasPreviousMaysi(hasPrevmaysi);
			isChange = true;
		}
		
		if( isAdminstred != record.isAdministered() )
		{
		    maysiAssessmentEvent.setIsAdministered(isAdminstred);
			isChange = true;
		}
		
		if (newReasonCode != null && newReasonCode.equalsIgnoreCase("NULL") == true && newReasonCode.equals(record.getReasonNotDoneId()) == false){	
		    maysiAssessmentEvent.setReasonNotDone(null);
			isChange = true;
		}
		
		if (newReasonCode != null && newReasonCode.equalsIgnoreCase("") == true && newReasonCode.equals(record.getReasonNotDoneId()) == false){	
		    maysiAssessmentEvent.setReasonNotDone("");
			isChange = true;
		}
		
		if (newReasonCode != null && !newReasonCode.equalsIgnoreCase("") && !newReasonCode.equalsIgnoreCase("NULL") && newReasonCode.equals(record.getReasonNotDoneId()) == false){	
		    maysiAssessmentEvent.setReasonNotDone(newReasonCode);
			isChange = true;
		}	
		
		if (newOtherReason != null && newOtherReason.equalsIgnoreCase("NULL") == true && newOtherReason.equals(record.getOtherReasonNotDone()) == false){	
		    maysiAssessmentEvent.setOtherReasonNotDone(null);
			isChange = true;
		}
		
		if (newOtherReason != null && newOtherReason.equalsIgnoreCase("") == true && newOtherReason.equals(record.getOtherReasonNotDone()) == false){	
		    maysiAssessmentEvent.setOtherReasonNotDone("");
			isChange = true;
		}
		
		if (newOtherReason != null && !newOtherReason.equalsIgnoreCase("") && !newOtherReason.equalsIgnoreCase("NULL") && newOtherReason.equals(record.getOtherReasonNotDone()) == false){	
		    maysiAssessmentEvent.setOtherReasonNotDone(newOtherReason);
			isChange = true;
		}
		
		
		
		if (newReferralnum != null && newReferralnum.equals("") == false){			
			if (newReferralnum.equals(record.getReferralNumber()) == false){
				maysiAssessmentEvent.setReferralNumber(newReferralnum);
				isChange = true;
			}
				//else{
//				regform.setMsg("Duplicate referral number entered. Please try again.");
//				return mapping.findForward("error");
//			}
		}
		
		String newAssessDate = regform.getNewBeginDate();
		String currentDateString = DateUtil.dateToString(record.getAssessmentDate(), "MM/dd/yyyy");

		boolean isAssessDateChanged = checkIfTwoValuesChanged(newAssessDate,DateUtil.dateToString(record.getAssessmentDate(),DateUtil.DATE_FMT_1));
		if (isAssessDateChanged)
		{
			Date newAssessmentDate = DateUtil.stringToDate(newAssessDate, DateUtil.DATE_FMT_1);
			maysiAssessmentEvent.setAssessmentDate(newAssessmentDate);
			isChange = true;
		}	
		else{
		    maysiAssessmentEvent.setAssessmentDate(record.getAssessmentDate());
			regform.setNewBeginDate("");
		}
		
		// check that a value changed before updating
		if(!isChange){
			regform.setMsg("At least one value needs to be changed.");
			return (mapping.findForward("error"));
		}
		//maysiAssessmentEvent.setAssessmentDate(DateUtil.stringToDate(newAssessDate, DateUtil.DATE_FMT_1));
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(maysiAssessmentEvent);
		regform.setMsg("");
		return mapping.findForward("success");
	}
	
	/**
	 * 
	 * @param regform
	 * @return
	 */
	private MAYSIDetailsResponseEvent retrieveRecord(ProdSupportForm regform){
		
		ArrayList maysis = regform.getMaysis();
		MAYSIDetailsResponseEvent record = null;
		
		Iterator iter = maysis.iterator();
		if (iter.hasNext())
		{
			record = (MAYSIDetailsResponseEvent)iter.next();
		}
		
		return record;
	}
	
	/**(
	 * compare two string values and determine if they are equal
	 * @param value
	 * @param compareValue
	 * @return
	 */
	private boolean checkIfTwoValuesChanged(String newValue, String OlderValue){
		boolean result = false;
		
		if(newValue != null && OlderValue != null && !newValue.equals("")){
			if(!newValue.equals(OlderValue)){
				result = true;
			}
		}else if (newValue != null && OlderValue == null){
				result = true;
		}
		
		return result;
	}

}
