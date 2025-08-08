package pd.supervision.supervisionoptions;

import java.util.ArrayList;
import java.util.Iterator;

import messaging.supervisionoptions.GetConditionSupervisionOptionsEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

/**
* @roseuid 42F7C40F01C5
*/
public class ConditionSupervisionOption extends CourtSupervisionOption {
	private String conditionId;
	/**
	* Properties for condition
	*/
	public Condition condition = null;
	/**
	* @roseuid 42F7C40F01C5
	*/
	public ConditionSupervisionOption() {
	}

	/**
	* @param conditionId
	* @roseuid 42F79A3902DE
	*/
	static public ConditionSupervisionOption find(String conditionSupervisionOptionId)
	{
		IHome home = new Home();
		return (ConditionSupervisionOption) home.find(conditionSupervisionOptionId, ConditionSupervisionOption.class);
	}

	/**
	* @return Iterator ConditionSupervisionOption
	* @param event
	*/
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, ConditionSupervisionOption.class);
	}
	
	public static Iterator findVariableElements(String conditionId, String courtId){
		GetConditionSupervisionOptionsEvent reqEvent = new GetConditionSupervisionOptionsEvent();
		reqEvent.setConditionId(conditionId);
		reqEvent.setCourtId(courtId);
		Iterator it = ConditionSupervisionOption.findAll(reqEvent);
		
		Iterator varElems = null;
		// there will be only one instance for a given condition and court
		if(it.hasNext()){
			ConditionSupervisionOption condSupOption = (ConditionSupervisionOption)it.next();
			varElems = condSupOption.getVariableElements().iterator();
		}else{
			varElems = (new ArrayList()).iterator();
		}

		return varElems;		
	}

	/**
	* Access method for the condition property.
	* @return the current value of the condition property
	*/
	public Condition getCondition() {
		initCondition();
		return condition;
	}
	/**
	* @roseuid 42F79A3B017A
	*/
	public void find() {
		fetch();
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
