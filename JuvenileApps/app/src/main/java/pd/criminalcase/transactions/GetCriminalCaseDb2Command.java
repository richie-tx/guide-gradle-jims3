package pd.criminalcase.transactions;

import java.util.Iterator;

import pd.criminalcase.CriminalCaseDb2;
import messaging.criminalcase.GetCriminalCaseDb2Event;
import messaging.criminalcase.reply.CriminalCaseDb2ResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

/**
 * 
 * @author RYoung
 *
 */
public class GetCriminalCaseDb2Command implements ICommand {
	
	
	public void execute(IEvent event) 
	   {
		
		GetCriminalCaseDb2Event requestEvent = (GetCriminalCaseDb2Event)event;
		
		Iterator caseIter = CriminalCaseDb2.findAll( requestEvent );
		
			if ( caseIter.hasNext() ){
				
				CriminalCaseDb2 crimCase = ( CriminalCaseDb2 ) caseIter.next();
				CriminalCaseDb2ResponseEvent crimCaseResp = this.buildResponse( crimCase );
				
				MessageUtil.postReply( crimCaseResp );
			}

	   }
	
	
	public CriminalCaseDb2ResponseEvent buildResponse( CriminalCaseDb2 caseDb2 ){
		
		CriminalCaseDb2ResponseEvent response = new CriminalCaseDb2ResponseEvent();
		
		response.setCaseFilingDate( caseDb2.getFilingDate() );
		response.setCaseNum( caseDb2.getCaseNum() );
		response.setCaseStatusId( caseDb2.getCaseStatusId() );
		response.setCourtId( caseDb2.getCourtId() );
		response.setCriminalCaseId( caseDb2.getCriminalCaseId());
		response.setDefendantId( caseDb2.getDefendantId() );
		response.setDefendantName( caseDb2.getDefendantName() );
		response.setDefendantStatusId( caseDb2.getDefendantStatusId() );
		response.setOffenseCodeId( caseDb2.getOffenseCodeId() );
		response.setProbationOfficerId( caseDb2.getProbationOfficerId() );
		response.setSequenceNum( caseDb2.getSequenceNum());
		response.setStreetName( caseDb2.getStreetName());
		response.setAdSequenceNum( caseDb2.getAdSequenceNum());
		response.setCity( caseDb2.getCity());
		response.setZipCode( caseDb2.getZipCode() );
				
		return response;
	
	}

}
