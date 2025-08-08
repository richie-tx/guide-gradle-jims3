package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.error.reply.ErrorResponseEvent;
import messaging.productionsupport.GetProductionSupportJuvenileReferralsEvent;
import messaging.productionsupport.UpdateProductionSupportJuvenileReferralEvent;
import messaging.productionsupport.reply.ProductionSupportJuvenileReferralResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author rcarter
 */

public class PerformUpdateMsReferralAction extends Action {
	
    	private Logger log = Logger.getLogger("PerformUpdateReferralAction");
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		String logonid = SecurityUIHelper.getLogonId();		
		
		//ProductionSupportJuvenileReferralResponseEvent record = retrieveRecord(regform);
		ProductionSupportJuvenileReferralResponseEvent record =  regform.getJuvprogref();
				
		if (record==null){
			regform.setMsg("PerformUpdateReferralAction.java (73) - Could not retrieve record.");
			return (mapping.findForward("error"));
		}
		
		if( !record.getJuvenileNum().equals( regform.getJuvenileId() )){
		    
		    // Check to see if duplicate record exist
		    GetProductionSupportJuvenileReferralsEvent getJuvenileRerralsEvent = (GetProductionSupportJuvenileReferralsEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTJUVENILEREFERRALS);
		    getJuvenileRerralsEvent.setJuvenileId( record.getJuvenileNum() );
		    getJuvenileRerralsEvent.setReferralNum( record.getReferralNum() );
		    ArrayList listEvent = (ArrayList) MessageUtil.postRequestListFilter(getJuvenileRerralsEvent, ProductionSupportJuvenileReferralResponseEvent.class);
		    if( listEvent.size() > 0 ){
			
			regform.setMsg("The current juvenile number and modified juvenile number includes overlapping referral number." +
					"\nPlease verify juvenile referral number.");
			
		    }

		}
		
		// Check if trying to close referral
/*		if( record.getCloseDate() != null && StringUtils.isNotBlank(record.getCloseDate() ) ){
		    
		    GetCasefilesForReferralsEvent req = (GetCasefilesForReferralsEvent)
				EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETCASEFILESFORREFERRALS);
		    req.setJuvenileNum( record.getJuvenileNum() );
		    req.setReferralNum( record.getReferralNum() );

		    JuvenileProfileReferralListResponseEvent activeList = (JuvenileProfileReferralListResponseEvent) 
			    			MessageUtil.postRequest(req, JuvenileProfileReferralListResponseEvent.class);
		    if( activeList.getCasefileReferrals()!=null ){
			
			Iterator<JuvenileCasefileReferral> iter = activeList.getCasefileReferrals().iterator();	
			while(iter.hasNext()){
			    
			    JuvenileCasefileReferral casefile = iter.next();
			    if("A".equalsIgnoreCase( casefile.getCaseStatusCd())){
				
				regform.setMsg("There is still an active Casefile associated to this Referral.");
				return mapping.findForward("error");
			    }
			}
		    }
		    		    
		}*/
		
		 
	 	 
	 	 
		// prepare event
		UpdateProductionSupportJuvenileReferralEvent updateReferral = 
				(UpdateProductionSupportJuvenileReferralEvent) 
			EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTJUVENILEREFERRAL) ;
			
			// set the referral number
			updateReferral.setReferralNum( record.getReferralNum() );
			updateReferral.setOID( record.getReferralOID() );
			
			// set values to update			 
			updateReferral.setJuvenileNum( record.getJuvenileNum() );
			updateReferral.setPrevJuvenileNum( regform.getFromJuvenileId() );
			updateReferral.setCloseDate(record.getCloseDate());
			updateReferral.setIntakeDate(record.getIntakeDate());
			updateReferral.setCourtDisposition( record.getCourtDisposition() );
			updateReferral.setCourtId( record.getCourtId() );
			updateReferral.setCourtResult( record.getCourtResult() );
			updateReferral.setDaLogNum( record.getDaLogNum() );
			updateReferral.setDispositionDate( record.getDispositionDate() );
			updateReferral.setIntakeDecision( record.getIntakeDecision() );
			updateReferral.setPiaStatus( record.getPiaStatus() );
			updateReferral.setReferralDate( record.getReferralDate() );
			updateReferral.setReferralOfficer( record.getReferralOfficer() );
			updateReferral.setReferralSource( record.getReferralSource() );
			updateReferral.setTransNum(record.getTransNum() );
			updateReferral.setViolationProbation(record.getViolationProbation() );
			updateReferral.setCtAssignJPOId(record.getCtAssignJPOId());
			updateReferral.setReferralTypeInd(record.getReferralTypeInd());
			updateReferral.setProbationStartDate(record.getProbationStartDate());
			updateReferral.setProbationEndDate(record.getProbationEndDate());
			updateReferral.setPdaDate(record.getPdaDate());
			/// task 172857
			updateReferral.setCountyREFD(record.getCountyREFD());
			updateReferral.setTJJDReferralDate(record.getTJJDreferralDate());
			updateReferral.setJpoId( record.getJpoId() );
			updateReferral.setOffenseTotal( record.getOffenseTotal() );
			updateReferral.setProbationJpoId( record.getProbationJpoId() );
			updateReferral.setDecisionType( record.getDecisionType() );
			
			
			
			CompositeResponse compResp = MessageUtil.postRequest( updateReferral );
			Object errResp = MessageUtil.filterComposite(compResp, ErrorResponseEvent.class);
			if (errResp != null){
			    
				log.info("UPDATE JUVENILE REFERRAL MASTER: " + SecurityUIHelper.getLogonId());
				return mapping.findForward("success");
			}else{
				return mapping.findForward("error");
			}

	}
	
	/**
	 * Returns first record only
	 * @param regform
	 * @return
	 */
	private ProductionSupportJuvenileReferralResponseEvent retrieveRecord(ProdSupportForm regform){
		
		ArrayList juvprogrefs = regform.getJuvprogrefs();
		ProductionSupportJuvenileReferralResponseEvent record = null;
		
		Iterator iter = juvprogrefs.iterator();
		if (iter.hasNext())
		{
			record = (ProductionSupportJuvenileReferralResponseEvent)iter.next();
		}
		
		return record;
	}

}
