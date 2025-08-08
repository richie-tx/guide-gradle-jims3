/*
 * Project: JIMS2
 * Class:   messaging.juvenileFamily.reply.FamilyMemberListResponseEvent
 * Version: 1.0.0
 *
 * Date:    2005-09-19
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package messaging.juvenilecase.reply;

import java.util.Date;
import java.util.List;

import mojo.km.messaging.IEvent;


/**
 *  
 * @author  athorat
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 * @version  1.0.0
 */
public class FamilyMemberListResponseEvent extends mojo.km.messaging.ResponseEvent implements IEvent, Comparable<FamilyMemberListResponseEvent> {

    // ------------------------------------------------------------------------
    // --- fields                                                           ---
    // ------------------------------------------------------------------------
	
	//	the oid of a member 
    private String memberNum;

    private String firstName;

    private String lastName;

    private String middleName;

    private boolean isDeceased;
    
    private boolean isIncarcerated;

    private String SSN;
    
    private String originalSSN; //Added for #102317
    
    private String formattedSSN ;
    
	private String suspiciousMember;
	private String suspiciousMemberMatch; //added for US 181437
	
	private String sex;
	
	private String sexCd;
	
	private String nationalityCd;
	
	private String nationalityDesc;
	
	private String ethnicityCd;
	
	private String ethnicityDesc;
	
	private Date dateOfBirth;
	
	private String familyNum;
	
	private List associatedJuveniles;
	
	private String driverLicenseNum; 
	private String driverLicenseStateId; 
	private String driverLicenseState; 
	
	private String stateIssuedIdNum; 
	private String stateIssuedIdStateId; 
	private String stateIssuedIdState; 
	
	private String passportNum;
	
	private boolean over21;
	    
	/**
	 * @return the fullName
	 */
	public String getFullNamelfm() {
		if(firstName!=null && !firstName.equals("")){
			String lfmName = lastName + ", " + firstName;
			if (middleName!=null){
				lfmName += " " + middleName;
			}
			return lfmName;
		}
		else{
			return lastName;
		}
	}

	/**
	 * 
	 */
	public FamilyMemberListResponseEvent()
	{
		
	}

    // ------------------------------------------------------------------------
    // --- methods                                                          ---
    // ------------------------------------------------------------------------

    /**
     *  
     * @return  The first name.
     */
    public String getFirstName() {
        return firstName;
    }//end of messaging.juvenileFamily.reply.FamilyMemberListResponseEvent.getFirstName

    /**
     *  
     * @return  The boolean.
     */
    public boolean isDeceased() {
        return isDeceased;
    }//end of messaging.juvenileFamily.reply.FamilyMemberListResponseEvent.isDeceased
    
    /**
     *  
     * @return  The boolean.
     */
    public boolean isIncarcerated() {
        return isIncarcerated;
    }

    /**
     *  
     * @return  The last name.
     */
    public String getLastName() {
        return lastName;
    }//end of messaging.juvenileFamily.reply.FamilyMemberListResponseEvent.getLastName

    /**
     *  
     * @return  The member num.
     */
    public String getMemberNum() {
        return memberNum;
    }//end of messaging.juvenileFamily.reply.FamilyMemberListResponseEvent.getMemberNum

    /**
     *  
     * @return  The middle name.
     */
    public String getMiddleName() {
        return middleName;
    }//end of messaging.juvenileFamily.reply.FamilyMemberListResponseEvent.getMiddleName

    /**
     *  
     * @return  The s s n.
     */
    public String getSSN() {
        return SSN;
    }//end of messaging.juvenileFamily.reply.FamilyMemberListResponseEvent.getSSN

    /**
     *  
     * @param string The first name.
     */
    public void setFirstName(String string) {
        firstName = string;
    }//end of messaging.juvenileFamily.reply.FamilyMemberListResponseEvent.setFirstName

    /**
     *  
     * @param b The deceased.
     */
    public void setDeceased(boolean b) {
        isDeceased = b;
    }//end of messaging.juvenileFamily.reply.FamilyMemberListResponseEvent.setDeceased
    
    /**
     *  
     * @param b The deceased.
     */
    public void setIncarcerated(boolean b) {
    	isIncarcerated = b;
    }//

    /**
     *  
     * @param string The last name.
     */
    public void setLastName(String string) {
        lastName = string;
    }//end of messaging.juvenileFamily.reply.FamilyMemberListResponseEvent.setLastName

    /**
     *  
     * @param string The member num.
     */
    public void setMemberNum(String string) {
        memberNum = string;
    }//end of messaging.juvenileFamily.reply.FamilyMemberListResponseEvent.setMemberNum

    /**
     *  
     * @param string The middle name.
     */
    public void setMiddleName(String string) {
        middleName = string;
    }//end of messaging.juvenileFamily.reply.FamilyMemberListResponseEvent.setMiddleName


    /**
     *  
     * @param string The s s n.
     */
    public void setSSN(String string) {
        SSN = string;
    }//end of messaging.juvenileFamily.reply.FamilyMemberListResponseEvent.setSSN

    public String getFormattedSSN( )
	{
		formattedSSN = "" ;
		if( SSN != null && SSN.length( ) > 0 )
		{
			StringBuffer ss = new StringBuffer( ) ;
			ss.append( SSN.substring( 0, 3 ) ).append( "-" ).append( SSN.substring( 3, 5 ) ).append( "-" ).append( SSN.substring( 5 ) ) ;
			formattedSSN = ss.toString( ) ;
		}
		return( formattedSSN ) ;
	}

	/**
	 * @return
	 */
	public String getSuspiciousMember()
	{
		return suspiciousMember;
	}

	/**
	 * @param string
	 */
	public void setSuspiciousMember(String string)
	{
		suspiciousMember = string;
	}
	
	
	public String getOriginalSSN()
	{
	    return originalSSN;
	}

	public void setOriginalSSN(String originalSSN)
	{
	    this.originalSSN = originalSSN;
	}

	/**
	 * @return Returns the sex.
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex The sex to set.
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	 /**
	 * @return the nationality
	 */
	public String getNationalityCd() {
		return nationalityCd;
	}

	/**
	 * @param nationality the nationality to set
	 */
	public void setNationalityCd(String nationality) {
		this.nationalityCd = nationality;
	}
	
	 /**
	 * @return the driverLicenseStateId
	 */
	public String getDriverLicenseStateId() {
		return driverLicenseStateId;
	}

	/**
	 * @param driverLicenseStateId the driverLicenseStateId to set
	 */
	public void setDriverLicenseStateId(String driverLicenseStateId) {
		this.driverLicenseStateId = driverLicenseStateId;
	}
	
	/**
	 * @return the stateIssuedIdStateId
	 */
	public String getStateIssuedIdStateId() {
		return stateIssuedIdStateId;
	}

	/**
	 * @param stateIssuedIdStateId the stateIssuedIdStateId to set
	 */
	public void setStateIssuedIdStateId(String stateIssuedIdStateId) {
		this.stateIssuedIdStateId = stateIssuedIdStateId;
	}
	 

	/**
	 * @return the ethnicity
	 */
	public String getEthnicityCd() {
		return ethnicityCd;
	}

	/**
	 * @param ethnicity the ethnicity to set
	 */
	public void setEthnicityCd(String ethnicity) {
		this.ethnicityCd = ethnicity;
	}

	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	/**
	 * @return the driverLicenseNum
	 */
	public String getDriverLicenseNum() {
		return driverLicenseNum;
	}
	

	/**
	 * @return the stateIssuedIdNum
	 */
	public String getStateIssuedIdNum() {
		return stateIssuedIdNum;
	}

	public String getNationalityDesc() {
		return nationalityDesc;
	}

	public void setNationalityDesc(String nationalityDesc) {
		this.nationalityDesc = nationalityDesc;
	}
	

	public String getDriverLicenseState() {
		return driverLicenseState;
	}

	public void setDriverLicenseState(String driverLicenseState) {
		this.driverLicenseState = driverLicenseState;
	} 
	
	public String getStateIssuedIdState() {
		return stateIssuedIdState;
	}

	public void setStateIssuedIdState(String stateIssuedIdState) {
		this.stateIssuedIdState = stateIssuedIdState;
	} 
	

	public String getEthnicityDesc() {
		return ethnicityDesc;
	}

	public void setEthnicityDesc(String ethnicityDesc) {
		this.ethnicityDesc = ethnicityDesc;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	/**
	 * @param driverLicenseNum the driverLicenseNum to set
	 */
	public void setDriverLicenseNum(String driverLicenseNum) {
		this.driverLicenseNum = driverLicenseNum;
	} 
	
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setStateIssuedIdNum(String stateIssuedIdNum) {
		this.stateIssuedIdNum = stateIssuedIdNum;
	} 
	

	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}

	public String getSexCd() {
		return sexCd;
	}

	public void setFamilyNum(String familyNum) {
		this.familyNum = familyNum;
	}

	public String getFamilyNum() {
		return familyNum;
	}

	public List getAssociatedJuveniles() {
		return associatedJuveniles;
	}

	public void setAssociatedJuveniles(List associatedJuveniles) {
		this.associatedJuveniles = associatedJuveniles;
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof FamilyMemberListResponseEvent))
		{	
			return false;
		}	
		FamilyMemberListResponseEvent n = (FamilyMemberListResponseEvent)o;
		return n.firstName.equals(firstName) &&  n.lastName.equals(lastName);
	}

	public int hashCode() {
		return 31*firstName.hashCode() + lastName.hashCode();
	}

    public String toString() {
    	return firstName + " " + lastName;
    }

    public int compareTo(FamilyMemberListResponseEvent n) {
        int lastCmp = lastName.compareTo(n.lastName);
        return (lastCmp != 0 ? lastCmp :
                firstName.compareTo(n.firstName));
    }

	public String getPassportNum() {
		return passportNum;
	}

	public void setPassportNum(String passportNum) {
		this.passportNum = passportNum;
	}

	public boolean isOver21() {
		return over21;
	}

	public void setOver21(boolean over21) {
		this.over21 = over21;
	}

	public String getSuspiciousMemberMatch()
	{
	    return suspiciousMemberMatch;
	}

	public void setSuspiciousMemberMatch(String suspiciousMemberMatch)
	{
	    this.suspiciousMemberMatch = suspiciousMemberMatch;
	}
	
} // end FamilyMemberListResponseEvent
