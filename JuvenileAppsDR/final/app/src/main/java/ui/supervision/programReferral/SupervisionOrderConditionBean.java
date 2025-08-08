package ui.supervision.programReferral;

import java.util.Comparator;


/**
 * 
 * @author cc_bjangay
 *
 */
public class SupervisionOrderConditionBean
{
	private int conditionSeqNumber;
	private String conditionDesc;
	public static Comparator conditionComp = new Comparator()
	{

		public int compare(Object o1, Object o2) 
		{
			SupervisionOrderConditionBean condition1 = (SupervisionOrderConditionBean)o1;
			SupervisionOrderConditionBean condition2 = (SupervisionOrderConditionBean)o2;
			
			if(condition1.getConditionSeqNumber() == condition2.getConditionSeqNumber())
			{
				return 0;
			}
			if(condition1.getConditionSeqNumber() > condition2.getConditionSeqNumber())
			{
				return 1;
			}
			return -1;
		}
	};
	
	

	/**
	 * @return the conditionSeqNumber
	 */
	public int getConditionSeqNumber() {
		return conditionSeqNumber;
	}

	/**
	 * @param conditionSeqNumber the conditionSeqNumber to set
	 */
	public void setConditionSeqNumber(int conditionSeqNumber) {
		this.conditionSeqNumber = conditionSeqNumber;
	}

	/**
	 * @return the conditionDesc
	 */
	public String getConditionDesc() {
		return conditionDesc;
	}

	/**
	 * @param conditionDesc the conditionDesc to set
	 */
	public void setConditionDesc(String conditionDesc) {
		this.conditionDesc = conditionDesc;
	}
	
}
