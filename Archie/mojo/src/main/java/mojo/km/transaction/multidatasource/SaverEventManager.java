package mojo.km.transaction.multidatasource;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import mojo.km.caching.generic.CacheManager;
import mojo.km.context.ContextManager;
import mojo.km.persistence.OIDEvent;
import mojo.km.persistence.PersistentObject;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.PrintUtilities;

/**
 * Handles access to all callbacks to save events.
 * 
 * @modelguid {3DB4D2F2-F055-4361-93D9-78A4AF82C684}
 */
public class SaverEventManager
{
	class CallbackManager
	{
		Method callback = null;

		Object obj = null;
	}

	private Class[] parentDependencies;

	/** @modelguid {21D5B9C2-ABFA-418A-9EB5-837EB99C4781} */
	private List savers = new java.util.ArrayList();

	/** @modelguid {2C22FB11-C5BF-4512-8B6E-5CD8AE536B91} */
	void addCallback(String methodName, Object callbackObject)
	{
		Class[] parms = new Class[1];
		parms[0] = PersistentObject.class;
		try
		{
			CallbackManager cM = new CallbackManager();
			Method callback = callbackObject.getClass().getDeclaredMethod(methodName, parms);
			cM.callback = callback;
			cM.obj = callbackObject;
			savers.add(cM);
		}
		catch (Exception e)
		{
			String msg = "Failure to add callback: " + methodName + " for " + callbackObject.getClass().getName() + ".";
			throw new AddCallbackException(msg, e);
		}
	}

	/**
	 * @return Returns the parentDependencies.
	 */
	public Class[] getParentDependencies()
	{
		return parentDependencies;
	}

	private void invalidateParents()
	{
		if (parentDependencies != null)
		{
			for (int i = 0; i < parentDependencies.length; i++)
			{
				CacheManager.invalidate(parentDependencies[i]);
			}
		}
	}

	/** @modelguid {20572B41-E362-4D02-BB10-835E5166121B} */
	void notify(PersistentObject pObj)
	{
		// isNew needs to be tracked b/c the pObj gets set "not" new in IMapping
		boolean isNew = pObj.isNew();

		for (Iterator i = savers.iterator(); i.hasNext();)
		{
			CallbackManager callbackMgr = (CallbackManager) i.next();
			try
			{
				Object[] parms = new Object[1];
				parms[0] = pObj;
				callbackMgr.callback.invoke(callbackMgr.obj, parms);
			}
			catch (InvocationTargetException e)
			{
				try{
					String msg = "Error saving :: " + pObj.getClass().getName() + " :: " + PrintUtilities.getStackTrace(e, null);
					SaveException outException = new SaveException(msg, e.getCause());
					throw outException;					
				}catch(IOException ex){
					throw new SaveException(ex.getMessage());
				}
			}
			catch (Exception e)
			{
				try{
					String msg = "Error saving :: " + pObj.getClass().getName() + " :: " + PrintUtilities.getStackTrace(e, null);
					SaveException outException = new SaveException(msg, e);
					throw outException;
				} catch(IOException ex){
					throw new SaveException(ex.getMessage());
				}
			}
		}

		// add to cache
		if (isNew == true)
		{
			CacheManager.invalidate(pObj.getClass());
			this.invalidateParents();
		}
		else if (pObj.isDeleted())
		{
			CacheManager.removeEntity(pObj);
		}
		else
		{
			OIDEvent oidEvent = new OIDEvent();
			oidEvent.setOID((String) pObj.getOID());

			ISecurityManager mgr = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
			if (mgr != null)
			{
				IUserInfo userInfo = mgr.getIUserInfo();
				pObj.setUpdateUserID(userInfo.getJIMSLogonId());
				pObj.setUpdateJIMS2UserID(userInfo.getJIMS2LogonId());
			}

			Date today = new Date();

			pObj.setUpdateTimestamp(new java.sql.Timestamp(today.getTime()));
			CacheManager.addEntity(pObj, oidEvent, pObj.getContextKey());

			this.invalidateParents();
		}
	}

	/**
	 * @param parentDependencies
	 *            The parentDependencies to set.
	 */
	public void setParentDependencies(Class[] parentDependencies)
	{
		this.parentDependencies = parentDependencies;
	}
}
