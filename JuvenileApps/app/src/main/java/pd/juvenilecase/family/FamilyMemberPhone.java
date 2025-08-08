/*
 * Project: JIMS2
 * Class:   pd.juvenilecase.family.FamilyMemberPhone
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
import pd.contact.Phone;
import pd.juvenilecase.family.*;

/**
 *  
 * @author  athorat
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 * @version  1.0.0
 */
public class FamilyMemberPhone extends mojo.km.persistence.PersistentObject {

    // ------------------------------------------------------------------------
    // --- fields                                                           ---
    // ------------------------------------------------------------------------

    /**
     * @detailerDoNotGenerate true
     * @referencedType pd.codetable.Code
     * @contextKey PHONE_TYPE
     */
    private Code phoneType = null;

    private String phoneTypeId;

    private String familyMemberId;

    /**
     * Properties for familyMember
     * @detailerDoNotGenerate true
     * @referencedType pd.juvenilecase.family.FamilyMember
     */
    private FamilyMember familyMember = null;

    private String phoneMasterId;

    /**
     * Properties for phoneMaster
     * @detailerDoNotGenerate true
     * @referencedType pd.contact.Phone
     */
    private Phone phoneMaster = null;


    // ------------------------------------------------------------------------
    // --- constructor                                                      ---
    // ------------------------------------------------------------------------

    /**
     */
    public FamilyMemberPhone() {

    }//end of pd.juvenilecase.family.FamilyMemberPhone.FamilyMemberPhone


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
    }//end of pd.juvenilecase.family.FamilyMemberPhone.setFamilyMemberId

    /**
     * Get the reference value to class :: pd.juvenilecase.family.FamilyMember
     *  
     * @return  The family member id.
     */
    public String getFamilyMemberId() {
        fetch();
        return familyMemberId;
    }//end of pd.juvenilecase.family.FamilyMemberPhone.getFamilyMemberId

    /**
     * Gets referenced type pd.juvenilecase.family.FamilyMember
     *  
     * @return  The family member.
     */
    public FamilyMember getFamilyMember() {
        fetch();
        initFamilyMember();
        return familyMember;
    }//end of pd.juvenilecase.family.FamilyMemberPhone.getFamilyMember

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
    }//end of pd.juvenilecase.family.FamilyMemberPhone.setFamilyMember

    /**
     * Set the reference value to class :: pd.contact.Phone
     *  
     * @param phoneMasterId The phone master id.
     */
    public void setPhoneMasterId(String phoneMasterId) {
        if (this.phoneMasterId == null || !this.phoneMasterId.equals(phoneMasterId)) {
        	markModified();
        }
        phoneMaster = null;
        this.phoneMasterId = phoneMasterId;
    }//end of pd.juvenilecase.family.FamilyMemberPhone.setPhoneMasterId

    /**
     * Get the reference value to class :: pd.contact.Phone
     *  
     * @return  The phone master id.
     */
    public String getPhoneMasterId() {
        fetch();
        return phoneMasterId;
    }//end of pd.juvenilecase.family.FamilyMemberPhone.getPhoneMasterId

    /**
     * Gets referenced type pd.contact.Phone
     *  
     * @return  The phone master.
     */
    public Phone getPhoneMaster() {
        fetch();
        initPhoneMaster();
        return phoneMaster;
    }//end of pd.juvenilecase.family.FamilyMemberPhone.getPhoneMaster

    /**
     * set the type reference for class member phoneMaster
     *  
     * @param phoneMaster The phone master.
     */
    public void setPhoneMaster(Phone phoneMaster) {
        if (this.phoneMaster == null || !this.phoneMaster.equals(phoneMaster)) {
        	markModified();
        }
        if (phoneMaster.getOID() == null) {
        	new mojo.km.persistence.Home().bind(phoneMaster);
        }
        setPhoneMasterId("" + phoneMaster.getOID());
        this.phoneMaster = (Phone) new mojo.km.persistence.Reference(phoneMaster).getObject();
    }//end of pd.juvenilecase.family.FamilyMemberPhone.setPhoneMaster

    /**
     * Set the reference value to class :: pd.codetable.Code
     *  
     * @param phoneTypeId The phone type id.
     */
    public void setPhoneTypeId(String phoneTypeId) {
        if (this.phoneTypeId == null || !this.phoneTypeId.equals(phoneTypeId)) {
        	markModified();
        }
        phoneType = null;
        this.phoneTypeId = phoneTypeId;
    }//end of pd.juvenilecase.family.FamilyMemberPhone.setPhoneTypeId

    /**
     * Get the reference value to class :: pd.codetable.Code
     *  
     * @return  The phone type id.
     */
    public String getPhoneTypeId() {
        fetch();
        return phoneTypeId;
    }//end of pd.juvenilecase.family.FamilyMemberPhone.getPhoneTypeId

    /**
     * Gets referenced type pd.codetable.Code
     *  
     * @return  The phone type.
     */
    public Code getPhoneType() {
        initPhoneType();
        return phoneType;
    }//end of pd.juvenilecase.family.FamilyMemberPhone.getPhoneType

    /**
     * set the type reference for class member phoneType
     *  
     * @param phoneType The phone type.
     */
    public void setPhoneType(Code phoneType) {
        if (this.phoneType == null || !this.phoneType.equals(phoneType)) {
        	markModified();
        }
        if (phoneType.getOID() == null) {
        	new mojo.km.persistence.Home().bind(phoneType);
        }
        setPhoneTypeId("" + phoneType.getOID());
        phoneType.setContext("PHONE_TYPE");
        this.phoneType = (Code) new mojo.km.persistence.Reference(phoneType).getObject();
    }//end of pd.juvenilecase.family.FamilyMemberPhone.setPhoneType

    /**
     * Initialize class relationship to class pd.juvenilecase.family.FamilyMember
     */
    private void initFamilyMember() {
        if (familyMember == null) {
        	familyMember = (FamilyMember) new mojo.km.persistence.Reference(familyMemberId, FamilyMember.class).getObject();
        }
    }//end of pd.juvenilecase.family.FamilyMemberPhone.initFamilyMember

    /**
     * Initialize class relationship to class pd.contact.Phone
     */
    private void initPhoneMaster() {
        if (phoneMaster == null) {
        	phoneMaster = (Phone) new mojo.km.persistence.Reference(phoneMasterId, Phone.class).getObject();
        }
    }//end of pd.juvenilecase.family.FamilyMemberPhone.initPhoneMaster

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initPhoneType() {
        if (phoneType == null) {
        	phoneType = (Code) new mojo.km.persistence.Reference(phoneTypeId, Code.class, "PHONE_TYPE").getObject();
        }
    }//end of pd.juvenilecase.family.FamilyMemberPhone.initPhoneType

	// ------------------------------------------------------------------------
	// --- static method                                                    ---
	// ------------------------------------------------------------------------

	/**
	 * Finds FamilyMemberPhone by a certain event
	 *  
	 * @param event The event.
	 * @return  Iterator of FamilyMemberPhone
	 */
	public static Iterator findAll(IEvent event) {
		IHome home = new Home();
		Iterator familyMemberPhones = home.findAll(event, FamilyMemberPhone.class);
		return familyMemberPhones;
	}//end of pd.juvenilecase.family.FamilyMemberPhone.findAll

	/**
	 *  
	 * @param memberNum
	 */
	public static FamilyMemberPhone find(String memberNum) {
		IHome home = new Home();
		FamilyMemberPhone familyPhone = (FamilyMemberPhone) home.find(memberNum, FamilyMemberPhone.class);
		return familyPhone;    
	}//end of pd.juvenilecase.family.FamilyMemberPhone.find

	/**
	* Finds all phones by an attribute value
	* @param attributeName
	* @param attributeValue
	* @return 
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		IHome home = new Home();
		Iterator familyMemberPhones = home.findAll(attributeName, attributeValue, FamilyMemberPhone.class);
		return familyMemberPhones;
	}


} // end FamilyMemberPhone
