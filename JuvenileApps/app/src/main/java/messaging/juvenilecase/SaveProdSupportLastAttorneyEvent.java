//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\SaveProdSupportLastAttorneyEvent.java

package messaging.juvenilecase;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class SaveProdSupportLastAttorneyEvent extends RequestEvent 
{
    public String galBarNum;	
    public String galName;;
    public String atyBarNum;	 
    public String atyName;
    public String juvenileNum;
    public String attConnect;
    public String lastAttorneyID;
    private String jjclcourtId;
    private String jjcldetentionId;
    
    



public SaveProdSupportLastAttorneyEvent() 
{

}


public String getGalBarNum()
{
    return galBarNum;
}


public void setGalBarNum(String galBarNum)
{
    this.galBarNum = galBarNum;
}


public String getGalName()
{
    return galName;
}


public void setGalName(String galName)
{
    this.galName = galName;
}


public String getAtyBarNum()
{
    return atyBarNum;
}


public void setAtyBarNum(String atyBarNum)
{
    this.atyBarNum = atyBarNum;
}


public String getAtyName()
{
    return atyName;
}


public void setAtyName(String atyName)
{
    this.atyName = atyName;
}


public String getJuvenileNum()
{
    return juvenileNum;
}


public void setJuvenileNum(String juvenileNum)
{
    this.juvenileNum = juvenileNum;
}


public String getAttConnect()
{
    return attConnect;
}


public void setAttConnect(String attConnect)
{
    this.attConnect = attConnect;
}


public String getLastAttorneyID()
{
    return lastAttorneyID;
}


public void setLastAttorneyID(String lastAttorneyID)
{
    this.lastAttorneyID = lastAttorneyID;
}


public String getJjclcourtId()
{
    return jjclcourtId;
}


public void setJjclcourtId(String jjclcourtId)
{
    this.jjclcourtId = jjclcourtId;
}


public String getJjcldetentionId()
{
    return jjcldetentionId;
}


public void setJjcldetentionId(String jjcldetentionId)
{
    this.jjcldetentionId = jjcldetentionId;
}




	
}
