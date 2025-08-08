//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilewarrant\\transactions\\GetJuvenileCasefileDispositionsCommand.java

package pd.juvenilewarrant.transactions;


import java.util.Iterator;

import messaging.juvenilewarrant.GetJuvenileCasefileDispositionListEvent;
import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingDispositionResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.util.DateUtil;
import pd.juvenilewarrant.JuvenileOffenderTrackingDisposition;

public class GetJuvenileCasefileDispositionListCommand implements ICommand 
{
   
   /**
    * @roseuid 467FB2AC012D
    */
   public GetJuvenileCasefileDispositionListCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 467AD34801C4
    */
   public void execute(IEvent event) 
   {
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   		GetJuvenileCasefileDispositionListEvent disp = (GetJuvenileCasefileDispositionListEvent) event;
   		Iterator dispIter = JuvenileOffenderTrackingDisposition.findAll("petitionNum", disp.getPetitionNum());
   		while(dispIter.hasNext())
   		{
   			JuvenileOffenderTrackingDisposition disposition = (JuvenileOffenderTrackingDisposition)dispIter.next();
   			JuvenileOffenderTrackingDispositionResponseEvent resp = getResponseEvent(disposition);   			
   			dispatch.postEvent(resp);
   		}
   }
   
   private JuvenileOffenderTrackingDispositionResponseEvent getResponseEvent(JuvenileOffenderTrackingDisposition disposition)
   {
   		JuvenileOffenderTrackingDispositionResponseEvent resp = new JuvenileOffenderTrackingDispositionResponseEvent();
   		resp.setJudgementDate(DateUtil.IntToDate( disposition.getJudgementDate(),DateUtil.DATE_FMT_2));
		String placement = disposition.getPlacementId();
		String withheld = disposition.getTycWithheldId();
		String needSupervision = disposition.getNeedSupervisionId();		
		if(placement != null && !placement.equals(""))
			resp.setDisposition(disposition.getPlacement().getDescription());
		else if(withheld != null && !withheld.equals(""))
			resp.setDisposition(disposition.getTycWithheld().getDescription());   			
		else if(needSupervision != null && !needSupervision.equals(""))
			resp.setDisposition(disposition.getNeedSupervision().getDescription());   			
		String judgementId = disposition.getJudgementId();
		if(judgementId!=null && !judgementId.equals(""))
			resp.setJudgement(disposition.getJudgement().getDescription());
		resp.setDispositionDate(DateUtil.IntToDate( disposition.getDispositionDate(),DateUtil.DATE_FMT_2 ));
		resp.setSeqNum(disposition.getSeqNum());
		/*String probationInd = disposition.getProbationInd();
		Integer temp;
		int varForProbOrTYC=0;
		if(probationInd.equalsIgnoreCase(PDJuvenileWarrantConstants.DM_DISP_IND_Y))
		{
			temp=new Integer(disposition.getProbationTime());
			varForProbOrTYC=temp.intValue()*12;
			resp.setProbationTime(""+varForProbOrTYC);
		}
		else if(probationInd.equalsIgnoreCase(PDJuvenileWarrantConstants.DM_DISP_IND_M))
			resp.setProbationTime(disposition.getProbationTime());
		else if(probationInd.equalsIgnoreCase(PDJuvenileWarrantConstants.DM_DISP_IND_D))
		{
			temp=new Integer(disposition.getProbationTime());
			varForProbOrTYC=temp.intValue()/30;
			resp.setProbationTime(""+varForProbOrTYC);
		}*/
		if(disposition.getDeviationReason()!=null)
			resp.setDeviationReason(disposition.getDeviationReason().getDescription());
		resp.setGuidelineSanction("Level " +disposition.getGuidelineSanction());
		resp.setAssignedSanction("Level " +disposition.getAssignedSanction());
		/*varForProbOrTYC=0;
		String tycInd = disposition.getTYCInd();
		if(tycInd.equalsIgnoreCase(PDJuvenileWarrantConstants.DM_DISP_IND_Y))
		{
			temp=new Integer(disposition.getTYCTime());
			varForProbOrTYC=temp.intValue()*12;
			resp.setTYCTime(""+varForProbOrTYC);
		}
		else if(tycInd.equalsIgnoreCase(PDJuvenileWarrantConstants.DM_DISP_IND_M))
			resp.setTYCTime(disposition.getTYCTime());
		else if(tycInd.equalsIgnoreCase(PDJuvenileWarrantConstants.DM_DISP_IND_D))
		{
			temp=new Integer(disposition.getTYCTime());
			varForProbOrTYC=temp.intValue()/30;
			resp.setTYCTime(""+varForProbOrTYC);
		}*/
		resp.setDaLogNum(disposition.getDaLogNum());
		return resp;
   }
   
   /**
    * @param event
    * @roseuid 467AD34801C6
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 467AD3480212
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 467AD3480250
    */
   public void update(Object anObject) 
   {
    
   }
   
   
}
