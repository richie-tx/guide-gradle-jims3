/**
 * 
 */
package messaging.csserviceprovider;

import mojo.km.messaging.Composite.CompositeRequest;

/**
 * @author cc_cwalters
 *
 */
public class ProgramProgramLanguageEvent extends CompositeRequest 
{
	private String programId;
	private String languageCode;
	
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
		
}
