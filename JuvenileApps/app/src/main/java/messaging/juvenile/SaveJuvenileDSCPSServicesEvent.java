package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class SaveJuvenileDSCPSServicesEvent extends RequestEvent {
	
	private String dualID;
	private String juvenileNumber;	
	private String supervisionNumber;
	private String CPSService;	
	

	public SaveJuvenileDSCPSServicesEvent()
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
	public String getCPSService()
	{
	    return CPSService;
	}

	public void setCPSService(String cPSService)
	{
	    CPSService = cPSService;
	}
		
}
