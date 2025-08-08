package pd.supervision.supervisionorder;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;

/**
* @roseuid 43B2E72E00AB
*/
public class SupervisionOrderConditionRelView extends PersistentObject {
	
	public static Iterator findAll(IEvent event){
		return new Home().findAll(event, SupervisionOrderConditionRelView.class);
	}
	
	public int getSupervisionOrderId() {
		return supervisionOrderId;
	}
	public void setSupervisionOrderId(int supervisionOrderId) {
		this.supervisionOrderId = supervisionOrderId;
	}
	public int getSupervisionOrderConditionId() {
		return supervisionOrderConditionId;
	}
	public void setSupervisionOrderConditionId(int supervisionOrderConditionId) {
		this.supervisionOrderConditionId = supervisionOrderConditionId;
	}
	public int getConditionId() {
		return conditionId;
	}
	public void setConditionId(int conditionId) {
		this.conditionId = conditionId;
	}
	public String getCriminalCaseId() {
		return criminalCaseId;
	}
	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}
	private int supervisionOrderId;
	private int supervisionOrderConditionId;
	private int conditionId;
	private String criminalCaseId;
}
