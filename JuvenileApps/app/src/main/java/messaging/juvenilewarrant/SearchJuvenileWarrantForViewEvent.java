/*
 * Created on August 31, 2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilewarrant;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * @author rcapestani
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 * 
 * 
 * 
 * 
 * Purpose of this event is to search for warrants in View 
 */
public class SearchJuvenileWarrantForViewEvent extends RequestEvent
{
    private String associateFirstName;

    private String associateLastName;
    
    private String firstName;
	
    private String lastName;
    
    private String officerId;
 
    private String originatorId;
    
    private Date searchDate1;
    
    private Date searchDate2;
	
    private String warrantNum;
    
    private String warrantOrigAgencyId;
    
    private String warrantStatusId;
    
    private String warrantTypeId;

   
    /**
     * @return Returns the associateFirstName.
     */
    public String getAssociateFirstName()
    {
        return associateFirstName;
    }

    /**
     * @return Returns the associateLastName.
     */
    public String getAssociateLastName()
    {
        return associateLastName;
    }

	/**
	 * @return Returns the Juvenile's firstName.
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @return Returns the Juvenile's lastName
	 */
	public String getLastName()
	{
		return lastName;
	}
    
    /**
     * @return Returns the officerId.
     */
    public String getOfficerId()
    {
        return officerId;
    }
    
    /**
	 * @return Returns the originatorUserId.
	 */
	public String getOriginatorId()
	{
		return originatorId;
	}
    	
    /**
	 * @return the searchDate1
	 */
	public Date getSearchDate1() {
		return searchDate1;
	}

	/**
	 * @return the searchDate2
	 */
	public Date getSearchDate2() {
		return searchDate2;
	}

	/**
    * @return Returns the warrantNum.
    */
    public String getWarrantNum()
    {
       return warrantNum;
    }
    
    /**
     * @return Returns the agencyId.
     */
    public String getWarrantOrigAgencyId()
    {
        return warrantOrigAgencyId;
    }

	/**
	 * @return the warrantStatusId
	 */
	public String getWarrantStatusId() {
		return warrantStatusId;
	}

	/**
	 * @return Returns the warrantTypeCode.
	 */
	public String getWarrantTypeId()
	{
		return warrantTypeId;
	}
 
    /**
     * @param associateFirstName
     * 		The associateFirstName to set.
     */
    public void setAssociateFirstName(String associateFirstName)
    {
        this.associateFirstName = associateFirstName;
    }
	
    /**
     * @param associateLastName
     * 		The associateLastName to set.
     */
    public void setAssociateLastName(String associateLastName)
    {
        this.associateLastName = associateLastName;
    }
    
    /**
	 * @param firstName
	 * 		The Juvenile's firstName to set.
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * @param lastName
	 * 		The Juvenile's lastName to set.
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

    /**
     * @param officerId The officerId to set.
     */
    public void setOfficerId(String officerId)
    {
        this.officerId = officerId;
    }

    /**
	 * @param originatorUserId
	 * 		The originatorUserId to set.
	 */
	public void setOriginatorId(String originatorId)
	{
		this.originatorId = originatorId;
	}

	/**
	 * @param searchDate1 the searchDate1 to set
	 */
	public void setSearchDate1(Date searchDate1) {
		this.searchDate1 = searchDate1;
	}

	/**
	 * @param searchDate2 the searchDate2 to set
	 */
	public void setSearchDate2(Date searchDate2) {
		this.searchDate2 = searchDate2;
	}

	/**
     * @param warrantNum
     * 		The warrantNum to set.
     */
    public void setWarrantNum(String warrantNum)
    {
        this.warrantNum = warrantNum;
    }

    /**
     * @param warrantOrigAgencyId
     *            The warrantOrigAgencyId to set.
     */
    public void setWarrantOrigAgencyId(String warrantOrigAgencyId)
    {
        this.warrantOrigAgencyId = warrantOrigAgencyId;
    }

	/**
	 * @param warrantStatusId the warrantStatusId to set
	 */
	public void setWarrantStatusId(String warrantStatusId) {
		this.warrantStatusId = warrantStatusId;
	}

	/**
	 * @param warrantTypeCode
	 * 		The warrantTypeCode to set
	 */
	public void setWarrantTypeId(String warrantTypeId)
	{
	    this.warrantTypeId = warrantTypeId;
	}
}