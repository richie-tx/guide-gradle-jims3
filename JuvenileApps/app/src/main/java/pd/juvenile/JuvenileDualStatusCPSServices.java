package pd.juvenile;

import java.util.Iterator;
import messaging.juvenile.GetJuvenileAbusePerpsListEvent;
import messaging.juvenile.GetJuvenileAbuserRelationshipListEvent;
import messaging.juvenile.GetJuvenileDSservicesListEvent;
import messaging.juvenile.SaveJuvenileAbusePerpEvent;
import messaging.juvenile.SaveJuvenileAbuserRelationshipEvent;
import messaging.juvenile.SaveJuvenileDSCPSServicesEvent;
import messaging.juvenile.reply.JuvenileAbusePerpatratorResponseEvent;
import messaging.juvenile.reply.JuvenileAbuseResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;
import pd.juvenilecase.TraitType;

public class JuvenileDualStatusCPSServices extends PersistentObject {
	
	//private int abusePerpId;

	private String dualID;
	private String cpsService;	
	private String juvenileID;	
	private String supervisionNumber;	
	

	/**
	 * @roseuid 42B062E1016F
	 */
	public JuvenileDualStatusCPSServices()
	{
	}
	
	public String getDualID() {
		return dualID;
	}

	public void setDualID(String pdualID) {
		if (this.dualID == null || !this.dualID.equals(pdualID))
		{
			markModified();
		}
		this.dualID = pdualID;
	}		
	public void setCpsService(String pcpsService)
	{
	    if (this.cpsService == null || !this.cpsService.equals(pcpsService))
		{
			markModified();
		}		
	    this.cpsService = pcpsService;
	}

	public String getCpsService()
	{
	    fetch();
	    return cpsService;
	}

	
	public String getJuvenileID()
	{
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
	
	
	/**
	 * 
	 * @param saveEvent
	 */
	public void hydrate(SaveJuvenileDSCPSServicesEvent saveEvent) {
		
		this.setDualID(saveEvent.getDualID());
		this.setJuvenileID(saveEvent.getJuvenileNumber());
		this.setSupervisionNumber(saveEvent.getSupervisionNumber());
		this.setCpsService(saveEvent.getCPSService());
	}


	public static Iterator findJuvenileDSdpsServices(GetJuvenileDSservicesListEvent event) {
		IHome home = new Home();
		return home.findAll(event, JuvenileDualStatusCPSServices.class);
	}
	
	
	
}
