//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\interviewinfo\\transactions\\UpdateJuvenileInsuranceCommand.java

package pd.juvenilecase.interviewinfo.transactions;

import java.util.Iterator;

import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileInsurance;
import messaging.interviewinfo.UpdateJuvenileInsuranceEvent;
import messaging.interviewinfo.reply.JuvenileInsuranceResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class UpdateJuvenileInsuranceCommand implements ICommand 
{
   
   /**
    * @roseuid 43F37ACF005F
    */
   public UpdateJuvenileInsuranceCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 43F371C6038D
    */
   public void execute(IEvent event) 
   {
		UpdateJuvenileInsuranceEvent saveInsuranceEvent = (UpdateJuvenileInsuranceEvent)event;
	
		Iterator iter = saveInsuranceEvent.getUpdateEvents().iterator();
		while ( iter.hasNext() )
		{
			JuvenileInsuranceResponseEvent evt= (JuvenileInsuranceResponseEvent)iter.next();
			
			JuvenileInsurance insurance = new JuvenileInsurance();
			insurance.setInsuranceTypeId(evt.getTypeId());
			insurance.setCarrier(evt.getCarrier());
			insurance.setPolicyNum(evt.getPolicyNum());
			// Profile stripping fix task 97541
			//Juvenile juv = Juvenile.find( saveInsuranceEvent.getJuvenileNum() );
			Juvenile juv = Juvenile.findJCJuvenile( saveInsuranceEvent.getJuvenileNum() );
			//			
			juv.insertInsurance(insurance);
		}
   }
   
   /**
    * @param event
    * @roseuid 43F371C6038F
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 43F371C60391
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 43F371C60393
    */
   public void update(Object anObject) 
   {
    
   }
   
}
