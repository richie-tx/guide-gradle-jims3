package messaging.juvenilewarrant;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileWarrantsForViewEvent extends RequestEvent
{
    private String firstName;

    private String lastName;

    private String warrantNum;

    private String warrantStatus;

    private String warrantTypeId;

    private String officerId;
    
    private String originatorId;

    private String juvenileNum;

    private String petitionNum;

    private String referralNum;

    private String associateFirstName;

    private String associateLastName;
    
    private Date searchDate1;
    
    private Date searchDate2;
    
    private String warrantStatusId;


    /**
     * @return Returns the associateFirstName.
     */
    public String getAssociateFirstName()
    {
        return associateFirstName;
    }

    /**
     * @param associateFirstName
     *            The associateFirstName to set.
     */
    public void setAssociateFirstName(String associateFirstName)
    {
        this.associateFirstName = associateFirstName;
    }

    /**
     * @return Returns the associateLastName.
     */
    public String getAssociateLastName()
    {
        return associateLastName;
    }

    /**
     * @param associateLastName
     *            The associateLastName to set.
     */
    public void setAssociateLastName(String associateLastName)
    {
        this.associateLastName = associateLastName;
    }

    /**
     * @return Returns the firstName.
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * @param firstName
     *            The firstName to set.
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * @return Returns the juvenileNum.
     */
    public String getJuvenileNum()
    {
        return juvenileNum;
    }

    /**
     * @param juvenileNum
     *            The juvenileNum to set.
     */
    public void setJuvenileNum(String juvenileNum)
    {
        this.juvenileNum = juvenileNum;
    }

    /**
     * @return Returns the lastName.
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * @param lastName
     *            The lastName to set.
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * @return Returns the officer_Id.
     */
    public String getOfficerId()
    {
        return officerId;
    }
    
    /**
     * @param officerId The officerId to set.
     */
    public void setOfficerId(String officerId)
    {
        this.officerId = officerId;
    }
    
    /**
     * @return Returns the originatorId.
     */
    public String getOriginatorId()
    {
        return originatorId;
    }

    /**
     * @param originatorId
     *            The originatorId to set.
     */
    public void setOriginatorId(String originatorId)
    {
        this.originatorId = originatorId;
    }

    /**
     * @return Returns the petitionNum.
     */
    public String getPetitionNum()
    {
        return petitionNum;
    }

    /**
     * @param petitionNum
     *            The petitionNum to set.
     */
    public void setPetitionNum(String petitionNum)
    {
        this.petitionNum = petitionNum;
    }

    /**
     * @return Returns the referralNum.
     */
    public String getReferralNum()
    {
        return referralNum;
    }

    /**
     * @param referralNum
     *            The referralNum to set.
     */
    public void setReferralNum(String referralNum)
    {
        this.referralNum = referralNum;
    }

    /**
	 * @return the searchDate1
	 */
	public Date getSearchDate1() {
		return searchDate1;
	}

	/**
	 * @param searchDate1 the searchDate1 to set
	 */
	public void setSearchDate1(Date searchDate1) {
		this.searchDate1 = searchDate1;
	}

	/**
	 * @return the searchDate2
	 */
	public Date getSearchDate2() {
		return searchDate2;
	}

	/**
	 * @param searchDate2 the searchDate2 to set
	 */
	public void setSearchDate2(Date searchDate2) {
		this.searchDate2 = searchDate2;
	}

	/**
     * @return Returns the warrantNum.
     */
    public String getWarrantNum()
    {
        return warrantNum;
    }

    /**
     * @param warrantNum
     *            The warrantNum to set.
     */
    public void setWarrantNum(String warrantNum)
    {
        this.warrantNum = warrantNum;
    }

    /**
     * @return Returns the warrantStatus.
     */
    public String getWarrantStatus()
    {
        return warrantStatus;
    }

    /**
     * @param warrantStatus
     *            The warrantStatus to set.
     */
    public void setWarrantStatus(String warrantStatus)
    {
        this.warrantStatus = warrantStatus;
    }

    /**
	 * @return the warrantStatusId
	 */
	public String getWarrantStatusId() {
		return warrantStatusId;
	}

	/**
	 * @param warrantStatusId the warrantStatusId to set
	 */
	public void setWarrantStatusId(String warrantStatusId) {
		this.warrantStatusId = warrantStatusId;
	}

	/**
     * @return Returns the warrantTypeId.
     */
    public String getWarrantTypeId()
    {
        return warrantTypeId;
    }

    /**
     * @param warrantTypeId
     *            The warrantTypeId to set.
     */
    public void setWarrantTypeId(String warrantTypeId)
    {
        this.warrantTypeId = warrantTypeId;
    }
}