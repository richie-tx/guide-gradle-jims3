/*
 * Created on May 6, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;;

/**
 * @author asrvastava
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetSupervisionTypeEvent extends RequestEvent
{
	private String assignmentLevelId;
	private String serviceUnitId;

	/**
	 * @return
	 */
	public String getAssignmentLevelId()
	{
		return assignmentLevelId;
	}

	/**
	 * @return
	 */
	public String getServiceUnitId()
	{
		return serviceUnitId;
	}

	/**
	 * @param string
	 */
	public void setAssignmentLevelId(String string)
	{
		assignmentLevelId = string;
	}

	/**
	 * @param string
	 */
	public void setServiceUnitId(String string)
	{
		serviceUnitId = string;
	}

}
