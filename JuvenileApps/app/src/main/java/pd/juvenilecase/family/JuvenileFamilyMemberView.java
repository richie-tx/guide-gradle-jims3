/*
 * Created on Jul 6, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase.family;

import java.util.Iterator;
import pd.codetable.Code;
import pd.juvenile.Juvenile;
import messaging.juvenilecase.reply.JuvenileFamilyMemberViewResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author jjose
 * 
 */
public class JuvenileFamilyMemberView extends PersistentObject {
	private String juvenileId;
	private Juvenile juvenile = null;
	private String memberNum;
	private String lastName;
	private String middleName;
	private String firstName;
	private boolean deceased;
	private String relationshipToJuvenileId;
	public Code relationshipToJuvenile = null;

	public static Iterator findAll(IEvent event) {
		IHome home = new Home();
		Iterator familyMembers = home.findAll(event,
				JuvenileFamilyMemberView.class);
		return familyMembers;
	}

	public JuvenileFamilyMemberViewResponseEvent getResponseEvent() {
		JuvenileFamilyMemberViewResponseEvent myRespEvt = new JuvenileFamilyMemberViewResponseEvent();
		myRespEvt.setDeceased(this.isDeceased());
		myRespEvt.setFirstName(this.getFirstName());
		myRespEvt.setJuvenileNum(this.getJuvenileId());
		if (this.getRelationshipToJuvenileId() != null
				&& !this.getRelationshipToJuvenileId().equals("")
				&& this.getRelationshipToJuvenile() != null)
			myRespEvt.setJuvRelation(this.getRelationshipToJuvenile()
					.getDescription());
		myRespEvt.setJuvRelationId(this.getRelationshipToJuvenileId());
		myRespEvt.setLastName(this.getLastName());
		myRespEvt.setMemberNum(this.getMemberNum());
		myRespEvt.setMiddleName(this.getMiddleName());
		return myRespEvt;
	}

	/**
	 * @return The boolean.
	 */
	public boolean isDeceased() {
		fetch();
		return deceased;
	}

	public void setDeceased(boolean b) {
		this.deceased = b;
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param relationshipToJuvenileId
	 *            The relationship to juvenile id.
	 */
	public void setRelationshipToJuvenileId(String relationshipToJuvenileId) {
		this.relationshipToJuvenileId = relationshipToJuvenileId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return The relationship to juvenile id.
	 */
	public String getRelationshipToJuvenileId() {
		fetch();
		return relationshipToJuvenileId;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return The relationship to juvenile.
	 */
	public Code getRelationshipToJuvenile() {
		fetch();
		initRelationshipToJuvenile();
		return relationshipToJuvenile;
	}

	/**
	 * set the type reference for class member relationshipToJuvenile
	 * 
	 * @param relationshipToJuvenile
	 *            The relationship to juvenile.
	 */
	public void setRelationshipToJuvenile(Code relationshipToJuvenile) {
		setRelationshipToJuvenileId("" + relationshipToJuvenile.getOID());
		relationshipToJuvenile.setContext("JUVENILE_RELATIONSHIP");
		this.relationshipToJuvenile = (Code) new mojo.km.persistence.Reference(
				relationshipToJuvenile).getObject();
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initRelationshipToJuvenile() {
		if (relationshipToJuvenile == null) {
			relationshipToJuvenile = (Code) new mojo.km.persistence.Reference(
					relationshipToJuvenileId, Code.class,
					"JUVENILE_RELATIONSHIP").getObject();
		}
	}

	/**
	 * @return
	 */
	public String getFirstName() {
		fetch();
		return firstName;
	}

	/**
	 * @return
	 */
	public String getLastName() {
		fetch();
		return lastName;
	}

	/**
	 * @return
	 */
	public String getMemberNum() {
		fetch();
		return memberNum;
	}

	/**
	 * @return
	 */
	public String getMiddleName() {
		fetch();
		return middleName;
	}

	/**
	 * @param string
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param string
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param i
	 */
	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}

	/**
	 * @param string
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * Set the reference value to class :: pd.juvenile.Juvenile
	 * 
	 * @param juvenileId
	 *            The juvenile id.
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}

	/**
	 * Get the reference value to class :: pd.juvenile.Juvenile
	 * 
	 * @return The juvenile id.
	 */
	public String getJuvenileId() {
		fetch();
		return juvenileId;
	}// end of pd.juvenilecase.family.FamilyConstellation.getJuvenileId

	/**
	 * Gets referenced type pd.juvenile.Juvenile
	 * 
	 * @return The juvenile.
	 */
	public Juvenile getJuvenile() {
		fetch();
		initJuvenile();
		return juvenile;
	}// end of pd.juvenilecase.family.FamilyConstellation.getJuvenile

	/**
	 * Initialize class relationship to class pd.juvenile.Juvenile
	 */
	private void initJuvenile() {
		if (juvenile == null) {
			// juvenile = (pd.juvenile.Juvenile) new
			// mojo.km.persistence.Reference(juvenileId,
			// pd.juvenile.Juvenile.class).getObject();
			juvenile = Juvenile.find(juvenileId);
		}
	}// end

	/**
	 * set the type reference for class member juvenile
	 * 
	 * @param juvenile
	 *            The juvenile.
	 */
	public void setJuvenile(Juvenile juvenile) {
		setJuvenileId("" + juvenile.getOID());
		this.juvenile = (Juvenile) new mojo.km.persistence.Reference(
				juvenile).getObject();
	}
}
