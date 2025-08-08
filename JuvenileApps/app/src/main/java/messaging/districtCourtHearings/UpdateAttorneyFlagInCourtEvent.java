package messaging.districtCourtHearings;

import java.util.Date;

import mojo.km.messaging.RequestEvent;
import pd.codetable.Code;
import pd.juvenile.Juvenile;

public class UpdateAttorneyFlagInCourtEvent extends RequestEvent
{
    
	private String	attorneyName;
	private String	attorneyConnection;
	private String	barNumber;
	private String	recType;
	private String	docketId;
	
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
	public String getRecType()
	{
	    return recType;
	}
	public void setRecType(String recType)
	{
	    this.recType = recType;
	}	
	
	public String getDocketId()
	{
	    return docketId;
	}
	public void setDocketId(String docketId)
	{
	    this.docketId = docketId;
	}
	
}
