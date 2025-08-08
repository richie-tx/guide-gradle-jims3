//Source file: C:\\views\\dev\\app\\src\\pd\\contact\\agency\\transactions\\ValidateDepartmentRequirementsCommand.java

/*
 * Created on Aug 29, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
 
package pd.contact.agency.transactions;

import java.util.Iterator;

import messaging.agency.ValidateDepartmentUpdateRequirementsEvent;
import messaging.security.reply.DuplicateRecordErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.PDSecurityConstants;
import pd.contact.agency.Department;

/**
 * @author mchowdhury
 * @description validate a department for update -- duplicate check  
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class ValidateDepartmentUpdateRequirementsCommand implements ICommand
{

	/**
	 * @roseuid 430638D30063
	 */
	public ValidateDepartmentUpdateRequirementsCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 4306266A03D6
	 */
	public void execute(IEvent event)
	{
		ValidateDepartmentUpdateRequirementsEvent validateEvent = (ValidateDepartmentUpdateRequirementsEvent) event;
		
		Department department = Department.find(validateEvent.getDepartmentId());
        
		// check department name. 
		if(validateEvent.getDepartmentName() != null && !(validateEvent.getDepartmentName().equals(""))){
			if(department != null && !(department.getDepartmentName().equalsIgnoreCase(validateEvent.getDepartmentName()))){
		      if(this.checkDepartmentName(validateEvent)){
				  return;
			   }
		   }
		}
		
		// check org code
//		if(validateEvent.getOrgCode() != null && !(validateEvent.getOrgCode().equals(""))){
//		    if(department != null && !(department.getOrgCode().equalsIgnoreCase(validateEvent.getOrgCode()))){
//			    this.checkOrgCode(validateEvent);
//		    }
//		}
	}
	
	
	/**
	 * @param validateEvent
	 * @return boolean true/false
	 */
	private boolean checkDepartmentName(ValidateDepartmentUpdateRequirementsEvent validateEvent)
	{/*
		Iterator iter = Department.findAll(PDSecurityConstants.DEPARTMENT_NAME,validateEvent.getDepartmentName().toUpperCase());
		while(iter.hasNext()){
		   iter.next();
		   this.sendDuplicateRecordErrorResponseEvent("error.duplicate.DepartmentName");
		   return true;
		}*/ //87191
		return false; 			
	}

	/**
	 * @param validateEvent
	 * @return boolean true/false
	 */
	private boolean checkOrgCode(ValidateDepartmentUpdateRequirementsEvent validateEvent)
	{
	//87191
		/*validateEvent.setDepartmentId("");
		validateEvent.setDepartmentName("");
		Iterator iter = Department.findAll(validateEvent);
		while(iter.hasNext()){
		   iter.next();
		   this.sendDuplicateRecordErrorResponseEvent("error.duplicate.OrgCode");
		   return true;
		}*/ //87191
		return false; 			
	}

	/**
	 * @param validateEvent
	 * @return boolean true/false
	 */
	private boolean checkDepartmentId(ValidateDepartmentUpdateRequirementsEvent validateEvent)
	{
		Department department = Department.find(validateEvent.getDepartmentId());
		if(department != null){
			this.sendDuplicateRecordErrorResponseEvent("error.duplicate.DepartmentId");
			return true;
		}
		return false; 
	}

	/**
	 * @param errorKey
	 */
	private void sendDuplicateRecordErrorResponseEvent(String errorKey)
	{
		DuplicateRecordErrorResponseEvent errorEvent = new DuplicateRecordErrorResponseEvent();
		errorEvent.setMessage(errorKey);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(errorEvent);
	}

	/**
	 * @param event
	 * @roseuid 4306266A03D8
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 4306266A03DA
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 4306266A03E5
	 */
	public void update(Object anObject)
	{

	}
}