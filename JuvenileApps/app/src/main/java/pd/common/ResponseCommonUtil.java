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
public class ResponseCommonUtil {
     
	/**
	 * @param context
	 * @param respFac
	 */
	public Object getResponseInstance(String context, ResponseContextFactory respFac) {
		Object creator = null;
		try {
			creator = respFac.lookup(context);
		} catch (ReflectionException e) {
			e.printStackTrace();
		}
		return creator;
	}
}
