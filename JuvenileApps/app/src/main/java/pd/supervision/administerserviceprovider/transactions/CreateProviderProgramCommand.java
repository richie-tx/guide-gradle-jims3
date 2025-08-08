//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\transactions\\CreateProviderProgramCommand.java

package pd.supervision.administerserviceprovider.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.lang.time.DateUtils;

import naming.PDAdministerServiceProviderConstants;
import pd.supervision.administerserviceprovider.JuvenileServiceProvider;
import pd.supervision.administerserviceprovider.ProgramSourceFund;
import pd.supervision.administerserviceprovider.ProviderProgram;
import pd.supervision.administerserviceprovider.SearchServiceProvider;
import messaging.administerserviceprovider.CreateProviderProgramEvent;
import messaging.administerserviceprovider.reply.ProviderProgramResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;

public class CreateProviderProgramCommand implements ICommand
{

	/**
	 * @roseuid 44735389025C
	 */
	public CreateProviderProgramCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 44734FEC00F5
	 */
	public void execute(IEvent event)
	{
		CreateProviderProgramEvent createEvent = (CreateProviderProgramEvent) event;
		ProviderProgram providerProgram = null;

		if(createEvent.isCreate()) {
			providerProgram = new ProviderProgram();
			providerProgram.setProviderProgram(createEvent);
			providerProgram.setCreateUserID(createEvent.getUserID());
			providerProgram.createOID();
			String providerProgramId = providerProgram.getOID().toString();
			providerProgram.setProgramId(createEvent.getProgramID());
			//US #11376 - save the new Program Source Fund
			ProgramSourceFund progFund = new ProgramSourceFund();
			progFund.setProvProgramId(providerProgramId);
			progFund.setFundEntryDate(new Date());
			progFund.setFundStartDate(createEvent.getFundStartDate());
			progFund.setFundEndDate(createEvent.getFundEndDate());
			progFund.setProgramSourceFund(createEvent.getSourceFund());			
			if(createEvent.getFundEndDate()==null)
				progFund.setFundStatus("ACTIVE");
			else
				progFund.setFundStatus("INACTIVE");		
			progFund.createOID();
			this.sendProviderProgramResponseEvent(providerProgramId); 
		}
		else if (createEvent.isInactivate()) {
			inactivateProgramStatus(createEvent);
		}		
		else {
			if(createEvent.getProviderProgramId() != null && !(createEvent.getProviderProgramId().equals(""))) {
				providerProgram = ProviderProgram.find(createEvent.getProviderProgramId());
			}			
			providerProgram.setProviderProgram(createEvent);
			if(createEvent.isNewSourceFund())
			{
				ProgramSourceFund newFund = new ProgramSourceFund();
				newFund.setProvProgramId(createEvent.getProviderProgramId());
				newFund.setFundEntryDate(new Date());
				newFund.setFundStartDate(createEvent.getFundStartDate());
				//newFund.setFundEndDate(createEvent.getFundEndDate());				
				newFund.setProgramSourceFund(createEvent.getSourceFund());
				//if(createEvent.getFundEndDate()==null)
				newFund.setFundStatus("ACTIVE");
				//else
					//newFund.setFundStatus("INACTIVE");				
				newFund.createOID();
			}
			if(createEvent.getOldSourceFundId()!=null)
			{
				ProgramSourceFund oldFund = ProgramSourceFund.find(createEvent.getOldSourceFundId());
				if(createEvent.getFundStartDate() != oldFund.getFundStartDate() && !createEvent.isNewSourceFund())
					oldFund.setFundStartDate(createEvent.getFundStartDate());
				//if(createEvent.getFundEndDate()!= oldFund.getFundEndDate() && !createEvent.isNewSourceFund())
				//	oldFund.setFundEndDate(createEvent.getFundEndDate());				
				//oldFund.setProgramSourceFund(createEvent.getSourceFund());
				if(createEvent.isNewSourceFund())
				{
				    oldFund.setFundEndDate(DateUtils.addDays(createEvent.getFundStartDate(), -1));
				    oldFund.setFundStatus("INACTIVE");
				}
				
				
					
			}
		}
	}
	private void inactivateProgramStatus(CreateProviderProgramEvent createEvent) {

		ProviderProgram program = ProviderProgram.find(createEvent.getProviderProgramId());
		program.setProviderProgram(createEvent);
		program.setStatusChangeDate(DateUtil.getCurrentDate());

		String programStatus = program.getStatusId();
		String serviceProviderId = program.getJuvenileServiceProviderId(); 
		boolean updateStatus = false;
		
		Iterator iter = SearchServiceProvider.findAll(PDAdministerServiceProviderConstants.PROGRAM_ID, createEvent.getProviderProgramId());
		ArrayList listServiceNames = new ArrayList();
/*		
		while(iter.hasNext()){ 
			SearchServiceProvider searchServiceProvider = (SearchServiceProvider)iter.next();
			if(searchServiceProvider.getServiceStatusId() != PDAdministerServiceProviderConstants.INACTIVE) {
				listServiceNames.add(searchServiceProvider.getServiceName());
			}
		}
		if(!(listServiceNames.size() !=0 && listServiceNames.size() > 0)) {
			updateStatus =  true;
		}
		if (updateStatus && programStatus.equals(PDAdministerServiceProviderConstants.PENDING)) {
*/
		
		if (programStatus.equals(PDAdministerServiceProviderConstants.PENDING)) {		
			program.updateProgramStatus(createEvent);		
			this.updateServiceProviderStatus(serviceProviderId);
		}
		
		//User Story 11376 - inactivate active source fund
		if(createEvent.getOldSourceFundId()!=null)
		{
			ProgramSourceFund oldFund = ProgramSourceFund.find(createEvent.getOldSourceFundId());
			if(oldFund!=null)
			{
				oldFund.setFundStatus("INACTIVE");
				if(program != null && program.getStatusId() != null && program.getStatusId().equalsIgnoreCase("I") && program.getEndDate() != null){
				  //set source fund end-date to program end-date - Bug 163544
				    oldFund.setFundEndDate(program.getEndDate());
				} else {
				    oldFund.setFundEndDate(new Date());
				}				
			}
		}
//		this.updateServiceProviderStatus(serviceProviderId);		
	}	
	
	public void updateServiceProviderStatus(String serviceProviderId) {
		if(serviceProviderId != null && !serviceProviderId.equals("")) {
			Iterator iter = ProviderProgram.findAll(PDAdministerServiceProviderConstants.JUV_SERVICE_PROVIDER_ID, serviceProviderId);
			int count = 0;
			while(iter.hasNext()){
				ProviderProgram program = (ProviderProgram) iter.next();
//				if(program.getStatusId().equals(PDAdministerServiceProviderConstants.PENDING)) {
				if(program.getStatusId().equals(PDAdministerServiceProviderConstants.ACTIVE)) {
					count = 0;
					break;					
				}
				if(program.getStatusId().equals(PDAdministerServiceProviderConstants.PENDING)) {
					count++;
				}
			}
			if((count != 0) && (count == 1)) {
				JuvenileServiceProvider juvServProv = JuvenileServiceProvider.find(new Integer(serviceProviderId).intValue());
				juvServProv.setStatusId(PDAdministerServiceProviderConstants.PENDING);
				juvServProv.setStatusChangeDate(DateUtil.getCurrentDate());			
			}
		}
	}	
	
	private void sendProviderProgramResponseEvent(String providerProgramId)
	{
		ProviderProgramResponseEvent event = new ProviderProgramResponseEvent();		
		event.setProviderProgramId(providerProgramId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(event);
	}	
	
	/**
	 * @param event
	 * @roseuid 44734FEC0103
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 44734FEC0105
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 44734FEC0113
	 */
	public void update(Object anObject)
	{

	}	
}