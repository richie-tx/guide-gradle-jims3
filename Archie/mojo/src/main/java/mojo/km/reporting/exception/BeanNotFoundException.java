/*
 * Created on Apr 6, 2006
 *
 */
package mojo.km.reporting.exception;

/**
 * @author Jim Fisher
 *
 */
public class BeanNotFoundException extends RuntimeException
{
	public BeanNotFoundException()
	{
		super();
	}

	public BeanNotFoundException(String msg)
	{
		super(msg);
	}
}
