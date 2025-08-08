/*
 * Created on Jun 17, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenile.reply;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import pd.juvenile.JuvenileAbusePerpatrator;

import mojo.km.messaging.ResponseEvent;

/**

 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenileReferralProbationDatesResponseEvent extends ResponseEvent
{
    	
    //add id
    	private String referralNumber;	
    	private String probationStartDate;
    	private String probationEndDate;
    	private String refCloseDate;
    	
	public String getRefCloseDate()
	{
	    return refCloseDate;
	}
	public void setRefCloseDate(String refCloseDate)
	{
	    this.refCloseDate = refCloseDate;
	}
	public String getProbationEndDate()
	{
	    return probationEndDate;
	}
	public void setProbationEndDate(String probationEndDate)
	{
	    this.probationEndDate = probationEndDate;
	}
	
    	public String getProbationStartDate()
	{
	    return probationStartDate;
	}
	public void setProbationStartDate(String probationStartDate)
	{
	    this.probationStartDate = probationStartDate;
	}
	public String getReferralNumber()
	{
	    return referralNumber;
	}
	public void setReferralNumber(String referralNumber)
	{
	    this.referralNumber = referralNumber;
	}
	
	
	
	

}
