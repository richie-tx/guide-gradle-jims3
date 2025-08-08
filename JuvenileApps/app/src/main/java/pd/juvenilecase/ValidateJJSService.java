package pd.juvenilecase;// no longer in use. Migrated to SM. Refer US #87188. No references in the mapping file.
/**Migrated to sql as part of referral conversion**/

/*
 * Created on Jun 06, 2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 
package pd.juvenilecase;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import mojo.km.utilities.DateUtil;

import pd.codetable.Code;
import pd.contact.officer.OfficerProfile;
import pd.exception.InvalidProbationOfficerException;

*//**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 *//*
public class ValidateJJSService extends FileUtility
{
	*//**
	 * 
	 *//*
	public ValidateJJSService()
	{
	}

	*//**
	 * validate the jjsService
	 *   
	 * @param jjsService
	*//*
	protected static String validate(JJSService jjsService) throws RuntimeException, Exception 
	{
		String caseManagerId = jjsService.getCaseLoadManagerId();
		if(caseManagerId == null || caseManagerId.equals("")){
			return "CaseLoadManagerId is null.";
		}
			
		String juvNum = jjsService.getJuvenileNum();
		if(juvNum == null || juvNum.equals("")){
			return "Juvenile Number in JJSService is null.";
		}
		
		String referralNum = jjsService.getReferralNum();
		if(referralNum == null || referralNum.equals("")){
			return "Referral Number in JJSService is null.";
		}
		
		if(jjsService.getServiceDate() == null){
			return "Service Date in JJSService is null.";
		}else{
			if(jjsService.getServiceDate().after(DateUtil.getCurrentDate())){
				return "Service Date can not be in future.";
			}
			
			double days = (double) (DateUtil.getCurrentDate().getTime() - jjsService.getServiceDate().getTime()) / (24*60*60*1000);
			if(days < 0.0 ||  days > 7.0 ){
				return "Service Date can not be older than 7 days.";
			}
		}
	
		if(jjsService.getAddDate() == null){
			return "Add Date in JJSService is null.";
		}
		return "";
	}
	
	*//**
	* Calculate the number of days between two calendar days
	*
	* @param c1 The first date.
	* @param c2 The second date. c2 suppose to be the more current date
	* 
	*//*
	private static int getDaysDifference (Calendar c1, Calendar c2) {
		if (c1.after(c2)) 
		{ 
		    return -1;
		}
		int days = c2.get(Calendar.DAY_OF_YEAR) - c1.get(Calendar.DAY_OF_YEAR);
		int day = c2.get(Calendar.YEAR);
		if (c1.get(Calendar.YEAR) != day) {
		    c1 = (Calendar) c1.clone();
		    do {
		    	days += c1.getActualMaximum(java.util.Calendar.DAY_OF_YEAR);
		    	c1.add(Calendar.YEAR, 1);
		    } while (c1.get(Calendar.YEAR) != day);
		}
		return days;
	} 

}
*/