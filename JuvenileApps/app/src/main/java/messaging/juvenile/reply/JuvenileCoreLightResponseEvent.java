/*
 * Project: JIMS
 * Class:   messaging.juvenile.reply.JuvenileProfileDetailResponseEvent
 *
 * Date:    2005-06-29
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package messaging.juvenile.reply;

import java.util.Date;

import pd.codetable.Code;


/**
 * 
 * @author athorat To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenileCoreLightResponseEvent extends mojo.km.messaging.ResponseEvent
{
	
    private Date dateOfBirth;

    private String firstName;
    
    private String preferredFirstName;

    private String hispanicInd;

    private String lastName;

    private String middleName;

    private String nameSuffix;

    private String propointJPOpIdId;	
    
    private Code race;

    private String raceId;
    
    private Code originalRace;
    
    //Added for US 90441
    private String originalRaceId;        

    private String sexId;

    private Code sex;

    private String SSN;

    private String juvenileType;

    //US 70421 Referrals Conversion
    private String juvAddress;
    private int addressId;
    private String juvCounty;
    private String juvSchoolDist;
    private String juvSchoolName;
    private String recType;
    //US 70421 Referrals Conversion for Migrated Legacy records without a casefile
    private String ssn1;
    private String ssn2;
    private Date createDate;
    
    
    
    public Date getDateOfBirth()
    {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }
    public String getFirstName()
    {
        return firstName;
    }
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    public String getPreferredFirstName()
    {
        return preferredFirstName;
    }
    public void setPreferredFirstName(String preferredFirstName)
    {
        this.preferredFirstName = preferredFirstName;
    }
    public String getHispanicInd()
    {
        return hispanicInd;
    }
    public void setHispanicInd(String hispanicInd)
    {
        this.hispanicInd = hispanicInd;
    }
    public String getLastName()
    {
        return lastName;
    }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    public String getMiddleName()
    {
        return middleName;
    }
    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }
    public String getNameSuffix()
    {
        return nameSuffix;
    }
    public void setNameSuffix(String nameSuffix)
    {
        this.nameSuffix = nameSuffix;
    }
    public String getPropointJPOpIdId()
    {
        return propointJPOpIdId;
    }
    public void setPropointJPOpIdId(String propointJPOpIdId)
    {
        this.propointJPOpIdId = propointJPOpIdId;
    }
    public Code getRace()
    {
        return race;
    }
    public void setRace(Code race)
    {
        this.race = race;
    }
    public String getRaceId()
    {
        return raceId;
    }
    public void setRaceId(String raceId)
    {
        this.raceId = raceId;
    }
    public Code getOriginalRace()
    {
        return originalRace;
    }
    public void setOriginalRace(Code originalRace)
    {
        this.originalRace = originalRace;
    }
    public String getOriginalRaceId()
    {
        return originalRaceId;
    }
    public void setOriginalRaceId(String originalRaceId)
    {
        this.originalRaceId = originalRaceId;
    }
    public String getSexId()
    {
        return sexId;
    }
    public void setSexId(String sexId)
    {
        this.sexId = sexId;
    }
    public Code getSex()
    {
        return sex;
    }
    public void setSex(Code sex)
    {
        this.sex = sex;
    }
    public String getSSN()
    {
        return SSN;
    }
    public void setSSN(String sSN)
    {
        SSN = sSN;
    }
    public String getJuvenileType()
    {
        return juvenileType;
    }
    public void setJuvenileType(String juvenileType)
    {
        this.juvenileType = juvenileType;
    }
    public String getJuvAddress()
    {
        return juvAddress;
    }
    public void setJuvAddress(String juvAddress)
    {
        this.juvAddress = juvAddress;
    }
    public int getAddressId()
    {
        return addressId;
    }
    public void setAddressId(int addressId)
    {
        this.addressId = addressId;
    }
    public String getJuvCounty()
    {
        return juvCounty;
    }
    public void setJuvCounty(String juvCounty)
    {
        this.juvCounty = juvCounty;
    }
    public String getJuvSchoolDist()
    {
        return juvSchoolDist;
    }
    public void setJuvSchoolDist(String juvSchoolDist)
    {
        this.juvSchoolDist = juvSchoolDist;
    }
    public String getJuvSchoolName()
    {
        return juvSchoolName;
    }
    public void setJuvSchoolName(String juvSchoolName)
    {
        this.juvSchoolName = juvSchoolName;
    }
    public String getRecType()
    {
        return recType;
    }
    public void setRecType(String recType)
    {
        this.recType = recType;
    }
    public String getSsn1()
    {
        return ssn1;
    }
    public void setSsn1(String ssn1)
    {
        this.ssn1 = ssn1;
    }
    public String getSsn2()
    {
        return ssn2;
    }
    public void setSsn2(String ssn2)
    {
        this.ssn2 = ssn2;
    }
    public Date getCreateDate()
    {
        return createDate;
    }
    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }
	
} // end JuvenileProfileDetailResponseEvent
