package pd.juvenilecase.transactions;

import java.util.Iterator;

import pd.juvenile.JuvenileBuilder;
import pd.juvenilecase.JJSJuvenile;
import messaging.juvenilecase.GetJJSJuvenileEvent;
import messaging.juvenilecase.reply.JJSJuvenileResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.util.MessageUtil;
import mojo.km.utilities.DateUtil;

public class GetJJSJuvenileCommand implements ICommand{

	@Override
	public void execute(IEvent event) throws Exception {
		// TODO Auto-generated method stub
		GetJJSJuvenileEvent request = (GetJJSJuvenileEvent) event;
		String juvenileNum = request.getJuvenileNum();
		String detentionStatus = request.getDetentionStatusId();
		
		if (juvenileNum != null ) {		    	
		     JJSJuvenile juvenile = JJSJuvenile.find(juvenileNum );
		     String masterStatus = (juvenile != null)?juvenile.calculatemasterStatusId():null;
		     if ( juvenile != null ){
			 JJSJuvenileResponseEvent response = new JJSJuvenileResponseEvent();
			 response.setJuvenileNum(juvenile.getOID());
			 response.setBirthDate( juvenile.getBirthDate() );
			 response.setAge( juvenile.getAge());
			 response.setFirstName( juvenile.getFirstName());
			 response.setLastName( juvenile.getLastName());
			 response.setMiddleName( juvenile.getMiddleName());
			 response.setDob(DateUtil.dateToString(juvenile.getBirthDate(),DateUtil.DATE_FMT_1));
			 response.setRaceId( juvenile.getRaceId());
			 response.setOriginalRaceId( juvenile.getOriginalRaceId() );
			 response.setSexId( juvenile.getSexId());			 
			 //response.setStatusId( juvenile.getStatusId());
			 //updating master status to jjs_ms_juvenile so it will reflect in all pages.
			 response.setStatusId(masterStatus );
			 if (!juvenile.getStatusId().equalsIgnoreCase(masterStatus))
			 {
			     juvenile.setStatusId(masterStatus);
			     new Home().bind( juvenile );
			 }
			 //
			 response.setPropointLevelId( juvenile.getPropointLevelId());
			 response.setPropointUnitId( juvenile.getPropointUnitId());
			 response.setRectype(juvenile.getRectype());
			 response.setJuvenileSsn( juvenile.getJuvenileSsn() );
			 response.setPreferredFirstName(juvenile.getPreferredFirstName());
				
			 MessageUtil.postReply(response);
		     }
		} else if (detentionStatus != null ){
		    
		   Iterator<JJSJuvenile> juvenilesIter = JJSJuvenile.findByDetentionStatus("T");
		   while ( juvenilesIter.hasNext() ){
		        JJSJuvenile juvenile = juvenilesIter.next();
		        JJSJuvenileResponseEvent response = new JJSJuvenileResponseEvent();
		       	response.setJuvenileNum(juvenile.getOID());
			response.setBirthDate( juvenile.getBirthDate() );
			response.setAge( juvenile.getAge());
			response.setFirstName( juvenile.getFirstName());
			response.setLastName( juvenile.getLastName());
			response.setMiddleName( juvenile.getMiddleName());
			response.setDob(DateUtil.dateToString(juvenile.getBirthDate(),DateUtil.DATE_FMT_1));
			response.setRaceId( juvenile.getRaceId());
			response.setSexId( juvenile.getSexId());
			response.setStatusId( juvenile.getStatusId());			
			response.setPropointLevelId( juvenile.getPropointLevelId());
			response.setPropointUnitId( juvenile.getPropointUnitId());
			response.setRectype(juvenile.getRectype());
			response.setJuvenileSsn( juvenile.getJuvenileSsn() );
			response.setDetentionStatusId(juvenile.getDetentionStatusId());
			response.setDetentionFacilityId(juvenile.getDetentionFacilityId());
			
			MessageUtil.postReply(response);
		       
		       
		   }
		}
		
		
	}

}
