/**
 * Created on Aug 23 2018
 * pd object for the new table JUV_REFERRAL_FAMILY
 * which will hold the M204 data from JJS FAMILY table 
 * only for Juveniles Who doesn't exist in JIMS2.JCJUVFAMMEMBERS 
 */
package pd.juvenilecase.family;

import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

/**
 * @author NEMathew
 *
 */
public class JuvReferralFamily extends mojo.km.persistence.PersistentObject
{
    private String juvenileNum;
    private String fathersAddress;
    private String fathersName;
    private String fathersPhone;
    private String mothersAddress;
    private String mothersName;
    private String mothersPhone;
    private String otherAddress;
    private String otherName;
    private String OtherPhone;
    
    
    public JuvReferralFamily()
    {
	// TODO Auto-generated constructor stub
    }
    public static Iterator findAll(String attributeName, String attributeValue)
    {
        IHome home = new Home();
       Iterator juvReferralFamily = home.findAll(attributeName, attributeValue, JuvReferralFamily.class);
       return juvReferralFamily;
   }
    public String getJuvenileNum()
    {
	return juvenileNum;
    }
    public void setJuvenileNum(String juvenileNum)
    {
	this.juvenileNum = juvenileNum;
    }
    public String getFathersAddress()
    {
	return fathersAddress;
    }
    public void setFathersAddress(String fathersAddress)
    {
	this.fathersAddress = fathersAddress;
    }
    public String getFathersName()
    {
	return fathersName;
    }
    public void setFathersName(String fathersName)
    {
	this.fathersName = fathersName;
    }
    public String getFathersPhone()
    {
	return fathersPhone;
    }
    public void setFathersPhone(String fathersPhone)
    {
	this.fathersPhone = fathersPhone;
    }
    public String getMothersAddress()
    {
	return mothersAddress;
    }
    public void setMothersAddress(String mothersAddress)
    {
	this.mothersAddress = mothersAddress;
    }
    public String getMothersName()
    {
	return mothersName;
    }
    public void setMothersName(String mothersName)
    {
	this.mothersName = mothersName;
    }
    public String getMothersPhone()
    {
	return mothersPhone;
    }
    public void setMothersPhone(String mothersPhone)
    {
	this.mothersPhone = mothersPhone;
    }
    public String getOtherAddress()
    {
	return otherAddress;
    }
    public void setOtherAddress(String otherAddress)
    {
	this.otherAddress = otherAddress;
    }
    public String getOtherName()
    {
	return otherName;
    }
    public void setOtherName(String otherName)
    {
	this.otherName = otherName;
    }
    public String getOtherPhone()
    {
	return OtherPhone;
    }
    public void setOtherPhone(String otherPhone)
    {
	OtherPhone = otherPhone;
    }

}
