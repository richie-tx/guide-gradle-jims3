package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class SaveJuvenileDSPlacementsEvent extends RequestEvent {
	
	private String dualID;
	private String juvenileNumber;	
	private String supervisionNumber;
	private String placementDate;
	private String placementType;
	private String placementtypeotherReason;	
	private String placementRemovalReason;
	private String placementremovalreasonOther;
	
	public SaveJuvenileDSPlacementsEvent()
	{

	}	
	
	public String getJuvenileNumber()
	{
	    return juvenileNumber;
	}


	public void setJuvenileNumber(String juvenileNumber)
	{
	    this.juvenileNumber = juvenileNumber;
	}
	public String getSupervisionNumber()
	{
	    return supervisionNumber;
	}


	public void setSupervisionNumber(String supervisionNumber)
	{
	    this.supervisionNumber = supervisionNumber;
	}
	public String getDualID()
	{
	    return dualID;
	}

	public void setDualID(String dualID)
	{
	    this.dualID = dualID;
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
	public String getPlacementtypeotherReason()
	{
	    return placementtypeotherReason;
	}

	public void setPlacementtypeotherReason(String placementtypeotherReason)
	{
	    this.placementtypeotherReason = placementtypeotherReason;
	}
	public String getPlacementRemovalReason()
	{
	    return placementRemovalReason;
	}

	public void setPlacementRemovalReason(String placementRemovalReason)
	{
	    this.placementRemovalReason = placementRemovalReason;
	}
	public String getPlacementremovalreasonOther()
	{
	    return placementremovalreasonOther;
	}

	public void setPlacementremovalreasonOther(String placementremovalreasonOther)
	{
	    this.placementremovalreasonOther = placementremovalreasonOther;
	}
	
		
}
