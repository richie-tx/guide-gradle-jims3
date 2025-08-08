/*
 * Project: JIMS2
 * Class:   pd.juvenilecase.family.FamilyConstellation
 * Version: 1.0.0
 *
 * Date:    2005-09-19
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package pd.juvenilecase.family;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;

/**
 * Class FamilyConstellation.
 *  
 * @author  Anand Thorat
 * @version  1.0.0
 */
public class FamilyConstellation extends mojo.km.persistence.PersistentObject {

    // ------------------------------------------------------------------------
    // --- fields                                                           ---
    // ------------------------------------------------------------------------

    /**
     * Properties for traits
     * @detailerDoNotGenerate false
     * @referencedType pd.juvenilecase.family.FamilyTrait
     */
    public Collection traits;

    /**
     * Properties for FamilyMember
     * @detailerDoNotGenerate false
     * @referencedType pd.juvenilecase.family.FamilyConstellationMember
     */
    public Collection familyConstellationMembers;

    private boolean active;

    private String familyConstellationId;

    private String juvenileId;

    private Date entryDate;
    private String entryDateString;
    
	private String lastName;
    
    private String middleName;

    private String firstName;

    private String juvRelation;

    private boolean guardian;

    private int memberNum;

    private String familyMemberId;
    private String suspiciousMemberMatch;
    
	private Timestamp entryDateTimestamp;
    
    private int financialId;
	private String familyNum;


    /**
     * Properties for juvenile
     * @detailerDoNotGenerate false
     * @referencedType pd.juvenile.Juvenile
     */
    //private Juvenile juvenile = null;
	private JuvenileCore juvenile = null;


    // ------------------------------------------------------------------------
    // --- constructor                                                      ---
    // ------------------------------------------------------------------------

    /**
     * @roseuid 431F36910203
     */
    public FamilyConstellation() {

    }//end of pd.juvenilecase.family.FamilyConstellation.FamilyConstellation


    // ------------------------------------------------------------------------
    // --- methods                                                          ---
    // ------------------------------------------------------------------------

    /**
     *  
     * @param constelltionNum @roseuid 431F1801017B
     */
    public static FamilyConstellation find(String familyNum) {
		IHome home = new Home();
		FamilyConstellation family = (FamilyConstellation) home.find(familyNum, FamilyConstellation.class);
		return family;    
	}//end of pd.juvenilecase.family.FamilyConstellation.find

    /**
     * @roseuid 431F18010187
     */
    public void bind() {
        markModified();
    }//end of pd.juvenilecase.family.FamilyConstellation.bind


    /**
     * returns a collection of pd.juvenilecase.family.FamilyTrait
     *  
     * @return  The traits.
     */
    public Collection getTraits() {
        fetch();
        initTraits();
        return traits;
    }//end of pd.juvenilecase.family.FamilyConstellation.getTraits

    /**
     * insert a pd.juvenilecase.family.FamilyTrait into class relationship collection.
     *  
     * @param anObject The an object.
     */
    public void insertTraits(FamilyTrait anObject) {
        initTraits();
        traits.add(anObject);
    }//end of pd.juvenilecase.family.FamilyConstellation.insertTraits

    /**
     * Removes a pd.juvenilecase.family.FamilyTrait from class relationship collection.
     *  
     * @param anObject The an object.
     */
    public void removeTraits(FamilyTrait anObject) {
        initTraits();
        traits.remove(anObject);
    }//end of pd.juvenilecase.family.FamilyConstellation.removeTraits

    /**
     * Clears all pd.juvenilecase.family.FamilyTrait from class relationship collection.
     */
    public void clearTraits() {
        initTraits();
        traits.clear();
    }//end of pd.juvenilecase.family.FamilyConstellation.clearTraits

    /**
     * returns a collection of pd.juvenilecase.family.FamilyConstellationMember
     *  
     * @return  The family constellation members.
     */
    public Collection getFamilyConstellationMembers() {
        fetch();
        initFamilyConstellationMembers();
        return familyConstellationMembers;
    }//end of pd.juvenilecase.family.FamilyConstellation.getFamilyConstellationMembers

    /**
     * insert a pd.juvenilecase.family.FamilyConstellationMember into class relationship collection.
     *  
     * @param anObject The an object.
     */
    public void insertFamilyConstellationMembers(FamilyConstellationMember anObject) {
        initFamilyConstellationMembers();
        familyConstellationMembers.add(anObject);
    }//end of pd.juvenilecase.family.FamilyConstellation.insertFamilyConstellationMembers

    /**
     * Removes a pd.juvenilecase.family.FamilyConstellationMember from class relationship collection.
     *  
     * @param anObject The an object.
     */
    public void removeFamilyConstellationMembers(FamilyConstellationMember anObject) {
        initFamilyConstellationMembers();
        familyConstellationMembers.remove(anObject);
    }//end of pd.juvenilecase.family.FamilyConstellation.removeFamilyConstellationMembers

    /**
     * Clears all pd.juvenilecase.family.FamilyConstellationMember from class relationship collection.
     */
    public void clearFamilyConstellationMembers() {
        initFamilyConstellationMembers();
        familyConstellationMembers.clear();
    }//end of pd.juvenilecase.family.FamilyConstellation.clearFamilyConstellationMembers

    /**
     * Set the reference value to class :: pd.juvenile.Juvenile
     *  
     * @param juvenileId The juvenile id.
     */
    public void setJuvenileId(String juvenileId) {
        if (this.juvenileId == null || !this.juvenileId.equals(juvenileId)) {
        	markModified();
        }
        juvenile = null;
        this.juvenileId = juvenileId;
    }//end of pd.juvenilecase.family.FamilyConstellation.setJuvenileId

    /**
     * Get the reference value to class :: pd.juvenile.Juvenile
     *  
     * @return  The juvenile id.
     */
    public String getJuvenileId() {
        fetch();
        return juvenileId;
    }//end of pd.juvenilecase.family.FamilyConstellation.getJuvenileId

    /**
     * Gets referenced type pd.juvenile.Juvenile
     *  
     * @return  The juvenile.
     */
    //public Juvenile getJuvenile() {
    public JuvenileCore getJuvenile() {
        fetch();
        initJuvenile();
        return juvenile;
    }//end of pd.juvenilecase.family.FamilyConstellation.getJuvenile

    /**
     * set the type reference for class member juvenile
     *  
     * @param juvenile The juvenile.
     */
    public void setJuvenile(Juvenile juvenile) {
        if (this.juvenile == null || !this.juvenile.equals(juvenile)) {
        	markModified();
        }
        if (juvenile.getOID() == null) {
        	new mojo.km.persistence.Home().bind(juvenile);
        }
        setJuvenileId("" + juvenile.getOID());
        this.juvenile = (Juvenile) new mojo.km.persistence.Reference(juvenile).getObject();
    }//end of pd.juvenilecase.family.FamilyConstellation.setJuvenile

    /**
     *  
     * @return  The boolean.
     */
    public boolean isActive() {
        fetch();
        return active;
    }//end of pd.juvenilecase.family.FamilyConstellation.isActive

    /**
     *  
     * @return  The entry date.
     */
    public Date getEntryDate() {
        fetch();
        return entryDate;
    }//end of pd.juvenilecase.family.FamilyConstellation.getEntryDate

    /**
     *  
     * @return  The family constellation id.
     */
    public String getFamilyConstellationId() {
        return "" + getOID();
    }//end of pd.juvenilecase.family.FamilyConstellation.getFamilyConstellationId

    /**
     *  
     * @param b The active.
     */
    public void setActive(boolean b) {
        if (this.active != b) {
        	markModified();
        }
        active = b;
    }//end of pd.juvenilecase.family.FamilyConstellation.setActive

    /**
     *  
     * @param date The entry date.
     */
    public void setEntryDate(Date date) {
        if (this.entryDate == null || !this.entryDate.equals(date)) {
        	markModified();
        }
        entryDate = date;
    }//end of pd.juvenilecase.family.FamilyConstellation.setEntryDate

    /**
     *  
     * @param string The family constellation id.
     */
    public void setFamilyConstellationId(String string) {
        if (this.familyConstellationId == null || !this.familyConstellationId.equals(string)) {
        	markModified();
        }
        familyConstellationId = string;
    }//end of pd.juvenilecase.family.FamilyConstellation.setFamilyConstellationId

    /**
     * Initialize class relationship implementation for pd.juvenilecase.family.FamilyTrait
     */
    private void initTraits() {
        if (traits == null) {
        	if (this.getOID() == null) {
        		new mojo.km.persistence.Home().bind(this);
        	}
        	traits = new mojo.km.persistence.ArrayList(FamilyTrait.class, "familyConstellationId", "" + getOID());
        }
    }//end of pd.juvenilecase.family.FamilyConstellation.initTraits

    /**
     * Initialize class relationship implementation for pd.juvenilecase.family.FamilyConstellationMember
     */
    private void initFamilyConstellationMembers() {
        if (familyConstellationMembers == null) {
        	if (this.getOID() == null) {
        		new mojo.km.persistence.Home().bind(this);
        	}
        	familyConstellationMembers = new mojo.km.persistence.ArrayList(FamilyConstellationMember.class, "familyConstellationId", "" + getOID());
        }
    }//end of pd.juvenilecase.family.FamilyConstellation.initFamilyConstellationMembers

    /**
     * Initialize class relationship to class pd.juvenile.Juvenile
     */
    private void initJuvenile() {
        if (juvenile == null) {
//        	juvenile = (pd.juvenile.Juvenile) new mojo.km.persistence.Reference(juvenileId, pd.juvenile.Juvenile.class).getObject();
            		// Profile stripping fix task 97538
			//juvenile = Juvenile.find(juvenileId);
            		juvenile = JuvenileCore.findCore(juvenileId);
        }
    }//end of pd.juvenilecase.family.FamilyConstellation.initJuvenile


    // ------------------------------------------------------------------------
    // --- static method                                                    ---
    // ------------------------------------------------------------------------

    /**
     * Finds FamilyConstellation by a certain event
     *  
     * @param event The event.
     * @return  Iterator of FamilyConstellation
     */
    public static Iterator findAll(IEvent event) {
        IHome home = new Home();
        Iterator familyConstellations = home.findAll(event, FamilyConstellation.class);
        return familyConstellations;
    }//end of pd.juvenilecase.family.FamilyConstellation.findAll

	/**
	 * @return
	 */
	public String getFamilyMemberId()
	{
		fetch();
		return familyMemberId;
	}

	/**
	 * @return
	 */
	public String getFirstName()
	{
		fetch();
		return firstName;
	}

	/**
	 * @return
	 */
	public boolean isGuardian()
	{
		fetch();
		return guardian;
	}

	/**
	 * @return
	 */
	public String getJuvRelation()
	{
		fetch();
		return juvRelation;
	}

	/**
	 * @return
	 */
	public String getLastName()
	{
		fetch();
		return lastName;
	}

	/**
	 * @return
	 */
	public int getMemberNum()
	{
		fetch();
		return memberNum;
	}

	/**
	 * @return
	 */
	public String getMiddleName()
	{
		fetch();
		return middleName;
	}

	/**
	 * @param string
	 */
	public void setFamilyMemberId(String string)
	{
		if (this.familyMemberId == null || !this.familyMemberId.equals(string)) {
			markModified();
		}
		this.familyMemberId = string;
	}

	/**
	 * @param string
	 */
	public void setFirstName(String string)
	{
		if (this.firstName == null || !this.firstName.equals(string)) {
			markModified();
		}
		this.firstName = string;
	}

	/**
	 * @param i
	 */
	public void setGuardian(boolean b)
	{
		if (this.guardian != b) {
			markModified();
		}
		this.guardian = b;
	}

	/**
	 * @param string
	 */
	public void setJuvRelation(String string)
	{
		if (this.juvRelation == null || !this.juvRelation.equals(string)) {
			markModified();
		}
		this.juvRelation = string;
	}

	/**
	 * @param string
	 */
	public void setLastName(String string)
	{
		if (this.lastName == null || !this.lastName.equals(string)) {
			markModified();
		}
		this.lastName = string;
	}

	/**
	 * @param i
	 */
	public void setMemberNum(int i)
	{
		if (this.memberNum != i) {
			markModified();
		}
		this.memberNum = i;
	}

	/**
	 * @param string
	 */
	public void setMiddleName(String string)
	{
		if (this.middleName == null || !this.middleName.equals(string)) {
			markModified();
		}
		this.middleName = string;
	}
	
	/**
	 * @return
	 */
	public int getFinancialId()
	{
		fetch();
		return financialId;
	}

	/**
	 * @return
	 */
	public Timestamp getEntryDateTimestamp()
	{
		fetch();
		return entryDateTimestamp;
	}

	/**
	 * @param i
	 */
	public void setFinancialId(int i)
	{
		if (this.financialId != i) {
			markModified();
		}
		this.financialId = i;
	}

	/**
	 * @param timestamp
	 */
	public void setEntryDateTimestamp(Timestamp timestamp)
	{
	
	   if (this.entryDateTimestamp == null || !this.entryDateTimestamp.equals(timestamp)) {
		   markModified();
	   }
	   entryDateTimestamp = timestamp;
	}
	
	/**
	 * @param string
	 */
	public void setFamilyNum(String string)
	{
		if (this.familyNum== null || !this.familyNum.equals(string)) {
			markModified();
		}
		this.familyNum = string;
	}
	
	/**
	 * @return
	 */
	public String getFamilyNum()
	{
		fetch();
		return this.familyNum;
	}
	
	public String getEntryDateString()
	{
	    return this.entryDateString;
	}
	
	public void setEntryDateString(String date) 
	{
		this.entryDateString = date;
	}


	public String getSuspiciousMemberMatch()
	{
	    return suspiciousMemberMatch;
	}


	public void setSuspiciousMemberMatch(String suspiciousMemberMatch)
	{
	    this.suspiciousMemberMatch = suspiciousMemberMatch;
	}
	
} // end FamilyConstellation
