package pd.juvenile;

import java.util.Date;
import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class SsnViewLog extends PersistentObject
{
    private String 	ssnViewId;
    private String 	juvenileNum;
    private String 	viewSsnUser;
    private Date 	createDate;
   
    
    public String getJuvenileNum()
    {
	return juvenileNum;
    }
    
    public void setJuvenileNum(String juvenileNum)
    {
	if (this.juvenileNum == null
		|| !this.juvenileNum.equals(juvenileNum)) {
	    markModified();
	}
	this.juvenileNum = juvenileNum;
    }
    
    public String getViewSsnUser()
    {
	return viewSsnUser;
    }
    
    public void setViewSsnUser(String viewSsnUser)
    {
	if (this.viewSsnUser== null
		|| !this.viewSsnUser.equals(viewSsnUser)) {
	    markModified();
	}
	this.viewSsnUser = viewSsnUser;
    }
    
    public Date getCreateDate()
    {
	return createDate;
    }
    
    public void setCreateDate(Date createDate)
    {
	if (this.createDate== null
		|| this.createDate != createDate) {
	    markModified();
	}
	this.createDate= createDate;
    }
    
   
    
    public static Iterator findAll(IEvent event)
    {
	IHome home = new Home();
	return (Iterator) home.findAll(event, SsnViewLog.class);
    }
    
    public static Iterator findAll(String attrName, String attrValue)
    {
	IHome home = new Home();
	return (Iterator) home.findAll(attrName, attrValue, SsnViewLog.class);
    }
    
    public static SsnViewLog find (String OID) {
	IHome home = new Home();
	return (SsnViewLog) home.find(OID, SsnViewLog.class);
    }

    public String getSsnViewId()
    {
	return ssnViewId;
    }

    public void setSsnViewId(String ssnViewId)
    {
	this.ssnViewId = ssnViewId;
    }
    
    
}
