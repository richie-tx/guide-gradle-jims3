package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class DeleteProductionSupportPetitionEvent extends RequestEvent 
{
   
	private String petitionId;	 
   
   

/**
    * @roseuid 45702FFC0393
    */
   public DeleteProductionSupportPetitionEvent() 
   {
    
   }

   public String getPetitionId()
	{
	    return petitionId;
	}

	public void setPetitionId(String petitionId)
	{
	    this.petitionId = petitionId;
	}
   
}
