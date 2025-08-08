package mojo.km.context.multidatasource;

/**
 * @author jmcnabb
 *
 * Should be thrown when the persistent object is locked for update 
 */
public class ObjectLockedException extends RuntimeException
{
	public ObjectLockedException(String msg) {
		super(msg);
	}
}
