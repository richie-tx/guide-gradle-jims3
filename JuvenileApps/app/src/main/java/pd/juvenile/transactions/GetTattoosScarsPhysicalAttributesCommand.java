package pd.juvenile.transactions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.codetable.GetCodesEvent;
import messaging.codetable.GetCodesEventGroup;
import messaging.codetable.person.reply.ScarsMarksTattoosCodeResponseEvent;
import messaging.juvenile.GetTattoosScarsPhysicalAttributesEvent;
import messaging.juvenile.GetTattoosScarsPhysicalAttributesEventGroup;
import messaging.juvenile.reply.TattoosScarsPhysicalAttributesResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

import org.apache.commons.collections.FastArrayList;
import org.apache.commons.collections.FastHashMap;

import pd.codetable.person.ScarsMarksTattoosCode;
import pd.juvenile.JuvenilePhysicalAttributesTattoosScarsMarksTattoosCode;
import pd.juvenile.JuvenileScarsAndMarksScarsMarksTattoosCode;


public class GetTattoosScarsPhysicalAttributesCommand implements ICommand{
	
	   public void execute(IEvent event){
		   GetTattoosScarsPhysicalAttributesEvent myEvent = (GetTattoosScarsPhysicalAttributesEvent) event;
		   GetCodesEventGroup getCodes = new GetCodesEventGroup();            
           String[] codesArray = new String[1];
           codesArray[0] = myEvent.getJuvenileNum();
           getCodes.setCodes(codesArray);
           IHome home = new Home();
           Iterator k = home.findAll(getCodes, JuvenilePhysicalAttributesTattoosScarsMarksTattoosCode.class);
           Map tattooMap = new FastHashMap();
           TattoosScarsPhysicalAttributesResponseEvent respEvent = new TattoosScarsPhysicalAttributesResponseEvent();
           if(k != null){
           while(k.hasNext())
           {
               JuvenilePhysicalAttributesTattoosScarsMarksTattoosCode code = (JuvenilePhysicalAttributesTattoosScarsMarksTattoosCode) k.next();                
               List tattoos = (List) tattooMap.get("Tattoos");                
               List comments = (List) tattooMap.get("OtherTattooComments");
               if(tattoos == null)
               {
                   tattoos = new FastArrayList();
                   comments = new FastArrayList();
                   tattooMap.put("Tattoos", tattoos);
                   tattooMap.put("OtherTattooComments", comments);
               }
               if (code.getChildId() != null){
               ScarsMarksTattoosCode tattooCode = ScarsMarksTattoosCode.find(code.getChildId());
               tattoos.add(tattooCode);
               }
               if(code.getOtherTattooComment() != null){
            	   comments.add(code.getOtherTattooComment());
               }
               respEvent.setTattooEntryDate(code.getEntryDate());
           }
           respEvent.setTattoos((List)tattooMap.get("Tattoos"));
           respEvent.setOtherTattooComments((List)tattooMap.get("OtherTattooComments"));
           
	   	 }
           GetTattoosScarsPhysicalAttributesEventGroup getScarsEvent = new GetTattoosScarsPhysicalAttributesEventGroup();
           getScarsEvent.setJuvenileNum(myEvent.getJuvenileNum());
           Iterator s = home.findAll(getScarsEvent, JuvenileScarsAndMarksScarsMarksTattoosCode.class);
           if(s != null){
			while (s.hasNext())
				{
					JuvenileScarsAndMarksScarsMarksTattoosCode code = (JuvenileScarsAndMarksScarsMarksTattoosCode) s.next();
					List scars = (List) tattooMap.get("scars");
					if(scars == null){
						scars = new FastArrayList();
						tattooMap.put("scars", scars);
					}
				if(code.getChildId() != null){
					ScarsMarksTattoosCode child = (ScarsMarksTattoosCode) new mojo.km.persistence.Reference(code.getChildId(),
							ScarsMarksTattoosCode.class).getObject();
					scars.add(child);
				}
				respEvent.setScarEntryDate(code.getEntryDate());
			}           
		   respEvent.setScars((List)tattooMap.get("scars"));
           }
           IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
           dispatch.postEvent(respEvent);
	   }

}
