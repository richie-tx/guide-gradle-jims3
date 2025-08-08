/*
 * Created on Mar 12, 2007
 */
package messaging.managetasks.to;

import messaging.managetasks.domintf.ICsTask;
import messaging.task.to.TaskBean;

/**
 * @author hrodriguez
 */
public class CsTaskBean extends TaskBean implements ICsTask
{
	private String name;
	private String court;
	private String spn;

	/**
	 * @return Returns the spn.
	 */
	public String getSpn() {
		return spn;
	}
	/**
	 * @param spn The spn to set.
	 */
	public void setSpn(String spn) {
		this.spn = spn;
	}
	/**
	 * @return Returns the court.
	 */
	public String getCourt() {
		return court;
	}
	/**
	 * @param court The court to set.
	 */
	public void setCourt(String court) {
		this.court = court;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
}
