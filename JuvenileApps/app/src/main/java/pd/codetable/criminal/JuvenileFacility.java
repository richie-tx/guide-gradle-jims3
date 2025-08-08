package pd.codetable.criminal;

import java.util.Iterator;

import messaging.codetable.reply.ICode;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;

/**
 * @author dapte JuvenileFacility entity
 */
public class JuvenileFacility extends PersistentObject implements ICode
{
    private String code;
    private String description;
    private String department;
    private String juvTJPCPlacementType; //added  for JIMS200077404
    private String address;
    private String city;
    private String zip;
    private String juvenileFacilityId;
    private String refereeCourt;
    private String secureInd;
    private String inactiveInd;

    //added for facility
    private String locationOne;
    private String locationTwo;
    private String locationThree;

    /**
     * Properties for levelOfCare
     * 
     * @referencedType pd.codetable.Code
     * @contextKey JUVENILE_LEVEL_CARE
     * @detailerDoNotGenerate true
     */
    private Code levelOfCare = null;
    private String levelOfCareId;

    private String facilityPhone;
    private String juvenilePlacementType;
    private String rectype = "FACILITY" ;
    private String avgCostPerDay;
    private String serviceType;
    private String vendor;
    private int tjjdFacilityId;
    private String tjjdFundingSrc;
    private String tjjdFacilityEdi;

    /**
     * @roseuid 41ACCAD2037F
     */
    public JuvenileFacility()
    {
    }

    /**
     * @return a JuvenileCourt object
     * @param juvenileFacilityId
     * @roseuid 41AC81DE0186
     */
    static public JuvenileFacility find(String juvenileFacilityId)
    {
	return (JuvenileFacility) new Home().find(juvenileFacilityId, JuvenileFacility.class);
    }

    /**
     * Find all Juvenile Courts.
     * 
     * @return java.util.Iterator
     */
    static public Iterator findAll()
    {
	IHome home = new Home();
	return home.findAll(JuvenileFacility.class);
    }

    /**
     * Finds all JuvenileFacility by an attribute value
     * 
     * @param attributeName
     * @param attributeValue
     * @return
     */
    static public Iterator findAll(String attributeName, String attributeValue)
    {
	return new Home().findAll(attributeName, attributeValue, JuvenileFacility.class);
    }

    /**
     * Finds a JuvenileFacility by an attribute value
     * 
     * @param attributeName
     * @param attributeValue
     * @return
     */
    static public JuvenileFacility find(String attributeName, String attributeValue)
    {
	return (JuvenileFacility) new Home().find(attributeName, attributeValue, JuvenileFacility.class);
    }

    /**
     * @return Returns the address.
     */
    public String getAddress()
    {
	fetch();
	return address;
    }

    /**
     * @param address
     *            The address to set.
     */
    public void setAddress(String address)
    {
	if (this.address == null || !this.address.equals(address))
	{
	    markModified();
	}
	this.address = address;
    }

    /**
     * @return Returns the city.
     */
    public String getCity()
    {
	fetch();
	return city;
    }

    /**
     * @param city
     *            The city to set.
     */
    public void setCity(String city)
    {
	if (this.city == null || !this.city.equals(city))
	{
	    markModified();
	}
	this.city = city;
    }

    /**
     * @return Returns the code.
     */
    public String getCode()
    {
	fetch();
	return code;
    }

    /**
     * @param code
     *            The code to set.
     */
    public void setCode(String code)
    {
	if (this.code == null || !this.code.equals(code))
	{
	    markModified();
	}
	this.code = code;
    }

    /**
     * @return Returns the department.
     */
    public String getDepartment()
    {
	fetch();
	return department;
    }

    /**
     * @param department
     *            The department to set.
     */
    public void setDepartment(String department)
    {
	if (this.department == null || !this.department.equals(department))
	{
	    markModified();
	}
	this.department = department;
    }

    /**
     * @return Returns the juvTJPCPlacementType.
     */
    public String getJuvTJPCPlacementType()
    {
	fetch();
	return juvTJPCPlacementType;
    }

    /**
     * @param juvTJPCPlacementType
     *            The juvTJPCPlacementType to set.
     */
    public void setJuvTJPCPlacementType(String juvTJPCPlacementType)
    {
	if (this.juvTJPCPlacementType == null || !this.juvTJPCPlacementType.equals(juvTJPCPlacementType))
	{
	    markModified();
	}
	this.juvTJPCPlacementType = juvTJPCPlacementType;
    }

    /**
     * @return Returns the description.
     */
    public String getDescription()
    {
	fetch();
	return description;
    }

    /**
     * @param description
     *            The description to set.
     */
    public void setDescription(String description)
    {
	if (this.description == null || !this.description.equals(description))
	{
	    markModified();
	}
	this.description = description;
    }

    /**
     * @return Returns the zip.
     */
    public String getZip()
    {
	fetch();
	return zip;
    }

    /**
     * @param zip
     *            The zip to set.
     */
    public void setZip(String zip)
    {
	if (this.zip == null || !this.zip.equals(zip))
	{
	    markModified();
	}
	this.zip = zip;
    }

    /**
     * @return Returns the juvenileFacilityId.
     */
    public String getJuvenileFacilityId()
    {
	fetch();
	return this.getOID().toString();
    }

    /**
     * @param juvenileFacilityId
     *            The juvenileFacilityId to set.
     */
    public void setJuvenileFacilityId(String juvenileFacilityId)
    {
	if (this.juvenileFacilityId == null || !this.juvenileFacilityId.equals(juvenileFacilityId))
	{
	    markModified();
	}
	this.setOID(juvenileFacilityId);
	this.juvenileFacilityId = juvenileFacilityId;
    }

    /**
     * Gets referenced type pd.codetable.Code
     */
    public Code getLevelOfCare()
    {
	fetch();
	initLevelOfCare();
	return levelOfCare;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getLevelOfCareId()
    {
	fetch();
	return levelOfCareId;
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initLevelOfCare()
    {
	if (levelOfCare == null)
	{
	    try
	    {
		levelOfCare = (Code) new mojo.km.persistence.Reference(levelOfCareId, Code.class, "JUVENILE_LEVEL_CARE").getObject();
	    }
	    catch (Throwable t)
	    {
		levelOfCare = null;
	    }
	}
    }

    /**
     * set the type reference for class member levelOfCare
     */
    public void setLevelOfCare(Code theLevelOfCare)
    {
	if (this.levelOfCare == null || !this.levelOfCare.equals(theLevelOfCare))
	{
	    markModified();
	}
	if (theLevelOfCare.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(theLevelOfCare);
	}
	setLevelOfCareId("" + theLevelOfCare.getOID());
	this.levelOfCare = (Code) new mojo.km.persistence.Reference(theLevelOfCare).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setLevelOfCareId(String theLevelOfCareId)
    {
	if (this.levelOfCareId == null || !this.levelOfCareId.equals(theLevelOfCareId))
	{
	    markModified();
	}
	levelOfCare = null;
	this.levelOfCareId = theLevelOfCareId;
    }

    /**
     * @return Returns the refereeCourt.
     */
    public String getRefereeCourt()
    {
	fetch();
	return refereeCourt;
    }

    /**
     * @param refereeCourt
     *            The refereeCourt to set.
     */
    public void setRefereeCourt(String refereeCourt)
    {
	if (this.refereeCourt == null || !this.refereeCourt.equals(refereeCourt))
	{
	    markModified();
	}
	this.refereeCourt = refereeCourt;
    }

    /**
     * @return Returns the secureInd.
     */
    public String getSecureInd()
    {
	fetch();
	return secureInd;
    }

    /**
     * @param secureInd
     *            The secureInd to set.
     */
    public void setSecureInd(String secureInd)
    {
	if (this.secureInd == null || !this.secureInd.equals(secureInd))
	{
	    markModified();
	}
	this.secureInd = secureInd;
    }

    public String getFacilityPhone()
    {
	fetch();
	return facilityPhone;
    }

    public void setFacilityPhone(String facilityPhone)
    {
	if (this.facilityPhone == null || !this.facilityPhone.equals(facilityPhone))
	{
	    markModified();
	}
	this.facilityPhone = facilityPhone;
    }

    public String getJuvenilePlacementType()
    {
	fetch();
	return juvenilePlacementType;
    }

    public void setJuvenilePlacementType(String juvenilePlacementType)
    {
	if (this.juvenilePlacementType == null || !this.juvenilePlacementType.equals(juvenilePlacementType))
	{
	    markModified();
	}
	this.juvenilePlacementType = juvenilePlacementType;
    }

    /**
     * @return Returns the inactiveInd.
     */
    public String getInactiveInd()
    {
	fetch();
	return inactiveInd;
    }

    /**
     * @param department
     *            The department to set.
     */
    public void setInactiveInd(String inactiveInd)
    {
	if (this.inactiveInd == null || !this.inactiveInd.equals(department))
	{
	    markModified();
	}
	this.inactiveInd = inactiveInd;
    }

    public void setLocationOne(String locationOne)
    {
	this.locationOne = locationOne;
    }

    public String getLocationOne()
    {
	return locationOne;
    }

    public void setLocationTwo(String locationTwo)
    {
	this.locationTwo = locationTwo;
    }

    public String getLocationTwo()
    {
	return locationTwo;
    }

    public void setLocationThree(String locationThree)
    {
	this.locationThree = locationThree;
    }

    public String getLocationThree()
    {
	return locationThree;
    }

    public String getRectype()
    {
	fetch();
	return rectype;
    }

    public void setRectype(String rectype)
    {
	if (this.rectype == null || !this.rectype.equals(rectype))
	{
	    markModified();
	}
	this.rectype = rectype;
    }

    public String getAvgCostPerDay()
    {
	fetch();
	return avgCostPerDay;
    }

    public void setAvgCostPerDay(String avgCostPerDay)
    {
	if (this.avgCostPerDay == null || !this.avgCostPerDay.equals(avgCostPerDay))
	{
	    markModified();
	}
	this.avgCostPerDay = avgCostPerDay;
    }

    public String getServiceType()
    {
	fetch();
	return serviceType;
    }

    public void setServiceType(String serviceType)
    {
	if (this.serviceType == null || !this.serviceType.equals(serviceType))
	{
	    markModified();
	}
	this.serviceType = serviceType;
    }

    public String getVendor()
    {
	fetch();
	return vendor;
    }

    public void setVendor(String vendor)
    {
	if (this.vendor == null || !this.vendor.equals(vendor))
	{
	    markModified();
	}
	this.vendor = vendor;
    }

    public int getTjjdFacilityId()
    {
	fetch();
        return tjjdFacilityId;
    }

    public void setTjjdFacilityId(int tjjdFacilityId)
    {

        this.tjjdFacilityId = tjjdFacilityId;
    }

    public String getTjjdFundingSrc()
    {
	fetch();
        return tjjdFundingSrc;
    }

    public void setTjjdFundingSrc(String tjjdFundingSrc)
    {
	if (this.tjjdFundingSrc == null || !this.tjjdFundingSrc.equals(tjjdFundingSrc))
	{
	    markModified();
	}
        this.tjjdFundingSrc = tjjdFundingSrc;
    }

    public String getTjjdFacilityEdi()
    {
	fetch();
	return tjjdFacilityEdi;
    }

    public void setTjjdFacilityEdi(String tjjdFacilityEdi)
    {
	if (this.tjjdFacilityEdi == null || !this.tjjdFacilityEdi.equals(tjjdFacilityEdi))
	{
	    markModified();
	}
        this.tjjdFacilityEdi = tjjdFacilityEdi;
    }

}
