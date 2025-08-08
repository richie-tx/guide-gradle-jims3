package pd.juvenile.transactions;

import java.util.Collection;
import java.util.Iterator;

import pd.codetable.person.ScarsMarksTattoosCode;
import pd.juvenile.JuvenilePhysicalAttributesTattoosScarsMarksTattoosCode;
import pd.juvenile.JuvenileScarsAndMarksScarsMarksTattoosCode;

import messaging.codetable.GetAllCodesForTheDate;
import messaging.codetable.GetAllScarCodesForDate;
import messaging.juvenile.SaveJuvenileTattooAndScarsEvent;
import messaging.juvenilewarrant.ScarMarkRequestEvent;
import messaging.juvenilewarrant.TattooRequestEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;

public class SaveJuvenileTattooAndScarsCommand implements
		mojo.km.context.ICommand {

	public void execute(IEvent event) {

		SaveJuvenileTattooAndScarsEvent saveEvent = (SaveJuvenileTattooAndScarsEvent) event;
		Collection tattoos = MessageUtil.compositeToCollection(saveEvent,
				TattooRequestEvent.class);
		Collection scars = MessageUtil.compositeToCollection(saveEvent,
				ScarMarkRequestEvent.class);
//		if(tattoos == null || tattoos.isEmpty()){
			GetAllCodesForTheDate removeEvent = new GetAllCodesForTheDate();
			removeEvent.setEntryDate(saveEvent.getEntryDate());
			removeEvent.setParentId(saveEvent.getJuvenileId());
			Iterator k = new Home().findAll(removeEvent, JuvenilePhysicalAttributesTattoosScarsMarksTattoosCode.class);
			while(k.hasNext()){
				JuvenilePhysicalAttributesTattoosScarsMarksTattoosCode removeTat = (JuvenilePhysicalAttributesTattoosScarsMarksTattoosCode) k.next();
				removeTat.delete();
			}
//		}
		
//		if(scars == null || scars.isEmpty()){
			GetAllScarCodesForDate removeScarEvent = new GetAllScarCodesForDate();
			removeScarEvent.setEntryDate(saveEvent.getEntryDate());
			removeScarEvent.setParentId(saveEvent.getJuvenileId());
			Iterator m = new Home().findAll(removeScarEvent, JuvenileScarsAndMarksScarsMarksTattoosCode.class);
			while(m.hasNext()){
				JuvenileScarsAndMarksScarsMarksTattoosCode removeScar = (JuvenileScarsAndMarksScarsMarksTattoosCode) m.next();
				removeScar.delete();
			}
//		}
		
		if (tattoos != null) {
			Iterator i = tattoos.iterator();
			while (i.hasNext()) {
				TattooRequestEvent tattoosEvent = (TattooRequestEvent) i.next();
				JuvenilePhysicalAttributesTattoosScarsMarksTattoosCode saveTattoos = new JuvenilePhysicalAttributesTattoosScarsMarksTattoosCode();
				saveTattoos.setChildId(tattoosEvent.getCode());
				saveTattoos.setParentId(saveEvent.getJuvenileId());
				saveTattoos.setEntryDate(DateUtil.dateToString(saveEvent.getEntryDate(), "mm/DD/yyyy"));
				if (!saveEvent.getOtherTattooComments().equals("")){
					saveTattoos.setOtherTattooComment(saveEvent.getOtherTattooComments());
				}
				IHome home = new Home();
				home.bind(saveTattoos);
				

			}
		}

		if (scars != null) {
			Iterator i = scars.iterator();
			while (i.hasNext()) {
				ScarMarkRequestEvent tattoosEvent = (ScarMarkRequestEvent) i.next();
				JuvenileScarsAndMarksScarsMarksTattoosCode saveScars = new JuvenileScarsAndMarksScarsMarksTattoosCode();
				saveScars.setChildId(tattoosEvent.getCode());
				saveScars.setParentId(saveEvent.getJuvenileId());
				IHome home = new Home();
				home.bind(saveScars);

			}
		}
	}

}
