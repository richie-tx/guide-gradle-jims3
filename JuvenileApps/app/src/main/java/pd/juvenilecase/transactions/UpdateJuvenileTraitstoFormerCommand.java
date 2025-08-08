package pd.juvenilecase.transactions;


import java.util.Iterator;
import java.sql.Timestamp;
import messaging.juvenilecase.GetJuvenileTraitsByParentTypeOIDEvent;
import messaging.juvenilecase.UpdateJuvenileTraitstoFormerEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.DateUtil;
import pd.juvenilecase.JuvenileTrait;


/**
 * 
 * @author anpillai
 *
 */
public class UpdateJuvenileTraitstoFormerCommand implements ICommand {

    @Override
    public void execute(IEvent event) throws Exception
    {
	UpdateJuvenileTraitstoFormerEvent evt = (UpdateJuvenileTraitstoFormerEvent)event;
	    // update Referral
	GetJuvenileTraitsByParentTypeOIDEvent jjsEvt = new GetJuvenileTraitsByParentTypeOIDEvent();
	    jjsEvt.setJuvenileNum(evt.getJuvenileNum());
	    jjsEvt.setTraitParent(evt.getTraitType());	    
	    //jjsEvt.setFacilityAdmitOID(evt.getOID());
	    Iterator<JuvenileTrait> refIter = JuvenileTrait.findAll(jjsEvt);

	    while (refIter.hasNext())
	    {
		//add code to check if from transfer then take transferoid coloumn else 
		JuvenileTrait traititer = refIter.next();		
		if (traititer != null)
		{
		    if(evt.getOriginaladmitOID()!=null)
		    {
			    if(evt.getOID().equals(traititer.getTransferAdmitOID()))
			    {
                		    traititer.setStatusId("FO");
                		    traititer.setUpdateTimestamp(new Timestamp(DateUtil.getCurrentDate().getTime()));		    
                		    IHome home = new Home();
                		    home.bind(traititer);                		    
			    }
		    }
		    //if(evt.getAction().equalsIgnoreCase("release"))
		    else
		    {
			    if(evt.getOID().equals(traititer.getFacilityAdmitOID()))
			    {
                		    traititer.setStatusId("FO");
                		    traititer.setUpdateTimestamp(new Timestamp(DateUtil.getCurrentDate().getTime()));		    
                		    IHome home = new Home();
                		    home.bind(traititer);
			    }
		    }
		   /* traititer.setStatusId("FO");
		    traititer.setUpdateTimestamp(new Timestamp(DateUtil.getCurrentDate().getTime()));		    
		    IHome home = new Home();
		    home.bind(traititer);*/
		}
	    }
        
    }

}
