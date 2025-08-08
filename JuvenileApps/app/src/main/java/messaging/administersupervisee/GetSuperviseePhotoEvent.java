/**
 * 
 */
package messaging.administersupervisee;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_cwalters
 *
 */
public class GetSuperviseePhotoEvent extends RequestEvent 
{
	private String spn;

	public String getSpn() {
		return spn;
	}

	public void setSpn(String spn) {
		this.spn = spn;
	}
		
}
