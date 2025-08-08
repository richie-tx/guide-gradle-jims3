package ui.supervision.administerserviceprovider.programreferral.action;

import java.util.List;

public class UIProgramHierarchyBean {

	String parentCd;
	String parentDesc;
	List childEvents;
	
	
	public String getParentCd() {
		return parentCd;
	}
	public void setParentCd(String parentCd) {
		this.parentCd = parentCd;
	}
	public String getParentDesc() {
		return parentDesc;
	}
	public void setParentDesc(String parentDesc) {
		this.parentDesc = parentDesc;
	}
	public List getChildEvents() {
		return childEvents;
	}
	public void setChildEvents(List childEvents) {
		this.childEvents = childEvents;
	}
	
	
	
}
