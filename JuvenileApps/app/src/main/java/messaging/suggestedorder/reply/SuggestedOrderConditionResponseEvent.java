/*
 * Created on Sep 29, 2005
 *
 */
package messaging.suggestedorder.reply;

import java.util.Comparator;
import java.util.Date;

import naming.SupervisionConstants;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
 *
 */
public class SuggestedOrderConditionResponseEvent extends ResponseEvent implements Comparable
{
	private String conditionId;
	private String conditionLiteral;
	private String conditionLiteralPreview;
	private String conditionName;
	private String conditionSubType;
	private String conditionSubTypeDetail;
	private String conditionType;
	private Date effectiveDate;
	private Date inactiveDate;
	private String seqNum;
	private String standardId;
	private String statusId;
	private String suggestedOrderConditionId;
	private String suggestedOrderId;

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object arg0)
	{
		SuggestedOrderConditionResponseEvent socre0 = (SuggestedOrderConditionResponseEvent) arg0;
		String newString = "";
		if (this.seqNum == null)
		{
			this.seqNum = newString;
		}
		if (socre0.getSeqNum() == null)
		{
			socre0.setSeqNum(newString);
		}
		if (!this.seqNum.equals(newString) && (!socre0.getSeqNum().equals(newString)))
		{
			String seqNum1 = prependWithZeros(this.seqNum,3);
			String seqNum2 = prependWithZeros(socre0.getSeqNum(),3);
			return seqNum1.compareTo(seqNum2);
		}
		else
		{
			if (this.conditionName == null)
			{
				this.conditionName = newString;
			}
			if (socre0.getConditionName() == null)
			{
				socre0.setConditionName(newString);
			}
			return this.conditionName.compareTo(socre0.getConditionName());
		}
		
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
	public String getConditionLiteral()
	{
		return conditionLiteral;
	}

	/**
	 * @return
	 */
	public String getConditionLiteralPreview()
	{
		return conditionLiteralPreview;
	}

	/**
	 * @return
	 */
	public String getConditionName()
	{
		return conditionName;
	}

	/**
	 * @return
	 */
	public String getConditionSubType()
	{
		return conditionSubType;
	}

	/**
	 * @return
	 */
	public String getConditionSubTypeDetail()
	{
		return conditionSubTypeDetail;
	}

	/**
	 * @return
	 */
	public String getConditionType()
	{
		return conditionType;
	}

	/**
	 * @return
	 */
	public Date getEffectiveDate()
	{
		return effectiveDate;
	}

	/**
	 * @return
	 */
	public Date getInactiveDate()
	{
		return inactiveDate;
	}
	/**
	 * @return
	 */
	public String getSeqNum()
	{
		return seqNum;
	}

	/**
	 * @return
	 */
	public String getStandardId()
	{
		return standardId;
	}

	/**
	 * @return
	 */
	public String getStandardDesc()
	{
		String string = "";
		if(standardId.equals(SupervisionConstants.STANDARD_ONLY_CONDITION)){
			string = SupervisionConstants.STANDARD_ONLY_CONDITION_DESC;
		}else if(standardId.equals(SupervisionConstants.NON_STANDARD_ONLY_CONDITION)){
			string = SupervisionConstants.NON_STANDARD_ONLY_CONDITION_DESC;
		}
		return string;
	}
	
	/**
	 * @return
	 */
	public String getStatusId()
	{
		return statusId;
	}

	/**
	 * @return
	 */
	public String getSuggestedOrderConditionId()
	{
		return suggestedOrderConditionId;
	}

	/**
	 * @return
	 */
	public String getSuggestedOrderId()
	{
		return suggestedOrderId;
	}

	/**
	 * @param aConditionSubTypeDetail
	 */
	public void setConditionSubTypeDetail(String aConditionSubTypeDetail)
	{
		conditionSubTypeDetail = aConditionSubTypeDetail;
	}

	/**
	 * @param aConditionId
	 */
	public void setConditionId(String aConditionId)
	{
		conditionId = aConditionId;
	}

	/**
	 * @param string
	 */
	public void setConditionLiteral(String string)
	{
		conditionLiteral = string;
	}

	/**
	 * @param string
	 */
	public void setConditionLiteralPreview(String string)
	{
		conditionLiteralPreview = string;
	}

	/**
	 * @param aConditionName
	 */
	public void setConditionName(String aConditionName)
	{
		conditionName = aConditionName;
	}

	/**
	 * @param string
	 */
	public void setConditionSubType(String string)
	{
		conditionSubType = string;
	}

	/**
	 * @param string
	 */
	public void setConditionType(String string)
	{
		conditionType = string;
	}

	/**
	 * @param aDate
	 */
	public void setEffectiveDate(Date aDate)
	{
		effectiveDate = aDate;
	}

	/**
	 * @param aDate
	 */
	public void setInactiveDate(Date aDate)
	{
		inactiveDate = aDate;
	}

	/**
	 * @param theSeqNum
	 */
	public void setSeqNum(String theSeqNum)
	{
		seqNum = theSeqNum;
	}

	/**
	 * @param string
	 */
	public void setStandardId(String string)
	{
		standardId = string;
	}

	/**
	 * @param aStatusId
	 */
	public void setStatusId(String aStatusId)
	{
		statusId = aStatusId;
	}

	/**
	 * @param theConditionId
	 */
	public void setSuggestedOrderConditionId(String theConditionId)
	{
		suggestedOrderConditionId = theConditionId;
	}

	/**
	 * @param theSuggestedOrderId
	 */
	public void setSuggestedOrderId(String theSuggestedOrderId)
	{
		suggestedOrderId = theSuggestedOrderId;
	}

	/**
	 * @param cre
	 * @return
	 */
	private static String prependWithZeros(String theField, int newFieldLength)
	{

		StringBuffer sb = new StringBuffer(theField);
		int theFieldLength = theField.length();
		if (theFieldLength < newFieldLength)
		{
			for (int i = 0; i < newFieldLength - theFieldLength; i++)
			{
				sb.insert(i, "0");
			}
		}
		return sb.toString();
	}

	public static Comparator StandardComparator = new Comparator() {
		public int compare(Object soCondRE, Object otherSOCondRE) {
		  String standardId = ((SuggestedOrderConditionResponseEvent)soCondRE).getStandardId();
		  String otherStandardId = ((SuggestedOrderConditionResponseEvent)otherSOCondRE).getStandardId();
		  
		  return otherStandardId.compareTo(standardId);
		}	
	};

}
