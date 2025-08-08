package messaging.juvenilecase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

//import ui.juvenilecase.facility.action.UpdateJuvenileTraitstoFormerEvent;

import mojo.km.messaging.RequestEvent;

public class UpdateJuvenileTraitstoFormerEvent extends RequestEvent 
{
    private String traitType;
    private String juvenileNum;
    private String OID;
    //private String action;
    private String originaladmitOID;
    

    public UpdateJuvenileTraitstoFormerEvent() 
    {
     
    }
    
    public String getTraitType()
    {
        return traitType;
    }


    public void setTraitType(String traitType)
    {
        this.traitType = traitType;
    }
    public String getJuvenileNum()
    {
        return juvenileNum;
    }

    public void setJuvenileNum(String juvenileNum)
    {
        this.juvenileNum = juvenileNum;
    }
    public String getOID()
    {
        return OID;
    }

    public void setOID(String oID)
    {
        OID = oID;
    }
    /*public String getAction()
    {
        return action;
    }

    public void setAction(String action)
    {
        this.action = action;
    }*/
    public String getOriginaladmitOID()
    {
        return originaladmitOID;
    }

    public void setOriginaladmitOID(String originaladmitOID)
    {
        this.originaladmitOID = originaladmitOID;
    }
    
 }