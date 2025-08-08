package pd.codetable.criminal;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;

import messaging.codetable.reply.ICode;
import messaging.juvenilecase.reply.JuvenileCasefileOffenseCodeResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * 
 * @author dgibler
 JuvenileOffenseCode entity
 */
public class JuvenileOffenseCode extends PersistentObject implements ICode
{
	private String category; //2. AND Offense Catagory = CF, F1, F2, or F3Rspswd*4
	private String citationCode;
	private String degree;
	private String dpsOffenseCode;
	private Date lcDateTime;
	private String lcUser;
	private String level;
	private String longDescription;
	private String numericCode;
	private String offenseCode;
	private String offenseCategory; 
	private String citationSource;
	private String severity; //3. or Severity code = 06, 07, 08, or 09, then Violent Felony = Yes
	private String reportGroup;
	private String shortDescription;
	private String inactiveInd;
	private String severitySubtype; //1. Severity Subtype indicator =V 
	private String severityType;
	private String discontCode; //U.S 58355
	private String ageRestrict;
	private String aggravated;
	private String weapons;
	private String ncicCode;
	private Date discontinuedDate;

	/**
	 * 
	 * @roseuid 41ACCAE60161
	 */
	public JuvenileOffenseCode()
	{
	}

	/**
	 * Access method for the offenseCode property.
	 * @return the current value of the offenseCode property
	 */

	public String getOffenseCode()
	{
		fetch();
		return offenseCode;
	}
	/**
	 * Sets the value of the offenseCode property.
	 * @param aOffenseCodeId the new value of the offenseCode property
	 */

	public void setOffenseCode(String aOffenseCodeId)
	{
		if (this.offenseCode == null || !this.offenseCode.equals(aOffenseCodeId))
		{
			markModified();
		}
		offenseCode = aOffenseCodeId;
	}
	/**
	 * Access method for the category property.
	 * @return the current value of the category property
	 */
	public String getCategory()
	{
		fetch();
		return category;
	}

	/**
	 * Sets the value of the category property.
	 * @param aCategory the new value of the category property
	 */

	public void setCategory(String aCategory)
	{
		if (this.category == null || !this.category.equals(aCategory))
		{
			markModified();
		}
		category = aCategory;
	}
	/**
	 * Access method for the citationCode property.
	 * @return the current value of the citationCode property
	 */
	public String getCitationCode()
	{
		fetch();
		return citationCode;
	}

	/**
	 * Sets the value of the citationCode property.
	 * @param aCitationCode the new value of the citationCode property
	 */

	public void setCitationCode(String aCitationCode)
	{
		if (this.citationCode == null || !this.citationCode.equals(aCitationCode))
		{
			markModified();
		}
		citationCode = aCitationCode;
	}
	/**
	 * 
	 * @return Returns the citationSource.
	 */
	public String getCitationSource()
	{
		fetch();
		return citationSource;
	}

	/**
	 * 
	 * @param citationSource The citationSource to set.
	 */
	public void setCitationSource(String citationSource)
	{
		if (this.citationSource == null || !this.citationSource.equals(citationSource))
		{
			markModified();
		}
		this.citationSource = citationSource;
	}

	/**
	 * Access method for the dpsOffenseCode property.
	 * @return the current value of the dpsOffenseCode property
	 */
	public String getDpsOffenseCode()
	{
		fetch();
		return dpsOffenseCode;
	}

	/**
	 * Sets the value of the dpsOffenseCode property.
	 * @param aDpsOffenseCode the new value of the dpsOffenseCode property
	 */

	public void setDpsOffenseCode(String aDpsOffenseCode)
	{
		if (this.dpsOffenseCode == null || !this.dpsOffenseCode.equals(aDpsOffenseCode))
		{
			markModified();
		}
		dpsOffenseCode = aDpsOffenseCode;
	}
	/**
	 * Access method for the lcDateTime property.
	 * @return the current value of the lcDateTime property
	 */
	public Date getLcDateTime()
	{
		fetch();
		return lcDateTime;
	}

	/**
	 * Sets the value of the lcDateTime property.
	 * @param aLcDateTime the new value of the lcDateTime property
	 */

	public void setLcDateTime(Date aLcDateTime)
	{
		if (this.lcDateTime == null || !this.lcDateTime.equals(aLcDateTime))
		{
			markModified();
		}
		lcDateTime = aLcDateTime;
	}
	/**
	 * Access method for the lcUser property.
	 * @return the current value of the lcUser property
	 */
	public String getLcUser()
	{
		fetch();
		return lcUser;
	}

	/**
	 * Sets the value of the lcUser property.
	 * @param aLcUser the new value of the lcUser property
	 */

	public void setLcUser(String aLcUser)
	{
		if (this.lcUser == null || !this.lcUser.equals(aLcUser))
		{
			markModified();
		}
		lcUser = aLcUser;
	}
	/**
	 * Access method for the numericCode property.
	 * @return the current value of the numericCode property
	 */
	public String getNumericCode()
	{
		fetch();
		return numericCode;
	}

	/**
	 * Sets the value of the numericCode property.
	 * @param aNumericCode the new value of the numericCode property
	 */

	public void setNumericCode(String aNumericCode)
	{
		if (this.numericCode == null || !this.numericCode.equals(aNumericCode))
		{
			markModified();
		}
		numericCode = aNumericCode;
	}
	/**
	 * Access method for the reportGroup property.
	 * @return the current value of the reportGroup property
	 */
	public String getReportGroup()
	{
		fetch();
		return reportGroup;
	}

	/**
	 * Sets the value of the reportGroup property.
	 * @param aReportGroup the new value of the reportGroup property
	 */

	public void setReportGroup(String aReportGroup)
	{
		if (this.reportGroup == null || !this.reportGroup.equals(aReportGroup))
		{
			markModified();
		}
		reportGroup = aReportGroup;
	}
	/**
	 * Access method for the severity property.
	 * @return the current value of the severity property
	 */
	public String getSeverity()
	{
		fetch();
		return severity;
	}

	/**
	 * Sets the value of the severity property.
	 * @param aSeverity the new value of the severity property
	 */

	public void setSeverity(String aSeverity)
	{
		if (this.severity == null || !this.severity.equals(aSeverity))
		{
			markModified();
		}
		severity = aSeverity;
	}
	/**
	 * Access method for the shortDescription property.
	 * @return the current value of the shortDescription property
	 */
	public String getShortDescription()
	{
		fetch();
		return shortDescription;
	}

	/**
	 * Sets the value of the shortDescription property.
	 * @param aShortDescription the new value of the shortDescription property
	 */

	public void setShortDescription(String aShortDescription)
	{
		if (this.shortDescription == null || !this.shortDescription.equals(aShortDescription))
		{
			markModified();
		}
		shortDescription = aShortDescription;
	}
	/**
	 * Access method for the longDescription property.
	 * @return the current value of the longDescription property
	 */

	public String getLongDescription()
	{
		fetch();
		return longDescription;
	}
	/**
	 * Sets the value of the longDescription property.
	 * @param aLongDescription the new value of the longDescription property
	 */
	public void setLongDescription(String aLongDescription)
	{
		if (this.longDescription == null || !this.longDescription.equals(aLongDescription))
		{
			markModified();
		}
		longDescription = aLongDescription;
	}

	/**
	 * 
	 * @return JuvenileOffenseCode object
	 * @param offenseCode
	 * @roseuid 41ACA9680353
	 Find JuvenileOffenseCode object by codeId
	 */
	public static JuvenileOffenseCode find(String offenseCodeId)
	{
		JuvenileOffenseCode joc = null;
		IHome home = new Home();
		joc = (JuvenileOffenseCode) home.find(offenseCodeId, JuvenileOffenseCode.class);
		return joc;
	}

	
	/**
	 * Finds all JuvenileOffenseCode objects
	 * @return all JuvenileOffenseCode objects
	 */
	public static Iterator findAll()
	{
		IHome home = new Home();
		return home.findAll(JuvenileOffenseCode.class);
	}
	
	/**
	 * Finds all JuvenileOffenseCode by an attribute value
	 * @return 
	 * @param attributeName
	 * @param attributeValue
	 */
	public static Iterator findAll(String attributeName, String attributeValue)
	{
		return new Home().findAll(attributeName, attributeValue, JuvenileOffenseCode.class);
	}
	
	/**
	 * 
	 * @param attributeName
	 * @param attributeValue
	 * @return
	 */
	public static JuvenileOffenseCode find(String attributeName, String attributeValue)
	{
		JuvenileOffenseCode joc = null;
		IHome home = new Home();
		joc = (JuvenileOffenseCode) home.find(attributeName, attributeValue, JuvenileOffenseCode.class);
		return joc;
	}
	
	public String getCode()
	{
		fetch();
		return this.getOffenseCode();
	}

	public String getDescription()
	{
		fetch();
		return this.getLongDescription();
	}
	
	/**
	 * 
	 * @return 
	 */
	public String getDegree()
	{
		fetch();
		return this.degree;
	}

	/**
	 * 
	 * @return 
	 */
	public String getLevel()
	{
		fetch();
		return this.level;
	}

	/**
	 * 
	 * @param string
	 */
	public void setDegree(String degree)
	{
		if (this.degree == null || !this.degree.equals(degree))
		{
			markModified();
		}
		this.degree = degree;
	}

	/**
	 * 
	 * @param string
	 */
	public void setLevel(String level)
	{
		if (this.level == null || !this.level.equals(level))
		{
			markModified();
		}
		this.level = level;
	}

	
	/**
	 * @return Returns the offenseCategory.
	 */
	public String getOffenseCategory() {
		fetch();
		return offenseCategory;
	}
	/**
	 * @param offenseCategory The offenseCategory to set.
	 */
	public void setOffenseCategory(String offenseCategory) {
		if (this.offenseCategory == null || !this.offenseCategory.equals(offenseCategory))
		{
			markModified();
		}
		this.offenseCategory = offenseCategory;
	}

	public String getInactiveInd() {
		fetch();
		return inactiveInd;
	}

	public void setInactiveInd(String inactiveInd) {
		if (this.inactiveInd == null || !this.inactiveInd.equals(inactiveInd))
		{
			markModified();
		}
		this.inactiveInd = inactiveInd;
	}

	public String getSeveritySubtype() {
		fetch();
		return severitySubtype;
	}

	public void setSeveritySubtype(String severitySubtype) {
		if (this.severitySubtype == null || !this.severitySubtype.equals(severitySubtype))
		{
			markModified();
		}
		this.severitySubtype = severitySubtype;
	}

	public String getSeverityType() {
		fetch();
		return severityType;
	}

	public void setSeverityType(String severityType) {
		if (this.severityType == null || !this.severityType.equals(severityType))
		{
			markModified();
		}
		this.severityType = severityType;
	}

	/**
	 * @return the discontCode
	 */
	public String getDiscontCode()
	{
	    fetch();
	    return discontCode;
	}

	/**
	 * @param discontCode the discontCode to set
	 */
	public void setDiscontCode(String discontCode)
	{
	    if (this.discontCode == null || !this.discontCode.equals(discontCode))
		{
			markModified();
		}
	    this.discontCode = discontCode;
	}
	
	public String getAgeRestrict()
	{
	    fetch();
	    return ageRestrict;
	}

	public void setAgeRestrict(String ageRestrict)
	{
	    if (this.ageRestrict == null || !this.ageRestrict.equals(ageRestrict))
		{
		    markModified();
		}
	    this.ageRestrict = ageRestrict;
	}
	

	public String getAggravated()
	{
	    fetch();
	    return aggravated;
	}

	public void setAggravated(String aggravated)
	{
	    if (this.aggravated == null || !this.aggravated.equals(aggravated))
		{
		    markModified();
		}
	    this.aggravated = aggravated;
	}

	public String getWeapons()
	{
	    fetch();
	    return weapons;
	}

	public void setWeapons(String weapons)
	{
	    if (this.weapons == null || !this.weapons.equals(weapons))
		{
		    markModified();
		}
	    this.weapons = weapons;
	}

	public String getNcicCode()
	{
	    fetch();
	    return ncicCode;
	}

	public void setNcicCode(String ncicCode)
	{
	    if (this.ncicCode == null || !this.ncicCode.equals(ncicCode))
		{
		    markModified();
		}
	    this.ncicCode = ncicCode;
	}
	
	

	public Date getDiscontinuedDate()
	{
	    fetch();
	    return discontinuedDate;
	}

	public void setDiscontinuedDate(Date discontinuedDate)
	{
	    if (this.discontinuedDate == null
		    || !this.discontinuedDate.equals(discontinuedDate))
		{
		    markModified();
		}
	    this.discontinuedDate = discontinuedDate;
	}

	public JuvenileCasefileOffenseCodeResponseEvent valueObject(){
	    
	    JuvenileCasefileOffenseCodeResponseEvent response = new JuvenileCasefileOffenseCodeResponseEvent();
	    
	    response.setAlphaCode(this.getOffenseCode());
	    response.setCategory(this.getCategory());
	    response.setDiscontCode(this.getDiscontCode());
	    response.setDpsCode(this.getDpsOffenseCode());
	    response.setInactiveInd(this.getInactiveInd());
	    response.setLongDescription(this.getLongDescription());
	    response.setNumericCode( this.getNumericCode());
	    response.setReportGroup( this.getReportGroup());
	    response.setSeverity( this.getSeverity());
	    response.setSeveritySubType( this.getSeveritySubtype() );
	    response.setSeverityType( this.getSeverityType() );
	    response.setShortDescription( this.getShortDescription() );
	    response.setAgeRestrict( this.getAgeRestrict() );
	    response.setAggravated(this.getAggravated());
	    response.setWeapons(this.getWeapons());
	    response.setNcicCode(this.getNcicCode());
    
	    
	    return response;
	    
	    
	}
}
