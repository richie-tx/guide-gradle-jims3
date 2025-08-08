/*
 * Created on Feb 24, 2006
 *
 */
package messaging.identity.domintf;

import java.io.Serializable;

/**
 * @author jfisher
 *
 */
public interface IIdentity extends Serializable
{
	String getId();
	
	void setId(String id); 
}
