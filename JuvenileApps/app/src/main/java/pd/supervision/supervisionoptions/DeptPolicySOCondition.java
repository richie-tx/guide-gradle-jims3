/*
 * Created on Nov 1, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.supervisionoptions;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DeptPolicySOCondition extends PersistentObject
{
	
	
	private String courtId;
	private String orderId;
	private String deptPolicyId;
	


	public DeptPolicySOCondition()
		{
		}
		
		/**
		* @roseuid 42F79A3902E0
		*/
		public void bind()
		{
			markModified();
		}
	
		/**
		* @param conditionId
		* @roseuid 42F79A3902DE
		*/
		static public DeptPolicySOCondition find(String aOid)
		{
			IHome home = new Home();
			return (DeptPolicySOCondition) home.find(aOid, DeptPolicySOCondition.class);
		}
		/**
		* @return Iterator Condition
		* @param event
		*/
		static public Iterator findAll(IEvent event)
		{
			IHome home = new Home();
			return home.findAll(event, DeptPolicySOCondition.class);
		}
		/**
		* Finds all Conditions by an attribute value
		* @return 
		* @param attributeName
		* @param attributeValue
		*/
		static public Iterator findAll(String attributeName, String attributeValue)
		{
			IHome home = new Home();
			Iterator csDeptPolicyConditions = home.findAll(attributeName, attributeValue, DeptPolicySOCondition.class);
			return csDeptPolicyConditions;
		}
	
		
		
	
		
		
		
		
	
		
	/**
	 * @return
	 */
	public String getOrderId()
	{
		fetch();
		return orderId;
	}

	/**
	 * @return
	 */
	public String getCourtId()
	{
		fetch();
		return courtId;
	}





	/**
	 * @param string
	 */
	public void setOrderId(String string)
	{
		if (this.orderId == null || !this.orderId.equals(string))
						{
							markModified();
						}
		orderId = string;
	}

	
	/**
	 * @param string
	 */
	public void setCourtId(String string)
	{
		if (this.courtId == null || !this.courtId.equals(string))
								{
									markModified();
								}
		courtId = string;
	}

	
	 
	public String getDeptPolicyId()
	{
		fetch();
		return deptPolicyId;
	}
	/**
	 * @param string
	 */
	public void setDeptPolicyId(String string)
	{
		if (this.deptPolicyId == null || !this.deptPolicyId.equals(string))
								{
									markModified();
								}
		deptPolicyId = string;
	}


}
