package pd.supervision.administerserviceprovider;

import java.util.Date;
import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class ContactSourceFund extends PersistentObject
{
    	private String profileId;
	
	private String fundSource;
	
	private Date fundStartDate;
	
	private Date fundEndDate;
	
	private String fundStatus;
	
	private Date fundEntryDate;
	
	private String contactSourceFundId;
	
	private String comments;
	
	public ContactSourceFund(){}

	
	public String getProfileId()
	{
	    return profileId;
	}

	public void setProfileId(String profileId)
	{
	    this.profileId = profileId;
	}

	public String getFundSource()
	{
	    return fundSource;
	}

	public void setFundSource(String fundSource)
	{
	    this.fundSource = fundSource;
	}

	public Date getFundStartDate()
	{
	    return fundStartDate;
	}

	public void setFundStartDate(Date fundStartDate)
	{
	    this.fundStartDate = fundStartDate;
	}

	public Date getFundEndDate()
	{
	    return fundEndDate;
	}

	public void setFundEndDate(Date fundEndDate)
	{
	    this.fundEndDate = fundEndDate;
	}

	public String getFundStatus()
	{
	    return fundStatus;
	}

	public void setFundStatus(String fundStatus)
	{
	    this.fundStatus = fundStatus;
	}

	public Date getFundEntryDate()
	{
	    return fundEntryDate;
	}

	public void setFundEntryDate(Date fundEntryDate)
	{
	    this.fundEntryDate = fundEntryDate;
	}

	public String getContactSourceFundId()
	{
	    return contactSourceFundId;
	}

	public void setContactSourceFundId(String contactSourceFundId)
	{
	    this.contactSourceFundId = contactSourceFundId;
	}

	public String getComments()
	{
	    return comments;
	}

	public void setComments(String comments)
	{
	    this.comments = comments;
	}
	
	public static Iterator findAll(String attrName, String attrValue) {
		Iterator contactSourceFund = null;
		IHome home = new Home();
		contactSourceFund = home.findAll(attrName, attrValue, ContactSourceFund.class);
		return contactSourceFund;
	}
	
	public static ContactSourceFund find(String contactSourceFundId) {
	    	ContactSourceFund contactSourceFund = null;
		IHome home = new Home();
		contactSourceFund = (ContactSourceFund) home.find(contactSourceFundId, ContactSourceFund.class);
		return contactSourceFund;
	}
}
