//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\codetable\\transactions\\GetCodeTableRecordCommand.java

package pd.codetable;

import java.lang.reflect.InvocationTargetException;

import pd.exception.CodetableException;
import mojo.km.messaging.IEvent;


/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public interface ICodetableCreator 
{
     public abstract void retrieve(IEvent event) throws SecurityException, IllegalArgumentException, InstantiationException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, CodetableException;
     public abstract void save(IEvent event) throws SecurityException, IllegalArgumentException, InstantiationException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, CodetableException;
}
