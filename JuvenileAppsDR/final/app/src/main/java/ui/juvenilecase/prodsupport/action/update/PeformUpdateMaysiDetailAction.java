package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.juvenilecase.prodsupport.helpers.Constants;
import ui.juvenilecase.prodsupport.helpers.QueryObject;
import ui.security.SecurityUIHelper;

/**
 * @author mlawles
 */

public class PeformUpdateMaysiDetailAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		
		String maysiassessmntId = regform.getMaysiassessmntId();

		if (maysiassessmntId == null || maysiassessmntId.equals("")) {
			regform.setMsg("PerformUpdateMaysiAssessAction.java (59) - Maysi Assessment ID was null.");
			return mapping.findForward("error");
		}
		
		String newAssessoption = regform.getAssessoptionBox();
		String newReferralnum = regform.getNewReferralNum();
	
		QueryObject record = retrieveRecord(regform);
				
		if (record==null){
			regform.setMsg("PerformUpdateMaysiAssessAction.java (63) - Could not retrieve record.");
			return (mapping.findForward("error"));
		}
		
		String updateStatement1 = null;
		String updateStatement2 = null;
		String updateStatement3 = null;
		
		ArrayList updateList = new ArrayList();
	
		if (newAssessoption!=null && newAssessoption.equals("")==false)
		{
			
		if (newAssessoption.equals(record.getAssessoption())==false)
			{
			updateStatement1 = "update jims2.JCMAYSIASSESSMNT set ASSESSOPTION = '"+newAssessoption
				+"' where maysiassessmnt_id="+ maysiassessmntId +";";
			
			Constants.writeToLog(updateStatement1, SecurityUIHelper.getLogonId());
			updateList.add(updateStatement1);
			}
		else
			{
				regform.setMsg("Duplicate code selected. Please try again.");
				return mapping.findForward("error");
			}
		}	
		
		String newAssessDate = null;
		String newAssessTimestamp = null;
		
		String beginday = regform.getBeginday();
		String beginmonth = regform.getBeginmonth();
		String beginyear = regform.getBeginyear();		
		
		if (beginday!=null&&beginmonth!=null&&beginyear!=null)
		{
			if (!beginday.equals("")&&!beginmonth.equals("")&&!beginyear.equals(""))
			{
				newAssessDate = beginyear+"-"+beginmonth+"-"+beginday;
				newAssessTimestamp = beginyear+"-"+beginmonth+"-"+beginday+"-01.01.01.000000";
				regform.setNewBeginDate(newAssessDate);
			}
		}
					
		if (newAssessDate!=null&&newAssessDate.equals("")==false)
		{
			if(checkOtherReferrals(record, newAssessDate)==false)
			{
				updateStatement2 = "update jims2.JCMAYSIASSESSMNT set ASSESSDATE='"+newAssessTimestamp
				+"' where maysiassessmnt_id="+ maysiassessmntId +";";
				updateList.add(updateStatement2);
			}
			else
			{
				regform.setMsg("Date/Referralnum combination already exists. Please try again.");
				return mapping.findForward("error");
			}
		}
		
		if (newReferralnum!=null && newReferralnum.equals("")==false)
		{			
			if (newReferralnum.equals(record.getReferralnumber())==false
				&&checkOtherReferrals(record, newAssessDate)==false)
			{
			updateStatement3 = "update jims2.JCMAYSIASSESSMNT set REFERRALNUMBER = '"+newReferralnum
				+"' where maysiassessmnt_id="+ maysiassessmntId +";";
			
			Constants.writeToLog(updateStatement3,SecurityUIHelper.getLogonId());
			updateList.add(updateStatement3);
			}
			else
			{
				regform.setMsg("Duplicate referral number entered. Please try again.");
				return mapping.findForward("error");
			}
		}
		

		boolean success = false;
		Iterator iter = updateList.iterator();
		
		while (iter.hasNext()){
			String updateStatement = (String) iter.next();
		
			success = Constants.runStatement(updateStatement);
			
			if (success==false)
				break;
		} 
		
		if (success==true){
			Constants.writeToLog("Performed an UPDATE MAYSI ASSESSMENT for MAYSIASSESSMNT_ID="
					+ maysiassessmntId, SecurityUIHelper.getLogonId());

			regform.setMsg("");
			return mapping.findForward("success");
		}
		else{
			regform.setMsg("Error - The assessment was not updated. PerformUpdateMaysiAssessAction.java (130)");
			return mapping.findForward("error");
		}

	}
	
	/** Returns first record only **/
	private QueryObject retrieveRecord(ProdSupportForm regform){
		
		ArrayList maysis = regform.getMaysis();
		QueryObject record = null;
		
		Iterator iter = maysis.iterator();
		if (iter.hasNext())
		{
			record = (QueryObject)iter.next();
		}
		
		return record;

	}
	
	/**JCMAYSIASSESSMENT has unique indexes on the combination of JuvenileID, Referralnumber, and
	 * AssessmentDate. No duplicate combinations of those 3 are allowed. 
	 * CheckOtherReferrals returns true if a matching record is found.**/
	
	private boolean checkOtherReferrals(QueryObject record, String newDate){
		boolean status = false;
		
		ArrayList referrals = Constants.singleOIDQuery("JCMAYSIASSESSMNT", "JUVENILE_ID", record.getJuvenileid());
		
		if (referrals !=null)
		{
		Iterator iter = referrals.iterator();
		
			while (iter.hasNext())
			{
				QueryObject next = (QueryObject) iter.next();
				if (newDate==null && next.getReferralnumber().equals(record.getReferralnumber()))
				{
					if (dateOnly(next.getAssessdate()).equals(dateOnly(record.getAssessdate())))
						status = true;
				}
				if (newDate!=null && next.getReferralnumber().equals(record.getReferralnumber()))
				{
					if (dateOnly(newDate).equals(dateOnly(next.getAssessdate())))
						status = true;					
				}
				
				if (status)
					break;
			}
		}
		
		return status;
	}
	
	/**Strips off the time of day from a date for comparison purposes**/
	
	private String dateOnly(String dateWithTime){
		
	String dateTime[] = dateWithTime.split(" ");
		
	return dateTime[0];	
	}

}
