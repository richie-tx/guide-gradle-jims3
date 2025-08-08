/*
 * Created on Nov 18, 2005
 *
 */
package mojo.pattern;

/**
 * @author Jim Fisher
 *
 * A marker interface for creating <code>RequestEvent</code> objects.  A single Factory class 
 * instance may be used for creating multiple types of <code>RequestEvent</code> 
 * objects for a given coarse grained subsystem concept.
 * 
 * For instance, while a subsystem may have a total of ten entity classes, it may only need 
 * one or two IResponseFactory classes.
 * 
 * The following is an example for using a class that implements the
 * <code>IResponseFactory</code> interface:
 * 
 * JuvenileWarrantRequestFactory factory = JuvenileWarrantRequestFactory.getInstance(); 
 * RequestEvent requestEvent = factory.createRecallRequest(form);
 *
 * [MARKER] Reference the following URL for more information on the marker interface pattern:
 * http://en.wikipedia.org/wiki/Marker_interface_pattern 
 */
public interface IRequestFactory
{
}
