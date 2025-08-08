/*
 * Created on Sep 17, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.codetable;

import java.lang.reflect.InvocationTargetException;

import mojo.km.messaging.IEvent;
import pd.exception.CodetableException;

/**
 * @author Nagalla
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface ICodetableRegCreator {
	
	public abstract void retrieve(IEvent event) throws SecurityException, IllegalArgumentException, InstantiationException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, CodetableException;
	public abstract void retrieveAttributeAndTypes(IEvent event) throws SecurityException, IllegalArgumentException, InstantiationException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, CodetableException;
    public abstract void save(IEvent event) throws SecurityException, IllegalArgumentException, InstantiationException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, CodetableException;
    public abstract void saveAttirbutes(IEvent event) throws SecurityException, IllegalArgumentException, InstantiationException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, CodetableException;


}
