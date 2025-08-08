/*
 * Created on Oct 27, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.km.caching.generic;

/**
 * @stereotype control
 * @eventPackage messaging.cacheaccessevents
 * @author eamundson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CacheAccessControl
{
	
	/**
	 * @stereotype design
	 * @commandList mojo.km.caching.generic.RemoteCacheAccess.PutCommand
	 * @param id
	 */
	public void cachePut(String id, Object cachedObject)
	{
	}
	
	/**
	 * @stereotype design
	 * @commandList mojo.km.caching.generic.RemoteCacheAccess.GetCommand
	 * @param id
	 */
	public void cacheGet(String id)
	{
	}
	/**
	 * @commandList mojo.km.caching.generic.RemoteCacheAccess.RemoveCommand
	 * @stereotype design
	 * @param id
	 */
	public void cacheRemove(String id)
	{
	}

}
