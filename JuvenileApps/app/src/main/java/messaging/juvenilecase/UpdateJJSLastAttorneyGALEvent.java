package messaging.juvenilecase;

import java.util.Date;

import mojo.km.messaging.RequestEvent;
import pd.codetable.Code;
import pd.juvenile.Juvenile;

public class UpdateJJSLastAttorneyGALEvent extends RequestEvent
{
    
	private String	juvenileNumber;	
	private String	galName;
	private String	galbarNumber;
	/*private String	jjclcourtId;
	private String	recType;*/
	
	public String getJuvenileNumber()
	{
	    return juvenileNumber;
	}
	public void setJuvenileNumber(String juvenileNumber)
	{
	    this.juvenileNumber = juvenileNumber;
	}
	public String getGalName()
	{
	    return galName;
	}
	public void setGalName(String galName)
	{
	    this.galName = galName;
	}
	public String getGalbarNumber()
	{
	    return galbarNumber;
	}
	public void setGalbarNumber(String galbarNumber)
	{
	    this.galbarNumber = galbarNumber;
	}
	/*public String getRecType()
	{
	    return recType;
	}
	public void setRecType(String recType)
	{
	    this.recType = recType;
	}
	public String getJjclcourtId()
	{
	    return jjclcourtId;
	}
	public void setJjclcourtId(String jjclcourtId)
	{
	    this.jjclcourtId = jjclcourtId;
	}*/
}
