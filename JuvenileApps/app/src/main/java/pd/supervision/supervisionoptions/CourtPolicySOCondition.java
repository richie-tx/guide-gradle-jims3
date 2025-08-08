/*
 * Created on Jun 19, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.supervision.supervisionoptions;


import java.util.Iterator;


import mojo.km.persistence.PersistentObject;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.IHome;
import mojo.km.persistence.Home;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CourtPolicySOCondition extends PersistentObject
{
	
	
	private String courtId;
	private String orderId;
	private String courtPolicyId;
	


	public CourtPolicySOCondition()
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
		static public CourtPolicySOCondition find(String aOid)
		{
			IHome home = new Home();
			return (CourtPolicySOCondition) home.find(aOid, CourtPolicySOCondition.class);
		}
		/**
		* @return Iterator Condition
		* @param event
		*/
		static public Iterator findAll(IEvent event)
		{
			IHome home = new Home();
			return home.findAll(event, CourtPolicySOCondition.class);
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
			Iterator csCourtConditions = home.findAll(attributeName, attributeValue, CourtPolicySOCondition.class);
			return csCourtConditions;
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

	
	 
	public String getCourtPolicyId()
	{
		fetch();
		return courtPolicyId;
	}
	/**
	 * @param string
	 */
	public void setCourtPolicyId(String string)
	{
		if (this.courtPolicyId == null || !this.courtPolicyId.equals(string))
								{
									markModified();
								}
		courtPolicyId = string;
	}


}
