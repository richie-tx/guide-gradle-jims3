package pd.supervision.supervisionoptions;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;


/**
* @author athorat
This persitent object is for VIEW purpose only.
The object retrives condition details from condition,supervisionOption and
groupCondition
*/
public class ConditionView extends mojo.km.persistence.PersistentObject {
	private String group1;
	private String courtId;
	private String group3;
	private String conditionOID;
	private String supervisionOptionOID;
	private String group2;
	private boolean standard;
	private String supervisionType;

	/**
	* @return 
	*/
	public String getConditionOID() {
		fetch();
		return conditionOID;
	}
	/**
	* @return 
	*/
	public String getCourtId() {
		fetch();
		return courtId;
	}
	/**
	* @return 
	*/
	public String getGroup1() {
		fetch();
		return group1;
	}
	/**
	* @return 
	*/
	public String getGroup2() {
		fetch();
		return group2;
	}
	/**
	* @return 
	*/
	public String getGroup3() {
		fetch();
		return group3;
	}
	/**
	* @return 
	*/
	public boolean isStandard() {
		fetch();
		return standard;
	}
	/**
	* @return 
	*/
	public String getSupervisionOptionOID() {
		fetch();
		return supervisionOptionOID;
	}
	/**
	* @return 
	*/
	public String getSupervisionType() {
		fetch();
		return supervisionType;
	}

	/**
	* @param string
	*/
	public void setConditionOID(String string) {
		if (this.conditionOID == null || !this.conditionOID.equals(string)) {
			markModified();
		}
		conditionOID = string;
	}
	/**
	* @param string
	*/
	public void setCourtId(String string) {
		if (this.courtId == null || !this.courtId.equals(string)) {
			markModified();
		}
		courtId = string;
	}
	/**
	* @param string
	*/
	public void setGroup1(String string) {
		if (this.group1 == null || !this.group1.equals(string)) {
			markModified();
		}
		group1 = string;
	}
	/**
	* @param string
	*/
	public void setGroup2(String string) {
		if (this.group2 == null || !this.group2.equals(string)) {
			markModified();
		}
		group2 = string;
	}
	/**
	* @param string
	*/
	public void setGroup3(String string) {
		if (this.group3 == null || !this.group3.equals(string)) {
			markModified();
		}
		group3 = string;
	}
	/**
	* @param b
	*/
	public void setStandard(boolean b) {
		if (this.standard != b) {
			markModified();
		}
		standard = b;
	}
	/**
	* @param string
	*/
	public void setSupervisionOptionOID(String string) {
		if (this.supervisionOptionOID == null || !this.supervisionOptionOID.equals(string)) {
			markModified();
		}
		supervisionOptionOID = string;
	}
	/**
	* @param string
	*/
	public void setSupervisionType(String string) {
		if (this.supervisionType == null || !this.supervisionType.equals(string)) {
			markModified();
		}
		supervisionType = string;
	}

	/**
	* @return Iterator ConditionView
	* @param event
	*/
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, ConditionView.class);
	}

}
