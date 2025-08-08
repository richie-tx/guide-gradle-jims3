package pd.juvenile.transactions;

import java.util.Iterator;

import pd.juvenile.DrugTesting;
import messaging.juvenile.GetDrugTestingInfoEvent;
import messaging.juvenile.reply.DrugTestingResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;

public class GetDrugTestingInfoCommand implements ICommand
{
    public GetDrugTestingInfoCommand(){}
    
    public void execute(IEvent event) {
	GetDrugTestingInfoEvent getEvent = (GetDrugTestingInfoEvent)event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	
	if ( getEvent.getCasefileId() != null
		&& getEvent.getCasefileId().length() > 0 ) {
	    Iterator drugInfoIter= DrugTesting.findAll("associateCasefile", getEvent.getCasefileId());
	   
	
        	while( drugInfoIter.hasNext() ) {
        	    DrugTesting drugTesting = (DrugTesting)drugInfoIter.next();
        	    DrugTestingResponseEvent responseEvent = new DrugTestingResponseEvent();
        	    
        	    if ( drugTesting != null ){
        		responseEvent.setDrugTestingId(drugTesting.getOID());
        		responseEvent.setAssociateCasefile(drugTesting.getAssociateCasefile());
        		responseEvent.setTestAdministered(drugTesting.getTestAdministered());
        		responseEvent.setTestDate(drugTesting.getTestDate());
        		responseEvent.setTestTime(drugTesting.getTestTime());
        		responseEvent.setSubstanceTested(drugTesting.getSubstanceTested());
        		responseEvent.setDrugTestResults(drugTesting.getDrugTestResults());
        		responseEvent.setTestLocation(drugTesting.getTestLocation());
        		responseEvent.setAdministeredBy(drugTesting.getAdministeredBy());		
        		responseEvent.setComments(drugTesting.getComments());
        		responseEvent.setCreateDate(drugTesting.getCreateTimestamp());
        		responseEvent.setJuvenileId(drugTesting.getJuvenileId());
        		responseEvent.setActivityId(drugTesting.getActivityId());
        		responseEvent.setCreateUser(drugTesting.getCreateUserID());
        		responseEvent.setUpdateUser(drugTesting.getUpdateUserID());
        		responseEvent.setUpdateDate(drugTesting.getUpdateTimestamp());
        		responseEvent.setCreateJIMS2UserID(drugTesting.getCreateJIMS2UserID());
        		responseEvent.setUpdateJIMS2UserID(drugTesting.getUpdateJIMS2UserID());
        		dispatch.postEvent(responseEvent);
        	    }
        	}
	} else if ( getEvent.getDrugTestingId() != null
		&& getEvent.getDrugTestingId().length() > 0 ) {
	    DrugTesting drugTesting = DrugTesting.find( getEvent.getDrugTestingId() );
	    
	    if ( drugTesting != null ){
		DrugTestingResponseEvent responseEvent = new DrugTestingResponseEvent();
		responseEvent.setDrugTestingId(drugTesting.getOID());
		responseEvent.setAssociateCasefile(drugTesting.getAssociateCasefile());
		responseEvent.setTestAdministered(drugTesting.getTestAdministered());
		responseEvent.setTestDate(drugTesting.getTestDate());
		responseEvent.setTestTime(drugTesting.getTestTime());
		responseEvent.setFormarttedTestDate(DateUtil.dateToString(drugTesting.getTestDate(), DateUtil.DATE_FMT_1));
		responseEvent.setFormattedTestTime(DateUtil.dateToString(drugTesting.getTestTime(), DateUtil.TIME24_FMT_1));
		responseEvent.setSubstanceTested(drugTesting.getSubstanceTested());
		responseEvent.setDrugTestResults(drugTesting.getDrugTestResults());
		responseEvent.setTestLocation(drugTesting.getTestLocation());
		responseEvent.setAdministeredBy(drugTesting.getAdministeredBy());		
		responseEvent.setComments(drugTesting.getComments());
		responseEvent.setCreateDate(drugTesting.getCreateTimestamp());
		responseEvent.setJuvenileId(drugTesting.getJuvenileId());
		responseEvent.setActivityId(drugTesting.getActivityId());
		responseEvent.setCreateUser(drugTesting.getCreateUserID());
		responseEvent.setUpdateUser(drugTesting.getUpdateUserID());
		responseEvent.setUpdateDate(drugTesting.getUpdateTimestamp());
		dispatch.postEvent(responseEvent);
	    }
	    
	} else {
	    Iterator drugInfoIter= DrugTesting.findAll("juvenileId", getEvent.getJuvenileId());
		   
		
        	while( drugInfoIter.hasNext() ) {
        	    DrugTesting drugTesting = (DrugTesting)drugInfoIter.next();
        	    DrugTestingResponseEvent responseEvent = new DrugTestingResponseEvent();
        	    
        	    if ( drugTesting != null ){
        		responseEvent.setDrugTestingId(drugTesting.getOID());
        		responseEvent.setAssociateCasefile(drugTesting.getAssociateCasefile());
        		responseEvent.setTestAdministered(drugTesting.getTestAdministered());
        		responseEvent.setTestDate(drugTesting.getTestDate());
        		responseEvent.setTestTime(drugTesting.getTestTime());
        		responseEvent.setSubstanceTested(drugTesting.getSubstanceTested());
        		responseEvent.setDrugTestResults(drugTesting.getDrugTestResults());
        		responseEvent.setTestLocation(drugTesting.getTestLocation());
        		responseEvent.setAdministeredBy(drugTesting.getAdministeredBy());		
        		responseEvent.setComments(drugTesting.getComments());
        		responseEvent.setCreateDate(drugTesting.getCreateTimestamp());
        		responseEvent.setJuvenileId(drugTesting.getJuvenileId());
        		responseEvent.setActivityId(drugTesting.getActivityId());
        		responseEvent.setCreateUser(drugTesting.getCreateUserID());
        		dispatch.postEvent(responseEvent);
        	    }
        	}
	}
    }
}
