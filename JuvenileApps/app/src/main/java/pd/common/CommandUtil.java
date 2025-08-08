/*
 * Created on Aug 15, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.common;

import pd.commonfunctionality.spnconsolidation.ISpnHandler;
import pd.exception.ReflectionException;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CommandUtil {

	/**
	 * @param lawviolation_dao_locator
	 * @return
	 */
	 protected DAOHandler getHandler(String daoLocator) {
	     DAOHandler handler = null;
	     DAOContextFactory daoFac = new DAOContextFactory();
		 try {
	   	     handler = (DAOHandler) daoFac.lookup(daoLocator);
		 } catch (ReflectionException e) {
		     e.printStackTrace();
		 }
		 return handler;
	 }  
	 
	 /**
		 * @param lawviolation_dao_locator
		 * @return
		 */
		 protected ISpnHandler getHandlerObject(String daoLocator) {
			 ISpnHandler handler = null;
		     DAOContextFactory daoFac = new DAOContextFactory();
			 try {
		   	     handler = (ISpnHandler) daoFac.lookup(daoLocator);
			 } catch (ReflectionException e) {
			     e.printStackTrace();
			 }
			 return handler;
		 }      
}
