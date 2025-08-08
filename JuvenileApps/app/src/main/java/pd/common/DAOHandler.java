/*
 * Created on Aug 15, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.common;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface DAOHandler {
     public abstract void execute(Object object);
     public abstract void remove(Object object);
     public abstract void update(Object object);
     public abstract void get(Object object);
     public abstract void refresh(Object object);
}
