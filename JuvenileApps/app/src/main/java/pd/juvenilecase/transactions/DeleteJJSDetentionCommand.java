package pd.juvenilecase.transactions;/*package pd.juvenilecase.transactions;

//user-story 81390
import messaging.juvenilecase.DeleteJJSDetentionEvent;
import mojo.km.context.ICommand;
import mojo.km.context.multidatasource.M204Exception;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;

public class DeleteJJSDetentionCommand implements ICommand {

	@Override
	public void execute(IEvent event) throws Exception {
		//delete all calendar detention for the juvenile
		DeleteJJSDetentionEvent jjsDetentionEvt = (DeleteJJSDetentionEvent)event;		
		
		if(jjsDetentionEvt!=null && jjsDetentionEvt.getJuvenileNumber()!=null && !jjsDetentionEvt.getJuvenileNumber().isEmpty())
		{
			try{
				JJSCLDetention.findAll(jjsDetentionEvt);
			}catch(M204Exception e){
				e.printStackTrace();
			}
			
		}
	}
}
*/