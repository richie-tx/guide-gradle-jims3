package pd.supervision.supervisionoptions;

import java.util.Iterator;

import mojo.km.persistence.PersistentObject;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.supervision.Court;

/**
* @author asrvastava
To change the template for this generated type comment go to
Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
*/
public class CourtConditionAgencyPolicy extends PersistentObject {
	private String courtId;
	private String agencyPolicyId;
	/**
	* Properties for court
	*/
	public Court court = null;
	/**
	* Properties for courtPolicy
	*/
	public AgencyPolicy agencyPolicy = null;
	private String conditionId;
	/**
	* Properties for condition
	*/
	public Condition condition = null;
	

	
	/**
	* Finds all Conditions by an attribute value
	* @return 
	* @param attributeName
	* @param attributeValue
	*/
	static public Iterator findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		Iterator objs = home.findAll(attributeName, attributeValue, CourtConditionAgencyPolicy.class);
		return objs;
	}
	
	/**
	* Set the reference value to class :: pd.supervision.Court
	*/
	public void setCourtId(String courtId) {
		if (this.courtId == null || !this.courtId.equals(courtId)) {
			markModified();
		}
		court = null;
		this.courtId = courtId;
	}
	/**
	* Get the reference value to class :: pd.supervision.Court
	*/
	public String getCourtId() {
		fetch();
		return courtId;
	}
	/**
	* Initialize class relationship to class pd.supervision.Court
	*/
	private void initCourt() {
		if (court == null) {
			court = (Court) new mojo.km.persistence.Reference(courtId, Court.class).getObject();
		}
	}
	/**
	* Gets referenced type pd.supervision.Court
	*/
	public Court getCourt() {
		initCourt();
		return court;
	}
	/**
	* set the type reference for class member court
	*/
	public void setCourt(Court court) {
		if (this.court == null || !this.court.equals(court)) {
			markModified();
		}
		if (court.getOID() == null) {
			new mojo.km.persistence.Home().bind(court);
		}
		setCourtId("" + court.getOID());
		this.court = (Court) new mojo.km.persistence.Reference(court).getObject();
	}
	/**
	* Set the reference value to class :: pd.supervision.supervisionoptions.AgencyPolicy
	*/
	public void setAgencyPolicyId(String agencyPolicyId) {
		if (this.agencyPolicyId == null || !this.agencyPolicyId.equals(agencyPolicyId)) {
			markModified();
		}
		agencyPolicy = null;
		this.agencyPolicyId = agencyPolicyId;
	}
	/**
	* Get the reference value to class :: pd.supervision.supervisionoptions.AgencyPolicy
	*/
	public String getAgencyPolicyId() {
		fetch();
		return agencyPolicyId;
	}
	/**
	* Initialize class relationship to class pd.supervision.supervisionoptions.AgencyPolicy
	*/
	private void initAgencyPolicy() {
		if (agencyPolicy == null) {
			agencyPolicy =
				(AgencyPolicy) new mojo
					.km
					.persistence
					.Reference(agencyPolicyId, AgencyPolicy.class)
					.getObject();
		}
	}
	/**
	* Gets referenced type pd.supervision.supervisionoptions.AgencyPolicy
	*/
	public AgencyPolicy getAgencyPolicy() {
		initAgencyPolicy();
		return agencyPolicy;
	}
	/**
	* set the type reference for class member agencyPolicy
	*/
	public void setAgencyPolicy(AgencyPolicy agencyPolicy) {
		if (this.agencyPolicy == null || !this.agencyPolicy.equals(agencyPolicy)) {
			markModified();
		}
		if (agencyPolicy.getOID() == null) {
			new mojo.km.persistence.Home().bind(agencyPolicy);
		}
		setAgencyPolicyId("" + agencyPolicy.getOID());
		this.agencyPolicy = (AgencyPolicy) new mojo.km.persistence.Reference(agencyPolicy).getObject();
	}
	/**
	* Set the reference value to class :: pd.supervision.supervisionoptions.Condition
	*/
	public void setConditionId(String conditionId) {
		if (this.conditionId == null || !this.conditionId.equals(conditionId)) {
			markModified();
		}
		condition = null;
		this.conditionId = conditionId;
	}
	/**
	* Get the reference value to class :: pd.supervision.supervisionoptions.Condition
	*/
	public String getConditionId() {
		fetch();
		return conditionId;
	}
	/**
	* Initialize class relationship to class pd.supervision.supervisionoptions.Condition
	*/
	private void initCondition() {
		if (condition == null) {
			condition =
				(Condition) new mojo.km.persistence.Reference(conditionId, Condition.class).getObject();
		}
	}
	/**
	* Gets referenced type pd.supervision.supervisionoptions.Condition
	*/
	public Condition getCondition() {
		initCondition();
		return condition;
	}
	/**
	* set the type reference for class member condition
	*/
	public void setCondition(Condition condition) {
		if (this.condition == null || !this.condition.equals(condition)) {
			markModified();
		}
		if (condition.getOID() == null) {
			new mojo.km.persistence.Home().bind(condition);
		}
		setConditionId("" + condition.getOID());
		this.condition = (Condition) new mojo.km.persistence.Reference(condition).getObject();
	}
}
