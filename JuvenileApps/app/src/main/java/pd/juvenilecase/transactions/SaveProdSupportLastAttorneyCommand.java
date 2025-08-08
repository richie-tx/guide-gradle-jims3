//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\SaveProdSupportLastAttorneyCommand.java

package pd.juvenilecase.transactions;

import messaging.juvenilecase.SaveProdSupportLastAttorneyEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JJSLastAttorney;

public class SaveProdSupportLastAttorneyCommand implements ICommand
{

	public SaveProdSupportLastAttorneyCommand()
	{

	}

	public void execute(IEvent event)
	{
		
		SaveProdSupportLastAttorneyEvent saveEvent = (SaveProdSupportLastAttorneyEvent) event;
				
		JJSLastAttorney lastAtyfile = null;	
		lastAtyfile = JJSLastAttorney.find(saveEvent.getLastAttorneyID());
		lastAtyfile.setAtyBarNum(saveEvent.getAtyBarNum());
		lastAtyfile.setAtyName(saveEvent.getAtyName());
		lastAtyfile.setAttConnect(saveEvent.getAttConnect());
		lastAtyfile.setGalName(saveEvent.getGalName());
		lastAtyfile.setLastAttorneyID(saveEvent.getLastAttorneyID());
		
		if ( saveEvent.getGalBarNum() == null
			|| saveEvent.getGalBarNum().replaceAll("\\s", "").length()==0 ){
		    lastAtyfile.setGalBarNum(null);
		} else {
		    lastAtyfile.setGalBarNum(saveEvent.getGalBarNum());
		}
		
		if ( saveEvent.getJjclcourtId() == null
			|| saveEvent.getJjclcourtId().replaceAll("\\s", "").length() == 0 ) {
		    lastAtyfile.setJjclcourtId(null);
		} else {
		    lastAtyfile.setJjclcourtId(saveEvent.getJjclcourtId());
		}
		
		
		if ( saveEvent.getJjcldetentionId() == null
			|| saveEvent.getJjcldetentionId().replaceAll("\\s", "").length() == 0){
		    lastAtyfile.setJjcldetentionId(null);
		} else {
		    lastAtyfile.setJjcldetentionId(saveEvent.getJjcldetentionId());   
		}
		
		
		
		
		
	}

	public void onRegister(IEvent event)
	{

	}

	public void onUnregister(IEvent event)
	{

	}

	public void update(Object anObject)
	{

	}

}
