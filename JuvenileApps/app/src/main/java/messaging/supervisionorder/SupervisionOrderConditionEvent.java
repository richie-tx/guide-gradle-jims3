/*
 * Created on Feb 8, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.supervisionorder;

import java.util.ArrayList;
import java.util.Collection;

import mojo.km.messaging.RequestEvent;

/**
 * @author asrvastava
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SupervisionOrderConditionEvent extends RequestEvent
{
	private String conditionId;
	private String resolvedDescription;
	private Collection orderConditionRelValues = new ArrayList();
	private boolean isSpecialCondition;
	private int sequenceNum;
	private String conditionName;  //to be used for historical orders only.
	
	/**
	 * @return
	 */
	public String getConditionId()
	{
		return conditionId;
	}

	/**
	 * @return
	 */
	public Collection getOrderConditionRelValues()
	{
		return orderConditionRelValues;
	}

	/**
	 * @param string
	 */
	public void setConditionId(String string)
	{
		conditionId = string;
	}

	/**
	 * @param collection
	 */
	public void setOrderConditionRelValues(Collection collection)
	{
		orderConditionRelValues = collection;
	}

	/**
	 * @param collection
	 */
	public void addOrderConditionRelValue(SupervisionOrderConditionRelValueEvent supervisionOrderConditionRelValueEvent)
	{
		orderConditionRelValues.add(supervisionOrderConditionRelValueEvent);
	}

	/**
	 * @return
	 */
	public String getResolvedDescription()
	{
		return resolvedDescription;
	}

	/**
	 * @param string
	 */
	public void setResolvedDescription(String string)
	{
		resolvedDescription = string;
	}

	/**
	 * @return
	 */
	public boolean isSpecialCondition()
	{
		return isSpecialCondition;
	}

	/**
	 * @param b
	 */
	public void setSpecialCondition(boolean b)
	{
		isSpecialCondition = b;
	}

	/**
	 * @return
	 */
	public int getSequenceNum()
	{
		return sequenceNum;
	}

	/**
	 * @param i
	 */
	public void setSequenceNum(int i)
	{
		sequenceNum = i;
	}

    /**
     * @return Returns the conditionName.
     */
    public String getConditionName() {
        return conditionName;
    }
    /**
     * @param conditionName The conditionName to set.
     */
    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }
}
