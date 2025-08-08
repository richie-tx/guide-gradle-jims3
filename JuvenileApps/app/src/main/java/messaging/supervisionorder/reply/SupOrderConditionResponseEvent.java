/*
 * Created on Feb 23, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.supervisionorder.reply;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;



import mojo.km.messaging.ResponseEvent;

/**
 * @author asrvastava
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SupOrderConditionResponseEvent extends ResponseEvent implements Comparable
{
	private String conditionId;
	private String description;
	private String group1Name;
	private String group2Name;
	private String group3Name;
	private String name;
	private String resolvedDescription;
	private boolean likeConditionInd;
	private Collection supOrderConditionRelValues = new ArrayList();
	private boolean specialCondition;
	private int sequenceNum;
	private boolean standard;
	private String status;
	private HashSet allCourtIds;

	/**
	 * @return Returns the standard.
	 */
	public boolean isStandard() {
		return standard;
	}
	/**
	 * @param standard The standard to set.
	 */
	public void setStandard(boolean standard) {
		this.standard = standard;
	}
	/**
	 * @param collection
	 */
	public void addSupOrderConditionRelValue(SupOrderConditionRelValueResponseEvent condRelValEvent)
	{
		supOrderConditionRelValues.add(condRelValEvent);
	}

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
	public String getDescription()
	{
		return description;
	}
	/**
	 * @return
	 */
	public String getGroup1Name()
	{
		return group1Name;
	}

	/**
	 * @return
	 */
	public String getGroup2Name()
	{
		return group2Name;
	}

	/**
	 * @return
	 */
	public String getGroup3Name()
	{
		return group3Name;
	}

	/**
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return
	 */
	public String getResolvedDescription()
	{
		return resolvedDescription;
	}

	/**
	 * @return
	 */
	public Collection getSupOrderConditionRelValues()
	{
		Collections.sort((List)supOrderConditionRelValues);
		return supOrderConditionRelValues;
	}

	/**
	 * @param aConditionId
	 */
	public void setConditionId(String aConditionId)
	{
		conditionId = aConditionId;
	}

	/**
	 * @param aDescription
	 */
	public void setDescription(String aDescription)
	{
		description = aDescription;
	}

	/**
	 * @param aGroup1
	 */
	public void setGroup1Name(String aGroup1)
	{
		group1Name = aGroup1;
	}

	/**
	 * @param aGroup2
	 */
	public void setGroup2Name(String aGroup2)
	{
		group2Name = aGroup2;
	}

	/**
	 * @param aGroup3
	 */
	public void setGroup3Name(String aGroup3)
	{
		group3Name = aGroup3;
	}

	/**
	 * @param aName
	 */
	public void setName(String aName)
	{
		name = aName;
	}

	/**
	 * @param aResolvedDescription
	 */
	public void setResolvedDescription(String aResolvedDescription)
	{
		resolvedDescription = aResolvedDescription;
	}

	/**
	 * @param aCollection
	 */
	public void setSupOrderConditionRelValues(Collection aCollection)
	{
		supOrderConditionRelValues = aCollection;
	}

	/**
	 * @return
	 */
	public boolean isLikeConditionInd()
	{
		return likeConditionInd;
	}

	/**
	 * @param b
	 */
	public void setLikeConditionInd(boolean b)
	{
		likeConditionInd = b;
	}

	public int compareTo(Object obj) throws ClassCastException {
		SupOrderConditionResponseEvent evt = (SupOrderConditionResponseEvent)obj;
		return name.compareTo(evt.getName());
	}

	/**
	 * @return
	 */
	public boolean isSpecialCondition()
	{
		return specialCondition;
	}

	/**
	 * @param b
	 */
	public void setSpecialCondition(boolean b)
	{
		specialCondition = b;
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
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return Returns the allCourtIds.
	 */
	public HashSet getAllCourtIds() {
		return allCourtIds;
	}
	/**
	 * @param allCourtIds The allCourtIds to set.
	 */
	public void setAllCourtIds(HashSet allCourtIds) {
		this.allCourtIds = allCourtIds;
	}
}
