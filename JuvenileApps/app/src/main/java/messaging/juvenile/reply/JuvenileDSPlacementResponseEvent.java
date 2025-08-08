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
public class JuvenileDSPlacementResponseEvent extends ResponseEvent
{
    	
    //add id
    	private String placementDate;
	private String placementType;
	private String placementtypeotherReason;	
	private String placementRemovalReason;
	private String placementremovalreasonOther;
	
	public String getPlacementType()
	{
	    return placementType;
	}

	public void setPlacementType(String placementType)
	{
	    this.placementType = placementType;
	}
	
	public String getPlacementRemovalReason()
	{
	    return placementRemovalReason;
	}

	public void setPlacementRemovalReason(String placementRemovalReason)
	{
	    this.placementRemovalReason = placementRemovalReason;
	}
	public String getPlacementDate()
	{
	    return placementDate;
	}

	public void setPlacementDate(String placementDate)
	{
	    this.placementDate = placementDate;
	}
	public String getPlacementremovalreasonOther()
	{
	    return placementremovalreasonOther;
	}

	public void setPlacementremovalreasonOther(String placementremovalreasonOther)
	{
	    this.placementremovalreasonOther = placementremovalreasonOther;
	}
	public String getPlacementtypeotherReason()
	{
	    return placementtypeotherReason;
	}

	public void setPlacementtypeotherReason(String placementtypeotherReason)
	{
	    this.placementtypeotherReason = placementtypeotherReason;
	}

}
