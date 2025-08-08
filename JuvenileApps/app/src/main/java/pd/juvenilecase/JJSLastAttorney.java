
package pd.juvenilecase;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

import java.util.Date;
import java.util.Iterator;

public class JJSLastAttorney extends PersistentObject
{
	private String galBarNum;	
	private String galName;;
	private String atyBarNum;	 
        private String atyName;
        private String juvenileNum;
        private String attConnect;
        private String lastAttorneyID;
        private String jjclcourtId;
        private String jjcldetentionId;
     // gal changes for task 158461
        private Date galadddate;	
        //
	
	
	public JJSLastAttorney()
	{
	}
	
	static public JJSLastAttorney find(String lastAttorneyID)
	{
		IHome home = new Home();
		JJSLastAttorney lastAty = (JJSLastAttorney) home.find(lastAttorneyID, JJSLastAttorney.class);
		return lastAty;
	}
	
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		Iterator lastAty = home.findAll(attrName, attrValue, JJSLastAttorney.class);
		return lastAty;
	}
	

	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		Iterator lastAty = home.findAll(event, JJSLastAttorney.class);
		return lastAty;
	}
	
	public String getLastAttorneyID()
	{
	    return lastAttorneyID;
	}

	public void setLastAttorneyID(String lastAttorneyID)
	{
	    if (this.lastAttorneyID == null || !this.lastAttorneyID.equals(lastAttorneyID))
		{
			markModified();
		}
	    this.lastAttorneyID = lastAttorneyID;
	}

	public String getGalBarNum()
	{
	    fetch();
	    return galBarNum;
	}
	public void setGalBarNum(String galBarNum)
	{
	    if (this.galBarNum == null || !this.galBarNum.equals(galBarNum))
		{
			markModified();
		}
	    this.galBarNum = galBarNum;
	}
	public String getGalName()
	{
	    fetch();
	    return galName;
	}
	public void setGalName(String galName)
	{
	    if (this.galName == null || !this.galName.equals(galName))
		{
			markModified();
		}
	    this.galName = galName;
	}
	public String getAtyBarNum()
	{
	    fetch();
	    return atyBarNum;
	}
	public void setAtyBarNum(String atyBarNum)
	{
	    if (this.atyBarNum == null || !this.atyBarNum.equals(atyBarNum))
		{
			markModified();
		}
	    this.atyBarNum = atyBarNum;
	}
	public String getAtyName()
	{
	    fetch();
	    return atyName;
	}
	public void setAtyName(String atyName)
	{
	    if (this.atyName == null || !this.atyName.equals(atyName))
		{
			markModified();
		}
	    this.atyName = atyName;
	}
	public String getJuvenileNum()
	{
	    fetch();
	    return juvenileNum;
	}
	public void setJuvenileNum(String juvenileNum)
	{
	    if (this.juvenileNum == null || !this.juvenileNum.equals(juvenileNum))
		{
			markModified();
		}
	    
	    this.juvenileNum = juvenileNum;
	}
	public String getAttConnect()
	{
	    fetch();
	    return attConnect;
	}
	public void setAttConnect(String attConnect)
	{
	    if (this.attConnect == null || !this.attConnect.equals(attConnect))
		{
			markModified();
		}
	    this.attConnect = attConnect;
	}
	public String getJjclcourtId()
	{
	    fetch();
	    return jjclcourtId;
	}

	public void setJjclcourtId(String jjclcourtId)
	{
	    if (this.jjclcourtId == null || !this.jjclcourtId.equals(jjclcourtId) ){
		markModified();
	    }
	    this.jjclcourtId = jjclcourtId;
	}
	public String getJjcldetentionId()
	{
	    fetch();
	    return jjcldetentionId;
	}

	public void setJjcldetentionId(String jjcldetentionId)
	{
	    if (this.jjcldetentionId == null || !this.jjcldetentionId.equals(jjcldetentionId)){
		markModified();
	    }
	    this.jjcldetentionId = jjcldetentionId;
	}
	// gal changes for task 158461
	public Date getGaladddate()
	{
	    return galadddate;
	}

	public void setGaladddate(Date galadddate)
	{
	    this.galadddate = galadddate;
	}
	//

	
}
