package pd.juvenilecase;// no longer in use. Migrated to SM. Refer US #87188. No references in the mapping file.
    /*MIGRATED SQL-PART OF REFERRAL CONVERSION*/
/*
 * Created on May 25, 2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 
package pd.juvenilecase;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import messaging.family.SaveFamilyMemberFinancialEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.DateUtil;
import naming.PDJuvenileCaseConstants;
import pd.address.Address;
import pd.codetable.Code;
import pd.contact.officer.OfficerProfile;
import pd.juvenile.Juvenile;
import pd.juvenilecase.family.FamilyConstellation;
import pd.juvenilecase.family.FamilyConstellationMember;
import pd.juvenilecase.family.FamilyMember;
import pd.juvenilecase.family.FamilyMemberPhone;
import pd.juvenilecase.family.JuvenileFamilyHelper;

*//**
 * @author mchowdhury To change the template for this generated type comment go
 *         to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 *         Comments
 *//*
public final class JuvenileCasefileGenerator extends CasefileExtractionUtility
{
    *//**
     * @param casefile
     *            JuvenileCasefile
     * @param jjsService
     * @param supervisionType
     * @param officer
     * @param juvenileMap
     *//*
    public static JuvenileCasefile generate(JuvenileCasefile casefile, JJSService jjsService, Code supervisionType, OfficerProfile officer, Map juvenileMap) throws RuntimeException, Exception
    {
	String juvNum = jjsService.getJuvenileNum();
	String supTypeId = supervisionType.getCode();

	Juvenile juv = (Juvenile) juvenileMap.get(juvNum);
	if (juv == null)
	{
		// check if Juvenile master record needs to be created
		juv = Juvenile.findJCJuvenile(juvNum);
		if (juv == null)
		{
			// create juvenile master record
			juv = createJuvenile(juvNum);
			if(juv != null){
				// import family member info
									
				JJSFamily juvfamily = JJSFamily.find(juvNum);
				if (juvfamily != null)
				{
					FamilyConstellation familyConstellation = createFamilyConstellation(juvfamily, juv.getJuvenileNum());
					// add constellation to juvenile
					if (familyConstellation != null)
					{
						juv.insertFamilyConstellationList(familyConstellation);
					} else {
						JuvenileNotificationGenerator.sendMissingConstellationNotification(juvNum, officer, jjsService.getAddDate());
					}
				} 
				juvfamily = null;
			}
		}
		juvenileMap.put(juvNum, juv);
	}

	//TODO Check if the kid has a family constellation else send notification to the officer. Create juvenile *SAVEJUVENILEPROFILEMAINCOMMAND* is already creating the JC juvenile and family constellation.
	//JuvenileNotificationGenerator.sendMissingConstellationNotification(juvNum, officer, jjsService.getAddDate());

	// new Casefile needs to be initiated
	casefile = new JuvenileCasefile();
	casefile.setJuvenileId(juvNum);
	casefile.setProbationOfficer(officer);
	casefile.setSupervisionTypeId(supTypeId);
	casefile.setSequenceNumber("0"); //bug fix 22374 
	casefile.setCaseStatusId(PDJuvenileCaseConstants.CASESTATUS_PENDING);
	casefile.setActivationDate(new Date());
	casefile.setCreateUserID(PDJuvenileCaseConstants.CASEFILE_CREATOR);
	casefile.setCreateJIMS2UserID(PDJuvenileCaseConstants.JIMS2_CASEFILE_CREATOR);
	juv = null; // free up memory		
	IHome home = new Home();
	return (JuvenileCasefile) home.bind(casefile);
    }

    *//**
     * @param juvfamily
     * @param juvNum
     *//*

    private static FamilyConstellation createFamilyConstellation(JJSFamily juvfamily, String juvNum) throws RuntimeException, Exception
    {
	FamilyConstellation familyConstellation = null;

	// check if member exists
	FamilyMember mother = null;
	ArrayList suspMemberIds = new ArrayList();
	if ((juvfamily.getMotherFirstName() != null && juvfamily.getMotherFirstName().trim().length() > 0) || (juvfamily.getMotherMiddleName() != null && juvfamily.getMotherMiddleName().trim().length() > 0) || (juvfamily.getMotherLastName() != null && juvfamily.getMotherLastName().trim().length() > 0))
	{
	    if (juvfamily.getMotherSsn() != null && !juvfamily.getMotherSsn().equals("") && !juvfamily.getMotherSsn().equals("666666666") && !juvfamily.getMotherSsn().equals("777777777") && !juvfamily.getMotherSsn().equals("888888888") && !juvfamily.getMotherSsn().equals("999999999"))
	    {
		//check if any other family member has the same ssn
		Iterator familyMembers = JuvenileCaseHelper.checkFamilyMemberSSN(juvfamily.getMotherSsn());
		while (familyMembers.hasNext())
		{
		    FamilyMember member = (FamilyMember) familyMembers.next();
		    //check if they have the same name
		    if (member.getFirstName().equalsIgnoreCase(juvfamily.getMotherFirstName()) && member.getLastName().equalsIgnoreCase(juvfamily.getMotherLastName()))
		    {
			mother = member;
		    }
		    //if not same name, create new member and mark both as suspicious
		    else
		    {
			suspMemberIds.add(member.getFamilyMemberId());

		    }
		}
	    }
	}
	boolean isGaurdianAvailable = false;
	if (mother == null)
	{
	    mother = getFamilyMember(juvfamily.getMotherFirstName(), juvfamily.getMotherMiddleName(), juvfamily.getMotherLastName(), juvfamily.getMotherAddress(), juvfamily.getMotherPhone(), juvfamily.getMotherSsn());
	}

	//suspicious
	if (suspMemberIds != null && !suspMemberIds.isEmpty())
	{
	    Iterator iter = suspMemberIds.iterator();
	    while (iter.hasNext())
	    {
		String memberId = (String) iter.next();
		JuvenileCaseHelper.markMembersSuspicious(memberId, mother.getFamilyMemberId());
	    }
	}

	// add member to constellation
	if (mother != null)
	{
	    if (familyConstellation == null)
		familyConstellation = createFamilyConstellation(juvNum);
	    FamilyConstellationMember familyConstellationMember = createCostellationMember(mother, "BM");
	    familyConstellationMember.setGuardian(true);
	    isGaurdianAvailable = true;
	    familyConstellation.insertFamilyConstellationMembers(familyConstellationMember);
	    SaveFamilyMemberFinancialEvent aEvent = new SaveFamilyMemberFinancialEvent();
	    aEvent.setConstellationMemberId(Integer.parseInt(familyConstellationMember.getTheFamilyMemberId()));
	    aEvent.setConstellationNum(familyConstellationMember.getFamilyConstellationId());
	    JuvenileFamilyHelper.saveConstellationMemberFinancial(aEvent, familyConstellationMember);
	}

	// father
	FamilyMember father = null;
	suspMemberIds = new ArrayList();

	if ((juvfamily.getFatherFirstName() != null && juvfamily.getFatherFirstName().trim().length() > 0) || (juvfamily.getFatherMiddleName() != null && juvfamily.getFatherMiddleName().trim().length() > 0) || (juvfamily.getFatherLastName() != null && juvfamily.getFatherLastName().trim().length() > 0))
	{
	    if (juvfamily.getFatherSsn() != null && !juvfamily.getFatherSsn().equals("") && !juvfamily.getFatherSsn().equals("666666666") && !juvfamily.getFatherSsn().equals("777777777") && !juvfamily.getFatherSsn().equals("888888888") && !juvfamily.getFatherSsn().equals("999999999"))
	    {
		Iterator familyMembers = JuvenileCaseHelper.checkFamilyMemberSSN(juvfamily.getFatherSsn());
		while (familyMembers.hasNext())
		{
		    FamilyMember member = (FamilyMember) familyMembers.next();
		    //check if they have the same name
		    if (member.getFirstName().equalsIgnoreCase(juvfamily.getFatherFirstName()) && member.getLastName().equalsIgnoreCase(juvfamily.getFatherLastName()))
		    {
			father = member;
		    }
		    //if not same name, create new member and mark both as suspicious
		    else
		    {
			suspMemberIds.add(member.getFamilyMemberId());

		    }
		}
	    }
	}
	if (father == null)
	{
	    father = getFamilyMember(juvfamily.getFatherFirstName(), juvfamily.getFatherMiddleName(), juvfamily.getFatherLastName(), juvfamily.getFatherAddress(), juvfamily.getFatherPhone(), juvfamily.getFatherSsn());
	}
	//suspicious
	if (suspMemberIds != null && !suspMemberIds.isEmpty())
	{
	    Iterator iter = suspMemberIds.iterator();
	    while (iter.hasNext())
	    {
		String memberId = (String) iter.next();
		JuvenileCaseHelper.markMembersSuspicious(memberId, father.getFamilyMemberId());
	    }
	}
	// add member to constellation
	if (father != null)
	{
	    if (familyConstellation == null)
		familyConstellation = createFamilyConstellation(juvNum);
	    FamilyConstellationMember familyConstellationMember = createCostellationMember(father, "BF");
	    familyConstellationMember.setGuardian(true);
	    isGaurdianAvailable = true;
	    familyConstellation.insertFamilyConstellationMembers(familyConstellationMember);
	    SaveFamilyMemberFinancialEvent aEvent = new SaveFamilyMemberFinancialEvent();
	    aEvent.setConstellationMemberId(Integer.parseInt(familyConstellationMember.getTheFamilyMemberId()));
	    aEvent.setConstellationNum(familyConstellationMember.getFamilyConstellationId());
	    JuvenileFamilyHelper.saveConstellationMemberFinancial(aEvent, familyConstellationMember);
	}

	//other
	FamilyMember other = null;
	suspMemberIds = new ArrayList();
	if ((juvfamily.getOtherFirstName() != null && juvfamily.getOtherFirstName().trim().length() > 0) || (juvfamily.getOtherMiddleName() != null && juvfamily.getOtherMiddleName().trim().length() > 0) || (juvfamily.getOtherLastName() != null && juvfamily.getOtherLastName().trim().length() > 0))
	{
	    if (juvfamily.getOtherSsn() != null && !juvfamily.getOtherSsn().equals("") && !juvfamily.getOtherSsn().equals("666666666") && !juvfamily.getOtherSsn().equals("777777777") && !juvfamily.getOtherSsn().equals("888888888") && !juvfamily.getOtherSsn().equals("999999999"))
	    {
		//check if any other family member has the same ssn
		Iterator familyMembers = JuvenileCaseHelper.checkFamilyMemberSSN(juvfamily.getOtherSsn());
		while (familyMembers.hasNext())
		{
		    FamilyMember member = (FamilyMember) familyMembers.next();
		    //check if they have the same name
		    if (member.getFirstName().equalsIgnoreCase(juvfamily.getOtherFirstName()) && member.getLastName().equalsIgnoreCase(juvfamily.getOtherLastName()))
		    {
			other = member;
		    }
		    //if not same name, create new member and mark both as suspicious
		    else
		    {
			suspMemberIds.add(member.getFamilyMemberId());

		    }
		}
	    }
	}
	if (other == null)
	{
	    other = getFamilyMember(juvfamily.getOtherFirstName(), juvfamily.getOtherMiddleName(), juvfamily.getOtherLastName(), juvfamily.getOtherAddress(), juvfamily.getOtherPhone(), juvfamily.getOtherSsn());
	}
	//suspicious
	if (suspMemberIds != null && !suspMemberIds.isEmpty())
	{
	    Iterator iter = suspMemberIds.iterator();
	    while (iter.hasNext())
	    {
		String memberId = (String) iter.next();
		JuvenileCaseHelper.markMembersSuspicious(memberId, other.getFamilyMemberId());
	    }
	}
	// add member to constellation
	if (other != null)
	{
	    if (familyConstellation == null)
		familyConstellation = createFamilyConstellation(juvNum);

	    FamilyConstellationMember familyConstellationMember = createCostellationMember(other, "OR");
	    familyConstellation.insertFamilyConstellationMembers(familyConstellationMember);
	    if (isGaurdianAvailable == false)
	    {
		familyConstellationMember.setGuardian(true);
		SaveFamilyMemberFinancialEvent aEvent = new SaveFamilyMemberFinancialEvent();
		aEvent.setConstellationMemberId(Integer.parseInt(familyConstellationMember.getTheFamilyMemberId()));
		aEvent.setConstellationNum(familyConstellationMember.getFamilyConstellationId());
		JuvenileFamilyHelper.saveConstellationMemberFinancial(aEvent, familyConstellationMember);
	    }
	}
	return familyConstellation;
    }

    *//**
     * @param firstName
     * @param middleName
     * @param lastName
     * @param memberAddress
     * @param memberPhoneNum
     * @param ssn
     * @return
     *//*
    private static FamilyMember getFamilyMember(String firstName, String middleName, String lastName, Address memberAddress, String memberPhoneNum, String ssn) throws RuntimeException, Exception
    {
	FamilyMember member = null;
	if ((firstName != null && firstName.trim().length() > 0) || (middleName != null && middleName.trim().length() > 0) || (lastName != null && lastName.trim().length() > 0))
	{
	    //TODO need to check if member exists
	    member = new FamilyMember();
	    member.setFirstName(firstName);
	    member.setLastName(lastName);
	    member.setMiddleName(middleName);
	    if (ssn == null || ssn.trim().equals(""))
		ssn = "666666666";
	    member.setSsn(ssn);
	    member.setCreateUserID(PDJuvenileCaseConstants.CASEFILE_CREATOR);
	    // create address
	    if (memberAddress != null)
	    {
		Address address = null;
		// check if address exists 
		Iterator iter = Address.findAll("streetName", memberAddress.getStreetName());
		if (iter.hasNext())
		{
		    address = (Address) iter.next();
		}
		else
		{
		    address = new Address();
		    address.setStreetName(memberAddress.getStreetName());
		    address.setStreetNum(memberAddress.getStreetNum());
		    address.setStreetTypeId(memberAddress.getStreetTypeId());
		    address.setAptNum(memberAddress.getAptNum());
		    address.setAddressTypeId(memberAddress.getAddressTypeId());
		    address.setStateId(memberAddress.getStateId());
		    address.setCity(memberAddress.getCity());
		    address.setZipCode(memberAddress.getZipCode());
		    address.setCreateUserID(PDJuvenileCaseConstants.CASEFILE_CREATOR);
		    address.setCreateJIMS2UserID(PDJuvenileCaseConstants.JIMS2_CASEFILE_CREATOR);
		    address.setValidated("N");
		}

		member.insertAddresses(address);
	    }

	    FamilyMemberPhone memberPhone = null;
	    if (memberPhoneNum != null && memberPhoneNum.trim().length() > 0)
		memberPhone = JuvenileFamilyHelper.createMemberPhone(memberPhoneNum, "", "HM", (String) member.getOID());

	    //add phonenum to member
	    if (memberPhone != null)
	    {
		member.insertPhoneNumbers(memberPhone);
	    }

	    if (member.getOID() == null)
	    {
		IHome home = new Home();
		home.bind(member);
	    }
	}
	return member;
    }

    
     * @param juvNum
     
    private static FamilyConstellation createFamilyConstellation(String juvNum)
    {
	FamilyConstellation familyConstellation = new FamilyConstellation();
	familyConstellation.setEntryDate(new Date());
	familyConstellation.setJuvenileId(juvNum);
	familyConstellation.setActive(true);
	familyConstellation.setCreateJIMS2UserID(PDJuvenileCaseConstants.JIMS2_CASEFILE_CREATOR);
	familyConstellation.setCreateUserID(PDJuvenileCaseConstants.CASEFILE_CREATOR);
	return familyConstellation;
    }

    *//**
     * @param mother
     * @param relationshipId
     * @return FamilyConstellationMember
     *//*
    private static FamilyConstellationMember createCostellationMember(FamilyMember member, String relationshipId)
    {
	FamilyConstellationMember familyConstellationMember = null;
	if (member != null)
	{
	    familyConstellationMember = new FamilyConstellationMember();
	    familyConstellationMember.setTheFamilyMemberId((String) member.getOID());
	    familyConstellationMember.setRelationshipToJuvenileId(relationshipId);
	    familyConstellationMember.setInHomeStatus(false); // default to No as per requrement
	    familyConstellationMember.setInvolvementLevelId(PDJuvenileCaseConstants.FAMILY_MEMBER_INVOLVEMENT_LVL_LOW); // default to LOW as per requirements
	    familyConstellationMember.setCreateJIMS2UserID(PDJuvenileCaseConstants.JIMS2_CASEFILE_CREATOR);
	    familyConstellationMember.setCreateUserID(PDJuvenileCaseConstants.CASEFILE_CREATOR);
	}
	return familyConstellationMember;
    }

    *//**
     * @param juvenileNum
     *//*
    private static Juvenile createJuvenile(String juvenileNum) throws RuntimeException, Exception
    {
	Juvenile juvenile = null;
	JJSJuvenile jjsJuvenile = JJSJuvenile.find(juvenileNum);
	if (jjsJuvenile != null)
	{
	    Date birthDate = jjsJuvenile.getBirthDate();
	    //  06/08/2012 - revised max age from 17 to 19 per ER 71590
	    if (getAgeInYears(birthDate) < 10 || getAgeInYears(birthDate) > 19)
	    {
		throw new Exception("Juvenile can not be created as Juvenile age is not in between 10-19.");
	    }

	    juvenile = new Juvenile();
	    populateJuvenileFromJJSJuvenile(jjsJuvenile, juvenile);
	    IHome home = new Home();
	    home.bind(juvenile);
	}
	return juvenile;
    }

    *//**
     * @param jjsJuvenile
     * @param juvenile
     * @throws Exception
     * @throws
     *//*
    private static void populateJuvenileFromJJSJuvenile(JJSJuvenile jjsJuvenile, Juvenile juvenile)
    {
	juvenile.setJuvenileNum(jjsJuvenile.getJuvenileNum());
	//ER Fix:JIMS200077276 defaulting student id to the juvenileNum.
	juvenile.setStudentId(jjsJuvenile.getJuvenileNum());
	juvenile.setCreateUserID(PDJuvenileCaseConstants.CASEFILE_CREATOR);
	juvenile.setCreateJIMS2UserID(PDJuvenileCaseConstants.JIMS2_CASEFILE_CREATOR);
	juvenile.setCreateTimestamp(new Timestamp(DateUtil.getCurrentDate().getTime()));
    }
}
*/