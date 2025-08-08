package messaging.facility.reply;

import messaging.identityaddress.domintf.IAddressable;
import mojo.km.messaging.ResponseEvent;

public class JuvenileFacilityReleaseResponseEvent extends ResponseEvent implements IAddressable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean saveable;
	private boolean updateable;
	
	
	
	/**
	 * @return the saveable
	 */
	public boolean isSaveable() {
		return saveable;
	}
	/**
	 * @param saveable the saveable to set
	 */
	public void setSaveable(boolean saveable) {
		this.saveable = saveable;
	}
	
	/**
	 * @return the updateable
	 */
	public boolean isUpdateable() {
		return updateable;
	}
	/**
	 * @param updateable the updateable to set
	 */
	public void setUpdateable(boolean updateable) {
		this.updateable = updateable;
	}

}
