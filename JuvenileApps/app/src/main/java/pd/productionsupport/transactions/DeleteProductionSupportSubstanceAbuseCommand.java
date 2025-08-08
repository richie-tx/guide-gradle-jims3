package pd.productionsupport.transactions;

import java.util.Iterator;

import pd.juvenile.DrugTesting;
import pd.juvenile.SubstanceAbuse;
import pd.juvenilecase.casefile.Activity;
import messaging.productionsupport.DeleteProductionSupportSubstanceAbuseEvent;
import messaging.productionsupport.DeleteProductionSupportDrugTestingInfoEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class DeleteProductionSupportSubstanceAbuseCommand implements ICommand
{
    public DeleteProductionSupportSubstanceAbuseCommand(){}
    
    public void execute(IEvent event)
    {
	System.out.println("DeleteProductionSupportSubstanceAbuse");
	DeleteProductionSupportSubstanceAbuseEvent deleteEvent = (DeleteProductionSupportSubstanceAbuseEvent) event;
	String substanceAbuseId = deleteEvent.getSubstanceAbuseId();
	if ( substanceAbuseId != null
		&& substanceAbuseId.length() > 0 ){
	    Iterator substanceAubseInfoIter = SubstanceAbuse.findAll("OID", substanceAbuseId);;
	    while( substanceAubseInfoIter.hasNext() ) {
		    SubstanceAbuse sAbuse = (SubstanceAbuse) substanceAubseInfoIter.next();
		    sAbuse.delete();
	    }
	}
    }

}
