package pd.juvenile;

import java.util.Iterator;

import messaging.juvenile.GetJuvenileDSPlacementListEvent;
import messaging.juvenile.SaveJuvenileDSPlacementsEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class JuvenileDualStatusPlacement extends PersistentObject {
	
	//private int abusePerpId;

	private String dualID;
	private String juvenileID;	
	private String supervisionNumber;
	private String placementDate;
	private String placementType;
	private String placementtypeotherReason;	
	private String placementRemovalReason;
	private String placementremovalreasonOther;
	

	/**
	 * @roseuid 42B062E1016F
	 */
	public JuvenileDualStatusPlacement()
	{
	}
	
	public String getDualID() {
	    	fetch();
		return dualID;
	}

	public void setDualID(String pdualID) {
		if (this.dualID == null || !this.dualID.equals(pdualID))
		{
			markModified();
		}
		this.dualID = pdualID;
	}		
	
	public String getJuvenileID()
	{
	    fetch();
	    return juvenileID;
	}

	public void setJuvenileID(String juvenileID)
	{
	    if (this.juvenileID == null || !this.juvenileID.equals(juvenileID))
		{
			markModified();
		}
	    this.juvenileID = juvenileID;
	}
	public String getSupervisionNumber()
	{
	    fetch();
	    return supervisionNumber;
	}

	public void setSupervisionNumber(String supervisionNumber)
	{
	    if (this.supervisionNumber == null || !this.supervisionNumber.equals(supervisionNumber))
		{
			markModified();
		}
	    this.supervisionNumber = supervisionNumber;
	}
	public void setPlacementDate(String placementDate)
	{
	    if (this.placementDate == null || !this.placementDate.equals(placementDate))
		{
			markModified();
		}
	    this.placementDate = placementDate;
	}
	public String getPlacementType()
	{
	    fetch();
	    return placementType;
	}
	public String getPlacementDate()
	{
	    fetch();
	    return placementDate;
	}

	public void setPlacementType(String placementType)
	{
	    if (this.placementType == null || !this.placementType.equals(placementType))
		{
			markModified();
		}
	    this.placementType = placementType;
	}
	public String getPlacementtypeotherReason()
	{
	    fetch();
	    return placementtypeotherReason;
	}

	public void setPlacementtypeotherReason(String placementtypeotherReason)
	{
	    if (this.placementtypeotherReason == null || !this.placementtypeotherReason.equals(placementtypeotherReason))
		{
			markModified();
		}
	    this.placementtypeotherReason = placementtypeotherReason;
	}
	public String getPlacementRemovalReason()
	{
	    fetch();
	    return placementRemovalReason;
	}

	public void setPlacementRemovalReason(String placementRemovalReason)
	{
	    if (this.placementRemovalReason == null || !this.placementRemovalReason.equals(placementRemovalReason))
		{
			markModified();
		}
	    this.placementRemovalReason = placementRemovalReason;
	}
	public String getPlacementremovalreasonOther()
	{
	    fetch();
	    return placementremovalreasonOther;
	}

	public void setPlacementremovalreasonOther(String placementremovalreasonOther)
	{
	    if (this.placementremovalreasonOther == null || !this.placementremovalreasonOther.equals(placementremovalreasonOther))
		{
			markModified();
		}
	    this.placementremovalreasonOther = placementremovalreasonOther;
	}
	
	
	
	/**
	 * 
	 * @param saveEvent
	 */
	public void hydrate(SaveJuvenileDSPlacementsEvent saveEvent) {
		
		this.setDualID(saveEvent.getDualID());
		this.setJuvenileID(saveEvent.getJuvenileNumber());
		this.setSupervisionNumber(saveEvent.getSupervisionNumber());
		this.setPlacementDate(saveEvent.getPlacementDate());
		this.setPlacementType(saveEvent.getPlacementType());
		this.setPlacementtypeotherReason(saveEvent.getPlacementtypeotherReason());
		this.setPlacementRemovalReason(saveEvent.getPlacementRemovalReason());
		this.setPlacementremovalreasonOther(saveEvent.getPlacementremovalreasonOther());
	}


	public static Iterator findJuvenileDSPlacements(GetJuvenileDSPlacementListEvent event) {
		IHome home = new Home();
		return home.findAll(event, JuvenileDualStatusPlacement.class);
	}
	
	
	
}
