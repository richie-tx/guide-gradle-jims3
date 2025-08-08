package pd.juvenilecase;// no longer in use. Migrated to SM. Refer US #87188.
    /*MIGRATED SQL-PART OF REFERRAL CONVERSION*/
/*
 * Created on Jun 19, 2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 
package pd.juvenilecase;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import pd.contact.user.UserProfile;

import mojo.km.utilities.DateUtil;

*//**
 * @author mchowdhury To change the template for this generated type comment go
 *         to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 *         Comments
 *//*
public class CasefileExtractionUtility
{

    *//**
     * Calculates the age in years given a date.
     * 
     * @param ageDate
     * @return age in years, 0 if age parameter is null
     *//*
    protected static int getAgeInYears(Date ageDate)
    {
	if (ageDate == null)
	{
	    return 0;
	}
	Calendar birthdate = Calendar.getInstance();
	birthdate.setTime(ageDate);
	Calendar now = Calendar.getInstance();

	int age = 0;
	age = now.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR);
	birthdate.add(Calendar.YEAR, age);
	if (now.before(birthdate))
	{
	    age--;
	}
	return age;
    }

    *//**
     * @param jjsService
     * @param errorMessage
     * @return
     *//*
    protected JuvenileCasefileExtractionErrorBean createErrorBean(JJSService jjsService, String errorMessage)
    {
	return new JuvenileCasefileExtractionErrorBean(jjsService, errorMessage);
    }

    protected Timestamp getCurrentTime()
    {
	return new Timestamp(DateUtil.getCurrentDate().getTime());
    }

    *//**
     * @param jjsService
     * @param string
     * @return
     *//*
    protected static String logMessage(JJSService jjsService, String errorMessage)
    {
	StringBuffer buff = new StringBuffer(DateUtil.getCurrentDate().toString());
	buff.append(" CaseLoadManager=");
	buff.append(jjsService.getCaseLoadManagerId());
	buff.append(" JuvenileNum=");
	buff.append(jjsService.getJuvenileNum());
	buff.append(" ReferralNum=");
	buff.append(jjsService.getReferralNum());
	buff.append(" JPOId=");
	UserProfile jpo = jjsService.getProbationOfficer();
	if (jpo != null)
	{
	    buff.append(jpo.getLogonId());
	}
	else
	{
	    buff.append(jjsService.getProbationOfficerId());
	}
	buff.append(" AssignmentLevelId=");
	buff.append(jjsService.getAssignmentLevelId());
	buff.append(" UnitId=");
	buff.append(jjsService.getUnitId());
	buff.append(" ServiceDate=");
	buff.append(DateUtil.dateToString(jjsService.getServiceDate(), DateUtil.DATE_FMT_1));
	buff.append(" AddDate=");
	buff.append(DateUtil.dateToString(jjsService.getAddDate(), DateUtil.DATE_FMT_1));
	buff.append(" ");
	buff.append(errorMessage);
	return buff.toString();
    }
}
*/