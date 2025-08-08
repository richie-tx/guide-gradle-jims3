package messaging.juvenilecase;

import java.util.Date;

import mojo.km.messaging.RequestEvent;
import pd.codetable.Code;
import pd.juvenile.Juvenile;

public class SaveJJSLastAttorneyEvent extends RequestEvent
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String	attorneyName;
	private String	attorneyConnection;
	private String	barNumber;
	private String	juvenileNumber;
	private String	jjclcourtId;
	private String	recType;
	// gal chnages for task 158461
	private String	galName;
	private String	galbarNumber;
	//
	
	public String getJuvenileNumber()
	{
	    return juvenileNumber;
	}
	public void setJuvenileNumber(String juvenileNumber)
	{
	    this.juvenileNumber = juvenileNumber;
	}

	/**
	 * @return the attorneyName
	 */
	public String getAttorneyName()
	{
	    return attorneyName;
	}
	/**
	 * @param attorneyName the attorneyName to set
	 */
	public void setAttorneyName(String attorneyName)
	{
	    this.attorneyName = attorneyName;
	}
	/**
	 * @return the attorneyConnection
	 */
	public String getAttorneyConnection()
	{
	    return attorneyConnection;
	}
	/**
	 * @param attorneyConnection the attorneyConnection to set
	 */
	public void setAttorneyConnection(String attorneyConnection)
	{
	    this.attorneyConnection = attorneyConnection;
	}
	/**
	 * @return the barNumber
	 */
	public String getBarNumber()
	{
	    return barNumber;
	}
	/**
	 * @param barNumber the barNumber to set
	 */
	public void setBarNumber(String barNumber)
	{
	    this.barNumber = barNumber;
	}
	
	/*public String getUpdateUser()
	{
	    return updateUser;
	}

	public void setUpdateUser(String updateUser)
	{
	    this.updateUser = updateUser;
	}
	
        
	public Date getUpdateDate()
	{
	    return updateDate;
	}

	public void setUpdateDate(Date updateDate)
	{
	    this.updateDate = updateDate;
	}*/
	
		/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid()
	{
	    return serialVersionUID;
	}
	public String getJjclcourtId()
	{
	    return jjclcourtId;
	}
	public void setJjclcourtId(String jjclcourtId)
	{
	    this.jjclcourtId = jjclcourtId;
	}
	public String getRecType()
	{
	    return recType;
	}
	public void setRecType(String recType)
	{
	    this.recType = recType;
	}
	// gal chnages for task 158461
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
	//
}
