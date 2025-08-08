/*
 * Created on Feb 24, 2006
 *
 */
package messaging.common.domintf;

/**
 * The interface for specifying how to uniquely identify an object.  For 
 * example, the GetJOTDataEvent implements this interface for requesting 
 * to uniquely access a JOT data record.
 * 
 * This interface is useful for separating the PD model from the boundary
 * layer. 
 * 
 * @author Jim Fisher
 *
 */
public interface IIdentity
{
	String getId();
	
	void setId(String id); 
}
