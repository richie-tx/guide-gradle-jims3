package pd.productionsupport.transactions;

import java.util.Iterator;

import pd.juvenilecase.casefile.CasefileClosingInfo;
import pd.juvenilecase.casefile.CommonAppDocMetadata;
import messaging.productionsupport.MoveCommonAppReportEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

public class MoveCommonAppReportCommand implements ICommand 
{
    public MoveCommonAppReportCommand(){}
    
    public void execute(IEvent event) 
    {
	MoveCommonAppReportEvent moveEvent = (MoveCommonAppReportEvent) event;
	if ( moveEvent.getCasefileId() != null
		&& moveEvent.getCasefileId().length() > 0 
		&& moveEvent.getCommonAppDocumentId() != null
		&& moveEvent.getCommonAppDocumentId().length() > 0 ){
	    CommonAppDocMetadata document = CommonAppDocMetadata.find(moveEvent.getCommonAppDocumentId());
	    if ( document != null ){
		document.setCasefileId(moveEvent.getCasefileId());
		IHome home = new Home();
		home.bind( document );
		document = null;
	    }
	    
	    if ( moveEvent.getOldCasefileId() != null
		    && moveEvent.getOldCasefileId().length() > 0 ){
		Iterator casefileClosingInfoIter =  CasefileClosingInfo.findAll("supervisionNumber",  moveEvent.getOldCasefileId());
		
		if ( casefileClosingInfoIter.hasNext() ) {
		    CasefileClosingInfo casefileClosingInfo = (CasefileClosingInfo) casefileClosingInfoIter.next();
		    casefileClosingInfo.setSupervisionNumber( moveEvent.getCasefileId() );
		    IHome home = new Home();
		    home.bind( casefileClosingInfo );
		    casefileClosingInfo = null;
			
		}
	    }
	    
	}
    }

}
