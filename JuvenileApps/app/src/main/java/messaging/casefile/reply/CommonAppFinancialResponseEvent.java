package messaging.casefile.reply;

import java.util.ArrayList;
import java.util.Collection;

import mojo.km.messaging.ResponseEvent;

/**
 * @author bschwartz
 *
 */
public class CommonAppFinancialResponseEvent extends ResponseEvent
{
	private Collection finacialInfo = new ArrayList();

	

	/**
	 * @return Returns the finacialInfo.
	 */
	public Collection getFinacialInfo() {
		return finacialInfo;
	}
	/**
	 * @param finacialInfo The finacialInfo to set.
	 */
	public void setFinacialInfo(Collection finacialInfo) {
		this.finacialInfo = finacialInfo;
	}
}
