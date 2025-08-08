package messaging.administerprogramreferrals.reply;

import java.util.Comparator;

import mojo.km.messaging.ResponseEvent;

public class SupervisionOrderConditionResponseEvent extends ResponseEvent
{
	private int conditionSeqNum;
	private String conditionDescription;
	
	
	
	/**
	 * @return the conditionSeqNum
	 */
	public int getConditionSeqNum() {
		return conditionSeqNum;
	}

	/**
	 * @param conditionSeqNum the conditionSeqNum to set
	 */
	public void setConditionSeqNum(int conditionSeqNum) {
		this.conditionSeqNum = conditionSeqNum;
	}

	/**
	 * @return the conditionDescription
	 */
	public String getConditionDescription() {
		return conditionDescription;
	}

	/**
	 * @param conditionDescription the conditionDescription to set
	 */
	public void setConditionDescription(String conditionDescription) {
		this.conditionDescription = conditionDescription;
	}
	
	public static Comparator SeqNumComparator = new Comparator() {
		public int compare(Object conditionDetailRE, Object otherConditionDetailRE) {
		  int seqNum = ((SupervisionOrderConditionResponseEvent)conditionDetailRE).getConditionSeqNum();
		  int otherSeqNum = ((SupervisionOrderConditionResponseEvent)otherConditionDetailRE).getConditionSeqNum();
		  
		  Integer seqNumInt = new Integer(seqNum);
		  Integer otherSeqNumInt = new Integer(otherSeqNum);
		  return seqNumInt.compareTo(otherSeqNumInt);
		}	
	};
	
}
