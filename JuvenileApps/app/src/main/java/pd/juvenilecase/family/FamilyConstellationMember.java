package pd.juvenilecase.family;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.codetable.Code;

/**
* Class FamilyConstellationMember.
* @author Anand Thorat
* @version 1.0.0
*/
public class FamilyConstellationMember extends mojo.km.persistence.PersistentObject {
	/**
	* Properties for familyConstellations
	* @referencedType pd.juvenilecase.family.FamilyConstellation
	* @detailerDoNotGenerate true
	*/
	private FamilyConstellation familyConstellation = null;
	private Date confirmedDate;
	private String involvementLevelId;
	private String familyConstellationId;
	/**
	* Properties for FamilyMember
	* @referencedType pd.juvenilecase.family.FamilyMember
	* @detailerDoNotGenerate true
	*/
	public FamilyMember theFamilyMember = null;
	private String relationshipToJuvenileId;
	/**
	* Properties for FamilyMemberFinancial
	* @referencedType pd.juvenilecase.family.FamilyMemberFinancial
	* @detailerDoNotGenerate true
	*/
	private Collection constellationMemberFinancials;
	/**
	* @referencedType pd.codetable.Code
	* @detailerDoNotGenerate true
	* @contextKey INVOLVEMENT_LEVEL
	*/
	public Code involvementLevel = null;
	/**
	* @referencedType pd.codetable.Code
	* @detailerDoNotGenerate true
	* @contextKey JUVENILE_RELATIONSHIP
	*/
	public Code relationshipToJuvenile = null;
	private boolean isGuardian;
	private boolean primaryCareGiver; //Er changes 11063
	private String theFamilyMemberId;
	private boolean isInHomeStatus;
	private boolean parentalRightsTerminated;
	private boolean detentionHearing;  
	private boolean detentionVisitation;
	private boolean primaryContact;
	private String createUserId;
	private Date createTimeStamp;
	private String updateUser;
	private Date updateDate;
	/**
	* @roseuid 4321A66F0148
	*/
	public FamilyConstellationMember() {
	}
	/**
	* @roseuid 4321A3250261
	*/
	public void find() {
		fetch();
	}
	/**
	* Set the reference value to class :: pd.juvenilecase.family.FamilyMember
	* @param theFamilyMemberId The the family member id.
	*/
	public void setTheFamilyMemberId(String theFamilyMemberId) {
		if (this.theFamilyMemberId == null || !this.theFamilyMemberId.equals(theFamilyMemberId)) {
			markModified();
		}
		theFamilyMember = null;
		this.theFamilyMemberId = theFamilyMemberId;
	}
	/**
	* Get the reference value to class :: pd.juvenilecase.family.FamilyMember
	* @return The the family member id.
	*/
	public String getTheFamilyMemberId() {
		fetch();
		return theFamilyMemberId;
	}
	/**
	* Gets referenced type pd.juvenilecase.family.FamilyMember
	* @return The the family member.
	*/
	public FamilyMember getTheFamilyMember() {
		fetch();
		initTheFamilyMember();
		return theFamilyMember;
	}
	/**
	* set the type reference for class member theFamilyMember
	* @param theFamilyMember The the family member.
	*/
	public void setTheFamilyMember(FamilyMember theFamilyMember) {
		if (this.theFamilyMember == null || !this.theFamilyMember.equals(theFamilyMember)) {
			markModified();
		}
		if (theFamilyMember.getOID() == null) {
			new mojo.km.persistence.Home().bind(theFamilyMember);
		}
		setTheFamilyMemberId("" + theFamilyMember.getOID());
		this.theFamilyMember = (FamilyMember) new mojo.km.persistence.Reference(theFamilyMember).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param relationshipToJuvenileId The relationship to juvenile id.
	*/
	public void setRelationshipToJuvenileId(String relationshipToJuvenileId) {
		if (this.relationshipToJuvenileId == null || !this.relationshipToJuvenileId.equals(relationshipToJuvenileId)) {
			markModified();
		}
		relationshipToJuvenile = null;
		this.relationshipToJuvenileId = relationshipToJuvenileId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @return The relationship to juvenile id.
	*/
	public String getRelationshipToJuvenileId() {
		fetch();
		return relationshipToJuvenileId;
	}
	/**
	* Gets referenced type pd.codetable.Code
	* @return The relationship to juvenile.
	*/
	public Code getRelationshipToJuvenile() {
		fetch();
		initRelationshipToJuvenile();
		return relationshipToJuvenile;
	}
	/**
	* set the type reference for class member relationshipToJuvenile
	* @param relationshipToJuvenile The relationship to juvenile.
	*/
	public void setRelationshipToJuvenile(Code relationshipToJuvenile) {
		if (this.relationshipToJuvenile == null || !this.relationshipToJuvenile.equals(relationshipToJuvenile)) {
			markModified();
		}
		if (relationshipToJuvenile.getOID() == null) {
			new mojo.km.persistence.Home().bind(relationshipToJuvenile);
		}
		setRelationshipToJuvenileId("" + relationshipToJuvenile.getOID());
		relationshipToJuvenile.setContext("JUVENILE_RELATIONSHIP");
		this.relationshipToJuvenile = (Code) new mojo.km.persistence.Reference(relationshipToJuvenile).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param involvementLevelId The involvement level id.
	*/
	public void setInvolvementLevelId(String involvementLevelId) {
		if (this.involvementLevelId == null || !this.involvementLevelId.equals(involvementLevelId)) {
			markModified();
		}
		involvementLevel = null;
		this.involvementLevelId = involvementLevelId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @return The involvement level id.
	*/
	public String getInvolvementLevelId() {
		fetch();
		return involvementLevelId;
	}
	/**
	* Gets referenced type pd.codetable.Code
	* @return The involvement level.
	*/
	public Code getInvolvementLevel() {
		fetch();
		initInvolvementLevel();
		return involvementLevel;
	}
	/**
	* set the type reference for class member involvementLevel
	* @param involvementLevel The involvement level.
	*/
	public void setInvolvementLevel(Code involvementLevel) {
		if (this.involvementLevel == null || !this.involvementLevel.equals(involvementLevel)) {
			markModified();
		}
		if (involvementLevel.getOID() == null) {
			new mojo.km.persistence.Home().bind(involvementLevel);
		}
		setInvolvementLevelId("" + involvementLevel.getOID());
		involvementLevel.setContext("INVOLVEMENT_LEVEL");
		this.involvementLevel = (Code) new mojo.km.persistence.Reference(involvementLevel).getObject();
	}
	/**
	* @return The confirmed date.
	*/
	public Date getConfirmedDate() {
		fetch();
		return confirmedDate;
	}
	/**
	* @return The family constellation id.
	*/
	public String getFamilyConstellationId() {
		fetch();
		return familyConstellationId;
	}
	/**
	* @return The boolean.
	*/
	public boolean isGuardian() {
		fetch();
		return isGuardian;
	}
	
	public String getGuardianYesNo()
	{
	    if (this.isGuardian())
		return "YES";
	    else
		return "NO";
	}
	
	public boolean isPrimaryCareGiver() {
		fetch();
		return primaryCareGiver;
	}
	
	public String getIsPrimaryCareGiverYesNo()
	{
	    if (this.isPrimaryCareGiver())
		return "YES";
	    else
		return "NO";
	}
	
	/**
	* @return The boolean.
	*/
	public boolean isInHomeStatus() {
		fetch();
		return isInHomeStatus;
	}
	
	public String getInHomeStatusYesNo()
	{
	    if (this.isInHomeStatus())
		return "YES";
	    else
		return "NO";
	}
	
	/**
	* @return The boolean.
	 */
	public boolean isParentalRightsTerminated() {
		fetch();
		return parentalRightsTerminated;
	}
	
	public String getIsParentalRightsTerminatedYesNo()
	{
	    if (this.isParentalRightsTerminated())
		return "YES";
	    else
		return "NO";
	}

	/**
	* @return The boolean.
	 */
	public boolean isDetentionHearing() {
		fetch();
		return detentionHearing;
	}
	
	public String getIsDetentionHearingYesNo()
	{
	    if (this.isDetentionHearing())
		return "YES";
	    else
		return "NO";
	}
	
	/**
	* @return The boolean.
	 */
	public boolean isDetentionVisitation() {
		fetch();
		return detentionVisitation;
	}
	
	public String getIsDetentionVisitationYesNo()
	{
	    if (this.isDetentionVisitation())
		return "YES";
	    else
		return "NO";
	}
	
	/**
	* @return The boolean.
	 */
	public boolean isPrimaryContact() {
		fetch();
		return primaryContact;
	}
	
	public String getIsPrimaryContactYesNo()
	{
	    if (this.isPrimaryContact())
		return "YES";
	    else
		return "NO";
	}
	/**
	* @param date The confirmed date.
	*/
	public void setConfirmedDate(Date date) {
		if (this.confirmedDate == null || !this.confirmedDate.equals(date)) {
			markModified();
		}
		confirmedDate = date;
	}
	/**
	* @param string The family constellation id.
	*/
	public void setFamilyConstellationId(String string) {
		if (this.familyConstellationId == null || !this.familyConstellationId.equals(string)) {
			markModified();
		}
		familyConstellationId = string;
	}
	/**
	* @param b The guardian.
	*/
	public void setGuardian(boolean b) {
		if (this.isGuardian != b) {
			markModified();
		}
		isGuardian = b;
	}
	
	public void setPrimaryCareGiver(boolean primaryCareGiver) {
		if (this.primaryCareGiver != primaryCareGiver) {
			markModified();
		}
		this.primaryCareGiver = primaryCareGiver;
	}
	
	/**
	* @param b The in home status.
	*/
	public void setInHomeStatus(boolean b) {
		if (this.isInHomeStatus != b) {
			markModified();
		}
		isInHomeStatus = b;
	}
	
	/**
	* @param b The parental rights terminated.
	*/
	public void setParentalRightsTerminated(boolean b) {
		if (this.parentalRightsTerminated != b) {
			markModified();
		}
		parentalRightsTerminated = b;
	}
	
	/**
	* @param b The Detention Hearing.
	*/
	public void setDetentionHearing(boolean b) {
		if (this.detentionHearing != b) {
			markModified();
		}
		detentionHearing = b;
	}
	
	/**
	* @param b The Detention Visitation.
	*/
	public void setDetentionVisitation(boolean b) {
		if (this.detentionVisitation != b) {
			markModified();
		}
		detentionVisitation = b;
	}
	/**
	* @param b The primary contact.
	*/
	public void setPrimaryContact(boolean b) {
		if (this.primaryContact != b) {
			markModified();
		}
		primaryContact = b;
	}
	public void bind() {
		markModified();
	}
	/**
	* Initialize class relationship to class pd.juvenilecase.family.FamilyMember
	*/
	private void initTheFamilyMember() {
		if (theFamilyMember == null) {
			theFamilyMember = (FamilyMember) new mojo.km.persistence.Reference(theFamilyMemberId, FamilyMember.class).getObject();
		}
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initRelationshipToJuvenile() {
		if (relationshipToJuvenile == null) {
			relationshipToJuvenile =
				(Code) new mojo.km.persistence.Reference(relationshipToJuvenileId, Code.class, "JUVENILE_RELATIONSHIP").getObject();
		}
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initInvolvementLevel() {
		if (involvementLevel == null) {
			involvementLevel = (Code) new mojo.km.persistence.Reference(involvementLevelId, Code.class, "INVOLVEMENT_LEVEL").getObject();
		}
	}
	/**
	* @param searchEvent
	* @return 
	*/
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return home.findAll(event, FamilyConstellationMember.class);
	}
	/**
	* Finds all casefiles by an attribute value
	* @param attributeName
	* @param attributeValue
	* @return 
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		IHome home = new Home();
		Iterator familyConstellationMembers = home.findAll(attributeName, attributeValue, FamilyConstellationMember.class);
		return familyConstellationMembers;
	}
	static public FamilyConstellationMember find(String familyConstellationMemberId) {
		IHome home = new Home();
		FamilyConstellationMember familyConstellationMember = (FamilyConstellationMember) home.find(familyConstellationMemberId, FamilyConstellationMember.class);
		return familyConstellationMember;
	}
	/**
	* Initialize class relationship implementation for pd.juvenilecase.family.FamilyMemberFinancial
	*/
	private void initConstellationMemberFinancials() {
		if (constellationMemberFinancials == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			constellationMemberFinancials = new mojo.km.persistence.ArrayList(FamilyMemberFinancial.class, "familyConstellationMemberId", "" + getOID());
		}
	}
	/**
	* returns a collection of pd.juvenilecase.family.FamilyMemberFinancial
	*/
	public Collection getConstellationMemberFinancials() {
		fetch();
		initConstellationMemberFinancials();
		return constellationMemberFinancials;
	}
	/**
	* insert a pd.juvenilecase.family.FamilyMemberFinancial into class relationship collection.
	*/
	public void insertConstellationMemberFinancials(FamilyMemberFinancial anObject) {
		initConstellationMemberFinancials();
		constellationMemberFinancials.add(anObject);
	}
	/**
	* Removes a pd.juvenilecase.family.FamilyMemberFinancial from class relationship collection.
	*/
	public void removeConstellationMemberFinancials(FamilyMemberFinancial anObject) {
		initConstellationMemberFinancials();
		constellationMemberFinancials.remove(anObject);
	}
	/**
	* Clears all pd.juvenilecase.family.FamilyMemberFinancial from class relationship collection.
	*/
	public void clearConstellationMemberFinancials() {
		initConstellationMemberFinancials();
		constellationMemberFinancials.clear();
	}
	/**
	* Initialize class relationship to class pd.juvenilecase.family.FamilyConstellation
	*/
	private void initFamilyConstellation() {
		if (familyConstellation == null) {
			familyConstellation =
				(FamilyConstellation) new mojo.km.persistence.Reference(familyConstellationId, FamilyConstellation.class).getObject();
		}
	}
	/**
	* Gets referenced type pd.juvenilecase.family.FamilyConstellation
	*/
	public FamilyConstellation getFamilyConstellation() {
		initFamilyConstellation();
		return familyConstellation;
	}
	/**
	* set the type reference for class member familyConstellation
	*/
	public void setFamilyConstellation(FamilyConstellation familyConstellation) {
		if (this.familyConstellation == null || !this.familyConstellation.equals(familyConstellation)) {
			markModified();
		}
		if (familyConstellation.getOID() == null) {
			new mojo.km.persistence.Home().bind(familyConstellation);
		}
		setFamilyConstellationId("" + familyConstellation.getOID());
		this.familyConstellation = (FamilyConstellation) new mojo.km.persistence.Reference(familyConstellation).getObject();
	}
	
	public String getCreateUserId() {
	    	fetch();
		return createUserId;
	}
	public void setCreateUserId(String createUser) {
		this.createUserId = createUser;
	}
	public Date getCreateTimeStamp() {
	    	fetch();
		return createTimeStamp;
	}
	public void setCreateTimeStamp(Date createDate) {
		this.createTimeStamp = createDate;
	}
	public String getUpdateUser() {
	    	fetch();
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Date getUpdateDate() {
	    	fetch();
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}
