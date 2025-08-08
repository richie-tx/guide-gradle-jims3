package pd.productionsupport.transactions;


import java.util.Calendar;
import java.util.Iterator;

import messaging.productionsupport.GetTransOffenseReferralsQueryEvent;
import messaging.productionsupport.UpdateProductionSupportJuvenileReferralEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.DateUtil;
import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilecase.referral.JJSTransferredOffenseReferral;
import pd.security.PDSecurityHelper;

public class UpdateProductionSupportJuvenileReferralCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public UpdateProductionSupportJuvenileReferralCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
       
	   UpdateProductionSupportJuvenileReferralEvent updateEvent = (UpdateProductionSupportJuvenileReferralEvent) event;
	   
	   Iterator<JJSReferral> refIter = JJSReferral.findAll("OID", updateEvent.getOID());
	   
	   if( refIter.hasNext()){
	       
	       JJSReferral foundRec = refIter.next();

	       foundRec.setJuvenileNum( updateEvent.getJuvenileNum() );
	       foundRec.setReferralNum( updateEvent.getReferralNum() );
	       foundRec.setCloseDate( DateUtil.stringToDate( updateEvent.getCloseDate(), DateUtil.DATE_FMT_1 ));
	       foundRec.setIntakeDate( DateUtil.stringToDate( updateEvent.getIntakeDate(), DateUtil.DATE_FMT_1 ) );
	       foundRec.setCourtDispositionId( updateEvent.getCourtDisposition() );
	       foundRec.setCourtId( updateEvent.getCourtId() );
	       foundRec.setCourtResultId( updateEvent.getCourtResult() );
	       foundRec.setDaLogNum( updateEvent.getDaLogNum() );
	       foundRec.setCourtDate( DateUtil.stringToDate( updateEvent.getDispositionDate(), DateUtil.DATE_FMT_1 ) );
	       foundRec.setIntakeDecisionId( updateEvent.getIntakeDecision() );
	       foundRec.setPIACode( updateEvent.getPiaStatus() );
	       foundRec.setReferralDate( DateUtil.stringToDate( updateEvent.getReferralDate(), DateUtil.DATE_FMT_1 ) );
	       foundRec.setReferralOfficer( updateEvent.getReferralOfficer() );
	       foundRec.setReferralSource( updateEvent.getReferralSource() );
	       foundRec.setTransactionNum( updateEvent.getTransNum() );
	       foundRec.setProbationViolation( updateEvent.getViolationProbation() );
	       //Bug #93012
	       foundRec.setLcUser(PDSecurityHelper.getLogonId());
	       foundRec.setLcDate(DateUtil.getCurrentDate());
	       foundRec.setLcTime(Calendar.getInstance().getTime());
	       foundRec.setReferralTypeInd(updateEvent.getReferralTypeInd());
	       
	       //task 171521 commenting below as countyrefd is editable from screen
	       /*if ( updateEvent.getReferralTypeInd() != null)
   	    	{
   	    	    if(updateEvent.getReferralTypeInd().equalsIgnoreCase("IC"))
   	    		foundRec.setCountyREFD("755");
   	    	    else if(updateEvent.getReferralTypeInd().equalsIgnoreCase("CD"))
   	    		foundRec.setCountyREFD("756");
   	    	    else if(updateEvent.getReferralTypeInd().equalsIgnoreCase("TR"))
   	    	    {
   	    		String countyId="";
   	    		GetTransOffenseReferralsQueryEvent getEvent = new GetTransOffenseReferralsQueryEvent();
   	    		getEvent.setJuvenileNumber(updateEvent.getJuvenileNum());
   	    		getEvent.setReferralNumber(updateEvent.getReferralNum());
                   	Iterator<JJSTransferredOffenseReferral> transOffenseReferralsIter = JJSTransferredOffenseReferral.findAll(getEvent);                	    
                   	while(transOffenseReferralsIter.hasNext()) 
                   	{
                   	    JJSTransferredOffenseReferral transOffenseReferral = (JJSTransferredOffenseReferral) transOffenseReferralsIter.next();
                   	    countyId=transOffenseReferral.getFromCountyCode();
                   	    foundRec.setCountyREFD(countyId);
                   	}
                   	   
   	    	    }
   	    	    else
   	    		foundRec.setCountyREFD("101");
   	    	}*/
	       //
	       foundRec.setCtAssignJPOId(updateEvent.getCtAssignJPOId());
	       foundRec.setProbationStartDate( DateUtil.stringToDate( updateEvent.getProbationStartDate(), DateUtil.DATE_FMT_1 ) );
	       foundRec.setProbationEndDate( DateUtil.stringToDate( updateEvent.getProbationEndDate(), DateUtil.DATE_FMT_1 ) );
	       foundRec.setPDADate( DateUtil.stringToDate( updateEvent.getPdaDate(), DateUtil.DATE_FMT_1 ) );
	       /// task 172857
	       foundRec.setTJJDReferralDate(DateUtil.stringToDate( updateEvent.getTJJDReferralDate(), DateUtil.DATE_FMT_1 ) );
	       foundRec.setCountyREFD(updateEvent.getCountyREFD());
	       foundRec.setJpoid( updateEvent.getJpoId() );
	       foundRec.setOffenseTotal( updateEvent.getOffenseTotal() );
	       foundRec.setProbJPOId( updateEvent.getProbationJpoId() );
	       foundRec.setDecisionType( updateEvent.getDecisionType() );
	       IHome home = new Home();
	       home.bind( foundRec );
	       System.out.println("updateProductionSupportJuvenileReferral");
	       foundRec = null;
	       
	   }
   }
   
   /**
    * @param event
    * @roseuid 44C8E0DB02F0
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 44C8E0DB02FD
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 44C8E0DB02FF
    */
   public void update(Object anObject) 
   {
    
   } 
}
