package pd.supervision.administercompliance.transactions.daos;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import messaging.administercompliance.GetNCFAS1FeesEvent;
import messaging.administercompliance.GetNCFeesEvent;
import messaging.administercompliance.GetNCResponseComponentsEvent;
import messaging.administercompliance.RefreshNCResponseComponentsEvent;
import messaging.administercompliance.UpdateNCFeeEvent;
import messaging.administercompliance.UpdateNCResponseEvent;
import messaging.administercompliance.reply.NCFeeResponseEvent;
import mojo.km.messaging.RequestEvent;
import mojo.km.utilities.MessageUtil;
import naming.ViolationReportConstants;
import pd.codetable.Code;
import pd.common.DAOHandler;
import pd.supervision.administercompliance.Comment;
import pd.supervision.administercompliance.DelinquentFee;
import pd.supervision.administercompliance.ViolationReport;
import pd.supervision.administercompliance.ViolationReportUtility;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
/**
 * When a condition is set to non-compliant, the event(s) leading to this are
 * documented.  Event Types come from Events configured in the Condition in MSO.
 */
public class DelinquentFeeDAO extends ViolationReportUtility implements DAOHandler
{
	/**
	 * 
	 * @roseuid 473B85EB0040
	 */
	public DelinquentFeeDAO()
	{
	}
	
	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#execute(java.lang.Object)
	 */
	public void execute(Object object) {
		postDb2Data(object.toString());
	}
	
	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#remove(java.lang.Object)
	 */
	public void remove(Object object) {
		deleteJims2Data(object.toString());
	}

	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#update(java.lang.Object)
	 */
	public void update(Object object) {
		UpdateNCResponseEvent reqEvent = (UpdateNCResponseEvent) object;
		createViolationReport(reqEvent);
		
		Enumeration enumer = reqEvent.getRequests();
		DelinquentFee fee = null;
		
		Map existingMap = DelinquentFee.findAllByNumericParameter(ViolationReportConstants.PARAM_NCRESPONSE_ID, reqEvent.getNcResponseId()); 
		
		while(enumer.hasMoreElements()){
			UpdateNCFeeEvent fEvent = (UpdateNCFeeEvent) enumer.nextElement();
			if(fEvent.getNcFeeId() == null || fEvent.getNcFeeId().equals("")){
				fee = new DelinquentFee();
				fee.setDelinquentFee(fEvent,reqEvent.getNcResponseId());
				fee.commit();
			}else{
				fee = DelinquentFee.find(fEvent.getNcFeeId());
				if(fee != null && existingMap.containsKey(fee.getOID())){
					existingMap.remove(fee.getOID());
				}
			}
     	}
		setComments (ViolationReportConstants.REQUEST_DELINQUENT_FEE, reqEvent.getNcResponseId(), reqEvent);
        // at this point existingmap only contains unwanted stuff
		Iterator unwantedIter = existingMap.values().iterator();
		while(unwantedIter.hasNext()){
			fee = (DelinquentFee) unwantedIter.next();
			if(fee != null){
				fee.delete();
				fee.commit();
			}
		}
		
		// post the current data
		postDb2Data(reqEvent.getNcResponseId());
	}

	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#get(java.lang.Object)
	 */
	public void get(Object object) {
		GetNCResponseComponentsEvent reqEvent = (GetNCResponseComponentsEvent) object;
		if(ViolationReportConstants.CREATE_MODE.equalsIgnoreCase(reqEvent.getMode())){
			
			Enumeration requests = reqEvent.getRequests();
			while ( requests.hasMoreElements() ){
				
				RequestEvent requestEvent = (RequestEvent) requests.nextElement();
				if ( requestEvent instanceof GetNCFeesEvent ){
					
					postLegacyData(reqEvent.getNcResponseId(),requestEvent );	
					
				}
				else if ( requestEvent instanceof GetNCFAS1FeesEvent ){
					
					postLegacyFas1Data( reqEvent.getNcResponseId(), requestEvent );
				}
			}
		}else{
			this.postDb2Data(reqEvent.getNcResponseId());
			postComments(ViolationReportConstants.REQUEST_DELINQUENT_FEE,reqEvent.getNcResponseId());
		}
	}
	

	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#refresh(java.lang.Object)
	 */
	public void refresh(Object object) {
		RefreshNCResponseComponentsEvent refEvent = (RefreshNCResponseComponentsEvent) object;
		
		// delete jims2 data
		ViolationReport vr = ViolationReport.find(refEvent.getNcResponseId());
		if(vr != null){
			deleteJims2Data(refEvent.getNcResponseId());
			Comment comment = getComments(ViolationReportConstants.REQUEST_DELINQUENT_FEE, refEvent.getNcResponseId());
			if(comment != null){
				comment.delete();
			}		
		}
		
		// retrieve from Legacy
		GetNCFeesEvent gEvent = new GetNCFeesEvent();
		gEvent.setCaseId(refEvent.getCaseId());
		gEvent.setCdi(refEvent.getCdi());
		postLegacyData(refEvent.getNcResponseId(),gEvent);
		
		GetNCFAS1FeesEvent fas1Event = new GetNCFAS1FeesEvent();
		fas1Event.setCaseId(refEvent.getCaseId());
		fas1Event.setCdi(refEvent.getCdi());
		fas1Event.setSpn( refEvent.getDefendantId() );
		
		postLegacyFas1Data( refEvent.getNcResponseId(),fas1Event );
	}
	
	/**
	 * @param ncResponseId
	 * @return
	 */
	private boolean postDb2Data(String ncResponseId){
		boolean isExist = false;
		Iterator iterator = DelinquentFee.findAllByNumericParam(ViolationReportConstants.PARAM_NCRESPONSE_ID, ncResponseId); 
		while(iterator.hasNext()){
			DelinquentFee d = (DelinquentFee) iterator.next();
			NCFeeResponseEvent resp = d.getResponse();
			MessageUtil.postReply(resp);
			isExist = true;
		}
		return isExist;
	}

	/**
	 * @param event
	 * @param ncResponseId
	 */
	private void postLegacyData(String ncResponseId, Object object) {
		GetNCFeesEvent gEvent = (GetNCFeesEvent) object;		
		Iterator iterator = DelinquentFee.findAll(gEvent); 
		while(iterator.hasNext()){
			DelinquentFee df = (DelinquentFee) iterator.next();
//			if((df.getAmountDelinquent() != null && !df.getAmountDelinquent().equals("00000000")) && (df.getAmountOrdered() != null && !df.getAmountOrdered().equals("00000000")) && (df.getPaidToDate() != null && !df.getPaidToDate().equals("00000000")) && (df.getPayType() != null && !df.getPayType().trim().equals(""))){
			if(df.getAmountDelinquent() != null && df.getAmountOrdered() != null && df.getPaidToDate() != null && df.getPayType() != null) {				
				NCFeeResponseEvent resp = df.getLegacyResponse();
				resp.setNcResponseId(ncResponseId);
				resp.setFeeId(new StringBuffer("L").append(resp.getFeeId()).toString());
				MessageUtil.postReply(resp);
			}
		}
	}	
	
	/**
	 * @param event
	 * @param ncResponseId
	 */
	private void postLegacyFas1Data(String ncResponseId, Object object) {
		
		GetNCFAS1FeesEvent gEvent = (GetNCFAS1FeesEvent) object;		
		Iterator iterator = DelinquentFee.findAll(gEvent); 
		
		while(iterator.hasNext()){
			DelinquentFee df = (DelinquentFee) iterator.next();
			String amtOrderedTrim = df.getAmountOrdered().trim();
			String amtDelinquentTrim = df.getAmountDelinquent().trim();
			String amtPaidTrim = df.getPaidToDate().trim();
			
			if( !"".equals( amtDelinquentTrim ) && !"".equals( amtOrderedTrim )
					&& !"".equals( amtPaidTrim ) && df.getPayType() != null) {				
				NCFeeResponseEvent resp = df.getLegacyResponse();
				resp.setNcResponseId( ncResponseId );
				String payType = resp.getPayType().trim();
				
				Code code = Code.find( "PAY_TYPE", payType );

				if (code != null)
				{
					resp.setPayType( code.getDescription() );
				}
				resp.setFeeId(new StringBuffer("L").append(resp.getFeeId()).toString());
				MessageUtil.postReply(resp);
			}
		}
	}	
	
	/**
	 * @param ncResponseId
	 */
	private void deleteJims2Data(String ncResponseId) {
		Iterator iterator = DelinquentFee.findAllByNumericParam(ViolationReportConstants.PARAM_NCRESPONSE_ID, ncResponseId); 
		while(iterator.hasNext()){
			DelinquentFee fee = (DelinquentFee) iterator.next();
		    fee.delete();
		}		
	}
}