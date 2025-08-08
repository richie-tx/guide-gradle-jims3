package pd.juvenilecase;

import java.util.Iterator;


import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class JJSCorespondent extends PersistentObject
{
    String OID;
    String juvenileNum;
    String referralNum;
    
    
    public String getJuvenileNum()
    {
	fetch();
        return juvenileNum;
    }
    public void setJuvenileNum(String juvenileNum)
    {
	if (this.juvenileNum == null 
		|| !this.juvenileNum.equals(juvenileNum)){
	    markModified();
	}
        this.juvenileNum = juvenileNum;
    }
    public String getReferralNum()
    {
	fetch();
        return referralNum;
    }
    public void setReferralNum(String referralNum)
    {
	if (this.referralNum == null 
		|| !this.referralNum.equals(referralNum))
        this.referralNum = referralNum;
    }
    
    static public Iterator findAll(IEvent event) {
	IHome home = new Home();
	return home.findAll(event, JJSCorespondent.class);
    }
}
