package pd.supervision.supervisionoptions;

import java.util.Iterator;

import pd.supervision.Court;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
* @roseuid 42F7C404032C
*/
public class CourtConditionCourtPolicy extends PersistentObject {
	private String courtPolicyId;
	/**
	* Properties for court
	*/
	public Court court = null;
	/**
	* Properties for condition
	*/
	public Condition condition = null;
	private String courtId;
	/**
	* Properties for courtPolicy
	*/
	public CourtPolicy courtPolicy = null;
	private String conditionId;

	/**
	* @roseuid 42F7C404032C
	*/
	public CourtConditionCourtPolicy() {
	}

	/**
	* Finds all Conditions by an attribute value
	* @return 
	* @param attributeName
	* @param attributeValue
	*/
	static public Iterator findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		Iterator objs = home.findAll(attributeName, attributeValue, CourtConditionCourtPolicy.class);
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
			try {
				court = (Court) new mojo.km.persistence.Reference(courtId, Court.class).getObject();
			} catch (Throwable t) {
			}
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
	* Set the reference value to class :: pd.supervision.supervisionoptions.CourtPolicy
	*/
	public void setCourtPolicyId(String courtPolicyId) {
		if (this.courtPolicyId == null || !this.courtPolicyId.equals(courtPolicyId)) {
			markModified();
		}
		courtPolicy = null;
		this.courtPolicyId = courtPolicyId;
	}
	/**
	* Get the reference value to class :: pd.supervision.supervisionoptions.CourtPolicy
	*/
	public String getCourtPolicyId() {
		fetch();
		return courtPolicyId;
	}
	/**
	* Initialize class relationship to class pd.supervision.supervisionoptions.CourtPolicy
	*/
	private void initCourtPolicy() {
		if (courtPolicy == null) {
			try {
				courtPolicy =
					(CourtPolicy) new mojo
						.km
						.persistence
						.Reference(courtPolicyId, CourtPolicy.class)
						.getObject();
			} catch (Throwable t) {
			}
		}
	}
	/**
	* Gets referenced type pd.supervision.supervisionoptions.CourtPolicy
	*/
	public CourtPolicy getCourtPolicy() {
		initCourtPolicy();
		return courtPolicy;
	}
	/**
	* set the type reference for class member courtPolicy
	*/
	public void setCourtPolicy(CourtPolicy courtPolicy) {
		if (this.courtPolicy == null || !this.courtPolicy.equals(courtPolicy)) {
			markModified();
		}
		if (courtPolicy.getOID() == null) {
			new mojo.km.persistence.Home().bind(courtPolicy);
		}
		setCourtPolicyId("" + courtPolicy.getOID());
		this.courtPolicy = (CourtPolicy) new mojo.km.persistence.Reference(courtPolicy).getObject();
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
			try {
				condition =
					(Condition) new mojo.km.persistence.Reference(conditionId, Condition.class).getObject();
			} catch (Throwable t) {
			}
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
