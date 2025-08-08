//Source file: C:\\views\\dev\\app\\src\\pd\\juvenile\\transactions\\SaveJuvenileJobCommand.java

package pd.juvenile.transactions;

import pd.juvenile.JuvenileJob;
import messaging.juvenile.SaveJuvenileJobEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class SaveJuvenileJobCommand implements ICommand 
{
   
   /**
    * @roseuid 42B18DC9035B
    */
   public SaveJuvenileJobCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42B18309007F
    */
    public void execute(IEvent event) 
    {
		SaveJuvenileJobEvent request = (SaveJuvenileJobEvent) event;
		JuvenileJob juvenilejob = new JuvenileJob();
		juvenilejob.setJuvenileId(request.getJuvenileNum());
		if(request.getSupervisorFamilyNum()!=null && !request.getSupervisorFamilyNum().equals(""))
			juvenilejob.setSupervisorFamilyNum(request.getSupervisorFamilyNum());
		else{
			juvenilejob.setSupervisorFamilyNum(null);
		}
		juvenilejob.setEmploymentPlace(request.getEmploymentPlace());
		juvenilejob.setEmploymentStatusId(request.getEmploymentStatus());
		juvenilejob.setSupervisorFirstName(request.getSupervisorFirstName());
		juvenilejob.setSupervisorLastName(request.getSupervisorLastName());
		juvenilejob.setSupervisorMiddleName(request.getSupervisorMiddleName());
		juvenilejob.setWorkHours(request.getWorkHours());
		juvenilejob.setJobDescription(request.getJobDescription());
		juvenilejob.setEntryDate(request.getEntryDate());
		juvenilejob.setSalary(request.getSalary());
		juvenilejob.setSalaryRateId(request.getSalaryRate());
	}
   
   /**
    * @param event
    * @roseuid 42B183090081
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42B183090083
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42B18309008C
    */
   public void update(Object anObject) 
   {
    
   }
   
}
