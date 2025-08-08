/*
 * Created on Aug 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase.family;

import java.util.Iterator;

import naming.PDCodeTableConstants;
import naming.PDConstants;

import messaging.contact.domintf.IName;
import messaging.contact.to.NameBean;
import messaging.juvenilecase.reply.AssociatedJuvenilesResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import ui.common.SimpleCodeTableHelper;

/**
 * @author jjose
 * 
 */
public class AssociatedJuvMemberView extends PersistentObject {
	private String juvenileId;
	private Juvenile juvenile = null;
	private String memberNum;
	private String lastName;
	private String middleName;
	private String firstName;
	private boolean deceased;
	private String relationshipToJuvenileId;
	public Code relationshipToJuvenile = null;
	private boolean constActive;
	private String constId;
	private boolean guardian;

	public static Iterator find(String aMemberNum) {
		IHome home = new Home();
		Iterator assocJuvs = home.findAll("memberNum", aMemberNum,
				AssociatedJuvMemberView.class);
		return assocJuvs;
	}

	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return (Iterator) home.findAll(event, AssociatedJuvMemberView.class);
	}

	public AssociatedJuvenilesResponseEvent getResponseEvent() {
		AssociatedJuvenilesResponseEvent myRespEvt = new AssociatedJuvenilesResponseEvent();
		myRespEvt.setDeceased(this.isDeceased());
		myRespEvt.setActiveConstellation(this.isConstActive());
		myRespEvt.setGuardian(this.isGuardian());
		myRespEvt.setConstellationId(this.getConstId());
		myRespEvt.setFamMemberId(this.getMemberNum());
		myRespEvt.setJuvId(this.getJuvenileId());
		if (this.getRelationshipToJuvenileId() != null
				&& !this.getRelationshipToJuvenileId().equals(PDConstants.BLANK)
				&& this.getRelationshipToJuvenile() != null)
			myRespEvt.setRelationType(this.getRelationshipToJuvenile()
					.getDescription());
		else
			myRespEvt.setRelationType(PDConstants.BLANK);
		myRespEvt.setRelationTypeId(this.getRelationshipToJuvenileId());
		IName juvName = new NameBean();
		myRespEvt.setJuvName(juvName);
		//Juvenile juvenile = this.getJuvenile();
		Juvenile juvenile = Juvenile.findJCJuvenile(this.getJuvenileId());
		JuvenileCore juvCore =  JuvenileCore.findCore(this.getJuvenileId());

		if (juvCore != null) {
			if (this.getFirstName() != null) {
				juvName.setFirstName(juvCore.getFirstName());
			}
			if (this.getMiddleName() != null) {
				juvName.setMiddleName(juvCore.getMiddleName());
			}
			if (this.getLastName() != null) {
				juvName.setLastName(juvCore.getLastName());
			}
			myRespEvt.setDateOfBirth(juvCore.getDateOfBirth());
			myRespEvt.setRaceCd(juvCore.getRaceId());
			
			if ( juvCore.getRaceId() != null
				&& juvCore.getRaceId().length() > 0 ) {
			    myRespEvt.setRaceDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.RACE, juvCore.getRaceId()));
			}
			
			myRespEvt.setSexCd(juvCore.getSexId());
			
			if ( juvCore.getSexId() != null
				&& juvCore.getSexId().length() > 0 ) {
			    myRespEvt.setSexDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SEX, juvCore.getSexId()));
			}
			  
			   
			
		}
		if (juvenile != null){
			myRespEvt.setEthnicityCd(juvenile.getEthnicityId());
			if ( juvenile.getEthnicityId() != null
				&& juvenile.getEthnicityId().length() > 0 ){
			    myRespEvt.setEthnicityDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_ETHNICITY, juvenile.getEthnicityId() ));
			}
			
		}
		myRespEvt.setGuardian(this.isGuardian());
		myRespEvt.setRelationTypeId(this.getRelationshipToJuvenileId());
		myRespEvt.setConstellationId(this.getConstId());
		
		return myRespEvt;
	}

	/**
	 * @return The boolean.
	 */
	public boolean isGuardian() {
		fetch();
		return guardian;
	}

	public void setGuardian(boolean b) {
		this.guardian = b;
	}

	/**
	 * @return The boolean.
	 */
	public boolean isConstActive() {
		fetch();
		return constActive;
	}

	public void setConstActive(boolean b) {
		this.constActive = b;
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
	 * @param i
	 */

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

	/**
	 * @return
	 */
	public String getConstId() {
		fetch();
		return constId;
	}

	/**
	 * @param i
	 */
	public void setConstId(String constId) {
		this.constId = constId;
	}
}
