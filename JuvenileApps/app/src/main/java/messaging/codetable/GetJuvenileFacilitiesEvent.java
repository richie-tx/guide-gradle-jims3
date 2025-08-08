/*
 * Created on Jul 15, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.codetable;

import mojo.km.messaging.RequestEvent;

/**
 * @author jfisher
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetJuvenileFacilitiesEvent extends RequestEvent
{
	private String code;
	private String department;
	private String juvTjpcPlacementType;

	/**
	 * @return Returns the code.
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code The code to set.
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the juvTjpcPlacementType
	 */
	public String getJuvTjpcPlacementType() {
		return juvTjpcPlacementType;
	}
	/**
	 * @param juvTjpcPlacementType the juvTjpcPlacementType to set
	 */
	public void setJuvTjpcPlacementType(String juvTjpcPlacementType) {
		this.juvTjpcPlacementType = juvTjpcPlacementType;
	}
	
	/**
	 * @return Returns the department.
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * @param department The department to set.
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
}
