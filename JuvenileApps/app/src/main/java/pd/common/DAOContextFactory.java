/*
 * Created on Aug 15, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.common;

import pd.exception.ReflectionException;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DAOContextFactory {
     
	public DAOContextFactory() {
	}
	
	public Object lookup(String context) throws ReflectionException{
		Object dao = null;
		try {
			Class c = Class.forName(context);
			dao = (Object) c.newInstance();			
		} catch (InstantiationException e1) {
			e1.printStackTrace();
			throw new ReflectionException(e1.getMessage());
		} catch (IllegalAccessException e2) {
			e2.printStackTrace();
			throw new ReflectionException(e2.getMessage());
		} catch (ClassNotFoundException e3) {
			e3.printStackTrace();
			throw new ReflectionException(e3.getMessage());
		}
		return dao;
     }
}
