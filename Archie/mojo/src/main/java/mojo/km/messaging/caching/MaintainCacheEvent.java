package mojo.km.messaging.caching;

import mojo.km.messaging.RequestEvent;

/**
 * Responsible for housing data that will be sent to MaintainCacheListener
 * 
 * @author James McNabb
 * @version 1.0
 */
public class MaintainCacheEvent extends RequestEvent {
	public String getObjectContent() {
		return objectContent;
	}

	public void setObjectContent(String objectContent) {
		this.objectContent = objectContent;
	}

	public boolean getRemove() {
		return remove;
	}

	public boolean isRemove() {
		return getRemove();
	}

	public void setRemove(boolean value) {
		this.remove = value;
	}

	private String objectContent = null;
	private boolean remove = false;
}
