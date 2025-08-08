
package messaging.juvenilecase;
import mojo.km.messaging.RequestEvent;

public class GetDetailsbyPetitionNumEvent extends RequestEvent{

	private String petitionNum;

	public String getPetitionNum()
	{
	    return petitionNum;
	}

	public void setPetitionNum(String petitionNum)
	{
	    this.petitionNum = petitionNum;
	}
	
}
