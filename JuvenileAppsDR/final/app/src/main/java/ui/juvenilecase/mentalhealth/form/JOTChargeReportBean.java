package ui.juvenilecase.mentalhealth.form;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import mojo.km.utilities.DateUtil;
import naming.PDConstants;
import naming.UIConstants;
import ui.common.CodeHelper;

/**
 * @author U_Gopinath
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class JOTChargeReportBean {
	
	private String juvenileName;	
	private String juvenileNum;
	private String raceId = "" ;
	private boolean multiracial = false;
	private boolean hispanic = false;
	private String gender = "" ;
	private String currentAge = "" ;
	private String dateOfBirth ;
	private boolean verifiedDOB = false ;
	private String sidNumber = "";
	private String petitionNum;
	private Date arrestDate;
	private String arrestTime;
	private String arrestingAgency;
	private String referralNum;
	private String summaryOF = "";
	
	private List jotCharges;
	private Collection summaryOfFacts;
	
	/**
	 * @return Returns the juvenile.
	 */
	public String getJuvenileName() {
		return juvenileName;
	}
	/**
	 * @param juvenile The juvenile to set.
	 */
	public void setJuvenileName(String name) {
		this.juvenileName = name;
	}
	
	 /**
     * @param string
     */
    public void setJuvenileNum(String string)
    {
        juvenileNum = string;
    }
    
    /**
     * @return
     */
    public String getJuvenileNum()
    {
        return juvenileNum;
    }
    
    /**
	 * @return race
	 */
	public String getRace( )
	{
		String description = "" ;
		if( raceId != null )
		{
			description = CodeHelper.getCodeRaceDescriptionByCode( CodeHelper.getJJSRaces( false ), raceId ) ;
		}
		
		return description ;
	}

	/**
	 * @return
	 */
	public String getRaceId( )
	{
		return raceId ;
	}
	
	/**
	 * @param string
	 */
	public void setRaceId( String string )
	{
		raceId = string ;
	}
	
	/**
	 * 
	 * @return The multiracial.
	 */
	public boolean isMultiracial( )
	{
		return multiracial ;
	}

	/**
	 * 
	 * @return The multiracial description.
	 */
	public String getMultiracialDescription( )
	{
		return ( PDConstants.YES.equals( multiracial ) ) ? UIConstants.YES : UIConstants.NO ;
	}
	
	/**
	 * 
	 * @param multiracial
	 *            The multiracial.
	 */
	public void setMultiracial( boolean multiracial )
	{
		this.multiracial = multiracial ;
	}
	
	/**
	 * 
	 * @return The hispanic.
	 */
	public boolean isHispanic( )
	{
		return hispanic ;
	}

	/**
	 * Returns the hispanic description.
	 * 
	 * @return The hispanic description.
	 */
	public String getHispanicDescription( )
	{
		return ( PDConstants.YES.equals( hispanic ) ) ? UIConstants.YES : UIConstants.NO ;
	}
	
	/**
	 * 
	 * @param hispanic
	 *            The hispanic.
	 */
	public void setHispanic( boolean hispanic )
	{
		this.hispanic = hispanic ;
	}
	
	
	/**
	 * @return
	 */
	public String getGender( )
	{
		return gender ;
	}
	
	/**
	 * @param string
	 */
	public void setGender( String string )
	{
		gender = string ;
	}
	
	/**
	 * @return currentAge
	 */
	public String getCurrentAge( )
	{
		return currentAge ;
	}
	
	/**
	 * @param aCurrentAge
	 */
	public void setCurrentAge( String aCurrentAge )
	{
		currentAge = aCurrentAge ;
	}
	
	/**
	 * 
	 * @return The date of birth.
	 */
	public String getDateOfBirth( )
	{
		return dateOfBirth ;
	}
	
	/**
	 * 
	 * @param string
	 *            The date of birth.
	 */
	public void setDateOfBirth( String string )
	{
		dateOfBirth = string ;
	}
	
	/**
	 * 
	 * @return The verified d o b.
	 */
	public boolean isVerifiedDOB( )
	{
		return verifiedDOB ;
	}
	
	/**
	 * 
	 * @return The verified d o b description.
	 */
	public String getVerifiedDOBDescription( )
	{
		return ( PDConstants.YES.equals( verifiedDOB ) ) ? UIConstants.YES : UIConstants.NO ;
	}
	
	/**
	 * 
	 * @param verifiedDOB
	 *            The verified d o b.
	 */
	public void setVerifiedDOB( boolean verifiedDOB )
	{
		this.verifiedDOB = verifiedDOB ;
	}
	
	public String getSidNumber() {
		return sidNumber;
	}
	public void setSidNumber(String sidNumber) {
		this.sidNumber = sidNumber;
	}
	
	public void setSummaryOfFacts(Collection facts)
	{
		summaryOfFacts = facts;
	}
	
	public Collection getSummaryOfFacts()
	{
		return summaryOfFacts;
	}
	
	/**
	 * @return the jotCharges
	 */
	public List getJotCharges() {
		return jotCharges;
	}

	/**
	 * @param jotCharges the jotCharges to set
	 */
	public void setJotCharges(List jotCharges) {
		this.jotCharges = jotCharges;
	}
	/** 
	 * @return petitionNum
	 */
	public String getPetitionNum()
	{
		return petitionNum;
	}
	
	/**
	 * @param string
	 */
	public void setPetitionNum(String string)
	{
		petitionNum = string;
	}
	/**
	 * @return cjisNum
	 */
	/**
	 * @return Returns the arrestDate.
	 */
	public String getArrestDate() {
		return DateUtil.dateToString(arrestDate, DateUtil.DATE_FMT_1);
	}
	/**
	 * @param arrestDate The arrestDate to set.
	 */
	public void setArrestDate(Date arrestDate) {
		this.arrestDate = arrestDate;
	}
	/**
	 * @return Returns the arrestTime.
	 */
	public String getArrestTime() {
		return arrestTime;
	}
	/**
	 * @param arrestTime The arrestTime to set.
	 */
	public void setArrestTime(String arrestTime) {
		this.arrestTime = arrestTime;
	}
	/**
     * @return
     */
    public String getReferralNum()
    {
        return referralNum;
    }
    /**
     * @param string
     */
    public void setReferralNum(String string)
    {
        referralNum = string;
    }
	/**
	 * @return the summaryOF
	 */
	public String getSummaryOF() {
		return summaryOF;
	}
	/**
	 * @param summaryOF the summaryOF to set
	 */
	public void setSummaryOF(String summaryOF) {
		this.summaryOF = summaryOF;
	}
	public String getArrestingAgency()
	{
	    return arrestingAgency;
	}
	public void setArrestingAgency(String arrestingAgency)
	{
	    this.arrestingAgency = arrestingAgency;
	}
}