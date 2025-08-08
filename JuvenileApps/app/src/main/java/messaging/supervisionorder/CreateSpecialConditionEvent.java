//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\supervisionorder\\CreateSpecialConditionEvent.java

package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;

public class CreateSpecialConditionEvent extends RequestEvent 
{
   private String description;
   private String group1;
   private String notes;
   private int sequenceNum;
   
   /**
    * @roseuid 43B2E40D01C5
    */
   public CreateSpecialConditionEvent() 
   {
    
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
	public String getGroup1()
	{
		return group1;
	}
	
	/**
	 * @return
	 */
	public String getNotes()
	{
		return notes;
	}
	
	/**
	 * @param string
	 */
	public void setDescription(String string)
	{
		description = string;
	}
	
	/**
	 * @param string
	 */
	public void setGroup1(String string)
	{
		group1 = string;
	}
	
	/**
	 * @param string
	 */
	public void setNotes(String string)
	{
		notes = string;
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

}
