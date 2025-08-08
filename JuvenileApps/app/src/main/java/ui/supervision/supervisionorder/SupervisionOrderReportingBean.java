/*
 * Created on Apr 10, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.supervision.supervisionorder;

import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;

import messaging.domintf.contact.party.IParty;

/**
 * @author kmurthy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SupervisionOrderReportingBean
{
	private String cause			= "";
	private String cloName			= "";
	private String courtCategory = "";
    
	private Collection conditions = null;
    private Collection conditionsAdded = null;
    private Collection conditionsRemoved = null;
    private Collection conditionsUpdated = null;
    private String comments = "";
	private String court			= "";       
	private String sentencingCourt  = "";
	private String dopDay			= "";   
	private String dopDayOrdinal = "";
	private String dopMonth = "";
	private String dopYear = "";
    private String fineAmountTotal = "";
                    
	private String origJudgeFLName = "";
	private String signedByFLName = "";
	
	private String name				= "";
	private String printedName = "";
	private String offense = "";
    private String offenseLevel = "";
	private String orderTitle		= "";
    private String orderTitleId       = "";
    
	private IParty partyDetails;
	
    private String partyName = "";
	private String plea				= "";                        
    
	private String previousOrder_Id = "";
	private boolean printSpanish = false;
    private String probationEndDate = "";
    private String probationEndDay = "";
    private String probationEndMonth = "";
    private String probationEndYear = "";
    
    private String probationStartDate = "";
    private String signedByDefendantDate = "";
    private String signedByJudgeDate = "";
    private String signedDate = "";

    private String signedDay = "";
    private String signedMonth = "";
    private String signedYear = "";
	private String spn				= "";
	private String summaryofChanges = "";
	private String selectedOrderId;

    
    private String supervisionLengthNum = "";
    private Date supOrderRevisionDate = null;
    private String termHCJ = "";
    private String termSTJ = "";

	private String versionNumber 	= "";
	private String versionType 		= "";

	// juvenile variables for Determinate Sentencing
	
	private String juvCourt;
    private String juvDay = "";
    private String juvMonth = "";
    private String juvYear = "";
	private String juvTerm = "";
	private String juvTermTimePeriod = "";
	
    /**
     * @return Returns the CurrentAddressStreetName2
     */
    public String getAddressAptNumber() {
        if(partyDetails != null)
            return partyDetails.getCurrentAddressStreetName2();
        else
            return null;

    }
    
    /**
     * @return Returns the CurrentAddressCity
     */
    public String getAddressCity() {
        if(partyDetails != null)
            return partyDetails.getCurrentAddressCity();
        else
            return null;

    }
    
    /**
     * @return Returns the CurrentAddressStateId
     */
    public String getAddressState() {
        if(partyDetails != null)
            return partyDetails.getCurrentAddressStateId();
        else
            return null;

    }
    
    /**
     * @return Returns the CurrentAddressStreetNum
     */
    public String getAddressStreet() {
        if(partyDetails != null)
            return partyDetails.getCurrentAddressStreetNum();
        else
            return null;

    }
    
    /**
     * @return Returns the CurrentAddressStreetName.
     */
    public String getAddressStreetName() {
        if(partyDetails != null)
            return partyDetails.getCurrentAddressStreetName();
        else
            return null;

    }
    
    /**
     * @return Returns the CurrentAddressZipCode
     */
    public String getAddressZip() {
        if(partyDetails != null)
            return partyDetails.getCurrentAddressZipCode();
        else
            return null;

    }
    
    /**
	 * @return
	 */
	public String getCause()
	{
		return cause;
	}

	/**
	 * @return
	 */
	public String getCloName()
	{
		return cloName;
	}
    
	/**
     * @return Returns the courtCategory.
     */
    public String getCourtCategory()
    {
        return courtCategory;
    }

    /**
     * @return Returns the EyeColorId
     */
    public String getColorofEye() {
        if(partyDetails != null)
            return partyDetails.getEyeColorId();
        else
            return null;

    }
    
    /**
     * @return Returns the HairColorId
     */
    public String getColorofHair() {
        if(partyDetails != null)
            return partyDetails.getHairColorId();
        else
            return null;

    }

	/**
	 * @return
	 */
	public Collection getConditions()
	{
		return conditions;
	}
    

    
	/**
	 * @return Returns the conditionsAdded.
	 */
	public Collection getConditionsAdded() {
		return conditionsAdded;
	}
	/**
	 * @return Returns the conditionsRemoved.
	 */
	public Collection getConditionsRemoved() {
		return conditionsRemoved;
	}
	/**
	 * @return Returns the conditionsUpdated.
	 */
	public Collection getConditionsUpdated() {
		return conditionsUpdated;
	}

		/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @return
	 */
	public String getCourt()
	{
		return court;
	}
    
    
    /**
     * @return Returns the DateOfBirth
     */
    public String getDateOfBirth() {
        if(partyDetails != null)
            return DateFormat.getDateInstance(DateFormat.LONG).format(partyDetails.getDateOfBirth());
        else
            return null;

    }
    
    /**
     * @return Returns the PhoneNum
     */
    public String getDefPhoneNumber() {
        if(partyDetails != null)
            return partyDetails.getPhoneNum();
        else
            return null;

    }

	/**
	 * @return
	 */
	public String getDopDay()
	{
		return dopDay;
	}
    
	/**
	 * @return
	 */
	public String getDopDayOrdinal()
	{
		return dopDayOrdinal;
	}
	
	/**
	 * @return
	 */
	public String getDopMonth()
	{
		return dopMonth;
	}
	
	/**
	 * @return
	 */
	public String getDopYear()
	{
		return dopYear;
	}
	
    /**
     * @return Returns the DriversLicenseNum
     */
    public String getDriversLicense() {
        if(partyDetails != null)
            return partyDetails.getDriversLicenseNum();
        else
            return null;

    }
    
    /**
     * @return Returns the DriversLicenseStateId
     */
    public String getDriversLicenseState() {
        if(partyDetails != null)
            return partyDetails.getDriversLicenseStateId();
        else
            return null;

    }
    
    /**
     * @return Returns the EmployerAddressStateId
     */
    public String geteEmployerAddressState() {
        if(partyDetails != null)
            return partyDetails.getEmployerAddressStateId();
        else
            return null;

    }
    
    /**
     * @return Returns the EmployerAddressCity
     */
    public String getEmployerAddressCity() {
        if(partyDetails != null)
            return partyDetails.getEmployerAddressCity();
        else
            return null;

    }
    
    /**
     * @return Returns the EmployerAddressZip
     */
    public String getEmployerAddressZip() {
        if(partyDetails != null)
            return partyDetails.getEmployerAddressZipCode();
        else
            return null;

    }
    
    /**
     * @return Returns the EmployerName
     */
    public String getEmployerName() {
        if(partyDetails != null)
            return partyDetails.getEmployerName();
        else
            return null;

    }
    
    /**
     * @return Returns the EmployerPhoneNumber.
     */
    public String getEmployerPhoneNumber() {
        if(partyDetails != null)
        	return partyDetails.getEmployerPhoneNum();
        else
            return null;
    }
    
    /**
     * @return Returns the EmployerAddressStreetNum
     */
    public String getEmployerStreet() {
        if(partyDetails != null)
            return partyDetails.getEmployerAddressStreetNum();
        else
            return null;

    }
    
    /**
     * @return Returns the EmployerAddressStreetName
     */
    public String getEmployerStreetName() {
        if(partyDetails != null)
            return partyDetails.getEmployerAddressStreetName();
        else
            return null;

    }

	/**
	 * @return
	 */
	public String getFineAmountTotal()
	{
		return fineAmountTotal;
	}
    
    /**
     * @return Returns the Height
     */
    public String getHeight() {
        if(partyDetails != null)
            return partyDetails.getHeight();
        else
            return null;

    }

	/**
	 *  @return
	 */
	
	public String getOrigJudgeFLName()
	{
		return origJudgeFLName;
	}

	/**
	 *  @return
	 */
	
	public String getSignedByFLName()
	{
		return signedByFLName;
	}
	
	/**
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return
	 */
	public String getPrintedName()
	{
		return printedName;
	}
	
	/**
	 * @return
	 */
	public String getOffense()
	{
		return offense;
	}
	/**
	 * @return Returns the offenseLevel.
	 */
	public String getOffenseLevel() {
		return offenseLevel;
	}

	/**
	 * @return
	 */
	public String getOrderTitle()
	{
		return orderTitle;
	}
	/**
	 * @return Returns the orderTitleId.
	 */
	public String getOrderTitleId() {
		return orderTitleId;
	}
    /**
     * @return Returns the partyDetails.
     */
    public IParty getPartyDetails() {
        return partyDetails;
    }    
    /**
     * @return Returns the partyName.
     */
    public String getPartyName()
    {
        return partyName;
    }
    
    /**
     * @return Returns the PlaceOfBirth
     */
    public String getPlaceOfBirth() {
        if(partyDetails != null)
            return partyDetails.getPlaceOfBirth();
        else
            return null;

    }

	/**
	 * @return
	 */
	public String getPlea()
	{
		return plea;
	}
	
	/**
	 * @return Returns the previousOrder_Id.
	 */
	public String getPreviousOrder_Id() {
		return previousOrder_Id;
	}
	
	/**
	 * @return Returns the probationEndDate.
	 */
	public String getProbationEndDate() {
		return probationEndDate;
	}
	/**
	 * @return Returns the probationEndDay.
	 */
	public String getProbationEndDay() {
		return probationEndDay;
	}
	/**
	 * @return Returns the probationEndMonth.
	 */
	public String getProbationEndMonth() {
		return probationEndMonth;
	}
	/**
	 * @return Returns the probationEndYear.
	 */
	public String getProbationEndYear() {
		return probationEndYear;
	}

	/**
	 * @return
	 */
	public String getProbationStartDate()
	{
		return probationStartDate;
	}
	/**
	 * @return Returns the signedByDefendantDate.
	 */
	public String getSignedByDefendantDate() {
		return signedByDefendantDate;
	}
	
	public String getSignedByJudgeDate() {
		return signedByJudgeDate;
	}

    /**
     * @return Returns the signedDate.
     */
    public String getSignedDate() {
        return signedDate;
    }
	/**
	 * @return Returns the signedDay.
	 */
	public String getSignedDay() {
		return signedDay;
	}
	/**
	 * @return Returns the signedMonth.
	 */
	public String getSignedMonth() {
		return signedMonth;
	}
	/**
	 * @return Returns the signedYear.
	 */
	public String getSignedYear() {
		return signedYear;
	}

	/**
	 * @return
	 */
	public String getSpn()
	{
		return spn;
	}

	/**
	 * @return
	 */
	public String getSummaryofChanges()
	{
		return summaryofChanges;
	}

	/**
	 * @return
	 */
	public String getSupervisionLengthNum()
	{
		return supervisionLengthNum;
	}

	/**
	 * @return Returns the supOrderRevisionDate.
	 */
	public Date getSupOrderRevisionDate() {
		return supOrderRevisionDate;
	}

	/**
	 * @return
	 */
	public String getTermHCJ()
	{
		return termHCJ;
	}

	/**
	 * @return
	 */
	public String getTermSTJ()
	{
		return termSTJ;
	}

	/**
	 * @return
	 */
	public String getVersionNumber()
	{
		return versionNumber;
	}

	/**
	 * @return
	 */
	public String getVersionType()
	{
		return versionType;
	}
    
	/**
	 * @return the juvCourt
	 */
	public String getJuvCourt() {
		return juvCourt;
	}

	/**
	 * @return the juvDay
	 */
	public String getJuvDay() {
		return juvDay;
	}

	/**
	 * @return the juvMonth
	 */
	public String getJuvMonth() {
		return juvMonth;
	}

	/**
	 * @return the juvYear
	 */
	public String getJuvYear() {
		return juvYear;
	}

	/**
	 * @return the juvTerm
	 */
	public String getJuvTerm() {
		return juvTerm;
	}

	/**
	 * @return the juvTermTimePeriod
	 */
	public String getJuvTermTimePeriod() {
		return juvTermTimePeriod;
	}

	/**
     * @return Returns the Weight
     */
    public String getWeight() {
        if(partyDetails != null)
            return partyDetails.getWeight();
        else
            return null;

    }

    
    /**
     * @return Returns the printSpanish.
     */
    public boolean isPrintSpanish() {
        return printSpanish;
    }

	/**
	 * @param string
	 */
	public void setCause(String string)
	{
		cause = string;
	}

	/**
	 * @param string
	 */
	public void setCloName(String string)
	{
		cloName = string;
	}

	/**
	 * @param collection
	 */
	public void setConditions(Collection collection)
	{
		conditions = collection;
	}
	/**
	 * @param conditionsAdded The conditionsAdded to set.
	 */
	public void setConditionsAdded(Collection conditionsAdded) {
		this.conditionsAdded = conditionsAdded;
	}
	/**
	 * @param conditionsRemoved The conditionsRemoved to set.
	 */
	public void setConditionsRemoved(Collection conditionsRemoved) {
		this.conditionsRemoved = conditionsRemoved;
	}
	/**
	 * @param conditionsUpdated The conditionsUpdated to set.
	 */
	public void setConditionsUpdated(Collection conditionsUpdated) {
		this.conditionsUpdated = conditionsUpdated;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @param string
	 */
	public void setCourt(String string)
	{
		court = string;
	}

	/**
	 * @param string
	 */
	public void setDopDay(String string)
	{
		dopDay = string;
	}

	/**
	 * @param string
	 */
	public void setDopDayOrdinal(String string)
	{
		dopDayOrdinal = string;
	}
	
	/**
	 * @param string
	 */
	public void setDopMonth(String string)
	{
		dopMonth = string;
	}
	
	/**
	 * @param string
	 */
	public void setDopYear(String string)
	{
		dopYear = string;
	}
	
	/**
	 * @param string
	 */
	public void setFineAmountTotal(String string)
	{
		fineAmountTotal = string;
	}

	/**
	 * @param string
	 */
	public void setOrigJudgeFLName(String string)
	{
		
		
		origJudgeFLName = string;
	}
	
	/**
	 * @param string
	 */
	public void setSignedByFLName(String string)
	{
		signedByFLName = string;
	}
	
	/**
	 * @param string
	 */
	public void setName(String string)
	{
		name = string;
	}

	/**
	 * @param string
	 */
	public void setPrintedName(String string)
	{
		printedName = string;
	}
	
	/**
	 * @param string
	 */
	public void setOffense(String string)
	{
		offense = string;
	}
	/**
	 * @param offenseLevel The offenseLevel to set.
	 */
	public void setOffenseLevel(String offenseLevel) {
		this.offenseLevel = offenseLevel;
	}

	/**
	 * @param string
	 */
	public void setOrderTitle(String string)
	{
		orderTitle = string;
	}
	/**
	 * @param orderTitleId The orderTitleId to set.
	 */
	public void setOrderTitleId(String orderTitleId) {
		this.orderTitleId = orderTitleId;
	}
	/**
	 * @param partyData
	 */
	public void setPartyDetails(IParty partyData) {
		// TODO Auto-generated method stub
		partyDetails =  partyData;
	}
    /**
     * @param partyName The partyName to set.
     */
    public void setPartyName(String partyName)
    {
        this.partyName = partyName;
    }

	/**
	 * @param string
	 */
	public void setPlea(String string)
	{
		plea = string;
	}
	
	/**
	 * @return Returns the probationEndDate.
	 */
	public void setPreviousOrder_Id(String previousOrder_Id) {
		this.previousOrder_Id = previousOrder_Id;
	}
	
    /**
     * @param printSpanish The printSpanish to set.
     */
    public void setPrintSpanish(boolean printSpanish) {
        this.printSpanish = printSpanish;
    }
	/**
	 * @param probationEndDate The probationEndDate to set.
	 */
	public void setProbationEndDate(String probationEndDate) {
		this.probationEndDate = probationEndDate;
	}
	/**
	 * @param probationEndDay The probationEndDay to set.
	 */
	public void setProbationEndDay(String probationEndDay) {
		this.probationEndDay = probationEndDay;
	}
	/**
	 * @param probationEndMonth The probationEndMonth to set.
	 */
	public void setProbationEndMonth(String probationEndMonth) {
		this.probationEndMonth = probationEndMonth;
	}
	/**
	 * @param probationEndYear The probationEndYear to set.
	 */
	public void setProbationEndYear(String probationEndYear) {
		this.probationEndYear = probationEndYear;
	}

	/**
	 * @param string
	 */
	public void setProbationStartDate(String string)
	{
		probationStartDate = string;
	}
	/**
	 * @param signedByDefendantDate The signedByDefendantDate to set.
	 */
	public void setSignedByDefendantDate(String signedByDefendantDate) {
		this.signedByDefendantDate = signedByDefendantDate;
	}
	
	public void setSignedByJudgeDate(String signedByJudgeDate) {
		this.signedByJudgeDate = signedByJudgeDate;
	}

    /**
     * @param signedDate The signedDate to set.
     */
    public void setSignedDate(String signedDate) {
        this.signedDate = signedDate;
    }
	/**
	 * @param signedDay The signedDay to set.
	 */
	public void setSignedDay(String probationStartDay) {
		this.signedDay = probationStartDay;
	}
	/**
	 * @param signedMonth The signedMonth to set.
	 */
	public void setSignedMonth(String probationStartMonth) {
		this.signedMonth = probationStartMonth;
	}
	/**
	 * @param signedYear The signedYear to set.
	 */
	public void setSignedYear(String probationStartYear) {
		this.signedYear = probationStartYear;
	}


	/**
	 * @param string
	 */
	public void setSpn(String string)
	{
		spn = string;
	}

	/**
	 * @param string
	 */
	public void setSummaryofChanges(String string)
	{
		summaryofChanges = string;
	}

	/**
	 * @param string
	 */
	public void setSupervisionLengthNum(String string)
	{
		supervisionLengthNum = string;
	}
	/**
	 * @param supOrderRevisionDate The supOrderRevisionDate to set.
	 */
	public void setSupOrderRevisionDate(Date supOrderRevisionDate) {
		this.supOrderRevisionDate = supOrderRevisionDate;
	}

	/**
	 * @param string
	 */
	public void setTermHCJ(String string)
	{
		termHCJ = string;
	}

	/**
	 * @param string
	 */
	public void setTermSTJ(String string)
	{
		termSTJ = string;
	}

	/**
	 * @param string
	 */
	public void setVersionNumber(String string)
	{
		versionNumber = string;
	}

	/**
	 * @param string
	 */
	public void setVersionType(String string)
	{
		versionType = string;
	}
	
	/**
	 * @param juvCourt the juvCourt to set
	 */
	public void setJuvCourt(String juvCourt) {
		this.juvCourt = juvCourt;
	}

	/**
	 * @param juvDay the juvDay to set
	 */
	public void setJuvDay(String juvDay) {
		this.juvDay = juvDay;
	}

    /**
	 * @param juvMonth the juvMonth to set
	 */
	public void setJuvMonth(String juvMonth) {
		this.juvMonth = juvMonth;
	}
	
	/**
	 * @param juvYear the juvYear to set
	 */
	public void setJuvYear(String juvYear) {
		this.juvYear = juvYear;
	}
	
	/**
	 * @param juvTerm the juvTerm to set
	 */
	public void setJuvTerm(String juvTerm) {
		this.juvTerm = juvTerm;
	}
	
	/**
	 * @param juvTermTimePeriod the juvTermTimePeriod to set
	 */
	public void setJuvTermTimePeriod(String juvTermTimePeriod) {
		this.juvTermTimePeriod = juvTermTimePeriod;
	}

    /**
     * @param courtCategory The courtCategory to set.
     */
    public void setCourtCategory(String courtCategory)
    {
        this.courtCategory = courtCategory;
    }

	public String getSelectedOrderId() {
		return selectedOrderId;
	}

	public void setSelectedOrderId(String selectedOrderId) {
		this.selectedOrderId = selectedOrderId;
	}
	public String getSentencingCourt() {
		return sentencingCourt;
	}

	public void setSentencingCourt(String sentencingCourt) {
		this.sentencingCourt = sentencingCourt;
	}

}
