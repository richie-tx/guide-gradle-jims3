//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administercasenotes\\DeleteCasenoteEvent.java

package messaging.administercasenotes;

import mojo.km.messaging.PersistentEvent;

public class DeleteCasenoteEvent extends PersistentEvent 
{
   
	private String casenoteId;

	/**
    * @roseuid 44F4616B01DA
    */
   public DeleteCasenoteEvent() 
   {
   }
   
	public String getCasenoteId()
	{
		return casenoteId;
	}

	/**
	 * @param aCasenoteId  The casenoteId to set.
	 */
	public void setCasenoteId(String aCasenoteId)
	{
		this.casenoteId = aCasenoteId;
	}

}
