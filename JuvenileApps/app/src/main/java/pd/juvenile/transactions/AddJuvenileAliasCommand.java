package pd.juvenile.transactions;

import java.util.Iterator;
import java.util.List;

import pd.juvenile.JuvenileAlias;
import messaging.juvenile.AddJuvenileAliasEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class AddJuvenileAliasCommand implements ICommand {

	
	
	public AddJuvenileAliasCommand(){
	    
	}
	
	
	public void execute(IEvent event) throws Exception {
		AddJuvenileAliasEvent juvEvent = (AddJuvenileAliasEvent)event;
		//changed per Bug #87697
		if(juvEvent.isDeleteFlag()){	
		    List<JuvenileAlias> juvAliases = JuvenileAlias.find(juvEvent.getJuvenileNum());
		    Iterator juvAliasIter = juvAliases.iterator();
		    while(juvAliasIter.hasNext())
		    {
			JuvenileAlias juvAlias = (JuvenileAlias)juvAliasIter.next();
			if(juvAlias.getOID().equals(juvEvent.getOID()))
			{
        			juvAlias.setOID(juvEvent.getOID());
        			juvAlias.setDeleted();
			}
		    }
		} else {
		    JuvenileAlias juvAlias = new JuvenileAlias();
			juvAlias.setJuvenileNum(juvEvent.getJuvenileNum());
			juvAlias.setJuvenileType(juvEvent.getJuvenileType());
			juvAlias.setAliasNotes(juvEvent.getAliasNotes());
			juvAlias.setRaceId(juvEvent.getRaceId());
			juvAlias.setSexId(juvEvent.getSexId());
			juvAlias.setFirstName(juvEvent.getFirstName());
			juvAlias.setLastName(juvEvent.getLastName());
			juvAlias.setMiddleName(juvEvent.getMiddleName());
			juvAlias.setDateOfBirth(juvEvent.getDateOfBirth());
			juvAlias.setAliasEntryDate(juvEvent.getAliasEntryDate());
		}		

	}

}
