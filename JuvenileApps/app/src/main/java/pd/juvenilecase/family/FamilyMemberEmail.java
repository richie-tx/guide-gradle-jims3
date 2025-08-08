/*
 * Project: JIMS2
 * Class:   pd.juvenilecase.family.FamilyMemberEmail
 * Version: 1.0.0
 *
 * Date:    2005-09-19
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package pd.juvenilecase.family;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.codetable.Code;

/**
 *  
 * @author  athorat
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 * @version  1.0.0
 */
public class FamilyMemberEmail extends mojo.km.persistence.PersistentObject {

    // ------------------------------------------------------------------------
    // --- fields                                                           ---
    // ------------------------------------------------------------------------

    /**
     * @detailerDoNotGenerate true
     * @referencedType pd.codetable.Code
     * @contextKey Email_TYPE
     */
    private Code emailType = null;

    private String emailTypeId;

    private String familyMemberId;
    
    private String emailAddress;
    
    private boolean primaryInd;
    /**
     * Properties for familyMember
     * @detailerDoNotGenerate true
     * @referencedType pd.juvenilecase.family.FamilyMember
     */
    private FamilyMember familyMember = null;

   


    // ------------------------------------------------------------------------
    // --- constructor                                                      ---
    // ------------------------------------------------------------------------

    /**
     */
    public FamilyMemberEmail() {

    }//end of pd.juvenilecase.family.FamilyMemberEmail.FamilyMemberEmail


    // ------------------------------------------------------------------------
    // --- methods                                                          ---
    // ------------------------------------------------------------------------

    /**
     * Set the reference value to class :: pd.juvenilecase.family.FamilyMember
     *  
     * @param familyMemberId The family member id.
     */
    public void setFamilyMemberId(String familyMemberId) {
        if (this.familyMemberId == null || !this.familyMemberId.equals(familyMemberId)) {
        	markModified();
        }
        familyMember = null;
        this.familyMemberId = familyMemberId;
    }//end of pd.juvenilecase.family.FamilyMemberEmail.setFamilyMemberId

    /**
     * Get the reference value to class :: pd.juvenilecase.family.FamilyMember
     *  
     * @return  The family member id.
     */
    public String getFamilyMemberId() {
        fetch();
        return familyMemberId;
    }//end of pd.juvenilecase.family.FamilyMemberEmail.getFamilyMemberId

    /**
     * Gets referenced type pd.juvenilecase.family.FamilyMember
     *  
     * @return  The family member.
     */
    public FamilyMember getFamilyMember() {
        fetch();
        initFamilyMember();
        return familyMember;
    }//end of pd.juvenilecase.family.FamilyMemberEmail.getFamilyMember

    /**
     * set the type reference for class member familyMember
     *  
     * @param familyMember The family member.
     */
    public void setFamilyMember(FamilyMember familyMember) {
        if (this.familyMember == null || !this.familyMember.equals(familyMember)) {
        	markModified();
        }
        if (familyMember.getOID() == null) {
        	new mojo.km.persistence.Home().bind(familyMember);
        }
        setFamilyMemberId("" + familyMember.getOID());
        this.familyMember = (FamilyMember) new mojo.km.persistence.Reference(familyMember).getObject();
    }//end of pd.juvenilecase.family.FamilyMemberEmail.setFamilyMember

    /**
     * Set the reference value to class :: pd.contact.Email
     *  
     * @param emailAddress The Email master id.
     */
    public void setEmailAddress(String aEmailAddress) {
        if (this.emailAddress == null || !this.emailAddress.equals(aEmailAddress)) {
        	markModified();
        }
        this.emailAddress = aEmailAddress;
    }//end of pd.juvenilecase.family.FamilyMemberEmail.setEmailAddress

    /**
     * Get the reference value to class :: pd.contact.Email
     *  
     * @return  The Email master id.
     */
    public String getEmailAddress() {
        fetch();
        return emailAddress;
    }//end of pd.juvenilecase.family.FamilyMemberEmail.getEmailAddress

   

    /**
     * Set the reference value to class :: pd.codetable.Code
     *  
     * @param emailTypeId The Email type id.
     */
    public void setEmailTypeId(String aEmailTypeId) {
        if (this.emailTypeId == null || !this.emailTypeId.equals(aEmailTypeId)) {
        	markModified();
        }
        emailType = null;
        this.emailTypeId = aEmailTypeId;
    }//end of pd.juvenilecase.family.FamilyMemberEmail.setEmailTypeId

    /**
     * Get the reference value to class :: pd.codetable.Code
     *  
     * @return  The Email type id.
     */
    public String getEmailTypeId() {
        fetch();
        return emailTypeId;
    }//end of pd.juvenilecase.family.FamilyMemberEmail.getemailTypeId

    /**
     * Gets referenced type pd.codetable.Code
     *  
     * @return  The Email type.
     */
    public Code getEmailType() {
        initEmailType();
        return emailType;
    }//end of pd.juvenilecase.family.FamilyMemberEmail.getemailType

    /**
     * set the type reference for class member emailType
     *  
     * @param emailType The Email type.
     */
    public void setEmailType(Code aEmailType) {
        if (this.emailType == null || !this.emailType.equals(aEmailType)) {
        	markModified();
        }
        if (aEmailType.getOID() == null) {
        	new mojo.km.persistence.Home().bind(aEmailType);
        }
        setEmailTypeId("" + aEmailType.getOID());
        aEmailType.setContext("EMAIL_TYPE");
        this.emailType = (Code) new mojo.km.persistence.Reference(aEmailType).getObject();
    }//end of pd.juvenilecase.family.FamilyMemberEmail.setemailType

    /**
     * Initialize class relationship to class pd.juvenilecase.family.FamilyMember
     */
    private void initFamilyMember() {
        if (familyMember == null) {
        	familyMember = (FamilyMember) new mojo.km.persistence.Reference(familyMemberId, FamilyMember.class).getObject();
        }
    }//end of pd.juvenilecase.family.FamilyMemberEmail.initFamilyMember

   
    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initEmailType() {
        if (emailType == null) {
        	emailType = (Code) new mojo.km.persistence.Reference(emailTypeId, Code.class, "EMAIL_TYPE").getObject();
        }
    }//end of pd.juvenilecase.family.FamilyMemberEmail.initemailType

	// ------------------------------------------------------------------------
	// --- static method                                                    ---
	// ------------------------------------------------------------------------

	/**
	 * Finds FamilyMemberEmail by a certain event
	 *  
	 * @param event The event.
	 * @return  Iterator of FamilyMemberEmail
	 */
	public static Iterator findAll(IEvent event) {
		IHome home = new Home();
		Iterator familyMemberEmails = home.findAll(event, FamilyMemberEmail.class);
		return familyMemberEmails;
	}//end of pd.juvenilecase.family.FamilyMemberEmail.findAll

	/**
	 *  
	 * @param memberNum
	 */
	public static FamilyMemberEmail find(String memberNum) {
		IHome home = new Home();
		FamilyMemberEmail familyEmail = (FamilyMemberEmail) home.find(memberNum, FamilyMemberEmail.class);
		return familyEmail;    
	}//end of pd.juvenilecase.family.FamilyMemberEmail.find

	/**
	* Finds all Emails by an attribute value
	* @param attributeName
	* @param attributeValue
	* @return 
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		IHome home = new Home();
		Iterator familyMemberEmails = home.findAll(attributeName, attributeValue, FamilyMemberEmail.class);
		return familyMemberEmails;
	}
	
	public boolean getPrimaryInd() {
		fetch();
		return primaryInd;
	}
	/**
	 * @param primaryInd the primaryInd to set
	 */
	public void setPrimaryInd(boolean primaryInd) {
		if (this.primaryInd != primaryInd) {
			markModified();
		}
		this.primaryInd = primaryInd;
	}


} // end FamilyMemberEmail
