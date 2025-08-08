package pd.juvenilecase.detentionCourtHearings.transactions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.detentionCourtHearings.DeleteFutureJJSCLDetentionSettingEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.DateUtil;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;

public class DeleteFutureJJSCLDetentionSettingCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	DeleteFutureJJSCLDetentionSettingEvent delEvt = (DeleteFutureJJSCLDetentionSettingEvent) event;
	JJSCLDetention detention = null;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	ArrayList<JJSCLDetention> detentionRecs = new ArrayList<JJSCLDetention>();

	@SuppressWarnings("unchecked")
	Iterator<JJSCLDetention> detentionItr = JJSCLDetention.findAll("chainNumber", delEvt.getChainNum());
	while (detentionItr.hasNext())
	{
	    //remove district dockets from list
	    detention = (JJSCLDetention) detentionItr.next();
	    if( "1".equals(detention.getCourtId().trim()) ){
		detentionRecs.add(detention);
	    }	    
	}
	//sort the array in descending order of court date
	Collections.sort((List<JJSCLDetention>) detentionRecs, new Comparator<JJSCLDetention>() {
	    @Override
	    public int compare(JJSCLDetention det1, JJSCLDetention det2)
	    {
		if (det2.getCourtDate() != null && det1.getCourtDate() != null)
		    return det2.getCourtDate().compareTo(det1.getCourtDate());
		else
		    return -1;
	    }
	});
	if ( !detentionRecs.isEmpty() && detentionRecs.size() > 1 )
	{
	    JJSCLDetention det = (JJSCLDetention) detentionRecs.get(0);
	    Date resetDate = DateUtil.stringToDate( delEvt.getCourtDate(), DateUtil.DEFAULT_DATE_FMT);
	    if ( resetDate != null && resetDate.equals( det.getCourtDate() ))
	    {
		// delete future rec
		det.delete();
		//System.out.println("Record was deleted " + det.getOID());
		
		//Update previous record				    
		JJSCLDetention prevDet = (JJSCLDetention) detentionRecs.get(1);
		//System.out.println("Record was updated " + prevDet.getOID());
		prevDet.setHearingDisposition(null);
		prevDet.setHearingResult(null);
		IHome home = new Home();
	        home.bind( prevDet );
	    }
	    else
	    {
		ErrorResponseEvent myError = new ErrorResponseEvent();
		myError.setDateTimeStamp(new Date());
		myError.setMessage("BACK-OUT ALLOWED ONLY ON THE LAST RECORD IN CHAIN; SUBSEQUENT RECORDS HAVE BEEN FOUND");
		dispatch.postEvent(myError);
		myError = null;
	    }
	}else{ // delete last record in the chain
		JJSCLDetention lastDetRec = (JJSCLDetention) detentionRecs.get(0);
		lastDetRec.delete();
	}	
	
    }

}
