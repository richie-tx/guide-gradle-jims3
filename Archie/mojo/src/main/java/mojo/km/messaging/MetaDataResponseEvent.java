/*
 * Created on Apr 2, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mojo.km.messaging;


/**
 * @author racooper
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MetaDataResponseEvent extends ResponseEvent {
	int count ;
	public int getCount()
	{
		
		return count;
	}
	/**
	 * @param count The count to set.
	 */
	public void setCount(int count) {
		this.count = count;
	}
}
