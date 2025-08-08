//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenile\\SaveJuvenileAbuseEvent.java

package messaging.juvenile;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class SaveJuvenileDualStatusEvent extends RequestEvent
{
	private String dualstatusId;
	private String juvenileNum;
    	private String supervisionNum;
    	private Date createDate;
    	private String referralRegion;	
	private String custodyStatus;	
	private String cpsLevelofcare;
	private String parentalRights;	
	private String CPSServices;
	private String placementDate;
	private String placementType;
	private String placementtypeOther;
	private String placementRemovalReason;
	private String placementremovalOther;	
	private String dualstatusComments;
	
	
	/**
	 * @roseuid 42BC4DFA00B7
	 */
	public SaveJuvenileDualStatusEvent()
	{

	}
		/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}

	/**
	 * @return
	 */
	public String getSupervisionNum()
	{
		return supervisionNum;
	}

	/**
	 * @param string
	 */
	public void setSupervisionNum(String string)
	{
		supervisionNum = string;
	}
	public String getDualstatusId()
	{
	    return dualstatusId;
	}
	public void setDualstatusId(String dualstatusId)
	{
	    this.dualstatusId = dualstatusId;
	}
	public Date getCreateDate()
	{
	    return createDate;
	}
	public void setCreateDate(Date createDate)
	{
	    this.createDate = createDate;
	}
	public String getReferralRegion()
	{
	    return referralRegion;
	}
	public void setReferralRegion(String referralRegion)
	{
	    this.referralRegion = referralRegion;
	}
	public String getCustodyStatus()
	{
	    return custodyStatus;
	}
	public void setCustodyStatus(String custodyStatus)
	{
	    this.custodyStatus = custodyStatus;
	}
	public String getCpsLevelofcare()
	{
	    return cpsLevelofcare;
	}
	public void setCpsLevelofcare(String cpsLevelofcare)
	{
	    this.cpsLevelofcare = cpsLevelofcare;
	}
	
	public String getCPSServices()
	{
	    return CPSServices;
	}
	public void setCPSServices(String cPSServices)
	{
	    CPSServices = cPSServices;
	}
	public String getPlacementDate()
	{
	    return placementDate;
	}
	public void setPlacementDate(String placementDate)
	{
	    this.placementDate = placementDate;
	}
	public String getPlacementType()
	{
	    return placementType;
	}
	public void setPlacementType(String placementType)
	{
	    this.placementType = placementType;
	}
	public String getPlacementtypeOther()
	{
	    return placementtypeOther;
	}
	public void setPlacementtypeOther(String placementtypeOther)
	{
	    this.placementtypeOther = placementtypeOther;
	}
	public String getPlacementRemovalReason()
	{
	    return placementRemovalReason;
	}
	public void setPlacementRemovalReason(String placementRemovalReason)
	{
	    this.placementRemovalReason = placementRemovalReason;
	}
	public String getPlacementremovalOther()
	{
	    return placementremovalOther;
	}
	public void setPlacementremovalOther(String placementremovalOther)
	{
	    this.placementremovalOther = placementremovalOther;
	}
	public String getDualstatusComments()
	{
	    return dualstatusComments;
	}
	public void setDualstatusComments(String dualstatusComments)
	{
	    this.dualstatusComments = dualstatusComments;
	}
	public String getParentalRights()
	{
	    return parentalRights;
	}
	public void setParentalRights(String parentalRights)
	{
	    this.parentalRights = parentalRights;
	}
	
}
