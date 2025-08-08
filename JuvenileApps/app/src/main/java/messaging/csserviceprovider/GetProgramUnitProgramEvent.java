/**
 * 
 */
package messaging.csserviceprovider;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_cwalters
 *
 */
public class GetProgramUnitProgramEvent extends RequestEvent 
{
	String programId;
	String programUnitId;
	String programStatus;

	public String getProgramUnitId() {
		return programUnitId;
	}

	public void setProgramUnitId(String programUnitId) {
		this.programUnitId = programUnitId;
	}

	public String getProgramStatus() {
		return programStatus;
	}

	public void setProgramStatus(String programStatus) {
		this.programStatus = programStatus;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

}
