package pd.supervision.suggestedorder;

import java.util.Comparator;
import java.util.Iterator;

import naming.SupervisionConstants;
import messaging.suggestedorder.reply.SuggestedOrderConditionResponseEvent;
import messaging.supervisionoptions.reply.ConditionResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.supervision.supervisionoptions.CommonSupervisionHelper;
import pd.supervision.supervisionoptions.Condition;

/**
* @roseuid 433AAD210075
*/
public class SuggestedOrderCondition extends PersistentObject
{
	/**
	* Properties for condition
	* @referencedType pd.supervisionoptions.Condition
	* @detailerDoNotGenerate false
	*/
	private Condition condition = null;
	private String conditionId;
	private int seqNum;
	/**
	* Properties for suggestedOrder
	* @referenceType pd.supervision.suggestedorder.SuggestedOrder
	* @detailerDoNotGenerate false
	*/
	private SuggestedOrder suggestedOrder = null;
	private String suggestedOrderId;
	private String suggestedOrderConditionId;

	/**
	* @roseuid 433AAD210075
	*/
	public SuggestedOrderCondition()
	{
	}
	/**
	* @roseuid 433AA9280160
	*/
	public void find()
	{
		fetch();
	}
	/**
	* Finds all SuggestedOrderCondition by an attribute value
	* @return 
	* @param attributeName
	* @param attributeValue
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		IHome home = new Home();
		Iterator orderConditions = home.findAll(attributeName, attributeValue, SuggestedOrderCondition.class);
		return orderConditions;
	}

	/**
	* Gets referenced type pd.supervision.supervisionoptions.Condition
	*/
	public Condition getCondition()
	{
		initCondition();
		return condition;
	}
	/**
	* Get the reference value to class :: pd.supervision.supervisionoptions.Condition
	*/
	public String getConditionId()
	{
		fetch();
		return conditionId;
	}
	/**
	* Access method for the seqNum property.
	* @return the current value of the seqNum property
	*/
	public int getSeqNum()
	{
		fetch();
		return seqNum;
	}
	/**
	* Gets referenced type pd.supervision.suggestedorder.SuggestedOrder
	*/
	public SuggestedOrder getSuggestedOrder()
	{
		initSuggestedOrder();
		return suggestedOrder;
	}
	/**
	* Access method for the suggestedOrderConditionId property.
	* @return the current value of the suggestedOrderConditionId property
	*/
	public String getSuggestedOrderConditionId()
	{
		return "" + getOID();
	}
	/**
	* Get the reference value to class :: pd.supervision.suggestedorder.SuggestedOrder
	*/
	public String getSuggestedOrderId()
	{
		fetch();
		return suggestedOrderId;
	}
	/**
	* Initialize class relationship to class pd.supervision.supervisionoptions.Condition
	*/
	private void initCondition()
	{
		if (condition == null)
		{
			condition =
				(Condition) new mojo
					.km
					.persistence
					.Reference(conditionId, Condition.class)
					.getObject();
		}
	}
	/**
	* Initialize class relationship to class pd.supervision.suggestedorder.SuggestedOrder
	*/
	private void initSuggestedOrder()
	{
		if (suggestedOrder == null)
		{
			suggestedOrder =
				(SuggestedOrder) new mojo
					.km
					.persistence
					.Reference(suggestedOrderId, SuggestedOrder.class)
					.getObject();
		}
	}
	/**
	* set the type reference for class member condition
	*/
	public void setCondition(Condition aCondition)
	{
		if (this.condition == null || !this.condition.equals(aCondition))
		{
			markModified();
		}
		if (aCondition.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aCondition);
		}
		setConditionId("" + aCondition.getOID());
		this.condition =
			(Condition) new mojo.km.persistence.Reference(aCondition).getObject();
	}
	/**
	* Set the reference value to class :: pd.supervision.supervisionoptions.Condition
	*/
	public void setConditionId(String aConditionId)
	{
		if (this.conditionId == null || !this.conditionId.equals(aConditionId))
		{
			markModified();
		}
		condition = null;
		this.conditionId = aConditionId;
	}
	/**
	* Sets the value of the seqNum property.
	* @param aSeqNum the new value of the seqNum property
	*/
	public void setSeqNum(int aSeqNum)
	{
		if (this.seqNum != aSeqNum)
		{
			markModified();
		}
		seqNum = aSeqNum;
	}
	/**
	* set the type reference for class member suggestedOrder
	*/
	public void setSuggestedOrder(SuggestedOrder aSuggestedOrder)
	{
		if (this.suggestedOrder == null || !this.suggestedOrder.equals(aSuggestedOrder))
		{
			markModified();
		}
		if (aSuggestedOrder.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aSuggestedOrder);
		}
		setSuggestedOrderId("" + aSuggestedOrder.getOID());
		this.suggestedOrder =
			(SuggestedOrder) new mojo
				.km
				.persistence
				.Reference(aSuggestedOrder)
				.getObject();
	}
	/**
	* Sets the value of the suggestedOrderConditionId property.
	* @param aSuggestedOrderConditionId the new value of the suggestedOrderConditionId property
	*/
	public void setSuggestedOrderConditionId(String aSuggestedOrderConditionId)
	{
		if (this.suggestedOrderConditionId == null
			|| !this.suggestedOrderConditionId.equals(aSuggestedOrderConditionId))
		{
			markModified();
		}
		suggestedOrderConditionId = aSuggestedOrderConditionId;
	}
	/**
	* Set the reference value to class :: pd.supervision.suggestedorder.SuggestedOrder
	*/
	public void setSuggestedOrderId(String aSuggestedOrderId)
	{
		if (this.suggestedOrderId == null || !this.suggestedOrderId.equals(aSuggestedOrderId))
		{
			markModified();
		}
		this.suggestedOrderId = aSuggestedOrderId;
	}
	/**
	 * Creates response event
	 * @return
	 */
	public SuggestedOrderConditionResponseEvent getResponseEvent()
	{
		Condition aCondition = this.getCondition();
//		if(aCondition.getIsDeleted()){
//			return null;
//		}
		//condition may have been deleted.
		if (aCondition == null){
		    return null;
		}
		SuggestedOrderConditionResponseEvent socre = new SuggestedOrderConditionResponseEvent();
		
		socre.setConditionId(this.getConditionId());
		socre.setSuggestedOrderConditionId(this.getSuggestedOrderConditionId());
		socre.setSuggestedOrderId(this.getSuggestedOrderId());
		socre.setSeqNum(new Integer(this.getSeqNum()).toString());
		socre.setStatusId(aCondition.getStatus());
		socre.setEffectiveDate(aCondition.getEffectiveDate());
		socre.setInactiveDate(aCondition.getInactiveDate());

		if (aCondition != null)
		{
			socre.setConditionName(aCondition.getName());
			socre.setConditionLiteral(aCondition.getDescription());
			socre.setEffectiveDate(aCondition.getEffectiveDate());
			socre.setInactiveDate(aCondition.getInactiveDate());
			if (aCondition.getIsStandard())
			{
				socre.setStandardId(SupervisionConstants.STANDARD_ONLY_CONDITION);
			}
			else
			{
				socre.setStandardId(SupervisionConstants.NON_STANDARD_ONLY_CONDITION);
			}
			ConditionResponseEvent cre = CommonSupervisionHelper.getConditionResponseEvent(aCondition);		
			socre.setConditionType(cre.getGroup1Name());
			socre.setConditionSubType(cre.getGroup2Name());
			socre.setConditionSubTypeDetail(cre.getGroup3Name());
		}

		socre.setTopic(SupervisionConstants.SUGGESTED_ORDER_CONDITION_EVENT_TOPIC);

		return socre;
	}
	
	public static Comparator SeqNumComparator = new Comparator() {
		public int compare(Object suggestedOrderCondition, Object otherSuggestedOrderCondition) {
		  int seqNum = ((SuggestedOrderCondition)suggestedOrderCondition).getSeqNum();
		  int otherSeqNum = ((SuggestedOrderCondition)otherSuggestedOrderCondition).getSeqNum();
		  
		  Integer seqNumInt = new Integer(seqNum);
		  Integer otherSeqNumInt = new Integer(otherSeqNum);
		  return seqNumInt.compareTo(otherSeqNumInt);
		}	
	};
	
}
