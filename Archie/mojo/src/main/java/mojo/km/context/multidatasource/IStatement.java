/*
 * Created on Mar 7, 2005
 *
 */
package mojo.km.context.multidatasource;

/**
 * @author Rcooper
 *  
 */
public interface IStatement
{
    void execute();

    Object get(String fieldname);

    void set(String fieldname);
}
