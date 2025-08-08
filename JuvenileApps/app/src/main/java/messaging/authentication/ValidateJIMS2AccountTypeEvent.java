/*
 * Created on Jan 10, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.authentication;

import mojo.km.messaging.RequestEvent;

/**
 * @author Rcooper
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ValidateJIMS2AccountTypeEvent extends RequestEvent
{
//	public String logonId;
	  public String JIMS2AccountTypeId;
	  public String JIMS2AccountTypeOID;
	  //public String JIMS2LogonId;
	  //private String departmentId;
	  //private String payrollNum;
	  //private String badgeNum;

	  /**
	   * @roseuid 4399CD3D0207
	   */
	  public ValidateJIMS2AccountTypeEvent()
	  {

	  }

	 
	  /**
	   * Access method for the JIMS2AccountTypeId property.
	   * 
	   * @return   the current value of the JIMS2AccountTypeId property
	   */
	  public String getJIMS2AccountTypeId()
	  {
		  return JIMS2AccountTypeId;
	  }

	  /**
	   * Sets the value of the JIMS2AccountTypeId property.
	   * 
	   * @param aJIMS2AccountTypeId the new value of the JIMS2AccountTypeId property
	   */
	  public void setJIMS2AccountTypeId(String aJIMS2AccountTypeId)
	  {
		  JIMS2AccountTypeId = aJIMS2AccountTypeId;
	  }

	  /**
	   * Access method for the JIMS2AccountTypeOID property.
	   * 
	   * @return   the current value of the JIMS2AccountTypeOID property
	   */
	  public String getJIMS2AccountTypeOID()
	  {
		  return JIMS2AccountTypeOID;
	  }

	  /**
	   * Sets the value of the JIMS2AccountTypeOID property.
	   * 
	   * @param aJIMS2AccountTypeOID the new value of the JIMS2AccountTypeOID property
	   */
	  public void setJIMS2AccountTypeOID(String aJIMS2AccountTypeOID)
	  {
		  JIMS2AccountTypeOID = aJIMS2AccountTypeOID;
	  }
 
 
}
