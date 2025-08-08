//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\CreateJuvenileAssociateEvent.java

package messaging.juvenilewarrant;

import java.util.Date;

import mojo.km.messaging.Composite.CompositeRequest;



public class CreateJuvenileAssociateEvent extends CompositeRequest 
{
   public String associateLastName;
   public String associateFirstName;
   public String associateMiddleName;
   public String associateId;
   public String associateSsn;
   public String relationshipToJuvenile;
   public String associateSex;
   private Date associateDateOfBirth;
   public String associateRace;
   public String cellPhone;
   public String fax;
   public String faxLocation;
   public String homePhone;
   public String workPhone;
   public String pager;
   public String email1;
   public String email2;
   public String email3;
   public String warrantNum;
   public String dlNumber;
   public String dlState;
   /**
    * @roseuid 421606E602FD
    */
   public CreateJuvenileAssociateEvent() 
   {
    
   }
   
   /**
    * @param associateLastName
    * @roseuid 4216049C01D4
    */
   public void setAssociateLastName(String associateLastName) 
   {
    this.associateLastName = associateLastName;
   }
   
   /**
    * @return String
    * @roseuid 4216049C01D6
    */
   public String getAssociateLastName() 
   {
    return associateLastName;
   }
   
   /**
    * @param associateFirstName
    * @roseuid 4216049C01E5
    */
   public void setAssociateFirstName(String associateFirstName) 
   {
    this.associateFirstName = associateFirstName;
   }
   
   /**
    * @return String
    * @roseuid 4216049C01E7
    */
   public String getAssociateFirstName() 
   {
    return associateFirstName;
   }
   
   /**
    * @param associateMiddleName
    * @roseuid 4216049C01E9
    */
   public void setAssociateMiddleName(String associateMiddleName) 
   {
    this.associateMiddleName = associateMiddleName;
   }
   
   /**
    * @return String
    * @roseuid 4216049C01EB
    */
   public String getAssociateMiddleName() 
   {
    return associateMiddleName;
   }
   
   /**
    * @param associateId
    * @roseuid 4216049C01F5
    */
   public void setAssociateId(String associateId) 
   {
    this.associateId = associateId;
   }
   
   /**
    * @roseuid 4216049C01F7
    */
   public String getAssociateId() 
   {
   	return associateId;
   }
   
   /**
    * @param associateSsn
    * @roseuid 4216049C01F9
    */
   public void setAssociateSsn(String associateSsn) 
   {
    this.associateSsn = associateSsn;
   }
   
   /**
    * @return String
    * @roseuid 4216049C01FB
    */
   public String getAssociateSsn() 
   {
    return associateSsn;
   }
   
   /**
    * @param relationshipToJuvenile
    * @roseuid 4216049C0203
    */
   public void setRelationshipToJuvenile(String relationshipToJuvenile) 
   {
    this.relationshipToJuvenile = relationshipToJuvenile;
   }
   
   /**
    * @return String
    * @roseuid 4216049C0205
    */
   public String getRelationshipToJuvenile() 
   {
    return relationshipToJuvenile;
   }
   
   /**
    * @param associateSex
    * @roseuid 4216049C0207
    */
   public void setAssociateSex(String associateSex) 
   {
    this.associateSex = associateSex;
   }
   
   /**
    * @return String
    * @roseuid 4216049C0209
    */
   public String getAssociateSex() 
   {
    return associateSex;
   }
   
   /**
    * @param associateRace
    * @roseuid 4216049C0213
    */
   public void setAssociateRace(String associateRace) 
   {
    this.associateRace = associateRace;
   }
   
   /**
    * @return String
    * @roseuid 4216049C0215
    */
   public String getAssociateRace() 
   {
    return associateRace;
   }
   
   /**
    * @param cellPhone
    * @roseuid 4216049C0217
    */
   public void setCellPhone(String cellPhone) 
   {
    this.cellPhone = cellPhone;
   }
   
   /**
    * @return String
    * @roseuid 4216049C0219
    */
   public String getCellPhone() 
   {
    return cellPhone;
   }
   
   /**
    * @param fax
    * @roseuid 4216049C0222
    */
   public void setFax(String fax) 
   {
    this.fax = fax;
   }
   
   /**
    * @return String
    * @roseuid 4216049C0224
    */
   public String getFax() 
   {
    return fax;
   }
   
   /**
    * @param faxLocation
    * @roseuid 4216049C0226
    */
   public void setFaxLocation(String faxLocation) 
   {
    this.faxLocation = faxLocation;
   }
   
   /**
    * @return String
    * @roseuid 4216049C0228
    */
   public String getFaxLocation() 
   {
    return faxLocation;
   }
   
   /**
    * @param homePhone
    * @roseuid 4216049C0232
    */
   public void setHomePhone(String homePhone) 
   {
    this.homePhone = homePhone;
   }
   
   /**
    * @return String
    * @roseuid 4216049C0234
    */
   public String getHomePhone() 
   {
    return homePhone;
   }
   
   /**
    * @param workPhone
    * @roseuid 4216049C0236
    */
   public void setWorkPhone(String workPhone) 
   {
    this.workPhone = workPhone;
   }
   
   /**
    * @return String
    * @roseuid 4216049C0238
    */
   public String getWorkPhone() 
   {
    return workPhone;
   }
   
   /**
    * @param pager
    * @roseuid 4216049C0243
    */
   public void setPager(String pager) 
   {
    this.pager = pager;
   }
   
   /**
    * @return String
    * @roseuid 4216049C0245
    */
   public String getPager() 
   {
    return pager;
   }
   
   /**
    * @param email1
    * @roseuid 4216049C0247
    */
   public void setEmail1(String email1) 
   {
    this.email1 = email1;
   }
   
   /**
    * @return String
    * @roseuid 4216049C0249
    */
   public String getEmail1() 
   {
    return email1;
   }
   
   /**
    * @param email2
    * @roseuid 4216049C0252
    */
   public void setEmail2(String email2) 
   {
    this.email2 = email2;
   }
   
   /**
    * @return String
    * @roseuid 4216049C0254
    */
   public String getEmail2() 
   {
    return email2;
   }
   
   /**
    * @param email3
    * @roseuid 4216049C0256
    */
   public void setEmail3(String email3) 
   {
    this.email3 = email3;
   }
   
   /**
    * @return String
    * @roseuid 4216049C0258
    */
   public String getEmail3() 
   {
    return email3;
   }
   
   /**
    * @param warrantNum
    * @roseuid 4216049C0262
    */
   public void setWarrantNum(String warrantNum) 
   {
    this.warrantNum = warrantNum;
   }
   
   /**
    * @return String
    * @roseuid 4216049C0264
    */
   public String getWarrantNum() 
   {
    return warrantNum;
   }
   
/**
 * @return
 */
public Date getAssociateDateOfBirth()
{
	return associateDateOfBirth;
}

/**
 * @param date
 */
public void setAssociateDateOfBirth(Date date)
{
	associateDateOfBirth = date;
}



/**
 * @return
 */
public String getDlNumber()
{
	return dlNumber;
}

/**
 * @return
 */
public String getDlState()
{
	return dlState;
}

/**
 * @param string
 */
public void setDlNumber(String dlNumber)
{
	this.dlNumber = dlNumber;
}

/**
 * @param string
 */
public void setDlState(String dlState)
{
	this.dlState = dlState;
}

}
