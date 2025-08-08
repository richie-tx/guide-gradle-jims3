package pd.productionsupport.transactions;

import java.util.Date;
import java.util.Iterator;

import messaging.productionsupport.UpdateProductionSupportCasefileClosingEvent;
import messaging.productionsupport.reply.ProductionSupportCalendarResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.common.calendar.CalendarEvent;
import pd.juvenilecase.casefile.CasefileClosingInfo;

public class UpdateProductionSupportCasefileClosingCommand implements ICommand
{

    /**
     * @roseuid 452BA24800A2
     */
    public UpdateProductionSupportCasefileClosingCommand()
    {

    }

    /**
     * @param event
     * @roseuid 4526B083011C
     */
    public void execute(IEvent event)
    {
    	System.out.println("updateProductionSupportCasefileClosing");
    	UpdateProductionSupportCasefileClosingEvent updateCasefileClosingEvent = (UpdateProductionSupportCasefileClosingEvent) event;
    	CasefileClosingInfo casefileClosing = null;
    	
    	String closingInfoID = updateCasefileClosingEvent.getCasefileClosingInfoId();
    	Iterator<CasefileClosingInfo> casefileClosingIter =  null;
    	// if oid present find by oid
		if( closingInfoID != null &&  closingInfoID.length() > 0 )
		{
			casefileClosing = CasefileClosingInfo.find( closingInfoID ) ;
		}
		// else check if a merge - if so then casefile merge - find by caseFileId
		else if(updateCasefileClosingEvent.getCasefileId() != null && !(updateCasefileClosingEvent.getCasefileId().equals("")) && 
    			updateCasefileClosingEvent.getMergeToCasefileId() != null && !(updateCasefileClosingEvent.getMergeToCasefileId().equals(""))) {
    			
			casefileClosingIter =  CasefileClosingInfo.findAll("supervisionNumber",updateCasefileClosingEvent.getCasefileId());
    		while(casefileClosingIter.hasNext()){
    			casefileClosing = (CasefileClosingInfo)casefileClosingIter.next();
    			break;
    		}
			
			casefileClosing.setSupervisionNumber(updateCasefileClosingEvent.getMergeToCasefileId());
		}
    	// case file closing based on oid
		if(updateCasefileClosingEvent.getSupervisionEndDate() != null){
			casefileClosing.setSupervisionEndDate(updateCasefileClosingEvent.getSupervisionEndDate());
		}
		if(updateCasefileClosingEvent.getControllingReferralId() != null && !updateCasefileClosingEvent.getControllingReferralId().equals("")){
			casefileClosing.setControllingReferralId(updateCasefileClosingEvent.getControllingReferralId());
		}
		if(updateCasefileClosingEvent.getSupervisionOutcomeId() != null && !updateCasefileClosingEvent.getSupervisionOutcomeId().equals("")){
			casefileClosing.setSupervisionOutcomeId(updateCasefileClosingEvent.getSupervisionOutcomeId());
		}
		if(updateCasefileClosingEvent.getSupervisionOutcomeDescriptionId() != null && !updateCasefileClosingEvent.getSupervisionOutcomeDescriptionId().equals("")){
			casefileClosing.setSupervisionOutcomeDescriptionId(updateCasefileClosingEvent.getSupervisionOutcomeDescriptionId());
		}
		if(updateCasefileClosingEvent.getRecordCLM() != null){
			casefileClosing.setRecordCLM(updateCasefileClosingEvent.getRecordCLM());
		}
		if(updateCasefileClosingEvent.getJuvLocUnitId() != null){
			casefileClosing.setJuvLocUnitId(updateCasefileClosingEvent.getJuvLocUnitId());
		}
    }

    /**
     * @param event
     * @roseuid 4526B083011E
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 4526B083012B
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 4526B083012D
     */
    public void update(Object anObject)
    {

    }

}
